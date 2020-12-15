package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface CutterParaMod {
	public String addCutPara(HashMap hashmap);
	public JSONObject QueryAllCutPara(HashMap hashmap);
	public String delCutPara(HashMap hashmap);
	public String editCutPara(HashMap hashmap);
}
