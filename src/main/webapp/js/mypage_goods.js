$(function() {
	/* const goodsbtns = document.querySelectorAll('.goods-info-button');
	goodsbtns.forEach((e) => {
	  e.addEventListener('click', function () {
		const goodscon = this.nextElementSibling;
		console.log(goodscon);
		goodscon.style.transform='scaleY(1)';
	  });
	}); */

	$(".goods-info-button").next('.goods-content').css('display', 'none');
	$('html').css('filter', 'blur(0)');
	$(".goods-info-button").click(function() {
		$(this).next('.goods-content').slideToggle('medium', function() {
			if ($(this).is(':visible'))
				$(this).css('display', 'flex');
		});

		var buttonText = $(this).find(".button-text").text();
		if (buttonText.includes('▼')) {
			$(this).find(".button-text").text(buttonText.replace('▼', '▲'));
		} else {
			$(this).find(".button-text").text(buttonText.replace('▲', '▼'));
		}

	});

	// 굿즈 수량 업데이트 

	$('.goods-info-select').focus(function() {		//select의 현재값을 저장	
		previousCount = $(this).val();
	});

	$('.goods-info-select').change(function() {
		var g_m_pk = $(this).data('gmpk');
		var u_twitter_id = $(this).data('userid');
		var g_category = $(this).data('category');
		var g_count = $(this).val();

		$.ajax({
			url: 'GoodsC', // 서블릿 URL을 여기에 입력하세요.
			type: 'POST',
			data: {
				g_m_pk: g_m_pk,
				u_twitter_id: u_twitter_id,
				g_category: g_category,
				g_count: g_count,
				previousCount: previousCount

			},
			success: function(response) {
				// 성공 시 동작
				console.log('굿즈 수량 업데이트 성공:', response);
			},
			error: function(error) {
				// 에러 시 동작
				console.error('에러:', error);
			}
		});
	});

});
