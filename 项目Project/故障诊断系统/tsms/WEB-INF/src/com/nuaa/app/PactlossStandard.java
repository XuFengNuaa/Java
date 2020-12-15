
package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;


/**
 * @author mahao
 *
 */
public interface PactlossStandard {
	public JSONObject getQueryFile(HashMap queryMap);
	
	public boolean modifyFile(HashMap addMap);
	
}