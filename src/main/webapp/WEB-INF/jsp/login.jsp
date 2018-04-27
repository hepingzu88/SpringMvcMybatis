<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/include/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<meta name="keywords" content="robot后台">
	<meta name="description" content="勇艺达机器人后台管理系统">
	<%-- <script src="${basePath}/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
	<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/> --%>
	<link rel="stylesheet" href="${basePath}/layuis/css/modules/layer/default/layer.css"/>
	<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
	<link rel="stylesheet" href="${basePath}/css/sccl.css"/>
    <title>登录</title>
</head>
<body class="login-bg">
    <div class="login-box">
        <header>
            <h1>框架后台管理系统</h1>
        </header>
        <div class="login-main">
			<form action="${basePath}/admin/login" class="layui-form" method="post" id="majorform">
				<input type="hidden" id="verifyCode" name="verifyCode"/>
				<div class="layui-form-item">
					<label class="login-icon">
						<i class="layui-icon">&#xe612;</i>
					</label>
					<input type="text" name="username" id="username" lay-verify="username" autocomplete="off" placeholder="这里输入登录名" class="layui-input">
				</div>
				<div class="layui-form-item">
					<label class="login-icon">
						<i class="layui-icon">&#xe642;</i>
					</label>
					<input type="password" name="password" id="password" lay-verify="password" autocomplete="off" placeholder="这里输入密码" class="layui-input">
				</div>
				<div class="layui-form-item">
					<div class="pull-left login-remember">
						<label>记住帐号？</label>

						<input type="checkbox" name="rememberMe" checked="checked" value="true" lay-skin="switch" title="记住帐号"><div class="layui-unselect layui-form-switch"><i></i></div>
					</div>
					<div class="pull-right">
						<button class="layui-btn layui-btn-primary" lay-submit="" id="login" lay-filter="login" type="button">
							<i class="layui-icon">&#xe60c;</i> 登录
						</button>
					</div>
					<div class="clear"></div>
				</div>
			</form>
		</div>
        <footer>
            <p></p>
        </footer>
		<script type="text/html" id="code-temp">
			<div class="login-code-box">
				<input type="text" class="layui-input" id="code" />
				<img id="valiCode" src="${basePath}/web/captcha" alt="验证码" />
			</div>
		</script>
    </div>
	<%-- <script type="text/javascript" src="${basePath}/lib/jquery-1.9.0.min.js"></script> --%>
	<script src="${basePath}/js/jquery-1.9.1.min.js"></script>
	<script src="${basePath}/layuis/lay/modules/layer.js"></script>
    <script src="${basePath}/layuis/layui.js"></script>

    <script>
		var errorMsg ="${errorMsg}";
		var errorCount ="${requestScope.errorCount}";
		if(errorMsg!=''){
			layer.msg(errorMsg,{icon:2});
		}

        layui.use(['layer', 'form'], function () {
            var layer = layui.layer,
				$ = layui.jquery,
				form = layui.form;

             /* form.verify({
                username: function (value) {
                    if (value === '')
                        return '请输入用户名';
                },
                password: function (value) {
                    if (value === '')
                        return '请输入密码';
                }
            });  */
            
          
            $("body").keydown(function() {
                if (event.keyCode == "13") {//keyCode=13是回车键
                    $('#login').click();
                    if($("#username").val() == ''){
                    	layer.msg("请输入用户名");
                    	return 
                    }
                    if($("#password").val() == ''){
                    	layer.msg("请输入密码");
                    	return 
                    }
                	submit();
                }
            });
            
            form.on('submit(login)', function (data) {
            	if($("#username").val() == ''){
                	layer.msg("请输入用户名");
                	return 
                }
                if($("#password").val() == ''){
                	layer.msg("请输入密码");
                	return 
                }
            	submit();
            });
            
            
            function submit(){
            	if (errorCount > 3) {
					layer.open({
						title: '<img src="' +location.origin+ '/layuis/images/face/7.gif" alt="[害羞]">输入验证码',
						type: 1,
						content: document.getElementById('code-temp').innerHTML,
						btn: ['确定'],
						yes: function (index, layero) {
							var $code = $('#code');
							if ($code.val() === '') {
								layer.msg('输入验证码啦，让我知道你是人类。');
								isCheck = false;
							} else {
								$("#verifyCode").val($code.val());
								$("#majorform").submit();
								layer.close(index);
							}
						},
						area: ['250px', '150px']
					});
					$('#valiCode').off('click').on('click', function () {
						this.src = '${basePath}/web/captcha?v=' + new Date().getTime();
					});
				}else{
					$("#majorform").submit();
				}
            }
            
            
        });

    </script>
  </body>
</html>