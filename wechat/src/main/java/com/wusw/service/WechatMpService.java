package com.wusw.service;

import java.sql.SQLException;
import java.util.List;

import com.wusw.dto.TMpMenuDto;
import com.wusw.dto.TUserLabelDto;

public interface WechatMpService {

	/**
	 * 将微信公众平台的用户标签   更新到本地
	 * @return
	 */
	boolean updateUserLabelFromWx();
	
	/**
	 * 获取所有用户标签列表
	 * @return
	 */
	List<TUserLabelDto> getUserLabelList();

	/**
	 * 新增用户标签
	 * @param dto
	 * @return
	 */
	long addUserLabel(TUserLabelDto dto);

	/**
	 * 修改用户标签
	 * @param dto
	 * @return
	 */
	int modifyUserLabel(TUserLabelDto dto);

	/**
	 * 创建默认菜单
	 * @param tMpMenuDtoList
	 * @return
	 * @throws SQLException
	 */
	boolean addDefaultMpMenu(List<TMpMenuDto> tMpMenuDtoList) throws SQLException;

	/**
	 * 创建个性化菜单
	 * @param tMpMenuDtoList
	 * @return
	 */
	boolean addSpecialMpMenu(List<TMpMenuDto> tMpMenuDtoList);

	/**
	 * 删除个性化菜单
	 * @param menuid
	 * @return
	 * @throws SQLException
	 */
	boolean delSpecialMpMenu(String menuid) throws SQLException;

	/**
	 * 修改个性化菜单
	 * @param tMpMenuDtoList
	 * @return
	 * @throws SQLException
	 */
	boolean modifySpecialMpMenu(List<TMpMenuDto> tMpMenuDtoList) throws SQLException;

	/**
	 * 删除用户标签
	 * @param id
	 * @return
	 */
	int removeUserLabel(long id);

	/**
	 * 根据用户标签获取对应菜单
	 * @param tagId
	 * @return
	 */
	List<TMpMenuDto> getMpMenuByTagId(int tagId);

	

}
