package yyd.yun.common.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yyd.yun.common.ResultModel;
import yyd.yun.common.util.VerifyCodeUtils;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String getLogin(){
		// 通过SecurityUtils.getSubject()获取当前subject
		Subject currentUser = SecurityUtils.getSubject();
		// 如果subject存在Principal表示已授权 , Principal翻译过来为当事人
		if(currentUser.getPrincipal() == null) {
			return "login";
		} else {
			// 如果已经登录重定向到index
			return "jsp/index";
		}
		
		//解释Subject , Principal
		// subject 是shiro的核心，是面向开发者的主要操作接口
		//          授权，登录，登出，判断是否有权限都可以通过subject接口
		
		// principal subject的属性，我们这里保存的是登录用户名(具体查看OperatorRealm)
		
		// shiro 的其原理与cookie登录差不多
	}
	
	@PostMapping("/login")
	@ResponseBody
	public ResultModel postLogin(HttpServletRequest request){
		String error = (String) request.getAttribute("shiroLoginFailure");
		
		System.out.println(error  +"  gdlfgjdlgjl");
		
		if (UnknownAccountException.class.getName().equals(error)) {
			error = "用户名/密码错误";
		} else if (IncorrectCredentialsException.class.getName().equals(error)) {
			error = "用户名/密码错误";
		} else if (error != null) {
			error = "错误提示：" + error;
		}
		
		if(StringUtils.isNotBlank(error)) {
			return new ResultModel(error, false);
		}
		return new ResultModel("登录成功", true);
	}
	
	@GetMapping("/psCaptcha.jpg")
	public void captcha(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("image/jpeg");
		
		HttpSession session = request.getSession(true);
		
		String code = "";
		try {
			code = VerifyCodeUtils.outputVerifyImage(240,100, response.getOutputStream(), 4);
		} catch (IOException e) {}
		
		//注意 ：session key 为 captchaCode， 如果改动同时要改动spring-shiro.xml中的配置
		session.setAttribute("captchaCode", code);
	}
}
