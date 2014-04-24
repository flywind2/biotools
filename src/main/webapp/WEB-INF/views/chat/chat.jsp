<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chat Room</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.min.js"></script>
</head>
<body style="height: 132px; ">

<label>Join In:</label><input type="text" name="user" value=""><input type="button" name="join" id="joinBtn" value="join">
<p></p>
<textarea rows="10" cols="60" name="msgs" id="msgs" contenteditable="false"></textarea>
<p></p>
<input type="text" name="content" >
<input type="button" name="postMsg" id="postMsg" value="post">
<script type="text/javascript">

$(document).ready(function(){

//join in chat room
$("#joinBtn").click(function(){	
	//alert($("input[name='user']").val());
	var user = $("input[name='user']").val();
	$.ajax({
		url:"${pageContext.request.contextPath }/chat/join",
		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		type:"get",
		data:{"user":encodeURI(user)},
		dataType:"text",
		cache:false,
		success:function(data){
			//get message from server client
			//alert(data.text);
			console.log(data);
			getMessages();
		},
		error:function(data){
		console.log(data);
		} 
	});
});


//post message
$("input[name='postMsg']").click(function(){
	var content = $("input[name='content']").val();
	$.ajax({
		url:"${pageContext.request.contextPath }/chat/post",
		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		type:"post",
		data:{"content":encodeURI(content)},
		dataType:"text",
		cache:false,
		success:function(data){
			//get message from server client
			$("input[name='content']").focus();
			console.log(data);
		},
		error:function(data){
			console.log("post error :"+data.responseText);
			//alert("post error : "+data.responseText);
		}
		
	});
});

//get messages
function getMessages(){
	//var user = $("input[name='user']").val();
	$.ajax({
		url:"${pageContext.request.contextPath }/chat/get",
		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		type:"get",
		dataType:"json",
		cache:false,
		success:function(data){
			//get message from server client
			
			if(data!='time is out'){
			var old = $("#msgs").val();
			$("#msgs").val(old+"\r\n["+decodeURI(data.user)+"@"+data.date+"] say:"+decodeURI(data.content));
			}
			
			
			getMessages();
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			//alert("get error : "+data.responseText);
			console.log("get error: "+ textStatus);
			getMessages();
		}
		
	});
}


});

</script>
</body>
</html>