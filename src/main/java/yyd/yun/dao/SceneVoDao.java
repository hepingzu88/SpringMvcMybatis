package yyd.yun.dao;

import java.util.List;

import yyd.yun.beans.SceneVo;

public interface SceneVoDao {

	List<SceneVo> findSceneAndIntent(SceneVo vo);
	
	List<SceneVo> findSceneAndEntity(SceneVo vo);
}
