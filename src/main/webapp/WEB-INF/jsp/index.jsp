<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/include/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link rel="stylesheet" href="${basePath}/css/sccl.css">
		<link rel="stylesheet" type="text/css" href="${basePath}/skin/molv/skin.css" id="layout-skin"/>
		<script src="${basePath}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${basePath}/layuis/layui.js"></script>
		<link rel="stylesheet" href="${basePath}/layuis/css/layui.css"/>
		<title>首页</title>
	</head>
	<body>
		<div class="layout-admin">
			<header class="layout-header">
				<span class="header-logo">系统框架</span> 
				<a class="header-menu-btn" href="javascript:;"><i class="icon-font">&#xe600;</i></a>
				<ul class="header-bar">
					<li class="header-bar-role">
						<a href="javascript:void(0);">
							<%-- <span id="admin-id"><shiro:principal property="id"/></span> --%>
						</a>
					</li>
					<li class="header-bar-nav">
						<a href="javascript:void(0);">
							<i class="layui-icon">&#xe612;</i>   
							<shiro:principal property="username"/>
							<i class="icon-font" style="margin-left:5px;">&#xe60c;</i>
						</a>
						<ul class="header-dropdown-menu">
							<li><a href="/admin/logout">退出</a></li>
						</ul>
					</li>
					<li class="header-bar-nav"> 
						<a href="javascript:;" title="换肤"><i class="icon-font">&#xe608;</i></a>
						<ul class="header-dropdown-menu right dropdown-skin">
							<li><a href="javascript:;" data-val="qingxin" title="清新">清新</a></li>
							<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
							<li><a href="javascript:;" data-val="molv" title="墨绿">墨绿</a></li>
						</ul>
					</li>
				</ul>
			
			<aside class="layout-side">
				<ul class="side-menu">
					<li class="menu-header menu-item"></li>
					<li class="menu-item">
						<a href="${basePath}/semantic/semanticIndexPublic?id=1">
							<i class="icon-font ">&#xe606;</i>
							<span>公用场景</span>
							<i class="icon-font icon-right">&#xe60b;</i>
						</a>
						<ul class="menu-item-child ">
							<shiro:hasPermission name="user:show">  
								<li>
									<a href="${basePath}/semantic/add">
										<i class="icon-font">&#xe610;</i>
										<span>创建语料</span>
									</a>
								</li>
							</shiro:hasPermission>  
						</ul>
					</li>
					<%-- <li class="menu-item">
						<a href="${basePath}/thesaurus/custom/custom-index?id="+<shiro:principal property="id"/>>
							<i class="icon-font ">&#xe606;</i>
							<span>自定义场景</span>
							<i class="icon-font icon-right">&#xe60b;</i>
						</a>
						<ul class="menu-item-child ">
							<li>
								<a href="">
								<i class="icon-font">&#xe610</i>
								<span id="add-custom-scene">添加场景</span>
								</a>
							</li>
						</ul>
					</li> --%>
					<li class="menu-item">
						<a href="${basePath}/semantic/semanticIndex">
							<i class="icon-font ">&#xe606;</i>
							<span>自定义场景</span>
							<i class="icon-font icon-right">&#xe60b;</i>
						</a>
						<ul class="menu-item-child ">
							<li>
								<a href="${basePath}/semantic/add">
									<i class="icon-font">&#xe610;</i>
									<span>创建语料</span>
								</a>
							</li>
							<%-- <li>
								<a href="${basePath}/semantic/upload">
									<i class="icon-font">&#xe610;</i>
									<span>批量上传语料</span>
								</a>
							</li> --%>
						</ul>
					</li>
					<%-- <li class="menu-item">
						<a href="${basePath}/answer/custom/custom-answer-index?id="+<shiro:principal property="id"/>>
							<i class="icon-font ">&#xe606;</i>
							<span>自定义问答</span>
							<i class="icon-font icon-right">&#xe60b;</i>
						</a>
						<ul class="menu-item-child ">
							<li>
								<a href="">
								<i class="icon-font">&#xe610</i>
								<span id="add-custom-scene">添加场景</span>
								</a>
							</li>
						</ul>
					</li> --%>
					<li class="menu-item">
						<a href="${basePath}/apply/apply-index">
							<i class="icon-font ">&#xe606;</i>
							<span>应用管理</span>
							<i class="icon-font icon-right">&#xe60b;</i>
						</a>
						<ul class="menu-item-child ">
							<li>
								<a href="${basePath}/apply/apply-add">
								<i class="icon-font">&#xe610</i>
								<span>创建应用</span>
								</a>
							</li>
						</ul>
					</li>
					<li class="menu-item">
						<a href="">
							<i class="icon-font">&#xe606;</i>
							<span>词库管理</span>
							<i class="icon-font icon-right">&#xe60b;</i>
						</a>
						<ul class="menu-item-child ">
							<%-- <li>
								<a href="${basePath}/thesaurus/index">
								<i class="icon-font">&#xe610</i>
									<span>词库</span>
								</a>
							</li> --%>
							<%-- <shiro:hasPermission name="user:show">
							<li>
								<a href="${basePath}/thesaurus/public/public-scene-add">
								<i class="icon-font">&#xe610</i>
									<span>创建公开场景</span>
								</a>
							</li>
							</shiro:hasPermission> --%>
							<li>
								<%-- <a href="${basePath}/thesaurus/custom/custom-scene-add">
								<i class="icon-font">&#xe610</i>
									<span>创建场景</span>
								</a> --%>
								<a href="${basePath}/thesaurus/custom/custom-index">
								<i class="icon-font">&#xe610</i>
									<span>场景</span>
								</a>
							</li>
							<li>
								<%-- <a href="${basePath}/thesaurus/entity/entity-add">
								<i class="icon-font">&#xe610</i>
									<span>创建实体类型</span>
								</a> --%>
								<a href="${basePath}/thesaurus/entity/entity-index">
								<i class="icon-font">&#xe610</i>
									<span>实体</span>
								</a>
							</li>
							<li>
								<a href="${basePath}/thesaurus/intent/intent-index">
								<i class="icon-font">&#xe610</i>
									<span>意图</span>
								</a>
								<%-- <a href="${basePath}/thesaurus/action/action-index">
								<i class="icon-font">&#xe610</i>
									<span>行为</span>
								</a> --%>
							</li>
							<li>
								<%-- <a href="${basePath}/thesaurus/affective/affective-add">
								<i class="icon-font">&#xe610</i>
									<span>情感</span>
								</a> --%>
								<a href="${basePath}/thesaurus/affective/affective-index">
								<i class="icon-font">&#xe610</i>
									<span>情感</span>
								</a>
							</li>
							<li>
								<a href="${basePath}/thesaurus/ageBand/ageBand-index">
								<i class="icon-font">&#xe610</i>
									<span>适用年龄</span>
								</a>
							</li>                
						</ul>
					</li>
					<li class="menu-item">
						<a href="">
							<i class="icon-font">&#xe606;</i>
							<span>资源标注</span>
							<i class="icon-font icon-right">&#xe60b;</i>
						</a>
						<ul class="menu-item-child ">
							<li>
								<a href="${basePath}/music/index">
								<i class="icon-font">&#xe610</i>
									<span>音乐资源标注</span>
								</a>
							</li>
							<li>
								<a href="${basePath}/edu/index">
								<i class="icon-font">&#xe610</i>
									<span>课程资源标注</span>
								</a>
							</li>
							<li>
								<a href="${basePath}/story/index">
								<i class="icon-font">&#xe610</i>
									<span>故事资源标注</span>
								</a>
							</li>
								<li>
								<a href="${basePath}/music/tagCategoryIndex">
								<i class="icon-font">&#xe610</i>
									<span>资源标签</span>
								</a>
							</li>
						</ul>
					</li>
					<li class="menu-item">
							<a href="${basePath}/admin/info">
								<i class="icon-font ">&#xe609;</i>
								<span>账号信息</span>
								<i class="icon-font icon-right">&#xe60b;</i>
							</a>
							<ul class="menu-item-child ">
								<shiro:hasPermission name="user:show">  
									<li>
										<a href="${basePath}/admin/user/list">
										<i class="icon-font">&#xe610</i>
										<span>用户列表</span>
										</a>
									</li>
								</shiro:hasPermission>
								<shiro:hasPermission name="user:show">  
									<li>
										<a href="${basePath}/admin/user/role">
										<i class="icon-font">&#xe610</i>
										<span>角色权限</span>
										</a>
									</li>
								</shiro:hasPermission>
								<shiro:hasPermission name="user:show">  
									<li>
										<a href="${basePath}/admin/userRoleIndex">
										<i class="icon-font">&#xe610</i>
										<span>用户角色</span>
										</a>
									</li>
								</shiro:hasPermission>
								<shiro:hasPermission name="user:show">  
									<li>
										<a href="${basePath}/admin/permissionIndex">
										<i class="icon-font">&#xe610</i>
										<span>权限列表</span>
										</a>
									</li>
								</shiro:hasPermission>
								<%-- <li>
									<a href="${basePath}/robot/robot-index">
									<i class="icon-font">&#xe610</i>
									<span>机器人对话</span>
									</a>
								</li> --%>
							</ul>
						</li>
					<li class="menu-item">
						<a href="${basePath}/log/select">
							<i class="icon-font ">&#xe606;</i>
							<span>日志信息</span>
							<i class="icon-font icon-right">&#xe60b;</i>
						</a>
						<%-- <ul class="menu-item-child ">
							<li>
								<a href="${basePath}/log/">
								<i class="icon-font">&#xe610</i>
								<span>查看日志</span>
								</a>
							</li>
						</ul> --%>
					</li>
				</ul>
			</aside>
		</header>
		<div class="layout-side-arrow"><div class="layout-side-arrow-icon"><i class="icon-font">&#xe60d;</i></div></div>
		
		<section class="layout-main">
			<div class="layout-main-tab">
				<button class="tab-btn btn-left"><i class="icon-font">&#xe60e;</i></button>
                <nav class="tab-nav">
                    <div class="tab-nav-content">
                        <a href="javascript:void (0);" style="cursor: default;" class="content-tab active" data-id="home"></a>
                    </div>
                </nav>
                <button class="tab-btn btn-right"><i class="icon-font">&#xe60f;</i></button>
			</div>
			<div class="layout-main-body">
				<iframe class="body-iframe" name="iframe0" width="100%" height="99%" src="${basePath}/admin/home" frameborder="0" data-id="home.jsp" seamless></iframe>
			</div>
	</section>
	<%--<div class="layout-footer">xxxxxxx</div>--%>
	</div>
	<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/sccl.js"></script>
	<script type="text/javascript" src="${basePath}/js/sccl-util.js"></script>
	<script type="text/javascript">
			layui.use(['form'], function(){
		        var form = layui.form;
		       
			  //增加自定义场景
			  $("#add-custom-scene").on('click',function(){
				  var url ="${basePath}/thesaurus/custom/custom-scene-add";
				  add_index(url);
			  })
			  
			  function add_index(url) {
				  layer.open({
		               type: 2,
		               area: ['450px', '280px'],
		               offset: ['120px', '450px'],
		               fixed: false, //不固定
		               maxmin: true,
		               content: [url,"no"]
		          })
			 }
		        
		    });	
		</script>
  </body>	
</html>