<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/common.jsp" %>
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
		       <form class="layui-form" id="_form" action="${basePath}/semantic/export">
			      <div class="demoTable layui-form">
				     	<div class="layui-inline">
			                <input type="hidden" id="pageNum" name="pageNum" value="${page}"/>
			                <%-- <select name="sceneId" id="sceneId" lay-filter="intent" lay-search>
			                    <option value="-1">所有</option>
			                    <c:forEach items="${list}" var="scene">
					        		<option value="${scene.id}">${scene.scene}</option>
					        	</c:forEach>
			                </select> --%>
		             	</div>
					  <div class="layui-inline">
				      	<label class="layui-form-label">开始时间</label>
				      	<div class="layui-input-inline">
				        	<input type="text" class="layui-input" id="startTime" placeholder="开始时间">
				      	</div>
				      </div>
				      <div class="layui-inline">
				      	<label class="layui-form-label">结束时间</label>
				      	<div class="layui-input-inline">
				        	<input type="text" class="layui-input" id="endTime" placeholder="结束时间">
				      	</div>
				      </div>
				      <div class="layui-inline">
				      	<label class="layui-form-label">IP地址</label>
				      	<div class="layui-input-inline">
					    	<input class="layui-input" placeholder="按请求IP搜索" id="reuqestIp" name="reuqestIp" autocomplete="off">
					    </div>
					  </div>
					  <div class="layui-inline">
						  <button class="layui-btn" data-type="reload" type="button">搜索</button>
						  <button class="layui-btn" data-type="refresh" type="button"> <i class="layui-icon">&#x1002;</i></button>
						  <button class="layui-btn" data-type="getCheckData" type="button">批量删除</button>
					  </div>
				  </div>
			  </form>
		      <table class="layui-hide" id="test" lay-filter="demo"></table>
		      <script type="text/html" id="barDemo">
  				<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
			  </script>
		</div>
		<script type="text/javascript">
			layui.use(['table','jquery','element','form','layer','laydate'], function(){
			  var table = layui.table,
			  $ = layui.$,
			  laydate = layui.laydate,
			  element = layui.element,
			  form = layui.form,
			  layer = layui.layer;
			  form.render();
			 
			  //日期时间范围
			  laydate.render({
			    elem: '#startTime'
			    ,type: 'datetime'
			  });
			 //日期时间范围
			  laydate.render({
			    elem: '#endTime'
			    ,type: 'datetime'
			  });
			  
			  var tableIns = table.render({
				    elem: '#test'
				    ,url:'${basePath}/log/selectList'
				    ,cols: [[
				      {type: 'checkbox',checkbox:true}
				      ,{field:'userName', title:'用户名',width:100}
				      ,{field:'reuqestIp', title:'IP', width:100}
					  ,{field:'methodName',title:'方法名',width:200}
				      ,{field:'startTime', title:'开始时间', width:150}
				      ,{field:'endTime', title:'结束时间', width:150}
				      ,{field:'actionType', title:'行为类型', width:200}
				      ,{field:'params', title:'请求参数', width:600}
				      ,{field:'lock', title:'编辑', width:100, templet: '#barDemo'}
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
				    	var removeId = [];
				    	removeId.push(data.id);
				    	remove(removeId);
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
				      var removeId = [];
				      $.each(data, function(i, n){
				    	  removeId.push(n.id);
				      });
				      remove(removeId);
				    },refresh:function(){
				    	location.reload();
				    	//view()
				    },reload: function(){
				        var reuqestIp = $("#reuqestIp").val();
				        var startTime = $("#startTime").val();
				        var endTime = $("#endTime").val();
				        table.render({
						    elem: '#test'
						    ,url:'${basePath}/log/selectList?reuqestIp='+reuqestIp+"&startTime="+startTime+"&endTime="+endTime
					    	,cols: [[
							      {type: 'checkbox',checkbox:true}
							      ,{field:'userName', title:'用户名',width:100}
							      ,{field:'reuqestIp', title:'IP', width:100}
								  ,{field:'methodName',title:'方法名',width:200}
							      ,{field:'startTime', title:'开始时间', width:150}
							      ,{field:'endTime', title:'结束时间', width:150}
							      ,{field:'actionType', title:'行为类型', width:200}
							      ,{field:'params', title:'请求参数', width:600}
							      ,{field:'lock', title:'编辑', width:100, templet: '#barDemo'}
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
			
			//delte删除大分类
			 function remove(removeArr){
			 	var url ="${basePath}/log/delete";
		        layer.confirm('确认删除日志信息', {
		            btn: ['删除','取消'],icon: 7, title:'注意' //按钮
		        }, function(index){
		            $.post(url,{"arrys":removeArr.join(",")},function (data) {
		                if(data.isSuccess){
		                    layer.msg(data.message);
		                    setTimeout(function(){
		                    	location.reload();
	               		 	}, 2000);
		                    //view();
		                    /* var pageNum = $("#pageNum").val();
		                    jump("${basePath}/semantic/find",pageNum); */
		                }else{
		                	layer.msg(data.message);
		                }
		            })
		        });
			 }
			
		</script>
	</body>
</html>