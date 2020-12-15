package com.nuaa.app.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.QueryMaterial_In_Out;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;

public class QueryMaterial_In_OutImpl implements QueryMaterial_In_Out{
	public JSONObject QueryMaterial_In_Out(HashMap hashmap) {
		final String filter = (String) hashmap.get("filter");
		final String order = (String) hashmap.get("order");
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sqlq = "select count(*) as ct from v_material_in_out where " + filter;
		try {
			DbUtil.execute(sqlq,new IResultSetProcessor(){
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
		} catch (SQLException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		String sql = "select *  from v_material_in_out  where " + filter +"order by "+ order;
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						JSONObject json = new JSONObject();
						try {
							json.put("ordernum",rs.getString("ordernum"));
						} catch (JSONException e4) {
							// TODO Auto-generated catch block
							e4.printStackTrace();
						}//取出orsernum
						//取出生产日期
						try {
							if(rs.getString("producedat")!=""){
								json.put("producedate",rs.getString("producedat"));
							}else{
								json.put("producedate","");
							}
							//取出有效期
							if(rs.getString("valiadate")!=""){
								json.put("valiadate",rs.getString("valiadate"));
							}else{
								json.put("valiadate","");
							}
							//取出保质期
							if(rs.getString("guaranteedate")!=""){
								json.put("guaranteedate",rs.getString("guaranteedate"));
							}else{
								json.put("guaranteedate","");
							}
							//取出入库量
							if(rs.getString("incount")!=""){
								json.put("incount",rs.getString("incount"));
							}else{
								json.put("incount","");
							}
							//取出入库时间
							if(!(rs.getString("indate").equals(""))){
								json.put("indate",rs.getDate("indate"));
							}else{
								json.put("indate","");
							}
							//取出入库人
							if(rs.getString("inperson")!=""){
								json.put("inperson",rs.getString("inperson"));
							}else{
								json.put("inperson","");
							}
							//取出名称
							if(rs.getString("name")!=""){
								json.put("name",rs.getString("name"));
							}else{
								json.put("name","");
							}
							//取出规格
							if(rs.getString("spec")!=""){
								json.put("spec",rs.getString("spec"));
							}else{
								json.put("spec","");
							}
							//取出型号
							if(rs.getString("modelnum")!=""){
								json.put("modelnum",rs.getString("modelnum"));
							}else{
								json.put("modelnum","");
							}
							//取出原库名
							if(rs.getString("orgkuname")!=""){
								/*if(rs.getString("orgkuname").equals("海波库")){*/
								json.put("orgkuname",rs.getString("orgkuname"));
								/*}else{
									continue;
								}*/
							}else{
								json.put("orgkuname","");
							}
							//取出移向的库名
							if(rs.getString("newkuname")!=""){
								json.put("newkuname",rs.getString("newkuname"));
							}else{
								json.put("newkuname","");
							}
							//取出移库数量
							if(rs.getString("changecount")!=""){
								json.put("changecount",rs.getString("changecount"));
							}else{
								json.put("changecount","");
							}
							//取出移库人
							if(rs.getString("changeperson")!=""){
								json.put("changeperson",rs.getString("changeperson"));
							}else{
								json.put("changepersn","");
							}
							//取出移库日期
							
								json.put("changedate",rs.getString("changedate"));
							
							
							
							//取出出库库名
							if(rs.getString("outkuname")!=""){
								json.put("outkuname",rs.getString("outkuname"));
							}else{
								json.put("outkuname","");
							}
						} catch (JSONException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						//取出出库数量
						try {
							if(rs.getString("outcount")!=""){
								json.put("outcount",rs.getString("outcount"));
							}else{
								json.put("outcount","");
							}
							//取出出库日期
							
								json.put("outdate",rs.getDate("outdate"));
							
						} catch (JSONException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						//取出出库人
						try {
							if(rs.getString("outperson")!=""){
								json.put("outperson",rs.getString("outperson"));
							}else{
								json.put("outpersn","");
							}
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//取出领用人
						if(rs.getString("getperson")!=""){
							try {
								json.put("getperson",rs.getString("getperson"));
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else{
							try {
								json.put("getpersn","");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						jsonarray.put(json);
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
	public JSONObject QueryMaterial_In_OutTQWC(HashMap hashmap) {
		final String filter = (String) hashmap.get("filter");
		final String order = (String) hashmap.get("order");
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		final String kuname = (String) hashmap.get("kuname");
		if("tiaoqi".equals(kuname)){
			String sql = "select count(*) as ct from v_in_out_tqwc where inkuname = '调漆间' and "+filter;
			try {
				DbUtil.execute(sql,new IResultSetProcessor(){
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
			
			String sql1 = "select * from v_in_out_tqwc where inkuname = '调漆间' and "+filter+" order by "+order;
			try {
				DbUtil.execute(sql1,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
							final JSONObject json = new JSONObject();
							try {
								json.put("ordernum",rs.getString("ordernum"));
								json.put("inkuname",rs.getString("inkuname"));
								json.put("inshelfnum",rs.getString("inshelfnum"));
								json.put("incount",rs.getString("incount"));
								json.put("inperson",rs.getString("inperson"));
								json.put("indate",rs.getString("indate"));
								json.put("mnum",rs.getString("mnum"));
								json.put("spec",rs.getString("spec"));
								json.put("name",rs.getString("name"));
								json.put("guaranteedate",rs.getString("guaranteedate"));
								json.put("modelnum",rs.getString("modelnum"));
								json.put("valiadate",rs.getString("valiadate"));
								json.put("outkuname",rs.getString("outkuname"));
								json.put("outshelfnum",rs.getString("shelfnum"));
								json.put("outdate",rs.getString("outdate"));
								json.put("outcount",rs.getString("outcount"));
								json.put("outperson",rs.getString("outperson"));
								json.put("getperson",rs.getString("getperson"));
								/*String ordernum = rs.getString("ordernum");*/
								/*String oldid = rs.getString("newid");
								String sql1 = "select * from v_in_out_tqwc where orgkuname = '调漆间' and oldid='"+oldid+"'";
								DbUtil.execute(sql1,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										while(rs.next()){
											try {
												json.put("changekuname", rs.getString("inkuname"));
												json.put("changecount", rs.getString("incount"));
												json.put("changedate", rs.getString("indate"));
												json.put("changeshelfnum", rs.getString("inshelfnum"));
												json.put("changeperson", rs.getString("inperson"));
												json.put("changeshelfnum", rs.getString("inshelfnum"));
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
										}
									}
									
								});*/
								
								json.put("changekuname", rs.getString("orgkuname"));
								json.put("changecount", rs.getString("changecount"));
								json.put("changedate", rs.getString("changedate"));
								json.put("changeshelfnum", rs.getString("changeshelfnum"));
								json.put("changeperson", rs.getString("changeperson"));
								json.put("newkuname", rs.getString("newkuname1"));
								/*json.put("changeshelfnum", rs.getString("inshelfnum"));*/
										
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							jsonarray.put(json);
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
		}else if("jiangbei".equals(kuname)){
			String sql =  "select count(*) as ct from v_in_out_tqwc where inkuname = '江北库'  and "+filter;
			try {
				DbUtil.execute(sql,new IResultSetProcessor(){
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
			
			String sql1 = "select * from v_in_out_tqwc where inkuname = '江北库'  and "+filter+" order by "+order;
			try {
				DbUtil.execute(sql1,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
							final JSONObject json = new JSONObject();
							try {
								json.put("ordernum",rs.getString("ordernum"));
								json.put("inkuname",rs.getString("inkuname"));
								json.put("inshelfnum",rs.getString("inshelfnum"));
								json.put("incount",rs.getString("incount"));
								json.put("inperson",rs.getString("inperson"));
								json.put("indate",rs.getString("indate"));
								json.put("mnum",rs.getString("mnum"));
								json.put("spec",rs.getString("spec"));
								json.put("name",rs.getString("name"));
								json.put("guaranteedate",rs.getString("guaranteedate"));
								json.put("modelnum",rs.getString("modelnum"));
								json.put("valiadate",rs.getString("valiadate"));
								json.put("outkuname",rs.getString("outkuname"));
								json.put("outshelfnum",rs.getString("shelfnum"));
								json.put("outdate",rs.getString("outdate"));
								json.put("outcount",rs.getString("outcount"));
								json.put("outperson",rs.getString("outperson"));
								json.put("getperson",rs.getString("getperson"));
								/*String ordernum = rs.getString("ordernum");*/
								/*String oldid = rs.getString("newid");
								String sql1 = "select * from v_in_out_tqwc where orgkuname = '江北库' and oldid='"+oldid+"'";
								DbUtil.execute(sql1,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										while(rs.next()){
											try {
												json.put("changekuname", rs.getString("inkuname"));
												json.put("changecount", rs.getString("incount"));
												json.put("changedate", rs.getString("indate"));
												json.put("changeshelfnum", rs.getString("inshelfnum"));
												json.put("changeperson", rs.getString("inperson"));
												json.put("changeshelfnum", rs.getString("inshelfnum"));
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
										}
									}
									
								});*/
								
								json.put("changekuname", rs.getString("orgkuname"));
								json.put("changecount", rs.getString("changecount"));
								json.put("changedate", rs.getString("changedate"));
								json.put("changeshelfnum", rs.getString("changeshelfnum"));
								json.put("changeperson", rs.getString("changeperson"));
								json.put("newkuname", rs.getString("newkuname1"));
								/*json.put("changeshelfnum", rs.getString("inshelfnum"));*/
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							jsonarray.put(json);
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
		}else if("jurong".equals(kuname)){
			String sql = "select count(*) as ct from v_in_out_tqwc where inkuname = '句容库' and "+filter;
			try {
				DbUtil.execute(sql,new IResultSetProcessor(){
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
			
			String sql1 = "select * from v_in_out_tqwc where inkuname = '句容库'  and "+filter+" order by "+order;
			try {
				DbUtil.execute(sql1,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
							final JSONObject json = new JSONObject();
							try {
								json.put("ordernum",rs.getString("ordernum"));
								json.put("inkuname",rs.getString("inkuname"));
								json.put("inshelfnum",rs.getString("inshelfnum"));
								json.put("incount",rs.getString("incount"));
								json.put("inperson",rs.getString("inperson"));
								json.put("indate",rs.getString("indate"));
								json.put("mnum",rs.getString("mnum"));
								json.put("spec",rs.getString("spec"));
								json.put("name",rs.getString("name"));
								json.put("guaranteedate",rs.getString("guaranteedate"));
								json.put("modelnum",rs.getString("modelnum"));
								json.put("valiadate",rs.getString("valiadate"));
								json.put("outkuname",rs.getString("outkuname"));
								json.put("outshelfnum",rs.getString("shelfnum"));
								json.put("outdate",rs.getString("outdate"));
								json.put("outcount",rs.getString("outcount"));
								json.put("outperson",rs.getString("outperson"));
								json.put("getperson",rs.getString("getperson"));
								String oldid = rs.getString("newid");
								/*String sql1 = "select * from v_in_out_tqwc where orgkuname = '句容库' and oldid='"+oldid+"'";
								DbUtil.execute(sql1,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										while(rs.next()){
											try {
												json.put("changekuname", rs.getString("inkuname"));
												json.put("changecount", rs.getString("incount"));
												json.put("changedate", rs.getString("indate"));
												json.put("changeshelfnum", rs.getString("inshelfnum"));
												json.put("changeperson", rs.getString("inperson"));
												json.put("changeshelfnum", rs.getString("inshelfnum"));
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
										}
									}
									
								});*/
								json.put("changekuname", rs.getString("orgkuname"));
								json.put("changecount", rs.getString("changecount"));
								json.put("changedate", rs.getString("changedate"));
								json.put("changeshelfnum", rs.getString("changeshelfnum"));
								json.put("changeperson", rs.getString("changeperson"));
								json.put("newkuname", rs.getString("newkuname1"));
								/*json.put("changeshelfnum", rs.getString("inshelfnum"));*/
										
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							jsonarray.put(json);
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
		}
		return obj;
	}

}
