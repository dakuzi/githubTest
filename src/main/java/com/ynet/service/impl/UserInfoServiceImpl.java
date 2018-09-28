package com.ynet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ynet.dao.UserinfoDao;
import com.ynet.model.Userinfo;
import com.ynet.model.UserinfoQuery;
import com.ynet.service.UserInfoService;
import com.ynet.util.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by kuzi on 2017/11/20.
 */

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    final Logger logger = org.apache.log4j.LogManager.getLogger(UserInfoServiceImpl.class.getName());

    @Resource
    private UserinfoDao userInfoDao;

    public Map<Object, Object> selectUserInfo(String userName, String passWord) {

        Map<Object, Object> map = new ResponseEvent().getDataMap();

        UserinfoQuery example = new UserinfoQuery();

        example.createCriteria().andUsernameEqualTo(userName).andPasswordEqualTo(passWord);

        List<Userinfo> userInfoList = userInfoDao.selectByExample(example);

        if (userInfoList == null || userInfoList.size() == 0) {
            map.put("returnCode", "000001");
            map.put("returnMsg", "用户名或密码不正确！");
        } else {
            map.put("userInfo", userInfoList.get(0));
        }

        return map;

    }

    public Map<Object, Object> register(String userName, String passWord, String email) {

        Map<Object, Object> map = new ResponseEvent().getDataMap();

        Userinfo userInfo = new Userinfo();

        userInfo.setUserid(UuidUtil.getUUid());
        userInfo.setUsername(userName);
        userInfo.setPassword(passWord);
        userInfo.setRemark(email);

        userInfoDao.insert(userInfo);

        return map;
    }

    public Map<Object, Object> wxgetUserInfo(String code) throws OApiException {

        Map<Object, Object> map = new ResponseEvent().getDataMap();

        /**
         * 获取accesstoken跟openid
         */
        JSONObject getAccesstoken;
        String accesstoken;
        String refreshtoken;
        String openid;
        getAccesstoken = HttpHelper.httpGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx9e912446bc023f86&secret=7cf532e4cd3b0027652d23fa06d10848&code="+code+"&grant_type=authorization_code");
        if (getAccesstoken.containsKey("access_token")) {
            accesstoken = getAccesstoken.getString("access_token");
            refreshtoken = getAccesstoken.getString("refresh_token");
        } else {
            throw new OApiResultException("access_token");
        }
        if(getAccesstoken.containsKey("openid")){
            openid = getAccesstoken.getString("openid");
        } else {
            throw new OApiResultException("openid");
        }
        logger.debug("accesstoken-----"+accesstoken);
        logger.debug("refreshtoken-----"+refreshtoken);
        logger.debug("openid-----"+openid);
        Userinfo u = new Userinfo();
        if(userInfoDao.selectByPrimaryKey(openid) == null){
            /**
             * 获取用户信息，目前就获取微信昵称和头像
             */
            String nickname;
            String headimgurl;
            JSONObject getUserInfo = HttpHelper.httpGet("https://api.weixin.qq.com/sns/userinfo?access_token="+accesstoken+"&openid="+openid+"&lang=zh_CN");

            if(getUserInfo.containsKey("nickname")){
                nickname = getUserInfo.getString("nickname");
            } else {
                throw new OApiResultException("nickname");
            }
            if(getUserInfo.containsKey("headimgurl")){
                headimgurl = getUserInfo.getString("headimgurl");
            }else{
                throw new OApiResultException("headimgurl");
            }
            logger.debug("nickname-----"+nickname);
            logger.debug("headimgurl-----"+headimgurl);
            u.setUserid(openid);
            u.setUsername(nickname);
            u.setRemark(headimgurl);
            u.setCreatetime(new Date());
            userInfoDao.insert(u);
        }else{
            u = userInfoDao.selectByPrimaryKey(openid);
        }
        map.put("user",u);
        return map;
    }

    public Map<Object, Object> test() {
        Map<Object, Object> map = new ResponseEvent().getDataMap();
        if(1==1){
            for (int i = 0; i < 10; i++) {
                Userinfo u =new Userinfo();
                u.setUserid("aaa"+i);
                u.setUsername("成才意"+i);
                userInfoDao.insert(u);
            }

        }
        if(2==2){
            for (int i = 0; i < 10; i++) {
                Userinfo u =new Userinfo();
                u.setUserid("bbb"+i);
                u.setUsername("大酷子"+i);
                userInfoDao.insert(u);
            }
        }
        return map;
    }
}

