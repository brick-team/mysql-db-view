package org.huifer.dbview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableStructure {

//  column_name
//column_comment
//column_type
//is_nullable

  private String columnName;
  private String columnComment;
  private String columnType;
  private Boolean nullable;
}
