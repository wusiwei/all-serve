package com.wusw.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wusw.dto.CheckResult;


@FeignClient(name = "accredit")
public interface FAccreditService {
	
	@RequestMapping(value="/accredit/validateJWT")
	String validateJWT(@RequestParam(value = "jwtStr") String jwtStr);
	
}
