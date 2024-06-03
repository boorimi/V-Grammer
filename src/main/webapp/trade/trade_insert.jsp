<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>トレード</title>
    <script src="js/trade.js"></script>
    <link rel="stylesheet" href="css/trade.css" />
    <script src="js/trade.js"></script>
  </head>
  <body>
    <c:choose>
      <c:when test="${trades.text != null}">
        <c:set var="id" value="updateForm" />
        <c:set var="action" value="UpdateTrade" />
        <c:set var="onclick" value="tradeUpdate()" />
        <c:set var="buttonText" value="수정하기" />
      </c:when>
      <c:otherwise>
        <c:set var="id" value="insertForm" />
        <c:set var="action" value="InsertTrade" />
        <c:set var="onclick" value="tradeInsert()" />
        <c:set var="buttonText" value="작성하기" />
      </c:otherwise>
    </c:choose>
    <div class="trade-container">
      <div class="trade-title">
        <h1>トレード (insert or update)</h1>
      </div>
      <form id="${id }" action="${action }" method="post">
        <div class="">
          <div>본문</div>
          <input name="no" type="hidden" value="${trades.pk }" />
          <div>
            <textarea style="resize: none" rows="30" cols="80" name="text">
${text2 }</textarea
            >
          </div>
        </div>
        <div>
          <div>
            <button type="button" onclick="${onclick}">${buttonText }</button>
          </div>
          <div>
            <button type="button" onclick="tradeCancleInsert()">취소</button>
          </div>
        </div>
      </form>
    </div>
  </body>
</html>
