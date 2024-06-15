document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth() + 1;
    var day = today.getDate();

    var currentDate = year + '-' + (month < 10 ? '0' + month : month) + '-' + (day < 10 ? '0' + day : day);

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialDate: currentDate,
        editable: false,
        selectable: false,
        businessHours: true,
        dayMaxEvents: true,
        events: [], // 초기에는 빈 배열로 설정
    });

    calendar.render();

    // 최초 로드 시 현재 연도와 10년 후까지의 모든 이벤트를 로드
    loadEventsForYears(year, year + 10, calendar);

    const btns = document.querySelectorAll('.fc-button');
    btns.forEach((btn) => {
        btn.addEventListener("click", () => {
            console.log("버튼이 클릭되었습니다.");
        });
    });
});

function loadEventsForYears(startYear, endYear, calendar) {
    $.ajax({
        url: 'CalendarEventC',
        type: 'GET',
        data: { year: startYear }, // 첫 요청은 현재 연도 기준
        dataType: 'json',
        success: function(res) {
            console.log("AJAX 응답:", res);
            for (let year = startYear; year <= endYear; year++) {
                res.forEach(event => {
                    let newEvent = { ...event };
                    newEvent.start = incrementYear(event.start, year - startYear);
                    calendar.addEvent(newEvent);
                });
            }
        },
        error: function(err) {
            console.error("Error fetching events: ", err);
        }
    });
}

function incrementYear(dateStr, increment) {
    let date = new Date(dateStr);
    date.setFullYear(date.getFullYear() + increment);
    return date.toISOString().split('T')[0]; // 'YYYY-MM-DD' 형식으로 반환
}
