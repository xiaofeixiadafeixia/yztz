
$(function(){
	//编辑按钮
	$("#exitbtn").click(function(){
		$("#option").css("display","inline-block");
		$(this).css("display","none");
		$("input[name='isCheck']").css("display","block");
	});
	//完成按钮
	$("#achieve").click(function(){
		$("#option").css("display","none");
		$("#exitbtn").css("display","inline-block");
		$("input[name='isCheck']").css("display","none");
	});
	
	//全选按钮
	var flag = true;
	$("#check_all").click(function(){
		if(flag){
			$("input[name='isCheck']").prop("checked",true);
			flag = false;
			$(this).text("全不选");
		}else{
			$("input[name='isCheck']").prop("checked",false);
			flag = true;
			$(this).text("全选");
		}
		
	});
	
	//移除按钮
	$("#remove_btn").click(function(){
//		var products = new Array();
//		$.each($(":checked"),function(index,ele){
//			products[index] = ele;
//		})
//		$.each(products,function(index,ele){
//			$(ele).parent().parent().parent().remove();
//		})
		if(window.confirm("确定删除吗？"))
			$("#formCollect").prop("action","CollectServlet?method=deleteCollect").submit();
	});
	
	//下单按钮
	$("#order_btn").click(function(){
		$("#addr_div").removeClass("hidden");
	});
	
	
	
});

function Order(address_id){
	$addr_div.addClass("hidden");
	if(window.confirm("系统会自动过滤掉  状态 不是为 “在售” 的收藏")){
		var isCheck = new Array();
		$.each($("input[name='isCheck']:checked"),function(index,ele){
			isCheck[index] = $(ele).val();
		});
	   
	   $.ajax({
	        //请求方式
	        type : "POST",
	        
			dateType:"json",
	        //请求地址
	        url : "OrderServlet?method=saveOrder",
	        
	        //数据，json字符串
	        data : {
	        	"isCheck":JSON.stringify(isCheck),
	        	"address_id":address_id
	        },
	        //请求成功
	        success : function(result) {
	        	var json = JSON.parse(result);
               	if(json.state=="1"){
               		window.location.href="UIServlet?method=orderUI";
               	}
               	else if(json.state == "0"){
               		alert(json.errorMessage);
               	}
               		
	        },
	        //请求失败，包含具体的错误信息
	        error : function(e){
	            alert("出错");
	        }
	   });
	}
}

var $addr_div;//地址模块
var $addr_add_div//添加地址模块
var $addr_show_div;//地址信息展示模块

var $tag_title//可修改文字之标题
var $tag_btn //可修改文字之按钮

var $address_tranself_id;
var $address_tranself_name;
var $address_tranself_addr;
var $address_tranself_phone;


$(function(){

	
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

	
	//关闭地址模块按钮单击事件
	$("#addr_edit_off_btn").click(function(){
		$addr_div.addClass("hidden");
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
						"<div><span style='margin-right: 30px;'>姓名：<label>"+json[i].pseudonym+"</label></span><span>电话：<label>"+json[i].phone+"</label></span><span style='float:right'><a href='#' onclick='Order("+json[i].address_id+")'>当下单地址</a></span></div>"+
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
