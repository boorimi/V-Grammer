<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
	
<link rel="stylesheet" href="css/schedule.css">
<script type="text/javascript" src="js/schedule.js" defer></script>

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
		<summary onclick="ScheduleLoginCheck()" style="background-color: pink; width: 150px;">スケジュール登録</summary>
		<form action="ScheduleInsertC">
			<select name="s_member">
				<option>メンバー</option>
				<option value="1">七彩てまり</option>
				<option value="2">田中りゅこ</option>
				<option value="3">夜夢瑠紅</option>
				<option value="4">赤衣アカメ</option>
				<option value="5">星ノ音コロン</option>
				<option value="6">愛咲よつのは</option>
				<option value="7">玉ノ井もなか</option>
				<option value="8">綾坂希穂</option>
				<option value="9">ソフィ・ローズ</option>
				<option value="10">天海くりね</option>
				<option value="11">鳳儚</option>
				<option value="12">小日向千虎</option>
				<option value="13">白砂つな</option>
				<option value="14">橘シエナ</option>
				<option value="15">ミラ・ルプス</option>
				<option value="16">銀灰まお</option>
				<option value="17">リン・ガーネット</option>
				<option value="18">明堂しろね</option>
				<option value="19">華糖シェリー</option>
				<option value="20">ぺるぽ</option>
				<option value="21">叶望ゆゆ</option>
			</select>
			<input name="s_date" type="date">
			<input name="s_time" type="time">
			<input name="s_title" style="width: 500px;">
			<button onclick="location.href=ScheduleC">登録</button>
		</form>
	</details>
</body>
</html>