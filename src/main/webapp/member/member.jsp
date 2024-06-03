<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>

<h1>
	Member Page
</h1>
<c:forEach var="m" items="${members }">
<div>
이름 : ${m.m_name }
</div>
</c:forEach>

</body>
</html>