<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div><img alt="" src="${sessionScope.accountInfo.u_profile_img }"></div>

<div>${sessionScope.accountInfo.u_twitter_id }</div>
<div>${sessionScope.accountInfo.u_nickname }</div>
<div>${sessionScope.accountInfo.u_screenName }</div>






</body>
</html>