<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Kanit:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bxslider@4.2.17/dist/jquery.bxslider.min.css"
    />
    <script
      src="https://code.jquery.com/jquery-3.7.1.js"
      integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
      crossorigin="anonymous"
    ></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bxslider@4.2.17/dist/jquery.bxslider.min.js"></script>
    <script src="js/main.js" defer></script>
  </head>
  <body class="main-body">
    <main>
      <div style="display: flex" class="live-contents">
        <div class="live-members">
          <span class="members">配信中のメンバー：</span>
        </div>
        <!--  아이콘 배열하는 곳 -->
        <c:forEach items="${streamIds }" var="s">
          <div>
            <a
              target="_blank"
              href="https://www.youtube.com/watch?v=${s.address }"
            >
              <img class="live-icon" src="haco_img/icon/${s.icon }" />
            </a>
          </div>
        </c:forEach>
        <!--  아이콘 배열 끝 -->
      </div>
      <!-- 방송 배열 시작-->
      <div class="sliderandbutton">
        <div class="prev">
          <button class="prev-button">&lt;</button>
        </div>
        <c:if test="${streamIds[0].address == null}">
          <div class="live-content-video-null">
            配信中のメンバーがありません。
          </div>
        </c:if>
        <div class="slider">
          <c:if test="${streamIds[0].address != null}">
            <!-- YouTube Live 슬라이드들이 여기에 들어갈 것입니다 -->
            <!-- bx.wrapper 클래스에 직접적으로 수정을 할 수 없어서 border를 없애는 코드를 js파일에 집어넣었음 -->
            <c:forEach items="${streamIds }" var="s">
              <div class="live-content-video">
                <iframe
                  width="1280"
                  height="720"
                  src="https://www.youtube.com/embed/${s.address }"
                  frameborder="0"
                  allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                  allowfullscreen
                ></iframe>
              </div>
            </c:forEach>
            <!-- youtube 슬라이더 끝 -->
          </c:if>
        </div>
        <div class="next">
          <button class="next-button">&gt;</button>
        </div>
      </div>
      <!--  방송 페이지 끝 -->
      <!-- D-day 흐르는거 시작 -->
      <div class="main-dday-container">
        <div class="dday-items-wrapper">
          <c:forEach var="dday" items="${ddayList}">
            <c:if test="${dday.daysUntilDebutDday <= 7}">
              <div class="dday-item">
                <span>名前 : ${dday.name} </span>
                <span>デビュー日 : ${dday.debutDate} </span>
                <span>D-day : D${dday.daysUntilDebutDday} </span>
              </div>
            </c:if>
          </c:forEach>
        </div>
      </div>
      <!-- D-day 흐르는거 끝 -->
      <!-- 뉴스 컨테이너 시작  -->
      <div class="main-news-container">
        <div class="main-news">
          <div class="news-photo">
            <img src="haco_img/img/newimg2.png" alt="News Photo" />
          </div>
          <ul class="news-board">
            <c:forEach var="news" items="${announcements}">
              <span>
                <li class="date-item">
                  <fmt:parseDate
                    value="${news.date}"
                    pattern="yyyy-MM-dd HH:mm:ss"
                    var="parsedDate"
                  />
                  <fmt:formatDate value="${parsedDate}" pattern="MM/dd" />
                </li>
                <li>
                  <a href="SelectAnnouncement?no=${news.pk}">${news.title}</a>
                </li>
              </span>
            </c:forEach>
          </ul>
        </div>
      </div>
      <!-- 오늘의 버튜버 시작 -->
      <div class="today-vtuber-wrapper">
        <div class="today-vtuber">
          <p>今日のおすすめVtuber</p>
          <div class="today-vtuber-info">
            <div class="today-vtuber-photo">
              <img alt="" src="haco_img/icon/${recommendVtuber[0].icon }" />
            </div>
            <div class="today-vtuber-archive-etc">
              <div class="today-vtuber-archive">
                <iframe
                  width="640"
                  height="360"
                  src="https://www.youtube.com/embed/${recommendVtuber[0].videoId}"
                  frameborder="0"
                  allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                  allowfullscreen
                ></iframe>
              </div>
              <div class="today-vtuber-etc">
                <div class="today-vtuber-etc-addr">
                  <div class="today-vtuber-etc-name">
                    ${recommendVtuber[0].name }
                  </div>
                  <div class="today-vtuber-address-box">
                    <c:forEach items="${recommendVtuber }" var="rv">
                      <div id="${rv.category}">
                        <a target="_blank" href="${rv.address }"
                          >${rv.category }</a
                        >
                      </div>
                    </c:forEach>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 오늘의 버튜버 끝 -->
    </main>
  </body>
</html>
