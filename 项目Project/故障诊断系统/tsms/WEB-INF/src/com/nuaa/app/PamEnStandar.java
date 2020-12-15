package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface PamEnStandar {
	
	public JSONObject getQueryMnumAll();
	public JSONObject getQuerynumAll(HashMap numMap);
	public JSONObject getQuerycomAll(HashMap comMap);
	public String addFile(HashMap addMap);
	public String addFileApp(HashMap addMap);
	public String deleteRe(HashMap delMap);
	public JSONObject getQueryFile(HashMap queryMap);
	public JSONObject getQueryFile1(HashMap queryMap);
	public boolean chexFile(HashMap chexMap);
	public boolean chexFile1(HashMap chexMap);
	public String chexTool(HashMap chexToolMap);
	public JSONObject getQueryApprove(HashMap queryMap);
}
