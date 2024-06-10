<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="stylesheet" href="css/index.css" />
  </head>
  <body>
    <nav>
      <div class="main-logo"></div>
      <ul class="mene-lists">
        <li>メンバー</li>
        <li>アーカイブ</li>
        <li>カレンダー</li>
        <li>スケジュール</li>
        <li>トレード</li>
        <li>お知らせ</li>
      </ul>

      <div class="nav-userinfo">
        <div class="nav-user">ザビラン様、ようこそ！</div>
        <div class="my-page">マイページ</div>
      </div>
    </nav>
    
    <jsp:includ>
    <div class="live-members"></div>
    </jsp:includ>
    
    <script type="text/javascript" src="js/index.js"></script>
  </body>
</html>
