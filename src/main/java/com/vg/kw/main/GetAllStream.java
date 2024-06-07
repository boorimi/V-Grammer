package com.vg.kw.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

public class GetAllStream {

	public static void getAllLive() {
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

		// 주기적으로 실행할 작업
		Runnable task = () -> {
			try {
				getLiveUrl(null); // 인자는 null로 전달하거나 필요한 객체를 전달하세요.
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

		// 10분마다 작업 반복 실행
		executorService.scheduleAtFixedRate(task, 0, 5, TimeUnit.MINUTES);
	}

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
				sql2 = "select count(*) from haco_currentlivestream ";
				sql2 += "where c_m_pk = ?";

				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, g.getPk());
				rs = pstmt.executeQuery();
				rs.next();

				System.out.print(i+" ");
				// "스트리밍 시작" 으로 검색하여 라이브 여부를 판단한다.
				if (streaming.contains("스트리밍 시작")) {

					if (rs.getInt(1) == 0) {
						String currentUrl = driver.getCurrentUrl();
//						System.out.println("Live stream URL: " + currentUrl);
						// "="의 인덱스를 찾습니다.
						int index = currentUrl.indexOf("=");

						// "=" 이후의 부분 문자열을 가져옵니다.
						String afterEquals = currentUrl.substring(index + 1);
						
						
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

}
