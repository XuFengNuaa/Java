package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.CutterMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class CutterModImpl implements CutterMod{

	@Override
	public JSONObject QueryAllCutter(HashMap hashmap) {
		// TODO Auto-generated method stub
		int start = Integer.parseInt((String)hashmap.get("start"));
		int limit = Integer.parseInt((String)hashmap.get("limit"));
		String filter = (String)hashmap.get("filter");
		String order = (String)hashmap.get("order");	
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		System.out.println(filter+""+order);
		String sql1 = "select count(*) as ct from T_CUTTER where "+ filter;
		String sql2 = "select * from (select * from  (select * from T_CUTTER where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from T_CUTTER where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
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
				try {
					while(rs.next()){
						
							JSONObject	json = new JSONObject();
							if(rs.getString("id")!=""){
								json.put("id", rs.getString("id"));
							}
							if(rs.getString("leixing")!=""){
								json.put("leixing", rs.getString("leixing"));
							}
							if(rs.getString("mingcheng")!=""){
								json.put("mingcheng", rs.getString("mingcheng"));
							}if(rs.getString("bianhao")!=""){
								json.put("bianhao", rs.getString("bianhao"));
							}if(rs.getString("company")!=""){
								json.put("company", rs.getString("company"));
							}if(rs.getString("xinghao")!=""){
								json.put("xinghao", rs.getString("xinghao"));
							}if(rs.getString("daoti")!=""){
								json.put("daoti", rs.getString("daoti"));
							}if(rs.getString("tuceng")!=""){
								json.put("tuceng", rs.getString("tuceng"));
							}if(rs.getString("zhijing")!=""){
								json.put("zhijing", rs.getString("zhijing"));
							}if(rs.getString("zhijinggc")!=""){
								json.put("zhijinggc", rs.getString("zhijinggc"));
							}if(rs.getString("daobing")!=""){
								json.put("daobing", rs.getString("daobing"));
							}if(rs.getString("dbgg")!=""){
								json.put("dbgg", rs.getString("dbgg"));
							}if(rs.getString("quanchang")!=""){
								json.put("quanchang", rs.getString("quanchang"));
							}if(rs.getString("renchang")!=""){
								json.put("renchang", rs.getString("renchang"));
							}if(rs.getString("renshu")!=""){
								json.put("renshu", rs.getString("renshu"));
							}if(rs.getString("lx")!=""){
								json.put("lx", rs.getString("lx"));
							}if(rs.getString("chishu")!=""){
								json.put("chishu", rs.getString("chishu"));
							}if(rs.getString("djjd")!=""){
								json.put("djjd", rs.getString("djjd"));
							}if(rs.getString("maxv")!=""){
								json.put("maxv", rs.getString("maxv"));
							}if(rs.getString("shjg1")!=""){
								json.put("shjg1", rs.getString("shjg1"));
							}if(rs.getString("shjg2")!=""){
								json.put("shjg2", rs.getString("shjg2"));
							}if(rs.getString("shjg3")!=""){
								json.put("shjg3", rs.getString("shjg3"));
							}if(rs.getString("xlh")!=""){
								json.put("xlh", rs.getString("xlh"));
							}if(rs.getString("daojiao")!=""){
								json.put("daojiao", rs.getString("daojiao"));
							}if(rs.getString("qj")!=""){
								json.put("qj", rs.getString("qj"));
							}if(rs.getString("hj")!=""){
								json.put("hj", rs.getString("hj"));
							}if(rs.getString("zp")!=""){
								json.put("zp", rs.getString("zp"));
							}if(rs.getString("fp")!=""){
								json.put("fp", rs.getString("fp"));
							}if(rs.getString("djc")!=""){
								json.put("djc", rs.getString("djc"));
							}if(rs.getString("rjbj")!=""){
								json.put("rjbj", rs.getString("rjbj"));
							}if(rs.getString("beizhu")!=""){
								json.put("beizhu", rs.getString("beizhu"));
							}
							
							jsonarray.put(json);
					}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
	public String AddCutter(final HashMap hashmap) {
		// TODO Auto-generated method stub
		final String xinghao = hashmap.get("xinghao").toString();
		String sql = "select count(*) as ct from T_CUTTER where xinghao = '"+xinghao+"'";
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
			sql1[0] = "insert into T_CUTTER (id,leixing,mingcheng,bianhao,company,xinghao,daoti,tuceng,zhijing,zhijinggc,daobing,dbgg,quanchang,renchang,renshu,lx,chishu,djjd,maxv,shjg1,shjg2,shjg3,xlh,daojiao,qj,hj,zp,fp,djc,rjbj,beizhu) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Logger.debug(sql1[0]);
			try {
				DbUtil.executeBatchs(sql1,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)throws SQLException {
						// TODO Auto-generated method stub
						pstmts[0].setString(1,id);
						pstmts[0].setString(2,hashmap.get("leixing").toString());
						if(hashmap.get("mingcheng").toString() != ""){
						pstmts[0].setString(3,hashmap.get("mingcheng").toString());
						}else{
							pstmts[0].setString(3,"");
						}if(hashmap.get("bianhao").toString() != ""){
							pstmts[0].setString(4,hashmap.get("bianhao").toString());
						}else{
							pstmts[0].setString(4,"");
						}
						if(hashmap.get("company").toString() != ""){
							pstmts[0].setString(5,hashmap.get("company").toString());
						}else{
							pstmts[0].setString(5,"");
						}if(hashmap.get("xinghao").toString() != ""){
							pstmts[0].setString(6,hashmap.get("xinghao").toString());
						}else{
							pstmts[0].setString(6,"");
						}if(hashmap.get("daoti").toString() != ""){
							pstmts[0].setString(7,hashmap.get("daoti").toString());
						}else{
							pstmts[0].setString(7,"");
						}if(hashmap.get("tuceng").toString() != ""){
							pstmts[0].setString(8,hashmap.get("tuceng").toString());
						}else{
							pstmts[0].setString(8,"");
						}if(hashmap.get("zhijing").toString() != ""){
							pstmts[0].setString(9,hashmap.get("zhijing").toString());
						}else{
							pstmts[0].setString(9,"");
						}if(hashmap.get("zhijinggc").toString() != ""){
							pstmts[0].setString(10,hashmap.get("zhijinggc").toString());
						}else{
							pstmts[0].setString(10,"");
						}if(hashmap.get("daobing").toString() != ""){
							pstmts[0].setString(11,hashmap.get("daobing").toString());
						}else{
							pstmts[0].setString(11,"");
						}if(hashmap.get("dbgg").toString() != ""){
							pstmts[0].setString(12,hashmap.get("dbgg").toString());
						}else{
							pstmts[0].setString(12,"");
						}if(hashmap.get("quanchang").toString() != ""){
							pstmts[0].setString(13,hashmap.get("quanchang").toString());
						}else{
							pstmts[0].setString(13,"");
						}if(hashmap.get("renchang").toString() != ""){
							pstmts[0].setString(14,hashmap.get("renchang").toString());
						}else{
							pstmts[0].setString(14,"");
						}if(hashmap.get("renshu").toString() != ""){
							pstmts[0].setString(15,hashmap.get("renshu").toString());
						}else{
							pstmts[0].setString(15,"");
						}if(hashmap.get("lx").toString() != ""){
							pstmts[0].setString(16,hashmap.get("lx").toString());
						}else{
							pstmts[0].setString(16,"");
						}if(hashmap.get("chishu").toString() != ""){
							pstmts[0].setString(17,hashmap.get("chishu").toString());
						}else{
							pstmts[0].setString(17,"");
						}if(hashmap.get("djjd").toString() != ""){
							pstmts[0].setString(18,hashmap.get("djjd").toString());
						}else{
							pstmts[0].setString(18,"");
						}if(hashmap.get("maxv").toString() != ""){
							pstmts[0].setString(19,hashmap.get("maxv").toString());
						}else{
							pstmts[0].setString(19,"");
						}if(hashmap.get("shjg1").toString() != ""){
							pstmts[0].setString(20,hashmap.get("shjg1").toString());
						}else{
							pstmts[0].setString(20,"");
						}if(hashmap.get("shjg2").toString() != ""){
							pstmts[0].setString(21,hashmap.get("shjg2").toString());
						}else{
							pstmts[0].setString(21,"");
						}if(hashmap.get("shjg3").toString() != ""){
							pstmts[0].setString(22,hashmap.get("shjg3").toString());
						}else{
							pstmts[0].setString(22,"");
						}if(hashmap.get("xlh").toString() != ""){
							pstmts[0].setString(23,hashmap.get("xlh").toString());
						}else{
							pstmts[0].setString(23,"");
						}if(hashmap.get("daojiao").toString() != ""){
							pstmts[0].setString(24,hashmap.get("daojiao").toString());
							System.out.println(hashmap.get("daojiao").toString());
						}else{
							pstmts[0].setString(24,"");
						}if(hashmap.get("qj").toString() != ""){
							pstmts[0].setString(25,hashmap.get("qj").toString());
							System.out.println(hashmap.get("qj").toString());
						}else{
							pstmts[0].setString(25,"");
						}if(hashmap.get("hj").toString() != ""){
							pstmts[0].setString(26,hashmap.get("hj").toString());
						}else{
							pstmts[0].setString(26,"");
						}if(hashmap.get("zp").toString() != ""){
							pstmts[0].setString(27,hashmap.get("zp").toString());
						}else{
							pstmts[0].setString(27,"");
						}if(hashmap.get("fp").toString() != ""){
							pstmts[0].setString(28,hashmap.get("fp").toString());
						}else{
							pstmts[0].setString(28,"");
						}if(hashmap.get("djc").toString() != ""){
							pstmts[0].setString(29,hashmap.get("djc").toString());
						}else{
							pstmts[0].setString(29,"");
						}if(hashmap.get("rjbj").toString() != ""){
							pstmts[0].setString(30,hashmap.get("rjbj").toString());
						}else{
							pstmts[0].setString(30,"");
						}if(hashmap.get("beizhu").toString() != ""){
							pstmts[0].setString(31,hashmap.get("beizhu").toString());
						}else{
							pstmts[0].setString(31,"");
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
	public JSONObject ViewCutter(HashMap hashmap) {
		// TODO Auto-generated method stub
		final String id = hashmap.get("id").toString();
		String sql = "select * from T_CUTTER where id = '"+ id +"'";
		final JSONObject json = new JSONObject();
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					try {
						while(rs.next()){
							if(rs.getString("leixing")!=""){
								json.put("leixing", rs.getString("leixing"));
							}
							if(rs.getString("mingcheng")!=""){
								json.put("mingcheng", rs.getString("mingcheng"));
							}if(rs.getString("bianhao")!=""){
								json.put("bianhao", rs.getString("bianhao"));
							}if(rs.getString("company")!=""){
								json.put("company", rs.getString("company"));
							}if(rs.getString("xinghao")!=""){
								json.put("xinghao", rs.getString("xinghao"));
							}if(rs.getString("daoti")!=""){
								json.put("daoti", rs.getString("daoti"));
							}if(rs.getString("tuceng")!=""){
								json.put("tuceng", rs.getString("tuceng"));
							}if(rs.getString("zhijing")!=""){
								json.put("zhijing", rs.getString("zhijing"));
							}if(rs.getString("zhijinggc")!=""){
								json.put("zhijinggc", rs.getString("zhijinggc"));
							}if(rs.getString("daobing")!=""){
								json.put("daobing", rs.getString("daobing"));
							}if(rs.getString("dbgg")!=""){
								json.put("dbgg", rs.getString("dbgg"));
							}if(rs.getString("quanchang")!=""){
								json.put("quanchang", rs.getString("quanchang"));
							}if(rs.getString("renchang")!=""){
								json.put("renchang", rs.getString("renchang"));
							}if(rs.getString("renshu")!=""){
								json.put("renshu", rs.getString("renshu"));
							}if(rs.getString("lx")!=""){
								json.put("lx", rs.getString("lx"));
							}if(rs.getString("chishu")!=""){
								json.put("chishu", rs.getString("chishu"));
							}if(rs.getString("djjd")!=""){
								json.put("djjd", rs.getString("djjd"));
							}if(rs.getString("maxv")!=""){
								json.put("maxv", rs.getString("maxv"));
							}if(rs.getString("shjg1")!=""){
								json.put("shjg1", rs.getString("shjg1"));
							}if(rs.getString("shjg2")!=""){
								json.put("shjg2", rs.getString("shjg2"));
							}if(rs.getString("shjg3")!=""){
								json.put("shjg3", rs.getString("shjg3"));
							}if(rs.getString("xlh")!=""){
								json.put("xlh", rs.getString("xlh"));
							}if(rs.getString("daojiao")!=""){
								json.put("daojiao", rs.getString("daojiao"));
							}if(rs.getString("qj")!=""){
								json.put("qj", rs.getString("qj"));
							}if(rs.getString("hj")!=""){
								json.put("hj", rs.getString("hj"));
							}if(rs.getString("zp")!=""){
								json.put("zp", rs.getString("zp"));
							}if(rs.getString("fp")!=""){
								json.put("fp", rs.getString("fp"));
							}if(rs.getString("djc")!=""){
								json.put("djc", rs.getString("djc"));
							}if(rs.getString("rjbj")!=""){
								json.put("rjbj", rs.getString("rjbj"));
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
	public String DelCutter(HashMap hashmap) {
		// TODO Auto-generated method stub
		final String id = hashmap.get("id").toString();
		String[] sql = new String[1];
		String result = "true";
		sql[0] = "delete from T_CUTTER where id in ('',"+ id +")";
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
	public String EditCutter(final HashMap hashmap) {
		// TODO Auto-generated method stub
		final String id = hashmap.get("id").toString();
		String result = "true";
		final String xinghao = hashmap.get("xinghao").toString();
		String sql = "select count(*) as ct from T_CUTTER where xinghao = '"+xinghao+"'and id!='"+id+"'";
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
			sqls[0] = "update T_CUTTER set leixing=?,mingcheng=?,bianhao=?,company=?,xinghao=?,daoti=?,tuceng=?,zhijing=?,zhijinggc=?,daobing=?,dbgg=?,quanchang=?,renchang=?,renshu=?,lx=?,chishu=?,djjd=?,maxv=?,shjg1=?,shjg2=?,shjg3=?,xlh=?,daojiao=?,qj=?,hj=?,zp=?,fp=?,djc=?,rjbj=?,beizhu=? where id = '"+ id +"'";
			try {
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)throws SQLException {
						// TODO Auto-generated method stub
						pstmts[0].setString(1,hashmap.get("leixing").toString());
						if(hashmap.get("mingcheng").toString() != ""){
						pstmts[0].setString(2,hashmap.get("mingcheng").toString());
						}else{
							pstmts[0].setString(2,"");
						}if(hashmap.get("bianhao").toString() != ""){
							pstmts[0].setString(3,hashmap.get("bianhao").toString());
						}else{
							pstmts[0].setString(3,"");
						}
						if(hashmap.get("company").toString() != ""){
							pstmts[0].setString(4,hashmap.get("company").toString());
						}else{
							pstmts[0].setString(4,"");
						}if(hashmap.get("xinghao").toString() != ""){
							pstmts[0].setString(5,hashmap.get("xinghao").toString());
						}else{
							pstmts[0].setString(5,"");
						}if(hashmap.get("daoti").toString() != ""){
							pstmts[0].setString(6,hashmap.get("daoti").toString());
						}else{
							pstmts[0].setString(6,"");
						}if(hashmap.get("tuceng").toString() != ""){
							pstmts[0].setString(7,hashmap.get("tuceng").toString());
						}else{
							pstmts[0].setString(7,"");
						}if(hashmap.get("zhijing").toString() != ""){
							pstmts[0].setString(8,hashmap.get("zhijing").toString());
						}else{
							pstmts[0].setString(8,"");
						}if(hashmap.get("zhijinggc").toString() != ""){
							pstmts[0].setString(9,hashmap.get("zhijinggc").toString());
						}else{
							pstmts[0].setString(9,"");
						}if(hashmap.get("daobing").toString() != ""){
							pstmts[0].setString(10,hashmap.get("daobing").toString());
						}else{
							pstmts[0].setString(10,"");
						}if(hashmap.get("dbgg").toString() != ""){
							pstmts[0].setString(11,hashmap.get("dbgg").toString());
						}else{
							pstmts[0].setString(11,"");
						}if(hashmap.get("quanchang").toString() != ""){
							pstmts[0].setString(12,hashmap.get("quanchang").toString());
						}else{
							pstmts[0].setString(12,"");
						}if(hashmap.get("renchang").toString() != ""){
							pstmts[0].setString(13,hashmap.get("renchang").toString());
						}else{
							pstmts[0].setString(13,"");
						}if(hashmap.get("renshu").toString() != ""){
							pstmts[0].setString(14,hashmap.get("renshu").toString());
						}else{
							pstmts[0].setString(14,"");
						}if(hashmap.get("lx").toString() != ""){
							pstmts[0].setString(15,hashmap.get("lx").toString());
						}else{
							pstmts[0].setString(15,"");
						}if(hashmap.get("chishu").toString() != ""){
							pstmts[0].setString(16,hashmap.get("chishu").toString());
						}else{
							pstmts[0].setString(16,"");
						}if(hashmap.get("djjd").toString() != ""){
							pstmts[0].setString(17,hashmap.get("djjd").toString());
						}else{
							pstmts[0].setString(17,"");
						}if(hashmap.get("maxv").toString() != ""){
							pstmts[0].setString(18,hashmap.get("maxv").toString());
						}else{
							pstmts[0].setString(18,"");
						}if(hashmap.get("shjg1").toString() != ""){
							pstmts[0].setString(19,hashmap.get("shjg1").toString());
						}else{
							pstmts[0].setString(19,"");
						}if(hashmap.get("shjg2").toString() != ""){
							pstmts[0].setString(20,hashmap.get("shjg2").toString());
						}else{
							pstmts[0].setString(20,"");
						}if(hashmap.get("shjg3").toString() != ""){
							pstmts[0].setString(21,hashmap.get("shjg3").toString());
						}else{
							pstmts[0].setString(21,"");
						}if(hashmap.get("xlh").toString() != ""){
							pstmts[0].setString(22,hashmap.get("xlh").toString());
						}else{
							pstmts[0].setString(22,"");
						}if(hashmap.get("daojiao").toString() != ""){
							pstmts[0].setString(23,hashmap.get("daojiao").toString());
							System.out.println(hashmap.get("daojiao").toString());
						}else{
							pstmts[0].setString(23,"");
						}if(hashmap.get("qj").toString() != ""){
							pstmts[0].setString(24,hashmap.get("qj").toString());
							System.out.println(hashmap.get("qj").toString());
						}else{
							pstmts[0].setString(24,"");
						}if(hashmap.get("hj").toString() != ""){
							pstmts[0].setString(25,hashmap.get("hj").toString());
						}else{
							pstmts[0].setString(25,"");
						}if(hashmap.get("zp").toString() != ""){
							pstmts[0].setString(26,hashmap.get("zp").toString());
						}else{
							pstmts[0].setString(26,"");
						}if(hashmap.get("fp").toString() != ""){
							pstmts[0].setString(27,hashmap.get("fp").toString());
						}else{
							pstmts[0].setString(27,"");
						}if(hashmap.get("djc").toString() != ""){
							pstmts[0].setString(28,hashmap.get("djc").toString());
						}else{
							pstmts[0].setString(28,"");
						}if(hashmap.get("rjbj").toString() != ""){
							pstmts[0].setString(29,hashmap.get("rjbj").toString());
						}else{
							pstmts[0].setString(29,"");
						}if(hashmap.get("beizhu").toString() != ""){
							pstmts[0].setString(30,hashmap.get("beizhu").toString());
						}else{
							pstmts[0].setString(30,"");
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
	public JSONObject QueryAllCompany() {
		// TODO Auto-generated method stub
		final JSONObject jsonobj = new JSONObject();
		final JSONArray jsonarry = new JSONArray();
		String sql = "select distinct company from T_CUTTER";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){

				@Override
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						try {
							JSONObject json = new JSONObject();
							json.put("company", rs.getString("company"));
							jsonarry.put(json);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					try {
						jsonobj.put("filedata", jsonarry);
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
		return jsonobj;
	}

	@Override
	public JSONObject QueryXLByCom(HashMap hashmap) {
		// TODO Auto-generated method stub
		final String company = hashmap.get("company").toString();
		String sql = "";
		JSONObject obj = new JSONObject();
		final JSONArray array = new JSONArray();
		sql = "select distinct xlh from T_CUTTER where company = '"+company+"'";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						JSONObject json = new JSONObject();
						try {
							json.put("xlh",rs.getString("xlh"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						array.put(json);
					}
				}
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			obj.put("filedata",array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public JSONObject QueryZHIJINGByXLH(HashMap hashmap) {
		// TODO Auto-generated method stub
		final String xlh = hashmap.get("xlh").toString();
		final String company = hashmap.get("company").toString();
		String sql = "";
		JSONObject obj = new JSONObject();
		final JSONArray array = new JSONArray();
		sql = "select distinct zhijing from T_CUTTER where xlh = '"+xlh+"'and company='"+company+"'";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						JSONObject json = new JSONObject();
						try {
							json.put("zhijing",rs.getString("zhijing"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						array.put(json);
					}
				}
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			obj.put("filedata",array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public JSONObject QueryRJBJByZJ(HashMap hashmap) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		final JSONArray array = new JSONArray();
		final String company = hashmap.get("company").toString();
		final String xlh = hashmap.get("xlh").toString();
		final String zhijing = hashmap.get("zhijing").toString();
		String sql = "select distinct rjbj from T_CUTTER where company='"+company+"'and xlh = '"+xlh+"'and zhijing ='"+zhijing+"'";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						JSONObject json = new JSONObject();
						try {
							json.put("rjbj",rs.getString("rjbj"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						array.put(json);
					}
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			obj.put("filedata",array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public JSONObject QueryDJXH(HashMap hashmap) {
		// TODO Auto-generated method stub
		final String company = hashmap.get("company").toString();
		final String xlh = hashmap.get("xlh").toString();
		final String zhijing = hashmap.get("zhijing").toString();
		final String rjbj = hashmap.get("rjbj").toString();
		final JSONObject jsonobj = new JSONObject();
		final JSONArray jsonarry = new JSONArray();
		String sql = "select distinct xinghao from T_CUTTER where company='"+company+"'and xlh = '"+xlh+"'and zhijing ='"+zhijing+"'and rjbj = '"+rjbj+"'";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){

				@Override
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						try {
							JSONObject json = new JSONObject();
							json.put("xinghao", rs.getString("xinghao"));
							jsonarry.put(json);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					try {
						jsonobj.put("filedata", jsonarry);
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
		return jsonobj;
	}

	@Override
	public JSONObject QueryZHIJINGByCOM(HashMap hashmap) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		final JSONArray array = new JSONArray();
		final String company = hashmap.get("company").toString();

		String sql = "select distinct zhijing from T_CUTTER where company='"+company+"'";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						JSONObject json = new JSONObject();
						try {
							json.put("zhijing",rs.getString("zhijing"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						array.put(json);
					}
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			obj.put("filedata",array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

}
