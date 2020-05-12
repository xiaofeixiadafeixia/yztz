<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>上传商品</title>
		<script type="text/javascript" src="dist/js/jquery-3.3.1.js" ></script>
		<link rel="stylesheet" href="dist/css/bootstrap.css"/>
		<script type="text/javascript" src="dist/js/bootstrap.js" ></script>
		
		<link rel="stylesheet" href="css/my_product_add.css"/>
	</head>
	<body>
		<%@include file="header.jsp" %>
	
		<div class="container" >
			<div class="row">
				<div class="col-md-12">
					<ul class="breadcrumb" style="font-size: 20px; background-color: darkgray;margin-top: 10px;">
					    <span>当前地址：</span>
					    <li><a href="UIServlet?method=myCenterUI">主页</a></li>
					    <li><a href="UIServlet?method=myProductUI">我的商品</a></li>
					    <li class="active">上传商品</li>
					</ul>
				</div>
			</div>
			
			<form id="myform" action="" enctype="multipart/form-data" method="post" >
				<div class="row">
					<div class="col-md-12">
						<span style="font-size: 25px;">上传商品<small>(上传完毕后可在 “我的商品” 中查看)</small></span>
						<button id="submit" style="float: right;" type="button" class="btn btn-primary" onclick="submitform()" > 
								完成
						</button>
					</div>
				</div>
				<hr />
				
				<div class="row">
					<form class="form-inline">
						<div class="col-md-4 form-group">
							<span>商品名称：</span><input class="form-control"  type="text" name="pname" placeholder="请输入商品名称！" maxlength="20" />
						</div>
						<div class="col-md-2 form-group" >
							<span>成色(例如：9.9)：</span><input class="form-control" type="number" name="pfineness" placeholder="请输入商品成色！" maxlength="3" max="10.00" min="1.00" step="1"/>
						</div>
						<div class="col-md-2 form-group">
							<span>出手价：</span><input class="form-control" type="number" name="pprice" placeholder="请输入商品价格！" maxlength="10" min="0.00" step="1" max="99999999.99"/>
						</div>
						<div class="col-md-2 form-group">
							<span>商品类别：</span>
							<select id="type_select" class="form-control"  style="text-align:center;text-align-last:center;">
								<option value="no">---请选择---</option>
							</select>
						</div>
						<div class="col-md-2 form-group">
							<span>商品所在院区：</span>
							<select id="college_select" class="form-control"  style="text-align:center;text-align-last:center;">
								<option value="no">---请选择---</option>
							</select>
						</div>
						<div class="col-md-12 form-group">
							<span>商品详情：</span>
							<textarea class="text-area form-control" rows="3" maxlength="2000" name="pdetails" ></textarea>
						</div>
					</form>
				</div>
				
				
				<hr />
				<div class="row">
					<div class="col-md-12">
						选择商品图片：(最少上传一张，最多上传五张，当前：<span id="pic_count" style="color: red;">0</span>/5)<input type="file" id="file" />
					</div>
					
				</div>
				
				<div id="all-div-img" class="row">
					<div class="col-md-4 hide position-re" >
						<div   bie="result0" index="0" >
							<img src="" class="position-ab "/>
							<div class=" shutdown-img" onclick="x(this)">x</div>
						</div>
					</div>
					<div class="col-md-4 hide position-re" >
						<div   bie="result1" index="1">
							<img src="" class="position-ab "/>
							<div class=" shutdown-img" onclick="x(this)">x</div>
						</div>
					</div>
					<div class="col-md-4 hide position-re" >
						<div   bie="result2" index="2">
							<img src="" class="position-ab "/>
							<div class=" shutdown-img" onclick="x(this)">x</div>
						</div>
					</div>
					<div class="col-md-4 hide position-re" >
						<div   bie="result3" index="3">
							<img src="" class="position-ab "/>
							<div class=" shutdown-img" onclick="x(this)">x</div>
						</div>
					</div>
					<div class="col-md-4 hide position-re" >
						<div   bie="result4" index="4">
							<img src="" class="position-ab "/>
							<div class=" shutdown-img"  onclick="x(this)">x</div>
						</div>
					</div>
					
				</div>
				
				
			</form>
			
		</div>
		
		
		 <!--用于提交数据时的提示操作。如：请等待。。。。。-->
		
		<div id="messagediv" style="border: 1px solid red;text-align: center; width: 500px; height:30px; display: none;background-color: black; z-index: 10;
			position: absolute;top: 0px;bottom: 0px;left: 0px;right: 0px;margin: auto;color: yellow;">
			<span id="message"></span>
		</div>
		
		<%@include file="footer.jsp" %>
	</body>
</html>
<script type="text/javascript" src="js/my_product_add.js"></script>