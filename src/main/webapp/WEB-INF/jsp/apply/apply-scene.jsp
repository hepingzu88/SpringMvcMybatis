<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
		<style type="text/css">
			.layui-btn-lg{
				border-color: #009688;
				height: 45px;
				width: 150px;
				overflow:hidden;
				white-space:nowrap;
				text-overflow:ellipsis;
			}
			.layui-colla-title{
				background-color: white;
			}
			.layui-col-md1 .grid-demo{
				margin:0;padding:0;
			}
		</style>
	</head>
	<body>	
		<div title="" id= "tips"></div>
		<div class="layui-tab layui-tab-card" style="height: 805px;overflow: visible;">
			<br>
			<span id="parentIframe" style="display: none"></span>
			<input value="${list.id}" id="applyId" style="display: none">
			<div class="layui-tab" lay-filter="demo" style="margin-left: 10px;">
			  <ul class="layui-tab-title">
			    <li class="layui-this" lay-id="11">情景模式</li>
			  </ul>
			  <div class="layui-tab-content">
			    <div class="layui-tab-item layui-show">
			    	<button class="layui-btn" style="margin-left:90%;" id="add-scene">添加技能</button>
			    	<br><br>
			    	<div class="layui-collapse" lay-accordion="">
						 <div class="layui-colla-item">
						    <h2 class="layui-colla-title">自定义场景</h2>
						    <div class="layui-colla-content layui-show">
							      <c:if test="${sceneList!=null && sceneList.size() > 0}">
							        <c:forEach items="${sceneList}" var="bean" varStatus="name">
									    <%--  <a href="${basePath}/semantic/semanticIndex" value="${bean.id}"> --%>
											<div class="layui-btn layui-btn-primary layui-btn-lg" onclick="edit(${bean.id})"> 
												<div class="layui-form-item tips2">
												    <!-- <div class="layui-inline">
												        <i class="layui-icon" style="font-size: 30px; color: #009688;">&#xe64a;</i>
												   </div> -->
												   <div class="layui-inline tips">
												     ${bean.scene}
												   </div>
											        <a href="javascript:volid(0);" value="${bean.id}" id="del-a" style="display: none">
													 <i class="layui-icon" id="delCustom" style="font-size: 18px; color: red; position: absolute;">&#xe640;</i> 
												    </a>
												</div>
											</div>
										<!-- </a> -->
										<%-- <a href="javascript:volid(0);" value="${bean.id}">
											<i class="layui-icon" id="delCustom" style="font-size: 15px; color: #009688;">&#x1006;</i> 
										</a> --%>
										&nbsp;&nbsp;
										<c:choose>
							             <c:when test="${name.count % 8 == 0}">
							                 <br><br> 
							             </c:when>
									    </c:choose>
							        </c:forEach>
						    	  </c:if>
						    	  <c:if test="${sceneList == null}">
							        <span>你还没有给该应用添加场景</span>
						    	  </c:if>
						    	<!-- </form> -->
						    </div>
						</div>
					 </div>
					 
					 <br>
					 <c:if test="${publicSceneList!=null && publicSceneList.size() > 0}">
						 <div class="layui-collapse" lay-accordion="">
							 <div class="layui-colla-item">
							    <h2 class="layui-colla-title">开放场景</h2>
							    <div class="layui-colla-content layui-show">
							        <c:forEach items="${publicSceneList}" var="bean" varStatus="name">
									     <%-- <a href="${basePath}/semantic/semanticIndex" value="${bean.id}"> --%>
											<div class="layui-btn layui-btn-primary layui-btn-lg" onclick="edit(${bean.id})"> 
												<div class="layui-form-item tips2">
												   <!--  <div class="layui-inline">
												        <i class="layui-icon" style="font-size: 30px; color: #009688;">&#xe64a;</i>  
												    </div> -->
												    <div class="layui-inline tips">
												    	 ${bean.scene}
												    </div>
												    <a href="javascript:volid(0);" value="${bean.id}" id="del-a" style="display: none">
													 <i class="layui-icon" id="delCustom" style="font-size: 18px; color: red; position: absolute;">&#xe640;</i> 
												    </a>
												</div>
											</div>
										<!-- </a> -->
									<%-- 	<a href="javascript:volid(0);" value="${bean.id}">
											<i class="layui-icon" id="delPublic" style="font-size: 15px; color: #009688;">&#x1006;</i> 
										</a> --%>
										&nbsp;&nbsp;
										<c:choose>
							             <c:when test="${name.count % 8 == 0}">
							                 <br><br> 
							             </c:when>
									   </c:choose>
							        </c:forEach>
							    	<c:if test="${publicSceneList == null}">
								       <span>你还没有给该应用添加场景</span>
							    	</c:if>
							    </div>
							</div>
						 </div>
					 </c:if>
			    </div>
			  </div>
			</div>
		</div>
		<script type="text/javascript">
		  	
			layui.use(['element','form','layer'], function(){
			  var $ = layui.jquery
			  ,element = layui.element,
			  form = layui.form,
			  layer = layui.layer; //Tab的切换功能，切换事件监听等，需要依赖element模块
			  form.render();
			  
			  //鼠标悬停事件
			  $(".layui-btn-lg").mouseover(function(){
				  //layer.msg($(this)[0].innerText);
				  var $a = $(this).children().children().last();
				  $a.show();
			  });
			  //鼠标离开
			  $(".layui-btn-lg").mouseout(function(){
				  var $a = $(this).children().children();
				  console.log($a[1]);
				  $($a[1]).hide();
			  });
			  //监听折叠
			  element.on('collapse(test)', function(data){
			  /*   layer.msg('展开状态：'+ data.show); */
			  });
			  
			  $('i').on('click',function(){
				  if($(this).attr('id') == 'delCustom'){
					var a = $(this).parent();
					var id = a.attr('value');
					console.log(id);
					del(id);
					return;
				  }
				  if($(this).attr('id') == 'delPublic'){
					var a = $(this).parent();
					var id = a.attr('value');
					delPublic(id);
					return;
				  }
			  })
			  
			  function del(id){
				  $.get('${basePath}/apply/apply-del',{"id":$("#applyId").val(),"sceneId":id},function(res){
					  if(res.isSuccess){
						 layer.msg(res.message);
						 window.location.href = "${basePath}/apply/apply-scene?id="+$("#applyId").val();
	                	 setTimeout(function(){
	                		 parent.layer.close(parent.layer.getFrameIndex(window.name)); //再执行关闭 
                		 }, 2000);
					 }else{
						 layer.msg(res.message); 
					 }
				  })
			  }
			  
			  function delPublic(id){
				  $.get('${basePath}/apply/apply-delPublic',{"id":$("#applyId").val(),"publicSceneId":id},function(res){
					  if(res.isSuccess){
						 layer.msg(res.message);
						 window.location.href = "${basePath}/apply/apply-scene?id="+$("#applyId").val();
	                	 setTimeout(function(){
	                		 parent.layer.close(parent.layer.getFrameIndex(window.name)); //再执行关闭 
                		 }, 2000);
					 }else{
						 layer.msg(res.message); 
					 }
				  })
			  }
			  $("#add-scene").on('click',function(){
				  var url ="${basePath}/apply/apply-add-scene?id="+$("#applyId").val();
		            layer.open({
		            	title:'增加场景', 
		                type: 2,
		                area: ['920px', '500px'],
		                offset: ['120px', '450px'],
		                fixed: false, //不固定
		                scrollbar: true,
		                maxmin: true,
		                content: [url]
		           })
			  })
			  
			
			})
		</script>
	</body>
</html>