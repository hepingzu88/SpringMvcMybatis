package yyd.yun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.beans.Action;
import yyd.yun.beans.Intent;

public interface IntentDao {

	public int addIntent(Intent intent);
	
	public List<Intent> findIntent();
	
	public List<Intent> queryByIdIntent(@Param("operatorId")Integer id);
	
	public Intent findByName(@Param("intent")String intent,@Param("operatorId")Integer operatorId);
	
	public int updateIntent(Integer id);
	
	public int delIntent(@Param("id")Integer id,@Param("operatorId")Integer operatorId);
	
	public List<Intent> findIntentById(Intent intent);
}
