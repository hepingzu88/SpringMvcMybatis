package yyd.yun.log.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yyd.yun.log.dao.RobotOnlineInfoDao;
import yyd.yun.log.domain.RobotOnlineInfo;
import yyd.yun.log.service.RobotOnlineInfoService;


@Service
public class RobotOnlineInfoServiceImpl implements RobotOnlineInfoService{

	@Autowired
	private RobotOnlineInfoDao dao;
	
	@Override
	public List<RobotOnlineInfo> queryAllProvince() {
		return dao.queryAllProvince();
	}

	@Override
	public List<RobotOnlineInfo> queryAllRtype() {
		return dao.queryAllRtype();
	}

	@Override
	public List<RobotOnlineInfo> queryDate(String onlineTime,String offlineTime) {
		return dao.queryDate(onlineTime,offlineTime);
	}

	@Override
	public List<RobotOnlineInfo> timeAndRtype(RobotOnlineInfo info) {
		return dao.timeAndRtype(info);
	}

	@Override
	public List<RobotOnlineInfo> queryByRtype(String[] arr) {
		return dao.queryByRtype(arr);
	}

}
