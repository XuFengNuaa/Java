package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface StoreCheck {
	public JSONObject getQuerycut(HashMap queryMap);
	public JSONObject getQueryct(HashMap queryMap);
	public JSONObject getQuerym(HashMap queryMap);
	public JSONObject getQuerymt(HashMap queryMap);
	public boolean cutCheck(HashMap cutcheckMap);
	public boolean ctCheck(HashMap cutcheckMap);
	public boolean mtCheck(HashMap cutcheckMap);
	public boolean mCheck(HashMap cutcheckMap);
}
