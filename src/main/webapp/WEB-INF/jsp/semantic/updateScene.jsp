<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/include/common.jsp" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Title</title>
	    <link rel="stylesheet" href="${basePath}/css/sccl.css">
	    <script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
		<style type="text/css">
			 .layui-input-block{
				width: 75%;
			}
			.layui-table{
				margin-left: 40px;
				width: 93%;
			}
		</style>
	</head>
	<body>
		<form class="layui-form" action="">
			<blockquote class="layui-elem-quote layui-text">
        		注意：更改场景后，意图，实体都会根据选择的场景改变，不同场景有不同意图与实体
			</blockquote>
			<div class="layui-form-item">
			    <div class="layui-inline">
			    <label class="layui-form-label">场景类型</label>
			     <div class="layui-input-inline">
			      <select name="sceneId" id="sceneId" lay-verify="required" lay-filter="sceneId" lay-search>
			      	<c:forEach items="${listScene}" var="item">
						<c:choose>
  							<c:when test="${semantic.sceneId != null && item.id == semantic.sceneId }">
		        				<option selected value="${item.id}">${item.scene}</option>
		        			</c:when>
		        			<c:otherwise> 
	                			<option value="${item.id}">${item.scene}</option>
	                		</c:otherwise>
		        		</c:choose>
              		</c:forEach>
			      </select>
			      </div>
			    </div>
	            <div class="layui-inline">
	             <label class="layui-form-label">意图</label>
	             	<div class="layui-input-inline">
	                <select name="intentId" id="intentId" lay-filter="intentId" lay-search>
	                	 <option value=""></option>
	                	 <c:forEach items="${intentList}" var="bean">
							<c:choose>
   								<c:when test="${(semantic.intentId != null && semantic.intentId != '') && (semantic.intentId == bean.intentId)}">
			        				<option selected value="${bean.intentId}">${bean.intent}</option>
			        			</c:when>
			        			<c:otherwise> 
		                			<option  value="${bean.intentId}">${bean.intent}</option>
		                		</c:otherwise>
			        		</c:choose>
		                </c:forEach>
	                </select>
	                </div>
	            </div>
	            <div class="layui-inline">
	            	<label class="layui-form-label">情感状态</label>
	           		 <div class="layui-input-inline">
	                <select id="sentiment" name="sentiment" lay-filter="aihao"  lay-search>
	                	<option value=""></option>
		                <c:forEach items="${affList}" var="bean">
							<c:choose>
   								<c:when test="${semantic.sentiment != null && semantic.sentiment == bean.affectiveName}">
			        				<option selected value="${bean.affectiveName}">${bean.affectiveName}</option>
			        			</c:when>
			        			<c:otherwise> 
		                			<option value="${bean.affectiveName}">${bean.affectiveName}</option>
		                		</c:otherwise>
			        		</c:choose>
		                </c:forEach>
	                </select>
	                </div>
	            </div>
	            <div class="layui-inline">
	             <label class="layui-form-label">适用年龄段</label>
	             <div class="layui-input-inline">
	                <select id="applyAge" name="applyAge" lay-filter="aihao" lay-search>
		                <option value=""></option>
		                <c:forEach items="${ageBandList}" var="bean">
							<c:choose>
   								<c:when test="${semantic.applyAge != null && semantic.applyAge == bean.ageBand}">
   									<option selected value="${bean.ageBand}">${bean.ageBand}</option>
			        			</c:when>
			        			<c:otherwise> 
		                			<option value="${bean.ageBand}">${bean.ageBand}</option>
		                		</c:otherwise>
			        		</c:choose>
		                </c:forEach>
	                </select>
	            </div>
	            </div>
	        </div>
	    		<c:if test="${semantic!=null}">
		   		<div>
				  <table class="layui-table">
				  	<colgroup>
				        <col width="85">
				        <col width="250">
				        <col width="50">
				    </colgroup>
				    <thead>
				      <tr>
				      	<th>service</th>
				        <th>text</th>
				        <th>操作</th>
				      </tr> 
				    </thead>
				    <tbody>
				      <span style="display: none" id="semanticId" >${semantic.id}</span>
				      <span style="display: none" id="semanticText" >${semantic.text}</span>
				      <tr>
				        <td>
				        	<select name="sceneEnglish" id="sceneEnglish" lay-search>
		                		<c:forEach items="${listScene}" var="item">
									<c:choose>
		   								<c:when test="${semantic.sceneId != null && item.id == semantic.sceneId }">
					        				<option disabled selected value="${item.sceneEnglish}">${item.sceneEnglish}</option>
					        			</c:when>
					        			<c:otherwise> 
				                			<option value="${item.sceneEnglish}">${item.sceneEnglish}</option>
				                		</c:otherwise>
					        		</c:choose>
		                		</c:forEach>
				        	</select>
				        </td>
				        <td>
				        	<input type="text" id="${semantic.text}" value="${semantic.text}" lay-verify="required" placeholder="text" class="layui-input id">
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
						        	<select name="entity" id="entity" lay-search lay-verify="required">
										<c:forEach items="${entity}" var="item" >
											<c:choose>
												<c:when test="${(vo.entity != null && vo.entity != '') && (vo.entity == item.entityEnglish)}">
									        		<option value="${item.entityEnglish}">${item.entityEnglish}</option>
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
				  <button class="layui-btn layui-btn-small" type="button" id="add-text" style="display: none;margin-left: 3%;">
           			Add Text
      			  </button>
		          <button class="layui-btn layui-btn-small" id="save" style="margin-left:78%;width: 84px;" lay-submit="" lay-filter="from" type="button">
		          	保存
		          </button>
				</div>
			 </c:if>
	     	<br><br>
		  <!-- <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 45%;">
                <button class="layui-btn" lay-submit="" lay-filter="from" type="button">立即提交</button>
            </div>
         </div> -->
         <br><br>
		</form>
	</body>
	<script type="text/javascript">
		layui.use(['layer','form','jquery'],function(){
			var layer = layui.layer,
			$ = layui.$,
			form = layui.form;
			form.render();
			
			 //选择场景后触发,查找指定场景下的意图
			 form.on('select(sceneId)',function(data){
	    	    $.post("${basePath}/thesaurus/intent/findSceneByIdIntent",{"sceneId":data.value},function (res) {
	    	      var html = [];
	              $.each(res.intentList, function(i, n){
	            	  html.push("<option value="+n.id+">"+n.intent+"</option>");
	              });
	              var select = $("#intentId");
	              select.html(html);
	              
	              var scene = [];//场景
	              var sceneSelect = $("#sceneEnglish");
	              scene.push("<option disabled selected value="+res.scList[0].sceneEnglish+">"+res.scList[0].sceneEnglish+"</option>");
	              sceneSelect.html(scene);
	              
	              //如果修改场景则动态显示该场景下的实体
	              var entity = [];
	              $.each(res.entityList, function(i, n){
	            	  entity.push("<option value="+n.entityEnglish+">"+n.entityEnglish+"</option>");
	              });
	              $("tr").find('select').each(function(i, n){
	            	  if(i > 0){
	            		  $(this).html(entity);
	            	  }
	              });
	              form.render();
	            })
	        })
	        
			 //鼠标选中字体触发     显示要标注的字体
			  var text = "";
			  $("input").on('select',function(){
				 id = $(this).attr("id");//text 获取被标注的整条语料
				 text = getSelectText(id);
				 $.each(text, function(i, n){
				  if(i == 2){
					  $("#add-text").text("Add text "+ "'"+n+"'");
				  }
				 });
				 $("#add-text").show();
			})
			
			//标注的字体    
			 $('button').on('click',function(){//点击增加标注字体事触发
				 if($(this).attr('id') == 'add-text'){
			 		$.post('${basePath}/semantic/findEntity',{"sceneId":$("#sceneId").val()},function(res){//根据场景Id查询该场景下的所有实体
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
						 $tr = $('tr').last();//每次追加在最后一行
						 $tr.after(html);
						 form.render();
			 		})
				 }
			 })
			 
			 $('div').on('click','button',function(){
				 if($(this).attr('id') == 'del'){//如果id的值是true 则证明没有保存，直接在页面删除点击的行即可
					 if($(this).attr('value') == 'true'){
						var $tr = $(this).parent().parent();//找到当前点击的那行tr
						$tr.empty();
						return;
					}
					var $tr = $(this).parent().parent();//找到当前点击的那行tr
				 	$tr.empty();//删除行
				 	compile();
				}
			 })
			 
			 
			//监听提交  layui-form-selected
			form.on('submit(from)', function(data){
				var bool = true;
				$("tr").find('select').each(function(i, n){
            	  if(i > 0){
            		  if($("#entity").val() == null || $("#entity").val() == '' || typeof($("#entity").val()) == 'undefined'){
      					console.log($("#entity").val());
      					layer.msg("实体不能为空");
      					bool = false;
      				}
            	  }
                });
				if(bool){
					compile();
				}
			});
			
			 //
			 function compile(){
				  var str = [];
				  $("table").find("input").each(function(index, element) {
					str.push($(this).val()+",")
				  });
				  if(str.length > 4){
					  str = str.join("");
					  str = str.substr(0,str.length-1);//除去最后一个","
				  }else{
					  str = null
				  }
				  var json = {};
				  var entities = [];
				  
				  //new 修改
				  if(($("#intentId").val() == "" || $("#intentId").val() == null) && ($("#sceneId").val() != null && $("#sceneId").val() != "")){
		        		$.post("${basePath}/thesaurus/findSceneById",{"id":$("#sceneId").val()},function (res) {
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
							     json.intent = "";
								  var text = $("#semanticText").text();
								  json.text = text;
							  }
							  json.entities = entities;
							  post(json);
		        		})
		        		return;
		        	}
				  
				  
				  $.post("${basePath}/thesaurus/intent/selectSceneAndIntent",{"id":$("#sceneId").val(),"intentId":$('#intentId').val()},function (res) {
					  if(str != null){
						 var arr  = str.split(",");
					     json.service = res[0].sceneEnglish;
					     json.intent = res[0].intentEnglish;
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
				 
				   function post(json){
					  console.log(JSON.stringify(json));
					  var semantic = {
						 "semantic":JSON.stringify(json),
						 "id":$("#semanticId").text(),
						 "sceneId":$("#sceneId").val(),
						 "intentId":$('#intentId').val(),
						 "applyAge":$("#applyAge").val(),
						 "sentiment":$("#sentiment").val()
					  }

					  $.post("${basePath}/semantic/updateScene",semantic,function(res){
						   if(res.isSuccess){
							   layer.msg("成功");
							   $("#add-text").hide();
							   /* setTimeout(function(){
			                		//parent.layer.close(parent.layer.getFrameIndex(window.name)); //再执行关闭 
			                		//location.reload();
		             		   }, 2000); */
						   }else{
							   layer.msg("失败"); 
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
	                	console.log(date);
	                    return date;
	                } else {
	                    return "";
	                }
	            } else {
	                return document.selection.createRange().text;
	            }
	        }
			
		})
	
	</script>
</html>