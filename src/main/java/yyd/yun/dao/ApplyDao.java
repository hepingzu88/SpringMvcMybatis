package yyd.yun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.beans.Apply;

public interface ApplyDao {
 
	public int addApply(Apply apply);
	
	public int updateApply(Apply apply);
	
	public int updateApplySceneId(Apply apply);
	
	public int updateApplyPublicSceneId(Apply apply);
	
	public List<Apply> queryAllApply(@Param("operatorId")Integer operatorId);
	
	public Apply findById(@Param("operatorId")Integer operatorId,@Param("id")Integer id);
	
	public Apply findByName(@Param("operatorId")Integer operatorId,@Param("applyName")String applyName);
	
	public int applyDelScene(Apply apply);
	
	public int deleteApply(@Param("operatorId")Integer operatorId,@Param("id")Integer id);
	
	public List<Apply>  selectApply(Apply apply);
}
