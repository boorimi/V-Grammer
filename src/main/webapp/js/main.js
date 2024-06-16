$(document).ready(function() {
	// bxSlider 초기화
	var slider = $(".slider").bxSlider({
		mode: "horizontal", // 슬라이드 모드 설정 (수평으로)
		auto: false, // 자동으로 슬라이드 전환 여부
		controls: false, // 이전/다음 버튼을 표시하지 않음
		pager: false, // 페이지 버튼을 표시하지 않음
		slideMargin: 0, // 슬라이드 간의 여백 (픽셀)
		minSlides: 1, // 최소로 보여줄 슬라이드 수
		maxSlides: 1, // 최대로 보여줄 슬라이드 수
		moveSlides: 1, // 한 번에 슬라이드할 슬라이드 수
		slideWidth: 1280, // 슬라이드의 너비 (픽셀)
		adaptiveHeight: true, // 슬라이드 높이를 내용에 맞게 자동 조정
	});

	// 이전 버튼 클릭 이벤트
	$(".prev-button").click(function() {
		slider.goToPrevSlide();
		return false;
	});

	// 다음 버튼 클릭 이벤트
	$(".next-button").click(function() {
		slider.goToNextSlide();
		return false;
	});
	
	// 페이지가 로드되면 bx슬라이더의 보더를 없애는 코드
	$(".bx-wrapper").css("border", "0px");
	
});