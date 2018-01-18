package com.wusw.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wusw.dto.TUserDto;
import com.wusw.model.ResultResponseDto;

@FeignClient(name = "user")
public interface FUserService {
	/**
	 * 检验用户和用户渠道是否存在,不存在则创建
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/user/checkAndAddUserForWechat")
	ResultResponseDto<Long> checkAndAddUserForWechat(TUserDto dto);
}
