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

import com.nuaa.app.StoreMeatool;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class StoreMeatoolImpl implements StoreMeatool {
	//高质新测量工具借出查询函数
		public JSONObject getQueryout(HashMap queryMap) {
			// TODO Auto-generated constructor stub
			HashMap queryHashMap=queryMap;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select count(*) as ct from v_mt2hqmmt where trim(state)='未处理' and "+(String)queryHashMap.get("filter");
			Logger.debug(sql);
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
			
			int start=Integer.parseInt((String)queryHashMap.get("start"));
			int limit=Integer.parseInt((String)queryHashMap.get("limit"));
			String filter=(String)queryHashMap.get("filter");
			String order=(String)queryHashMap.get("order");
			sql="select * from (select * from (select * from v_mt2hqmmt where trim(state)='未处理' and "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from (select * from v_mt2hqmmt where trim(state)='未处理' and "+filter+" order by "+order+") where rownum<="+start+") order by "+order; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("id", rs.getString("id"));
								obj.put("indi_id", rs.getString("indi_id"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("classname", rs.getString("classname"));
								obj.put("indi_num", rs.getString("indi_num"));
								obj.put("name", rs.getString("name"));
								obj.put("spec", rs.getString("spec"));
								obj.put("use_freq", rs.getString("use_freq"));
								obj.put("realname", rs.getString("realname"));
								obj.put("worker_num", rs.getString("worker_num"));
								obj.put("drawing_num", rs.getString("drawing_num"));
								obj.put("location",rs.getString("location"));
								obj.put("apply_num",rs.getString("apply_num"));
								obj.put("apply_time",rs.getDate("apply_time"));
								obj.put("status",rs.getString("status"));
								obj.put("gleader_approve", rs.getString("gleader_approve"));
								obj.put("group_num", rs.getString("group_num"));
								jsonArray.put(obj);
							}	
								jsonObj.put("filedata", jsonArray);
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
		
		
		
	//	普通测量工具借出查询
		public JSONObject getQuerygene(HashMap query_toolMap) {
			// TODO 自动生成方法存根
			HashMap query_toolHashMap=query_toolMap;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select  count(*) as ct  from  v_tmindi2v_type_com where ((trim(hq)='是' and trim(newstatus)='旧') or trim(hq)='否') and trim(status)='在库' and "+(String)query_toolMap.get("filter");
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
			int start=Integer.parseInt((String)query_toolHashMap.get("start"));
			int limit=Integer.parseInt((String)query_toolHashMap.get("limit"));
			
			String filter=(String)query_toolHashMap.get("filter");
			String order=(String)query_toolHashMap.get("order");        
			sql="select * from (select * from  (select * from v_tmindi2v_type_com where ((trim(hq)='是' and trim(newstatus)='旧') or trim(hq)='否') and trim(status)='在库' and "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from v_tmindi2v_type_com where ((trim(hq)='是' and trim(newstatus)='旧') or trim(hq)='否') and trim(status)='在库' and "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
			
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("type_id", rs.getString("type_id"));
								obj.put("indi_id", rs.getString("indi_id"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("indi_num", rs.getString("indi_num"));
								obj.put("classname", rs.getString("classname"));
								obj.put("name",rs.getString("name"));
								obj.put("diviv",rs.getString("diviv"));
								obj.put("company", rs.getString("company"));								
								obj.put("hq", rs.getString("hq"));
								obj.put("location", rs.getString("location"));
								obj.put("remark",rs.getString("remark"));
								obj.put("spec", rs.getString("spec"));	
								obj.put("use_freq", rs.getString("use_freq"));	
								obj.put("status", rs.getString("status"));	
								obj.put("newstatus", rs.getString("newstatus"));	
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
		
		
		//测量工具归还查询
		public JSONObject getQueryre(HashMap queryMap) {
			// TODO Auto-generated constructor stub
			HashMap queryHashMap=queryMap;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select count(*) as ct from v_mtbr2trecord where apply_num is not null and "+(String)queryHashMap.get("filter");
			Logger.debug(sql);
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
			
			int start=Integer.parseInt((String)queryHashMap.get("start"));
			int limit=Integer.parseInt((String)queryHashMap.get("limit"));
			String filter=(String)queryHashMap.get("filter");
			String order=(String)queryHashMap.get("order");
			sql="select * from (select * from (select * from v_mtbr2trecord where apply_num is not null and "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from (select * from v_mtbr2trecord where apply_num is not null and "+filter+" order by "+order+") where rownum<="+start+") order by "+order; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("id", rs.getString("id"));
								obj.put("hq", rs.getString("hq"));
								obj.put("indi_id", rs.getString("indi_id"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("classname", rs.getString("classname"));
								obj.put("indi_num", rs.getString("indi_num"));
								obj.put("name", rs.getString("name"));
								obj.put("spec", rs.getString("spec"));
								obj.put("worker_num", rs.getString("worker_num"));
								obj.put("location",rs.getString("location"));
								obj.put("apply_num",rs.getString("apply_num"));
								obj.put("time",rs.getDate("time"));
								obj.put("newstatus",rs.getString("newstatus"));
								obj.put("workname",rs.getString("workname"));
								obj.put("group_num",rs.getString("group_num"));
								jsonArray.put(obj);
							}	
								jsonObj.put("filedata", jsonArray);
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
		
		
		//测量工具明细查询
		public JSONObject detailMt(HashMap detail_m_indiHMap) {
			// TODO 自动生成方法存根
			HashMap detail_m_indiHashMap=detail_m_indiHMap;
			final JSONObject jsonObj=new JSONObject();
			final String indi_id=(String)detail_m_indiHashMap.get("indi_id");
			String sql="";
			sql="select * from v_tmindi2v_type_com where indi_id='"+indi_id+"'"; 

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
								jsonObj.put("hq", rs.getString("hq"));
								jsonObj.put("remark", rs.getString("remark"));
								jsonObj.put("status", rs.getString("status"));	
								jsonObj.put("order_num", rs.getString("order_num"));	
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
		
		
		//高质新测量工具借出批准
		public boolean appOut(HashMap appcutHMap) {
			// TODO 自动生成方法存根
			final String apply_nums=(String)appcutHMap.get("apply_nums");
//			final String [] apply_num=apply_nums.split(",");
			final String apply_num=(String)appcutHMap.get("apply_num");
			final String use_freq=(String)appcutHMap.get("use_freq");
			final String indi_id=(String)appcutHMap.get("indi_id");
			final String worker_nums=(String)appcutHMap.get("worker_num");
//			final String [] worker_num=worker_nums.split(",");
			final String indi_ids=(String)appcutHMap.get("indi_ids");
//			final String [] indi_id=indi_ids.split(",");
			final String id=(String)appcutHMap.get("id");
			Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String[] sqls=new String[4];
			try {
				sqls[0]="update t_hqmt_bapply set state=? where apply_num in("+apply_num+")";
				sqls[1]="update t_mt_indi set status=?,keeper=?,newstatus=?,apply_num=?,apply_state=?,apply_time=? where indi_id in("+indi_id+")";
				sqls[2]="insert into t_mt_br_record(apply_num,worker_num,indi_id,ot,time,br_num,hq) values(?,?,?,?,?,?,?)";
				sqls[3]="update t_mt_type set use_freq=? where id in("+id+")";
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
							public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,"借出");
								pstmts[0].addBatch();
//								for(int i=0;i<indi_id.length;i++){
									pstmts[1].setString(1,"借出");
									pstmts[1].setString(2,worker_nums);
									pstmts[1].setString(3,"旧");
									pstmts[1].setString(4,apply_nums);
									pstmts[1].setString(5,"未申请");
									pstmts[1].setString(6,"");
									pstmts[1].addBatch();
//								}
//								for(int j=0;j<indi_id.length;j++){
									String uuid = UUID.randomUUID().toString();
									pstmts[2].setString(1,apply_nums);
									pstmts[2].setString(2,worker_nums);
									pstmts[2].setString(3,indi_ids);
									pstmts[2].setString(4,"4");
									pstmts[2].setDate(5,curDate);
									pstmts[2].setString(6,uuid);
									pstmts[2].setString(7,"是");
									pstmts[2].addBatch();
//								}
								pstmts[3].setString(1,use_freq);
								pstmts[3].addBatch();
							}
				});
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
//		判断归还刀具时同一时间段内此量具是否被操作过
		public boolean checkReturn(HashMap checkHMap) {
			// TODO 自动生成方法存根
			String sql ="";	
			String apply_num=(String)checkHMap.get("apply_num");
			sql="select * from v_mtbr2trecord where apply_num="+apply_num;  
			Logger.debug(sql);
			final int[] count=new int[1];
			count[0]=0;
			try {			
				DbUtil.execute(sql,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException{
						while(rs.next()){		
								count[0] = 1;
						}
					}
				});	
				if (count[0]==1){	
					return true;
				}else{
					return false;
				}
			}catch (SQLException e){
				e.printStackTrace();
				return false;
			}
		}			
		
		//判断借出的同一段时间与空间内是否已被操作
		public boolean checkState(HashMap checkHMap) {
			// TODO 自动生成方法存根
			String sql ="";	
			String apply_num=(String)checkHMap.get("apply_num");
			sql="select * from v_mt2hqmmt where trim(state)='未处理' and apply_num="+apply_num;  
			Logger.debug(sql);
			final int[] count=new int[1];
			count[0]=0;
			try {			
				DbUtil.execute(sql,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException{
						while(rs.next()){					
							count[0]=1;
						}
					}
				});	
				if (count[0]==1){	
					return true;
				}else{
					return false;
				}
			}catch (SQLException e){
				e.printStackTrace();
				return false;
			}
		}
		
		//判断普通借出时同一时间段内测量工具是否刚被借出
		public boolean checkStatus(HashMap checkHMap) {
			// TODO 自动生成方法存根
			String sql ="";	
			String indi_id=(String)checkHMap.get("indi_ids");
			sql="select * from t_mt_indi where trim(status)='在库' and indi_id in "+indi_id;  
			Logger.debug(sql);
			final int[] count=new int[1];
			count[0]=0;
			try {			
				DbUtil.execute(sql,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException{
						while(rs.next()){					
							count[0]=1;
						}
					}
				});	
				if (count[0]==1){	
					return true;
				}else{
					return false;
				}
			}catch (SQLException e){
				e.printStackTrace();
				return false;
			}
		}
		
		//判断此工号与条形码的匹配
		public boolean checkNum(HashMap checknumHMap) {
			// TODO 自动生成方法存根
			String sql ="";	
			String stuff_num=(String)checknumHMap.get("worker_num");
			String order_num=(String)checknumHMap.get("order_num");
			sql="select * from t_user where stuff_num='"+stuff_num+"' and order_num='"+order_num+"'";
			Logger.debug(sql);
			final int[] count=new int[1];
			count[0]=0;
			try {			
				DbUtil.execute(sql,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException{
						while(rs.next()){					
							count[0]=1;
						}
					}
				});	
				if (count[0]==1){	
					return true;
				}else{
					return false;
				}
			}catch (SQLException e){
				e.printStackTrace();
				return false;
			}
		}
		
		
		//普通借出批准
		public boolean appneOut(HashMap appcutHMap) {
			// TODO 自动生成方法存根
			final String hq=(String)appcutHMap.get("hq");
			final String indi_id=(String)appcutHMap.get("indi_ids");
			final String use_freq=(String)appcutHMap.get("use_freq");
			final String indi_id1=(String)appcutHMap.get("indi_id1");
			final String worker_num=(String)appcutHMap.get("worker_num");
			final String type_id=(String)appcutHMap.get("type_id");
			Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String[] sqls=new String[3];
			try {
				sqls[0]="update t_mt_type set use_freq=? where id in("+type_id+")";
				sqls[1]="update t_mt_indi set status=?,keeper=?,newstatus=?,apply_num=? where indi_id in("+indi_id+")";
				sqls[2]="insert into t_mt_br_record(apply_num,hq,worker_num,indi_id,ot,time,br_num) values(?,?,?,?,?,?,?)";
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
							public void process(PreparedStatement[] pstmts)throws SQLException {	
								String uuid = UUID.randomUUID().toString();
								String uuid1 = UUID.randomUUID().toString();
								pstmts[0].setString(1,use_freq);
								pstmts[0].addBatch();
								pstmts[1].setString(1,"借出");
								pstmts[1].setString(2,worker_num);
								pstmts[1].setString(3, "旧");
								pstmts[1].setString(4,uuid);
								pstmts[1].addBatch();
								pstmts[2].setString(1,uuid);
								pstmts[2].setString(2,hq);
								pstmts[2].setString(3,worker_num);
								pstmts[2].setString(4,indi_id1);
								pstmts[2].setString(5,"4");
								pstmts[2].setDate(6,curDate);
								pstmts[2].setString(7,uuid1);
								pstmts[2].addBatch();
							}
				});						
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		}
		
		
		//正常归还
		public boolean appReturn(HashMap appcutHMap) {
			// TODO 自动生成方法存根
			final String apply_num=(String)appcutHMap.get("apply_num");
			final String apply_num1=(String)appcutHMap.get("apply_num1");
			final String worker_num=(String)appcutHMap.get("worker_num");
			final String indi_id=(String)appcutHMap.get("indi_id");
			final String hq=(String)appcutHMap.get("hq");
			Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String[] sqls=new String[2];
			try {
				sqls[0]="update t_mt_indi set status=?,keeper=?,apply_num=? where apply_num in("+apply_num+")";
				sqls[1]="insert into t_mt_br_record(apply_num,worker_num,indi_id,ot,time,br_num,hq) values(?,?,?,?,?,?,?)";
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
							public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,"在库");
								pstmts[0].setString(2,"store");
								pstmts[0].setString(3,"");
								pstmts[0].addBatch();
								String uuid = UUID.randomUUID().toString();
								pstmts[1].setString(1,apply_num1);
								pstmts[1].setString(2,worker_num);
								pstmts[1].setString(3,indi_id);
								pstmts[1].setString(4,"5");				//归还****‘4’为借出
								pstmts[1].setDate(5,curDate);
								pstmts[1].setString(6,uuid);
								pstmts[1].setString(7,hq);
								pstmts[1].addBatch();
							}
				});
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		
		//正常报废
		public boolean appScrap(HashMap appcutHMap) {
			// TODO 自动生成方法存根
			final String apply_num=(String)appcutHMap.get("apply_num");
			final String apply_num1=(String)appcutHMap.get("apply_num1");
			final String worker_num=(String)appcutHMap.get("worker_num");
			final String indi_id=(String)appcutHMap.get("indi_id");
			final String hq=(String)appcutHMap.get("hq");
			final String id=(String)appcutHMap.get("id");
			final String charger=(String)appcutHMap.get("charger");
			final String reason=(String)appcutHMap.get("reason");
			Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String[] sqls=new String[3];
			try {
				sqls[0]="update t_mt_indi set status=?,keeper=?,apply_num=? where apply_num in("+apply_num+")";
				sqls[1]="insert into t_mt_br_record(apply_num,worker_num,indi_id,ot,time,br_num,hq) values(?,?,?,?,?,?,?)";
				sqls[2]="insert into t_mt_lt_record(lt_num,liable_stuff_num,indi_id,ot,apply_time,type_id,over,leader_approve,reason) values(?,?,?,?,?,?,?,?,?)";
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
							public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,"报废");
								pstmts[0].setString(2,"store");
								pstmts[0].setString(3,"");
								pstmts[0].addBatch();
								
								String uuid = UUID.randomUUID().toString();
								pstmts[1].setString(1,apply_num1);
								pstmts[1].setString(2,charger);
								pstmts[1].setString(3,indi_id);
								pstmts[1].setString(4,"1");				//正常报废
								pstmts[1].setDate(5,curDate);
								pstmts[1].setString(6,uuid);
								pstmts[1].setString(7,hq);
								pstmts[1].addBatch();
								
								String uuid1 = UUID.randomUUID().toString();
								pstmts[2].setString(1,uuid1);
								pstmts[2].setString(2,charger);
								pstmts[2].setString(3,indi_id);
								pstmts[2].setString(4,"正常报废");
								pstmts[2].setDate(5,curDate);
								pstmts[2].setString(6,id);
								pstmts[2].setString(7,"未处理");
								pstmts[2].setString(8,"未处理");
								pstmts[2].setString(9,reason);
								pstmts[2].addBatch();
							}
				});
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		
		//非正常报废
		public boolean appIlle(HashMap appcutHMap) {
			// TODO 自动生成方法存根
			final String apply_num=(String)appcutHMap.get("apply_num");
			final String apply_num1=(String)appcutHMap.get("apply_num1");
			final String worker_num=(String)appcutHMap.get("worker_num");
			final String indi_id=(String)appcutHMap.get("indi_id");
			final String reason=(String)appcutHMap.get("reason");
			final String id=(String)appcutHMap.get("id");
			final String hq=(String)appcutHMap.get("hq");
			final String charger=(String)appcutHMap.get("charger");
			Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String[] sqls=new String[3];
			try {
				sqls[0]="update t_mt_indi set status=?,keeper=?,apply_num=? where apply_num in("+apply_num+")";
				sqls[1]="insert into t_mt_br_record(apply_num,worker_num,indi_id,ot,time,br_num,hq) values(?,?,?,?,?,?,?)";
				sqls[2]="insert into t_mt_lt_record(lt_num,liable_stuff_num,indi_id,ot,apply_time,reason,type_id,over,leader_approve) values(?,?,?,?,?,?,?,?,?)";
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
							public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,"报废");
								pstmts[0].setString(2,"store");
								pstmts[0].setString(3,"");
								pstmts[0].addBatch();
								
								String uuid = UUID.randomUUID().toString();
								pstmts[1].setString(1,apply_num1);
								pstmts[1].setString(2,charger);
								pstmts[1].setString(3,indi_id);
								pstmts[1].setString(4,"2");				//非正常报废
								pstmts[1].setDate(5,curDate);
								pstmts[1].setString(6,uuid);
								pstmts[1].setString(7,hq);
								pstmts[1].addBatch();
								
								String uuid1 = UUID.randomUUID().toString();
								pstmts[2].setString(1,uuid1);
								pstmts[2].setString(2,charger);
								pstmts[2].setString(3,indi_id);
								pstmts[2].setString(4,"非正常报废");
								pstmts[2].setDate(5,curDate);
								pstmts[2].setString(6,reason);
								pstmts[2].setString(7,id);
								pstmts[2].setString(8,"未处理");
								pstmts[2].setString(9,"未处理");
								pstmts[2].addBatch();
							}
				});
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		
	//丢失
		public boolean appLost(HashMap appcutHMap) {
			// TODO 自动生成方法存根
			final String apply_num=(String)appcutHMap.get("apply_num");
			final String apply_num1=(String)appcutHMap.get("apply_num1");
			final String worker_num=(String)appcutHMap.get("worker_num");
			final String indi_id=(String)appcutHMap.get("indi_id");
			final String id=(String)appcutHMap.get("id");
			final String hq=(String)appcutHMap.get("hq");
			final String charger=(String)appcutHMap.get("charger");
			final String reason=(String)appcutHMap.get("reason");
			Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String[] sqls=new String[3];
			try {
				sqls[0]="update t_mt_indi set status=?,keeper=?,apply_num=? where apply_num in("+apply_num+")";
				sqls[1]="insert into t_mt_br_record(apply_num,worker_num,indi_id,ot,time,br_num,hq) values(?,?,?,?,?,?,?)";
				sqls[2]="insert into t_mt_lt_record(lt_num,liable_stuff_num,indi_id,ot,apply_time,type_id,over,leader_approve,reason) values(?,?,?,?,?,?,?,?,?)";
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
							public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,"报废");
								pstmts[0].setString(2,"store");
								pstmts[0].setString(3,"");
								pstmts[0].addBatch();
								
								String uuid = UUID.randomUUID().toString();
								pstmts[1].setString(1,apply_num1);
								pstmts[1].setString(2,charger);
								pstmts[1].setString(3,indi_id);
								pstmts[1].setString(4,"3");					//丢失
								pstmts[1].setDate(5,curDate);
								pstmts[1].setString(6,uuid);
								pstmts[1].setString(7,hq);
								pstmts[1].addBatch();
								
								String uuid1 = UUID.randomUUID().toString();
								pstmts[2].setString(1,uuid1);
								pstmts[2].setString(2,charger);
								pstmts[2].setString(3,indi_id);
								pstmts[2].setString(4,"丢失");
								pstmts[2].setDate(5,curDate);
								pstmts[2].setString(6,id);
								pstmts[2].setString(7,"未处理");
								pstmts[2].setString(8,"未处理");
								pstmts[2].setString(9,reason);
								pstmts[2].addBatch();
							}
				});
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		
		//拒绝特殊借出时实时判断选择的量具是否有的已被处理
		public boolean checkFn(HashMap checkHMap) {
			// TODO 自动生成方法存根
			String sql ="";	
			String apply_nums=(String)checkHMap.get("apply_nums");
			String length=(String)checkHMap.get("length");
			final int lengths = Integer.parseInt(length);
			sql="select * from v_mt2hqmmt where trim(state)='未处理' and apply_num in ("+apply_nums+")";
			Logger.debug(sql);
			final int[] count=new int[1];
			count[0]=0;
			try {			
				DbUtil.execute(sql,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException{
						int wholecount = 0;
						while(rs.next()){					
							wholecount = wholecount+1;
						}
						if(lengths>wholecount){
							count[0] = 0;
						}else{
							count[0] = 1;
						}
					}
				});	
				if (count[0]==1){	
					return true;
				}else{
					return false;
				}
			}catch (SQLException e){
				e.printStackTrace();
				return false;
			}
		}
		
		
		//拒绝借出高质新测量工具
		public boolean refOut(HashMap refcutHMap) {
			// TODO 自动生成方法存根
			final String apply_nums=(String)refcutHMap.get("apply_nums");
			final String indi_ids=(String)refcutHMap.get("indi_ids");
			String[] sqls=new String[2];
			try {
				sqls[0]="update t_hqmt_bapply set state=? where apply_num in("+apply_nums+")";
				sqls[1]="update t_mt_indi set apply_state=?,apply_time=? where indi_id in("+indi_ids+")";
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
							public void process(PreparedStatement[] pstmts)throws SQLException {	
								pstmts[0].setString(1,"拒绝");
								pstmts[0].addBatch();
								pstmts[1].setString(1, "未申请");
								pstmts[1].setString(2, "");
								pstmts[1].addBatch();
							}
				});						
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		}
	
}
