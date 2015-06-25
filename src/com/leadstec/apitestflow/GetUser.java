package com.leadstec.apitestflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.leadstec.common.JsonEquals;
import com.leadstec.common.JsonValidator;
import com.leadstec.common.Share;

public class GetUser {
	/*
	 * 模块：获得用户列表
	 * 参数：无
	 * 测试用例：1个
	 * 验证点：2个
	 */
	public void testGetUser() throws Exception{
		Share share = new Share();
		
		String TCAddress = "D:\\jjzhai\\apitestworkspace\\apitest_array\\src\\com\\leadstec\\tc\\TCGetUser.txt";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");
		String outputAddress = "D:\\jjzhai\\apitestworkspace\\apitest_array\\src\\com\\leadstec\\log\\LogGetUser.txt";
		
		String apiBaseUrl = "http://n01.lxpt.cn:50063/user";
		
		//获得测试用例数组
		String[] tc = share.getTC(TCAddress);
		//获得期望结果数组
		String[] expectResult = share.getExpectResult(TCAddress);
		
		//开始测试
		share.outputToTxt("********************"+df.format(new Date())+" 开始测试 用户列表********************", outputAddress);
		
		//准备数据
		share.outputToTxt("准备数据：", outputAddress);
		//获取实际用户列表
		String userList = share.callApi(apiBaseUrl);
		share.outputToTxt("实际用户列表："+userList, outputAddress);
		//获得期望用户列表
		share.outputToTxt("期望用户列表："+expectResult[0], outputAddress);
		
		//开始检查点
		share.outputToTxt("\n开始检查点：", outputAddress);
		//判断实际用户列表是否为合法JSON格式
		JsonValidator jsonValidator = new JsonValidator();
		boolean check1 = jsonValidator.validate(userList);
		if(check1){
			share.outputToTxt("检查点一：实际用户列表  是  合法JSON格式", outputAddress);
		}else {
			share.outputToTxt("检查点一：实际用户列表  不是  合法JSON格式", outputAddress);
		}		
		//判断实际和期望用户列表是否匹配
		JsonEquals jsonEquals = new JsonEquals();
		boolean check2 = jsonEquals.jsonEquals(userList, expectResult[0]);
		if(check2){
			share.outputToTxt("检查点二：实际和期望用户列表   匹配", outputAddress);
		}else {
			share.outputToTxt("检查点二：实际和期望用户列表   不匹配", outputAddress);
		}
		
		//测试结果
		share.outputToTxt("\n测试结果：", outputAddress);
		if(check1 && check2){
			share.outputToTxt("所有检查点全部通过", outputAddress);
		}else {
			share.outputToTxt("有未通过的检查点", outputAddress);
		}		
		
		//结束测试
		share.outputToTxt("********************"+df.format(new Date())+" 结束测试 用户列表********************\n", outputAddress);
	}
}
