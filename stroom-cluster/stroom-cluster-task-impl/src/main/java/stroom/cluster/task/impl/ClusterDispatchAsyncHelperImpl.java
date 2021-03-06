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

package stroom.cluster.task.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stroom.cluster.api.ClusterConfig;
import stroom.cluster.task.api.ClusterDispatchAsync;
import stroom.cluster.task.api.ClusterDispatchAsyncHelper;
import stroom.cluster.task.api.ClusterTask;
import stroom.cluster.task.api.DefaultClusterResultCollector;
import stroom.cluster.task.api.NodeNotFoundException;
import stroom.cluster.task.api.NullClusterStateException;
import stroom.cluster.task.api.TargetType;
import stroom.docref.SharedObject;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ClusterDispatchAsyncHelperImpl implements ClusterDispatchAsyncHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterDispatchAsyncHelperImpl.class);

    private static final Long ONE_MINUTE = 60000L;

    private final ClusterConfig clusterConfig;
    private final ClusterResultCollectorCacheImpl collectorCache;
    private final Provider<ClusterDispatchAsync> dispatchAsyncProvider;
    private final TargetNodeSetFactoryImpl targetNodeSetFactory;

    private volatile long lastClusterStateWarn;

    @Inject
    public ClusterDispatchAsyncHelperImpl(final ClusterConfig clusterConfig,
                                          final ClusterResultCollectorCacheImpl collectorCache,
                                          final Provider<ClusterDispatchAsync> dispatchAsyncProvider,
                                          final TargetNodeSetFactoryImpl targetNodeSetFactory) {
        this.clusterConfig = clusterConfig;
        this.collectorCache = collectorCache;
        this.dispatchAsyncProvider = dispatchAsyncProvider;
        this.targetNodeSetFactory = targetNodeSetFactory;
    }

    public <R extends SharedObject> DefaultClusterResultCollector<R> execAsync(final ClusterTask<R> task, final String targetNode) {
        final long waitTimeMs = clusterConfig.getClusterResponseTimeoutMs();
        final String sourceNode = targetNodeSetFactory.getSourceNode();
        final Set<String> targetNodes = Collections.singleton(targetNode);
        return execAsync(task, waitTimeMs, TimeUnit.MILLISECONDS, sourceNode, targetNodes);
    }

    public <R extends SharedObject> DefaultClusterResultCollector<R> execAsync(final ClusterTask<R> task, final long waitTime, final TimeUnit timeUnit, final String targetNode) {
        final String sourceNode = targetNodeSetFactory.getSourceNode();
        final Set<String> targetNodes = Collections.singleton(targetNode);
        return execAsync(task, waitTime, timeUnit, sourceNode, targetNodes);
    }

    public <R extends SharedObject> DefaultClusterResultCollector<R> execAsync(final ClusterTask<R> task, final TargetType targetType) {
        final long waitTimeMs = clusterConfig.getClusterResponseTimeoutMs();
        final String sourceNode = targetNodeSetFactory.getSourceNode();
        final Set<String> targetNodes = getTargetNodesByType(targetType);
        return execAsync(task, waitTimeMs, TimeUnit.MILLISECONDS, sourceNode, targetNodes);
    }

    public <R extends SharedObject> DefaultClusterResultCollector<R> execAsync(final ClusterTask<R> task, final long waitTime, final TimeUnit timeUnit, final TargetType targetType) {
        final String sourceNode = targetNodeSetFactory.getSourceNode();
        final Set<String> targetNodes = getTargetNodesByType(targetType);
        return execAsync(task, waitTime, timeUnit, sourceNode, targetNodes);
    }

    private Set<String> getTargetNodesByType(final TargetType targetType) {
        Set<String> targetNodes = Collections.emptySet();
        try {
            targetNodes = targetNodeSetFactory.getTargetNodesByType(targetType);
        } catch (final NullClusterStateException e) {
            final long now = System.currentTimeMillis();
            if (lastClusterStateWarn < now - ONE_MINUTE) {
                lastClusterStateWarn = now;
                LOGGER.warn(e.getMessage());
            }
        } catch (final NodeNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        return targetNodes;
    }

    private <R extends SharedObject> DefaultClusterResultCollector<R> execAsync(final ClusterTask<R> task, final long waitTime, final TimeUnit timeUnit, final String sourceNode, final Set<String> targetNodes) {
        final DefaultClusterResultCollectorImpl<R> collector = new DefaultClusterResultCollectorImpl<>(task, sourceNode,
                targetNodes);

        try {
            if (targetNodes != null && targetNodes.size() > 0) {
                // Remember the collector until we get all results.
                collectorCache.put(collector.getId(), collector);
                try {
                    dispatchAsyncProvider.get().execAsync(task, collector, sourceNode, targetNodes);
                    collector.waitToComplete(waitTime, timeUnit);
                } catch (final RuntimeException e) {
                    LOGGER.error(e.getMessage(), e);
                } finally {
                    // Forget the collector from the cache.
                    collectorCache.remove(collector.getId());
                }
            }
        } catch (final RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return collector;
    }

    public boolean isClusterStateInitialised() {
        boolean initialised = true;
        try {
            targetNodeSetFactory.getClusterState();
        } catch (final NullClusterStateException e) {
            initialised = false;
            final long now = System.currentTimeMillis();
            if (lastClusterStateWarn < now - ONE_MINUTE) {
                lastClusterStateWarn = now;
                LOGGER.warn(e.getMessage());
            }
        } catch (final RuntimeException e) {
            LOGGER.debug(e.getMessage(), e);
        }
        return initialised;
    }
}
