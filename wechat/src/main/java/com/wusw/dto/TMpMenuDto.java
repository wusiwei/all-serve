package com.wusw.dto;

import java.util.List;

public class TMpMenuDto {
	private Long id;
	
	private String type;
	
	private String name;
	
	private String key;
	
	private String url;
	
	private String media_id;
	
	private String appid;
	
	private String pagepath;
	
	private int sub_button;
	
	private Long parent_id;
	
	private int tag_id;
	
	private String menuid;

	private List<TMpMenuDto> subMenuDtoList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPagepath() {
		return pagepath;
	}

	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}

	public int getSub_button() {
		return sub_button;
	}

	public void setSub_button(int sub_button) {
		this.sub_button = sub_button;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public int getTag_id() {
		return tag_id;
	}

	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public List<TMpMenuDto> getSubMenuDtoList() {
		return subMenuDtoList;
	}

	public void setSubMenuDtoList(List<TMpMenuDto> subMenuDtoList) {
		this.subMenuDtoList = subMenuDtoList;
	}
	
}
