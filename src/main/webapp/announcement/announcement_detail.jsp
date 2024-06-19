<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>お知らせ</title>
    <script src="js/announcement.js"></script>
    <link rel="stylesheet" href="css/announcement.css" />
  </head>
  <body>
    <div class="announcement-container">
      <div class="announcement-title"><p>お知らせ</p></div>
      <div class="announcement-detail-content">
        <div class="announcement-con-title"><h1>${announcements.title }</h1></div>
        <div class="announcement-con-date"><h3>${announcements.date }</h3></div>
      </div>
      <div class="announcement-con-txt">${announcements.text}</div>
      <div style="display: flex; justify-content: space-evenly; margin-top:30px;">
        <c:if test="${sessionScope.twitterId == 459978973 }">  
        <div>
          <button class="cute-button-blue"
            onclick="location.href='UpdateAnnouncement?no=${announcements.pk}'"
          >
            수정
          </button>
        </div>
        <div>
          <button  class="cute-button-blue" onclick="announcementDelete(${announcements.pk})">
            삭제
          </button>
        </div>
        </c:if>
      </div>
    </div>
  </body>
</html>
