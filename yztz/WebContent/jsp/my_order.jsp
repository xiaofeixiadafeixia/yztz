<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>我的订单</title>
		
		<script type="text/javascript" src="dist/js/jquery-3.3.1.js" ></script>
		<link rel="stylesheet" href="dist/css/bootstrap.css"/>
		<script type="text/javascript" src="dist/js/bootstrap.js" ></script>
		
		<link rel="stylesheet" href="css/product.css" />
		<style>
			.div-margin{
				margin: 20px 0px;
			}
			.span-margin{
				margin-left: 30px;
			}
		</style>
	</head>
	<body>
	
		<%@include file="header.jsp" %>
	
	
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ul class="breadcrumb" style="font-size: 20px; background-color: darkgray;margin-top: 10px;">
					    <span>当前地址：</span>
					    <li><a href="UIServlet?method=mainUI">主页</a></li>
					    <li class="active">我的订单</li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<c:if test="${fn:length(pm.records)<1 }">
						<div class="col-md-6 col-md-offset-3" style="height: 350px;text-align: center;font-size: 30px;color: cadetblue;position: relative;">
							<div style="position: absolute;margin: auto;top: 0px;bottom: 0px;left: 0px;right: 0px;width: 100%;height: 50px;">
								<img src="${pageContext.request.contextPath}/img/leaf.png" />
								咦，咋啥都没有呢？
								<img src="${pageContext.request.contextPath}/img/leaf.png" />
							</div>
					</div>
					</c:if>
					
					<c:forEach items="${pm.records }" var="order">
						<div class="row">
							<div class="col-md-2">
								<img class="product-img" src="${order.product.pictures.toArray()[0].url }"/>
							</div>
							<div class="col-md-9">
								<div class="div-margin">
									<span class="span-margin">订单号：${order.orders_id}</span></span>
									<span class="span-margin">商品名称：${order.product.name }</span>
									<span class="span-margin">下单时间：<fmt:formatDate pattern="yyyy年MM月dd日 HH时mm分"   value="${order.order_time }" /></span>
								</div>
								<div class="div-margin">
									<span class="span-margin">
										价格：<span style="color: red;">&yen;${order.money }</span>
									</span>
									<span class="span-margin">地址：${order.address.address }</span>
								</div>
							</div>
							<div class="col-md-1">
								<div>
									<button type="button" class="btn btn-primary" onclick="deleteOrder(this)"  data-toggle="button" value="${order.orders_id}"> 
										删除记录
									</button>
								</div>
							</div>
						</div><!-- row -->
					</c:forEach>
				</div>
			</div>
			
			
			
				
			
		</div>
		<%@include file="pageFile.jsp" %>
		
		<%@include file="footer.jsp" %>
		
		<script>
			function deleteOrder($this){
				
				$.ajax({
		            //请求方式
		            type : "POST",
		            
					dateType:"json",
		            //请求地址
		            url : "OrderServlet?method=deleteOrder",
		            //数据，json字符串
		            data : {"oid":$($this).val()},
		            //请求成功
		            success : function(result) {
		                var json = JSON.parse(result);
		                if(json.state == 1){
		                	$($this).parent().parent().parent().remove();
		                }else if(json.state == 0){
		                	alert(json.errorMessage);
		                }
		            },
		            //请求失败，包含具体的错误信息
		            error : function(e){
		                alert("出错");
		            }
		       });
			}
		</script>
		
	</body>
</html>
