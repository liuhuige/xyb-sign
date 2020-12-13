package cn.xiaomingxingwu.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 格式化信息
 * @author xiaomingxing
 * @create 2020/12/14 2:10
 */
public class FormatMsg {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 格式化签到请求信息
     * @param json 结果
     */
    public void signRequsetMsg(String json) {
        if (json == "") {
            return;
        }
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readValue(json, JsonNode.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String msg = jsonNode.get("msg").toString();
        System.out.println("签到请求：" + msg);
        System.out.println("-----------------------------------------------------");

    }


    /**
     * 格式化签到响应信息
     * @param json 结果
     */
    public void signResponseMsg(String json) {
        if (json == "") {
            return;
        }
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readValue(json, JsonNode.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String failCount = jsonNode.get("data").get("failCount").toString();
        String successCount = jsonNode.get("data").get("successCount").toString();
        String msg = jsonNode.get("msg").toString();

        System.out.println("签到响应：" + msg);
        System.out.println("状态码（0：false, 1：true）：" + "failCount(" + failCount + ")" + "successCount(" + successCount + ")");
        System.out.println("-----------------------------------------------------");
    }

    /**
     * 格式化签到最新的响应信息
     * @param json 结果
     */
    public void signLatestMsg(String json) {
        if (json == "") {
            return;
        }
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readValue(json, JsonNode.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String date = jsonNode.get("data").get("clockInfo").get("date").toString();
        String address = jsonNode.get("data").get("clockInfo").get("inAddress").toString();
        String antAddress = jsonNode.get("data").get("postInfo").get("address").toString();
        String time = jsonNode.get("data").get("clockInfo").get("inTime").toString();
        String week = jsonNode.get("data").get("clockInfo").get("week").toString();
        String msg = jsonNode.get("msg").toString();



        System.out.println("签到：" + msg);
        System.out.println("当前签到信息：");
        System.out.println("日期：" + date);
        System.out.println("地址：" + address);
        System.out.println("时间：" + time);
        System.out.println("星期：" + date);

        System.out.println();

        System.out.println("预设签到信息：");
        System.out.println("地址：" + antAddress);
        System.out.println("-----------------------------------------------------");
    }


}
