// 이미지 바뀌는 js
//const memberList = document.querySelectorAll('.member-memberList');
//const memberImgs = document.querySelectorAll('.member-backgroundImg');
//memberImgs[0].classList.add('show'); 
//let idx = 0;
//memberList.forEach((member) => {
//	member.addEventListener("click", (e) => {
//
//		memberImgs.forEach((img) => {
//			img.classList.remove('show');
//		});
//
//		idx = member.getAttribute('id');
////		console.log(memberImgs[idx-1]) 
//		memberImgs[idx-1].classList.add('show');
//
//	});
//});

$(document).ready(function() {
    const $memberList = $('.member-memberList');
    const $memberImgs = $('.member-backgroundImg');
    $memberImgs.eq(0).addClass('show'); 
    $memberList.eq(0).addClass('show');
    let idx = 0;
    
    $memberList.each(function() {
        $(this).on('click', function() {
            $memberImgs.removeClass('show');
            $memberList.removeClass('show');
            idx = $(this).attr('id');
            $memberImgs.eq(idx - 1).addClass('show');
            $memberList.eq(idx - 1).addClass('show');
        });
    });
    
    // 스크롤하면 멤버 타이틀보다 멤버 div가 아래로 가도록 z-index 변경
	$('.member-memberList-container').scroll(function(){
		$('.member-list-title').css('z-index','900');
		$(this).$('.member-memberList.show').css('z-index','500');
	});
	// 클릭하면 다시 z-index 변경
	$('.member-memberList-container').click(function(){
		$('.member-list-title').css('z-index','500');
		$(this).$('.member-memberList.show').css('z-index','900');
	});
    

    
    
    
});


