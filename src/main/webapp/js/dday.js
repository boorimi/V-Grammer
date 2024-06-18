document.addEventListener("DOMContentLoaded", function() {
    console.log("DOMContentLoaded 이벤트가 발생했습니다.");

    var calendarButton = document.getElementById("calendarButton");
    console.log("캘린더로 돌아가기 버튼을 가져왔습니다:", calendarButton);

    calendarButton.addEventListener("click", function() {
        console.log("캘린더로 돌아가기 버튼이 클릭되었습니다.");

        // CalendarC 서블릿으로 이동
        console.log("CalendarC 서블릿으로 이동합니다.");
        window.location.href = 'CalendarC';
    });
});
