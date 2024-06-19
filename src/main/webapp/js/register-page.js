$(document).ready(function(){
	
	let registerOK = false;
	
	//닉네임 글자수 제한
	
	
	
	
	//닉네임 유효성 검사
	$("#register-nickname-input").blur(function(){
		
		const inputNickName = $(this).val();
		console.log("input닉네임: "+inputNickName);
		
		const checkResult = $("#check-result");
		
		$.ajax({
			url : 'RegisterC',
			data : {inputNickName},
		
		}).done(function(res){
			console.log(res);
			console.log(JSON.stringify(res));
			
			//판정결과를 문구로 표시하는 부분
			if(res == 0){
				checkResult.text('このニックネームは使用可能です');
				checkResult.css('color', 'blue');
				$("#register-nickname-input").css('border', '1px solid blue');
				registerOK = true;
				
			}else{
				checkResult.text('このニックネームは使用中です');
				checkResult.css('color', 'red');
				$("#register-nickname-input").css('border', '1px solid red');
				$("#register-nickname-input").focus();
				registerOK = false;
			}
			
			
		});
		
		
	});
	
	//요청 보낼지 말지
	 $('form').on('submit', function(event) {
        if (!registerOK) {
            // registerOK가 false이면 폼 제출을 막습니다.
            event.preventDefault();
            alert('조건이 만족되지 않았습니다. 폼을 제출할 수 없습니다.');
        } else {
            // registerOK가 true이면 폼을 정상적으로 제출합니다.
			confirm('이 정보로 등록하시겠습니까?');

        }
    });
	
	
	
	
	
	
	
	
});