package com.wusw;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.wusw.dto.CheckResult;
import com.wusw.service.FAccreditService;

@Component
public class PreRequstRightFilter extends ZuulFilter {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(PreRequstRightFilter.class);

	@Autowired
	private FAccreditService fAccreditService;
	
	@Override
	public Object run() {
		RequestContext rqc=RequestContext.getCurrentContext();
		
		/*if(taken==null){
			request.getCookies();
			Map<String,Cookie> cookieMap = ReadCookieMap(request);
		    if(cookieMap.containsKey("")){
		        Cookie cookie = (Cookie)cookieMap.get("");
		        cookie.getValue();
		        cookie.setValue("1111");
		        return cookie;
		    }else{
		        return null;
		    }
		}*/
		String originalRequestPath = (String) rqc.get(FilterConstants.REQUEST_URI_KEY);
		if("/user/login".equals(originalRequestPath)){
			return null;
		}
		
		HttpServletRequest request=rqc.getRequest();
		String taken=request.getParameter("loginUserKey");
		
//		LOGGER.info("请求转发前拦截"+request.getRequestURL().toString());
		LOGGER.info("请求转发前拦截"+(String)originalRequestPath);
		/*if(taken == null || taken=="") {  
			LOGGER.warn("access token is empty");  
	        //过滤该请求，不往下级服务去转发请求，到此结束  
			rqc.setSendZuulResponse(false);  
			rqc.setResponseStatusCode(401);  
			rqc.setResponseBody("{\"result\":\"accessToken为空!\"}");  
			rqc.getResponse().setContentType("application/json;charset=UTF-8");  
	        return null;  
	    }else{
	    	String result=fAccreditService.validateJWT(taken);
	    	LOGGER.info(result); 
	    	CheckResult checkResult=JSONObject.parseObject(result, com.wusw.run.dto.CheckResult.class);
	    	if(!checkResult.isSuccess()){
	    		rqc.setSendZuulResponse(false);  
				rqc.setResponseStatusCode(401);  
				rqc.setResponseBody("{\"result\":\"accessToken错误!\"}");  
				rqc.getResponse().setContentType("application/json;charset=UTF-8");
				return null;
	    	}else{
	    		LOGGER.info("access token ok");  
	    		return null;
	    	}
	    }*/
		
		
		return null;
	}

	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return FilterConstants.PRE_DECORATION_FILTER_ORDER+1;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return FilterConstants.PRE_TYPE;
	}

}
