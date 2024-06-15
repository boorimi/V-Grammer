<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
.login-container {
	display: flex;
	width: 25vh;
	justify-content: space-between;
}

.login-ok-button-wrap{
	direction: flex;
	justify-content: space-between;

}
</style>
</head>
<body>
	<div class="login-container">

		<div class="login-profile-icon"
			style="overflow: hidden; width: 30px; height: 30px; border-radius: 50%">
			<img src="${accountInfo.u_profile_img }">
		</div>
		<div>ようこそ<br>${sessionScope.accountInfo.u_nickname}様</div>
		<div class="login-ok-button-wrap">
			<button onclick="location.href='LogoutC'">LOGOUT</button>
			<button onclick="location.href='MyPageC'">MyPage</button>
		</div>
	</div>
</body>
</html>