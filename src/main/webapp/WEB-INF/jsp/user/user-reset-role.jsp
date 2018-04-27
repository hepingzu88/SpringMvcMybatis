<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
		<title>Title</title>
	    <link rel="stylesheet" href="${basePath}/css/sccl.css">
	    <script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
	  <%--   <script src="${basePath}/js/page.js"></script> --%>
	</head>
	<body>
		<blockquote class="layui-elem-quote layui-text">
			<!-- 已勾选的复选框,就是该角色已经拥有的角色 -->
			用户授权
		</blockquote>
		<form class="layui-form" action="">
			<input type="hidden" value="${id}" id="id" name="id">
		   <%-- <div class="layui-form-item">
		    	<label class="layui-form-label">角色列表</label>
			    <div class="layui-input-block" style="width: 70%;">
			      <c:forEach items="${list}" var="bean">
			      	<input type="checkbox" name="arr"  title="${bean.roleName}" value="${bean.id}">
			      </c:forEach>
			    </div>
		    </div> --%>
		    <div class="layui-form-item">
		     	<select name="roleIdList" id="roleIdList" lay-verify="required" lay-search="">
		     		<c:forEach items="${list}" var="bean">
        				<option value="${bean.id}">${bean.roleName}</option>
        			</c:forEach>
		    	</select>
		    </div>
		    <br><br><br><br>
		  	<div class="layui-form-item">
	          <div class="layui-input-block" style="margin-left: 40%;"> 
	              <button class="layui-btn" lay-submit="" lay-filter="from" type="button">立即提交</button>
	          </div>
	        </div>
  		</form>
		<script type="text/javascript">
			layui.use(['form', 'layedit', 'laydate'], function(){
			  var form = layui.form
			  ,layer = layui.layer
			  ,layedit = layui.layedit
			  ,laydate = layui.laydate;
			  form.render();
			  
			  //监听提交
			  form.on('submit(from)', function(data){
				/*  var text = $("input:checkbox[name='arr']:checked").map(function(index,elem) {
		            return $(elem).val();
		         }).get().join(',');
				
				 if($("input[type='checkbox']").is(':checked') == false){
					text = null;
				 }; */
			     $.post("${basePath}/admin/user/resetRole",{"operatorId":$("#id").val(),"roleId":$("#roleIdList").val()},function(res){
			    	if(res.isSuccess){
			    		layer.msg(res.message);
			    		 setTimeout(function(){
		           			 parent.layer.close(parent.layer.getFrameIndex(window.name)); //再执行关闭 
		           			 parent.location.reload();
              		 	 }, 2000);
			    	}else{
			    		layer.msg(res.message);
			    	}
			    })
			  });//
			  
				//显示角色
				$.post('${basePath}/admin/user/findUserRole',{"id":$("#id").val()},function(res){
					console.log(res);
					var roleArr = [];
					$.each(res, function(index,item){ 
						roleArr.push(item.id);
				    }); 
					if(roleArr != null){
					　 addClass(roleArr);
					}
				});
			  
			 	 function addClass(data) {
					$.each(data, function(i, n){
						$("input").each(function(index,elem) {
							console.log(elem.value);
							 if(elem.value == n){
								 $(this).attr({"checked":""});
								 form.render();
							 }
					    })
					});
				}
			});
		</script>
	</body>
</html>