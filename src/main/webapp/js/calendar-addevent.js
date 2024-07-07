document.addEventListener('DOMContentLoaded', () => {
    console.log("DOMContentLoaded 이벤트가 발생했습니다.");

    const calendarEl = document.getElementById('calendar');
    const today = new Date();
    const year = today.getFullYear();
    const month = (today.getMonth() + 1).toString().padStart(2, '0');
    const day = today.getDate().toString().padStart(2, '0');
    const currentDate = `${year}-${month}-${day}`;
    console.log("현재 날짜:", currentDate);

    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialDate: currentDate,
        editable: false,
        selectable: false,
        businessHours: true,
        dayMaxEvents: true,
        events: [],
        dayHeaderDidMount: handleDayHeaderDidMount,
        eventDidMount: handleEventDidMount,
        eventMoreLinkDidMount: handleEventMoreLinkDidMount,
        googleCalendarApiKey: "AIzaSyDhsiwuDVCQ5lEt5qt_ofW35UMlnOeMpAg",
        eventSources: [
            {
                googleCalendarId: 'ja.japanese#holiday@group.v.calendar.google.com',
                color: 'red',
                textColor: 'white'
            }
        ]
    });

    calendar.render();
    console.log("캘린더가 렌더링되었습니다.");

    let loadedEvents = [];

    loadEventsForYears(year, year + 4, calendar);

    document.querySelectorAll('.fc-button').forEach(button => {
        button.addEventListener("click", () => {
            console.log("버튼이 클릭되었습니다.");
            updateEvents(year, year + 4, calendar);
        });
    });

    function loadEventsForYears(startYear, endYear, calendar) {
        console.log(`이벤트 로드 시작: ${startYear}년부터 ${endYear}년까지`);
        alert("カレンダーを読み込み中です。 OKボタンを押したら 読み込めます。時間がかかる場合があります。");

        $.ajax({
            url: 'CalendarEventC',
            type: 'GET',
            data: { year: startYear },
            dataType: 'json',
            success: res => {
                console.log("AJAX 응답:", res);
                loadedEvents = res;
                addEventsForYears(res, startYear, endYear, calendar);
                alert("読み込み完了！");
            },
            error: err => {
                console.error("Error fetching events:", err);
            }
        });
    }

    function addEventsForYears(events, startYear, endYear, calendar) {
        console.log(`이벤트 추가 시작: ${startYear}년부터 ${endYear}년까지`);
        for (let year = startYear; year <= endYear; year++) {
            console.log(`연도: ${year}`);
            events.forEach(event => {
                const newEvent = {
                    ...event,
                    start: incrementYear(event.start, year - new Date(event.start).getFullYear()),
                    color: event.title.includes('の誕生日') || event.title.includes('祝日') ? 'red' : event.color
                };
                console.log("새 이벤트 추가:", newEvent);
                calendar.addEvent(newEvent);
            });
        }
    }

    function updateEvents(startYear, endYear, calendar) {
        console.log(`이벤트 업데이트 시작: ${startYear}년부터 ${endYear}년까지`);
        $.ajax({
            url: 'CalendarEventC',
            type: 'GET',
            data: { year: startYear },
            dataType: 'json',
            success: res => {
                console.log("AJAX 응답:", res);
                const eventsToRemove = loadedEvents.filter(event => !res.some(newEvent => newEvent.id === event.id));
                const eventsToAdd = res.filter(newEvent => !loadedEvents.some(event => event.id === newEvent.id));

                console.log("제거할 이벤트:", eventsToRemove);
                console.log("추가할 이벤트:", eventsToAdd);

                eventsToRemove.forEach(event => {
                    console.log("이벤트 제거:", event);
                    const existingEvent = calendar.getEventById(event.id);
                    if (existingEvent) {
                        existingEvent.remove();
                    }
                });

                eventsToAdd.forEach(event => {
                    const newEvent = {
                        ...event,
                        start: incrementYear(event.start, year - new Date(event.start).getFullYear()),
                        color: event.title.includes('の誕生日') || event.title.includes('祝日') ? 'red' : event.color
                    };
                    console.log("새 이벤트 추가:", newEvent);
                    calendar.addEvent(newEvent);
                });

                loadedEvents = res;
                console.log("이벤트가 업데이트되었습니다.");
            },
            error: err => {
                console.error("Error fetching events:", err);
            }
        });
    }

    function incrementYear(dateStr, increment) {
        const date = new Date(dateStr);
        date.setFullYear(date.getFullYear() + increment);
        const newDateStr = date.toISOString().split('T')[0];
        console.log(`날짜 변환: ${dateStr} -> ${newDateStr}`);
        return newDateStr;
    }

    function handleDayHeaderDidMount(info) {
        const { date, el } = info;
        const day = date.getDay();
        const headerLink = el.querySelector('.fc-col-header-cell-cushion');
        const headerNumber = el.querySelector('.fc-daygrid-day-number');

        if (day === 0) {
            el.classList.add('sunday-header');
            if (headerLink) headerLink.style.color = 'red';
            if (headerNumber) headerNumber.style.color = 'red';
        } else if (day >= 1 && day <= 5) {
            el.classList.add('weekday-header');
            if (headerLink) headerLink.style.color = 'black';
            if (headerNumber) headerNumber.style.color = 'black';
        }
    }

    function handleEventDidMount(info) {
        addPopoverToEvent(info.el, info.event);
    }

    function handleEventMoreLinkDidMount(info) {
        const popover = info.el.querySelector('.fc-popover');
        if (popover) {
            popover.style.left = 'unset';
            popover.style.right = '0';
        }
    }

    function addPopoverToEvent(eventEl, event) {
        let popover = null;
        let popoverTimer = null;

        const createPopover = () => {
            if (!popover) {
                popover = document.createElement('div');
                popover.className = 'popover fade bs-popover-top';
                popover.role = 'tooltip';
                popover.innerHTML = `
                    <style>
                        .popover {
                            z-index: 9999;
                        }
                    </style>
                    <div class="arrow"></div>
                    <h3 class="popover-header">${event.title}</h3>
                    <div class="popover-body">
                        ${event.extendedProps.imagePath ? `<img src="${event.extendedProps.imagePath}" alt="event image">` : ''}
                    </div>
                `;
                document.body.appendChild(popover);
            }
        };

        const destroyPopover = () => {
            if (popover) {
                popover.remove();
                popover = null;
            }
        };

        const showPopover = () => {
            createPopover();
            positionPopover(popover, eventEl);
            popover.classList.add('show');
            clearTimeout(popoverTimer);
        };

        const hidePopover = () => {
            popoverTimer = setTimeout(() => {
                destroyPopover();
            }, 400);
        };

        eventEl.addEventListener('mouseenter', showPopover);
        eventEl.addEventListener('mouseleave', hidePopover);

        document.addEventListener('scroll', () => {
            if (popover) {
                positionPopover(popover, eventEl);
            }
        });

        document.addEventListener('click', e => {
            if (popover && !popover.contains(e.target) && e.target !== eventEl && !eventEl.contains(e.target)) {
                destroyPopover();
            }
        });

        const positionPopover = (popover, eventEl) => {
            const rect = eventEl.getBoundingClientRect();
            popover.style.top = `${rect.top + window.scrollY - popover.offsetHeight}px`;
            popover.style.left = `${rect.left + rect.width / 2 - popover.offsetWidth / 2}px`;
        };
    }
});
