<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		
		<style type="text/css">
			a {
			    padding: 10px 15px;
			    background: #51B148;
			    color: #FFF;
			    margin-left: 20px;
			}
			a:HOVER {
				background: blue;
			}
		</style>
	</head>
	<body>
		<h1>内容管理</h1>
		<hr>
		
		<shiro:hasPermission name="cms:cms:insert">
			<a class="actionBtn">
				<span>新增</span>
			</a>
		</shiro:hasPermission>
		
		<shiro:hasPermission name="cms:cms:delete">
			<a class="actionBtn">
				<span>删除</span>
			</a>
		</shiro:hasPermission>
		
		<shiro:hasPermission name="cms:cms:update">
			<a class="actionBtn">
				<span>修改</span>
			</a>
		</shiro:hasPermission>
	</body>
</html>