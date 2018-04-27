<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    	<title>Title</title>
	    <link rel="stylesheet" href="${basePath}/css/sccl.css">
	    <script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
	 <%--    <script src="${basePath}/js/page.js"></script> --%>
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
		      <span style="display: none" id="parentId">${id}</span>
			      <div class="demoTable layui-form">
				     	<div class="layui-inline">
			                <input type="hidden" id="pageNum" name="pageNum" value="${page}"/>
		             	</div>
					 <!--  <div class="layui-inline">
					    <input class="layui-input" placeholder="按问题搜索" id="text" name="text" autocomplete="off">
					  </div>
					  <button class="layui-btn" data-type="reload" type="button">搜索</button> -->
					  <button class="layui-btn" data-type="refresh" type="button"> <i class="layui-icon">&#x1002;</i></button>
					  <button class="layui-btn" data-type="getCheckData" type="button">批量删除</button>
					  <button class="layui-btn" type="button" data-type="insert">新增同义句</button>
					  <a style="display: none" href="" id="download_a"></a>
				  </div>
				 <div class="layui-form-item">
    				<div class="layui-inline">
		      			<table class="layui-hide" id="test" lay-filter="demo"></table>
		      		</div>
			        <div class="layui-inline">
				      <form class="layui-form" id="_form" style="width: 500px;top: 300px;">
					  </form>
				    </div>
				 </div>
		      <script type="text/html" id="barDemo">
  				<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
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
			 
			 //监听单元格编辑
			  table.on('edit(demo)', function(obj){
			    var value = obj.value //得到修改后的值
			    ,data = obj.data //得到所在行所有键值
			    ,field = obj.field; //得到字段
			    var id = data.id;
			    if(field == 'text'){
			    	var text = value;
			    }else if(field == 'answer'){
			    	var answer = value;
			    }
			    $.post("${basePath}/semantic/update",{"id":id,"text":text,"answer":answer},function(res){
			    	if(res == 'true'){
			    		layer.msg('[ID: '+ data.id +'] ' + field + ' 更改为：'+ value);
			    	}else{
			    		layer.msg("修改失败");
			    	}
			    })
			  });
			  
			  var tableIns = table.render({
				    elem: '#test'
				    ,url:'${basePath}/answer/custom/custom-answer-find?id='+$("#parentId").text()
				    ,cols: [[
				      {type: 'checkbox',checkbox:true,disabled:true}
				      ,{field:'id', title:'ID', width:100, sort: true}
				      ,{field:'text', title:'语料',width:480,edit:'text'}
				      ,{field:'answer', title:'回答', width:480,edit:'text'}
				      ,{field:'lock', title:'编辑', width:170, templet: '#barDemo'}
				    ]]
				    ,id: 'testReload'
				    ,page: true
				    ,limit:10
				    ,limits:[10]
			   });
			  
			  
			  table.on('tool(demo)', function(obj){
				    var data = obj.data;
				     if(obj.event === 'del'){
				    	remove(data.id);
				    } else if(obj.event === 'edit'){
				    	edit(data.id,data.sceneId);
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
				      batchDeleteSynonymous(remove);
				    },refresh:function(){
				    	location.reload();
				    },insert : function(){
						var url ="${basePath}/semantic/insertSynonymous?id="+$("#parentId").text();
				        layer.open({
							title:"新增同义句",				        	
				            type: 2,
				            area: ['800px', '350px'],
				            offset: ['60px', '250px'],
				            fixed: false, //不固定
				            maxmin: true,
				            content: [url],
				            end:function(){
				            	$(".layui-laypage-skip").find(".layui-laypage-btn").click();
				            }
				         })
				    },
				    reload: function(){
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
			})
			
			function edit(id,intentId){
		   		if(typeof(id) == 'undefined'){
		   			layer.confirm('请选保存同义句', {icon: 3, title:'提示',offset: ['60px', '500px']}, function(index){
		   				layer.close(index);
		   			});
		   			return;
		   		}
		   		/* alert(intentId);
		   		if(typeof(intentId) == 'undefined' || intentId == null || intentId == ''){
		   			layer.confirm('该语料暂时没有意图，请先为语料编辑意图', {icon: 3, title:'提示',offset: ['60px', '500px']}, function(index){
		   				layer.close(index);
		   			});
		   			return;
		   		} */
		        var url ="${basePath}/semantic/edit?id="+id;
		        layer.open({
		            type: 2,
		            area: ['1000px', '420px'],
		            offset: ['20px', '160px'],
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
								edit(arr[i],intentId);
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
								edit(arr[i],intentId);
	                			break;
	                		}
	                	}
	                	layer.close(index);
	              	}
		        })
		   	 }
			
			$("div").on('click','#remove',function(){
				var button = $(this).context;
				var id = button.value;
				if(id == 'true'){
					$(this).parent().parent().empty();
				}
			})
			
			//delte删除
			 function remove(id){
				if(id == $("#parentId").text()){
					var url ="${basePath}/semantic/deleteSemanticAndSimilarity";
					layer.confirm('确认删除语料，以及语料的同义句', {
						offset: ['60px', '500px'],								
			            btn: ['删除','取消'],icon: 7, title:'注意' //按钮
			        }, function(index){
			            $.post(url,{"id":id},function (data) {
			                if(data.isSuccess){
			                    layer.msg(data.message);
			                    setTimeout(function(){
			                    	 window.location.href = "${basePath}/semantic/semanticIndex?page="+$("#page").text();
		               		 	}, 2000);
			                }else{
			                	layer.msg(data.message);
			                }
			            })
			        });
				}else{
					var url ="${basePath}/semantic/delete";
					layer.confirm('确认删除', {
						offset: ['60px', '500px'],						
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
			 }
			
			//批量删除
			function batchDeleteSynonymous(removeArr){
			 	var url ="${basePath}/semantic/batchDeleteSynonymous";
		        layer.confirm('确认删除同义语句', {
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