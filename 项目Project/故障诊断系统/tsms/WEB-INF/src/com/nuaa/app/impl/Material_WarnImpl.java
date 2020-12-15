package com.nuaa.app.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.Material_Warn;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;

public class Material_WarnImpl implements Material_Warn{

	public JSONObject Material_Warn(HashMap hashmap) {
		final String filter = (String) hashmap.get("filter");
		final String order = (String) hashmap.get("order");
		final String text = (String) hashmap.get("text");
		final int preday = Integer.parseInt(text);
		System.out.println(preday);
		final String userlevel = (String)hashmap.get("userlevel");
	    final String[] count = new String[1];
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		if("8".equals(userlevel)){
			String sql = "select count(*) as ct from vmaterial_com  where "+filter;
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
			

         String sql1 = "select * from vmaterial_com where " + filter + "and valiadate < sysdate +"+preday;
         System.out.println(sql1);
				try {
					DbUtil.execute(sql1,new IResultSetProcessor(){
						public void process(ResultSet rs) throws SQLException {
							while(rs.next()){
								String ordernum = rs.getString("ordernum");
								String id = rs.getString("id");
								//获取ordernum下的移库量
								String sql = "select count(*)as ct from t_material_delete_record where id ='"+ id +"'and kuname='海波库'";
								DbUtil.execute(sql,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										while(rs.next()){
											if("0".equals(rs.getString("ct"))){
												count[0]="1";
											}else{
												count[0]="0";
											}
										}
									}
								});
								if("1".equals(count[0])){
									final JSONObject json = new JSONObject();
									try {
										json.put("ordernum",rs.getString("ordernum"));
										json.put("kuname",rs.getString("kuname"));
										json.put("shelfnum",rs.getString("shelfnum"));
										json.put("valiadate",rs.getDate("valiadate"));
										json.put("indate",rs.getDate("indate"));
										json.put("incount",rs.getString("incount"));
										json.put("company",rs.getString("company"));
										json.put("modelnum",rs.getString("modelnum"));
										json.put("spec",rs.getString("spec"));
									} catch (JSONException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									//获取ordernum下的移库量
									String sql4 = "select changecount from t_material_change_record where oldid = '" + id +"' and orgkuname = '海波库'";
									DbUtil.execute(sql4,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											JSONObject sumobj = new JSONObject();
											int sum = 0;
											while(rs.next()){
											sum += rs.getInt("changecount");
											}
											try {
												json.put("changecount",sum);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});
									//计算出库量
									String sql5 = "select outcount from t_material_out_record where id = '" + id +"' and kuname = '海波库'";
									DbUtil.execute(sql5,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											JSONObject sumobj = new JSONObject();
											int sum = 0;
											while(rs.next()){
											sum += rs.getInt("outcount");
											}
											try {
												json.put("outcount", sum);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});
									
									try {
										int nowcount = 0;
										nowcount = json.getInt("incount") - json.getInt("outcount") - json.getInt("changecount");
										if(nowcount==0){
											continue;
										}else{
										json.put("nowcount", nowcount);
										}
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									jsonarray.put(json);
								}else{
									continue;
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
					
		}else if("9".equals(userlevel)){
			String sql = "select count(*) as ct from v_matchange_com  where "+filter+"and newkuname = '调漆间'";
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
			
			String sql1 = "select * from v_matchange_com where " + filter + "and valiadate < sysdate +"+preday+"and newkuname='调漆间'";
	         System.out.println(sql1);
					try {
						DbUtil.execute(sql1,new IResultSetProcessor(){
							public void process(ResultSet rs) throws SQLException {
								while(rs.next()){
									String id = rs.getString("newid");
									//获取ordernum下的移库量
									String sql = "select count(*)as ct from t_material_delete_record where id ='"+ id+"'and kuname='调漆间'";
									DbUtil.execute(sql,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											while(rs.next()){
												if("0".equals(rs.getString("ct"))){
													count[0]="1";
												}else{
													count[0]="0";
												}
											}
										}
									});
									if("1".equals(count[0])){
										final JSONObject json = new JSONObject();
										try {
											json.put("ordernum",rs.getString("ordernum"));
											json.put("kuname",rs.getString("newkuname"));
											json.put("shelfnum",rs.getString("newshelfnum"));
											json.put("valiadate",rs.getDate("valiadate"));
											
											json.put("indate",rs.getDate("changedate"));
											json.put("incount",rs.getString("changecount"));
											json.put("company",rs.getString("company"));
											json.put("modelnum",rs.getString("modelnum"));
											json.put("spec",rs.getString("spec"));
										} catch (JSONException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										//获取ordernum下的移库量
										String sql4 = "select changecount from t_material_change_record where oldid = '" + id +"' and orgkuname = '调漆间'";
										DbUtil.execute(sql4,new IResultSetProcessor(){
											public void process(ResultSet rs)throws SQLException {
												JSONObject sumobj = new JSONObject();
												int sum = 0;
												while(rs.next()){
												sum += rs.getInt("changecount");
												}
												try {
													json.put("changecount",sum);
												} catch (JSONException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
										});
										//计算出库量
										String sql5 = "select outcount from t_material_out_record where id = '" + id +"' and kuname = '调漆间'";
										DbUtil.execute(sql5,new IResultSetProcessor(){
											public void process(ResultSet rs)throws SQLException {
												JSONObject sumobj = new JSONObject();
												int sum = 0;
												while(rs.next()){
												sum += rs.getInt("outcount");
												}
												try {
													json.put("outcount", sum);
												} catch (JSONException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
										});
										
										try {
											int nowcount = 0;
											nowcount = json.getInt("incount") - json.getInt("outcount") - json.getInt("changecount");
											if(nowcount==0){
												continue;
											}else{
											json.put("nowcount", nowcount);
											}
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										jsonarray.put(json);
									}else{
										continue;
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
					
		}else if("10".equals(userlevel)){
			String sql = "select count(*) as ct from v_matchange_com  where "+filter+"and newkuname in ('江北库','句容库')";
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
			String sql1 = "select * from v_matchange_com where " + filter + "and valiadate < sysdate +"+preday+"and newkuname in ('江北库','句容库')";
	         System.out.println(sql1);
	         try {
				DbUtil.execute(sql1,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
							String ordernum = rs.getString("ordernum");
							String id = rs.getString("newid");
							//获取ordernum下的移库量
							String sql = "select count(*)as ct from t_material_delete_record where id ='"+ id+"'and kuname in ('江北库','句容库')";
							DbUtil.execute(sql,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									while(rs.next()){
										if("0".equals(rs.getString("ct"))){
											count[0]="1";
										}else{
											count[0]="0";
										}
									}
								}
							});
							if("1".equals(count[0])){
								final JSONObject json = new JSONObject();
								try {
									json.put("ordernum",rs.getString("ordernum"));
									json.put("kuname",rs.getString("newkuname"));
									json.put("shelfnum",rs.getString("newshelfnum"));
									json.put("valiadate",rs.getDate("valiadate"));
									json.put("indate",rs.getDate("changedate"));
									json.put("incount",rs.getString("changecount"));
									json.put("company",rs.getString("company"));
									json.put("modelnum",rs.getString("modelnum"));
									json.put("spec",rs.getString("spec"));
								} catch (JSONException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								String kuname = rs.getString("newkuname");
								if("江北库".equals(kuname)){
									String sql4 = "select changecount from t_material_change_record where oldid = '" + id +"' and orgkuname = '江北库'";
									DbUtil.execute(sql4,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											JSONObject sumobj = new JSONObject();
											int sum = 0;
											while(rs.next()){
											sum += rs.getInt("changecount");
											}
											try {
												json.put("changecount",sum);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});
									//计算出库量
									String sql5 = "select outcount from t_material_out_record where id = '" + id +"' and kuname = '江北库'";
									DbUtil.execute(sql5,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											JSONObject sumobj = new JSONObject();
											int sum = 0;
											while(rs.next()){
											sum += rs.getInt("outcount");
											}
											try {
												json.put("outcount", sum);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});
									try {
										int nowcount = 0;
										nowcount = json.getInt("incount") - json.getInt("outcount") - json.getInt("changecount");
										json.put("nowcount",nowcount);
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									jsonarray.put(json);
								}
								
								else if("句容库".equals(kuname)){
									String sql4 = "select changecount from t_material_change_record where oldid = '" + id +"' and orgkuname = '江北库'";
									DbUtil.execute(sql4,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											JSONObject sumobj = new JSONObject();
											int sum = 0;
											while(rs.next()){
											sum += rs.getInt("changecount");
											}
											try {
												json.put("changecount",sum);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});
									//计算出库量
									String sql5 = "select outcount from t_material_out_record where id = '" + id +"' and kuname = '江北库'";
									DbUtil.execute(sql5,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											JSONObject sumobj = new JSONObject();
											int sum = 0;
											while(rs.next()){
											sum += rs.getInt("outcount");
											}
											try {
												json.put("outcount", sum);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});
									try {
										int nowcount = 0;
										nowcount = json.getInt("incount") - json.getInt("outcount") - json.getInt("changecount");
										json.put("nowcount",nowcount);
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									jsonarray.put(json);
								}
								
							}
						}
					try {
						obj.put("filedata", jsonarray);
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
