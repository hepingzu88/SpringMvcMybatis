package yyd.yun.service;

import java.util.List;


import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Scene;

public interface SceneService {
	
	public int addScene(Scene scene);
	
	public List<Scene> findScene();
	
	public PageInfo<Scene> queryById(Integer id,Integer pageNum);
	
	public List<Scene> findById(Integer id);
	
	public List<Scene> queryList(String []sceneArr);
	
	public Scene findSceneByName(String scene);
	
	public List<Scene> findSceneById(Scene scene);
	
	public int deleteScene(Integer id,Integer operatorId,String scene);
}
