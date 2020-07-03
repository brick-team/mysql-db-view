package org.huifer.dbview.cache;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.util.StringUtils;

public class JdbcTemplateCache {

  public static final String NO_DB = "jdbc:mysql://%s:%d/?useUnicode=true&characterEncoding=utf8&useSSL=false&user=%s&password=%s";
  public static final String HAS_DB = "jdbc:mysql://%s:%d/%s?useUnicode=true&characterEncoding=utf8&useSSL=false&user=%s&password=%s";
  private static final Map<String, JdbcTemplate> jdbcTemplateCache = new ConcurrentHashMap<>();

  private JdbcTemplateCache() {
    throw new IllegalArgumentException();
  }

  public static JdbcTemplate get(String ip, int port, String username, String password,
      String dbName) throws SQLException {
    String s = genKey(ip, String.valueOf(port), username, password, dbName);
    JdbcTemplate jdbcTemplate = jdbcTemplateCache.get(s);
    if (jdbcTemplate != null) {
      return jdbcTemplate;
    } else {
      set(ip, port, username, password, dbName);
      return jdbcTemplateCache.get(s);
    }
  }

  private static void set(String ip, int port, String username, String password,
      String dbName) throws SQLException {
    String jdbcUrl;
    if (StringUtils.isEmpty(dbName)) {
      jdbcUrl = String.format(NO_DB, ip, port, username, password);
    } else {
      jdbcUrl = String.format(HAS_DB, ip, port, username, password, dbName);
    }
    String s = genKey(ip, String.valueOf(port), username, password, dbName);
    Connection target = DriverManager.getConnection(jdbcUrl);
    JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(target, true));
    jdbcTemplateCache.put(s, jdbcTemplate);
  }


  private static String genKey(String... arg) {
    StringBuffer sb = new StringBuffer(64);
    for (String s : arg) {
      sb.append(s);
    }
    return sb.toString();
  }
}
