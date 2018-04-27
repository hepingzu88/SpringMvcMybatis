package yyd.yun.dao;

import java.util.List;

import yyd.yun.beans.TbAge;

public interface TbAgeMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(TbAge record);

    int insertSelective(TbAge record);

    TbAge selectByPrimaryKey(Integer id);
    
    List<TbAge> listTbAge();
    
    int updateByPrimaryKeySelective(TbAge record);

    int updateByPrimaryKey(TbAge record);
    
    
}