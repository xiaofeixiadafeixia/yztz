<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		
	<link rel="stylesheet" href="css/header.css" />
	
		<!--
            	描述：菜单栏
            -->
	<div class="container">
			<div class="row">
				<div class="col-md-6">
					<img src="${pageContext.request.contextPath}/img/yztz.png" class="img-responsive"/>
					
				</div>
				<div class="col-md-6" style="padding-top: 20px">
					
					<nav class="navbar navbar-default" role="navigation">
					    <div class="container-fluid">
						    <div class="navbar-header">
						        <a class="navbar-brand" href="#">与我相关</a>
						    </div>
						    <div>
						        <ul class="nav navbar-nav">
						            <c:if test="${empty user}">
										<li><a
											href="${pageContext.request.contextPath}/UIServlet?method=loginUI">登录</a></li>
										<li><a
											href="${pageContext.request.contextPath}/UIServlet?method=registerUI">注册</a></li>
									</c:if>
									<c:if test="${not empty user }">
										<li><a >我的消息</a></li>
										<li><a href="${pageContext.request.contextPath}/UIServlet?method=myCenterUI">个人中心</a></li>
										<li class="dropdown">
							                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
							                    	更多
							                    <b class="caret"></b>
							                </a>
							                <ul class="dropdown-menu">
							                     <li ><a href="UIServlet?method=orderUI">我的订单</a></li>
												<li ><a href="UIServlet?method=myProductUI">我的商品</a></li>
												<li><a href="${pageContext.request.contextPath}/UIServlet?method=myCollectionUI">我的收藏</a></li>
												<li class="divider"></li>
												<li id="remindSet"><a href="#">消息通知设置</a></li>
							                </ul>
							            </li>
										<li><a>${user.name }</a></li>
										<li><a href="${pageContext.request.contextPath}/UserServlet?method=userExit">退出</a></li>
									</c:if>
						            
						            
						        </ul>
						    </div>
					    </div>
					</nav>
					
				</div>
			</div>
		</div>
	<!--
            	描述：导航条
            -->
	<div class="container">
		<nav class="navbar navbar-inverse main-nav">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="UIServlet?method=mainUI">首页</a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					
					<ul class="nav navbar-nav" id="myul">

					</ul>
					
					<form class="navbar-form navbar-right" role="search" action="UIServlet">
					
						<div class="form-group">
							<input name="str" type="text" class="form-control" placeholder="商品的关键字">
							<input name="method" value="productListUI" class="hidden" />
						</div>
						<button type="submit" class="btn btn-default">搜索</button>
					</form>

				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>
	
	<!-- 消息提醒设置模块 -->
	<div id="remindDiv" class="hide" 
		style="z-index: 10; width: 500px; height: 300px; background-color: darkgoldenrod;position: absolute;margin: auto;top:0px;bottom: 0px;left: 0px;right: 0px;">
		<div id="remindDivClose" style="float:right;"><a href="#">关闭</a></div>
		<div style="text-align: center;">
			<label  style="font-size: 20px;">邮件消息提醒设置</label>
			<dl>
				<dt>
					<input type="checkbox" name="emailNote" value="64" />关闭邮件提醒
					<div><small>（关闭之后将无法收到任何邮件提醒）</small></div>
				</dt>
				<br />
				<dd>
					<input type="checkbox" name="emailNote" value="1"/>系统消息
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="emailNote" value="2"/>互动消息
				</dd>
				<br />
				<dd>
					<input type="checkbox" name="emailNote" value="4"/>好友消息
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="emailNote" value="8"/>陌生消息
				</dd>
				<br />
				<dd>
					<input type="checkbox" name="emailNote" value="16"/>验证消息
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="emailNote" value="32"/>订单消息
				</dd>
			</dl>
			<button id="updateRemind_type" class="btn btn-primary" style="background-color: greenyellow;">应用</button>
			
		</div>
	</div>
	
	
	<c:choose>
		<c:when test="${not empty user }">
			<script>
				var totalNoteValue=${user.remind_type };
			</script>
		</c:when>
		<c:otherwise>
			<script>
				var totalNoteValue=39;
			</script>
		</c:otherwise>
	</c:choose>
	<script type="text/javascript">
		
		var tempTotalNoteValue = totalNoteValue;//临时变量，用于记录用户的更改。
		$(function(){
			getHeader();
			
			$("#remindSet").click(function(){
				$("#remindDiv").removeClass("hide");
				
			});
			$("#remindDivClose").click(function(){
				$("#remindDiv").addClass("hide");
				tempTotalNoteValue = totalNoteValue;//关闭，说明用户取消了更改。所以把临时变量改为原来值
				calculationOptions();
			});
			
			$("input[name='emailNote']").change(function(){
				if($(this).is(':checked'))
					tempTotalNoteValue = Number($(this).val())+Number(tempTotalNoteValue);
				else
					tempTotalNoteValue = Number(tempTotalNoteValue)-Number($(this).val());
				if($(this).val()==64){
					if(tempTotalNoteValue>=64){
						$("input[name='emailNote']").not(":first").prop("disabled","disabled");
					}else{
						$("input[name='emailNote']").not(":first").removeAttr("disabled");
					}
				}
			});
			
			calculationOptions();//必须放在复选框改变事件后面自动调用。不然无法赋值
			
			$("#updateRemind_type").click(function(){
				$.ajax({
		            //请求方式
		            type : "POST",
		            
					dateType:"json",
		            //请求地址
		            url : "UserServlet?method=updateEmailNote",
		            //数据，json字符串
		            data : {"remind_type":tempTotalNoteValue},
		            //请求成功
		            success : function(result) {
		                var json = JSON.parse(result);
		                if(json.state == 1){
		                	totalNoteValue = tempTotalNoteValue;
		                	calculationOptions();
		                	alert("更改成功");
		                	$("#remindDiv").addClass("hide");
		                }else if(json.state == 0){
		                	tempTotalNoteValue = totalNoteValue;
		                	calculationOptions();
		                	alert("更改失败");
		                }
		            },
		            //请求失败，包含具体的错误信息
		            error : function(e){
		                alert("出错");
		            }
		       });
			});
			
		});
		
		function calculationOptions(){
			//每次调用这个方法，先把复选框选中状态清空，然后取消禁用状态
			if(tempTotalNoteValue<64){
				$("input[name='emailNote']").prop("checked",false);
				$("input[name='emailNote']").not(":first").removeAttr("disabled");
			}
			//1：系统消息	2：互动消息   4：好友消息  8：陌生消息    16：验证消息	32：订单消息  64：关闭邮件提醒 128：便于计算
			var arr = new Array(1,2,4,8,16,32,64,128);
			var filterArr = new Array();
			var filterArrIndex=0;
			var temp = totalNoteValue;
			for(var i = arr.length-1;i>=1;i--){
				if(temp>=arr[i-1]&&temp<=arr[i] && temp!=0){//如果比前一个元素大，比后一个元素小
					filterArr[filterArrIndex++] = arr[i-1];
					temp -= arr[i-1];
				}
				
			}
			for(var i= 0;i<filterArr.length;i++){
				switch(filterArr[i]){
					case 1:$("input[name='emailNote']").eq(1).prop("checked","checked");break;
					case 2:$("input[name='emailNote']").eq(2).prop("checked","checked");break;
					case 4:$("input[name='emailNote']").eq(3).prop("checked","checked");break;
					case 8:$("input[name='emailNote']").eq(4).prop("checked","checked");break;
					case 16:$("input[name='emailNote']").eq(5).prop("checked","checked");break;
					case 32:$("input[name='emailNote']").eq(6).prop("checked","checked");break;
					default:
						if(filterArr[i]>=64){
							$("input[name='emailNote']").not(":first").prop("disabled","disabled");
							$("input[name='emailNote']").eq(0).prop("checked","checked");
						}
				}
			}
			
		}
		
		function getHeader(){
			if(sessionStorage.getItem("Types")==undefined){
				$.post("ProductServlet",{"method":"getAllTypeOfProduct"},function(data){
					sessionStorage.setItem('Types',JSON.stringify(data));
					$.each(data,function(i,obj){
						$("#myul").append("<li><a href='${pageContext.request.contextPath}/UIServlet?method=productListUI&type_id="+obj.type_id+"&Num=1'>"+obj.name+"</a></li>");
					});
				},"json");
			}else{
				var Types = JSON.parse(sessionStorage.getItem("Types"))
				$.each(Types,function(i,obj){
					$("#myul").append("<li><a href='${pageContext.request.contextPath}/UIServlet?method=productListUI&type_id="+obj.type_id+"&Num=1'>"+obj.name+"</a></li>");
				});
			}
		}
		
	</script>