package org.huifer.dbview.controller;

import org.huifer.dbview.cache.MySqlConfigCache;
import org.huifer.dbview.conf.MySqlConfig;
import org.huifer.dbview.entity.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mysql")
public class ConfController {

  @PostMapping("/set")
  public ResultVO set(@RequestBody MySqlConfig mySqlConfig) {
    if (mySqlConfig != null) {
      MySqlConfigCache.setMySqlConfig(mySqlConfig);
    }
    return new ResultVO("ok", "ok", 200);

  }
}
