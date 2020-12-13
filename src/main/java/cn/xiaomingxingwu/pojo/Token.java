package cn.xiaomingxingwu.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author xiaomingxing
 * @create 2020/12/14 0:46
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {

    @JsonProperty("openId")
    private String openId;
    @JsonProperty("unionId")
    private String unionId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
