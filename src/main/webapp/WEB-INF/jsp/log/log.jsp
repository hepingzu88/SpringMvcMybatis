<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/common.jsp" %>
<html>
	<head>
	    <title>Title</title>
	    <link rel="stylesheet" href="${basePath}/css/sccl.css">
	    <%@include file="/include/common_style.jsp" %>
	    <%@include file="/include/common_js.jsp" %>
	    <script src="${basePath}/js/page.js"></script>
	    <style type="text/css">
	        .add-button{
	            float: left
	        }
	    </style>
	</head>
	<body>
		<div class="content-box">
		    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		        <legend>默认表格</legend>
		    </fieldset>
		
		    <form class="layui-form" id="_form">
		        <div class="layui-form-item">
		            <a id="refresh" class="layui-btn  layui-btn-warm layui-btn-big add-button"
		               href="javascript:void(0);">
		                <i class="layui-icon">&#x1002;</i>
		            </a>
		            <a id="add" class="layui-btn layui-btn-normal layui-btn-big add-button" href="javascript:void(0);" >
		                <i class="layui-icon">&#xe654;</i>
		            </a>
		            <label class="layui-form-label">名称</label>
		            <div class="layui-input-inline">
		                <input type="hidden" id="pageNum" name="pageNum"/>
		                <input name="contentName" lay-verify="name" autocomplete="off" class="layui-input">
		            </div>
		            <button id="search" class="layui-btn layui-btn-primary" type="button">搜  索</button>
		        </div>
		        <div id="form_content">
		        	
		        </div>
		    </form>
		</div>
		<script type="text/javascript">
			 function closeLayer(index) {
		        layer.close(index);
		        location.reload();
		     }
	
		    function _returnData(pageNum) {
		        var url = "${basePath}/log/query";
		        jump(url,pageNum);
		    }
		    
		</script>		
	</body>
</html>
