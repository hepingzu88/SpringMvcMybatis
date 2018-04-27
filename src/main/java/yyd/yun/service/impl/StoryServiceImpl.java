package yyd.yun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yyd.yun.aspect.CommentAnno;
import yyd.yun.beans.StoryResVo;
import yyd.yun.beans.TbStoryAlbum;
import yyd.yun.beans.TbStoryCategory;
import yyd.yun.beans.TbStoryRes;
import yyd.yun.beans.TbStoryResTag;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.StoryResVoDao;
import yyd.yun.dao.TbStoryAlbumMapper;
import yyd.yun.dao.TbStoryCategoryMapper;
import yyd.yun.dao.TbStoryResMapper;
import yyd.yun.dao.TbStoryResTagMapper;
import yyd.yun.service.StoryService;

@Service
public class StoryServiceImpl implements StoryService{

	@Autowired
	private TbStoryResMapper tbStoryResMapper;
	
	@Autowired
	private StoryResVoDao storyResVoDao;
	
	@Autowired
	private TbStoryAlbumMapper tbStoryAlbumMapper;
	
	@Autowired
	private TbStoryResTagMapper tbStoryResTagMapper;
	
	@Autowired
	private TbStoryCategoryMapper tbStoryCategoryMapper;
	
	@Override
	public PageInfo<StoryResVo> pageStoryResVo(StoryResVo vo, Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<StoryResVo> list = storyResVoDao.selectStoryResVo(vo);
		 if (list != null && list.size() > 0) { 
			 return new PageInfo<StoryResVo>(list);
         } else {
            return null;
         }
	}

	@Override
	public List<TbStoryRes> listTbStoryRes(TbStoryRes tbStoryRes) {
		return tbStoryResMapper.listTbStoryRes(tbStoryRes);
	}

	@CommentAnno(operationType="修改故事资源标签:",operationName="修改故事资源标签")
	@Override
	public int upateTbStoryRes(TbStoryRes tbStoryRes) {
		return tbStoryResMapper.updateByPrimaryKeySelective(tbStoryRes);
	}

	@CommentAnno(operationType="删除故事资源标签:",operationName="删除故事资源标签")
	@Override
	public int delTbStoryRes(Integer id) {
		return tbStoryResMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<TbStoryAlbum> ListTbStoryAlbum() {
		return tbStoryAlbumMapper.selectTbStoryAlbum();
	}

	@CommentAnno(operationType="新增故事资源标签:",operationName="新增故事资源标签")
	@Override
	public int addTbStoryResTag(TbStoryResTag tbStoryResTag) {
		return tbStoryResTagMapper.insert(tbStoryResTag);
	}

	@CommentAnno(operationType="删除故事资源标签:",operationName="删除故事资源标签")
	@Override
	public int deleteByStoryResId(Integer storyResId) {
		return tbStoryResTagMapper.deleteByStoryResId(storyResId);
	}

	@Override
	public List<TbStoryResTag> selectStoryResTag(TbStoryResTag tbStoryResTag) {
		return tbStoryResTagMapper.selectStoryResTag(tbStoryResTag);
	}

	@Override
	public List<TbStoryCategory> selectTbStoryCategory() {
		return tbStoryCategoryMapper.selectTbStoryCategory();
	}

	@Override
	public int deleteStoryTagId(Integer tagId) {
		return tbStoryResTagMapper.deleteStoryTagId(tagId);
	}
	


}
