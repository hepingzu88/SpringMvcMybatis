package yyd.yun.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/verfiy")
public class verfiy {
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
    public String verfiy(HttpServletRequest request){
    	HttpSession session = request.getSession(true);
    	String str = (String) session.getAttribute("verfiy");
    	String code = request.getParameter("code");
    	if(str.equals(code)){
    		return "true";
    	}
    	return "false";
    }
}
