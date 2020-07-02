package org.huifer.dbview.entity.row;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.huifer.dbview.entity.TableIndex;
import org.springframework.jdbc.core.RowMapper;

public class TableIndexRowMapper implements RowMapper<TableIndex> {

  @Override
  public TableIndex mapRow(ResultSet resultSet, int i) throws SQLException {
    TableIndex tableIndex = new TableIndex();
    tableIndex.setTableName(resultSet.getString("TABLE_NAME"));
    tableIndex.setColumnName(resultSet.getString("COLUMN_NAME"));
    tableIndex.setConstraintName(resultSet.getString("CONSTRAINT_NAME"));
    tableIndex.setReferencedTableName(resultSet.getString("REFERENCED_TABLE_NAME"));
    tableIndex.setReferencedColumnName(resultSet.getString("REFERENCED_COLUMN_NAME"));
    return tableIndex;
  }
}
