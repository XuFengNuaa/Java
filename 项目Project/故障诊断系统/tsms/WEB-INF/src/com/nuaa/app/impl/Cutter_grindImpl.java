package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.FileState;
import com.nuaa.app.Cutter_grind;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class Cutter_grindImpl implements Cutter_grind{
	  public JSONObject query_cut_togrind(HashMap queryMap) {
			// TODO 自动生成方法存根
			HashMap query_cutterHashMap=queryMap;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select  count(*) as ct  from  V_CUTTER2COM where "+(String)queryMap.get("filter");
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
			int start=Integer.parseInt((String)query_cutterHashMap.get("start"));
			int limit=Integer.parseInt((String)query_cutterHashMap.get("limit"));
			
			String filter=(String)query_cutterHashMap.get("filter");
			String order=(String)query_cutterHashMap.get("order");        
			sql="select * from (select * from  (select * from V_CUTTER2COM where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from V_CUTTER2COM where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
			
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
								decimalformat.setMaximumFractionDigits(2);	
								obj.put("id", rs.getString("id"));
								obj.put("mnum", rs.getString("mnum"));								
								obj.put("pclass", rs.getString("pclass"));
								obj.put("bclass", rs.getString("bclass"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));								
								obj.put("hq", rs.getString("hq"));
								obj.put("rank_angle", rs.getString("rank_angle"));
								obj.put("c_mate", rs.getString("c_mate"));
								obj.put("coat_mate", rs.getString("coat_mate"));
								obj.put("mini_qs", rs.getString("mini_qs"));
								obj.put("suit_mate1", rs.getString("suit_mate1"));
								obj.put("suit_mate2", rs.getString("suit_mate2"));
								obj.put("suit_mate3", rs.getString("suit_mate3"));
								obj.put("use_freq", rs.getString("use_freq"));
								obj.put("location", rs.getString("location"));
								obj.put("remark",rs.getString("remark"));
								obj.put("e_diam", rs.getString("e_diam"));
								obj.put("h_diam", rs.getString("h_diam"));
								obj.put("e_leng", rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
								obj.put("t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));								
								obj.put("e_count",rs.getString("e_count"));								
								obj.put("type", rs.getString("type"));	
								obj.put("series_num", rs.getString("series_num"));	
								obj.put("order_num", rs.getString("order_num"));
								obj.put("nc_count_in", rs.getString("nc_count_in"));
								obj.put("uc_count_in", rs.getString("uc_count_in"));
								obj.put("s_ang", rs.getString("s_ang"));
								
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
		};
		 
		//刀具明细
		public JSONObject view_grind_cutter(HashMap view_grind_cutterHMap) {
			// TODO 自动生成方法存根
			HashMap view_grind_cutterHashMap=view_grind_cutterHMap;
			final JSONObject jsonObj=new JSONObject();
			final String id=(String)view_grind_cutterHashMap.get("id");
			String sql="";
			sql="select * from V_CUTTER2COM where id='"+id+"'"; 

			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
								decimalformat.setMaximumFractionDigits(2);
								jsonObj.put("id", rs.getString("id"));
								jsonObj.put("mnum", rs.getString("mnum"));
								jsonObj.put("pclass", rs.getString("pclass"));
								jsonObj.put("bclass", rs.getString("bclass"));								
								jsonObj.put("name",rs.getString("name"));
								jsonObj.put("nc_count_in", rs.getString("nc_count_in"));
								jsonObj.put("uc_count_in", rs.getString("uc_count_in"));								
								jsonObj.put("mini_qs", rs.getString("mini_qs"));							
								jsonObj.put("suit_mate1", rs.getString("suit_mate1"));
								jsonObj.put("suit_mate2", rs.getString("suit_mate2"));
								jsonObj.put("suit_mate3", rs.getString("suit_mate3"));
								jsonObj.put("use_freq", rs.getString("use_freq"));
								jsonObj.put("rank_angle", rs.getString("rank_angle"));
								jsonObj.put("c_mate", rs.getString("c_mate"));
								jsonObj.put("coat_mate",rs.getString("coat_mate"));
								jsonObj.put("e_diam", rs.getString("e_diam"));
								jsonObj.put("h_diam", rs.getString("h_diam"));
								jsonObj.put("e_leng", rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
								jsonObj.put("t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));						
								jsonObj.put("e_count",rs.getString("e_count"));
								jsonObj.put("remark", rs.getString("remark"));
								jsonObj.put("type", rs.getString("type"));
								jsonObj.put("series_num", rs.getString("series_num"));
								jsonObj.put("order_num", rs.getString("order_num"));						
								jsonObj.put("hq", rs.getString("hq"));
								jsonObj.put("company",rs.getString("company"));
								jsonObj.put("location", rs.getString("location"));	
								jsonObj.put("s_ang", rs.getString("s_ang"));
								
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
		
	  //将某型号刀具添加到送磨单中 
		public boolean to_grind_cutter(HashMap to_grindHMap) {
			// TODO 自动生成方法存根
			final HashMap to_grind_cutterHMap=to_grindHMap;
			final String id=(String)to_grindHMap.get("id");
			final String amount=(String)to_grindHMap.get("amount");	
			final String rid=UUID.randomUUID().toString(); 
			try {				
			String[] sqls = new String[1];	
			sqls[0] = "update T_CUTTER set uc_count_in=?  where id='"+id+"'";
			System.out.println(sqls[0]);
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)	throws SQLException {			
						pstmts[0].setString(1,amount);										
						pstmts[0].addBatch();
					}
				});
				
				String []sql1 = new String[1];
				sql1[0]= "insert into T_C_GRD_RECORD(rid,type_id,s_count,status) values(?,?,?,?)";
				DbUtil.executeBatchs(sql1,
						new IArrayPreparedStatementProcessor() {
							public void process(
							PreparedStatement[] pstmt)
							throws SQLException {
								pstmt[0].setString(1,rid);
						if(to_grind_cutterHMap.get("id")!=null){
							pstmt[0].setString(2,(String)to_grind_cutterHMap.get("id"));//mnum
						}else{
							pstmt[0].setString(2,"");//mnum
						}
						if(to_grind_cutterHMap.get("grind_amount")!=null){
							pstmt[0].setString(3,(String)to_grind_cutterHMap.get("grind_amount"));//class
						}else{
							pstmt[0].setString(3,"");//
						}						
						pstmt[0].setString(4,"0");//						
						pstmt[0].addBatch();				
					}							
				});				
				return true;			
			}
			catch (SQLException e) {
				e.printStackTrace();
				return false;
			}				
		}	
		
		//从送磨单中删除某刀具
		public String modi_grind_cutter(HashMap modi_grind_HMap) {
			// TODO 自动生成方法存根
			final JSONObject jsonObj=new JSONObject();			
			final String id=(String)modi_grind_HMap.get("id");
			final String rid=(String)modi_grind_HMap.get("rid");
			final String amount=(String)modi_grind_HMap.get("amount");				 
			String sql="";
			sql="select * from T_CUTTER where id='"+id+"'"; 
			final String []result=new String[1];
			result[0]="true";
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								jsonObj.put("id", rs.getString("id"));
								jsonObj.put("uc_count_in", rs.getString("uc_count_in"));								
								final int now_amount = Integer.parseInt(amount)+Integer.parseInt(jsonObj.getString("uc_count_in"));								
								String[] sqls = new String[1];	
								sqls[0] = "update T_CUTTER set uc_count_in=?  where id='"+id+"'";								
								try {
									DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
										public void process(PreparedStatement[] pstmts)	throws SQLException {			
											pstmts[0].setString(1,String.valueOf(now_amount));										
											pstmts[0].addBatch();
										}										
									});										
								}catch (SQLException e) {
								// TODO 自动生成 catch 块
									e.printStackTrace();
									result[0]="exception";
								}								
							}								
							String[] sql1 = new String[1];	
							sql1[0] = "delete from T_C_GRD_RECORD  where rid='"+rid+"'";	
							try {
								DbUtil.executeBatchs(sql1,new IArrayPreparedStatementProcessor(){
									public void process(PreparedStatement[] pstmts)	throws SQLException {																				
										pstmts[0].addBatch();
									}										
								});										
							}catch (SQLException e) {
							// TODO 自动生成 catch 块
								e.printStackTrace();
								result[0]="exception";
							}			
							
						} catch (JSONException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
							result[0]="exception";
						}
					}				
				});					
			}catch (SQLException e) {
			// TODO 自动生成 catch 块
				e.printStackTrace();
				result[0]="exception";
			}			
			return result[0];			
				
		}	
		
		
		//送磨刀具一览，未送领导审批前的送磨刀具
		 public JSONObject get_grind_cutter(HashMap get_grind_HMap) {
				// TODO 自动生成方法存根
				HashMap get_grind_cutter=get_grind_HMap;
				final JSONObject jsonObj=new JSONObject();
				final JSONArray jsonArray=new JSONArray();
				String sql="";
				sql="select  count(*) as ct  from  V_CUTTER2GRD_RECORD where status=0 and grd_num is null";
				final String [] count=new String[1];
				count[0] = "";
				try {
					DbUtil.execute(sql,new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {
							if (rs.next()) {
								try {
									jsonObj.put("readin_totalProp",rs.getString("ct"));
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
				int start=Integer.parseInt((String)get_grind_cutter.get("start"));
				int limit=Integer.parseInt((String)get_grind_cutter.get("limit"));
				
				String filter=" status=0  and grd_num is null";				
				String order=(String)get_grind_cutter.get("order")+" ASC ";        
				sql="select * from V_CUTTER2GRD_RECORD where "+filter+" order by "+order+""; 
				
				Logger.debug(sql);
				try {			
					DbUtil.execute(sql,new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {					
							try {
								while(rs.next()){
									JSONObject obj=new JSONObject();
									DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
									decimalformat.setMaximumFractionDigits(2);
									obj.put("id", rs.getString("id"));
									obj.put("grind_id", rs.getString("type_id"));
									obj.put("grind_price", rs.getString("price")==null ? "":decimalformat.format(rs.getFloat("price")));	
									obj.put("grind_mnum", rs.getString("mnum"));								
									obj.put("grind_pclass", rs.getString("pclass"));
									obj.put("grind_bclass", rs.getString("bclass"));
									obj.put("grind_name",rs.getString("name"));
									obj.put("grind_company", rs.getString("company"));								
									obj.put("grind_hq", rs.getString("hq"));
									obj.put("grind_rank_angle", rs.getString("rank_angle"));
									obj.put("grind_c_mate", rs.getString("c_mate"));
									obj.put("grind_coat_mate", rs.getString("coat_mate"));									
									obj.put("grind_location", rs.getString("location"));									
									obj.put("grind_e_diam", rs.getString("e_diam"));
									obj.put("grind_h_diam", rs.getString("h_diam"));
									obj.put("grind_e_leng", rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
									obj.put("grind_t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));								
									obj.put("grind_e_count",rs.getString("e_count"));								
									obj.put("grind_type", rs.getString("type"));	
									obj.put("grind_series_num", rs.getString("series_num"));	
									obj.put("grind_order_num", rs.getString("order_num"));
									obj.put("grind_amount", rs.getString("s_count"));
								      jsonArray.put(obj);								      
								}	
									jsonObj.put("readin_data", jsonArray);
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
			};	
		
            //定价
			public String price_grind(HashMap price_HMap) {
				// TODO 自动生成方法存根
				final HashMap re_add_grindHMap=price_HMap;
				final String id=(String)price_HMap.get("id");
				final String price=(String)price_HMap.get("price");
				final String []result = new String[1];	
				result[0] = "true" ;
				
				try {	
					String []sql1 = new String[1];
					sql1[0]="update T_C_GRD_RECORD set price=?  where rid='"+id+"'";
					DbUtil.executeBatchs(sql1,
							new IArrayPreparedStatementProcessor() {
								public void process(
								PreparedStatement[] pstmt)
								throws SQLException {
									pstmt[0].setString(1,price);
							        pstmt[0].addBatch();				
						}							
					});			
				}
				catch (SQLException e){
					e.printStackTrace();
					result[0] = "exception";
				}	
			 return result[0];	
			}	
			
		//修改送磨返库刀数量
		public String re_grind_count(HashMap count_HMap) {
				// TODO 自动生成方法存根
				final HashMap re_add_grindHMap=count_HMap;
				final String id=(String)count_HMap.get("id");
				final String r_count=(String)count_HMap.get("r_count");
				final String []result = new String[1];	
				
				Calendar r_time = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				final String now = format.format(r_time.getTime())  ;
			
				
				result[0] = "true" ;
				
				try {	
					String []sql1 = new String[1];
					sql1[0]="update T_C_GRD_RECORD set r_count=?,R_TIME=?,R_TIME_DATE=?  where rid='"+id+"'";
					DbUtil.executeBatchs(sql1,
							new IArrayPreparedStatementProcessor() {
								public void process(
								PreparedStatement[] pstmt)
								throws SQLException {
									pstmt[0].setString(1,r_count);
									pstmt[0].setString(2,now);
									pstmt[0].setDate(3,java.sql.Date.valueOf(now));
							        pstmt[0].addBatch();				
						}							
					});			
				}
				catch (SQLException e){
					e.printStackTrace();
					result[0] = "exception";
				}	
			    return result[0];	
			}	
		
        //提交送磨返库申请
		public String re_grind_check(HashMap check_HMap) {
				// TODO 自动生成方法存根
				final HashMap re_add_grindHMap=check_HMap;
				final String grd_num=(String)check_HMap.get("grd_num");
				final String []result = new String[1];	
				
				result[0] = "true" ;
				
				try {	
					String []sql1 = new String[1];
					sql1[0]="update T_C_GRD_RECORD set STATUS=?,APP_STATUS_IN=?  where grd_num="+grd_num+"";
					DbUtil.executeBatchs(sql1,
							new IArrayPreparedStatementProcessor() {
								public void process(
								PreparedStatement[] pstmt)
								throws SQLException {
									pstmt[0].setString(1,"2");
									pstmt[0].setString(2,"未处理");									
							        pstmt[0].addBatch();				
						}							
					});			
				}
				catch (SQLException e){
					e.printStackTrace();
					result[0] = "exception";
				}	
			    return result[0];	
			}		
		
			
			//增加已在送磨单某型号刀具的送磨数量
			public boolean re_add_grind(HashMap re_add_grind_HMap) {
				// TODO 自动生成方法存根
				final HashMap re_add_grindHMap=re_add_grind_HMap;
				final String id=(String)re_add_grind_HMap.get("id");
				final String amount=(String)re_add_grind_HMap.get("amount");				
				try {				
				String[] sqls = new String[2];	
				sqls[0] = "update T_CUTTER set uc_count_in=?  where id='"+id+"'";
				sqls[1]="update T_C_GRD_RECORD set s_count=?  where type_id='"+id+"' and status=0 and app_status is null ";
				System.out.println(sqls[0]);
					DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
						public void process(PreparedStatement[] pstmts)	throws SQLException {			
							pstmts[0].setString(1,amount);										
							pstmts[0].addBatch();
							
							pstmts[1].setString(1,(String)re_add_grindHMap.get("grind_amount"));																					
							pstmts[1].addBatch();	
						}
					});							
					return true;			
				}
				catch (SQLException e) {
					e.printStackTrace();
					return false;
				}				
			}		
			
			
			//在送磨一览中增加或减少某刀具送磨数量
			public String change_grind(HashMap change_grind_HMap) {
				// TODO 自动生成方法存根
				final JSONObject jsonObj=new JSONObject();			
				final String id=(String)change_grind_HMap.get("id");
				final String rid=(String)change_grind_HMap.get("rid");
				final String amount=(String)change_grind_HMap.get("amount");	
				final String operation=(String)change_grind_HMap.get("operation");	
				final int []now_amount=new int[1];
				final int []new_amount=new int[1];
				String sql="";
				sql="select * from T_CUTTER where id='"+id+"'"; 
				final String []result=new String[1];
				result[0]="true";
				try {			
					DbUtil.execute(sql,new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {					
							try {
								while(rs.next()){									
									jsonObj.put("id", rs.getString("id"));
									jsonObj.put("uc_count_in", rs.getString("uc_count_in"));	
									if("minus".equals(operation)){
										now_amount[0]= Integer.parseInt(amount)+Integer.parseInt(jsonObj.getString("uc_count_in"));											
									}else{
										now_amount[0]= Integer.parseInt(jsonObj.getString("uc_count_in"))-Integer.parseInt(amount);	
									}									 						
									String[] sqls = new String[1];	
									sqls[0] = "update T_CUTTER set uc_count_in=?  where id='"+id+"'";								
									try {
										DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
											public void process(PreparedStatement[] pstmts)	throws SQLException {			
												pstmts[0].setString(1,String.valueOf(now_amount[0]));										
												pstmts[0].addBatch();
											}										
										});										
									}catch (SQLException e) {
									// TODO 自动生成 catch 块
										e.printStackTrace();
										result[0]="exception";
									}								
								}								
								String sql1 ="" ;	
								sql1 = "select * from T_C_GRD_RECORD  where rid='"+rid+"'";	
								try {
									DbUtil.execute(sql1,new IResultSetProcessor() {
										public void process(ResultSet rs) throws SQLException {					
											try {
												while(rs.next()){
													JSONObject obj=new JSONObject();
													obj.put("rid", rs.getString("rid"));
													obj.put("s_count", rs.getString("s_count"));								
													if("minus".equals(operation)){
														new_amount[0]=Integer.parseInt(obj.getString("s_count"))-Integer.parseInt(amount);																									
													}else{
														new_amount[0]= Integer.parseInt(amount)+Integer.parseInt(obj.getString("s_count"));	
													}													
													String[] sql2 = new String[1];	
													sql2[0] = "update T_C_GRD_RECORD set s_count=?  where rid='"+rid+"'";								
													try {
														DbUtil.executeBatchs(sql2,new IArrayPreparedStatementProcessor(){
															public void process(PreparedStatement[] pstmts)	throws SQLException {			
																pstmts[0].setString(1,String.valueOf(new_amount[0]));										
																pstmts[0].addBatch();
															}										
														});										
													}catch (SQLException e) {
													// TODO 自动生成 catch 块
														e.printStackTrace();
														result[0]="exception";
													}						
												     
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
									result[0]="exception";
								}			
								
							} catch (JSONException e) {
								// TODO 自动生成 catch 块
								e.printStackTrace();
								result[0]="exception";
							}
						}				
					});					
				}catch (SQLException e) {
				// TODO 自动生成 catch 块
					e.printStackTrace();
					result[0]="exception";
				}			
				return result[0];			
					
			}	
			
     	public String delete_grind(HashMap delete_grind_HMap) {
				// TODO 自动生成方法存根
				final JSONObject jsonObj=new JSONObject();			
				final String id=(String)delete_grind_HMap.get("id");
				final String rid=(String)delete_grind_HMap.get("rid");
				final String amount=(String)delete_grind_HMap.get("amount");				 
				String sql="";
				sql="select * from T_CUTTER where id='"+id+"'"; 
				final String []result=new String[1];
				result[0]="true";
				try {			
					DbUtil.execute(sql,new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {					
							try {
								while(rs.next()){
									jsonObj.put("id", rs.getString("id"));
									jsonObj.put("uc_count_in", rs.getString("uc_count_in"));								
									final int now_amount = Integer.parseInt(amount)+Integer.parseInt(jsonObj.getString("uc_count_in"));								
									String[] sqls = new String[1];	
									sqls[0] = "update T_CUTTER set uc_count_in=?  where id='"+id+"'";								
									try {
										DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
											public void process(PreparedStatement[] pstmts)	throws SQLException {			
												pstmts[0].setString(1,String.valueOf(now_amount));										
												pstmts[0].addBatch();
											}										
										});										
									}catch (SQLException e) {
									// TODO 自动生成 catch 块
										e.printStackTrace();
										result[0]="exception";
									}								
								}								
								String[] sql1 = new String[1];	
								sql1[0] = "delete from T_C_GRD_RECORD  where rid='"+rid+"'";	
								try {
									DbUtil.executeBatchs(sql1,new IArrayPreparedStatementProcessor(){
										public void process(PreparedStatement[] pstmts)	throws SQLException {																				
											pstmts[0].addBatch();
										}										
									});										
								}catch (SQLException e) {
								// TODO 自动生成 catch 块
									e.printStackTrace();
									result[0]="exception";
								}			
								
							} catch (JSONException e) {
								// TODO 自动生成 catch 块
								e.printStackTrace();
								result[0]="exception";
							}
						}				
					});					
				}catch (SQLException e) {
				// TODO 自动生成 catch 块
					e.printStackTrace();
					result[0]="exception";
				}			
				return result[0];			
					
			}		
	
	
	//送磨返库 查询
	 public JSONObject query_cut_regrind(HashMap queryMap) {
			// TODO 自动生成方法存根
			HashMap query_cutterHashMap=queryMap;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select  count(*) as ct  from  v_cutter2grd_record where "+(String)queryMap.get("filter");
			final String [] count=new String[1];
			count[0] = "";
			try {
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {
						if (rs.next()) {
							try {
								jsonObj.put("regrind_totalProp",rs.getString("ct"));
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
			int start=Integer.parseInt((String)query_cutterHashMap.get("start"));
			int limit=Integer.parseInt((String)query_cutterHashMap.get("limit"));
			
			String filter=(String)query_cutterHashMap.get("filter");
			String order=(String)query_cutterHashMap.get("order");        
			sql="select * from (select * from  (select * from v_cutter2grd_record where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from v_cutter2grd_record where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
			
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
								decimalformat.setMaximumFractionDigits(2);
								obj.put("id", rs.getString("id"));															
								obj.put("pclass", rs.getString("pclass"));
								obj.put("bclass", rs.getString("bclass"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));	
								obj.put("companyid", rs.getString("companyid"));
								obj.put("hq", rs.getString("hq"));
								obj.put("rank_angle", rs.getString("rank_angle"));
								obj.put("c_mate", rs.getString("c_mate"));
								obj.put("coat_mate", rs.getString("coat_mate"));														
								obj.put("location", rs.getString("location"));							
								obj.put("e_diam", rs.getString("e_diam"));
								obj.put("h_diam", rs.getString("h_diam"));
								obj.put("e_leng", rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
								obj.put("t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));								
								obj.put("e_count",rs.getString("e_count"));								
								obj.put("type", rs.getString("type"));	
								obj.put("series_num", rs.getString("series_num"));	
								obj.put("grd_department", rs.getString("grd_department"));	
								obj.put("s_time", rs.getString("s_time"));	
								obj.put("grd_num", rs.getString("grd_num"));	
								obj.put("s_count", rs.getString("s_count"));
								obj.put("undo_count", rs.getInt("s_count")-rs.getInt("r_count"));
							      jsonArray.put(obj);
							     
							}	
								jsonObj.put("regrind_data", jsonArray);
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
		};
	
		//送磨返库列表
		public JSONObject alter_grind_cutter(HashMap alterMap) {
			// TODO 自动生成方法存根
			HashMap view_grind_cutterHashMap=alterMap;
			final JSONObject jsonObj=new JSONObject();			
			final JSONArray jsonArray=new JSONArray();
			final String grd_num=(String)view_grind_cutterHashMap.get("grd_num");
			String sql="";
			String sql1="";
			           
			sql="select * from v_cutter2grd_record where "+(String)alterMap.get("filter")+" and status='2' "+" order by "+(String)alterMap.get("order")+"" ;; 
			sql1="select  count(*) as ct  from  T_C_GRD_RECORD  where  "+(String)alterMap.get("filter")+"  and status='2' "; 
             
			final String [] count=new String[1];
			count[0] = "";
			Logger.debug(sql1);
			try {
				DbUtil.execute(sql1,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {
						if (rs.next()) {
							try {
								jsonObj.put("regrind_totalProp",rs.getString("ct"));
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
			
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
								decimalformat.setMaximumFractionDigits(2);
								obj.put("id", rs.getString("id"));								
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));
								obj.put("companyid", rs.getString("companyid"));	
								obj.put("rank_angle", rs.getString("rank_angle"));
								obj.put("c_mate", rs.getString("c_mate"));
								obj.put("coat_mate", rs.getString("coat_mate"));														
								obj.put("location", rs.getString("location"));							
								obj.put("e_diam", rs.getString("e_diam"));
								obj.put("h_diam", rs.getString("h_diam"));
								obj.put("e_leng", rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
								obj.put("t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));								
								obj.put("e_count",rs.getString("e_count"));								
								obj.put("grd_num", rs.getString("grd_num"));	
								obj.put("s_count", rs.getString("s_count"));
								obj.put("r_count", rs.getString("r_count"));
								obj.put("s_time", rs.getString("s_time"));
								obj.put("r_time", rs.getString("r_time"));
								obj.put("app_status_in", rs.getString("app_status_in"));
								obj.put("undo_count", rs.getInt("r_count")-rs.getInt("l_count"));
								obj.put("price", rs.getString("price"));								
								jsonArray.put(obj);
								}	
							jsonObj.put("regrind_data", jsonArray);
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
		
		//返库申请中 数量为0的 
		public String zero_regrind(HashMap zero_HMap) {
			// TODO 自动生成方法存根
			
			final String rid=(String)zero_HMap.get("rid");				
			
			try {				
			String[] sqls = new String[1];	
			sqls[0] = "update T_C_GRD_RECORD set status='3'  where rid in ('"+rid+"')";
			System.out.println(sqls[0]);
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)	throws SQLException {											
						pstmts[0].addBatch();
					}
				});				
				return "1";			
			}
			catch (SQLException e) {
				e.printStackTrace();
				return "0";
			}	
		 
		}	
		
//		送磨返库（某刀具）详情
		public JSONObject detail_single_cutter(HashMap singleMap) {
			// TODO 自动生成方法存根
			HashMap view_grind_cutterHashMap=singleMap;
			final JSONObject jsonObj=new JSONObject();
			final String id=(String)view_grind_cutterHashMap.get("id");
			String sql="";
			sql="select * from v_cutter2grd_record where id='"+id+"'"; 

			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
								decimalformat.setMaximumFractionDigits(2);
								jsonObj.put("id", rs.getString("id"));								
								jsonObj.put("name",rs.getString("name"));
								jsonObj.put("company", rs.getString("company"));
								jsonObj.put("companyid", rs.getString("companyid"));	
								jsonObj.put("rank_angle", rs.getString("rank_angle"));
								jsonObj.put("c_mate", rs.getString("c_mate"));
								jsonObj.put("coat_mate", rs.getString("coat_mate"));														
								jsonObj.put("location", rs.getString("location"));							
								jsonObj.put("e_diam", rs.getString("e_diam"));
								jsonObj.put("h_diam", rs.getString("h_diam"));
								jsonObj.put("e_leng", rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
								jsonObj.put("t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));								
								jsonObj.put("e_count",rs.getString("e_count"));								
								jsonObj.put("grd_num", rs.getString("grd_num"));	
								jsonObj.put("s_count", rs.getString("s_count"));
								jsonObj.put("r_count", rs.getString("r_count"));								
								jsonObj.put("undo_count", rs.getInt("r_count")-rs.getInt("l_count"));								
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
		
		 //将送磨返库刀具添加到刀具基本表中
		public String insert_regrind(HashMap regrindMap) {
			// TODO 自动生成方法存根
			final HashMap insert_regrindHMap=regrindMap;
			final String id=(String)regrindMap.get("id");
			final String type_id=UUID.randomUUID().toString();
			final String now_r_count=(String)regrindMap.get("r_count");	
			final String check_undo_count=(String)regrindMap.get("check_undo_count");
			final String []result = new String[1];		
			
			check_undo_count.equals("0");
			
			result[0]="true";
			String sqls0 ="";	
			sqls0 = "select L_COUNT from T_C_GRD_RECORD  where rid='"+id+"'";
			try {		
			DbUtil.execute(sqls0,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if (rs.next()) {
						final int db ;
				     	db = rs.getInt("L_COUNT");		 
						try {						
								String[] sqls = new String[1];	
								sqls[0] = "update T_C_GRD_RECORD set L_COUNT=?,status=? where rid='"+id+"'";
								System.out.println(sqls[0]);
									DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
										public void process(PreparedStatement[] pstmts)	throws SQLException {	
											pstmts[0].setInt(1,Integer.parseInt(now_r_count)+db);
											pstmts[0].setInt(2,Integer.parseInt(check_undo_count)==0 ?3:2);	
											pstmts[0].addBatch();
										}
										});		
							} catch (SQLException e) {
								// TODO 自动生成 catch 块
								e.printStackTrace();
								result[0]="exception";	
							}
					     try{	
									String []sql1 = new String[1];
									sql1[0]= "insert into T_CUTTER(id,bclass,pclass,name,companyid,rank_angle,c_mate,coat_mate,suit_mate1,suit_mate2,location,remark,e_diam,h_diam,e_leng,t_leng,e_count,gc_count_in,effect_length) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
									Logger.debug(sql1[0]);
									DbUtil.executeBatchs(sql1,
											new IArrayPreparedStatementProcessor() {
												public void process(
												PreparedStatement[] pstmt)
												throws SQLException {
													pstmt[0].setString(1,type_id);
											if(insert_regrindHMap.get("bclass")!=null){
												pstmt[0].setString(2,(String)insert_regrindHMap.get("bclass"));//mnum
											}else{
												pstmt[0].setString(2,"");//mnum
											}					
											if(insert_regrindHMap.get("pclass")!=null){
												pstmt[0].setString(3,(String)insert_regrindHMap.get("pclass"));//name
											}else{
												pstmt[0].setString(3,"");//name
											}							
											if(insert_regrindHMap.get("name")!=null){
												pstmt[0].setString(4,(String)insert_regrindHMap.get("name"));//diviv
											}else{
												pstmt[0].setString(4,"");//diviv
											}						
											if(insert_regrindHMap.get("companyid")!=null){
												pstmt[0].setString(5,(String)insert_regrindHMap.get("companyid"));//companyid
											}else{
												pstmt[0].setString(5,"");//company
											}					
											if(insert_regrindHMap.get("rank_angle")!=null){
												pstmt[0].setString(6,(String)insert_regrindHMap.get("rank_angle"));//hq
											}else{
												pstmt[0].setString(6,"");//hq
											}					
											if(insert_regrindHMap.get("c_mate")!=null){
												pstmt[0].setString(7,(String)insert_regrindHMap.get("c_mate"));//hq
											}else{
												pstmt[0].setString(7,"");//hq
											}
											if(insert_regrindHMap.get("coat_mate")!=null){
												pstmt[0].setString(8,(String)insert_regrindHMap.get("coat_mate"));//hq
											}else{
												pstmt[0].setString(8,"");//hq
											}						
											if(insert_regrindHMap.get("suit_mate1")!=null){
												pstmt[0].setString(9,(String)insert_regrindHMap.get("suit_mate1"));//hq
											}else{
												pstmt[0].setString(9,"");//hq
											}						
											if(insert_regrindHMap.get("suit_mate2")!=null){
												pstmt[0].setString(10,(String)insert_regrindHMap.get("suit_mate2"));//hq
											}else{
												pstmt[0].setString(10,"");//hq
											}					
											if(insert_regrindHMap.get("location")!=null){
												pstmt[0].setString(11,(String)insert_regrindHMap.get("location"));//remark
											}else{
												pstmt[0].setString(11,"");//remark
											}						
											if(insert_regrindHMap.get("remark")!=null){
												pstmt[0].setString(12,(String)insert_regrindHMap.get("remark"));//remark
											}else{
												pstmt[0].setString(12,"");//remark
											}						
											if(insert_regrindHMap.get("e_diam")!=null){
												pstmt[0].setString(13,(String)insert_regrindHMap.get("e_diam"));//mnum							
											}else{
												pstmt[0].setString(13,"");//mnum							
											}						
											if(insert_regrindHMap.get("h_diam")!=null){
												pstmt[0].setString(14,(String)insert_regrindHMap.get("h_diam"));//class
											}else{
												pstmt[0].setString(14,"");//class
											}					
											if(insert_regrindHMap.get("e_leng")!=null){
												pstmt[0].setString(15,(String)insert_regrindHMap.get("e_leng"));//name
											}else{
												pstmt[0].setString(15,"");//name
											}						
											if(insert_regrindHMap.get("t_leng")!=null){
												pstmt[0].setString(16,(String)insert_regrindHMap.get("t_leng"));//spec
											}else{
												pstmt[0].setString(16,"");//spec
											}				
											if(insert_regrindHMap.get("e_count")!=null){
												pstmt[0].setString(17,(String)insert_regrindHMap.get("e_count"));//hq
											}else{
												pstmt[0].setString(17,"");//hq
											}					
											if(insert_regrindHMap.get("r_count")!=null){
												pstmt[0].setString(18,(String)insert_regrindHMap.get("r_count"));//remark
											}else{
												pstmt[0].setString(18,"");//remark
											}						
											if(insert_regrindHMap.get("effect_length")!=null){
												pstmt[0].setString(19,(String)insert_regrindHMap.get("effect_length"));//remark
											}else{
												pstmt[0].setString(19,"");//remark
											}						
											    pstmt[0].addBatch();			
										}	 						
									});	
						} catch (SQLException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
							result[0]="exception";	
						}
					}
				}	
			});	
			}
			catch (SQLException e) {
				e.printStackTrace();
				result[0] = "Exception";
			}
		 return result[0];	
		}	
		
			
		//送磨提交领导审批
		public String ok_grind(HashMap ok_grind_HMap) {
			// TODO 自动生成方法存根
			
			final String rid=(String)ok_grind_HMap.get("rid");			
			final String s_time=(String)ok_grind_HMap.get("s_time");
			final String grd_department=(String)ok_grind_HMap.get("grd_department");			
			final String grd_num=(String)ok_grind_HMap.get("grd_num");
			
			String sql="select count(*) as ct from T_C_GRD_RECORD where grd_num='"+grd_num+"'";		
			final String []count = new String[1];
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
				return "0";
			  }
			if(count[0].equals("0")){
			try {				
			String[] sqls = new String[1];	
			sqls[0] = "update T_C_GRD_RECORD set s_time='"+s_time+"',grd_department='"+grd_department+"',grd_num='"+grd_num+"',status='0' , app_status='未处理'  where rid in ("+rid+")";
			System.out.println(sqls[0]);
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)	throws SQLException {											
						pstmts[0].addBatch();
					}
				});				
				return "1";			
			}
			catch (SQLException e) {
				e.printStackTrace();
				return "0";
			}	
		  }else{			  
			  return "2";
		  }
		}	
			
	     //刀具送磨审批状态查询
		 public JSONObject query_do_togrind(HashMap queryMap) {
				// TODO 自动生成方法存根
				HashMap query_cutterHashMap=queryMap;
				final JSONObject jsonObj=new JSONObject();
				final JSONArray jsonArray=new JSONArray();
				String sql="";
				String sql1="";
				sql="select  count(distinct grd_num) as ct  from  T_C_GRD_RECORD  where "+(String)queryMap.get("filter");
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
				int start=Integer.parseInt((String)query_cutterHashMap.get("start"));
				int limit=Integer.parseInt((String)query_cutterHashMap.get("limit"));
				
				String filter=(String)query_cutterHashMap.get("filter");
				String order=(String)query_cutterHashMap.get("order");        
				sql1="select distinct grd_num,grd_department,s_time from (select distinct grd_num,grd_department,s_time from  (select distinct grd_num,grd_department,s_time from T_C_GRD_RECORD where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  distinct grd_num,grd_department,s_time  from  (select distinct grd_num,grd_department,s_time from T_C_GRD_RECORD where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
				
				Logger.debug(sql);
				Logger.debug(sql1);
				try {			
					DbUtil.execute(sql1,new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {					
							try {
								while(rs.next()){
									final JSONObject jsonOb=new JSONObject();
									JSONObject obj=new JSONObject();
									DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
									decimalformat.setMaximumFractionDigits(2);	
									obj.put("grd_num", rs.getString("grd_num"));
									obj.put("grd_department", rs.getString("grd_department"));								
									obj.put("s_time", rs.getString("s_time"));									
								
									String sql2="select app_status from  T_C_GRD_RECORD  where grd_num="+"'"+(rs.getString("grd_num"))+"'";
									Logger.debug(sql2);
									
									DbUtil.execute(sql2,new IResultSetProcessor() {
										public void process(ResultSet rs) throws SQLException {					
											try {												
												int k = 0 ;
												int j = 0 ;
												int l = 0 ;
												int i = 0 ;
												while(rs.next()){												
													i++;
													if(rs.getString("app_status").equals("批准")){
														k++;
													}else if(rs.getString("app_status").equals("拒绝")){
														j++;
													}else{
														l++;
													}													
												}	
												if(k==i){
													jsonOb.put("now_status", "批准");
												}else if(j==i){
													jsonOb.put("now_status", "拒绝");
												}else if(l!=0){
													jsonOb.put("now_status", "未处理");
												}else{
													jsonOb.put("now_status", "已处理");
												}
												
											} catch (JSONException e) {
												// TODO 自动生成 catch 块
												e.printStackTrace();
											}
										}				
									});	
									
									obj.put("app_status", jsonOb.getString("now_status"));									
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
			};
			
			 //刀具送磨返库审批状态查询
			 public JSONObject query_do_regrind(HashMap queryMap) {
					// TODO 自动生成方法存根
					HashMap query_cutterHashMap=queryMap;
					final JSONObject jsonObj=new JSONObject();
					final JSONArray jsonArray=new JSONArray();
					String sql="";
					String sql1="";
					sql="select  count(distinct grd_num) as ct  from  T_C_GRD_RECORD  where "+(String)queryMap.get("filter");
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
					int start=Integer.parseInt((String)query_cutterHashMap.get("start"));
					int limit=Integer.parseInt((String)query_cutterHashMap.get("limit"));
					
					String filter=(String)query_cutterHashMap.get("filter");
					String order=(String)query_cutterHashMap.get("order");        
					sql1="select distinct grd_num,grd_department,r_time from (select distinct grd_num,grd_department,r_time from  (select distinct grd_num,grd_department,r_time from T_C_GRD_RECORD where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  distinct grd_num,grd_department,r_time  from  (select distinct grd_num,grd_department,r_time from T_C_GRD_RECORD where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
					
					System.out.println(sql);
					
					try {			
						DbUtil.execute(sql1,new IResultSetProcessor() {
							public void process(ResultSet rs) throws SQLException {					
								try {
									while(rs.next()){
										final JSONObject jsonOb=new JSONObject();
										JSONObject obj=new JSONObject();
										DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
										decimalformat.setMaximumFractionDigits(2);	
										obj.put("grd_num", rs.getString("grd_num"));
										obj.put("grd_department", rs.getString("grd_department"));								
										obj.put("r_time", rs.getString("r_time"));									
									
										String sql2="select app_status_in from  T_C_GRD_RECORD  where grd_num="+"'"+(rs.getString("grd_num"))+"'";
										Logger.debug(sql2);
										
										DbUtil.execute(sql2,new IResultSetProcessor() {
											public void process(ResultSet rs) throws SQLException {					
												try {												
													int k = 0 ;
													int j = 0 ;
													int l = 0 ;
													int i = 0 ;
													while(rs.next()){												
														i++;
														if(rs.getString("app_status_in").equals("批准")){
															k++;
														}else if(rs.getString("app_status_in").equals("拒绝")){
															j++;
														}else{
															l++;
														}													
													}	
													if(k==i){
														jsonOb.put("now_status", "批准");
													}else if(j==i){
														jsonOb.put("now_status", "拒绝");
													}else if(l!=0){
														jsonOb.put("now_status", "未处理");
													}else{
														jsonOb.put("now_status", "已处理");
													}
													
												} catch (JSONException e) {
													// TODO 自动生成 catch 块
													e.printStackTrace();
												}
											}				
										});	
										
										obj.put("app_status_in", jsonOb.getString("now_status"));									
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
				};
			
				
             //刀具送磨审批状态查询
			 public JSONObject detail_do_togrind(HashMap queryMap) {
					// TODO 自动生成方法存根
					HashMap detail_cutterHashMap=queryMap;
					final JSONObject jsonObj=new JSONObject();
					final JSONArray jsonArray=new JSONArray();
					String sql="";
					String sql1="";
					sql="select  *  from  v_cutter2grd_record  where "+(String)queryMap.get("filter")+" order by "+(String)queryMap.get("order")+"" ;
					sql1="select  count(*) as ct  from  T_C_GRD_RECORD  where "+(String)queryMap.get("filter");
					final String [] count=new String[1];
					count[0] = "";
					
					try {
						DbUtil.execute(sql1,new IResultSetProcessor() {
							public void process(ResultSet rs) throws SQLException {
								if (rs.next()) {
									try {
										jsonObj.put("check_totalProp",rs.getString("ct"));
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
					
					try {			
						DbUtil.execute(sql,new IResultSetProcessor() {
							public void process(ResultSet rs) throws SQLException {					
								try {
									while(rs.next()){
										JSONObject obj=new JSONObject();
										DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
										decimalformat.setMaximumFractionDigits(2);
										obj.put("id", rs.getString("id"));	
										obj.put("type_id", rs.getString("type_id"));	
										obj.put("pclass", rs.getString("pclass"));
										obj.put("bclass", rs.getString("bclass"));
										obj.put("name",rs.getString("name"));
										obj.put("company", rs.getString("company"));	
										obj.put("companyid", rs.getString("companyid"));
										obj.put("hq", rs.getString("hq"));
										obj.put("rank_angle", rs.getString("rank_angle"));
										obj.put("c_mate", rs.getString("c_mate"));
										obj.put("coat_mate", rs.getString("coat_mate"));														
										obj.put("location", rs.getString("location"));							
										obj.put("e_diam", rs.getString("e_diam"));
										obj.put("h_diam", rs.getString("h_diam"));
										obj.put("e_leng", rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
										obj.put("t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));								
										obj.put("e_count",rs.getString("e_count"));								
										obj.put("type", rs.getString("type"));	
										obj.put("series_num", rs.getString("series_num"));	
										obj.put("grd_department", rs.getString("grd_department"));	
										obj.put("s_time", rs.getString("s_time"));	
										obj.put("grd_num", rs.getString("grd_num"));	
										obj.put("s_count", rs.getString("s_count"));
										obj.put("price", rs.getString("price"));
										obj.put("app_status", rs.getString("app_status"));
										obj.put("r_count", rs.getString("r_count"));
									      jsonArray.put(obj);
									     
									}	
										jsonObj.put("check_data", jsonArray);
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
				};
					
				
				//生成送磨单
				public String finish_grind(HashMap finish_grind_HMap) {
					// TODO 自动生成方法存根	
					final String grd_num=(String)finish_grind_HMap.get("grd_num");
					try {				
					String[] sqls = new String[1];	
					sqls[0] = "update T_C_GRD_RECORD set status='1'  where grd_num in ("+grd_num+")";
					System.out.println(sqls[0]);
						DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
							public void process(PreparedStatement[] pstmts)	throws SQLException {											
								pstmts[0].addBatch();
							}
						});				
						return "1";			
					}
					catch (SQLException e) {
						e.printStackTrace();
						return "0";
					}				
				}	
				
				//从送磨单中删除领导拒绝的项
				public String delete_check(HashMap delete_check_HMap) {
					// TODO 自动生成方法存根
					final JSONObject jsonObj=new JSONObject();			
					final String id=(String)delete_check_HMap.get("id");
					final String rid=(String)delete_check_HMap.get("rid");
					final String amount=(String)delete_check_HMap.get("amount");				 
					String sql="";
					sql="select * from T_CUTTER where id='"+id+"'"; 
					final String []result=new String[1];
					result[0]="true";
					try {			
						DbUtil.execute(sql,new IResultSetProcessor() {
							public void process(ResultSet rs) throws SQLException {					
								try {
									while(rs.next()){
										jsonObj.put("id", rs.getString("id"));
										jsonObj.put("uc_count_in", rs.getString("uc_count_in"));								
										final int now_amount = Integer.parseInt(amount)+Integer.parseInt(jsonObj.getString("uc_count_in"));								
										String[] sqls = new String[1];	
										sqls[0] = "update T_CUTTER set uc_count_in=?  where id='"+id+"'";								
										try {
											DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
												public void process(PreparedStatement[] pstmts)	throws SQLException {			
													pstmts[0].setString(1,String.valueOf(now_amount));										
													pstmts[0].addBatch();
												}										
											});										
										}catch (SQLException e) {
										// TODO 自动生成 catch 块
											e.printStackTrace();
											result[0]="exception";
										}								
									}								
									String[] sql1 = new String[1];	
									sql1[0] = "delete from T_C_GRD_RECORD  where rid='"+rid+"'";	
									try {
										DbUtil.executeBatchs(sql1,new IArrayPreparedStatementProcessor(){
											public void process(PreparedStatement[] pstmts)	throws SQLException {																				
												pstmts[0].addBatch();
											}										
										});										
									}catch (SQLException e) {
									// TODO 自动生成 catch 块
										e.printStackTrace();
										result[0]="exception";
									}			
									
								} catch (JSONException e) {
									// TODO 自动生成 catch 块
									e.printStackTrace();
									result[0]="exception";
								}
							}				
						});					
					}catch (SQLException e) {
					// TODO 自动生成 catch 块
						e.printStackTrace();
						result[0]="exception";
					}			
					return result[0];			
						
				}	
				
}