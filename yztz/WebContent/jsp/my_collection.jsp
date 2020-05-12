<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>我的收藏</title>
		
		<script type="text/javascript" src="dist/js/jquery-3.3.1.js" ></script>
		<link rel="stylesheet" href="dist/css/bootstrap.css"/>
		<script type="text/javascript" src="dist/js/bootstrap.js" ></script>
		
		<link rel="stylesheet" href="css/product.css" />
		<link rel="stylesheet" href="css/shopping_cart.css" />
		<script type="text/javascript" src="js/my_collect.js"></script>
	</head>
	<body>
	
		<%@include file="header.jsp" %>
	
	
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ul class="breadcrumb" style="font-size: 20px; background-color: darkgray;margin-top: 10px;">
					    <span>当前地址：</span>
					    <li><a href="UIServlet?method=mainUI">主页</a></li>
					    <li class="active">我的收藏</li>
					</ul>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<div class="font-size-25">
						<span id="top">我的收藏</span>
						<span style="float: right;">
							<span id="option" style="display: none;">
								<button id="check_all" type="button" class="btn btn-primary"  data-toggle="button"> 
										全选
								</button>
								<button id="remove_btn" type="button" class="btn btn-primary"  data-toggle="button"> 
										移除
								</button>
								<button id="order_btn" type="button" class="btn btn-primary"  data-toggle="button"> 
										下单
								</button>
								<button id="achieve" type="button" class="btn btn-primary"  data-toggle="button"> 
										完成
								</button>
							</span>
							<button id="exitbtn" type="button" class="btn btn-primary"  data-toggle="button"> 
								编辑
							</button>
						</span>
					</div>
					<hr />
				</div>
				
				<div class="col-md-10 col-md-offset-1">
					<c:if test="${fn:length(collects)<1 }">
						<div class="col-md-6 col-md-offset-3" style="height: 350px;text-align: center;font-size: 30px;color: cadetblue;position: relative;">
							<div style="position: absolute;margin: auto;top: 0px;bottom: 0px;left: 0px;right: 0px;width: 100%;height: 50px;">
								<img src="${pageContext.request.contextPath}/img/leaf.png" />
								咦，咋啥都没有呢？
								<img src="${pageContext.request.contextPath}/img/leaf.png" />
							</div>
						</div>
						
					</c:if>
					<form id="formCollect" action="" method="post">
						<c:forEach items="${collects }" var="collect">
							<div class="row div-padding">
							
								<div class="col-md-2">
									<img class="product-img" src="${collect.uionPKID.product.pictures.toArray()[0].url }"/>
								</div>
								<div class="col-md-8">
									<div class="div-margin">
										<span class="span-margin">编号：${collect.uionPKID.product.goods_id }</span>
										<span class="span-margin">名称：${collect.uionPKID.product.name }</span>
										<span class="span-margin">成色：${collect.uionPKID.product.fineness }</span>
									</div>
								
									<div class="div-margin">
										<span class="span-margin">
											价格：<span style="color: red;">&yen;${collect.uionPKID.product.price }</span>
										</span>
										<span class="span-margin">上传时间：
										<fmt:formatDate pattern="yyyy年MM月dd日 HH时mm分ss秒"   value="${collect.uionPKID.product.time}" /></span>
										<span class="span-margin">状态：
											<c:choose>
											    <c:when test="${collect.uionPKID.product.state == 1}">
													在售
											    </c:when>
											    <c:when test="${collect.uionPKID.product.state == 2}">
											      	下架
											    </c:when>
											    <c:otherwise>
											       	售罄
											    </c:otherwise>
											</c:choose>
										</span>
									</div>
								</div>
								<div class="col-md-2">
									<div style="margin-top:25px">
										<input type="checkbox" name="isCheck" style="display: none;" value="${collect.uionPKID.product.goods_id }"/>
									</div>
								</div>
							</div>
						</c:forEach>
					</form>
					
				</div>
				
				
				
			</div><!-- row -->
			
			
				
			
		</div>
		
		
		<%@include file="footer.jsp" %>
		
		
		<!-- 地址模块 -->
		<div id="addr_div" class="hidden" style="z-index: 9;overflow:hidden;z-index: 10; height: 560px;width: 500px;background-color:#D9EDF7; position: absolute;margin: auto;top: 0px;bottom: 0px;left: 0px;right: 0px;">
			<div style="text-align: center;height: 50px;border-bottom: 1px dashed red;padding-top: 10px;">
				<span>学&nbsp;&nbsp;院：</span>
				<select id="college" style="text-align:center;text-align-last:center;height: 25px;background-color:#D9EDF7;">
					<c:forEach items="${colleges }" var="c">
						<option value="${c.college_id }">${c.region }</option>
					</c:forEach>
				</select>
				<button id="addr_edit_off_btn" type="button" class="btn btn-primary"  data-toggle="button" style="float: right;background-color: #D9EDF7;color: black;"> 
						关闭
				</button>
				<button id="open_addr_add_btn"  type="button" class="btn btn-primary"  data-toggle="button" style="float: left;background-color: #D9EDF7;color: black;"> 
						添加
				</button>
			</div>
			
			<!-- 显示区  -->
			<div id="addr_show_div" style="height:510px;overflow-y: scroll;padding-bottom: 20px;" >
				
			</div>
			
			
			
			<div id="addr_add_div" style="height: 109px;background-color:#D9EDF7;border-top: 1px dashed red;">
				<div style="text-align: center;">
					<span>----<strong id="tag_title" style="color: red;">添加</strong>收货地址----</span>
					<a id="addr_add_close_a" href="javascript:void(0)" style="float: right;">关闭</a>
				</div>
				<div>
					<input id="address_tranself_id" type="text" value="" style="display: none;"/>
					<span class="margin-left">姓名：</span><input id="address_tranself_name" type="text" maxlength="10" placeholder="姓名不能为空！" style="background-color: #D9EDF7;"/>
					<span class="margin-left">电话：</span><input id="address_tranself_phone" type="text" maxlength="20" placeholder="电话不能为空！" style="background-color: #D9EDF7;"/><br />
					<span class="margin-left">地址：</span><input id="address_tranself_addr" type="text" maxlength="50" placeholder="地址不能为空！" style="width: 350px;background-color: #D9EDF7;"/>
				</div>
				<div style="text-align: center;">
					<button id="tag_btn" type="button" class="btn btn-primary"  data-toggle="button" > 
						添加
					</button>
				</div>
			</div>
			
		</div> <!--地址模块-->
		<a href="#top" style="position: fixed;bottom: 0px;right: 0px; width: 50px;height: 50px;font-size: 20px;cursor: pointer;background-color: greenyellow;text-align: center;line-height: 50px;">
			顶部
		</a>
		
	</body>
</html>
