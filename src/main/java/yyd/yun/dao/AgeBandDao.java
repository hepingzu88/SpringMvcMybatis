package yyd.yun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.beans.AgeBand;

public interface AgeBandDao {

	public int addAgeBand(AgeBand ageBand);
	
	public List<AgeBand> findAgeBand();
	
	public List<AgeBand> queryByIdAgeBand(@Param("operatorId")Integer id);
	
	public AgeBand findByName(@Param("ageBand")String ageBand,@Param("operatorId")Integer operatorId);
	
	public int updateAction(Integer id);
	
	public int delAgeBand(@Param("id")Integer id,@Param("operatorId")Integer operatorId);
	
	public List<AgeBand> findAgeBandById(AgeBand ageBand);
}
