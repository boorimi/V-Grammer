$(document).ready(function () {
  $(".trade-category").on("change", ".goodsCategory", function (event) {
    let category = $(".goodsCategory:checked").last().val(); // 마지막으로 체크된 값
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
        alert("Error retrieving information.");
      },
    });
  }); // 100ms 딜레이 추가
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
      "\n================================\n" +
      text +
      "\n================================\n";
  }
  let currentText = $("#trade-textarea").val();
  let newText = currentText + text2;

  $("#trade-textarea").val(newText);
}
