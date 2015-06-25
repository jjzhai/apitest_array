package com.leadstec.apitestflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.leadstec.common.JsonEquals;
import com.leadstec.common.JsonValidator;
import com.leadstec.common.Share;

public class GetUser {
	/*
	 * ģ�飺����û��б�
	 * ��������
	 * ����������1��
	 * ��֤�㣺2��
	 */
	public void testGetUser() throws Exception{
		Share share = new Share();
		
		String TCAddress = "D:\\jjzhai\\apitestworkspace\\apitest_array\\src\\com\\leadstec\\tc\\TCGetUser.txt";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");
		String outputAddress = "D:\\jjzhai\\apitestworkspace\\apitest_array\\src\\com\\leadstec\\log\\LogGetUser.txt";
		
		String apiBaseUrl = "http://n01.lxpt.cn:50063/user";
		
		//��ò�����������
		String[] tc = share.getTC(TCAddress);
		//��������������
		String[] expectResult = share.getExpectResult(TCAddress);
		
		//��ʼ����
		share.outputToTxt("********************"+df.format(new Date())+" ��ʼ���� �û��б�********************", outputAddress);
		
		//׼������
		share.outputToTxt("׼�����ݣ�", outputAddress);
		//��ȡʵ���û��б�
		String userList = share.callApi(apiBaseUrl);
		share.outputToTxt("ʵ���û��б�"+userList, outputAddress);
		//��������û��б�
		share.outputToTxt("�����û��б�"+expectResult[0], outputAddress);
		
		//��ʼ����
		share.outputToTxt("\n��ʼ���㣺", outputAddress);
		//�ж�ʵ���û��б��Ƿ�Ϊ�Ϸ�JSON��ʽ
		JsonValidator jsonValidator = new JsonValidator();
		boolean check1 = jsonValidator.validate(userList);
		if(check1){
			share.outputToTxt("����һ��ʵ���û��б�  ��  �Ϸ�JSON��ʽ", outputAddress);
		}else {
			share.outputToTxt("����һ��ʵ���û��б�  ����  �Ϸ�JSON��ʽ", outputAddress);
		}		
		//�ж�ʵ�ʺ������û��б��Ƿ�ƥ��
		JsonEquals jsonEquals = new JsonEquals();
		boolean check2 = jsonEquals.jsonEquals(userList, expectResult[0]);
		if(check2){
			share.outputToTxt("�������ʵ�ʺ������û��б�   ƥ��", outputAddress);
		}else {
			share.outputToTxt("�������ʵ�ʺ������û��б�   ��ƥ��", outputAddress);
		}
		
		//���Խ��
		share.outputToTxt("\n���Խ����", outputAddress);
		if(check1 && check2){
			share.outputToTxt("���м���ȫ��ͨ��", outputAddress);
		}else {
			share.outputToTxt("��δͨ���ļ���", outputAddress);
		}		
		
		//��������
		share.outputToTxt("********************"+df.format(new Date())+" �������� �û��б�********************\n", outputAddress);
	}
}
