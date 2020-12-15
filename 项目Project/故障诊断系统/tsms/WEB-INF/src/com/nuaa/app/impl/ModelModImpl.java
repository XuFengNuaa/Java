package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.ModelMod;
import com.nuaa.app.ModelMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class ModelModImpl implements ModelMod{
	
	public String addModle(HashMap addcompanyHMap) {
		final HashMap addcompanyHashMap=addcompanyHMap;
		String modelnum = (String) addcompanyHMap.get("modelnum");
		String name = (String) addcompanyHMap.get("name");
		final String id=UUID.randomUUID().toString();  //自动生成id
		String sql ="";	
		sql="select count(*) as ct from t_model where modelnum='"+modelnum+"'";
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
				sqls[0]= "insert into t_model(id,modelnum,name) values(?,?,?)";
				DbUtil.executeBatchs(sqls,
						new IArrayPreparedStatementProcessor() {
							public void process(
							PreparedStatement[] pstmts)
							throws SQLException {
							pstmts[0].setString(1,id);
						if(addcompanyHashMap.get("modelnum")!=null){
							pstmts[0].setString(2,(String)addcompanyHashMap.get("modelnum"));//userid
						}else{
							pstmts[0].setString(2," ");//userid
						}
						if(addcompanyHashMap.get("name")!=null){
							pstmts[0].setString(3,(String)addcompanyHashMap.get("name"));//username
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
	public JSONObject getQueryModel(HashMap hashmap) {
		HashMap queryHashMap=hashmap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select  count(*) as ct  from  t_model where "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from t_model where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from t_model where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("modelnum", rs.getString("modelnum"));
							obj.put("name", rs.getString("name"));
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

	public JSONObject viewModel(HashMap hashmap) {
		HashMap viewCompanyHashMap=hashmap;
		final JSONObject jsonObj=new JSONObject();
		final String id=(String)viewCompanyHashMap.get("id");
		String sql="";
		sql="select * from T_MODEL where id='"+id+"'"; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							jsonObj.put("id", rs.getString("id"));
							jsonObj.put("modelnum", rs.getString("modelnum"));
							jsonObj.put("name", rs.getString("name"));
							}	
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
	public String editModel(HashMap hashmap) {
		final String id=(String)hashmap.get("id");
		final String modelnum=(String)hashmap.get("modelnum");
		final String name=(String)hashmap.get("name");

		String sql="";
		sql="select count(*) as ct from T_MODEL where modelnum='"+modelnum+"'and id!='"+id+"'";
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
		System.out.println(count[0]);
		try {
		if ("0".equals(count[0])){							
		String[] sqls = new String[1];	
		sqls[0] = "update T_MODEL set modelnum=?,name=? where id='"+id+"'";
		System.out.println(sqls[0]);
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts)	throws SQLException {			
					pstmts[0].setString(1,modelnum);
					pstmts[0].setString(2,name);
					pstmts[0].addBatch();
				}
			});
			
		}else {
		 return "false";	
		}}
		catch (SQLException e) {
			e.printStackTrace();
			return "exception";
		}
		return "true";
	}

	@Override
	public String delModel(HashMap hashmap) {
		
		final String id=(String)hashmap.get("id");
		final String []result=new String[1];
		result[0]="true";
		String []sqls=new String[1];
		try {
			sqls[0]="delete from T_MODEL where id in('',"+id+")";
			DbUtil.executeBatchs(sqls,
					new IArrayPreparedStatementProcessor() {
						public void process(
						PreparedStatement[] pstmts)
						throws SQLException {						
							pstmts[0].addBatch();		
					}
			});			 
		} catch (Exception ex1) {
			ex1.printStackTrace();
			result[0]="exception";
		}	
	 return result[0];	
	 
	}

}
