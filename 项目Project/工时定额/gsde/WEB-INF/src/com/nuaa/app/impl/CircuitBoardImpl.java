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
import com.nuaa.app.CircuitBoard;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class CircuitBoardImpl implements CircuitBoard{
	
	public JSONObject getQueryCircuitBoard(HashMap queryCircuitMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryCircuitMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select  count(*) as ct  from  T_CIRCUIT_BOARD where "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from T_CIRCUIT_BOARD where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from T_CIRCUIT_BOARD where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("name", rs.getString("name"));
							obj.put("time_one", rs.getString("time_one"));
							obj.put("time_two", rs.getString("time_two"));
							obj.put("remark", rs.getString("remark"));
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
	
	
	 public String addCitcuitBoard(HashMap addcircuitHMap) {
			// TODO 自动生成方法存根
			final HashMap addcircuitHashMap=addcircuitHMap;
			final String id=UUID.randomUUID().toString();  //自动生成id代码
			//Calendar c = Calendar.getInstance();
			//final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String sql ="";	
			String name=(String)addcircuitHMap.get("name");
			Logger.debug(sql);
			//final JSONArray idArray=new JSONArray();
			
			
			
			sql="select count(*) as ct from T_CIRCUIT_BOARD where name='"+name+"'";
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
					sqls[0]= "insert into T_CIRCUIT_BOARD(id,name,time_one,time_two,remark) values(?,?,?,?,?)";
					DbUtil.executeBatchs(sqls,
							new IArrayPreparedStatementProcessor() {
								public void process(
								PreparedStatement[] pstmts)
								throws SQLException {
								pstmts[0].setString(1,id);
							if(addcircuitHashMap.get("name")!=null){
								pstmts[0].setString(2,(String)addcircuitHashMap.get("name"));//userid
							}else{
								pstmts[0].setString(2," ");//userid
							}
							if(addcircuitHashMap.get("time_one")!=null){
								pstmts[0].setString(3,(String)addcircuitHashMap.get("time_one"));//username
							}else{
								pstmts[0].setString(3," ");//username
							}
							if(addcircuitHashMap.get("time_two")!=null){
								pstmts[0].setString(4,(String)addcircuitHashMap.get("time_two"));//pwd
							}else{
								pstmts[0].setString(4," ");//pwd
							}
							if(addcircuitHashMap.get("remark")!=null){
								pstmts[0].setString(5,(String)addcircuitHashMap.get("remark"));//email
							}else{
								pstmts[0].setString(5," ");//email
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
	
	
	 public String delCircuitBoard(HashMap delCircuitBoardHMap) {
			// TODO 自动生成方法存根
			final String id=(String)delCircuitBoardHMap.get("id");
			final String []result=new String[1];
			result[0]="true";
			String []sqls=new String[1];
			try {
				sqls[0]="delete from T_CIRCUIT_BOARD where id in('',"+id+")";
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
	 
	 
	 
	 public JSONObject viewCircuitBoard(HashMap viewCircuitMap) {
			// TODO 自动生成方法存根
			HashMap viewCircuitHashMap=viewCircuitMap;
			final JSONObject jsonObj=new JSONObject();
			final String id=(String)viewCircuitHashMap.get("id");
			String sql="";
			sql="select * from T_CIRCUIT_BOARD where id='"+id+"'"; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								jsonObj.put("id", rs.getString("id"));
								jsonObj.put("name", rs.getString("name"));
								jsonObj.put("time_one", rs.getString("time_one"));
								jsonObj.put("time_two", rs.getString("time_two"));
								jsonObj.put("remark", rs.getString("remark"));
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
	 
	 public String editCircuitBoard(HashMap editCircuitHMap) {
			// TODO 自动生成方法存根
			final String id=(String)editCircuitHMap.get("id");
			final String name=(String)editCircuitHMap.get("name");
			final String time_one=(String)editCircuitHMap.get("time_one");
			final String time_two=(String)editCircuitHMap.get("time_two");
			final String remark=(String)editCircuitHMap.get("remark");
			
			String sql="";
			sql="select count(*) as ct from T_CIRCUIT_BOARD where name='"+name+"' and id!='"+id+"'";
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
			sqls[0] = "update T_CIRCUIT_BOARD set name=?,time_one=?,time_two=?,remark=? where id='"+id+"'";
			System.out.println(sqls[0]);
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)	throws SQLException {	
						pstmts[0].setString(1,name);
						pstmts[0].setString(2,time_one);
						pstmts[0].setString(3,time_two);
						pstmts[0].setString(4,remark);
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

	

