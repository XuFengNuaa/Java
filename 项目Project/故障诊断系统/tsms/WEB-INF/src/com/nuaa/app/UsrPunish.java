package com.nuaa.app;
import java.util.HashMap;
import org.json.JSONObject;
public interface UsrPunish{
	
public JSONObject getQueryUsrPunishC(HashMap queryMap1);
public JSONObject getQueryUsrPunishC_hide(HashMap queryMap1);
public JSONObject getQueryUsrPunishM(HashMap queryMap2);
public JSONObject getQueryUsrPunishM_hide(HashMap queryMap2);
public JSONObject getQueryUsrPunishCT(HashMap queryMap3);
public JSONObject getQueryUsrPunishCT_hide(HashMap queryMap3);
public JSONObject getQueryUsrPunishMT(HashMap queryMap4);
public JSONObject getQueryUsrPunishMT_hide(HashMap queryMap4);
public JSONObject viewUsrPunishC(HashMap viewusrMap);
	
}
