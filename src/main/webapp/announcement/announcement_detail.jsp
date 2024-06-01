<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="stylesheet" href="css/announcement.css" />
  </head>
  <body>
    <div class="announcement-container">
      <div class="announcement-title"><h1>announcement page detail</h1></div>
      <div class="announcement-content">
        <div class="announcement-con-title">${announcements.title }</div>
        <div class="announcement-con-date">${announcements.date }</div>
      </div>
      <div class="announcement-con-txt">${announcements.text}</div>
      <div style="display: flex; justify-content: space-evenly">
        <div>
          <button
            onclick="location.href='UpdateAnnouncementC?no=${announcements.pk}'"
          >
            수정
          </button>
        </div>
        <div>
          <button onclick="deleteAnnouncement('${announcements.pk}')">
            삭제
          </button>
        </div>
      </div>
    </div>
  </body>
</html>
