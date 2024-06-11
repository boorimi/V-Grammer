<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div><img alt="" src="${sessionScope.accountInfo.u_profile_img }"><button>修正</button></div>
<hr>
<div>${sessionScope.accountInfo.u_twitter_id }</div>

<div>ハコナカニックネーム : ${sessionScope.accountInfo.u_nickname }<button>修正</button></div>
<div>X ID : @${sessionScope.accountInfo.u_screenName }</div>
<hr>
<div><span>登録されたマイグッズ一覧</span> </div>




</body>
</html>