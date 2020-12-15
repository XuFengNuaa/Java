package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface PacEnStandar {
	public JSONObject getQueryMnumAll();
	public JSONObject getQuerynumAll(HashMap numMap);
	public JSONObject getQuerycomAll(HashMap comMap);
	public String addFile(HashMap addMap);
	public String addFile1(HashMap addMap);
	public String addFile2(HashMap addMap);
	public String addFileApp(HashMap addMap);
	public String deleteRe(HashMap delMap);
	public JSONObject getQueryFile(HashMap queryMap);
	public JSONObject getQueryFile1(HashMap queryMap1);
	public JSONObject getQueryFile2(HashMap queryMap2);
	public JSONObject getQueryFile3(HashMap queryMap3);
	public JSONObject getQueryFile4(HashMap queryMap4);
	public JSONObject getQueryFileScore(HashMap queryMap2);
	public boolean chexFile(HashMap chexMap);
	public boolean chexFile1(HashMap chexMap1);
	public JSONObject getQueryApprove(HashMap queryMap);
	public JSONObject getQueryApprove1(HashMap queryMap);
	 public HashMap uploadFile1(HashMap fileHmap);
	 public HashMap uploadFile2(HashMap fileHmap);
	 public String getFile(String dir,String mnum);
	 public String getFile1(String dir,String mnum);
}
