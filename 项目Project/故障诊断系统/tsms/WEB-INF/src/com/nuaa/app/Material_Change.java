package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface Material_Change {
	public JSONObject queryAll(HashMap hashmap);
	public String changeMaterial(HashMap hashmap);
}
