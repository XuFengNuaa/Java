
package com.nuaa.app;
import java.util.HashMap;

import org.json.JSONObject;
public interface huizongMod {
	public String add_cutter(HashMap add_cutter_HMap);
	public JSONObject getQuery_cutter(HashMap queryMap);
	public JSONObject view_cutter(HashMap view_cutter_HMap);
	public String edit_cutter(HashMap edit_cutter_HMap);
	public String edit_grind_cutter(HashMap edit_cutter_HMap);
	public String del_cutter_amount(HashMap del_cutter_amountHMap);
	public HashMap uploadfile(HashMap fileHmap);
	public String getExcel(HashMap xlsHmap);
	public String correct_cutter(HashMap correct_HMap);
	public String combine_cutter(HashMap combine_HMap);
	public String del_cutter(HashMap del_HMap);
}

