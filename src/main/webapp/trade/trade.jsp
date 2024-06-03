<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>トレード</title>
    <script
      src="https://code.jquery.com/jquery-3.7.1.js"
      integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
      crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="css/announcement.css" />
  </head>
  <body>
    <div class="announcement-container">
      <div class="announcement-title"><h1>トレード</h1></div>
      <div class="announcement-conmain">
        <!-- 본문페이지 for문 시작 -->
        <c:set var="totalItems" value="${fn:length(trades)}" />
        <c:forEach var="t" items="${trades }" varStatus="status">
          <div class="announcement-content">
            <div class="announcement-number">${totalItems - status.index}</div>
            <div class="announcement-con-title">
              <a href="SelectAnnouncement?no=${t.pk}">${t.text }</a>
            </div>
            <div class="announcement-con-txt">${t.date }</div>
          </div>
        </c:forEach>
        <!-- 본문페이지 for문 끝 -->
      </div>
      <div id="insert-button">
        <button onclick="location.href='InsertAnnouncement'">글쓰기</button>
      </div>
      <div class="announcement-bottom">
        <div><a href="AnnouncementPage?p=1">처음</a></div>
        <c:set var="pageUnit" value="4" />
        <c:set
          var="page"
          value="${fn:substringBefore(Math.floor((curPageNo - 1) div pageUnit) * pageUnit, '.')}"
        />
        <div>
          <c:if test="${page != 0}">
            <a href="AnnouncementPage?p=${page - pageUnit + 1}"
              >이전 ${pageUnit }페이지</a
            >
          </c:if>
        </div>
        <div style="display: flex">
          <c:forEach
            var="i"
            begin="1"
            end="${page + pageUnit <= pageCount ? page + pageUnit : pageCount}"
          >
            <div class="announcement-page-no">
              <a href="AnnouncementPage?p=${i }">[${i }]</a>
            </div>
          </c:forEach>
        </div>
        <div>
          <c:if
            test="${page + (curPageNo % pageUnit) < pageCount - (pageCount % pageUnit) && page + pageUnit != pageCount}"
          >
            <a href="AnnouncementPage?p=${page + pageUnit + 1 }"
              >다음 ${pageUnit }페이지</a
            >
          </c:if>
        </div>
        <div><a href="AnnouncementPage?p=${pageCount}">끝</a></div>
      </div>
    </div>
    <!-- <div>
      <input id="search-input" />
      <button id="search-btn">버튼</button>
      <hr />
      <span id="result"></span>
    </div> -->
  </body>
</html>
