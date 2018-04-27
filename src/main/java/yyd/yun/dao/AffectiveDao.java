package yyd.yun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.beans.Affective;

public interface AffectiveDao {

	public int addAffective(Affective affective);
	
	public int deleteAffective(@Param("id")Integer id,@Param("operatorId")Integer operatorId);
	
	public int updateAffective(Affective affective);

	public List<Affective> findAffectiveList(Affective affective);
	
	public Affective findAffective(@Param("id")Integer id,@Param("operatorId")Integer operatorId);
}
