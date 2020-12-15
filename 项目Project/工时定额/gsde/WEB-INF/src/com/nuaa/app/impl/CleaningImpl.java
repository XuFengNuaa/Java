package com.nuaa.app.impl;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import org.apache.commons.fileupload.FileItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.FileState;
import com.nuaa.app.Cleaning;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class CleaningImpl implements Cleaning{
	
	public JSONObject getQueryCleaning(HashMap queryCleaningMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryCleaningMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select  count(*) as ct  from  T_CLEANING where "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from T_CLEANING where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from T_CLEANING where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("numn", rs.getString("numn"));
							obj.put("cleaner", rs.getString("cleaner"));
							obj.put("cleaning_process", rs.getString("cleaning_process"));
							obj.put("cleaning_method", rs.getString("cleaning_method"));
							obj.put("work_time", rs.getString("work_time"));
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
	
	
	 public String addCleaning(HashMap addcleaningHMap) {
			// TODO 自动生成方法存根
			final HashMap addcleaningHashMap=addcleaningHMap;
			final String id=UUID.randomUUID().toString();  //自动生成id代码
			//Calendar c = Calendar.getInstance();
			//final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String sql ="";	
			String numn=(String)addcleaningHMap.get("numn");
			Logger.debug(sql);
			//final JSONArray idArray=new JSONArray();
			
			
			
			sql="select count(*) as ct from T_CLEANING where numn='"+numn+"'";
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
					sqls[0]= "insert into T_CLEANING(id,numn,cleaner,cleaning_process,cleaning_method,work_time) values(?,?,?,?,?,?)";
					DbUtil.executeBatchs(sqls,
							new IArrayPreparedStatementProcessor() {
								public void process(
								PreparedStatement[] pstmts)
								throws SQLException {
								pstmts[0].setString(1,id);
							if(addcleaningHashMap.get("numn")!=null){
								pstmts[0].setString(2,(String)addcleaningHashMap.get("numn"));//userid
							}else{
								pstmts[0].setString(2," ");//userid
							}
							if(addcleaningHashMap.get("cleaner")!=null){
								pstmts[0].setString(3,(String)addcleaningHashMap.get("cleaner"));//username
							}else{
								pstmts[0].setString(3," ");//username
							}
							if(addcleaningHashMap.get("cleaning_process")!=null){
								pstmts[0].setString(4,(String)addcleaningHashMap.get("cleaning_process"));//pwd
							}else{
								pstmts[0].setString(4," ");//pwd
							}
							if(addcleaningHashMap.get("cleaning_method")!=null){
								pstmts[0].setString(5,(String)addcleaningHashMap.get("cleaning_method"));//email
							}else{
								pstmts[0].setString(5," ");//email
							}
							if(addcleaningHashMap.get("work_time")!=null){
								pstmts[0].setString(6,(String)addcleaningHashMap.get("work_time"));//email
							}else{
								pstmts[0].setString(6," ");//email
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
	
	
	 public String delCleaning(HashMap delCleaningHMap) {
			// TODO 自动生成方法存根
			final String id=(String)delCleaningHMap.get("id");
			final String []result=new String[1];
			result[0]="true";
			String []sqls=new String[1];
			try {
				sqls[0]="delete from T_CLEANING where id in('',"+id+")";
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
	 
	 
	 
	 public JSONObject viewCleaning(HashMap viewCleaningMap) {
			// TODO 自动生成方法存根
			HashMap viewCleaningHashMap=viewCleaningMap;
			final JSONObject jsonObj=new JSONObject();
			final String id=(String)viewCleaningHashMap.get("id");
			String sql="";
			sql="select * from T_CLEANING where id='"+id+"'"; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								jsonObj.put("id", rs.getString("id"));
								jsonObj.put("numn", rs.getString("numn"));
								jsonObj.put("cleaner", rs.getString("cleaner"));
								jsonObj.put("cleaning_process", rs.getString("cleaning_process"));
								jsonObj.put("cleaning_method", rs.getString("cleaning_method"));
								jsonObj.put("work_time", rs.getString("work_time"));
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
	 
	 public String editCleaning(HashMap editCleaningHMap) {
			// TODO 自动生成方法存根
			final String id=(String)editCleaningHMap.get("id");
			final String numn=(String)editCleaningHMap.get("numn");
			final String cleaner=(String)editCleaningHMap.get("cleaner");
			final String cleaning_process=(String)editCleaningHMap.get("cleaning_process");
			final String cleaning_method=(String)editCleaningHMap.get("cleaning_method");
			final String work_time=(String)editCleaningHMap.get("work_time");
			String sql="";
			sql="select count(*) as ct from T_CLEANING where numn='"+numn+"' and id!='"+id+"'";
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
			sqls[0] = "update T_CLEANING set numn=?,cleaner=?,cleaning_process=?,cleaning_method=?,work_time=? where id='"+id+"'";
			System.out.println(sqls[0]);
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)	throws SQLException {	
						pstmts[0].setString(1,numn);
						pstmts[0].setString(2,cleaner);
						pstmts[0].setString(3,cleaning_process);
						pstmts[0].setString(4,cleaning_method);
						pstmts[0].setString(5,work_time);
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
	
	
	
	
	
}

	

