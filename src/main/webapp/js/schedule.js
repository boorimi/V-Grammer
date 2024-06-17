$(document).ready(function() {

	$(function() {
		let data = $("#weekJSON").text();
		data = JSON.parse(data);
		console.log(data[0])
		let content = `<div class="s-data">
						</div>`;

		for (var i = 1; i < 8; i++) {
			let sDataBox = $(".day" + i + "-content .s-data-box");

			$(sDataBox).each((idx, s) => {
				let start = parseInt($(s).attr("start"));
				let end = parseInt($(s).attr("end"));
				
				// 시간순으로 div 붙이기
				data[i - 1].sort((a, b) => a.intTime - b.intTime);

				$.each(data[i - 1], (j, obj) => {
					if (start <= obj.intTime && obj.intTime < end) {
						
						let sData = $("<div></div>").addClass("s-data");
						
						let mName = $("<div></div>").text(obj.m_name);
						let sTime = $("<div></div>").text(obj.s_time);
						
						sData.append(sTime).append(mName);
						
//						content2.append(sData);
						$(s).append(sData);
						
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
