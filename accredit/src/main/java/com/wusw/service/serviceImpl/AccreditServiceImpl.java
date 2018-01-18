package com.wusw.service.serviceImpl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wusw.dto.CheckResult;
import com.wusw.dto.WxAccessToken;
import com.wusw.dto.WxOauth2AccessToken;
import com.wusw.model.UserLoginDto;
import com.wusw.service.AccreditService;
import com.wusw.util.HttpUtils;
import com.wusw.util.TokenMgr;

@Service
public class AccreditServiceImpl implements AccreditService{
	
	@Value("${wx.vipcn.accessTokenPath}")
	private String wxAccessTokenPath;
	
	@Value("${wx.vipcn.jsapiTicketPath}")
	private String jsapiTicketPath;
	
	@Value("${wx.vipcn.appid}")
	private String wxAppid;
	
	@Value("${wx.vipcn.secret}")
	private String wxSecret;
	
	@Value("${wx.oauth2.accessTokenPath}")
	private String wxOauth2AccessTokenPath;
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public String getWxAccessToken(){
		String accessTokenstring=stringRedisTemplate.opsForValue().get("wxAccessToken");
		if(accessTokenstring==null || accessTokenstring==""){
			String getParam="grant_type=client_credential&appid="+wxAppid+"&secret="+wxSecret;
			String result=HttpUtils.requestByGet(wxAccessTokenPath, getParam);
			System.out.println(result);
			WxAccessToken wxAccessToken=JSON.parseObject(result, com.wusw.dto.WxAccessToken.class);
			
			if("-1".equals(wxAccessToken.getErrcode())){
				result=HttpUtils.requestByGet(wxAccessTokenPath, getParam);
				wxAccessToken=JSON.parseObject(result, com.wusw.dto.WxAccessToken.class);
			}
			accessTokenstring=wxAccessToken.getAccess_token();
			Long timeOut=wxAccessToken.getExpires_in();
			System.out.println(timeOut);
			stringRedisTemplate.opsForValue().set("wxAccessToken", accessTokenstring ,timeOut-1,TimeUnit.SECONDS);
		}
		
		return accessTokenstring;
	}
	
	@Override
	public String getJsapiTicket(){
		String jsapi_ticket=stringRedisTemplate.opsForValue().get("jsapi_ticket");
		if(jsapi_ticket==null || jsapi_ticket==""){
			String accessTokenstring=getWxAccessToken();
			String getParam="access_token="+accessTokenstring+"&type=jsapi";
			String result=HttpUtils.requestByGet(jsapiTicketPath, getParam);
			System.out.println(result);
			JSONObject resultJson=JSONObject.parseObject(result);
			
			if("-1".equals(resultJson.getString("errcode"))){
				result=HttpUtils.requestByGet(wxAccessTokenPath, getParam);
				resultJson=JSON.parseObject(result);
			}
			jsapi_ticket=resultJson.getString("ticket");
			Long timeOut=resultJson.getLong("expires_in");
			System.out.println(timeOut);
			stringRedisTemplate.opsForValue().set("jsapi_ticket", jsapi_ticket ,timeOut-1,TimeUnit.SECONDS);
		}
		
		return jsapi_ticket;
	}
	
	@Override
	public String getOpenidByCode(String wxCode){
		String openid=stringRedisTemplate.opsForValue().get(wxCode);
		if(openid==null || openid==""){
			String getParam="appid="+wxAppid+"&secret="+wxSecret+"&code="+wxCode+"&grant_type=authorization_code";
			String result=HttpUtils.requestByGet(wxOauth2AccessTokenPath, getParam);
			System.out.println(result);
			WxOauth2AccessToken wxAccessToken=JSON.parseObject(result, com.wusw.dto.WxOauth2AccessToken.class);
			if("40029".equals(wxAccessToken.getErrcode())){
				return "";
			}
			openid=wxAccessToken.getOpenid();
			System.out.println(openid);
			stringRedisTemplate.opsForValue().set(wxCode, openid ,wxAccessToken.getExpires_in(),TimeUnit.SECONDS);
		}
		
		return openid;
	}
	
	@Override
	public String createJWT(UserLoginDto dto){
		String jwt=TokenMgr.createJWT(dto.getUserid().toString(), TokenMgr.generalSubject(dto), 60*60*1000);
		return jwt;
	}
	
	public static void main(String[] args) {
		UserLoginDto dto=new UserLoginDto();
		dto.setUserid(1L);
		dto.setUserName("aaaa");
		AccreditServiceImpl a=new AccreditServiceImpl();
		System.out.println(a.createJWT(dto));
	}
	
	@Override
    public CheckResult validateJWT(String jwtStr){
    	CheckResult checkResult=TokenMgr.validateJWT(jwtStr);
		return checkResult;
	}
	
}
