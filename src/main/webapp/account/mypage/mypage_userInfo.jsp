<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="mypage-userInfo-container">
		<div>
			<h2>情報修正</h2>
		</div>
		<form action="UserInfoC" method="post" enctype="multipart/form-data">
			<div class="mypage-userInfo-items">
				<div>
					<img alt="" src="${sessionScope.accountInfo.u_profile_img }" />
				</div>
				<div>
					<h3>プロフィール画像変更</h3>
					<input type="file" name="new-profile-img">
				</div>
			</div>
			<div class="mypage-userInfo-items">
				<div>
					<h3>ハコナカニックネーム</h3>
				</div>
				<div>
					<input value="${sessionScope.accountInfo.u_nickname }" name="new-nickname">
				</div>
			</div>
			<div class="mypage-userInfo-items">
				<div>
					<h3>「X」ID</h3>
				</div>
				<div>
					<input value="@${sessionScope.accountInfo.u_screenName}"
						readonly="readonly">
				</div>
			</div>
			<div class="mypage-userInfo-button">
				<button>Save Changes</button>
			</div>
		</form>
	</div>
</body>
</html>