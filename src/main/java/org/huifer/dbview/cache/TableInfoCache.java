package org.huifer.dbview.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.huifer.dbview.entity.TableInfoEntity;

public class TableInfoCache {

  /**
   * 表结构的缓存
   */
  private static final Map<String, TableInfoEntity> TABLE_STRUCT = new ConcurrentHashMap<>();

  private static final int size = 1024;
  private static String dk;


  private static String gk(String db, String tableName) {
    return db + ":" + tableName;
  }

  public static TableInfoEntity getTableInfoEntity(String db, String tableName) {
    return TABLE_STRUCT.get(gk(db, tableName));
  }



  public static void setTableStruct(String db, String tableName, TableInfoEntity tableInfoEntity) {
    String gk = gk(db, tableName);
    if (!TABLE_STRUCT.containsKey(gk)) {
      TABLE_STRUCT.put(gk, tableInfoEntity);
    }
  }


}
