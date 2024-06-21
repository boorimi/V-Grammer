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
	});

	updateButton.addEventListener('click', () => {
		console.log('updateButton');
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
					let sData = $("<div></div>").addClass("s-data").attr('data-m-pk', obj.s_m_pk)
						.attr('data-s-pk', obj.s_pk).attr('data-s-time', obj.s_time).attr('data-m-name', obj.m_name)
						.attr('data-s-title', obj.s_title).attr('data-i-icon', obj.i_icon);

					let sTime = $("<div></div>").text(obj.s_time);
					let mName = $("<div></div>").text(obj.m_name);
					let timeNameDiv = $("<div>").addClass("time-name");
					timeNameDiv.append(sTime, mName);

					let imgSrc = "haco_img/icon/" + obj.i_icon;
					let imgEl = $("<img>").attr("src", imgSrc);
					let sIcon = $("<div>").addClass("s-icon").append(imgEl);

					let sInnerBox = $("<div>").addClass("s-inner-box").append(sIcon).append(timeNameDiv);

					//            						console.log(obj.s_pk)

					let sTitle = $("<div>").addClass("s-data-title");

					let titleDetail = `<div class="s-title-text-box">
										<div>${obj.s_title}</div>
										</div>`;

					sTitle.append(titleDetail);
					sData.append(sInnerBox).append(sTitle);
					//						content2.append(sData);
					// 디브 생성 후 컬러 세팅함수 호출
					setBackgroundColor(sData, obj.s_m_pk);
					$(s).append(sData);
				}
			});
		});
	}

	let sPk = null;
	$('.s-data').click(function() {
		$('.modal-a-box').css('display', 'block');
		$('.modal-b-box').css('display', 'none');

		modal.showModal();
		console.log(this.dataset);


		sPk = this.dataset.sPk;
		console.log(sPk);

		modal.querySelector("#time").innerText = this.dataset.sTime;
		modal.querySelector("#name").innerText = this.dataset.mName;
		modal.querySelector("#title").innerText = this.dataset.sTitle;
		// Modal Delete Click
		// 로그인 했을 때만 가능하도록 세션값 벨류에 심어둠.
		// 일단 기능부터 만들고 수정 필요
	});
	$('#deleteButton').click(function() {
		// 스케줄의 pk
		console.log('딜리트 클릭 콘솔 : ' + sPk);
		if ($('#deleteButton').val() !== null && $('#deleteButton').val() !== "") {
			if (confirm('本当に削除しますか？')) {
				location.href = "DeleteScheduleC?sPk=" + sPk;
			}
		} else {
			alert("ログインが必要です！");
		}
	});
	// Modal Update Click
	$('#updateButton').click(function() {
		console.log('업데이트 클릭 콘솔 : ' + sPk);
		if ($('#deleteButton').val() !== null && $('#deleteButton').val() !== "") {
			$('.modal-a-box').css('display', 'none');
			$('.modal-b-box').css('display', 'block');
		} else {
			alert("ログインが必要です！");
		}
	});
	$('#s-update-button').click(function() {
		console.log('등록 버튼 클릭 콘솔 : ' + sPk);
		let date = $('#s-update-date').val();
		let time = $('#s-update-time').val();
		let title = $('#s-update-title').val();

		location.href = "UpdateScheduleC?sPk=" + sPk + "&s_date=" + date + "&s_time=" + time
			+ "&s_title=" + title;
	});


	// 인서트 js
	for (let i = 0; i < 7; i++) {
		let insertInputList =
			`<div class="s-input-box">
				<div class="s-input-date">
					<input name="s_date" type="date" class="s-input-date1"/>
				</div>
				<div class="s-input-time">
					<input name="s_time" type="time" class="s-input-time1"/>
				</div>
				<div class="s-input-title">
					<input name="s_title" class="s-input-title1" placeholder="配信タイトル" />
				</div>
			</div>`;

		$('.s-input-container').append(insertInputList);
	}

	const $openButton = $("#schedule-insert-detail-button");
	const $insertcontainer = $(".s-insert-content-a-box");
	const $insertButton = $("#s-insert-button");
	$openButton.text('登録');

	// 로그인 한 사람만 인서트 디브 볼 수 있도록.
	$openButton.on("click", function() {
		if ($openButton.val() !== null && $openButton.val() !== "") {
			$insertcontainer.slideToggle(function() {
				if ($insertcontainer.is(":visible")) {
					$openButton.text('閉じる');
				} else {
					$openButton.text('登録');
				}
			});
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
	// 수정중

	let activerow;
	$(".s-input-date1").focus(function (){
		console.log($(this));
		activerow = $(this).closest('.s-input-box');
	});


	$insertButton.on("click", function() {
		console.log($(activerow).html());
		console.log('나와라얍');
		const list = $("#s-member-list").val();
		const date1 = $(activerow).find($(".s-input-date1")).val();
		const time1 = $(activerow).find($(".s-input-time1")).val();
		const title1 = $(activerow).find($(".s-input-title1")).val();
		console.log(list)
		console.log(date1)
		console.log(time1)
		console.log(title1)
		if (!list) {
			alert("メンバーをチェックください！");
			return false;
		}
		if (!date1) {
			alert("日付をチェックください！");
			return false;
		}
		if (!time1) {
			alert("タイムをチェックください！");
			return false;
		}
		if (!title1) {
			alert("タイトルをチェックください！");
			return false;
		}


		for (i = 0; i < 7; i++) {
			if ($("#s-insert-date").val() === "") {
				alert("日付をチェックください！");
				return false;
			}
			if ($("#s-insert-time").val() === "") {
				alert("タイムをチェックください！");
				return false;
			}
			if ($("#s-insert-title").val() === "") {
				alert("タイトルをチェックください！");
				return false;
			}

		}
	});





});