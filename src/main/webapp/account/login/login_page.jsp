<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="LoginC" method="post">
		<div>
			Hakonaka ID : <input name="login-input-id"> <br>
			Hakonaka PW : <input name="login-input-pw">
		</div>
	</form>



	<form action="TwitterLoginServlet">
		<button>회원가입 with X</button>
	</form>
</body>
</html>