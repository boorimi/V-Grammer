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
		<li><a href="">メンバー</a></li>
		<li><a href="">アーカイブ</a></li>
		<li><a href="">カレンダー</a></li>
		<li><a href="">スケジュール</a></li>
		<li><a href="">トレード</a></li>
		<li><a href="">お知らせ</a></li>

      </ul>

      <div class="nav-userinfo">
        <div class="nav-user">ザビラン様、ようこそ！</div>
        <div class="my-page">マイページ</div>
      </div>
    </nav>
    
<div class="content">
		<jsp:include page="${content }"></jsp:include>
				
	</div>
   
  </body>
</html>