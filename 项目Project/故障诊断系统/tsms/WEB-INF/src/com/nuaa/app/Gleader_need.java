package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface Gleader_need {

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
	public boolean refCut(HashMap refcutMap);
	public boolean refCt(HashMap refcutMap);
	public boolean refMmt(HashMap refmmtMap);
	public boolean refMt(HashMap refmmtMap);
	public boolean checkCut(HashMap checkcutMap);
	public boolean checkCt(HashMap checkctMap);
	public boolean checkM(HashMap checkmMap);
	public boolean checkMt(HashMap checkmtMap);
	
}
