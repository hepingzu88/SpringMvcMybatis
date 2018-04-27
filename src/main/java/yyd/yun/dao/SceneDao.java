package yyd.yun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.beans.Scene;

public interface SceneDao {

	public int addScene(Scene scene);
	
	public List<Scene> findScene();
	
	public List<Scene> queryById(@Param("id")Integer id);
	
	public List<Scene> findById(@Param("id")Integer id);
	
	public List<Scene> queryList(@Param("sceneArr")String []sceneArr);
	
	public Scene findSceneByName(@Param("operatorId")Integer operatorId,@Param("scene")String scene);
	
	public List<Scene> findSceneById(Scene scene);
	
	public int deleteScene(@Param("id")Integer id,@Param("operatorId")Integer operatorId);
}
