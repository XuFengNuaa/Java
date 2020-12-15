package com.nuaa.app;

import java.io.OutputStream;
import java.util.HashMap;

import org.json.JSONObject;

public interface UsageStatMod {
	public JSONObject UsageQuery(HashMap hashmap);
	public void UsageExcel(HashMap hashmap ,OutputStream os);
}
