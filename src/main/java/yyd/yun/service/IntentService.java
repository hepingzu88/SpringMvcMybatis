package yyd.yun.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Intent;
import yyd.yun.beans.SceneVo;

public interface IntentService {

	public int addIntent(Intent intent);
	
	public PageInfo<SceneVo> findIntent(Integer id,Integer pageNum);
	
	public List<Intent> queryByIdIntent(Integer id);
	
	public int updateIntent(Integer id);
	
	public int delIntent(Integer id,Integer operatorId,String intent);
	
	public Intent findByName(String intent);
	
	public List<Intent> findIntentById(Intent intent);
	
	public List<SceneVo> findSceneAndIntent(SceneVo vo);
}
