document.addEventListener("DOMContentLoaded", function() {
    var rows = document.querySelectorAll("#dday tbody tr");

    // 행을 배열로 변환하여 정렬
    var sortedRows = Array.from(rows).sort(function(rowA, rowB) {
        var daysUntilDebutDdayA = parseInt(rowA.cells[2].textContent.trim());
        var daysUntilDebutDdayB = parseInt(rowB.cells[2].textContent.trim());
        var daysUntilBirthDdayA = parseInt(rowA.cells[4].textContent.trim());
        var daysUntilBirthDdayB = parseInt(rowB.cells[4].textContent.trim());

        var nearestDdayA = Math.min(daysUntilDebutDdayA, daysUntilBirthDdayA);
        var nearestDdayB = Math.min(daysUntilDebutDdayB, daysUntilBirthDdayB);

        return nearestDdayB - nearestDdayA; // 내림차순 정렬
    });

    // 정렬된 행을 다시 테이블에 추가
    var tbody = document.querySelector("#dday tbody");
    tbody.innerHTML = ""; // 기존 테이블 내용 초기화
    sortedRows.forEach(function(row) {
        tbody.appendChild(row);
    });

    var calendarButton = document.getElementById("calendarButton");
    calendarButton.addEventListener("click", function() {
        window.location.href = 'CalendarC';
    });
});
