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
<style type="text/css">
.mypage-goods-container {
	position: relative;
	width: 50vw;
}

.bromide-wrap {
	display: flex;
	justify-content: center;
	width: 180px;
}

#bromide-content {
	display: flex;
	width: 100%;
	justify-content: center;
	flex-wrap: wrap;
	gap: 20px 3px;
}

.bromide-wrap>div {
	text-align: center;
}

.bromide-wrap>div:nth-child(1) {
	width: 10%;
}

.bromide-wrap>div:nth-child(2) {
	text-align: left;
	width: 70%;
	padding-left: 5px;
}

.bromide-wrap>div:nth-child(3) {
	width: 20%;
}
</style>
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
				<div>
					<div class="bromide-wrap">
						<div class="goods-info-bromide">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-bromide">${bromide.m_name}</div>
						<div class="goods-info-bromide">
							<select>
								<optgroup label="${bromide.g_count}ea"></optgroup>
								<c:forEach begin="0" end="10" var="i">
									<c:choose>
										<c:when test="${i != bromide.g_count}">
											<option value="${i}">${i }</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i }">${i }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button id="goods-button-bromide"> ▼</button>
		<div id="bromide-content">
			<c:forEach var="bromide" items="${bromideInfos }">
				<div>
					<div class="bromide-wrap">
						<div class="goods-info-bromide">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-bromide">${bromide.m_name}</div>
						<div class="goods-info-bromide">
							<select>
								<optgroup label="${bromide.g_count}ea"></optgroup>
								<c:forEach begin="0" end="10" var="i">
									<c:choose>
										<c:when test="${i != bromide.g_count}">
											<option value="${i}">${i }</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i }">${i }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>