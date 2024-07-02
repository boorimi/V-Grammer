document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar'); // calendar 엘리먼트 가져오기

    // 현재 날짜 정보 가져오기
    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth() + 1;
    var day = today.getDate();
    var currentDate = year + '-' + (month < 10 ? '0' + month : month) + '-' + (day < 10 ? '0' + day : day);
    console.log("현재 날짜:", currentDate);

    // FullCalendar 초기화
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialDate: currentDate, // 초기 날짜 설정
        editable: false, // 편집 불가 설정
        selectable: false, // 선택 불가 설정
        businessHours: true, // 영업 시간 표시 설정
        dayMaxEvents: true, // 하루 최대 이벤트 수 설정
        events: [], // 초기 이벤트 설정
        dayHeaderDidMount: function(info) {
            // 각 날짜 헤더가 마운트될 때 호출되는 콜백 함수
            var day = info.date.getDay();
            if (day === 0) { // 일요일인 경우
                info.el.classList.add('sunday-header'); // 일요일 클래스 추가
                var headerLink = info.el.querySelector('.fc-col-header-cell-cushion');
                if (headerLink) {
                    headerLink.style.color = 'red'; // 일요일 헤더 텍스트 색상을 빨간색으로 변경
                }
            } else if (day >= 1 && day <= 5) { // 월요일부터 금요일인 경우
                info.el.classList.add('weekday-header'); // 평일 헤더 클래스 추가
                var headerLink = info.el.querySelector('.fc-col-header-cell-cushion');
                if (headerLink) {
                    headerLink.style.color = 'black'; // 평일 헤더 텍스트 색상을 검정색으로 변경
                }
            }

            // 날짜 숫자 색상 변경
            var headerNumber = info.el.querySelector('.fc-daygrid-day-number');
            if (headerNumber) {
                if (day === 0) { // 일요일인 경우
                    headerNumber.style.color = 'red'; // 일요일 날짜 숫자 색상을 빨간색으로 변경
                } else { // 월요일부터 금요일인 경우
                    headerNumber.style.color = 'black'; // 평일 날짜 숫자 색상을 검정색으로 변경
                }
            }
        },
        eventDidMount: function(info) {
            // 각 이벤트가 마운트될 때 호출되는 콜백 함수
            addPopoverToEvent(info.el, info.event); // 이벤트에 팝오버 추가 함수 호출
        },
        eventMoreLinkDidMount: function(info) {
            // 이벤트가 링크로 더 보기 옵션을 통해 마운트될 때 호출되는 콜백 함수
            var popover = info.el.querySelector('.fc-popover');
            if (popover) {
                popover.style.left = 'unset';
                popover.style.right = '0'; // 팝오버 위치 조정
            }
        }
    });

    calendar.render(); // 캘린더 렌더링
    console.log("캘린더가 렌더링되었습니다.");

    var loadedEvents = []; // 로드된 이벤트 배열 초기화

    // 시작 연도부터 4년 후까지의 이벤트 로드
    loadEventsForYears(year, year + 4, calendar);

    // FullCalendar 버튼 이벤트 리스너 설정
    const buttons = document.querySelectorAll('.fc-button');
    buttons.forEach((button) => {
        button.addEventListener("click", () => {
            console.log("버튼이 클릭되었습니다.");
            updateEvents(year, year + 4, calendar); // 이벤트 업데이트 함수 호출
        });
    });

    // 시작 연도부터 끝 연도까지의 이벤트 로드 함수
    function loadEventsForYears(startYear, endYear, calendar) {
        console.log(`이벤트 로드 시작: ${startYear}년부터 ${endYear}년까지`);
        alert("カレンダーを読み込み中です。 OKボタンを押したら 読み込めます。時間がかかる場合があります。");

        // AJAX를 통해 서버에서 이벤트 데이터 가져오기
        $.ajax({
            url: 'CalendarEventC', // 요청할 URL
            type: 'GET', // HTTP 메서드 설정
            data: { year: startYear }, // 요청 파라미터 설정
            dataType: 'json', // 데이터 타입 설정
            success: function(res) { // 성공 시 콜백 함수
                console.log("AJAX 응답:", res);
                loadedEvents = res; // 로드된 이벤트 업데이트
                addEventsForYears(res, startYear, endYear, calendar); // 연도별 이벤트 추가 함수 호출
                alert("読み込み完了！"); // 완료 메시지 알림
            },
            error: function(err) { // 에러 발생 시 콜백 함수
                console.error("Error fetching events: ", err);
            }
        });
    }

    // 특정 연도 범위의 이벤트 추가 함수
    function addEventsForYears(events, startYear, endYear, calendar) {
        console.log(`이벤트 추가 시작: ${startYear}년부터 ${endYear}년까지`);
        for (let year = startYear; year <= endYear; year++) {
            console.log(`연도: ${year}`);
            events.forEach(event => {
                let newEvent = { ...event };
                newEvent.start = incrementYear(event.start, year - new Date(event.start).getFullYear());
                if (newEvent.title.includes('の誕生日')) { // 생일 이벤트인 경우
                    newEvent.color = 'red'; // 빨간색으로 설정
                }
                if (newEvent.title.includes('Holiday')) { // 공휴일 이벤트인 경우
                    newEvent.color = 'red'; // 빨간색으로 설정
                }
                console.log("새 이벤트 추가:", newEvent);
                calendar.addEvent(newEvent); // 캘린더에 이벤트 추가
            });
        }
    }

    // 특정 연도 범위의 이벤트 업데이트 함수
    function updateEvents(startYear, endYear, calendar) {
        console.log(`이벤트 업데이트 시작: ${startYear}년부터 ${endYear}년까지`);
        $.ajax({
            url: 'CalendarEventC', // 요청할 URL
            type: 'GET', // HTTP 메서드 설정
            data: { year: startYear }, // 요청 파라미터 설정
            dataType: 'json', // 데이터 타입 설정
            success: function(res) { // 성공 시 콜백 함수
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
                        if (newEvent.title.includes('の誕生日')) { // 생일 이벤트인 경우
                            newEvent.color = 'red'; // 빨간색으로 설정
                        }
                        if (newEvent.title.includes('祝日')) { // 공휴일 이벤트인 경우
                            newEvent.color = 'red'; // 빨간색으로 설정
                        }
                        console.log("새 이벤트 추가:", newEvent);
                        calendar.addEvent(newEvent); // 캘린더에 이벤트 추가
                    });

                    loadedEvents = res; // 로드된 이벤트 업데이트
                    console.log("이벤트가 업데이트되었습니다.");
                } else {
                    console.log("변동 사항이 없습니다.");
                }
            },
            error: function(err) { // 에러 발생 시 콜백 함수
                console.error("Error fetching events: ", err);
            }
        });
    }

    // 특정 연도를 기준으로 날짜를 증가시키는 함수
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
                document.body.appendChild(popover); // 팝오버를 문서에 추가
            }
        }

        function destroyPopover() {
            if (popover) {
                popover.remove(); // 팝오버 제거
                popover = null;
            }
        }

        function showPopover() {
            createPopover(); // 팝오버 생성 함수 호출
            positionPopover(popover, eventEl); // 팝오버 위치 설정
            popover.classList.add('show'); // 팝오버 표시
            clearTimeout(popoverTimer);
        }

        function hidePopover() {
            popoverTimer = setTimeout(function() {
                destroyPopover(); // 팝오버 제거 함수 호출 (지연)
            }, 400);
        }

        // 마우스 진입 시 팝오버 표시
        eventEl.addEventListener('mouseenter', function() {
            showPopover();
        });

        // 마우스 이탈 시 팝오버 제거
        eventEl.addEventListener('mouseleave', function() {
            hidePopover();
        });

        // 스크롤 시 팝오버 위치 조정
        document.addEventListener('scroll', function() {
            if (popover) {
                positionPopover(popover, eventEl);
            }
        });

        // 문서 클릭 시 팝오버 제거
        document.addEventListener('click', function(e) {
            if (popover && !popover.contains(e.target) && e.target !== eventEl && !eventEl.contains(e.target)) {
                destroyPopover();
            }
        });

        // 팝오버 위치 설정 함수
        function positionPopover(popover, eventEl) {
            let rect = eventEl.getBoundingClientRect();
            popover.style.top = `${rect.top + window.scrollY - popover.offsetHeight}px`;
            popover.style.left = `${rect.left + rect.width / 2 - popover.offsetWidth / 2}px`;
        }
    }
});
