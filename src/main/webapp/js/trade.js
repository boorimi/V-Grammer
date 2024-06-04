function tradeInsert() {
  let ok = confirm("작성하시겠습니까?");
  if (ok) {
    document.getElementById("insertForm").submit();
  }
}
function tradeCancleInsert() {
  let ok = confirm("글쓰기를 취소 하시겠습니까?");
  if (ok) {
    location.href = "trade";
  }
}
function tradeUpdate() {
  let ok = confirm("수정하시겠습니까?");
  if (ok) {
    document.getElementById("updateForm").submit();
  }
}
function tradeDelete(pk) {
  let ok = confirm("삭제하시겠습니까?");
  if (ok) {
    location.href = "DeleteTrade?no=" + pk;
  }
}

function tradeCommentsInsert() {
  let ok = confirm("작성하시겠습니까?");
  if (ok) {
    document.getElementById("insertTradeCommentsForm").submit();
  }
}
$(function () {
  $(".trade-openComments").click(function () {
    let post = $(this).closest(".trade-content");
    let commentsDiv = post.find(".trade-comments");

    commentsDiv.each(function () {
      $(this).toggle();
    });

    // 버튼 텍스트 변경
    if ($(this).text() === "댓글보기") {
      $(this).text("댓글닫기");
    } else {
      $(this).text("댓글보기");
    }
  });
});
