<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/schedule.css">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Insert title here</title>
</head>
<body>
	<h1>スケジュールのページスタートしようかなー！</h1>
	<h3>시간쪼개는 단위</h3>
	<h4>00시~12시 1블록</h4>
	<h4>12시~18시 1블록</h4>
	<h4>18시~24시까지 30분단위로 1블록</h4>
	<details>
		<summary style="background-color: pink; width: 150px;">スケジュール登録</summary>
		<form action="InsertScheduleC">
			<select>
				<option value="">メンバー</option>
				<c:forEach items="${members}" var="m">
				<option>${m.m_name }</option>
				</c:forEach>
			</select>
			<select></select>
			<input name="s_time" placeholder="数字だけ入力　ex: 1230">
			<input name="s_title">
			<button>登録</button>
		</form>
	</details>
</body>
</html>