<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/include/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="${basePath}/css/sccl.css">
	    <script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
	    
	</head>
	<body>
		  <hr>
  		  <div>
		    <button class="layui-btn" id="public">公共场景</button>
		    <button class="layui-btn layui-btn-normal" id="custom">自定义场景</button>
		    <button class="layui-btn layui-btn-warm" id="endtity">实体类型</button>
		    <button class="layui-btn layui-btn-danger" id="action">动作类型</button>
		  </div>
		  
		<script type="text/javascript">
			layui.use(['form'], function(){
		        var form = layui.form;
		        form.on('select(contentType)',function (data) {
		            jump("${basePath}/admin/album/recommendAlbum",null);
		        });
		       
		      //增加实体语句分类
			/*   $("#endtity").on('click',function(){
				  var url ="${basePath}/thesaurus/entity/entity-add";
				  add_index(url);
			  })
			  
			  //增加自定义场景
			  $("#custom").on('click',function(){
				  var url ="${basePath}/thesaurus/custom/custom-scene-add";
				  add_index(url);
			  })
			  
			  //增加行为类型
			  $("#action").on('click',function(){
				  var url ="${basePath}/thesaurus/action/action-add";
				  add_index(url);
			  })
			   */
			  function add_index(url) {
				  layer.open({
		               type: 2,
		               area: ['450px', '280px'],
		               offset: ['120px', '450px'],
		               fixed: false, //不固定
		               maxmin: true,
		               content: [url,"no"]
		          })
			 }
		        
		        
		    });	
		</script>
	</body>
</html>