package com.ynet.util;

/**
 * 系统参数常量配置
 * @author liuxin
 *
 */
public interface ParamConstants {
	
	/**
	 * 分页查询每页显示记录数
	 */
	public static final int pageSize = 10;
	
	/**
	 * 分页查询默认查询开始行
	 */
	public static final int startRow = 1;
	
	
	public static final String SUCCESS = "000000";
	public static final String SUCCESSMSG = "成功";

	public static final String SYSTEM_ERROR_CODE = "999999";
	public static final String SYSTEM_ERROR_MSG = "系统繁忙";
	
	public static final String UNKNOWNCODE ="888888";
	public static final String UNKNOWNMSG ="未知的异常";
	
	//缓存名称
	public static final String FILECACHE = "fileCache";
	
	//////////////////////////// 系统参数 start ////////////////////////////
	/**
	 * 平台热点统计区间(单位小时)
	 */
	public static final String PLATFORM_HOT_INTERVAL ="PLATFORM_HOT_INTERVAL";
	//////////////////////////// 系统参数 end ////////////////////////////
	
	/**
	 * 问答数据源标志；1：从本地数据库获取；2：从云问答获取
	 */
	public static final String BASE_DATA_SRC_FLAG = "BASE_DATA_SRC_FLAG";
	public static final String BASE_DATA_SRC_FLAG_1 = "1";
	public static final String BASE_DATA_SRC_FLAG_2 = "2";
	
}
