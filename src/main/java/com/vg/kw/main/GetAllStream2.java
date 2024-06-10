package com.vg.kw.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vg.ignore.DBManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.Credential.Builder;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.LiveBroadcastListResponse;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.services.youtube.model.LiveBroadcast;

import java.security.GeneralSecurityException;
import java.util.List;

public class GetAllStream2 {

	private static final String CLIENT_SECRETS = "C:\\kds\\client_secret.json";
	private static final Collection<String> SCOPES = Collections.singletonList(YouTubeScopes.YOUTUBE_READONLY);
	private static final String APPLICATION_NAME = "Your Application Name";
	private static final String API_KEY = "AIzaSyBzwtozuKqzf_5_3G8r17ZFntNFxBSwOu8"; // 여기에 API 키를 넣어주세요
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	private ScheduledExecutorService executorService;

	public static void getLiveUrl(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		System.setProperty("webdriver.chrome.driver", "C:\\kds\\chromedriverwin64\\chromedriver.exe"); // 크롬 드라이버 경로 설정
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless"); // 브라우저가 눈에 보이지 않게 설정
		WebDriver driver = new ChromeDriver(options);

		try {
			// 멤버pk와 유튜브 고유ID를 배열로 저장하는 코드
			String sql = "select a_m_pk, a_address from haco_address ";
			sql += "where a_category = 'YchannelID' order by a_m_pk asc";

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			ArrayList<GetAllStreamDTO> getAllStreams = new ArrayList<GetAllStreamDTO>();

			while (rs.next()) {

				GetAllStreamDTO g = new GetAllStreamDTO(rs.getString(1), rs.getString(2));
				getAllStreams.add(g);

			}

			rs.close();
			pstmt.close();

			String sql2 = null;
			String sql3 = null;
			int i = 1;

			for (GetAllStreamDTO g : getAllStreams) {
				String channelId = g.getYchannelID();

				String url = "https://www.youtube.com/channel/" + channelId + "/live";
				driver.get(url);

				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement liveBadge = wait.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("#info.style-scope.ytd-watch-info-text")));
				String streaming = liveBadge.getText();
				// DB안에 이미 자료가 있는지 없는지 판단하는 코드
				sql2 = "select count(*), c_address  from haco_currentlivestream ";
				sql2 += "where c_m_pk = ?";

				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, g.getPk());
				rs = pstmt.executeQuery();
				rs.next();

				System.out.print(i + " ");
				String currentUrl = driver.getCurrentUrl();
				// "="의 인덱스를 찾습니다.
				int index = currentUrl.indexOf("=");

				// "=" 이후의 부분 문자열을 가져옵니다.
				String afterEquals = currentUrl.substring(index + 1);
				// "스트리밍 시작" 으로 검색하여 라이브 여부를 판단한다.
				if (streaming.contains("스트리밍 시작")) {

					if (rs.getInt(1) == 0) {

						sql3 = "insert into haco_currentlivestream values ";
						sql3 += "(null,?,?)";

						rs.close();
						pstmt.close();

						pstmt = con.prepareStatement(sql3);
						pstmt.setString(1, g.getPk());
						pstmt.setString(2, afterEquals);
						if (pstmt.executeUpdate() == 1) {
							System.out.println("새로운 방송 시작. 입력 성공.");
						}

					} else if (!rs.getString(2).equals(afterEquals)) {
						sql3 = "delete from haco_currentlivestream ";
						sql3 += "where c_m_pk = ?";

						rs.close();
						pstmt.close();

						pstmt = con.prepareStatement(sql3);
						pstmt.setString(1, g.getPk());
						if (pstmt.executeUpdate() == 1) {
							System.out.println("새로운 라이브 감지. 데이터 삭제 성공");
						}
						
						sql3 = "insert into haco_currentlivestream values ";
						sql3 += "(null,?,?)";

						rs.close();
						pstmt.close();

						pstmt = con.prepareStatement(sql3);
						pstmt.setString(1, g.getPk());
						pstmt.setString(2, afterEquals);
						if (pstmt.executeUpdate() == 1) {
							System.out.println("새로운 방송 시작. 입력 성공.");
						}
						
					} else {
						System.out.println("이미 자료가 있음(갱신X)");
					}

				} else {
					if (rs.getInt(1) == 1) {
						sql3 = "delete from haco_currentlivestream ";
						sql3 += "where c_m_pk = ?";

						rs.close();
						pstmt.close();

						pstmt = con.prepareStatement(sql3);
						pstmt.setString(1, g.getPk());
						if (pstmt.executeUpdate() == 1) {
							System.out.println("라이브 종료. 데이터 삭제 성공");
						}

					} else {
						System.out.println("라이브 없음");
					}
				}
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
			driver.quit();
		}
	}

	public static void getLiveUrl2(Object object) throws IOException {

		// Build and authorize the YouTube API client
		final NetHttpTransport httpTransport = new NetHttpTransport();
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new FileReader(CLIENT_SECRETS));
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
				clientSecrets, SCOPES).setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
				.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8080).build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

		// Build the YouTube service
		YouTube youtubeService = new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();

		// Fetch live stream info
		YouTube.LiveBroadcasts.List request = youtubeService.liveBroadcasts().list("snippet,contentDetails,status")
				.setBroadcastStatus("all");
		LiveBroadcastListResponse response = request.execute();
		System.out.println(response);

		response.getItems().forEach(broadcast -> {
			System.out.println("Title: " + broadcast.getSnippet().getTitle());
			System.out.println("Description: " + broadcast.getSnippet().getDescription());
			System.out.println("Scheduled Start Time: " + broadcast.getSnippet().getScheduledStartTime());
		});

	}

}
