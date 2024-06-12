<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>YouTube Live Slider</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css"
    />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
  </head>
  <body>
    <div class="slider">
      <!-- YouTube Live 슬라이드들이 여기에 들어갈 것입니다 -->
    </div>

    <button class="prev">이전</button>
    <button class="next">다음</button>

    <input id="videoId" value="${s.address }" />

    <script>
      let livestream = document.querySelector("#videoId");

      $(document).ready(function () {
        // YouTube 라이브 비디오 ID 배열
        var youtubeLiveVideos = [
        	livestream
          // 필요한 만큼 계속 추가
        ];

        // 각 YouTube 비디오를 슬라이드로 추가
        youtubeLiveVideos.forEach(function (videoId) {
          $(".slider").append(
            '<div><iframe width="560" height="315" src="https://www.youtube.com/embed/' +
              videoId +
              '?autoplay=0" frameborder="0" allowfullscreen></iframe></div>'
          );
        });

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
          slideWidth: 560, // 슬라이드의 너비 (픽셀)
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
