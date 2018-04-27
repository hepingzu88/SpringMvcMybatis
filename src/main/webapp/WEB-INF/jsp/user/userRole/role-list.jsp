<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/common.jsp" %>
<table class="layui-table">
    <colgroup>
        <col width="400">
        <col width="400">
        <col width="150">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>用户</th>
        <th>角色</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${page.list!=null && page.list.size()>0}">
        <c:forEach items="${page.list}" var="bean">
            <tr>
                <td>${bean.username}</td>
                <td>${bean.roleName}</td>
                <td>
                    <button class="layui-btn layui-btn-small" type="button" onclick="edit(${bean.roleId})">
                        	角色授权
                    </button>
                    <button class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick="del(${bean.id},${bean.roleId})">
                        <i class="layui-icon">删除用户角色</i>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<%@include file="/include/page.jsp" %>
