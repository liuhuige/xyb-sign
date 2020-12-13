package cn.xiaomingxingwu.pojo;

/**
 * @author xiaomingxing
 * @create 2020/12/14 0:55
 */
public class LocationForm {
    private String traineeId;
    private String adcode;
    private String lat;
    private String lng;
    private String address;
    private String deviceName = "microsoft";
    private String punchInStatus = "1";
    private String clockStatus = "2";
    private String imgUrl;
    private String reason;

    public String getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(String traineeId) {
        this.traineeId = traineeId;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPunchInStatus() {
        return punchInStatus;
    }

    public void setPunchInStatus(String punchInStatus) {
        this.punchInStatus = punchInStatus;
    }

    public String getClockStatus() {
        return clockStatus;
    }

    public void setClockStatus(String clockStatus) {
        this.clockStatus = clockStatus;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
