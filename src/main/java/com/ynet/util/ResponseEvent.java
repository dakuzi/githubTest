package com.ynet.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回参数
 * 
 * @author liuxin
 *
 */
public class ResponseEvent {

	private Map<Object, Object> dataMap = new HashMap<Object, Object>();

	private String returnCode = ParamConstants.SUCCESS;

	private String returnMsg = ParamConstants.SUCCESSMSG;

	public ResponseEvent() {
		dataMap.put("returnCode", returnCode);
		dataMap.put("returnMsg", returnMsg);
	}

	@SuppressWarnings("serial")
	public Map<String, Object> getReturnDataMap() {
		return new HashMap<String, Object>() {
			{
				put("returnCode", returnCode);
				put("returnMsg", returnMsg);
			}
		};
	}

	public Map<Object, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<Object, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

}
