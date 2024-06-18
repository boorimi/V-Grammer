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
        <div class="centered"><h1>멤버의 디데이 목록</h1></div>
        <table class="table table-bordered" id="dday">
            <thead>
                <tr>
                    <th>이름</th>
                    <th>데뷔 날짜</th>
                    <th>생일</th>
                    <th>디데이까지 남은 일수</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="prevName" value="" />
                <c:forEach var="dday" items="${ddayList}">
                    <c:if test="${not empty prevName}">
                        <c:if test="${prevName ne dday.m_name}">
                            <tr>
                                <td>${dday.m_name}</td>
                                <td></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty dday.m_birth}">
                                            생일: ${dday.m_birth}
                                        </c:when>
                                        <c:otherwise>
                                            생일: 없음
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td></td>
                            </tr>
                        </c:if>
                    </c:if>
                    <tr>
                        <td>${dday.m_name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty dday.m_debut}">
                                    데뷔 날짜: ${dday.m_debut}
                                </c:when>
                                <c:otherwise>
                                    데뷔 날짜: 없음
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td></td>
                        <td>${dday.m_debut != null ? dday.daysUntilDday : ''}</td>
                    </tr>
                    <c:set var="prevName" value="${dday.m_name}" />
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="js/dday.js"></script>
</body>
</html>
