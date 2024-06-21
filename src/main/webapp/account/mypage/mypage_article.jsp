<%@page import="com.vg.ds.trade.TradeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>トレード</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<script src="js/mypage_article.js" defer></script>
<link rel="stylesheet" href="css/trade.css" />
</head>
<%
response.setContentType("text/html; charset=UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<body>

	<div style="text-align: center;">
		<button class="trade-openCategorys">カテゴリーで検索 ▼</button>
	</div>
	<form action="ArticleC">
		<c:choose>
			<c:when test="${category3 != null}">
				<c:set var="displayValue" value="flex" />
			</c:when>
			<c:otherwise>
				<c:set var="displayValue" value="none" />
			</c:otherwise>
		</c:choose>
		<div class="trade-category" style="display: ${displayValue};">
			<c:forEach items="${checkboxItems }" var="cbi">
				<div>
					<label><input type="checkbox" name="goodsCategory"
						value="${cbi.value }"
						${fn:contains(category3, cbi.value) ? 'checked="checked"' : ''} />${cbi.label }</label>
				</div>
			</c:forEach>
			<button id="trade-search-category">検索</button>
		</div>
	</form>

	<div class="trade-conmain">
		<!-- 본문페이지 for문 시작 -->
		<c:set var="totalItems" value="${fn:length(trades)}" />
		<c:forEach var="t" items="${trades }" varStatus="status">
			<div class="trade-content">
				<div class="trade-content-title">
					<div>${t.nickname}</div>
					<div>
						<a target="_blnck" href="https://x.com/${t.screenName}"><img
							src="haco_img/icon-twitter.png" /></a>
					</div>
					<div>${t.date}</div>
				</div>
				<!--  카테고리 for문 시작 -->
				<div class="trade-content-category ">
				<c:forEach var="c" items="${t.category }">
					<c:forEach var="item" items="${checkboxItems}">
						<c:if test="${item.value == c}">
							<div class="trade-goods-category">${item.label}</div>
						</c:if>
					</c:forEach>
				</c:forEach>
				<!-- 카테고리for문 끝 -->
			</div>
			<div class="trade-content-text">
				<div class="trade-con-title">${t.text }</div>
				<c:if test="${sessionScope.twitterId == t.twitterId}">
					<div>
						<a onclick="location.href='UpdateTrade?no=${t.pk}'"> <img
							class="crud-icon" src="haco_img/update.png" alt="" />
						</a>
					</div>
					<div>
						<a onclick="tradeDelete(${t.pk})"> <img class="crud-icon"
							src="haco_img/delete.png" alt="" />
						</a>
					</div>
				</c:if>
			</div>

			<!-- 댓글 for문 시작 -->
			<c:set var="i" value="0" />
			<c:forEach var="tc" items="${tradeComments }">
				<c:if test="${tc.t_pk == t.pk }">
					<div class="trade-comments" style="display: none">
						<div>
							<div>${tc.sNickname}</div>
							<div>${tc.date }</div>
						</div>
						<div>
							<div>${tc.text}</div>
							<c:if test="${sessionScope.twitterId == tc.sTwitterId}">
								<div>
									<a onclick="tradeDelete(${t.pk})"> <img class="crud-icon"
										src="haco_img/delete.png" alt="">
									</a>
								</div>
							</c:if>
						</div>
					</div>
					<c:set var="i" value="${i + 1}" />
				</c:if>
			</c:forEach>
			<!-- 댓글 for문 끝 -->
			<div class="cute-button-box">
						<button class="trade-openComments cute-button-pink">コメント(${i })</button>
					</div>
			<div class="trade-comments" style="display: none; border: 0px;">
						<form id="insertTradeCommentsForm_${t.pk }"
							action="InsertTradeComments">
							<input name="no" type="hidden" value="${t.pk }" /> <input
								name="masterTwitterId" type="hidden" value="${t.twitterId}" />
							<textarea style="width: 99%; height: 70px;" name="text"></textarea>
							<button class="cute-button-blue" type="button"
								onclick="tradeCommentsInsert('${t.pk }')">作成</button>
						</form>
					</div>
	</div>
	</c:forEach>
	<!-- 본문페이지 for문 끝 -->
	</div>




	<!-- more -->





	<div>
		<button id="more-btn" value="0">More</button>
	</div>
	<!-- <div>
      <input id="search-input" />
      <button id="search-btn">버튼</button>
      <hr />
      <span id="result"></span>
    </div> -->
</body>
</html>
