package com.wusw.mapper;

import java.util.List;

import com.wusw.dto.TMpMenuDto;

public interface TMpMenuMapper {
	void batchAddMpMenu(TMpMenuDto dto);
	
	int addMpMenu(TMpMenuDto dto);
	
	int delMpMenuByMenuid(String menuid);
	
	List<TMpMenuDto> getMpMenuByTagId(int tagId);
	
	int delDefaultMpMenu();
}
