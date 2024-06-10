<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>D-Day and Contact List</title>
    <link rel="stylesheet" href="css/dday.css">
</head>
<body>
    <div class="container">
        <h1>멤버의 디데이 목록</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>이름</th>
                    <th>날짜</th>
                    <th>디데이까지 남은 일수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dday" items="${ddayList}">
                    <tr>
                        <td>${dday.m_name}</td>
                        <td>${dday.m_debut}</td>
                        <td>${dday.daysUntilDday}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
