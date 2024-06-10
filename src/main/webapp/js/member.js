// 이미지 바뀌는 js
//const memberList = document.querySelectorAll('.member-memberList');
//const memberImgs = document.querySelectorAll('.member-backgroundImg');
//memberImgs[0].classList.add('show'); 
//let idx = 0;
//memberList.forEach((member) => {
//	member.addEventListener("click", (e) => {
//
//		memberImgs.forEach((img) => {
//			img.classList.remove('show');
//		});
//
//		idx = member.getAttribute('id');
////		console.log(memberImgs[idx-1]) 
//		memberImgs[idx-1].classList.add('show');
//
//	});
//});

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
	$listContainer.css('background', 'linear-gradient(to right, lightpink, rgba(255, 182, 193, 0)');
	$detailbox.css('background-color', 'rgba(255, 192, 203, 0.353)');

	$memberList.each(function() {
		$(this).on('click', function() {
			$memberImgs.removeClass('show');
			$memberList.removeClass('show');
			idx = $(this).attr('id');
			console.log(idx);
			$memberImgs.eq(idx - 1).addClass('show');
			$memberList.eq(idx - 1).addClass('show');
	
			// 클릭 시 마다 멤버 리스트와 디테일 박스 색상 변경 코드
			if(idx == 1){
				$listContainer.css('background', 'linear-gradient(to right, lightpink, rgba(255, 182, 193, 0)');
				$detailbox.css('background-color', 'rgba(255, 192, 203, 0.3)');
			} else if (idx == 2){
				$listContainer.css('background', 'linear-gradient(to right, #A5CDEC, rgba(165, 205, 236, 0)');
				$detailbox.css('background-color', 'rgba(165, 205, 236, 0.3)');
			} else if (idx == 3){
				$listContainer.css('background', 'linear-gradient(to right, #CDB8FF, rgba(205, 184, 255, 0)');
				$detailbox.css('background-color', 'rgba(205, 184, 255, 0.3)');
			} else if (idx == 4){
				$listContainer.css('background', 'linear-gradient(to right, #C25B7C, rgba(194, 91, 124, 0)');
				$detailbox.css('background-color', 'rgba(194, 91, 124, 0.3)');
			} else if (idx == 5){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} else if (idx == 6){
				$listContainer.css('background', 'linear-gradient(to right, #8BEC97, rgba(139, 236, 151, 0)');
				$detailbox.css('background-color', 'rgba(139, 236, 151, 0.3)');
			} else if (idx == 7){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} else if (idx == 8){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} else if (idx == 9){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} else if (idx == 10){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} else if (idx == 11){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} else if (idx == 12){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} else if (idx == 13){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} else if (idx == 14){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} else if (idx == 15){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} else if (idx == 16){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} else if (idx == 17){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} else if (idx == 18){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} else if (idx == 19){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} else if (idx == 20){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} else if (idx == 21){
				$listContainer.css('background', 'linear-gradient(to right, #FFD6D4, rgba(255, 214, 212, 0)');
				$detailbox.css('background-color', 'rgba(255, 214, 212, 0.3)');
			} 
			
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
		$('.member-detail-container2').show();
	});

	// 접기 클릭 시 더보기 div 감춤
	$close.on('click', function() {
		$('.member-detail-container2').hide();
		$('.member-detail-container').show();
	});

	// 멤버 리스트 클릭 시 더보기 다시 접힘
	$memberList.on('click', function() {
		$('.member-detail-container2').hide();
		$('.member-detail-container').show();
	});
	// 멤버 리스트 클릭 시 퍼스널컬러에 맞춰 배경색 변경



});




