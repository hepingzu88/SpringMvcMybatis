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
		  <input type="hidden" id="storyResId" value="${storyResId}">
		  <%-- <span>${cheshi}</span> --%>
		  <div class="layui-form-item"  id="sele">
		    <label class="layui-form-label">资源标签</label>
		    <div class="layui-input-block">
		      <select name="tagId" id="tagId" lay-filter="tag"  lay-verify="required" multiple="multiple" class="downlist">
		        <c:forEach items="${listTag}" var="item">
		        	<option value="${item.id}" >${item.tagName}</option>
		        </c:forEach>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">年龄段</label>
		    <div class="layui-input-block">
		      <select name="albumId" id="albumId" lay-filter="album" lay-search lay-verify="required" >
		        <c:forEach items="${listAlbum}" var="item">
		        	<c:choose>
						<c:when test="${storyRes != null && storyRes.albumId == item.id}">
        					<option selected value="${item.id}" >${item.album}</option>
	        			</c:when>
	        			<c:otherwise> 
                			<option value="${item.id}" >${item.album}</option>
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
						<c:when test="${storyRes != null && storyRes.ageId == item.id}">
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
		layui.use(['table','jquery','element','form','layer'], function(){
		  var table = layui.table,
		  $ = layui.$,
		  element = layui.element,
		  form = layui.form,
		  layer = layui.layer;
		  form.render();
		  
		   form.on('submit(from)',function(data){
			   var text = $("input:checkbox[name='arr']:checked").map(function(index,elem) {
		            return $(elem).val();
		         }).get().join(',');
			     if($("input[type='checkbox']").is(':checked') == false){
					text = null;
				 };
				 console.log(text);
	    	    $.post("${basePath}/story/edit",{"ageId":$("#ageId").val(),
	    	    	"tagArr":text,
	    	    	"id":$("#storyResId").val(),
	    	    	"albumId":$("#albumId").val()
	    	    	},function (res) {
	    	   
	    		   if(res.isSuccess){
	    			   layer.msg(res.message);
	    		   }else{
	    			   layer.msg(res.message); 
	    		   }
	            })
	       })
	     
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
			   $.post('${basePath}/story/queryStoryResTag',{"storyResId":$("#storyResId").val()},function(res){
					var roleArr = [];
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