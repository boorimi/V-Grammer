<%@page import="com.vg.jw.mypage.MyPageDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/mypage_goods.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<script src="js/mypage_goods.js"></script>
<script type="text/javascript">
	$(function() {
		$(".goods-info-button").click(function() {
			 $(this).next('.goods-content').slideToggle();

		});
	});
</script>
<style type="text/css">
.mypage-goods-container {
	position: relative;
	width: 50vw;
	display: flex;
	flex-direction: column;
	gap: 0px 20px;
}

.goods-wrap {
	display: flex;
	justify-content: center;
	width: 180px;
}

.goods-content {
	display: flex;
	width: 100%;
	justify-content: center;
	flex-wrap: wrap;
	gap: 20px 3px;
	
	
}

.goods-wrap>div {
	text-align: center;
}

.goods-wrap>div:nth-child(1) {
	width: 10%;
}

.goods-wrap>div:nth-child(2) {
	text-align: left;
	width: 70%;
	padding-left: 5px;
}

.goods-wrap>div:nth-child(3) {
	width: 20%;
}

.goods-info-button{
	margin-top: 20px;
}
</style>
</head>
<%
MyPageDAO.getBromide(request);
%>


<body>
	<div class="mypage-goods-container">
		<div>
			<h2>グッズ管理</h2>
		</div>
		<button class="goods-info-button">白賞ブロマイド ▼</button>
		<div class="goods-content">
			<c:forEach var="bromide" items="${bromideInfos }">
				<div>
					<div class="goods-wrap">
						<div class="goods-info-bromide">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-bromide">${bromide.m_name}</div>
						<div class="goods-info-bromide">
							<select>
								<optgroup label="${bromide.g_count}ea"></optgroup>
								<c:forEach begin="0" end="10" var="i">
									<c:choose>
										<c:when test="${i != bromide.g_count}">
											<option value="${i}">${i }</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i }">${i }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class ="goods-info-button">57mm缶バッジ ▼</button>
		<div class="goods-content">
			<c:forEach var="bromide" items="${bromideInfos }">
				<div>
					<div class="goods-wrap">
						<div class="goods-info-bromide">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-bromide">${bromide.m_name}</div>
						<div class="goods-info-bromide">
							<select>
								<optgroup label="${bromide.g_count}ea"></optgroup>
								<c:forEach begin="0" end="10" var="i">
									<c:choose>
										<c:when test="${i != bromide.g_count}">
											<option value="${i}">${i }</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i }">${i }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">76mm缶バッジ" ▼</button>
		<div class="goods-content">
			<c:forEach var="bromide" items="${bromideInfos }">
				<div>
					<div class="goods-wrap">
						<div class="goods-info-bromide">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-bromide">${bromide.m_name}</div>
						<div class="goods-info-bromide">
							<select>
								<optgroup label="${bromide.g_count}ea"></optgroup>
								<c:forEach begin="0" end="10" var="i">
									<c:choose>
										<c:when test="${i != bromide.g_count}">
											<option value="${i}">${i }</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i }">${i }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">SD絵アクキー ▼</button>
		<div class="goods-content">
			<c:forEach var="bromide" items="${bromideInfos }">
				<div>
					<div class="goods-wrap">
						<div class="goods-info-bromide">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-bromide">${bromide.m_name}</div>
						<div class="goods-info-bromide">
							<select>
								<optgroup label="${bromide.g_count}ea"></optgroup>
								<c:forEach begin="0" end="10" var="i">
									<c:choose>
										<c:when test="${i != bromide.g_count}">
											<option value="${i}">${i }</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i }">${i }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">コスタ ▼</button>
		<div class="goods-content">
			<c:forEach var="bromide" items="${bromideInfos }">
				<div>
					<div class="goods-wrap">
						<div class="goods-info-bromide">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-bromide">${bromide.m_name}</div>
						<div class="goods-info-bromide">
							<select>
								<optgroup label="${bromide.g_count}ea"></optgroup>
								<c:forEach begin="0" end="10" var="i">
									<c:choose>
										<c:when test="${i != bromide.g_count}">
											<option value="${i}">${i }</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i }">${i }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">思い出チェキ風カード ▼</button>
		<div class="goods-content">
			<c:forEach var="bromide" items="${bromideInfos }">
				<div>
					<div class="goods-wrap">
						<div class="goods-info-bromide">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-bromide">${bromide.m_name}</div>
						<div class="goods-info-bromide">
							<select>
								<optgroup label="${bromide.g_count}ea"></optgroup>
								<c:forEach begin="0" end="10" var="i">
									<c:choose>
										<c:when test="${i != bromide.g_count}">
											<option value="${i}">${i }</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i }">${i }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">DMM：色紙 ▼</button>
		<div class="goods-content">
			<c:forEach var="bromide" items="${bromideInfos }">
				<div>
					<div class="goods-wrap">
						<div class="goods-info-bromide">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-bromide">${bromide.m_name}</div>
						<div class="goods-info-bromide">
							<select>
								<optgroup label="${bromide.g_count}ea"></optgroup>
								<c:forEach begin="0" end="10" var="i">
									<c:choose>
										<c:when test="${i != bromide.g_count}">
											<option value="${i}">${i }</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i }">${i }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">DMM：57mm缶バッジ ▼</button>
		<div class="goods-content">
			<c:forEach var="bromide" items="${bromideInfos }">
				<div>
					<div class="goods-wrap">
						<div class="goods-info-bromide">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-bromide">${bromide.m_name}</div>
						<div class="goods-info-bromide">
							<select>
								<optgroup label="${bromide.g_count}ea"></optgroup>
								<c:forEach begin="0" end="10" var="i">
									<c:choose>
										<c:when test="${i != bromide.g_count}">
											<option value="${i}">${i }</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i }">${i }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">DMM：ミニアクスタ ▼</button>
		<div class="goods-content">
			<c:forEach var="bromide" items="${bromideInfos }">
				<div>
					<div class="goods-wrap">
						<div class="goods-info-bromide">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-bromide">${bromide.m_name}</div>
						<div class="goods-info-bromide">
							<select>
								<optgroup label="${bromide.g_count}ea"></optgroup>
								<c:forEach begin="0" end="10" var="i">
									<c:choose>
										<c:when test="${i != bromide.g_count}">
											<option value="${i}">${i }</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i }">${i }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">DMM：チェキ ▼</button>
		<div class="goods-content">
			<c:forEach var="bromide" items="${bromideInfos }">
				<div>
					<div class="goods-wrap">
						<div class="goods-info-bromide">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-bromide">${bromide.m_name}</div>
						<div class="goods-info-bromide">
							<select>
								<optgroup label="${bromide.g_count}ea"></optgroup>
								<c:forEach begin="0" end="10" var="i">
									<c:choose>
										<c:when test="${i != bromide.g_count}">
											<option value="${i}">${i }</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i }">${i }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>