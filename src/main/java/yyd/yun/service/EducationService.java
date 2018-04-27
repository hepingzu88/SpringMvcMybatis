package yyd.yun.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.EducationVo;
import yyd.yun.beans.TbEduGrade;
import yyd.yun.beans.TbEduRes;
import yyd.yun.beans.TbEduResTag;
import yyd.yun.beans.TbEduSemester;
import yyd.yun.beans.TbEduVersion;

public interface EducationService {

	//查询教育 分页
	public PageInfo<EducationVo> pageEduResVo(EducationVo vo,Integer pageNum);
	
	//查询版本
	public List<TbEduVersion> listTbEduVersion();
	
	//查询年级
	public List<TbEduGrade> listTbEduGrade();
	
	//查询学期
	public List<TbEduSemester> listTbEduSemester();
	
	//查询教育资源表
	public List<TbEduRes> listTbEduRes(TbEduRes tbEduRes);
	
	//查询教育资源标签表
	public List<TbEduResTag> listTbEduResTag(TbEduResTag tbEduResTag);
	
	//修改教育资源表
	public int updateTbEduRes(TbEduRes tbEduRes);
	
	//修改教育资源标签表
	public int updateTbEduResTag(TbEduResTag tbEduResTag);
	
	//根据eduResId删除教育资源标签表
	public int deleteEduResTagId(Integer eduResId);
	
	//根据id删除教育资源标签表
	public int deleteEduResTagByPrimaryKey(Integer id);
	
	//insert教育资源标签表
	public int insertEduResTag(TbEduResTag tbEduResTag);
	
	public int deleteEduRes(Integer id);
	
	//selectTbEduVersion
	public List<TbEduVersion> selectTbEduVersion();
	
	public int deleteEduTagId(Integer tagId);
}
