<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>달력</title>
    <link rel="stylesheet" href="css/calendar.css">
</head>
<body>
  <header>
    <div class="wrapper">
      <div class="c-monthyear">
        <div class="c-month">
          <span id="prev" class="prev fa fa-angle-left" aria-hidden="true"></span>
          <div id="c-paginator">
            <span class="c-paginator__month">JANUARY</span>
            <span class="c-paginator__month">FEBRUARY</span>
            <span class="c-paginator__month">MARCH</span>
            <span class="c-paginator__month">APRIL</span>
            <span class="c-paginator__month">MAY</span>
            <span class="c-paginator__month">JUNE</span>
            <span class="c-paginator__month">JULY</span>
            <span class="c-paginator__month">AUGUST</span>
            <span class="c-paginator__month">SEPTEMBER</span>
            <span class="c-paginator__month">OCTOBER</span>
            <span class="c-paginator__month">NOVEMBER</span>
            <span class="c-paginator__month">DECEMBER</span>
          </div>
          <span id="next" class="next fa fa-angle-right" aria-hidden="true"></span>
        </div>
        <span class="c-paginator__year">2022</span>
      </div>
      <div class="c-sort">
        <a class="o-btn c-today__btn" href="javascript:;">TODAY</a>
      </div>
    </div>
  </header>
  <div class="wrapper">
    <div class="c-calendar">
      <div class="c-calendar__style c-aside">
        <a class="c-add o-btn js-event__add" href="javascript:;">add event <span class="fa fa-plus"></span></a>
        <div class="c-aside__day">
          <span class="c-aside__num"></span> <span class="c-aside__month"></span>
        </div>
        <div class="c-aside__eventList">
        </div>
      </div>
      <div class="c-cal__container c-calendar__style">
        <script>
          // 변수 설정
          var year = 2022;
          var today = new Date("January 1, " + year);
          var start_day = today.getDay() + 1;

          // 함수: 요일 타이틀 출력
          function day_title(day_name) {
            document.write("<div class='c-cal__col'>" + day_name + "</div>");
          }

          // 함수: 달력 내용 채우기
          function fill_table(month, month_length, indexMonth) {
            var day = 1;
            document.write("<div class='c-main c-main-" + indexMonth + "'>");
            document.write("<div class='c-cal__row'>");
            day_title("Sun");
            day_title("Mon");
            day_title("Tue");
            day_title("Wed");
            day_title("Thu");
            day_title("Fri");
            day_title("Sat");
            document.write("</div>");
            document.write("<div class='c-cal__row'>");
            
            // 첫째 주의 빈 셀 채우기
            for (var i = 1; i < start_day; i++) {
              document.write("<div class='c-cal__cel'></div>");
            }

            // 첫째 주 채우기
            for (var i = start_day; i < 8; i++) {
              document.write(
                "<div data-day='" +
                year + "-" + indexMonth + "-0" + day +
                "' class='c-cal__cel'><p>" +
                day +
                "</p></div>"
              );
              day++;
            }
            document.write("</div>");

            // 나머지 주 채우기
            while (day <= month_length) {
              document.write("<div class='c-cal__row'>");
              for (var i = 1; i <= 7 && day <= month_length; i++) {
                var paddedDay = (day < 10) ? "0" + day : day;
                document.write(
                  "<div data-day='" +
                  year + "-" + indexMonth + "-" + paddedDay +
                  "' class='c-cal__cel'><p>" +
                  day +
                  "</p></div>"
                );
                day++;
              }
              document.write("</div>");
              start_day = i;
            }
            document.write("</div>");
          }

          // 각 달력 테이블 채우기
          fill_table("January", 31, "01");
          fill_table("February", 28, "02");
          fill_table("March", 31, "03");
          fill_table("April", 30, "04");
          fill_table("May", 31, "05");
          fill_table("June", 30, "06");
          fill_table("July", 31, "07");
          fill_table("August", 31, "08");
          fill_table("September", 30, "09");
          fill_table("October", 31, "10");
          fill_table("November", 30, "11");
          fill_table("December", 31, "12");
        </script>
      </div>
    </div>
    <div class="c-event__creator c-calendar__style js-event__creator">
      <a href="javascript:;" class="o-btn js-event__close">CLOSE <span class="fa fa-close"></span></a>
      <form id="addEvent">
        <input placeholder="Event name" type="text" name="name">
        <input type="date" name="date">
        <textarea placeholder="Notes" name="notes" cols="30" rows="10"></textarea>
        <select name="tags">
          <option value="event">event</option>
          <option value="important">important</option>
          <option value="birthday">birthday</option>
          <option value="festivity">festivity</option>
        </select>
      </form>
      <br>
      <a href="javascript:;" class="o-btn js-event__save">SAVE <span class="fa fa-save"></span></a>
    </div>
  </div>
</body>
</html>
