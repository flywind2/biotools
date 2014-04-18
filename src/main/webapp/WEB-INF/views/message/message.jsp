<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Messages</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>
</head>
<body>
<h1>
	Hello world!  
</h1>



<p>
<a href="${pageContext.request.contextPath}/message/add" title="点击添加消息" >Add Message</a>
</p>
<ul>
<c:forEach items="${requestScope.messages }" var="message">

<li class="message">
<c:out value="${message.messageId }"></c:out><label>&nbsp;&nbsp;</label>
<a class="update" href="${pageContext.request.contextPath}/message/update/${message.messageId }" id="${message.messageId }">Update</a><label>&nbsp;&nbsp;</label>
<a class="delete" href="${pageContext.request.contextPath}/message/delete/${message.messageId }" id="${message.messageId }">Delete</a><label>&nbsp;&nbsp;</label>
<a class="detail" href="${pageContext.request.contextPath}/message/show/${message.messageId }">Detail</a><label>&nbsp;&nbsp;</label>
<c:out value="${message.text }"></c:out>
<c:if test="${message.jdResponses.size() gt '0'}">
<ul class="response">
<c:forEach items="${message.jdResponses }" var="response">
<li class="response"><c:out value="${response.text }"></c:out></li>
</c:forEach>
</ul>
</c:if>

</li>

</c:forEach>
</ul>
<p></p>

<form action="${pageContext.request.contextPath}/message/reply" method="post">
<input type="hidden" id="messageId" name="messageId">
<textarea rows="5" cols="60" id="message_text" disabled="disabled"></textarea><br>
<textarea rows="5" cols="60" id="text" name="text"></textarea><br>
<input type="button" name="submit"  value="submit" onclick="javascript:postReply();"><br>
<c:if test="${errorInfo !=null }">
<c:out value="${errorInfo }"></c:out>
</c:if>
</form>

<script type="text/javascript">

$(document).ready(function(){
	$(".delete").click(function(){
		if(confirm("make sure to delete message?")){
	
			var obj = $(this);
			
			$.ajax({
				  type: 'POST',
				  url: "delete/"+$(this).attr("id"),
				  success: function(data){
					  $(obj).parent().remove();
				  },
				  error:function(data){
					  alert("delete message failed");
				  }
				  //dataType: ""
				});
			
			
		}
		
		
		return;
	});
});



function postReply(){
	var obj = $(this);
	$.ajax({
		  type: 'POST',
		  url: "reply/"+$("#messageId").val(),
		  data:{text:$("#text").val()},
		  success: function(data){
			  //alert( $(obj).parent().find(".response").last().html());
			 obj.each(e, t);
			 
		  },
		  error:function(data){
			  alert(data+":  reply message failed");
		  }
		  //dataType: ""
		});
}

function reply(message_id,b){
	$("#messageId").val(message_id);
	$("#message_text").val(b);
	
// 	document.getElementById("message_id").value=message_id;
// 	document.getElementById("message_text").value=b;
	return;
}

</script>
</body>
</html>