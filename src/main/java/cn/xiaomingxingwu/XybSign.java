package cn.xiaomingxingwu;

import cn.xiaomingxingwu.pojo.JsonRootBean;
import cn.xiaomingxingwu.pojo.Location;
import cn.xiaomingxingwu.pojo.Token;
import cn.xiaomingxingwu.task.Login;
import cn.xiaomingxingwu.task.Sign;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * TODO：配合server酱将信息发送到微信上
 * @author xiaomingxing
 * @create 2020/12/14 0:36
 */
public class XybSign {
    public static void main(String[] args) {
        File file = new File("user.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 加载配置文件并生成对应的实体类
            JsonRootBean jsonRootBean = objectMapper.readValue(file, JsonRootBean.class);
            Token token = jsonRootBean.getToken();
            Location location = jsonRootBean.getLocation();

            // 登录
            Login login = new Login();
            String loginerId = login.login(token);

            // 签到
            Sign sign = new Sign();
            sign.manualSign(token, location, loginerId);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
