$(function () {
  // 비동기 페이징 (archive.jsp)
  localStorage.setItem("member", "未分類");
  localStorage.setItem("category", "未分類");
  localStorage.setItem("title", "");

  $.ajax({
    url: "ArchiveSearchC",
    type: "post",
    data: { page : 1},
    dataType: "json",
  }).done(function (resData) {
    console.log(resData);
    getPagingVariableStart().then(function (resData) {
	console.log(resData)
    let data = resData.split("!");
    localStorage.setItem("currentPage", data[0]); // 현재페이지 정보
    localStorage.setItem("pageCount", data[1]);
      }
    );
  });

  $(document).on("click", ".archive-paging-no", function () {
    let page = localStorage.setItem("currentPage", $(this).text());
    let member = localStorage.getItem("member");
    let category = localStorage.getItem("category");
    let title = localStorage.getItem("title");

    let paramData = {};
    page = localStorage.getItem("currentPage");
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
  $(document).on(
    "click",
    ".archive-paging-start, .archive-paging-unit-prev, .archive-paging-unit-next, .archive-paging-end, #archive-search-button",
    function () {
      localStorage.setItem("member", $("select[name='member']").val());
      localStorage.setItem("category", $("select[name='category']").val());
      localStorage.setItem("title", $("input[name='title']").val());
	  let tempPagePrev = (Math.floor(( localStorage.getItem("currentPage") - 1 ) / 10) - 1) * 10 + 1;
	  let tempPageNext = (Math.floor(( localStorage.getItem("currentPage") - 1 ) / 10) + 1) * 10 + 1;
      
      if ($(this).hasClass("archive-paging-start")) {
        localStorage.setItem("currentPage", "1");
      } else if ($(this).hasClass("archive-paging-unit-prev")) {
        localStorage.setItem("currentPage", tempPagePrev);
      } else if ($(this).hasClass("archive-paging-unit-next")) {
        localStorage.setItem("currentPage", tempPageNext);
      } else if ($(this).hasClass("archive-paging-end")) {
        localStorage.setItem("currentPage", localStorage.getItem("pageCount"));
      } else if ($(this).attr("id") === "archive-search-button") {
        localStorage.setItem("currentPage", "1");
        localStorage.setItem("pageCount", "????");
      }
      let page = localStorage.getItem("currentPage");
      let member = localStorage.getItem("member");
      let category = localStorage.getItem("category");
      let title = localStorage.getItem("title");

      $("#archive-list").css({ opacity: 0.3 });
      adjustOpacity(1);

      $.ajax({
        url: "ArchiveSearchC",
        type: "post",
        data: { page, member, category, title },
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
    }
  );

	
  // 업데이트 페이지로 비동기 처리	
  $(document).on("click", ".archive-update-button-1", function () {
	//$("#archive-list2").css({ opacity: 0.3 });
     let a_pk = $(this).val();
     console.log(a_pk);
     $.ajax({
      url: "ArchiveUpdateC",
      type: "get",
      data: { a_pk },
      dataType: "json",
      success: function(resData) {
        // 요청이 성공했을 때 실행할 코드
        test2(resData);
  		  adjustOpacity2(1);
  		  updateArchive();
  		  $("select[name='collabo']").change(function () {
  		  toggleButton();
	});
    },
    error: function(xhr, status, error) {
        // 요청이 실패했을 때 실행할 코드
        console.log("Request failed: " + status + ", " + error);
    }
      
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
  }
  //페이징 영역
  $paging.html("");
  let asdf = await pagingVariable;
  console.log(asdf);
  let data = asdf.split("!");
  let currentPage = localStorage.getItem("currentPage"); // 현재페이지 정보
  let pageCount = localStorage.getItem("pageCount"); // 총 페이지 정보
  console.log(currentPage);
  console.log(pageCount);
  let pageUnit = 10; // 페이징 단위
  //page변수 = 현재페이지 * 페이지유닛
  let page = ((currentPage - 1) / pageUnit) * pageUnit;
  console.log(pageUnit);
  console.log(page);
  ////////////////////////////////////////
  let initialHtml = `<div class="archive-paging-start">
        <a>最初に</a>
      </div>`;
  $paging.append(initialHtml);
  ////////////////////////////////////////
  let initialHtml2 = `<div class="archive-paging-unit-prev">`;
  if (page != 0) {
    initialHtml2 += `<a>以前 ${pageUnit}ページ</a>`;
  }
  initialHtml2 += `</div>`;
  $paging.append(initialHtml2);
  ///////////////////////////////////////////
  let initialHtml3 = `<div class="archive-paging-no-div">`;
  console.log(currentPage);
  console.log(pageCount);
  
  for (
    let i = currentPage - (currentPage % 10) + 1;
    i <= (currentPage + 9 < pageCount ? currentPage - (currentPage % 10) + 10 : pageCount);
    i++
  ) {
    initialHtml3 += `<div class="archive-paging-no">${i}</div>`;
  }
  initialHtml3 += `</div>`;
  $paging.append(initialHtml3);
  ///////////////////////////////////////
  let initialHtml4 = `<div class="archive-paging-unit-next">`;
  if (
    page + (currentPage % pageUnit) < pageCount - (pageCount % pageUnit) &&
    page + pageUnit != pageCount
  ) {
    initialHtml4 += `<a>次 ${pageUnit}ページ</a>`;
  }
  initialHtml4 += `</div>`;
  $paging.append(initialHtml4);
  //////////////////////////////////////////
  let initialHtml5 = `<div class="archive-paging-end">
        <a>最後に</a>
      </div>`;
  $paging.append(initialHtml5);
}
////////////////////////////////////////////

function test2(resData) {
  let $body = $(".all-wrapper");
  $body.html("");
    let archive = resData;

    const dateString = archive.a_date;
    // 문자열을 공백과 쉼표로 분리하여 구성 요소 추출
    const [monthName, day, year] = dateString.replace(",", "").split(" ");
    // 월을 숫자로 변환
    const month = String(getMonthNumber(monthName)).padStart(2, "0");
    const dayPadded = String(day).padStart(2, "0");

    // yyyy-MM-dd 형식으로 변환
    const formattedDate = `${year}-${month}-${dayPadded}`;

    const formattedTime = convertTimeTo24Hours(archive.a_time);

    let html = `<div id="archive-list2">
      <form method="post">
        <div class="archive-contents-update">
          <div>
            <button id="updateButton" class="cute-button">修正</button>
          </div>
          <p>
            <img class="archive-icon" src="haco_img/icon/${archive.i_icon}" />
          </p>
          <input type="hidden" name="a_pk" value="${archive.a_pk}" />
          <div class="archive-membername">${archive.m_name}</div>

          <div class="archive-collabo">
            <div>コラボ</div>
            <select name="collabo">
              <option value="未分類">未分類</option>
              <option value="外部コラボ">外部コラボ</option>
              <option value="ハコ内コラボ">ハコ内コラボ</option>
              <option value="なし">なし</option>
            </select>
            <input
              class="collabo-value"
              type="hidden"
              value="${archive.a_collabo}"
            />
          </div>
          <div class="archive-collabo-member">
            <div style="font-size: 11pt">コラボメンバー</div>
            <button
              type="button"
              onclick="openModal(this)"
              class="openModalButton"
            >
              選択
            </button>
            <input
              type="hidden"
              class="collaboMember"
              name="collabomember"
              value="${archive.a_collabomember}"
            />
            <div class="collaboMember2"></div>
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
            <input
              class="category-value"
              type="hidden"
              value="${archive.a_category}"
            />
          </div>
          <div class="archive-date">${formattedDate}</div>
          <div class="archive-time">${formattedTime}</div>
          <div class="archive-title">${archive.a_title}</div>
          <div class="archive-thumbnail">
            <img
              src="${archive.a_thumbnail}"
              alt="${archive.a_title} Thumbnail"
            />
          </div>
        </div>
      </form>
    </div>

    <div class="dialog-container" id="myModal">
      <div class="modal-content">
        <span id="close" onclick="closeModal()">&times;</span>
        <div id="checkboxForm" class="form-container">
          <p>Select your options:</p>
          <div>
            <div class="archive-modal-detail-gen">
              <div class="archive-modal-detail">
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="七彩てまり"
                    />七彩てまり</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="田中りゅこ"
                    />田中りゅこ</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="夜夢瑠紅"
                    />夜夢瑠紅</label
                  >
                </div>
              </div>
            </div>
            <div class="archive-modal-detail-gen">
              <div class="archive-modal-detail">
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="赤衣アカメ"
                    />赤衣アカメ</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="星ノ音コロン"
                    />星ノ音コロン</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="愛咲よつのは"
                    />愛咲よつのは</label
                  >
                </div>
              </div>
              <div class="archive-modal-detail">
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="玉ノ井もなか"
                    />玉ノ井もなか</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="綾坂希穂"
                    />綾坂希穂</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="ソフィ・ローズ"
                    />ソフィ・ローズ</label
                  >
                </div>
              </div>
            </div>
            <div class="archive-modal-detail-gen">
              <div class="archive-modal-detail">
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="天海くりね"
                    />天海くりね</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="鳳儚"
                    />鳳儚</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="小日向千虎"
                    />小日向千虎</label
                  >
                </div>
              </div>
              <div class="archive-modal-detail">
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="白砂つな"
                    />白砂つな</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="橘シエナ"
                    />橘シエナ</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="ミラ・ルプス"
                    />ミラ・ルプス</label
                  >
                </div>
              </div>
            </div>
            <div class="archive-modal-detail-gen">
              <div class="archive-modal-detail">
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="銀灰まお"
                    />銀灰まお</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="リン・ガーネット"
                    />リン・ガーネット</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="明堂しろね"
                    />明堂しろね</label
                  >
                </div>
              </div>
              <div class="archive-modal-detail">
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="華糖シェリー"
                    />華糖シェリー</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="ぺるぽ"
                    />ぺるぽ</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="叶望ゆゆ"
                    />叶望ゆゆ</label
                  >
                </div>
              </div>
            </div>
            <div class="archive-modal-detail-gen">
              <div class="archive-modal-detail">
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="雫川なのか"
                    />雫川なのか</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="堕天しすた"
                    />堕天しすた</label
                  >
                </div>
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="山寧恋"
                    />山寧恋</label
                  >
                </div>
              </div>
              <div class="archive-modal-detail">
                <div>
                  <label
                    ><input
                      type="checkbox"
                      name="collabomember"
                      value="翠森アトリ"
                    />翠森アトリ</label
                  >
                </div>
              </div>
            </div>
          </div>
          <button
            class="cute-button"
            type="button"
            name="collabomember"
            value="selectedOptions"
            id="submitButton"
            onclick="applyModal()"
            style="margin-top: 5px"
          >
            apply
          </button>
        </div>
      </div>
    </div>`;
    $body.append(html);
  }


// 투명도를 조절하는 함수
function adjustOpacity(opacityValue) {
  $("#archive-list").animate({ opacity: opacityValue }, 350);
}
function adjustOpacity2(opacityValue) {
  $("#archive-list2").animate({ opacity: opacityValue }, 350);
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

function getPagingVariableStart() {
  return new Promise(function (resolve, reject) {
    $.ajax({
      url: "GetPagingVariableC",
      type: "GET",
      data: { },
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

// 모달 팝업 기능
let activeBtn;
let activeInput;
let activeDiv;
function openModal(btn) {
  var closeButton = document.querySelector("#close");
  var submitButton = document.querySelector("#submitButton");
  activeBtn = btn;
  activeInput = btn.parentElement.children[2];
  activeDiv = btn.parentElement.children[3];
  console.log(activeInput);
  console.log(activeBtn);
  console.log(activeDiv);
  console.log("-----------");
  document.querySelectorAll("input[name='collabomember']").forEach((asd) => {
    asd.checked = false;
    if (activeInput.value.indexOf(asd.value) != -1) {
      asd.checked = true;
    }
  });
  console.log("-----------");

  var modal = document.querySelector("#myModal");
  modal.style.display = "block";
}

function applyModal() {
  var closeButton = document.querySelector("#close");
  console.log("Submit button clicked");
  let form = document.querySelector(".dialog-container .form-container");
  let selectedOptions = [];
  let checkboxes = form.querySelectorAll('input[type="checkbox"]:checked');
  checkboxes.forEach(function (checkbox) {
    selectedOptions.push(checkbox.value);
  });
  console.log(activeBtn);
  console.log(selectedOptions);
  //   activeBtn.innerText = selectedOptions.join("<br>");
  activeInput.value = selectedOptions.join("!");
  //   activeDiv.innerText = selectedOptions.join("\n");

  let a = $(activeBtn.nextElementSibling);
  replaceCollabomemberString(a);

  document
    .querySelectorAll("#checkboxForm input[type='checkbox']:checked")
    .forEach((chkInput) => {
      chkInput.checked = false;
    });

  closeButton.click();
}

function closeModal() {
  document.querySelector("#myModal").style.display = "none";
}

function toggleButton() {
  $("select[name='collabo']").each(function () {
    let select = $(this);
    let openModalButton = select
      .closest(".archive-collabo")
      .next(".archive-collabo-member")
      .find(".openModalButton");
    if (select.val() === "ハコ内コラボ") {
      openModalButton.css("display", "block");
    } else {
      openModalButton.css("display", "none");
    }

    // ハコ内コラボ 아닐때 콜라보멤버 초기화 함수
     let select2 = $(this);
    let openModalButton2 = select2
      .closest(".archive-collabo")
      .next(".archive-collabo-member");
    if (select2.val() != "ハコ内コラボ") {
      openModalButton2.find(".collaboMember2").text("未分類");
      openModalButton2.find("input").val("未分類");
    } 
  });
}
function updateArchive() {
	$(document).on("click", "#updateButton", function () {
    $.ajax({
      url: "ArchiveUpdateC",
      type: "post",
      data: { a_pk, collabo, collabomember, category },
      dataType: "json",
    }).done(function (resData) {
      history.back();
     });
  });	
}
