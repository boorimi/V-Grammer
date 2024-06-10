package com.vg.kw.main;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.vg.ignore.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class GetRecommendVtuber implements ServletContextListener {

	private ScheduledExecutorService executorService;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		executorService = Executors.newSingleThreadScheduledExecutor();

		Runnable task = () -> {
			try {
				getRecommendVtuber(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0); // 12시로 설정
		calendar.set(Calendar.MINUTE, 1); // 5분으로 설정
		calendar.set(Calendar.SECOND, 0); // 0초로 설정

		long initialDelay = calendar.getTimeInMillis() - System.currentTimeMillis();
		if (initialDelay < 0) {
			initialDelay += TimeUnit.DAYS.toMillis(1); // 다음 날 같은 시간으로 설정
		}

		executorService.scheduleAtFixedRate(task, initialDelay, TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (executorService != null && !executorService.isShutdown()) {
			executorService.shutdown();
		}
	}

	public static void getRecommendVtuber(Object obj) {
		System.out.println("Running getLiveUrl at " + new Date());
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql2 = "delete from haco_recommendvtuber";
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql2);

			if (pstmt.executeUpdate() >= 1) {

				System.out.println("삭제성공");
			}
			
			
			
			String sql = "insert into haco_recommendvtuber (rv_ar_pk, rv_ad_pk, rv_ad_m_pk, rv_ad_category, rv_ad_address, rv_ar_title, rv_ar_videoid, rv_i_icon, rv_m_name) ";
			sql += "SELECT ar.a_pk, ad.*, ar.a_title, ar.a_videoid, im.i_icon, m.m_name ";
			sql += "FROM haco_address ad ";
			sql += "JOIN haco_archive ar ON ar.a_m_pk = ad.a_m_pk ";
			sql += "JOIN haco_member m ON m.m_pk = ar.a_m_pk ";
			sql += "JOIN haco_image im On im.i_m_pk = m.m_pk ";
			sql += "JOIN ( SELECT a_pk FROM haco_archive ORDER BY RAND() LIMIT 1 ) ra ON ar.a_pk = ra.a_pk ";
			sql += "where not ad.a_category = 'YchannelID' and not ad.a_category = 'YuploadPLID' AND m.m_pk NOT IN (2, 3, 8)";
			
			pstmt.close();
			pstmt = con.prepareStatement(sql);

			if (pstmt.executeUpdate() >= 1) {

				System.out.println("입력성공");
			}

//			request.setAttribute("recommendVtuber", recommendVtuber);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);

		}
	}
}
