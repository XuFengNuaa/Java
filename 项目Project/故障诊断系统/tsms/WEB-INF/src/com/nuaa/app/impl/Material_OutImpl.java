package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.Material_Out;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;

public class Material_OutImpl implements Material_Out {
	//查询所以与材料有关的信息
	//分角色查询8.9.10三种角色。不同角色查询到的库名和在库量是不同的，首先判断是否该订单或者有报废记录。如果有直接continue跳到下一个订单
	//如果无报废记录。
	//对于角色8：先计算该id的入库数量，然后计算移库记录中，该id的移库数量，最后计算该id的出库数量。现库存 = 入库量 - 出库量 - 移库量；
	//对于角色9，10：先计算该id的目标库为调漆间、江北、句容库的移库数量incount，然后计算移库记录中原库名为调漆间、江北、句容库的移库数目changecount。最后计算出库记录中该id的出库数量outcount
	//在库量  = incount - changecount - outciunt;
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
			String sql8 =  "select count(*) as ct from vmaterial_com where "+filter;
			try {
				DbUtil.execute(sql8,new IResultSetProcessor(){
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
							String id = rs.getString("id");
							int incount = rs.getInt("incount");
							String sql = "select count(*)as ct from t_material_delete_record where id ='"+ id+"'and kuname='海波库'";
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
							//如果该订单没有被海波库的报废记录
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
												/*json.put("prewarndate",rs.getString("prewarndate"));*/
												json.put("measure",rs.getString("measure"));
												json.put("guaranteedate",rs.getString("guaranteedate"));
												/*json.put("mincycle",rs.getString("mincycle"));
												json.put("maxcycle",rs.getString("maxcycle"));*/
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
									json.put("incount",rs.getString("incount"));
									json.put("state",rs.getString("state"));
									json.put("id",rs.getString("id"));
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
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
								
								/*  //获取ordernum下的移入库量
								String sql6 = "select changecount from t_material_change_record where oldid = '" + id +"' and newkuname = '海波库'";
								DbUtil.execute(sql4,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										JSONObject sumobj = new JSONObject();
										int sum = 0;
										while(rs.next()){
										sum += rs.getInt("changecount");
										}
										try {
											json.put("changecount1",sum);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});*/
								
								
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
									nowcount = incount - json.getInt("outcount") - json.getInt("changecount") /*+ json.getInt("changecount1")*/;
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
			String sql9 = "select count(*) as ct from v_matchange_com where " +filter +"and newkuname ='调漆间'";
			try {
				DbUtil.execute(sql9,new IResultSetProcessor(){
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
			//查询全部属性包括出库、移库数量
			String sqlchange = "select * from v_matchange_com where "+ filter +"and newkuname = '调漆间' order by " + order;
			try {
				DbUtil.execute(sqlchange,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
						final JSONObject json = new JSONObject();
						final String newid = rs.getString("newid");
						String sql = "select count(*) as ct from t_material_delete_record where id = '" +newid +"' and kuname = '调漆间'";
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
						if("1".equals(count1[0])){
							try {
								json.put("id",rs.getString("newid"));
								json.put("oldid",rs.getString("oldid"));
								json.put("ordernum",rs.getString("ordernum"));
								json.put("mnum",rs.getString("mnum"));
								json.put("company",rs.getString("company"));
								json.put("spec",rs.getString("spec"));
								json.put("classname",rs.getString("classname"));
								json.put("name",rs.getString("name"));
								/*json.put("prewarndate",rs.getString("prewarndate"));*/
								json.put("measure",rs.getString("measure"));
								json.put("guaranteedate",rs.getString("guaranteedate"));
								/*json.put("mincycle",rs.getString("mincycle"));*/
								json.put("monthuse",rs.getString("monthuse"));
								/*json.put("maxcycle",rs.getString("maxcycle"));*/
								json.put("minstore",rs.getString("minstore"));
								json.put("maxstore",rs.getString("maxstore"));
								json.put("singlepur",rs.getString("singlepur"));
								json.put("comopany",rs.getString("company"));
								json.put("remark",rs.getString("remark"));
								json.put("valiadate",rs.getDate("valiadate"));
								json.put("modelnum",rs.getString("modelnum"));
								json.put("state",rs.getString("state"));
								json.put("indate",rs.getDate("changedate"));
								json.put("incount",rs.getString("changecount"));
								json.put("kuname",rs.getString("newkuname"));
								json.put("shelfnum",rs.getString("newshelfnum"));
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							final String oldid = rs.getString("newid");
							/*final String newid = rs.getString("newid");*/
							int changecountall = rs.getInt("changecount");//取出总的移库数量changecountall
							try {
								json.put("incount",changecountall);
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							int nowcount = 0;
							//查询移库表中此订单有无移库记录
							String sqlyikucount = "select count(*) as ct from t_material_change_record where oldid = '" + oldid +"' and orgkuname = '调漆间'";
							DbUtil.execute(sqlyikucount,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									while(rs.next()){
										if("0".equals(rs.getString("ct"))){
											try {
												System.out.println("zhixingdao zhilil");
												json.put("changecount",0);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}else{
											String sql = "select changecount from t_material_change_record where oldid = '"+oldid +"' and orgkuname = '调漆间'";
											//如果有出库记录求出总的移库数量changecount
											DbUtil.execute(sql,new IResultSetProcessor(){
												int changesum = 0;
												public void process(ResultSet rs)throws SQLException {
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
										
										/*String sql = "select changecount from t_material_change_record where oldid = '"+newid +"' and orgkuname = '调漆间'";
										//如果有出库记录求出总的移库数量changecount
										DbUtil.execute(sql,new IResultSetProcessor(){
											int changesum = 0;
											public void process(ResultSet rs)throws SQLException {
												while(rs.next()){
													
												changesum += rs.getInt("changecount1");
											}
											try {
												json.put("changecount1",changesum);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											}
										});*/
									}
									
								}
							});
							
							//计算调漆间中的该id有无出库的记录
							String sqlchukucount = "select count(*) as ct from t_material_out_record where id = '" + newid +"' and kuname = '调漆间'";
							DbUtil.execute(sqlchukucount,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									while(rs.next()){
										if("0".equals(rs.getString("ct"))){
											try {
												json.put("outcount",0);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}else{
											//如果有出库记录，求出总的出库量outcount
											String sql = "select outcount from t_material_out_record where id = '"+newid +"' and kuname = '调漆间'";
											DbUtil.execute(sql,new IResultSetProcessor(){
												int outsum = 0;
												public void process(ResultSet rs)throws SQLException {
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
									}
									
								}
								
							});
							
							
							try {
								nowcount = changecountall -  json.getInt("changecount") - json.getInt("outcount") /*+ json.getInt("changecount1")*/;
								System.out.println(changecountall);
								System.out.println(json.getInt("changecount"));
								System.out.println(json.getInt("outcount"));
								System.out.println(nowcount);
								if(nowcount<=0){
									continue;
								}else{
									json.put("nowcount",nowcount);
								}
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
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
			return obj;
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
							String newid = rs.getString("newid");
							String oldid = rs.getString("oldid");
							if(rs.getString("newkuname").equals("江北库")){
								String sql = "select count(*) as ct from t_material_delete_record where id = '"+newid+"' and kuname = '江北库'";
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
								String sql = "select count(*) as ct from t_material_delete_record where id = '"+newid+"' and kuname = '句容库'";
								
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
									json.put("id",rs.getString("newid"));
									json.put("ordernum",rs.getString("ordernum"));
									json.put("mnum",rs.getString("mnum"));
									json.put("company",rs.getString("company"));
									json.put("spec",rs.getString("spec"));
									json.put("classname",rs.getString("classname"));
									json.put("name",rs.getString("name"));
									/*json.put("prewarndate",rs.getString("prewarndate"));*/
									json.put("measure",rs.getString("measure"));
									json.put("monthuse",rs.getString("monthuse"));
									json.put("modelnum",rs.getString("modelnum"));
									json.put("guaranteedate",rs.getString("guaranteedate"));
									/*json.put("mincycle",rs.getString("mincycle"));
									json.put("maxcycle",rs.getString("maxcycle"));*/
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
								String sqls = "select changecount from v_matchange_com where newid = '"+newid+"' and newkuname='江北库'"; 
								DbUtil.execute(sqls,new IResultSetProcessor(){
									public void process(ResultSet rs)throws SQLException {
										int changecount = 0;
										while(rs.next()){
											changecount += rs.getInt("changecount");
										}
										try {
											json.put("incount",changecount);
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});
								String sqlsk= "select changecount from v_matchange_com where oldid = '"+newid+"' and orgkuname='江北库'"; 
								DbUtil.execute(sqlsk,new IResultSetProcessor(){
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
								int nowcountjb = 0;
								//判断在此id下出库记录中有无江北库移库记录
								String sqlss = "select outcount from t_material_out_record where id = '"+newid+"' and kuname='江北库'"; 
								DbUtil.execute(sqlss,new IResultSetProcessor(){
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
								//计算在库量
								try {
									nowcountjb = json.getInt("incount")-json.getInt("outcount")-json.getInt("changecount");
									if(nowcountjb<=0){
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
									String sqls = "select changecount from v_matchange_com where newid = '"+newid+"' and newkuname='句容库'"; 
									DbUtil.execute(sqls,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											int changecount = 0;
											while(rs.next()){
												changecount += rs.getInt("changecount");
											}
											try {
												json.put("incount",changecount);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									});
									String sqlsk= "select changecount from v_matchange_com where oldid = '"+newid+"' and orgkuname='句容库'"; 
									DbUtil.execute(sqlsk,new IResultSetProcessor(){
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
									int nowcountjb = 0;
									//判断在此ordernum下出库记录中有无调漆间移库记录
									String sqlss = "select outcount from t_material_out_record where id = '"+newid+"' and kuname='句容库'"; 
									DbUtil.execute(sqlss,new IResultSetProcessor(){
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
									//计算在库量
									try {
										nowcountjb = json.getInt("incount")-json.getInt("outcount")-json.getInt("changecount");
										if(nowcountjb<=0){
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

	@Override
	//出库记录函数
	public String outMaterial(HashMap hashmap) {
		//final String id = UUID.randomUUID().toString();
		final String ordernum = (String) hashmap.get("ordernum");
		final String kuname = (String) hashmap.get("kuname");
		final String shelfnum = (String) hashmap.get("shelfnum");
		final String outcount = (String) hashmap.get("outcount");
		final String outdate = (String) hashmap.get("outdate");
		final String realname = (String) hashmap.get("realname");
		final String getperson = (String) hashmap.get("getperson");
		final String oldid = (String) hashmap.get("id");
		String[] sqls = new String[1];
		System.out.println(outdate+realname+getperson);
		sqls[0]= "insert into t_material_out_record (id,ordernum,kuname,shelfnum,outcount,outdate,outperson,getperson)values(?,?,?,?,?,to_date(?,'yyyy/mm/dd'),?,?)";
		try {
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts) throws SQLException {
					pstmts[0].setString(1,oldid);
					pstmts[0].setString(2,ordernum);
					pstmts[0].setString(3,kuname);
					pstmts[0].setString(4,shelfnum);
					pstmts[0].setString(5,outcount);
					pstmts[0].setString(6,outdate);
					pstmts[0].setString(7,realname);
					pstmts[0].setString(8,getperson);
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

	@Override
	public JSONObject getQueryMnum() {
		// TODO Auto-generated method stub
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sql = "select materialgetperson  from t_material_getperson";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						JSONObject json = new JSONObject();
						try {
							json.put("materialgetperson",rs.getString("materialgetperson"));
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
		return obj;
		
	}
	
}
