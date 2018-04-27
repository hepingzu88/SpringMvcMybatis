<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<title>天翼快修管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="http://branch2.1.r.equickfix.cn/min/?b=backend/style&f=manage.css&v=20160623">
<script type="text/javascript" src="http://branch2.1.r.equickfix.cn/min/?b=backend/js/jquery&f=jquery-1.7.1.min.js&v=20160730"></script>
</head>

<body scroll="no">
	
	<!-- 引入头部 -->
	<jsp:include page="head.jsp" />
	
	<div class="main" id="main">
		<div class="mainA">
			<!-- 引入菜单栏  -->
			<jsp:include page="left.jsp" />
		</div>
		<div class="mainB" id="mainB">
			<iframe src="/main" name="win" id="win" width="100%" height="100%" frameborder="0"></iframe>
		</div>
	</div>
</body>
</html>