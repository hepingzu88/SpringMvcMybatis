<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/include/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
		<script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
	</head>
	<body>
		<div class="content-box">
			<br><br>
		    <form class="layui-form" action="" >
		        <div class="layui-form-item">
		            <label class="layui-form-label">角色名称</label>
		              <div class="layui-input-block" style="width: 70%;">
		                <input name="roleName" lay-verify="required" placeholder="请输入角色名称" autocomplete="off" class="layui-input" type="text">
		            </div>
		        </div>
		        <br><br>
		        <div class="layui-form-item">
		            <div class="layui-input-block">
		                <button class="layui-btn" lay-submit="" lay-filter="from" type="button">立即提交</button>
		                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		            </div>
		        </div>
		    </form>
		</div>
		<script>
		    layui.use(['form','layer'], function(){
		        var form = layui.form,
		        layer = layui.layer;
		        //监听提交
		        form.on('submit(from)', function(data){
		            $.post("${basePath}/admin/user/addRole",data.field,function (res) {
		                if(res.isSucceed){
		                    layer.msg(res.message);
		                    setTimeout(function(){
		                		 parent.closeLayer(layer.index)
	               		 	}, 2000);
		                }else{
		                    layer.msg(res.message);
		                }
		            })
		        });
		
		    });
		
		</script>
	</body>
</html>