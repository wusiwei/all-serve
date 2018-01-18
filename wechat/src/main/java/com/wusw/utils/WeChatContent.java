package com.wusw.utils;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;

public class WeChatContent {
	
	private static HashMap<String,JSONObject> wechatMap;
	
	static{
		wechatMap=new HashMap<String,JSONObject>();
		JSONObject ckgzh=new JSONObject();
		ckgzh.put("appid", "appid");
		ckgzh.put("secret", "secret");
		wechatMap.put("ckgzh", ckgzh);
		
		JSONObject ckxcx=new JSONObject();
		ckxcx.put("appid", "appid");
		ckxcx.put("secret", "secret");
		wechatMap.put("ckxcx", ckxcx);
	}
	
	/**
	 * 获取appid
	 * @param wechatWayType
	 * @return
	 */
	public static String  getAppid(String wechatWayType){
		return wechatMap.get(wechatWayType).getString("appid");
	}
	
	/**
	 * 获取secret
	 * @param wechatWayType
	 * @return
	 */
	public static String  getSecret(String wechatWayType){
		return wechatMap.get(wechatWayType).getString("secret");
	}
}
