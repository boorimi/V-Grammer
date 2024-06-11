function tradeInsert() {
  let ok = confirm("作成しますか？");
  const checkboxes = document.querySelectorAll(
    'input[name="goodsCategory"]:checked'
  );
  if (ok) {
    if (checkboxes.length === 0) {
      alert("最低１個以上のチェックボックスを選択してください。");
      return false; // 폼 제출을 중단
    } else {
      document.getElementById("insertForm").submit();
    }
  }
}
function tradeCancleInsert() {
  let ok = confirm("作成をキャンセルしますか？");
  if (ok) {
    location.href = "trade";
  }
}
function tradeUpdate() {
  let ok = confirm("修正しますか？");
  if (ok) {
    document.getElementById("updateForm").submit();
  }
}
function tradeDelete(pk) {
  let ok = confirm("削除しますか？");
  if (ok) {
    location.href = "DeleteTrade?no=" + pk;
  }
}

function tradeCommentsInsert() {
  let ok = confirm("作成しますか？");
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
  });
});

$(function () {
  $(".trade-openCategorys").click(function () {
    let post = $(this).closest(".trade-container");
    let commentsDiv = post.find(".trade-category");

    commentsDiv.each(function () {
      $(this).toggle();
    });
  });
});


