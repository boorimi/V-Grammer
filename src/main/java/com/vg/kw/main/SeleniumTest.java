package com.vg.kw.main;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

	public static void main(String[] args) {
        // Selenium WebDriver 설정
        System.setProperty("webdriver.chrome.driver", "C:\\kds\\chromedriverwin64\\chromedriver.exe"); // 크롬 드라이버 경로 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 브라우저가 눈에 보이지 않게 설정
        WebDriver driver = new ChromeDriver(options);

        try {
        	
        	// 아마미쿠리네 주소
			String channelId1 = "UCt0-gubsgxcYTGc8MsJSaHw";
			// 요루무 루쿠 주소
			String channelId2 = "UCsDM8jwzGNt40TWcA9y1dAQ";
			// 아카이 아카메 주소
			String channelId3 = "UCf0XdGC_TkebEQfoo63lg3A";

			String url = "https://www.youtube.com/channel/" + channelId1 + "/live";
            driver.get(url);

            // 라이브 스트림 상태 확인 style-scope yt-formatted-string bold
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement liveBadge = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#info.style-scope.ytd-watch-info-text")));
            String streaming = liveBadge.getText();

            if (streaming.contains("스트리밍 시작")) {
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
