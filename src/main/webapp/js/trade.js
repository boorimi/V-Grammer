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
