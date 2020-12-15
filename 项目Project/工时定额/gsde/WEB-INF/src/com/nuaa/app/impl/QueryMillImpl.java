package com.nuaa.app.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.QueryMill;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class QueryMillImpl implements QueryMill{
	public JSONObject QueryMillall(HashMap hashmap) {
		final String filter = (String) hashmap.get("filter");
		final String order = (String) hashmap.get("order");
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sqlq = "select count(*) as ct from t_milltotalrecord where " + filter;
		Logger.debug(sqlq);
		try {
			DbUtil.execute(sqlq,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						try {
							obj.put("totalProperty",rs.getString("ct"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
			});
		} catch (SQLException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		String sql = "select *  from t_milltotalrecord  where " + filter +"order by "+ order;
		Logger.debug(sql);
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						final JSONObject json = new JSONObject();
						try {
							json.put("goodname",rs.getString("good_name"));
							json.put("partname",rs.getString("part_name"));
							json.put("goodnum",rs.getString("good_num"));
							json.put("partnum",rs.getString("part_num"));
							json.put("materialname",rs.getString("material_name"));
							json.put("materialcard",rs.getString("material_card"));
							json.put("materialnum",rs.getString("material_num"));
							json.put("materialstandard",rs.getString("material_standard"));
							json.put("milllong",rs.getString("mill_long"));
							json.put("millwide",rs.getString("mill_wide"));
							json.put("millthick",rs.getString("mill_thick"));
							json.put("millnum",rs.getString("mill_num"));
							json.put("milldate",rs.getString("mill_date"));
							} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						jsonarray.put(json);
					}
					try {
						obj.put("filedata",jsonarray);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public JSONObject QueryMilldetail(HashMap hashmap) {
		final String filter = (String) hashmap.get("filter");
		final String order = (String) hashmap.get("order");
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sqlq = "select count(*) as ct from t_millrecord where " + filter;
		Logger.debug(sqlq);
		try {
			DbUtil.execute(sqlq,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						try {
							obj.put("totalProperty",rs.getString("ct"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
			});
		} catch (SQLException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		String sql = "select *  from t_millrecord  where " + filter +"order by "+ order;
		Logger.debug(sql);
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						final JSONObject json = new JSONObject();
						try {
							json.put("processnum_detail",rs.getString("process_num"));
							json.put("process_detail",rs.getString("process"));
							json.put("worktype_detail",rs.getString("worktype"));
							json.put("farm_detail",rs.getString("farm"));
							json.put("readytime_detail",rs.getString("readytime"));
							json.put("worktime_detail",rs.getString("worktime"));
							json.put("groupnum_detail",rs.getString("groupnum"));
							} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						jsonarray.put(json);
					}
					try {
						obj.put("filedata",jsonarray);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

}
