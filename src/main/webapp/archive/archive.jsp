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
<link rel="stylesheet" href="css/archive.css">
<script src="js/archive.js" defer></script>
</head>

<body>
	<!-- 상단 페이징 시작 -->
	<div class="archive-page-top">
		<div class="archive-page-start">
			<a href="ArchivePageC?p=1">最初に</a>
		</div>
		<c:set var="pageUnit" value="10" />
		<!-- page변수 = 현재페이지 * 페이지유닛 -->
		<c:set var="page"
			value="${fn:substringBefore(Math.floor((curPageNo - 1) div pageUnit) * pageUnit, '.')}" />
		<div class="archive-page-unit-prev">
			<c:if test="${page != 0}">
				<a href="ArchivePageC?p=${page - pageUnit + 1}">
				    以前 ${pageUnit }ページ
				</a>
			</c:if>
		</div>
		<div class="archive-page-no-div">
			<c:forEach var="i" begin="${page + 1 }"
				end="${page + pageUnit <= pageCount ? page + pageUnit : pageCount}">
				<div class="archive-page-no">${i }</div>
			</c:forEach>
		</div>
		<div class="archive-page-unit-next">
			<c:if
				test="${page + (curPageNo % pageUnit) < pageCount - (pageCount % pageUnit) && page + pageUnit != pageCount}">
				<a href="ArchivePageC?p=${page + pageUnit + 1}">次 ${pageUnit }ページ</a>
			</c:if>
		</div>
		<div class="archive-page-end">
			<a href="ArchivePageC?p=${pageCount}">最後に</a>
		</div>
	</div>
	<!-- 상단 페이징 끝 -->
	<div id="archive-list">
		<c:forEach items="${archives}" var="archive">
			<div class="archive-contents">
				<div class="archive-update-div">
				<button onclick="location.href='ArchiveUpdateC?pk=${archive.a_pk}'">修正する</button>
				</div>
				<div class="archive-icon-div" >
					<img class="archive-icon" src="haco_img/icon/${archive.i_icon}">
				</div>
				<!--  <p>${archive.a_m_pk }</p> -->
				<div class="archive-membername">${archive.m_name }</div>
				<div class="archive-collabo">${archive.a_collabo }</div>
				<div class="archive-collabo-member">
				  <input type="hidden" class="collaboMember" value="${archive.a_collabomember}" />
				  <div class="collaboMember2"></div>
				</div>
				<div class="archive-category">${archive.a_category }</div>
				<div class="archive-date">${archive.a_date}</div>
				<div class="archive-time">${archive.a_time}</div>
				<div class="archive-title">${archive.a_title}</div>
				<div class="archive-thumbnail">
					<a target="_blank" href="https://www.youtube.com/watch?v=${archive.a_videoid }">
						<img 
						src="${archive.a_thumbnail}" 
						alt="${archive.a_title} Thumbnail">
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
  </body>
</html>