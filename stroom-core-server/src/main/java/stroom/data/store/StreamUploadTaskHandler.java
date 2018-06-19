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
 *
 */

package stroom.data.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stroom.entity.shared.EntityServiceException;
import stroom.entity.util.EntityServiceExceptionUtil;
import stroom.feed.FeedDocCache;
import stroom.data.meta.api.AttributeMap;
import stroom.feed.StroomHeaderArguments;
import stroom.proxy.repo.StroomStreamProcessor;
import stroom.proxy.repo.StroomZipFile;
import stroom.proxy.repo.StroomZipFileType;
import stroom.security.Security;
import stroom.data.meta.api.StreamProperties;
import stroom.data.store.api.StreamStore;
import stroom.data.store.api.StreamTarget;
import stroom.streamstore.shared.StreamTypeNames;
import stroom.data.store.impl.fs.serializable.NestedStreamTarget;
import stroom.streamtask.StreamTargetStroomStreamHandler;
import stroom.streamtask.statistic.MetaDataStatistic;
import stroom.task.AbstractTaskHandler;
import stroom.task.TaskContext;
import stroom.task.TaskHandlerBean;
import stroom.util.date.DateUtil;
import stroom.util.io.CloseableUtil;
import stroom.util.io.StreamProgressMonitor;
import stroom.util.io.StreamUtil;
import stroom.util.shared.VoidResult;
import stroom.util.thread.BufferFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;

@TaskHandlerBean(task = StreamUploadTask.class)
class StreamUploadTaskHandler extends AbstractTaskHandler<StreamUploadTask, VoidResult> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUploadTaskHandler.class);

    private static final String AGGREGATION_DELIMITER = "_";
    private static final String FILE_SEPERATOR = ".";
    private static final String GZ = "GZ";

    private final TaskContext taskContext;
    private final StreamStore streamStore;
    private final FeedDocCache feedDocCache;
    private final MetaDataStatistic metaDataStatistics;
    private final Security security;

    @Inject
    StreamUploadTaskHandler(final TaskContext taskContext,
                            final StreamStore streamStore,
                            final FeedDocCache feedDocCache,
                            final MetaDataStatistic metaDataStatistics,
                            final Security security) {
        this.taskContext = taskContext;
        this.streamStore = streamStore;
        this.feedDocCache = feedDocCache;
        this.metaDataStatistics = metaDataStatistics;
        this.security = security;
    }

    @Override
    public VoidResult exec(final StreamUploadTask task) {
        return security.secureResult(() -> {
            taskContext.info(task.getFile().toString());
            uploadData(task);
            return VoidResult.INSTANCE;
        });
    }

    private void uploadData(final StreamUploadTask task) {
        if (task.getFeedName() == null) {
            throw new EntityServiceException("Feed not set!");
        }
        if (task.getStreamTypeName() == null) {
            throw new EntityServiceException("Stream Type not set!");
        }
        if (task.getFileName() == null) {
            throw new EntityServiceException("File not set!");
        }

        final String name = task.getFileName().toUpperCase();

        final AttributeMap attributeMap = new AttributeMap();
        if (task.getMetaData() != null && task.getMetaData().trim().length() > 0) {
            try {
                attributeMap.read(task.getMetaData().getBytes(StreamUtil.DEFAULT_CHARSET));
            } catch (final IOException e) {
                LOGGER.error("uploadData()", e);
            }
        }

        if (task.getEffectiveMs() != null) {
            attributeMap.put(StroomHeaderArguments.EFFECTIVE_TIME, DateUtil.createNormalDateTimeString(task.getEffectiveMs()));
        }
        attributeMap.put(StroomHeaderArguments.REMOTE_FILE, task.getFileName());
        attributeMap.put(StroomHeaderArguments.FEED, task.getFeedName());
        attributeMap.put(StroomHeaderArguments.RECEIVED_TIME, DateUtil.createNormalDateTimeString(System.currentTimeMillis()));
        attributeMap.put(StroomHeaderArguments.USER_AGENT, "STROOM-UI");

        if (name.endsWith(FILE_SEPERATOR + StroomHeaderArguments.COMPRESSION_ZIP)) {
            attributeMap.put(StroomHeaderArguments.COMPRESSION, StroomHeaderArguments.COMPRESSION_ZIP);
            uploadZipFile(taskContext, task, attributeMap);
        } else {
            if (name.endsWith(FILE_SEPERATOR + StroomHeaderArguments.COMPRESSION_GZIP)) {
                attributeMap.put(StroomHeaderArguments.COMPRESSION, StroomHeaderArguments.COMPRESSION_GZIP);
            }
            if (name.endsWith(FILE_SEPERATOR + GZ)) {
                attributeMap.put(StroomHeaderArguments.COMPRESSION, StroomHeaderArguments.COMPRESSION_GZIP);
            }
            uploadStreamFile(task, task, attributeMap);
        }
    }

    private void uploadZipFile(final TaskContext taskContext,
                               final StreamUploadTask streamUploadTask,
                               final AttributeMap attributeMap) {
        StroomZipFile stroomZipFile = null;
        try {
            taskContext.info("Zip");

            stroomZipFile = new StroomZipFile(streamUploadTask.getFile());

            final List<List<String>> groupedFileLists = stroomZipFile.getStroomZipNameSet()
                    .getBaseNameGroupedList(AGGREGATION_DELIMITER);

            for (int i = 0; i < groupedFileLists.size(); i++) {
                taskContext.info("Zip {}/{}", i, groupedFileLists.size());

                uploadData(stroomZipFile, streamUploadTask, attributeMap, groupedFileLists.get(i));

            }
        } catch (final RuntimeException | IOException e) {
            throw EntityServiceExceptionUtil.create(e);
        } finally {
            CloseableUtil.closeLogAndIgnoreException(stroomZipFile);
            taskContext.info("done");
        }
    }

    private void uploadStreamFile(final StreamUploadTask task,
                                  final StreamUploadTask streamUploadTask, final AttributeMap attributeMap) {
        try {
            final List<StreamTargetStroomStreamHandler> handlerList = StreamTargetStroomStreamHandler
                    .buildSingleHandlerList(streamStore, feedDocCache, metaDataStatistics, task.getFeedName(), task.getStreamTypeName());
            final byte[] buffer = BufferFactory.create();
            final StroomStreamProcessor stroomStreamProcessor = new StroomStreamProcessor(attributeMap, handlerList,
                    buffer, "Upload");
            try (final InputStream inputStream = Files.newInputStream(streamUploadTask.getFile())) {
                stroomStreamProcessor.process(inputStream, "Upload");
                stroomStreamProcessor.closeHandlers();
            }
        } catch (final RuntimeException | IOException e) {
            throw EntityServiceExceptionUtil.create(e);
        }
    }

    private void uploadData(final StroomZipFile stroomZipFile, final StreamUploadTask task, final AttributeMap attributeMap,
                            final List<String> fileList) throws IOException {
        StreamTarget streamTarget = null;

        try {
            final Long effectiveMs = task.getEffectiveMs();
            final StreamProgressMonitor streamProgressMonitor = new StreamProgressMonitor(taskContext,
                    "Read");

            final StreamProperties streamProperties = new StreamProperties.Builder()
                    .feedName(task.getFeedName())
                    .streamTypeName(task.getStreamTypeName())
                    .effectiveMs(effectiveMs)
                    .build();

            streamTarget = streamStore.openStreamTarget(streamProperties);

            final NestedStreamTarget rawNestedStreamTarget = new NestedStreamTarget(streamTarget, true);

            int count = 0;
            final int maxCount = fileList.size();
            for (final String inputBase : fileList) {
                count++;
                taskContext.info("{}/{}", count, maxCount);
                streamContents(stroomZipFile, attributeMap, rawNestedStreamTarget, inputBase, StroomZipFileType.Data,
                        streamProgressMonitor);
                streamContents(stroomZipFile, attributeMap, rawNestedStreamTarget, inputBase, StroomZipFileType.Meta,
                        streamProgressMonitor);
                streamContents(stroomZipFile, attributeMap, rawNestedStreamTarget, inputBase, StroomZipFileType.Context,
                        streamProgressMonitor);
            }

            rawNestedStreamTarget.close();
            streamStore.closeStreamTarget(streamTarget);

        } catch (final RuntimeException e) {
            LOGGER.error("importData() - aborting import ", e);
            streamStore.deleteStreamTarget(streamTarget);
        }
    }

    private void streamContents(final StroomZipFile stroomZipFile, final AttributeMap globalAttributeMap,
                                final NestedStreamTarget nestedStreamTarget, final String baseName, final StroomZipFileType stroomZipFileType,
                                final StreamProgressMonitor streamProgressMonitor) throws IOException {
        final InputStream sourceStream = stroomZipFile.getInputStream(baseName, stroomZipFileType);
        // Quit if we have nothing to write
        if (sourceStream == null && !StroomZipFileType.Meta.equals(stroomZipFileType)) {
            return;
        }
        if (StroomZipFileType.Data.equals(stroomZipFileType)) {
            nestedStreamTarget.putNextEntry();
            streamToStream(sourceStream, nestedStreamTarget.getOutputStream(), streamProgressMonitor);
            nestedStreamTarget.closeEntry();
        }
        if (StroomZipFileType.Meta.equals(stroomZipFileType)) {
            final AttributeMap segmentAttributeMap = new AttributeMap();
            segmentAttributeMap.putAll(globalAttributeMap);
            if (sourceStream != null) {
                segmentAttributeMap.read(sourceStream, false);
            }
            nestedStreamTarget.putNextEntry(StreamTypeNames.META);
            segmentAttributeMap.write(nestedStreamTarget.getOutputStream(StreamTypeNames.META), false);
            nestedStreamTarget.closeEntry(StreamTypeNames.META);
        }
        if (StroomZipFileType.Context.equals(stroomZipFileType)) {
            nestedStreamTarget.putNextEntry(StreamTypeNames.CONTEXT);
            streamToStream(sourceStream, nestedStreamTarget.getOutputStream(StreamTypeNames.CONTEXT), streamProgressMonitor);
            nestedStreamTarget.closeEntry(StreamTypeNames.CONTEXT);
        }
        if (sourceStream != null) {
            sourceStream.close();
        }
    }

    private boolean streamToStream(final InputStream inputStream, final OutputStream outputStream,
                                   final StreamProgressMonitor streamProgressMonitor) throws IOException {
        final byte[] buffer = BufferFactory.create();
        int len;
        while ((len = StreamUtil.eagerRead(inputStream, buffer)) != -1) {
            outputStream.write(buffer, 0, len);
            streamProgressMonitor.progress(len);
        }
        return false;
    }

}