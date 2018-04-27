<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- <c:if test="${page.list!=null && page.pages>1}"> --%>
    <div id="test" class="layui-box layui-laypage">
       <%--  <!--如果是第一页，则上一页和首页按钮失效 如果当前页是1则不满足条件，不显示首页，第一页-->
        <c:if test="${page.pageNum>1}">
            <a class="layui-laypage-prev" href="javascript:void(0);" onclick="_returnData(1)">首页</a>
            <a class="laypage_first" href="javascript:void(0);" onclick="_returnData(${page.pageNum-1})">上一页</a>
        </c:if>
        <!--<span>…</span>-->
        <c:set value="${page.pageNum-5<1?1:page.pageNum-3}" var="begin"></c:set>
        <c:set value="${begin+9>page.pages?page.pages:begin+9}" var="end"></c:set>

        <c:forEach begin="${begin}" step="1" end="${end}" var="num">
            <c:choose>
                <c:when test="${page.pageNum==num}">
                    <span class="layui-laypage-curr">
                    <em class="layui-laypage-em"></em>
                    <em>${num}</em>
                    </span>
                </c:when>
                <c:otherwise>
                    <a href="javascript:void(0);" onclick="_returnData(${num})">${num}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <!--如果是最后一页，则下一页和尾页按钮失效-->
        <c:if test="${page.pageNum<page.pages}">
            <a class="layui-laypage-next" href="javascript:void(0);" onclick="_returnData(${page.pageNum+1})">下一页</a>
            <a class="layui-laypage-last" href="javascript:void(0);" title="尾页" onclick="_returnData(${page.pages})">末页</a>
        </c:if>
    </div>
</c:if>--%>
 </div>
<script>
	layui.use(['laypage', 'layer','form'], function(){
	  var laypage = layui.laypage
	  ,layer = layui.layer
	  ,form = layui.form;
	  form.render();
	  //完整功能
		laypage.render({
		  elem: 'test'
		  ,count: ${page.total}
		  ,layout: ['count', 'prev', 'page', 'next', 'skip']
		  ,curr: $("#pageNum").val()
		  ,jump: function(obj,first){
			  if(!first){
				   _returnData(obj.curr);
		      }
		  }
		});
	
	})
</script>