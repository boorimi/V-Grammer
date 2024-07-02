function openTradePage(id) {
  if (id != null) {
    location.href = "TradePage?p=1";
  } else {
    alert("ログインが必要です。");
  }
}

$(function(){
if(location.href == 'http://localhost/V-Grammer/RegisterC'){
	console.log($('footer').css("margin-top", "0px"));
}
	
});
