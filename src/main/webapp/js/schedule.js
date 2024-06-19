$(document).ready(function() {

	const modal = document.getElementById('modal');
	const closeModalButton = document.getElementById('closeModalButton');
	const deleteButton = document.getElementById('deleteButton');
	const updateButton = document.getElementById('updateButton');
	console.log(modal);


	closeModalButton.addEventListener('click', () => {
		modal.close();
	});

	deleteButton.addEventListener('click', () => {
		console.log('deleteButton');
		modal.close();
	});

	updateButton.addEventListener('click', () => {
		console.log('updateButton');
		modal.close();
	});

	modal.addEventListener('click', (event) => {
		if (event.target === modal) {
			modal.close();
		}
	});


	// 페이지 열리면 오늘 날짜 탭이 바로 열리게 하는 코드
	let today = new Date().getDay();
	let todayId;

	switch (today) {
		case 0: todayId = "s-sun"; break;
		case 1: todayId = "s-mon"; break;
		case 2: todayId = "s-tue"; break;
		case 3: todayId = "s-wed"; break;
		case 4: todayId = "s-thu"; break;
		case 5: todayId = "s-fri"; break;
		case 6: todayId = "s-sat"; break;
	}
	$("#" + todayId).prop("checked", true);

	let timeColor = {
		"s-mon": 'lightpink', "s-tue": '#b7f1ff', "s-wed": '#d1b7a4', "s-thu": '#ffe089',
		"s-fri": '#cf80e0', "s-sat": '#ffa658', "s-sun": '#bdde87'
	};

	$('input[name="tab_item"]').change(function() {
		let dayOfWeek = $(this).attr('id');
		let backgroundColor = timeColor[dayOfWeek];
		$('.s-time').css('backgroundColor', backgroundColor);
	});

	// 초기 페이지 로드 시 요일에 맞게 s-time div 컬러 색상 세팅
	$('.s-time').css('backgroundColor', timeColor[todayId]);


	$(function() {
		// 멤버 pk에 맞는 컬러
		let memberColors = {
			1: "lightpink", 2: "#A5CDEC", 3: "#CDB8FF", 4: "#C25B7C",
			5: "#FFD6D4", 6: "#8BEC97", 7: "#D6BAEB", 8: "#FFB9BB",
			9: "#BFD2EE", 10: "#B7F1FF", 11: "#D1B7A4", 12: "#FFE089",
			13: "#CF80E0", 14: "#FFA658", 15: "#BDDE87", 16: "#C4BEC9",
			17: "#D4485F", 18: "#DE617E", 19: "#77788F", 20: "#B2E2F7",
			21: "#BF8ADE", 22: "#CDEDFF", 23: "#C5C2C3", 24: "#FAC6D3", 25: "#BEC48B"
		};

		// 디브 컬러 세팅 함수
		function setBackgroundColor(element, value) {
			let backgroundColor = memberColors[value];
			element.css('background-color', backgroundColor);
		}

		// 스케줄 맞는 시간대 박스에 집어넣는 코드
		let data = $("#weekJSON").text();
		data = JSON.parse(data);
		console.log(data[0]);

		for (var i = 1; i < 8; i++) {
			let sDataBox = $(".day" + i + "-content .s-data-box");

			$(sDataBox).each((idx, s) => {
				let start = parseInt($(s).attr("start"));
				let end = parseInt($(s).attr("end"));

				// 시간순으로 div 붙이기
				data[i - 1].sort((a, b) => a.intTime - b.intTime);

				$.each(data[i - 1], (j, obj) => {
					if (start <= obj.intTime && obj.intTime < end) {
						let sData = $("<div></div>").addClass("s-data").attr('data-m-pk', obj.s_m_pk).attr('data-s-pk', obj.s_pk).attr('data-s-time', obj.s_time).attr('data-m-name', obj.m_name);

						let sTime = $("<div></div>").text(obj.s_time);
						let mName = $("<div></div>").text(obj.m_name);

						//            						console.log(obj.s_pk)

						let sTitle = $("<div>").addClass("s-data-title");

						let titleDetail = `<div class="s-title-text-box">
										<div>◈タイトル◈</div>
										<div>${obj.s_title}</div>
										</div>`;

						sTitle.append(titleDetail);
						sData.append(sTime).append(mName).append(sTitle);


						//						content2.append(sData);
						// 디브 생성 후 컬러 세팅함수 호출
						setBackgroundColor(sData, obj.s_m_pk);
						$(s).append(sData);


					}
				});
			});





		}
			$('.s-data').click(function() {
				modal.showModal();
				console.log(this.dataset);
				modal.querySelector("#title").innerText = this.dataset.mName;
				modal.querySelector("#name").innerText = this.dataset.mPk;
				modal.querySelector("#time").innerText = this.dataset.sTime;
				let zzz = this.dataset.mName = 'aaaa'
				modal.querySelector("#title").innerText = zzz;
				
			})
	});

	// Delete JS

	//	let iddd= $(this).val();
	//	console.log(iddd);
	//	console.log($(this).data('data'));
	//	});


	// 인서트 js
	const $openButton = $(".schedule-insert-detail-button");
	const $insertcontainer = $(".schedule-insert-inner-container");
	const $insertButton = $(".schedule-insert-button");

	// 로그인 한 사람만 인서트 디브 볼 수 있도록.
	$openButton.on("click", function() {
		if ($openButton.val() !== null && $openButton.val() !== "") {
			$insertcontainer.slideToggle();
		} else {
			alert("ログインが必要です！");
		}
	});

	// 타이틀 입력 인풋 엔터 방지
	$("#schedule-title").keydown(function(event) {
		if (event.keyCode === 13) {
			event.preventDefault();
		}
	});

	// 입력 안하면 입력 알럿
	$insertButton.on("click", function() {
		if ($("#schedule-member").val() === "999") {
			alert("メンバーをチェックください！");
			return false;
		}
		if ($("#schedule-date").val() === "") {
			alert("日付をチェックください！");
			return false;
		}
		if ($("#schedule-time").val() === "") {
			alert("タイムをチェックください！");
			return false;
		}
		if ($("#schedule-title").val() === "") {
			alert("タイトルをチェックください！");
			return false;
		}
	});





});
