package com.wusw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wusw.dto.CheckResult;
import com.wusw.model.ResultResponse;
import com.wusw.model.UserLoginDto;
import com.wusw.service.AccreditService;



@RestController
@RequestMapping("/accredit")
public class AccreditController {
	
    private final static Logger LOGGER = LoggerFactory.getLogger(AccreditController.class);
	
    @Autowired
	private AccreditService accreditService;
    
	@RequestMapping(value="/getWxAccess")
	@ResponseBody
	public String getWxAccess(){
		try{
			return accreditService.getWxAccessToken();
		}catch(Exception e){
			
			LOGGER.error("getWxAccess error", e);
			return "";
		}
		
	}
	
	@RequestMapping(value="/getJsapiTicket")
	@ResponseBody
	public String getJsapiTicket(){
		try{
			return accreditService.getJsapiTicket();
		}catch(Exception e){
			
			LOGGER.error("getJsapiTicket error", e);
			return "";
		}
		
	}
	
	@RequestMapping(value="/getOpenidByCode")
	@ResponseBody
	public String getOpenidByCode(String wxCode){
		try{
			return accreditService.getOpenidByCode(wxCode);
		}catch(Exception e){
			
			LOGGER.error("getWxAccess error", e);
			return "";
		}
	}
	
	@RequestMapping("/getJWTByUserLogin")
	@ResponseBody
	public ResultResponse<?> getJWTByUserLogin(@RequestBody UserLoginDto dto) {
	    ResultResponse<String> result = new ResultResponse<>();
	    try {
	        result.setCode(ResultResponse.CODE_SUCCESS);
	        result.setData(accreditService.createJWT(dto));
	    } catch (Exception e) {
	        result.setCode(ResultResponse.CODE_ERROR);
	        result.setMessage(e.getMessage());
	        LOGGER.error("accredit getJWTByUserLogin error",e);
	    }
	    return result;
	        
	}
	
	@RequestMapping("/validateJWT")
	@ResponseBody
	public CheckResult validateJWT(String jwtStr) {
	    CheckResult result = new CheckResult();
	    try {
	    	result=accreditService.validateJWT(jwtStr);
	    } catch (Exception e) {
	    	result.setSuccess(false);
	        LOGGER.error("accredit validateJWT error",e);
	    }
	    return result;
	        
	}
}
