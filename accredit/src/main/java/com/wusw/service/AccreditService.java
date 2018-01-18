package com.wusw.service;

import com.wusw.dto.CheckResult;
import com.wusw.model.UserLoginDto;

public interface AccreditService {

	/**
	 * 获取微信公众号接口授权凭证
	 * @return
	 */
	String getWxAccessToken();

	/**
	 * 根据微信公众号登录的code获取用户的openid
	 * @param wxCode
	 * @return
	 */
	String getOpenidByCode(String wxCode);

	/**
	 * 获取调用微信JS接口的临时票据
	 * @return
	 */
	String getJsapiTicket();

	/**
	 * 签发jwt,权限令牌
	 * @param dto
	 * @return
	 */
	String createJWT(UserLoginDto dto);

	/**
	 * 校验jwt令牌
	 * @param jwtStr
	 * @return
	 */
	CheckResult validateJWT(String jwtStr);
	
}
