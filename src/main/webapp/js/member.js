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
    
    // 스크롤하면 z-index 변경되게 하고 싶은데 아직 수정중인 코드임
	$('.member-list-container').scroll(function(){
		$('.member-list-title').css('z-index','900');
		
	});
    
    
    
    
    
});


