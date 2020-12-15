package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface kongjinshuhuaMod {
	public JSONObject getQueryCompany(HashMap queryMap);
	public String addCompany(HashMap addusrHMap);
	public String delCompany(HashMap delusrHMap);
	public String editCompany(HashMap editusrHMap);
	public JSONObject viewCompany(HashMap viewusrMap);
	public JSONObject getQueryCompanyAll();
	
}
