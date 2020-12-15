package com.nuaa.app.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.Store_SearchByMnum;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;

public class Store_SearchByMnumImpl implements Store_SearchByMnum{
	public JSONObject queryAllStoreMnnum(HashMap hashmap) {
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray(); 
		final String filter = (String) hashmap.get("filter");
		final String order = (String) hashmap.get("order");
		final String userlevel = (String) hashmap.get("userlevel");
		if("8".equals(userlevel)){
			String sql = "select count(distinct typeid) as ct from vmaterial_com where " + filter /*+" and valiadate >= sysdate"*/;
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
			
			String sql1 = "select distinct mnum from vmaterial_com where " + filter + " order by " + order;
			try {
				DbUtil.execute(sql1, new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
							final JSONObject json = new JSONObject();
							try {
								json.put("mnum",rs.getString("mnum"));
								/*json.put("typeid",rs.getString("typeid"));*/
								
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							final String mnum = rs.getString("mnum");
							/*final String delid = rs.getString("id");*/
							/*String typeid = rs.getString("typeid");*/
							String sql = "select * from t_material where mnum ='"+mnum+"'";
							DbUtil.execute(sql,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									while(rs.next()){
										final String mnum = rs.getString("mnum");
										try {
											json.put("name",rs.getString("name"));
											json.put("classname",rs.getString("classname"));
											json.put("modelnum",rs.getString("modelnum"));
											/*json.put("spec",rs.getString("spec"));*/
											json.put("minstore",rs.getString("minstore"));
											/*json.put("mincycle",rs.getString("mincycle"));*/
											json.put("maxstore",rs.getString("maxstore"));
											/*json.put("maxcycle",rs.getString("maxcycle"));*/
											/*json.put("measure",rs.getString("measure"));*/
											json.put("kuname","海波库");
											/*json.put("buycycle",rs.getString("buycycle"));
											json.put("monthuse",rs.getString("monthuse"));
											json.put("guaranteedate",rs.getString("guaranteedate"));
											json.put("singlepur",rs.getString("singlepur"));*/
										} catch (JSONException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										//计算入库量
										String sqlsumin = "select incount from vmaterial_com where mnum = '" + mnum+"'";
										DbUtil.execute(sqlsumin,new IResultSetProcessor(){
											public void process(ResultSet rs)throws SQLException {
												int insum = 0;
												while(rs.next()){
													insum += rs.getInt("incount");
												}
												try {
													json.put("incount",insum);
												} catch (JSONException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
											
										});
										//计算出库量
										String sql = "select outcount from v_matout_mnum where mnum = '" + mnum +"'and kuname = '海波库'";
										DbUtil.execute(sql,new IResultSetProcessor(){
											public void process(ResultSet rs)throws SQLException {
												int outsum = 0;
												while(rs.next()){
													outsum += rs.getInt("outcount");
												}
												try {
													json.put("outcount",outsum);
												} catch (JSONException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
											
										});
										//计算移库量
										String sql1 = "select changecount from v_matchange_com where mnum = '" + mnum +"' and orgkuname = '海波库'";
										DbUtil.execute(sql1,new IResultSetProcessor(){
											public void process(ResultSet rs)throws SQLException {
												int changesum = 0;
												while(rs.next()){
													changesum += rs.getInt("changecount");
												}
												try {
													json.put("changecount",changesum);
												} catch (JSONException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
											
										});
										//计算报废数量
										String sqldel = "select id from vmaterial_com where mnum = '" +mnum+"'";
										DbUtil.execute(sqldel,new IResultSetProcessor(){
											public void process(ResultSet rs)throws SQLException {
												while(rs.next()){
													String delid = rs.getString("id");
													String sql11 = "select delcount from v_del_com where mnum = '" + mnum + "' and kuname = '海波库'";
													DbUtil.execute(sql11,new IResultSetProcessor(){
														public void process(ResultSet rs)throws SQLException {
															int delsum = 0;
															while(rs.next()){
																delsum += rs.getInt("delcount");
																System.out
																		.println(delsum);
															}
															try {
																json.put("delcount",delsum);
															} catch (JSONException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
														}
														
													});
												}
											}
											
										});
										
										
										//计算在库量
										try {int nowcount = 0;
											 nowcount = json.getInt("incount") - json.getInt("outcount") - json.getInt("changecount") - json.getInt("delcount");
											 
												 json.put("nowcount",nowcount);
											 
											
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
									}
									
								}
								
							});
							try {
								if(json.getInt("nowcount")<=0){
									continue;
								}else{
									jsonarray.put(json);
								}
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
		}else if("9".equals(userlevel)){
			String sql = "select count(distinct mnum) as ct from v_matchange_com where newkuname = '调漆间'";
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
			
			String sqlchange = "select distinct mnum,newkuname from v_matchange_com where newkuname = '调漆间'";
			try {
				DbUtil.execute(sqlchange,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
							final JSONObject json = new JSONObject();
							try {
								
								json.put("mnum",rs.getString("mnum"));
								/*json.put("typeid",rs.getString("typeid"));*/
								
								
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							String mnum = rs.getString("mnum");
							String sql = "select * from t_material where mnum ='"+mnum+"'";
							
							DbUtil.execute(sql,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									while(rs.next()){
										String mnum = rs.getString("mnum");
										String typeid = rs.getString("id");
										try {
											json.put("name",rs.getString("name"));
											json.put("classname",rs.getString("classname"));
											json.put("modelnum",rs.getString("modelnum"));
											/*json.put("spec",rs.getString("spec"));*/
											json.put("minstore",rs.getString("minstore"));
										/*	json.put("mincycle",rs.getString("mincycle"));*/
											json.put("maxstore",rs.getString("maxstore"));
											/*json.put("maxcycle",rs.getString("maxcycle"));*/
											/*json.put("measure",rs.getString("measure"));*/
											json.put("kuname","调漆间");
											/*json.put("buycycle",rs.getString("buycycle"));
											json.put("monthuse",rs.getString("monthuse"));
											json.put("guaranteedate",rs.getString("guaranteedate"));
											json.put("singlepur",rs.getString("singlepur"));*/
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
										//计算入库量
										String sqltiaoqi = "select changecount from v_matchange_com where newkuname = '调漆间' and mnum ='"+ mnum +"'";
										DbUtil.execute(sqltiaoqi,new IResultSetProcessor(){
											public void process(ResultSet rs)throws SQLException {
												int insum = 0;
												while(rs.next()){
													insum += rs.getInt("changecount");
												}
												System.out
														.println(insum+"=insum");
												try {
													json.put("incount",insum);
												} catch (JSONException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}	
										});
										//计算移库量
										String sqlyiku = "select changecount from v_matchange_com where orgkuname = '调漆间' and mnum ='"+ mnum+"'";
										DbUtil.execute(sqlyiku,new IResultSetProcessor(){
											public void process(ResultSet rs)throws SQLException {
												int changesum = 0;
												while(rs.next()){
													changesum += rs.getInt("changecount");
												}
												System.out
												.println(changesum+"=changesum");
												try {
													json.put("changecount",changesum);
												} catch (JSONException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
											
										});
										//计算报废量
										/*String sqldel = "select newid from v_matchange_com where mnum = '" +mnum+"'";
										DbUtil.execute(sqldel,new IResultSetProcessor(){
											public void process(ResultSet rs)throws SQLException {
												while(rs.next()){
													String delid = rs.getString("newid");
													String sql11 = "select delcount from t_material_delete_record where id = '" + delid + "' and kuname = '调漆间'";
													DbUtil.execute(sql11,new IResultSetProcessor(){
														public void process(ResultSet rs)throws SQLException {
															int delsum = 0;
															while(rs.next()){
																delsum += rs.getInt("delcount");
																System.out
																		.println(delsum);
															}
															try {
																json.put("delcount",delsum);
															} catch (JSONException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
														}
														
													});
												}
											}
											
										});*/
										//计算报废量
										String sql11 = "select delcount from v_del_com where mnum ='"+mnum+"' and kuname = '调漆间'";
										DbUtil.execute(sql11,new IResultSetProcessor(){
											public void process(ResultSet rs)throws SQLException {
												int delsum = 0;
												while(rs.next()){
													delsum += rs.getInt("delcount");
													
												}
												System.out
												.println(delsum+"=delsum");
												try {
													json.put("delcount",delsum);
												} catch (JSONException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
											
										});
										
										
										//计算出库量
										String sqlchuku = "select outcount from v_matout_mnum where kuname = '调漆间' and mnum ='"+ mnum +"'";
										DbUtil.execute(sqlchuku, new IResultSetProcessor(){
											public void process(ResultSet rs)throws SQLException {
												int outsum = 0;
												while(rs.next()){
													outsum += rs.getInt("outcount");
												}
												try {
													json.put("outcount",outsum);
													System.out
															.println(outsum+"=outsum");
												} catch (JSONException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
										});
										try {
											int nowcount = 0;
											nowcount = json.getInt("incount") - json.getInt("outcount") - json.getInt("changecount")-json.getInt("delcount");
											
												json.put("nowcount",nowcount);
												System.out.println(json.getInt("incount"));
												System.out.println(json.getInt("outcount"));
												System.out.println(json.getInt("changecount"));
												System.out.println(json.getInt("nowcount"));
												System.out.println(json.getInt("delcount"));
											
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									
								}
								
							});
							try {
								if(json.getInt("nowcount")<=0){
									continue;
								}else{
									jsonarray.put(json);
								}
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
		}else if("10".equals(userlevel)){
			String sql = "select count(distinct mnum) as ct from v_matchange_com where newkuname in('江北库','句容库')";
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
			String sqlchange = "select distinct mnum,newkuname from v_matchange_com where newkuname in('江北库','句容库')";
			try {
				DbUtil.execute(sqlchange, new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
							final JSONObject json = new JSONObject();
							try {
								json.put("mnum",rs.getString("mnum"));
								
								/*json.put("typeid",rs.getString("typeid"));*/
								/*json.put("kuname",rs.getString("newkuname"));*/
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							/*String mnum = rs.getString("mnum");
							String sqlkuname = "select newkuname from v_matchange_com where newkuname in ('江北库','句容库') and mnum = '" +mnum +"'";
							DbUtil.execute(sqlkuname,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									while(rs.next()){
									try {
										json.put("kuname",rs.getString("newkuname"));
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								}
							});*/

							//计算入库量
							final String mnum = rs.getString("mnum");
							try {
								json.put("kuname",rs.getString("newkuname"));
							} catch (JSONException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							if("江北库".equals(rs.getString("newkuname"))){
								String sqltiaoqi = "select changecount from v_matchange_com where newkuname = '江北库' and mnum ='"+ mnum +"'";
								String baofei = "select delcount from v_del_com where kuname = '江北库' and mnum ='"+ mnum +"'";
								DbUtil.execute(baofei,new IResultSetProcessor(){
									public void process(ResultSet rs)
											throws SQLException {
										int baofeicount = 0;
										while(rs.next()){
											baofeicount += rs.getInt("delcount");
										}
										try {
											json.put("baofeicount",baofeicount);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									
								});
								DbUtil.execute(sqltiaoqi,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										int insum = 0;
										while(rs.next()){
											insum += rs.getInt("changecount");
										}
										try {
											json.put("incount",insum);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}	
								});
							} else if("句容库".equals(rs.getString("newkuname"))){
								String sqltiaoqi = "select changecount from v_matchange_com where newkuname = '句容库' and  mnum ='"+ mnum +"'";
								String baofei = "select delcount from v_del_com where kuname = '句容库' and mnum ='"+ mnum +"'";
								DbUtil.execute(baofei,new IResultSetProcessor(){
									public void process(ResultSet rs)
											throws SQLException {
										int baofeicount = 0;
										while(rs.next()){
											baofeicount += rs.getInt("delcount");
										}
										try {
											json.put("baofeicount",baofeicount);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									
								});
								DbUtil.execute(sqltiaoqi,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										int insum = 0;
										while(rs.next()){
											insum += rs.getInt("changecount");
										}
										try {
											json.put("incount",insum);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}	
								});
							}
							if("江北库".equals(rs.getString("newkuname"))){
								String sqlyiku = "select changecount from v_matchange_com where orgkuname = '江北库' and mnum ='"+ mnum +"'";
								DbUtil.execute(sqlyiku,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										int changesum = 0;
										while(rs.next()){
											changesum += rs.getInt("changecount");
										}
										try {
											json.put("changecount",changesum);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									
								});
							} else if("句容库".equals(rs.getString("newkuname"))){
								String sqlyiku = "select changecount from v_matchange_com where orgkuname = '句容库' and mnum ='"+ mnum +"'";
								DbUtil.execute(sqlyiku,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										int changesum = 0;
										while(rs.next()){
											changesum += rs.getInt("changecount");
										}
										try {
											json.put("changecount",changesum);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									
								});
							}
							if("江北库".equals(rs.getString("newkuname"))){
								String sqlchuku = "select outcount from v_matout_mnum where kuname = '江北库' and mnum ='"+ mnum +"'";
								DbUtil.execute(sqlchuku, new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										int outsum = 0;
										while(rs.next()){
											outsum += rs.getInt("outcount");
										}
										try {
											json.put("outcount",outsum);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});
							} else if("句容库".equals(rs.getString("newkuname"))){
								String sqlchuku = "select outcount from v_matout_mnum where kuname = '句容库' and mnum ='"+ mnum +"'";
								DbUtil.execute(sqlchuku, new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										int outsum = 0;
										while(rs.next()){
											outsum += rs.getInt("outcount");
										}
										try {
											json.put("outcount",outsum);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});
							}
							if("江北库".equals(rs.getString("newkuname"))){
							String sqldel = "select newid from v_matchange_com where mnum = '" +mnum+"'";
							DbUtil.execute(sqldel,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									while(rs.next()){
										String delid = rs.getString("newid");
										String sql11 = "select delcount from v_del_com where mnum = '" + mnum + "' and kuname = '江北库'";
										DbUtil.execute(sql11,new IResultSetProcessor(){
											public void process(ResultSet rs)throws SQLException {
												int delsum = 0;
												while(rs.next()){
													delsum += rs.getInt("delcount");
													System.out
															.println(delsum);
												}
												try {
													json.put("delcount",delsum);
												} catch (JSONException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
											
										});
									}
								}
							});
							} else if("句容库".equals(rs.getString("newkuname"))){
								String sqldel = "select newid from v_matchange_com where mnum = '" +mnum+"'";
								DbUtil.execute(sqldel,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										while(rs.next()){
											String delid = rs.getString("newid");
											String sql11 = "select delcount from v_del_com where mnum = '" + mnum + "' and kuname = '句容库'";
											DbUtil.execute(sql11,new IResultSetProcessor(){
												public void process(ResultSet rs)throws SQLException {
													int delsum = 0;
													while(rs.next()){
														delsum += rs.getInt("delcount");
														System.out
																.println(delsum);
													}
													try {
														json.put("delcount",delsum);
													} catch (JSONException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												}
												
											});
										}
									}
								});
							}
							//计算在库量
							try {
								int nowcount = 0;
								nowcount = json.getInt("incount") - json.getInt("outcount") - json.getInt("changecount")-json.getInt("baofeicount");
								
									json.put("nowcount",nowcount);
									System.out.println(json.getInt("incount"));
									System.out.println(json.getInt("outcount"));
									System.out.println(json.getInt("changecount"));
								
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							//取出材料的具体属性
							String sql = "select * from t_material where mnum ='"+mnum+"'";
							DbUtil.execute(sql,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									while(rs.next()){
										try {
											json.put("name",rs.getString("name"));
											json.put("classname",rs.getString("classname"));
											json.put("modelnum",rs.getString("modelnum"));
											/*json.put("spec",rs.getString("spec"));*/
											json.put("minstore",rs.getString("minstore"));
											/*json.put("mincycle",rs.getString("mincycle"));*/
											json.put("maxstore",rs.getString("maxstore"));
											
											/*json.put("maxcycle",rs.getString("maxcycle"));
											json.put("measure",rs.getString("measure"));
											json.put("buycycle",rs.getString("buycycle"));
											json.put("monthuse",rs.getString("monthuse"));
											json.put("guaranteedate",rs.getString("guaranteedate"));
											json.put("singlepur",rs.getString("singlepur"));*/
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
									}
									
								}
								
							});
							try {
								if(json.getInt("nowcount")<=0){
									continue;
								}else{
									jsonarray.put(json);
								}
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
			
		}
		return obj;
	}

}
