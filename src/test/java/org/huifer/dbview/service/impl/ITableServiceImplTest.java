package org.huifer.dbview.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.huifer.dbview.entity.TableDetail;
import org.huifer.dbview.service.ITableService;
import org.junit.jupiter.api.Test;

class ITableServiceImplTest {

  @Test
  public void hh() throws SQLException {
    String pwd = "1314dafa9900";
    ITableService dbService = new ITableServiceImpl();
    dbService.tableInfo(
        "10.10.0.124", 3306, "shandsmod3", "shandsmod3", "shandsmod3", "hs_pay_log");

  }

  @Test
  public void findAll() throws SQLException {
    ITableService dbService = new ITableServiceImpl();
    TableDetail select = dbService.select(
        "10.10.0.124", 3306, "root", "1314dafa9900", "shandsmod3", "hs_pay_log");
    System.out.println();

  }
}