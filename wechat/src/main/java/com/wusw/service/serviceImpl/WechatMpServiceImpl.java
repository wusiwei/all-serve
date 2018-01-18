package com.wusw.service.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wusw.dto.TMpMenuDto;
import com.wusw.dto.TUserLabelDto;
import com.wusw.mapper.TMpMenuMapper;
import com.wusw.mapper.TUserLabelMapper;
import com.wusw.service.FAccreditService;
import com.wusw.service.WechatMpService;
import com.wusw.utils.HttpUtils;

@Service
public class WechatMpServiceImpl implements WechatMpService{
	
	@Autowired
	private TMpMenuMapper tMpMenuMapper;
	
	@Autowired
	private TUserLabelMapper tUserLabelMapper;
	
	@Autowired
	private FAccreditService fAccreditService;
	
	@Value("${wechat.mp.tags.getPath}")
	private String tagsGetPath;
	
	@Value("${wechat.mp.tags.createPath}")
	private String tagsCreatePath;
	
	@Value("${wechat.mp.tags.updatePath}")
	private String tagsUpdatePath;
	
	@Value("${wechat.mp.tags.deletePath}")
	private String tagsDeletePath;
	
	@Value("${wechat.mp.menu.createPath}")
	private String menuCreatePath;
	
	@Value("${wechat.mp.menu.addconditional}")
	private String menuAddconditional;
	
	@Value("${wechat.mp.menu.delconditional}")
	private String menuDelconditional;
	
	String testString="5__XZtU0xBY_AZ2uq2GsIAngnjFmlA5g0XMTeFoScExTI-dsdY1J-pIpCXR3_oDhD8SrN5vF6iMDby5CtruF4yDZzOUhes7YJ3efYEW5WqjYN1cEp4bY453jXLKH1MaX6JzhY8jYn2e1ng04sITXXdAJAWAA";
	
	@Override
	@Transactional
	public boolean updateUserLabelFromWx(){
		String access_token = fAccreditService.getWxAccess();
//		String access_token =testString;
		String result=HttpUtils.requestByGet(tagsGetPath, "access_token="+access_token);
		JSONObject jsonObject=JSONObject.parseObject(result);
		JSONArray tagsJSONArray=jsonObject.getJSONArray("tags");
		String js=JSONObject.toJSONString(tagsJSONArray, SerializerFeature.WriteClassName);
		List<TUserLabelDto>  tUserLabelDtoList = JSONObject.parseArray(js, TUserLabelDto.class);
		tUserLabelMapper.deleteAllUserlabel();
		tUserLabelMapper.batchAddUserlabel(tUserLabelDtoList);
		return true;
	}
	
	@Override
	public long addUserLabel(TUserLabelDto dto){
		JSONObject tag=new JSONObject();
		tag.put("name", dto.getName());
		JSONObject sendStr=new JSONObject();
		sendStr.put("tag", tag);
		
		String access_token = fAccreditService.getWxAccess();
//		String access_token =testString;
		String result=HttpUtils.requestByPost(tagsCreatePath+"?access_token="+access_token, sendStr.toJSONString());
		JSONObject resultObject=JSONObject.parseObject(result);
		tag = resultObject.getJSONObject("tag");
		dto.setId(tag.getLong("id"));
		tUserLabelMapper.addUserlabel(dto);
		return dto.getId();
	}
	 
	@Override
	public int modifyUserLabel(TUserLabelDto dto){
		JSONObject tag=new JSONObject();
		tag.put("id", dto.getId());
		tag.put("name", dto.getName());
		JSONObject sendStr=new JSONObject();
		sendStr.put("tag", tag);
		
		String access_token = fAccreditService.getWxAccess();
//		String access_token =testString;
		String result=HttpUtils.requestByPost(tagsUpdatePath+"?access_token="+access_token, sendStr.toJSONString());
		JSONObject resultObject=JSONObject.parseObject(result);
		String errcode = resultObject.getString("errcode");
		if("0".equals(errcode)){
			return tUserLabelMapper.modifyUserlabel(dto);
		}
		return 0;
	}
	
	@Override
	public int removeUserLabel(long id){
		JSONObject tag=new JSONObject();
		tag.put("id", id);
		JSONObject sendStr=new JSONObject();
		sendStr.put("tag", tag);
		
		String access_token = fAccreditService.getWxAccess();
//		String access_token =testString;
		String result=HttpUtils.requestByPost(tagsDeletePath+"?access_token="+access_token, sendStr.toJSONString());
		JSONObject resultObject=JSONObject.parseObject(result);
		String errcode = resultObject.getString("errcode");
		if("0".equals(errcode)){
			return tUserLabelMapper.removeUserlabel(id);
		}
		return 0;
	}
	
	@Override
	public List<TUserLabelDto> getUserLabelList(){
		return tUserLabelMapper.getAllUserlabel();
	}
	
	
	@Transactional
	@Override
	public boolean addDefaultMpMenu(List<TMpMenuDto> tMpMenuDtoList) throws SQLException{
		tMpMenuMapper.delDefaultMpMenu();
		
		for(TMpMenuDto dto : tMpMenuDtoList){
			tMpMenuMapper.addMpMenu(dto);
			if(dto.getSub_button() ==1){
				tMpMenuMapper.batchAddMpMenu(dto);
			}
		}
		
		JSONObject mpMenu=dealMenuList(tMpMenuDtoList);
		String access_token = fAccreditService.getWxAccess();
//		String access_token =testString;
		String result=HttpUtils.requestByPost(menuCreatePath+"?access_token="+access_token, mpMenu.toJSONString());
		System.out.println(result);
		JSONObject resultObject=JSONObject.parseObject(result);
		String errcode = resultObject.getString("errcode");
		if("0".equals(errcode)){
			return true;
		}else{
			throw new SQLException();
		}
	}
	
	@Transactional
	@Override
	public boolean addSpecialMpMenu(List<TMpMenuDto> tMpMenuDtoList){
		int tag_id=tMpMenuDtoList.get(0).getTag_id();
		JSONObject mpMenu=dealMenuList(tMpMenuDtoList);
		JSONObject matchrule=new JSONObject();
		matchrule.put("tag_id", tag_id);
		mpMenu.put("matchrule", matchrule);
		
		String access_token = fAccreditService.getWxAccess();
//		String access_token =testString;
		System.out.println(mpMenu.toJSONString());
		String result=HttpUtils.requestByPost(menuAddconditional+"?access_token="+access_token, mpMenu.toJSONString());
		System.out.println(result);
		JSONObject resultObject=JSONObject.parseObject(result);
		String menuid = resultObject.getString("menuid");
		System.out.println(menuid);
		if(menuid==null || menuid==""){
			return false;
		}
		for(TMpMenuDto dto : tMpMenuDtoList){
			dto.setMenuid(menuid);
			tMpMenuMapper.addMpMenu(dto);
			if(dto.getSub_button() ==1){
				tMpMenuMapper.batchAddMpMenu(dto);
			}
		}
		return true;
	}
	
	@Transactional
	@Override
	public boolean delSpecialMpMenu(String menuid) throws SQLException{
		JSONObject delJson=new JSONObject();
		delJson.put("menuid", menuid);
		
		String access_token = fAccreditService.getWxAccess();
//		String access_token =testString;
		System.out.println(delJson.toJSONString());
		String result=HttpUtils.requestByPost(menuDelconditional+"?access_token="+access_token, delJson.toJSONString());
		System.out.println(result);
		JSONObject resultObject=JSONObject.parseObject(result);
		String errcode = resultObject.getString("errcode");
		if("0".equals(errcode)){
			tMpMenuMapper.delMpMenuByMenuid(menuid);
			return true;
		}else{
			throw new SQLException();
		}
	}
	
	@Transactional
	@Override
	public boolean modifySpecialMpMenu(List<TMpMenuDto> tMpMenuDtoList) throws SQLException{
		JSONObject delJson=new JSONObject();
		String menuid=tMpMenuDtoList.get(0).getMenuid();
		delJson.put("menuid", menuid);
		
		String access_token = fAccreditService.getWxAccess();
//		String access_token =testString;
		String result=HttpUtils.requestByPost(menuDelconditional+"?access_token="+access_token, delJson.toJSONString());
		JSONObject resultObject=JSONObject.parseObject(result);
		String errcode = resultObject.getString("errcode");
		if("0".equals(errcode)){
			tMpMenuMapper.delMpMenuByMenuid(menuid);
			addSpecialMpMenu(tMpMenuDtoList);
			return true;
		}else{
			throw new SQLException();
		}
	}
	
	@Override
	public List<TMpMenuDto> getMpMenuByTagId(int tagId){
		return tMpMenuMapper.getMpMenuByTagId(tagId);
	}
	
	/**
	 * 将菜单集合封装成相应的json对象
	 * @param tMpMenuDtoList
	 * @return
	 */
	public JSONObject dealMenuList(List<TMpMenuDto> tMpMenuDtoList){
		JSONArray parentMenu=new JSONArray();
		for(TMpMenuDto dto:tMpMenuDtoList){
			if(dto.getSub_button()==0){
				parentMenu.add(getMenuByMenuDto(dto));
			}else{
				JSONObject menu=new JSONObject();
				menu.put("name", dto.getName());
				JSONArray subMenuArray=new JSONArray();
				for(TMpMenuDto subDto:dto.getSubMenuDtoList()){
					subMenuArray.add(getMenuByMenuDto(subDto));
				}
				menu.put("sub_button", subMenuArray);
				parentMenu.add(menu);
			}
		}
		JSONObject a=new JSONObject();
		a.put("button", parentMenu);
		return a;
	}
	
	/**
	 * 将菜单对象转成相应的json对象
	 * @param dto
	 * @return
	 */
	private JSONObject getMenuByMenuDto(TMpMenuDto dto){
		JSONObject menu = new JSONObject();
		if("view".equals(dto.getType())){//菜单为跳转页面时
			menu.put("type", "view");
			menu.put("name", dto.getName());
			menu.put("url", dto.getUrl());
		}else if("media_id".equals(dto.getType())){//菜单为素材页面时
			menu.put("type", "media_id");
			menu.put("name", dto.getName());
			menu.put("media_id", dto.getMedia_id());
		}else if("miniprogram".equals(dto.getType())){//当菜单为小程序时
			menu.put("type", "miniprogram");
			menu.put("name", dto.getName());
			menu.put("url", dto.getUrl());
			menu.put("appid", dto.getAppid());
			menu.put("pagepath", dto.getPagepath());
		}else if("click".equals(dto.getType())){//菜单为点击时
			menu.put("type", "click");
			menu.put("name", dto.getName());
			menu.put("key", dto.getKey());
		}
		return menu;
		
	}
	
	
	
	
	
}
