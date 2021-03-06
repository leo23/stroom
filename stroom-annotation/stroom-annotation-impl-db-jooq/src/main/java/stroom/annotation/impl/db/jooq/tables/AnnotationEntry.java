/*
 * This file is generated by jOOQ.
 */
package stroom.annotation.impl.db.jooq.tables;


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

import stroom.annotation.impl.db.jooq.Indexes;
import stroom.annotation.impl.db.jooq.Keys;
import stroom.annotation.impl.db.jooq.Stroom;
import stroom.annotation.impl.db.jooq.tables.records.AnnotationEntryRecord;


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
public class AnnotationEntry extends TableImpl<AnnotationEntryRecord> {

    private static final long serialVersionUID = 1735932001;

    /**
     * The reference instance of <code>stroom.annotation_entry</code>
     */
    public static final AnnotationEntry ANNOTATION_ENTRY = new AnnotationEntry();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AnnotationEntryRecord> getRecordType() {
        return AnnotationEntryRecord.class;
    }

    /**
     * The column <code>stroom.annotation_entry.id</code>.
     */
    public final TableField<AnnotationEntryRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>stroom.annotation_entry.version</code>.
     */
    public final TableField<AnnotationEntryRecord, Integer> VERSION = createField("version", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>stroom.annotation_entry.create_time_ms</code>.
     */
    public final TableField<AnnotationEntryRecord, Long> CREATE_TIME_MS = createField("create_time_ms", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>stroom.annotation_entry.create_user</code>.
     */
    public final TableField<AnnotationEntryRecord, String> CREATE_USER = createField("create_user", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>stroom.annotation_entry.update_time_ms</code>.
     */
    public final TableField<AnnotationEntryRecord, Long> UPDATE_TIME_MS = createField("update_time_ms", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>stroom.annotation_entry.update_user</code>.
     */
    public final TableField<AnnotationEntryRecord, String> UPDATE_USER = createField("update_user", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>stroom.annotation_entry.fk_annotation_id</code>.
     */
    public final TableField<AnnotationEntryRecord, Long> FK_ANNOTATION_ID = createField("fk_annotation_id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>stroom.annotation_entry.type</code>.
     */
    public final TableField<AnnotationEntryRecord, String> TYPE = createField("type", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>stroom.annotation_entry.data</code>.
     */
    public final TableField<AnnotationEntryRecord, String> DATA = createField("data", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>stroom.annotation_entry</code> table reference
     */
    public AnnotationEntry() {
        this(DSL.name("annotation_entry"), null);
    }

    /**
     * Create an aliased <code>stroom.annotation_entry</code> table reference
     */
    public AnnotationEntry(String alias) {
        this(DSL.name(alias), ANNOTATION_ENTRY);
    }

    /**
     * Create an aliased <code>stroom.annotation_entry</code> table reference
     */
    public AnnotationEntry(Name alias) {
        this(alias, ANNOTATION_ENTRY);
    }

    private AnnotationEntry(Name alias, Table<AnnotationEntryRecord> aliased) {
        this(alias, aliased, null);
    }

    private AnnotationEntry(Name alias, Table<AnnotationEntryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> AnnotationEntry(Table<O> child, ForeignKey<O, AnnotationEntryRecord> key) {
        super(child, key, ANNOTATION_ENTRY);
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
        return Arrays.<Index>asList(Indexes.ANNOTATION_ENTRY_ANNOTATION_ENTRY_FK_ANNOTATION_ID, Indexes.ANNOTATION_ENTRY_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<AnnotationEntryRecord, Long> getIdentity() {
        return Keys.IDENTITY_ANNOTATION_ENTRY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AnnotationEntryRecord> getPrimaryKey() {
        return Keys.KEY_ANNOTATION_ENTRY_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AnnotationEntryRecord>> getKeys() {
        return Arrays.<UniqueKey<AnnotationEntryRecord>>asList(Keys.KEY_ANNOTATION_ENTRY_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<AnnotationEntryRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AnnotationEntryRecord, ?>>asList(Keys.ANNOTATION_ENTRY_FK_ANNOTATION_ID);
    }

    public Annotation annotation() {
        return new Annotation(this, Keys.ANNOTATION_ENTRY_FK_ANNOTATION_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableField<AnnotationEntryRecord, Integer> getRecordVersion() {
        return VERSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AnnotationEntry as(String alias) {
        return new AnnotationEntry(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AnnotationEntry as(Name alias) {
        return new AnnotationEntry(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AnnotationEntry rename(String name) {
        return new AnnotationEntry(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AnnotationEntry rename(Name name) {
        return new AnnotationEntry(name, null);
    }
}
