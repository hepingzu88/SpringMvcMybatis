<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/common.jsp" %>
<table class="layui-table">
    <colgroup>
        <col width="500">
        <col width="500">
        <col width="250">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>用户名</th>
        <th>权限名称</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${page.list!=null && page.list.size()>0}">
        <c:forEach items="${page.list}" var="bean">
            <tr>
                <td>${bean.username}</td>
                <td>${bean.permission}</td>
                <td>
                    <button class="layui-btn layui-btn-small layui-btn-danger" onclick="del(${bean.id},${bean.permissionId})">
                        <i class="layui-icon">&#xe640;</i>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<c:if test="${page.list == null}">
	<span style="margin-left: 35%">用户暂无任何权限,去授权
  	</span>
</c:if>
<%@include file="/include/page.jsp" %>
