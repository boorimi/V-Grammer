<%@page import="com.vg.jw.mypage.MyPageDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/mypage_goods.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<script src="js/mypage_goods.js"></script>
<script type="text/javascript">
$(function() {
	$("#goods-button-bromide").click(function() {
		$("#bromide-content").slideToggle();
		
	});
});

</script>
</head>
<% 
	MyPageDAO.getBromide(request);
%>


<body>
	<div class="mypage-goods-container">
		<div>
			<h2>グッズ管理</h2>
		</div>
			<button id="goods-button-bromide">브로마이드 정보 ▼</button>
			<div id="bromide-content">
			<c:forEach var="bromide" items="${bromideInfos }">
				<div class="goods-info-bromide">${bromide.g_category}</div>
				<div class="goods-info-bromide">${bromide.g_count}</div>
				<div class="goods-info-bromide"> <img alt="" src="haco_img/icon/${bromide.i_icon}"> ${bromide.i_icon}</div>
				<div class="goods-info-bromide">${bromide.m_name}</div>
				<br>
			</c:forEach>
			</div>
	</div>
</body>
</html>