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
	       <form class="layui-form" id="_form">
		      <div class="demoTable layui-form">
			     	<div class="layui-form-item">
		                <input type="hidden" id="pageNum" name="pageNum" value="${page}"/>
		                <div class="layui-input-inline">
			                <select name="version" id="versionId" lay-filter="intent" lay-search>
			                    <option value="-1">所有</option>
			                    <c:forEach items="${listVersion}" var="item">
					        		<option value="${item.id}">${item.version}</option>
					        	</c:forEach>
			                </select>
		                </div>
		                <div class="layui-input-inline">
			                <select name="isTagged" id="isTagged" lay-filter="intent" lay-search>
			                    <option value="-1">所有</option>
					        	<option value="0">未标注标签</option>
					        	<option value="1">已标注标签</option>
			                </select>
		                </div>
		              <div class="layui-inline">
				    	<input class="layui-input" placeholder="按课程名称搜索" id="name" name="name" autocomplete="off">
				  	  </div>
					  <button class="layui-btn" data-type="reload" type="button">搜索</button>
					  <button class="layui-btn" data-type="refresh" type="button"> <i class="layui-icon">&#x1002;</i></button>
					 <!--  <button class="layui-btn" data-type="getCheckData" type="button">批量删除</button> -->
					  <button class="layui-btn" data-type="addTag" type="button">新增资源标签</button>
					  <a style="display: none" href="" id="download_a"></a>
	             	</div>
			  </div>
		  </form>
	      <table class="layui-hide" id="test" lay-filter="demo"></table>
	      <script type="text/html" id="barDemo">
  			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		  </script>
		</div>
		<script type="text/javascript" charset="UTF-8">
			
			layui.use(['table','jquery','element','form','layer'], function(){
			  var table = layui.table,
			  $ = layui.$,
			  element = layui.element,
			  form = layui.form,
			  layer = layui.layer;
			  form.render();
			 
			  var tableIns = table.render({
				    elem: '#test'
				    ,url:'${basePath}/edu/res/index'
				    ,cols: [[
				      {type: 'checkbox',checkbox:true}
				      ,{field:'id', title:'ID', width:100, sort: true}
				      ,{field:'name', title:'课程',edit:'text',width:300}
				      ,{field:'course', title:'课程描述',width:300}
				      ,{field:'semester', title:'学期', width:150}
				      ,{field:'year', title:'年份', width:100}
				      ,{field:'grade', title:'年级', width:100}
				      ,{field:'version', title:'版本', width:100}
				      ,{field:'level', title:'学段', width:100}
				      ,{field:'subject', title:'学科', width:100}
				      ,{field:'state', title:'是否标注', width:100}
				      ,{field:'lock', title:'编辑', width:200, templet: '#barDemo'}
				    ]]
				    ,id: 'testReload'
				    ,page: true
				    ,limit:10
				    ,limits:[10]
			   });
			  
			  
			  table.on('tool(demo)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'detail'){
				    	answers(data.id,data.sceneId);
				    } else if(obj.event === 'del'){
				    	remove(data.id);
				    } else if(obj.event === 'edit'){
				    	edit(data.id);
				    }
				  });
				  
				  var $ = layui.$, active = {
				    getCheckData: function(){ //获取选中数据  批量删除
				      var checkStatus = table.checkStatus('testReload')
				      ,data = checkStatus.data;
				      if(data.length == 0){
				    	  layer.msg('请勾选复选框', {icon: 6}); 
				    	  return;
				      }
				      var remove = [];
				      $.each(data, function(i, n){
				    	remove.push(n.id);
				      });
				      batchRemove(remove);
				    },refresh:function(){
				    	location.reload();
				    },addTag:function(){
				    	var url ="${basePath}/music/addTag";
			            layer.open({
							title:"新增资源标签",			            	
			                type: 2,
			                area: ['600px', '350px'],
			                offset: ['120px', '450px'],
			                fixed: false, //不固定
			                maxmin: true,
			                content: [url]
			           })
				    },reload: function(){//搜索
				        var isTagged = $('#isTagged').val();
				    	var versionId = $('#versionId').val();
				    	var name = $("#name").val();
				        table.render({
						    elem: '#test'
						    ,url:'${basePath}/edu/res/index?isTagged='+isTagged+"&versionId="+versionId+"&name="+name
						    ,cols: [[
						    	 {type: 'checkbox',checkbox:true}
							      ,{field:'id', title:'ID', width:100, sort: true}
							      ,{field:'name', title:'课程',edit:'text',width:300}
							      ,{field:'course', title:'课程描述',width:300}
							      ,{field:'semester', title:'学期', width:150}
							      ,{field:'year', title:'年份', width:100}
							      ,{field:'grade', title:'年级', width:100}
							      ,{field:'version', title:'版本', width:100}
							      ,{field:'level', title:'学段', width:100}
							      ,{field:'subject', title:'学科', width:100}
							      ,{field:'state', title:'是否标注', width:100}
							      ,{field:'lock', title:'编辑', width:200, templet: '#barDemo'}
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
			    $.post("${basePath}/edu/updateEduRes",{"id":data.id,"name":value},function (data) {
	                if(data.isSuccess){
	                    layer.msg(data.message);
	                    setTimeout(function(){
	                    	location.reload();
               		 	}, 2000);
	                }else{
	                	layer.msg(data.message);
	                }
	            })
			  });
				  
			  
			  function edit(id){
				  var url ="${basePath}/edu/edit?id="+id;
		            layer.open({
		                type: 2,
		                area: ['800px', '480px'],
		                offset: ['120px', '450px'],
		                fixed: false, //不固定
		                maxmin: true,
		                content: [url],
		                end:function(){
		              		$(".layui-laypage-skip").find(".layui-laypage-btn").click();
		              	}
		           })
			  }
		      
			})
			
			//delte删除大分类
			 function remove(id){
			 	var url ="${basePath}/edu/deletEduRes";
		        layer.confirm('确认删除', {
		            btn: ['删除','取消'],icon: 7, title:'注意' //按钮
		        }, function(index){
		            $.post(url,{"tbEduResId":id},function (data) {
		                if(data.isSuccess){
		                    layer.msg(data.message);
		                    setTimeout(function(){
		                    	location.reload();
	               		 	}, 2000);
		                }else{
		                	layer.msg(data.message);
		                }
		            })
		        });
			 }
			
			//批量删除
			function batchRemove(removeArr){
			 	var url ="${basePath}/semantic/batchDelete";
		        layer.confirm('确认删除该语料，以及该语料同义语句', {
		            btn: ['删除','取消'],icon: 7, title:'注意' //按钮
		        }, function(index){
		        	console.log(removeArr.join(","));
		            $.post(url,{'arrys':removeArr.join(",")},function (data) {
		                if(data.isSuccess){
		                    layer.msg(data.message, {icon: 6});
		                    setTimeout(function(){
		                    	location.reload();
	               		 	}, 2000); 
		                }else{
		                	layer.msg(data.message, {icon: 2});
		                }
		            })
		        });
			 }
		</script>
	</body>
</html>