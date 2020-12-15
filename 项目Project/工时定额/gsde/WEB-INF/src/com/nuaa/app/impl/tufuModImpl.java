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
import com.nuaa.app.tufuMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class tufuModImpl implements tufuMod{
	       
		    public String addCompany(HashMap addcompanyHMap) {
			// TODO 自动生成方法存根
			final HashMap addcompanyHashMap=addcompanyHMap;
			final String id=UUID.randomUUID().toString();  //自动生成id代码
			String sql ="";	
			final String leibie=(String)addcompanyHMap.get("leibie");
			final String mj1=(String)addcompanyHMap.get("mj1");
			final String mj2=(String)addcompanyHMap.get("mj2");
			final String mj3=(String)addcompanyHMap.get("mj3");
			final String mj4=(String)addcompanyHMap.get("mj4");
			final String mj5=(String)addcompanyHMap.get("mj5");
			System.out.println(leibie);
			Logger.debug(sql);
			sql="select count(*) as ct from T_TUFU where leibie='"+leibie+"'";
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
					sqls[0]= "insert into T_TUFU (id,leibie,mj1,mj2,mj3,mj4,mj5)values(?,?,?,?,?,?,?)";
					DbUtil.executeBatchs(sqls,
							new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)
								throws SQLException {
								pstmts[0].setString(1,id);
							if(addcompanyHashMap.get("leibie")!=null){
								pstmts[0].setString(2,leibie);//userid
							}else{
								pstmts[0].setString(2," ");//userid
							}
							pstmts[0].setString(3,mj1);
							pstmts[0].setString(4,mj2);
							pstmts[0].setString(5,mj3);
							pstmts[0].setString(6,mj4);
							pstmts[0].setString(7,mj5);
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
			sql="select  count(*) as ct  from  T_TUFU where "+(String)queryHashMap.get("filter");
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
			sql="select * from (select * from  (select * from T_TUFU where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from T_TUFU where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("id", rs.getString("id"));
								obj.put("leibie", rs.getString("leibie"));
								obj.put("mj1", rs.getString("mj1"));
								obj.put("mj2", rs.getString("mj2"));
								obj.put("mj3", rs.getString("mj3"));
								obj.put("mj4", rs.getString("mj4"));
								obj.put("mj5", rs.getString("mj5"));
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
			sql="select * from T_TUFU ";
			
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("id", rs.getString("id"));
								obj.put("leibie", rs.getString("leibie"));
								
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
				sqls[0]="delete from T_TUFU where id in('',"+id+")";
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
			final String leibie=(String)editCompanyHMap.get("leibie");
			final String mj1=(String)editCompanyHMap.get("mj1");
			final String mj2=(String)editCompanyHMap.get("mj2");
			final String mj3=(String)editCompanyHMap.get("mj3");
			final String mj4=(String)editCompanyHMap.get("mj4");
			final String mj5=(String)editCompanyHMap.get("mj5");
			String sql="";
			sql="select count(*) as ct from T_TUFU where leibie='"+leibie+"' and mj1 ='"+mj1+"' and mj2 ='"+mj2+"' and mj3 ='"+mj3+"' and mj4 ='"+mj4+"' and mj5 ='"+mj5+"' and id !='"+id+"'";
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
			sqls[0] = "update T_TUFU set leibie=?,mj1=?,mj2=?,mj3=?,mj4=?,mj5=? where id='"+id+"'";
			System.out.println(sqls[0]);
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)	throws SQLException {			
						pstmts[0].setString(1,leibie);
						pstmts[0].setString(2,mj1);
						pstmts[0].setString(3,mj2);
						pstmts[0].setString(4,mj3);
						pstmts[0].setString(5,mj4);
						pstmts[0].setString(6,mj5);
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
			sql="select * from T_TUFU where id='"+id+"'"; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								jsonObj.put("id", rs.getString("id"));
								jsonObj.put("leibie", rs.getString("leibie"));
								jsonObj.put("mj1", rs.getString("mj1"));
								jsonObj.put("mj2", rs.getString("mj2"));
								jsonObj.put("mj3", rs.getString("mj3"));
								jsonObj.put("mj4", rs.getString("mj4"));
								jsonObj.put("mj5", rs.getString("mj5"));
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

	

