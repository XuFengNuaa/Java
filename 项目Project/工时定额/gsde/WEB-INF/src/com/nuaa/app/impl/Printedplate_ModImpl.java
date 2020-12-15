package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.Printedplate_Mod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class Printedplate_ModImpl implements Printedplate_Mod{

//	@Override
	//搜索物资编码
	/*public JSONObject getQueryMnum() {
		
		final JSONObject jsonobj = new JSONObject();
		final JSONArray jsonarry = new JSONArray();
		String sql = "select distinct mnum from t_material";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){

	//			@Override
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						try {
							JSONObject json = new JSONObject();
							json.put("mnum", rs.getString("mnum"));
							jsonarry.put(json);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					try {
						jsonobj.put("filedata", jsonarry);
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
		return jsonobj;
	}*/


//通过物资编码查找材料详细信息
	/*public JSONObject queryByMnum(HashMap hashmap) {
		final String mnum = (String)hashmap.get("mnum");
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sql = "select * from t_material where mnum = '" + mnum + "'";
		try {
			DbUtil.execute(sql, new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						final JSONObject json = new JSONObject();
						try {
							json.put("mnum", mnum);
							String companyid = rs.getString("companyid");
							String sql1 = "select company from t_company where id = '" + companyid + "'";
							DbUtil.execute(sql1, new IResultSetProcessor(){
								public void process(ResultSet rs) throws SQLException {
									while(rs.next()){
										try {
											json.put("company", rs.getString("company"));
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
								});
							json.put("id", rs.getString("id"));
							json.put("name", rs.getString("name"));
							json.put("mincycle",rs.getInt("mincycle"));
							json.put("minstore",rs.getInt("minstore"));
							json.put("classname",rs.getString("classname"));
							json.put("measure",rs.getString("measure"));
							json.put("spec",rs.getInt("spec"));
							json.put("maxcycle",rs.getInt("maxcycle"));
							json.put("maxstore",rs.getInt("maxstore"));
							json.put("buycycle",rs.getInt("buycycle"));
							json.put("monthuse", rs.getFloat("monthuse"));
							json.put("singlepur",rs.getInt("singlepur"));
							json.put("guaranteedate",rs.getInt("guaranteedate"));
							jsonarray.put(json);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					try {
						obj.put("filedata", jsonarray);
						//System.out.println(obj.toString());
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
	}*/
  
//机械加工工序切削参数查询
	@Override
	public JSONObject QueryAllCutPara(HashMap hashmap) {
		// TODO Auto-generated method stub
		int start = Integer.parseInt((String)hashmap.get("start"));
		int limit = Integer.parseInt((String)hashmap.get("limit"));
		String filter = (String)hashmap.get("filter");
		String order = (String)hashmap.get("order");	
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sql1 = "select count(*) as ct from t_ppcut";
		String sql2 = "select * from t_ppcut"; 
		Logger.debug(sql1);
		Logger.debug(sql2);
		try {
			DbUtil.execute(sql1,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						try {
							obj.put("totalProperty",rs.getString("ct"));
							//System.out.println(obj.get("totalProperty"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			DbUtil.execute(sql2,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
				while(rs.next()){
					final JSONObject json;
					try {
						json = new JSONObject();
						json.put("id", rs.getString("id"));
						if(rs.getString("zhijing")!=""){
							json.put("zhijing", rs.getString("zhijing"));
						}if(rs.getString("ap")!=""){
							json.put("ap", rs.getDouble("ap"));
						}if(rs.getString("ae")!=""){
							json.put("ae", rs.getDouble("ae"));							
						}if(rs.getString("vf")!=""){
							json.put("vf", rs.getString("vf"));
						}if(rs.getString("vc")!=""){
							json.put("vc", rs.getString("vc"));
						}if(rs.getString("zzzs")!=""){
							json.put("zzzs", rs.getString("zzzs"));
						}if(rs.getString("mcjg")!=""){
							json.put("mcjg", rs.getString("mcjg"));
						}if(rs.getString("chishu")!=""){
							json.put("chishu", rs.getString("chishu"));
						}if(rs.getString("beizhu")!=""){
							json.put("beizhu", rs.getString("beizhu"));
						}						
						jsonarray.put(json);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
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

//零件总体信息存储
//	@Override
	public String allEntry(HashMap hashmap) {
		final String[] pa = new String[13];
		for(int i=0;i<pa.length;i++){
			pa[i] = "";
		}
		pa[0] = (String) hashmap.get("goodname");
		pa[1] = (String) hashmap.get("partname");
		pa[2] = (String) hashmap.get("goodnum");
		pa[3] = (String) hashmap.get("partnum");
		pa[4] = (String) hashmap.get("materialname");
		pa[5] = (String) hashmap.get("materialcard");
		pa[6] = (String) hashmap.get("materialnum");
		pa[7] = (String) hashmap.get("materialstandard");
		pa[8] = (String) hashmap.get("platethick");
		pa[9] = (String) hashmap.get("platelong");
		pa[10] = (String) hashmap.get("platewide");
		pa[11] = (String) hashmap.get("platenum");
		pa[12] = (String) hashmap.get("platedate");
		String[] sqls = new String[1];
		sqls[0] = "insert into t_platetotalrecord(good_name,part_name,good_num,part_num,material_name,material_card,material_num,material_standard,plate_thick,plate_long,plate_wide,plate_num,plate_date)values(?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy/mm/dd'))";
		try {
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts) throws SQLException {
					pstmts[0].setString(1,pa[0]);
					pstmts[0].setString(2,pa[1]);
					pstmts[0].setString(3, pa[2]);
					pstmts[0].setString(4, pa[3]);
					pstmts[0].setString(5, pa[4]);
					pstmts[0].setString(6, pa[5]);
					pstmts[0].setString(7, pa[6]);
					pstmts[0].setString(8, pa[7]);
					pstmts[0].setString(9, pa[8]);
					pstmts[0].setString(10, pa[9]);
					pstmts[0].setString(11, pa[10]);
					pstmts[0].setString(12, pa[11]);
					pstmts[0].setString(13,pa[12]);
					pstmts[0].addBatch();
					//System.out.println("000000000000000000");
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	
	//零件定额信息存储
//	@Override
	public String addEntry(HashMap hashmap) {
		final String[] pa = new String[9];
		for(int i=0;i<pa.length;i++){
			pa[i] = "";
		}
		pa[0] = (String) hashmap.get("goodnum");
		pa[1] = (String) hashmap.get("partnum");
		pa[2] = (String) hashmap.get("processnum");
		final int processnum = Integer.parseInt(pa[2]);
		pa[3] = (String) hashmap.get("process");
		pa[4] = (String) hashmap.get("worktype");
		pa[5] = (String) hashmap.get("farm");
		pa[6] = (String) hashmap.get("readytime");
		pa[7] = (String) hashmap.get("worktime");
		pa[8] = (String) hashmap.get("groupnum");
		String[] sqls = new String[1];
		sqls[0] = "insert into t_platerecord(good_num,part_num,process_num,process,worktype,farm,readytime,worktime,groupnum)values(?,?,?,?,?,?,?,?,?)";
		try {
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts) throws SQLException {
					pstmts[0].setString(1,pa[0]);
					pstmts[0].setString(2,pa[1]);
					pstmts[0].setInt(3, processnum);
					pstmts[0].setString(4, pa[3]);
					pstmts[0].setString(5, pa[4]);
					pstmts[0].setString(6, pa[5]);
					pstmts[0].setString(7, pa[6]);
					pstmts[0].setString(8, pa[7]);
					pstmts[0].setString(9, pa[8]);
					pstmts[0].addBatch();
					//System.out.println("000000000000000000");
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}

}
