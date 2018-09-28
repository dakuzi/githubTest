package com.ynet.dao;

import com.ynet.model.Userinfo;
import com.ynet.model.UserinfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserinfoDao {
    int countByExample(UserinfoQuery example);

    int deleteByExample(UserinfoQuery example);

    int deleteByPrimaryKey(String userid);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    List<Userinfo> selectByExample(UserinfoQuery example);

    Userinfo selectByPrimaryKey(String userid);

    int updateByExampleSelective(@Param("record") Userinfo record, @Param("example") UserinfoQuery example);

    int updateByExample(@Param("record") Userinfo record, @Param("example") UserinfoQuery example);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
}