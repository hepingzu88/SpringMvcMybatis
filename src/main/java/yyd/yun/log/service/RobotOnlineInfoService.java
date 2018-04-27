package yyd.yun.log.service;

import java.util.List;

import yyd.yun.log.domain.RobotOnlineInfo;

public interface RobotOnlineInfoService {

	public List<RobotOnlineInfo> queryAllProvince();
	
	public List<RobotOnlineInfo> queryDate(String onlineTime,String offlineTime);
	
	public List<RobotOnlineInfo> queryAllRtype();
	
	public List<RobotOnlineInfo> timeAndRtype(RobotOnlineInfo info);
	
	public List<RobotOnlineInfo> queryByRtype(String [] arr);
}
