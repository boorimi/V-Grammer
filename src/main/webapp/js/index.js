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
    }).done(function (resData) {
      for (let i = 0; i < resData.length; i++) {
        console.log(resData[i].a_title);
      }

      console.log(JSON.stringify(resData));
    });
  });
});
