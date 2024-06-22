package com.vg.jw.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;

@WebServlet("/TwitterLoginServlet")
public class TwitterLoginServlet extends HttpServlet {
	// https://developer.x.com/en/portal/projects/1797437355793747968/apps/28863621/keys
	// 에서 키 정보 확인 가능
	// KEY, URL은 필드에 정의
	// CONSUMER = 기능을 사용하는 hakonaka 사이트
	private static final String CONSUMER_KEY = "gy2OsmzCmmbslkC5QMj46aD4L";
	private static final String COUNSUMER_SECRET = "Sgv9cF3R5BXves1VterhrKQSikGtQsU0G7Pt9nU0fqBkVC20TS";

	// 로그인 완료 후 호출될 URL
	private static final String CALLBACK_URL = "http://localhost/V-Grammer/TwitterCallbackServlet";
//	private static final String CALLBACK_URL = "http://haconaka.com/V-Grammer/TwitterCallbackServlet";

	// OAuth인증을 수행하기 위한 consumer(hakonaka)정보/provider(트위터)정보
	private OAuthConsumer consumer; // consumer key, consumer secret가 들어감
	private OAuthProvider provider; // 요청 토큰의 url, 사용자 승인url, 액세스 토큰url이 들어감

	
	//Servlet초기화될 때  OAuthConsumer와 OAuthProvider 객체를 초기화하고, 이들 객체에 필요한 정보를 설정
	@Override
	public void init() throws ServletException{
		System.out.println("트위터로그인 진입");
		//hakonaka사이트의 정보
		consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, COUNSUMER_SECRET);
		
		//OAuthProvider객체 => 트위터 서버랑 통신 담당.
		//리퀘스트토큰 엔드포인트url, 액세스토큰 엔드포인트 url, 사용자 승인 엔드포인트 url
		provider = new CommonsHttpOAuthProvider("https://api.twitter.com/oauth/request_token",
				"https://api.twitter.com/oauth/access_token", "https://api.twitter.com/oauth/authorize");
//		System.out.println(consumer);
//		System.out.println(provider);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("get 진입 성공");
//			System.out.println(consumer);
//			System.out.println(CALLBACK_URL);

			// provider 객체를 사용하여 Twitter로부터 요청 토큰을 받아옴.
			// consumer객체+콜백url로 트위터api에 리퀘스트토큰 요청 
			// -> 트위터가 리퀘스트토큰을 주면 
			//.retrieveRequestToken()이 트위터 인증 페이지(로그인/권한부여)로 리다이렉트할 URL생성하고 반환해줌 
			String authUrl = provider.retrieveRequestToken(consumer, CALLBACK_URL);
			
			//리퀘스트 토큰 관련 정보를 세션에 저장, callback할때 불러서 사용
			request.getSession().setAttribute("oauthConsumer", consumer);
			request.getSession().setAttribute("oauthProvider", provider);
			
			// 유저를 Twitter의 인증 페이지로.
			response.sendRedirect(authUrl);

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
