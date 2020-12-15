package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface UsrMod {
	public JSONObject getQueryUsr(HashMap queryMap);
	public String addUsr(HashMap addusrHMap);
	public boolean checkNum(HashMap checknumHMap);
	public boolean delUsr(HashMap delusrHMap);
	public boolean delUsr1(HashMap delusrHMap);
	public JSONObject viewUsr(HashMap viewusrMap);
	public String modiUser(HashMap modiUsrHMap);
	public JSONObject allTeamLeader();
	public String exsit(HashMap get_numHashMap);
}
