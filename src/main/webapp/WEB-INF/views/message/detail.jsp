<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>show  message detail informations</title>
</head>
<body>

<label>message:</label><br>
<label>${requestScope.message.text }</label>

<a href="${pageContext.request.contextPath }/message">Back</a>
</body>
</html>