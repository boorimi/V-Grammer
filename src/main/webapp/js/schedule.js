$(document).ready(function() {
	const $openButton = $('.schedule-insert-detail-button');
	const $insertcontainer = $('.schedule-insert-inner-container');
	const $insertButton = $('.schedule-insert-button');

	// 로그인 한 사람만 인서트 디브 볼 수 있도록.
	$openButton.on('click', function() {
		if ($openButton.val() !== null && $openButton.val() !== "") {
			$insertcontainer.slideToggle();
		} else {
			alert('ログインが必要です！');
		}
	});


	// 입력 안하면 입력 알럿
	$insertButton.on('click', function() {
		let formData = [];

		if ($('#schedule-member').val() === "") {
			alert('メンバーをチェックください！');
		} if ($('#schedule-date').val() === "") {
			alert('日付をチェックください！');
		} if ($('#schedule-time').val() === "") {
			alert('タイムをチェックください！');
		} if ($('#schedule-title').val() === "") {
			alert('タイトルをチェックください！');
		}

		$('.schedule-insert-box').each(function() {
			let member = $(this).find($('#schedule-member')).val();
			let date = $(this).find($('#schedule-date')).val();
			let time = $(this).find($('#schedule-time')).val();
			let title = $(this).find($('#schedule-title')).val();

			if (member !== 0 && date !== "" && time !== "" && title !== "") {
				formData.push({
					s_member: member,
					s_date: date,
					s_time: time,
					s_title: title
				});
			}

		});


	});
});
