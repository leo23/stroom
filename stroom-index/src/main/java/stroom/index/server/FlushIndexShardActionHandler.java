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

package stroom.index.server;

import stroom.entity.cluster.FindFlushServiceClusterTask;
import stroom.index.shared.FindIndexShardCriteria;
import stroom.index.shared.FlushIndexShardAction;
import stroom.task.cluster.ClusterDispatchAsyncHelper;
import stroom.task.cluster.TargetNodeSetFactory.TargetType;
import stroom.task.server.AbstractTaskHandler;
import stroom.task.server.TaskHandlerBean;
import stroom.util.shared.VoidResult;
import stroom.util.spring.StroomScope;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;

@TaskHandlerBean(task = FlushIndexShardAction.class)
@Scope(StroomScope.TASK)
class FlushIndexShardActionHandler extends AbstractTaskHandler<FlushIndexShardAction, VoidResult> {
    private final ClusterDispatchAsyncHelper dispatchHelper;

    @Inject
    FlushIndexShardActionHandler(final ClusterDispatchAsyncHelper dispatchHelper) {
        this.dispatchHelper = dispatchHelper;
    }

    @Override
    public VoidResult exec(final FlushIndexShardAction action) {
        final FindFlushServiceClusterTask<FindIndexShardCriteria> clusterTask = new FindFlushServiceClusterTask<>(
                action.getUserToken(), action.getTaskName(), IndexShardWriterCache.class,
                action.getCriteria());

        dispatchHelper.execAsync(clusterTask, TargetType.ACTIVE);
        return new VoidResult();
    }
}
