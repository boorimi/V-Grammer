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

    var loadedEvents = []; // 서버에서 로드한 이벤트를 저장할 배열

    // 최초 로드 시 현재 연도와 10년 후까지의 모든 이벤트를 로드
    loadEventsForYears(year, year + 10, calendar);

    const btns = document.querySelectorAll('.fc-button');
    btns.forEach((btn) => {
        btn.addEventListener("click", () => {
            console.log("버튼이 클릭되었습니다.");
            // 버튼 클릭 시 이벤트 추가 로직 예시
            // 실제 추가 로직은 여기에 구현
            // 추가 로직이 완료되면 다음 함수를 호출하여 이벤트를 업데이트
            updateEvents(year, year + 10, calendar);
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
                loadedEvents = res; // 서버에서 로드한 이벤트 저장
                addEventsForYears(res, startYear, endYear, calendar);
            },
            error: function(err) {
                console.error("Error fetching events: ", err);
            }
        });
    }

    function addEventsForYears(events, startYear, endYear, calendar) {
        for (let year = startYear; year <= endYear; year++) {
            events.forEach(event => {
                let newEvent = { ...event };
                newEvent.start = incrementYear(event.start, year - new Date(event.start).getFullYear());
                calendar.addEvent(newEvent);
            });
        }
    }

    function updateEvents(startYear, endYear, calendar) {
        $.ajax({
            url: 'CalendarEventC',
            type: 'GET',
            data: { year: startYear }, // 첫 요청은 현재 연도 기준
            dataType: 'json',
            success: function(res) {
                console.log("AJAX 응답:", res);
                // 이전에 추가된 이벤트와 비교하여 변동 사항 확인
                let eventsToRemove = loadedEvents.filter(event => !res.some(newEvent => newEvent.id === event.id));
                let eventsToAdd = res.filter(newEvent => !loadedEvents.some(event => event.id === newEvent.id));

                // 변동 사항이 있는 경우만 처리
                if (eventsToRemove.length > 0 || eventsToAdd.length > 0) {
                    eventsToRemove.forEach(event => {
                        calendar.getEventById(event.id).remove(); // 이벤트 제거
                    });

                    eventsToAdd.forEach(event => {
                        let newEvent = { ...event };
                        newEvent.start = incrementYear(event.start, year - new Date(event.start).getFullYear());
                        calendar.addEvent(newEvent); // 이벤트 추가
                    });

                    loadedEvents = res; // 변경된 이벤트로 업데이트
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
});
