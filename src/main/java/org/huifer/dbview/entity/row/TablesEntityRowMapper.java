package org.huifer.dbview.entity.row;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.huifer.dbview.entity.TablesEntity;
import org.springframework.jdbc.core.RowMapper;

public class TablesEntityRowMapper implements RowMapper<TablesEntity> {

  private final String filedName;

  public TablesEntityRowMapper(String filedName) {
    this.filedName = filedName;
  }

  @Override
  public TablesEntity mapRow(ResultSet resultSet, int i) throws SQLException {
    TablesEntity tablesEntity = new TablesEntity();
    tablesEntity.setName(resultSet.getString(filedName));
    return tablesEntity;
  }
}
