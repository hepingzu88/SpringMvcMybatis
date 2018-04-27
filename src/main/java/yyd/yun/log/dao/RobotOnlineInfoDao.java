package yyd.yun.log.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import yyd.yun.log.domain.RobotOnlineInfo;

public interface RobotOnlineInfoDao {

	public List<RobotOnlineInfo> queryAllProvince();
	
	public List<RobotOnlineInfo> queryDate(@Param("onlineTime")String onlineTime,@Param("offlineTime")String offlineTime);
	
	public List<RobotOnlineInfo> timeAndRtype(RobotOnlineInfo info);
	
	public List<RobotOnlineInfo> queryAllRtype();
	
	public List<RobotOnlineInfo> queryByRtype(@Param("arr")String [] arr);
}
