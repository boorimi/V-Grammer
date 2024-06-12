document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    
    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth() + 1;
    var day = today.getDate();
    
    var currentDate = year + '-' + (month < 10 ? '0' + month : month) + '-' + (day < 10 ? '0' + day : day);

    var events = [
        {
            title: 'event1',
            start: '2024-06-11'
        },
        {
            title: 'event2',
            start: '2024-06-15',
            end: '2024-06-17'
        },
        {
            title: 'event3',
            start: '2010-01-09T12:30:00',
            allDay: false
        }
    ];

    // 이벤트 날짜에 빨간색 테두리를 추가할지 여부를 확인하는 함수
    function shouldHighlightEvent(event) {
        var highlightDates = ['2024-06-11', '2024-06-15']; // 강조할 날짜를 여기 추가하세요
        return highlightDates.includes(event.startStr);
    }

    // 빨간색 테두리를 추가하기 위한 eventContent 콜백 정의
    function eventContent(arg) {
        var eventEl = document.createElement('div');
        eventEl.innerHTML = arg.event.title;
        
        if (shouldHighlightEvent(arg.event)) {
            eventEl.style.border = '2px solid red';
            eventEl.style.padding = '2px';
        }
        
        return { domNodes: [eventEl] };
    }

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialDate: currentDate,
        editable: true,
        selectable: true,
        businessHours: true,
        dayMaxEvents: true,
        events: events,
        eventContent: eventContent
    });

    calendar.render();

    $.ajax({
        url: 'CalendarEventC',
        type: 'GET',
        success: function(res){
            var list = res;
            console.log(list);
            
            var events = list.map(function(item) {
                return {
                    start : item.reservationDate + "T" + item.reservationTime
                };
            });

        }
    });
});
