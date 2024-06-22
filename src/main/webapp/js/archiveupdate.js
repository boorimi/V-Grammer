$(function () {

  // 비동기 페이징 (archiveupdate.jsp)
  // 추가 : 페이징 후에도 select태그 자동지정 및 토글버튼 구현
  $(document).on("click", ".archive-update-button-1", function () {
     let a_pk = $("input[name='a_pk']").val();
     console.log(a_pk);
     $.ajax({
      url: "ArchiveUpdateC",
      type: "get",
      data: { a_pk },
      dataType: "json",
      success: function(resData) {
        // 요청이 성공했을 때 실행할 코드
        test2(resData);
    },
    error: function(xhr, status, error) {
        // 요청이 실패했을 때 실행할 코드
        console.log("Request failed: " + status + ", " + error);
    }
      
    });
  });

  // 페이지 로드 시 투명도를 올리는 함수 호출
  adjustOpacity2(1);

  //콜라보멤버 div 처리
  let a = $(".collaboMember");
  replaceCollabomemberString(a);

  // select 태그 자동지정 처리
  collabo_yesno_selected();
  category_selected();

  // 페이지 로드될때 이벤트 처리
  toggleButton();

// 미분류 문자를 null로 바꾸는 함수
  replaceNull();

  // select 요소의 값이 변경될 때 이벤트 처리
  $("select[name='collabo']").change(function () {
    toggleButton();
    replaceNull();
  });
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

// 투명도를 조절하는 함수
function adjustOpacity2(opacityValue) {
  $("#archive-list2").animate({ opacity: opacityValue }, 350);
}

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
// 콜라보 yes, no 를 선택한값에 맞게 적용하기
function collabo_yesno_selected() {
  document.querySelectorAll("select[name='collabo']").forEach((select) => {
    Array.from(select.options).forEach((option) => {
      if (option.value == select.nextElementSibling.value) {
        option.selected = true;
      }
    });
  });
}

// 카테고리를 수정한값에 맞게 표시해주기
function category_selected() {
  document.querySelectorAll("select[name='category']").forEach((select) => {
    //    console.log(select.nextElementSibling.value);
    //  console.log("Select element:", select);
    Array.from(select.options).forEach((option) => {
      //    console.log("Option value:", option.value);
      if (option.value == select.nextElementSibling.value) {
        option.selected = true;
      }
    });
  });
}
//ハコ内コラボ일때만 버튼 나오게


// 콜라보 멤버 문자열을 div로 감싸 표현하는 함수
function replaceCollabomemberString(a) {
  // jQuery를 사용하여 NodeList를 가져오기 (hidden된 input 자체를 가져와야함)
  let collabomemberStrings = a;
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

// 미분류 문자를 null로 바꾸는 함수
function replaceNull() {
  $(".collaboMember2").each(function () {
    let textInDiv = $(this);
    console.log(textInDiv.text());
    if (textInDiv.text() == "未分類") {
      textInDiv.text("");
    }
  });
}




