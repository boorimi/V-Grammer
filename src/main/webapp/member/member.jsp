<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/member.css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
			<c:forEach var="m" items="${members }" >
				<div class="member-memberList" id="${m.m_pk }">${m.m_name }</div>
			</c:forEach>
		</div>
		<div class="member-img-container">
			<c:forEach items="${members }" var="m">
				<div class="member-backgroundImg" id="${m.m_pk }">
					<img src="haco_img/background/${m.i_background }">
					<div class="member-detail-container">
						<div class="member-detail">
							<div class="member-name">名前： ${m.m_name }</div>
							<div class="member-birth">お誕生日：${m.m_birth } </div>
							<div class="member-debut">デビュー日： ${m.m_debut }</div>
							<!-- 상세보기 (위치 수정 필요)-->
							<details>
								<summary>더보기..</summary>
								<div class="member-introduce">自己紹介</div>
								<div class="member-mother-box">-ママは-</div>
								<div class="member-mother-name">お名前：${m.m_mother_name } </div>
								<div class="member-twitter">ツイッター：<a href="${m.m_mother_twitter } "> ${m.m_mother_twitter } </a></div>
								<div class="member-hashtag-box">-ハッシュタグ-
								<c:forEach items="${m.hashTag }" var="h">
								<div class="member-hashtag-category">${h.h_category } </div>
								<div class="member-hashtag">${h.h_tag }</div>
								</c:forEach>
								</div>
							</details>
							<!-- 상세보기 끝 -->
							<div class="member-address-box">
							<c:forEach items="${m.address }" var="a">
								<div id="member-address"><a href="${a.a_address }"> &nbsp; ${a.a_category }</a></div>
							</c:forEach>
							</div>
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