<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
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

      <div class="sliderandbutton">
      <div class="slider">
        <!-- YouTube Live 슬라이드들이 여기에 들어갈 것입니다 -->
        <c:forEach items="${streamIds }" var="s">
          <div class="live-content">
            <iframe
              width="1280"
              height="810"
              src="https://www.youtube.com/embed/${s.address }"
              frameborder="0"
              allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
              allowfullscreen
            ></iframe>
          </div>

        </c:forEach>
      </div>
      <button class="prev">&lt;</button>
      <button class="next">&gt;</button>
    </div>
        <!--  방송 페이지 끝 -->
      </div>
      <div class="main-dday-container">
    <div class="dday-items-wrapper">
        <c:forEach var="dday" items="${ddayList}">
            <c:if test="${dday.daysUntilDday >= -14 && dday.daysUntilDday <= 0}">
                <div class="dday-item">
                    <span>이름 : ${dday.m_name} </span>
                    <span>데뷔 날짜 : ${dday.m_debut} </span>
                    <span>D-day : D${dday.daysUntilDday} </span>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>

      <div class="main-news">
        <div class="news-photo">
          <img src="haco_img/img/newimg2.png" alt="">
        </div>
        <ul class="news-board">
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          

        </ul>
        

      </div>

      <div class="today-vtuber">
        <p>今日のおすすめVtuber</p>
        <div class="today-vtuber-info">
          <div class="today-vtuber-photo">
            <img alt="" src="haco_img/icon/${recommendVtuber[0].icon }" />
          </div>
          <div class="today-vtuber-archive-etc">
            <div class="today-vtuber-archive">
              <iframe
                width="480"
                height="270"
                src="https://www.youtube.com/embed/${recommendVtuber[0].videoId }"
                frameborder="0"
                allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                allowfullscreen
              ></iframe>
            </div>
            <div class="today-vtuber-etc">
              <div>
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
    </main>
    <footer></footer>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

    <script>
      $(document).ready(function () {
  // bxSlider 초기화
  var slider = $(".slider").bxSlider({
    mode: "horizontal", // 슬라이드 모드 설정 (수평으로)
    auto: false, // 자동으로 슬라이드 전환 여부
    controls: false, // 이전/다음 버튼을 표시하지 않음
    pager: false, // 페이지 버튼을 표시하지 않음
    slideMargin: 0, // 슬라이드 간의 여백 (픽셀)
    minSlides: 1, // 최소로 보여줄 슬라이드 수
    maxSlides: 1, // 최대로 보여줄 슬라이드 수
    moveSlides: 1, // 한 번에 슬라이드할 슬라이드 수
    slideWidth: 1280, // 슬라이드의 너비 (픽셀)
    adaptiveHeight: true // 슬라이드 높이를 내용에 맞게 자동 조정
  });

  // 이전 버튼 클릭 이벤트
  $(".prev").click(function () {
    slider.goToPrevSlide();
    return false;
  });

  // 다음 버튼 클릭 이벤트
  $(".next").click(function () {
    slider.goToNextSlide();
    return false;
  });
});

    </script>
  </body>
</html>
