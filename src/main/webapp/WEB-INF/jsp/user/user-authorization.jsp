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
			已勾选的复选框，就是该角色已经拥有的权限
		</blockquote>
		<form class="layui-form" action="">
			<input type="hidden" value="${id}" id="id" name="id">
		   <div class="layui-form-item">
		    	<label class="layui-form-label">权限列表</label>
			    <div class="layui-input-block" style="width: 70%;">
			      <%-- <select lay-verify="required" placeholder="选择权限" multiple="multiple" class="downlist">
			      	<c:forEach items="${list}" var="bean">
	                	<option value="${bean.id}">${bean.permissionName}</option>
	                </c:forEach>
			      </select> --%>
			      <c:forEach items="${list}" var="bean">
			      	<input type="checkbox" name="arr"  title="${bean.permissionName}" value="${bean.id}">
			      </c:forEach>
			    </div>
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
			  
			  /* $(function(){
			   	//遍历渲染
			   	$("select.downlist").each(function(index,item) {
			   		var $this=$(this);
			   		var $select=$this.next(".layui-form-select");
			   		$select.addClass("downpanel");
			   		var $dl=$select.find("dl");
			   		$(".layui-select-title input",$select).val($this.attr("placeholder"));
			   		$dl.empty();
			   		var str="";
			   		$("option",$this).each(function() {
			   			str=["<dd>","<input type='checkbox' name='arr' lay-skin='primary' title='",$(this).text(),"' value='",$(this).val(),"'>","</dd>"].join("");
			   			$dl.append(str);
			   		});
			   		form.render("checkbox");
			   	})

			   	$(".downpanel").on("click",".layui-select-title",function(e){
			   		var $select=$(this).parents(".layui-form-select");
			   		$(".layui-form-select").not($select).removeClass("layui-form-selected");
			   		$select.addClass("layui-form-selected");
			   		e.stopPropagation();
			   	}).on("click",".layui-form-checkbox",function(e){
			   			e.stopPropagation();
			   	});
			  }); */
			  
			  //监听提交
			  form.on('submit(from)', function(data){
				 var text = $("input:checkbox[name='arr']:checked").map(function(index,elem) {
		            return $(elem).val();
		         }).get().join(',');
				
				 if($("input[type='checkbox']").is(':checked') == false){
					text = null;
				 };
			     $.post("${basePath}/admin/user/authorization",{"id":$("#id").val(),"authorizationId":text},function(res){
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
			  
			//显示已添加的场景
				$.post('${basePath}/admin/user/findRolePermission',{"roleId":$("#id").val()},function(res){
					console.log(res);
					var permissionArr = [];
					$.each(res, function(index,item){ 
						permissionArr.push(item.permissionId);
				    }); 
						
					console.log(permissionArr);
					if(permissionArr != null){
					　 addClass(permissionArr);
					}
				});
			  
			 	 function addClass(data) {
					$.each(data, function(i, n){
						$("input").each(function(index,elem) {
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