package yyd.yun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.beans.Entity;

public interface EntityDao {

	public int addEntity(Entity entity);
	
	public List<Entity> queryById(@Param("operatorId")Integer operatorId);
	
	public List<Entity> queryByIdEntity(Integer id);
	
	public int updateEntity(Integer id);
	
	public int delEntity(Integer id);
	
	public Entity findEntityByName(@Param("operatorId")Integer operatorId,@Param("entity")String entity);
	
	public List<Entity> findEntityById(Entity entity);
}
