<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<script type="text/javascript" src="http://branch2.1.r.equickfix.cn/min/?b=backend/js/jquery&f=jquery-1.7.1.min.js&v=20160730"></script>
<div class="header">
	<div class="logo189">xiujj.cn</div>
	<div class="nav">
		<ul>
			
			<li index="0">
				<div>
					<a href="/admini/default/home.html" target="win" hidefocus>首页</a>
				</div>
			</li>
			
			
			<shiro:hasPermission name="set"> <!--  set  等同于 set:* -->
			<li index="1">
				<div>
					<a href="/admini/config/index.html" target="win" hidefocus>设置</a>
				</div>
			</li>
			</shiro:hasPermission>
			
			<!--  -->
			<shiro:hasPermission name="cms">
			<li index="2">
				<div>
					<a href="/admini/post/index.html" target="win" hidefocus>内容</a>
				</div>
			</li>
			</shiro:hasPermission>
		</ul>
	</div>
	<div class="logininfo">
		<span class="welcome"><img src="http://branch2.1.equickfix.cn/backend/images/user_edit.png?v=20160623" align="absmiddle"> 欢迎, <em>admin</em>
		</span> <a href="/admini/admin/ownerUpdate.html" target="win">修改密码</a> <a href="/admini/public/logout.html" target="_top">退出登录</a>
	</div>
</div>
<div class="topline">
	<div class="toplineimg left" id="imgLine"></div>
</div>