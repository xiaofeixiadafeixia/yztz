<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品详情</title>
		<script type="text/javascript" src="dist/js/jquery-3.3.1.js" ></script>
		<link rel="stylesheet" href="dist/css/bootstrap.css"/>
		<script type="text/javascript" src="dist/js/bootstrap.js" ></script>
		
		<link rel="stylesheet" href="css/product_info.css" />
		
		<script type="text/javascript" src="js/jquery.qqFace.js"></script>
		<link rel="stylesheet" href="css/reset.css" />
		<link rel="stylesheet" href="css/reset-1.css" />
	</head>
	<body>
	
	
		<%@include file="header.jsp" %>
		
		<div class="container">
			<div class="row">
				
				<div class="col-md-12">
					<ul class="breadcrumb" style="font-size: 20px; background-color: darkgray;margin-top: 10px;">
					    <span>当前地址：</span>
					    <li><a href="UIServlet?method=mainUI">主页</a></li>
					    <li><a href="${pageContext.request.contextPath}/UIServlet?method=productListUI&type_id=${product.type_product.type_id }&Num=1">${product.type_product.name }</a></li>
					    <li class="active">${product.name }</li>
					</ul>
				</div>
				
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-6">
							<div id="myCarousel" class="carousel slide">
							    <!-- 轮播（Carousel）指标 -->
							    <ol class="carousel-indicators">
							     	<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
							       <c:forEach var="pic" items="${product.pictures}" begin="1" varStatus="index">
							       		 <li data-target="#myCarousel" data-slide-to="${index.count }" ></li>
							       </c:forEach>
							        
							    </ol>   
							    <!-- 轮播（Carousel）项目 -->
							    <div class="carousel-inner">
							    	 <div class="item active">
							              <img src="${product.pictures.toArray()[0].url }" style="margin: auto;">
							          </div>
							       <c:forEach var="pic" items="${product.pictures}" begin="1">
							       	  <div class="item ">
							              <img src="${pic.url }" style="margin: auto;" >
							          </div>
							       </c:forEach>
							    </div>
							    <!-- 轮播（Carousel）导航 -->
							    <a class="carousel-control left" href="#myCarousel" data-slide="prev"> 
							    	<span _ngcontent-c3="" aria-hidden="true" class="glyphicon glyphicon-chevron-left" style="color: green;"></span>
							    </a>
							    <a class="carousel-control right" href="#myCarousel"  data-slide="next">
							    	<span _ngcontent-c3="" aria-hidden="true" class="glyphicon glyphicon-chevron-right" style="color: green;"></span>
							    </a>
							</div>
						</div>
						
						
						
						<div class="col-md-offset-1 col-md-5 ">
							<div class="product-message">
								<div class="row" style="margin: 0px;">
									
									<div class="col-md-12 margin-top">
										<div>
											<label>${product.name }</label>
										</div>
										<div class="margin-top">
											<div>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：${product.type_product.name}</div>
											
											<hr>
										</div>
									</div>
									<div class="col-md-12">
										<div style="margin-bottom: 10px;">成&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：${product.fineness }新</div>
										<div>
											出&nbsp;手&nbsp;价：<span style="color: red;font-size: 16px;"><strong>&yen;${product.price }</strong></span>
										</div>
										<hr>
									</div>
									<div class="col-md-12">
										<div>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：<span>${product.user.email }</span></div>
										<div>所在学院：${product.college.region}</div>
										<hr>
									</div>
									<div class="col-md-12">
										<div>当前收藏数：<span id="collectCount">${collectCount }</span></div>
										<hr />
									</div>
									<div class="col-md-6 ">
										<input type="text" value="${product.goods_id}" id="pid" style="display: none;"/>
										<c:choose>
											<c:when test="${isCanCollect eq('2') }">
												<div id="collectProduct_btn" class="a-none" style="cursor: pointer;text-align: center;color: blue;" >
													已收藏
												</div>
											</c:when>
											<c:when test="${isCanCollect eq('3') }">
												<div id="collectProduct_btn" class="a-none" style="cursor: pointer;text-align: center;color: blue;" >
													自己商品
												</div>
											</c:when>
											<c:when test="${isCanCollect eq('1') }">
												<div id="collectProduct_btn" class="a-none" style="cursor: pointer;text-align: center;color: blue;" onclick="collectProduct()">
													收藏商品
												</div>
											</c:when>
											<c:when test="${isCanCollect eq('4') }">
												<div id="collectProduct_btn" class="a-none" style="cursor: pointer;text-align: center;color: blue;" >
													用户已删除
												</div>
											</c:when>
										</c:choose>
										
									</div>
									
									<div class="col-md-12 margin-top">
										<strong>商品详情：</strong>
										<textarea class="text-area" rows="3" maxlength="200" readonly="readonly" disabled="disabled">${product.info }</textarea>
									</div>
									
								</div>
							</div>
						</div>
						
						
					</div> <!-- row -->
				</div><!-- col-md-12 -->
				
				
				
				
				<div class="col-md-8 col-md-offset-2" style="margin-top: 50px;">
					<div style="text-align: center;font-size: 30px; color: red;" id="leave_title">留言</div>
					<div class="shows" >
						<div>
							<img class="header_leave" src="img/enheng.jpg" style="width: 40px;height: 40px;"/>
							<span class="nick_leave" name=""></span>
							<span style="float: right;margin-top: 5px;" class="time_leave" >2019年10月14日16:19:32</span>
						</div>
						<hr />
						<div class="content_leave" >
							
						</div>
					</div>
					<div class="shows" >
						<div>
							<img class="header_leave" src="img/enheng.jpg" style="width: 40px;height: 40px;"/>
							<span class="nick_leave" name=""></span>
							<span style="float: right;margin-top: 5px;" class="time_leave" >2019年10月14日16:19:32</span>
						</div>
						<hr />
						<div class="content_leave" >
							
						</div>
					</div>
					<div class="shows" >
						<div>
							<img class="header_leave" src="img/enheng.jpg" style="width: 40px;height: 40px;"/>
							<span class="nick_leave" name=""></span>
							<span style="float: right;margin-top: 5px;" class="time_leave" >2019年10月14日16:19:32</span>
						</div>
						<hr />
						<div class="content_leave" >
							
						</div>
					</div>
					<div class="shows" >
						<div>
							<img class="header_leave" src="img/enheng.jpg" style="width: 40px;height: 40px;"/>
							<span class="nick_leave" name=""></span>
							<span style="float: right;margin-top: 5px;" class="time_leave" >2019年10月14日16:19:32</span>
						</div>
						<hr />
						<div class="content_leave" >
							
						</div>
					</div>
					<div class="shows" >
						<div>
							<img class="header_leave" src="img/enheng.jpg" style="width: 40px;height: 40px;"/>
							<span class="nick_leave" name=""></span>
							<span style="float: right;margin-top: 5px;" class="time_leave" >2019年10月14日16:19:32</span>
						</div>
						<hr />
						<div class="content_leave" >
							
						</div>
					</div>
					
					<div class="col-md-12" style="text-align: center;display: none;" id="liLeaveNote">
						共<span id="totalPage" style="color: red;">5</span>页，当前第<span style="color: red;" id="note_page_index">1</span>页
					</div>
					<div class="col-md-12" style="text-align: center;font-size: 30px;margin-top: 20px;" id="noLeaveNote">
						暂无留言，快来做第一人吧~
					</div>
					
					<div style="text-align: center;">
						<ul class="pagination" id="pager_li">
						    <li style="display: none;" ><a href="#leave_title" id="last_page" onclick="lastLiClick(this)">上一页</a></li>
						    <li style="display: none;"  class="active"><a href="#leave_title" class="lis" onclick="liClick(this)">1</a></li>
						    <li style="display: none;"><a href="#leave_title" class="lis" onclick="liClick(this)">2</a></li>
						    <li style="display: none;"><a href="#leave_title" class="lis" onclick="liClick(this)">2</a></li>
						    <li style="display: none;"><a href="#leave_title" class="lis" onclick="liClick(this)">3</a></li>
						    <li style="display: none;"><a href="#leave_title" class="lis" onclick="liClick(this)">4</a></li>
						    <li style="display: none;"><a href="#leave_title" class="lis" onclick="liClick(this)">5</a></li>
						    <li style="float: right;display: none;"><a href="#leave_title"leave_title id="next_page" onclick="nextLiClick(this)">下一页</a></li>
						</ul>
					</div>
					
					
				    <div class="comment">
				        <div class="com_form">
				        	<textarea maxlength="200" class="input" id="saytext" name="saytext"></textarea>
				        	<p><input type="button" class="sub_btn" value="提交"><span class="emotion">表情</span></p>
				        </div>
				    </div> 
				</div>
				
			</div><!-- row -->
		</div><!-- container -->
		
		<script type="text/javascript" src="js/product_info.js" ></script>
		<script>
		$(function(){
			$('.emotion').qqFace({
				id : 'facebox', 
				assign:'saytext', 
				path:'arclist/'	//表情存放的路径
			});
			$(".sub_btn").click(function(){
				$.ajax({
			        //请求方式
			        type : "POST",
			        
					dateType:"json",
			        //请求地址
			        url : "LeaveServlet?method=saveLeave",
			        //数据，json字符串
			        data : {
			        	"goods_id":$("#pid").val(),
			        	"content":$("#saytext").val()
			        },
			        //请求成功
			        success : function(result) {
			            var json = JSON.parse(result);
			            if(json.state == 1){
			            	currentNum = 1;
			            	getLeaves();
			            	$("#saytext").val("");
			            	alert("留言成功")
			            }else if(json.state == 0 && json.errorMessage == "NoUser"){
			            	window.location.href="UIServlet?method=loginUI";
			            }else if(json.state == 0){
			            	alert(json.errorMessage);
			            }
			        },
			        //请求失败，包含具体的错误信息
			        error : function(e){
			            alert("出错");
			        }
			   });
			});
			
		});
		//查看结果
		function replace_em(str){
			str = str.replace(/\</g,'&lt;');
			str = str.replace(/\>/g,'&gt;');
			str = str.replace(/\n/g,'<br/>');
			str = str.replace(/\[em_([0-9]*)\]/g,'<img src="arclist/$1.gif" border="0" />');
			return str;
		}
		
		function collectProduct(){
			$.ajax({
	            //请求方式
	            type : "POST",
	            
				dateType:"json",
	            //请求地址
	            url : "UIServlet?method=collectProduct&goods_id="+$("#pid").val(),
	            //数据，json字符串
	            data : {"product_id":$("#pid").val()},
	            //请求成功
	            success : function(result) {
	                var json = JSON.parse(result);
	                if(json.state == "1"){
	                	$("#collectProduct_btn").text("已收藏");
						$("#collectProduct_btn").removeAttr("onclick");
	                	$("#collectCount").text(Number($("#collectCount").text())+1);
	                	
	                }else if(json.state=="0"){
	                	if(json.errorMessage == "NoUser")
	                		window.location.href = "UIServlet?method=loginUI";
	                }
	            },
	            //请求失败，包含具体的错误信息
	            error : function(e){
	                alert("出错");
	            }
	       });
		}
		</script>
		<%@include file="footer.jsp" %>
		
	</body>
	
</html>


