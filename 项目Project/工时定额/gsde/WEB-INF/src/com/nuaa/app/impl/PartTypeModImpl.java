package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.FileState;
import com.nuaa.app.PartTypeMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class PartTypeModImpl implements PartTypeMod{
	       
		    public String addCompany(HashMap addcompanyHMap) {
			// TODO 自动生成方法存根
			final HashMap addcompanyHashMap=addcompanyHMap;
			final String id=UUID.randomUUID().toString();  //自动生成id代码
			String sql ="";	
			final String dalei=(String)addcompanyHMap.get("dalei");
			final String xiaolei=(String)addcompanyHMap.get("xiaolei");
			System.out.println(dalei+""+xiaolei);
			Logger.debug(sql);
			sql="select count(*) as ct from T_PARTTYPE where dalei='"+dalei+"'and xiaolei = '"+xiaolei+"'";
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
					sqls[0]= "insert into T_PARTTYPE (id,dalei,xiaolei)values(?,?,?)";
					DbUtil.executeBatchs(sqls,
							new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)
								throws SQLException {
								pstmts[0].setString(1,id);
							if(addcompanyHashMap.get("dalei")!=null){
								pstmts[0].setString(2,dalei);//userid
							}else{
								pstmts[0].setString(2," ");//userid
							}
							if(addcompanyHashMap.get("xiaolei")!=null){
								pstmts[0].setString(3,xiaolei);//username
							}else{
								pstmts[0].setString(3," ");//userid
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

		public JSONObject getQueryCompany(HashMap queryCompanyMap) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryCompanyMap;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select  count(*) as ct  from  T_PARTTYPE where "+(String)queryHashMap.get("filter");
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
			sql="select * from (select * from  (select * from T_PARTTYPE where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from T_PARTTYPE where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("id", rs.getString("id"));
								obj.put("dalei", rs.getString("dalei"));
								obj.put("xiaolei", rs.getString("xiaolei"));
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
        
		public JSONObject getQueryCompanyAll() {
			// TODO 自动生成方法存根
			
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select * from T_PARTTYPE ";
			
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("id", rs.getString("id"));
								obj.put("dalei", rs.getString("dalei"));
								
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

		
		public String delCompany(HashMap delCompanyHMap) {
			// TODO 自动生成方法存根
			final String id=(String)delCompanyHMap.get("id");
			final String []result=new String[1];
			result[0]="true";
			final String []sqls=new String[1];
			try {
				sqls[0]="delete from T_PARTTYPE where id in('',"+id+")";
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
							public void process(
							PreparedStatement[] pstmts)
							throws SQLException {						
								pstmts[0].addBatch();
								DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
									public void process(PreparedStatement[] pstmt)	throws SQLException {																				
										pstmt[0].addBatch();
									}										
								});			
									
						}
				});			 
			} catch (Exception ex1) {
				ex1.printStackTrace();
				result[0]="exception";
			}	
		 return result[0];	
		}

		public String editCompany(HashMap editCompanyHMap) {
			// TODO 自动生成方法存根
			final String id=(String)editCompanyHMap.get("id");
			final String dalei=(String)editCompanyHMap.get("dalei");
			final String xiaolei=(String)editCompanyHMap.get("xiaolei");
			String sql="";
			sql="select count(*) as ct from T_PARTTYPE where dalei='"+dalei+"' and xiaolei ='"+xiaolei+"' and id !='"+id+"'";
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
			String[] sqls = new String[1];	
			sqls[0] = "update T_PARTTYPE set dalei=?,xiaolei=? where id='"+id+"'";
			System.out.println(sqls[0]);
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)	throws SQLException {			
						pstmts[0].setString(1,dalei);
						pstmts[0].setString(2,xiaolei);
						pstmts[0].addBatch();
					}
				});
				return "true";
			}else {
			 return "false";	
			}}
			catch (SQLException e) {
				e.printStackTrace();
				return "exception";
			}
		}

		public JSONObject viewCompany(HashMap viewCompanyMap) {
			// TODO 自动生成方法存根
			HashMap viewCompanyHashMap=viewCompanyMap;
			final JSONObject jsonObj=new JSONObject();
			final String id=(String)viewCompanyHashMap.get("id");
			String sql="";
			sql="select * from T_PARTTYPE where id='"+id+"'"; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								jsonObj.put("id", rs.getString("id"));
								jsonObj.put("dalei", rs.getString("dalei"));
								jsonObj.put("xiaolei", rs.getString("xiaolei"));
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

		
	}

	

