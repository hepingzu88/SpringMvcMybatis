package yyd.yun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import yyd.yun.aspect.CommentAnno;
import yyd.yun.beans.EducationVo;
import yyd.yun.beans.TbEduGrade;
import yyd.yun.beans.TbEduRes;
import yyd.yun.beans.TbEduResTag;
import yyd.yun.beans.TbEduSemester;
import yyd.yun.beans.TbEduVersion;
import yyd.yun.constants.AdminConstant;
import yyd.yun.dao.EducationVoDao;
import yyd.yun.dao.TbEduGradeMapper;
import yyd.yun.dao.TbEduResMapper;
import yyd.yun.dao.TbEduResTagMapper;
import yyd.yun.dao.TbEduSemesterMapper;
import yyd.yun.dao.TbEduVersionMapper;
import yyd.yun.service.EducationService;

@Service
public class EducationServiceImpl implements EducationService{

	@Autowired
	private EducationVoDao educationVoDao;
	
	@Autowired
	private TbEduGradeMapper tbEduGradeMapper;
	
	@Autowired
	private TbEduResTagMapper tbEduResTagMapper;
	
	@Autowired
	private TbEduSemesterMapper tbEduSemesterMapper;
	
	@Autowired
	private TbEduVersionMapper tbEduVersionMapper;
	
	@Autowired
	private TbEduResMapper tbEduResMapper;
	
	
	@Override
	public PageInfo<EducationVo> pageEduResVo(EducationVo vo, Integer pageNum) {
		PageHelper.startPage(pageNum, AdminConstant.ADMIN_PAGE_SIZE);
		List<EducationVo> list = educationVoDao.selectEducationVo(vo);
		 if (list != null && list.size() > 0) { 
			 return new PageInfo<EducationVo>(list);
         } else {
            return null;
         }
	}

	@Override
	public List<TbEduVersion> listTbEduVersion() {
		return tbEduVersionMapper.selectTbEduVersion();
	}

	@Override
	public List<TbEduGrade> listTbEduGrade() {
		return tbEduGradeMapper.selectGrade();
	}

	@Override
	public List<TbEduSemester> listTbEduSemester() {
		return tbEduSemesterMapper.selectTbEduSemester();
	}

	@Override
	public List<TbEduRes> listTbEduRes(TbEduRes tbEduRes) {
		return tbEduResMapper.selectTbEduRes(tbEduRes);
	}

	@Override
	public List<TbEduResTag> listTbEduResTag(TbEduResTag tbEduResTag) {
		return tbEduResTagMapper.selectTbEduResTag(tbEduResTag);
	}

	@CommentAnno(operationType="修改教育资源:",operationName="修改教育资源")
	@Override
	public int updateTbEduRes(TbEduRes tbEduRes) {
		return tbEduResMapper.updateByPrimaryKeySelective(tbEduRes);
	}

	@CommentAnno(operationType="修改教育资源标签:",operationName="修改教育资源标签")
	@Override
	public int updateTbEduResTag(TbEduResTag tbEduResTag) {
		return tbEduResTagMapper.updateByPrimaryKeySelective(tbEduResTag);
	}

	@CommentAnno(operationType="删除教育资源:",operationName="删除教育资源")
	@Override
	public int deleteEduResTagId(Integer eduResId) {
		return tbEduResTagMapper.deleteEduResId(eduResId);
	}

	@CommentAnno(operationType="删除教育资源:",operationName="删除教育资源标签")
	@Override
	public int deleteEduResTagByPrimaryKey(Integer id) {
		return tbEduResTagMapper.deleteByPrimaryKey(id);
	}

	@CommentAnno(operationType="新增教育资源:",operationName="新增教育资源标签")
	@Override
	public int insertEduResTag(TbEduResTag tbEduResTag) {
		return tbEduResTagMapper.insert(tbEduResTag);
	}

	@CommentAnno(operationType="删除教育资源:",operationName="删除教育资源")
	@Override
	public int deleteEduRes(Integer id) {
		return tbEduResMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<TbEduVersion> selectTbEduVersion() {
		return tbEduVersionMapper.selectTbEduVersion();
	}

	@Override
	public int deleteEduTagId(Integer tagId) {
		return tbEduResTagMapper.deleteEduTagId(tagId);
	}
	
	
	

}
