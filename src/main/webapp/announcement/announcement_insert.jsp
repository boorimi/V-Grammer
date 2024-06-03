<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>お知らせ</title>
    <script src="js/announcement.js"></script>
    <link rel="stylesheet" href="css/announcement.css" />
  </head>
  <body>
    <c:choose>
      <c:when test="${announcements.text != null}">
        <c:set var="id" value="updateForm" />
        <c:set var="action" value="UpdateAnnouncement" />
        <c:set var="onclick" value="announcementUpdate()" />
        <c:set var="buttonText" value="수정하기" />
      </c:when>
      <c:otherwise>
        <c:set var="id" value="insertForm" />
        <c:set var="action" value="InsertAnnouncement" />
        <c:set var="onclick" value="announcementInsert()" />
        <c:set var="buttonText" value="작성하기" />
      </c:otherwise>
    </c:choose>
    <div class="announcement-container">
      <div class="announcement-title">
        <h1>お知らせ (insert or update)</h1>
      </div>
      <form id="${id }" action="${action }" method="post">
        <div class="">
          <div class="">
            <div>제목</div>
            <div>
              <input name="title" id="announcement-title" value="${announcements.title }" />
              <input name="no" type="hidden" value="${announcements.pk }" />
            </div>
          </div>
        </div>
        <div class="">
          <div>본문</div>
          <div>
            <textarea style="resize: none" rows="30" cols="80" name="text">
${text2 }</textarea
            >
          </div>
        </div>
        <div>
          <div>
            <button type="button" onclick="${onclick}">${buttonText }</button>
          </div>
          <div>
            <button type="button" onclick="announcementCancleInsert()">
              취소
            </button>
          </div>
        </div>
      </form>
    </div>
  </body>
</html>
