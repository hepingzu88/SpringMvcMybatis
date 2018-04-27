<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@include file="/include/common.jsp" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
		<script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
		<style type="text/css">
			.layui-form-item{
				width: 50%;
			}
		</style>
	</head>
	<body>
		<div class="layui-tab layui-tab-card" style="height: 805px;">
			<%-- <br>
			<button type="button" class="layui-btn layui-btn-primary" style="margin-left: 85%;" id="test3"><i class="layui-icon"></i>批量追加</button>
			<a href="${basePath}/common/Q&A.xlsx"><button type="button" class="layui-btn layui-btn-primary"  id="template">模板</button></a>
			--%><br><br> 
			<form class="layui-form layui-form-pane" action="" style="margin-left: 40px;">
		        <div class="layui-form-item" >
		            <label class="layui-form-label">语料</label>
		            <div class="layui-input-block">
		                <input name="text" id="text" lay-verify="required" placeholder="text" autocomplete="off" class="layui-input" type="text">
		            </div>
		        </div>
		        
		        <div class="layui-form-item" >
		            <label class="layui-form-label">回复</label>
		            <div class="layui-input-block">
		                <input name="answer" id="answer"  placeholder="text" autocomplete="off" class="layui-input" type="text">
		            </div>
		        </div>
		        
		         <div class="layui-form-item">
		         <label class="layui-form-label">场景</label>
		            <div class="layui-input-block">
		                <select id="sceneId" name="sceneId" lay-filter="sceneId" lay-verify="required">
			                <option value=""></option>
			                <c:forEach items="${list}" var="bean">
			                	<option value="${bean.id}">${bean.scene}</option>
			                </c:forEach>
		                </select>
		            </div>
		        </div>
	        	
		        <div class="layui-form-item">
		         <label class="layui-form-label">意图</label>
		            <div class="layui-input-block">
		                <select id="intentId" name="intentId" lay-filter="aihao">
			                <option value=""></option>
			                
		                </select>
		            </div>
		        </div>
		        <div class="layui-form-item">
		         <label class="layui-form-label">情感状态</label>
		            <div class="layui-input-block">
		                <select id="sentiment" name="sentiment" lay-filter="aihao">
			                <option value=""></option>
							<c:forEach items="${affList}" var="bean">
			                	<option value="${bean.affectiveName}">${bean.affectiveName}</option>
			                </c:forEach>
		                </select>
		            </div>
		        </div>
		         <div class="layui-form-item">
		         	<label class="layui-form-label">适用年龄段</label>
		            <div class="layui-input-block">
		                <select id="applyAge" name="applyAge" lay-filter="aihao">
		                	<option value=""></option>
		                	<option value="全年龄段">全年龄段</option>
			                <option value="儿童">儿童</option>
			                <option value="成人">成人</option>
		                </select>
		            </div>
		        </div>
		        <div class="layui-form-item">
		            <div class="layui-input-block">
		                <button class="layui-btn" lay-submit="" lay-filter="from" type="button">立即提交</button>
		                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		            </div>
		        </div>
	     	</form>
	   </div>
	   <script type="text/javascript">
	   	layui.use(['form','upload'], function(){
	        var form = layui.form,
	        upload = layui.upload;
	        form.render();
	      	//自定义验证规则
	        form.verify({
	        	intent: [/[A-Za-z]$/, '必填项，且只能为字母']
	        });
	      	
	       //指定允许上传的文件类型
	        upload.render({
	          elem: '#test3'
	          ,url: '${basePath}/semantic/upload'
	          ,accept: 'file' //普通文件
	          ,choose:function(){
	        	  layer.load();
	          }
	          ,done: function(res){
	        	  if(res.isSuccess){ //上传成功
	        		//layer.closeAll('loading');
	  		    	layer.msg(res.message)
	        	  }else{
	      		    layer.msg(res.message) 
	        	  }
	          }
	        });
	       
	       form.on('select(sceneId)',function(data){
	    	   $.post("${basePath}/thesaurus/intent/findSceneByIdIntent",{"sceneId":$("#sceneId").val(),"id":$("#intentId").val()},function (res) {
	              var html = [];
	              html.push("<option value=''></option>")
	              $.each(res.intentList, function(i, n){
	            	  html.push("<option value="+n.id+">"+n.intent+"</option>");
	              });
	              var select = $("#intentId");
	              select.html(html);
	              form.render();
	            }) 
	        })
	      	
	        //监听提交
	        form.on('submit(from)', function(data){
	        	
	        	if(($("#intentId").val() == "" || $("#intentId").val() == null) && ($("#sceneId").val() != null && $("#sceneId").val() != "")){
	        		$.post("${basePath}/thesaurus/findSceneById",{"id":$("#sceneId").val()},function (res) {
	        		  var json = {};
					  var entities = [];
					  //json.service_intent = res[0].sceneEnglish+"_"+res[0].intentEnglish;
					  json.service = res[0].sceneEnglish;
					  json.intent = "";
					  var text = $("#text").val();
					  json.text = text;
					  json.entities = entities;
					  var semantic = {
						 "semantic":JSON.stringify(json),
						 "text":$("#text").val(),
						 "answer":$("#answer").val(),
						 "sceneId":$("#sceneId").val(),
						 "intentId":$('#intentId').val(),
						 "applyAge":$("#applyAge").val(),
						 "sentiment":$("#sentiment").val()
					  };
					  post(semantic);
	        		})
	        		return;
	        	}
	        	
	        	$.post("${basePath}/thesaurus/intent/selectSceneAndIntent",{"id":$("#sceneId").val(),"intentId":$('#intentId').val()},function (res) {
					  console.log(res);
					  var json = {};
					  var entities = [];
					  //json.service_intent = res[0].sceneEnglish+"_"+res[0].intentEnglish;
					  json.service = res[0].sceneEnglish;
					  json.intent = res[0].intentEnglish;
					  var text = $("#text").val();
					  json.text = text;
					  json.entities = entities;
					  var semantic = {
						 "semantic":JSON.stringify(json),
						 "text":$("#text").val(),
						 "answer":$("#answer").val(),
						 "sceneId":$("#sceneId").val(),
						 "intentId":$('#intentId').val(),
						 "applyAge":$("#applyAge").val(),
						 "sentiment":$("#sentiment").val()
					  }
					  post(semantic);
		          })
	        });
	       
	       
	        function post(semantic){
	    	   $.post("${basePath}/semantic/addText",semantic,function (res) {
	               if(res=='true'){
	               	 layer.msg('增加成功');
	               	 setTimeout(function(){
	               		 parent.closeLayer(layer.index)
	           		 }, 2000);
	               }else{
	                   layer.msg("增加失败");
	               }
	           }) 
	        }
	       
	       
	    });
	   </script>
	</body>
</html>