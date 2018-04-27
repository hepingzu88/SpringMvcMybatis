<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/common.jsp" %>
<table class="layui-table">
    <colgroup>
        <col width="150">
        <col width="200">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>I值</th>
        <th>时间</th>
        <th>ID</th>
        <th>内容</th>
        <th>意图</th>
        <th>Service</th>
        <th>Operation</th>
        <th>结果</th>
        <th>回答</th>
        <th>语义</th>
        <th>型号</th>
      
    </tr>
    </thead>
    <tbody>
    <c:if test="${page.list!=null && page.list.size()>0}">
        <c:forEach items="${page.list}" var="bean">
            <tr>
                <td>${bean.i}</td>
                <td><fmt:formatDate value="${bean.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${bean.id}</td>
                <td>${bean.text}</td>
                <td>${bean.intent}</td>
               <%--  <td>${bean.textUrl}</td> --%>
                <td>${bean.service}</td>
              <%--   <td>${bean.serviceFrom}</td> --%>
                <td>${bean.operation}</td>
                <td>${bean.moreResults}</td>
                <td>${bean.answer}</td>
                <td>${bean.semantic}</td>
                <td>${bean.rtype}</td>
                <%-- <td>${bean.version}</td>
                <td>${bean.wrong}</td>
                <td>${bean.reply}</td> --%>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<%@include file="/include/page.jsp" %>