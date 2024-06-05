package com.vg.kw.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vg.ignore.DBManager;

import java.io.IOException;

public class JsoupTest {
	public static int findFirstDifference(String str1, String str2) {
		int minLength = Math.min(str1.length(), str2.length());

		// Compare characters until a difference is found or the end of the shorter
		// string is reached
		for (int i = 0; i < minLength; i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				return i;
			}
		}

		// If no difference found within the length of the shorter string,
		// check if the strings are of different lengths
		if (str1.length() != str2.length()) {
			return minLength;
		}

		return -1; // Strings are identical
	}

	public static void main(String[] args) {

		// 확인하려는 유튜브 채널 ID
		

		try {
			
			con = DBManager.connect();
			
			
			
			String channelId = "UCsDM8jwzGNt40TWcA9y1dAQ";
			
			String urlString = "https://www.youtube.com/channel/" + channelId + "/live";

			Document doc = Jsoup.connect(urlString).get();
			String html = doc.html();

			// '시청 중' 문자열을 통해 라이브 스트림 여부를 확인합니다.
			if (html.contains("시청 중")) {
				String videoId = null;

				// HTML에서 videoId를 찾습니다.
				String videoIdPrefix = "\"videoId\":\"";
				int startIndex = html.indexOf(videoIdPrefix);
				if (startIndex != -1) {
					startIndex += videoIdPrefix.length();
					int endIndex = html.indexOf("\"", startIndex);
					if (endIndex != -1) {
						videoId = html.substring(startIndex, endIndex);
					}
				}
				
				if (videoId != null) {
					String videoUrl = "https://www.youtube.com/watch?v=" + videoId;
					System.out.printf("Live Stream Video ID: %s, URL: %s\n", videoId, videoUrl);
				} else {
					System.out.println("라이브중이지만 videoId를 찾을 수 없습니다.");
				}
			} else {
				System.out.println("라이브중이 아닙니다.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, null, null);
		}
	}

}