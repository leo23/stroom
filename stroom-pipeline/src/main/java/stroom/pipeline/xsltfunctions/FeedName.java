/*
 * Copyright 2016 Crown Copyright
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

package stroom.pipeline.xsltfunctions;

import net.sf.saxon.expr.XPathContext;
import net.sf.saxon.om.EmptyAtomicSequence;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.value.StringValue;
import stroom.feed.shared.Feed;
import stroom.pipeline.state.FeedHolder;
import stroom.util.shared.Severity;

import javax.inject.Inject;

class FeedName extends StroomExtensionFunctionCall {
    private final FeedHolder feedHolder;

    @Inject
    FeedName(final FeedHolder feedHolder) {
        this.feedHolder = feedHolder;
    }

    @Override
    protected Sequence call(String functionName, XPathContext context, Sequence[] arguments) {
        String result = null;

        try {
            final Feed feed = feedHolder.getFeed();
            if (feed != null) {
                result = feed.getName();
            }
        } catch (final Exception e) {
            log(context, Severity.ERROR, e.getMessage(), e);
        }

        if (result == null) {
            return EmptyAtomicSequence.getInstance();
        }
        return StringValue.makeStringValue(result);
    }
}
