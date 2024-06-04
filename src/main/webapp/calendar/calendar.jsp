<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jsp를 이용한 달력</title>
</head>
<style>
.top {
	background-color: #cdfaef;
}

.date {
	background-color: #faf8e3;
	font-size: "2";
	text-align: center;
}

a {
	text-decoration: none;
}
</style>
<body>

	<%
	Calendar cr = Calendar.getInstance();
	//시스템상 현재 년월일
	int CurrentYear = cr.get(Calendar.YEAR);
	int CurrentMonth = cr.get(Calendar.MONTH)+1;
	int Currentdate = cr.get(Calendar.DATE);
	// 시스템상 오늘 날짜
	String today = CurrentYear + ":" +(CurrentMonth)+ ":"+Currentdate;
	
	//이동시 매개변수로 들어오는 년,월
	String paraYear = request.getParameter("year");
	String paraMonth = request.getParameter("month");

	//string으로 받은 값을 int로 변환할 거임.
	int year, month;
	
	//매개변수 없는 처음 상태의 경우 year,month는 현재 날짜로 정해준다
	if(paraYear == null && paraMonth == null){
    year = CurrentYear;
    month = CurrentMonth;
} else {
    year = Integer.parseInt(paraYear);
    month = Integer.parseInt(paraMonth);
    if (month < 1) { // 여기를 수정
        month = 12; // 여기도 수정
        year = year - 1;
    }
    if (month > 12) {
        month = 1; // 여기도 수정
        year = year + 1;
    }
}

	
	// 1일부터 시작하는 달력을 만들기 위해 오늘의 연도,월을 셋팅하고 일부분은 1을 셋팅한다.
	// 왜인지 모르겠는데 month에 -1을 안해주면 다음달이 들어온다;; 
	cr.set(year, month-1, 1);
	
	// 해당 월의 첫날과 마지막날
	int startDate = cr.getMinimum(Calendar.DATE);//1인데 그냥 설정해줌.
	int endDate = cr.getActualMaximum(Calendar.DATE);
	
	// 1일의 요일
	int startDay = cr.get(Calendar.DAY_OF_WEEK);
	int count = 0;
	%>

	<!-- 달력이 올라갈 테이블 생성 -->
	<table width="400" cellpadding="2" cellspacing="0" border="1"
		align="center">
		<tr class="top">

			<!-- href 설정이 관건이었다. 이걸로 매개변수를 받아 매월/매년 이동 가능 -->
			<th colspan="1"><a
				href="calendar.jsp?year=<%out.print(year-1);%>&month=<%out.print(month);%>">◀◀</a></th>
			<th colspan="1"><a
				href="calendar.jsp?year=<%out.print(year);%>&month=<%out.print(month-1);%>">◀</a></th>
			<th colspan="3"><%=year %>년 <%=month%>월</th>
			<th colspan="1"><a
				href="calendar.jsp?year=<%out.print(year);%>&month=<%out.print(month+1);%>">▶</a></th>
			<th colspan="1"><a
				href="calendar.jsp?year=<%out.print(year+1);%>&month=<%out.print(month);%>">▶▶</a></th>
		</tr>
		<!-- 요일 부분이 들어갈 행 생성 -->
		<tr class="date" height="30">
			<td>일</td>
			<td>월</td>
			<td>화</td>
			<td>수</td>
			<td>목</td>
			<td>금</td>
			<td>토</td>
		</tr>
		<!-- 날짜 부분이 들어갈 행 생성 -->
		<tr height="30">
			<%
	for (int i=1;i<startDay;i++){
	 count++;
	%>
			<!-- 이 달의 시작요일까지는 공백출력 -->
			<td>&nbsp;</td>
			<% 
	}
	for (int i=startDate;i<=endDate;i++){
		/* 현재 요일과 같은 칸에는 노랑색 표시 */
	 String bgcolor = (today.equals(year+":"+(month)+":"+i))? "#ffff00" : "#FFFFFF";
	 	/* 일요일과 토요일에 해당하는 날짜에는 붉은색이 표시되도록 함 */
		String color = (count%7 == 0 || count%7 == 6)? "red" : "black";
	 count++;
	%>
			<!-- 날짜를 찍어주는 부분 + 색이랑 폰트속성 지정 -->
			<td bgcolor="<%=bgcolor %>"><font size="2" color=<%=color %>><%=i %></font></td>
			<%
	  if(count%7 == 0 && i < endDate){
	%>
		</tr>
		<tr height="30">
			<%
	  }
	}
	/* 일주일 간격으로 나누어떨어지지 않는 경우, 뒷부분에 공백을 더 추가해준다(여백 맞추기) */
	while(count%7 != 0){
	%>
			<td>&nbsp;</td>
			<% 
	count++;
	 }
	%>
		</tr>
	</table>
</body>
</html>
