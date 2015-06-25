package com.leadstec.apitestflow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.print.attribute.standard.SheetCollate;

import com.leadstec.common.Share;

/*
 * 模块：由userid获得用户
 * 参数：userid
 * 测试用例：2个
 */
public class GetUserByUserid{
	public void testGetUserByUserid() throws Exception {
		Share share = new Share();
		
		String TCAddress = "D:\\jjzhai\\apitestworkspace\\apitest_array\\src\\com\\leadstec\\tc\\TCGetUserByUserid.txt";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");
		String outputAddress = "D:\\jjzhai\\apitestworkspace\\apitest_array\\src\\com\\leadstec\\log\\LogGetUserByUserid.txt";
		
		String apiBaseUrl = "http://n01.lxpt.cn:50063/user/";
		
		//获得测试用例数组
		String[] tc = share.getTC(TCAddress);
		//获得期望结果数组
		String[] expectResult = share.getExpectResult(TCAddress);
		
		//开始测试
		share.outputToTxt("********************"+df.format(new Date())+" 开始测试 由userid获得用户********************", outputAddress);
		
		for(int i = 0; i < tc.length; i++){
			String[] row = tc[i].split(",");
			for (int j = 0; j < row.length; j++) {
				//准备数据
				share.outputToTxt("\n准备数据：", outputAddress);
				//参数信息
				share.outputToTxt("参数："+row[j], outputAddress);
				//获得实际结果
				String actualResult = share.callApi(apiBaseUrl+tc[i]);
				share.outputToTxt("实际结果："+actualResult, outputAddress);
				//获得期望结果
				share.outputToTxt("期望结果："+expectResult[i], outputAddress);
				
				//测试结果
				share.outputToTxt("测试结果：", outputAddress);
				if(actualResult.equals(expectResult[i])){
					share.outputToTxt("通过", outputAddress);
				}else {
					share.outputToTxt("！！！不通过！！！  实际结果和预期结果不符", outputAddress);
				}
			}
		}
		
		share.outputToTxt("********************"+df.format(new Date())+" 结束测试 由userid获得用户********************", outputAddress);
	}
}
