<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>个人中心</title>
		<script type="text/javascript" src="dist/js/jquery-3.3.1.js" ></script>
		<link rel="stylesheet" href="dist/css/bootstrap.css"/>
		<script type="text/javascript" src="dist/js/bootstrap.js" ></script>
		
		<link rel="stylesheet" href="css/product.css" />
		<link rel="stylesheet" href="css/my_center.css" />
		<script type="text/javascript" src="js/my_center.js" ></script>
		<script>
			$(function(){
				refreshUser();
				
				
				//‘取消‘按钮单击事件
				$calloff_btn.click(function(){
					$finish_btn.addClass("hidden");
					$(this).addClass("hidden")
					$edit_btn.removeClass("hidden");
					$code_call_off_btn.addClass("hidden").next().addClass("hidden");
					
//					$uEmail.val(nowEmail);
					refreshUser();
					input_disable();
				});
				
			})
			
			function refreshUser(){
				$uName.val("${user.name}") ;
				$uEmail.val("${user.email}") ;
				$uInfo.val("${user.info}") ;
				$uLast_time.text("<fmt:formatDate pattern="yyyy年MM月dd日 HH时mm分ss秒"   value="${user.last_date}" />") ;
				$head_pic.attr("src","${user.pic}") ;
				$update_head_pic_file_btn.val("");
				if("${user.sex}" == "女"){
					$("input[name='sex'][value='女']").prop("checked","checked");
				}else
					$("input[name='sex'][value='男']").prop("checked","checked");
					
				nowFile="";
			}
			
			function checkMessage(){
				if($uName.val().trim()==""){
					alert("用户名不能为空");
					return false;
				}
				if($uEmail.val()!="${user.email}"){
					if(!checkEmail()){
						return false;
					}
					if($uCode.val().trim()===""){
						alert("请填写验证码！")
						return false;
					}
				}
				if($head_pic.attr("src")!="${user.pic}" && $update_head_pic_file_btn.val()==""){
					alert("头像不能为空");
					$head_pic.attr("src",null);
					return false;
				}
				return true;
			}
		</script>
	</head>
	
	<body>
		
		<%@include file="header.jsp" %>
		
		<!-- 地址模块 -->
		<div id="addr_div" class="hidden" style="overflow:hidden;z-index: 10; height: 560px;width: 500px;background-color:#D9EDF7; position: absolute;margin: auto;top: 0px;bottom: 0px;left: 0px;right: 0px;">
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
             
		
		
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ul class="breadcrumb" style="font-size: 20px; background-color: darkgray;margin-top: 10px;">
					    <span>当前地址：</span>
					    <li><a href="UIServlet?method=mainUI">主页</a></li>
					    <li class="active">个人中心</li>
					    
					   
					</ul>
					
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					
					<div class="font-size-25">
						<span>个人信息</span>
						<span style="float: right;">
							<button id="edit_btn" type="button" class="btn btn-primary"  data-toggle="button" > 
								编辑
							</button>
							<button id="calloff_btn" type="button" class="btn btn-primary hidden"  data-toggle="button"> 
								取消
							</button>
							<button id="finish_btn" type="button" class="btn btn-primary hidden"  data-toggle="button"> 
								完成
							</button>
						</span>
					</div>
					<hr />
					
					
					<div class="row">
						<div class="col-md-3">
							<img id="head_pic" class="img-responsive" src="" />
							
						</div>
						<div class="col-md-8 col-md-offset-1">
							<div class="row">
								
								<form id="form" action="UserServlet?method=userUpdate" method="post" enctype="multipart/form-data">
									<div class="col-md-6">
										<div class="div-margin">
											<span>昵&nbsp;&nbsp;称：</span>
											<input style="border-radius: 5px;" name="name" maxlength="10" type="text" disabled="disabled"  value=""/>
										</div>
										<div >
											<span>性&nbsp;&nbsp;别：</span>
											<input name="sex" type="radio" checked="checked" value="男" disabled="disabled"/>男
											<input name="sex" style="margin-left: 50px;"  type="radio" value="女" disabled="disabled"/>女
										</div>
										<div class="col-md-12">
											<div class="div-margin">
												<input type="button" id="addr_edit_on_btn" value="编辑地址信息" />
											</div>
										</div>
										
									</div>
									<div class="col-md-6">
										<div class="div-margin">
											<span>邮&nbsp;箱：</span>
											<input style="border-radius: 5px;"  name="email" maxlength="30"  type="text" placeholder="邮箱不能为空" disabled="disabled" value=""/>
											<input id="email_edit_btn" type="button" value="编辑" class="hidden"/>
											<input id="code_call_off_btn" type="button" value="取消"   class="hidden"/>
											<div class="hidden">
												<span>验证码:</span>
												<input style="border-radius: 5px;"  name="code" type="text" value="" placeholder="请输入邮箱收到的验证码" />
												<input id="code_get_btn" type="button" value="获取" />
											</div>
											
										</div>
										<div class="div-margin">
											<span>上次登录：</span>
											<label id="last_time" disabled="disabled"></label>
										</div>
									</div>
									
									<div class="col-md-12 div-margin">
										<span>更改头像：</span><input id="update_head_pic_file_btn" name="pic" disabled="disabled" type="file" style="display: inline-block;"/>
									</div>
									
									<div class="col-md-12">
										<div>个人说明</div>
										<textarea style="border-radius: 5px;"  name="info" disabled="disabled" maxlength="50"></textarea>
									</div>
								</form>
								
								
								
							</div>
						</div>
					</div>
					
				</div>
			</div><!-- row -->
			

			
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<div class="font-size-25 div-margin">
						<span>最近浏览</span>
					</div>
					<hr />
					
					<c:forEach items="${browseHistorys }" var="bh">
						<div class="col-md-2 product">
							<a href="UIServlet?method=productInfoUI&goods_id=${bh.browseHistoryUionPKID.product.goods_id }">
								<img class="product-img" src="${bh.browseHistoryUionPKID.product.pictures.toArray()[0].url }" 
									alt="${bh.browseHistoryUionPKID.product.name }"/>
								
								<div class="product-intro">
									${bh.browseHistoryUionPKID.product.name }
								</div>
							</a>
							<div class="product-price">
								<span>&yen;：${bh.browseHistoryUionPKID.product.price }</span>
							</div>
						</div>
					</c:forEach>
					
					
				
				</div><!-- col-md-10 col-md-offset-1 -->
			</div> <!--row -->
			
			
		</div><!--container-->
		
		<%@include file="footer.jsp" %>
		
	</body>
</html>
