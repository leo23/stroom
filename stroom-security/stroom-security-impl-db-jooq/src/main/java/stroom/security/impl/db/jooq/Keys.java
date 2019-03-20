/*
 * This file is generated by jOOQ.
 */
package stroom.security.impl.db.jooq;


import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;

import stroom.security.impl.db.jooq.tables.AppPermission;
import stroom.security.impl.db.jooq.tables.DocPermission;
import stroom.security.impl.db.jooq.tables.StroomUser;
import stroom.security.impl.db.jooq.tables.StroomUserGroups;
import stroom.security.impl.db.jooq.tables.records.AppPermissionRecord;
import stroom.security.impl.db.jooq.tables.records.DocPermissionRecord;
import stroom.security.impl.db.jooq.tables.records.StroomUserGroupsRecord;
import stroom.security.impl.db.jooq.tables.records.StroomUserRecord;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>stroom</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<AppPermissionRecord, Long> IDENTITY_APP_PERMISSION = Identities0.IDENTITY_APP_PERMISSION;
    public static final Identity<DocPermissionRecord, Long> IDENTITY_DOC_PERMISSION = Identities0.IDENTITY_DOC_PERMISSION;
    public static final Identity<StroomUserRecord, Long> IDENTITY_STROOM_USER = Identities0.IDENTITY_STROOM_USER;
    public static final Identity<StroomUserGroupsRecord, Long> IDENTITY_STROOM_USER_GROUPS = Identities0.IDENTITY_STROOM_USER_GROUPS;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AppPermissionRecord> KEY_APP_PERMISSION_PRIMARY = UniqueKeys0.KEY_APP_PERMISSION_PRIMARY;
    public static final UniqueKey<DocPermissionRecord> KEY_DOC_PERMISSION_PRIMARY = UniqueKeys0.KEY_DOC_PERMISSION_PRIMARY;
    public static final UniqueKey<StroomUserRecord> KEY_STROOM_USER_PRIMARY = UniqueKeys0.KEY_STROOM_USER_PRIMARY;
    public static final UniqueKey<StroomUserRecord> KEY_STROOM_USER_NAME = UniqueKeys0.KEY_STROOM_USER_NAME;
    public static final UniqueKey<StroomUserRecord> KEY_STROOM_USER_USR_UUID_INDEX = UniqueKeys0.KEY_STROOM_USER_USR_UUID_INDEX;
    public static final UniqueKey<StroomUserGroupsRecord> KEY_STROOM_USER_GROUPS_PRIMARY = UniqueKeys0.KEY_STROOM_USER_GROUPS_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<AppPermissionRecord, StroomUserRecord> APP_PERMISSION_IBFK_1 = ForeignKeys0.APP_PERMISSION_IBFK_1;
    public static final ForeignKey<DocPermissionRecord, StroomUserRecord> DOC_PERMISSION_IBFK_1 = ForeignKeys0.DOC_PERMISSION_IBFK_1;
    public static final ForeignKey<StroomUserGroupsRecord, StroomUserRecord> STROOM_USER_GROUPS_IBFK_1 = ForeignKeys0.STROOM_USER_GROUPS_IBFK_1;
    public static final ForeignKey<StroomUserGroupsRecord, StroomUserRecord> STROOM_USER_GROUPS_IBFK_2 = ForeignKeys0.STROOM_USER_GROUPS_IBFK_2;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<AppPermissionRecord, Long> IDENTITY_APP_PERMISSION = Internal.createIdentity(AppPermission.APP_PERMISSION, AppPermission.APP_PERMISSION.ID);
        public static Identity<DocPermissionRecord, Long> IDENTITY_DOC_PERMISSION = Internal.createIdentity(DocPermission.DOC_PERMISSION, DocPermission.DOC_PERMISSION.ID);
        public static Identity<StroomUserRecord, Long> IDENTITY_STROOM_USER = Internal.createIdentity(StroomUser.STROOM_USER, StroomUser.STROOM_USER.ID);
        public static Identity<StroomUserGroupsRecord, Long> IDENTITY_STROOM_USER_GROUPS = Internal.createIdentity(StroomUserGroups.STROOM_USER_GROUPS, StroomUserGroups.STROOM_USER_GROUPS.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<AppPermissionRecord> KEY_APP_PERMISSION_PRIMARY = Internal.createUniqueKey(AppPermission.APP_PERMISSION, "KEY_app_permission_PRIMARY", AppPermission.APP_PERMISSION.ID);
        public static final UniqueKey<DocPermissionRecord> KEY_DOC_PERMISSION_PRIMARY = Internal.createUniqueKey(DocPermission.DOC_PERMISSION, "KEY_doc_permission_PRIMARY", DocPermission.DOC_PERMISSION.ID);
        public static final UniqueKey<StroomUserRecord> KEY_STROOM_USER_PRIMARY = Internal.createUniqueKey(StroomUser.STROOM_USER, "KEY_stroom_user_PRIMARY", StroomUser.STROOM_USER.ID);
        public static final UniqueKey<StroomUserRecord> KEY_STROOM_USER_NAME = Internal.createUniqueKey(StroomUser.STROOM_USER, "KEY_stroom_user_name", StroomUser.STROOM_USER.NAME, StroomUser.STROOM_USER.IS_GROUP);
        public static final UniqueKey<StroomUserRecord> KEY_STROOM_USER_USR_UUID_INDEX = Internal.createUniqueKey(StroomUser.STROOM_USER, "KEY_stroom_user_usr_uuid_index", StroomUser.STROOM_USER.UUID);
        public static final UniqueKey<StroomUserGroupsRecord> KEY_STROOM_USER_GROUPS_PRIMARY = Internal.createUniqueKey(StroomUserGroups.STROOM_USER_GROUPS, "KEY_stroom_user_groups_PRIMARY", StroomUserGroups.STROOM_USER_GROUPS.ID);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<AppPermissionRecord, StroomUserRecord> APP_PERMISSION_IBFK_1 = Internal.createForeignKey(stroom.security.impl.db.jooq.Keys.KEY_STROOM_USER_USR_UUID_INDEX, AppPermission.APP_PERMISSION, "app_permission_ibfk_1", AppPermission.APP_PERMISSION.USER_UUID);
        public static final ForeignKey<DocPermissionRecord, StroomUserRecord> DOC_PERMISSION_IBFK_1 = Internal.createForeignKey(stroom.security.impl.db.jooq.Keys.KEY_STROOM_USER_USR_UUID_INDEX, DocPermission.DOC_PERMISSION, "doc_permission_ibfk_1", DocPermission.DOC_PERMISSION.USER_UUID);
        public static final ForeignKey<StroomUserGroupsRecord, StroomUserRecord> STROOM_USER_GROUPS_IBFK_1 = Internal.createForeignKey(stroom.security.impl.db.jooq.Keys.KEY_STROOM_USER_USR_UUID_INDEX, StroomUserGroups.STROOM_USER_GROUPS, "stroom_user_groups_ibfk_1", StroomUserGroups.STROOM_USER_GROUPS.USER_UUID);
        public static final ForeignKey<StroomUserGroupsRecord, StroomUserRecord> STROOM_USER_GROUPS_IBFK_2 = Internal.createForeignKey(stroom.security.impl.db.jooq.Keys.KEY_STROOM_USER_USR_UUID_INDEX, StroomUserGroups.STROOM_USER_GROUPS, "stroom_user_groups_ibfk_2", StroomUserGroups.STROOM_USER_GROUPS.GROUP_UUID);
    }
}