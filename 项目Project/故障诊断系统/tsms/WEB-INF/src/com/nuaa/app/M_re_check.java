package com.nuaa.app;
import java.util.HashMap;
import org.json.JSONObject;
public interface M_re_check {
	public JSONObject getQuerym_re_check(HashMap requeryMap);
	public boolean re_check(HashMap re_check_HMap);
	public boolean delete_re_check(HashMap delete_re_check_HMap);
	public boolean undelete_re_check(HashMap undelete_re_check_HMap);

}
