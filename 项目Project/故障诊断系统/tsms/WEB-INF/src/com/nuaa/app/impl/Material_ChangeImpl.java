package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.Material_Change;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;

public class Material_ChangeImpl implements  Material_Change{
	public JSONObject queryAll(HashMap hashmap) {
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String userlevel = (String) hashmap.get("userlevel");
		String filter = (String)hashmap.get("filter");
		String order = (String)hashmap.get("order");
		String sql = "";
		final String[] count = new String[1];
		final String[] count1 = new String[1];
		final String[] flagjb = new String[1];
		final String[] flagjr = new String[1];
		if("8".equals(userlevel)){	
			String sqlnum =  "select count(*) as ct from vmaterial_com where "+filter;
			try {
				DbUtil.execute(sqlnum,new IResultSetProcessor(){
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
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sql = "select * from vmaterial_com where " +filter+ "order by "+ order;
			try {
				DbUtil.execute(sql,new IResultSetProcessor(){
					
					public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
							final JSONObject json = new JSONObject();
							//判断订单号是不是报废过
							String ordernum = rs.getString("ordernum");
							int incount = rs.getInt("incount");
							String sql = "select count(*)as ct from t_material_delete_record where ordernum ='"+ ordernum+"'and kuname = '海波库'";
							DbUtil.execute(sql,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									while(rs.next()){
										if("0".equals(rs.getString("ct"))){
											count[0] = "1";
										}else{
											count[0] = "0";
										}
									}
								}
							});
							System.out.println("****************************");
							//如果该订单没有被报废记录
							if("1".equals(count[0])){
								String typeid = rs.getString("typeid");
								String sql1 = "select * from t_material where id ='"+typeid +"'";
								DbUtil.execute(sql1, new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										while(rs.next()){
											//从材料库中选出基本信息
											try {
												json.put("mnum",rs.getString("mnum"));
												json.put("classname",rs.getString("classname"));
												json.put("name",rs.getString("name"));
												json.put("modelnum",rs.getString("modelnum"));
												json.put("spec",rs.getString("spec"));
												json.put("prewarndate",rs.getString("prewarndate"));
												json.put("measure",rs.getString("measure"));
												json.put("guaranteedate",rs.getString("guaranteedate"));
												json.put("mincycle",rs.getString("mincycle"));
												json.put("maxcycle",rs.getString("maxcycle"));
												json.put("minstore",rs.getString("minstore"));
												json.put("maxstore",rs.getString("maxstore"));
												json.put("buycycle",rs.getString("buycycle"));
												json.put("monthuse",rs.getString("monthuse"));
												json.put("singlepur",rs.getString("singlepur"));
												json.put("remark",rs.getString("remark"));
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											String companyid = rs.getString("companyid");
											String sql2 = "select company from t_company where id = '" + companyid +"'";
											DbUtil.execute(sql2, new IResultSetProcessor(){
												public void process(ResultSet rs)
														throws SQLException {
													while(rs.next()){
														//取出厂商名称
														try {
															json.put("company",rs.getString("company"));
														} catch (JSONException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
													}
												}
												
											});
											
										}
										
									}
									
								});
								
								try {
									json.put("ordernum",rs.getString("ordernum"));
									json.put("kuname",rs.getString("kuname"));
									json.put("shelfnum",rs.getString("shelfnum"));
									json.put("valiadate",rs.getDate("valiadate"));
									json.put("indate",rs.getDate("indate"));
									json.put("state",rs.getString("state"));
									json.put("id",rs.getString("id"));
									json.put("incount",rs.getString("incount"));
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							    //获取ordernum下的移库量
								String sql4 = "select changecount from t_material_change_record where ordernum = '" + ordernum +"' and orgkuname = '海波库'and newkuname !='海波库'";
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
								
								String sql5 = "select outcount from t_material_out_record where ordernum = '" + ordernum +"' and kuname = '海波库'";
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
									nowcount = incount - json.getInt("outcount") - json.getInt("changecount");
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
							}//判断报废否结束
							else{
								continue;
							}
							
						}
					}
				});
				try {
					obj.put("filedata",jsonarray);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("9".equals(userlevel)){
			//计算总的记录数目赋给totalProperty
			String sqlchange = "select count(*) as ct from v_matchange_com where " + filter + " and newkuname = '调漆间'"; 
			try {
				DbUtil.execute(sqlchange,new IResultSetProcessor(){
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
			//取出ordernum判断此ordernum在报废表中是否有调漆间报废记录
			String sqloreder = "select * from v_matchange_com where " + filter + " and newkuname = '调漆间' order by '" + order + "'";
			try {
				DbUtil.execute(sqloreder,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
							final JSONObject json = new JSONObject();
							String ordernum = rs.getString("ordernum");
							String sql = "select count(*) as ct from t_material_delete_record where ordernum = '" +ordernum +"' and kuname = '调漆间'";
							DbUtil.execute(sql,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									while(rs.next()){
										if("0".equals(rs.getString("ct"))){
											count1[0]="1";
										}else{
											count1[0]="0";
										}
									}
									
								}
								
							});
							//如果不存在报废记录
							if("1".equals(count1[0])){
								try {
									
									json.put("id",rs.getString("id"));
									json.put("ordernum",rs.getString("ordernum"));
									json.put("mnum",rs.getString("mnum"));
									json.put("company",rs.getString("company"));
									json.put("spec",rs.getString("spec"));
									json.put("modelnum",rs.getString("modelnum"));
									json.put("monthuse",rs.getString("monthuse"));
									json.put("classname",rs.getString("classname"));
									json.put("name",rs.getString("name"));
									json.put("prewarndate",rs.getString("prewarndate"));
									json.put("measure",rs.getString("measure"));
									json.put("guaranteedate",rs.getString("guaranteedate"));
									json.put("mincycle",rs.getString("mincycle"));
									json.put("maxcycle",rs.getString("maxcycle"));
									json.put("minstore",rs.getString("minstore"));
									json.put("maxstore",rs.getString("maxstore"));
									json.put("singlepur",rs.getString("singlepur"));
									json.put("comopany",rs.getString("company"));
									json.put("remark",rs.getString("remark"));
									json.put("valiadate",rs.getDate("valiadate"));
									json.put("state",rs.getString("state"));
									json.put("indate",rs.getDate("changedate"));
									json.put("incount",rs.getString("changecount"));
									json.put("kuname",rs.getString("newkuname"));
									json.put("shelfnum",rs.getString("newshelfnum"));
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								int nowcount = 0;
								
								//计算移库记录表中此订单移入数量
								String sqls = "select changecount from t_material_change_record where ordernum = '"+ordernum+"' and newkuname='调漆间' and orgkuname!='调漆间'"; 
								DbUtil.execute(sqls,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										int changecount = 0;
										while(rs.next()){
											changecount += rs.getInt("changecount");
										}
										try {
											json.put("changecountall",changecount);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});
								
								//判断在此ordernum下移库记录中有无调漆间移库记录
								String sqlsk= "select changecount from t_material_change_record where ordernum = '"+ordernum+"' and orgkuname='调漆间' and newkuname!='调漆间'"; 
								DbUtil.execute(sqlsk,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										int changesum = 0;
										while(rs.next()){
											changesum += rs.getInt("changecount");
										}
										try {
											json.put("changesum",changesum);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});
								
								//判断在此ordernum下出库记录中有无调漆间出库记录
								String sqlss = "select outcount from t_material_out_record where ordernum = '"+ordernum+"' and kuname='调漆间'"; 
								DbUtil.execute(sqlss,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										int outsum = 0;
										while(rs.next()){
											outsum += rs.getInt("outcount");
										}
										try {
											json.put("outsum",outsum);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});
								//计算在库量
								try {
									nowcount = json.getInt("changecountall")-json.getInt("outsum")-json.getInt("changesum");
									if(nowcount==0){
										continue;
									}
									else{
									json.put("nowcount",nowcount);
									}
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							//如果存在报废记录进行下一个订单号的检查
							else{
								continue;
							}
							jsonarray.put(json);
						}
						
					}
					
				});
				try {
					obj.put("filedata",jsonarray);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("10".equals(userlevel)){
			String sql10 = "select count(*) as ct from v_matchange_com where  newkuname in ('江北库','句容库') and " + filter ; 
			try {
				DbUtil.execute(sql10,new IResultSetProcessor(){
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
			String sqljb = "select * from v_matchange_com where " + filter + " and newkuname in('江北库','句容库') order by '" + order +"'";
			try {
				DbUtil.execute(sqljb,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
						    final JSONObject json = new JSONObject();
							String ordernum = rs.getString("ordernum");
							if(rs.getString("newkuname").equals("江北库")){
								String sql = "select count(*) as ct from t_material_delete_record where ordernum = '"+ordernum+"' and kuname = '江北库'";
								DbUtil.execute(sql,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										while(rs.next()){
											if("0".equals(rs.getString("ct"))){
												flagjr[0]="1";
											}else{
												flagjr[0]="0";
											}
										}
									}
								});
							}else if(rs.getString("newkuname").equals("句容库")){
								String sql = "select count(*) as ct from t_material_delete_record where ordernum = '"+ordernum+"' and kuname = '句容库'";
								DbUtil.execute(sql,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										while(rs.next()){
											if("0".equals(rs.getString("ct"))){
												flagjr[0]="1";
											}else{
												flagjr[0]="0";
											}
										}
									}
								});
							}
							if(("1".equals(flagjr[0]))||("1".equals(flagjb[0]))){
								try {
									json.put("id",rs.getString("id"));
									json.put("ordernum",rs.getString("ordernum"));
									json.put("mnum",rs.getString("mnum"));
									json.put("company",rs.getString("company"));
									json.put("spec",rs.getString("spec"));
									json.put("classname",rs.getString("classname"));
									json.put("name",rs.getString("name"));
									json.put("prewarndate",rs.getString("prewarndate"));
									json.put("measure",rs.getString("measure"));
									json.put("monthuse",rs.getString("monthuse"));
									json.put("modelnum",rs.getString("modelnum"));
									json.put("guaranteedate",rs.getString("guaranteedate"));
									json.put("mincycle",rs.getString("mincycle"));
									json.put("maxcycle",rs.getString("maxcycle"));
									json.put("minstore",rs.getString("minstore"));
									json.put("maxstore",rs.getString("maxstore"));
									json.put("singlepur",rs.getString("singlepur"));
									json.put("comopany",rs.getString("company"));
									json.put("remark",rs.getString("remark"));
									json.put("valiadate",rs.getDate("valiadate"));
									json.put("state",rs.getString("state"));
									json.put("indate",rs.getDate("changedate"));
									json.put("incount",rs.getString("changecount"));
									json.put("kuname",rs.getString("newkuname"));
									json.put("shelfnum",rs.getString("newshelfnum"));
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if("江北库".equals(rs.getString("newkuname"))){
								//计算移库记录表中此订单移入数量
								String sqls = "select changecount from t_material_change_record where ordernum = '"+ordernum+"' and newkuname='江北库'"; 
								DbUtil.execute(sqls,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										int changecount = 0;
										while(rs.next()){
											changecount += rs.getInt("changecount");
										}
										try {
											json.put("changecountall",changecount);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});
								String sqlsk= "select changecount from t_material_change_record where ordernum = '"+ordernum+"' and orgkuname='江北库'"; 
								DbUtil.execute(sqlsk,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										int changesum = 0;
										while(rs.next()){
											changesum += rs.getInt("changecount");
										}
										try {
											json.put("changesum",changesum);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});
								int nowcountjb = 0;
								//判断在此ordernum下出库记录中有无调漆间移库记录
								String sqlss = "select outcount from t_material_out_record where ordernum = '"+ordernum+"' and kuname='江北库'"; 
								DbUtil.execute(sqlss,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										int outsum = 0;
										while(rs.next()){
											outsum += rs.getInt("outcount");
										}
										try {
											json.put("outsum",outsum);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});
								//计算在库量
								try {
									nowcountjb = json.getInt("changecountall")-json.getInt("outsum")-json.getInt("changesum");
									if(nowcountjb==0){
										continue;
									}else{
										json.put("nowcount",nowcountjb);
									}
									
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								}else if("句容库".equals(rs.getString("newkuname"))){
									//计算移库记录表中此订单移入数量
									String sqls = "select changecount from t_material_change_record where ordernum = '"+ordernum+"' and newkuname='句容库'"; 
									DbUtil.execute(sqls,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											int changecount = 0;
											while(rs.next()){
												changecount += rs.getInt("changecount");
											}
											try {
												json.put("changecountall",changecount);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});
									String sqlsk= "select changecount from t_material_change_record where ordernum = '"+ordernum+"' and orgkuname='句容库'"; 
									DbUtil.execute(sqlsk,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											int changesum = 0;
											while(rs.next()){
												changesum += rs.getInt("changecount");
											}
											try {
												json.put("changesum",changesum);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});
									int nowcountjb = 0;
									//判断在此ordernum下出库记录中有无调漆间移库记录
									String sqlss = "select outcount from t_material_out_record where ordernum = '"+ordernum+"' and kuname='句容库'"; 
									DbUtil.execute(sqlss,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											int outsum = 0;
											while(rs.next()){
												outsum += rs.getInt("outcount");
											}
											try {
												json.put("outsum",outsum);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});
									//计算在库量
									try {
										nowcountjb = json.getInt("changecountall")-json.getInt("outsum")-json.getInt("changesum");
										if(nowcountjb==0){
											continue;
										}else{
											json.put("nowcount",nowcountjb);
										}
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}
								
								
							}else{
								continue;
							}
							jsonarray.put(json);
						}
						
					}
				});
				try {
					obj.put("filedata",jsonarray);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return obj;
	}
	public String changeMaterial(HashMap hashmap) {
		final String id = UUID.randomUUID().toString();
		final String ordernum = (String) hashmap.get("ordernum");
		final String orgkuname = (String) hashmap.get("kuname");
		final String newkuname = (String) hashmap.get("newkuname");
		final String orgshelfnum = (String) hashmap.get("shelfnum");
		final String newshelfnum = (String) hashmap.get("newshelfnum");
		final String changecount = (String) hashmap.get("changecount");
		final String changedate = (String) hashmap.get("changedate");
		final String realname = (String) hashmap.get("realname");
		final String oldid = (String) hashmap.get("id");
		String[] sqls = new String[1];
		System.out.println(changedate+realname);
		if("海波库".equals(newkuname)){
			String[] sqls1 = new String[1];	
			sqls1[0] = "update T_MATERIAL_IN_RECORD set incount = incount +"+changecount+"where ordernum = '"+ordernum+"'";
			System.out.println(sqls[0]);
			try {
				DbUtil.executeBatchs(sqls1,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)	throws SQLException {			
						pstmts[0].addBatch();
					}
				});
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sqls[0]= "insert into T_MATERIAL_CHANGE_RECORD (id,ordernum,orgkuname,newkuname,orgshelfnum,newshelfnum,changecount,changedate,changeperson,oldid)values(?,?,?,?,?,?,?,to_date(?,'yyyy/mm/dd'),?,?)";
		try {
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts) throws SQLException {
					pstmts[0].setString(1,id);
					pstmts[0].setString(2,ordernum);
					pstmts[0].setString(3,orgkuname);
					pstmts[0].setString(4,newkuname);
					pstmts[0].setString(5,orgshelfnum);
					pstmts[0].setString(6,newshelfnum);
					pstmts[0].setString(7,changecount);
					pstmts[0].setString(8,changedate);
					pstmts[0].setString(9,realname);
					pstmts[0].setString(10,oldid);//oldid入库时生成的id
					pstmts[0].addBatch();
					//System.out.println("000000000000000000");
				}
			});
			return "true";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		
	}
	}
