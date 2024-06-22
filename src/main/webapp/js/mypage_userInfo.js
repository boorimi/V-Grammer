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

	let changeOK = false;
	let nickNameLengthOK = false;

	// 닉네임 입력 필드에서 포커스가 벗어날 때 유효성 검사 실행
	$("#userInfo-nickname-input").blur(function() {
		if (validateNicknameLength()) { //닉네임 글자수 검사가 true일 때 중복검사를 실행
			validateNicknameDuplicate();
		} else {
			changeOK = false; //검사 실패 시 form 제출 조건을 만족하지 않음
		}
	});

	// 닉네임 글자수 검사 함수
	function validateNicknameLength() {
		const inputNickName = $('#userInfo-nickname-input').val();
		const checkResult = $('#check-result');

		// 닉네임 유효
		if (inputNickName.length >= 2 && inputNickName.length <= 30) {
			nickNameLengthOK = true;
		} else { // 닉네임 글자수에 문제 있을 때
			nickNameLengthOK = false;
			checkResult.css('color', 'red');
			if (inputNickName.length === 0) {
				$('#check-result').text('ニックネームを入力して下さい');
			} else {
				$('#check-result').text('ニックネームは2~30文字でお願いします');
			}
		}
		return nickNameLengthOK;
	}

	// 닉네임 중복검사
	function validateNicknameDuplicate() {
		const inputNickName = $('#userInfo-nickname-input').val();
		const checkResult = $("#check-result");
		return $.ajax({
			url: 'RegisterC',
			data: { inputNickName },
			dataType: 'json'
		}).done(function(res) {
			console.log("userInfo input닉네임: " + inputNickName);
			console.log("DB의 일치하는 닉네임 수: " + res);

			if (res == 0) {
				checkResult.text('このニックネームは使用可能です');
				checkResult.css('color', 'blue');
				/*$("#userInfo-nickname-input").css('border', '1px solid blue');*/
				changeOK = true;
			} else {
				checkResult.text('このニックネームは使用中です');
				checkResult.css('color', 'red');
				/*$("#userInfo-nickname-input").css('border', '1px solid red');*/
				changeOK = false;
			}
		});
	}

	// 폼 제출 시 유효성 검사 결과에 따라 제출 여부 결정

	$('#change-nickname-button').on('click', function(event) {
		event.preventDefault(); // 기본 버튼 동작을 막음
		event.stopPropagation(); //이벤트 전파 방지

		if (validateNicknameLength()) {
			validateNicknameDuplicate().done(function() {
				if (changeOK) {

					document.getElementById('nickname-form').submit(); // 폼 제출
					console.log("폼 제출됨");

				} else {
					alert('入力を確かめて下さい。');
					console.log("닉네임 중복 통과 못함");
				}
			});
		} else {
			alert('入力を確かめて下さい。');
			console.log("닉네임 길이 오류");
		}
	});





	//닉네임 변경
	$('#change-nickname-button').click(function(event) {
		event.preventDefault(); // 기본 폼 제출 동작 방지
		console.log("닉네임 변경 js 실행");
		const inputNickname = $('#userInfo-nickname-input').val();

		if (changeOK == true) {
			if (confirm("「" + inputNickname + "」でいいの？")) {
				$.ajax({
					url: 'UserInfoC', // 서버의 URL
					type: 'POST',
					data: {
						inputNickname: inputNickname
					},
					success: function(response) {
						console.log(inputNickname + " 으로 닉네임 변경 완료");
						alert("ニックネーム変更完了だよ");
						window.location.reload(); // 페이지 새로고침


					},
					error: function(xhr, status, error) {
						// 에러 시 처리
						console.error('AJAX 요청 실패:', status, error);
						alert('서버와의 통신 중 오류가 발생했습니다.');
					}
				});
			}else{
				console.log("입력 재확인 취소 시");
				 $('#userInfo-nickname-input').val('').focus();
			}
		}else{
			console.log("입력값이 옳지 않을 때");
			$('#userInfo-nickname-input').val('').focus();
		}
	});


	//이미지 변경
	$('#change-img-button').click(function(event) {
		event.preventDefalut();
		console.log("프로필이미지 변경 js 실행");
		const inputImg = $('#userInfo-img-input').val();

		$.ajax({
			url: 'UserInfo_ProfileImgC',
			type: 'POST',
			data: { inputImg: inputImg },
			success: function(response) {
				console.log("사진변경");
				alert("사진변경 완료");
				window.location.reload();
			},
			error: function(xhr, status, error) {
				// 에러 시 처리
				console.error('AJAX 요청 실패:', status, error);
				alert('서버와의 통신 중 오류가 발생했습니다.');
			}


		})

	})

	//인풋창 
	$('.form-element-input').change(function() {
		if ($(this).val()) {
			$(this).addClass('hasValue');
		} else {
			$(this).removeClass('hasValue');
		}
	});







});