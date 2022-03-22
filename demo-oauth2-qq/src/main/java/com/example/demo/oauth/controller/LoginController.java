package com.example.demo.oauth.controller;

import com.example.demo.oauth.constant.LoginQQConstant;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/login")
public class LoginController {

//https://blog.csdn.net/qq_16504067/article/details/88532724
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 处理登录事件,点击前端的图片会访问到这个方法
     * 获取AccessToken
     * 点击登录的按钮之后就会进入后端的loginByQQ()方法。
     *
     * 以GET方式访问 https://graph.qq.com/oauth2.0/authorize，在后面添加参数
     */
    @RequestMapping("/loginByQQ")
    public void loginByQQ(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String response_type = "code";
        String client_id = LoginQQConstant.APP_ID;
        // 官方文档强调此处要对url进行URLEncode，我试了一下，直接使用原始回调地址也能正常使用
        String redirect_uri = URLEncoder.encode(LoginQQConstant.CALLBACK_URL, "UTF-8");
//        String redirect_uri = LoginQQConstant.CALLBACK_URL;
        //client端的状态值。用于第三方应用防止CSRF攻击。
        String state = new Date().toString();
        req.getSession().setAttribute("state", state);

        String url = String.format(LoginQQConstant.GET_AUTHORIZATION_CODE +
                "?response_type=%s&client_id=%s&redirect_uri=%s&state=%s", response_type, client_id, redirect_uri, state);

        resp.sendRedirect(url);

        // 如果一切顺利，就会进入callbackHandler方法
    }

    /**
     * 用户授权后的回调方法
     * 这一步过后会出现用户QQ登录授权页面，用户授权后再进入我们定义的回调方法callbackHandler()；
     *
     * 从回调方法中的request中取出authorization_code
     *访问 https://graph.qq.com/oauth2.0/token
     */
    @ResponseBody
    @RequestMapping("/callbackHandler")
    public String callbackHandler(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 1.获取回调的authorization
        String authorization_code = req.getParameter("code");
        if (authorization_code == null || authorization_code.trim().isEmpty()) {
            throw new RuntimeException("未获取到AuthorizationCode");
        }
        // 2.client端的状态值。用于第三方应用防止CSRF攻击。
        String state = req.getParameter("state");
        if (!state.equals(req.getParameter("state"))) {
            throw new RuntimeException("client端的状态值不匹配！");
        }

        // 3.获取accessToken
        String urlForAccessToken = getUrlForAccessToken(authorization_code);
        String access_token = getAccessToken(urlForAccessToken);

        // 4.根据accessToken获取openId
        if (access_token == null || access_token.trim().isEmpty()) {
            throw new RuntimeException("未获取到accessToken");
        }
        String openid = getOpenId(access_token);

        // 5.根据openid获取用户信息
        if (openid == null || openid.trim().isEmpty()) {
            throw new RuntimeException("未获取到openId");
        }
        String userInfo = getUserInfo(openid,access_token);
        return "userInfo为：" + userInfo;

        // ... 获取到用户信息就可以进行自己的业务逻辑处理了
    }

    // 下面是辅助方法

    /**
     * 拼接用于获取accessToken的链接
     */
    private String getUrlForAccessToken(String authorization_code) throws UnsupportedEncodingException {
        String grant_type = "authorization_code";
        String client_id = LoginQQConstant.APP_ID;
        String client_secret = LoginQQConstant.APP_KEY;
//        String redirect_uri = URLEncoder.encode(CALLBACK_URL, "UTF-8"); 此处进行URLEncode会导致无法获取AccessToken
        String redirect_uri = LoginQQConstant.CALLBACK_URL;

        String url = String.format(LoginQQConstant.GET_ACCESS_TOKEN +
                        "?grant_type=%s&client_id=%s&client_secret=%s&code=%s&redirect_uri=%s",
                grant_type, client_id, client_secret, authorization_code, redirect_uri);

        return url;
    }

    /**
     * 获取accessToken
     */
    private String getAccessToken(String urlForAccessToken) {
        //第一次用服务器发起模拟客户端访问,得到的是包含access_token的字符串,其格式如下
        //access_token=0FFD92ABD1DFD4F5&expires_in=7776000&refresh_token=04CE5D1F1E290B0974C5
        String firstCallbackInfo = restTemplate.getForObject(urlForAccessToken, String.class);
        String[] params = firstCallbackInfo.split("&");
        String access_token = null;
        for (String param : params) {
            String[] keyvalue = param.split("=");
            if (keyvalue[0].equals("access_token")) {
                access_token = keyvalue[1];
                break;
            }
        }
        return access_token;
    }

    /**
     * 根据accessToken获取openid
     * 使用accessToken去获取openid
     *
     * 请求 https://graph.qq.com/oauth2.0/me
     */
    private String getOpenId(String access_token) throws IOException {
        String url = String.format(LoginQQConstant.GET_OPEN_ID + "?access_token=%s", access_token);
        //第二次模拟客户端发出请求后得到的是带openid的返回数据,格式如下
        //callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        String secondCallbackInfo = restTemplate.getForObject(url, String.class);

        //正则表达式处理
        String regex = "\\{.*\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(secondCallbackInfo);
        if (!matcher.find()) {
            throw new RuntimeException("异常的回调值: " + secondCallbackInfo);
        }

        //调用jackson
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap hashMap = objectMapper.readValue(matcher.group(0), HashMap.class);

        String openid = ((String) hashMap.get("openid"));
        //return "获取到的openid为：" + openid;
        return openid;
    }

    /**
     * 根据openid获取用户信息
     * 根据openid调用官方提供的api，来获取用户信息
     * https://graph.qq.com/user/get_user_info?access_token=YOUR_ACCESS_TOKEN&oauth_consumer_key=YOUR_APP_ID&openid=YOUR_OPENID
     */
    private String getUserInfo(String openid,String access_token) {
        String infoUrl = String.format(LoginQQConstant.GET_USER_INFO + "?access_token=%s&oauth_consumer_key=%s&openid=%ss", access_token, LoginQQConstant.APP_ID, openid);
        String userInfo = restTemplate.getForObject(infoUrl, String.class);
        return userInfo;
    }
}