<%@ page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title>Archives</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
</head>
<body>
	<button onclick="location.href='ArchiveUpdateC'">수정하기</button>
	<!-- 상단 페이징 시작 -->
	<div class="archive-bottom">
		<div>
			<a href="ArchivePageC?p=1">最初に</a>
		</div>
		<c:set var="pageUnit" value="10" />
		<!-- page변수 = 현재페이지 * 페이지유닛 -->
		<c:set var="page"
			value="${fn:substringBefore(Math.floor((curPageNo - 1) div pageUnit) * pageUnit, '.')}" />
		<div>
			<c:if test="${page != 0}">
				<a href="ArchivePageC?p=${page - pageUnit + 1}">이전 ${pageUnit }페이지</a>
			</c:if>
		</div>
		<div style="display: flex">
			<c:forEach var="i" begin="${page + 1 }"
				end="${page + pageUnit <= pageCount ? page + pageUnit : pageCount}">
				<div class="archive-page-no">${i }</div>
			</c:forEach>
		</div>
		<div>
			<c:if
				test="${page + (curPageNo % pageUnit) < pageCount - (pageCount % pageUnit) && page + pageUnit != pageCount}">
				<a href="ArchivePageC?p=${page + pageUnit + 1}">다음 ${pageUnit }페이지</a>
			</c:if>
		</div>
		<div>
			<a href="ArchivePageC?p=${pageCount}">最後に</a>
		</div>
	</div>
	<!-- 상단 페이징 끝 -->
	<div id="archive-list">
		<c:forEach items="${archives}" var="archive">
			<div class="archive-contents">
				<p style="margin-top: 0px">
					<img class="archive-icon" src="haco_img/icon/${archive.i_icon}">
				</p>
				<p>${archive.a_m_pk }</p>
				<div class="archive-membername">${archive.m_name }</div>
				<div class="archive-collabo">コラボ : ${archive.a_collabo }</div>
				<div class="archive-collabomember">コラボメンバー :
					${archive.a_collabomember }</div>
				<div class="archive-category">カテゴリー : ${archive.a_category }</div>
				<div class="archive-date">${archive.a_date}</div>
				<div class="archive-time">${archive.a_time}</div>
				<div class="archive-title">${archive.a_title}</div>
				<div class="archive-thumbnail">
					<img src="${archive.a_thumbnail}"
						alt="${archive.a_title} Thumbnail">
				</div>
			</div>
		</c:forEach>
	</div>
	<!-- 여기부터 페이징 -->
	<div class="archive-bottom">
		<div>
			<a href="ArchivePageC?p=1">最初に</a>
		</div>
		<c:set var="pageUnit" value="10" />
		<!-- page변수 = 현재페이지 * 페이지유닛 -->
		<c:set var="page"
			value="${fn:substringBefore(Math.floor((curPageNo - 1) div pageUnit) * pageUnit, '.')}" />
		<div>
			<c:if test="${page != 0}">
				<a href="ArchivePageC?p=${page - pageUnit + 1}">이전 ${pageUnit }페이지</a>
			</c:if>
		</div>
		<div style="display: flex">
			<c:forEach var="i" begin="${page + 1 }"
				end="${pageUnit * ( page + 1 )}">
				<div class="archive-page-no">${i }</div>
			</c:forEach>
		</div>
		<div>
			<c:if
				test="${page + (curPageNo % pageUnit) < pageCount - (pageCount % pageUnit) && page + pageUnit != pageCount}">
				<a href="ArchivePageC?p=${page + pageUnit + 1 }">다음 ${pageUnit }페이지</a>
			</c:if>
		</div>
		<div>
			<a href="ArchivePageC?p=${pageCount}">最後に</a>
		</div>
	</div>
	<!-- 여기까지 페이징 -->


</body>


</html>

<%--     <c:if test="${empty archives}">
        <p>No archives found.</p>
    </c:if> --%>
