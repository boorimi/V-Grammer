<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src='js/calendar.js'></script>
<link rel="stylesheet" href="css/calendar.css">
<script>
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
            events: [
                {
                    title  : 'event1',
                    start  : '2024-06-11'
                },
                {
                    title  : 'event2',
                    start  : '2010-01-05',
                    end    : '2010-01-07'
                },
                {
                    title  : 'event3',
                    start  : '2010-01-09T12:30:00',
                    allDay : false
                }
            ]
        });

        calendar.render();
    });
     
    $.ajax({
        url: 'CalendarEventC',
        type: 'GET',
        success: function(res){
            var list = res;
            console.log(list);
            
            var calendarEl = document.getElementById('calendar');
            
            var events = list.map(function(item) {
                return {
                    title : item.reservationTitle,
                    start : item.reservationDate + "T" + item.reservationTime
                }
            });
            
            var calendar = new FullCalendar.Calendar(calendarEl, {
                events : events,
                eventTimeFormat: {
                    hour: '2-digit',
                    minute: '2-digit',
                    hour12: false
                }
            });
            calendar.render();
        },
    });
</script>
</head>
<body class="html-body">
    <div id='calendar'></div>
</body>
</html>
