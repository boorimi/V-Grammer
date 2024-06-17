<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>D-Day and Contact List</title>
    <link rel="stylesheet" href="css/dday.css">
</head>
<body class="html-body">
    <div class="container">
        <button id="calendarButton" class="button-15">캘린더로 돌아가기</button>
        <h1>멤버의 디데이 목록</h1>
        <table class="table table-bordered" id="dday">
            <thead>
                <tr>
                    <th>이름</th>
                    <th>데뷔 날짜</th>
                    <th>생일 (월-일)</th> <!-- 수정된 부분 -->
                    <th>디데이까지 남은 일수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dday" items="${ddayList}">
                    <tr>
                        <td>${dday.m_name}</td>
                        <td>${dday.m_debut}</td>
                        <td>${dday.m_birth}</td> <!-- 수정된 부분 -->
                        <td>${dday.daysUntilDday}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="js/dday.js"></script>
</body>
</html>
