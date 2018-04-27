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
	</head>
	<body>
		<form class="layui-form" action="" >
			<blockquote class="layui-elem-quote layui-text">
				同义句的场景与意图根据第一句的场景意图一致，如原句场景意图已改，则同义句一起更改，选中第一条语料(text)任意字体可进行标注,高度可自适应
			</blockquote>
			<c:if test="${info==null}">
			  	<hr>
			  	<span style="margin-left: 45%">暂无数据</span>
			  	<hr>
			</c:if>
			  <c:if test="${info!=null}">
		    	<span style="display: none" id="sceneId">${info.sceneId}</span>
		    	<span style="display: none" id="intentId">${info.intentId}</span>
		    	<span style="display: none" id="semanticText">${info.text}</span>
		    	<span style="display: none" id="semanticId">${info.id}</span>
		   		<div>
				  <table class="layui-table">
				  	<colgroup>
				        <col width="150">
				        <col width="200">
				        <col width="150">
				    </colgroup>
				    <thead>
				      <tr>
				      	<th>service</th>
				        <th>text</th>
				        <th>操作</th>
				      </tr> 
				    </thead>
				    <tbody>
				      <tr id="${info.id}">
				        <td>
				        	<select name="sceneEnglish" id="sceneEnglish" lay-search>
								<c:forEach items="${slist}" var="item">
									<c:choose>
		   								<c:when test="${(info.sceneId != null && info.sceneId != '') &&(item.id == info.sceneId)}">
					        				<option selected disabled value="${item.sceneEnglish}">${item.sceneEnglish}</option>
					        			</c:when>
					        			<c:otherwise> 
				                		<%-- 	<option value="${item.sceneEnglish}">${item.sceneEnglish}</option> --%>
				                		</c:otherwise>
					        		</c:choose>
		                		</c:forEach>
				        	</select>
				        </td>
				        <td>
				        	<input type="text" id="${info.text}" value="${info.text}" lay-verify="required" placeholder="text" class="layui-input">
				        </td>
				        <td>
          					<!-- <button class="layui-btn layui-btn-small layui-btn-danger" type="button" id='remove'>
               					<i class="layui-icon">&#xe640;</i>
          					</button> -->
				        </td>
				      </tr>
				      <c:if test="${info.list!=null}">
					      <c:forEach items="${info.list}" var="vo">
						      <tr>
						        <td>
						        	<input type="text" style="display:none;" id="start" value="${vo.start}"  class="layui-input">
						        	<input type="text" style="display:none;" id="end"  value="${vo.end}"  class="layui-input">
						        	<select name="entity" lay-search lay-verify="required">
						        		<c:forEach items="${entity}" var="item">
											<c:choose>
												<c:when test="${(vo.entity != null && vo.entity != '') && (vo.entity == item.entityEnglish)}">
									        		<option selected value="${item.entityEnglish}">${item.entityEnglish}</option>
								        		</c:when>
								        		<c:otherwise>
					                				<option value="${item.entityEnglish}">${item.entityEnglish}</option>
					                			</c:otherwise>
				                			</c:choose>
				                		</c:forEach>
						        	</select>
						        </td>
						        <td>
						        	<input type="text" value="${vo.value}" lay-verify="required" placeholder="text" class="layui-input">
						        </td>
						        <td>
						        	<!-- <button class="layui-btn layui-btn-small" type="button" onclick="edit()">
                        				<i class="layui-icon">&#xe642;</i>
                   					</button> -->
                   					<button class="layui-btn layui-btn-small layui-btn-danger" type="button" id="del">
                        				<i class="layui-icon">&#xe640;</i>
                   					</button>
						        </td>
						      </tr>
					      </c:forEach>
					    </c:if>
				    </tbody>
				  </table>
				  <br>
				  <button class="layui-btn layui-btn-small" type="button" id="add-text" style="display: none;margin-left: 2%;">
           				Add Text
      				  </button>
		          <button class="layui-btn layui-btn-small" id="save" style="width: 84px; margin-left: 78%;" lay-submit="" lay-filter="from" type="button">
		          	Save
		          </button>
		          <br><br><br><br>
				</div>
			 </c:if>
	    </form>
	    <br><br>
	    <script type="text/javascript">
		    layui.use(['table','jquery','element','form','layer'], function(){
				  var table = layui.table,
				  $ = layui.$,
				  element = layui.element,
				  form = layui.form,
				  layer = layui.layer;
				  form.render();
				  
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
				  
				  //鼠标选中字体触发
				  var text = "";
				  $("input").on('select',function(){
					/*  var $div = $(this).parent().parent().parent().parent().parent();
					 $tr = $(this).parent().parent(); */
					 id = $(this).attr("id");//text
					 text = getSelectText(id);
					 $.each(text, function(i, n){
					  if(i == 2){
						  $("#add-text").text("Add text "+ "'"+n+"'");
					  }
					 });
					 $("#add-text").show();
				})
				
				//选中的字体增加
				 $('button').on('click',function(){
					 if($(this).attr('id') == 'add-text'){
						 $("#save").show();
						 $.post('${basePath}/semantic/findEntity',{"sceneId":$("#sceneId").text()},function(res){
							var text = getSelectText(id);
							var elm = [];
							$.each(res, function(i, n){
					 			elm +="<option value="+n.entityEnglish+">"+n.entityEnglish+"</option>"
					 		});
							 var html = [];
							 html += "<tr><td><input type='text' style='display:none;' id='start' value='"+text[0]+"' autocomplete='off' class='layui-input'>",
							 html += "<input type='text' style='display:none;' id='end' value='"+text[1]+"' autocomplete='off' class='layui-input'>",
						 	 html += "<select name='entity' id='entity' lay-search lay-verify='required'>"
						 	 html += elm
						 	 html += "</select></td>"
						 	 html += "<td><input type='text' id='text' value="+text[2]+" lay-verify='required' placeholder='text' autocomplete='off' class='layui-input'></td>",
							 html +="<td><button class='layui-btn layui-btn-small layui-btn-danger' value='true' type='button' id='del'><i class='layui-icon'>&#xe640;</i></button></td></tr>"
							 $tr = $('tr').last();
							 $tr.after(html);
							 form.render();
						 })
					 }
				 })
				 
				 //监听提交
				  /*  form.on('submit(from)', function(data){
					var $div = $(this).parent();
					console.log($div.parent());
				  }); */
				 
				 
				//分解后的文本进行保存
				 $('div').on('click','button',function(){
					  if($(this).attr('id') == 'save' ){
						 var bool = true;
					     if($("#entity").val() == null || $("#entity").val() == '' || typeof($("#entity").val()) == 'undefined'){
	      					console.log($("#entity").val());
	      					layer.msg("实体不能为空");
	      					bool = false;
	      				 }
					     if(bool){
					    	 add();
					     }
					  }
					 if($(this).attr('id') == 'del'){
						 var $tr = $(this).parent().parent();
						if($(this).attr('value') == 'true'){
							$tr.empty();
							return;
						}
					 	$tr.empty();
					 	add();
					}
				 })
				 
				 //
				 function add(){
					  var str = [];
					  $("input").each(function(index, element) {
						str.push($(this).val()+",");
					  });
					  if(str.length > 4){
						  str = str.join("");
						  str = str.substr(0,str.length-1);//除去最后一个","sceneId
					  }else{
						  str = null
					  }
					  var json = {};
					  var entities = [];
					  //new 修改
					  if(($("#intentId").text() == "" || $("#intentId").text() == null) && ($("#sceneId").text() != null && $("#sceneId").text() != "")){
			        		$.post("${basePath}/thesaurus/findSceneById",{"id":$("#sceneId").text()},function (res) {
			        			if(str != null){
									 var arr  = str.split(",");
								     json.service = res[0].sceneEnglish;
								     json.intent = "";
								     console.log(res[0].sceneEnglish);
									 json.text = arr[1];
									 for(var i = 2; i < arr.length;i+=4){//json
										 var obj = {};
										 obj.start = arr[i];
										 obj.end = arr[i+1];
										 obj.entity = arr[i+2];
										 obj.value = arr[i+3];
										 entities.push(obj);
									 }
								  }else{
									 json.service = res[0].sceneEnglish;
								     json.intent = res[0].intentEnglish;
									  var text = $("#semanticText").text();
									  json.text = text;
								  }
								  json.entities = entities;
								  post(json);
			        		})
			        		return;
			        	}
					  
					  $.post("${basePath}/thesaurus/intent/selectSceneAndIntent",{"id":$("#sceneId").text(),"intentId":$('#intentId').text()},function (res) {
						  if(str != null){
							 var arr  = str.split(",");
							 //json.service_intent = res[0].sceneEnglish+":"+res[0].intentEnglish;
							 json.service = res[0].sceneEnglish;
					     	 json.intent = res[0].intentEnglish;
							 json.text = arr[1];
							 for(var i = 2; i < arr.length;i+=4){
								 var obj = {};
								 obj.start = arr[i];
								 obj.end = arr[i+1];
								 obj.entity = arr[i+2];
								 obj.value = arr[i+3];
								 entities.push(obj);
							 }
						  }else{
							 // json.service_intent = res[0].sceneEnglish+":"+res[0].intentEnglish;
							 json.service = res[0].sceneEnglish;
					     	 json.intent = res[0].intentEnglish;
							  var text = $("#semanticText").text();
							  json.text = text;
						  }
						  json.entities = entities;
						  post(json);
			           })
			           
			          function post(json){
						 var semantic = {
							 "semantic":JSON.stringify(json),
							 "id":$("#semanticId").text(),
						 }
						$.post('${basePath}/semantic/update',semantic,function(res){
							if(res == "true"){
								layer.msg("操作成功");
								 $("#add-text").hide();
								// $("#save").hide();
								/* setTimeout(function(){
		                		//parent.layer.close(parent.layer.getFrameIndex(window.name)); //再执行关闭 
		                		//location.reload();
	             		   		}, 2000); */
							}else{
								layer.msg("操作失败");
							}
						 })
					 }
				  }
				 
				 //delte删除大分类
				 function remove(id){
				 	var url ="${basePath}/semantic/deleteSemanticAndSimilarity";
			        layer.confirm('确认删除语料，以及语料的同义句', {
			            btn: ['删除','取消'],icon: 7, title:'注意' //按钮
			        }, function(index){
			            $.post(url,{"id":id},function (data) {
			                if(data.isSuccess){
			                	layer.msg(data.message);
			                	setTimeout(function(){
			                		 parent.closeLayer(layer.index)
		               		 	}, 2000);
			                }else{
			                	layer.msg(data.message);
			                }
			            })
			        });
				 }
				 
			    //获取鼠标选中的值
			    function getSelectText(id) {
		            var t = document.getElementById(id);
		            if (window.getSelection) {
		                if (t.selectionStart != undefined && t.selectionEnd != undefined) {
		                	var date = [];
		                	date.push(t.selectionStart);
		                	date.push(t.selectionEnd);
		                	date.push(t.value.substring(t.selectionStart, t.selectionEnd));
		                    return date;
		                } else {
		                    return "";
		                }
		            } else {
		                return document.selection.createRange().text;
		            }
		        }
			  });
	    </script>
	</body>
</html>