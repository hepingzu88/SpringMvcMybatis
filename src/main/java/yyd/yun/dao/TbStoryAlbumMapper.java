package yyd.yun.dao;

import java.util.List;

import yyd.yun.beans.TbStoryAlbum;

public interface TbStoryAlbumMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(TbStoryAlbum record);

    int insertSelective(TbStoryAlbum record);

    TbStoryAlbum selectByPrimaryKey(Integer id);
    
    List<TbStoryAlbum> selectTbStoryAlbum();

    int updateByPrimaryKeySelective(TbStoryAlbum record);

    int updateByPrimaryKey(TbStoryAlbum record);
}