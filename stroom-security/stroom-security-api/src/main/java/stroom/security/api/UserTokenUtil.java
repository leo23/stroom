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

package stroom.security.api;

import stroom.security.shared.UserToken;

public final class UserTokenUtil {
    private static final UserToken INTERNAL_PROCESSING_USER_TOKEN = createStaticInternal();

    //    private static final String DELIMITER = "|";
    private static final String INTERNAL = "INTERNAL";
    private static final String USER = "user";
    private static final String SYSTEM = "system";

    private static final String INTERNAL_PROCESSING_USER_TYPE = SYSTEM;
    private static final String INTERNAL_PROCESSING_USER_USER_ID = INTERNAL;

    private UserTokenUtil() {
        // Utility class.
    }

    private static UserToken createStaticInternal() {
        return create(INTERNAL_PROCESSING_USER_TYPE, INTERNAL_PROCESSING_USER_USER_ID, null);
    }

    public static String getInternalProcessingUserId() {
        return INTERNAL_PROCESSING_USER_USER_ID;
    }

    public static UserToken processingUser() {
        return INTERNAL_PROCESSING_USER_TOKEN;
    }

    public static UserToken create(final String userId) {
        return create(USER, userId, null);
    }

    public static UserToken create(final String userId, final String sessionId) {
        return create(USER, userId, sessionId);
    }

    private static UserToken create(final String type, final String userId, final String sessionId) {
        return new UserToken(type, userId, sessionId);

//        final StringBuilder sb = new StringBuilder();
//        if (type != null) {
//            sb.append(type);
//        }
//        sb.append(DELIMITER);
//        if (userId != null) {
//            sb.append(userId);
//        }
//        sb.append(DELIMITER);
//        if (sessionId != null) {
//            sb.append(sessionId);
//        }
//        return sb.toString();
    }

//    public static String getType(final String token) {
//        return getPart(token, 0);
//    }
//
//    public static String getUserId(final String token) {
//        return getPart(token, 1);
//    }
//
//    public static String getSessionId(final String token) {
//        return getPart(token, 2);
//    }
//
//    private static String getPart(final String token, final int index) {
//        if (token == null) {
//            return null;
//        }
//        final String[] parts = token.split("\\|", -1);
//        if (parts.length - 1 < index) {
//            return null;
//        }
//        return parts[index];
//    }
}
