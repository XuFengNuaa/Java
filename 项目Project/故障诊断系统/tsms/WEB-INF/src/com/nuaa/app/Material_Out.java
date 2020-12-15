package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface Material_Out {
	public JSONObject queryAll(HashMap hashmap);
	public String outMaterial(HashMap hashmap);
	public JSONObject getQueryMnum();
}

