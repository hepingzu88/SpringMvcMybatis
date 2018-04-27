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
	</head>
	<body>
		<form class="layui-form" action="" >
			<br>
	        <div class="layui-form-item">
	            <label class="layui-form-label">text</label>
	            <div class="layui-input-inline">
	                <input name="scene" id="scene" lay-verify="scene" placeholder="scene" autocomplete="off" class="layui-input" type="text">
	            </div>
	        </div>
	       <!--  <div class="layui-form-item">
	         <div class="layui-inline">
		      <label class="layui-form-label">类别</label>
		      <div class="layui-input-inline">
		        <select name="modules" lay-verify="required" lay-search="">
		          <option value="0">语句场景</option>
		          <option value="1">拆分场景</option>
		        </select>
		      </div>
		   	 </div>
		    </div> -->
	        <div class="layui-form-item">
	            <div class="layui-input-block">
	                <button class="layui-btn" lay-submit="" lay-filter="from" type="button">立即提交</button>
	                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	            </div>
	        </div>
	    </form>
	   <script type="text/javascript">
	   	layui.use(['form'], function(){
	        var form = layui.form;
			
	      	//自定义验证规则
	        form.verify({
	          scene: [/[A-Za-z]$/, '必填项，且只能为字母']
	        });
	      	
	        //监听提交
	        form.on('submit(from)', function(data){
	             $.post("${basePath}/semantic/addScene",data.field,function (res) {
	                if(res=='true'){
	                	 layer.msg('增加成功');
	                	 setTimeout(function(){
	                		 parent.closeLayer(layer.index)
                		 }, 2000);
	                }else{
	                    layer.msg("增加失败");
	                }
	            }) 
	        });
	    });
	   </script>
	</body>
</html>