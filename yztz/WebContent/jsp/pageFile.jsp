<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<c:if test="${pm.totalPageNum>1}">
		<%--分页显示的开始 --%>
    	<div style="text-align:center">
    	<form class="navbar-form navbar-left" style="width:100%">
    		<lable style="margin-right:20px">共${pm.totalPageNum}页/第<span style="color:red">
    			<c:if test="${pm.totalPageNum>0}">
    				${pm.currentPageNum}
    			</c:if>
    			<c:if test="${pm.totalPageNum==0}">
    				0
    			</c:if>
    		</span>页</lable>
    		<ul class="pagination" id="pager_li">
    		
    		
    			
	    		<c:if test="${pm.totalPageNum>9}">
	    			<li><a href="${pageContext.request.contextPath}/${pm.url}&currentPageNum=1">首页</a></li>
	    		</c:if>
	    		
	    		<c:if test="${pm.currentPageNum>1}">
	    			<li><a href="${pageContext.request.contextPath}/${pm.url}&currentPageNum=${pm.prePageNum}">上一页</a></li>
	    		</c:if>
	    		<%--显示的页码，使用forEach遍历显示的页面 --%>
	    		<c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="pagenum">
	    			<li><a href="${pageContext.request.contextPath}/${pm.url}&currentPageNum=${pagenum}">${pagenum}</a></li>
	    		</c:forEach>
	    		
	    		<c:if test="${pm.currentPageNum<pm.totalPageNum}">
	    			<li><a href="${pageContext.request.contextPath}/${pm.url}&currentPageNum=${pm.nextPageNum}">下一页</a></li>
	    		</c:if>
	    		
	    		<c:if test="${pm.totalPageNum>9}">
	    			<li><a href="${pageContext.request.contextPath}/${pm.url}&currentPageNum=${pm.totalPageNum}">末页</a></li>
	    		</c:if>
    			<input style="margin-left:20px" class="form-control" type="text" id="pagenum" name="pagenum" size="1"/>
    			<input class="btn btn-default" type="button" value="前往" onclick="jump()" />
    		
    		</ul>
    		</form>
    		<script type="text/javascript">
    			function jump(){
    				var totalpage = ${pm.totalPageNum};
    				var pagenum = document.getElementById("pagenum").value;
    				//判断输入的是一个数字
    				var reg =/^[1-9][0-9]*$/;
    				if(!reg.test(pagenum)){
    					//不是一个有效数字
    					alert("请输入符合规定的数字");
    					return ;
    				}
    				//判断输入的数字不能大于总页数
    				if(parseInt(pagenum)>parseInt(totalpage)){
    					//超过了总页数
    					alert("不能大于总页数");
    					return;
    				}
    				//转向分页显示的Servlet
    				window.location.href="${pageContext.request.contextPath}/${pm.url}&currentPageNum="+pagenum;
    			}
    		</script>
    	</div>
    	<%--分页显示的结束--%>	
	</c:if>
