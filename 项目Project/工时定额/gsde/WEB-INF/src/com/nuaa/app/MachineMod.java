package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface MachineMod {
	public JSONObject QueryAllMachine(HashMap hashmap);
	public String AddMachine(HashMap hashmap);
	public JSONObject ViewMachine(HashMap hashmap);
	public String DelMachine(HashMap hashmap);
	public String EditMachine(HashMap hashmap);
	public JSONObject getQueryMachineAll();
}
