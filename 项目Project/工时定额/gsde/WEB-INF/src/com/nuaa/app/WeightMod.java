package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface WeightMod {
	public JSONObject queryWeight();
	public String editWeight(HashMap hashmap);
	
}
