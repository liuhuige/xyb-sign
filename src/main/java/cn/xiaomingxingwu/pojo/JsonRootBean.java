package cn.xiaomingxingwu.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xiaomingxing
 * @create 2020/12/14 0:36
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonRootBean {


    @JsonProperty("token")
    private Token token;
    @JsonProperty("location")
    private Location location;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
