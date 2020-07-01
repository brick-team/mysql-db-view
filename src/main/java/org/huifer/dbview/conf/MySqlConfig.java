package org.huifer.dbview.conf;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MySqlConfig {

  public static final String url = "jdbc:mysql://%s:%s/?useUnicode=true&characterEncoding=utf8&useSSL=false";
  private String host;
  private int port;
  private String username;
  private String password;

  public String getUrl() {
    return url;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
