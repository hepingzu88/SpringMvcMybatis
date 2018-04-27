<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
		<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
	</head>
	<body>
	    <form class="layui-form" action="" style="margin-left: 2%;">
		    <blockquote class="layui-elem-quote layui-text">
		    	注意:必须为批量语料选择一个对应的场景
			</blockquote>
			  <div class="layui-form-item" style="width: 30%;">
			      <select name="sceneId" id= "sceneId" lay-verify="required">
			      	<c:forEach items="${list}" var="item">
			        	<option value="${item.id}">${item.scene}</option>
			        </c:forEach>
			      </select>
			  </div>
		</form>
		<div class="layui-upload" style="width: 96%;margin-left: 2%;">
		  <div class="layui-upload-list">
		    <table class="layui-table">
		      <thead>
		        <tr><th>文件名</th>
		        <th>大小</th>
		        <th>状态</th>
		        <th>操作</th>
		      </tr></thead>
		      <tbody id="demoList"></tbody>
		    </table>
		  </div>
		   <div class="layui-progress layui-progress-big" id="progressdiv" style="display: none;width: 99%">
              <div class="layui-progress-bar" style="width: 0%" id="progress"></div>
          </div>
          <br>
          <button type="button" class="layui-btn layui-btn-normal" id="testList">选择文件</button> 
		  <button type="button" class="layui-btn" id="testListAction">开始上传</button>
		  <span id="fileName" style="display: none"></span>
		</div> 
	</body>
	<script type="text/javascript">
	  //多文件列表示例
	  var listView = $('#showList');
	  var entity ={
		"sceneId":$("#sceneId").val()
	  } 
	  layui.use(['upload','layer','form','element'], function(){
		  var $ = layui.jquery
		  ,upload = layui.upload,
		  layer = layui.layer,
		  form = layui.form,
		  element = layui.element;
		  form.render();
		  
		  var demoListView = $('#demoList')
		  ,uploadListIns = upload.render({
		    elem: '#testList'
		    ,url: '${basePath}/semantic/upload'
		    ,accept: 'file'
		    ,multiple: true
		    ,auto: false
		    ,bindAction: '#testListAction'
		    ,choose: function(obj){   
		      files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
		      //读取本地文件
		      obj.preview(function(index, file, result){
		    	var fileName = file.name;
		    	$("#fileName").text(fileName);
		        var tr = $(['<tr id="upload-'+ index +'">'
		          ,'<td>'+ file.name +'</td>'
		          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
		          ,'<td>等待上传</td>'
		          ,'<td>'
		            ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
		            ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
		          ,'</td>'
		        ,'</tr>'].join(''));
		        //单个重传
		        tr.find('.demo-reload').on('click', function(){
		          obj.upload(index, file);
		        });
		        
		        //删除
		        tr.find('.demo-delete').on('click', function(){
		          delete files[index]; //删除对应的文件
		          $("#fileName").text("");
		          tr.remove();
		          uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
		        });
		        
		        demoListView.append(tr);
		      });
		    },before:function(obj){
		    	if($("#fileName").text() == null || $("#fileName").text() == ''){
		    		layer.msg("请选择文件(已传文件不能重复上传)",{icon: 5});	
		    		return;
		    	}
		    	var entity ={
  					"id":$("#sceneId").val()
  		        } 
		    	if(entity.id == '' ||entity.id == null){
		    		layer.msg("请选择场景");
		    		return;
		    	}
		    	this.data = entity;
		    	indexLoad = layer.load();
		    	/*//$("#progressdiv").show();
	           // $("#progress").css("width",0);
		    	//showUploadProgress(); */
		    },done: function(res, index, upload){
		      if(res.isSuccess){ //上传成功
		    	layer.close(indexLoad);
		    	$("#fileName").text("");
		    	layer.msg(res.message);
		    	 var tr = demoListView.find('tr#upload-'+ index)
		         ,tds = tr.children();
		         tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
		         //tds.eq(3).html(''); //清空操作
		    	 setTimeout(function(){
		    		$("#progressdiv").hide();
       		 	 }, 2000);
		         return delete this.files[index]; //删除文件队列已经上传成功的文件
		      }else{
		    	  layer.msg(res.message);
		      }
		      this.error(index, upload);
		    }
		    ,error: function(index, upload){
		      var tr = demoListView.find('tr#upload-'+ index)
		      ,tds = tr.children();
		      tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
		      tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
		    }
		  });
	  });
	
	//上传进度
    function showUploadProgress() {
        var bytesRead=0;
        var contentLength=0;
        var succeed = false;
        var url ='${basePath}/upload/progress?radom='+Math.random();
        $.ajax({
            url : url,
            async : false,
            dataType : "json",
            success: function(data){
                succeed = data.succeed;
                bytesRead = data.bytesRead;
                contentLength = data.contentLength;
            }
        });
        $("#progress").width(Math.round(bytesRead/contentLength*100)+'%');
        console.log($("#progress").width());
        console.log(bytesRead);
        console.log(contentLength);
        /* if(succeed==0){
            setTimeout(arguments.callee, 500);//setInterval  clearInterval(int);
        } */
    }
	
	</script>
</html>