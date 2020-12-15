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
import com.nuaa.app.Milling_Mod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class Milling_ModImpl implements Milling_Mod{
	       
        
		public JSONObject getQueryMillingAll(HashMap hashmap) {
			// TODO 自动生成方法存根
			final String getbeishu = (String)hashmap.get("beishu");
			//final int beishu = Integer.parseInt(getbeishu);
			final float beishu = Float.parseFloat(getbeishu);
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select * from xjgceshi ";
			
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("processnum_dx", rs.getString("hao"));
								obj.put("process_dx", rs.getString("gongxu"));
								obj.put("readytime_dx", rs.getInt("readytime"));
								obj.put("worktime_dx", rs.getInt("worktime"));
								
								obj.put("readytime_tj", (rs.getInt("readytime"))*beishu);
								obj.put("worktime_tj", (rs.getInt("worktime"))*beishu);
								
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
		
		//零件总体信息存储
//		@Override
		public String allEntry(HashMap hashmap){

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
			pa[8] = (String) hashmap.get("millthick");
			pa[9] = (String) hashmap.get("milllong");
			pa[10] = (String) hashmap.get("millwide");
			pa[11] = (String) hashmap.get("millnum");
			pa[12] = (String) hashmap.get("milldate");
			String[] sqls = new String[1];
			sqls[0] = "insert into t_milltotalrecord(good_name,part_name,good_num,part_num,material_name,material_card,material_num,material_standard,mill_thick,mill_long,mill_wide,mill_num,mill_date)values(?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy/mm/dd'))";
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
//		@Override
		public String addEntry(HashMap hashmap){

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
			sqls[0] = "insert into t_millrecord(good_num,part_num,process_num,process,worktype,farm,readytime,worktime,groupnum)values(?,?,?,?,?,?,?,?,?)";
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

	

