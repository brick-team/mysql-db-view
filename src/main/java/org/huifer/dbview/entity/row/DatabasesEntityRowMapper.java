package org.huifer.dbview.entity.row;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.huifer.dbview.entity.DatabasesEntity;
import org.springframework.jdbc.core.RowMapper;

public class DatabasesEntityRowMapper implements RowMapper<DatabasesEntity> {

  @Override
  public DatabasesEntity mapRow(ResultSet resultSet, int i) throws SQLException {
    DatabasesEntity databasesEntity = new DatabasesEntity();
    databasesEntity.setName(resultSet.getString("Database"));
    return databasesEntity;
  }
}
