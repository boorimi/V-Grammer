<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="RegisterC" method="post" enctype="multipart/form-data">
		<div>
			<img alt="" src="${sessionScope.twitterProfileImgUrl }">
		</div>
		<div>
			Nickname <input name="register-input-nickname">
		</div>
		<div>
			your X ID : <span>${sessionScope.twitterScreenName }</span>
		</div>
		<div>
			profile img : <input type="file" name="register-input-file" />
		</div>

		<div>
			<button>ようこそ！Hakonakaへ！</button>
		</div>
	</form>
</body>
</html>