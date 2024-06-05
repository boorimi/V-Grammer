<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Archives</title>
</head>
<body>
    <h1>Archives</h1>
    <c:forEach items="${archives}" var="archive">
        <div>
            <h2>${archive.a_title}</h2>
            <p>Date: ${archive.a_date}</p>
           	<p>Time: ${archive.a_time}</p>
            <img src="${archive.a_thumbnail}" alt="${archive.a_title} Thumbnail">
        </div>
    </c:forEach>
    <c:if test="${empty archives}">
        <p>No archives found.</p>
    </c:if>
</body>
</html>

