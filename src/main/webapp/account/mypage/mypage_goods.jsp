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

.goods-info-box {
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

.goods-info-box>div {
	text-align: center;
}

.goods-info-box>div:nth-child(1) {
	width: 10%;
}

.goods-info-box>div:nth-child(2) {
	text-align: left;
	width: 70%;
	padding-left: 5px;
}

.goods-info-box>div:nth-child(3) {
	width: 20%;
}

.goods-info-button{
	margin-top: 20px;
}

.goods-info-button {
  padding: 1.5em 3em;
  border-radius: 50px;
  background: #7986cb;
  font-size: 20pt;
  padding: 0;
  color: #fff;
  -webkit-transition: background-color 0.3s, color 0.3s;
  transition: background-color 0.3s, color 0.3s;
}
.goods-info-button.button--inverted {
  background: #ECEFF1;
  color: #37474f;
}
.goods-info-button::before {
 
  position: absolute;
  top: -20px;
  left: -20px;
  bottom: -20px;
  right: -20px;
  background: inherit;
  border-radius: 50px;
  z-index: -1;
  opacity: 0.4;
  -webkit-transform: scale3d(0.8, 0.5, 1);
  transform: scale3d(0.8, 0.5, 1);
}
.goods-info-button:hover {
  -webkit-transition: background-color 0.1s 0.3s, color 0.1s 0.3s;
  transition: background-color 0.1s 0.3s, color 0.1s 0.3s;
  color: #ECEFF1;
  background-color: #3f51b5;
  -webkit-animation: anim-moema-1 0.3s forwards;
  animation: anim-moema-1 0.3s forwards;
}
.goods-info-button.button--inverted:hover {
  color: #ECEFF1;
  background-color: #7986cb;
}
.goods-info-button:hover::before {
  -webkit-animation: anim-moema-2 0.3s 0.3s forwards;
  animation: anim-moema-2 0.3s 0.3s forwards;
}
@-webkit-keyframes anim-moema-1 {
  60% {
    -webkit-transform: scale3d(0.8, 0.8, 1);
    transform: scale3d(0.8, 0.8, 1);
  }
  85% {
    -webkit-transform: scale3d(1.1, 1.1, 1);
    transform: scale3d(1.1, 1.1, 1);
  }
  100% {
    -webkit-transform: scale3d(1, 1, 1);
    transform: scale3d(1, 1, 1);
  }
}
@keyframes anim-moema-1 {
  60% {
    -webkit-transform: scale3d(0.8, 0.8, 1);
    transform: scale3d(0.8, 0.8, 1);
  }
  85% {
    -webkit-transform: scale3d(1.1, 1.1, 1);
    transform: scale3d(1.1, 1.1, 1);
  }
  100% {
    -webkit-transform: scale3d(1, 1, 1);
    transform: scale3d(1, 1, 1);
  }
}

</style>
</head>
<%
MyPageDAO.getBromide(request);
MyPageDAO.get57mmCanBadge(request);
MyPageDAO.get76mmCanBadge(request);
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
					<div class="goods-info-box">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-member">${bromide.m_name}</div>
						<div class="goods-info-count">
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
			<c:forEach var="CanBadge57mm" items="${CanBadge57mmInfos }">
				<div>
					<div class="goods-info-box">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${CanBadge57mm.i_icon}">
						</div>
						<div class="goods-info-member">${CanBadge57mm.m_name}</div>
						<div class="goods-info-count">
							<select>
								<optgroup label="${CanBadge57mm.g_count}ea"></optgroup>
								<c:forEach begin="0" end="10" var="i">
									<c:choose>
										<c:when test="${i != CanBadge57mm.g_count}">
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
			<c:forEach var="CanBadge76mm" items="${CanBadge76mmInfos }">
				<div>
					<div class="goods-info-box">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${CanBadge76mm.i_icon}">
						</div>
						<div class="goods-info-member">${CanBadge76mm.m_name}</div>
						<div class="goods-info-count">
							<select>
								<optgroup label="${CanBadge76mm.g_count}ea"></optgroup>
								<c:forEach begin="0" end="10" var="i">
									<c:choose>
										<c:when test="${i != CanBadge76mm.g_count}">
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
					<div class="goods-info-box">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-member">${bromide.m_name}</div>
						<div class="goods-info-count">
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
					<div class="goods-info-box">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-member">${bromide.m_name}</div>
						<div class="goods-info-count">
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
					<div class="goods-info-box">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-member">${bromide.m_name}</div>
						<div class="goods-info-count">
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
					<div class="goods-info-box">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-member">${bromide.m_name}</div>
						<div class="goods-info-count">
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
					<div class="goods-info-box">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-member">${bromide.m_name}</div>
						<div class="goods-info-count">
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
					<div class="goods-info-box">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-member">${bromide.m_name}</div>
						<div class="goods-info-count">
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
					<div class="goods-info-box">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-member">${bromide.m_name}</div>
						<div class="goods-info-count">
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