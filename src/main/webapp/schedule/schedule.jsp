<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="css/schedule.css" />
<script type="text/javascript" src="js/schedule.js" defer></script>

<title>Insert title here</title>
<style>
dialog {
	border: none;
	border-radius: 5px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	padding: 20px;
	width: 300px;
}

dialog::backdrop {
	background: rgba(0, 0, 0, 0.4);
}

.modal-header, .modal-footer {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.modal-footer {
	margin-top: 20px;
}

.close-button {
	background: none;
	border: none;
	font-size: 1.2em;
	cursor: pointer;
}
</style>
</head>
<body>
	<textarea rows="" cols="" id="weekJSON" style="display: none">${weekJSON }</textarea>
	<div class="schedule-container">
		<div class="s-week-list">
			<input id="s-week" type="radio" name="tab_item" /> <label
				class="tab_item">スケジュール</label>
			<!-- 월 -->
			<input id="s-mon" type="radio" name="tab_item" checked /> <label
				class="tab_item" for="s-mon">${thisWeek[0] } 月</label>
			<!-- 화 -->
			<input id="s-tue" type="radio" name="tab_item" /> <label
				class="tab_item" for="s-tue">${thisWeek[1] } 火</label>
			<!-- 수 -->
			<input id="s-wed" type="radio" name="tab_item" /> <label
				class="tab_item" for="s-wed">${thisWeek[2] } 水</label>
			<!-- 목 -->
			<input id="s-thu" type="radio" name="tab_item" /> <label
				class="tab_item" for="s-thu">${thisWeek[3] } 木</label>
			<!-- 금 -->
			<input id="s-fri" type="radio" name="tab_item" /> <label
				class="tab_item" for="s-fri">${thisWeek[4] } 金</label>
			<!-- 토 -->
			<input id="s-sat" type="radio" name="tab_item" /> <label
				class="tab_item" for="s-sat">${thisWeek[5] } 土</label>
			<!-- 일 -->
			<input id="s-sun" type="radio" name="tab_item" /> <label
				class="tab_item" for="s-sun">${thisWeek[6] } 日</label>


			<dialog id="modal">
			<div class="modal-container">
				<div class="modal-header">
					<h2 id="time"></h2>
					<h2 id="name"></h2>
					<button class="close-button" id="closeModalButton">&times;</button>
				</div>
				<div class="modal-title-box" style="font-weight: 600">
					<p>- 配信のタイトル</p>
					<p id="title"></p>
				</div>
				<div class="modal-footer">
					<button id="deleteButton"
						value="${sessionScope.accountInfo.u_twitter_id }">Delete</button>
					<button id="updateButton"
						value="${sessionScope.accountInfo.u_twitter_id }">Update</button>
				</div>
			</div>
			</dialog>


			<!-- 월 -->
			<c:forEach var="s" items="${weekSchedules}" varStatus="st">
				<div class="tab_content day${st.count }-content">
					<div class="s-time-list">
						<div class="s-time a-time">00:00 ~ 12:00</div>
						<div class="s-data-box" start="0" end="1200"></div>
					</div>
					<div class="s-time-list">
						<div class="s-time b-time">12:00 ~ 18:00</div>
						<div class="s-data-box" start="1200" end="1800"></div>
					</div>
					<div class="s-time-list">
						<div class="s-time c-time">18:00 ~ 18:30</div>
						<div class="s-data-box" start="1800" end="1830"></div>
					</div>
					<div class="s-time-list">
						<div class="s-time d-time">18:30 ~ 19:00</div>
						<div class="s-data-box" start="1830" end="1900"></div>
					</div>
					<div class="s-time-list">
						<div class="s-time e-time">19:00 ~ 19:30</div>
						<div class="s-data-box" start="1900" end="1930"></div>
					</div>
					<div class="s-time-list">
						<div class="s-time f-time">19:30 ~ 20:00</div>
						<div class="s-data-box" start="1930" end="2000"></div>
					</div>
					<div class="s-time-list">
						<div class="s-time g-time">20:00 ~ 20:30</div>
						<div class="s-data-box" start="2000" end="2030"></div>
					</div>
					<div class="s-time-list">
						<div class="s-time h-time">20:30 ~ 21:00</div>
						<div class="s-data-box" start="2030" end="2100"></div>
					</div>
					<div class="s-time-list">
						<div class="s-time i-time">21:00 ~ 21:30</div>
						<div class="s-data-box" start="2100" end="2130"></div>
					</div>
					<div class="s-time-list">
						<div class="s-time j-time">21:30 ~ 22:00</div>
						<div class="s-data-box" start="2130" end="2200"></div>
					</div>
					<div class="s-time-list">
						<div class="s-time k-time">22:00 ~ 22:30</div>
						<div class="s-data-box" start="2200" end="2230"></div>
					</div>
					<div class="s-time-list">
						<div class="s-time l-time">22:30 ~ 23:00</div>
						<div class="s-data-box" start="2230" end="2300"></div>
					</div>
					<div class="s-time-list">
						<div class="s-time m-time">23:00 ~ 23:30</div>
						<div class="s-data-box" start="2300" end="2330"></div>
					</div>
					<div class="s-time-list">
						<div class="s-time n-time">23:30 ~ 24:00</div>
						<div class="s-data-box" start="2330" end="2400"></div>
					</div>
				</div>
			</c:forEach>

			<div class="s-insert-a-container">
				<!-- 로그인 한 사람만 인서트 가능하도록 value에 세션값 넣어서 js에서 체크 -->
				<button id="schedule-insert-detail-button"
					value="${sessionScope.accountInfo.u_twitter_id }">スケジュール登録</button>
				<div class="s-insert-content-a-box">
					<form action="ScheduleInsertC" id="schedule-form">
						<div class="s-insert-content-b-box">
							<div class="s-member-select">
								<select name="s_member" id="s-member-list">
									<option value="999">メンバー</option>
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
									<option value="22">雫川なのか</option>
									<option value="23">堕天しすた</option>
									<option value="24">山寧恋</option>
									<option value="25">翠森アトリ</option>
								</select>
							</div>
							<div class="s-input-container">
								<div class="s-input-box">
									<div class="input-date">
										<input name="s_date" type="date" id="schedule-date"
											data-placeholder="日付" />
									</div>
									<div class="input-time">
										<input name="s_time" type="time" id="schedule-time"
											data-placeholder="時間" />
									</div>
									<div class="input-title">
										<input name="s_title" id="schedule-title"
											placeholder="放送のタイトル" />
									</div>
								</div>
							</div>
							<div class="s-insert-button">
								<button id="s-insert-button">登録</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
