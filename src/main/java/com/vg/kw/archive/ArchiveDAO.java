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
                JSONObject resourceId = (JSONObject) snippet.get("resourceId");
                
                String videoId = (String) resourceId.get("videoId");
                System.out.println(videoId);
//                System.out.println(resourceId);
                
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
        String sql  = "SELECT ha.*, hm.m_name, hi.i_icon from haco_archive ha, haco_member hm, haco_image hi\n"
        		+ "where ha.a_m_pk = hm.m_pk and hi.i_m_pk = hm.m_pk";
        try {
            con = DBManager.connect();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ArrayList<ArchiveDTO> archives = new ArrayList<>();
            while (rs.next()) {
                ArchiveDTO archive = new ArchiveDTO();
                
                archive.setA_pk(rs.getInt(1));
                archive.setA_m_pk(rs.getInt(2));
                archive.setA_date(rs.getDate(3));
                archive.setA_time(rs.getTime(4));
                archive.setA_collabo(rs.getString(5));
                archive.setA_collabomember(rs.getString(6));
                archive.setA_category(rs.getString(7));
                archive.setA_title(rs.getString(8));
                archive.setA_thumbnail(rs.getString(9));
                archive.setA_videoid(rs.getString(10));
                archive.setM_name(rs.getString(11));
                archive.setI_icon(rs.getString(12));
                archives.add(archive);
                
            }
            req.setAttribute("archives", archives);
            System.out.println("Archives fetched: " + archives.size()); // 디버깅 메시지
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(con, pstmt, rs);
        }
    }

    public static void UpdateArchive(HttpServletRequest request) {
        Connection con = null;
        PreparedStatement pstmt = null;

        // Get parameters from request
        String collabo = request.getParameter("collabo");
        String collabomember = request.getParameter("collabomember");
        String category = request.getParameter("category");
        String a_pk = request.getParameter("a_pk"); // Unique identifier

        // Validate that necessary parameters are not null or empty
        if (collabo == null || collabomember == null || category == null || a_pk == null) {
            System.out.println("One or more parameters are missing.");
            return;
        }

        // Construct the SQL query
        String sql = "UPDATE haco_archive ha " +
                     "JOIN haco_member hm ON ha.a_m_pk = hm.m_pk " +
                     "JOIN haco_image hi ON hi.i_m_pk = hm.m_pk " +
                     "SET ha.a_collabo = ?, " +
                     "    ha.a_collabomember = ?, " +
                     "    ha.a_category = ? " +
                     "WHERE ha.a_pk = ?";  // Unique condition

        try {
            // Connect to the database
            con = DBManager.connect();
            // Prepare the SQL statement
            pstmt = con.prepareStatement(sql);
            // Set the parameters
            pstmt.setString(1, collabo);
            pstmt.setString(2, collabomember);
            pstmt.setString(3, category);
            pstmt.setString(4, a_pk); // Set the unique title

            // Execute the update
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update successful. Rows updated: " + rowsUpdated);
            } else {
                System.out.println("No rows updated. Check your condition.");
            }
        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        } finally {
            // Close the database resources
            DBManager.close(con, pstmt, null);
        }
    }



}
