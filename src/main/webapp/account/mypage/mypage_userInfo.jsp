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
	<div class="full-wrap">
		<div class="mypageinfo-tab-title">
			<div class="mypage-tab-icon-wrap">
				<img class="mypage-tab-icon" alt=""
					src="account/mypage/mypage_index_icon/userinfo.png">
			</div>
			<h2>会員情報</h2>
			<button id="flip-button" class="upper-flip-button" onclick="flip()">
				<img id="flip-button-icon" src="account/mypage/right-left-arrow.png">
				<span id="flip-button-text">BACK SIDE</span>
			</button>
		</div>
		<div class="flip-container" id="flip-container">
			<div class="flipper">
				<!-- 앞면 -->
				<div class="front">
					<div class="mypage-userinfo-left">
						<div class="userinfo-logo-wrap">
							<img id="userinfo-logo" alt=""
								src="haco_img/haconaka_logo_small.png">
						</div>
						<div class="profile-part-wrap">
							<form action="UserInfo_ProfileImgC" method="post"
								enctype="multipart/form-data" id="profileForm">
								<div class="mypage-profile-icon-box">
									<img class="mypage-profile-icon" alt="" id="profile-icon"
										src="${sessionScope.accountInfo.u_profile_img }" />
								</div>
								<div>
									<input type="file" id="userInfo-img-input"
										name="new-profile-img">
								</div>
								<div id="change-img-button-wrap"></div>
								<button id="change-img-button">アイコン変更</button>
							</form>
						</div>
					</div>
					<div class="mypage-userinfo-right">
						<div class="nickname-part">
							<form id="nickname-form">
								<div>
									<h3 style="font-size: 2.0rem">ハコナカニックネーム</h3>
								</div>
								<div class="form-input">
									<input id="userInfo-nickname-input"
										name="userInfo-nickname-input" class="form-element-input"
										type="input"
										placeholder="${sessionScope.accountInfo.u_nickname }" required />
									<div class="form-element-bar"></div>
									<label class="form-element-label" for="name">新しいニックネームを入力して!</label>
									<small class="form-element-hint" id="check-result">ニックネームは2~30文字!</small>
								</div>
								<div id="change-nickname-button-wrap">
									<button id="change-nickname-button">ニックネーム変更</button>
								</div>
							</form>
						</div>
						<div class="x-part">
							<div class="xid-wrap">
								<p
									style="margin-top: 20px; margin-bottom: 2px; font-size: 2.0rem;">連動された「X」ID</p>
							</div>
							<div>@${sessionScope.accountInfo.u_screenName}</div>
						</div>
						<div class="retire-button-wrap">
							<button class="retire-button" id="flip-button" onclick="flip()">会員退会</button>
						</div>
					</div>
				</div>
				<!-- 뒷면 -->
				<div class="back">
					<h2>ハコナカ会員退会</h2>
					<p>押さないでくれ~~</p>
					<div class="retire-button-wrap-back">
						<button class="retire-button-back" id="retire-button"
							value="${sessionScope.accountInfo.u_twitter_id }">会員退会</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>