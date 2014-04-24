<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gene Query</title>
</head>
<body>
<form:form action="queryAsyn" enctype="multipart/form-data">
<select name="field">
<option label="Symbol" value="Symbol"></option>
<option label="Gene ID" value="GeneID"></option>
		</select>
<br>		
<textarea rows="10" cols="12" name="value"></textarea>
<br>
<input type="file" name="file">
<input type="submit" value="Query">
</form:form>
<br>
<br>
<c:if test="${not empty genes }">
<table>
<thead>
<tr>
<th>Gene ID</th>
<th>Symbol</th>
</tr>
</thead>
<tbody>
<c:forEach items="${genes }" var="gene">
<tr>
<td>${gene.geneID }</td>
<td>${gene.symbol }</td>
</tr>
</c:forEach>
</tbody>
</table>
</c:if>
</body>
</html>