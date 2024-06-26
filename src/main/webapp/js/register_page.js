$(document).ready(function() {

	let registerOK = false;
	let nickNameLengthOK = false;

	// 닉네임 입력 필드에서 포커스가 벗어날 때 유효성 검사 실행
	$("#register-nickname-input").blur(function() {
		if (validateNicknameLength()) { //닉네임 글자수 검사가 true일 때 중복검사를 실행
			validateNicknameDuplicate();
		} else {
			registerOK = false; //검사 실패 시 form 제출 조건을 만족하지 않음
		}
	});

	// 닉네임 글자수 검사 함수
	function validateNicknameLength() {
		const inputNickName = $('#register-nickname-input').val();
		const checkResult = $('#check-result');

		// 닉네임 유효
		if (inputNickName.length >= 2 && inputNickName.length <= 30 && !/\s/.test(inputNickName)) {
			nickNameLengthOK = true;
		} else { // 닉네임 글자수에 문제 있을 때
			nickNameLengthOK = false;
			checkResult.css('color', 'red');
			if (inputNickName.length === 0) {
				$('#check-result').text('ニックネームを入力して下さい');
			} else {
				$('#check-result').html('ニックネームは2~30文字、<br>空白なしでお願いします');
			}
		}
		return nickNameLengthOK;
	}

	// 닉네임 중복검사
	function validateNicknameDuplicate() {
		const inputNickName = $('#register-nickname-input').val();
		const checkResult = $("#check-result");
		return $.ajax({
			url: 'RegisterC',
			data: { inputNickName },
			dataType: 'json'
		}).done(function(res) {
			console.log("input닉네임: " + inputNickName);
			console.log("DB의 일치하는 닉네임 수: " + res);

			if (res == 0) {
				checkResult.text('このニックネームは使用可能です');
				checkResult.css('color', 'blue');
				/*$("#register-nickname-input").css('border', '1px solid blue');*/
				registerOK = true;
			} else {
				checkResult.text('このニックネームは使用中です');
				checkResult.css('color', 'red');
				/*$("#register-nickname-input").css('border', '1px solid red');*/
				registerOK = false;
			}
		});
	}

	// 폼 제출 시 유효성 검사 결과에 따라 제출 여부 결정
	 $('#register-submit-button').on('click', function(event) {
        event.preventDefault(); // 기본 버튼 동작을 막음
		event.stopPropagation();
		
        if (validateNicknameLength()) {
            validateNicknameDuplicate().done(function() {
                if (registerOK) {
                    if (confirm('この情報で登録しますか？')) {
                        document.getElementById('register-form').submit(); // 폼 제출
                        console.log("폼 제출됨");
                    }
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


	//인풋창 
	$('.form-element-input').change(function() {
		if ($(this).val()) {
			$(this).addClass('hasValue');
		} else {
			$(this).removeClass('hasValue');
		}
	});


	//배경화면 슬라이드
	const images = [
		'haco_img/background/background_akai_akame.png',
		'haco_img/background/background_amami_kurine.png',
		'haco_img/background/background_ayasaka_kiho.png',
		'haco_img/background/background_daten_shisuta.png',
		'haco_img/background/background_ginkai_mao.png',
		'haco_img/background/background_hoshinone_koron.png',
		'haco_img/background/background_kanami_yuyu.png',
		'haco_img/background/background_kato_cherie.png',
		'haco_img/background/background_kohinata_chiko.png',
		'haco_img/background/background_meido_shirone.png',
		'haco_img/background/background_mira_lupus.png',
		'haco_img/background/background_nanairo_temari.png',
		'haco_img/background/background_ootori_hakana.png',
		'haco_img/background/background_perupo.png',
		'haco_img/background/background_rin_garnet.png',
		'haco_img/background/background_shirasuna_tsuna.png',
		'haco_img/background/background_shizukawa_nanoka.png',
		'haco_img/background/background_sophie_rose.png',
		'haco_img/background/background_suimori_atori.png',
		'haco_img/background/background_tachibana_shiena.png',
		'haco_img/background/background_tamanoi_monaka.png',
		'haco_img/background/background_tanaka_ryuko.png',
		'haco_img/background/background_usaki_yotsunoha.png',
		'haco_img/background/background_yamane_koi.png',
		'haco_img/background/background_yorumu_ruku.png'
	];
	let currentIndex = 0;

	  function changeBackgroundImage() {
        $('#register-section').fadeOut(3000, function() {
            currentIndex = (currentIndex + 1) % images.length;
            $('#register-section').css('background-image', `url('${images[currentIndex]}')`);
            $('#register-section').fadeIn(1000);
        });
    }
	

	setInterval(changeBackgroundImage, 10); // 5000ms = 5초


});