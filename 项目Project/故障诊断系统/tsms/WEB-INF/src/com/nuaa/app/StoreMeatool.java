package com.nuaa.app;
import java.util.HashMap;

import org.json.JSONObject;
public interface StoreMeatool {
	public JSONObject getQueryout(HashMap queryMap);
	public JSONObject getQueryre(HashMap queryMap);
	public JSONObject getQuerygene(HashMap queryMap);
	public JSONObject detailMt(HashMap view_mt_indi_Map);
	public boolean appOut(HashMap appoutMap);
	public boolean checkNum(HashMap checknumMap);
	public boolean checkState(HashMap checknumMap);
	public boolean checkStatus(HashMap checknumMap);
	public boolean appneOut(HashMap appoutMap);
	public boolean appReturn(HashMap appoutMap);
	public boolean appScrap(HashMap appoutMap);
	public boolean appIlle(HashMap appoutMap);
	public boolean appLost(HashMap appoutMap);
	public boolean refOut(HashMap refoutMap);
	public boolean checkReturn(HashMap appoutMap);
	public boolean checkFn(HashMap appoutMap);
}
