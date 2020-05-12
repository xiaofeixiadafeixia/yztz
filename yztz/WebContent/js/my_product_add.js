   var results;
   var foot=0;
   var form ;
   var mypic ;
   var myform;
   var imgs ;
   var divs ;
   var messagespan;
   var messagediv;
   var messageindex=0;
   var pic_count=0;
 window.onload = function(){
  	results = new Array();
	form =document.getElementById("myform");//获取表单元素
	
	mypic = new FormData();//用于暂存图片数据
  	imgs = $("img").not(":eq(0)");//获取所有图片元素     注意：这里过滤了第一个图片，因为这是header.jsp里面有一个img。
  	divs =  document.getElementById("all-div-img").children
  	
  	messagespan = document.getElementById("message");//获取上传时的提示框
  	messagediv = document.getElementById("messagediv");
  	for(var x=0;x<divs.length;x++){
  		results[x] = divs[x].children[0];
  	}
  	getTypes();
  	getColleges();
  }
 window.onscroll = function(){
 	messageDivHeight();
 }
  function messageDivHeight(){
  	var ch =  document.documentElement.clientHeight ;//网页可见区域高
  	$(messagediv).css("top",(ch/2)+document.documentElement.scrollTop+"px")
  }
  
function ProcessFile(e) {
    var file = document.getElementById('file').files[0];
    if (file) {
	    var reader = new FileReader();
	    if(typeof FileReader != 'undefined'){      
		     if((file.type).indexOf("image/")==-1){  
		     	alert("请上传图片")  
		     	return ;
		    }  
		}else{  
		   var fileName=file.value;  
		   var suffixIndex=fileName.lastIndexOf(".");  
		   var suffix=fileName.substring(suffixIndex+1).toUpperCase();  
		   if(suffix!="BMP"&&suffix!="JPG"&&suffix!="JPEG"&&suffix!="PNG"&&suffix!="GIF"){  
		    alert( "请上传图片（格式BMP、JPG、JPEG、PNG、GIF等）!");  
		   }  
		}  
//		messageDivHeight();
    	messagediv.style.display = "block";//提示框显示
    	setWaitContent();//手动调用一次
    	var setWaitContentTime = window.setInterval(setWaitContent,1000);
    	
	    reader.onload = function (event) {
	       var txt = event.target.result;
	       for(var x=0;x<imgs.length;x++){
	    		if(imgs[x].getAttribute("src")==""){
	    			foot = x;
	    			break;
	    		}else if(x===imgs.length-1){
	    			alert("超出最大图片数")
	    			window.clearInterval(setWaitContentTime);//清除提示框动画事件
	    			messagediv.style.display = "none";//提示框隐藏
	    			return;
	    		}
	    	}
	    	imgs[foot].src = txt;//将图片base64字符串赋值给img的src
	    	mypic.append(results[foot].getAttribute("bie"),file);
	    	divs[foot].classList.remove("hide");
	    	$("#pic_count").text(++pic_count);
	    	window.clearInterval(setWaitContentTime);//清除提示框动画事件
	    	messagediv.style.display = "none";//提示框隐藏
	    };
    }
    reader.readAsDataURL(file);
	
}

//用于初始化上传文件按钮事件
function contentLoaded() {
      document.getElementById('file').addEventListener('change',ProcessFile,false);
}

window.addEventListener("DOMContentLoaded", contentLoaded, false);

function x(a){//删除图片事件
	mypic.delete(a.parentNode.getAttribute("bie"));
	imgs[a.parentNode.getAttribute("index")].src="";
	divs[a.parentNode.getAttribute("index")].classList.add("hide");
	$("#pic_count").text(--pic_count);
}

function submitform(){
	if(checkMessage()){
		$("#submit").attr("disabled","disabled");
		myform = new FormData(form);//获取表单元素数据
		myform.append("type_id",$("#type_select").find(':selected').val());
		myform.append("college_id",$("#college_select").find(':selected').val());
		for(var x=0;x<divs.length;x++){
			var data = mypic.get(results[x].getAttribute("bie"));
			if(data!=null){
				myform.append(results[x].getAttribute("bie"),data);
			}
	  		
	    }
//		messageDivHeight()
		messagediv.style.display = "block";
		setNoteContent();//因为动画有一秒钟的启动间隔,所以手动调用一次来补充空白
		var setNoteContentTime = window.setInterval(setNoteContent,1000);
		
		$.ajax({
			url:'ProductServlet?method=saveProduct',
			type:'POST',
			data:myform,
			async: true,  
			cache: false, 
			contentType: false, //不设置内容类型
			processData: false, //不处理数据
			success:function(data){
				window.clearInterval(setNoteContentTime);//清除等待滚动事件
				$("#submit").removeAttr("disabled");
				var json = JSON.parse(data);
                if(json.state == 1){
                	messagespan.textContent="上传成功！";
                	clear();//清空
                }else if(json.state == 0){
                	messagespan.textContent=json.errorMessage;
                }
               window.setTimeout(function(){//定时关闭提示框
                	messagediv.style.display = "none";
               },3000);
			},
			error:function(){
				window.clearInterval(setNoteContentTime);
				messagespan.textContent = "请求错误...即将跳转";
				setTimeout(jumurl,3000);
				$("#submit").removeAttr("disabled");
			}
		})
	}
}
//清除所有的信息
function clear(){
	myform = null;
	mypic=new FormData();
	pic_count=0;
	$("img").not(":eq(0)").each(function(){
		$(this).prop("src","");
	});
	for(var i=0;i<divs.length;i++){
		divs[i].classList.add("hide");
	}
	$("#pic_count").text(pic_count);//显示图片个数清零
	
	$("input[name='pname']").val("");
	$("input[name='pfineness']").val("");
	$("input[name='pprice']").val("");
	$("textarea[name='pdetails']").val("");
	$("#type_select").find(':first').prop("selected","selected");
	$("#college_select").find(':first').prop("selected","selected");
}
//检查信息是否正确
function checkMessage(){
	var pfineness = $("input[name='pfineness']").val();
	var pprice = $("input[name='pprice']").val();
	if($("input[name='pname']").val().trim()==""){
		alert("商品名称不能为空");
		return false;
	}
	if(isNaN(parseFloat(pfineness))||parseFloat(pfineness)>10.00 || parseFloat(pfineness)<0.00){
		alert("'成色' 的值不对!")
		return false;
	}
	if(isNaN(parseFloat(pprice))||parseFloat(pprice)>99999999.99 || parseFloat(pprice)<0.00){
		alert("'价格' 的值不对!")
		return false;
	}
	if($("#type_select").find(':selected').val()=="no"){
		alert("请选择商品类别");
		return false;
	}
	if($("#college_select").find(':selected').val()=="no"){
		alert("请选择商品所在学院");
		return false;
	}
	if(pic_count<1){
		alert("请上传至少一张图片");
		return false;
	}
	return true;
}

//页面跳转
function jumurl(){
　　window.location.href = 'UIServlet?method=myCenterUI';
}

//提交时的等待动画
var setNoteContent = function (){
	if(messageindex==7)
		messageindex = 0;
	var str = "上传中，请稍等";
	for(var x=0;x<messageindex;x++){
		str += ".";
	}
	messageindex++;
	messagespan.textContent = str;
}
//加载图片时的等待动画
var setWaitContent = function(){
	if(messageindex==7)
		messageindex = 0;
	var str = "图片正在加载,请稍后.";
	for(var x=0;x<messageindex;x++){
		str += ".";
	}
	messageindex++;
	messagespan.textContent = str;
}

//获取商品类别
function getTypes(){
	if(sessionStorage.getItem("Types")==undefined){
		$.post("ProductServlet",{"method":"getAllTypeOfProduct"},function(data){
			$.each(data,function(i,obj){
				$("#type_select").append("<option value='"+obj.type_id+"'>"+obj.name+"</option>");
			});
		},"json");
	}else{
		var Types = JSON.parse(sessionStorage.getItem("Types"))
		$.each(Types,function(i,obj){
			$("#type_select").append("<option value='"+obj.type_id+"'>"+obj.name+"</option>");
		});
	}
}
//获得所有学院
function getColleges(){
	$.post("AddressServlet",{"method":"getColleges"},function(data){
			$.each(data,function(i,obj){
				$("#college_select").append("<option value='"+obj.college_id+"'>"+obj.region+"</option>");
			});
	},"json");
}

