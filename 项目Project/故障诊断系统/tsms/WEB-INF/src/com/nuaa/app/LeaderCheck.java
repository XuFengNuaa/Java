package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface LeaderCheck {
	public JSONObject getQuerycut(HashMap queryMap);
	public JSONObject getQueryct(HashMap queryMap);
	public JSONObject getQuerym(HashMap queryMap);
	public JSONObject getQuerymt(HashMap queryMap);
	public JSONObject getQueryCgout(HashMap queryMap);
	public JSONObject detail_do_togrind(HashMap queryMap);
	public JSONObject detail_do_ingrind(HashMap queryMap);
	public JSONObject getQueryCgin(HashMap queryMap);
	public String appCut(HashMap appcutMap);
	public String refCut(HashMap appcutMap);
	public String appCt(HashMap appcutMap);
	public String refCt(HashMap appcutMap);
	public String appM(HashMap appcutMap);
	public String refM(HashMap appcutMap);
	public String appMt(HashMap appcutMap);
	public String refMt(HashMap appcutMap);
	public String appCgout(HashMap appcutMap);
	public String appCgoutM(HashMap appcutMap);
	public String refCgout(HashMap appcutMap);
	public String refCgoutM(HashMap appcutMap);
	public String appCgin(HashMap appcutMap);
	public String appCginM(HashMap appcutMap);
	public boolean cutCheck(HashMap checkMap);
	public boolean ctCheck(HashMap checkMap);
	public boolean mCheck(HashMap checkMap);
	public boolean mtCheck(HashMap checkMap);
	public boolean grdMCheck(HashMap checkMap);
	public boolean grdCheck(HashMap checkMap);
	public boolean grdinMCheck(HashMap checkMap);
	public boolean grdinCheck(HashMap checkMap);
}
