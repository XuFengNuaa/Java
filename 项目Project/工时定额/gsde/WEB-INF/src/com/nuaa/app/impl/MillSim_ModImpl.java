package com.nuaa.app.impl;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jenkov.prizetags.tree.itf.ITree;
import com.jenkov.prizetags.tree.itf.ITreeNode;
import com.nuaa.app.MillSim_Mod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;



public class MillSim_ModImpl implements MillSim_Mod{

	@Override
	public JSONObject QueryAllDalei() {
		// TODO Auto-generated method 
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarry = new JSONArray();
		String sql = "select distinct dalei from t_parttype";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						try {
						 JSONObject json = new JSONObject();
							json.put("dalei",rs.getString("dalei"));
							jsonarry.put(json);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					try {
						obj.put("filedata", jsonarry);
						System.out.println(obj);
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
	public JSONObject QueryAllXiaoleiByDalei(HashMap hashmap) {
		// TODO Auto-generated method stub
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarry = new JSONArray();
		final String dalel = hashmap.get("dalei").toString();
		String sql = "select distinct xiaolei from t_parttype where dalei = '"+dalel+"'";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						try {
							JSONObject json = new JSONObject();
							json.put("xiaolei",rs.getString("xiaolei"));
							jsonarry.put(json);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					try {
						obj.put("filedata", jsonarry);
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
	public JSONObject getQuerytuhaoAll(){
		// TODO Auto-generated method stub
		String sql = "select * from T_TUHAOYUZHI";
		final JSONObject jsonObj = new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("tuhao", rs.getString("tuhao"));
							
							jsonArray.put(obj);
						}	
							jsonObj.put("filedata", jsonArray);
							System.out.println(jsonObj.toString());
					} catch (JSONException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
				}				
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		Logger.debug(jsonObj.toString());
		return jsonObj;	
	}
	
	@Override
	public JSONObject getQueryMaterialAll(){
		// TODO Auto-generated method stub
		String sql = "select * from T_MATERIAL";
		final JSONObject jsonObj = new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("paihao", rs.getString("paihao"));
							
							jsonArray.put(obj);
						}	
							jsonObj.put("filedata", jsonArray);
							System.out.println(jsonObj.toString());
					} catch (JSONException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
				}				
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		Logger.debug(jsonObj.toString());
		return jsonObj;
	}
	
	@Override
	public JSONObject getQueryMachineAll(){
		// TODO Auto-generated method stub
		String sql = "select * from T_MACHINE";
		final JSONObject jsonObj = new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("xinghao", rs.getString("xinghao"));
							
							jsonArray.put(obj);
						}	
							jsonObj.put("filedata", jsonArray);
							System.out.println(jsonObj.toString());
					} catch (JSONException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
				}				
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		Logger.debug(jsonObj.toString());
		return jsonObj;
	}

	@Override
	public JSONObject getQuerySimAll(){
		// TODO Auto-generated method stub
		String sql = "select * from T_MILLSIM";
		final JSONObject jsonObj = new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("dalei_sim", rs.getString("dalei_sim"));
							obj.put("xiaolei_sim", rs.getString("xiaolei_sim"));
							obj.put("daihao_sim", rs.getString("daihao_sim"));
							obj.put("mingcheng_sim", rs.getString("mingcheng_sim"));
							obj.put("tuhao_sim", rs.getString("tuhao_sim"));
							obj.put("material_sim", rs.getString("material_sim"));
							obj.put("machine_sim", rs.getString("machine_sim"));
							obj.put("maopilong_sim", rs.getString("maopilong_sim"));
							obj.put("maopiwidth_sim", rs.getString("maopiwidth_sim"));
							obj.put("maopihigh_sim", rs.getString("maopihigh_sim"));
							obj.put("qxtj_sim", rs.getString("qxtj_sim"));
							obj.put("qxmj_sim", rs.getString("qxmj_sim"));
							
							jsonArray.put(obj);
						}	
							jsonObj.put("filedata", jsonArray);
							System.out.println(jsonObj.toString());
					} catch (JSONException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
				}				
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		Logger.debug(jsonObj.toString());
		return jsonObj;
	}

	@Override
	public JSONObject QueryMilldetail(HashMap hashmap){
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
							json.put("processnum_dx",rs.getString("process_num"));
							json.put("process_dx",rs.getString("process"));
							json.put("worktype_dx",rs.getString("worktype"));
							json.put("farm_dx",rs.getString("farm"));
							json.put("readytime_dx",rs.getString("readytime"));
							json.put("worktime_dx",rs.getString("worktime"));
							json.put("groupnum_dx",rs.getString("groupnum"));
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
	public JSONObject SimView(HashMap hashmap){
		// TODO Auto-generated method stub
		final String part_num = hashmap.get("part_num").toString();
		String sql = "select * from T_MILLTOTALRECORD where part_num = '"+ part_num +"'";
		final JSONObject json = new JSONObject();
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					try {
						while(rs.next()){	
							json.put("part_name", rs.getString("part_name"));
							json.put("part_num", rs.getString("part_num"));
							json.put("material_name", rs.getString("material_name"));
							json.put("mill_long", rs.getString("mill_long"));
							json.put("mill_wide", rs.getString("mill_wide"));
							json.put("mill_thick", rs.getString("mill_thick"));
						}
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
		return json;	
	}
	
	
	
	
	
}
