package com.vg.kw.archive;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GetLiveStartTime {

	public static String getLiveStartTime(String videoId) {

		String actualStartTime = null;

		try {

			String url2 = null;
			URL u2 = null;

			url2 = "https://www.googleapis.com/youtube/v3/videos";
			url2 += "?part=liveStreamingDetails";
			url2 += "&id=" + videoId;
			url2 += "&key=AIzaSyDaJwlJQy--MSHz_DRBAcvTi2tc4Cg78CY";
			u2 = new URL(url2);

			HttpsURLConnection huc2 = (HttpsURLConnection) u2.openConnection();
			InputStream is2 = huc2.getInputStream();
			InputStreamReader isr2 = new InputStreamReader(is2, "utf-8");

			// JSON 파서 객체 생성
			JSONParser jp2 = new JSONParser();
			JSONObject youtubeData2 = (JSONObject) jp2.parse(isr2);
			JSONArray items = (JSONArray) youtubeData2.get("items");

			JSONObject item = (JSONObject) items.get(0);
			///////////////////////
			JSONObject liveStreamingDetails = null;
			liveStreamingDetails = (JSONObject) item.get("liveStreamingDetails");
			
			if (liveStreamingDetails == null) {
				actualStartTime = "";
			}else if ((String) liveStreamingDetails.get("actualStartTime") != null) {
				actualStartTime = (String) liveStreamingDetails.get("actualStartTime");
			} else if ((String) liveStreamingDetails.get("scheduledStartTime") != null) {
				actualStartTime = "notStartLiveYet";
			} else {
				actualStartTime = "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return actualStartTime;
	}

}
