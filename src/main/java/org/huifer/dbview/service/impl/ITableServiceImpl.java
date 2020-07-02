package org.huifer.dbview.service.impl;

import java.sql.SQLException;
import java.util.List;
import org.huifer.dbview.cache.JdbcTemplateCache;
import org.huifer.dbview.entity.TableIndex;
import org.huifer.dbview.entity.TableStructure;
import org.huifer.dbview.entity.row.TableIndexRowMapper;
import org.huifer.dbview.entity.row.TableStructureRowMapper;
import org.huifer.dbview.service.ITableService;
import org.springframework.jdbc.core.JdbcTemplate;

public class ITableServiceImpl implements ITableService {

  public static final String TABLE_INFO = "select column_name,\n"
      + "column_comment,column_type,is_nullable from information_schema.columns where table_schema ='%s' and\n"
      + "table_name = '%s' ;";
  public static final String KEY_INDEX = "select\n"
      + "    TABLE_NAME,COLUMN_NAME,CONSTRAINT_NAME, REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME\n"
      + "from INFORMATION_SCHEMA.KEY_COLUMN_USAGE\n"
      + "where CONSTRAINT_SCHEMA ='%s' AND\n"
      + "    TABLE_NAME = '%s';";

  @Override
  public void tableInfo(String ip, int port, String username, String password, String db,
      String tableName) throws SQLException {
    JdbcTemplate jdbcTemplate = JdbcTemplateCache.get(ip, port, username, password, db);

    List<TableStructure> tableStruct = getTableStruct(db, tableName, jdbcTemplate);
    List<TableIndex> tableIndex = getTableIndex(db, tableName, jdbcTemplate);
    System.out.println();

  }

  /**
   * 获取表结构
   *
   * @param db
   * @param tableName
   * @param jdbcTemplate
   */
  private List<TableStructure> getTableStruct(String db, String tableName,
      JdbcTemplate jdbcTemplate) {
    List<TableStructure> query = jdbcTemplate
        .query(String.format(TABLE_INFO, db, tableName), new TableStructureRowMapper());
    return query;
  }

  /**
   * 表的索引结构
   *
   * @param db
   * @param tableName
   * @param jdbcTemplate
   * @return
   */
  private List<TableIndex> getTableIndex(String db, String tableName, JdbcTemplate jdbcTemplate) {
    return jdbcTemplate.query(String.format(KEY_INDEX, db, tableName), new TableIndexRowMapper());
  }
}
