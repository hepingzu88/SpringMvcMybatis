<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<div class="panel-heading"><h2>二维码与有Logo 的二维码</h2></div>
    <div class="panel-body">
        <div class="col-md-6" align="center">
        <a href="zxingdecode?realImgPath=${imageName }">
        <img class="img-responsive img-rounded" src="${pageContext.request.contextPath}${imageUrl }"/>
        <b class="btn btn-success" >我要解码</b>
        </a>
        </div>
        <div class="col-md-6" align="center">
        <a href="zxingdecode?realImgPath=${imageName }">
        <img class="img-responsive img-rounded" src="${pageContext.request.contextPath}${logoImageUrl }"/>
        <b class="btn btn-success" >我要解码</b>
        </a>
        </div>
        <div class="col-md-12">
        <br>
        <a class="btn btn-warning" href="${pageContext.request.contextPath }">返回</a>
        </div>
    </div>
</body>
</html>