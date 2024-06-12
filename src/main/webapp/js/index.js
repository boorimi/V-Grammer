function openTradePage(id) {
  if (id != null) {
    location.href = "TradePage?p=1";
  } else {
    alert("로그인 후 이용 가능합니다.");
  }
}

$(function () {
  $(document).on("click", ".archive-page-no", function () {
    let page = $(this).text();
    $.ajax({
      url: "ArchiveC",
      type: "post",
      data: { page },
      dataType: "json",
    }).done(function (resData) {
      test(resData);
      console.log(JSON.stringify(resData));
    });
  });
});

$(function () {
  $(document).on("click", ".archive-page-no2", function () {
    let page = $(this).text();
    $.ajax({
      url: "ArchiveC",
      type: "post",
      data: { page },
      dataType: "json",
    }).done(function (resData) {
      test2(resData);
      console.log(JSON.stringify(resData));
    });
  });
});

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
           <p style="margin-top: 0px"> <img class="archive-icon" src="haco_img/icon/${archive.i_icon}" ></p>
            <p>${archive.a_m_pk} </p>
            <div class="archive-membername"> ${archive.m_name}</div>
           	<div class="archive-collabo"> コラボ : ${archive.a_collabo} </div>
           	<div class="archive-collabomember">	コラボメンバー : ${archive.a_collabomember} </div>
           	<div class="archive-category">カテゴリー : ${archive.a_category}</div>
            <div class="archive-date">	${formattedDate} </div>
           	<div class="archive-time">	${formattedTime} </div>
           	<div class="archive-title">	Title: ${archive.a_title}</div>
           	<div class="archive-thumbnail"> <img src="${archive.a_thumbnail}" alt="${archive.a_title} Thumbnail"> </div>
        </div>`;
    $archiveList.append(html);
  }
}
function test2(resData) {
  let $archiveList = $("#archive-list2");
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

    let html = `<form action="ArchiveUpdateC" method="post">
        <div class="archive-contents-update">
            <p style="margin-top: 0px">
                <img class="archive-icon" src="haco_img/icon/${archive.i_icon}" >
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
            </div>
             <div class="archive-collabomember">
             <div>コラボメンバー</div>
                <button type="button" onclick="openModal(this)" class="openModalButton">${archive.a_collabomember}</button>
				<input class="collaboMember" type="text" name="collabomember" /></div>
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
            </div>
            <div class="archive-date">${formattedDate}</div>
            <div class="archive-time">${formattedTime}</div>
            <div class="archive-title">Title: ${archive.a_title}</div>
            <div class="archive-thumbnail">
                <img src="${archive.a_thumbnail}" alt="${archive.a_title} Thumbnail">
            </div>
            <button type="submit">수정</button>
        </div>
    </form>
    `;
    $archiveList.append(html);
  }
}
