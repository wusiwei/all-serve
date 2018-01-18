package com.wusw.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信公众号号，h5页面开发
 * @author Administrator
 *
 */
public interface WechatMpH5Service {

	/**
	 * 微信公众号JSSDK调用,检查配置
	 * @param wechatWayType
	 * @param url
	 * @return
	 */
	JSONObject wechatMpJssdkCheckConfig(String wechatWayType, String url);

}
