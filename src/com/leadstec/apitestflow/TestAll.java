package com.leadstec.apitestflow;

public class TestAll {
	public static void main(String[] args) throws Exception{
		GetUser getUser = new GetUser();
		getUser.testGetUser();
		
		GetUserByUserid getUserByUserid = new GetUserByUserid();
		getUserByUserid.testGetUserByUserid();
	}
}