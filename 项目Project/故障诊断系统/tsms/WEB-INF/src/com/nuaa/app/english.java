package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;
public interface english {
	 public HashMap uploadFile(HashMap fileHmap);
	 public boolean addFile(HashMap fileHmap);
	 public JSONObject getQueryApprove(HashMap queryMap);
	 public String delPart(HashMap hashMap);
	 public String getFile(String dir,String tuhao);
	 public HashMap uploadFile1(HashMap fileHmap);
	 public boolean addFile1(HashMap fileHmap);
	 public JSONObject getQueryApprove1(HashMap queryMap);
	 public String delPart1(HashMap hashMap);
	 public String getFile1(String dir,String tuhao);
	// public HashMap uploadFile1(HashMap fileHmap);
	// public JSONObject getQueryFile(HashMap queryMap);
	//public JSONObject getQueryFile1(HashMap queryMap1);
}