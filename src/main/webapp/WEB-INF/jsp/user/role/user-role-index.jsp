<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/common.jsp" %>
<html>
	<head>
	    <title>Title</title>
	    <link rel="stylesheet" href="${basePath}/css/sccl.css">
	     <script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
	    <script src="${basePath}/js/page.js"></script>
	    <style type="text/css">
	        .add-button{
	            float: left;
	        }
	    </style>
	</head>
	<body>
		<div class="content-box">
		   <br>
		    <form class="layui-form" action="">
		        <div class="layui-form-item">
		            <button class="layui-btn add-button" type="button">
		                	新增角色
		            </button>
		            <label class="layui-form-label">用户名</label>
		            <div class="layui-input-inline">
		                <input name="adminName" lay-verify="phone" autocomplete="off" class="layui-input" type="text">
		                <input type="hidden" id="pageNum" name="pageNum"/>
		            </div>
		            <button class="layui-btn layui-btn-primary">搜  索</button>
		        </div>
		        <div id="form_content">
		        
		        </div>
		    </form>
		</div>
		<script type="text/javascript">
		   layui.use(['layer','form','jquery'], function(){
				var $ = layui.jquery
				,layer = layui.layer,
				form = layui.form; //Tab的切换功能，切换事件监听等，需要依赖element模块
				//监听提交
		
				$(".add-button").on('click',function(){
					var url = "${basePath}/admin/user/addRole";
					layer.open({
			            title: "新增角色",
						type: 2,
			            area: ['600px', '350px'],
			            offset: ['120px', '320px'],
			            fixed: false, //不固定
			            maxmin: true,
			            content: [url,"no"]
			        })
				})
		    })
		   
		   function edit(id){
			   if(id == null){
				   layer.msg("请选给用户设置角色");
				   return;
			   }
			   var url = "${basePath}/admin/user/authorization?id="+id
				layer.open({
					title:"授权",						
		            type: 2,
		            area: ['1000px', '400px'],
		            offset: ['100px', '350px'],
		            fixed: false, //不固定
		            maxmin: true,
		            content: [url]
		        })
			}
		
		    function _returnData(pageNum) {
		        var url = "${basePath}/admin/user/role";
		        jump(url,pageNum);
		    }
		</script>
	</body>
</html>