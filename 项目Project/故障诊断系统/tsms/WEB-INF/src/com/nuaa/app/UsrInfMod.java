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
	/*public JSONObject getRealName();
	public String addKeeper(HashMap addhashmap);
	public JSONObject qureyAllKeeper(HashMap qreryhashmap);
	public JSONObject viewKeeper(HashMap qreryhashmap);
	public String editKeeper(HashMap hashmap);
	public String delKeeper(HashMap hashmap);*/
}
