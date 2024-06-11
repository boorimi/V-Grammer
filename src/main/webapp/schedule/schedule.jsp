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
				<option value="七彩てまり">七彩てまり</option>
				<option value="田中りゅこ">田中りゅこ</option>
				<option value="夜夢瑠紅">夜夢瑠紅</option>
				<option value="赤衣アカメ">赤衣アカメ</option>
				<option value="星ノ音コロン">星ノ音コロン</option>
				<option value="愛咲よつのは">愛咲よつのは</option>
				<option value="玉ノ井もなか">玉ノ井もなか</option>
				<option value="綾坂希穂">綾坂希穂</option>
				<option value="ソフィ・ローズ">ソフィ・ローズ</option>
				<option value="天海くりね">天海くりね</option>
				<option value="鳳儚">鳳儚</option>
				<option value="小日向千虎">小日向千虎</option>
				<option value="白砂つな">白砂つな</option>
				<option value="橘シエナ">橘シエナ</option>
				<option value="ミラ・ルプス">ミラ・ルプス</option>
				<option value="銀灰まお">銀灰まお</option>
				<option value="リン・ガーネット">リン・ガーネット</option>
				<option value="明堂しろね">明堂しろね</option>
				<option value="華糖シェリー">華糖シェリー</option>
				<option value="ぺるぽ">ぺるぽ</option>
				<option value="叶望ゆゆ">叶望ゆゆ</option>
			</select>
			<input type="date" min="">
			<input name="s_time" type="time">
			<input name="s_title">
			<button>登録</button>
		</form>
	</details>
</body>
</html>