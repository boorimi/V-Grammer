package com.vg.jw.login;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	private static final String SECRET_KEY = "T4Pof1honeCyrvNhuEnR0Pel1fcRreSzkqQqx6a2YFNI2NELcF"; // 비밀 키는 안전하게 관리되어야 합니다.

	public static String generateToken(String userId) {
		return Jwts.builder().setSubject(userId).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1시간 유효
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
}
