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
	   <%--  <script src="${basePath}/js/page.js"></script> --%>
	    <style type="text/css">
	        .add-button{
	            float: left;
	        }
	 		.layui-form-checkbox[lay-skin=primary] i{
	 			top:1px;
	 		}
	 		.layui-table-cell{
	 			height: auto;
	 		}
	   		.layui-table-body{
   			    position: relative;
			    overflow: hidden;
			    margin-right: -1px;
	   		}
	    </style>
	</head>
	<body>
	
		<div class="content-box">
		   <br>
		    <!-- <form class="layui-form" action="">
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
		    </form> -->
		       <form class="layui-form" id="_form">
		      <div class="demoTable layui-form">
			     	<div class="layui-form-item">
		                <input type="hidden" id="pageNum" name="pageNum" value="${page}"/>
					  <button class="layui-btn" data-type="refresh" type="button"> <i class="layui-icon">&#x1002;</i></button>
	             	</div>
			  </div>
		  </form>
	      <table class="layui-hide" id="test" lay-filter="demo"></table>
	      <script type="text/html" id="barDemo">
  			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		  </script>
		    
		</div>
		<script type="text/javascript">
			layui.use(['table','jquery','element','form','layer'], function(){
				  var table = layui.table,
				  $ = layui.$,
				  element = layui.element,
				  form = layui.form,
				  layer = layui.layer;
				  form.render();
				 
				  var tableIns = table.render({
					    elem: '#test'
					    ,url:'${basePath}/music/selectTagCategoryList'
					    ,cols: [[
					    	{type: 'checkbox',checkbox:true}
						      ,{field:'tagId', title:'ID', width:200, sort: true}
						      ,{field:'tagName', title:'标签名',edit:'text',width:500}
						      ,{field:'category', title:'标签分类',width:500}
						      ,{field:'lock', title:'编辑', width:400, templet: '#barDemo'}
					    ]]
					    ,id: 'testReload'
					    ,page: true
					    ,limit:10
					    ,limits:[10]
				   });
				  
				  table.on('tool(demo)', function(obj){
					    var data = obj.data;
					    if(obj.event === 'del'){
					    	del(data.tagId,data.categoryId);
					    }
					  });
					  var $ = layui.$, active = {
					    refresh:function(){
					    	location.reload();
					    },reload: function(){//搜索
					        table.render({
							    elem: '#test'
							    ,url:'${basePath}/music/selectTagCategoryList'
							    ,cols: [[
							    	{type: 'checkbox',checkbox:true}
							      ,{field:'tagId', title:'ID', width:200, sort: true}
							      ,{field:'tagName', title:'标签名',edit:'text',width:500}
							      ,{field:'category', title:'标签分类',width:500}
							      ,{field:'lock', title:'编辑', width:400, templet: '#barDemo'}
							    ]]
							    ,id: 'testReload'
							    ,page: true
							    ,limit:10
							    ,limits:[10]
							});
					      }
					  };
					  
				  $('.demoTable .layui-btn').on('click', function(){
				    var type = $(this).data('type');
				    active[type] ? active[type].call(this) : '';
				  });
					  
				  //监听单元格编辑
				  table.on('edit(demo)', function(obj){
				    var value = obj.value //得到修改后的值
				    ,data = obj.data //得到所在行所有键值
				    ,field = obj.field; //得到字段
				    $.post("${basePath}/music/tagUpdate",{"id":data.id,"name":value},function (data) {
		                if(data.isSuccess){
		                    layer.msg(data.message);
		                    setTimeout(function(){
		                    	$(".layui-laypage-skip").find(".layui-laypage-btn").click();//刷新当前页
	               		 	}, 2000);
		                }else{
		                	layer.msg(data.message);
		                }
		            })
				  });
			  })
		   
		   /* function edit(tagId){
			   var url ="${basePath}/music/editTag?id="+tagId;
	            layer.open({
	                type: 2,
	                area: ['600px', '300px'],
	                offset: ['120px', '450px'],
	                fixed: false, //不固定
	                maxmin: true,
	                content: [url]
	           })
		   } */
		    
		   function del(tagId,categoryId){
			   var url = null;
			   if(categoryId == 1){//1音乐
				   url = "${basePath}/music/delTag";
			   }
			   if(categoryId == 2){//2故事 
				   url = "${basePath}/story/delTag";
			   }
			   if(categoryId == 3){//3教育
				   url = "${basePath}/edu/delTag"; 
			   }
			   layer.confirm('确认删除', {
		            btn: ['删除','取消'],icon: 7, title:'注意' //按钮
		        }, function(index){
		            $.post(url,{'tagId':tagId,'categoryId':categoryId},function (data) {
		                if(data.isSuccess){
		                    layer.msg(data.message, {icon: 6});
		                    setTimeout(function(){
		                    	$(".layui-laypage-skip").find(".layui-laypage-btn").click();
	               		 	}, 2000); 
		                }else{
		                	layer.msg(data.message, {icon: 2});
		                }
		            })
		        });
			}
		
		   /* function _returnData(pageNum) {
		       var url = "${basePath}/music/selectTagCategoryList";
		        jump(url,pageNum);
		    } */
		</script>
	</body>
</html>