<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Archives</title>
</head>
<body>
    <button onclick="location.href='ArchiveUpdateC'">수정하기</button> 
    <c:forEach items="${archives}" var="archive">
        <div class="archive-contents">
            
           <p style="margin-top: 0px"> <img class="archive-icon" src="haco_img/icon/${archive.i_icon}" ></p>
            <div class="archive-membername"> ${archive.m_name }</div>
           	<div class="archive-collabo"> コラボ : ${archive.a_collabo } </div>
           	<div class="archive-collabomember">	コラボメンバー : ${archive.a_collabomember } </div>
           	<div class="archive-category">カテゴリー : ${archive.a_category }</div>
            <div class="archive-date">	${archive.a_date} </div>
           	<div class="archive-time">	${archive.a_time} </div>
           	<div class="archive-title">	Title: ${archive.a_title}</div>
           	<div class="archive-thumbnail"> <img src="${archive.a_thumbnail}" alt="${archive.a_title} Thumbnail"> </div>
          
        </div>
    </c:forEach>
</body>


</html>

<%--     <c:if test="${empty archives}">
        <p>No archives found.</p>
    </c:if> --%>
