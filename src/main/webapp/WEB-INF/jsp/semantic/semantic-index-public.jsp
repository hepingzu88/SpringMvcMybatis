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
			 <!-- <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
			    <legend>自定义场景</legend>
			 </fieldset> -->
			<%--  <form class="layui-form" id="_form">
			  	 <div class="layui-form-item">
			  	 	<div class="layui-inline">
			            <button class="layui-btn"> <i class="layui-icon">&#x1002;</i></button>
			        </div>
	             	<div class="layui-inline">
		                <input type="hidden" id="pageNum" name="pageNum" value="${page}"/>
		                <select name="sceneId" lay-filter="intent" lay-search>
		                    <option value="-1">所有</option>
		                    <c:forEach items="${list}" var="scene">
				        		<option value="${scene.id}">${scene.scene}</option>
				        	</c:forEach>
		                </select>
	             	</div> 
		         </div>
				<div id="form_content">
			        	
			  	</div>
		      </form> --%>
		      <br>
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
				  <button class="layui-btn" data-type="reload">搜索</button>
				  <button class="layui-btn" data-type="refresh"> <i class="layui-icon">&#x1002;</i></button>
				  <shiro:hasPermission name="user:shows">
				  	<button class="layui-btn" data-type="getCheckData">批量删除</button>
				  </shiro:hasPermission>
			  </div>
			  
		      <table class="layui-hide" id="test" lay-filter="demo"></table>
		      <shiro:hasPermission name="user:shows">  
			      <script type="text/html" id="barDemo">
  					<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">新增同义句</a>
  					<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  					<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
			  	 </script>
		     </shiro:hasPermission>
		</div>
		<script type="text/javascript">
			layui.use(['table','jquery','element','form','layer'], function(){
			  var table = layui.table,
			  $ = layui.$,
			  element = layui.element,
			  form = layui.form,
			  layer = layui.layer;
			  form.render();
			 
			  table.render({
				    elem: '#test'
				    ,url:'${basePath}/semantic/findPublic'
				    ,cols: [[
				      /* {type: 'checkbox',checkbox:true} */
				      {field:'id', title:'ID', width:100, sort: true}
				      ,{field:'text', title:'语料',width:480}
				      ,{field:'answer', title:'回答', width:577}
				      ,{field:'scene',title:'场景',width:120}
					  ,{field:'intent',title:'意图',width:120}
				      ,{field:'sentiment', title:'情感状态', width:120}
				      ,{field:'applyAge', title:'适用年龄', width:120}
				      /* ,{field:'lock', title:'编辑', width:350, templet: '#barDemo'} */
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
				    	//view();
				    },reload: function(){
				        var sceneId = $('#sceneId').val();
				        var text = $("#text").val();
				        table.render({
						    elem: '#test'
						    ,url:'${basePath}/semantic/findPublic?sceneId='+sceneId+"&text="+text
						    ,cols: [[
						      /*  {type: 'checkbox',checkbox:true} */
						      {field:'id', title:'ID', width:100, sort: true}
						      ,{field:'text', title:'语料',width:500}
						      ,{field:'answer', title:'回答', width:600}
						      ,{field:'scene',title:'场景',width:150}
							  ,{field:'intent',title:'意图',width:150}
						      ,{field:'sentiment', title:'情感状态', width:150}
						      ,{field:'applyAge', title:'适用年龄', width:150}
						     /*  ,{field:'lock', title:'编辑', width:350, templet: '#barDemo'} */
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
			  
			 /*  form.on('select(intent)',function (data) {
		          jump("${basePath}/semantic/find",null);
		      }); */
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
		                    //view();
		                    //var pageNum = $("#pageNum").val();
		                   // jump("${basePath}/semantic/find",pageNum);
		                }else{
		                	layer.msg(data.message, {icon: 2});
		                }
		            })
		        });
			 }
			
			
			function updateScene(id){
		        var url ="${basePath}/semantic/updateScene?id="+id;
		        layer.open({
		            type: 2,
		            area: ['1325px', '550px'],
		            offset: ['100px', '140px'],
		            fixed: false, //不固定
		            maxmin: true,
		            content: [url],
		           /*  end: function(){ //此处用于演示
		            	var url = "${basePath}/semantic/find";
				        jump(url,$("#pageNum").val());
		            } */
		            btn: ['上一个','下一条'],
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
	              	},btn2:function(index){//下一条
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
	              	}
		        })
		    }
			//问答
			function answers(id,sceneId) {
				 if(sceneId == "" || sceneId == null || typeof(sceneId) == 'undefined'){
					 layer.msg("该语料无场景，请点击编辑，为语料选择场景");
					 return;
				 }
				 /* var url = "${basePath}/answer/custom/custom-answer-index?id="+id+"&pageNum="+$("#pageNum").val();
				 layer.open({
			            type: 2,
			            area: ['1300px', '550px'],
			            offset: ['100px', '140px'],
			            fixed: false, //不固定
			            maxmin: true,
			            content: [url]
			        }) */
				 window.location.href = "${basePath}/answer/custom/custom-answer-index?id="+id+"&pageNum="+$("#pageNum").val();
			}
			
			/*$(function () {
			    _returnData($("#pageNum").val());
			    $("#refresh").click(function () {
			        location.reload();
			    });
			}) */

			/* function jump(url,pageNum) {
			    if(pageNum!=null && pageNum!=''){
			        $("#pageNum").val(pageNum);
			    }
			    $.ajax({
			        url : url,
			        type: "POST",
			        data: $("#_form").serialize(),
			        success: function(data){
			            $("#form_content").html(data);
			        }
			    })
			}
			
			function closeLayer(index) {
		        layer.close(index);
		        location.reload();
		    }
	
		    function _returnData(pageNum) {
		        var url = "${basePath}/semantic/find";
		        jump(url,pageNum);
		    }  */
		</script>
	</body>
</html>