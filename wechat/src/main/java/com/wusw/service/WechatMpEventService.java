package com.wusw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信公众号事件监听
 * @author Administrator
 *
 */
public interface WechatMpEventService {

	void dealWayFromCkfwh(HttpServletRequest request, HttpServletResponse response);

	void dealWayFromCkdyh(HttpServletRequest request, HttpServletResponse response);

	void checkWx(HttpServletRequest request, HttpServletResponse response);

}
