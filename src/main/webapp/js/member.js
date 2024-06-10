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
    
    // 더보기 div 감춤
    $('.member-detail-container2').hide();
    
    // 더보기 클릭 시 더보기 div 노출
    $('.member-detail-open').on('click', function(){
		
		// 버튼을 누르면 컨테이너가 늘어나는 transition 걸고,
		// transition 효과 끝나면 사라지게?
		// 아니면 버튼 누르면 바로 사라지고
		// 컨테이너 2가 transition 효과 반영되면서 올라오게??
		$('.member-detail-container').hide();
		$('.member-detail-container2').show();
	})
	
    // 접기 클릭 시 더보기 div 감춤
    $('.member-detail-close').on('click', function(){
		$('.member-detail-container2').hide();
		$('.member-detail-container').show();
	})
    
});


