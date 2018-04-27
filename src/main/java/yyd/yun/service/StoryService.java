package yyd.yun.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.StoryResVo;
import yyd.yun.beans.TbStoryAlbum;
import yyd.yun.beans.TbStoryCategory;
import yyd.yun.beans.TbStoryRes;
import yyd.yun.beans.TbStoryResTag;

public interface StoryService {
	
	//查询所有音乐与歌手
	public PageInfo<StoryResVo> pageStoryResVo(StoryResVo vo,Integer pageNum);
	
	//查询故事资源
	public List<TbStoryRes> listTbStoryRes(TbStoryRes tbStoryRes);
	
	//修改故事资源
	public int upateTbStoryRes(TbStoryRes tbStoryRes);
	
	//删除故事资源
	public int delTbStoryRes(Integer id);
	
	//查询所有故事专辑
	public List<TbStoryAlbum> ListTbStoryAlbum();
	
	//查询故事资源标签表
	List<TbStoryResTag> selectStoryResTag(TbStoryResTag tbStoryResTag);
	
	//新增故事资源标签表
	public int addTbStoryResTag(TbStoryResTag tbStoryResTag);
	
	//删除故事资源标签表
	public int deleteByStoryResId(Integer storyResId);
	
	//查询所有故事类别
	public List<TbStoryCategory> selectTbStoryCategory();
	
	 int deleteStoryTagId(Integer tagId);
}
