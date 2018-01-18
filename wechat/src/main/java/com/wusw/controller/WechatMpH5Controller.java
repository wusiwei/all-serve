package com.wusw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wusw.model.ResultResponseDto;
import com.wusw.service.WechatMpH5Service;

@RestController
@RequestMapping("/wechat/mp")
public class WechatMpH5Controller {
	private final static Logger LOGGER = LoggerFactory.getLogger(WechatMpH5Controller.class);
	
	@Autowired
	private WechatMpH5Service wechatMpH5Service;
	
	@RequestMapping(value="/wechatMpJssdkCheckConfig")
	@ResponseBody
	public ResultResponseDto<?> wechatMpJssdkCheckConfig(String wechatWayType, String url){
		ResultResponseDto<JSONObject> result = new ResultResponseDto<>();
		try{
			result.setData(wechatMpH5Service.wechatMpJssdkCheckConfig(wechatWayType,url));
			result.setCode(ResultResponseDto.CODE_SUCCESS);
		}catch(Exception e){
			result.setCode(ResultResponseDto.CODE_ERROR);
			LOGGER.error("wechatMpJssdkCheckConfig error", e);
		}
		return result;
	}
}
