<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<script src="js/register_page.js"></script>
<link rel="stylesheet" href="css/register_page.css">

</head>
<body>
	<div class="waku-wrap"
		style="background-image: url('account/register/short-pink.png');">
		<form action="RegisterC" method="post" enctype="multipart/form-data">
			<div class="register-container">
				<div class="register-items">
					<div class="register-profile-box">
						<img class="register-profile" alt=""
							src="${sessionScope.twitterProfileImgUrl }">
					</div>
					<br> <br>
					<div class="register-nickname-wrap">
						<div class="form-input">
							<input id="register-nickname-input"
								name="register-nickname-input" class="form-element-input"
								type="input" placeholder="2~30文字で入力して!!" required />
							<div class="form-element-bar"></div>
							<label class="form-element-label" for="name">あなたのニックネームを教えて!</label> <small
								class="form-element-hint" id="check-result"></small>
						</div>
					</div>
					<div id="check-result"></div>
					<div>
						連動X ID<br> <span style="font-family: 'Uzura', sans-serif; font-size: 20px">@${sessionScope.twitterScreenName }</span>
					</div>
					<div id="register-button-div">
						<button id="register-submit-button" type="submit">ハコナカ登録!</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="register-section" id="register-section"></div>
</body>
</html>