<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/mypage_goods.css">
</head>
<body>
	<div class="mypage-goods-container">
		<div>
			<h2>グッズ管理</h2>
		</div>
		<form action="GoodsC">
		<button>▼</button>
			<c:forEach var="bromide" items="${bromideInfos }">
				<div>${bromide}</div><br>
			</c:forEach>
		</form>
	</div>
</body>
</html>