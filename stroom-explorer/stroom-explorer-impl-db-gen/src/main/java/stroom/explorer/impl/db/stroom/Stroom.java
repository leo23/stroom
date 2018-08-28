/*
 * This file is generated by jOOQ.
 */
package stroom.explorer.impl.db.stroom;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import stroom.explorer.impl.db.DefaultCatalog;
import stroom.explorer.impl.db.stroom.tables.Explorertreenode;
import stroom.explorer.impl.db.stroom.tables.Explorertreepath;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Stroom extends SchemaImpl {

    private static final long serialVersionUID = -203578054;

    /**
     * The reference instance of <code>stroom</code>
     */
    public static final Stroom STROOM = new Stroom();

    /**
     * The table <code>stroom.explorerTreeNode</code>.
     */
    public final Explorertreenode EXPLORERTREENODE = stroom.explorer.impl.db.stroom.tables.Explorertreenode.EXPLORERTREENODE;

    /**
     * The table <code>stroom.explorerTreePath</code>.
     */
    public final Explorertreepath EXPLORERTREEPATH = stroom.explorer.impl.db.stroom.tables.Explorertreepath.EXPLORERTREEPATH;

    /**
     * No further instances allowed
     */
    private Stroom() {
        super("stroom", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Explorertreenode.EXPLORERTREENODE,
            Explorertreepath.EXPLORERTREEPATH);
    }
}
