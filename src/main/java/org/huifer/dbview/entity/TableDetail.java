package org.huifer.dbview.entity;

import java.util.List;
import java.util.Map;

public class TableDetail {

  private List<Map<String, Object>> data;
  private List<String> filedName;
  private List<String> filedEnName;
  private List<List<Object>> showData;

  public List<List<Object>> getShowData() {
    return showData;
  }

  public void setShowData(List<List<Object>> showData) {
    this.showData = showData;
  }

  public List<Map<String, Object>> getData() {
    return data;
  }

  public void setData(List<Map<String, Object>> data) {
    this.data = data;
  }

  public List<String> getFiledName() {
    return filedName;
  }

  public void setFiledName(List<String> filedName) {
    this.filedName = filedName;
  }

  public List<String> getFiledEnName() {
    return filedEnName;
  }

  public void setFiledEnName(List<String> filedEnName) {
    this.filedEnName = filedEnName;
  }
}
