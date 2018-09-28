package com.ynet.util;

import java.util.UUID;

/**
 * 获取uuid
 * 
 * @author keming
 *
 */
public class UuidUtil {

	public static String getUUid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
}
