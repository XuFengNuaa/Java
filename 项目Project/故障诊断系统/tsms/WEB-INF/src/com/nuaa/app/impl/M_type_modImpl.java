
package com.nuaa.app.impl;
	
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.*;

import jxl.Cell;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import org.apache.commons.fileupload.FileItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.FileState;
import com.nuaa.app.M_type_mod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

	public class M_type_modImpl implements M_type_mod{
			    public String add_m_type(HashMap add_m_type_HMap) {
				// TODO 自动生成方法存根
				final HashMap add_m_typeHashMap=add_m_type_HMap;
				final String id=UUID.randomUUID().toString();  //自动生成id
				String sql ="";	
				String mnum=(String)add_m_type_HMap.get("mnum");
				String hq=(String)add_m_type_HMap.get("hq");
				String order_num=(String)add_m_type_HMap.get("order_num");
				String companyid=(String)add_m_type_HMap.get("companyid");
				Logger.debug(sql);
				//final JSONArray idArray=new JSONArray();
				final String[] count=new String[2];
				if("否".equals(hq)){
				sql="select count(*) as ct from T_M_TYPE where mnum='"+mnum+"' and companyid='"+companyid+"' and hq='否'";			
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
				  }}else if("是".equals(hq)){
					  sql="select count(*) as bt from T_M_TYPE where order_num='"+order_num+"'and companyid='"+companyid+"' and hq='是'";			
						try {			
							DbUtil.execute(sql,new IResultSetProcessor(){
								public void process(ResultSet rs) throws SQLException{
									while(rs.next()){					
										count[1]=rs.getString("bt");
									}
								}
							});	
						}catch (SQLException e){
							e.printStackTrace();
						  }
					  
				  }					
				
				try {
					if (("否".equals(hq)&&"0".equals(count[0]))||"是".equals(hq)&&"0".equals(count[1])){	
						String []sqls = new String[1];
						sqls[0]= "insert into T_M_TYPE(id,mnum,classname,name,spec,diviv,companyid,mini_qs,hq,remark,order_num,use_freq) values(?,?,?,?,?,?,?,?,?,?,?,?)";
						DbUtil.executeBatchs(sqls,
								new IArrayPreparedStatementProcessor() {
									public void process(
									PreparedStatement[] pstmts)
									throws SQLException {
									pstmts[0].setString(1,id);
								if(add_m_typeHashMap.get("mnum")!=null){
									pstmts[0].setString(2,(String)add_m_typeHashMap.get("mnum"));//mnum
								}else{
									pstmts[0].setString(2," ");//mnum
								}
								if(add_m_typeHashMap.get("classname")!=null){
									pstmts[0].setString(3,(String)add_m_typeHashMap.get("classname"));//class
								}else{
									pstmts[0].setString(3," ");//class
								}
								if(add_m_typeHashMap.get("name")!=null){
									pstmts[0].setString(4,(String)add_m_typeHashMap.get("name"));//name
								}else{
									pstmts[0].setString(4," ");//name
								}
								if(add_m_typeHashMap.get("spec")!=null){
									pstmts[0].setString(5,(String)add_m_typeHashMap.get("spec"));//spec
								}else{
									pstmts[0].setString(5," ");//spec
								}
								if(add_m_typeHashMap.get("diviv")!=null){
									pstmts[0].setString(6,(String)add_m_typeHashMap.get("diviv"));//diviv
								}else{
									pstmts[0].setString(6," ");//diviv
								}
								if(add_m_typeHashMap.get("companyid")!=null){
									pstmts[0].setString(7,(String)add_m_typeHashMap.get("companyid"));//companyid
								}else{
									pstmts[0].setString(7," ");//company
								}
								if(add_m_typeHashMap.get("mini_qs")!=null){
									pstmts[0].setString(8,(String)add_m_typeHashMap.get("mini_qs"));//mini_qs
								}else{
									pstmts[0].setString(8," ");//mini_qs
								}
								if(add_m_typeHashMap.get("hq")!=null){
									pstmts[0].setString(9,(String)add_m_typeHashMap.get("hq"));//hq
								}else{
									pstmts[0].setString(9," ");//hq
								}
								if(add_m_typeHashMap.get("remark")!=null){
									pstmts[0].setString(10,(String)add_m_typeHashMap.get("remark"));//remark
								}else{
									pstmts[0].setString(10," ");//remark
								}
								if(add_m_typeHashMap.get("order_num")!=null){
									pstmts[0].setString(11,(String)add_m_typeHashMap.get("order_num"));//remark
								}else{
									pstmts[0].setString(11," ");//remark
								}
								pstmts[0].setInt(12,0);
								pstmts[0].addBatch();
								
							}
						});
						
						return "true";
					}else{
						return "false";
					}
				}catch (SQLException e) {
					e.printStackTrace();
					return "Exception";
				}
			}

			/* （非 Javadoc）
			 * @see com.nuaa.app.UsrMod#getQueryUsr(java.util.HashMap)
			 */
			public JSONObject getQuery_m_type(HashMap query_m_typeMap) {
				// TODO 自动生成方法存根
				HashMap query_m_typeHashMap=query_m_typeMap;
				final JSONObject jsonObj=new JSONObject();
				final JSONArray jsonArray=new JSONArray();
				String sql="";
				sql="select  count(*) as ct  from  V_MTYPE2COMPANY where "+(String)query_m_typeHashMap.get("filter");
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
				int start=Integer.parseInt((String)query_m_typeHashMap.get("start"));
				int limit=Integer.parseInt((String)query_m_typeHashMap.get("limit"));
				
				String filter=(String)query_m_typeHashMap.get("filter");
				String order=(String)query_m_typeHashMap.get("order");        
				sql="select * from (select * from  (select * from V_MTYPE2COMPANY where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from V_MTYPE2COMPANY where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
				
				Logger.debug(sql);
				try {			
					DbUtil.execute(sql,new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {					
							try {
								
								while(rs.next()){
								final	JSONObject obj1=new JSONObject();
								final	JSONObject obj2=new JSONObject();
									JSONObject obj=new JSONObject();
									obj.put("id", rs.getString("id"));
									obj.put("mnum", rs.getString("mnum"));
									obj.put("classname", rs.getString("classname"));
									obj.put("name", rs.getString("name"));
									obj.put("spec", rs.getString("spec"));
									obj.put("diviv",rs.getString("diviv"));
									obj.put("company", rs.getString("company"));
									obj.put("mini_qs", rs.getString("mini_qs"));
									obj.put("hq", rs.getString("hq"));
									obj.put("remark", rs.getString("remark"));
									obj.put("order_num", rs.getString("order_num"));
									String sql1="select count(*) as new_store from  T_M_INDI where newstatus='新' and STATUS='在库' and type_id='"+rs.getString("id")+"'"; 
									try {
										DbUtil.execute(sql1,new IResultSetProcessor() {
											public void process(ResultSet rs) throws SQLException {
												if (rs.next()) {
													try {
														obj1.put("new_store", rs.getString("new_store"));														
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
									String sql2="select count(*) as old_now_store from  T_M_INDI where newstatus='旧' and status='在库' and type_id='"+rs.getString("id")+"'"; 
									try {
										DbUtil.execute(sql2,new IResultSetProcessor() {
											public void process(ResultSet rs) throws SQLException {
												if (rs.next()) {
													try {
														obj2.put("old_now_store", rs.getString("old_now_store"));														
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
									
									obj.put("new_store", obj1.getString("new_store"));
									obj.put("old_now_store", obj2.getString("old_now_store"));
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
        //此类型数量为0时可删！
		/*	public boolean delCompany(HashMap delCompanyHMap) {
				// TODO 自动生成方法存根
				String id=(String)delCompanyHMap.get("id");
				String []sqls=new String[1];
				try {
					sqls[0]="delete from T_COMPANY where id in('',"+id+")";
					DbUtil.executeBatchs(sqls,
							new IArrayPreparedStatementProcessor() {
								public void process(
								PreparedStatement[] pstmts)
								throws SQLException {						
									pstmts[0].addBatch();
								}
					});
				  return true;
				} catch (Exception ex1) {
					ex1.printStackTrace();
					return false;
				}	
			}  */
     
			public String edit_m_type(HashMap edit_m_typeHMap) {
				// TODO 自动生成方法存根
				final String id=(String)edit_m_typeHMap.get("id");
				final String mnum=(String)edit_m_typeHMap.get("mnum");
				final String m_class=(String)edit_m_typeHMap.get("classname");
				final String name=(String)edit_m_typeHMap.get("name");
				final String spec=(String)edit_m_typeHMap.get("spec");
				final String diviv=(String)edit_m_typeHMap.get("diviv");
				final String companyid=(String)edit_m_typeHMap.get("companyid");
				final String mini_qs=(String)edit_m_typeHMap.get("mini_qs");
				final String hq=(String)edit_m_typeHMap.get("hq");
				final String remark=(String)edit_m_typeHMap.get("remark");
				final String order_num=(String)edit_m_typeHMap.get("order_num");
				
				String sql ="";	
				final String[] count=new String[2];
				if("否".equals(hq)){
					sql="select count(*) as ct from T_M_TYPE where mnum='"+mnum+"'and  companyid='"+companyid+"'and hq='否' and id!='"+id+"'";			
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
					  }}else if("是".equals(hq)){
						  sql="select count(*) as bt from T_M_TYPE where order_num='"+order_num+"'and companyid='"+companyid+"'and hq='是'  and id!='"+id+"'";			
							try {			
								DbUtil.execute(sql,new IResultSetProcessor(){
									public void process(ResultSet rs) throws SQLException{
										while(rs.next()){					
											count[1]=rs.getString("bt");
										}
									}
								});	
							}catch (SQLException e){
								e.printStackTrace();
							  }				  
					  }				
				try {
					if (("0".equals(count[0])&&"否".equals(hq))||("是".equals(hq)&&"0".equals(count[1]))){	
				String[] sqls = new String[1];	
				sqls[0] = "update T_M_TYPE set mnum=?,classname=?,name=?,spec=?,diviv=?,companyid=?,remark=?,mini_qs=?,hq=?,order_num=? where id='"+id+"'";
				System.out.println(sqls[0]);
					DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
						public void process(PreparedStatement[] pstmts)	throws SQLException {			
							pstmts[0].setString(1,mnum);
							pstmts[0].setString(2,m_class);
							pstmts[0].setString(3,name);
							pstmts[0].setString(4,spec);
							pstmts[0].setString(5,diviv);
							pstmts[0].setString(6,companyid);
							pstmts[0].setString(7,remark);
							pstmts[0].setString(8,mini_qs);
							pstmts[0].setString(9,hq);
							pstmts[0].setString(10,order_num);
							pstmts[0].addBatch();
						}
					});
					return "true";
				}else{
					return "false";
					}
				}
				catch (SQLException e) {
					e.printStackTrace();
					return "exception";
				}
			}

			public JSONObject view_m_type(HashMap view_m_typeHMap) {
				// TODO 自动生成方法存根
				HashMap view_m_typeHashMap=view_m_typeHMap;
				final JSONObject jsonObj=new JSONObject();
				final JSONObject jsonObj1=new JSONObject();
				final JSONObject jsonObj2=new JSONObject();
				final String id=(String)view_m_typeHashMap.get("id");
				String sql="";
				sql="select * from V_MTYPE2COMPANY where id='"+id+"'"; 

				Logger.debug(sql);
				try {			
					DbUtil.execute(sql,new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {					
							try {
								while(rs.next()){
									jsonObj.put("id", rs.getString("id"));
									jsonObj.put("mnum", rs.getString("mnum"));
									jsonObj.put("classname", rs.getString("classname"));
									jsonObj.put("name", rs.getString("name"));
									jsonObj.put("spec", rs.getString("spec"));
									jsonObj.put("diviv",rs.getString("diviv"));
									jsonObj.put("companyid", rs.getString("companyid"));
									jsonObj.put("mini_qs", rs.getString("mini_qs"));
									jsonObj.put("hq", rs.getString("hq"));
									jsonObj.put("remark", rs.getString("remark"));
									jsonObj.put("use_freq", rs.getString("use_freq"));
									jsonObj.put("order_num", rs.getString("order_num"));
									jsonObj.put("company", rs.getString("company"));
									
									String sql1="select count(*) as new_store from  T_M_INDI where newstatus='新' and type_id='"+rs.getString("id")+"'"; 
									try {
										DbUtil.execute(sql1,new IResultSetProcessor() {
											public void process(ResultSet rs) throws SQLException {
												if (rs.next()) {
													try {
														jsonObj1.put("new_store", rs.getString("new_store"));														
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
									String sql2="select count(*) as old_now_store from  T_M_INDI where newstatus='旧' and status='在库' and type_id='"+rs.getString("id")+"'"; 
									try {
										DbUtil.execute(sql2,new IResultSetProcessor() {
											public void process(ResultSet rs) throws SQLException {
												if (rs.next()) {
													try {
														jsonObj2.put("old_now_store", rs.getString("old_now_store"));														
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
									jsonObj.put("new_store", jsonObj1.getString("new_store"));
									jsonObj.put("old_now_store", jsonObj2.getString("old_now_store"));
									
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
		
			public JSONObject getQuery_m_indi(HashMap query_m_indiMap) {
				// TODO 自动生成方法存根
				HashMap query_m_indiHashMap=query_m_indiMap;
				final JSONObject jsonObj=new JSONObject();
				final JSONArray jsonArray=new JSONArray();
				String sql="";
				sql="select  count(*) as ct  from  v_mindi2v_mcom where "+(String)query_m_indiHashMap.get("filter");
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
				int start=Integer.parseInt((String)query_m_indiHashMap.get("start"));
				int limit=Integer.parseInt((String)query_m_indiHashMap.get("limit"));
				
				String filter=(String)query_m_indiHashMap.get("filter");
				String order=(String)query_m_indiHashMap.get("order");
				
				sql="select * from (select * from  (select * from v_mindi2v_mcom where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from v_mindi2v_mcom where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
				Logger.debug(sql);
				try {			
					DbUtil.execute(sql,new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {					
							try {
								while(rs.next()){
									JSONObject obj=new JSONObject();
									obj.put("indi_id", rs.getString("indi_id"));
									obj.put("indi_num", rs.getString("indi_num"));
									obj.put("mnum", rs.getString("mnum"));
									obj.put("classname", rs.getString("classname"));
									obj.put("name", rs.getString("name"));
									obj.put("spec", rs.getString("spec"));
									obj.put("diviv",rs.getString("diviv"));
									obj.put("company", rs.getString("company"));
									obj.put("newstatus", rs.getString("newstatus"));
									obj.put("location", rs.getString("location"));
									obj.put("keeper", rs.getString("keeper"));
									obj.put("startdate", rs.getString("startdate"));
									obj.put("cycle", rs.getString("cycle"));
									obj.put("valid", rs.getString("valid"));
									obj.put("hq", rs.getString("hq"));
									obj.put("remark", rs.getString("remark"));
									obj.put("status", rs.getString("status"));
									obj.put("realname", rs.getString("realname"));
									obj.put("group_num", rs.getString("group_num"));
									jsonArray.put(obj);
																
								}	
									jsonObj.put("indi_filedata", jsonArray);
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
			
			public JSONObject view_m_indi(HashMap view_m_indiHMap) {
				// TODO 自动生成方法存根
				HashMap view_m_indiHashMap=view_m_indiHMap;
				final JSONObject jsonObj=new JSONObject();
				final String indi_id=(String)view_m_indiHashMap.get("indi_id");
				String sql="";
				sql="select * from v_mindi2v_mcom where indi_id='"+indi_id+"'"; 

				Logger.debug(sql);
				try {			
					DbUtil.execute(sql,new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {					
							try {
								while(rs.next()){
									jsonObj.put("indi_id", rs.getString("indi_id"));
									jsonObj.put("indi_num", rs.getString("indi_num"));
									jsonObj.put("type_id", rs.getString("type_id"));
									jsonObj.put("mnum", rs.getString("mnum"));
									jsonObj.put("classname", rs.getString("classname"));
									jsonObj.put("name", rs.getString("name"));
									jsonObj.put("startdate", rs.getString("startdate"));
									jsonObj.put("valid", rs.getString("valid"));
									jsonObj.put("cycle", rs.getString("cycle"));
									jsonObj.put("newstatus",rs.getString("newstatus"));
									jsonObj.put("keeper", rs.getString("keeper"));
									jsonObj.put("location", rs.getString("location"));
									jsonObj.put("remark", rs.getString("remark"));
									jsonObj.put("order_num", rs.getString("order_num"));
									jsonObj.put("company", rs.getString("company"));									
									jsonObj.put("diviv", rs.getString("diviv"));
									jsonObj.put("hq", rs.getString("hq"));
									jsonObj.put("spec", rs.getString("spec"));
									jsonObj.put("realname", rs.getString("realname"));
									jsonObj.put("group_num", rs.getString("group_num"));
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
			
			public JSONObject detail_m_indi(HashMap detail_m_indiHMap) {
				// TODO 自动生成方法存根
				HashMap detail_m_indiHashMap=detail_m_indiHMap;
				final JSONObject jsonObj=new JSONObject();
				final String indi_id=(String)detail_m_indiHashMap.get("indi_id");
				String sql="";
				sql="select * from v_mindi2v_mcom where indi_id='"+indi_id+"'"; 

				Logger.debug(sql);
				try {			
					DbUtil.execute(sql,new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {					
							try {
								while(rs.next()){
									jsonObj.put("indi_id", rs.getString("indi_id"));
									jsonObj.put("indi_num", rs.getString("indi_num"));
									jsonObj.put("mnum", rs.getString("mnum"));
									jsonObj.put("classname", rs.getString("classname"));
									jsonObj.put("name", rs.getString("name"));
									jsonObj.put("spec", rs.getString("spec"));
									jsonObj.put("diviv",rs.getString("diviv"));
									jsonObj.put("company", rs.getString("company"));
									jsonObj.put("newstatus", rs.getString("newstatus"));
									jsonObj.put("location", rs.getString("location"));
									jsonObj.put("keeper", rs.getString("keeper"));
									jsonObj.put("startdate", rs.getString("startdate"));
									jsonObj.put("cycle", rs.getString("cycle"));
									jsonObj.put("valid", rs.getString("valid"));
									jsonObj.put("hq", rs.getString("hq"));
									jsonObj.put("remark", rs.getString("remark"));
									jsonObj.put("status", rs.getString("status"));	
									jsonObj.put("order_num", rs.getString("order_num"));
									jsonObj.put("realname", rs.getString("realname"));
									jsonObj.put("group_num", rs.getString("group_num"));
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
			
		
			public String edit_m_indi(HashMap edit_m_indiHMap) {
				// TODO 自动生成方法存根
				final String indi_id=(String)edit_m_indiHMap.get("indi_id");
				final String indi_num=(String)edit_m_indiHMap.get("indi_num");
				final String startdate=(String)edit_m_indiHMap.get("startdate");				
				final String valid=(String)edit_m_indiHMap.get("valid");				
				final String cycle=(String)edit_m_indiHMap.get("cycle");
				final String newstatus=(String)edit_m_indiHMap.get("newstatus");
				final String type_id=(String)edit_m_indiHMap.get("type_id");				
				final String location=(String)edit_m_indiHMap.get("location");
				final String remark=(String)edit_m_indiHMap.get("remark");
				
				//验证个体编号是否重复				
				String sql ="";	
				
				sql="select count(*) as ct from T_M_INDI where indi_num='"+indi_num+"' and indi_id!='"+indi_id+"'";
				final String[] count=new String[1];
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
				}
				try {
					if ("0".equals(count[0])){	
				String[] sqls = new String[1];	
				sqls[0] = "update T_M_INDI set type_id=?,indi_num=?,startdate=?,valid=?,cycle=?,newstatus=?,remark=?,location=? where indi_id='"+indi_id+"'";
				System.out.println(sqls[0]);
					DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
						public void process(PreparedStatement[] pstmts)	throws SQLException {			
							pstmts[0].setString(1,type_id);
							pstmts[0].setString(2,indi_num);
							pstmts[0].setString(3,startdate);
							pstmts[0].setString(4,valid);
							pstmts[0].setString(5,cycle);
							pstmts[0].setString(6,newstatus);
							pstmts[0].setString(7,remark);							
							pstmts[0].setString(8,location);
							pstmts[0].addBatch();
						}
					});
					return "true";
				}else{
				  return "false";	
				}
				}
					catch (SQLException e) {
					e.printStackTrace();
					return "exception";
				}
			}
			
			public JSONObject getQueryM_TypeAll() {
				// TODO 自动生成方法存根
				
				final JSONObject jsonObj=new JSONObject();
				final JSONArray jsonArray=new JSONArray();
				String sql="";
				sql="select distinct mnum from T_M_TYPE ";
				
				Logger.debug(sql);
				try {			
					DbUtil.execute(sql,new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {					
							try {
								while(rs.next()){
									JSONObject obj=new JSONObject();								
									obj.put("mnum", rs.getString("mnum"));
									jsonArray.put(obj);
								}	
									jsonObj.put("all_filedata", jsonArray);
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
			

			public String check_m_type(HashMap check_m_type_HMap) {
				// TODO 自动生成方法存根				
				final String mnum=(String)check_m_type_HMap.get("mnum");			
				String sql ="";	
				final int []count=new int[1];	
				String []result = new String[1];
				
				count[0]=0;
			try {
				sql="select * from T_M_TYPE where mnum='"+mnum+"'";							
					DbUtil.execute(sql,new IResultSetProcessor(){
						public void process(ResultSet rs) throws SQLException{
							while(rs.next()){					
								count[0]=1;
							}
						}
					});					
			    	if (count[0]==1){			
				    	result[0]="true";
			    	}else{
			    		result[0]="false";	
				  }
				}
				catch (SQLException e) {
					e.printStackTrace();
					result[0]="Exception";	
				}
				return result[0];
			}		
			
			public JSONObject query_mnum_com(HashMap query_mnum_com_HMap) {
				// TODO 自动生成方法存根
				
				final String mnum=(String)query_mnum_com_HMap.get("filter");		
				final JSONObject jsonObj=new JSONObject();
				final JSONArray jsonArray=new JSONArray();
				String sql="";				
				sql="select distinct company from v_mtype2company where mnum='"+mnum+"'";	
				Logger.debug(sql);
				try {			
					DbUtil.execute(sql,new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {					
							try {
								while(rs.next()){
									JSONObject obj=new JSONObject();									
									obj.put("company", rs.getString("company"));															
									jsonArray.put(obj);																
								}	
									jsonObj.put("com_filedata", jsonArray);
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
			
			
			public JSONObject query_mnum(HashMap query_mnum_HMap) {
				// TODO 自动生成方法存根
				
				final String mnum=(String)query_mnum_HMap.get("mnum");		
				final JSONObject jsonObj=new JSONObject();
				final JSONArray jsonArray=new JSONArray();
				String sql="";				
				sql="select * from v_mtype2company where mnum='"+mnum+"'";	
				Logger.debug(sql);
				try {			
					DbUtil.execute(sql,new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {					
							try {
								while(rs.next()){
									JSONObject obj=new JSONObject();									
									obj.put("company", rs.getString("company"));
									obj.put("mnum", rs.getString("mnum"));
									obj.put("order_num", rs.getString("order_num"));
									obj.put("class", rs.getString("classname"));
									obj.put("name", rs.getString("name"));
									obj.put("spec", rs.getString("spec"));
									obj.put("diviv", rs.getString("diviv"));
									obj.put("hq", rs.getString("hq"));
									obj.put("remark", rs.getString("remark"));
									obj.put("type_id", rs.getString("id"));
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
			
			public JSONObject query_order_num(HashMap query_order_numHMap) {
				// TODO 自动生成方法存根
				
				final String filter=(String)query_order_numHMap.get("filter");		
				final JSONObject jsonObj=new JSONObject();
				final JSONArray jsonArray=new JSONArray();
				String sql="";				
				sql="select * from v_mtype2company where "+filter+"";	
				Logger.debug(sql);
				try {			
					DbUtil.execute(sql,new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {					
							try {
								while(rs.next()){
									JSONObject obj=new JSONObject();									
									obj.put("company", rs.getString("company"));
									obj.put("mnum", rs.getString("mnum"));
									obj.put("order_num", rs.getString("order_num"));
									obj.put("class", rs.getString("classname"));
									obj.put("name", rs.getString("name"));
									obj.put("spec", rs.getString("spec"));
									obj.put("diviv", rs.getString("diviv"));
									obj.put("hq", rs.getString("hq"));
									obj.put("remark", rs.getString("remark"));
									obj.put("type_id", rs.getString("id"));
									jsonArray.put(obj);																
								}	
									jsonObj.put("ordernum_filedata", jsonArray);
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
			
			//上传文件
			public HashMap uploadfile(HashMap fileHmap) {
				final HashMap fileHashMap = fileHmap;	
		    	final HashMap returnHashMap=new HashMap();	
		    	String tempPath = "\\web\\tempfile\\"; // 用于存放临时文件的目录
				String trace = "";
				String filename = "";
				String sclassid = "";
				String dir = (String)fileHashMap.get("dir");
				//建临时文件夹
				File fii=new File(dir+tempPath);
				if(!fii.exists()) {
				   fii.mkdirs(); 
				}
				try{	
					System.out.println("执行到这里了！！！");
					List fileItems = (List)fileHashMap.get("fileItems");
					Iterator i = fileItems.iterator();
					while(i.hasNext()){		
		    			  FileItem fi = (FileItem)i.next();
		    			  String fileName = fi.getName();
		  			 	  if(fileName!=null){
			  			 	//	Calendar date = Calendar.getInstance();
			  				//	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-kk时-mm分-ss秒");
			  				//	final String now = format.format(date.getTime())  ;
		    					String[] s=fileName.split("\\.");
		    					filename = "导入量具."+s[s.length-1];
		    					System.out.println("1111111111111111"+filename);
		    					System.out.println("22222222222"+s);
		    					String[] n = filename.split("\\.");
		    					sclassid = n[0];
		    					trace = tempPath+filename;
		    					fi.write(new File(dir+trace));
		    					returnHashMap.put("filename",sclassid);
		    					System.out.println("***********"+sclassid);
		    			  }
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
				return returnHashMap;
			}
			
			
           //导入文件
			public String getExcel(HashMap xlsHmap) {
             //TODO 自动生成方法存根
				HashMap xlsHashMap=xlsHmap;
				final String []result = new String[1];
				result[0]="true";
				String dir = (String)xlsHashMap.get("dir"); 
				final String[] pa = new String[30];
				for (int i = 0; i < pa.length; i++) {
					pa[i] = "";
				}
				
				try 
				{ 	
					WorkbookSettings setting=new WorkbookSettings();   
					setting.setEncoding("ISO-8859-1");   
					Workbook book= Workbook.getWorkbook(new File(dir+"\\web\\tempfile\\导入量具.xls"),setting); 
			        //获得第一个工作表对象 
					Sheet sheet=book.getSheet(0); 
		        	//得到第一列第一行的单元格 
					
					Pattern pattern = Pattern.compile("^((19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$"); 
					Pattern pattern1 = Pattern.compile("^(3|6|12|24|36)$");  //匹配鉴定周期
					Pattern pattern2 = Pattern.compile("^(0|[1-9][0-9]*)$"); //匹配整数					
					Pattern pattern3 = Pattern.compile("^[0-9]{2}-[0-9]{2}-0[0-3]-[0-9]{2}$"); //匹配库存地点
			        Pattern pattern4 = Pattern.compile("^\\d+$"); //匹配数字
			        Pattern pattern5 = Pattern.compile("^\\d{2}-\\d{1,2}-\\d{1,2}$");//匹配excel中日期格式
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					
					for(int x = 1; x < sheet.getRows(); x++){						
						
						Cell check1=sheet.getCell(0,x);	//个体编号
						Cell check2=sheet.getCell(3,x); //库存地点
						
						Matcher matcher5 = pattern4.matcher(sheet.getCell(6,x).getContents());//鉴定周期
						boolean a = matcher5.matches();
						
						if(!sheet.getCell(6,x).getContents().equals("")&&a==false){
							result[0]="cyclefalse";	
						}else if(!sheet.getCell(6,x).getContents().equals("")&&a==true){
						    NumberCell check4=(NumberCell)sheet.getCell(6,x); //鉴定周期		
						    Matcher matcher2 = pattern1.matcher(check4.getContents());
						    boolean d = matcher2.matches();
						    if(d==false&&result[0].equals("true")){
								result[0]="cyclefalse";	
						     }	
						  }else{
							  result[0]="cyclenull";
						  }						
						
						Matcher matcher6 = pattern4.matcher(sheet.getCell(16,x).getContents());//最低库存量	
						boolean d = matcher6.matches();
						
						if(!sheet.getCell(16,x).getContents().equals("")&&d==false){
							result[0]="ministorefalse";	
						}else if(!sheet.getCell(16,x).getContents().equals("")&&d==true){
							NumberCell check6=(NumberCell)sheet.getCell(16,x);//最低库存量	
							Matcher matcher4 = pattern2.matcher(check6.getContents());
							boolean f = matcher4.matches();
						    if(f==false&&result[0].equals("true")){
								result[0]="ministorefalse";	
						     }	
						  }else{
							  result[0]="mininull";
						  }
						 
						boolean b = true; 
						boolean c = true;
						
						//判断有无启用日期，若有，判断格式是否正确						
						Matcher matcher7 = pattern5.matcher(sheet.getCell(5,x).getContents());//启用日期	
						boolean h = matcher7.matches();						
						
						if(!sheet.getCell(5,x).equals("")&&h==false){
							result[0]="datefalse";
						}else if(!sheet.getCell(5,x).equals("")&&h==true){							
							DateCell check3=(DateCell)sheet.getCell(5,x);//启用日期
							Date date1 = check3.getDate();   //启用日期
														
							//判断格式是否正确
							Matcher matcher = pattern.matcher(format.format(date1));
							b = matcher.matches();
						}
						
                        //判断有无有效期，若有，判断格式是否正确
						Matcher matcher8 = pattern5.matcher(sheet.getCell(7,x).getContents());//启用日期	
						boolean i = matcher8.matches();	
						
						if(!sheet.getCell(7,x).equals("")&&i==false){
							result[0]="datefalse";
						}else if(sheet.getCell(7,x).equals("")){							
							c = false;
						}else if(!sheet.getCell(7,x).equals("")&&i==true){
							DateCell check5=(DateCell)sheet.getCell(7,x);//有效期
							Date date2 = check5.getDate();	 //有效期	
														
							//判断格式是否正确
							Matcher matcher1 = pattern.matcher(format.format(date2));
							c = matcher1.matches();	
						}
						
						
						Cell check7=sheet.getCell(15,x); //高质
						Cell check8=sheet.getCell(2,x);	//新旧
						Cell check9=sheet.getCell(18,x);	//厂商名称
						Cell check10=sheet.getCell(9,x);	//物资编码	
						Cell check11=sheet.getCell(11,x); //类别
						Cell check12=sheet.getCell(12,x); //名称
						Cell check13=sheet.getCell(1,x);	//状态	
						Cell check14=sheet.getCell(4,x); //保管人工号
						Cell check15=sheet.getCell(10,x);	//订货号						
						
						String indi_num = check1.getContents();
						String indi_hq = check7.getContents();
						String check_company = check9.getContents();
						String check_mnum = check10.getContents();						
						String indi_new = check8.getContents();	
						
						
						//库存地点是否为空
						Matcher matcher3 = pattern3.matcher(check2.getContents());											
						boolean e = matcher3.matches();
						
						
                       //判断excel文件中此个体编号是否重复
						for(int j=x+1;j < sheet.getRows(); j++){
							 Cell checkindi1=sheet.getCell(0,j);
							 String indi=checkindi1.getContents();
							 System.out.println(indi);
							 if(indi_num.equals(indi)){
								 result[0]="numfalse";
									break;
							 }						 
						 }						
						
						//判断个体编号、物资编码、厂商、名称不能为空，类别和状态必须为系统中的分类
						if(indi_num.equals("")){
							result[0]="indifalse";
							break;
						}else if(check_company.equals("")){
							result[0]="companyfalse";
							break;
						}else if(check_mnum.equals("")){
							result[0]="mnumfalse";
							break;
						}else if(check12.getContents().equals("")){
							result[0]="namefalse";
							break;
						}else if(check11.getContents().equals("")||(!check11.getContents().equals("千分尺")&&!check11.getContents().equals("卡尺")&&!check11.getContents().equals("量表")&&!check11.getContents().equals("角尺")&&!check11.getContents().equals("块规")&&!check11.getContents().equals("塞规环规")&&!check11.getContents().equals("量仪")&&!check11.getContents().equals("平板")&&!check11.getContents().equals("其他"))){
							result[0]="classfalse";
							break;
						}else if(check13.getContents().equals("")||(!check13.getContents().equals("借出")&&!check13.getContents().equals("在库")&&!check13.getContents().equals("送检"))){
							System.out.println(check13.getContents());
							result[0]="statusfalse";
							break;
						}
						
                        //判断高质量具是否有订货号
						if(check7.getContents().equals("是")){
							if(check15.getContents().equals("")){
							  result[0]="orderfalse";
							}
						}
						
						//判断保管人是否是系统中已存在的人
						if(check14.getContents().equals("")){
							result[0]="keeperfalse";
							break;
						}else{
							String []sqlkeeper = new String[1];
							sqlkeeper[0]="select * from t_user where stuff_num='"+check14.getContents()+"'";
							Logger.debug(sqlkeeper[0]);
							try {			
								DbUtil.execute(sqlkeeper[0],new IResultSetProcessor(){
									public void process(ResultSet rs) throws SQLException{
										if(rs.next()){					
										}else{
										  result[0]="keeperfalse";
										}
									}
								});	
							
							}catch (SQLException o){
								o.printStackTrace();
								result[0]="error";
							}
						}
						
												
						//判断excel文件中高质取值为“是”“否”
						for(int j=x+1;j < sheet.getRows(); j++){
							 Cell checkindi1=sheet.getCell(15,j);
							 String hq=checkindi1.getContents();
							 System.out.println(hq);
							 if(!indi_hq.equals("是")&&!indi_hq.equals("否")){
								 result[0]="hqfalse";
									break;
							 }						 
						 }
						
                       //判断excel文件中新旧取值为“新”“旧”
						for(int j=x+1;j < sheet.getRows(); j++){
							 Cell checkindi1=sheet.getCell(2,j);
							 String newstatus=checkindi1.getContents();
							 System.out.println(newstatus);
							 if(!indi_new.equals("新")&&!indi_new.equals("旧")){
								 result[0]="newfalse";
									break;
							 }						 
						 }
						
						//判断数据库中有无此个体编号
						 final String []count=new String[1];
						 String sql="select count(*) as ct from T_M_INDI where indi_num='"+indi_num+"'";			
							try {			
								DbUtil.execute(sql,new IResultSetProcessor(){
									public void process(ResultSet rs) throws SQLException{
										while(rs.next()){					
											count[0]=rs.getString("ct");													
											System.out.println("++++++++++++++++++"+count[0]);
											if(Integer.parseInt(count[0])!=0){
												result[0]="db_numfalse";
											}
										}
									}
								});	
							}catch (SQLException p){
								p.printStackTrace();
								result[0]="error";
							  }	
							
 						//出现错误即调出循环
						if((b==false||c==false)&&result[0].equals("true")){
							result[0]="datefalse";							
							break;
						}else if(result[0].equals("cyclefalse")){
							result[0]="cyclefalse";							
							break;							
						}else if(e==false&&result[0].equals("true")){
							result[0]="locationfalse";							
							break;							
						}else if(result[0].equals("ministorefalse")){
							result[0]="ministorefalse";							
							break;							
						}else if(result[0].equals("numfalse")){
							result[0]="numfalse";							
							break;							
						}else if(result[0].equals("db_numfalse")){
							result[0]="db_numfalse";							
							break;							
						}else if(result[0].equals("hqfalse")){
							result[0]="hqfalse";							
							break;							
						}else if(result[0].equals("newfalse")){
							result[0]="newfalse";							
							break;							
						}else if(result[0].equals("keeperfalse")){
							result[0]="keeperfalse";							
							break;							
						}else if(result[0].equals("orderfalse")){
							result[0]="orderfalse";							
							break;							
						}						
					}
					
					System.out.println(result[0]);
					System.out.println(result[0].equals("true"));
					//写入数据库
					if(result[0].equals("true")){
						for(int x = 1; x < sheet.getRows(); x++){							
							Cell cell1=sheet.getCell(0,x);	//个体编号
							Cell cell2=sheet.getCell(1,x);	//状态	
							Cell cell3=sheet.getCell(2,x);	//新旧
							Cell cell4=sheet.getCell(3,x); //库存地点
							Cell cell5=sheet.getCell(4,x); //保管人工号
							DateCell cell6=(DateCell)sheet.getCell(5,x);//启用日期						
							NumberCell cell7=(NumberCell)sheet.getCell(6,x); //鉴定周期						
							DateCell cell8=(DateCell)sheet.getCell(7,x);//有效期		
							Cell cell9=sheet.getCell(8,x);	//个体备注
							Cell cell10=sheet.getCell(9,x);	//物资编码	
							Cell cell11=sheet.getCell(10,x);	//订货号
							Cell cell12=sheet.getCell(11,x); //类别
							Cell cell13=sheet.getCell(12,x); //名称
							Cell cell14=sheet.getCell(13,x);	//规格	
							Cell cell15=sheet.getCell(14,x);	//分度值							
							Cell cell16=sheet.getCell(15,x); //高质
							NumberCell cell17=(NumberCell)sheet.getCell(16,x);//最低库存量
							Cell cell18=sheet.getCell(17,x);	//型号备注	
							Cell cell19=sheet.getCell(18,x);	//厂商名称
							Cell cell20=sheet.getCell(19,x); //厂商地址
							Cell cell21=sheet.getCell(20,x); //厂商联系人
							Cell cell22=sheet.getCell(21,x); //厂商电话
							Cell cell23=sheet.getCell(22,x); //厂商邮箱	
							Cell cell24=sheet.getCell(23,x); //厂商备注	
							
							final String []company=new String[6];
							company[0]=cell19.getContents();
							company[1]=cell20.getContents();
							company[2]=cell21.getContents();
							company[3]=cell22.getContents();
							company[4]=cell23.getContents();
							company[5]=cell24.getContents();
							
							Date indi_date1 = cell6.getDate();
							Date indi_date2 = cell8.getDate();	
							final String []type=new String[9];
							type[0]=cell10.getContents();//物资编码
							type[1]=cell11.getContents();//订货号
							type[2]=cell12.getContents();//类别
							type[3]=cell13.getContents();//名称
							type[4]=cell14.getContents();//规格
							type[5]=cell15.getContents();//分度值	
							type[6]=cell16.getContents();//高质
							type[7]=cell17.getContents();//最低库存量
							type[8]=cell18.getContents();//型号备注
							
							final String []indi_value=new String[9];
							indi_value[0]=cell1.getContents();//个体编号
							indi_value[1]=cell2.getContents();//状态
							indi_value[2]=cell3.getContents();//新旧
							indi_value[3]=cell4.getContents();//库存地点
							indi_value[4]=cell5.getContents();//保管人工号
							indi_value[5]=format.format(indi_date1);//启用日期
							indi_value[6]=cell7.getContents(); //鉴定周期	
							indi_value[7]=format.format(indi_date2);//有效期	
							indi_value[8]=cell9.getContents();//个体备注
							
							
                           //判断数据库中有无此厂商
							 final String []company_id=new String[1];							 
							 String sql1="select id from T_COMPANY where COMPANY='"+cell19.getContents()+"'";			
								try {			
									DbUtil.execute(sql1,new IResultSetProcessor(){
										public void process(ResultSet rs) throws SQLException{
											if(rs.next()){					
												//读取id存到类型表中
												company_id[0]=rs.getString("id");   //记录厂商id
												System.out.println("*************************"+company_id[0]);
											}else{											
											  try {					
											 	    final String id=UUID.randomUUID().toString();  //自动生成id
											 	    company_id[0]=id;  //记录厂商id
											 	   System.out.println("*************************"+company_id[0]);
													String []sqlcom = new String[1];
													sqlcom[0]= "insert into T_COMPANY(id,company,address,contact_person,phone,email,remark) values(?,?,?,?,?,?,?)";
													DbUtil.executeBatchs(sqlcom,
															new IArrayPreparedStatementProcessor() {
																public void process(
																PreparedStatement[] pstmts)
																throws SQLException {
																pstmts[0].setString(1,id);
															if(company[0]!=null){
																pstmts[0].setString(2,(String)company[0]);//userid
															}else{
																pstmts[0].setString(2," ");//userid
															}
															if(company[1]!=null){
																pstmts[0].setString(3,(String)company[1]);//username
															}else{
																pstmts[0].setString(3," ");//username
															}
															if(company[2]!=null){
																pstmts[0].setString(4,(String)company[2]);//pwd
															}else{
																pstmts[0].setString(4," ");//pwd
															}
															if(company[3]!=null){
																pstmts[0].setString(5,(String)company[3]);//email
															}else{
																pstmts[0].setString(5," ");//email
															}
															if(company[4]!=null){
																pstmts[0].setString(6,(String)company[4]);//mobilephone
															}else{
																pstmts[0].setString(6," ");//mobilephone
															}
															if(company[5]!=null){
																pstmts[0].setString(7,(String)company[5]);//telephone
															}else{
																pstmts[0].setString(7," ");//telephone
															}
															pstmts[0].addBatch();
															
														}
													});												
											}catch (SQLException e) {
												e.printStackTrace();
												result[0]="error";
											}
										 }
										}										
									});	
								}catch (SQLException p){
									p.printStackTrace();
									result[0]="error";
								  }				
						
                                //判断数据库中有无此类型
							if(result[0].equals("true")){
								final String []type_id=new String[1];
								String sql2="";
								String sql3="";
								type_id[0]="";
								if("否".equals(cell16.getContents())){
									sql2="select id from T_M_TYPE where mnum='"+cell10.getContents()+"' and companyid='"+company_id[0]+"' and hq='否'";			
									try {			
										DbUtil.execute(sql2,new IResultSetProcessor(){
											public void process(ResultSet rs) throws SQLException{
												if(rs.next()){					
													type_id[0]=rs.getString("id");
												}
											}
										});	
									 }catch (SQLException e){
										e.printStackTrace();
										result[0]="error";
									 }
									}else if("是".equals(cell16.getContents())){
										  sql3="select id from T_M_TYPE where order_num='"+cell11.getContents()+"'and companyid='"+company_id[0]+"' and hq='是'";			
											try {			
												DbUtil.execute(sql3,new IResultSetProcessor(){
													public void process(ResultSet rs) throws SQLException{
														while(rs.next()){					
															type_id[0]=rs.getString("id");
														}
													}
												});	
											}catch (SQLException e){
												e.printStackTrace();
												result[0]="error";
											  }
										  
									  }	
								  try {
									if (type_id[0].equals("")){	
										final String create_typeid=UUID.randomUUID().toString();  //自动生成id
										type_id[0]=create_typeid;
										String []sqltype = new String[1];
										sqltype[0]= "insert into T_M_TYPE(id,mnum,classname,name,spec,diviv,companyid,mini_qs,hq,remark,order_num,use_freq) values(?,?,?,?,?,?,?,?,?,?,?,?)";
										DbUtil.executeBatchs(sqltype,
												new IArrayPreparedStatementProcessor() {
													public void process(
													PreparedStatement[] pstmts)
													throws SQLException {
													pstmts[0].setString(1,create_typeid);
												if(type[0]!=null){
													pstmts[0].setString(2,(String)type[0]);//mnum
												}else{
													pstmts[0].setString(2," ");//mnum
												}
												if(type[2]!=null){
													pstmts[0].setString(3,(String)type[2]);//class
												}else{
													pstmts[0].setString(3," ");//class
												}
												if(type[3]!=null){
													pstmts[0].setString(4,(String)type[3]);//name
												}else{
													pstmts[0].setString(4," ");//name
												}
												if(type[4]!=null){
													pstmts[0].setString(5,(String)type[4]);//spec
												}else{
													pstmts[0].setString(5," ");//spec
												}
												if(type[5]!=null){
													pstmts[0].setString(6,(String)type[5]);//diviv
												}else{
													pstmts[0].setString(6," ");//diviv
												}
												if(company_id[0]!=null){
													pstmts[0].setString(7,(String)company_id[0]);//companyid
												}else{
													pstmts[0].setString(7," ");//company
												}
												if(type[7]!=null){
													pstmts[0].setString(8,(String)type[7]);//mini_qs
												}else{
													pstmts[0].setString(8," ");//mini_qs
												}
												if(type[6]!=null){
													pstmts[0].setString(9,(String)type[6]);//hq
												}else{
													pstmts[0].setString(9," ");//hq
												}
												if(type[8]!=null){
													pstmts[0].setString(10,(String)type[8]);//remark
												}else{
													pstmts[0].setString(10," ");//remark
												}
												if(type[1]!=null){
													pstmts[0].setString(11,(String)type[1]);//remark
												}else{
													pstmts[0].setString(11," ");//remark
												}
												pstmts[0].setInt(12,0);
												pstmts[0].addBatch();
												
											}
										});
									}
								}catch (SQLException e) {
									e.printStackTrace();
									result[0]="error";
								}
							
                                //向数据库中插入个体
							 if(result[0].equals("true")){	
								try {		
									final String new_indi_id=UUID.randomUUID().toString();  //自动生成id
									String[] sqlindi = new String[1];									
								    sqlindi[0] = "insert into  t_m_indi (indi_id,type_id,indi_num,newstatus,location,keeper,remark,startdate,valid,cycle,status) values (?,?,?,?,?,?,?,?,?,?,?)";//,startdate,valid,cycle
								    System.out.println(sqlindi[0]);
									DbUtil.executeBatchs(sqlindi,
											new IArrayPreparedStatementProcessor() {
												public void process(PreparedStatement[] pstmts)
														throws SQLException {
													pstmts[0].setString(1,new_indi_id);
													pstmts[0].setString(2, type_id[0]);
													pstmts[0].setString(3, indi_value[0]);
													pstmts[0].setString(4, indi_value[2]);
													pstmts[0].setString(5, indi_value[3]);
													pstmts[0].setString(6, indi_value[4]);
													pstmts[0].setString(7, indi_value[8]);
													pstmts[0].setString(8, indi_value[5]);
													pstmts[0].setString(9, indi_value[7]);
													pstmts[0].setString(10,indi_value[6]);
													pstmts[0].setString(11,indi_value[1]);
													pstmts[0].addBatch();
												}
											});									
									
								} catch (SQLException e) {
									e.printStackTrace();
									result[0]="error";
								}
							  }	
							}									
						}
					}
					book.close(); 
				}catch(Exception e) { 
					e.printStackTrace();
					result[0]="error";
				}
				System.out.println("+++++++++++++++++++++++++++"+result[0]); 
				return result[0];
		        
			}
			
}

		




