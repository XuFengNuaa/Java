package com.nuaa.app;
import java.util.HashMap;

import org.json.JSONObject;

public interface StoreTool {
		public JSONObject getQueryout(HashMap queryMap);
		public JSONObject getQueryre(HashMap queryMap);
		public JSONObject getQuerygene(HashMap queryMap);
		public boolean checkNum(HashMap checknumHMap);
		public boolean checkhqCount(HashMap checknumHMap);
		public boolean checkCount(HashMap checknumHMap);
		public boolean appOut(HashMap appoutMap);
		public boolean temOut(HashMap appoutMap);
		public boolean appneOut(HashMap appoutMap);
		public boolean appReturn(HashMap appoutMap);
		public boolean checkReturn(HashMap appoutMap);
		public boolean refOut(HashMap refoutMap);
}
