$(document).ready(function() {
	const $memberList = $('.member-memberList');
	const $memberImgs = $('.member-backgroundImg');
	const $open = $('.member-detail-open');
	const $close = $('.member-detail-close');
	const $listContainer = $('.member-memberList-container');
	const $detailbox = $('.member-detail');


	$memberImgs.eq(0).addClass('show');
	$memberList.eq(0).addClass('show');
	let idx = 0;

	// 멤버별 퍼스널컬러 세팅
	let colors = {
		1: ['linear-gradient(to right, lightpink, rgba(255, 182, 193, 0)', 'rgba(255, 192, 203, 0.2)'],
		2: ['linear-gradient(to right, #A5CDEC, rgba(165, 205, 236, 0)', 'rgba(165, 205, 236, 0.2)'],
		3: ['linear-gradient(to right, #CDB8FF, rgba(205, 184, 255, 0)', 'rgba(205, 184, 255, 0.2)'],
		4: ['linear-gradient(to right, #C25B7C, rgba(194, 91, 124, 0)', 'rgba(194, 91, 124, 0.2)'],
		5: ['linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)', 'rgba(255, 214, 212, 0.2)'],
		6: ['linear-gradient(to right, #8BEC97, rgba(139, 236, 151, 0)', 'rgba(139, 236, 151, 0.2)'],
		7: ['linear-gradient(to right, #D6BAEB, rgba(214, 186, 235, 0)', 'rgba(214, 186, 235, 0.2)'],
		8: ['linear-gradient(to right, #FFB9BB, rgba(255, 185, 187, 0)', 'rgba(255, 185, 187, 0.2)'],
		9: ['linear-gradient(to right, #BFD2EE, rgba(191, 210, 238, 0)', 'rgba(191, 210, 238, 0.2)'],
		10: ['linear-gradient(to right, #B7F1FF, rgba(183, 241, 255, 0)', 'rgba(183, 241, 255, 0.2)'],
		11: ['linear-gradient(to right, #D1B7A4, rgba(209, 183, 164, 0)', 'rgba(73, 73, 84, 0.2)'],
		12: ['linear-gradient(to right, #FFE089, rgba(255, 224, 137, 0)', 'rgba(255, 224, 137, 0.2)'],
		13: ['linear-gradient(to right, #CF80E0, rgba(207, 128, 224, 0)', 'rgba(207, 128, 224, 0.2)'],
		14: ['linear-gradient(to right, #FFA658, rgba(255, 166, 88, 0)', 'rgba(255, 166, 88, 0.2)'],
		15: ['linear-gradient(to right, #BDDE87, rgba(189, 222, 135, 0)', 'rgba(189, 222, 135, 0.2)'],
		16: ['linear-gradient(to right, #C4BEC9, rgba(184, 178, 189, 0)', 'rgba(184, 178, 189, 0.2)'],
		17: ['linear-gradient(to right, #D4485F, rgba(212, 72, 95, 0)', 'rgba(212, 72, 95, 0.2)'],
		18: ['linear-gradient(to right, #DE617E, rgba(222, 97, 126, 0)', 'rgba(222, 97, 126, 0.2)'],
		19: ['linear-gradient(to right, #77788F, rgba(119, 120, 143, 0)', 'rgba(103, 105, 128, 0.2)'],
		20: ['linear-gradient(to right, #B2E2F7, rgba(178, 226, 247, 0)', 'rgba(178, 226, 247, 0.2)'],
		21: ['linear-gradient(to right, #BF8ADE, rgba(191, 138, 222, 0)', 'rgba(191, 138, 222, 0.2)'],
		22: ['linear-gradient(to right, #CDEDFF, rgba(205, 237, 255, 0)', 'rgba(205, 237, 255, 0.2)'],
		23: ['linear-gradient(to right, #C5C2C3, rgba(197, 194, 195, 0)', 'rgba(197, 194, 195, 0.2)'],
		24: ['linear-gradient(to right, #FAC6D3, rgba(250, 198, 211, 0)', 'rgba(250, 198, 211, 0.2)'],
		25: ['linear-gradient(to right, #BEC48B, rgba(190, 196, 139, 0)', 'rgba(190, 196, 139, 0.2)']
	}

	// 첫페이지 인덱스 pk값 1번 멤버 컬러로 노출되도록.
	$listContainer.css('background', colors[1][0]);
	$detailbox.css('background-color', colors[1][1]);

	// 멤버리스트 클릭 시 발생되는 이벤트 함수
	$memberList.each(function() {
		$(this).on('click', function() {
			$memberImgs.removeClass('show');
			$memberList.removeClass('show');
			idx = $(this).attr('id');
			//			console.log(idx);
			$memberImgs.eq(idx - 1).addClass('show');
			$memberList.eq(idx - 1).addClass('show');
			
			
			// 22~25 배경이미지 컬러 그라데이션 넣어주기 위한 if문
			if($(this).attr('id')==='22'){
				$('.member-img-container').css('background-color', '#FCEDEF')
			} else if($(this).attr('id')==='23'){
				$('.member-img-container').css('background-color', '#C1C1C5')
			} else if($(this).attr('id')==='24'){
				$('.member-img-container').css('background-color', '#D4D0DF')
			} else if($(this).attr('id')==='25'){
				$('.member-img-container').css('background-color', '#F4F4E4')
			}  else if($(this).attr('id')==='17'){
				$('.member-img-container').css('background-color', '#FDF9FA')
			} else {
				$('.member-img-container').css('background-color', 'rgb(0,0,0,0)')
			}
//			if($(this).attr('id')==='22'){
//				$('.member-img-box').css('background-color', '#FCEDEF')
//			} else if($(this).attr('id')==='23'){
//				$('.member-img-box').css('background-color', '#C1C1C5')
//			} else if($(this).attr('id')==='24'){
//				$('.member-img-box').css('background-color', '#D4D0DF')
//			} else if($(this).attr('id')==='25'){
//				$('.member-img-box').css('background-color', '#F4F4E4')
//			}  else if($(this).attr('id')==='17'){
//				$('.member-img-box').css('background-color', '#FBEFF1')
//			} else {
//				$('.member-img-box').css('background-color', 'rgb(0,0,0,0)')
//			}

			// 클릭 시 마다 멤버 리스트와 디테일 박스 색상 변경
			function setColor(idx, $listContainer, $detailbox, colors) {
				$listContainer.css('background', colors[idx][0]);
				$detailbox.css('background-color', colors[idx][1]);
			}
			setColor(idx, $listContainer, $detailbox, colors);
		});
	});

	// 더보기 div 감춤
	$('.member-detail-container2').hide();

	// 더보기 클릭 시 더보기 div 노출
	$open.on('click', function() {

		// 버튼을 누르면 컨테이너가 늘어나는 transition 걸고,
		// transition 효과 끝나면 사라지게?
		// 아니면 버튼 누르면 바로 사라지고
		// 컨테이너 2가 transition 효과 반영되면서 올라오게??
		$('.member-detail-container').hide();
		$('.member-detail-container2').fadeIn(1000);
	});

	// 접기 클릭 시 더보기 div 감춤
	$close.on('click', function() {
		$('.member-detail-container2').hide();
		$('.member-detail-container').fadeIn(1000);
	});

	// 멤버 리스트 클릭 시 더보기 다시 접힘
	$memberList.on('click', function() {
		$('.member-detail-container2').hide();
		$('.member-detail-container').show();
	});

	// 멤버이름 클릭시 음성재생 코드
	const playAudioDivs = document.querySelectorAll(".member-memberList");
	let currentAudio = null;

	playAudioDivs.forEach(div => {
		div.addEventListener("click", function() {
			// 현재 재생 중인 오디오가 있으면 중지
			if (currentAudio && !currentAudio.paused) {
				currentAudio.pause();
				currentAudio.currentTime = 0; // 오디오를 처음으로 되돌림
			}

			// 클릭한 div에 연결된 오디오 재생
			const audioId = 'audio-' + this.id;
			const audio = document.getElementById(audioId);
			audio.play();

			// 현재 재생 중인 오디오 업데이트
			currentAudio = audio;
		});
	});

});




