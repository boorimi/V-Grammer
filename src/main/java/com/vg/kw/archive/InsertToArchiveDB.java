package com.vg.kw.archive;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.vg.ignore.DBManager;
import com.vg.kw.main.YoutubeIDDTO;

public class InsertToArchiveDB {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {

			// DB에서 각 멤버의 YchannelID, YuploadPLID 받아오기

			String sql = "SELECT * FROM haco_address ";
			sql += "where a_category in ('YchannelID','YuploadPLID')";

			connection = DBManager.connect();
			statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();

			ArrayList<YoutubeIDDTO> youtubeIds = new ArrayList<YoutubeIDDTO>();

			while (rs.next()) {

				String pk = rs.getString(1);
				String m_pk = rs.getString(2);
				String category = rs.getString(3);
				String address = rs.getString(4);

				YoutubeIDDTO y = new YoutubeIDDTO(pk, m_pk, category, address);
				youtubeIds.add(y);
			}
			String url = null;
			URL u = null;
			for (YoutubeIDDTO y : youtubeIds) {
				if (y.getCategory().equals("YuploadPLID")) {
					// YouTube API 호출
					url = "https://www.googleapis.com/youtube/v3/playlistItems";
					url += "?part=snippet";
					url += "&maxResults=20";
					url += "&status=";
					url += "&nextPageToken=";
					url += "&playlistId=" + y.getAddress();
					url += "&key=AIzaSyBzwtozuKqzf_5_3G8r17ZFntNFxBSwOu8";
					u = new URL(url);
				} else {
					continue;
				}

				HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();
				InputStream is = huc.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "utf-8");

				// JSON 파서 객체 생성
				JSONParser jp = new JSONParser();
				JSONObject naverData = (JSONObject) jp.parse(isr);
				JSONArray items = (JSONArray) naverData.get("items");

				// 데이터베이스 연결

				String sql2 = "INSERT INTO haco_archive (a_pk, a_m_pk, a_date, a_time, a_collabo,a_collabomember, a_category, a_title, a_thumbnail,a_videoid) VALUES (null, ?, ?, ?, '未分類', '未分類', '未分類', ?, ?, ?)";

				String sql3 = "select count(*) from haco_archive where a_videoId = ?";
				
				for (int i = 0; i < items.size(); i++) {
					statement = connection.prepareStatement(sql3);
					JSONObject item = (JSONObject) items.get(i);
					JSONObject snippet = (JSONObject) item.get("snippet");

					String title = (String) snippet.get("title");
					String publishedAt = (String) snippet.get("publishedAt");
					String ChannelId = (String) snippet.get("videoOwnerChannelId");
					JSONObject resourceId = (JSONObject) snippet.get("resourceId");

					String videoId = (String) resourceId.get("videoId");
//                System.out.println(resourceId);

					// Published At에서 날짜와 시간 분리
					String[] dateTime = publishedAt.split("T");
					String date = dateTime[0];
					String time = dateTime[1].substring(0, 8); // 초단위는 무시

					// 기본 썸네일 URL 추출
					JSONObject thumbnails = (JSONObject) snippet.get("thumbnails");
					String defaultThumbnailUrl = ((JSONObject) thumbnails.get("default")).get("url").toString();

                System.out.println("ChannelId: " + ChannelId);
                System.out.println("Date: " + date);
                System.out.println("Time: " + time);
                System.out.println("Title: " + title);
                System.out.println("Default Thumbnail URL: " + defaultThumbnailUrl);
                System.out.println("videoId : " + videoId);
                System.out.println();

					// videoId 값 대조 후에 있으면 continue
					statement.setString(1, videoId);
					rs = statement.executeQuery();
					rs.next();
					
					if (rs.getInt(1) >= 1) {
						rs.close();
						statement.close();
						continue;
					}
					
					rs.close();
					statement.close();
					
					statement = connection.prepareStatement(sql2);
					// 데이터베이스에 값 삽입
					String[] timeComponents = time.split(":");
					Time sqlTime = Time.valueOf(timeComponents[0] + ":" + timeComponents[1] + ":" + timeComponents[2]);

					statement.setString(1, y.getM_pk());
					statement.setDate(2, Date.valueOf(date));
					statement.setTime(3, sqlTime);
					statement.setString(4, title);
					statement.setString(5, defaultThumbnailUrl);
					statement.setString(6, videoId);

					
					if (statement.executeUpdate() > 0) {
						System.out.println("A new row has been inserted successfully!");
						System.out.println();
						statement.close();
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 리소스 닫기
			DBManager.close(connection, statement, null);
		}
	}
}
