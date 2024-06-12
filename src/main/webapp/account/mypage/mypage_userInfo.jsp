<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="mypage-userInfo-container">
	<div><h2>情報修正</h2> </div>
	
		<div class="mypage-userInfo-items">
			<div>
				<img alt="" src="${sessionScope.accountInfo.u_profile_img }" />
			</div>
			<div><h3>プロフィール画像変更</h3></div>
		</div>
		<div class="mypage-userInfo-items">
		<div><h3>ハコナカニックネーム</h3></div>
			<div>${sessionScope.accountInfo.u_nickname }</div>
		</div>
		<div class="mypage-userInfo-items">
			<div><h3>「X」ID</h3></div>
			<div>
				<input value="${sessionScope.accountInfo.u_twitter_id}"
					readonly="readonly">
			</div>
		</div>
		<div class="mypage-userInfo-button">
			<button>Save Changes</button>
		</div>
	</div>
</body>
</html>