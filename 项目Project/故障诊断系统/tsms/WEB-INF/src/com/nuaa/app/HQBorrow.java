package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface HQBorrow {
	public JSONObject getQuerygene(HashMap queryMap);
	public boolean appOut(HashMap appoutMap);
	public boolean needC(HashMap appoutMap);
	public JSONObject getQuerygeneCT(HashMap queryMap);
	public boolean appOutCT(HashMap appoutMap);
	public boolean needCT(HashMap appoutMap);
	public JSONObject getQuerygeneM(HashMap queryMap);
	public boolean appOutM(HashMap appoutMap);
	public boolean needM(HashMap appoutMap);
	public JSONObject getQuerygeneMT(HashMap queryMap);
	public boolean appOutMT(HashMap appoutMap);
	public boolean needMT(HashMap appoutMap);
	public boolean checkStateM(HashMap checkHMap);
	public boolean checkStateMT(HashMap checkHMap);
}
