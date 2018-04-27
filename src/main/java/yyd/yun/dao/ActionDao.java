package yyd.yun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.beans.Action;

public interface ActionDao {

	public int addAction(Action action);
	
	public List<Action> findAction();
	
	public List<Action> queryByIdAction(@Param("operatorId")Integer id);
	
	public Action findByName(@Param("actionName")String actionName,@Param("operatorId")Integer operatorId);
	
	public int updateAction(Integer id);
	
	public int delAction(@Param("id")Integer id,@Param("operatorId")Integer operatorId);
	
	public List<Action> findActionById(Action action);
}
