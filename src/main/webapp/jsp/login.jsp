<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>shiro学习后台</title>

<style>
body {
	padding: 100px;
}
div {
	padding: 20px;
}
</style>

</head>
<body>
	<div>	
		用户名： <input id="username" />
	</div>
	
	<div>	
		密    码： <input id="password" type="password" />
	</div>
	
	<div>	
		验证码： <input id="captchaCode" /> 
		<img src="/psCaptcha.jpg" alt="验证码" />
	</div>
	
	<button>ajax提交</button>
	
	<script type="text/javascript" src="/common/js/jquery.js"></script>
	<script type="text/javascript">
		//绑定点击事件	
		$('button').on('click', function(){
			var data = {
				username    : $('#username').val(),
				password    : $('#password').val(),
				captchaCode : $('#captchaCode').val()
			};
			
			$.post('/login', data, function(res){
				console.log(res);
				if(res.isSuccess) {
					window.location.reload();
				} else {
					alert(res.message);
				}
			})	
		});
	</script>
</body>
</html>