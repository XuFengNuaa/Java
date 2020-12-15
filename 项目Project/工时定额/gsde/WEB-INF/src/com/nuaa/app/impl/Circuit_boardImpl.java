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
import com.nuaa.app.Circuit_board;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class Circuit_boardImpl implements Circuit_board{
	
	public JSONObject view_circuit_board(HashMap circuit_board_HMap) {
		// TODO 自动生成方法存根
		HashMap circuit_boardHashMap=circuit_board_HMap;
		final JSONObject jsonObj=new JSONObject();
		final String name=(String)circuit_boardHashMap.get("name");
		String sql="";
		sql="select * from T_CIRCUIT_BOARD where name='"+name+"'"; 

		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
							decimalformat.setMaximumFractionDigits(2);//最多保留两位小数
							jsonObj.put("id",rs.getString("id"));
							jsonObj.put("name",rs.getString("name"));
							jsonObj.put("time_one",rs.getString("time_one"));
							jsonObj.put("time_two", rs.getString("time_two"));
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
	
	public JSONObject view_cleaning(HashMap cleaning_HMap) {
		// TODO 自动生成方法存根
		HashMap cleaningHashMap=cleaning_HMap;
		final JSONObject jsonObj=new JSONObject();
		final String numn=(String)cleaningHashMap.get("numn");
		String sql="";
		sql="select * from T_CLEANING where numn='"+numn+"'"; 

		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
							decimalformat.setMaximumFractionDigits(2);
							jsonObj.put("id",rs.getString("id"));
							jsonObj.put("numn",rs.getString("numn"));
							jsonObj.put("cleaner",rs.getString("cleaner"));
							jsonObj.put("cleaning_process",rs.getString("cleaning_process"));
							jsonObj.put("cleaning_method",rs.getString("cleaning_method"));
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
	
	
	
	
	
	
}

	

