package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface Store_needcheck {
	
	public JSONObject getQuerycut(HashMap queryMap);
	public JSONObject getQueryct(HashMap queryMap);
	public JSONObject getQuerym(HashMap queryMap);
	public JSONObject getQuerymt(HashMap queryMap);
	public JSONObject detailM(HashMap queryMap);
	public JSONObject detailC(HashMap queryMap);
	public JSONObject detailMt(HashMap queryMap);
	public boolean appCut(HashMap appcutMap);
	public boolean appCt(HashMap appcutMap);
	public boolean appMmt(HashMap appmmtMap);
	public boolean appMt(HashMap appmmtMap);
	
}
