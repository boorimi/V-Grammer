document.addEventListener("DOMContentLoaded", () => {
    console.log("DOMContentLoaded 이벤트가 발생했습니다.");

    // 1. 테이블에서 모든 행을 가져오기
    const rows = document.querySelectorAll("#dday tbody tr");
    console.log("테이블 행을 가져왔습니다:", rows);

    // 2. 오늘 날짜를 가져오고 시간을 00:00:00으로 설정
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    console.log("오늘 날짜:", today);

    // 3. 각 행의 데이터를 담을 배열 선언
    const ddayData = [];

    // 4. 각 행에 대해 반복하며 처리
    rows.forEach(row => {
        const ddayCell = row.cells[2]; // 디데이까지 남은 일수 셀
        const ddayText = ddayCell.textContent.trim(); // 셀의 텍스트 내용 가져오기
        let ddayValue = parseInt(ddayText, 10); // 텍스트를 정수로 변환

        const dateCell = row.cells[1]; // 기념일 날짜 셀
        const dateText = dateCell.textContent.trim(); // 셀의 텍스트 내용 가져오기
        const eventDate = parseEventDate(dateText, today); // 기념일 날짜 파싱
        console.log("기념일 날짜 파싱 결과:", eventDate);

        // 5. 기념일 날짜가 파싱됐을 경우 처리
        if (eventDate) {
            if (eventDate < today) {
                eventDate.setFullYear(today.getFullYear() + 1);
            }

            // 6. 오늘부터 기념일까지 남은 일수 계산
            const daysUntilNextEvent = calculateDaysUntilEvent(today, eventDate);

            // 7. 양수로 변환된 디데이 값 계산
            ddayValue = calculatePositiveDday(daysUntilNextEvent);
        } else {
            console.log("기념일 날짜 파싱 실패:", dateText);
        }

        // 8. 디데이 값을 업데이트하고 음수값이 아닌 경우 앞에 "D-"를 붙임
        ddayCell.textContent = ddayValue >= 0 ? `D-${ddayValue}` : ddayValue;

        // 9. 데이터 객체 생성 및 배열에 추가
        ddayData.push({ row, ddayValue });
    });

    // 10. 디데이 값을 기준으로 데이터 배열 정렬
    ddayData.sort((a, b) => a.ddayValue - b.ddayValue);
    console.log("정렬된 디데이 데이터:", ddayData);

    // 11. 테이블의 tbody 엘리먼트 가져오기
    const tbody = document.querySelector("#dday tbody");
    tbody.innerHTML = ""; // tbody 초기화

    // 12. 정렬된 데이터를 테이블에 추가
    ddayData.forEach(data => tbody.appendChild(data.row));

    // 13. "calendarButton" 버튼 엘리먼트 가져오기
    const calendarButton = document.getElementById("calendarButton");
    console.log("캘린더로 돌아가기 버튼을 가져왔습니다:", calendarButton);

    // 14. "calendarButton" 클릭 시 이벤트 처리
    calendarButton.addEventListener("click", () => {
        console.log("캘린더로 돌아가기 버튼이 클릭되었습니다.");
        window.location.href = 'CalendarC';
    });

    // 기념일 날짜와 오늘 날짜 사이의 일수를 계산하는 함수
    function calculateDaysUntilEvent(today, eventDate) {
        const timeDifference = eventDate - today; // 밀리초 단위의 시간 차이 계산
        const dayDifference = Math.ceil(timeDifference / (1000 * 60 * 60 * 24)); // 일 단위로 변환 및 올림 처리
        console.log("계산된 디데이:", dayDifference);
        return dayDifference; // 계산된 일수 반환
    }

    // 기념일 날짜를 파싱하는 함수
    function parseEventDate(dateText, today) {
        const datePatternFull = /(\d{4})-(\d{2})-(\d{2})/; // YYYY-MM-DD 형식을 위한 정규식
        const datePatternShort = /(\d{2})-(\d{2})/; // MM-DD 형식을 위한 정규식

        const matchFull = dateText.match(datePatternFull);
        if (matchFull) {
            const [year, month, day] = matchFull.slice(1).map(Number); // 연도, 월, 일 추출
            return new Date(year, month - 1, day); // Date 객체 반환
        }

        const matchShort = dateText.match(datePatternShort);
        if (matchShort) {
            const [month, day] = matchShort.slice(1).map(Number); // 월, 일 추출
            return new Date(today.getFullYear(), month - 1, day); // 현재 연도 기준으로 Date 객체 반환
        }

        return null; // 매치 실패 시 null 반환
    }

    // 양수로 조정된 디데이 값을 반환하는 함수
    function calculatePositiveDday(ddayValue) {
        ddayValue %= 365; // 365로 나눈 나머지 값으로 조정
        if (ddayValue < 0) {
            ddayValue += 365;
        }
        return ddayValue; // 양수로 조정된 디데이 값 반환
    }
});
