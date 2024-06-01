function announcementInsert() {
  let ok = confirm("작성하시겠습니까?");
  if (ok) {
    document.getElementById("insertForm").submit();
  }
}
function announcementCancleInsert() {
  let ok = confirm("글쓰기를 취소 하시겠습니까?");
  if (ok) {
    location.href = "게시판메인페이지";
  }
}
