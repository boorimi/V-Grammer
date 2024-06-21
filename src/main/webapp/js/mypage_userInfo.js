$(document).ready(function() {
	$('#retire-button').click(function(event) {
		event.preventDefault();
		let retireOK = confirm("本当に退会しますか?(会員情報は復旧不可能です)");

		if (retireOK == true) {
			let userId = this.value;
			console.log(userId);
			$.ajax({
				url: 'UserInfoC',
				type: 'GET', // 요청 방식 (GET 방식으로 수정)
				data: { userId: userId },
				dataType: 'json', // 서버 응답을 JSON으로 받음
				success: function(response) {
					if (response.status === "success") {
						alert(response.message); // 성공 메시지 팝업창 표시
						window.location.href = "HC"; // 페이지 리다이렉트
					} else {
						alert(response.message); // 실패 메시지 팝업창 표시
					}
				},
				error: function(error) {
					console.log('에러 발생', error);
					alert('삭제 요청 중 에러가 발생했습니다.');
				}
			});
		}
	});

	
	//인풋창 
	$('.form-element-input').change(function() {
		if ($(this).val()) {
			$(this).addClass('hasValue');
		} else {
			$(this).removeClass('hasValue');
		}
	});







});