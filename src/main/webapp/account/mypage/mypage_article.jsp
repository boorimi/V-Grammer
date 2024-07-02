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
<script src="js/mypage_article.js" defer="defer"></script>
<link rel="stylesheet" href="css/trade.css" />
</head>
<%
response.setContentType("text/html; charset=UTF-8");
response.setCharacterEncoding("UTF-8");
%>
<body>
	<c:choose>
		<c:when test="${checkArticleCount == true }">
			<div style="width: 80%; position: relative; left: 10%;">
				<div class="mypage-tab-title">
					<div class="mypage-tab-icon-wrap">
						<img class="mypage-tab-icon" alt=""
							src="account/mypage/mypage_index_icon/article.png">
					</div>
					<h2>MY記事一覧</h2>

				</div>
				<div class="trade-conmain" data-article-count="${articleCount }">
					<!-- 본문페이지 for문 시작 -->
					<c:set var="totalItems" value="${fn:length(trades)}" />
					<c:forEach var="t" items="${trades }" varStatus="status">
						<div class="trade-content">
							<div class="trade-content-title">
								<div>${t.nickname}</div>
								<div>
									<a target="_blnck" href="https://x.com/${t.screenName}"><img
										src="haco_img/icon-twitter.png" /></a> ${t.pk}
								</div>
								<div>${t.date}</div>
							</div>
							<!--  카테고리 for문 시작 -->
							<div class="trade-content-category ">
								<c:forEach var="c" items="${t.category }">
									<c:forEach var="item" items="${checkboxItems}">
										<c:if test="${item.key == c}">
											<div class="trade-goods-category">${item.value}</div>
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

							<c:set var="cnt" value="0" />
							<c:forEach var="c_cnt" items="${tradeComments }">
								<%-- ${c_cnt.t_pk} --%>
								<c:if test="${c_cnt.t_pk == t.pk }">
									<c:set var="cnt" value="${cnt + 1 }" />
								</c:if>
							</c:forEach>
							<c:set var="cnt2" value="${cnt}" />
							<div class="cute-button-box">
								<button class="trade-openComments cute-button-pink">コメント(${cnt2})</button>
							</div>
							<!-- 댓글 for문 시작 -->
							<c:set var="i" value="0" />
							<c:forEach var="tc" items="${tradeComments }">

								<c:if test="${tc.t_pk == t.pk }">
									<div class="trade-comments comment-wrap" style="display: none">
										<div>
											<div>${tc.sNickname}</div>
											<div>${tc.date }</div>
										</div>
										<div>
											<div>${tc.text}</div>
											<c:if test="${sessionScope.twitterId == tc.sTwitterId}">
												<div>
													<a onclick="tradeDelete(${t.pk})"> <img
														class="crud-icon" src="haco_img/delete.png" alt="">
													</a>
												</div>
											</c:if>
										</div>
									</div>
									<c:set var="i" value="${i + 1}" />
								</c:if>
							</c:forEach>
							<!-- 댓글 for문 끝 -->

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
				<div>
					<button id="more-btn" class="more-button-blue" value="5">More</button>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div style="width: 80%; position: relative; left: 10%;">
				<div class="mypage-tab-title">
					<div class="mypage-tab-icon-wrap">
						<img class="mypage-tab-icon" alt=""
							src="account/mypage/mypage_index_icon/article.png">
					</div>
					<h2>MY記事一覧</h2><br>
					<div>없다 이놈아</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>


	<!-- more -->






	<!-- <div>
      <input id="search-input" />
      <button id="search-btn">버튼</button>
      <hr />
      <span id="result"></span>
    </div> -->
</body>
<style>
@import
	url("https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@700&display=swap")
	;

.more-button-box {
	display: flex;
	justify-content: center;
	margin-bottom: 20px;
}

.more-button-blue {
	font-family: "Noto Sans JP", sans-serif; /* 일본어 텍스트에 어울리는 글꼴 사용 */
	font-size: 1rem; /* 글자 크기 */
	color: #fff; /* 텍스트 색상 */
	background: linear-gradient(45deg, #6985ff, #69c3ff);
	/* 버튼 배경색 그라데이션 */
	border: none; /* 기본 테두리 제거 */
	border-radius: 20px; /* 둥근 테두리 */
	padding: 10px 20px; /* 내부 여백 */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 상자 그림자 */
	cursor: pointer; /* 커서 변경 */
	transition: background 0.3s ease, transform 0.3s ease; /* 부드러운 전환 효과 */
	position: relative;
	left: 50%;
	transform: translateX(-50%);
}

.more-button-blue:hover {
	background: linear-gradient(45deg, #6985ff, #69c3ff);
	/* 호버 시 배경색 그라데이션 변경 */
	transform: translateY(-3px); /* 호버 시 살짝 위로 이동 */
}

.more-button-blue:active {
	background: linear-gradient(45deg, #6985ff, #69c3ff); /* 클릭 시 배경색 유지 */
	transform: translateY(0); /* 클릭 시 원래 위치로 돌아감 */
}

.more-button-blue:focus {
	outline: none; /* 포커스 시 기본 아웃라인 제거 */
	box-shadow: 0 0 0 3px rgba(105, 153, 255, 0.5); /* 포커스 시 그림자 효과 추가 */
}
</style>
</html>
