package cn.xiaomingxingwu.task;

import cn.xiaomingxingwu.pojo.LocationForm;
import cn.xiaomingxingwu.pojo.SignForm;

import java.util.HashMap;
import java.util.Map;

/**
 * JavaBean to form params.
 * @author xiaomingxing
 * @create 2020/12/14 1:03
 */
public class BeanConvertToParams {

    /**
     * Location class to location form params.
     * @param locationForm locationForm object
     * @return location form params
     */
    public HashMap<String, String> getLocationParams(LocationForm locationForm) {
        HashMap<String, String> hashMap = new HashMap<String, String>();

        hashMap.put("traineeId", locationForm.getTraineeId());
        hashMap.put("adcode", locationForm.getAdcode());
        hashMap.put("lat", locationForm.getLat());
        hashMap.put("lng", locationForm.getLng());
        hashMap.put("address", locationForm.getAddress());
        hashMap.put("deviceName", locationForm.getDeviceName());
        hashMap.put("punchInStatus", locationForm.getPunchInStatus());
        hashMap.put("clockStatus", locationForm.getClockStatus());
        hashMap.put("imgUrl", "");
        hashMap.put("reason", "");

        return hashMap;
    }


    /**
     * Sign class convert to sign form params.
     * @param signForm signForm object
     * @return sign form params
     */
    public Map<String, String> getSignFormParams(SignForm signForm) {

        Map<String, String> signInfoParams = new HashMap<String, String>();
        signInfoParams.put("app", signForm.getApp());
        signInfoParams.put("appVersion", signForm.getAppVersion());
        signInfoParams.put("userId", signForm.getUserId());
        signInfoParams.put("deviceToken", signForm.getDeviceToken());
        signInfoParams.put("userName", signForm.getUserName());
        signInfoParams.put("country", signForm.getCountry());
        signInfoParams.put("province", signForm.getProvince());
        signInfoParams.put("city", signForm.getCity());
        signInfoParams.put("deviceModel", signForm.getDeviceModel());
        signInfoParams.put("operatingSystem", signForm.getOperatingSystem());
        signInfoParams.put("operatingSystemVersion", signForm.getOperatingSystemVersion());
        signInfoParams.put("screenHeight", signForm.getScreenHeight());
        signInfoParams.put("screenWidth", signForm.getScreenWidth());
        signInfoParams.put("eventTime", signForm.getEventTime()); // 时间截
        signInfoParams.put("pageId", signForm.getPageId());
        signInfoParams.put("pageName", signForm.getPageName());
        signInfoParams.put("preferName", signForm.getPreferName());
        signInfoParams.put("stayTime", signForm.getStayTime());
        signInfoParams.put("eventType", signForm.getEventType());
        signInfoParams.put("eventName", signForm.getEventName());
        signInfoParams.put("clientIP", signForm.getClientIP());
        signInfoParams.put("reportSrc", signForm.getReportSrc());
        signInfoParams.put("login", signForm.getLogin());
        signInfoParams.put("netType", signForm.getNetType());
        signInfoParams.put("itemID", signForm.getItemID());
        signInfoParams.put("itemType", signForm.getItemType());

        return signInfoParams;
    }
}
