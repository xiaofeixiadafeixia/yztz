var totalPage;//总留言页数
var total ;//留言记录总数
var currentNum;//当前页
var pageNum;//每个留言个数

var shows;//总留言框
var header_leaves;
var nick_leaves;
var time_leaves;
var content_leaves;
var lis ;
$(function(){
	currentNum=1;
	
	shows = $(".shows");
	pageNum = shows.length;
	
	header_leaves = $(".header_leave");
	nick_leaves = $(".nick_leave");
	time_leaves = $(".time_leave");
	content_leaves = $(".content_leave");
	lis = $(".lis");
	
	getLeaves();
	
});
function liClick($this){
	currentNum = Number($($this).text());
	getLeaves();
}
function lastLiClick($this){
	currentNum -=1;
	getLeaves();
}
function nextLiClick($this){
	currentNum +=1;
	getLeaves();
}
//此方法需要用到当前页，与总页数
function setlis(){
	var start;
	for(var i=0;i<5;i++)
		$(lis[i]).parent().removeClass("active");
		
	if(totalPage>=5){//总页数大于等于5
		if(currentNum<3){
			start = 1;
		}else if(currentNum> totalPage-2){
			start = totalPage-4;
		}else{
			start=currentNum-2;
		}
		
		for(var i=0;i<5;i++){
			$(lis[i]).parent().css("display","inline-block");
			if(start==currentNum)
				$(lis[i]).parent().addClass("active");
			$(lis[i]).text(start++);
		}
	}else{
		for(var i=0;i<totalPage;i++){
			$(lis[i]).parent().css("display","inline-block");
			if(i+1==currentNum)
				$(lis[i]).parent().addClass("active");
			$(lis[i]).text(i+1);
		}
	}
	
	if(currentNum>1)
		$("#last_page").parent().css("display","inline-block");
	else
		$("#last_page").parent().css("display","none");
		
	if(currentNum<totalPage)
		$("#next_page").parent().css("display","inline-block");
	else
		$("#next_page").parent().css("display","none");
	
}

function getLeaves(){
	$.ajax({
        //请求方式
        type : "POST",
        
		dateType:"json",
        //请求地址
        url : "LeaveServlet?method=getLeaves",
        //数据，json字符串
        data : {
        	"goods_id":$("#pid").val(),
        	"pageNum":pageNum,
        	"currentNum":currentNum
        },
        //请求成功
        success : function(result) {
            var json = JSON.parse(result);
            total = json.total;
            if(total>0){
            	$("#noLeaveNote").css("display","none");
            	$("#liLeaveNote").css("display","inline-block");
            }else{
            	$("#noLeaveNote").css("display","inline-block");
            	$("#liLeaveNote").css("display","none");
            }
            //显示总页数与当前页数
            totalPage =  total%5==0?total/5:Math.floor(total/5+1);
            $("#totalPage").text(totalPage);
            $("#note_page_index").text(currentNum);
            
            //这里要先全部隐藏掉留言框
            for(var i=0; i< shows.length;i++){
            	$(shows[i]).css("display","none");
            }
            //开始赋值并显示留言数据
            for(var i in json.record){
            	$(shows[i]).css("display","block");
            	$(header_leaves[i]).prop("src",json.record[i].user.pic);
            	$(nick_leaves[i]).text(json.record[i].user.name);
            	$(time_leaves[i]).text(new Date(new Date(json.record[i].time).getTime()).toLocaleString());
            	$(content_leaves[i]).html(replace_em(json.record[i].content));
            }
            
            //设置页数导航
            setlis();
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            alert("出错");
        }
   });
}
