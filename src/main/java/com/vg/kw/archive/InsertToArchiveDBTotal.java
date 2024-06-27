package com.vg.kw.archive;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.vg.ignore.DBManager;
import com.vg.kw.main.YoutubeIDDTO;

public class InsertToArchiveDBTotal {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			// 여기서는 search를 사용하여 100개씩 소비하되 날짜와 채널ID를 변수로
			// 원하는 만큼 가져와 담기를 할것

			String sql = "SELECT * FROM haco_address ";
			sql += "where a_category = 'YchannelID' ";
			sql += "and a_m_pk = ?";

			connection = DBManager.connect();
			statement = connection.prepareStatement(sql);
			// a_m_pk 변수 수동으로 수정해주기
			statement.setInt(1, 1);
			rs = statement.executeQuery();
			rs.next();

			String pk = rs.getString(1);
			String m_pk = rs.getString(2);
			String category = rs.getString(3);
			String address = rs.getString(4);

			YoutubeIDDTO y = new YoutubeIDDTO(pk, m_pk, category, address);

			String url = "https://www.googleapis.com/youtube/v3/search";
			url += "?part=snippet";
			url += "&maxResults=50";
			url += "&channelId=" + y.getAddress();
			url += "&order=date";
			url += "&publishedBefore=2024-04-10T00:00:00Z";
			url += "&type=video";
			url += "&key=AIzaSyDaJwlJQy--MSHz_DRBAcvTi2tc4Cg78CY";

			URL u = new URL(url);
			HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();
			InputStream is = huc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");

			// Json parse 객체 (JSON 파싱 하려고)
			JSONParser jp = new JSONParser();
			JSONObject youtubeDAta = (JSONObject) jp.parse(isr);
			JSONArray items = (JSONArray) youtubeDAta.get("items");

			// 인서트 sql문
			String sql2 = "INSERT INTO haco_archive (a_pk, a_m_pk, a_date, a_time, a_collabo,a_collabomember, a_category, a_title, a_thumbnail,a_videoid) VALUES (null, ?, ?, ?, '未分類', '未分類', '未分類', ?, ?, ?)";

			// videoId값이 있는지 없는지에 대해 확인하기위한 sql문
			String sql3 = "select count(*) from haco_archive where a_videoId = ?";

			for (int i = 0; i < items.size(); i++) {
				statement = connection.prepareStatement(sql3);

				// videoId 추출
				JSONObject item = (JSONObject) items.get(i);
				JSONObject resourceId = (JSONObject) item.get("id");
				String videoId = (String) resourceId.get("videoId");

				// 제목, 채널ID 추출
				JSONObject snippet = (JSONObject) item.get("snippet");
				String title = (String) snippet.get("title");
				String ChannelId = (String) snippet.get("videoOwnerChannelId");

				// 기본 썸네일 URL 추출
				JSONObject thumbnails = (JSONObject) snippet.get("thumbnails");
				String defaultThumbnailUrl = ((JSONObject) thumbnails.get("default")).get("url").toString();

				// videoId를 통해 라이브 시작시간 가져오기

				String publishedAt = null;
				if (GetLiveStartTime.getLiveStartTime(videoId).equals("notStartLiveYet")) {
					continue;
				} else if (GetLiveStartTime.getLiveStartTime(videoId) != "") {
					publishedAt = GetLiveStartTime.getLiveStartTime(videoId);
				} else {
					publishedAt = (String) snippet.get("publishedAt");
				}

				// Published At에서 날짜와 시간 분리
				String[] dateTime = publishedAt.split("T");
				String date = dateTime[0];
				String time = dateTime[1].substring(0, 8); // 초단위는 무시


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

				// 데이터베이스에 값 삽입
				statement = connection.prepareStatement(sql2);

				// 시간을 9시간 더해주기
				String[] timeComponents = time.split(":");
				Time sqlTime = Time.valueOf(timeComponents[0] + ":" + timeComponents[1] + ":" + timeComponents[2]);
				LocalTime localTime = sqlTime.toLocalTime();
				LocalTime newLocalTime = localTime.plus(9, ChronoUnit.HOURS);
				Time newSqlTime = Time.valueOf(newLocalTime);

				System.out.println("ChannelId: " + ChannelId);
				System.out.println("VideoId: " + videoId);
				System.out.println("Date: " + date);
				System.out.println("Time: " + time);
				System.out.println("Title: " + title);
				System.out.println("Default Thumbnail URL: " + defaultThumbnailUrl);
				System.out.println();

				statement.setString(1, y.getM_pk());
				statement.setDate(2, Date.valueOf(date));
				statement.setTime(3, newSqlTime);
				statement.setString(4, title);
				statement.setString(5, defaultThumbnailUrl);
				statement.setString(6, videoId);

				if (statement.executeUpdate() > 0) {
					System.out.println("A new row has been inserted successfully!");
					System.out.println();
					statement.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(connection, statement, rs);
		}
	}
}
