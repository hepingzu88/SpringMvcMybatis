package yyd.yun.web.log;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import yyd.yun.aspect.service.RecordHandleLogService;
import yyd.yun.beans.RecordHandleLog;
import yyd.yun.common.ResultModel;
import yyd.yun.log.domain.Log;
import yyd.yun.log.domain.RobotOnlineInfo;
import yyd.yun.log.service.LogService;
import yyd.yun.log.service.RobotOnlineInfoService;
@Controller
@RequestMapping("/log")
public class LogAction {

	@Autowired
	private RobotOnlineInfoService robotOnlineInfoService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private RecordHandleLogService recordHandleLogService;
	
	
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public String RecordHandleLogIndex(){
		return "log/record_handle_log";
	}
	
	@RequestMapping(value="/selectList")
	@ResponseBody
	public Map<String,Object> findPublic(Integer page,Integer limit,ModelMap modelMap,RecordHandleLog log){
		if(page==null||page<1){
			page=1;
        }
		Map<String,Object> map = new HashMap<>();
		PageInfo<RecordHandleLog> pages = recordHandleLogService.selectRecordHandleLog(page,log);
		if(pages == null){
			map = null;
			return map;
		}
		map.put("code", 0);
		map.put("msg","");
		map.put("count",pages.getTotal());
		map.put("data", pages.getList());
		return map;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public ResultModel delete(String [] arrys){
		if(arrys.length == 0){
			return new ResultModel("请选择要删除的复选框",false);
		}
		if(recordHandleLogService.delete(arrys) > 0){
			return new ResultModel("删除成功",true);
		}
		return new ResultModel("删除失败",false);
	}
	
	
	@RequestMapping(value="/query",method=RequestMethod.POST)
	public String test(Integer pageNum,ModelMap modelMap){
		if(pageNum==null||pageNum<1){
            pageNum=1;
        }
        PageInfo<Log> page = (PageInfo<Log>) logService.queryAllLog(pageNum);
        modelMap.put("page",page);
		return "log/log-list";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String showLog(HttpSession session){
		return "log/log";
	}
	
	@RequestMapping(value="/visit-times",method=RequestMethod.GET)
	public String showVisitTimes(HttpSession session){
		return "log/visit-log";
	}
	
	@RequestMapping(value="/visit-times",method=RequestMethod.POST)
	@ResponseBody
	public int getDatas(HttpSession session){
		
		long endTime=System.currentTimeMillis();
		long startTime = endTime - 5*1000;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeStr = format.format(new Date(startTime));
		String endTimeStr = format.format(new Date(endTime));
		
		int count = logService.queryVisitCount(startTimeStr, endTimeStr);
//				System.out.println(startTimeStr+"--"+ endTimeStr+"--->"+count);
//		int count = new Random().nextInt(100);
		return count;
	}
	
	
	
	
	
	
	
	
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
