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
		
		<link rel="stylesheet" href="css/my_product_add_update.css"/>
	</head>
	<body>
		<%@include file="header.jsp" %>
	
		<div class="container" >
			<div class="row">
				<div class="col-md-12">
					<ul class="breadcrumb" style="font-size: 20px; background-color: darkgray;margin-top: 10px;">
					    <span>当前地址：</span>
					    <li><a href="#">主页</a></li>
					    <li><a href="#">我的商品</a></li>
					    <li class="active">上传商品</li>
					</ul>
				</div>
			</div>
			
			<form id="myform" action="imgServlet" enctype="multipart/form-data" method="post" >
				<div class="row">
					<div class="col-md-12">
						<span style="font-size: 25px;">上传商品</span>
						<button id="submit" style="float: right;" type="button" class="btn btn-primary" onclick="submitform()" > 
								完成
						</button>
					</div>
				</div>
				<hr />
				
				<div class="row">
					<div class="col-md-3">
						<span>商品名称：</span><input type="text" name="pname" placeholder="请输入商品名称！" maxlength="15" />
					</div>
					<div class="col-md-3">
						<span>成色：</span><input type="text" name="pold" placeholder="新旧程度进行简要描述！" maxlength="10"/>
					</div>
					<div class="col-md-3">
						<span>出手价：</span><input  type="number" name="pprice" placeholder="请输入商品价格！" maxlength="10" min="0.00" step="1"/>
					</div>
					<div class="col-md-3">
						<span>类别：</span>
						<select style="text-align:center;text-align-last:center;">
							<option>---请选择---</option>
						</select>
					</div>
					<div class="col-md-12">
						<span>商品详情：</span>
						<textarea class="text-area" rows="3" maxlength="200" name="pdetails" ></textarea>
					</div>
				</div>
				
				
				<hr />
				<div class="row">
					<div class="col-md-12">
						选择商品图片：<input type="file" id="file" />
						<input type="button" value="测试图片数据" onclick="test()"/>
						<input type="button" value="测试表单数据" onclick="testform()"/>
					</div>
				</div>
				<div id="all-div-img" class="row">
					<div class="col-md-4 hide position-re" >
						<div   bie="result0" index="0" >
							<img src="" class=".position-ab "/>
							<div class=" shutdown-img" onclick="x(this)">x</div>
						</div>
					</div>
					<div class="col-md-4 hide position-re" >
						<div   bie="result1" index="1">
							<img src="" class=".position-ab "/>
							<div class=" shutdown-img" onclick="x(this)">x</div>
						</div>
					</div>
					<div class="col-md-4 hide position-re" >
						<div   bie="result2" index="2">
							<img src="" class=".position-ab "/>
							<div class=" shutdown-img" onclick="x(this)">x</div>
						</div>
					</div>
					<div class="col-md-4 hide position-re" >
						<div   bie="result3" index="3">
							<img src="" class=".position-ab "/>
							<div class=" shutdown-img" onclick="x(this)">x</div>
						</div>
					</div>
					<div class="col-md-4 hide position-re" >
						<div   bie="result4" index="4">
							<img src="" class=".position-ab "/>
							<div class=" shutdown-img"  onclick="x(this)">x</div>
						</div>
					</div>
					
				</div>
				
				
				
				
			</form>
			
		</div>
		
		
		 <!--用于提交数据时的提示操作。如：请等待。。。。。-->
		
		<div id="messagediv" style="border: 1px solid red;text-align: center; width: 500px; height:30px; display: none;
			position: absolute;top: 0px;bottom: 0px;left: 0px;right: 0px;margin: auto;">
			<span id="message"></span>
		</div>
		
		<%@include file="footer.jsp" %>
	</body>
</html>
<script type="text/javascript" src="js/my_product_add.js"></script>