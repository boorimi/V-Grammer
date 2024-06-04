<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/member.css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous">
</script>
<script type="text/javascript" src="js/member.js" defer></script>
</head>
<body>
	<div class="member-container">
		<div class="member-memberList-container">
			<div class="member-list">Members</div>
			<c:forEach var="m" items="${members }" begin="0">
				<div class="member-memberList" id="${m.m_pk }">${m.m_name }</div>
			</c:forEach>
		</div>
		<div class="member-img-container">
			<c:forEach items="${images }" var="i" begin="0">
				<div class="member-backgroundImg" id="${i.i_m_pk }">
					<img src="${i.i_background }">
					<div class="member-detail-container">
						<div class="member-detail">설명~</div>
					</div>
					<div class="member-img-box">
						<div class="member-img">
							<img src="${i.i_pic }">
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>


</body>


</html>