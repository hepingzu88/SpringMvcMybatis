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
			 <br>
			 <button class="layui-btn" id="addScene">创建意图</button>
			 <button class="layui-btn" id="refresh" type="button"><i class="layui-icon">&#x1002;</i></button>
			  <form class="layui-form" id="_form">
			  	 <div class="layui-form-item">
			        <input type="hidden" id="pageNum" name="pageNum"/>
	             	<%-- <div class="layui-inline">
		                <input type="hidden" id="pageNum" name="pageNum"/>
		                <select name="intent" lay-filter="intent">
		                    <option value="">所有</option>
		                    <c:forEach items="${list}" var="info">
				        		<option value="${info.intent}">${info.intent}</option>
				        	</c:forEach>
		                </select>
	             	</div> --%>
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
			 $("#refresh").on('click',function(){
				 location.reload();
			  })
			  //增加text语句拆分场景
			  $("#addScene").on('click',function(){
				  var url ="${basePath}/thesaurus/intent/intent-add";
		            layer.open({
		                type: 2,
		                area: ['800px', '400px'],
		                offset: ['120px', '450px'],
		                fixed: false, //不固定
		                maxmin: true,
		                content: [url]
		           })
			  })
			  
			  form.on('select(intent)',function (data) {
		          jump("${basePath}/semantic/find",null);
		      });
			})
			
			//delte删除大分类
			 function remove(id,intent){
			 	var url ="${basePath}/thesaurus/intent/intent-delete";
		        layer.confirm('确认删除', {
		            btn: ['删除','取消'],icon: 7, title:'注意' //按钮
		        }, function(index){
		            $.post(url,{"id":id,"intent":intent},function (data) {
		                if(data.isSuccess){
		                    layer.msg(data.message);
		                    var pageNum = $("#pageNum").val();
		                    jump("${basePath}/thesaurus/intent/intent-find",pageNum);
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
		        var url = "${basePath}/thesaurus/intent/intent-find";
		        jump(url,pageNum);
		    }
		</script>
	</body>
</html>