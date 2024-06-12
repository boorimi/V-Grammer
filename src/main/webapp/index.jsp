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
    <nav>
      <div class="main-logo">
        <a href="HC">ハコナカ</a>
      </div>
      <ul class="mene-lists">
        <li><a href="MemberC">メンバー</a></li>
        <li><a href="ArchivePageC?p=1">アーカイブ</a></li>
        <li><a href="CalendarC">カレンダー</a></li>
        <li><a href="ScheduleC">スケジュール</a></li>
        <li>
          <a
            style="cursor: pointer"
            onclick="openTradePage(${sessionScope.twitterId})"
            >トレード</a
          >
        </li>
        <li><a href="Announcement">お知らせ</a></li>
      </ul>

      <div class="nav-userinfo">
        <div class="nav-user">
          <jsp:include page="${loginContent }"></jsp:include>
        </div>
      </div>
    </nav>

    <div class="content">
      <jsp:include page="${content }"></jsp:include>
    </div>
  </body>
</html>
