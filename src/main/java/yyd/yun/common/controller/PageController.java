package yyd.yun.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  这里为了方便，将所有页面全部映射成mapper
 */
@Controller
public class PageController {
	
	@RequestMapping("/main")
	public String main(){
		return "main";
	}
	
	@RequestMapping("/setting/{page}")
	public String setting(@PathVariable String page){
		return "setting/" + page;
	}
	
	@RequestMapping("/cms/{page}")
	public String cms(@PathVariable String page){
		return "cms/" + page;
	}
}
