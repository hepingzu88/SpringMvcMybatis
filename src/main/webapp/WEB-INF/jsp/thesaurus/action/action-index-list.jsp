<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/common.jsp" %>
<table class="layui-table">
    <colgroup>
        <col width="600">
        <col width="600">
        <col>
    </colgroup>
    <thead>
       <tr>
      	<th>行为</th>
        <th>创建时间</th>
        <th>操作</th>
       </tr> 
    </thead>
    <tbody>
    <c:if test="${page.list!=null && page.list.size()>0}">
        <c:forEach items="${page.list}" var="bean">
            <tr>
                <td>${bean.action}</td>
                <td><fmt:formatDate value="${bean.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>
                    <%-- <button class="layui-btn layui-btn-small" type="button" onclick="edit(${bean.id})">
                        <i class="layui-icon">&#xe642;</i>
                    </button> --%>
                    <button class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick='remove(${bean.id},"${bean.action}")'>
                        <i class="layui-icon">&#xe640;</i>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<%@include file="/include/page.jsp" %>