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
		    <form class="layui-form" id="_form">
			  	 <button class="layui-btn add-button" type = "button" style="margin-left: 80%;">授权</button>
			     <input type="hidden" id="id" name="id" value="${id}"/>
			  	 <div class="layui-form-item">
			        <input type="hidden" id="pageNum" name="pageNum"/>
		         </div>
				 <div id="form_content">
				 
			  	 </div>
		    </form>
		</div>
		<script type="text/javascript">
			layui.use(['form', 'layer'], function(){
			   var form = layui.form
			  ,layer = layui.layer;
		
				$(".add-button").on('click',function(){
					var url = "${basePath}/admin/user/authorization?id="+$("#id").val();
					layer.open({
						title:"授权",						
			            type: 2,
			            area: ['600px', '300px'],
			            offset: ['100px', '250px'],
			            fixed: false, //不固定
			            maxmin: true,
			            content: [url,"no"]
			        })
				})
			})
			
			function del(id,permissionId){
				$.post("${basePath}/admin/user/delPermission",{"id":id,"permissionId":permissionId},function(res){
					if(res.isSucceed){
						layer.msg(res.message);
					}else{
						layer.msg(res.message);
					}
					
				})
			}
			
			function closeLayer(index) {
		        layer.close(index);
		        location.reload();
		    }
		 
		    function _returnData(pageNum) {
		        var url = "${basePath}/admin/user/edit";
		        jump(url,pageNum);
		    }
		</script>
	</body>
</html>
