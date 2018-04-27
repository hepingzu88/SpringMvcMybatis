package yyd.yun.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import yyd.yun.beans.Affective;

public interface AffectiveService {
	
	public int addAffective(Affective affective);
	
	public int deleteAffective(Integer id,Integer operatorId,String name);
	
	public int updateAffective(Affective affective);

	public List<Affective> findAffectiveList(Affective affective);
	
	public Affective findAffective(Integer id,Integer operatorId);
	
	public PageInfo<Affective> findPageInfoAffectiv(Affective affective,Integer pageNum);
}
