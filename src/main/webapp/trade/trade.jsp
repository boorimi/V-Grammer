<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>トレード</title>
    <script
      src="https://code.jquery.com/jquery-3.7.1.js"
      integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
      crossorigin="anonymous"
    ></script>
    <script src="js/trade.js" defer></script>
    <link rel="stylesheet" href="css/trade.css" />
  </head>
  <body>
    <div class="trade-container">
      <div class="trade-title">
        <h1>トレード</h1>
      </div>
      <div id="insert-button">
        <button onclick="location.href='InsertTrade'">글쓰기</button>
      </div>
      <div class="trade-conmain">
        <!-- 본문페이지 for문 시작 -->
        <c:set var="totalItems" value="${fn:length(trades)}" />
        <c:forEach var="t" items="${trades }" varStatus="status">
          <div class="trade-content">
            <div>
              <div>${t.nickname}</div>
              <div>${t.twitterId}</div>
              <div style="display:flex; flex-wrap: wrap;">
              <!--  카테고리 for문 시작 -->
              <c:forEach var="c" items="${t.category }">
              	<div class="trade-goods-category" style="margin:2px;padding: 2px;"> ${c }</div>
              </c:forEach>
              <!-- 카테고리for문 끝 -->
              </div>
              <div>${t.date }</div>
            </div>
            <div>
              <div class="trade-con-title">${t.text }</div>
              <div>
                <a onclick="location.href='UpdateTrade?no=${t.pk}'">
                  <img class="crud-icon" src="haco_img/update.png" alt="">
                </a>
              </div>
              <div>
                <a onclick="tradeDelete(${t.pk})">
                <img class="crud-icon" src="haco_img/delete.png" alt="">
                </a>
              </div>
            </div>
            <div>
              <button class="trade-openComments">댓글보기</button>
            </div>
            <!-- 댓글 for문 시작 -->
            <c:forEach var="tc" items="${tradeComments }">
              <c:if test="${tc.t_pk == t.pk }">
                <div class="trade-comments" style="display: none">
                  <div>
                  <div>${tc.sNickname}</div>
                  <div>${tc.date }</div>
                  </div>
                  <div>${tc.text}</div>
                </div>
              </c:if>
            </c:forEach>
            <!-- 댓글 for문 끝 -->
            <div class="trade-comments" style="display: none">
              <form id="insertTradeCommentsForm" action="InsertTradeComments">
                <input name="no" type="hidden" value="${t.pk }"> 
                <textarea
                  style="resize: none"
                  rows="5"
                  cols="70"
                  name="text"
                ></textarea>
                <button type="button" onclick="tradeCommentsInsert()">작성</button>
              </form>
            </div>
          </div>
        </c:forEach>
        <!-- 본문페이지 for문 끝 -->
      </div>
      
      <div class="trade-bottom">
        <div>
          <a href="TradePage?p=1">처음</a>
        </div>
        <c:set var="pageUnit" value="4" />
        <c:set
          var="page"
          value="${fn:substringBefore(Math.floor((curPageNo - 1) div pageUnit) * pageUnit, '.')}"
        />
        <div>
          <c:if test="${page != 0}">
            <a href="TradePage?p=${page - pageUnit + 1}"
              >이전 ${pageUnit }페이지</a
            >
          </c:if>
        </div>
        <div style="display: flex">
          <c:forEach
            var="i"
            begin="${page + 1 }"
            end="${page + pageUnit <= pageCount ? page + pageUnit : pageCount}"
          >
            <div class="trade-page-no">
              <a href="TradePage?p=${i }">[${i }]</a>
            </div>
          </c:forEach>
        </div>
        <div>
          <c:if
            test="${page + (curPageNo % pageUnit) < pageCount - (pageCount % pageUnit) && page + pageUnit != pageCount}"
          >
            <a href="TradePage?p=${page + pageUnit + 1 }"
              >다음 ${pageUnit }페이지</a
            >
          </c:if>
        </div>
        <div>
          <a href="TradePage?p=${pageCount}">끝</a>
        </div>
      </div>
    </div>
    <!-- <div>
      <input id="search-input" />
      <button id="search-btn">버튼</button>
      <hr />
      <span id="result"></span>
    </div> -->
  </body>
</html>
