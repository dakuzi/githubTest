package com.ynet.controller;

import com.ynet.service.UserInfoService;
import com.ynet.util.SignUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kuzi on 2017/11/17.
 */
@Controller
public class WxLoginController {

    @Resource
    private UserInfoService userInfoService;

    final Logger logger = LogManager.getLogger(WxLoginController.class.getName());

    @RequestMapping("Wxlogin")
    @ResponseBody
    public void Wxlogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.debug("开始校验签名");

        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();

        if(SignUtil.checkSignature(signature, timestamp, nonce)){
            logger.debug("签名校验通过");
            out.print(echostr);
        }else{
            logger.debug("签名校验失败");
        }
    }

}
