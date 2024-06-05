<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="RegisterC" method="post">
		<div>
			Hakonaka ID <input name="register-input-id">
		</div>
		<div>
			Hakonaka PW <input name="register-input-pw">
		</div>
		<div>
			NickName <input name="register-input-nickname">
		</div>
		<div>
			your X ID : <span>${sessionScope.twitterScreenName }</span>
		</div>

	<div><button>ようこそ！Hakonakaへ！</button> </div>
	</form>
</body>
</html>