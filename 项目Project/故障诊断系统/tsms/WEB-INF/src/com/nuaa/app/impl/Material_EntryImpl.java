package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.Material_Entry;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;

public class Material_EntryImpl implements Material_Entry{

	@Override
	//搜索物资编码
	public JSONObject getQueryMnum() {
		
		final JSONObject jsonobj = new JSONObject();
		final JSONArray jsonarry = new JSONArray();
		String sql = "select distinct name from t_material";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){

				@Override
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						try {
							JSONObject json = new JSONObject();
							json.put("mnum", rs.getString("name"));
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
	}

//检验输入的物资编码是否存在
	public String checkMnum(HashMap hashmap) {
	    String result = ""; 
		String mnum = (String) hashmap.get("mnum");
		final String[] count = new String[1];
		String sql = "select count(*) as ct from t_material where name ='" + mnum +"'";
		try {
			DbUtil.execute(sql, new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						if("0".equals(rs.getString("ct"))){
							count[0] = "0";
						}else{
							count[0] = "1";
						}
				}
				}
				
			});
		if ("0".equals(count[0])){
			result = "false";
		}else{
			result = "true";
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "exception";
		}
		return result;
	}
//通过物资编码查找材料详细信息
	public JSONObject queryByMnum(HashMap hashmap) {
		final String mnum = (String)hashmap.get("mnum");
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sql = "select * from t_material where name = '" + mnum + "'";
		System.out.println(sql);
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
							
							json.put("minstore",rs.getInt("minstore"));
							json.put("classname",rs.getString("classname"));
							json.put("measure",rs.getString("measure"));
							json.put("spec",rs.getString("spec"));
							
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
	}

//通过物资编码获取厂商
	public JSONObject queryAllCompany(HashMap hashmap) {
		String mnum = (String) hashmap.get("mnum");
		final JSONObject jsonobj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sql = "select distinct companyid from t_material where mnum = '" + mnum + "'";
		try {
			DbUtil.execute(sql, new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						String companyid = rs.getString("companyid");
						String sql1 = "select company from t_company where id = '" +companyid +"'";
						DbUtil.execute(sql1, new IResultSetProcessor(){
							public void process(ResultSet rs) throws SQLException {
								while(rs.next()){
									JSONObject obj = new JSONObject();
									try {
										obj.put("company", rs.getString("company"));
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									jsonarray.put(obj);
								}
								
							}
							
						});
					}
					try {
						jsonobj.put("filedata",jsonarray );
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
	}

	@Override
	public JSONObject querySpec(HashMap hashmap) {
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		final String company = (String) hashmap.get("company");
		final String mnum = (String) hashmap.get("mnum");
		String sql = "select id from t_company where company = '" + company + "'";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						String compayid = rs.getString("id");
						String sql1 = "select * from t_material where companyid = '" + compayid + "'" + "and mnum = '" + mnum +"'";
						DbUtil.execute(sql1, new IResultSetProcessor(){
							public void process(ResultSet rs) throws SQLException {
								while(rs.next()){
									JSONObject json = new JSONObject();
									try {
										json.put("id", rs.getString("id"));
										json.put("company",company);
										json.put("mnum", rs.getString("mnum"));
										json.put("name", rs.getString("name"));
										/*json.put("mincycle",rs.getInt("mincycle"));*/
										json.put("minstore",rs.getInt("minstore"));
										json.put("classname",rs.getString("classname"));
										json.put("measure",rs.getString("measure"));
										json.put("spec",rs.getInt("spec"));
									/*	json.put("maxcycle",rs.getInt("maxcycle"));*/
										json.put("maxstore",rs.getInt("maxstore"));
										json.put("buycycle",rs.getInt("buycycle"));
										json.put("monthuse", rs.getFloat("monthuse"));
										json.put("singlepur",rs.getInt("singlepur"));
										json.put("guaranteedate",rs.getInt("guaranteedate"));
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									jsonarray.put(json);
								}
							}
						});
					}
					try {
						obj.put("filedata", jsonarray);
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

	public JSONObject getQueryAllSpec(HashMap hashmap) {
		String company = (String) hashmap.get("company");
		final String mnum = (String) hashmap.get("mnum");
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sql = "select id from t_company where company = '" + company +"'";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
					String companyid = rs.getString("id");
					String sql1 = "select spec from t_material where companyid = '" + companyid + "' and mnum = '" + mnum +"'";
					DbUtil.execute(sql1, new IResultSetProcessor(){
						public void process(ResultSet rs) throws SQLException {
							// TODO Auto-generated method stub
							while(rs.next()){
								JSONObject json = new JSONObject();
								try {
									json.put("spec", rs.getString("spec"));
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								jsonarray.put(json);
							}
						}
					});
					try {
						obj.put("filedata", jsonarray);
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
		return obj;
	}
//材料入库
	@Override
	public String materialEntry(HashMap hashmap) {
		final String[] pa = new String[12];
		for(int i=0;i<pa.length;i++){
			pa[i] = "";
		}
		pa[0] = UUID.randomUUID().toString();
		pa[1] = (String) hashmap.get("id");
		pa[2] = (String) hashmap.get("ordernum");
		pa[3] = (String) hashmap.get("kuname");
		pa[4] = (String) hashmap.get("shelfnum");
		pa[5] = (String) hashmap.get("producedate");
		pa[6] = (String) hashmap.get("guaranteedate");
		pa[7] = (String) hashmap.get("valiadate");
		pa[8] = (String) hashmap.get("incount");
		final int incount = Integer.parseInt(pa[8]);
		pa[9] = (String) hashmap.get("indate");
		pa[10] = (String) hashmap.get("inperson");
		pa[11] = (String) hashmap.get("state");
		String[] sqls = new String[1];
		sqls[0] = "insert into t_material_in_record(id,ordernum,typeid,kuname,shelfnum,producedate,guaranteedate,valiadate,incount,indate,inperson,state)values(?,?,?,?,?,to_date(?,'yyyy/mm/dd'),?,to_date(?,'yyyy/mm/dd'),?,to_date(?,'yyyy/mm/dd'),?,?)";
		try {
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts) throws SQLException {
					pstmts[0].setString(1,pa[0]);
					pstmts[0].setString(2,pa[2]);
					pstmts[0].setString(3, pa[1]);
					pstmts[0].setString(4, pa[3]);
					pstmts[0].setString(5, pa[4]);
					pstmts[0].setString(6, pa[5]);
					pstmts[0].setString(7, pa[6]);
					pstmts[0].setString(8, pa[7]);
					pstmts[0].setInt(9, incount);
					pstmts[0].setString(10,pa[9]);
					pstmts[0].setString(11,pa[10]);
					pstmts[0].setString(12,pa[11]);
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
