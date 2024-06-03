<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/member.css" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function name() {

	}
</script>
</head>
<body>
	<div class="member_containor">
		<div class="member_memberList_containor">
			<div class="member_memberList">Members</div>
			<!-- <form action="MemberC" enctype="multipart/form-data"> -->
			<c:forEach var="m" items="${members }" begin="0">
				<div class="member_memberList">${m.m_name }</div>
			</c:forEach>
			<!-- </form> -->
		</div>
		<div class="member_content">
			<c:forEach items="${images }" var="i">
				<div class="member_images">
					<img src="${i.i_background }">
				</div>
			</c:forEach>
		</div>

	</div>

</body>
</html>