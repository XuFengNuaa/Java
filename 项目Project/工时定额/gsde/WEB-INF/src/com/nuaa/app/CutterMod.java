package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface CutterMod {
	public JSONObject QueryAllCutter(HashMap hashmap);//
	public String AddCutter(HashMap hashmap);//
	public JSONObject ViewCutter(HashMap hashmap);//
	public String DelCutter(HashMap hashmap);//
	public String EditCutter(HashMap hashmap);//
	public JSONObject QueryAllCompany();
	public JSONObject QueryXLByCom(HashMap hashmap);
	public JSONObject QueryZHIJINGByXLH(HashMap hashmap);
	public JSONObject QueryZHIJINGByCOM(HashMap hashmap);
	public JSONObject QueryRJBJByZJ(HashMap hashmap);
	public JSONObject QueryDJXH(HashMap hashmap);
}
