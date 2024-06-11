  document.addEventListener("DOMContentLoaded", function() {
            // 캘린더로 돌아가는 버튼 클릭 이벤트 핸들러
            var calendarButton = document.getElementById("calendarButton");
            calendarButton.addEventListener("click", function() {
                // CalendarC 서블릿으로 이동
                window.location.href = 'CalendarC';
            });
        });