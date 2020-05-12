var $edit_btn;
var $calloff_btn;
var $finish_btn;
var $email_edit_btn;
var $code_call_off_btn;
var $update_head_pic_file_btn;//上传图片按钮

var $uName;//姓名框
var $uSex;//性别框
var $uEmail;//邮箱框
var $uInfo;//个人详情框
var $uCode;//验证码框
var $uLast_time;//编号
var $head_pic;//显示头像div
//-------------------------------------

var $addr_div;//地址模块
var $addr_add_div//添加地址模块
var $addr_show_div;//地址信息展示模块

var $tag_title//可修改文字之标题
var $tag_btn //可修改文字之按钮

var $address_tranself_id;
var $address_tranself_name;
var $address_tranself_addr;
var $address_tranself_phone;

var nowEmail;

$(function(){
	$uName = $("input[name='name']");
	$uSex = $("input[name='sex']");
	$uEmail = $("input[name='email']");
	$uInfo = $("textarea[name='info']");
	$uCode = $("input[name='code']");
	$uLast_time = $("#last_time");
	$head_pic = $("#head_pic");
	
	$edit_btn = $("#edit_btn");
	$calloff_btn = $("#calloff_btn");
	$finish_btn = $("#finish_btn");
	$email_edit_btn = $("#email_edit_btn");
	$code_call_off_btn = $("#code_call_off_btn");
	$update_head_pic_file_btn = $("#update_head_pic_file_btn");
	
	//-------------------------------------------------------------
	$addr_div = $("#addr_div");
	$addr_add_div = $("#addr_add_div");
	$addr_show_div = $("#addr_show_div");
	
	$tag_title = $("#tag_title");
	$tag_btn = $("#tag_btn");
	
	$address_tranself_id = $("#address_tranself_id");
	$address_tranself_name = $("#address_tranself_name");
	$address_tranself_phone = $("#address_tranself_phone");
	$address_tranself_addr = $("#address_tranself_addr");
	
	//‘编辑’按钮单击事件
	$edit_btn.click(function(){
			$uName.removeAttr("disabled");
			$uSex.removeAttr("disabled");
			$uInfo.removeAttr("disabled");
			$uCode.removeAttr("disabled");
			$update_head_pic_file_btn.removeAttr("disabled");
			$calloff_btn.removeClass("hidden");
			$finish_btn.removeClass("hidden");
			$(this).addClass("hidden");
			$email_edit_btn.removeClass("hidden");
			
	});
	
	
	//‘完成’按钮单击事件
	$finish_btn.click(function(){
		if(checkMessage()){
			updateUserRequest();
	//		$("#form").submit();
			
		}
		
	});
	
	//邮箱编辑按钮单击事件
	$email_edit_btn.click(function(){
		$(this).addClass("hidden").next().removeClass("hidden").next().removeClass("hidden");
		$uEmail.removeAttr("disabled");
		nowEmail = $uEmail.val();
	});
	
	//取消编辑邮箱
	$code_call_off_btn.click(function(){
		$(this).addClass("hidden").prev().removeClass("hidden");
		$(this).next().addClass("hidden");
		$uEmail.attr("disabled","disabled");
		$uEmail.val(nowEmail);
	});
	
	//关闭地址模块按钮单击事件
	$("#addr_edit_off_btn").click(function(){
		$addr_div.addClass("hidden");
	});
	//打开地址模块按钮单击事件
	$("#addr_edit_on_btn").click(function(){
		$addr_div.removeClass("hidden");
	});
	
	//展开添加地址模块
	$("#open_addr_add_btn").click(function(){
		$addr_show_div.css("height","400px");
		$(this).addClass("hidden");
		$tag_btn.text("添加");
		$tag_title.text("添加");
		
		$address_tranself_id.val("");
		$address_tranself_name.val("");
		$address_tranself_addr.val("");
		$address_tranself_phone.val("");
	})
	
	//关闭添加地址模块
	$("#addr_add_close_a").click(function(){
		$addr_show_div.css("height","510px");
		$("#open_addr_add_btn").removeClass("hidden");
	});
	
	//文件上传按钮
	$("#update_head_pic_file_btn").change(function(e){
		var file = e.target.files[0];

		if (file) {
		    var reader = new FileReader();
		    if(typeof FileReader != 'undefined'){      
			     if((file.type).indexOf("image/")==-1){  
			     	alert("请上传图片")  
			     	return ;
			    }  
			}else{  
			   var fileName=file.value;  S
			   var suffixIndex=fileName.lastIndexOf(".");  
			   var suffix=fileName.substring(suffixIndex+1).toUpperCase();  
			   if(suffix!="BMP"&&suffix!="JPG"&&suffix!="JPEG"&&suffix!="PNG"&&suffix!="GIF"){  
			    alert( "请上传图片（格式BMP、JPG、JPEG、PNG、GIF等）!");  
			   }  
			}  
	    
		    reader.onload = function (event) {
		       var txt = event.target.result;
		    	$("#head_pic").attr("src",txt);//将图片base64字符串赋值给img的src
		    };
		    reader.readAsDataURL(file);
	    }
	    
	});
	
	//邮箱验证码获取
	$("#code_get_btn").click(function(){
		if(checkEmail()){
			$("#code_get_btn").attr("disabled","disabled");
			$.ajax({
	            //请求方式
	            type : "POST",
	            
				dateType:"json",
	            //请求地址
	            url : "UserServlet?method=sendEmailVerificationCode",
	            //数据，json字符串
	            data : {"email":$uEmail.val().trim()},
	            //请求成功
	            success : function(result) {
	                var json = JSON.parse(result);
	                if(json.state == 1){
	                	alert("验证码发送成功，请注意接收！")
	                }else if(json.state == 0){
	                	alert(json.errorMessage);
	                }
	                $("#code_get_btn").removeAttr("disabled");
	            },
	            //请求失败，包含具体的错误信息
	            error : function(e){
	                alert("出错");
	                $("#code_get_btn").removeAttr("disabled");
	            }
	       });
		}
	})
	
	//自动获取用户在第一个学院里的地址
	getAddressRequest($('#college option:first').val());
	
	//学院选下拉框改变事件
	$("#college").change(function(){
		getAddressRequest($(this).find(':selected').val());
	});
	
	//地址提交按钮
	$tag_btn.click(function(){
		var regPhone= /^[0-9]{11}$/
		if(regPhone.test($address_tranself_phone.val()) && $address_tranself_name.val()!="" && $address_tranself_addr.val() !=""){
			var data_addr = {
				"address_id":$address_tranself_id.val(),
				"address_name":$address_tranself_name.val(),
				"address_phone":$address_tranself_phone.val(),
				"address_addr":$address_tranself_addr.val(),
				"address_college_id":$("#college").find(':selected').val()
			};
			$.ajax({
	            //请求方式
	            type : "POST",
	            
				dateType:"json",
	            //请求地址
	            url : "AddressServlet?method=addressSubmitOrUpdate",
	            //数据，json字符串
	            data : data_addr,
	            //请求成功
	            success : function(result) {
	                var json = JSON.parse(result);
	                if(json.state == "1"){
	                	alert("成功!")
	                	getAddressRequest($("#college").find(':selected').val());
	                }else if(json.state=="0"){
	                	alert("失败")
	                }
	            },
	            //请求失败，包含具体的错误信息
	            error : function(e){
	                alert("出错");
	            }
	       });
		}else{
			alert("信息不能为空 电话号码必须为11位!");
		}
		
	});
	
});

function checkEmail(){
	if(!($uEmail.val().match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/ ))){
		alert("请输入正确的邮箱格式");
		return false;
	}
	return true;
}
//禁用或隐藏控件
function input_disable(){
	$uName.attr("disabled","disabled");
	$uSex.attr("disabled","disabled");
	$uEmail.attr("disabled","disabled");
	$uInfo.attr("disabled","disabled");
	$uCode.attr("disabled","disabled");
	$update_head_pic_file_btn.attr("disabled","disabled");
	$email_edit_btn.addClass("hidden");
}

function getAddressRequest(college_id){
	$.ajax({
            //请求方式
            type : "POST",
            
			dateType:"json",
            //请求地址
            url : "AddressServlet?method=getAddressByUserWithCollege",
            //数据，json字符串
            data : {"college_id":college_id},
            //请求成功
            success : function(result) {
            	$addr_show_div.html("");
                var json = JSON.parse(result);
                
                for(i in json){
                	 $addr_show_div.append("<div style='padding:5px 20px 20px;margin: 20px 0px 0px;border:1px black dashed; border-radius: 20px;' address_id='"+json[i].address_id+"'>"+
						"<div style='width: 100%;height: 20px;'>"+
							"<div style='float: right;'><a name='update_address_a' onclick='updateAddress(this)' href='#'>修改</a>&nbsp;&nbsp;&nbsp;"+
								"<a name='delete_address_a' onclick='deleteAddress(this)' href='#'>删除</a></div></div>"+
						"<div><span style='margin-right: 30px;'>姓名：<label>"+json[i].pseudonym+"</label></span><span>电话：<label>"+json[i].phone+"</label></span></div>"+
						"<div><span>地址：<label>"+json[i].address+"</label></span></div></div>"
					);
                }
               
            },
            //请求失败，包含具体的错误信息
            error : function(e){
                alert("出错");
            }
       });
}


//ajax提交表单方式
function updateUserRequest(){
	var form = document.getElementById("form");
	var myform = new FormData(form);
	
	
	$.ajax({
		url:'UserServlet?method=userUpdate',
		type:'POST',
		data:myform,
		async: false,  
		cache: false, 
		contentType: false, //不设置内容类型
		processData: false, //不处理数据
		success:function(data){
			var json = JSON.parse(data);
            if(json.state == 1){
            	window.location.href="UIServlet?method=myCenterUI";
            }else if(json.state == 0){
            	alert(json.errorMessage);
            	$uCode.val("");
            }
		},
		error:function(){
			
		}
	})
}

//修改地址事件
function updateAddress($this){
	$tag_btn.text("修改");
	$tag_title.text("修改");
	$("#open_addr_add_btn").removeClass("hidden");
	$addr_show_div.css("height","400px");
	
	var $parentDiv = $($this).parent().parent().parent();
	
	$address_tranself_id.val($parentDiv.attr("address_id"));
	$address_tranself_name.val($parentDiv.find("label").eq(0).text());
	$address_tranself_phone.val($parentDiv.find("label").eq(1).text());
	$address_tranself_addr.val($parentDiv.find("label").eq(2).text());
	
}

//删除地址事件
function deleteAddress($this){
	var $parentDiv = $($this).parent().parent().parent();
	$.ajax({
            //请求方式
            type : "POST",
			dateType:"json",
            //请求地址
            url : "AddressServlet?method=deleteAddressByAddress_id",
            //数据，json字符串
            data : {"address_id":$parentDiv.attr("address_id")},
            //请求成功
            success : function(result) {
                var json = JSON.parse(result);
               	if(json.state=="1")
               		getAddressRequest($('#college').find(':selected').val());
               	else if(json.state == "0")
               		alert("删除失败")
            },
            //请求失败，包含具体的错误信息
            error : function(e){
                alert("出错");
            }
    });
}
