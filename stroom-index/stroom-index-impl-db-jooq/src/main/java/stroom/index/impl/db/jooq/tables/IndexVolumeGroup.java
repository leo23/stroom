/*
 * This file is generated by jOOQ.
 */
package stroom.index.impl.db.jooq.tables;


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

import stroom.index.impl.db.jooq.Indexes;
import stroom.index.impl.db.jooq.Keys;
import stroom.index.impl.db.jooq.Stroom;
import stroom.index.impl.db.jooq.tables.records.IndexVolumeGroupRecord;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class IndexVolumeGroup extends TableImpl<IndexVolumeGroupRecord> {

    private static final long serialVersionUID = 1660229987;

    /**
     * The reference instance of <code>stroom.index_volume_group</code>
     */
    public static final IndexVolumeGroup INDEX_VOLUME_GROUP = new IndexVolumeGroup();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<IndexVolumeGroupRecord> getRecordType() {
        return IndexVolumeGroupRecord.class;
    }

    /**
     * The column <code>stroom.index_volume_group.id</code>.
     */
    public final TableField<IndexVolumeGroupRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>stroom.index_volume_group.version</code>.
     */
    public final TableField<IndexVolumeGroupRecord, Integer> VERSION = createField("version", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>stroom.index_volume_group.create_time_ms</code>.
     */
    public final TableField<IndexVolumeGroupRecord, Long> CREATE_TIME_MS = createField("create_time_ms", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>stroom.index_volume_group.create_user</code>.
     */
    public final TableField<IndexVolumeGroupRecord, String> CREATE_USER = createField("create_user", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>stroom.index_volume_group.update_time_ms</code>.
     */
    public final TableField<IndexVolumeGroupRecord, Long> UPDATE_TIME_MS = createField("update_time_ms", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>stroom.index_volume_group.update_user</code>.
     */
    public final TableField<IndexVolumeGroupRecord, String> UPDATE_USER = createField("update_user", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>stroom.index_volume_group.name</code>.
     */
    public final TableField<IndexVolumeGroupRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * Create a <code>stroom.index_volume_group</code> table reference
     */
    public IndexVolumeGroup() {
        this(DSL.name("index_volume_group"), null);
    }

    /**
     * Create an aliased <code>stroom.index_volume_group</code> table reference
     */
    public IndexVolumeGroup(String alias) {
        this(DSL.name(alias), INDEX_VOLUME_GROUP);
    }

    /**
     * Create an aliased <code>stroom.index_volume_group</code> table reference
     */
    public IndexVolumeGroup(Name alias) {
        this(alias, INDEX_VOLUME_GROUP);
    }

    private IndexVolumeGroup(Name alias, Table<IndexVolumeGroupRecord> aliased) {
        this(alias, aliased, null);
    }

    private IndexVolumeGroup(Name alias, Table<IndexVolumeGroupRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> IndexVolumeGroup(Table<O> child, ForeignKey<O, IndexVolumeGroupRecord> key) {
        super(child, key, INDEX_VOLUME_GROUP);
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
        return Arrays.<Index>asList(Indexes.INDEX_VOLUME_GROUP_NAME, Indexes.INDEX_VOLUME_GROUP_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<IndexVolumeGroupRecord, Integer> getIdentity() {
        return Keys.IDENTITY_INDEX_VOLUME_GROUP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<IndexVolumeGroupRecord> getPrimaryKey() {
        return Keys.KEY_INDEX_VOLUME_GROUP_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<IndexVolumeGroupRecord>> getKeys() {
        return Arrays.<UniqueKey<IndexVolumeGroupRecord>>asList(Keys.KEY_INDEX_VOLUME_GROUP_PRIMARY, Keys.KEY_INDEX_VOLUME_GROUP_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableField<IndexVolumeGroupRecord, Integer> getRecordVersion() {
        return VERSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IndexVolumeGroup as(String alias) {
        return new IndexVolumeGroup(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IndexVolumeGroup as(Name alias) {
        return new IndexVolumeGroup(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public IndexVolumeGroup rename(String name) {
        return new IndexVolumeGroup(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public IndexVolumeGroup rename(Name name) {
        return new IndexVolumeGroup(name, null);
    }
}
