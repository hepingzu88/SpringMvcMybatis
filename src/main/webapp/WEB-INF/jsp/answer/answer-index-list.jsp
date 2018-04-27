<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/common.jsp" %>
<!-- <div class="layui-tab layui-tab-card" style="height: 750px;"> -->
	<c:forEach items="${page.list}" var="bean">
         	<span id="id" style="display: none">${bean.id}</span>
         	<span id="operatorId" style="display: none">${bean.operatorId}</span>
       </c:forEach>
	<form class="layui-form" action="" >
        <c:if test="${page.list!=null}">
	   		<div class="layui-form" id="kuang">
			  <table class="layui-table" id="table">
			  	<colgroup>
			        <col width="300">
			        <col width="300">
			        <col width="100">
			    </colgroup>
			    <thead>
			      <tr>
			      	<th>问题</th>
			        <th>回答</th>
			        <th>操作</th>
			      </tr> 
			    </thead>
			    <tbody>
			     <c:forEach items="${page.list}" var="bean">
			      <tr>
			        <td>
			        	<input name="semanticId" value="${bean.id}" style="display: none" id="semanticId">
			        	<input name="question" id="question" value="${bean.text}"  placeholder="自定义问题"  class="layui-input" type="text">
			        </td>
			        <td>
			        	<input name="answer" id="answer"  value="${bean.answer}" placeholder="自定义回答" class="layui-input" type="text">
			        </td>
			        <td>
        				<button class="layui-btn layui-btn-small layui-btn-danger" type="button" id='remove' value="${bean.id}">
             				<i class="layui-icon">&#xe640;</i>
        				</button>
        				<button class="layui-btn layui-btn-small" type="button" id="split" onclick="edit(${bean.id},${bean.intentId})">
                    	<i class="layui-icon">&#xe642;</i>
	                    </button>
			        </td>
			      </tr>
			      </c:forEach>
			    </tbody>
			  </table>
			  <button class="layui-btn layui-btn-small" type="button" id="add-tr" style="margin-left:1%;">
       			新增同义句
   			 </button>
     		 <button class="layui-btn layui-btn-small"  id="save" style="margin-left:85%;width: 80px;" lay-submit="" lay-filter="from" type="button">
	          	Save
	         </button>
	         <!-- <button class="layui-btn layui-btn-small"  id="return" style="width: 84px;" lay-submit="" lay-filter="return" type="button">
	          	返回
	         </button> -->
		  </div>
	   </c:if>
    </form>
<!--  </div> -->
 <c:if test="${page.list==null}">
  	<span style="margin-left: 35%">你还没有自定义语料，
  		<a href="${basePath}/answer/custom/custom-answer-add">
  			<span style="color: red;">去创建</span>
  		</a>
  	</span>
  </c:if>
  <%@include file="/include/page.jsp" %>