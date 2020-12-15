package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

public interface InstrumentMod {
	public String addInstrument(HashMap hashmap);
	public JSONObject queryInstrument(HashMap hashmap);
	public JSONObject viewInstrument(HashMap hashmap);
	public String editInstrument(HashMap hashmap);
	public String delInstrument(HashMap hashmap);
	public HashMap uploadfile(HashMap fileHmap);
	public String getExcel(HashMap fileHmap);
}
