$(document).ready(function() {
	const $openButton = $('.schedule-insert-detail-button');
	const $insertcontainer = $('.schedule-insert-inner-container');
	const $insertButton = $('schedule-insert-button');
	
	// 로그인 한 사람만 인서트 디브 볼 수 있도록.
	$openButton.on('click', function() {
		if($openButton.val() !== null && $openButton.val() !== ""){
		$insertcontainer.slideToggle();
		} else{
			alert('ログインが必要です！');
		}
	});
	
	
	
	
})