package yyd.yun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.beans.Semantic;
import yyd.yun.beans.SemanticInfo;

public interface SemanticInfoDao {

	public Semantic findById(@Param("id")Integer id,@Param("operatorId")Integer operatorId);
	
	public List<SemanticInfo> findSemantic(Semantic info);
	
	public Semantic selectSemantic(@Param("id")Integer id,@Param("operatorId")Integer operatorId);
	
	public List<SemanticInfo> exportSemantic(SemanticInfo info);
}
