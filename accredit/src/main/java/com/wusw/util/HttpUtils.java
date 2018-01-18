package com.wusw.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpUtils {
	
	/**
	 * 以Get方式请求接口
	 * @param httpUrl
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String requestByGet(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl = httpUrl + "?" + httpArg;

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
	        //connection.setRequestProperty("apikey",  "您自己的apikey");
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally{
	    	try{
	    		if(reader!=null){
	    			reader.close();
	    		}
	    	}catch(Exception e2){
	    		e2.printStackTrace();
	    	}
	    	
	    }
	    return result;
	}
	
	
	/**
	 * 以Post方式请求接口
	 * @param httpUrl
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String requestByPost(String httpUrl, String httpArg) {
		 HttpClient client = new DefaultHttpClient();
		 HttpPost post = new HttpPost(httpUrl);
		 post.setHeader("Content-Type", "application/json");
		 post.addHeader("Authorization", "Basic YWRtaW46");
		 String result = "";
		 try {
		    StringEntity s = new StringEntity(httpArg, "utf-8");
		    // s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
		    post.setEntity(s);
		    // 发送请求
		    HttpResponse httpResponse = client.execute(post);
		    // 获取响应输入流
		    InputStream inStream = httpResponse.getEntity().getContent();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
		    StringBuilder strber = new StringBuilder();
		    String line = null;
		    while ((line = reader.readLine()) != null)
		          strber.append(line);
		    inStream.close();
		    result = strber.toString();
		    if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
		        System.out.println("请求服务器成功，做相应处理");
		    } else {
		        System.out.println("请求服务端失败");
		    }
		              
		 } catch (Exception e) {
		      System.out.println("请求异常");
		      throw new RuntimeException(e);
		 }
		  
		 return result;
	}
	
	
}
