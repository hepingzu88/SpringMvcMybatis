<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/include/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Title</title>
	    <link rel="stylesheet" href="${basePath}/css/sccl.css">
	    <script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
		<style type="text/css">
			.layui-btn-lg{
				border-color: #009688;
				height: 45px;
				width: 150px;
			}
			.layui-colla-title{
				background-color: white;
			}
		</style>
	</head>
	<body>
		 <form class="layui-form" action="">
		 	 <span style="display: none" id="idApply">${apply.id}</span>
		 	 <span style="display: none" id="message"></span>
		 	 <div class="layui-tab-content">
			    <div class="layui-tab-item layui-show">
			    	<br><br>
			    	<div class="layui-collapse" lay-accordion="">
						 <div class="layui-colla-item">
						    <h2 class="layui-colla-title">自定义场景</h2>
						    <div class="layui-colla-content layui-show">
						      <c:forEach items="${list}" var="bean" varStatus="name">
						      	<button class="layui-btn layui-btn-primary" type="button" style="width: 160px;">
						      		<input type="checkbox" name="custom" title="${bean.scene}" lay-skin="primary" value="${bean.id}">
						      	</button>
						        <c:choose>
						             <c:when test="${name.count % 5 == 0}">
						                 <br><br> 
						             </c:when>
								</c:choose>
						      </c:forEach>
							</div>
						</div>
					 </div> 
					<br> 
					<div class="layui-collapse" lay-accordion="">
						 <div class="layui-colla-item">
						    <h2 class="layui-colla-title">公开场景</h2>
						    <div class="layui-colla-content layui-show">
						    <c:forEach items="${li}" var="bean" varStatus="name" >	
						    	<button class="layui-btn layui-btn-primary" type="button" style="width: 160px;">	 
							        <input type="checkbox" name="publi" title="${bean.scene}" lay-skin="primary" value="${bean.id}">
							    </button>
						        <c:choose>
						             <c:when test="${name.count % 5 == 0}">
						                 <br><br> 
						             </c:when>
								</c:choose>
						      </c:forEach>
							</div>
						</div>
					</div>
					<br><br>  		 
				   <div class="layui-form-item" style="margin-left: 35%;">
		            <div class="layui-input-block">
		                <button class="layui-btn" lay-submit="" lay-filter="from" type="button">完成</button>
		            </div>
		          </div>
		       </div>
		    </div>
		</form>
		<script type="text/javascript">
			layui.use(['form', 'element','layer'], function(){
			  var form = layui.form
			  ,layer = layui.layer
			  ,element = layui.element
			  form.render();
			  
				//监听折叠
			  element.on('collapse(test)', function(data){
			  /*   layer.msg('展开状态：'+ data.show); */
			  });
			  
			  var index = "";
			  //监听提交 layui-form-checked
			  form.on('submit(from)', function(data){
				 var publi = $("input:checkbox[name='publi']:checked").map(function(index,elem) {
			           return $(elem).val();
			     }).get().join(',');
					
				 if($("input[type='checkbox']").is(':checked') == false){
					 publi = null;
				 };
				 
				 var custom = $("input:checkbox[name='custom']:checked").map(function(index,elem) {
			          return $(elem).val();
			     }).get().join(',');
					
				 if($("input[type='checkbox']").is(':checked') == false){
					 custom = null;
				 };
				 
				$.post('${basePath}/apply/apply-add-scene',{"sceneId":custom,"publicSceneId":publi,"id":$("#idApply").text()},function(res){
					result = res;
					if(res.isSuccess){
						 index = res.isSuccess;
						 $("#message").text(res.isSuccess);					 
						 layer.msg(res.message);
	                	 setTimeout(function(){
		           			   parent.layer.close(parent.layer.getFrameIndex(window.name)); //再执行关闭 
		           			   parent.location.reload();
                		 }, 2000);
					 }else{
						 layer.msg(res.message); 
					 }
				 })
				 
			  });
			
				//显示已添加的场景
				$.post('${basePath}/apply/apply-scene-find',{"id":$("#idApply").text()},function(res){
					var apply = res.apply;
					var sceneId = apply.sceneId;
					if(sceneId != null && sceneId.indexOf(",")>-1){
					　     var scene = sceneId.split(",");
					　     addClass(scene);
					}else{
						var arr = new Array(apply.sceneId);
						addClass(arr);
					}
					var publicSceneId = apply.publicSceneId;
					if(publicSceneId != null && publicSceneId.indexOf(",") >-1){
					　    var publicScene = publicSceneId.split(",");
					　    addClass(publicScene);
					}else{
						var arr = new Array(apply.publicSceneId);
						addClass(arr);
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
			})
		</script>
	</body>
</html>