package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface StoreCut {
	public JSONObject getQueryout(HashMap queryMap);
	public JSONObject getQueryre(HashMap queryMap);
	public JSONObject getQuerygene(HashMap queryMap);
	public JSONObject viewCut(HashMap queryMap);
	public String checkNum(HashMap checknumHMap);
	public String checkCount(HashMap checknumHMap);
	public String checkhqCount(HashMap checknumHMap);
	public boolean appOut(HashMap appoutMap);
	public boolean temOut(HashMap appoutMap);
	public boolean appneOut(HashMap appoutMap);
	public boolean appReturn(HashMap appoutMap);
	public boolean checkReturn(HashMap appoutMap);
	public boolean refOut(HashMap refoutMap);
	public String ClearCut();
	public String TrueCut();
}
