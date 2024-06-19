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
        <button id="calendarButton" class="button-15">カレンダー戻る</button>
        <div class="centered"><h1>各メンバーのDdayリスト</h1></div>
        <table class="table table-bordered" id="dday">
            <thead>
                <tr>
                    <th>名前</th>
                    <th>記念日</th>
                    <th>残っている日</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dday" items="${ddayList}">
                    <tr>
                        <td>${dday.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${dday.eventType eq '데뷔'}">
                                    デビュー日 : ${dday.eventDate}
                                </c:when>
                                <c:when test="${dday.eventType eq '생일'}">
                                    誕生日 : ${dday.eventDate}
                                </c:when>
                                <c:otherwise>
                                    ${dday.eventDate}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${dday.daysUntilDday >= 0}">
                                    D-${dday.daysUntilDday}
                                </c:when>
                                <c:otherwise>
                                    ${dday.daysUntilDday}
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="js/dday.js"></script>
</body>
</html>
