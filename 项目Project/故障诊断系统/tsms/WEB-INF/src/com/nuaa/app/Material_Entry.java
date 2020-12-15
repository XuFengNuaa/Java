package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface Material_Entry {
	public JSONObject getQueryMnum();
	public String checkMnum(HashMap hashmap);
	public JSONObject queryByMnum(HashMap hashmap);
	public JSONObject queryAllCompany(HashMap hashmap);
	public JSONObject querySpec(HashMap hashmap);
	public JSONObject getQueryAllSpec(HashMap hashmap);
	public String materialEntry(HashMap hashmap);
}
