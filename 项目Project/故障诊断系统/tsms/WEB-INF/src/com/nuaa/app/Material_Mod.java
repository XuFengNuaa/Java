package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface Material_Mod {
	public String addMaterial(HashMap hashmap);
	public JSONObject getQueryMaterial(HashMap queryMap);
	public String editMaterial(HashMap hashmap);
	public String editMaterial1(HashMap hashmap);
	public JSONObject viewMaterial(HashMap queryMap);
	public String delMaterial(HashMap hashmap);
	public HashMap uploadfile(HashMap fileHmap);
	public String getExcel(HashMap fileHmap);
}
