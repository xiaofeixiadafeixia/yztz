<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>我的商品</title>
		<script type="text/javascript" src="dist/js/jquery-3.3.1.js" ></script>
		<link rel="stylesheet" href="dist/css/bootstrap.css"/>
		<script type="text/javascript" src="dist/js/bootstrap.js" ></script>
		
		<link rel="stylesheet" href="css/product.css" />
		<link rel="stylesheet" href="css/my_center.css" />
		
	</head>
	<body>
		
		<%@include file="header.jsp" %>
	
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ul class="breadcrumb" style="font-size: 20px; background-color: darkgray;margin-top: 10px;">
					    <span>当前地址：</span>
					    <li><a href="UIServlet?method=mainUI">主页</a></li>
					    <li class="active">我的商品</li>
					</ul>
				</div>
			</div>
			<div class="row div-margin">
				<div class="col-md-10 col-md-offset-1">
					<div class="font-size-25 ">
						<span>我的商品</span>
						<button onclick="javaScript:window.location.href='UIServlet?method=myProductAddUI'" type="button" class="btn btn-primary"  data-toggle="button" style="float: right;"> 
								上传商品
						</button>
					</div>
					<hr />
					
					<c:if test="${fn:length(pm.records)<1 }">
						<div class="col-md-6 col-md-offset-3" style="height: 350px;text-align: center;font-size: 30px;color: cadetblue;position: relative;">
							<div style="position: absolute;margin: auto;top: 0px;bottom: 0px;left: 0px;right: 0px;width: 100%;height: 50px;">
								<img src="${pageContext.request.contextPath}/img/leaf.png" />
								咦，咋啥都没有呢？
								<img src="${pageContext.request.contextPath}/img/leaf.png" />
							</div>
					</div>
					</c:if>
					
					<c:forEach items="${pm.records}" var="p" >
						<div id="p${p.goods_id }" class="row div-padding">
							<div class="col-md-2">
								<img class="product-img" src="${p.pictures.toArray()[0].url }"/>
							</div>
							<div class="col-md-8">
								<div class="div-margin">
									<span class="span-margin">编号：${p.goods_id }</span>
									<span class="span-margin">名称：${p.name}</span>
									<span class="span-margin">成色：${p.fineness}成新</span>
								</div>
							
								<div class="div-margin">
									<span class="span-margin">
										价格：<span style="color: red;">&yen;${p.price }</span>
									</span>
									<span class="span-margin">上传时间：
										<fmt:formatDate pattern="yyyy年MM月dd日  HH点" value="${p.time}" />
									</span>
									<span class="span-margin">状态：
										<span id="showState${p.goods_id }">
											<c:choose>
											    <c:when test="${p.state == 1}">
													在售
											    </c:when>
											    <c:when test="${p.state == 2}">
											      	下架
											    </c:when>
											    <c:otherwise>
											       	售罄
											    </c:otherwise>
											</c:choose>
										</span>
									</span>
								</div>
							</div>
							<div class="col-md-2">
								<div>
									<button type="button" class="btn btn-primary"  data-toggle="button" value="${p.goods_id }" state="${p.state}" onclick="updateProduct(this)" >
							    		<c:choose>
										    <c:when test="${p.state == 1}">
										    	下架 
										    </c:when>
										    <c:otherwise>
										    	上架
										    </c:otherwise>
										</c:choose>
									</button>
								</div>
								<div>
									<button type="button" class="btn btn-primary" value="${p.goods_id }"  data-toggle="button" onclick="deleteProduct(this)"> 
										删除
									</button>
								</div>
								<div>
									<button type="button" class="btn btn-primary"  data-toggle="button" 
									onclick="javaScript:window.location.href='UIServlet?method=productInfoUI&goods_id=${p.goods_id}'"> 
										查看
									</button>
								</div>
							</div>
						</div>
					</c:forEach>
					
					
					
					
					
					
					
				</div>
			</div><!-- row -->
		</div><!-- container -->
		
		<%@include file="pageFile.jsp" %>
			
		<%@include file="footer.jsp" %>
		<script>
			var nowPage = ${pm.currentPageNum}
			function deleteProduct($this){
				if(confirm("确定删除这个商品吗？")){
					$.ajax({
				        //请求方式
				        type : "POST",
				        
						dateType:"json",
				        //请求地址
				        url : "ProductServlet?method=updateProductState",
				        //数据，json字符串
				        data : {
				        	"id":$($this).val(),
				        	"state":"4"
				        },
				        //请求成功
				        success : function(result) {
				            var json = JSON.parse(result);
				            if(json.state == 1){
				            	window.location.href="UIServlet?method=myProductUI&currentPageNum="+nowPage;
				            }else if(json.state == 0){
				            	alert("删除失败");
				            }
				        },
				        //请求失败，包含具体的错误信息
				        error : function(e){
				            alert("出错");
				        }
				   });
				}
			}
			
			
			function updateProduct($this){
				$.ajax({
			        //请求方式
			        type : "POST",
			        
					dateType:"json",
			        //请求地址
			        url : "ProductServlet?method=updateProductState",
			        //数据，json字符串
			        data : {
			        	"id":$($this).val(),
			        	"state":$($this).attr("state")
			        	},
			        //请求成功
			        success : function(result) {
			            var json = JSON.parse(result);
			            if(json.state == 1){
			            	if(json.nowState == 1){
			            		$("#showState"+$($this).val()).text("在售");
			            		$($this).text("下架");
			            		$($this).attr("state",1);
			            	}else if(json.nowState == 2){
			            		$("#showState"+$($this).val()).text("下架");
			            		$($this).text("上架");
			            		$($this).attr("state",2);
			            	}
			            }else if(json.state == 0){
			            	alert("更改失败");
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
