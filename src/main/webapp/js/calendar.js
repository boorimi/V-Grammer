$(function() {
    loadCalendar();
    setTimeout(callBirth, 1000);
});

function callBirth() {
    let title = $('.fc-toolbar-title');
    let text = $(title).text();
    let month = text.charAt(5);

    $.ajax({
        url: 'CalendarEventC',
        data: { month },
    })
    .done(function(resData) {
        setBirth(resData);
    });
}

function setBirth(resData) {
    const days = document.querySelectorAll(".fc-daygrid-day-number");
    const div = document.createElement('div');
    div.style.position = 'absolute';
    days.forEach((day) => {
        const date = JSON.parse(day.getAttribute("data-navlink"));
        let top = 0;
        let idx = 0;
        resData.forEach((obj) => {
            if (date.date == obj.debut) {
                console.log(idx += 16)
                const debutDiv = day.parentNode.parentNode.appendChild(div.cloneNode(true));
                debutDiv.innerText = obj.name;
                debutDiv.style.top = parseInt(idx) + 17 + 'px';
            }
        });
    });
};

function loadCalendar() {
    var calendarEl = $('#calendar')[0];
    var calendar = new FullCalendar.Calendar(calendarEl, {
        height: '700px',
        expandRows: true,
        slotMinTime: '08:00',
        slotMaxTime: '20:00',
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth'
        },
        initialView: 'dayGridMonth',
        navLinks: true,
        editable: false,
        selectable: false, // 날짜 선택 비활성화
        nowIndicator: true,
        dayMaxEvents: true,
        locale: 'ja',
    });
    calendar.render();
}