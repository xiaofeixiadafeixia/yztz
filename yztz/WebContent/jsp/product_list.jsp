<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品</title>
		<script type="text/javascript" src="dist/js/jquery-3.3.1.js" ></script>
		<link rel="stylesheet" href="dist/css/bootstrap.css" />
		<script type="text/javascript" src="dist/js/bootstrap.js" ></script>
		
		<link rel="stylesheet" href="css/product.css" />
	</head>
	<body>
	
		<%@include file="header.jsp" %>
	
	
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ul class="breadcrumb" style="font-size: 20px;background-color: darkgray;margin-top: 10px;">
						<span>当前地址：</span>
					    <li><a href="UIServlet?method=mainUI">主页</a></li>
					    <li class="active">
					    	<c:choose>
					    		<c:when test="${not empty str }">
					    			关键字搜索 ：${str }
					    		</c:when>
					    		<c:otherwise>
					    			${type.name }
					    		</c:otherwise>
					    	</c:choose>
					    </li>
					</ul>
				</div>
				
				<c:if test="${fn:length(pm.records)<1 }">
					<div class="col-md-6 col-md-offset-3" style="height: 350px;text-align: center;font-size: 30px;color: cadetblue;position: relative;">
						<div style="position: absolute;margin: auto;top: 0px;bottom: 0px;left: 0px;right: 0px;width: 100%;height: 50px;">
							<img src="${pageContext.request.contextPath}/img/leaf.png" />
							咦，咋啥都没有呢？
							<img src="${pageContext.request.contextPath}/img/leaf.png" />
						</div>
					</div>
				</c:if>
				<c:forEach items="${pm.records }" var="p">
					<div class="col-md-2 product">
						<a href="UIServlet?method=productInfoUI&goods_id=${p.goods_id }" target="_blank">
							<img class="product-img" src="${p.pictures.toArray()[0].url }" 
								alt="图片未加载出来"/>
							
							<div class="product-intro">
								${p.name }
							</div>
						</a>
						<div class="product-price">
							<span>&yen;：${p.price }</span>
						</div>
					</div>
				</c:forEach>
				
				
				
				
			</div>
		</div>
		
		<%@include file="pageFile.jsp" %>
		<%@include file="footer.jsp" %>
		
	</body>
</html>
