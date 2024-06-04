package com.vg.kw.archive;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.vg.ignore.DBManager;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        try {
            String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&q=星ノ音コロン&order=date&type=video&key=AIzaSyDaJwlJQy--MSHz_DRBAcvTi2tc4Cg78CY";

            URL u = new URL(url);
            HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();

            InputStream is = huc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");

            // JSON 파서 객체 생성
            JSONParser jp = new JSONParser();
            JSONObject naverData = (JSONObject) jp.parse(isr);

            JSONArray items = (JSONArray) naverData.get("items");

            for (int i = 0; i < items.size(); i++) {
                JSONObject item = (JSONObject) items.get(i);
                JSONObject snippet = (JSONObject) item.get("snippet");

                String title = (String) snippet.get("title");
                String publishedAt = (String) snippet.get("publishedAt");
                
                // Published At에서 날짜와 시간 분리
                String[] dateTime = publishedAt.split("T");
                String date = dateTime[0];
                String time = dateTime[1].substring(0, 8); // 초단위는 무시

                // 기본 썸네일 URL 추출
                JSONObject thumbnails = (JSONObject) snippet.get("thumbnails");
                String defaultThumbnailUrl = ((JSONObject) thumbnails.get("default")).get("url").toString();

                System.out.println("Title: " + title);
                System.out.println("Date: " + date);
                System.out.println("Time: " + time);
                System.out.println("Default Thumbnail URL: " + defaultThumbnailUrl);
                System.out.println();
                
                try {
                	
                	Connection connection = DBManager.connect();
                    // 쿼리문 준비
                    String sql = "INSERT INTO haco_address VALUES ('-',?,?, '-','-',?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    
                    // 값 설정
                    statement.setString(1, date); 
                    statement.setString(2, time); 
                    statement.setString(3, defaultThumbnailUrl);
                    
                    // 쿼리 실행
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("A new row has been inserted successfully!");
                    }
                } catch (SQLException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


