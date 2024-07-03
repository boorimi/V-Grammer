document.addEventListener('DOMContentLoaded', function() {
    // 페이지가 로드되었을 때 실행되는 함수
    var calendarEl = document.getElementById('calendar');

    // 현재 날짜를 가져와서 문자열로 변환
    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth() + 1;
    var day = today.getDate();
    var currentDate = year + '-' + (month < 10 ? '0' + month : month) + '-' + (day < 10 ? '0' + day : day);
    console.log("현재 날짜:", currentDate);

    // FullCalendar 초기화
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialDate: currentDate,  // 캘린더가 처음 로드될 날짜
        editable: false,  // 이벤트 수정 불가
        selectable: false,  // 날짜 선택 불가
        businessHours: true,  // 영업 시간 표시
        dayMaxEvents: true,  // 하루에 최대 이벤트 수 제한
        events: [],  // 초기 이벤트 데이터
        dayHeaderDidMount: function(info) {
            // 요일 헤더가 로드될 때 스타일 변경
            var day = info.date.getDay();
            if (day === 0) {
                info.el.classList.add('sunday-header');
                var headerLink = info.el.querySelector('.fc-col-header-cell-cushion');
                if (headerLink) {
                    headerLink.style.color = 'red';
                }
            } else if (day >= 1 && day <= 5) {
                info.el.classList.add('weekday-header');
                var headerLink = info.el.querySelector('.fc-col-header-cell-cushion');
                if (headerLink) {
                    headerLink.style.color = 'black';
                }
            }

            var headerNumber = info.el.querySelector('.fc-daygrid-day-number');
            if (headerNumber) {
                if (day === 0) {
                    headerNumber.style.color = 'red';
                } else {
                    headerNumber.style.color = 'black';
                }
            }
        },
        eventDidMount: function(info) {
            // 이벤트가 로드될 때 팝오버 추가
            addPopoverToEvent(info.el, info.event);
        },
        eventMoreLinkDidMount: function(info) {
            // 이벤트가 많을 때 "more" 링크에 팝오버 스타일 추가
            var popover = info.el.querySelector('.fc-popover');
            if (popover) {
                popover.style.left = 'unset';
                popover.style.right = '0';
            }
        },
        // 구글 캘린더 연동 설정
        googleCalendarApiKey: "AIzaSyDhsiwuDVCQ5lEt5qt_ofW35UMlnOeMpAg",
        eventSources: [
            {
                googleCalendarId: 'ja.japanese#holiday@group.v.calendar.google.com', // 일본 공휴일 캘린더 ID
                color: 'red',   // 캘린더 이벤트 색상 설정
                textColor: 'white'  // 캘린더 이벤트 텍스트 색상 설정
            }
        ]
    });

    calendar.render(); // 캘린더 렌더링
    console.log("캘린더가 렌더링되었습니다.");

    var loadedEvents = [];

    // 이벤트 로드 함수 호출 (현재 연도부터 4년 후까지)
    loadEventsForYears(year, year + 4, calendar);

    // 캘린더 버튼 클릭 시 이벤트 업데이트
    const buttons = document.querySelectorAll('.fc-button');
    buttons.forEach((button) => {
        button.addEventListener("click", () => {
            console.log("버튼이 클릭되었습니다.");
            updateEvents(year, year + 4, calendar);
        });
    });

    // 특정 연도 범위의 이벤트를 로드하는 함수
    function loadEventsForYears(startYear, endYear, calendar) {
        console.log(`이벤트 로드 시작: ${startYear}년부터 ${endYear}년까지`);
        alert("カレンダーを読み込み中です。 OKボタンを押したら 読み込めます。時間がかかる場合があります。");

        // AJAX 요청을 통해 서버에서 이벤트 데이터를 가져옴
        $.ajax({
            url: 'CalendarEventC', // 서버 엔드포인트
            type: 'GET',
            data: { year: startYear },
            dataType: 'json',
            success: function(res) {
                console.log("AJAX 응답:", res);
                loadedEvents = res;
                addEventsForYears(res, startYear, endYear, calendar); // 가져온 이벤트 추가
                alert("読み込み完了！");
            },
            error: function(err) {
                console.error("Error fetching events: ", err);
            }
        });
    }

    // 특정 연도 범위의 이벤트를 추가하는 함수
    function addEventsForYears(events, startYear, endYear, calendar) {
        console.log(`이벤트 추가 시작: ${startYear}년부터 ${endYear}년까지`);
        for (let year = startYear; year <= endYear; year++) {
            console.log(`연도: ${year}`);
            events.forEach(event => {
                let newEvent = { ...event };
                newEvent.start = incrementYear(event.start, year - new Date(event.start).getFullYear());
                if (newEvent.title.includes('の誕生日')) {
                    newEvent.color = 'red'; // 생일 이벤트의 색상 설정
                }
                if (newEvent.title.includes('祝日')) {
                    newEvent.color = 'red'; // 공휴일 이벤트의 색상 설정
                }
                console.log("새 이벤트 추가:", newEvent);
                calendar.addEvent(newEvent); // 새로운 이벤트 추가
            });
        }
    }

    // 이벤트를 업데이트하는 함수
    function updateEvents(startYear, endYear, calendar) {
        console.log(`이벤트 업데이트 시작: ${startYear}년부터 ${endYear}년까지`);
        $.ajax({
            url: 'CalendarEventC', // 서버 엔드포인트
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
                        calendar.getEventById(event.id).remove(); // 이벤트 제거
                    });

                    eventsToAdd.forEach(event => {
                        let newEvent = { ...event };
                        newEvent.start = incrementYear(event.start, year - new Date(event.start).getFullYear());
                        if (newEvent.title.includes('の誕生日')) {
                            newEvent.color = 'red'; // 생일 이벤트의 색상 설정
                        }
                        if (newEvent.title.includes('祝日')) {
                            newEvent.color = 'red'; // 공휴일 이벤트의 색상 설정
                        }
                        console.log("새 이벤트 추가:", newEvent);
                        calendar.addEvent(newEvent); // 새로운 이벤트 추가
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

    // 연도를 증가시키는 함수
    function incrementYear(dateStr, increment) {
        let date = new Date(dateStr);
        date.setFullYear(date.getFullYear() + increment);
        let newDateStr = date.toISOString().split('T')[0];
        console.log(`날짜 변환: ${dateStr} -> ${newDateStr}`);
        return newDateStr;
    }

    // 이벤트에 팝오버를 추가하는 함수
    function addPopoverToEvent(eventEl, event) {
        let popover = null;
        let popoverTimer = null;

        // 팝오버 생성
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

        // 팝오버 제거
        function destroyPopover() {
            if (popover) {
                popover.remove();
                popover = null;
            }
        }

        // 팝오버 표시
        function showPopover() {
            createPopover();
            positionPopover(popover, eventEl);
            popover.classList.add('show');
            clearTimeout(popoverTimer);
        }

        // 팝오버 숨기기
        function hidePopover() {
            popoverTimer = setTimeout(function() {
                destroyPopover();
            }, 400);
        }

        // 이벤트 엘리먼트에 마우스 진입 시 팝오버 표시
        eventEl.addEventListener('mouseenter', function() {
            showPopover();
        });

        // 이벤트 엘리먼트에 마우스 나갈 시 팝오버 숨기기
        eventEl.addEventListener('mouseleave', function() {
            hidePopover();
        });

        // 스크롤 시 팝오버 위치 조정
        document.addEventListener('scroll', function() {
            if (popover) {
                positionPopover(popover, eventEl);
            }
        });

        // 클릭 시 팝오버 제거
        document.addEventListener('click', function(e) {
            if (popover && !popover.contains(e.target) && e.target !== eventEl && !eventEl.contains(e.target)) {
                destroyPopover();
            }
        });

        // 팝오버 위치 조정 함수
        function positionPopover(popover, eventEl) {
            let rect = eventEl.getBoundingClientRect();
            popover.style.top = `${rect.top + window.scrollY - popover.offsetHeight}px`;
            popover.style.left = `${rect.left + rect.width / 2 - popover.offsetWidth / 2}px`;
        }
    }
});
