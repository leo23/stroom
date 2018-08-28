/*
 * This file is generated by jOOQ.
 */
package stroom.explorer.impl.db.stroom;


import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;

import stroom.explorer.impl.db.stroom.tables.Explorertreenode;
import stroom.explorer.impl.db.stroom.tables.Explorertreepath;
import stroom.explorer.impl.db.stroom.tables.records.ExplorertreenodeRecord;
import stroom.explorer.impl.db.stroom.tables.records.ExplorertreepathRecord;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>stroom</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<ExplorertreenodeRecord, Integer> IDENTITY_EXPLORERTREENODE = Identities0.IDENTITY_EXPLORERTREENODE;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ExplorertreenodeRecord> KEY_EXPLORERTREENODE_PRIMARY = UniqueKeys0.KEY_EXPLORERTREENODE_PRIMARY;
    public static final UniqueKey<ExplorertreenodeRecord> KEY_EXPLORERTREENODE_TYPE = UniqueKeys0.KEY_EXPLORERTREENODE_TYPE;
    public static final UniqueKey<ExplorertreepathRecord> KEY_EXPLORERTREEPATH_PRIMARY = UniqueKeys0.KEY_EXPLORERTREEPATH_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<ExplorertreenodeRecord, Integer> IDENTITY_EXPLORERTREENODE = Internal.createIdentity(Explorertreenode.EXPLORERTREENODE, Explorertreenode.EXPLORERTREENODE.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<ExplorertreenodeRecord> KEY_EXPLORERTREENODE_PRIMARY = Internal.createUniqueKey(Explorertreenode.EXPLORERTREENODE, "KEY_explorerTreeNode_PRIMARY", Explorertreenode.EXPLORERTREENODE.ID);
        public static final UniqueKey<ExplorertreenodeRecord> KEY_EXPLORERTREENODE_TYPE = Internal.createUniqueKey(Explorertreenode.EXPLORERTREENODE, "KEY_explorerTreeNode_type", Explorertreenode.EXPLORERTREENODE.TYPE, Explorertreenode.EXPLORERTREENODE.UUID);
        public static final UniqueKey<ExplorertreepathRecord> KEY_EXPLORERTREEPATH_PRIMARY = Internal.createUniqueKey(Explorertreepath.EXPLORERTREEPATH, "KEY_explorerTreePath_PRIMARY", Explorertreepath.EXPLORERTREEPATH.ANCESTOR, Explorertreepath.EXPLORERTREEPATH.DESCENDANT);
    }
}
