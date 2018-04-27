<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/common.jsp" %>
<html>
	<head>
    	<title>Title</title>
	    <link rel="stylesheet" href="${basePath}/css/sccl.css">
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
	       <form class="layui-form" id="_form" action="${basePath}/semantic/export">
		      <div class="demoTable layui-form">
			     	<div class="layui-inline">
		                <input type="hidden" id="pageNum" name="pageNum" value="${page}"/>
		                <select name="sceneId" id="sceneId" lay-filter="intent" lay-search>
		                    <option value="-1">所有</option>
		                    <c:forEach items="${list}" var="scene">
				        		<option value="${scene.id}">${scene.scene}</option>
				        	</c:forEach>
		                </select>
	             	</div>
				  <div class="layui-inline">
				    <input class="layui-input" placeholder="按问题搜索" id="text" name="text" autocomplete="off">
				  </div>
				  <button class="layui-btn" data-type="reload" type="button">搜索</button>
				  <button class="layui-btn" data-type="refresh" type="button"> <i class="layui-icon">&#x1002;</i></button>
				  <button class="layui-btn" data-type="getCheckData" type="button">批量删除</button>
				  <button class="layui-btn" data-type="import_semantic" type="button">批量导入</button>
				  <button class="layui-btn" data-type="export_semantic" type="button">批量导出</button>
				 <!--  <button class="layui-btn" lay-submit="" lay-filter="demo1">批量导出</button> -->
				  <a style="display: none" href="" id="download_a"></a>
			  </div>
		  </form>
	      <table class="layui-hide" id="test" lay-filter="demo"></table>
	      <script type="text/html" id="barDemo">
  			<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">新增同义句</a>
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
				    ,url:'${basePath}/semantic/find'
				    ,cols: [[
				      {type: 'checkbox',checkbox:true}
				      ,{field:'id', title:'ID', width:100, sort: true}
				      ,{field:'text', title:'语料',width:300}
				      ,{field:'answer', title:'回答', width:500}
				      ,{field:'scene',title:'场景',width:100}
					  ,{field:'intent',title:'意图',width:100}
				      ,{field:'sentiment', title:'情感状态', width:100}
				      ,{field:'applyAge', title:'适用年龄', width:100}
				      ,{field:'lock', title:'编辑', width:350, templet: '#barDemo'}
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
				    	updateScene(data.id);
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
				    	//$(".layui-laypage-skip").find(".layui-laypage-btn").click();
				    },import_semantic:function(){
				    	var url ="${basePath}/semantic/upload";
			            layer.open({
			                type: 2,
			                area: ['680px', '480px'],
			                offset: ['120px', '450px'],
			                fixed: false, //不固定
			                maxmin: true,
			                content: [url]
			           })
				    },export_semantic:function(){
				    	layer.confirm('确认导出语料(根据问题|场景)', {
				            btn: ['确认','取消'],icon: 7, title:'注意' //按钮
				        }, function(index){
				    		$("form").submit();
				        })
				    },reload: function(){
				        var sceneId = $('#sceneId').val();
				        var text = $("#text").val();
				        table.render({
						    elem: '#test'
						    ,url:'${basePath}/semantic/find?sceneId='+sceneId+"&text="+text
						    ,cols: [[
						       {type: 'checkbox',checkbox:true}
						      ,{field:'id', title:'ID', width:100, sort: true}
						      ,{field:'text', title:'语料',width:300}
						      ,{field:'answer', title:'回答', width:500}
						      ,{field:'scene',title:'场景',width:100}
							  ,{field:'intent',title:'意图',width:100}
						      ,{field:'sentiment', title:'情感状态', width:100}
						      ,{field:'applyAge', title:'适用年龄', width:100}
						      ,{field:'lock', title:'编辑', width:350, templet: '#barDemo'}
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
			  
			  //按下enter键 ，自动搜索
			  $("body").keydown(function() {
	                if (event.keyCode == "13") {//keyCode=13是回车键
	                	var sceneId = $('#sceneId').val();
				        var text = $("#text").val();
				        table.render({
						    elem: '#test'
						    ,url:'${basePath}/semantic/find?sceneId='+sceneId+"&text="+text
						    ,cols: [[
						       {type: 'checkbox',checkbox:true}
						      ,{field:'id', title:'ID', width:100, sort: true}
						      ,{field:'text', title:'语料',width:300}
						      ,{field:'answer', title:'回答', width:500}
						      ,{field:'scene',title:'场景',width:100}
							  ,{field:'intent',title:'意图',width:100}
						      ,{field:'sentiment', title:'情感状态', width:100}
						      ,{field:'applyAge', title:'适用年龄', width:100}
						      ,{field:'lock', title:'编辑', width:350, templet: '#barDemo'}
						    ]]
						    ,id: 'testReload'
						    ,page: true
						    ,limit:10
						    ,limits:[10]
						});
	                }
	            });
			  
			  //增加text语句拆分场景
			  $("#add-scene").on('click',function(){
				  var url ="${basePath}/semantic/addScene";
		            layer.open({
		                type: 2,
		                area: ['480px', '280px'],
		                offset: ['120px', '450px'],
		                fixed: false, //不固定
		                maxmin: true,
		                content: [url,"no"]
		           })
			  })
		      
			  function updateScene(id){
			        var url ="${basePath}/semantic/updateScene?id="+id;
			        layer.open({
			            type: 2,
			            area: ['1325px', '550px'],
			            offset: ['100px', '140px'],
			            fixed: false, //不固定
			            maxmin: true,
			            content: [url],
			            btn: ['上一条','下一条'],
			            yes: function(index, layero){//上一条
			            	var arr= [];
			            	$("tr td:nth-child(2)").each(function(i,n){
		              			arr.push(n.innerText);
		                	}) 
		                	arr = arr.reverse();
		                	for(var i = 0 ; i <= arr.length;i++){
		                		layer.close(i+1);
								if(Number(arr[i]) < Number(id)){
									updateScene(arr[i]);
									break;
		                		}
		                	}
		                	layer.close(index);
		              	},btn2:function(index){
		              		var arr= [];
		              		$("tr td:nth-child(2)").each(function(i,n){
		              			arr.push(n.innerText);
		                	}) 
		                	for(var i = 0 ; i < arr.length;i++){
		                		layer.close(i+1);
								if(Number(arr[i]) > Number(id)){
									updateScene(arr[i]);
		                			break;
		                		}
		                	}
		                	layer.close(index);
		              	},end:function(){
		              		$(".layui-laypage-skip").find(".layui-laypage-btn").click();
		              	}
			        })
			    }
		      
		   	 	//问答
				function answers(id) {
					 var url = "${basePath}/answer/custom/custom-answer-index?id="+id+"&pageNum="+$("#pageNum").val();
					 layer.open({
				            type: 2,
				            area: ['1325px', '550px'],
				            offset: ['100px', '140px'],
				            fixed: false, //不固定
				            maxmin: true,
				            content: [url],
							btn: ['上一条','下一条'],
				            yes: function(index, layero){//上一条
				            	var arr= [];
				            	$("tr td:nth-child(2)").each(function(i,n){
			              			arr.push(n.innerText);
			                	}) 
			                	arr = arr.reverse();
			                	for(var i = 0 ; i <= arr.length;i++){
			                		layer.close(i+1);
									if(Number(arr[i]) < Number(id)){
										answers(arr[i]);
										break;
			                		}
			                	}
			                	layer.close(index);
			              	},btn2:function(index){//下一条
			              		var arr= [];
			              		$("tr td:nth-child(2)").each(function(i,n){
			              			arr.push(n.innerText);
			                	}) 
			                	for(var i = 0 ; i < arr.length;i++){
			                		layer.close(i+1);
									if(Number(arr[i]) > Number(id)){
										answers(arr[i]);
			                			break;
			                		}
			                	}
			                	layer.close(index);
			              	},end:function(){
			              		$(".layui-laypage-skip").find(".layui-laypage-btn").click();
			              	}
				        })
				}
			})
			
			//delte删除大分类
			 function remove(id){
			 	var url ="${basePath}/semantic/deleteSemanticAndSimilarity";
		        layer.confirm('确认删除该语料，以及该语料同义语句', {
		            btn: ['删除','取消'],icon: 7, title:'注意' //按钮
		        }, function(index){
		            $.post(url,{"id":id},function (data) {
		                if(data.isSuccess){
		                    layer.msg(data.message);
		                    setTimeout(function(){
		                    	$(".layui-laypage-skip").find(".layui-laypage-btn").click();
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
		                    	$(".layui-laypage-skip").find(".layui-laypage-btn").click();
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