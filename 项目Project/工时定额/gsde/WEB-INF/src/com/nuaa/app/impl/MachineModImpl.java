package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.MachineMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class MachineModImpl implements MachineMod{
	//查询机床函数
	public JSONObject QueryAllMachine(HashMap hashmap) {
		// TODO Auto-generated method stub
		int start = Integer.parseInt((String)hashmap.get("start"));
		int limit = Integer.parseInt((String)hashmap.get("limit"));
		String filter = (String)hashmap.get("filter");
		String order = (String)hashmap.get("order");	
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sql1 = "select count(*) as ct from T_MACHINE where "+ filter;
		String sql2 = "select * from (select * from  (select * from T_MACHINE where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from T_MACHINE where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql1);
		Logger.debug(sql2);
		try {
			DbUtil.execute(sql1,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						try {
							obj.put("totalProperty",rs.getString("ct"));
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
					JSONObject json;
					try {
						json = new JSONObject();
						json.put("id", rs.getString("id"));
						json.put("bianhao", rs.getString("bianhao"));
						if(rs.getString("xinghao")!=""){
							json.put("xinghao", rs.getString("xinghao"));
						}if(rs.getString("xingcheng")!=""){
							json.put("xingcheng", rs.getString("xingcheng"));
						}if(rs.getString("chicun")!=""){
							json.put("chicun", rs.getString("chicun"));
						}if(rs.getString("zhoushu")!=""){
							json.put("zhoushu", rs.getString("zhoushu"));
						}if(rs.getString("niuju")!=""){
							json.put("niuju", rs.getString("niuju"));
						}if(rs.getString("jingeili")!=""){
							json.put("jingeili", rs.getString("jingeili"));
						}if(rs.getString("maxzhuansu")!=""){
							json.put("zhuansumax", rs.getString("maxzhuansu"));
						}if(rs.getString("minzhuansu")!=""){
							json.put("zhuansumin", rs.getString("minzhuansu"));
						}if(rs.getString("vmax")!=""){
							json.put("vmax", rs.getString("vmax"));
						}if(rs.getString("vmin")!=""){
							json.put("vmin", rs.getString("vmin"));
						}if(rs.getString("jingdu")!=""){
							json.put("jingdu", rs.getString("jingdu"));
						}if(rs.getString("power")!=""){
							json.put("power", rs.getString("power"));
						}if(rs.getString("company")!=""){
							json.put("company", rs.getString("company"));
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
	public String AddMachine(final HashMap hashmap) {
		// TODO Auto-generated method stub
		final String bianhao = hashmap.get("bianhao").toString();
		//System.out.println(hashmap.get("xingcheng").toString());
		final String xinghao = hashmap.get("xinghao").toString();
		String sql = "select count(*) as ct from T_MACHINE where xinghao = '"+xinghao+"'";
		final String[] count = new String[1];
		String result = "true";
		Logger.debug(sql);
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
					count[0] = rs.getString("ct");
					}
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if("0".equals(count[0])){
			final String id = UUID.randomUUID().toString();
			String[] sql1 = new String[1];
			sql1[0] = "insert into T_MACHINE (id,bianhao,xinghao,xingcheng,chicun,zhoushu,niuju,jingeili,maxzhuansu,minzhuansu,vmax,vmin,jingdu,power,company,beizhu) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Logger.debug(sql1[0]);
			try {
				DbUtil.executeBatchs(sql1,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)throws SQLException {
						// TODO Auto-generated method stub
						pstmts[0].setString(1,id);
						if(hashmap.get("bianhao").toString() != ""){
							pstmts[0].setString(2,hashmap.get("bianhao").toString());
							}else{
								pstmts[0].setString(2,"");
							}
						if(hashmap.get("xinghao").toString() != ""){
						pstmts[0].setString(3,hashmap.get("xinghao").toString());
						}else{
							pstmts[0].setString(3,"");
						}if(hashmap.get("xingcheng").toString() != ""){
							pstmts[0].setString(4,hashmap.get("xingcheng").toString());
						}else{
							pstmts[0].setString(4,"");
						}
						if(hashmap.get("chicun").toString() != ""){
							pstmts[0].setString(5,hashmap.get("chicun").toString());
						}else{
							pstmts[0].setString(5,"");
						}if(hashmap.get("zhoushu").toString() != ""){
							pstmts[0].setString(6,hashmap.get("zhoushu").toString());
						}else{
							pstmts[0].setString(6,"");
						}if(hashmap.get("niuju").toString() != ""){
							pstmts[0].setString(7,hashmap.get("niuju").toString());
						}else{
							pstmts[0].setString(7,"");
						}if(hashmap.get("jingeili").toString() != ""){
							pstmts[0].setString(8,hashmap.get("jingeili").toString());
						}else{
							pstmts[0].setString(8,"");
						}if(hashmap.get("maxzhuansu").toString() != ""){
							pstmts[0].setString(9,hashmap.get("maxzhuansu").toString());
						}else{
							pstmts[0].setString(9,"");
						}if(hashmap.get("minzhuansu").toString() != ""){
							pstmts[0].setString(10,hashmap.get("minzhuansu").toString());
						}else{
							pstmts[0].setString(10,"");
						}if(hashmap.get("vmax").toString() != ""){
							pstmts[0].setString(11,hashmap.get("vmax").toString());
						}else{
							pstmts[0].setString(11,"");
						}if(hashmap.get("vmin").toString() != ""){
							pstmts[0].setString(12,hashmap.get("vmin").toString());
						}else{
							pstmts[0].setString(12,"");
						}if(hashmap.get("jingdu").toString() != ""){
							pstmts[0].setString(13,hashmap.get("jingdu").toString());
						}else{
							pstmts[0].setString(13,"");
						}if(hashmap.get("power").toString() != ""){
							pstmts[0].setString(14,hashmap.get("power").toString());
						}else{
							pstmts[0].setString(14,"");
						}if(hashmap.get("company").toString() != ""){
							pstmts[0].setString(15,hashmap.get("company").toString());
						}else{
							pstmts[0].setString(15,"");
						}if(hashmap.get("beizhu").toString() != ""){
							pstmts[0].setString(16,hashmap.get("beizhu").toString());
						}else{
							pstmts[0].setString(16,"");
						}
						pstmts[0].addBatch();
					}
				});
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "false";
			}
			result = "true";
		}
		else{
			result = "repeat";
		}
		     return result;
	}

	@Override
	public JSONObject ViewMachine(HashMap hashmap) {
		// TODO Auto-generated method stub
		final String id = hashmap.get("id").toString();
		String sql = "select * from T_MACHINE where id = '"+ id +"'";
		final JSONObject json = new JSONObject();
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){//数据库操作
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					try {
						while(rs.next()){	
							if(rs.getString("bianhao")!=""){
								json.put("bianhao", rs.getString("bianhao"));
							}
							if(rs.getString("xinghao")!=""){
								json.put("xinghao", rs.getString("xinghao"));
							}if(rs.getString("xingcheng")!=""){
								json.put("xingcheng", rs.getString("xingcheng"));
							}if(rs.getString("chicun")!=""){
								json.put("chicun", rs.getString("chicun"));
							}if(rs.getString("zhoushu")!=""){
								json.put("zhoushu", rs.getString("zhoushu"));
							}if(rs.getString("niuju")!=""){
								json.put("niuju", rs.getString("niuju"));
							}if(rs.getString("jingeili")!=""){
								json.put("jingeili", rs.getString("jingeili"));
							}if(rs.getString("maxzhuansu")!=""){
								json.put("zhuansumax", rs.getString("maxzhuansu"));
							}if(rs.getString("minzhuansu")!=""){
								json.put("zhuansumin", rs.getString("minzhuansu"));
							}if(rs.getString("vmax")!=""){
								json.put("vmax", rs.getString("vmax"));
							}if(rs.getString("vmin")!=""){
								json.put("vmin", rs.getString("vmin"));
							}if(rs.getString("jingdu")!=""){
								json.put("jingdu", rs.getString("jingdu"));
							}if(rs.getString("power")!=""){
								json.put("power", rs.getString("power"));
							}if(rs.getString("company")!=""){
								json.put("company", rs.getString("company"));
							}if(rs.getString("beizhu")!=""){
								json.put("beizhu", rs.getString("beizhu"));
							}
						}
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
		return json;
	}

	@Override
	public String DelMachine(HashMap hashmap) {
		// TODO Auto-generated method stub
		final String id = hashmap.get("id").toString();
		String[] sql = new String[1];
		String result = "true";
		sql[0] = "delete from T_MACHINE where id in ('',"+ id +")";
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
	public String EditMachine(final HashMap hashmap) {
		// TODO Auto-generated method stub
		final String id = hashmap.get("id").toString();
		String result = "true";
		final String bianhao = hashmap.get("bianhao").toString();
		final String xinghao = hashmap.get("xinghao").toString();
		String sql = "select count(*) as ct from T_MACHINE where xinghao = '"+xinghao+"'and id!='"+id+"'";
		Logger.debug(sql);
		final String [] count = new String[1];
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						count[0] = rs.getString("ct");
					}
				}
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if("0".equals(count[0])){
			String[] sqls = new String[1];
			sqls[0] = "update T_MACHINE set bianhao=?,xinghao=?,xingcheng=?,chicun=?,zhoushu=?,niuju=?,jingeili=?,maxzhuansu=?,minzhuansu=?,vmax=?,vmin=?,jingdu=?,power=?,company=?,beizhu=? where id = '"+ id +"'";
			try {
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)throws SQLException {
						// TODO Auto-generated method stub
						if(hashmap.get("bianhao").toString() != ""){
							pstmts[0].setString(1,hashmap.get("bianhao").toString());
							}else{
								pstmts[0].setString(1,"");
						}
						if(hashmap.get("xinghao").toString() != ""){
						pstmts[0].setString(2,hashmap.get("xinghao").toString());
						}else{
							pstmts[0].setString(2,"");
						}if(hashmap.get("xingcheng").toString() != ""){
							pstmts[0].setString(3,hashmap.get("xingcheng").toString());
						}else{
							pstmts[0].setString(3,"");
						}
						if(hashmap.get("chicun").toString() != ""){
							pstmts[0].setString(4,hashmap.get("chicun").toString());
						}else{
							pstmts[0].setString(4,"");
						}if(hashmap.get("zhoushu").toString() != ""){
							pstmts[0].setString(5,hashmap.get("zhoushu").toString());
						}else{
							pstmts[0].setString(5,"");
						}if(hashmap.get("niuju").toString() != ""){
							pstmts[0].setString(6,hashmap.get("niuju").toString());
						}else{
							pstmts[0].setString(6,"");
						}if(hashmap.get("jingeili").toString() != ""){
							pstmts[0].setString(7,hashmap.get("jingeili").toString());
						}else{
							pstmts[0].setString(7,"");
						}if(hashmap.get("maxzhuansu").toString() != ""){
							pstmts[0].setString(8,hashmap.get("maxzhuansu").toString());
						}else{
							pstmts[0].setString(8,"");
						}if(hashmap.get("minzhuansu").toString() != ""){
							pstmts[0].setString(9,hashmap.get("minzhuansu").toString());
						}else{
							pstmts[0].setString(9,"");
						}if(hashmap.get("vmax").toString() != ""){
							pstmts[0].setString(10,hashmap.get("vmax").toString());
						}else{
							pstmts[0].setString(10,"");
						}if(hashmap.get("vmin").toString() != ""){
							pstmts[0].setString(11,hashmap.get("vmin").toString());
						}else{
							pstmts[0].setString(11,"");
						}if(hashmap.get("jingdu").toString() != ""){
							pstmts[0].setString(12,hashmap.get("jingdu").toString());
						}else{
							pstmts[0].setString(12,"");
						}if(hashmap.get("power").toString() != ""){
							pstmts[0].setString(13,hashmap.get("power").toString());
						}else{
							pstmts[0].setString(13,"");
						}if(hashmap.get("company").toString() != ""){
							pstmts[0].setString(14,hashmap.get("company").toString());
						}else{
							pstmts[0].setString(14,"");
						}if(hashmap.get("beizhu").toString() != ""){
							pstmts[0].setString(15,hashmap.get("beizhu").toString());
						}else{
							pstmts[0].setString(15,"");
						}
						pstmts[0].addBatch();
					}
				});
				result = "true";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "false";
			}
		}else{
			result = "repeat";
		}
		return result;
	}

	@Override
	public JSONObject getQueryMachineAll() {
		// TODO Auto-generated method stub
		String sql = "select * from T_MACHINE";
		final JSONObject jsonObj = new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("xinghao", rs.getString("xinghao"));
							
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

}
