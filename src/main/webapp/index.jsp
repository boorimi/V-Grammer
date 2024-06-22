<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="stylesheet" href="css/index.css" />
    <script
      src="https://code.jquery.com/jquery-3.7.1.js"
      integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
      crossorigin="anonymous"
    ></script>
    <script src="js/index.js" defer></script>
  </head>
  <body>
    <div class="main-menu-all">
      <div class="main-logo">
        <div>
          <a href="HC"><img src="haco_img/haconaka_logo.png" /></a>
        </div>
      </div>
      <div class="menu-lists">
        <div onclick="location.href='MemberC'" class="menu-lists-member">
          メンバー
        </div>
        <div
          onclick="location.href='ArchiveC?'"
          class="menu-lists-archive"
        >
          アーカイブ
        </div>
        <div onclick="location.href='CalendarC'" class="menu-lists-calendar">
          カレンダー
        </div>
        <div onclick="location.href='ScheduleC'" class="menu-lists-schedule">
          スケジュール
        </div>
        <div
          onclick="openTradePage(${sessionScope.twitterId})"
          class="menu-lists-trade"
        >
          トレード
        </div>
        <div
          onclick="location.href='Announcement'"
          class="menu-lists-announcement"
        >
          お知らせ
        </div>
      </div>

      <div class="nav-userinfo">
        <div class="nav-user">
          <jsp:include page="${loginContent }"></jsp:include>
        </div>
      </div>
    </div>

    <div class="content">
      <jsp:include page="${content }"></jsp:include>
    </div>
    <footer>
      <div class="footer-container">
        <div class="footer-menu-all">
          <div class="footer-logo">
            <a href="HC"><img src="haco_img/haconaka_logo.png" /></a>
          </div>
          <div class="footer-menu-lists">
            <div class="footer-menu-lists-member">
              <a href="MemberC">メンバー</a>
            </div>
            <div class="footer-menu-lists-archive">
              <a href="ArchivePageC?p=1">アーカイブ</a>
            </div>
            <div class="footer-menu-lists-calendar">
              <a href="CalendarC">カレンダー</a>
            </div>
            <div class="footer-menu-lists-schedule">
              <a href="ScheduleC">スケジュール</a>
            </div>
            <div class="footer-menu-lists-trade">
              <a
                style="cursor: pointer"
                onclick="openTradePage(${sessionScope.twitterId})"
                >トレード</a
              >
            </div>
            <div class="footer-menu-lists-announcement">
              <a href="Announcement">お知らせ</a>
            </div>
          </div>
        </div>
        <div class="footer-contents">
          <div class="footer-content-haconect">
            <div>ハコネクト公式</div>
            <div class="footer-content-haconect-icon">
              <div>
                <a
                  target="_blank"
                  href="https://www.youtube.com/channel/UCR_NmKXLzwhhW3E0tXHJglQ"
                >
                  <img src="haco_img/icon-youtube.png" />
                </a>
              </div>
              <div>
                <a target="_blank" href="https://x.com/haconect">
                  <img src="haco_img/icon-twitter.png" />
                </a>
              </div>
              <div>
                <a target="_blank" href="https://haconect.com/">
                  <img src="haco_img/icon_haconect.png" />
                </a>
              </div>
              <div>
                <a target="_blank" href="https://discord.com/invite/KAXPJS9JMk">
                  <img src="haco_img/icon_discord.png" />
                </a>
              </div>
            </div>
          </div>
          <div class="footer-content-haconaka">
            ハコナカ公式アカウント<br />
            <a href="https://x.com/haconectnakama">
              <img src="haco_img/icon-twitter.png" />
            </a>
            <br />
            お問い合わせ<br />
            ds6951@naver.com
          </div>
          <div class="footer-content-teamname">
            制作 : Team V-Grammer <br />
            Kim dae-san (キム・デサン)<br />
            Choi bo-mi (チェ・ボミ) Park joon-woo (じゅぬ) <br />
            Park keon-woo(パク・ゴヌ) Kim sang-woo (キム・サンウ)<br />
            <br />
            Special Thanks <br />
            Mz
          </div>
        </div>
      </div>
    </footer>
  </body>
</html>
