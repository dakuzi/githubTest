package com.ynet.service;

import com.ynet.util.OApiException;

import java.util.Map;

/**
 * Created by kuzi on 2017/11/20.
 */
public interface UserInfoService {


    Map<Object,Object> selectUserInfo(String userName, String passWord);

    Map<Object,Object> register(String userName, String passWord, String email);

    Map<Object,Object> wxgetUserInfo(String code) throws OApiException;

    Map<Object,Object> test();
}
