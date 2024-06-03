<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>D-Day List</title>
</head>
<body>
    <h1>멤버의 디데이 목록</h1>
    <table border="1">
        <thead>
            <tr>
                <th>이름</th>
                <th>날짜</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="dday" items="${ddayList}">
                <tr>
                    <td>${dday.name}</td>
                    <td>${dday.date}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
