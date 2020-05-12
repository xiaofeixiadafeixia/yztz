$(document).ready(function(){
	$("#passwordagain").blur(function(){
		checkPassWord();
	});
	$("#password").blur(function(){
		
	})
	$("#button1").click(function(){
		
		if(checkEmail()){
			$("#button1").attr("disabled","disabled");
			$("#button1").val("发送中...");
        	$.ajax({
	            //请求方式
	            type : "POST",
	            
				dateType:"json",
	            //请求地址
	            url : "UserServlet?method=sendEmailVerificationCode",
	            //数据，json字符串
	            data : {"email":$("#email").val().trim()},
	            //请求成功
	            success : function(result) {
	                var json = JSON.parse(result);
	                if(json.state == 1){
	                	alert("验证码发送成功，请注意接收！")
	                }else if(json.state == 0){
	                	alert(json.errorMessage);
	                }
	                $("#button1").removeAttr("disabled");
	                $("#button1").val("点我获取验证码");
	            },
	            //请求失败，包含具体的错误信息
	            error : function(e){
	                alert("出错");
	                 $("#button1").removeAttr("disabled");
	                 $("#button1").val("点我获取验证码");
	            }
	       });
	       
	       
		}
			
	});
	
	$("#button2").click(function(){
		if($("#name").val()===""){
			alert("用户名不能为空！")
		}else if(checkPassWord()&&checkPassWordAgain()&&checkEmail()&&checkVerificationCode()){
			$.ajax({
	            //请求方式
	            type : "POST",
	            
				dateType:"json",
	            //请求地址
	            url : "UserServlet?method=userResgiter",
	            //数据，json字符串
	            data : {
		            	"verificationCode":$("#verificationCode").val().trim(),
		            	"name":$("#name").val().trim(),
		            	"password":$("#password").val().trim()
	           		},
	            //请求成功
	            success : function(result) {
	                var json = JSON.parse(result);
	                if(json.state == 1){
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
		}
	});
});

function checkPassWord(){
	if($("#password").val().trim()===""){
		alert("密码不能为空!");
		return false;
	}else
		return true;
}
function checkEmail(){
	if(!($("#email").val().match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ ))){
		alert("请输入正确的邮箱格式");
		return false;
	}
	return true;
}

function checkPassWordAgain(){
	if(!($("#password").val()===$("#passwordagain").val())){
		alert("两次密码不一致");
		return false;
	}else
		return true;
}

function checkVerificationCode(){
	if($("#verificationCode").val().trim()===""){
		alert("请填写验证码！")
		return false;
	}
	return true;
}
