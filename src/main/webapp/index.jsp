<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" href="css/index.css" />
<script src="js/index.js"></script>
</head>
<body>
	<nav>
		<div class="main-logo">
			<a href="HC">ハコナカ</a>
		</div>
		<ul class="mene-lists">
			<li><a href="MemberC">メンバー</a></li>
			<li><a href="ArchiveC">アーカイブ</a></li>
			<li><a href="CalendarC">カレンダー</a></li>
			<li><a href="ScheduleC">スケジュール</a></li>
			<li><a href="TradePage?p=1">トレード</a></li>
			<li><a href="Announcement">お知らせ</a></li>
		</ul>

		<div class="nav-userinfo">
			<div class="nav-user"><jsp:include page="${loginContent }"></jsp:include></div>
			<div class="my-page">マイページ</div>
		</div>
	</nav>

	<div class="content">
		<jsp:include page="${content }"></jsp:include>
	</div>
	
</body>
</html>
