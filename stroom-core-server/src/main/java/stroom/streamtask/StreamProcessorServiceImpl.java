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

package stroom.streamtask;

import event.logging.BaseAdvancedQueryItem;

import stroom.entity.CriteriaLoggingUtil;
import stroom.entity.QueryAppender;
import stroom.entity.StroomEntityManager;
import stroom.entity.SystemEntityServiceImpl;
import stroom.entity.util.HqlBuilder;
import stroom.pipeline.shared.PipelineEntity;
import stroom.security.Insecure;
import stroom.security.Secured;
import stroom.streamtask.shared.FindStreamProcessorCriteria;
import stroom.streamtask.shared.StreamProcessor;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Set;

@Singleton
// @Transactional
@Secured(StreamProcessor.MANAGE_PROCESSORS_PERMISSION)
public class StreamProcessorServiceImpl extends SystemEntityServiceImpl<StreamProcessor, FindStreamProcessorCriteria>
        implements StreamProcessorService {
    @Inject
    StreamProcessorServiceImpl(final StroomEntityManager entityManager) {
        super(entityManager);
    }

    @Insecure
    @Override
    public StreamProcessor loadByIdInsecure(final long id) {
        return loadById(id);
    }

    @Override
    public Class<StreamProcessor> getEntityClass() {
        return StreamProcessor.class;
    }

    @Override
    public FindStreamProcessorCriteria createCriteria() {
        return new FindStreamProcessorCriteria();
    }

    @Override
    public void appendCriteria(final List<BaseAdvancedQueryItem> items, final FindStreamProcessorCriteria criteria) {
        CriteriaLoggingUtil.appendEntityIdSet(items, "pipelineIdSet", criteria.getPipelineIdSet());
        super.appendCriteria(items, criteria);
    }

    @Override
    public StreamProcessorQueryAppender createQueryAppender(final StroomEntityManager entityManager) {
        return new StreamProcessorQueryAppender(entityManager);
    }

    private static class StreamProcessorQueryAppender extends QueryAppender<StreamProcessor, FindStreamProcessorCriteria> {
        public StreamProcessorQueryAppender(StroomEntityManager entityManager) {
            super(entityManager);
        }

        @Override
        protected void appendBasicJoin(final HqlBuilder sql, final String alias, final Set<String> fetchSet) {
            super.appendBasicJoin(sql, alias, fetchSet);
            if (fetchSet != null && fetchSet.contains(PipelineEntity.ENTITY_TYPE)) {
                sql.append(" LEFT OUTER JOIN FETCH ");
                sql.append(alias);
                sql.append(".pipeline");
            }
        }

        @Override
        protected void appendBasicCriteria(final HqlBuilder sql, final String entityName,
                                           final FindStreamProcessorCriteria criteria) {
            super.appendBasicCriteria(sql, entityName, criteria);
            sql.appendEntityIdSetQuery(entityName + ".pipeline", criteria.getPipelineIdSet());
        }
    }
}