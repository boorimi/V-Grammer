<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.css" rel="stylesheet" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script scr="https://cdn.jsdelivr.net/npm/rrule@2.6.2/dist/es5/rrule.min.js"></script>
<script src='js/calendar-addevent.js'></script>
<link rel="stylesheet" href="css/calendar.css">
</head>
<body class="html-body">
    <div id='calendar'></div>
    <div id="button-container">
        <br>
        <button id="button-dday" class="button-dday" onclick="window.location.href='DdayC'">Dday로</button>
    </div>
</body>
</html>
