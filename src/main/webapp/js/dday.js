document.addEventListener("DOMContentLoaded", function() {
    console.log("DOMContentLoaded 이벤트가 발생했습니다.");

    var rows = document.querySelectorAll("#dday tbody tr");
    console.log("테이블 행을 가져왔습니다:", rows);

    // 행을 배열로 변환하여 정렬
    var sortedRows = Array.from(rows).sort(function(rowA, rowB) {
        // 생일을 현재 시점에서 가장 가까운 날짜로 정렬
        var birthDateA = rowA.cells[2].textContent.trim();
        var birthDateB = rowB.cells[2].textContent.trim();

        var today = new Date(); // 현재 시간
        var parsedBirthDateA = parseDate(birthDateA, today);
        var parsedBirthDateB = parseDate(birthDateB, today);

        // 생일을 월과 일로 분리하여 비교
        var monthA = parsedBirthDateA.getMonth();
        var monthB = parsedBirthDateB.getMonth();
        var dayA = parsedBirthDateA.getDate();
        var dayB = parsedBirthDateB.getDate();

        // 생일이 오늘 이후인 경우 올해 기준, 이전인 경우 내년 기준으로 설정
        var yearA = today.getFullYear();
        var yearB = today.getFullYear();
        if (parsedBirthDateA <= today) {
            yearA++;
        }
        if (parsedBirthDateB <= today) {
            yearB++;
        }

        // 월과 일을 기준으로 비교하여 정렬
        if (monthA !== monthB) {
            return monthA - monthB; // 월 순으로 정렬
        } else {
            return dayA - dayB; // 같은 월일 경우 일 순으로 정렬
        }
    });

    // 정렬된 행을 다시 테이블에 추가
    var tbody = document.querySelector("#dday tbody");
    tbody.innerHTML = ""; // 기존 테이블 내용 초기화
    sortedRows.forEach(function(row) {
        tbody.appendChild(row);
    });

    var calendarButton = document.getElementById("calendarButton");
    console.log("캘린더로 돌아가기 버튼을 가져왔습니다:", calendarButton);

    calendarButton.addEventListener("click", function() {
        console.log("캘린더로 돌아가기 버튼이 클릭되었습니다.");

        // CalendarC 서블릿으로 이동
        console.log("CalendarC 서블릿으로 이동합니다.");
        window.location.href = 'CalendarC';
    });
});

// 생일을 현재 시점에서 가장 가까운 날짜로 파싱하는 함수
function parseDate(dateString, today) {
    // 생일 날짜를 월과 일로 분리
    var parts = dateString.split("-");
    var month = parseInt(parts[0]) - 1; // 월은 0부터 시작하므로 -1
    var day = parseInt(parts[1]);

    // 오늘의 월과 일을 기준으로 새로운 Date 객체 생성
    var parsedDate = new Date(today.getFullYear(), month, day);

    // 생일이 오늘 이전이면 내후년(2025년)으로 설정, 오늘 이후면 올해 설정
    if (parsedDate <= today) {
        parsedDate.setFullYear(today.getFullYear() + 2); // 내후년(2025년)
    } else {
        parsedDate.setFullYear(today.getFullYear() + 1); // 올해
    }

    return parsedDate;
}
