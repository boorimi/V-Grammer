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

        var dateCell = row.cells[1]; // 기념일 셀
        var dateText = dateCell.textContent.trim();
        var eventDate = parseEventDate(dateText); // 기념일 날짜 파싱
        console.log("기념일 날짜 파싱 결과:", eventDate);

        if (eventDate) {
            eventDate.setFullYear(today.getFullYear());
            if (eventDate < today) {
                eventDate.setFullYear(today.getFullYear() + 1);
            }

            var daysUntilNextEvent = calculateDaysUntilEvent(today, eventDate);
            ddayValue = calculatePositiveDday(daysUntilNextEvent);
        } else {
            console.log("기념일 날짜 파싱 실패:", dateText);
        }

        // D-day 값을 업데이트하고 음수값이 아닌 경우 앞에 "D-"를 붙임
        if (ddayValue >= 0) {
            ddayCell.textContent = "D-" + ddayValue;
        } else {
            ddayCell.textContent = ddayValue;
        }
    });

    // 파싱된 날짜와 오늘 사이의 일수를 계산하는 함수
    function calculateDaysUntilEvent(today, eventDate) {
        var timeDifference = eventDate - today;
        var dayDifference = Math.ceil(timeDifference / (1000 * 60 * 60 * 24));
        console.log("계산된 디데이:", dayDifference);
        return dayDifference;
    }

    // 기념일 날짜를 파싱하는 함수
    function parseEventDate(dateText) {
        var datePatternFull = /(\d{4})-(\d{2})-(\d{2})/; // YYYY-MM-DD 형식
        var datePatternShort = /(\d{2})-(\d{2})/; // MM-DD 형식

        var matchFull = dateText.match(datePatternFull);
        if (matchFull) {
            var year = parseInt(matchFull[1]);
            var month = parseInt(matchFull[2]) - 1; // 월은 0부터 시작하므로 -1
            var day = parseInt(matchFull[3]);
            return new Date(year, month, day);
        }

        var matchShort = dateText.match(datePatternShort);
        if (matchShort) {
            var month = parseInt(matchShort[1]) - 1; // 월은 0부터 시작하므로 -1
            var day = parseInt(matchShort[2]);
            return new Date(today.getFullYear(), month, day);
        }

        return null;
    }

    // 디데이 값을 0에서 365 사이로 조정하는 함수
    function calculatePositiveDday(ddayValue) {
        while (ddayValue > 365) {
            ddayValue -= 365;
        }
        while (ddayValue < 0) {
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
