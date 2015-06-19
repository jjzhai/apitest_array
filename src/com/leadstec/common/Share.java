package com.leadstec.common;

import net.sf.json.JSONArray;

public class Share {
	public void getUserInfo() {
		UserInfo userInfo = new UserInfo("jjzhai",27,"female");
		JSONArray jsonArray = JSONArray.fromObject(userInfo);
		System.out.println(jsonArray);
	}
}
