<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset='utf-8' />
<!-- 화면 해상도에 따라 글자 크기 대응(모바일 대응) -->
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- fullcalendar CDN -->
<link
	href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css'
	rel='stylesheet' />
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
<!-- fullcalendar 언어 CDN -->
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>
<link rel="stylesheet" type="text/css" href="css/calendar.css">
<style>
	/* 추가된 CSS */
	.button-dday {
		background: #000;
		color: #fff;
		z-index: 1;
		position: relative; /* 절대 위치 요소의 부모로 설정 */
		padding: 10px 20px;
		border: none;
		cursor: pointer;
	}
	.button-dday:after {
		position: absolute;
		content: "";
		width: 0;
		height: 100%;
		top: 0;
		right: 0;
		z-index: -1;
		background: #e0e5ec;
		transition: all 0.3s ease;
	}
	.button-dday:hover {
		color: #000;
	}
	.button-dday:hover:after {
		left: 0;
		width: 100%;
	}
	.button-dday:active {
		top: 2px;
	}
</style>
</head>
<body style="padding: 30px;">
	<!-- calendar 태그 -->
	<div id='calendar-container'>
		<div id='calendar'></div>
	</div>
	
	<!-- DdayC로 연결되는 버튼 -->
	<button id="ddayButton" class="button-dday">D-Day 확인</button>
	
	<script src="js/calendar.js"></script>
	
	<!-- 추가된 스크립트 -->
	<script>
		$(document).ready(function() {
			// 버튼 클릭 이벤트 핸들러
			$('#ddayButton').click(function() {
				// DdayC 서블릿으로 이동
				window.location.href = 'DdayC';
			});
		});
	</script>
</body>
</html>
