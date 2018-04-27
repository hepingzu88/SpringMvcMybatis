package yyd.yun.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.AgeBand;

public interface AgeBandService {

	public int addAgeBand(AgeBand ageBand);
	
	public PageInfo<AgeBand> findAgeBand(Integer id,Integer pageNum);
	
	public List<AgeBand> queryByIdAgeBand(Integer id);
	
	public int updateAgeBand(Integer id);
	
	public int delAgeBand(Integer id,Integer operatorId,String ageBand);
	
	public AgeBand findByName(String ageBand);
	
	public List<AgeBand> findAgeBandById(AgeBand ageBand);
}
