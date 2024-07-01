document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth() + 1;
    var day = today.getDate();
    var currentDate = year + '-' + (month < 10 ? '0' + month : month) + '-' + (day < 10 ? '0' + day : day);
    console.log("현재 날짜:", currentDate);

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialDate: currentDate,
        editable: false,
        selectable: false,
        businessHours: true,
        dayMaxEvents: true,
        events: [],
        dayHeaderDidMount: function(info) {
            var day = info.date.getDay();
            if (day === 0) { // 일요일
                info.el.classList.add('sunday-header');
                var headerLink = info.el.querySelector('.fc-col-header-cell-cushion');
                if (headerLink) {
                    headerLink.style.color = 'red'; // 일요일 글자색 빨간색으로 변경
                }
            } else if (day >= 1 && day <= 5) { // 월요일부터 금요일
                info.el.classList.add('weekday-header');
                var headerLink = info.el.querySelector('.fc-col-header-cell-cushion');
                if (headerLink) {
                    headerLink.style.color = 'black'; // 평일 글자색 검정색으로 변경
                }
            }

            // 날짜 숫자 색상 변경
            var headerNumber = info.el.querySelector('.fc-daygrid-day-number');
            if (headerNumber) {
                if (day === 0) { // 일요일
                    headerNumber.style.color = 'red'; // 일요일 날짜 숫자 빨간색으로 변경
                } else { // 월요일부터 금요일
                    headerNumber.style.color = 'black'; // 평일 날짜 숫자 검정색으로 변경
                }
            }
        },
        eventDidMount: function(info) {
            addPopoverToEvent(info.el, info.event);
        },
        eventMoreLinkDidMount: function(info) {
            var popover = info.el.querySelector('.fc-popover');
            if (popover) {
                popover.style.left = 'unset';
                popover.style.right = '0';
            }
        }
    });

    calendar.render();
    console.log("캘린더가 렌더링되었습니다.");

    var loadedEvents = [];

    loadEventsForYears(year, year + 4, calendar);

    const buttons = document.querySelectorAll('.fc-button');
    buttons.forEach((button) => {
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
            success: function(res) {
                console.log("AJAX 응답:", res);
                loadedEvents = res;
                addEventsForYears(res, startYear, endYear, calendar);
                alert("読み込み完了！");
            },
            error: function(err) {
                console.error("Error fetching events: ", err);
            }
        });
    }

    function addEventsForYears(events, startYear, endYear, calendar) {
        console.log(`이벤트 추가 시작: ${startYear}년부터 ${endYear}년까지`);
        for (let year = startYear; year <= endYear; year++) {
            console.log(`연도: ${year}`);
            events.forEach(event => {
                let newEvent = { ...event };
                newEvent.start = incrementYear(event.start, year - new Date(event.start).getFullYear());
                if (newEvent.title.includes('の誕生日')) {
                    newEvent.color = 'red';
                }
                if (newEvent.title.includes('Holiday')) { // 공휴일 이벤트 식별
                    newEvent.color = 'red';
                }
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
            success: function(res) {
                console.log("AJAX 응답:", res);
                let eventsToRemove = loadedEvents.filter(event => !res.some(newEvent => newEvent.id === event.id));
                let eventsToAdd = res.filter(newEvent => !loadedEvents.some(event => event.id === newEvent.id));

                console.log("제거할 이벤트:", eventsToRemove);
                console.log("추가할 이벤트:", eventsToAdd);

                if (eventsToRemove.length > 0 || eventsToAdd.length > 0) {
                    eventsToRemove.forEach(event => {
                        console.log("이벤트 제거:", event);
                        calendar.getEventById(event.id).remove();
                    });

                    eventsToAdd.forEach(event => {
                        let newEvent = { ...event };
                        newEvent.start = incrementYear(event.start, year - new Date(event.start).getFullYear());
                        if (newEvent.title.includes('の誕生日')) {
                            newEvent.color = 'red';
                        }
                        if (newEvent.title.includes('祝日')) { // 공휴일 이벤트 식별
                            newEvent.color = 'red';
                        }
                        console.log("새 이벤트 추가:", newEvent);
                        calendar.addEvent(newEvent);
                    });

                    loadedEvents = res;
                    console.log("이벤트가 업데이트되었습니다.");
                } else {
                    console.log("변동 사항이 없습니다.");
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
        let newDateStr = date.toISOString().split('T')[0];
        console.log(`날짜 변환: ${dateStr} -> ${newDateStr}`);
        return newDateStr;
    }

    function addPopoverToEvent(eventEl, event) {
        let popover = null;
        let popoverTimer = null;

        function createPopover() {
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
        }

        function destroyPopover() {
            if (popover) {
                popover.remove();
                popover = null;
            }
        }

        function showPopover() {
            createPopover();
            positionPopover(popover, eventEl);
            popover.classList.add('show');
            clearTimeout(popoverTimer);
        }

        function hidePopover() {
            popoverTimer = setTimeout(function() {
                destroyPopover();
            }, 400);
        }

        eventEl.addEventListener('mouseenter', function() {
            showPopover();
        });

        eventEl.addEventListener('mouseleave', function() {
            hidePopover();
        });

        document.addEventListener('scroll', function() {
            if (popover) {
                positionPopover(popover, eventEl);
            }
        });

        document.addEventListener('click', function(e) {
            if (popover && !popover.contains(e.target) && e.target !== eventEl && !eventEl.contains(e.target)) {
                destroyPopover();
            }
        });

        function positionPopover(popover, eventEl) {
            let rect = eventEl.getBoundingClientRect();
            popover.style.top = `${rect.top + window.scrollY - popover.offsetHeight}px`;
            popover.style.left = `${rect.left + rect.width / 2 - popover.offsetWidth / 2}px`;
        }
    }
});
