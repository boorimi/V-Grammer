<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <script
      src="https://code.jquery.com/jquery-3.7.1.js"
      integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
      crossorigin="anonymous"
    ></script>

    <link rel="stylesheet" href="css/schedule.css" />
    <script type="text/javascript" src="js/schedule.js" defer></script>

    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <title>Insert title here</title>
  </head>
  <body>
    <div class="schedule-container">
    
        <div class="s-week-list">
			<input id="s-week" type="radio" name="tab_item" checked>
			<label class="tab_item" for="all">スケジュール</label>
			<input id="s-mon" type="radio" name="tab_item">
			<label class="tab_item" for="programming">月</label>
			<input id="s-tue" type="radio" name="tab_item">
			<label class="tab_item" for="design">火</label>
			<input id="s-wen" type="radio" name="tab_item">
			<label class="tab_item" for="design">水</label>
			<input id="s-thr" type="radio" name="tab_item">
			<label class="tab_item" for="design">木</label>
			<input id="s-fri" type="radio" name="tab_item">
			<label class="tab_item" for="design">金</label>
			<input id="s-sat" type="radio" name="tab_item">
			<label class="tab_item" for="design">土</label>
			<input id="s-sun" type="radio" name="tawjdｄｄb_item">
			<label class="tab_item" for="design">日</label>
		</div>

      <div class="s-content-box">
        <div class="s-time-list">
          <div class="s-time-a">00:00 ~ 12:00</div>
          <div class="s-time-a-data">
          <div id="s-hidden-data"></div>
            <div id="s-data">test1</div>
            <div id="s-data">test1</div>
            <div id="s-data">test1</div>
            <div id="s-data">test1</div>
            <div id="s-data">test1</div>
            <div id="s-data">test1</div>
            <div id="s-data">test1</div>
            <div id="s-data">test1</div>
            <div id="s-data">test1</div>
            <div id="s-data">test1</div>
            <div id="s-data">test1</div>
            <div id="s-data">test1</div>
            <div id="s-data">test1</div>
            <div id="s-data">test1</div>
            <div id="s-data">test1</div>
            <div id="s-data">test1</div>
          </div>
        </div>
        <div class="s-time-list">
          <div class="s-time-a">12:00 ~ 18:00</div>
          <div class="s-time-a-data">
          </div>
        </div>
        <div class="s-time-list">
          <div class="s-time-b">18:00 ~ 18:30</div>
          <div class="s-time-a-data">
          </div>
        </div>
        <div class="s-time-list">
          <div class="s-time-b">18:30 ~ 19:00</div>
          <div class="s-time-a-data">
          <div id="s-data">test1</div>
          <div id="s-data">test1</div>
          <div id="s-data">test1</div>
          <div id="s-data">test1</div>
          <div id="s-data">test1</div>
          <div id="s-data">test1</div>
          <div id="s-data">test1</div>
          <div id="s-data">test1</div>
          <div id="s-data">test1</div>
          <div id="s-data">test1</div>
          <div id="s-data">test1</div>
          </div>
        </div>
        <div class="s-time-list">
          <div class="s-time-b">19:30 ~ 20:00</div>
          <div class="s-time-a-data">
          </div>
        </div>
        <div class="s-time-list">
          <div class="s-time-b">20:30 ~ 21:00</div>
          <div class="s-time-a-data">
          </div>
        </div>
        <div class="s-time-list">
          <div class="s-time-b">21:30 ~ 22:00</div>
          <div class="s-time-a-data">
          </div>
        </div>
        <div class="s-time-list">
          <div class="s-time-b">22:30 ~ 23:00</div>
          <div class="s-time-a-data">
          </div>
        </div>
        <div class="s-time-list">
          <div class="s-time-b">23:30 ~ 00:00</div>
          <div class="s-time-a-data">
          </div>
        </div>
      </div>

      <div class="schedule-insert-container">
        <!-- 로그인 한 사람만 인서트 가능하도록 value에 세션값 넣어서 js에서 체크 -->
        <button
          class="schedule-insert-detail-button"
          value="${sessionScope.accountInfo.u_twitter_id }"
        >
          スケジュール登録
        </button>
        <div class="schedule-insert-inner-container">
          <form action="ScheduleInsertC" id="schedule-form">
            <div class="schedule-insert-box">
              <div class="">
                <select name="s_member" id="schedule-member">
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
                </select>
              </div>
              <div class="insert-box-date">
                <input
                  name="s_date"
                  type="date"
                  id="schedule-date"
                  data-placeholder="日付"
                />
              </div>
              <div class="insert-box-time">
                <input
                  name="s_time"
                  type="time"
                  id="schedule-time"
                  data-placeholder="時間"
                />
              </div>
              <div class="insert-box-title">
                <input
                  name="s_title"
                  style="width: 500px"
                  id="schedule-title"
                  placeholder="放送のタイトル"
                />
              </div>
            </div>
            <div class="schedule-insert-box">
              <div id="schedule-blank-box"></div>
              <input name="s_date" type="date" id="schedule-date" />
              <input name="s_time" type="time" id="schedule-time" />
              <input name="s_title" style="width: 500px" id="schedule-title" />
            </div>
            <div class="schedule-insert-box">
              <div id="schedule-blank-box"></div>
              <input name="s_date" type="date" id="schedule-date" />
              <input name="s_time" type="time" id="schedule-time" />
              <input name="s_title" style="width: 500px" id="schedule-title" />
            </div>
            <div class="schedule-insert-box">
              <div id="schedule-blank-box"></div>
              <input name="s_date" type="date" id="schedule-date" />
              <input name="s_time" type="time" id="schedule-time" />
              <input name="s_title" style="width: 500px" id="schedule-title" />
            </div>
            <div class="schedule-insert-box">
              <div id="schedule-blank-box"></div>
              <input name="s_date" type="date" id="schedule-date" />
              <input name="s_time" type="time" id="schedule-time" />
              <input name="s_title" style="width: 500px" id="schedule-title" />
            </div>
            <div class="schedule-insert-box">
              <div id="schedule-blank-box"></div>
              <input name="s_date" type="date" id="schedule-date" />
              <input name="s_time" type="time" id="schedule-time" />
              <input name="s_title" style="width: 500px" id="schedule-title" />
            </div>
            <div class="schedule-insert-box">
              <div id="schedule-blank-box"></div>
              <input name="s_date" type="date" id="schedule-date" />
              <input name="s_time" type="time" id="schedule-time" />
              <input name="s_title" style="width: 500px" id="schedule-title" />
            </div>
            <button class="schedule-insert-button">登録</button>
          </form>
        </div>
      </div>
    </div>
    <h3>시간쪼개는 단위</h3>
    <h4>00시~12시 1블록</h4>
    <h4>12시~18시 1블록</h4>
    <h4>18시~24시까지 30분단위로 1블록</h4>
  </body>
</html>
