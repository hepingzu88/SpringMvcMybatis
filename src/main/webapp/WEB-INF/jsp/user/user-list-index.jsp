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
		    <form class="layui-form" action="" id="_form">
		        <div class="layui-form-item">
		        	<input type="hidden" id="pageNum" name="pageNum"/>
		            <button class="layui-btn add-button" type="button">
		               	 添加用户
		            </button>
		            <label class="layui-form-label">用户名</label>
		            <div class="layui-input-inline">
		                <input name="adminName" lay-verify="phone" autocomplete="off" class="layui-input" type="text">
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
					var url = "${basePath}/admin/user-add";
					layer.open({
			            title: "添加用户",
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
				var url = "${basePath}/admin/user/resetRole?id="+id;
				layer.open({
		            type: 2,
		            area: ['800px', '400px'],
		            offset: ['100px', '250px'],
		            fixed: false, //不固定
		            maxmin: true,
		            content: [url]
		        })
			}
		   
		   function del(id){
			 //删除语料以及同义句
				var url ="${basePath}/admin/user/delUser";
				layer.confirm('确认删除用户', {
		            btn: ['删除','取消'],icon: 7, title:'注意' //按钮
		        }, function(index){
		            $.post(url,{"id":id},function (data) {
		                if(data.isSuccess){
		                    layer.msg(data.message);
		                    location.reload();
		                    /* setTimeout(function(){
		                    	 window.location.href = "${basePath}/semantic/semanticIndex?page="+$("#page").text();
	               		 	}, 2000); */
		                }else{
		                	layer.msg(data.message);
		                }
		            })
		        });
		   }
		   
		   function closeLayer(index) {
		        layer.close(index);
		        location.reload();
		    }
		
		    function _returnData(pageNum) {
		        var url = "${basePath}/admin/user/list";
		        jump(url,pageNum);
		    }
		</script>
	</body>
</html>
