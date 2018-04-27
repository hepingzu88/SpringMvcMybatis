package yyd.yun.service;

import java.util.List;


import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Action;

public interface ActionService {

	public int addAction(Action action);
	
	public PageInfo<Action> findAction(Integer id,Integer pageNum);
	
	public List<Action> queryByIdAction(Integer id);
	
	public int updateAction(Integer id);
	
	public int delAction(Integer id,Integer operatorId,String action);
	
	public Action findByName(String actionName);
	
	public List<Action> findActionById(Action action);
}
