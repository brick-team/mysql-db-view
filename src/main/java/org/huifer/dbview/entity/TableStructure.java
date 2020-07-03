package org.huifer.dbview.entity;

public class TableStructure {

//  column_name
//column_comment
//column_type
//is_nullable

  private String columnName;
  private String columnComment;
  private String columnType;
  private Boolean nullable;

  public TableStructure() {
  }

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getColumnComment() {
    return columnComment;
  }

  public void setColumnComment(String columnComment) {
    this.columnComment = columnComment;
  }

  public String getColumnType() {
    return columnType;
  }

  public void setColumnType(String columnType) {
    this.columnType = columnType;
  }

  public Boolean getNullable() {
    return nullable;
  }

  public void setNullable(Boolean nullable) {
    this.nullable = nullable;
  }
}
