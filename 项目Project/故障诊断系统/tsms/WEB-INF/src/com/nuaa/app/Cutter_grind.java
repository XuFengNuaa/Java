package com.nuaa.app;
import java.util.HashMap;

import org.json.JSONObject;

public interface Cutter_grind {
//	public String add_cutter(HashMap add_cutter_HMap);
	public JSONObject query_cut_togrind(HashMap queryMap);
	public JSONObject view_grind_cutter(HashMap view_grind_cutter_HMap);
//	public String edit_cutter(HashMap edit_cutter_HMap);
	public boolean to_grind_cutter(HashMap to_grind_HMap);
	public String modi_grind_cutter(HashMap modi_grind_HMap);
	public JSONObject get_grind_cutter(HashMap get_grind_HMap);
	public boolean re_add_grind(HashMap re_add_grind_HMap);
	public String change_grind(HashMap change_grind_HMap);
	public String delete_grind(HashMap delete_grind_HMap);	
	public JSONObject query_cut_regrind(HashMap queryMap);
	public JSONObject alter_grind_cutter(HashMap alterMap);
	public JSONObject detail_single_cutter(HashMap singleMap);
	public String insert_regrind(HashMap regrindMap);
	public String ok_grind(HashMap ok_grind_HMap);	
	public JSONObject query_do_togrind(HashMap queryMap);
	public JSONObject query_do_regrind(HashMap queryMap);
	public JSONObject detail_do_togrind(HashMap queryMap);
	public String finish_grind(HashMap finish_grind_HMap);	
	public String price_grind(HashMap price_HMap);	
	public String re_grind_count(HashMap count_HMap);
	public String re_grind_check(HashMap check_HMap);
	public String delete_check(HashMap delete_check_HMap);
	public String zero_regrind(HashMap zero_HMap);
}

