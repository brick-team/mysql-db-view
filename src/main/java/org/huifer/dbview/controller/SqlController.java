package org.huifer.dbview.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.huifer.dbview.cache.JdbcTemplateCache;
import org.huifer.dbview.cache.MySqlConfigCache;
import org.huifer.dbview.conf.MySqlConfig;
import org.huifer.dbview.entity.ResultVO;
import org.huifer.dbview.entity.req.ExcReq;
import org.huifer.dbview.entity.res.ExcRes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sql")
public class SqlController {

  @PostMapping("/exc")
  public ResultVO exc(
      @RequestBody ExcReq sql
  ) throws SQLException {
    MySqlConfig mySqlConfig = MySqlConfigCache.getMySqlConfig();

    JdbcTemplate jdbcTemplate = JdbcTemplateCache
        .get(mySqlConfig.getHost(), mySqlConfig.getPort(), mySqlConfig.getUsername(),
            mySqlConfig.getPassword(), sql.getDb());

    if (sql.getSql().startsWith("select") || sql.getSql().startsWith("SELECT")) {
      List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql.getSql());

      ExcRes excRes = new ExcRes();

      if (maps.size() > 0) {
        Map<String, Object> stringObjectMap = maps.get(0);
        List<String> head = new ArrayList<>();
        stringObjectMap.forEach((k, v) -> {
          head.add(k);
        });

        List<List<Object>> dat = new ArrayList<>();

        for (Map<String, Object> map : maps) {
          List<Object> list = new ArrayList<>();

          map.forEach((k, v) -> {
            list.add(v);
          });

          dat.add(list);
        }
        excRes.setFiledName(head);
        excRes.setShowData(dat);

      }

      return new ResultVO("ok", excRes, 200);
    } else {
      jdbcTemplate.execute(sql.getSql());
      return new ResultVO("ok", "执行完成", 200);
    }

  }


}
