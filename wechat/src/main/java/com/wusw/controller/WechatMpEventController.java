package com.wusw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wusw.service.WechatMpEventService;

@RestController
@RequestMapping("/wechat/mp")
public class WechatMpEventController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(WechatMpEventController.class);
	
	@Autowired
	private WechatMpEventService wechatMpEventService;
	
	
	@PostMapping("/eventFromCkdyh")
    @ResponseBody
    public void dealWayFromCkdyh (HttpServletRequest request, HttpServletResponse response ) {
        try {
        	wechatMpEventService.dealWayFromCkdyh(request, response);
        } catch (Exception e) {
            LOGGER.error("wechat eventFromCkdyh dealWayFromCkdyh error",e);
        }
    }
    
    @GetMapping("/eventFromCkdyh")
    @ResponseBody
    public void checkWxFromCkdyh (HttpServletRequest request, HttpServletResponse response ) {
        try {
        	wechatMpEventService.checkWx(request,response);
        } catch (Exception e) {
            LOGGER.error("wechat eventFromCkdyh checkWx error",e);
        }
    }
    
    @PostMapping("/eventFromCkfwh")
    @ResponseBody
    public void dealWayFromCkfwh (HttpServletRequest request, HttpServletResponse response ) {
        try {
        	wechatMpEventService.dealWayFromCkfwh(request, response);
        } catch (Exception e) {
            LOGGER.error("wechat eventFromCkfwh dealWayFromCkfwh error",e);
        }
    }
    
    @GetMapping("/eventFromCkfwh")
    @ResponseBody
    public void checkWxFromCkfwh (HttpServletRequest request, HttpServletResponse response ) {
        try {
        	wechatMpEventService.checkWx(request,response);
        } catch (Exception e) {
            LOGGER.error("wechat eventFromCkfwh checkWx error",e);
        }
    }
}
