/*
 * This file is generated by jOOQ.
 */
package stroom.config.impl.db.stroom.tables;


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

import stroom.config.impl.db.stroom.Indexes;
import stroom.config.impl.db.stroom.Keys;
import stroom.config.impl.db.stroom.Stroom;
import stroom.config.impl.db.stroom.tables.records.ConfigHistoryRecord;


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
public class ConfigHistory extends TableImpl<ConfigHistoryRecord> {

    private static final long serialVersionUID = -49331059;

    /**
     * The reference instance of <code>stroom.config_history</code>
     */
    public static final ConfigHistory CONFIG_HISTORY = new ConfigHistory();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ConfigHistoryRecord> getRecordType() {
        return ConfigHistoryRecord.class;
    }

    /**
     * The column <code>stroom.config_history.id</code>.
     */
    public final TableField<ConfigHistoryRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>stroom.config_history.update_time</code>.
     */
    public final TableField<ConfigHistoryRecord, Long> UPDATE_TIME = createField("update_time", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>stroom.config_history.update_user</code>.
     */
    public final TableField<ConfigHistoryRecord, String> UPDATE_USER = createField("update_user", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>stroom.config_history.name</code>.
     */
    public final TableField<ConfigHistoryRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>stroom.config_history.val</code>.
     */
    public final TableField<ConfigHistoryRecord, String> VAL = createField("val", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * Create a <code>stroom.config_history</code> table reference
     */
    public ConfigHistory() {
        this(DSL.name("config_history"), null);
    }

    /**
     * Create an aliased <code>stroom.config_history</code> table reference
     */
    public ConfigHistory(String alias) {
        this(DSL.name(alias), CONFIG_HISTORY);
    }

    /**
     * Create an aliased <code>stroom.config_history</code> table reference
     */
    public ConfigHistory(Name alias) {
        this(alias, CONFIG_HISTORY);
    }

    private ConfigHistory(Name alias, Table<ConfigHistoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private ConfigHistory(Name alias, Table<ConfigHistoryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> ConfigHistory(Table<O> child, ForeignKey<O, ConfigHistoryRecord> key) {
        super(child, key, CONFIG_HISTORY);
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
        return Arrays.<Index>asList(Indexes.CONFIG_HISTORY_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<ConfigHistoryRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CONFIG_HISTORY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ConfigHistoryRecord> getPrimaryKey() {
        return Keys.KEY_CONFIG_HISTORY_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ConfigHistoryRecord>> getKeys() {
        return Arrays.<UniqueKey<ConfigHistoryRecord>>asList(Keys.KEY_CONFIG_HISTORY_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigHistory as(String alias) {
        return new ConfigHistory(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigHistory as(Name alias) {
        return new ConfigHistory(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ConfigHistory rename(String name) {
        return new ConfigHistory(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ConfigHistory rename(Name name) {
        return new ConfigHistory(name, null);
    }
}
