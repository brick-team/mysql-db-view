package org.huifer.dbview.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TableInfoCache {

  private static Map<String, Object> map = new ConcurrentHashMap<>();

}
