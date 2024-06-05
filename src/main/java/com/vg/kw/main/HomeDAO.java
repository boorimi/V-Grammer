package com.vg.kw.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.vg.ds.trade.TradeCommentsDTO;
import com.vg.ds.trade.TradeDAO;
import com.vg.ds.trade.TradeDTO;
import com.vg.ignore.DBManager;

public class HomeDAO {

	private ArrayList<LiveStreamDTO> liveStreams = null;
//	private ArrayList<TradeCommentsDTO> tradeComments = null;
	private Connection con = null;
	public static final HomeDAO HDAO = new HomeDAO();

	private HomeDAO() {
		try {
			con = DBManager.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
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

	public void getLiveUrl(HttpServletRequest request) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			String sql = "select a_m_pk, a_address from haco_address ";
				sql	+= "where a_category = 'YchannelID' order by a_m_pk asc";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			liveStreams = new ArrayList<LiveStreamDTO>();
			
			while (rs.next()) {
				
				LiveStreamDTO l = new LiveStreamDTO(rs.getString(1), rs.getString(2));
				liveStreams.add(l);
			
				
			}
			for (LiveStreamDTO liveStreamDTO : liveStreams) {
				System.out.println(liveStreamDTO);
			}

//			LiveStreamDTO channelId = liveStreams.get(1);
//
//			String urlString = "https://www.youtube.com/channel/" + channelId + "/live";
//
//			Document doc = Jsoup.connect(urlString).get();
//			String html = doc.html();
//
//			// '시청 중' 문자열을 통해 라이브 스트림 여부를 확인합니다.
//			if (html.contains("시청 중")) {
//				String videoId = null;
//
//				// HTML에서 videoId를 찾습니다.
//				String videoIdPrefix = "\"videoId\":\"";
//				int startIndex = html.indexOf(videoIdPrefix);
//				if (startIndex != -1) {
//					startIndex += videoIdPrefix.length();
//					int endIndex = html.indexOf("\"", startIndex);
//					if (endIndex != -1) {
//						videoId = html.substring(startIndex, endIndex);
//					}
//				}
//
//				if (videoId != null) {
//					String videoUrl = "https://www.youtube.com/watch?v=" + videoId;
//					System.out.printf("Live Stream Video ID: %s, URL: %s\n", videoId, videoUrl);
//				} else {
//					System.out.println("라이브중이지만 videoId를 찾을 수 없습니다.");
//				}
//			} else {
//				System.out.println("라이브중이 아닙니다.");
//			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(null, null, null);
		}
	}

}
