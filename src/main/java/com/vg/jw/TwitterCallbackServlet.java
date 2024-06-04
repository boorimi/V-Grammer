package com.vg.jw;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;

@WebServlet("/TwitterCallbackServlet")
public class TwitterCallbackServlet extends HttpServlet {
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("callback 실행");
		//consumer = 기능을 사용하는 hakonaka 사이트
		OAuthConsumer consumer = (OAuthConsumer) request.getSession().getAttribute("oauthConsumer");
		//provider = API제공해주는 트위터 
		OAuthProvider provider = (OAuthProvider) request.getSession().getAttribute("oauthProvider");
		String verifier = request.getParameter("oauth_verifier");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
