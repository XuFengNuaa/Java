package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface LeaderApplt {
	public JSONObject getQueryc(HashMap queryMap);
	public JSONObject getQueryct(HashMap queryMap);
	public JSONObject getQuerym(HashMap queryMap);
	public JSONObject getQuerymt(HashMap queryMap);
	public JSONObject detailM(HashMap queryMap);
	public JSONObject detailC(HashMap queryMap);
	public JSONObject detailMt(HashMap queryMap);
	public boolean appCut(HashMap appcutMap);
	public boolean appCt(HashMap appcutMap);
	public boolean appM(HashMap appcutMap);
	public boolean appMt(HashMap appcutMap);
	public boolean checkCut(HashMap checkcutMap);
	public boolean checkCt(HashMap checkctMap);
	public boolean checkM(HashMap checkmMap);
	public boolean checkMt(HashMap checkmtMap);
}
