package org.huifer.dbview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableIndex {
//    TABLE_NAME,COLUMN_NAME,CONSTRAINT_NAME, REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME

  private String tableName;
  private String columnName;
  private String constraintName;
  private String referencedTableName;
  private String referencedColumnName;

}
