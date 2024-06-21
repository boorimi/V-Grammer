function tradeInsert() {
	let ok = confirm("作成しますか？");
	const checkboxes = document.querySelectorAll(
		'input[name="goodsCategory"]:checked'
	);
	if (ok) {
		if (checkboxes.length === 0) {
			alert("最低１個以上のチェックボックスを選択してください。");
			return false; // 폼 제출을 중단
		} else {
			document.getElementById("insertForm").submit();
		}
	}
}
function tradeCancleInsert() {
	let ok = confirm("作成をキャンセルしますか？");
	if (ok) {
		location.href = "trade";
	}
}
function tradeUpdate() {
	let ok = confirm("修正しますか？");
	if (ok) {
		document.getElementById("updateForm").submit();
	}
}
function tradeDelete(pk) {
	let ok = confirm("削除しますか？");
	if (ok) {
		location.href = "DeleteTrade?no=" + pk;
	}
}

function tradeCommentsInsert(pk) {
	let ok = confirm("作成しますか？");
	if (ok) {
		const formId = `insertTradeCommentsForm_${pk}`;
		const formElement = document.getElementById(formId);
		if (formElement) {
			formElement.submit();
		} else {
			console.error(`Form with ID ${formId} not found.`);
		}
	}
}
function tradeCommentsDelete(pk) {
	let ok = confirm("削除しますか？");
	if (ok) {
		location.href = "DeleteCommentsTrade?no=" + pk;
	}
}


$(function() {

	$(document).on("click", ".trade-openComments", function() {
		let post = $(this).closest(".trade-content");
		let commentsDiv = post.find(".trade-comments");
		console.log($(this).val());
		$(commentsDiv).each(function(idx, cd) {
			$(cd).toggle();
		})
	});

	$(".trade-openCategorys").click(function() {
		let post = $(this).closest(".trade-container");
		let commentsDiv = post.find(".trade-category");

		commentsDiv.each(function() {
			$(".trade-search").css("display", "none");
			$(this).toggle();
		});
	});

	$(".trade-openSearch").click(function() {
		let post = $(this).closest(".trade-container");
		let commentsDiv = post.find(".trade-search");

		commentsDiv.each(function() {
			$(".trade-category").css("display", "none");
			$(".trade-comments").toggle();
		});
	});


	//  $(document).on('click', '.paging-link', function(event) {
	//		var url = $(this).attr('href');
	//
	//		$.ajax({
	//			url: url,
	//			method: 'GET',
	//			success: function(response) {
	//				console.log("페이징 ajax호출 성공");
	//				console.log(response);
	//				$('.mypage-jsp-section').html(response);
	//			},
	//			error: function(xhr, status, error) {
	//				console.error('AJAX 요청 실패:', status, error);
	//			}
	//		});
	//	});


	/*const moreBtn = document.querySelector("#more-btn");
	console.log(moreBtn);
	let limit = parseInt(moreBtn.value);
	console.log(limit);
	const tradeContent = document.querySelector('.trade-conmain');


	moreBtn.addEventListener("click", () => {
		alert('clicked!')
		let url = "ArticleAPI?limit=" + limit;
		fetch(url)
			.then(response => response.json())
			.then(data => {
				console.log(data); // 받은 데이터를 처리합니다.
				limit += 5;
				console.log('호출 후 limit' + limit);

				data.forEach((t) => {
					console.log("t:" + t);
					
					 let categoriesHtml = '';
							 (t.category).forEach((cate) => {
						categoriesHtml += `<span style="margin-right: 5px;">${cate}</span>`;
						 });
					let content =
						`<div class="trade-content">
						<div>
						<div>${t.nickname}</div>
						<div>${t.twitterId}</div>
						<div style="display: flex; flex-wrap: wrap;">
								${categoriesHtml}
						</div>
						<div>${t.date}</div>
						</div></div>`;
					tradeContent.innerHTML += content;
					console.log("카테고리스:"+categoriesHtml);
				});

			});
	});*/


	const moreBtn = $("#more-btn");
	console.log(moreBtn);
	let limit = parseInt(moreBtn.val());
	console.log(limit);
	const tradeContent = $('.trade-conmain');

	moreBtn.on("click", function() {
		alert('clicked!');
		let url = "ArticleAPI?limit=" + limit;
		$.ajax({
			url: url,
			method: 'GET',
			dataType: 'json',
			success: function(data) {
				console.log(data); // 받은 데이터를 처리합니다.
				limit += 5;
				console.log('호출 후 limit' + limit);

				data.forEach(function(t) {
					console.log(t);

					let categoriesHtml = '';
					$.each(t.category, function(index, cate) {
						categoriesHtml += `<div class="trade-goods-category">${cate}</div>`;
					});

					let commentHTML = "";
					$.each(t.comments, (idx, cm) => {
						commentHTML += `<div class="trade-comments comment-wrap" style="display: none">
							<div>
								<div>${cm.sNickname}</div>
								<div>${cm.date}</div>
							</div>
							<div>${cm.text}
							</div>
							<div>
										<a onclick="tradeDelete(${t.pk})"> <img class="crud-icon"
											src="haco_img/delete.png" alt="">
										</a>
									</div>
						</div>`;
					});
					console.log(commentHTML);



					let content =
						`<div class="trade-content">
				<div class="trade-content-title">
					<div>${t.nickname}</div>
					<div>
						<a target="_blnck" href="https://x.com/${t.screenName}"><img
							src="haco_img/icon-twitter.png" /></a>
					</div>
					<div>${t.date}</div>
				</div>
				<!--  카테고리 for문 시작 -->
				<div class="trade-content-category ">
							${categoriesHtml}
					<!-- 카테고리for문 끝 -->
				</div>
				<div class="trade-content-text">
					<div class="trade-con-title">${t.text}</div>
						<div>
							<a onclick="location.href='UpdateTrade?no=${t.pk}'"> <img
								class="crud-icon" src="haco_img/update.png" alt="" />
							</a>
						</div>
						<div>
							<a onclick="tradeDelete(${t.pk})"> <img class="crud-icon"
								src="haco_img/delete.png" alt="" />
							</a>
						</div>
				</div>
<div class="cute-button-box">
					<button class="trade-openComments cute-button-pink">コメント(${t.comments.length})</button>
				</div>
				${commentHTML}
				<div class="trade-comments" style="display: none; border: 0px;">
					<form id="insertTradeCommentsForm_${t.pk}"
						action="InsertTradeComments">
						<input name="no" type="hidden" value="${t.pk}" /> <input
							name="masterTwitterId" type="hidden" value="${t.twitterId}" />
						<textarea style="width: 99%; height: 70px;" name="text"></textarea>
						<button class="cute-button-blue" type="button"
							onclick="tradeCommentsInsert('${t.pk}')">作成</button>
					</form>
				</div>
			</div>`;
					tradeContent.append(content);
				});
			}
		});
	});
});


