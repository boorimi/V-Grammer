<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/register_page.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<script src="js/register-page.js"></script>
</head>
<body>
	<section class="register-section">
		<div class="register-container">
			<form action="RegisterC" method="post" enctype="multipart/form-data">
				<div>
					<img alt="" src="${sessionScope.twitterProfileImgUrl }">
				</div>
				<div>
					ハコナカニックネーム<br> <input id="register-nickname-input" name="register-input-nickname">
				</div>
				<div id="check-result"></div>
				<div>
					連動されたX ID<br> <span>${sessionScope.twitterScreenName }</span>
				</div>

				<div>
					<button>ようこそ！Hakonakaへ！</button>
				</div>
			</form>
		</div>
	</section>
</body>
</html>