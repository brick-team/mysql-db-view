package org.huifer.dbview.entity.req;

public class ExcReq {

  private String sql;
  private String db;

  public ExcReq() {
  }

  public String getSql() {
    return sql;
  }

  public void setSql(String sql) {
    this.sql = sql;
  }

  public String getDb() {
    return db;
  }

  public void setDb(String db) {
    this.db = db;
  }
}
