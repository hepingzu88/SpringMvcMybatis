package yyd.yun.service;



import java.util.List;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Apply;

public interface ApplyService {

	public int addApply(Apply apply);
	
	public int updateApply(Apply apply);
	
	public PageInfo<Apply> queryAllApply(Integer operatorId,Integer pageNum);
	
	public Apply findById(Integer operatorId,Integer id);
	
	public int applyDelScene(Apply apply,boolean bool);
	
	public Apply findByName(Integer operatorId,String applyName);
	
	public int deleteApply(Integer operatorId,Integer id,String applyName);
	
	public List<Apply> selectApply(Apply apply);
}
