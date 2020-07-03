package org.huifer.dbview.cache;

import org.huifer.dbview.conf.MySqlConfig;

public class MySqlConfigCache {

  private static MySqlConfig mySqlConfig;


  public static MySqlConfig getMySqlConfig() {
    pre();
    return mySqlConfig;
  }

  public static void setMySqlConfig(MySqlConfig msc) {
    pre();
    mySqlConfig = msc;
  }

  private static void pre() {
    if (mySqlConfig == null) {
      mySqlConfig = new MySqlConfig();
      mySqlConfig.setHost("10.10.0.124");
      mySqlConfig.setPassword("1314dafa9900");
      mySqlConfig.setPort(3306);
      mySqlConfig.setUsername("root");
    }
  }
}
