<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/include/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
		<style type="text/css">
			.layui-form-item{
				width: 98%;
			}
		
		</style>
	</head>
	<body>
			<br><br>
			<form class="layui-form layui-form-pane" style="margin-left: 10px;">
			  <div class="layui-form-item">
			    <label class="layui-form-label">资源标签名</label>
			    <div class="layui-input-block">
			      <input type="text" name="tagName" id="tagName" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			
			  <div class="layui-form-item">
			    <label class="layui-form-label">资源类别</label>
			    <div class="layui-input-block">
			      <select name="categoryId" lay-filter="category" lay-search>
			        <c:forEach items="${listCategory}" var="item">
			        	<option value="${item.id}">${item.category}</option>
			        </c:forEach>
			      </select>
			    </div>
			  </div>
			  <br><br>
			  <div class="layui-form-item">
	            <div class="layui-input-block" style="margin-left: 30%;">
	                <button class="layui-btn" lay-submit="" lay-filter="from" type="button">立即提交</button>
	                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	            </div>
	         </div>
			  
			</form>
	</body>
	<script type="text/javascript">
	 	layui.use(['table','jquery','element','form','layer'], function(){
		  var table = layui.table,
		  $ = layui.$,
		  element = layui.element,
		  form = layui.form,
		  layer = layui.layer;
		  form.render();
	
		   form.on('submit(from)',function(data){
			   console.log(data.field);
	    	   $.post("${basePath}/music/addTag",data.field,function (res) {
	    		   if(res.isSuccess){
	    			   layer.msg(res.message);
	    		   }else{
	    			   layer.msg(res.message); 
	    		   }
	            }) 
	       })
	 	})
	</script>
</html>


