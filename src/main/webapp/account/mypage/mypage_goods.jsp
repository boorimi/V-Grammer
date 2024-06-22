<%@page import="com.vg.jw.mypage.MyPageDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="css/mypage_goods.css"> -->
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous"></script>
<script src="js/mypage_goods.js" defer="defer"></script>

</head>
<%
String[] category = { "bromide", "57mmCanBadge", "76mmCanBadge", "akuki", "coaster", "omoideCyeki", "dmmMiniShikishi",
		"dmm57CanBadge", "dmmMiniAkusuta", "dmmCyeki" };

for (String goodsCategory : category) {
	MyPageDAO.getGoodsInfo(request, goodsCategory);
}
%>


<body>
	<div class="mypage-goods-container">
		<div class="mypage-tab-title">
			<div class="mypage-tab-icon-wrap">
				<img class="mypage-tab-icon" alt=""
					src="account/mypage/mypage_index_icon/goods.png">
			</div>
			<h2>グッズ管理</h2>
		</div>
		<button class="goods-info-button">白賞ブロマイド ▼</button>
		<div class="goods-content">
			<c:forEach var="bromide" items="${Infos_bromide }">
				<div>
					<div class="goods-info-box"
						style="background-color: ${bromide.m_personalcolor}">
						<div class="goods-info-icon">
							<img alt=""
								src="haco_img/icon/${bromide.i_icon}">
						</div>
						<div class="goods-info-member">${bromide.m_name}</div>
						<div class="goods-info-count">
							<select class="goods-info-select" data-gmpk="${bromide.g_m_pk}"
								data-userid="${bromide.u_twitter_id }"
								data-category="${bromide.g_category}">
								<optgroup label="${bromide.g_count}ea"></optgroup>
								<c:forEach begin="0" end="9" var="i">
									<c:choose>
										<c:when test="${i != bromide.g_count}">
											<option value="${i}">${i}</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i}">${i}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${10 != bromide.g_count}">
										<option value="10">10+</option>
									</c:when>
									<c:otherwise>
										<option selected value="10">10+</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">57mm缶バッジ ▼</button>
		<div class="goods-content">
			<c:forEach var="canBadge57mm" items="${Infos_57mmCanBadge }">
				<div>
					<div class="goods-info-box"
						style="background-color: ${canBadge57mm.m_personalcolor}">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${canBadge57mm.i_icon}">
						</div>
						<div class="goods-info-member">${canBadge57mm.m_name}</div>
						<div class="goods-info-count">
							<select class="goods-info-select" data-gmpk="${canBadge57mm.g_m_pk}"
								data-userid="${canBadge57mm.u_twitter_id }"
								data-category="${canBadge57mm.g_category}">
								<optgroup label="${canBadge57mm.g_count}ea"></optgroup>
								<c:forEach begin="0" end="9" var="i">
									<c:choose>
										<c:when test="${i != canBadge57mm.g_count}">
											<option value="${i}">${i}</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i}">${i}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${10 != canBadge57mm.g_count}">
										<option value="10">10+</option>
									</c:when>
									<c:otherwise>
										<option selected value="10">10+</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">76mm缶バッジ" ▼</button>
		<div class="goods-content">
			<c:forEach var="canBadge76mm" items="${Infos_76mmCanBadge }">
				<div>
					<div class="goods-info-box"
						style="background-color: ${canBadge76mm.m_personalcolor}">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${canBadge76mm.i_icon}">
						</div>
						<div class="goods-info-member">${canBadge76mm.m_name}</div>
						<div class="goods-info-count">
							<select class="goods-info-select" data-gmpk="${canBadge76mm.g_m_pk}"
								data-userid="${canBadge76mm.u_twitter_id }"
								data-category="${canBadge76mm.g_category}">
								<optgroup label="${canBadge76mm.g_count}ea"></optgroup>
								<c:forEach begin="0" end="9" var="i">
									<c:choose>
										<c:when test="${i != canBadge76mm.g_count}">
											<option value="${i}">${i}</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i}">${i}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${10 != canBadge76mm.g_count}">
										<option value="10">10+</option>
									</c:when>
									<c:otherwise>
										<option selected value="10">10+</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">SD絵アクキー ▼</button>
		<div class="goods-content">
			<c:forEach var="akuki" items="${Infos_akuki }">
				<div>
					<div class="goods-info-box"
						style="background-color: ${akuki.m_personalcolor}">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${akuki.i_icon}">
						</div>
						<div class="goods-info-member">${akuki.m_name}</div>
						<div class="goods-info-count">
							<select class="goods-info-select" data-gmpk="${akuki.g_m_pk}"
								data-userid="${akuki.u_twitter_id }"
								data-category="${akuki.g_category}">
								<optgroup label="${akuki.g_count}ea"></optgroup>
								<c:forEach begin="0" end="9" var="i">
									<c:choose>
										<c:when test="${i != akuki.g_count}">
											<option value="${i}">${i}</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i}">${i}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${10 != akuki.g_count}">
										<option value="10">10+</option>
									</c:when>
									<c:otherwise>
										<option selected value="10">10+</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">コスタ ▼</button>
		<div class="goods-content">
			<c:forEach var="coaster" items="${Infos_coaster }">
				<div>
					<div class="goods-info-box"
						style="background-color: ${coaster.m_personalcolor}">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${coaster.i_icon}">
						</div>
						<div class="goods-info-member">${coaster.m_name}</div>
						<div class="goods-info-count">
							<select class="goods-info-select" data-gmpk="${coaster.g_m_pk}"
								data-userid="${coaster.u_twitter_id }"
								data-category="${coaster.g_category}">
								<optgroup label="${coaster.g_count}ea"></optgroup>
								<c:forEach begin="0" end="9" var="i">
									<c:choose>
										<c:when test="${i != coaster.g_count}">
											<option value="${i}">${i}</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i}">${i}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${10 != coaster.g_count}">
										<option value="10">10+</option>
									</c:when>
									<c:otherwise>
										<option selected value="10">10+</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">思い出チェキ風カード ▼</button>
		<div class="goods-content">
			<c:forEach var="omoideCyeki" items="${Infos_omoideCyeki }">
				<div>
					<div class="goods-info-box"
						style="background-color: ${omoideCyeki.m_personalcolor}">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${omoideCyeki.i_icon}">
						</div>
						<div class="goods-info-member">${omoideCyeki.m_name}</div>
						<div class="goods-info-count">
							<select class="goods-info-select" data-gmpk="${omoideCyeki.g_m_pk}"
								data-userid="${omoideCyeki.u_twitter_id }"
								data-category="${omoideCyeki.g_category}">
								<optgroup label="${omoideCyeki.g_count}ea"></optgroup>
								<c:forEach begin="0" end="9" var="i">
									<c:choose>
										<c:when test="${i != omoideCyeki.g_count}">
											<option value="${i}">${i}</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i}">${i}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${10 != omoideCyeki.g_count}">
										<option value="10">10+</option>
									</c:when>
									<c:otherwise>
										<option selected value="10">10+</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">DMM：色紙 ▼</button>
		<div class="goods-content">
			<c:forEach var="dmmMiniShikishi" items="${Infos_dmmMiniShikishi }">
				<div>
					<div class="goods-info-box"
						style="background-color: ${dmmMiniShikishi.m_personalcolor}">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${dmmMiniShikishi.i_icon}">
						</div>
						<div class="goods-info-member">${dmmMiniShikishi.m_name}</div>
						<div class="goods-info-count">
							<select class="goods-info-select" data-gmpk="${dmmMiniShikishi.g_m_pk}"
								data-userid="${dmmMiniShikishi.u_twitter_id }"
								data-category="${dmmMiniShikishi.g_category}">
								<optgroup label="${dmmMiniShikishi.g_count}ea"></optgroup>
								<c:forEach begin="0" end="9" var="i">
									<c:choose>
										<c:when test="${i != dmmMiniShikishi.g_count}">
											<option value="${i}">${i}</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i}">${i}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${10 != dmmMiniShikishi.g_count}">
										<option value="10">10+</option>
									</c:when>
									<c:otherwise>
										<option selected value="10">10+</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">DMM：57mm缶バッジ ▼</button>
		<div class="goods-content">
			<c:forEach var="dmm57CanBadge" items="${Infos_dmm57CanBadge }">
				<div>
					<div class="goods-info-box"
						style="background-color: ${dmm57CanBadge.m_personalcolor}">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${dmm57CanBadge.i_icon}">
						</div>
						<div class="goods-info-member">${dmm57CanBadge.m_name}</div>
						<div class="goods-info-count">
							<select class="goods-info-select" data-gmpk="${dmm57CanBadge.g_m_pk}"
								data-userid="${dmm57CanBadge.u_twitter_id }"
								data-category="${dmm57CanBadge.g_category}">
								<optgroup label="${dmm57CanBadge.g_count}ea"></optgroup>
								<c:forEach begin="0" end="9" var="i">
									<c:choose>
										<c:when test="${i != dmm57CanBadge.g_count}">
											<option value="${i}">${i}</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i}">${i}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${10 != dmm57CanBadge.g_count}">
										<option value="10">10+</option>
									</c:when>
									<c:otherwise>
										<option selected value="10">10+</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">DMM：ミニアクスタ ▼</button>
		<div class="goods-content">
			<c:forEach var="dmmMiniAkusuta" items="${Infos_dmmMiniAkusuta }">
				<div>
					<div class="goods-info-box"
						style="background-color: ${dmmMiniAkusuta.m_personalcolor}">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${dmmMiniAkusuta.i_icon}">
						</div>
						<div class="goods-info-member">${dmmMiniAkusuta.m_name}</div>
						<div class="goods-info-count">
							<select class="goods-info-select" data-gmpk="${dmmMiniAkusuta.g_m_pk}"
								data-userid="${dmmMiniAkusuta.u_twitter_id }"
								data-category="${dmmMiniAkusuta.g_category}">
								<optgroup label="${dmmMiniAkusuta.g_count}ea"></optgroup>
								<c:forEach begin="0" end="9" var="i">
									<c:choose>
										<c:when test="${i != dmmMiniAkusuta.g_count}">
											<option value="${i}">${i}</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i}">${i}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${10 != dmmMiniAkusuta.g_count}">
										<option value="10">10+</option>
									</c:when>
									<c:otherwise>
										<option selected value="10">10+</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">DMM：チェキ ▼</button>
		<div class="goods-content">
			<c:forEach var="dmmCyeki" items="${Infos_dmmCyeki }">
				<div>
					<div class="goods-info-box"
						style="background-color: ${dmmCyeki.m_personalcolor}">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${dmmCyeki.i_icon}">
						</div>
						<div class="goods-info-member">${dmmCyeki.m_name}</div>
						<div class="goods-info-count">
							<select class="goods-info-select" data-gmpk="${dmmCyeki.g_m_pk}"
								data-userid="${dmmCyeki.u_twitter_id }"
								data-category="${dmmCyeki.g_category}">
								<optgroup label="${dmmCyeki.g_count}ea"></optgroup>
								<c:forEach begin="0" end="9" var="i">
									<c:choose>
										<c:when test="${i != dmmCyeki.g_count}">
											<option value="${i}">${i}</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i}">${i}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${10 != dmmCyeki.g_count}">
										<option value="10">10+</option>
									</c:when>
									<c:otherwise>
										<option selected value="10">10+</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<button class="goods-info-button">테스트 ▼</button>
		<div class="goods-content">
			<c:forEach var="dmmCyeki" items="${Infos_dmmCyeki }">
				<div>
					<div class="goods-info-box"
						style="background-color: ${dmmCyeki.m_personalcolor}">
						<div class="goods-info-icon">
							<img alt="" style="width: 50px;"
								src="haco_img/icon/${dmmCyeki.i_icon}">
						</div>
						<div class="goods-info-member">${dmmCyeki.m_name}</div>
						<div class="goods-info-count">
							<select class="goods-info-select" data-gmpk="${dmmCyeki.g_m_pk}"
								data-userid="${dmmCyeki.u_twitter_id }"
								data-category="${dmmCyeki.g_category}">
								<optgroup label="${dmmCyeki.g_count}ea"></optgroup>
								<c:forEach begin="0" end="9" var="i">
									<c:choose>
										<c:when test="${i != dmmCyeki.g_count}">
											<option value="${i}">${i}</option>
										</c:when>
										<c:otherwise>
											<option selected value="${i}">${i}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${10 != dmmCyeki.g_count}">
										<option value="10">10+</option>
									</c:when>
									<c:otherwise>
										<option selected value="10">10+</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>