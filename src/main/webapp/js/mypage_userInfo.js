$(document).ready(function() {
    $('#retire-button').click(function(event) {
        event.preventDefault(); 
        let retireOK = confirm("本当に会員脱退しますか?(会員情報は無くなります)");

        if (retireOK == true) {
            let userId = this.value;
			console.log(userId);
            $.ajax({
                url: 'UserInfoC',
                type: 'GET', 
                data: { userId: userId },
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8', // 데이터 타입 명시
                success: function(response) {
                    console.log('UserInfoC에 값 전달');
                },
                error: function(error) {
                    console.log('에러 발생', error);
                }
            });
        }
    });
});