<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
		<script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
		<style type="text/css">
			input{
				width: 80%;
			}
		
		</style>
	</head>
	<body>
		<form class="layui-form" action="">
			<br>
	        <div class="layui-form-item" >
	        	<input type="hidden" value= "${id}" name="id">
	        	<div class="layui-inline">
		            <label class="layui-form-label">语料</label>
		            <div class="layui-input-inline">
		                <input name="text" id="text" placeholder="问题" style="width: 280px;" autocomplete="off" class="layui-input" type="text">
		            </div>
	        	</div>
	        	<div class="layui-inline">
		            <label class="layui-form-label">回复</label>
		            <div class="layui-input-inline">
		                <input name="answer" id="answer" style="width: 280px;" placeholder="回复" autocomplete="off" class="layui-input" type="text">
		            </div>
	            </div>
	        </div>
	        
	        <div class="layui-form-item">
	        	<div class="layui-inline">
		            <div class="layui-input-block">
		                <button class="layui-btn" lay-submit="" lay-filter="from" type="button">立即提交</button>
		                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		            </div>
	            </div>
	        </div>
		</form>
		<script type="text/javascript">
			layui.use(['form'], function(){
			  var form = layui.form
			  ,layer = layui.layer;
			  //监听提交
			  form.on('submit(from)', function(data){
				  if($("#text").val() == "" && $("#answer").val() == ""){
					  layer.msg("问题与回复不能同时为空");
					  return;
				  }
				  $.post("${basePath}/semantic/insertSynonymous",data.field,function(res){
					  if(res.isSuccess){
						layer.msg(res.message);	  
					  }else{
						  layer.msg(res.message);
					  }
				  })
			  })
			})
		</script>
	</body>
</html>