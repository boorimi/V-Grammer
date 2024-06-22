<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>トレード</title>
    <link rel="stylesheet" href="css/trade.css" />
    <script src="js/trade.js"></script>
    <script src="js/tradeinsert.js"></script>
  </head>
  <body>
    <c:choose>
      <c:when test="${trades.text != null}">
        <c:set var="id" value="updateForm" />
        <c:set var="action" value="UpdateTrade" />
        <c:set var="onclick" value="tradeUpdate()" />
        <c:set var="buttonText" value="修正する" />
      </c:when>
      <c:otherwise>
        <c:set var="id" value="insertForm" />
        <c:set var="action" value="InsertTrade" />
        <c:set var="onclick" value="tradeInsert()" />
        <c:set var="buttonText" value="作成する" />
      </c:otherwise>
    </c:choose>
    
    <div class="trade-container">
      <div class="trade-title">
        <p>トレード</p>
      </div>
      <form id="${id }" action="${action }" method="post">
        <div>カテゴリー</div>
        <input id="goods-pk" type="hidden" value="${sessionScope.accountInfo.u_twitter_id }" />
        <c:set var="checkboxValues" value="${trades.category}" />
       	<c:set var="checkboxValuesStr" value="${fn:join(checkboxValues, ',')}" />
        <div class="trade-category">
          <c:forEach items="${checkboxItems }" var="cbi">
          <div>
            <label
              ><input
              	class="goodsCategory"
                type="checkbox"
                name="goodsCategory"
                value="${cbi.key }"
                <c:if test="${fn:contains(checkboxValuesStr,cbi.key)}">checked="checked"</c:if>/>${cbi.value }</label>
          </div>
          </c:forEach>
          </div>
          <div>
          <div>内容</div>
          <input name="no" type="hidden" value="${trades.pk }" />
          <div>
            <textarea id="trade-textarea" style="width:99%; height:50vh; border-radius: 5px;" name="text">
${text2 }</textarea
            >
          </div>
        </div>
        <div style="display:flex; justify-content: flex-end;">
          <div style="margin:10px 5px;">
            <button class="cute-button-blue" type="button" onclick="${onclick}">${buttonText }</button>
          </div>
          <div style="margin:10px 5px;">
            <button class="cute-button-pink" type="button" onclick="tradeCancleInsert()">キャンセル</button>
          </div>
        </div>
      </form>
    </div>
  </body>
</html>
