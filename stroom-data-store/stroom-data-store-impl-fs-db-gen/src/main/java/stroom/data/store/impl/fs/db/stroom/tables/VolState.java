/*
 * This file is generated by jOOQ.
 */
package stroom.data.store.impl.fs.db.stroom.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import stroom.data.store.impl.fs.db.stroom.Indexes;
import stroom.data.store.impl.fs.db.stroom.Keys;
import stroom.data.store.impl.fs.db.stroom.Stroom;
import stroom.data.store.impl.fs.db.stroom.tables.records.VolStateRecord;


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
public class VolState extends TableImpl<VolStateRecord> {

    private static final long serialVersionUID = 585576612;

    /**
     * The reference instance of <code>stroom.VOL_STATE</code>
     */
    public static final VolState VOL_STATE = new VolState();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VolStateRecord> getRecordType() {
        return VolStateRecord.class;
    }

    /**
     * The column <code>stroom.VOL_STATE.ID</code>.
     */
    public final TableField<VolStateRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>stroom.VOL_STATE.VER</code>.
     */
    public final TableField<VolStateRecord, Byte> VER = createField("VER", org.jooq.impl.SQLDataType.TINYINT.nullable(false), this, "");

    /**
     * The column <code>stroom.VOL_STATE.BYTES_USED</code>.
     */
    public final TableField<VolStateRecord, Long> BYTES_USED = createField("BYTES_USED", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>stroom.VOL_STATE.BYTES_FREE</code>.
     */
    public final TableField<VolStateRecord, Long> BYTES_FREE = createField("BYTES_FREE", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>stroom.VOL_STATE.BYTES_TOTL</code>.
     */
    public final TableField<VolStateRecord, Long> BYTES_TOTL = createField("BYTES_TOTL", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>stroom.VOL_STATE.STAT_MS</code>.
     */
    public final TableField<VolStateRecord, Long> STAT_MS = createField("STAT_MS", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>stroom.VOL_STATE</code> table reference
     */
    public VolState() {
        this(DSL.name("VOL_STATE"), null);
    }

    /**
     * Create an aliased <code>stroom.VOL_STATE</code> table reference
     */
    public VolState(String alias) {
        this(DSL.name(alias), VOL_STATE);
    }

    /**
     * Create an aliased <code>stroom.VOL_STATE</code> table reference
     */
    public VolState(Name alias) {
        this(alias, VOL_STATE);
    }

    private VolState(Name alias, Table<VolStateRecord> aliased) {
        this(alias, aliased, null);
    }

    private VolState(Name alias, Table<VolStateRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> VolState(Table<O> child, ForeignKey<O, VolStateRecord> key) {
        super(child, key, VOL_STATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Stroom.STROOM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.VOL_STATE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<VolStateRecord, Integer> getIdentity() {
        return Keys.IDENTITY_VOL_STATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<VolStateRecord> getPrimaryKey() {
        return Keys.KEY_VOL_STATE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<VolStateRecord>> getKeys() {
        return Arrays.<UniqueKey<VolStateRecord>>asList(Keys.KEY_VOL_STATE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VolState as(String alias) {
        return new VolState(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VolState as(Name alias) {
        return new VolState(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public VolState rename(String name) {
        return new VolState(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VolState rename(Name name) {
        return new VolState(name, null);
    }
}
