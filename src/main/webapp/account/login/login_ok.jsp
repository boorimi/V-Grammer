<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="LogoutC">
		<div>
			ログイン成功!<br> ${sessionScope.twitterScreenName}様、<br>
			ようこそハコナカへ
		</div>
		<div class="my-page"><button>マイページ</button></div>
		<div><button>로그아웃</button></div>
	</form>
</body>
</html>