package org.huifer.dbview.service.impl;

import java.sql.SQLException;
import java.util.List;
import org.huifer.dbview.service.IDbService;
import org.junit.jupiter.api.Test;

class IDbServiceImplTest {

  @Test
  public void testShowDatabase() throws SQLException {
    String pwd = "1314dafa9900";
    IDbService dbService = new IDbServiceImpl();
    List<String> root = dbService.showDatabase("10.10.0.124", 3306, "root", pwd);
    System.out.println();
  }

  @Test
  public void testShowTables() throws SQLException {
    String pwd = "1314dafa9900";
    IDbService dbService = new IDbServiceImpl();
    List<String> root = dbService
        .showTables("10.10.0.124", 3306, "shandsmod3", "shandsmod3", "shandsmod3");
  }
}