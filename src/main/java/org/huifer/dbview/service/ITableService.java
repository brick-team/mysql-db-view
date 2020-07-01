package org.huifer.dbview.service;

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

  void tableInfo(String ip, int port, String username, String password, String db,
      String tableInfo);

}
