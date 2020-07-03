package org.huifer.dbview.service;

import java.sql.SQLException;
import org.huifer.dbview.entity.TableDetail;
import org.huifer.dbview.entity.TableInfoEntity;

/**
 * 表相关信息查询
 * <ol>
 *
 *   <li>字段</li>
 *   <li>索引</li>
 *   <li>外键</li>
 *
 * </ol>
 */
public interface ITableService {

  TableInfoEntity tableInfo(String ip, int port, String username, String password, String db,
      String tableName) throws SQLException;

  TableDetail select(String ip, int port, String username, String password, String db,
      String tableName) throws SQLException;
}
