// js/calendar-addevent.js
document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');

	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var day = today.getDate();

	var currentDate = year + '-' + (month < 10 ? '0' + month : month) + '-' + (day < 10 ? '0' + day : day);

	var calendar = new FullCalendar.Calendar(calendarEl, {
		/*locale: "jp",*/
		initialDate: currentDate,
		editable: true,
		selectable: true,
		businessHours: true,
		dayMaxEvents: true,
		events: []
	});

	calendar.render();

	const btns = document.querySelectorAll('.fc-button');
	btns.forEach((btn) => {
		btn.addEventListener("click", () => {
			setVal(calendar);
		});
	});

	// 페이지 로드 시 초기 이벤트 로드
	setVal(calendar);


});

function setVal(calendar) {
	const date = document.querySelector(".fc-toolbar-title").innerText;
	const [monthName, year] = date.split(" ");
	const month = monthToNumber(monthName, year);

	$.ajax({
		url: 'CalendarEventC',
		type: 'GET',
		data: { month, year },
		locale: "en",
		dataType: 'json', // 응답을 JSON으로 기대
		success: function(res) {
			console.log("AJAX 응답:", res); // 응답 데이터 확인
			const events = res; // 응답을 이미 JSON 객체로 처리
			console.log("파싱된 이벤트:", events); // 파싱된 데이터 확인
			calendar.removeAllEvents(); // 기존 이벤트 제거
			events.forEach(event => {
				calendar.addEvent(event);
			});
		},
		error: function(err) {
			console.error("Error fetching events: ", err);
		}
	});
}

function monthToNumber(monthString, year) {
	const date = new Date(monthString + ' 1,' + year);
	let month = date.getMonth() + 1;
	let formattedMonth = month < 10 ? '0' + month : month;
	return formattedMonth;
}
