package com.TXST.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Proxy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class NetBug {

	public static List<String> getImgLabel(String url, String regex) throws Exception {
		

		HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
		urlConnection.setRequestMethod("POST");  
		urlConnection.setInstanceFollowRedirects(false);
//		urlConnection.setFollowRedirects(false);
		urlConnection.connect();
		
		System.out.println("in list");
		System.out.println(urlConnection.getResponseCode());
//		if (urlConnection.getResponseCode()==302||urlConnection.getResponseCode()==301) {
//			
//			url = new URL(urlConnection.getHeaderField("Location"));
//			urlConnection = (HttpURLConnection) url.openConnection();
//			
//		}
//		BufferedReader bReader = new BufferedReader(new FileReader(file));
		
		BufferedReader bReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
		Pattern pattern = Pattern.compile(regex);

		List<String> list = new ArrayList<String>();

		String line = null;
		while ((line = bReader.readLine()) != null) {

			Matcher matcher = pattern.matcher(line);
			while (matcher.find()) {
				if (matcher.group().contains("alt")) {

					list.add(matcher.group());
				}
			}
		}
		bReader.close();
		return list;
	}

	public static List<Map<String, String>> getNameAndUrl(String url, String regex) throws Exception {

		Pattern pattern;
		Matcher matcher;
		
		List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
		System.out.println("in list map");

		List<String> list = getImgLabel(url, regex);
		for (String string : list) {

			pattern = Pattern.compile("alt=\"[^\"]+");
			matcher = pattern.matcher(string);
			Map<String, String> map = new HashMap<String, String>();

			if (matcher.find()) {

				map.put("alt", matcher.group().replace("alt=\"", ""));
			}

			pattern = Pattern.compile("src=\"[^\"]+");
			matcher = pattern.matcher(string);
			if (matcher.find()) {

				map.put("src", matcher.group().replace("src=\"", ""));
			}

			pattern = Pattern.compile("data-lazyload=\"[^\"]+");
			matcher = pattern.matcher(string);
			if (matcher.find()) {

				map.put("data-lazyload", matcher.group().replace("data-lazyload=\"", ""));
			}
			listMap.add(map);
		}

		return listMap;
	}
	
	public static void SaveImgInfo(String alt,String src,String data_lazyload) throws Exception {
		
		Connection connection = connectionFactory.getConnection();
		
		String sql = "insert into Img (alt,src,data_lazyload) values (?,?,?)";
		PreparedStatement pStatement = connection.prepareCall(sql);
		pStatement.setString(1, alt);
		pStatement.setString(2, src);
		pStatement.setString(3, data_lazyload);
		
		pStatement.execute();
		
	}
	

	@Test
	public void NetBugTest() throws Exception {
		
//		File file = new File("D:\\1.txt");
		String regex = "<img[^>]+>";
		
//		String url = "http://meitu.fuwo.com/plist-p0p0p0p0/";  //p11p12p12p0
		for(int i=1;i<13;i++){
			
			String changeUrl = ""+i;
			String url = "http://meitu.fuwo.com/plist-p0p0p"+changeUrl+"p0/";
			
			List<Map<String, String>> result = getNameAndUrl(url, regex);
			System.out.println(result.size());
			for (Map<String, String> map : result) {
//				System.out.println(map.get("alt"));
//				System.out.println(map.get("src"));
//				System.out.println(map.get("data-lazyload"));
//				System.out.println();
			SaveImgInfo(map.get("alt"), map.get("src"), map.get("data-lazyload"));
			}
			
		}
		
		
//		 List<String> result = getImgLabel(url, regex);
//		
//		 System.out.println(result.size());
//		 for (int i=0;i<result.size();i++) {
//		 System.out.println(result.get(i));
//		 }

	}

}
