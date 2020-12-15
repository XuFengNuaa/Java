/**
 * 
 */
package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

/**
 * @author Administrator
 *
 */
public interface UsrInfMod {
	public JSONObject viewUsr(HashMap viewusrMap);
	public boolean editpwdUsr(HashMap editpwdusrHMap);
}
