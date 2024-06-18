<%@ page import="java.util.ArrayList"%> <%@ page
contentType="text/html;charset=UTF-8" language="java"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fn"
uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
  <head>
    <title>Archives</title>
    <script
      src="https://code.jquery.com/jquery-3.7.1.js"
      integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
      crossorigin="anonymous"
    ></script>
    <link rel="stylesheet" href="css/archive.css" />
    <script src="js/archive.js" defer></script>
  </head>

  <body>
    <!-- 검색창 시작 -->
    <form>
      <div class="archive-search-wrapper">
        <div class="archive-search-member">
          <select name="member">
            <option value="未分類">未分類</option>
            <option value="七彩てまり">七彩てまり</option>
            <option value="田中りゅこ">田中りゅこ</option>
            <option value="夜夢瑠紅">夜夢瑠紅</option>
            <option value="赤衣アカメ">赤衣アカメ</option>
            <option value="星ノ音コロン">星ノ音コロン</option>
            <option value="愛咲よつのは">愛咲よつのは</option>
            <option value="玉ノ井もなか">玉ノ井もなか</option>
            <option value="綾坂希穂">綾坂希穂</option>
            <option value="ソフィ・ローズ">ソフィ・ローズ</option>
            <option value="天海くりね">天海くりね</option>
            <option value="鳳儚">鳳儚</option>
            <option value="小日向千虎">小日向千虎</option>
            <option value="白砂つな">白砂つな</option>
            <option value="橘シエナ">橘シエナ</option>
            <option value="ミラ・ルプス">ミラ・ルプス</option>
            <option value="銀灰まお">銀灰まお</option>
            <option value="リン・ガーネット">リン・ガーネット</option>
            <option value="明堂しろね">明堂しろね</option>
            <option value="華糖シェリー">華糖シェリー</option>
            <option value="ぺるぽ">ぺるぽ</option>
            <option value="叶望ゆゆ">叶望ゆゆ</option>
            <option value="雫川なのか">雫川なのか</option>
            <option value="堕天しすた">堕天しすた</option>
            <option value="山寧恋">山寧恋</option>
            <option value="翠森アトリ">翠森アトリ</option>
          </select>
        </div>
        <div class="archive-search-category">
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
        </div>
        <div class="archive-search-title">
          <input type="text" name="title" placeholder="タイトルで検索" />
          <button type="button" id="archive-search-button">検索</button>
        </div>
      </div>
    </form>

    <div id="archive-list">
      <c:forEach items="${archives}" var="archive">
        <div class="archive-contents">
          <div class="archive-update-div">
            <button onclick="location.href='ArchiveUpdateC?pk=${archive.a_pk}'">
              修正する
            </button>
          </div>
          <div class="archive-icon-div">
            <img class="archive-icon" src="haco_img/icon/${archive.i_icon}" />
          </div>
          <!--  <p>${archive.a_m_pk }</p> -->
          <div class="archive-membername">${archive.m_name }</div>
          <div class="archive-collabo">${archive.a_collabo }</div>
          <div class="archive-collabo-member">
            <input
              type="hidden"
              class="collaboMember"
              value="${archive.a_collabomember}"
            />
            <div class="collaboMember2"></div>
          </div>
          <div class="archive-category">${archive.a_category }</div>
          <div class="archive-date">${archive.a_date}</div>
          <div class="archive-time">${archive.a_time}</div>
          <div class="archive-title">${archive.a_title}</div>
          <div class="archive-thumbnail">
            <a
              target="_blank"
              href="https://www.youtube.com/watch?v=${archive.a_videoid }"
            >
              <img
                src="${archive.a_thumbnail}"
                alt="${archive.a_title} Thumbnail"
              />
            </a>
          </div>
        </div>
      </c:forEach>
    </div>
    <!--  페이징 시작 -->
    <div class="archive-paging-container">
      <div class="archive-paging-start">
        <a href="ArchivePageC?p=1">最初に</a>
      </div>
      <c:set var="pageUnit" value="10" />
      <!-- page변수 = 현재페이지 * 페이지유닛 -->
      <c:set
        var="page"
        value="${fn:substringBefore(Math.floor((curPageNo - 1) div pageUnit) * pageUnit, '.')}"
      />
      <div class="archive-paging-unit-prev">
        <c:if test="${page != 0}">
          <a href="ArchivePageC?p=${page - pageUnit + 1}">
            以前 ${pageUnit }ページ
          </a>
        </c:if>
      </div>

      <div class="archive-paging-no-div">
        <c:forEach
          var="i"
          begin="${page+1 }"
          end="${page + pageUnit <= pageCount ? page + pageUnit : pageCount}"
        >
          <div class="archive-paging-no">${i }</div>
        </c:forEach>
      </div>
      <div class="archive-paging-unit-next">
        <c:if
          test="${page + (curPageNo % pageUnit) < pageCount - (pageCount % pageUnit) && page + pageUnit != pageCount}"
        >
          <a href="ArchivePageC?p=${page + pageUnit + 1}"
            >次 ${pageUnit }ページ</a
          >
        </c:if>
      </div>
      <div class="archive-paging-end">
        <a href="ArchivePageC?p=${pageCount}">最後に</a>
      </div>
    </div>
    <!-- 상단 페이징 끝 -->
  </body>
</html>
