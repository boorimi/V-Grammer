<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" href="css/mypage_index.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<script src="js/mypage_index.js"></script>
</head>
<body>


	<div class="mypage-container">

		<div class="mypage-menu-section">
			<div class="mypage-menu-title">
				<h2>MY PAGE</h2>
			</div>
			<div class="mypage-menu-items" id="mypage-menu-userInfo">
				<div class="mypage-menu-icon-wrap">
					<img class="mypage-menu-icon" alt=""
						src="account/mypage/mypage_index_icon/userinfo.png">
				</div>
				<span>情報修正</span>
			</div>
			<div class="mypage-menu-items" id="mypage-menu-goods">
				<div class="mypage-menu-icon-wrap">
					<img class="mypage-menu-icon" alt=""
						src="account/mypage/mypage_index_icon/goods.png">
				</div>
				<span>グッズ管理</span>
			</div>
			<div class="mypage-menu-items" id="mypage-menu-article">
				<div class="mypage-menu-icon-wrap">
					<img class="mypage-menu-icon" alt=""
						src="account/mypage/mypage_index_icon/article.png">
				</div>
				<span>MY記事一覧</span>
			</div>
			<div class="mypage-menu-items" id="mypage-menu-comment">
				<div class="mypage-menu-icon-wrap">
					<img class="mypage-menu-icon" alt=""
						src="account/mypage/mypage_index_icon/comment.png">
				</div>
				<span>MYコメント</span>
			</div>
		</div>
		<div class="mypage-jsp-section">jsp인클루드 영역임</div>

	</div>

</body>
</html>
