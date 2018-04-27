<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/include/common.jsp" %>
<c:if test="${page.list==null}">
  	<hr>
  	<span style="margin-left: 45%">暂无数据</span>
  	<hr>
</c:if>
  <c:if test="${page.list!=null && page.list.size()>0}">
	   <c:forEach items="${page.list}" var="bean">
		  <div class="layui-collapse" lay-filter="test">
			  <div class="layui-colla-item">
			    <h2 class="layui-colla-title">${bean.text}</h2>
			    <div class="layui-colla-content">
			   		<div class="layui-form">
					  <table class="layui-table">
					    <thead>
					      <tr>
					      	<th>intent</th>
					        <th>text</th>
					        <th>text</th>
					        <th>操作</th>
					      </tr> 
					    </thead>
					    <tbody>
					      <tr id="${bean.id}">
					        <td>
					        	<input type="text" name="intent" value="${bean.intent}" lay-verify="required" placeholder="text" class="layui-input">
					        </td>
					        <td>
					        	<input type="text" id="${bean.text}" value="${bean.text}" lay-verify="required" placeholder="text" class="layui-input">
					        </td>
					        <td>${bean.text}</td>
					        <td>
					        	<!-- <button class="layui-btn layui-btn-small" type="button" onclick="edit()">
                       				<i class="layui-icon">&#xe642;</i>
                  					</button> -->
                  					<button class="layui-btn layui-btn-small layui-btn-danger" type="button" id='remove'>
                       				<i class="layui-icon">&#xe640;</i>
                  					</button>
					        </td>
					      </tr>
					      <c:if test="${bean.list!=null && bean.list.size()>0}">
						      <c:forEach items="${bean.list}" var="vo">
							      <tr>
							        <td>
							        	<input type="text" style="display:none;" value="${vo.start},${vo.end}"  class="layui-input">
							        	<input type="text" value="${vo.entity}" lay-verify="required" placeholder="text" class="layui-input">
							        </td>
							        <td>
							        	<input type="text" value="${vo.value}" lay-verify="required" placeholder="text" class="layui-input">
							        </td>
							        <td>${vo.value}</td>
							        <td>
							        	<!-- <button class="layui-btn layui-btn-small" type="button" onclick="edit()">
	                        				<i class="layui-icon">&#xe642;</i>
	                   					</button> -->
	                   					<button class="layui-btn layui-btn-small layui-btn-danger" type="button" id="del">
	                        				<i class="layui-icon">&#xe640;</i>
	                   					</button>
							        </td>
							      </tr>
						      </c:forEach>
						    </c:if>
					    </tbody>
					  </table>
					  <button class="layui-btn layui-btn-small" type="button" id="add-text" style="display: none;">
            				Add Text
       				  </button>
			          <button class="layui-btn layui-btn-small" sid="${bean.id}" id="save" style="display: none;margin-left:76%;width: 84px;" lay-submit="" lay-filter="from" type="button">
			          	Save
			          </button>
					</div>
			    </div>
	  		</div>
  		</div>
  	</c:forEach>
 </c:if>
 <%@include file="/include/page.jsp" %>