<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
		<script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
		<script src="${basePath}/js/page.js"></script>
	</head>
	<body>
		<div class="layui-tab layui-tab-card" style="height: 750px;">
			<c:forEach items="${list}" var="bean">
	          	<span id="id" style="display: none">${bean.id}</span>
	          	<span id="operatorId" style="display: none">${bean.operatorId}</span>
	        </c:forEach>
			<form class="layui-form" action="" >
		        <c:if test="${list!=null}">
			   		<div class="layui-form">
					  <table class="layui-table">
					  	<colgroup>
					        <col width="150">
					        <col width="150">
					        <col width="200">
					        <col width="150">
					    </colgroup>
					    <thead>
					      <tr>
					      	<th>问题</th>
					        <th>回答</th>
					        <th>操作</th>
					      </tr> 
					    </thead>
					    <tbody>
					     <c:forEach items="${list}" var="bean">
					      <tr>
					        <td>
					        	<input name="id" value="${bean.id}" style="display: none" id="semanticId">
					        	<input name="question" id="question" value="${bean.text}"  lay-verify="required" placeholder="自定义问题"  class="layui-input" type="text">
					        </td>
					        <td>
					        	<input name="answer" id="answer" lay-verify="required" value="${bean.answer}" placeholder="自定义回答" class="layui-input" type="text">
					        </td>
					        <td>
               					<button class="layui-btn layui-btn-small layui-btn-danger" type="button" id='remove' onclick="remove(${bean.id})">
                    					<i class="layui-icon">&#xe640;</i>
               					</button>
               					 <button class="layui-btn layui-btn-small" type="button" id="split" onclick="edit(${bean.id})">
			                        <i class="layui-icon">&#xe642;</i>
			                    </button>
					        </td>
					      </tr>
					      </c:forEach>
					    </tbody>
					  </table>
					  <button class="layui-btn layui-btn-small" type="button" id="add-tr">
	           			 新增同义句
	      			  </button>
	      			  <button class="layui-btn layui-btn-small"  id="save" style="margin-left:76%;width: 84px;" lay-submit="" lay-filter="from" type="button">
			          	Save
			          </button>
				  </div>
			   </c:if>
		    </form>
		 </div>
		
	   <script type="text/javascript">
	   	function remove(id){
	   		var url ="${basePath}/semantic/delete";
	        layer.confirm('确认删除', {
	            btn: ['删除','取消'],icon: 7, title:'注意' //按钮
	        }, function(index){
	            $.post(url,{"id":id},function (data) {
	                if(data=='true'){
	                    layer.msg('删除成功', {icon: 2});
	                    //var pageNum = $("#pageNum").val();
	                    //jump("${basePath}/semantic/find",pageNum);
	                    setTimeout(function(){
	                		// parent.layer.close(parent.layer.getFrameIndex(window.name)); //再执行关闭 
	                		 window.location.href = "${basePath}/answer/custom/custom-answer-add?id="+$("#semanticId").val();
               		 }, 2000);
	                }else{
	                    layer.msg('删除失败', {icon: 2});
	                }
	            })
	        });
     	}
	   
	   	function edit(id){
	   		if(typeof(id) == 'undefined'){
	   			layer.msg('请选保存同义句');
	   			return;
	   		}
	        var url ="${basePath}/semantic/edit?id="+id;
	        layer.open({
	            type: 2,
	            area: ['900px', '600px'],
	            offset: ['100px', '250px'],
	            fixed: false, //不固定
	            maxmin: true,
	            content: [url,"no"]
	        })
	   	 }
	   
	   	layui.use(['form','layer'], function(){
	        var form = layui.form,
	        layer = layui.layer;
	      	//自定义验证规则
	        form.verify({
	        	intent: [/[A-Za-z]$/, '必填项，且只能为字母']
	        });
	      	
	       $("#add-tr").on('click',function(){
	    	   var $tr = $("tr").last();
	    	   var html = [];
				 html += "<tr><td><input type='text' style='display:none;' name='id' value='0' class='layui-input'>",
				 html += "<input name='question' id='question' value=''  lay-verify='required' placeholder='自定义问题'  class='layui-input' type='text'>"
			 	 html += "<td><input name='answer' id='answer' lay-verify='required' value='' placeholder='自定义回答' class='layui-input' type='text'></td>",
				 html +="<td><button class='layui-btn layui-btn-small layui-btn-danger' type='button' id='remove' onclick='remove()'><i class='layui-icon'>&#xe640;</i></button>",
			 	 html += "<button class='layui-btn layui-btn-small' type='button' onclick='edit()'><i class='layui-icon'>&#xe642;</i></button></td></tr>"
				 $tr.after(html);
				 form.render();
	       })
	      	
	        //监听提交
	        $('form').on('click','#save', function(data){
	        	var arr = [];
	        	$("input").each(function(i, n){
        		  arr.push(n.value);
        		});
	            $.post("${basePath}/answer/custom/custom-answer-add",
	            		{"arr":arr.join(","),
	            		"id":$("#id").text(),
	            		"operatorId":$("#operatorId").text()},
	            function (res) {
	                if(res.isSuccess){
	                	 layer.msg(res.message);
	                	 setTimeout(function(){
	                		 parent.layer.close(parent.layer.getFrameIndex(window.name)); //再执行关闭 
	                		 window.location.href = "${basePath}/answer/custom/custom-answer-add?id="+$("#semanticId").val();
                		 }, 2000);
	                }else{
	                	layer.msg(res.message);
	                }
	            })
	        });
	    });
	   </script>
	</body>
</html>