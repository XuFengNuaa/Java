
package com.nuaa.app;
import java.util.HashMap;
import org.json.JSONObject;
public interface M_type_mod {
	
public JSONObject getQuery_m_type(HashMap queryMap);
public String add_m_type(HashMap add_m_type_HMap);
//public boolean delCompany(HashMap delusrHMap);
public JSONObject view_m_type(HashMap edit_m_type_HMap);
public String edit_m_type(HashMap edit_m_type_HMap);
public JSONObject view_m_indi(HashMap view_m_indi_Map);
public String edit_m_indi(HashMap edit_m_indi_Map);
public JSONObject getQuery_m_indi(HashMap view_m_indi_Map);
public JSONObject getQueryM_TypeAll();
public JSONObject detail_m_indi(HashMap view_m_indi_Map);
public String check_m_type(HashMap check_m_type_HMap);
public JSONObject query_mnum_com(HashMap query_mnum_com_HMap);
public JSONObject query_mnum(HashMap query_mnum_HMap);
public JSONObject query_order_num(HashMap query_order_numHMap);
public HashMap uploadfile(HashMap fileHmap);
public String getExcel(HashMap xlsHmap);
}




