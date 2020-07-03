package org.huifer.dbview.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.huifer.dbview.cache.JdbcTemplateCache;
import org.huifer.dbview.cache.TableInfoCache;
import org.huifer.dbview.entity.TableDetail;
import org.huifer.dbview.entity.TableIndex;
import org.huifer.dbview.entity.TableInfoEntity;
import org.huifer.dbview.entity.TableStructure;
import org.huifer.dbview.entity.row.TableIndexRowMapper;
import org.huifer.dbview.entity.row.TableStructureRowMapper;
import org.huifer.dbview.service.ITableService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
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
  public TableInfoEntity tableInfo(String ip, int port, String username, String password, String db,
      String tableName) throws SQLException {
    TableInfoEntity tableInfoEntity1 = TableInfoCache.getTableInfoEntity(db, tableName);
    if (tableInfoEntity1 != null) {
      return tableInfoEntity1;
    } else {

      JdbcTemplate jdbcTemplate = JdbcTemplateCache.get(ip, port, username, password, db);

      List<TableStructure> tableStruct = getTableStruct(db, tableName, jdbcTemplate);
      List<TableIndex> tableIndex = getTableIndex(db, tableName, jdbcTemplate);
      TableInfoEntity tableInfoEntity = new TableInfoEntity();
      tableInfoEntity.setTableStruct(tableStruct);
      tableInfoEntity.setTableIndex(tableIndex);
      tableInfoEntity.setTableName(tableName);
      tableInfoEntity
          .setEnFiled(
              tableStruct.stream().map(s -> s.getColumnName()).collect(Collectors.toList()));
      tableInfoEntity.setCnFiled(
          tableStruct.stream().map(s -> s.getColumnComment()).collect(Collectors.toList()));

      // 设置缓存
      TableInfoCache.setTableStruct(db, tableName, tableInfoEntity);
      return tableInfoEntity;
    }
  }


  @Override
  public TableDetail select(String ip, int port, String username, String password,
      String db, String tableName) throws SQLException {

    JdbcTemplate jdbcTemplate = JdbcTemplateCache.get(ip, port, username, password, db);
    // todo: 手动分页
    List<Map<String, Object>> maps = jdbcTemplate
        .queryForList(String.format("select * from %s ", tableName));
    TableDetail tableDetail = new TableDetail();

    TableInfoEntity tableInfoEntity = TableInfoCache.getTableInfoEntity(db, tableName);
    if (tableInfoEntity != null) {
      tableInfoEntity = TableInfoCache.getTableInfoEntity(db, tableName);
    } else {
      tableInfoEntity = tableInfo(ip, port, username, password, db, tableName);

    }
    tableDetail.setData(maps);
    tableDetail.setFiledName(tableInfoEntity.getEnFiled());
    tableDetail.setFiledEnName(tableInfoEntity.getCnFiled());

    List<List<Object>> rel = new ArrayList<>();

    for (Map<String, Object> map : maps) {
      List<Object> r = new ArrayList<>();
      map.forEach((k, v) -> {
        r.add(v);
      });
      rel.add(r);
    }
    tableDetail.setShowData(rel);

    return tableDetail;
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
