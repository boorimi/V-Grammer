<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login_ok.css" />
<link rel="stylesheet" href="css/archieve.css" />
</head>
<body>
	<div class="login-container">

		<div class="login-profile-icon-box">
			<img class="login-profile-icon" src="${accountInfo.u_profile_img }">
		</div>
		<div>ようこそ<br><div class="login-nickname">${sessionScope.accountInfo.u_nickname}様</div></div>
		<div class="login-ok-button-wrap">
			<button class="login-cute-button" onclick="location.href='LogoutC'">LOGOUT</button>
			<button class="login-cute-button" onclick="location.href='MyPageC'">MyPage</button>
		</div>
	</div>
</body>
</html>