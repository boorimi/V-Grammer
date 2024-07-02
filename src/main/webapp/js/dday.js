document.addEventListener("DOMContentLoaded", function() {
    console.log("DOMContentLoaded 이벤트가 발생했습니다.");

    // 테이블에서 모든 행을 가져오기
    var rows = document.querySelectorAll("#dday tbody tr");
    console.log("테이블 행을 가져왔습니다:", rows);

    // 오늘 날짜를 가져오고 시간을 00:00:00으로 설정
    var today = new Date();
    today.setHours(0, 0, 0, 0);
    console.log("오늘 날짜:", today);

    // 각 행의 데이터를 담을 배열 선언
    var ddayData = [];

    // 각 행에 대해 반복하며 처리
    rows.forEach(function(row) {
        var ddayCell = row.cells[2]; // 디데이까지 남은 일수 셀
        var ddayText = ddayCell.textContent.trim(); // 셀의 텍스트 내용 가져오기
        var ddayValue = parseInt(ddayText); // 텍스트를 정수로 변환

        var dateCell = row.cells[1]; // 기념일 날짜 셀
        var dateText = dateCell.textContent.trim(); // 셀의 텍스트 내용 가져오기
        var eventDate = parseEventDate(dateText); // 기념일 날짜 파싱
        console.log("기념일 날짜 파싱 결과:", eventDate);

        // 기념일 날짜가 파싱됐을 경우 처리
        if (eventDate) {
            eventDate.setFullYear(today.getFullYear()); // 기념일 날짜의 연도를 현재 연도로 설정

            // 만약 오늘 날짜보다 기념일 날짜가 이전이면 내년으로 설정
            if (eventDate < today) {
                eventDate.setFullYear(today.getFullYear() + 1);
            }

            // 오늘부터 기념일까지 남은 일수 계산
            var daysUntilNextEvent = calculateDaysUntilEvent(today, eventDate);

            // 양수로 변환된 디데이 값 계산
            ddayValue = calculatePositiveDday(daysUntilNextEvent);
        } else {
            console.log("기념일 날짜 파싱 실패:", dateText);
        }

        // 디데이 값을 업데이트하고 음수값이 아닌 경우 앞에 "D-"를 붙임
        if (ddayValue >= 0) {
            ddayCell.textContent = "D-" + ddayValue;
        } else {
            ddayCell.textContent = ddayValue;
        }

        // 데이터 객체 생성 및 배열에 추가
        var dataObject = {
            row: row,
            ddayValue: ddayValue
        };
        ddayData.push(dataObject);
    });

    // 디데이 값을 기준으로 데이터 배열 정렬
    ddayData.sort(function(a, b) {
        return a.ddayValue - b.ddayValue;
    });

    console.log("정렬된 디데이 데이터:", ddayData);

    // 테이블의 tbody 엘리먼트 가져오기
    var tbody = document.querySelector("#dday tbody");
    tbody.innerHTML = ""; // tbody 초기화

    // 정렬된 데이터를 테이블에 추가
    ddayData.forEach(function(data) {
        tbody.appendChild(data.row);
    });

    // 기념일 날짜와 오늘 날짜 사이의 일수를 계산하는 함수
    function calculateDaysUntilEvent(today, eventDate) {
        var timeDifference = eventDate - today; // 밀리초 단위의 시간 차이 계산
        var dayDifference = Math.ceil(timeDifference / (1000 * 60 * 60 * 24)); // 일 단위로 변환 및 올림 처리
        console.log("계산된 디데이:", dayDifference);
        return dayDifference; // 계산된 일수 반환
    }

    // 기념일 날짜를 파싱하는 함수
    function parseEventDate(dateText) {
        var datePatternFull = /(\d{4})-(\d{2})-(\d{2})/; // YYYY-MM-DD 형식을 위한 정규식
        var datePatternShort = /(\d{2})-(\d{2})/; // MM-DD 형식을 위한 정규식

        // 전체 형식과 단축 형식에 대해 정규식 매치 시도
        var matchFull = dateText.match(datePatternFull);
        if (matchFull) {
            var year = parseInt(matchFull[1]); // 연도 추출
            var month = parseInt(matchFull[2]) - 1; // 월 추출 (0부터 시작하므로 -1)
            var day = parseInt(matchFull[3]); // 일 추출
            return new Date(year, month, day); // Date 객체 반환
        }

        var matchShort = dateText.match(datePatternShort);
        if (matchShort) {
            var month = parseInt(matchShort[1]) - 1; // 월 추출 (0부터 시작하므로 -1)
            var day = parseInt(matchShort[2]); // 일 추출
            return new Date(today.getFullYear(), month, day); // 현재 연도 기준으로 Date 객체 반환
        }

        return null; // 매치 실패 시 null 반환
    }

    // 양수로 조정된 디데이 값을 반환하는 함수
    function calculatePositiveDday(ddayValue) {
        while (ddayValue > 365) { // 365보다 큰 경우 365로 나눠줌
            ddayValue -= 365;
        }
        while (ddayValue < 0) { // 음수인 경우 365를 더해줌
            ddayValue += 365;
        }
        return ddayValue; // 양수로 조정된 디데이 값 반환
    }

    // "calendarButton" 버튼 엘리먼트 가져오기
    var calendarButton = document.getElementById("calendarButton");
    console.log("캘린더로 돌아가기 버튼을 가져왔습니다:", calendarButton);

    // "calendarButton" 클릭 시 이벤트 처리
    calendarButton.addEventListener("click", function() {
        console.log("캘린더로 돌아가기 버튼이 클릭되었습니다.");

        // "CalendarC" 서블릿으로 이동
        console.log("CalendarC 서블릿으로 이동합니다.");
        window.location.href = 'CalendarC';
    });
});
