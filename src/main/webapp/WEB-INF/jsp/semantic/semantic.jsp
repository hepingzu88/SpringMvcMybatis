<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/common.jsp" %>
<html>
	<head>
    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
		<script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
	</head>
	<body>
		<div class="content-box">
			 <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
			    <legend>语义拆分List</legend>
			 </fieldset>
		      <input type="hidden" id="pageNum" name="pageNum"/>
		      <div style="margin-left: 80%">
			    <button class="layui-btn" id="add">增加语句</button>
			    <button class="layui-btn layui-btn-warm" id="refresh"><i class="layui-icon">&#x1002;</i></button>
			  </div>
			  <br>
			  <div id="form_content">
		        	
		      </div>
		</div>
		<script type="text/javascript">
			layui.use(['table','jquery','element','form','layer'], function(){
			  var table = layui.table,
			  $ = layui.$,
			  element = layui.element,
			  form = layui.form,
			  layer = layui.layer;
		     
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
			  var text = "";
			  $("input").on('select',function(){
				 var $div = $(this).parent().parent().parent().parent().parent();
				 $tr = $(this).parent().parent();
				 id = $(this).attr("id");//text
				 text = getSelectText(id);
				 $.each(text, function(i, n){
				  if(i == 2){
					  $div.find("#add-text").text("Add text "+ "'"+n+"'");
				  }
				 });
				 $div.find("#add-text").show();
				 $div.find("#save").show();
			})
			
			//选中的字体增加
			 $('button').on('click',function(){
				 if($(this).attr('id') == 'add-text'){
					 var text = getSelectText(id);
					 $.each(text, function(i, n){
						 var index = [];
						  if(i < 2){
							  index.push(n);
						  }
						  var str = "";
						  $.each(index, function(i, n){
							  str += n+",";
						  });
						  var ind = str.substr(0,str.length-1);//除去最后一个","
						  if(i == 2){
							text.pop();
							$tr.after("<tr><td><input type='text' style='display:none;' name='password' value='"+text+"' lay-verify='required' placeholder='text' autocomplete='off' class='layui-input'><input type='text' name='password' value='' lay-verify='required' placeholder='text' autocomplete='off' class='layui-input'></td><td><input type='text' id='text' value="+n+" lay-verify='required' placeholder='text' autocomplete='off' class='layui-input'></td><td>"+n+"</td><td><button class='layui-btn layui-btn-small layui-btn-danger' type='button' id='del'><i class='layui-icon'>&#xe640;</i></button></td></tr>")
						  }
					 });
				 }
			 })
			 
			//分解后的文本进行保存
			 $("button").on('click',function(){
				 if($(this).attr('id') == 'save' ){
					 var $div = $(this).parent();
					 add($div,$(this));
				 }
				 if($(this).attr("id") == 'remove'){
					 var id = $(this).parent().parent().attr('id');
					 remove(id);
				 }
			 })
			 
			 //
			 function add(action,obj){
				  var str = [];
				  action.find("input").each(function(index, element) {
					str += $(this).val()+","
				  });
				 str = str.substr(0,str.length-1);//除去最后一个","
				 $.post('${basePath}/semantic/update',{"data":str,"id":obj.attr('sid')},function(res){
					if(res == "true"){
						layer.msg("操作成功");
					}else{
						layer.msg("操作失败");
					}
				 })
			  }
			 
			 //删除
			 $("button").on('click',function(){
				if($(this).attr('id') == 'del'){
					var $tr = $(this).parent().parent();
					var $div = $(this).parent().parent().parent().parent().parent();
					var $button = $div.children().last();
					console.log($button);
				 	$tr.empty();
				 	add($div,$button);
				}
			 })
			 
			 //delte删除大分类
			 function remove(id){
			 	var url ="${basePath}/semantic/delete";
		        layer.confirm('确认删除', {
		            btn: ['删除','取消'],icon: 7, title:'注意' //按钮
		        }, function(index){
		            $.post(url,{"id":id},function (data) {
		                if(data=='true'){
		                    //var pageNum = $("#pageNum").val();
		                    // jump("${basePath}/admin/album/list",pageNum);
		                    layer.msg('删除成功', {icon: 2});
		                    layer.close(index);
		                }else{
		                    layer.msg('删除失败', {icon: 2});
		                }
		            })
		        });
			 }
			 
		    //获取鼠标选中的值
		    function getSelectText(id) {
	            var t = document.getElementById(id);
	          	console.log(t);
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
			
			$(function(){
				_returnData(1);
				$("#refresh").click(function () {
			        location.reload();
			    });
		    })	
		    
			function _returnData(pageNum) {
		        var url = "${basePath}/semantic/query";
		        jump(url,pageNum);
		    }	
		 	
		    function jump(url,pageNum) {
		     	if(pageNum!=null && pageNum!=''){
	    	        $("#pageNum").val(pageNum);
	    	    }
	    	     $.ajax({
			        url : url,
			        type: "POST",
			        data: {"pageNum":pageNum},
			        success: function(data){
			            $("#form_content").html(data);
			        }
			    })
			}
		</script>
	</body>
</html>