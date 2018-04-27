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
	</head>
	<body>
		<div class="content-box">
			  <form class="layui-form" id="_form">
			  	 <div class="layui-form-item">
			        <input type="hidden" id="pageNum" name="pageNum"/>
		         </div>
				 <div id="form_content">
				 
			  	 </div>
		      </form>
		</div>
		<script type="text/javascript">
			layui.use(['table','jquery','element','form','layer'], function(){
			  var table = layui.table,
			  $ = layui.$,
			  element = layui.element,
			  form = layui.form,
			  layer = layui.layer;
			 //form.render();
			  //增加text语句分类
			  $("#add").on('click',function(){
				  var url ="${basePath}/semantic/add";
		            layer.open({
		                type: 2,
		                area: ['450px', '280px'],
		                offset: ['120px', '450px'],
		                fixed: false, //不固定
		                maxmin: true,
		                content: [url,"no"]
		           })
			  })
			  
			  //增加text语句拆分场景
			  $("#add-scene").on('click',function(){
				  var url ="${basePath}/semantic/addScene";
		            layer.open({
		                type: 2,
		                area: ['950px', '580px'],
		                offset: ['120px', '450px'],
		                fixed: false, //不固定
		                maxmin: true,
		                content: [url,"no"]
		           })
			  })
			  
			  form.on('select(intent)',function (data) {
		          jump("${basePath}/semantic/find",null);
		      });
			})
			
			//delte删除大分类
			 function remove(id,applyName){
			 	var url ="${basePath}/apply/deleteApply";
		        layer.confirm('确认删除', {
		            btn: ['删除','取消'],icon: 7, title:'注意' //按钮
		        }, function(index){
		            $.post(url,{"id":id,"applyName":applyName},function (res) {
		                if(res.isSuccess){
		                    layer.msg(res.message);
		                    var pageNum = $("#pageNum").val();
		                    jump("${basePath}/apply/index",pageNum);
		                }else{
		                	layer.msg(res.message);
		                }
		            })
		        });
			 }
			
			 function edit(id){
		       window.location.href = "${basePath}/apply/apply-scene?id="+id;
		     }
			
			function closeLayer(index) {
		        layer.close(index);
		        location.reload();
		    }
	
		    function _returnData(pageNum) {
		    	var url = "${basePath}/apply/index";
		        jump(url,pageNum);
		    }
		</script>
	</body>
</html>