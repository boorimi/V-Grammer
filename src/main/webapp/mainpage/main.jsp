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
  <body>
    <main>
      <div style="display: flex">
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
      <div class="live-contents">
        <div class="left-slider-button">
          <svg
            width="60"
            height="60"
            viewBox="0 0 60 60"
            fill="black"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              fill-rule="evenodd"
              clip-rule="evenodd"
              d="M5 17.5C5 10.5964 10.5964 5 17.5 5H42.5C49.4036 5 55 10.5964 55 17.5V42.5C55 49.4036 49.4036 55 42.5 55H17.5C10.5964 55 5 49.4036 5 42.5V17.5ZM17.5 10C13.3579 10 10 13.3579 10 17.5V42.5C10 46.6421 13.3579 50 17.5 50H42.5C46.6421 50 50 46.6421 50 42.5V17.5C50 13.3579 46.6421 10 42.5 10H17.5ZM34.2678 20.7322C35.2441 21.7085 35.2441 23.2915 34.2678 24.2678L28.5355 30L34.2678 35.7322C35.2441 36.7085 35.2441 38.2915 34.2678 39.2678C33.2915 40.2441 31.7085 40.2441 30.7322 39.2678L23.2322 31.7678C22.2559 30.7915 22.2559 29.2085 23.2322 28.2322L30.7322 20.7322C31.7085 19.7559 33.2915 19.7559 34.2678 20.7322Z"
              fill="black"
            />
          </svg>
        </div>
        <!--  여기부터 진짜 방송 나오는지 테스트용 -->
        <c:forEach items="${streamIds }" var="s">
          <div class="live-content">
            <iframe
              width="960"
              height="540"
              src="https://www.youtube.com/embed/${s.address }"
              frameborder="0"
              allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
              allowfullscreen
            ></iframe>
          </div>
        </c:forEach>
        <!--  방송 페이지 끝 -->
        <div class="right-slider-button">
          <svg
            width="60"
            height="60"
            viewBox="0 0 60 60"
            fill="black"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              fill-rule="evenodd"
              clip-rule="evenodd"
              d="M0 15C0 6.71573 6.71573 0 15 0H45C53.2843 0 60 6.71573 60 15V45C60 53.2843 53.2843 60 45 60H15C6.71573 60 0 53.2843 0 45V15ZM15 6C10.0294 6 6 10.0294 6 15V45C6 49.9706 10.0294 54 15 54H45C49.9706 54 54 49.9706 54 45V15C54 10.0294 49.9706 6 45 6H15ZM24.8787 18.8787C26.0503 17.7071 27.9497 17.7071 29.1213 18.8787L38.1213 27.8787C39.2929 29.0503 39.2929 30.9497 38.1213 32.1213L29.1213 41.1213C27.9497 42.2929 26.0503 42.2929 24.8787 41.1213C23.7071 39.9497 23.7071 38.0503 24.8787 36.8787L31.7574 30L24.8787 23.1213C23.7071 21.9497 23.7071 20.0503 24.8787 18.8787Z"
              fill="black"
            />
          </svg>
        </div>
      </div>
      <div class="main-dday-container">
        <c:forEach var="dday" items="${ddayList}">
          <c:if test="${dday.daysUntilDday >= -14 && dday.daysUntilDday <= 0}">
            <div>
              <div>${dday.m_name}</div>
              <div>${dday.m_debut}</div>
              <div>${dday.daysUntilDday}</div>
            </div>
          </c:if>
        </c:forEach>
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
  </body>
</html>
