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
        <a href="HC">ハコナカ</a>
      </div>
      <div class="menu-lists">
        <div class="menu-lists-member"><a href="MemberC">メンバー</a></div>
        <div class="menu-lists-archive"><a href="ArchivePageC?p=1">アーカイブ</a></div>
        <div class="menu-lists-calendar"><a href="CalendarC">カレンダー</a></div>
        <div class="menu-lists-schedule"><a href="ScheduleC">スケジュール</a></div>
        <div class="menu-lists-trade">
          <a
            style="cursor: pointer"
            onclick="openTradePage(${sessionScope.twitterId})"
            >トレード</a
          >
        </div>
        <div class="menu-lists-announcement"><a href="Announcement">お知らせ</a></div>
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
  </body>
</html>
