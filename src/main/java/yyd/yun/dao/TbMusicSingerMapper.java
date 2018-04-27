package yyd.yun.dao;

import yyd.yun.beans.TbMusicSinger;

public interface TbMusicSingerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbMusicSinger record);

    int insertSelective(TbMusicSinger record);

    TbMusicSinger selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbMusicSinger record);

    int updateByPrimaryKey(TbMusicSinger record);
}