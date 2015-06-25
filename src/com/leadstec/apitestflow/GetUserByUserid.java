package com.leadstec.apitestflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.print.attribute.standard.SheetCollate;

import com.leadstec.common.Share;

/*
 * ģ�飺��userid����û�
 * ������userid
 * ����������2��
 */
public class GetUserByUserid{
	public void testGetUserByUserid() throws Exception {
		Share share = new Share();
		
		String TCAddress = "D:\\jjzhai\\apitestworkspace\\apitest_array\\src\\com\\leadstec\\tc\\TCGetUserByUserid.txt";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");
		String outputAddress = "D:\\jjzhai\\apitestworkspace\\apitest_array\\src\\com\\leadstec\\log\\LogGetUserByUserid.txt";
		
		String apiBaseUrl = "http://n01.lxpt.cn:50063/user/";
		
		//��ò�����������
		String[] tc = share.getTC(TCAddress);
		//��������������
		String[] expectResult = share.getExpectResult(TCAddress);
		
		//��ʼ����
		share.outputToTxt("********************"+df.format(new Date())+" ��ʼ���� ��userid����û�********************", outputAddress);
		
		for(int i = 0; i < tc.length; i++){
			String[] row = tc[i].split(",");
			for (int j = 0; j < row.length; j++) {
				//׼������
				share.outputToTxt("\n׼�����ݣ�", outputAddress);
				//������Ϣ
				share.outputToTxt("������"+row[j], outputAddress);
				//���ʵ�ʽ��
				String actualResult = share.callApi(apiBaseUrl+tc[i]);
				share.outputToTxt("ʵ�ʽ����"+actualResult, outputAddress);
				//����������
				share.outputToTxt("���������"+expectResult[i], outputAddress);
				
				//���Խ��
				share.outputToTxt("���Խ����", outputAddress);
				if(actualResult.equals(expectResult[i])){
					share.outputToTxt("ͨ��", outputAddress);
				}else {
					share.outputToTxt("��������ͨ��������  ʵ�ʽ����Ԥ�ڽ������", outputAddress);
				}
			}
		}
		
		share.outputToTxt("********************"+df.format(new Date())+" �������� ��userid����û�********************", outputAddress);
	}
}
