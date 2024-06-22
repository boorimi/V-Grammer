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
		<div class="mypage-userInfo-items">
			<div class="mypage-profile-icon-box">
				<img class="mypage-profile-icon" alt=""
					src="${sessionScope.accountInfo.u_profile_img }" />
			</div>
			<div>
				<h3>プロフィール画像変更</h3>
				<input type="file" name="new-profile-img">
			</div>
		</div>
		<div class="mypage-userInfo-items">
			<div>
				<h3>連動された「X」ID</h3>
			</div>
			<div>
				<input value="@${sessionScope.accountInfo.u_screenName}"
					readonly="readonly">
			</div>
		</div>
		<div class="mypage-userInfo-button">
			<button>Save Changes</button>
		</div>

		<div class="mypage-userInfo-items">
			<div>
				<h3>ハコナカニックネーム</h3>
			</div>
			<div class="form-input">
				<input id="userInfo-nickname-input" name="userInfo-nickname-input"
					class="form-element-input" type="input"
					placeholder="${sessionScope.accountInfo.u_nickname }" required />
				<div class="form-element-bar"></div>
				<label class="form-element-label" for="name">新しいニックネームを入力して!</label>
				<small class="form-element-hint" id="check-result">ニックネームは2~30文字!</small>
			</div>
		</div>
		<button id="change-nickname-button">닉네임 변경</button>

		<div class="mypage-userInfo-button">
			<button id="retire-button"
				value="${sessionScope.accountInfo.u_twitter_id }">会員退会</button>
		</div>

	</div>
</body>
</html>