package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface MaterialMod {
	public JSONObject QueryAllMaterial(HashMap hashmap);
	public String AddMaterial(HashMap hashmap);
	public JSONObject ViewMaterial(HashMap hashmap);
	public String DelMaterial(HashMap hashmap);
	public String EditMaterial(HashMap hashmap);
	public JSONObject getQueryMaterialAll();
}
