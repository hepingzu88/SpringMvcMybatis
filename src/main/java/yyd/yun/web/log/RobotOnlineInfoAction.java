package yyd.yun.web.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yyd.yun.log.domain.RobotOnlineInfo;
import yyd.yun.log.service.RobotOnlineInfoService;

@Controller
@RequestMapping("/robot")
public class RobotOnlineInfoAction {

	@Autowired
	private RobotOnlineInfoService robotOnlineInfoService;
	
	//查询型号
	@RequestMapping(value="/map-data")
	public String show(ModelMap map){
		List<RobotOnlineInfo> list = robotOnlineInfoService.queryAllRtype();
		map.put("list", list);
		return "chart/chart-display";
	}
	
	@RequestMapping(value="/map",method = RequestMethod.GET)
	public String showMap(){
		return "chart/map";
	}
	
	//根据时间
	@RequestMapping(value="/queryDate",method=RequestMethod.POST)
	@ResponseBody
	public List<RobotOnlineInfo> queryDate(String arr,String onlineTime,String offlineTime,ModelMap map){
		List<RobotOnlineInfo> list = null;
		if(arr != null && !"".equals(arr) && offlineTime != "" && onlineTime != ""){
			String[] str = arr.split(",");
			RobotOnlineInfo info = new RobotOnlineInfo();
			info.setArr(str);
			info.setOnlineTime(onlineTime);
			info.setOfflineTime(offlineTime);
			list = robotOnlineInfoService.timeAndRtype(info);
		}else if(!"".equals(onlineTime) && !"".equals(offlineTime) && "".equals(arr)){
			list = robotOnlineInfoService.queryDate(onlineTime,offlineTime);
		}else if(arr != null && !"".equals(arr) && "".equals(onlineTime) && "".equals(offlineTime)){
			String[] str = arr.split(",");
			list = robotOnlineInfoService.queryByRtype(str);
		}else if("".equals(arr) && "".equals(onlineTime) && "".equals(offlineTime)){
			list = robotOnlineInfoService.queryAllProvince();
		}
		return list;
	}
	
	//查询所有省份
	@RequestMapping(value="/queryAllProvince",method=RequestMethod.POST)
	@ResponseBody
	public List<RobotOnlineInfo> queryAllProvince(){
		List<RobotOnlineInfo> list = robotOnlineInfoService.queryAllProvince();
		return list;
	}
	
	@RequestMapping(value="/queryAllCity",method=RequestMethod.POST)
	@ResponseBody
	public List<RobotOnlineInfo> queryAllCity(RobotOnlineInfo info){
		
		return null;
	}
	
}
