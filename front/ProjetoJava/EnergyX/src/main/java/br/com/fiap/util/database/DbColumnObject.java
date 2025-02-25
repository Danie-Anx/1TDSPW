package br.com.fiap.util.database;

import java.lang.reflect.Field;

public class DbColumnObject {

    private String tableName;
    private String name;
    private String type;
    private boolean primaryKey;
    private boolean generatedByDefault;
    private DbColumnObject foreignKeyReference;
    private boolean notNull;
    private boolean unique;
    private Field relatedField;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isGeneratedByDefault() {
        return generatedByDefault;
    }

    public void setGeneratedByDefault(boolean generatedByDefault) {
        this.generatedByDefault = generatedByDefault;
    }

    public DbColumnObject getForeignKeyReference() {
        return foreignKeyReference;
    }

    public void setForeignKeyReference(DbColumnObject foreignKeyReference) {
        this.foreignKeyReference = foreignKeyReference;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public Field getRelatedField() {
        return relatedField;
    }

    public void setRelatedField(Field relatedField) {
        this.relatedField = relatedField;
    }

    @Override
    public String toString() {
        return "DbColumnObject{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", primaryKey=" + primaryKey +
                ", generatedByDefault=" + generatedByDefault +
                ", foreignKey='" + foreignKeyReference + '\'' +
                ", notNull=" + notNull +
                ", unique=" + unique +
                '}';
    }
}
