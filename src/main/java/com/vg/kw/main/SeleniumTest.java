package com.vg.kw.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest {

	public static void main(String[] args) {
        // Selenium WebDriver 설정
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // 크롬 드라이버 경로 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 브라우저가 눈에 보이지 않게 설정
        WebDriver driver = new ChromeDriver(options);

        try {
            String channelId = "UCIn9T6RZs9OyyKnGkvLRH8w"; // 확인하려는 유튜브 채널 ID
            String url = "https://www.youtube.com/channel/" + channelId + "/live";
            driver.get(url);

            // 라이브 스트림 상태 확인
            WebElement liveBadge = driver.findElement(By.cssSelector("button.ytp-live-badge"));

            if (liveBadge != null) {
                System.out.println("The channel is currently live streaming.");
                // liveBadge가 있는 경우 URL을 가져올 수 있습니다.
                String currentUrl = driver.getCurrentUrl();
                System.out.println("Live stream URL: " + currentUrl);
            } else {
                System.out.println("The channel is not live streaming.");
            }
        } catch (Exception e) {
            System.out.println("The channel is not live streaming.");
        } finally {
            // WebDriver 종료
            driver.quit();
        }
    }
	
}
