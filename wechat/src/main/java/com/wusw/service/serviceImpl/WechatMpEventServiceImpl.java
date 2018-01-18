package com.wusw.service.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.wusw.dto.Signature;
import com.wusw.dto.TUserDto;
import com.wusw.dto.WechatEventDto;
import com.wusw.service.FAccreditService;
import com.wusw.service.FUserService;
import com.wusw.service.WechatMpEventService;
import com.wusw.utils.CheckUtil;
import com.wusw.utils.EnAndDeCodeUtils;
import com.wusw.utils.HttpUtils;

@Service
public class WechatMpEventServiceImpl implements WechatMpEventService {
	
	@Autowired
	private FAccreditService fAccreditService;
	
	@Autowired
	private FUserService fUserService;
	
	@Override
	public void dealWayFromCkfwh(HttpServletRequest request, HttpServletResponse response){
		InputStream inStream;
		PrintWriter pw;
		try {
			inStream = request.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
		    StringBuilder strber = new StringBuilder();
		    String line = null;
		    while ((line = reader.readLine()) != null){
		    	 strber.append(line);
		    }
		    System.out.println(strber);
		    XStream xStream = new XStream();
		    xStream.alias("xml", com.wusw.dto.WechatEventDto.class);
		    String requestData=strber.toString();
		    WechatEventDto event =(WechatEventDto) xStream.fromXML(requestData);
		    this.dealEvent(event,"ckfwh");
		    pw= response.getWriter();
		    pw.print("");
		    pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			inStream=null;
			pw=null;
		}
	}
    
	@Override
	public void dealWayFromCkdyh(HttpServletRequest request, HttpServletResponse response){
		InputStream inStream;
		PrintWriter pw;
		try {
			inStream = request.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
		    StringBuilder strber = new StringBuilder();
		    String line = null;
		    while ((line = reader.readLine()) != null){
		    	 strber.append(line);
		    }
		    System.out.println(strber);
		    XStream xStream = new XStream();
		    xStream.alias("xml", com.wusw.dto.WechatEventDto.class);
		    String requestData=strber.toString();
		    WechatEventDto event =(WechatEventDto) xStream.fromXML(requestData);
		    this.dealEvent(event,"ckdyh");
		    pw= response.getWriter();
		    pw.print("");
		    pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			inStream=null;
			pw=null;
		}
	}
	
	private TUserDto getUserInfoByOpenid(String openid,String wechatWay){
    	TUserDto user = new TUserDto();
    	//   	String wxAccessToken=fAccreditService.getWxAccess(wechatWay);
    	String wxAccessToken="5_3SxBtrEV7l4fsCiT1bD9voI9UU3Nr47BG1f64LzOja9t7EG1oISw18THjvMErcerflq4TldFHtFegDTvZImDr7J22nl4i8F6AHOEQWtij8PnKsRewt-hAEGWuEgGVZiADAAPT";
    	String paramStr="access_token="+wxAccessToken+"&openid="+openid+"&lang=zh_CN";
    	String result=HttpUtils.requestByGet("https://api.weixin.qq.com/cgi-bin/user/info",paramStr);
    	JSONObject rJson=JSONObject.parseObject(result);
    	user.setWechatNickname(rJson.getString("nickname")==null ?EnAndDeCodeUtils.encodeByBase64("未取得微信昵称") :EnAndDeCodeUtils.encodeByBase64(rJson.getString("nickname")));
    	user.setUnionid(rJson.getString("unionid"));
    	user.setSubscribe(rJson.getInteger("subscribe"));
    	user.setHeadimgurl(rJson.getString("headimgurl"));
    	user.setWechatWay(wechatWay);
    	return user;
    }
	
    
    private void dealEvent(WechatEventDto event,String wechatWay) {
    	if (event == null) return;
    	String openid=event.getFromUserName();
    	TUserDto user = getUserInfoByOpenid(openid,wechatWay);
    	String encodeOpenid=EnAndDeCodeUtils.encodeByBase64(openid);
    	user.setOpenid(encodeOpenid);
    	//订阅-->更新为注册用户
    	if ("subscribe".equals(event.getEvent()) && StringUtils.isBlank(event.getEventKey())) {
    		user.setSubscribe(1);
        //取消订阅-->更新为未关注状态
    	} else if ("unsubscribe".equals(event.getEvent())) {
    		user.setSubscribe(0);
    	//扫描二维码
    	} else if ("subscribe".equals(event.getEvent()) && StringUtils.isNotBlank(event.getEventKey())){
    		String eventKey = event.getEventKey().substring(8);
    		user.setReferrerId(Long.parseLong(eventKey));
    		user.setSubscribe(1);
    	}
    	fUserService.checkAndAddUserForWechat(user);
    }
	
    @Override
	public void checkWx(HttpServletRequest request, HttpServletResponse response) {
    	Signature sg = new Signature(request.getParameter("signature"),request.getParameter("timestamp"),
    			request.getParameter("nonce"),request.getParameter("echostr"));
        PrintWriter out;
		try {
			out = response.getWriter();
			if(CheckUtil.checkSignature(sg)){
		    	out.print(sg.getEchostr());
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out=null;
		}
    			
	}
}
