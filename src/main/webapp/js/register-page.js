$(document).ready(function(){
	
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
			
			
			
		})
		
		
	})
	
	
	
	
	
	
	
	
})