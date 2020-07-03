package org.huifer.dbview.entity.res;

import java.util.List;

public class ExcRes {
  private List<String> filedName;

  private List<List<Object>> showData;

  public ExcRes() {
  }

  public List<String> getFiledName() {
    return filedName;
  }

  public void setFiledName(List<String> filedName) {
    this.filedName = filedName;
  }

  public List<List<Object>> getShowData() {
    return showData;
  }

  public void setShowData(List<List<Object>> showData) {
    this.showData = showData;
  }
}
