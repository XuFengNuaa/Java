package com.nuaa.app;
import java.util.HashMap;
import org.json.JSONObject;
public interface Cleaning {
	public JSONObject getQueryCleaning(HashMap queryMap);
	public String addCleaning(HashMap addusrHMap);
	public String delCleaning(HashMap delusrHMap);
	public JSONObject viewCleaning(HashMap viewusrMap);
	public String editCleaning(HashMap editusrHMap);
	
	
}