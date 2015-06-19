package com.leadstec.apitestflow;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class GetUser {
	public int sendGetRequest(String url){
		try {
			HttpClient httpClient = new HttpClient();
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(2000);
			GetMethod getMethod = new GetMethod(url);
			
			int statusCode = httpClient.executeMethod(getMethod);
			
			if(statusCode != HttpStatus.SC_OK){
				System.out.println("API∑√Œ  ß∞‹");
			}else {
				BufferedReader reader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream()));
				StringBuffer stringBuffer = new StringBuffer();
				String result = "";
				while((result = reader.readLine())!=null){
					stringBuffer.append(result);
				}
				String ts = stringBuffer.toString();
				
				System.out.println(ts);
			}								
			httpClient.getHttpConnectionManager().closeIdleConnections(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return 1;
	}
}
