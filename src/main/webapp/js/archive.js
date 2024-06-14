$(function () {
  // 비동기 페이징 (archive.jsp)
  $(document).on("click", ".archive-page-no", function () {
    $("#archive-list").css({ opacity: 0.3 });
    adjustOpacity(1);
    let page = $(this).text();
    $.ajax({
      url: "ArchiveC",
      type: "post",
      data: { page },
      dataType: "json",
    }).done(function (resData) {
      test(resData);
      replaceCollabomemberString();
      //console.log(JSON.stringify(resData));
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
				<p style="margin-top: 0px">
					<img class="archive-icon" src="haco_img/icon/${archive.i_icon}">
				</p>
				<p>${archive.a_m_pk}</p>
				<div class="archive-membername">${archive.m_name}</div>
				<div class="archive-collabo">${archive.a_collabo}</div>
				<div class="archive-collabomember">
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
        `<div class="archive-collabomember-item archive-${item}">${item}</div>`
    );
    // jQuery를 사용하여 해당 요소의 자식 요소에 추가하기
    //console.log(this);
    $(this)
      .closest(".archive-collabomember")
      .find(".collaboMember2")
      .html(divWrappedArray.join(""));
  });
}

// 미분류 글자를 null로 바꿔주는 함수
function replaceNull() {
  $(".collaboMember2").each(function () {
    let textInDiv = $(this).find(".archive-collabomember-item");
    if (textInDiv.text() == "未分類") {
      textInDiv.text("");
    }
  });
}
