$(function () {
  // 비동기 페이징 (archive.jsp)
  localStorage.setItem("member", "未分類");
  localStorage.setItem("category", "未分類");
  localStorage.setItem("title", "");

  $(document).on("click", ".archive-paging-no", function () {
    let member = localStorage.getItem("member");
    let category = localStorage.getItem("category");
    let title = localStorage.getItem("title");

    let paramData = {};
    let page = $(this).text();
    paramData.page = page;
    if (member != "未分類") {
      paramData.member = member;
    }
    if (category != "未分類") {
      paramData.category = category;
    }
    if (title != "") {
      paramData.title = title;
    }

    $("#archive-list").css({ opacity: 0.3 });
    adjustOpacity(1);

    $.ajax({
      url: "ArchiveC",
      type: "post",
      data: paramData,
      dataType: "json",
    }).done(function (resData) {
      test(resData);
      replaceCollabomemberString();
      replaceNull();
      //console.log(JSON.stringify(resData));
    });
  });

  // 검색 기능
  $(document).on("click", "#archive-search-button", function () {
    localStorage.setItem("member", $("select[name='member']").val());
    localStorage.setItem("category", $("select[name='category']").val());
    localStorage.setItem("title", $("input[name='title']").val());

    let member = localStorage.getItem("member");
    let category = localStorage.getItem("category");
    let title = localStorage.getItem("title");

    $("#archive-list").css({ opacity: 0.3 });
    adjustOpacity(1);

    $.ajax({
      url: "ArchiveSearchC",
      type: "post",
      data: { member, category, title },
      dataType: "json",
    }).done(function (resData) {
      console.log(resData);
      let pagingVariable = getPagingVariable(member, category, title).then(
        function (resData) {
          return resData;
        }
      );
      searchPage(resData, pagingVariable);
      replaceCollabomemberString();
      replaceNull();

      // console.log(JSON.stringify(resData));
    });
  });

  // 페이지 로드 시 투명도를 올리는 함수 호출
  adjustOpacity(1);

  //콜라보멤버 div 처리
  replaceCollabomemberString();

  // '미분류'라는 글자 빈칸으로 보여주기
  replaceNull();
});

// 시간 형식 변환 함수
function convertTimeTo24Hours(timeString) {
  let [time, meridiem] = timeString.split(" "); // 시간과 AM/PM을 분리

  let [hours, minutes, seconds] = time.split(":"); // 시간, 분, 초를 추출

  // 오후인 경우 시간을 12를 더해서 24시간 형식으로 변환
  if (meridiem === "오후") {
    hours = String(Number(hours) + 12).padStart(2, "0");
  }

  // 24시간 형식으로 조합하여 반환
  return `${hours}:${minutes}:${seconds}`;
}

// 월 이름을 숫자로 변환하는 함수
function getMonthNumber(monthName) {
  const months = [
    "1월",
    "2월",
    "3월",
    "4월",
    "5월",
    "6월",
    "7월",
    "8월",
    "9월",
    "10월",
    "11월",
    "12월",
  ];
  return months.indexOf(monthName) + 1;
}

// archive.jsp 비동기 적용 함수
function test(resData) {
  let $archiveList = $("#archive-list");
  $archiveList.html("");
  for (let i = 0; i < resData.length; i++) {
    let archive = resData[i];

    const dateString = archive.a_date;
    // 문자열을 공백과 쉼표로 분리하여 구성 요소 추출
    const [monthName, day, year] = dateString.replace(",", "").split(" ");
    // 월을 숫자로 변환
    const month = String(getMonthNumber(monthName)).padStart(2, "0");
    const dayPadded = String(day).padStart(2, "0");

    // yyyy-MM-dd 형식으로 변환
    const formattedDate = `${year}-${month}-${dayPadded}`;

    const formattedTime = convertTimeTo24Hours(archive.a_time);

    let html = `<div class="archive-contents">
				<div class="archive-update-div">
				<button class="cute-button" onclick="location.href='ArchiveUpdateC?pk=${archive.a_pk}'">修正する</button>
				</div>
				<div class="archive-icon-div" >
					<img class="archive-icon" src="haco_img/icon/${archive.i_icon}">
				</div>
				<!--  <p>${archive.a_m_pk}</p> -->
				<div class="archive-membername">${archive.m_name}</div>
				<div class="archive-collabo">${archive.a_collabo}</div>
				<div class="archive-collabo-member">
				  <input type="hidden" class="collaboMember" value="${archive.a_collabomember}" />
				  <div class="collaboMember2"></div>
				</div>
				<div class="archive-category">${archive.a_category}</div>
				<div class="archive-date">${formattedDate}</div>
				<div class="archive-time">${formattedTime}</div>
				<div class="archive-title">${archive.a_title}</div>
				<div class="archive-thumbnail">
					<a target="_blank" href="https://www.youtube.com/watch?v=${archive.a_videoid}">
						<img 
						src="${archive.a_thumbnail}" 
						alt="${archive.a_title} Thumbnail">
					</a>
				</div>
			</div>`;
    $archiveList.append(html);
  }
}

//비동기 검색 세부
async function searchPage(resData, pagingVariable) {
  let $archiveList = $("#archive-list");
  let $paging = $(".archive-paging-container");
  $archiveList.html("");

  // 본문영역
  for (let i = 0; i < resData.length; i++) {
    let archive = resData[i];

    const dateString = archive.a_date;
    // 문자열을 공백과 쉼표로 분리하여 구성 요소 추출
    const [monthName, day, year] = dateString.replace(",", "").split(" ");
    // 월을 숫자로 변환
    const month = String(getMonthNumber(monthName)).padStart(2, "0");
    const dayPadded = String(day).padStart(2, "0");

    // yyyy-MM-dd 형식으로 변환
    const formattedDate = `${year}-${month}-${dayPadded}`;

    const formattedTime = convertTimeTo24Hours(archive.a_time);

    let html = `<div class="archive-contents">
			<div class="archive-update-div">
				<button class="cute-button" onclick="location.href='ArchiveUpdateC?pk=${archive.a_pk}'">修正する</button>
			</div>
			<div class="archive-icon-div" >
				<img class="archive-icon" src="haco_img/icon/${archive.i_icon}">
			</div>
			<!--  <p>${archive.a_m_pk}</p> -->
			<div class="archive-membername">${archive.m_name}</div>
			<div class="archive-collabo">${archive.a_collabo}</div>
			<div class="archive-collabo-member">
				<input type="hidden" class="collaboMember" value="${archive.a_collabomember}" />
				<div class="collaboMember2"></div>
			</div>
			<div class="archive-category">${archive.a_category}</div>
			<div class="archive-date">${formattedDate}</div>
			<div class="archive-time">${formattedTime}</div>
			<div class="archive-title">${archive.a_title}</div>
			<div class="archive-thumbnail">
				<a target="_blank" href="https://www.youtube.com/watch?v=${archive.a_videoid}">
					<img
						src="${archive.a_thumbnail}"
						alt="${archive.a_title} Thumbnail">
				</a>
			</div>
		</div>`;
    $archiveList.append(html);

    //페이징 영역
    $paging.html("");
    let asdf = await pagingVariable;
    console.log(asdf);
    let data = asdf.split("!");
    let curPageNo = data[0]; // 현재페이지 정보
    let pageCount = data[1]; // 총 페이지 정보
    console.log(curPageNo);
    console.log(pageCount);
    let pageUnit = 10; // 페이징 단위
    //page변수 = 현재페이지 * 페이지유닛
    let page = ((curPageNo - 1) / pageUnit) * pageUnit;
    console.log(pageUnit);
    console.log(page);

    let initialHtml = `<div class="archive-paging-start">
        <a href="ArchivePageC?p=1">最初に</a>
      </div>
      <div class="archive-paging-unit-prev">
        <c:if test="${page != 0}">
          <a href="ArchivePageC?p=${page - pageUnit + 1}">
            以前 ${pageUnit}ページ
          </a>
        </c:if>
      </div>`;
    $paging.append(initialHtml);

    let initialHtml2 = `<div class="archive-paging-no-div">`;
    for (
      let i = page + 1;
      i <= (page + pageUnit <= pageCount ? page + pageUnit : pageCount);
      // i <= page + pageUnit;
      // i <= 9;
      i++
    ) {
      initialHtml2 += `<div class="archive-paging-no">${i}</div>`;
    }
    initialHtml2 += `</div>`;
    $paging.append(initialHtml2);
    let initialHtml3 = `<div class="archive-paging-unit-next">
        <c:if
          test="${
            page + (curPageNo % pageUnit) <
              pageCount - (pageCount % pageUnit) && page + pageUnit != pageCount
          }"
        >
          <a href="ArchivePageC?p=${page + pageUnit + 1}"
            >次 ${pageUnit}ページ</a
          >
        </c:if>
      </div>
      <div class="archive-paging-end">
        <a href="ArchivePageC?p=${pageCount}">最後に</a>
      </div>`;

    $paging.append(initialHtml3);
  }
}

// 투명도를 조절하는 함수
function adjustOpacity(opacityValue) {
  $("#archive-list").animate({ opacity: opacityValue }, 350);
}

// 콜라보 멤버 문자열을 div로 감싸 표현하는 함수
function replaceCollabomemberString() {
  // jQuery를 사용하여 NodeList를 가져오기
  let collabomemberStrings = $(".collaboMember");
  // NodeList를 배열로 변환하여 forEach 메서드 사용
  $(collabomemberStrings).each(function (idx, collabomemberString) {
    let collabomemberStringUpdate = collabomemberString.value.split("!");
    let divWrappedArray = collabomemberStringUpdate.map(
      (item) =>
        `<div class="archive-collabo-member-item archive-${item}">${item}</div>`
    );
    // jQuery를 사용하여 해당 요소의 자식 요소에 추가하기
    //console.log(this);
    $(this)
      .closest(".archive-collabo-member")
      .find(".collaboMember2")
      .html(divWrappedArray.join(""));
  });
}

// 미분류 글자를 null로 바꿔주는 함수
function replaceNull() {
  $(".collaboMember2").each(function () {
    let textInDiv = $(this).find(".archive-collabo-member-item");
    if (textInDiv.text() == "未分類") {
      textInDiv.text("");
    }
  });
}

function getPagingVariable(member, category, title) {
  return new Promise(function (resolve, reject) {
    $.ajax({
      url: "GetPagingVariableC",
      type: "GET",
      data: { member, category, title },
      success: function (resData) {
        resolve(resData); // 성공 시 데이터를 resolve
      },
      error: function (xhr, status, error) {
        reject(error); // 에러 발생 시 reject
      },
    });
  });
}

$(document).ready(function () {
  $(".archive-paging-no").click(function () {
    $(".archive-paging-no").removeClass("active"); // 모든 요소에서 active 클래스 제거
    $(this).addClass("active"); // 클릭된 요소에 active 클래스 추가
  });
});
