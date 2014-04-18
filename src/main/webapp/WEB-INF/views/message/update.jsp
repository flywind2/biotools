<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>post message</title>
</head>
<body>
<form:form commandName="jdMessage" style="padding:8px">
<label>message:</label><br>
<form:textarea cols="60" rows="18" path="text" /><br>
<input type="submit" value="Update"/><a href="${pageContext.request.contextPath }/message">Back</a>
</form:form>
</body>
</html>