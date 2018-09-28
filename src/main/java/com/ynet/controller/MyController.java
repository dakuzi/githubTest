package com.ynet.controller;

import com.ynet.service.UserInfoService;
import com.ynet.util.OApiException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by kuzi on 2017/11/17.
 */
@Controller
public class MyController {

    @Resource
    private UserInfoService userInfoService;

    final Logger logger = LogManager.getLogger(MyController.class.getName());

    @RequestMapping("login")
    @ResponseBody
    public Map<Object,Object> welcome(HttpServletRequest request){

        logger.debug("开始登录");
        Map<Object,Object> map;

        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");

        logger.debug("用户名---"+userName);

        map = userInfoService.selectUserInfo(userName,passWord);

        logger.debug(map.toString());

        return map;
    }
    @RequestMapping("register")
    @ResponseBody
    public Map<Object,Object> register(HttpServletRequest request){

        logger.debug("开始注册");
        Map<Object,Object> map;

        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        String email = request.getParameter("email");

        logger.debug("用户名---"+userName+"密码---"+passWord+"邮箱---"+email);

        map = userInfoService.register(userName,passWord,email);

        logger.debug(map.toString());

        return map;
    }
    @RequestMapping("UserInfo")
    @ResponseBody
    public Map<Object,Object> UserInfo(HttpServletRequest request) throws OApiException {

        logger.debug("开始微信登录");
        Map<Object, Object> map;


        String code = request.getParameter("code");


        logger.debug("code---"+code);

        map = userInfoService.wxgetUserInfo(code);

        logger.debug(map.toString());

        return map;
    }
    @RequestMapping("Test")
    @ResponseBody
    public Map<Object,Object> Test(HttpServletRequest request) throws OApiException {

        logger.debug("开始测试");
        Map<Object, Object> map;

        map = userInfoService.test();

        logger.debug(map.toString());

        return map;
    }
}
