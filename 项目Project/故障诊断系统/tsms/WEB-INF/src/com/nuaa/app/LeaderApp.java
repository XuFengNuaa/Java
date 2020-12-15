package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface LeaderApp {
	public JSONObject getQuerycut(HashMap queryMap);
	public JSONObject getQuerycutTem(HashMap queryMap);
	public JSONObject getQueryct(HashMap queryMap);
	public JSONObject getQueryctTem(HashMap queryMap);
	public JSONObject detailC(HashMap queryMap);
	public boolean appCut(HashMap appcutMap);
	public boolean appCt(HashMap appcutMap);
	public boolean refCut(HashMap refcutMap);
	public boolean refCt(HashMap refcutMap);
	public boolean checkCut(HashMap checkcutMap);
	public boolean checkCt(HashMap checkctMap);
}
