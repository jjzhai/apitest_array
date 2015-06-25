package com.leadstec.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class Share {
	/*
	 * ��TCAddress��ȡ���������ļ�
	 * ���������ļ����������������������aLTC���������aLExpectResult
	 */
	public int getTC(String TCAddress, ArrayList<String> aLTC, ArrayList<String> aLExpectResult) {
		int TCCount=0;
		try {    			
			File file = new File(TCAddress);//�����ļ�����
			FileInputStream fileInputStream=new FileInputStream(file);
			InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"GB2312");//��������					
			BufferedReader br = new BufferedReader(inputStreamReader); //������ȡ��
			
			//��ȡ����
			String temp;//��������    			
		    while((temp = br.readLine())!=null){
		    	aLTC.add(temp.substring(0,temp.indexOf(";")));//�ַ�������		    	
		    	aLExpectResult.add(temp.substring(temp.indexOf(";")+1));
		    	TCCount++;
		    }	
		    br.close();    		       		    
		} catch (Exception e) {	
			e.printStackTrace();
		}
		return TCCount;
	}
	
	/*
	 * ���ز�����������
	 */
	public String[] getTC(String TCAddress) throws Exception{
		Share share = new Share();
		
		ArrayList<String> aLTC = new ArrayList<String>();
		ArrayList<String> aLExpectResult =new ArrayList<String>();
	    
		int TCCount=0;
	    TCCount = share.getTC(TCAddress, aLTC, aLExpectResult);
	    
	    String[] tC = new String[TCCount];
	    
	    tC=aLTC.toArray(new String[0]);
	    
	    return tC;
	}
	
	/*
	 * ���������������
	 */
	public String[] getExpectResult(String TCAddress) throws Exception{
		Share share = new Share();
		
		ArrayList<String> aLTC = new ArrayList<String>();
		ArrayList<String> aLExpectResult =new ArrayList<String>();
	    
		int TCCount=0;
	    TCCount = share.getTC(TCAddress, aLTC, aLExpectResult);
	    
	    String[] expectResult = new String[TCCount];
	    
	    expectResult=aLExpectResult.toArray(new String[0]);
	    
	    return expectResult;
	}
	
	/*
	 * ����API����ʵ�ʽ��
	 * ������url
	 */
	public String callApi(String url){
		String actualResult="";
		
		try {
			HttpClient httpClient = new HttpClient();
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(2000);
			GetMethod getMethod = new GetMethod(url);
			
			int statusCode = httpClient.executeMethod(getMethod);
			
			if(statusCode != HttpStatus.SC_OK){
				actualResult = "API����ʧ��";
			}else {
				BufferedReader reader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream()));
				StringBuffer stringBuffer = new StringBuffer();
				String result = "";
				while((result = reader.readLine())!=null){
					stringBuffer.append(result);
				}
				actualResult = stringBuffer.toString();
			}	
			
			httpClient.getHttpConnectionManager().closeIdleConnections(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return actualResult;
	}
	
	/*
	 * ��ָ������outputContent�����ָ���ļ�outputAddress��
	 * д����־
	 */
	public void outputToTxt(String outputContent, String outputAddress) throws IOException{
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputAddress, true),"UTF-8");
		osw.write(outputContent);
		osw.append("\r\n");
		osw.close();
	}
}
