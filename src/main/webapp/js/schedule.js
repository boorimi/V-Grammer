$(document).ready(function() {

	$(function() {
		let data = $("#weekJSON").text();
		data = JSON.parse(data);
		console.log(data[0])
		let content = `<div style="border: 1px solid blue;">
						</div>`;

		for (var i = 1; i < 8; i++) {
			let sData = $(".day" + i + "-content .s-data");

			$(sData).each((idx, s) => {
				let content2 = $(content).clone();
				let start = parseInt($(s).attr("start"));
				let end = parseInt($(s).attr("end"));

				$.each(data[i - 1], (j, obj) => {
					if (start <= obj.intTime && obj.intTime < end) {
						$(content2).text(obj.m_name + " " + obj.s_time);
						$(s).append(content2);
					}
				});

			});
		};
	})

	// 인서트 js
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

	// 타이틀 입력 인풋 엔터 방지
	$('#schedule-title').keydown(function(event) {
		if (event.keyCode === 13) {
			event.preventDefault();
		};
	});

	// 입력 안하면 입력 알럿
	$insertButton.on('click', function() {
		if ($('#schedule-member').val() === "999") {
			alert('メンバーをチェックください！');
			return false;
		} if ($('#schedule-date').val() === "") {
			alert('日付をチェックください！');
			return false;
		} if ($('#schedule-time').val() === "") {
			alert('タイムをチェックください！');
			return false;
		} if ($('#schedule-title').val() === "") {
			alert('タイトルをチェックください！');
			return false;
		}
	});


});
