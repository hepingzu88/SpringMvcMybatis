<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/common.jsp" %>
<table class="layui-table">
    <colgroup>
        <col width="400">
        <col width="500">
        <col width="100">
        <col width="100">
      <!--   <col width="100">
        <col width="100"> -->
        <col width="200">
    </colgroup>
    <thead>
       <tr>
        <th>语料</th>
        <th>回答</th>
      <!--   <th>场景</th>
        <th>行为</th> -->
        <th>情感状态</th>
        <th>适用年龄</th>
        <shiro:hasPermission name="user:shows">
        <th>操作</th>
        </shiro:hasPermission>
       </tr> 
    </thead>
    <tbody>
    <c:if test="${page.list!=null && page.list.size()>0}">
        <c:forEach items="${page.list}" var="bean">
            <tr>
                <td>${bean.text}</td>
                <td>${bean.answer}</td>
                <%-- <td>${bean.scene}</td>
                <td>${bean.actionName}</td> --%>
                <td>${bean.sentiment}</td>
                <td>${bean.applyAge}</td>
                <shiro:hasPermission name="user:shows">
                <td>
                    <button class="layui-btn layui-btn-small" type="button" onclick="answers(${bean.id},${bean.sceneId})">
       					<i class="layui-icon">问答</i>
  					</button>
  					<button class="layui-btn layui-btn-small" type="button" onclick="updateScene(${bean.id})">
       					<i class="layui-icon">编辑</i>
  					</button>
  					 <button class="layui-btn layui-btn-small layui-btn-danger" type="button" onclick='remove(${bean.id})'>
                        <i class="layui-icon">&#xe640;</i>
                    </button>
                </td>
                </shiro:hasPermission>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<c:if test="${page.list==null}">
  <span style="margin-left: 35%">暂时没有语料
  		<shiro:hasPermission name="user:show">，
		<a href="${basePath}/semantic/add">
			<span style="color: red;">去创建</span>
		</a>
		 </shiro:hasPermission>
	</span>
 </c:if>
<%@include file="/include/page.jsp" %>