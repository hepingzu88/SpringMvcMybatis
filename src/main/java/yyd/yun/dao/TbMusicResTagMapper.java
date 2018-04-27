package yyd.yun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.beans.TbMusicResTag;

public interface TbMusicResTagMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByPrimaryMusicResId(Integer musicResId);

    int insert(TbMusicResTag record);

    int insertSelective(TbMusicResTag record);

    List<TbMusicResTag> selectByPrimaryKey(TbMusicResTag tag);

    int updateByPrimaryKeySelective(TbMusicResTag record);

    int updateByPrimaryKey(TbMusicResTag record);
    
    int deleteMusicResTagId(@Param("tagId")Integer tagId);
}