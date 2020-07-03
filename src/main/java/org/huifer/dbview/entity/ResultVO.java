package org.huifer.dbview.entity;


public class ResultVO {

  private String msg;
  private Object data;
  private Integer code;

  public ResultVO(String msg, Object data, Integer code) {
    this.msg = msg;
    this.data = data;
    this.code = code;
  }

  public ResultVO() {
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }
}
