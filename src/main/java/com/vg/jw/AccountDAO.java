package com.vg.jw;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpHeaders;

import com.github.scribejava.core.model.Response;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vg.ignore.DBManager;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public class AccountDAO {

//	public static void registerUserLocal(HttpServletRequest request) throws IOException {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		// 트위터 계정정보 가져오기
//		HttpSession twitterSession = request.getSession();
//
//		long twitterId = (long) twitterSession.getAttribute("twitterId");
//		String twitterScreenName = (String) twitterSession.getAttribute("twitterScreenName");
//		String twitterProfileImgUrl = (String) twitterSession.getAttribute("twitterProfileImgUrl");
//		System.out.println("AccountDAO테스트 출력(twitterId):" + twitterId);
//		System.out.println("AccountDAO테스트 출력(twitterScreenName):" + twitterScreenName);
//		System.out.println("트위터 프사 url 테스트 출력" + twitterProfileImgUrl);
//
//		// 사진받을 준비
//		String path = request.getServletContext().getRealPath("account/profileImg");
//
//		// 파일처리
//		MultipartRequest mr = new MultipartRequest(request, path, 1024 * 1024 * 10, "utf-8",
//				new DefaultFileRenamePolicy());
//
//		// 회원가입시 입력한 값 받아오기
//		String inputId = mr.getParameter("register-input-id");
//		String inputPw = mr.getParameter("register-input-pw");
//		String inputNickname = mr.getParameter("register-input-nickname");
//
//		String inputImgfile = mr.getFilesystemName("register-input-imgfile");
//
//		// 이미지를 등록하지 않았을 경우 자동으로 트위터 기본이미지 설정
//		if (inputImgfile == null) {
//			inputImgfile = twitterProfileImgUrl;
//		}
//
//		System.out.println(inputId);
//		System.out.println(inputPw);
//		System.out.println(inputNickname);
//		System.out.println(inputImgfile);
//
//		String sql = "INSERT INTO haco_user values(?, ?, ?, ?, 1, ?)";
//
//		try {
//
//			con = DBManager.connect();
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, inputId);
//			pstmt.setString(2, inputPw);
//			pstmt.setLong(3, twitterId);
//			pstmt.setString(4, inputNickname);
//			pstmt.setString(5, inputImgfile);
//
//			if (pstmt.executeUpdate() >= 1) {
//				System.out.println("유저 등록 성공");
//
//				System.out.println("어디로 갈지 설정");
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(con, pstmt, null);
//		}
//
//	}

	public static void registerUser(HttpServletRequest request) throws IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		// 트위터 계정정보 가져오기
		HttpSession twitterSession = request.getSession();

		long twitterId = (long) twitterSession.getAttribute("twitterId");
		String twitterScreenName = (String) twitterSession.getAttribute("twitterScreenName");
		String twitterProfileImgUrl = (String) twitterSession.getAttribute("twitterProfileImgUrl");
		System.out.println("AccountDAO테스트 출력(twitterId):" + twitterId);
		System.out.println("AccountDAO테스트 출력(twitterScreenName):" + twitterScreenName);
		System.out.println("트위터 프사 url 테스트 출력" + twitterProfileImgUrl);

		// 사진받을 준비
		String path = request.getServletContext().getRealPath("account/profileImg");

		// 파일처리
		MultipartRequest mr = new MultipartRequest(request, path, 1024 * 1024 * 10, "utf-8",
				new DefaultFileRenamePolicy());

		// 회원가입시 입력한 값 받아오기
		String inputNickname = mr.getParameter("register-input-nickname");
		String inputImgfile = mr.getFilesystemName("register-input-imgfile");

		// 이미지를 등록하지 않았을 경우 자동으로 트위터 기본이미지 설정
		if (inputImgfile == null) {
			inputImgfile = twitterProfileImgUrl;
		}

		System.out.println(inputNickname);
		System.out.println(inputImgfile);

		String sql = "INSERT INTO haco_user values(?, ?, ?, 1)";

		try {

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, twitterId);
			pstmt.setString(2, inputNickname);
			pstmt.setString(3, inputImgfile);

			if (pstmt.executeUpdate() >= 1) {
				System.out.println("유저 등록 성공");

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	// 유저가 회원가입이 되어있는지 판별하는 메서드
	public static void registerCheck(HttpServletRequest request, HttpServletResponse response) {

		HttpSession twitterLoginSession = request.getSession();
		long twitterId = (long) twitterLoginSession.getAttribute("twitterId");
		long dbId = 0;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select*from haco_user where u_twitter_id = ?";
		AccountDTO accountInfo = null;
		try {

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, twitterId);
			rs = pstmt.executeQuery();

			String loginResult = "";

			//rs.next가 false = 가입된 계정이 존재하지 않음 => 회원등록 컨트롤러로 보냄
			if (!rs.next()) {
				System.out.println("이 트위터 계정으로 가입된 아이디가 존재하지 않음");
				loginResult = "会員登録ページへ移動します。";
				response.sendRedirect("RegisterC");
				return;
				
			} else {
				dbId = rs.getLong("u_twitter_id");
				
				//ID 활성화 여부 검사
				if (rs.getInt("u_yesno") == 1) {
					System.out.println("로그인 성공");
					loginResult = "ログイン成功";

					twitterId = rs.getLong("u_twitter_id");
					// 계정 정보들 저장
					accountInfo = new AccountDTO();
					accountInfo.setU_twitter_id(rs.getLong("u_twitter_id"));
					accountInfo.setU_nickname(rs.getString("u_nickname"));
					accountInfo.setU_profile_img(rs.getString("u_profile_img"));
					accountInfo.setU_yesno(rs.getInt("u_yesno"));

					// 로그인 세션에 정보 추가
					twitterLoginSession.setAttribute("accountInfo", accountInfo);
					twitterLoginSession.setMaxInactiveInterval(60 * 10);

				} else {
					loginResult = "削除された会員です。";
					System.out.println("삭제되거나 비활성화된 아이디");
				}

			}

			request.setAttribute("loginResult", loginResult);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	// 회원등록 여부 검사 후 로그인판정 개시
	public static void login(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String inputId = request.getParameter("login-input-id");
		String inputPw = request.getParameter("login-input-pw");
		long twitterId = 0;
		String sql = "SELECT*FROM haco_user WHERE u_id = ?";

		AccountDTO accountInfo = null;
		try {

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputId);
			rs = pstmt.executeQuery();

			String loginResult = "";
			if (rs.next()) {
				String dbPw = rs.getString("u_pw");

				if (inputPw.equals(dbPw)) {
					// 유저 정보 활성화 여부 검사
					if (rs.getInt("u_yesno") == 1) {
						System.out.println("로그인 성공");
						loginResult = "ログイン成功";

						twitterId = rs.getLong("u_twitter_id");
						System.out.println("트위터 아이디로 screenName받아오는거 테스트" + getTwitterScreenName());
						// 계정 정보들 저장
						accountInfo = new AccountDTO();
						accountInfo.setU_id(rs.getString("u_id"));
						accountInfo.setU_pw(rs.getString("u_pw"));
						accountInfo.setU_twitter_id(rs.getLong("u_twitter_id"));
						accountInfo.setU_nickname(rs.getString("u_nickname"));
						accountInfo.setU_yesno(rs.getInt("u_yesno"));
						accountInfo.setU_profile_img(rs.getString("u_profile_img"));

						// 로그인 세션 생성
						HttpSession loginSession = request.getSession();
						loginSession.setAttribute("accountInfo", accountInfo);
						loginSession.setMaxInactiveInterval(60 * 10);

					} else {
						System.out.println("삭제되거나 비활성화된 아이디");
					}

				} else {
					System.out.println("비밀번호 오류");
					loginResult = "PW不一致です。";
				}

			}
			System.out.println("계정 id :" + accountInfo.getU_id());
//			System.out.println("계정 pw :" + accountInfo.getU_pw());
//			System.out.println("트위터id: " + accountInfo.getU_twitter_id());
//			System.out.println("닉네임 : " + accountInfo.getU_nickname());
//			System.out.println("계정 활성화 여부: " + accountInfo.getU_yesno());
//			System.out.println(accountInfo);

			request.setAttribute("loginResult", loginResult);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}

	}

	// 로그인 체크 메서드(로컬)
	public static boolean loginCheck(HttpServletRequest request) {
		HttpSession loginSession = request.getSession(false); // 세션이 아예 없으면 null반환
		if (loginSession != null) {
			AccountDTO accountInfo = (AccountDTO) loginSession.getAttribute("accountInfo");
			if (accountInfo != null) {
				return true; // 로그인 상태
			}
		}
		return false; // 로그인되지 않은 상태
	}

	// 로그인 체크 메서드(트위터)
	public static boolean loginCheckTwitter(HttpServletRequest request) {
		HttpSession twitterLoginSession = request.getSession(false); // 세션이 없을 경우 null을 반환

		if (twitterLoginSession != null) { // 세션이 존재할 경우 oauth를 거쳐 생성된 액세스토큰을 가져옴
			String accessToken = (String) twitterLoginSession.getAttribute("accessToken");
			String accessTokenSecret = (String) twitterLoginSession.getAttribute("accessTokenSecret");

			if (accessToken != null && accessTokenSecret != null) { // 액세스토큰 존재여부를 판정
				return true;
			}
		}
		return false;
	}

}
