package com.nuaa.app;
import java.util.HashMap;
import org.json.JSONObject;
public interface Ministore{
	
public JSONObject getQueryMinistoreC(HashMap queryMap1);
public JSONObject getQueryMinistoreM(HashMap queryMap2);
public JSONObject getQueryMinistoreMT(HashMap queryMap3);
public JSONObject getQueryMinistoreCT(HashMap queryMap4);
	
}
