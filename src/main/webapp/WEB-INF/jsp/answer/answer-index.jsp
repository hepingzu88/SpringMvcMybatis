<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/common.jsp" %>
<html>
	<head>
    	<title>Title</title>
	    <link rel="stylesheet" href="${basePath}/css/sccl.css">
	    <script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
	    <script src="${basePath}/js/page.js"></script>
	</head>
	<body>
		<div class="content-box">
			 <span id="page" style="display: none">${page}</span>
			  <form class="layui-form" id="_form">
			  	 <div class="layui-form-item">
			        <input type="hidden" id="pageNum" name="pageNum"/>
			        <input type="hidden" id="id" 	  name="id" value="${id}"/>
		         </div>
				 <div id="form_content">
				 
			  	 </div>
		      </form>
		</div>
		<script type="text/javascript">
		
			$("div").on('click','#remove',function(){
				var button = $(this).context;
				var id = button.value;
				if(id == 'true'){
					$(this).parent().parent().empty();
		   		}else{
		   			remove(id);
		   		}
			})
		 
			function remove(id){
				//删除语料以及同义句
				if(id == $("#id").val()){
					var url ="${basePath}/semantic/deleteSemanticAndSimilarity";
					layer.confirm('确认删除语料，以及语料的同义句', {
						offset: ['60px', '500px'],								
			            btn: ['删除','取消'],icon: 7, title:'注意' //按钮
			        }, function(index){
			            $.post(url,{"id":id},function (data) {
			                if(data.isSuccess){
			                    layer.msg(data.message);
			                    setTimeout(function(){
			                    	//window.location.href = "${basePath}/answer/custom/custom-answer-index?id="+$("#semanticId").val();
			                    	//location.reload();
			                    	 window.location.href = "${basePath}/semantic/semanticIndex?page="+$("#page").text();
		               		 	}, 2000);
			                }else{
			                	layer.msg(data.message);
			                }
			            })
			        });
				}else{//删除同义句
					var url ="${basePath}/semantic/delete";
					layer.confirm('确认删除', {
						offset: ['60px', '500px'],						
			            btn: ['删除','取消'],icon: 7, title:'注意' //按钮
			        }, function(index){
			            $.post(url,{"id":id},function (data) {
			                if(data.isSuccess){
			                    layer.msg(data.message);
			                    setTimeout(function(){
			                    	$(".layui-laypage-skip").find(".layui-laypage-btn").click();
		               		 	}, 2000);
			                }else{
			                	layer.msg(data.message);
			                }
			            })
			        });
				}
	     	}
		   
		   	function edit(id,intentId){
		   		if(typeof(id) == 'undefined'){
		   			layer.confirm('请选保存同义句', {icon: 3, title:'提示',offset: ['60px', '500px']}, function(index){
		   				layer.close(index);
		   			});
		   			return;
		   		}
		   		if(typeof(intentId) == 'undefined' || intentId == null || intentId == ''){
		   			layer.confirm('该语料暂时没有意图，请先为语料编辑意图', {icon: 3, title:'提示',offset: ['60px', '500px']}, function(index){
		   				layer.close(index);
		   			});
		   			return;
		   		}
		        var url ="${basePath}/semantic/edit?id="+id;
		        layer.open({
		            type: 2,
		            area: ['1000px', '420px'],
		            offset: ['20px', '160px'],
		            fixed: false, //不固定
		            maxmin: true,
		            content: [url],
		            btn: ['上一条','下一条'],
		            yes: function(index, layero){//上一条
		            	var arr= [];
		            	$("input[name*='semanticId']").each(function(i,n){
	              			arr.push($(n).val());
	                	}) 
	                	arr = arr.reverse();
	                	for(var i = 0 ; i <= arr.length;i++){
	                		layer.close(i+1);
							if(Number(arr[i]) < Number(id)){
								edit(arr[i],intentId);
								break;
	                		}
	                	}
	                	layer.close(index);
	              	},btn2:function(index){
	              		var arr= [];
	              		$("input[name*='semanticId']").each(function(i,n){
	              			arr.push($(n).val());
	                	}) 
	                	for(var i = 0 ; i < arr.length;i++){
	                		layer.close(i+1);
							if(Number(arr[i]) > Number(id)){
								edit(arr[i],intentId);
	                			break;
	                		}
	                	}
	                	layer.close(index);
	              	}
		        })
		   	 }
		   
		   	layui.use(['form','layer'], function(){
		        var form = layui.form,
		        layer = layui.layer;
		      	//自定义验证规则
		        form.verify({
		        	intent: [/[A-Za-z]$/, '必填项，且只能为字母']
		        });
		      	
		       $("form").on('click','#add-tr',function(){
		    	   var $tr = $("tr").last();
		    	   var html = [];
					 html += "<tr><td><input type='text' style='display:none;' name='id' value='0' class='layui-input'>",
					 html += "<input name='question' id='question' value='' placeholder='自定义问题'  class='layui-input' type='text'>"
				 	 html += "<td><input name='answer' id='answer' value='' placeholder='自定义回答' class='layui-input' type='text'></td>",
					 html +="<td><button class='layui-btn layui-btn-small layui-btn-danger' type='button' id='remove' value='true'><i class='layui-icon'>&#xe640;</i></button>",
				 	 html += "<button class='layui-btn layui-btn-small' type='button' onclick='edit()'><i class='layui-icon'>&#xe642;</i></button></td></tr>"
					 $tr.after(html);
					 form.render();
		       })
		       
		       form.on('submit(return)',function(){
		    	   window.location.href = "${basePath}/semantic/semanticIndex?page="+$("#page").text();
			       	//jump(url,$("#page").text());
		       })
		       
		        //监听提交
		        form.on('submit(from)', function(data){
		        	var arr = [];
		        	var $input = $("td").find('input');
		        	$input.each(function(i, n){
		        		if(n.value == '' || n.value == null){
		        			arr.push("null");
		        		}else{
		        			arr.push(n.value);
		        		}
	        		});
		            $.post("${basePath}/answer/custom/custom-answer-add",{"arr":arr.join(","),"id":$("#id").val(),"operatorId":$("#operatorId").text()},function (res) {
			                if(res.isSuccess){
			                	/* debugger; */
			                	layer.msg(res.message,{offset: ['60px', '500px']});
			                	 /* setTimeout(function(){
			                		 //parent.layer.close(parent.layer.getFrameIndex(window.name)); //再执行关闭 
			                		 //jump("${basePath}/answer/custom/custom-answer-find",null);
		                		 }, 2000); */
			                }else{
			                	layer.msg(res.message,{offset: ['60px', '500px']});
			               }
		            })
		        });
	    	});
			
			function closeLayer(index) {
		        layer.close(index);
		        location.reload();
		    }
	
		    function _returnData(pageNum) {
		        var url = "${basePath}/answer/custom/custom-answer-find";
		        jump(url,pageNum);
		    }
		</script>
	</body>
</html>