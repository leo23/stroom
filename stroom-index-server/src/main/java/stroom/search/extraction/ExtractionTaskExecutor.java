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

package stroom.search.extraction;

import stroom.search.taskqueue.TaskExecutor;
import stroom.task.ExecutorProvider;
import stroom.task.ThreadPoolImpl;
import stroom.util.lifecycle.StroomShutdown;
import stroom.util.shared.ThreadPool;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ExtractionTaskExecutor extends TaskExecutor {
    private static final ThreadPool THREAD_POOL = new ThreadPoolImpl(
            "Extraction Task Executor",
            5,
            1,
            1);

    @Inject
    ExtractionTaskExecutor(final ExecutorProvider executorProvider) {
        super(executorProvider.getExecutor(THREAD_POOL));
    }

    @StroomShutdown
    @Override
    public void shutdown() {
        super.shutdown();
    }
}