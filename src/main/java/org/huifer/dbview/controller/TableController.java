package org.huifer.dbview.controller;

import java.sql.SQLException;
import org.huifer.dbview.cache.MySqlConfigCache;
import org.huifer.dbview.conf.MySqlConfig;
import org.huifer.dbview.entity.ResultVO;
import org.huifer.dbview.entity.TableDetail;
import org.huifer.dbview.entity.TableInfoEntity;
import org.huifer.dbview.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/table")
public class TableController {

  @Autowired
  ITableService tableService;

  /**
   * 查看表结构
   */
  @GetMapping("/struct")
  public ResultVO structure(
      @RequestParam String tableName,
      @RequestParam String dbName
  ) throws SQLException {
    MySqlConfig mySqlConfig = MySqlConfigCache.getMySqlConfig();

    TableInfoEntity tableInfoEntity = tableService
        .tableInfo(mySqlConfig.getHost(), mySqlConfig.getPort(), mySqlConfig.getUsername(),
            mySqlConfig.getPassword(), dbName, tableName);

    return new ResultVO("ok", tableInfoEntity, 200);
  }

  /**
   * 查询数据表,select * from table_name 没有分页
   */
  @GetMapping("/findAll")
  public ResultVO findAll(
      @RequestParam String tableName,
      @RequestParam String dbName
  ) throws SQLException {

    MySqlConfig mySqlConfig = MySqlConfigCache.getMySqlConfig();

    TableDetail select = tableService
        .select(mySqlConfig.getHost(), mySqlConfig.getPort(), mySqlConfig.getUsername(),
            mySqlConfig.getPassword(), dbName, tableName);

    return new ResultVO("ok", select, 200);
  }
}
