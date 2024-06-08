<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Google font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Kanit:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="css/member.css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous">
	
</script>
<script type="text/javascript" src="js/member.js" defer></script>
</head>
<body>
	<div class="member-container">
		<div class="member-memberList-container">
			<div class="member-list-box">
			<div class="member-list-title">Members</div>
				<c:forEach var="m" items="${members }">
					<div class="member-memberList" id="${m.m_pk }">${m.m_name }</div>
				</c:forEach>
			</div>
		</div>
		<div class="member-img-container">
			<c:forEach items="${members }" var="m">
				<div class="member-backgroundImg" id="${m.m_pk }">
					<img src="haco_img/background/${m.i_background }">
					<div class="member-detail-container">
						<div class="member-detail">
							<div id="member-name">${m.m_name }</div>
							<div id="member-birth">
								<span>お誕生日&nbsp;</span><span>${m.m_birth }</span>
							</div>
							<div id="member-debut">
								<span>デビュー日&nbsp;</span><span>${m.m_debut }</span>
							</div>
							<!-- 상세보기 (위치 수정 필요)-->
							<details>
								<summary> 더보기..</summary>
								<div class="member-detail2-box">
									<div class="member-introduce-box">
										<div id="member-introduce">${m.m_introduce }</div>
									</div>
									<div class="member-mother-box">
										<div id="member-mother-name">
											<span>彼女のママは、</span> <span><a
												href="${m.m_mother_twitter }">${m.m_mother_name }</a></span>
										</div>
									</div>
									<div class="member-hashtag-container">
										<div id="member-hashtag-title">-ハッシュタグ-</div>
										<div class="member-hashtag-box">
											<c:forEach items="${m.hashTag }" var="h">
												<div id="member-hashtag">
													<a href="https://x.com/search?q=${h.h_tag }">
														${h.h_category }</a>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
							</details>
							<!-- 상세보기 끝 -->
							<div class="member-address-container">
								<div id="member-address-title">-アドレス-</div>
								<div class="member-address-box">
									<c:forEach items="${m.address }" var="a">
										<div id="${a.a_category }">
											<a href="${a.a_address }">${a.a_category }</a>
										</div>
									</c:forEach>
								</div>
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