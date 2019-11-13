/*
 * Copyright 2017 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package stroom.kafkademo.pipeline;

import net.sf.saxon.event.ReceivingContentHandler;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import stroom.docref.DocRef;
import stroom.index.shared.IndexDoc;
import stroom.kafkaConfig.shared.KafkaConfigDoc;
import stroom.pipeline.LocationFactoryProxy;
import stroom.pipeline.errorhandler.ErrorReceiverProxy;
import stroom.pipeline.errorhandler.LoggedException;
import stroom.pipeline.factory.ConfigurableElement;
import stroom.pipeline.factory.PipelineProperty;
import stroom.pipeline.factory.PipelinePropertyDocRef;
import stroom.pipeline.filter.AbstractXMLFilter;
import stroom.pipeline.shared.ElementIcons;
import stroom.pipeline.shared.data.PipelineElementType;
import stroom.util.logging.LogUtil;
import stroom.util.shared.Severity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


@ConfigurableElement(
        type = "StandardKafkaProducer",
        category = PipelineElementType.Category.DESTINATION,
        roles = {PipelineElementType.ROLE_TARGET,
                PipelineElementType.ROLE_HAS_TARGETS,
                PipelineElementType.VISABILITY_SIMPLE},
        icon = ElementIcons.KAFKA)
class StandardKafkaProducer extends AbstractXMLFilter {

    private static class KafkaMessageState{
        public String partition = null;
        public String topic = null;
        public String key = null;
        public ArrayList<String> headerNames = new ArrayList();
        public ArrayList<String> headerVals = new ArrayList();
        public String timestamp = null;
        public String messageValue = null;
        public boolean inHeader = false;
        public String lastElement = null;

        public String whyInvalid (){
            if (topic == null)
                return "Topic is not defined.";
            if (key == null && messageValue == null)
                return "Neither the key or the value of the message are defined.";
            if (headerNames.size() != headerVals.size())
                return "Incomplete header information.";

            return null;
        }
    }


    private static final String RECORD_ELEMENT_LOCAL_NAME = "kafkaRecord";
    private static final String HEADER_ELEMENT_LOCAL_NAME = "header";
    private static final String KEY_ELEMENT_LOCAL_NAME = "key";
    private static final String VALUE_ELEMENT_LOCAL_NAME = "value";
    private static final String TOPIC_ATTRIBUTE_LOCAL_NAME = "topic";
    private static final String TIMESTAMP_ATTRIBUTE_LOCAL_NAME = "timestamp";
    private static final String PARTITION_ATTRIBUTE_LOCAL_NAME = "partition";

    private final ErrorReceiverProxy errorReceiverProxy;
    private final LocationFactoryProxy locationFactory;
    private final KafkaProducerFactory stroomKafkaProducerFactory;
    private final KafkaConfigStore configStore;

    private Locator locator = null;

    private DocRef configRef = null;

    @Inject
    StandardKafkaProducer(final ErrorReceiverProxy errorReceiverProxy,
                          final LocationFactoryProxy locationFactory,
                          final KafkaProducerFactory stroomKafkaProducerFactory,
                          final KafkaConfigStore configStore) {
        this.errorReceiverProxy = errorReceiverProxy;
        this.locationFactory = locationFactory;
        this.stroomKafkaProducerFactory = stroomKafkaProducerFactory;
        this.configStore = configStore;
    }

    /**
     * Sets the locator to use when reporting errors.
     *
     * @param locator The locator to use.
     */
    @Override
    public void setDocumentLocator(final Locator locator) {
        this.locator = locator;
        super.setDocumentLocator(locator);
    }

    /**
     * Initialise
     */
    @Override
    public void startProcessing() {
        try {
            if (configRef == null) {
                log(Severity.FATAL_ERROR, "KafkaConfig has not been set", null);
                throw new LoggedException("KafkaConfig has not been set");
            }

            //Initialise connection to Kafka broker
            KafkaConfigDoc config = configStore.readDocument(configRef);

            System.out.println("Using config:");
            System.out.println(config.getData());

        } finally {
            super.startProcessing();
        }
    }

    private Random random = new Random();
    private int id = random.nextInt(99);

    private KafkaMessageState state = null;

    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes atts)
            throws SAXException {

        if (RECORD_ELEMENT_LOCAL_NAME.equals(localName)) {
            state = new KafkaMessageState();

            for (int a = 0; a < atts.getLength(); a++){
                String attName = atts.getLocalName(a);

                if (TOPIC_ATTRIBUTE_LOCAL_NAME.equals(attName)){
                    state.topic = atts.getValue(a);
                } else if (TIMESTAMP_ATTRIBUTE_LOCAL_NAME.equals(attName)){
                    state.timestamp = atts.getValue(a);
                } else if (PARTITION_ATTRIBUTE_LOCAL_NAME.equals(attName)){
                    state.partition = atts.getValue(a);
                }
            }
        }

        if (HEADER_ELEMENT_LOCAL_NAME.equals(localName)){
            state.inHeader = true;
        }

        if (state != null){
            state.lastElement = localName;
        }



        super.startElement(uri, localName, qName, atts);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String val = new String (ch, start, length);
        String element = state.lastElement;
        if (KEY_ELEMENT_LOCAL_NAME.equals(element)) {
            if (state.inHeader){
                state.headerNames.add(val);
            } else {
                state.key = val;
            }
        } else if (VALUE_ELEMENT_LOCAL_NAME.equals (element)) {
            if (state.inHeader){
                state.headerVals.add(val);
            }
            else {
                state.messageValue = val;
            }
        }

        super.characters(ch, start, length);
    }


    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        state.lastElement = null;

        if (HEADER_ELEMENT_LOCAL_NAME.equals(localName)){
            state.inHeader = false;
        } else if (RECORD_ELEMENT_LOCAL_NAME.equals(localName)) {
            createKafkaMessage (state);
            state = null;
        }

        super.endElement(uri, localName, qName);
    }

    private void createKafkaMessage(KafkaMessageState state) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Topic: " + state.topic);
        buffer.append(" Timestamp: " + state.timestamp);
        buffer.append(" Key: " + state.key);
        for (int i = 0; i < state.headerNames.size(); i++) {
            buffer.append(" Header " + state.headerNames.get(i) + " = " + state.headerVals.get(i));
        }
        buffer.append(" Value: " + state.messageValue);

        System.out.println(buffer.toString());
    }


    @PipelineProperty(description = "Kafka configuration details relating to where and how to send Kafka messages.", displayPriority = 1)
    @PipelinePropertyDocRef(types = KafkaConfigDoc.DOCUMENT_TYPE)
    public void setKafkaConfig(final DocRef configRef) {
        this.configRef = configRef;
    }

    private void log(final Severity severity, final String message, final Exception e) {
        errorReceiverProxy.log(severity, locationFactory.create(locator), getElementId(), message, e);
    }

}