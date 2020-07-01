package org.huifer.dbview.service;

import java.sql.SQLException;
import java.util.List;

public interface IDbService {

  /**
   * 获取所有数据库名称
   *
   * @param ip   ip
   * @param port port
   * @param username username
   * @param password password
   * @return 数据库名称
   */
  List<String> showDatabase(String ip, int port, String username, String password) throws SQLException;

  List<String> showTables(String ip, int port, String username, String password, String db)
      throws SQLException;
}
