package cn.xiaomingxingwu.task;

import cn.xiaomingxingwu.api.Api;
import cn.xiaomingxingwu.pojo.Location;
import cn.xiaomingxingwu.pojo.LocationForm;
import cn.xiaomingxingwu.pojo.SignForm;
import cn.xiaomingxingwu.pojo.Token;
import cn.xiaomingxingwu.util.HttpUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * 签到
 * @author xiaomingxing
 * @create 2020/12/14 0:54
 */
public class Sign {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static BeanConvertToParams bctp = new BeanConvertToParams();
    private static FormatMsg fm = new FormatMsg();

    // 用于请求签到信息的参数
    private static String traineeId;

    /**
     * 以校友邦的手动签到命名
     * @param token 令牌（通行证）
     * @param location 用户文件中的自定义地址
     * @throws JsonProcessingException
     */
    public void manualSign(Token token, Location location, String loginerId) throws JsonProcessingException {

        Map<String, String> signFormParams = bctp.getSignFormParams(this.getSignFormInfo(loginerId, token.getOpenId()));
        HashMap<String, String> locationFormParams = bctp.getLocationParams(this.getLocationFormInfo(location));
        HashMap<String, String> traineeIdParam = new HashMap<String, String>();
        traineeIdParam.put("traineeId", traineeId);

        // 签到请求
        String signRequsetRes = HttpUtils.doPost(Api.behavior, signFormParams, Api.host2);
        fm.signRequsetMsg(signRequsetRes);
//        System.out.println(signRequsetRes);

        // 执行签到
        String signResponseRes = HttpUtils.doPost(Api.manualSign, locationFormParams, Api.host1);
        fm.signRequsetMsg(signResponseRes);
//        System.out.println(signResponseRes);

        // 获取更新后的签到结果
        String signLatestRes = HttpUtils.doPost(Api.planInfoDetail, traineeIdParam, Api.host1);
        fm.signLatestMsg(signLatestRes);
//        System.out.println(signLatestRes);
    }


    /**
     * 获取签到所需的参数
     * @param loginerId 用户ID
     * @param openId 用户使用该微信小程序的唯一标识
     * @return 签到表单对象
     * @throws JsonProcessingException
     */
    public SignForm getSignFormInfo(String loginerId, String openId) throws JsonProcessingException {
        String userId = loginerId;
        String deviceToken = openId;

        // 获取userName
        String userNameRes = HttpUtils.doPost(Api.accountInfo, new HashMap<String, String>(), Api.host1);
        JsonNode userNameResNode = objectMapper.readValue(userNameRes, JsonNode.class);
        String userName = userNameResNode.get("data").get("loginer").toString();

        // 获取客户端IP
        String clientIPRes = HttpUtils.doPost(Api.ip, new HashMap<String, String>(), Api.host1);
        JsonNode clientIPResNode= objectMapper.readValue(clientIPRes, JsonNode.class);
        String clientIP = clientIPResNode.get("data").get("ip").toString();

        // 生成签到表单对象
        SignForm signForm = new SignForm();
        signForm.setUserId(userId);
        signForm.setDeviceToken(deviceToken);
        signForm.setUserName(userName);
        signForm.setClientIP(clientIP);

        return signForm;
    }

    /**
     * 获取地址请求参数
     * @param location 用户文件中的自定义地址
     * @return 地址表单对象
     * @throws JsonProcessingException
     */
    public LocationForm getLocationFormInfo(Location location) throws JsonProcessingException {

        // 获取traineeId
        String traineeIdRes = HttpUtils.doPost(Api.planInfo, new HashMap<String, String>(), Api.host1);
        JsonNode traineeIdResNode = objectMapper.readValue(traineeIdRes, JsonNode.class);
        traineeId = traineeIdResNode.get("data").get("clockVo").get("traineeId").toString();


        // 获取经纬度
        HashMap<String, String> traineeIdParam = new HashMap<String, String>();
        traineeIdParam.put("traineeId", traineeId);

        String locationRes = HttpUtils.doPost(Api.planInfoDetail, traineeIdParam, Api.host1);
        JsonNode locationResNode = objectMapper.readValue(locationRes, JsonNode.class);
        String lat = locationResNode.get("data").get("postInfo").get("lat").toString();
        String lng = locationResNode.get("data").get("postInfo").get("lng").toString();

        // 封装locationForm
        LocationForm locationForm = new LocationForm();
        locationForm.setTraineeId(traineeId);
        locationForm.setLat(lat);
        locationForm.setLng(lng);
        locationForm.setAdcode(location.getAdcode());
        locationForm.setAddress(location.getAddress());

        return locationForm;
    }


}
