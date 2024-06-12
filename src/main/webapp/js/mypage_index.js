
$(document).ready(function() {
	$("#mypage-menu-userInfo").click(function() {
		$.ajax({
			url: "account/mypage/mypage_userInfo.jsp",
			type: "GET",
			success: function(data) {
				console.log("AJAX 요청 성공");
				console.log(data);
				$(".mypage-jsp-section").empty();
				console.log("지우는거까지는 됨");
				$(".mypage-jsp-section").append(data);
			},
			error: function() {
				alert("Error loading page");
			}
		});
	});
});
