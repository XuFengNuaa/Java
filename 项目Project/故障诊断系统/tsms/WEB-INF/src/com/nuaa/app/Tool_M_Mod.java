package com.nuaa.app;
import java.util.HashMap;
import org.json.JSONObject;
public interface Tool_M_Mod {
	
public JSONObject getQuery_tm_type(HashMap queryMap);
public String add_tm_type(HashMap add_tm_type_HMap);
//public boolean delCompany(HashMap delusrHMap);
public JSONObject view_tm_type(HashMap edit_tm_type_HMap);
public String edit_tm_type(HashMap edit_tm_type_HMap);
public JSONObject view_tm_indi(HashMap view_tm_indi_Map);
public String edit_tm_indi(HashMap edit_tm_indi_Map);
public JSONObject getQuery_tm_indi(HashMap view_tm_indi_Map);
public JSONObject getQueryTM_TypeAll();
public JSONObject detail_tm_indi(HashMap view_tm_indi_Map);
public String check_tm_type(HashMap check_tm_type_HMap);
public JSONObject query_tm_mnum_com(HashMap query_tm_mnum_comHMap);
public JSONObject query_tm_mnum(HashMap query_tm_mnumHMap);
public JSONObject query_tm_order_num(HashMap query_tm_order_numHMap);
public HashMap uploadfile(HashMap fileHmap);
public String getExcel(HashMap xlsHmap);
}




