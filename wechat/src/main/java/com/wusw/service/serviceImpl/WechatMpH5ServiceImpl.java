package com.wusw.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wusw.service.FAccreditService;
import com.wusw.service.WechatMpH5Service;
import com.wusw.utils.EnAndDeCodeUtils;
import com.wusw.utils.RandomUtils;
import com.wusw.utils.WeChatContent;

@Service
public class WechatMpH5ServiceImpl implements WechatMpH5Service{
	
	@Autowired
	private FAccreditService fAccreditService;
	
	@Override
	public JSONObject wechatMpJssdkCheckConfig(String wechatWayType,String url){
		JSONObject rJson=new JSONObject();
		String appid=WeChatContent.getAppid(wechatWayType);
		String jsapi_ticket=fAccreditService.getJsapiTicket();
		//String jsapi_ticket="HoagFKDcsGMVCIY2vOjf9iRstLS4ca59KejrMttkfqh4T2EWmnys9REt8okqB5zmbCC_hZq0IcbEsszWWf-bFA";
		String noncestr= RandomUtils.createUpperAndNumString(16);
		Long timestamp=System.currentTimeMillis()/1000;
		String signature="jsapi_ticket="+jsapi_ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;
		System.out.println(signature);
		signature=EnAndDeCodeUtils.SHA1Encode(signature);
		rJson.put("appId", appid);
		rJson.put("noncestr", noncestr);
		rJson.put("timestamp", timestamp);
		rJson.put("signature", signature);
		return rJson;
	}
	
}
