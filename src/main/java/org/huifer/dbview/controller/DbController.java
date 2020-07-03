package org.huifer.dbview.controller;

import java.sql.SQLException;
import java.util.List;
import org.huifer.dbview.cache.MySqlConfigCache;
import org.huifer.dbview.conf.MySqlConfig;
import org.huifer.dbview.entity.ResultVO;
import org.huifer.dbview.service.IDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class DbController {

  @Autowired
  IDbService dbService;

  @GetMapping("/db")
  public ResultVO dbs() throws SQLException {
    MySqlConfig mySqlConfig = MySqlConfigCache.getMySqlConfig();

    List<String> strings = dbService
        .showDatabase(mySqlConfig.getHost(), mySqlConfig.getPort(), mySqlConfig.getUsername(),
            mySqlConfig.getPassword());
    return new ResultVO("ok", strings, 200);
  }

  @GetMapping("/table")
  public ResultVO table(
      @RequestParam String db
  ) throws SQLException {

    MySqlConfig mySqlConfig = MySqlConfigCache.getMySqlConfig();
    List<String> strings = dbService
        .showTables(mySqlConfig.getHost(), mySqlConfig.getPort(), mySqlConfig.getUsername(),
            mySqlConfig.getPassword(), db);
    return new ResultVO("ok", strings, 200);
  }
}
