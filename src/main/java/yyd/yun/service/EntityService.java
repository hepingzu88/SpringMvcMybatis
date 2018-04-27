package yyd.yun.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Entity;
import yyd.yun.beans.SceneVo;

public interface EntityService {

	public int addEntity(Entity entity);
	
	public List<Entity> queryById(Integer operatorId);
	
	public PageInfo<SceneVo> queryByIdEntity(Integer id,Integer pageNum);
	
	public int updateEntity(Integer id);
	
	public int delEntity(Integer id);
	
	public Entity findEntityByName(String entity);

	public List<SceneVo> findSceneAndEntity(SceneVo vo);
	
	public List<Entity> findEntityById(Entity entity);
}
