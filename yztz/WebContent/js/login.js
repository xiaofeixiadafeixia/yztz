$(function(){
	getPicCode();
   
   $("#img").click(function(){
   		getPicCode();
   });
   
   $("#login_btn").click(function(){
   		var userDate = {"method":"userLogin",
   				"email":$("#email").val().trim(),
   				"password":$("#password").val().trim(),
   				"vCode":$("#verificationCode").val().trim(),
   				"isAutoLogin":$("#isAutoLogin").prop("checked")?$("#isAutoLogin").val():null
   				};
		
		$.ajax({
	        //请求方式
	        type : "POST",
	        
			dateType:"json",
	        //请求地址
	        url : "UserServlet",
	        //数据，json字符串
	        data : userDate,
	        //请求成功
	        success : function(result1){
				var json = JSON.parse(result1);
				if(json.state == 1){
					window.location.href="UIServlet?method=mainUI";
			    }else if(json.state == 0){
			    	alert(json.errorMessage);
			    	getPicCode();
			    	document.getElementById("verificationCode").value="";
			    }else if(json.state == 2){
			    	window.location.href="UIServlet?method=lastRequestUrl";
			    }
			    	
	        },
	        //请求失败，包含具体的错误信息
	        error : function(e){
	            alert("出错");
	        }
	   });
   });
   
})

function getPicCode(){
	$.ajax({
        //请求方式
        type : "POST",
        
		dateType:"json",
        //请求地址
        url : "UserServlet",
        //数据，json字符串
        data : {
        	"method":"getPicVerificationCode"
       	},
        //请求成功
        success : function(result) {
            var json = JSON.parse(result);
		    if(json.state == 1){
				$("#img").attr("src",json.imgUrl);
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
