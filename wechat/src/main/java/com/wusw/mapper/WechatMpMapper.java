package com.wusw.mapper;

import java.util.List;

import com.wusw.dto.TUserLabelDto;

public interface WechatMpMapper {
	void batchAddUserlabel(List<TUserLabelDto>  tUserLabelDtoList);
	
	int deleteAllUserlabel();
}
