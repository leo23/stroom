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

package stroom.security.impl.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stroom.task.api.TaskCallback;
import stroom.task.api.TaskHandler;
import stroom.util.shared.VoidResult;

import javax.inject.Inject;

class ClusterPermissionChangeEventTaskHandler implements TaskHandler<ClusterPermissionChangeEventTask, VoidResult> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterPermissionChangeEventTaskHandler.class);

    private final PermissionChangeEventBusImpl eventBus;

    @Inject
    public ClusterPermissionChangeEventTaskHandler(final PermissionChangeEventBusImpl eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void exec(final ClusterPermissionChangeEventTask task, final TaskCallback<VoidResult> callback) {
        try {
            eventBus.fireLocally(task.getEvent());
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        try {
            callback.onSuccess(VoidResult.INSTANCE);
        } catch (final Throwable t) {
            // Ignore errors thrown returning result.
            LOGGER.trace(t.getMessage(), t);
        }
    }
}
