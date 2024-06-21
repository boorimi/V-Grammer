function openTradePage(id) {
  if (id != null) {
    location.href = "TradePage?p=1";
  } else {
    alert("로그인 후 이용 가능합니다.");
  }
}

$(function(){
if(location.href == 'http://localhost/V-Grammer/RegisterC'){
	console.log($('footer').css("margin-top", "0px"));
}
	
});
