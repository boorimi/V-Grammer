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
				<div class="member-memberList" id="${m.m_pk }">${m.m_name }
				<%-- <input type="hidden" name="member_pk" value="${m.m_pk }"> --%>
				</div>
			</c:forEach>
		</div>
		<div class="member-img-container">
			<c:forEach items="${members }" var="m">
				<div class="member-backgroundImg" id="${m.m_pk }">
					<img src="haco_img/background/${m.i_background }">
					<div class="member-detail-container">
						<div class="member-detail">
							<div class="member-name">名前： ${m.m_name }</div>
							<div class="member-birth">お誕生日： ${m.m_birth }</div>
							<div class="member-debut">デビュー日： ${m.m_debut }</div>
							<div class="member-link-icon">
								<div id="member-twitter"><a href="${m.address[0].a_address }">visit</a></div>
								<div id="member-youtube">${m.address[1].a_address }</div>
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
							<img src="haco_img/img/${m.i_img }">
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>


</body>


</html>