package com.vg.kw.archive;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import javax.servlet.http.HttpServletRequest;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.vg.ignore.DBManager;

public class ArchiveDAO {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // YouTube API 호출
            String url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&status=&nextPageToken=&playlistId=UUXuXvC53isoiOUJKpUMSfLQ&key=AIzaSyBzwtozuKqzf_5_3G8r17ZFntNFxBSwOu8";
            URL u = new URL(url);
            
            HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();
            InputStream is = huc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");

            
            
            // JSON 파서 객체 생성
            JSONParser jp = new JSONParser();
            JSONObject naverData = (JSONObject) jp.parse(isr);
            JSONArray items = (JSONArray) naverData.get("items");

            // 데이터베이스 연결
            connection = DBManager.connect();
            String sql = "INSERT INTO haco_archive (a_pk, a_m_pk, a_date, a_time, a_collabo,a_collabomember, a_category, a_title, a_thumbnail) VALUES (null, ?, ?, ?, '未分類', '未分類', '未分類', ?, ?)";
            
            statement = connection.prepareStatement(sql);
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

                System.out.println("Date: " + date);
                System.out.println("Time: " + time);
                System.out.println("Title: " + title);
                System.out.println("Default Thumbnail URL: " + defaultThumbnailUrl);
                System.out.println();
                
                // 데이터베이스에 값 삽입
                String[] timeComponents = time.split(":");
                Time sqlTime = Time.valueOf(timeComponents[0] + ":" + timeComponents[1] + ":" + timeComponents[2]);
               
//                statement.setInt(1, 5);
//                statement.setDate(2, Date.valueOf(date));
//                statement.setTime(3, sqlTime);
//                statement.setString(4, title);
//                statement.setString(5, defaultThumbnailUrl);
              

               
                statement.setInt(1, 5);
              	statement.setDate(2, Date.valueOf(date));
              	statement.setTime(3, sqlTime);
              	statement.setString(4, title);
              	statement.setString(5, defaultThumbnailUrl);


                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new row has been inserted successfully!");
                }
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 리소스 닫기
            DBManager.close(connection, statement, null);
        }
        
    }
    
    public static void selectAllArchive(HttpServletRequest req) {
    		Connection con = null;
    		PreparedStatement pstmt = null;
    		ResultSet rs = null;
    		String sql  = "select * from asdasdds";
    		try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				ArchiveDTO archive = null;
				ArrayList<ArchiveDTO> archives = new ArrayList<ArchiveDTO>();
				while (true) {
					archive = new ArchiveDTO();
					
					archive.setA_pk(rs.getInt(1));
					archive.setA_m_pk(rs.getInt(2));
					archive.setA_date(rs.getDate(3));
					archive.setA_time(rs.getDate(4));
					archive.setA_title(rs.getString(8));
					
					archives.add(archive);
					req.setAttribute("archives", archives);
				}
				
    			
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(con, pstmt, rs);
			}
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    	
    }
    
    
}
