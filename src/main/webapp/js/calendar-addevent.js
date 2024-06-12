document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');

	var today = new Date();
	var year = today.getFullYear();
	var month = today.getMonth() + 1;
	var day = today.getDate();

	var currentDate = year + '-' + (month < 10 ? '0' + month : month) + '-' + (day < 10 ? '0' + day : day);

	var calendar = new FullCalendar.Calendar(calendarEl, {
		initialDate: currentDate,
		editable: true,
		selectable: true,
		businessHours: true,
		dayMaxEvents: true,
		events: [] // 초기 이벤트를 비워 둡니다.
	});

	calendar.render();
//	setVal();
	
	const btns =document.querySelectorAll('.fc-button');
	btns.forEach((btn)=>{
		btn.addEventListener("click", ()=>{
			setVal();
		});
	});
});

let year;
function setVal(){
	const date = document.querySelector("#fc-dom-1").innerText;
	const monthDay = date.split(" ");
	year = monthDay[1];
	month = monthToNumber(monthDay[0], year);
	
	$.ajax({
		url: 'CalendarEventC',
		type: 'GET',
		data : {month, year},
		success: function(res) {
			console.log(res);

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
