package org.huifer.dbview.entity.row;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.huifer.dbview.entity.TableStructure;
import org.springframework.jdbc.core.RowMapper;

public class TableStructureRowMapper implements RowMapper<TableStructure> {

  @Override
  public TableStructure mapRow(ResultSet resultSet, int i) throws SQLException {
    TableStructure tableStructure = new TableStructure();
    tableStructure.setColumnName(resultSet.getString("column_name"));
    tableStructure.setColumnComment(resultSet.getString("column_comment"));
    tableStructure.setColumnType(resultSet.getString("column_type"));
    tableStructure.setNullable(resultSet.getString("is_nullable").equals("YES"));
    return tableStructure;
  }
}
