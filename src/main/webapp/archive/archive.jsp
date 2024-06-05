<%@ page import="java.util.ArrayList" %>
<%@ page import="your.package.name.Archive" %>
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
            <h2>${archive.title}</h2>
            <p>Date: ${archive.date}</p>
            <p>Time: ${archive.time}</p>
            <img src="${archive.thumbnailUrl}" alt="${archive.title} Thumbnail">
        </div>
    </c:forEach>
</body>
</html>