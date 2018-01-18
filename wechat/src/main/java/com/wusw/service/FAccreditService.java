package com.wusw.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "accredit")
public interface FAccreditService {
	@RequestMapping(value="/accredit/getWxAccess")
	String getWxAccess();
	
	@RequestMapping(value="/accredit/getJsapiTicket")
	String getJsapiTicket();
}
