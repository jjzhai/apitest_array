package com.leadstec.apitestflow;

import com.leadstec.common.Share;

public class TestAll {
	public static void main(String[] args) throws Exception{
		GetUser getUser = new GetUser();
		getUser.sendGetRequest("http://n01.lxpt.cn:50063/user");
		Share share = new Share();
		share.getUserInfo();
	}
}