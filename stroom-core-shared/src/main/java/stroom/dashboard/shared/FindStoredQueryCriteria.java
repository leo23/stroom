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

package stroom.dashboard.shared;

import stroom.util.shared.FindDocumentEntityCriteria;

public class FindStoredQueryCriteria extends FindDocumentEntityCriteria {
    private static final long serialVersionUID = -4421720204507720754L;

    public static final String FIELD_ID = "Id";
    public static final String FIELD_TIME = "Time";

    private String userId;
    private String dashboardUuid;
    private String componentId;
    private Boolean favourite;

    public FindStoredQueryCriteria() {
        // Default constructor necessary for GWT serialisation.
    }

    public FindStoredQueryCriteria(final String name) {
        super(name);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getDashboardUuid() {
        return dashboardUuid;
    }

    public void setDashboardUuid(final String dashboardUuid) {
        this.dashboardUuid = dashboardUuid;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(final String componentId) {
        this.componentId = componentId;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(final Boolean favourite) {
        this.favourite = favourite;
    }
}
