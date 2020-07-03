package org.huifer.dbview.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import org.huifer.dbview.cache.JdbcTemplateCache;
import org.huifer.dbview.entity.DatabasesEntity;
import org.huifer.dbview.entity.TablesEntity;
import org.huifer.dbview.entity.row.DatabasesEntityRowMapper;
import org.huifer.dbview.entity.row.TablesEntityRowMapper;
import org.huifer.dbview.service.IDbService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class IDbServiceImpl implements IDbService {

  /**
   * 获取所有数据库名称
   *
   * @param ip   ip
   * @param port port
   * @return 数据库名称
   */
  @Override
  public List<String> showDatabase(String ip, int port, String username, String password)
      throws SQLException {

    JdbcTemplate jdbcTemplate = JdbcTemplateCache.get(ip, port, username, password, null);

    List<DatabasesEntity> show_databases = jdbcTemplate
        .query("show DATABASES", new DatabasesEntityRowMapper());
    return show_databases.stream().map(s -> s.getName()).collect(Collectors.toList());
  }

  @Override
  public List<String> showTables(String ip, int port, String username, String password, String db)
      throws SQLException {
    JdbcTemplate jdbcTemplate = JdbcTemplateCache.get(ip, port, username, password, db);

    List<TablesEntity> show_tables = jdbcTemplate
        .query("show tables", new TablesEntityRowMapper("Tables_in_" + db));

    return show_tables.stream().map(s -> s.getName()).collect(Collectors.toList());
  }
}
