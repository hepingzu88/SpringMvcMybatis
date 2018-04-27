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
		<!-- <div class="layui-tab layui-tab-card" style="height: 805px;"> -->
		<br><br>
			<form class="layui-form layui-form-pane"><!--  action="" style="margin-left: 40px;" -->
	         	<div class="layui-form-item">
				    <label class="layui-form-label">适用年龄段</label>
				    <div class="layui-input-block">
				      <input name="ageBand" id="ageBand" lay-verify="required" placeholder="适用年龄段" autocomplete="off" class="layui-input" type="text">
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
	   <!--  </div> -->
	   <script type="text/javascript">
	   	layui.use(['form'], function(){
	        var form = layui.form;
	      	//自定义验证规则
	        //form.verify({
	        	//intent: [/[A-Za-z]$/, '必填项，且只能为字母']
	        //});
	        //监听提交
	        form.on('submit(from)', function(data){
	             $.post("${basePath}/thesaurus/ageBand/ageBand-add",data.field,function (res) {
	            	 if(res.isSuccess){
	                	 layer.msg(res.message);
	                	 setTimeout(function(){
	                		 parent.layer.close(parent.layer.getFrameIndex(window.name)); //再执行关闭   
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