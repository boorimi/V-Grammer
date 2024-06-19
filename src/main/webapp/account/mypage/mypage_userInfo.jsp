<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/mypage_userInfo.css" />
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<script src="js/mypage_userInfo.js" defer="defer"></script>
</head>
<body>
	<div class="mypage-userInfo-container">
		<div>
			<h2>情報修正</h2>
		</div>
		<form action="UserInfoC" method="post" enctype="multipart/form-data">
			<div class="mypage-userInfo-items">
				<div class="mypage-profile-icon-box">
					<img class="mypage-profile-icon" alt="" src="${sessionScope.accountInfo.u_profile_img }" />
				</div>
				<div>
					<h3>プロフィール画像変更</h3>
					<input type="file" name="new-profile-img">
				</div>
			</div>
			<div class="mypage-userInfo-items">
				<div>
					<h3>ハコナカニックネーム</h3>
				</div>
				<div>
					<input value="${sessionScope.accountInfo.u_nickname }" name="new-nickname">
				</div>
			</div>
			<div class="mypage-userInfo-items">
				<div>
					<h3>「X」ID</h3>
				</div>
				<div>
					<input value="@${sessionScope.accountInfo.u_screenName}"
						readonly="readonly">
				</div>
			</div>
			<div class="mypage-userInfo-button">
				<button>Save Changes</button>
			</div>
			<div class="mypage-userInfo-button">
				<button id="retire-button" value="${sessionScope.accountInfo.u_twitter_id }">会員退会</button>
			</div>
		</form>
	</div>
</body>
</html>