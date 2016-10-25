package com.isharipov.model;

/**
 * Created by Илья on 25.10.2016.
 */
public class TableInfo {
    private final String columnName;
    private final String typeName;

    public TableInfo(String columnName, String typeName) {
        this.columnName = columnName;
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getColumnName() {
        return columnName;
    }
}
