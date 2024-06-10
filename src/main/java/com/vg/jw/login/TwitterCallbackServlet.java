package com.vg.jw.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

@WebServlet("/TwitterCallbackServlet")
public class TwitterCallbackServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("callback 실행");
		// LoginServlet의 consumer/provider를 가져옴

		// consumer = 기능을 사용하는 hakonaka 사이트
		OAuthConsumer consumer = (OAuthConsumer) request.getSession().getAttribute("oauthConsumer");
		// provider = API제공해주는 트위터
		OAuthProvider provider = (OAuthProvider) request.getSession().getAttribute("oauthProvider");
		// 트위터에서 보내는 리디렉션 URL에 oauth_verifier 라는 파라미터가 있음
		String verifier = request.getParameter("oauth_verifier");

		// 로그인 취소할 경우 denied파라미터가 url에 존재하게 됨
		String denied = request.getParameter("denied");
	
		try {
			//denied가 null이 아닐 경우 = 로그인 취소
			if (denied != null) {
				response.sendRedirect("HC");
			} else {

				// provider객체에서 consumer객체(consumer key와 secret가 들어있음)+verifier로
				// 액세스토큰과 액세스토큰 시크릿 받아서 consumer에 저장
				provider.retrieveAccessToken(consumer, verifier);
				// getToken()을 통해 액세스토큰(유저 인증정보 접근 시 사용함)을 문자열 형태로 가져옴.
				String accessToken = consumer.getToken();
				String accessTokenSecret = consumer.getTokenSecret();

				// 세션에 액세스토큰 저장
				request.getSession().setAttribute("accessToken", accessToken);
				request.getSession().setAttribute("accessTokenSecret", accessTokenSecret);
				
				ConfigurationBuilder cb = new ConfigurationBuilder();
				cb.setDebugEnabled(true)
				.setOAuthConsumerKey("c7X647iwEHvSF8Eu4CIRulro0")
				.setOAuthConsumerSecret("T4Pof1honeCyrvNhuEnR0Pel1fcRreSzkqQqx6a2YFNI2NELcF")
				.setOAuthAccessToken(accessToken)
				.setOAuthAccessTokenSecret(accessTokenSecret);
				
				TwitterFactory tf = new TwitterFactory(cb.build());
				Twitter twitter = tf.getInstance();
				
				try {
					
					//사용자 정보 가져옴
					twitter4j.User user = twitter.verifyCredentials();
					
					
					long userId = user.getId(); //유저 고유 ID값.
					
					
					
					String screenName = user.getScreenName();
					System.out.println("--------------------");
					System.out.println(screenName);
					System.out.println("--------------------");
					String name = user.getName();
					String profileImgUrl = user.getProfileImageURL();
					
					System.out.println("트위터 콜백 서블릿 테스트 출력(userId) : "+ userId);
					System.out.println("트위터 콜백 서블릿 테스트 출력(screenName) : "+ screenName);
					System.out.println("트위터 콜백 서블릿 테스트 출력(name) : "+ name);
					System.out.println("트위터 콜백 서블릿 테스트 출력(트위터 프로필이미지) : " + profileImgUrl);
					
					//정보를 세션에 저장
					request.getSession().setAttribute("twitterId", userId);
					request.getSession().setAttribute("twitterScreenName", screenName);
					request.getSession().setAttribute("twitterName", name);
					request.getSession().setAttribute("twitterProfileImgUrl", profileImgUrl);
					
					// 종료 후 로그인 컨트롤러로 보내서 기존회원인지 검증
					response.sendRedirect("LoginC");
					
					
					
				} catch (TwitterException te) {
					te.printStackTrace();
					throw new ServletException(te);
				}
				
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
