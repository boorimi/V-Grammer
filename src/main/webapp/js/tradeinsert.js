$(document).ready(function () {
  $(".trade-category").on("change", ".goodsCategory", function (event) {
    if ($(this).is(":checked")) { // 체크박스가 체크되었을 때만 실행
      let category = $(this).val(); // 현재 체크된 값
      let pk = $("#goods-pk").val();
      console.log(category);
      $.ajax({
        url: "InsertGoodsValueC",
        method: "POST",
        data: { category, pk },
        dataType: "json",
        success: function (res) {
          writeHtmlCode(res);
        },
        error: function () {
          alert("정보를 가져오는 중 오류가 발생했습니다.");
        },
      });
    }
  });
});

function writeHtmlCode(res) {
  let text = "";
  let text2 = "";
  res.forEach(function (item) {
    text += item.category + "\t" + item.name + "\t" + item.count + "個\n";
    console.log(item.category);
  });
  if (text != "") {
    text2 =
      "================================\n" +
      text +
      "================================\n";
  }
  let currentText = $("#trade-textarea").val();
  let newText = currentText + text2;

  $("#trade-textarea").val(newText);
}
