package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface ModelMod {
	public String addModle(HashMap hashmap);
	public JSONObject getQueryModel(HashMap hashmap);
	public JSONObject viewModel(HashMap hashmap);
	public String editModel(HashMap hashmap);
	public String delModel(HashMap hashmap);
}
