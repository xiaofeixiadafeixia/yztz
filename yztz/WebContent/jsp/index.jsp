<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>永职跳蚤</title>
		<script type="text/javascript" src="dist/js/jquery-3.3.1.js" ></script>
		<link rel="stylesheet" href="dist/css/bootstrap.css" />
		<script type="text/javascript" src="dist/js/bootstrap.js" ></script>
		<link rel="stylesheet" href="css/main.css" />
		<link rel="stylesheet" href="css/product.css" />
		
		<link rel="stylesheet" href="css/header.css" />
		<style type="text/css">
			
			#box{
				width: 100px;
				height: 300px;
				font-size:50px;
				position:absolute;
				bottom:0px;
				right: 0px;
				text-align: center;
			}
			#box1{
				width: 100px;
				height: 300px;
				font-size:50px;
				position:absolute;
				bottom:0px;
				text-align: center;
			}
		</style>
	</head>
	
	<body>
		<div id="box">有<br>广<br>告</div>
		<div id="box1">看<br>这<br>里</div>
		<%@ include file="header.jsp" %>
		
		<script>
			var box = document.getElementById("box");
			window.onscroll = function(){
				move(box,box1,10);
			}
			var boxheight = box.offsetHeight;//获取盒子高度
			
			
			var timer=null;
			
			function move(box,box1,speed){
				var docheight = document.documentElement.scrollTop;//获取滚动高度
				var winheight = document.documentElement.clientHeight;//获取可见区域高度
				window.clearInterval(timer);
				timer = window.setInterval(boxmove,30);
				var boxtop =  docheight+winheight-boxheight;//盒子的应该定位高度
				
				function boxmove(){
					var boxxiancha = 0;
					var upordown = boxtop - box.offsetTop;
					if(upordown>0){
						 boxxiancha = Math.ceil(upordown/speed);
					}else{
						boxxiancha = Math.floor(upordown/speed);
					}
						 
					if(box.offsetTop == boxtop){
						window.clearInterval(timer);
					}else{
						box.style.top = box.offsetTop + boxxiancha+"px";
						box1.style.top = box1.offsetTop + boxxiancha+"px";
					}
				}
			}
		</script>
		
		<!--                 身体                                  -->
		
		
		<div class="container" >
			<div class="row" style="margin-bottom: 20px;">
				<div class="col-md-3">
					<img class="head-img" src="img/QQ图片20191015100408.jpg" />
				</div>
				<div class="col-md-6">
					
					<div id="myCarousel" class="carousel slide">
					    <!-- 轮播（Carousel）指标 -->
					    <ol class="carousel-indicators" style="z-index: 9;">
					        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					        <li data-target="#myCarousel" data-slide-to="1"></li>
					        <li data-target="#myCarousel" data-slide-to="2"></li>
					    </ol>   
					    <!-- 轮播（Carousel）项目 -->
					    <div class="carousel-inner">
					        <div class="item active">
					            <img src="img/5eef6257gw1f1xerz6mhhg207m07mu0x.gif" alt="First slide">
					            <div class="carousel-caption">癞皮狗</div>
					        </div>
					        <div class="item">
					            <img src="img/5U0sD4OPT0SpFfj7dVObmbYUzZvbczfM.gif" alt="Second slide">
					            <div class="carousel-caption">天女散花</div>
					        </div>
					        <div class="item">
					            <img src="img/baf4de57697f83e1fab8e817ce01940a.jpg" alt="Third slide">
					            <div class="carousel-caption">跳蚤</div>
					        </div>
					    </div>
					    <!-- 轮播（Carousel）导航 -->
					    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
					        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					        <span class="sr-only">Previous</span>
					    </a>
					    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
					        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					        <span class="sr-only">Next</span>
					    </a>
					</div><!--  轮播图模块  -->
				</div>
				<div class="col-md-3">
					<img class="head-img" src="img/sleep.jpg" />
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-md-12">
					<div class="new-product">新上架商品</div>
				</div>
				
				<c:forEach items="${newProducts }" var="p">
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
			
			<div class="row">
				<div class="col-md-12">
					<div class="hot-product">🔥全部商品🔥</div>
				</div>
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
