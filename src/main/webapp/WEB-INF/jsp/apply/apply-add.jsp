<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@include file="/include/common.jsp" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
	    <link rel="stylesheet" href="${basePath}/css/sccl.css">
	    <script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
	  <%--   <script src="${basePath}/js/page.js"></script> --%>
	</head>
	<body>
		<div class="layui-tab layui-tab-card" style="height: 805px;">
			<br><br>
			<form class="layui-form layui-form-pane" action="" style="margin-left: 40px;">
			  <div class="layui-form-item" style="width: 50%;">
			    <label class="layui-form-label">应用名称</label>
			    <div class="layui-input-block">
			      <input type="text" name="applyName" lay-verify="required" autocomplete="off" placeholder="请输入应用名称" class="layui-input">
			    </div>
			  </div>
			  
			  <div class="layui-form-item" style="width: 50%;">
			    <label class="layui-form-label">应用分类</label>
			    <div class="layui-input-block">
			      <input type="text" name="applyClass" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  
			   <div class="layui-form-item" style="width: 50%;">
			    <label class="layui-form-label">应用平台</label>
			    <div class="layui-input-block">
			      <select name="platform" lay-filter="aihao" lay-verify="required">
			        <option value=""></option>
			        <option value="Windows">任何平台</option>
			        <option value="Windows">Windows</option>
			        <option value="Linux">Linux</option>
			        <option value="Android">Android</option>
			        <option value="Ios">Ios</option>
			        <option value="WebApp">WebApp</option>
			      </select>
			    </div>
			   </div>
			  
			  <div class="layui-form-item layui-form-text" style="width: 50%;">
			    <label class="layui-form-label">应用描述</label>
			    <div class="layui-input-block">
			      <textarea name="depict" placeholder="请输入内容描述" lay-verify="required" class="layui-textarea"></textarea>
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
		<script type="text/javascript">
			layui.use(['form', 'layedit', 'laydate'], function(){
			  var form = layui.form
			  ,layer = layui.layer
			  ,layedit = layui.layedit
			  ,laydate = layui.laydate;
			  form.render();
			  
			  //监听提交
			  form.on('submit(from)', function(data){
			    $.post("${basePath}/apply/apply-add",data.field,function(res){
			    	if(res.isSuccess){
			    		layer.msg(res.message);
			    	}else{
			    		layer.msg(res.message);
			    	}
			    })
			    
			  });
			  
			});
		</script>
	</body>
</html>