package yyd.yun.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Semantic;
import yyd.yun.beans.SemanticInfo;

public interface SemanticService {

	@Transactional
	public int add(Semantic info);
	
	public int batchAdd(Semantic info);
	
	public int updateSemantic(String data,Integer id);
	
	public PageInfo<Semantic> findSemantic(Integer pageNum);
	
	public PageInfo<SemanticInfo> find(Integer pageNum,Semantic info);
	
	public int deletSemantic(@Param("operatorId") Integer operatorId,Integer id);
	
	public PageInfo<Semantic> findByIdAndUserId(@Param("id")Integer id,@Param("operatorId")Integer operatorId,Integer pageNum);
	
	public Semantic findById(@Param("id")Integer id,@Param("operatorId")Integer operatorId);
	
	public List<Semantic> findIntent();
	
	public int updateSemanticAnswer(String arr,Integer id,Integer operatorId);

	//根据行为ID  场景ID
	public List<Semantic> seletSemantic(Semantic semantic);
	
	public Semantic selectSemantic(Integer id,Integer userId);
	
	//修改场景  行为  情感状态  年龄段 同时修改同义句
	public int updateSemanticInfo(Semantic semantic);
	
	//
	public int updateLabelSemantic(Semantic semantic);
	//删除语料以及同义语句
	public int deleteSemanticAndSimilarity(Integer operatorId,Integer id);
	
	//批量删除
	public int batchDelete(String arrys[]);
	
	public int updateSemanticJson(Semantic semantic);
	
	//导出
	public List<SemanticInfo> exportSemantic(SemanticInfo info);
	//查询所有同义句
	//public List<Semantic> queryAllsimilarityId(Integer id,Integer operatorId);	
	
	public int insertSynonymous(Semantic semantic);
	
	//批量删除同义句
	public int batchDeleteSynonymous(@Param("arrys")String arrys[]);
}
