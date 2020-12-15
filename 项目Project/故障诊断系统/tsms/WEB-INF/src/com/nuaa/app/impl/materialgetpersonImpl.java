package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.materialgetperson;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class materialgetpersonImpl implements materialgetperson {

	@Override
	public String addmaterialgetperson(HashMap hashmap) {
		// TODO Auto-generated method stub
		final HashMap addcompanyHashMap=hashmap;
		final String id=UUID.randomUUID().toString();  //自动生成id代码
		
		String sql ="";	
		String materialgetperson=(String)addcompanyHashMap.get("materialgetperson");
		String materialgetname=(String)addcompanyHashMap.get("materialgetname");
		
		sql="select count(*) as ct from t_material_getperson where materialgetperson='"+materialgetperson+"'";
		
		final String[] count=new String[1];
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					while(rs.next()){					
						count[0]=rs.getString("ct");
					}
				}
			});	
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		try {
			if ("0".equals(count[0])){	
				String []sqls = new String[1];
				sqls[0]= "insert into t_material_getperson(id,materialgetperson,materialgetname) values(?,?,?)";
				DbUtil.executeBatchs(sqls,
						new IArrayPreparedStatementProcessor() {
							public void process(
							PreparedStatement[] pstmts)
							throws SQLException {
							pstmts[0].setString(1,id);
						if(addcompanyHashMap.get("materialgetperson")!=null){
							pstmts[0].setString(2,(String)addcompanyHashMap.get("materialgetperson"));//userid
						}else{
							pstmts[0].setString(2," ");//userid
						}
						if(addcompanyHashMap.get("materialgetname")!=null){
							pstmts[0].setString(3,(String)addcompanyHashMap.get("materialgetname"));//username
						}else{
							pstmts[0].setString(3," ");//username
						}
						
						pstmts[0].addBatch();
						
					}
				});
				return "true";
			}else{
				return "false";
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return "Exception";
		}
	}

	@Override
	public JSONObject getQuerymaterialgetperson(HashMap queryMap) {
		// TODO Auto-generated method stub
		HashMap queryHashMap=queryMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select  count(*) as ct  from  t_material_getperson where "+(String)queryHashMap.get("filter");
		final String [] count=new String[1];
		count[0] = "";
		try {
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if (rs.next()) {
						try {
							jsonObj.put("totalProperty",rs.getString("ct"));
						} catch (JSONException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						}
					}
				}	
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		int start=Integer.parseInt((String)queryHashMap.get("start"));
		int limit=Integer.parseInt((String)queryHashMap.get("limit"));
		
		String filter=(String)queryHashMap.get("filter");
		String order=(String)queryHashMap.get("order");
		sql="select * from (select * from  (select * from t_material_getperson where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from t_material_getperson where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("materialgetperson", rs.getString("materialgetperson"));
							obj.put("materialgetname", rs.getString("materialgetname"));
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
	public String delmaterialgetperson(HashMap delusrHMap) {
		// TODO Auto-generated method stub
		String stuff_num=(String)delusrHMap.get("id");
		String []sqls=new String[1];
		sqls[0]="delete from t_material_getperson where id in ("+stuff_num+")";
		
		Logger.debug(sqls[0]);
		
			try {
				DbUtil.executeBatchs(sqls,
					new IArrayPreparedStatementProcessor() {
						public void process(
						PreparedStatement[] pstmts)
						throws SQLException {						
							pstmts[0].addBatch();
													}
			});
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}	
	}
	
	
	
	

}
