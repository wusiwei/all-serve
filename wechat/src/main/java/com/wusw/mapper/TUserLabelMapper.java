package com.wusw.mapper;

import java.util.List;

import com.wusw.dto.TUserLabelDto;

public interface TUserLabelMapper {
    void batchAddUserlabel(List<TUserLabelDto>  tUserLabelDtoList);
	
	int deleteAllUserlabel();
	
	int addUserlabel(TUserLabelDto dto);
	
	int modifyUserlabel(TUserLabelDto dto);
	
	int removeUserlabel(long id);
	
	List<TUserLabelDto> getAllUserlabel();
}
