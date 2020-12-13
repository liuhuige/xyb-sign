package cn.xiaomingxingwu.task;

import cn.xiaomingxingwu.api.Api;
import cn.xiaomingxingwu.pojo.Token;
import cn.xiaomingxingwu.util.HttpUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

/**
 * 程序的入口
 * @author xiaomingxing
 * @create 2020/12/14 0:52
 */
public class Login {

    /**
     * 登录
     * @param token 令牌（通行证）
     * @return 返回用户ID
     */
    public String login(Token token) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("openId", token.getOpenId());
        hashMap.put("unionId", token.getUnionId());
        String res = HttpUtils.doPost(Api.login, hashMap, Api.host1);

        JsonNode jsonNode = null;
        try {
            jsonNode = new ObjectMapper().readValue(res, JsonNode.class);
            return jsonNode.get("data").get("loginerId").toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (jsonNode.get("msg").toString().equals("登录成功")) {
            System.out.println("登录成功");
        }
        return null;
    }

}
