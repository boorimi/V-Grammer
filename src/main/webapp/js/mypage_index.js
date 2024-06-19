$(document).ready(function() {
	// 공통 AJAX 요청 함수
	function loadContent(url) {
		$.ajax({
			url: url,
			type: "GET",
			success: function(data) {
				console.log("공통 AJAX 요청 성공");
				$(".mypage-jsp-section").empty();
				console.log("공통 지우는거 성공");
				$(".mypage-jsp-section").append(data);
			},
			error: function() {
				alert("Error loading page 로그인 세션이 만료되었습니다");
			}
		});
	}
	
	loadContent("account/mypage/mypage_userInfo.jsp");

	// AJAX 요청 함수 with blur effect
	function loadContentWithBlur(url) {
		$.ajax({
			url: url,
			type: "GET",
			beforeSend: function() {
				$('html').css('filter', 'blur(5px)');
			},
			success: function(data) {
				console.log("블러 AJAX 요청 성공");
				$(".mypage-jsp-section").empty();
				console.log("블러 지우는거 성공");
				$(".mypage-jsp-section").append(data);
				$('html').css('filter', 'none'); // 블러 효과 제거
			},
			error: function() {
				alert("Error loading page 로그인 세션이 만료되었습니다");
				$('html').css('filter', 'none'); // 블러 효과 제거
			}
		});
	}

	// 각 메뉴 아이템에 대해 클릭 이벤트를 설정
	$("#mypage-menu-userInfo").click(function() {
		loadContent("account/mypage/mypage_userInfo.jsp");
	});

	$("#mypage-menu-goods").click(function() {
		loadContentWithBlur("account/mypage/mypage_goods.jsp");
	});

	/*  $("#mypage-menu-article").click(function() {
		  loadContent("account/mypage/mypage_article.jsp");
		  console.log("article.jsp로드 성공");
	  });*/

	$("#mypage-menu-comment").click(function() {
		loadContent("account/mypage/mypage-comment.jsp");
	});


	$('#mypage-menu-article').click(function() {
		$.ajax({
			url: 'ArticleC', // 서블릿 URL
			method: 'GET', // 또는 'POST'
			success: function(response) {
				console.log("작성글 인클루드 ajax 호출 성공");
				$('.mypage-jsp-section').html(response);
			},
			error: function(xhr, status, error) {
				console.error('AJAX 요청 실패:', status, error);
			}
		});
	});
	
	$(document).on('click', '.paging-link', function(event) {
		var url = $(this).attr('href');

		$.ajax({
			url: url,
			method: 'GET',
			success: function(response) {
				console.log("페이징 ajax호출 성공");
				console.log(response);
				$('.mypage-jsp-section').html(response);
			},
			error: function(xhr, status, error) {
				console.error('AJAX 요청 실패:', status, error);
			}
		});
	});



});