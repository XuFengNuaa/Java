package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.PPcutMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class PPcutModImpl implements PPcutMod{

	@Override
	public String addCutPara(final HashMap hashmap) {
		// TODO Auto-generated method stub
		final String zhijing = hashmap.get("zhijing").toString();
		final String ap = hashmap.get("ap").toString();
		final String ae = hashmap.get("ae").toString();
		final String chishu = hashmap.get("chishu").toString();
		final String zzzs = hashmap.get("zzzs").toString();
		final String mcjg = hashmap.get("mcjg").toString();
		final String beizhu = hashmap.get("beizhu").toString();
		
		String result = "true";
		final String id = UUID.randomUUID().toString();
		String[] sql1 = new String[1];
		sql1[0] = "insert into T_PPCUT (id,zhijing,ap,ae,vf,vc,zzzs,mcjg,chishu,beizhu)values (?,?,?,?,?,?,?,?,?,?)";
		Logger.debug(sql1[0]);
		try {
			DbUtil.executeBatchs(sql1,new IArrayPreparedStatementProcessor(){
				public void process(final PreparedStatement[] pstmts)throws SQLException {
					// TODO Auto-generated method stub
					float jgsd = 0;
					float qxsd = 0;					
					jgsd = Integer.parseInt(chishu)*Integer.parseInt(zzzs)*Float.parseFloat(mcjg);
					qxsd = (float) ((Integer.parseInt(zhijing)*3.1415*Integer.parseInt(zzzs))/1000);
					
					pstmts[0].setString(1,id);
					pstmts[0].setString(2,zhijing);
					pstmts[0].setString(3,ap);
					pstmts[0].setString(4,ae);
					pstmts[0].setFloat(5,jgsd);
					pstmts[0].setFloat(6,qxsd);
					pstmts[0].setString(7,zzzs);
					pstmts[0].setString(8,mcjg);
					pstmts[0].setString(9,chishu);
					pstmts[0].setString(10,beizhu);
					pstmts[0].addBatch();
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "false";
		}
		result = "true";
	
		     return result;
	}

	@Override
	public JSONObject QueryAllCutPara(HashMap hashmap) {
		// TODO Auto-generated method stub
		int start = Integer.parseInt((String)hashmap.get("start"));
		int limit = Integer.parseInt((String)hashmap.get("limit"));
		String filter = (String)hashmap.get("filter");
		String order = (String)hashmap.get("order");	
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sql1 = "select count(*) as ct from t_ppcut where "+ filter;
		String sql2 = "select * from (select * from  (select * from t_ppcut where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from t_ppcut where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
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


	@Override
	public String delCutPara(HashMap hashmap) {
		// TODO Auto-generated method stub
		final String id = hashmap.get("id").toString();
		String[] sql = new String[1];
		String result = "true";
		sql[0] = "delete from T_PPCUT where id in ('',"+ id +")";
		Logger.debug(sql[0]);
		
		try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts) throws SQLException {
					// TODO Auto-generated method stub
					pstmts[0].addBatch();
				}
			});
			result = "true";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "false";
		}
		return result;
	}

	@Override
	public String editCutPara(final HashMap hashmap) {
		// TODO Auto-generated method stub
		final String id = hashmap.get("id").toString();
		String result = "true";
		final String zhijing = hashmap.get("zhijing").toString();
		final String ap = hashmap.get("ap").toString();
		final String ae = hashmap.get("ae").toString();
		final String chishu = hashmap.get("chishu").toString();
		final String zzzs = hashmap.get("zzzs").toString();
		final String mcjg = hashmap.get("mcjg").toString();
		final String beizhu = hashmap.get("beizhu").toString();
		
			String[] sqls = new String[1];
			sqls[0] = "update T_PPCUT set ZHIJING=?,AP=?,AE=?,VF=?,VC=?,ZZZS=?,MCJG=?,CHISHU=?,BEIZHU=? where id ='"+ id +"'";
				Logger.debug(sqls[0]);
				try {
					DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
						public void process(final PreparedStatement[] pstmts)throws SQLException {
							// TODO Auto-generated method stub
							float jgsd = 0;
							float qxsd = 0;					
							jgsd = Integer.parseInt(chishu)*Integer.parseInt(zzzs)*Float.parseFloat(mcjg);
							qxsd = (float) ((Integer.parseInt(zhijing)*3.1415*Integer.parseInt(zzzs))/1000);
							
							pstmts[0].setString(1,zhijing);
							pstmts[0].setString(2,ap);
							pstmts[0].setString(3,ae);
							pstmts[0].setFloat(4,jgsd);
							pstmts[0].setFloat(5,qxsd);
							pstmts[0].setString(6,zzzs);
							pstmts[0].setString(7,mcjg);
							pstmts[0].setString(8,chishu);
							pstmts[0].setString(9,beizhu);
							pstmts[0].addBatch();
						}
					});
				result = "true";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "false";
			}

		return result;
	}

}
