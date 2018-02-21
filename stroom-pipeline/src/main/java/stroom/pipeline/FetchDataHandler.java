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

package stroom.pipeline;

import stroom.feed.FeedService;
import stroom.logging.StreamEventLog;
import stroom.pipeline.errorhandler.ErrorReceiverProxy;
import stroom.pipeline.factory.PipelineDataCache;
import stroom.pipeline.factory.PipelineFactory;
import stroom.pipeline.shared.AbstractFetchDataResult;
import stroom.pipeline.shared.FetchDataAction;
import stroom.pipeline.state.FeedHolder;
import stroom.pipeline.state.PipelineHolder;
import stroom.pipeline.state.StreamHolder;
import stroom.security.Secured;
import stroom.security.SecurityContext;
import stroom.streamstore.StreamStore;
import stroom.streamstore.shared.Stream;
import stroom.task.TaskHandlerBean;

import javax.inject.Inject;

@TaskHandlerBean(task = FetchDataAction.class)
@Secured(Stream.VIEW_DATA_PERMISSION)
class FetchDataHandler extends AbstractFetchDataHandler<FetchDataAction> {
    @Inject
    FetchDataHandler(final StreamStore streamStore,
                     final FeedService feedService,
                     final FeedHolder feedHolder,
                     final PipelineHolder pipelineHolder,
                     final StreamHolder streamHolder,
                     final PipelineService pipelineService,
                     final PipelineFactory pipelineFactory,
                     final ErrorReceiverProxy errorReceiverProxy,
                     final PipelineDataCache pipelineDataCache,
                     final StreamEventLog streamEventLog,
                     final SecurityContext securityContext) {
        super(streamStore,
                feedService,
                feedHolder,
                pipelineHolder,
                streamHolder,
                pipelineService,
                pipelineFactory,
                errorReceiverProxy,
                pipelineDataCache,
                streamEventLog,
                securityContext);
    }

    @Override
    public AbstractFetchDataResult exec(final FetchDataAction action) {
        final Long streamId = action.getStreamId();

        if (streamId != null) {
            return getData(streamId, action.getChildStreamType(), action.getStreamRange(), action.getPageRange(),
                    action.isMarkerMode(), null, action.isShowAsHtml(), action.getExpandedSeverities());
        }

        return null;
    }
}
