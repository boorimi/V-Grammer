function announcementInsert() {
  let ok = confirm("작성하시겠습니까?");
  if (ok) {
    document.getElementById("insertForm").submit();
  }
}
function announcementCancleInsert() {
  let ok = confirm("글쓰기를 취소 하시겠습니까?");
  if (ok) {
    location.href = "Announcement";
  }
}
function announcementUpdate() {
  let ok = confirm("수정하시겠습니까?");
  if (ok) {
    document.getElementById("updateForm").submit();
  }
}
function announcementDelete(pk) {
  let ok = confirm("삭제하시겠습니까?");
  if (ok) {
    location.href = "DeleteAnnouncement?no=" + pk;
  }
}
