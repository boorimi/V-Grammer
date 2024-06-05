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
				<div class="member-memberList" id="${m.m_pk }" onclick="getPk()" >${m.m_name }
				<%-- <input type="hidden" name="member_pk" value="${m.m_pk }"> --%>
				</div>
			</c:forEach>
		</div>
		<div class="member-img-container">
			<c:forEach items="${images }" var="i" begin="0">
				<div class="member-backgroundImg" id="${i.i_m_pk }">
					<img src="${i.i_background }">
					<div class="member-detail-container">
						<div class="member-detail">
							<div class="member-name">名前：</div>
							<div class="member-birth">お誕生日：</div>
							<div class="member-debut">デビュー日：</div>
							<div class="member-link-icon">
								<div id="member-twitter"></div>
								<div id="member-youtube"></div>
								<div id="member-tictok"></div>
							</div>
							<details>
								<summary>더보기..</summary>
								<div>自己紹介<br></div>
								<div>ママは<br></div>
								<div>ハッシュタグ<br></div>
							</details>
						</div>
					</div>
					<div class="member-img-box">
						<div class="member-img">
							<img src="${i.i_img }">
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>


</body>


</html>