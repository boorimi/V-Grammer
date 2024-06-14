<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/archive.css">
<script src="js/archiveupdate.js" defer></script>
</head>
<body>
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
				<div class="archive-page-no2">${i }</div>
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
	<div id="archive-list2">
		<c:forEach items="${archives}" var="archive">
			<form action="ArchiveUpdateC" method="post">
				<div class="archive-contents-update">
			<div><button type="submit">수정</button></div>
					<p style="margin-top: 0px">
						<img class="archive-icon" src="haco_img/icon/${archive.i_icon}" />
					</p>
					<input type="hidden" name="a_pk" value="${archive.a_pk}">
					<div class="archive-membername">${archive.m_name}</div>

					<div class="archive-collabo">
						<div>コラボ</div>
						<select name="collabo">
							<option value="未分類">未分類</option>
							<option value="yes">Yes</option>
							<option value="no">No</option>
						</select>
						<input class="collabo-value" type="hidden" value="${archive.a_collabo}"/>
					</div>
					<div class="archive-collabomember">
						<div>コラボメンバー</div>
						<button type="button" onclick="openModal(this)"
							class="openModalButton">선택하기</button>
						<input type="hidden" class="collaboMember" name="collabomember" value="${archive.a_collabomember}" />
						<div class="collaboMember2">
						</div>
					</div>
					<div class="archive-category">
						<div>カテゴリー</div>
						<select name="category">
							<option value="未分類">未分類</option>
							<option value="雑談">雑談</option>
							<option value="歌枠">歌枠</option>
							<option value="ゲーム">ゲーム</option>
							<option value="企画">企画</option>
							<option value="ASMR">ASMR</option>
							<option value="shorts">shorts</option>
							<option value="切り抜き">切り抜き</option>
							<option value="オリジナル曲">オリジナル曲</option>
							<option value="他">他</option>
						</select>
						<input class="category-value" type="hidden" value="${archive.a_category}"/>
					</div>
					<div class="archive-date">${archive.a_date}</div>
					<div class="archive-time">${archive.a_time}</div>
					<div class="archive-title">${archive.a_title}</div>
					<div class="archive-thumbnail">
						<img src="${archive.a_thumbnail}"
							alt="${archive.a_title} Thumbnail" />
					</div>
					
				</div>
			</form>
		</c:forEach>
	</div>

	<div class="dialog-container" id="myModal">
    <div class="modal-content">
        <span id="close" onclick="closeModal()">&times;</span>
        <div id="checkboxForm" class="form-container">
            <p>Select your options:</p>
            <div>
            	<div class="archive-modal-detail-gen">
                <div class="archive-modal-detail">
                    <div><label><input type="checkbox" name="collabomember" value="七彩てまり"> 七彩てまり</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="田中りゅこ"> 田中りゅこ</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="夜夢瑠紅"> 夜夢瑠紅</label></div>
                </div>
                </div>
                <div class="archive-modal-detail-gen">
                <div class="archive-modal-detail">
                    <div><label><input type="checkbox" name="collabomember" value="赤衣アカメ"> 赤衣アカメ</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="星ノ音コロン"> 星ノ音コロン</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="愛咲よつのは"> 愛咲よつのは</label></div>
                </div>
                <div class="archive-modal-detail">    
                    <div><label><input type="checkbox" name="collabomember" value="玉ノ井もなか"> 玉ノ井もなか</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="綾坂希穂"> 綾坂希穂</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="ソフィ・ローズ"> ソフィ・ローズ</label></div>
                </div>
                </div>
                <div class="archive-modal-detail-gen">
                <div class="archive-modal-detail">    
                    <div><label><input type="checkbox" name="collabomember" value="天海くりね"> 天海くりね</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="鳳儚"> 鳳儚</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="小日向千虎"> 小日向千虎</label></div>
				</div>
                <div class="archive-modal-detail">
                    <div><label><input type="checkbox" name="collabomember" value="白砂つな"> 白砂つな</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="橘シエナ"> 橘シエナ</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="ミラ・ルプス"> ミラ・ルプス</label></div>
                </div>
                </div>
                <div class="archive-modal-detail-gen">
                <div class="archive-modal-detail">    
                    <div><label><input type="checkbox" name="collabomember" value="銀灰まお"> 銀灰まお</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="リン・ガーネット"> リン・ガーネット</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="明堂しろね"> 明堂しろね</label></div>
                </div>
                <div class="archive-modal-detail">
                    <div><label><input type="checkbox" name="collabomember" value="華糖シェリー"> 華糖シェリー</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="ぺるぽ"> ぺるぽ</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="叶望ゆゆ"> 叶望ゆゆ</label></div>
                </div>
                </div>
                <div class="archive-modal-detail-gen">
                <div class="archive-modal-detail">    
                    <div><label><input type="checkbox" name="collabomember" value="雫川なのか"> 雫川なのか</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="堕天しすた"> 堕天しすた</label></div>
                    <div><label><input type="checkbox" name="collabomember" value="山寧恋"> 山寧恋</label></div>
                </div>
                <div class="archive-modal-detail">    
                    <div><label><input type="checkbox" name="collabomember" value="翠森アトリ"> 翠森アトリ</label></div>
                </div>
                </div>
            </div>
            <button type="button" name="collabomember" value="selectedOptions" id="submitButton" onclick="applyModal()">apply</button>
        </div>
    </div>
</div>

</body>
<script>

    	
            

</script>
</html>



