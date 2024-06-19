document.addEventListener("DOMContentLoaded", function() {
    console.log("DOMContentLoaded 이벤트가 발생했습니다.");

    var rows = document.querySelectorAll("#dday tbody tr");
    console.log("테이블 행을 가져왔습니다:", rows);

    var today = new Date();
    today.setHours(0, 0, 0, 0); // 오늘의 시간을 00:00:00으로 설정

    rows.forEach(function(row) {
        var ddayCell = row.cells[2]; // 디데이까지 남은 일수 셀
        var ddayText = ddayCell.textContent.trim();
        var ddayValue = parseInt(ddayText);

        if (ddayValue >= 0) {
            // 양수이면 DB에서 불러온 날짜의 연도만 내년으로 설정하고 계산
            var dateCell = row.cells[1]; // 기념일 셀
            var dateText = dateCell.textContent.trim();
            var eventDate = parseEventDate(dateText); // 기념일 날짜 파싱

            if (eventDate) {
                eventDate.setFullYear(today.getFullYear() + 1); // 연도를 내년으로 설정
                var daysUntilNextEvent = calculateDaysUntilEvent(today, eventDate);
                ddayCell.textContent = adjustDdayValue(daysUntilNextEvent);
            }
        } else {
            // 음수인 경우 -365가 넘어가지 않도록 조정
            ddayCell.textContent = adjustDdayValue(ddayValue);
        }
    });

    // 파싱된 날짜와 오늘 사이의 일수를 계산하는 함수
    function calculateDaysUntilEvent(today, eventDate) {
        var timeDifference = eventDate - today;
        var dayDifference = Math.ceil(timeDifference / (1000 * 60 * 60 * 24));
        return -dayDifference; // 음수로 반환
    }

    // 기념일 날짜를 파싱하는 함수
    function parseEventDate(dateText) {
        var datePattern = /(\d{4})-(\d{2})-(\d{2})/; // YYYY-MM-DD 형식
        var match = dateText.match(datePattern);
        if (match) {
            var year = parseInt(match[1]);
            var month = parseInt(match[2]) - 1; // 월은 0부터 시작하므로 -1
            var day = parseInt(match[3]);
            return new Date(year, month, day);
        }
        return null;
    }

    // 디데이 값을 조정하는 함수 (음수인 경우 -365가 넘어가지 않도록)
    function adjustDdayValue(ddayValue) {
        while (ddayValue < -365) {
            ddayValue += 365;
        }
        return ddayValue;
    }

    var calendarButton = document.getElementById("calendarButton");
    console.log("캘린더로 돌아가기 버튼을 가져왔습니다:", calendarButton);

    calendarButton.addEventListener("click", function() {
        console.log("캘린더로 돌아가기 버튼이 클릭되었습니다.");

        // CalendarC 서블릿으로 이동
        console.log("CalendarC 서블릿으로 이동합니다.");
        window.location.href = 'CalendarC';
    });
});
