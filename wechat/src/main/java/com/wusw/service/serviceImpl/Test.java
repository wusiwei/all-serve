package com.wusw.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wusw.dto.TMpMenuDto;
import com.wusw.dto.TUserLabelDto;
import com.wusw.utils.HttpUtils;

public class Test {
	/*public static void main(String[] args) {
		String access_token = "5_b7a4sceSohgaqDybX0FTK_yaWBV1PXCleFUDySaFmSwmzz8Ga45pqKXfxzOY2-Hcvur2E_RRaaQP0QungU5hj8xaK4KnKRKRd-gqfl7drgX2VVkaIPng2DoJ8xZrOGrmHPjEv0k_lbWbum5qCDShADAMNC";
		String result=HttpUtils.requestByGet("https://api.weixin.qq.com/cgi-bin/tags/get", "access_token="+access_token);
		JSONObject jsonObject=JSONObject.parseObject(result);
		JSONArray tagsJSONArray=jsonObject.getJSONArray("tags");
		String js=JSONObject.toJSONString(tagsJSONArray, SerializerFeature.WriteClassName);
		List<TUserLabelDto>  collection = JSONObject.parseArray(js, TUserLabelDto.class);
		System.out.println(collection.size());
	}*/
	
	public static void main(String[] args) {
		WechatMpServiceImpl a=new WechatMpServiceImpl();
		TMpMenuDto a1=new TMpMenuDto();
		TMpMenuDto a2=new TMpMenuDto();
		
		a1.setName("一级菜单1");
		a1.setSub_button(0);
		a1.setType("view");
		a1.setUrl("url111111");
		
		
		a2.setName("一级菜单2");
		a2.setSub_button(1);
		
		TMpMenuDto a3=new TMpMenuDto();
		a3.setName("二级菜单3");
		a3.setType("view");
		a3.setUrl("二级菜单url33333");
		
		TMpMenuDto a4=new TMpMenuDto();
		a4.setName("二级菜单4");
		a4.setType("media_id");
		a4.setMedia_id("media_id444444");
		
		
		TMpMenuDto a5=new TMpMenuDto();
		a5.setName("二级菜单5");
		a5.setType("click");
		a5.setMedia_id("click5555");
		
		TMpMenuDto a6=new TMpMenuDto();
		a6.setName("二级菜单6");
		a6.setType("miniprogram");
		a6.setUrl("二级菜单url66666");
		a6.setAppid("appid66666666");
		a6.setPagepath("pagepath6666666");
		
		List<TMpMenuDto> a1List=new ArrayList<TMpMenuDto>();
		a1List.add(a3);
		a1List.add(a4);
		a1List.add(a5);
		a1List.add(a6);
		
		a2.setSubMenuDtoList(a1List);
		
		List<TMpMenuDto> a2List=new ArrayList<TMpMenuDto>();
		a2List.add(a1);
		a2List.add(a2);
		JSONObject aa=a.dealMenuList(a2List);
		System.out.println(aa.toJSONString());
	}
}
