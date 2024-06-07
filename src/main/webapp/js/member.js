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
    let idx = 0;
    
    $memberList.each(function() {
        $(this).on('click', function() {
            $memberImgs.removeClass('show');
            
            idx = $(this).attr('id');
            $memberImgs.eq(idx - 1).addClass('show');
        });
    });
});
