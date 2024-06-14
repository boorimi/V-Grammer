function openTradePage(id) {
  if (id != null) {
    location.href = "TradePage?p=1";
  } else {
    alert("로그인 후 이용 가능합니다.");
  }
}