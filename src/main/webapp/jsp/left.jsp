<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div id="leftmenu" class="menu">
	<ul index="0" class="left_menu">
		<li index="0"><a href="/admini/default/home.html" target="win">系统首页</a></li>
		<li index="1"><a href="/admini/catalog/index.html" target="win">栏目管理</a></li>
	</ul>
	<ul index="1" class="left_menu">
		<li index="2"><a href="/setting/brand" target="win">品牌管理</a></li>
		<li index="3"><a href="/setting/model" target="win">型号管理</a></li>
	</ul>
	<ul index="2" class="left_menu">
		
		<shiro:hasPermission name="cms:cms">
		<li index="0"><a href="/cms/cms" target="win">内容管理</a></li>
		</shiro:hasPermission>
		
		<shiro:hasPermission name="cms:msg">
		<li index="2"><a href="/cms/msg" target="win">短信管理</a></li>
		</shiro:hasPermission>
	</ul>
</div>

<script type="text/javascript">
window.onload =window.onresize= function(){winresize();}
function winresize()
{
function $(s){return document.getElementById(s);}
var D=document.documentElement||document.body,h=D.clientHeight-90,w=D.clientWidth-160;
 $("main").style.height=h+"px";
 $("mainB").style.width=w+"px";
}
$(document).ready(function(){
    var s=document.location.hash;
    if(s==undefined||s==""){s="#0_0";}
    s=s.slice(1);
    var navIndex=s.split("_");
    $(".nav").find("li:eq("+navIndex[0]+")").addClass("active");
    var targetLink=$(".menu").find("ul").hide().end()
                             .find(".left_menu:eq("+navIndex[0]+")").show()
                             .find("li:eq("+navIndex[1]+")").addClass("active")
                             .find("a").attr("href");
    $("#win").attr("src",targetLink);
    $(".nav").find("li").click(function(){
        $(this).parent().find("li").removeClass("active").end().end()
               .addClass("active");
        var index=$(this).attr("index");
        $(".menu").find(".left_menu").hide();
        $(".menu").find(".left_menu:eq("+index+")").show()
                  .find("li").removeClass("active").first().addClass("active");
        document.location.hash=index+"_0";
    });
    $(".left_menu").find("li").click(function(){
            $(this).parent().find("li").removeClass("active").end().end()
                            .addClass("active");
        document.location.hash=$(this).parent().attr("index")+"_"+$(this).attr("index");
    });
});
</script>
