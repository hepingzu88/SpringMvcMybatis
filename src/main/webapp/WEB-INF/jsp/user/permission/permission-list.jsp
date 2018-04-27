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
        <th>权限名</th>
        <th>权限标签</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${page.list!=null && page.list.size()>0}">
        <c:forEach items="${page.list}" var="bean">
            <tr>
                <td>${bean.permissionName}</td>
                <td>${bean.permission}</td>
                <td>
                    <button class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick="del(${bean.id})">
                        <i class="layui-icon">&#xe640;</i>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<%@include file="/include/page.jsp" %>
