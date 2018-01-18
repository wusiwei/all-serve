package com.wusw.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wusw.dto.TMpMenuDto;
import com.wusw.dto.TUserLabelDto;
import com.wusw.model.ResultResponseDto;
import com.wusw.service.WechatMpService;

@RestController
@RequestMapping("/wechat/mp")
public class WechatMpController {
	private final static Logger LOGGER = LoggerFactory.getLogger(WechatMpController.class);
	
	@Autowired
	private WechatMpService wechatMpService;
	
	@RequestMapping(value="/updateUserLabelFromWx")
	@ResponseBody
	public ResultResponseDto<?> updateUserLabelFromWx(){
		ResultResponseDto<Boolean> result = new ResultResponseDto<>();
		try{
			result.setData(wechatMpService.updateUserLabelFromWx());
			result.setCode(ResultResponseDto.CODE_SUCCESS);
		}catch(Exception e){
			result.setCode(ResultResponseDto.CODE_ERROR);
			LOGGER.error("updateUserLabelFromWx error", e);
		}
		return result;
	}
	
	@RequestMapping(value="/getUserLabel")
	@ResponseBody
	public ResultResponseDto<?> getUserLabel(){
		ResultResponseDto<List<TUserLabelDto>> result = new ResultResponseDto<>();
		try{
			result.setData(wechatMpService.getUserLabelList());
			result.setCode(ResultResponseDto.CODE_SUCCESS);
		}catch(Exception e){
			result.setCode(ResultResponseDto.CODE_ERROR);
			LOGGER.error("getUserLabel error", e);
		}
		return result;
	}
	
	@RequestMapping(value="/addUserLabel")
	@ResponseBody
	public ResultResponseDto<?> addUserLabel(@RequestBody TUserLabelDto dto){
		ResultResponseDto<Long> result = new ResultResponseDto<>();
		try{
			result.setData(wechatMpService.addUserLabel(dto));
			result.setCode(ResultResponseDto.CODE_SUCCESS);
		}catch(Exception e){
			result.setCode(ResultResponseDto.CODE_ERROR);
			LOGGER.error("addUserLabel error", e);
		}
		return result;
	}
	
	@RequestMapping(value="/modifyUserLabel")
	@ResponseBody
	public ResultResponseDto<?> modifyUserLabel(@RequestBody TUserLabelDto dto){
		ResultResponseDto<Integer> result = new ResultResponseDto<>();
		try{
			result.setData(wechatMpService.modifyUserLabel(dto));
			result.setCode(ResultResponseDto.CODE_SUCCESS);
		}catch(Exception e){
			result.setCode(ResultResponseDto.CODE_ERROR);
			LOGGER.error("modifyUserLabel error", e);
		}
		return result;
	}
	
	@RequestMapping(value="/removeUserLabel")
	@ResponseBody
	public ResultResponseDto<?> removeUserLabel(long id){
		ResultResponseDto<Integer> result = new ResultResponseDto<>();
		try{
			result.setData(wechatMpService.removeUserLabel(id));
			result.setCode(ResultResponseDto.CODE_SUCCESS);
		}catch(Exception e){
			result.setCode(ResultResponseDto.CODE_ERROR);
			LOGGER.error("removeUserLabel error", e);
		}
		return result;
	}
	
	@RequestMapping(value="/addDefaultMpMenu")
	@ResponseBody
	public ResultResponseDto<?> addDefaultMpMenu(@RequestBody List<TMpMenuDto> tMpMenuDtoList){
		ResultResponseDto<Boolean> result = new ResultResponseDto<>();
		try{
			result.setData(wechatMpService.addDefaultMpMenu(tMpMenuDtoList));
			result.setCode(ResultResponseDto.CODE_SUCCESS);
		}catch(Exception e){
			result.setData(false);
			result.setCode(ResultResponseDto.CODE_ERROR);
			LOGGER.error("addDefaultMpMenu error", e);
		}
		return result;
	}
	
	@RequestMapping(value="/addSpecialMpMenu")
	@ResponseBody
	public ResultResponseDto<?> addSpecialMpMenu(@RequestBody List<TMpMenuDto> tMpMenuDtoList){
		ResultResponseDto<Boolean> result = new ResultResponseDto<>();
		try{
			result.setData(wechatMpService.addSpecialMpMenu(tMpMenuDtoList));
			result.setCode(ResultResponseDto.CODE_SUCCESS);
		}catch(Exception e){
			result.setData(false);
			result.setCode(ResultResponseDto.CODE_ERROR);
			LOGGER.error("addSpecialMpMenu error", e);
		}
		return result;
	}
	
	@RequestMapping(value="/delSpecialMpMenu")
	@ResponseBody
	public ResultResponseDto<?> delSpecialMpMenu(String menuid){
		ResultResponseDto<Boolean> result = new ResultResponseDto<>();
		try{
			result.setData(wechatMpService.delSpecialMpMenu(menuid));
			result.setCode(ResultResponseDto.CODE_SUCCESS);
		}catch(Exception e){
			result.setData(false);
			result.setCode(ResultResponseDto.CODE_ERROR);
			LOGGER.error("delSpecialMpMenu error", e);
		}
		return result;
	}
	
	@RequestMapping(value="/getMpMenuByTagId")
	@ResponseBody
	public ResultResponseDto<?> getMpMenuByTagId(int tagId){
		ResultResponseDto<List<TMpMenuDto>> result = new ResultResponseDto<>();
		try{
			result.setData(wechatMpService.getMpMenuByTagId(tagId));
			result.setCode(ResultResponseDto.CODE_SUCCESS);
		}catch(Exception e){
			result.setCode(ResultResponseDto.CODE_ERROR);
			LOGGER.error("getMpMenuByTagId error", e);
		}
		return result;
	}
	
}
