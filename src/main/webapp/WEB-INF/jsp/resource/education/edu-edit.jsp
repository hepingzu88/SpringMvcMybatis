<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/include/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
		<style type="text/css">
			.layui-form-item{
				width:98%;
			}
		</style>
	</head>
	<body>
		<br><br>
		<form class="layui-form layui-form-pane" style="margin-left: 10px;">
		  <input type="hidden" id="eduResId" value="${eduResId}">
		  <div class="layui-form-item"  id="sele">
		    <label class="layui-form-label">资源标签</label>
		    <div class="layui-input-block">
		      <select name="tagId" id="tagId" lay-filter="tag" lay-search lay-verify="required" multiple="multiple" class="downlist">
		        <c:forEach items="${listTag}" var="item">
		        	<option value="${item.id}" >${item.tagName}</option>
		        </c:forEach>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		      <label class="layui-form-label">年</label>
		      <div class="layui-input-block">
		        <input type="text" name="year" lay-verify="required"  class="layui-input" id="year" placeholder="2018">
		      </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">版本</label>
		    <div class="layui-input-block">
		      <select name="versionId" id="versionId" lay-filter="version" lay-search lay-verify="required" >
		        <c:forEach items="${listTbEduVersion}" var="item">
		        	<c:choose>
						<c:when test="${eduRes != null && eduRes.versionId == item.id}">
        					<option selected value="${item.id}" >${item.version}</option>
	        			</c:when>
	        			<c:otherwise> 
                			<option value="${item.id}" >${item.version}</option>
                		</c:otherwise>
	        		</c:choose>
		        </c:forEach>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">学册</label>
		    <div class="layui-input-block">
		      <select name="semesterId" id="semesterId" lay-filter="version" lay-search lay-verify="required" >
		        <c:forEach items="${listTbEduSemester}" var="item">
		        	<c:choose>
						<c:when test="${eduRes != null && eduRes.semesterId == item.id}">
        					<option selected value="${item.id}" >${item.semester}</option>
	        			</c:when>
	        			<c:otherwise> 
                			<option value="${item.id}" >${item.semester}</option>
                		</c:otherwise>
	        		</c:choose>
		        </c:forEach>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">年级</label>
		    <div class="layui-input-block">
		      <select name="gradeId" id="gradeId" lay-filter="version" lay-search lay-verify="required" >
		        <c:forEach items="${listGrade}" var="item">
		        	<c:choose>
						<c:when test="${eduRes != null && eduRes.gradeId == item.id}">
        					<option selected value="${item.id}">${item.grade}</option>
	        			</c:when>
	        			<c:otherwise> 
                			<option value="${item.id}">${item.grade}</option>
                		</c:otherwise>
	        		</c:choose>
		        </c:forEach>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">年龄段</label>
		    <div class="layui-input-block">
		      <select name="ageId" id="ageId" lay-filter="age" lay-search lay-verify="required" >
		        <c:forEach items="${listAge}" var="item">
		        	<c:choose>
						<c:when test="${eduRes != null && eduRes.ageId == item.id}">
        					<option selected value="${item.id}" >${item.age}</option>
	        			</c:when>
	        			<c:otherwise> 
                			<option value="${item.id}" >${item.age}</option>
                		</c:otherwise>
	        		</c:choose>
		        </c:forEach>
		      </select>
		    </div>
		  </div>
		  <br><br>
		  <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left: 35%;">
                <button class="layui-btn" lay-submit="" lay-filter="from" type="button">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
         </div>
		</form>	
	</body>
	<script type="text/javascript">
		layui.use(['table','jquery','element','form','layer','laydate'], function(){
		  var table = layui.table,
		  $ = layui.$,
		  laydate = layui.laydate,
		  element = layui.element,
		  form = layui.form,
		  layer = layui.layer;
		  form.render();
		  //年选择器
		  laydate.render({
		    elem: '#year'
		    ,type: 'year'
		  });
		   form.on('submit(from)',function(data){
			   var text = $("input:checkbox[name='arr']:checked").map(function(index,elem) {
		            return $(elem).val();
		         }).get().join(',');
			     if($("input[type='checkbox']").is(':checked') == false){
					text = null;
				 };
			   $.post("${basePath}/edu/edit",{"id":$("#eduResId").val(),
						  				    "ageId":$("#ageId").val(),
				   							"versionId":$("#versionId").val(),
				   							"semesterId":$("#semesterId").val(),
				   							"gradeId":$("#gradeId").val(),
				   							"year":$("#year").val(),
				   							"tagArr":text
				 },function (res) {
	    		   if(res.isSuccess){
	    			   layer.msg(res.message);
	    		   }else{
	    			   layer.msg(res.message); 
	    		   }
	            }) 
	       })
	       
	       //查询当前歌曲的所有标签
			/* $.post('${basePath}/music/queryMusicResTag',{"id":$("#musicResId").val()},function(res){
				console.log(res);
				var roleArr = [];
				$.each(res, function(index,item){ 
					roleArr.push(item.id);
			    })
				if(roleArr != null){
				　addClass(roleArr);
				}
			}); */
		  
		 	/*  function addClass(data) {
				$.each(data, function(i, n){
					$("select.downlist").find("option").each(function(index,elem) {
						 if(elem.value == n){
							 console.log(elem)//layui-unselect layui-form-checkbox layui-form-checked
							var $this=$(this);
							console.log($($this).find("dl"));
							$(this).find("div").attr({"class":"layui-form-checked"});
							 form.render("checkbox");
						 }
				    })
				});
			} */
			$(function(){
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
		   	});
			
	       $(function(){
			   	//遍历渲染
			   	Array.prototype.contains = function ( needle ) {
				  for (i in this) {
				    if (this[i] == needle) return true;
				  }
				  return false;
				}
			   $.post('${basePath}/edu/queryEduResTag',{"eduResId":$("#eduResId").val()},function(res){
					var roleArr = [];
					console.log(res);
					$.each(res, function(index,item){ 
						console.log(item.tagId);
						roleArr.push(item.tagId);
				    })
					if(roleArr != null){
						$("select.downlist").each(function(index,item){
					   	  	var $this=$(this);
					   		var $select=$this.next(".layui-form-select");
					   		$select.addClass("downpanel");
					   		var $dl=$select.find("dl");
					   		$(".layui-select-title input",$select).val($this.attr("placeholder"));
					   		$dl.empty();
					   		var str="";
					   		var html = [];
					   		$("option",$this).each(function(i,n) {
					   			if(roleArr.contains($(this).val())){
					   				str=["<dd>","<input type='checkbox' name='arr' checked='checked' lay-skin='primary' title='",$(this).text(),"' value='",$(this).val(),"'>","</dd>"].join("");
								}else{
									str=["<dd>","<input type='checkbox' name='arr' lay-skin='primary' title='",$(this).text(),"' value='",$(this).val(),"'>","</dd>"].join("");
								}
					   			$dl.append(str);
					   		});
					   		form.render("checkbox"); 
					   	});
					}else{
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
					}
			   });
			   	$(".downpanel").on("click",".layui-select-title",function(e){
			   		var $select=$(this).parents(".layui-form-select");
			   		$(".layui-form-select").not($select).removeClass("layui-form-selected");
			   		$select.addClass("layui-form-selected");
			   		e.stopPropagation();
			   	}).on("click",".layui-form-checkbox",function(e){
			   		e.stopPropagation();
			   	});
		   	});
			
		})
	
	</script>
</html>