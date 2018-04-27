package yyd.yun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.beans.Semantic;

public interface SemanticDao {

	public int add(Semantic info);
	
	public int updateSemantic(Semantic info);
	
	public List<Semantic> findSemantic(Semantic info);
	
	public int updateLabelSemantic(Semantic info);
	//删除同义句
	public int deletSemantic(@Param("operatorId") Integer operatorId,@Param("id")Integer id);
	
	public Semantic findById(@Param("id")Integer id,@Param("operatorId") Integer operatorId);
	
	public List<Semantic> findByIdAndUserId(@Param("id")Integer id,@Param("operatorId")Integer operatorId);
	
	public List<Semantic> findIntent();
	
	public Semantic querysimilarityId(@Param("id") Integer id,@Param("operatorId") Integer operatorId);
	
	//根据行为ID  场景ID
	public List<Semantic> seletSemantic(Semantic semantic);
	
	//修改场景  行为  情感状态  年龄段 同时修改同义句
	public int updateSemanticInfo(Semantic semantic);
	
	//删除语料以及同义语句
	public int deleteSemanticAndSimilarity(@Param("operatorId") Integer operatorId,@Param("id")Integer id);
	
	//批量删除
	public int batchDelete(@Param("arrys")String arrys[],@Param("operatorId")Integer operatorId);
	
	//查询所有同义句
	public List<Semantic> queryAllsimilarityId(@Param("id")Integer id,@Param("operatorId") Integer operatorId);	
	
	//新增是生成json
	int updateSemanticJson(Semantic semantic);
	
	//批量删除同义句
	public int batchDeleteSynonymous(@Param("arrys")String arrys[],@Param("operatorId")Integer operatorId);
	
}
