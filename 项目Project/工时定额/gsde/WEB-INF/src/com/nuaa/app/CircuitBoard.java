package com.nuaa.app;
import java.util.HashMap;
import org.json.JSONObject;
public interface CircuitBoard {
	public JSONObject getQueryCircuitBoard(HashMap queryMap);
	public String addCitcuitBoard(HashMap addusrHMap);
	public String delCircuitBoard(HashMap delusrHMap);
	public JSONObject viewCircuitBoard(HashMap viewusrMap);
	public String editCircuitBoard(HashMap editusrHMap);
	
	
}