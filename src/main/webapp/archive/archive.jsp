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
        <div class="archive-contents">
            
           	<p>コラボ : ${archive.a_collabo }
           	<p>カテゴリー : ${archive.a_category }
            <p>日付: ${archive.a_date}</p>
           	<p>時間: ${archive.a_time}</p>
           	<p >コラボメンバー : ${archive.a_collabomember }</p>
           	<p style="margin-top: 12px">Title: ${archive.a_title}</p>
           <p style="margin-top: 0px"> <img src="${archive.a_thumbnail}" alt="${archive.a_title} Thumbnail"></p>
        </div>
    </c:forEach>
</body>
</html>

<%--     <c:if test="${empty archives}">
        <p>No archives found.</p>
    </c:if> --%>
