<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/include/common.jsp" %>
<html>
	<head>
	    <title>Title</title>
	    <link rel="stylesheet" href="${basePath}/css/sccl.css">
	    <script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
	    <style type="text/css">
	        .add-button{
	            float: left;
	        }
	    </style>
	</head>
	<body>
		<div class="content-box">
		    <div class="layui-tab layui-tab-card" style="height: 800px;">
			  <ul class="layui-tab-title">
			    <li class="layui-this">用户信息</li>
			    <li>修改密码</li>
			  </ul>
			  <div class="layui-tab-content" style="height: 100px;">
			    <div class="layui-tab-item layui-show">
			    	<br>
				    <form class="layui-form layui-form-pane" action="">
				      <input type="hidden" name="id" value="<shiro:principal property="id"/>">
					  <div class="layui-form-item" style="width: 50%;">
					    <label class="layui-form-label">用户名</label>
					    <div class="layui-input-block">
					      <input type="text" name="username" lay-verify="required" autocomplete="off" placeholder="请输入标题" class="layui-input" value="${user.username}">
					    </div>
					  </div>
					  <br>
					  <div class="layui-form-item">
				         <div class="layui-input-block">
				             <button class="layui-btn" lay-submit="" lay-filter="from" type="button">立即提交</button>
				             <button type="reset" class="layui-btn layui-btn-primary">重置</button>
				         </div>
				      </div>
					</form>
			    </div>
			    <div class="layui-tab-item">
			    	<br>
				    <form class="layui-form layui-form-pane" action="">
					  <input type="hidden" name="id" value="<shiro:principal property="id"/>">
					  <div class="layui-form-item" style="width: 50%;">
					    <label class="layui-form-label">旧密码</label>
					    <div class="layui-input-block">
					      <input type="password" name="oldPassword" lay-verify="required" autocomplete="off" placeholder="请输入标题" class="layui-input" value="">
					    </div>
					  </div>
					  <div class="layui-form-item" style="width: 50%;">
					    <label class="layui-form-label">新密码</label>
					    <div class="layui-input-block">
					      <input type="password" name="newPassword" lay-verify="required" autocomplete="off" placeholder="请输入标题" class="layui-input" value="">
					    </div>
					  </div>
					  <div class="layui-form-item" style="width: 50%;">
					    <label class="layui-form-label">确认密码</label>
					    <div class="layui-input-block">
					      <input type="password" name="affirmPassword" lay-verify="required" autocomplete="off" placeholder="请输入标题" class="layui-input" value="">
					    </div>
					  </div>
					  <br>
					  <div class="layui-form-item">
				         <div class="layui-input-block">
				             <button class="layui-btn" lay-submit="" lay-filter="from" type="button">立即提交</button>
				             <button type="reset" class="layui-btn layui-btn-primary">重置</button>
				         </div>
				      </div>
					</form>
			    </div>
			  </div>
			</div>
		</div>
		<script type="text/javascript">
			layui.use(['element','form','jquery'], function(){
				  var $ = layui.jquery
				  ,element = layui.element,
				  form = layui.form; //Tab的切换功能，切换事件监听等，需要依赖element模块
				  //监听提交
				  form.on('submit(from)', function(data){
					 if(data.field.affirmPassword != data.field.newPassword){
						 layer.msg('后两次密码输入不一致');
						 return;
					 }
					 $.post('${basePath}/admin/user/updatePassword',data.field,function(res){
						 if(res.isSussccus){
							 layer.msg("修改成功 ,请重新登录");
							 //setTimeout(function(){
								 window.location.href = "/admin/logout";
		               		 	//}, 2000);
						 }else{
							 layer.msg(res.message);
						 }												 
					 })
				  });
			})
		</script>
	</body>
</html>
