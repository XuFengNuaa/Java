package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.StoreCut;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class StoreCutImpl implements StoreCut {
	
	//查询工人已申请的高质新刀具
	public JSONObject getQueryout(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_cut2hqapply where trim(status)='未处理' and "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from (select * from v_cut2hqapply where trim(status)='未处理' and "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from (select * from v_cut2hqapply where status='未处理' and "+filter+" order by "+order+") where rownum<="+start+") order by "+order;  
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("hq", rs.getString("hq"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("pclass", rs.getString("pclass"));
							obj.put("bclass", rs.getString("bclass"));
							obj.put("name", rs.getString("name"));
							obj.put("count", rs.getString("count"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("realname", rs.getString("realname"));
							obj.put("worker_num", rs.getString("worker_num"));
							obj.put("drawing_num", rs.getString("drawing_num"));
							obj.put("location",rs.getString("location"));
							obj.put("nc_count_in",rs.getString("nc_count_in"));
							obj.put("apply_num",rs.getString("apply_num"));
							obj.put("apply_time",rs.getDate("apply_time"));
							obj.put("gleader_approve", rs.getString("gleader_approve"));
							obj.put("leader_approve", rs.getString("leader_approve"));
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
	
//	查询工人已借用的刀具
	public JSONObject getQueryre(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_cutbr2trecord where "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from (select * from v_cutbr2trecord where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from (select * from v_cutbr2trecord where "+filter+" order by "+order+") where rownum<="+start+") order by "+order;  
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							final JSONObject obj_cutter=new JSONObject();
							String type_id = rs.getString("id");
							String sql1="select * from  V_CUTTER2COM where id='"+type_id+"'";										
							DbUtil.execute(sql1,new IResultSetProcessor() {
									public void process(ResultSet rs) throws SQLException {					
										try {
											while(rs.next()){
												DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();	
											//	decimalformat.applyPattern("###.##");
												decimalformat.setMaximumFractionDigits(2);
												
												obj_cutter.put("mnum", rs.getString("mnum")==null?"":rs.getString("mnum"));
												obj_cutter.put("iso_num", rs.getString("iso_num")==null?"":rs.getString("iso_num"));												
												obj_cutter.put("pclass", rs.getString("pclass"));
												obj_cutter.put("bclass", rs.getString("bclass"));
												obj_cutter.put("name",rs.getString("name")==null?"":rs.getString("name"));
												obj_cutter.put("company", rs.getString("company")==null?"":rs.getString("company"));								
												obj_cutter.put("hq", rs.getString("hq")==null?"":rs.getString("hq"));
												obj_cutter.put("rank_angle",rs.getString("rank_angle")==null?"":rs.getString("rank_angle"));
												obj_cutter.put("location", rs.getString("location")==null?"":rs.getString("location"));												
												obj_cutter.put("e_diam", rs.getString("e_diam")==null?"":rs.getString("e_diam"));							
												obj_cutter.put("h_diam",rs.getString("h_diam")==null?"":rs.getString("h_diam"));
												obj_cutter.put("e_leng",rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
												obj_cutter.put("t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));
												obj_cutter.put("tee_count",rs.getString("tee_count")==null?"":rs.getString("tee_count"));
												obj_cutter.put("s_ang", rs.getString("s_ang")==null?"":rs.getString("s_ang"));
												obj_cutter.put("tip_heig", rs.getString("tip_heig")==null ? "":decimalformat.format(rs.getFloat("tip_heig")));
												obj_cutter.put("bla_wide", rs.getString("bla_wide")==null ? "":decimalformat.format(rs.getFloat("bla_wide")));
												obj_cutter.put("c_leng", rs.getString("c_leng")==null ? "":decimalformat.format(rs.getFloat("c_leng")));
												obj_cutter.put("e_count",rs.getString("e_count")==null?"":rs.getString("e_count"));
												obj_cutter.put("e_wide", rs.getString("e_wide")==null ? "":decimalformat.format(rs.getFloat("e_wide")));
												obj_cutter.put("t_form", rs.getString("t_form")==null?"":rs.getString("t_form"));
												obj_cutter.put("c_way", rs.getString("c_way")==null?"":rs.getString("c_way"));
												obj_cutter.put("t_type", rs.getString("t_type")==null?"":rs.getString("t_type"));
												obj_cutter.put("spec", rs.getString("spec")==null?"":rs.getString("spec"));	
												obj_cutter.put("type", rs.getString("type")==null?"":rs.getString("type"));	
												obj_cutter.put("series_num", rs.getString("series_num")==null?"":rs.getString("series_num"));	
												obj_cutter.put("order_num", rs.getString("order_num")==null?"":rs.getString("order_num"));
												obj_cutter.put("effect_length", rs.getString("effect_length")==null ? "":decimalformat.format(rs.getFloat("effect_length")));	
												
											}																					
										} catch (JSONException e) {
											// TODO 自动生成 catch 块
											e.printStackTrace();
										}
									}				
								});								
							obj.put("id", type_id);							
							obj.put("time", rs.getDate("time"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("pclass", rs.getString("pclass"));
							obj.put("bclass", rs.getString("bclass"));
							obj.put("name", rs.getString("name"));
							obj.put("br_count", rs.getString("br_count"));
							obj.put("br_num", rs.getString("br_num"));
							obj.put("worker_num", rs.getString("worker_num"));
							obj.put("location",rs.getString("location"));
							obj.put("apply_num",rs.getString("apply_num"));							
							obj.put("workname",rs.getString("workname"));
							obj.put("group_num",rs.getString("group_num"));
							obj.put("uc_count_in", rs.getString("uc_count_in"));							
							obj.put("gc_count_in", rs.getString("gc_count_in"));
							obj.put("iso_num", obj_cutter.getString("iso_num"));	
					//		System.out.println("*****************"+obj.getString("iso_num"));
							obj.put("company", obj_cutter.getString("company"));								
							obj.put("hq", obj_cutter.getString("hq"));
							obj.put("rank_angle",obj_cutter.getString("rank_angle"));
							obj.put("location", obj_cutter.getString("location"));												
							obj.put("e_diam", obj_cutter.getString("e_diam"));							
							obj.put("h_diam",obj_cutter.getString("h_diam"));
							obj.put("e_leng",obj_cutter.getString("e_leng"));
							obj.put("t_leng", obj_cutter.getString("t_leng"));
							obj.put("tee_count",obj_cutter.getString("tee_count"));
							obj.put("s_ang", obj_cutter.getString("s_ang"));
							obj.put("tip_heig", obj_cutter.getString("tip_heig"));
							obj.put("bla_wide", obj_cutter.getString("bla_wide"));
							obj.put("c_leng", obj_cutter.getString("c_leng"));
							obj.put("e_count",obj_cutter.getString("e_count"));
							obj.put("e_wide", obj_cutter.getString("e_wide"));
							obj.put("t_form", obj_cutter.getString("t_form"));
							obj.put("c_way", obj_cutter.getString("c_way"));
							obj.put("t_type", obj_cutter.getString("t_type"));
							obj.put("spec", obj_cutter.getString("spec"));	
							obj.put("type", obj_cutter.getString("type"));	
							obj.put("series_num", obj_cutter.getString("series_num"));	
							obj.put("order_num", obj_cutter.getString("order_num"));
							obj.put("effect_length", obj_cutter.getString("effect_length"));	
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

	//普通借用时查询刀具
	  public JSONObject getQuerygene(HashMap query_cutterMap) {
			// TODO 自动生成方法存根
			HashMap query_cutterHashMap=query_cutterMap;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select  count(*) as ct  from  V_CUTTER2COM where "+(String)query_cutterMap.get("filter");
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
								obj.put("iso_num", rs.getString("iso_num"));
								obj.put("pclass", rs.getString("pclass"));
								obj.put("bclass", rs.getString("bclass"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));								
								obj.put("hq", rs.getString("hq"));
								obj.put("rank_angle",rs.getString("rank_angle"));
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
								obj.put("h_diam",rs.getString("h_diam"));
								obj.put("e_leng",rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
								obj.put("t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));
								obj.put("tee_count",rs.getString("tee_count"));
								obj.put("s_ang", rs.getString("s_ang"));
								obj.put("tip_heig", rs.getString("tip_heig")==null ? "":decimalformat.format(rs.getFloat("tip_heig")));
								obj.put("bla_wide", rs.getString("bla_wide")==null ? "":decimalformat.format(rs.getFloat("bla_wide")));
								obj.put("c_leng", rs.getString("c_leng")==null ? "":decimalformat.format(rs.getFloat("c_leng")));
								obj.put("e_count",rs.getString("e_count"));
								obj.put("e_wide", rs.getString("e_wide")==null ? "":decimalformat.format(rs.getFloat("e_wide")));
								obj.put("t_form", rs.getString("t_form"));
								obj.put("c_way", rs.getString("c_way"));
								obj.put("t_type", rs.getString("t_type"));
								obj.put("spec", rs.getString("spec"));	
								obj.put("type", rs.getString("type"));	
								obj.put("series_num", rs.getString("series_num"));	
								obj.put("order_num", rs.getString("order_num"));
								obj.put("effect_length", rs.getString("effect_length")==null ? "":decimalformat.format(rs.getFloat("effect_length")));								
								obj.put("nc_count_in", rs.getString("nc_count_in"));	
								obj.put("uc_count_in", rs.getString("uc_count_in"));	
								obj.put("gc_count_in", rs.getString("gc_count_in"));
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
		 	
	//高质新刀具借出
	public boolean appOut(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String hq=(String)appcutHMap.get("hqs");
		final String apply_num=(String)appcutHMap.get("apply_nums");
		final String mnum=(String)appcutHMap.get("mnums");
		final String use_freq=(String)appcutHMap.get("use_freq");
		final String finalcount=(String)appcutHMap.get("finalcounts");
		final String worker_num=(String)appcutHMap.get("worker_num");
		final String count=(String)appcutHMap.get("counts");
		final String id=(String)appcutHMap.get("ids");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[4];
		try {
			sqls[0]="update t_hq_c_apply_record set status=? where apply_num in('"+apply_num+"')";
			sqls[1]="update t_cutter set nc_count_in=?,use_freq=? where id='"+id+"'";
			sqls[2]="insert into t_c_br_record(apply_num,hq,worker_num,count,type_id,ot,time,br_num) values(?,?,?,?,?,?,?,?)";
			sqls[3]="insert into t_c_br_trecord(worker_num,br_count,type_id,br_num) values(?,?,?,?)";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,"借出");
							pstmts[0].addBatch();
							pstmts[1].setString(1,finalcount);
							pstmts[1].setString(2,use_freq);
							pstmts[1].addBatch();
							String uuid = UUID.randomUUID().toString();
							pstmts[2].setString(1,apply_num);
							pstmts[2].setString(2,hq);
							pstmts[2].setString(3,worker_num);
							pstmts[2].setString(4,count);
							pstmts[2].setString(5,id);
							pstmts[2].setString(6,"4");
							pstmts[2].setDate(7,curDate);
							pstmts[2].setString(8,uuid);
							pstmts[2].addBatch();
							pstmts[3].setString(1,worker_num);
							pstmts[3].setString(2,count);
							pstmts[3].setString(3,id);
							pstmts[3].setString(4,uuid);
							pstmts[3].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	
//	高质新刀具暂批
	public boolean temOut(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String hq=(String)appcutHMap.get("hqs");
		final String apply_num=(String)appcutHMap.get("apply_nums");
		final String mnum=(String)appcutHMap.get("mnums");
		final String use_freq=(String)appcutHMap.get("use_freq");
		final String finalcount=(String)appcutHMap.get("finalcounts");
		final String worker_num=(String)appcutHMap.get("worker_num");
		final String count=(String)appcutHMap.get("counts");
		final String id=(String)appcutHMap.get("ids");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[4];
		try {
			sqls[0]="update t_hq_c_apply_record set status=?,ways=? where apply_num in('"+apply_num+"')";
			sqls[1]="update t_cutter set nc_count_in=?,use_freq=? where id='"+id+"'";
			sqls[2]="insert into t_c_br_record(apply_num,hq,worker_num,count,type_id,ot,time,br_num) values(?,?,?,?,?,?,?,?)";
			sqls[3]="insert into t_c_br_trecord(worker_num,br_count,type_id,br_num) values(?,?,?,?)";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,"借出");
							pstmts[0].setString(2,"1");			//	"1"表示暂批方式
							pstmts[0].addBatch();
							pstmts[1].setString(1,finalcount);
							pstmts[1].setString(2,use_freq);
							pstmts[1].addBatch();
							String uuid = UUID.randomUUID().toString();
							pstmts[2].setString(1,apply_num);
							pstmts[2].setString(2,hq);
							pstmts[2].setString(3,worker_num);
							pstmts[2].setString(4,count);
							pstmts[2].setString(5,id);
							pstmts[2].setString(6,"4");
							pstmts[2].setDate(7,curDate);
							pstmts[2].setString(8,uuid);
							pstmts[2].addBatch();
							pstmts[3].setString(1,worker_num);
							pstmts[3].setString(2,count);
							pstmts[3].setString(3,id);
							pstmts[3].setString(4,uuid);
							pstmts[3].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	
	//刀具归还
	public boolean appReturn(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String hq=(String)appcutHMap.get("hq");
		
		final String ok=(String)appcutHMap.get("ok");
		
		final String apply_num=(String)appcutHMap.get("apply_num");
		
		final String id=(String)appcutHMap.get("id");
		
		final String id1=(String)appcutHMap.get("id1");
		
		final String reason=(String)appcutHMap.get("reason");
		
		final String br_num=(String)appcutHMap.get("br_num");
		
		final String worker_num=(String)appcutHMap.get("worker_num");
		
		final String charger=(String)appcutHMap.get("charger");
		
		final String uc_count_in=(String)appcutHMap.get("uc_count_in");
		
		final String lose_count=(String)appcutHMap.get("lose_count");		//丢失数量
		
		final String reg_count=(String)appcutHMap.get("reg_count");				//正常报废数量
		
		final String extra_count=(String)appcutHMap.get("extra_count");		//非正常报废数量
		
		final String rest_count=(String)appcutHMap.get("rest_count");			//归还后剩余数量
		
		final String return_count=(String)appcutHMap.get("return_count");			//正常归还数量
		
		final int return_count1 = Integer.parseInt(return_count);
		
		final int rest_count1 = Integer.parseInt(rest_count);
		
		final int extra_count1 = Integer.parseInt(extra_count);
		
		final int reg_count1 = Integer.parseInt(reg_count);
		
		final int lose_count1 = Integer.parseInt(lose_count);
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[9];
		try {
			if(ok.equals("true")){
				sqls[0]="update t_cutter set gc_count_in=? where id in("+id+")";
			}else{
				sqls[0]="update t_cutter set uc_count_in=? where id in("+id+")";
			}
			Logger.debug(sqls[0]);
			if(rest_count1!=0){
				sqls[1]="update t_c_br_trecord set br_count=? where br_num in("+br_num+")";
				Logger.debug(sqls[1]);
			}else{
				sqls[1]="delete from t_c_br_trecord where br_num="+br_num+"";
				Logger.debug(sqls[1]);
			}
			if(lose_count1!=0){
				sqls[2]="insert into t_c_br_record(apply_num,hq,worker_num,count,type_id,ot,time,br_num) values(?,?,?,?,?,?,?,?)";
				Logger.debug(sqls[2]);
				sqls[3]="insert into t_c_lt_apply_record(lt_num,pl_num,count,type_id,lt,apply_time,over,leader_approve,reason) values(?,?,?,?,?,?,?,?,?)";
				Logger.debug(sqls[3]);
			}else{
				sqls[2]="commit;";
				sqls[3]="commit;";
			}
			if(reg_count1!=0){
				sqls[4]="insert into t_c_br_record(apply_num,hq,worker_num,count,type_id,ot,time,br_num) values(?,?,?,?,?,?,?,?)";
				Logger.debug(sqls[4]);
				sqls[5]="insert into t_c_lt_apply_record(lt_num,pl_num,count,type_id,lt,apply_time,over,leader_approve,reason) values(?,?,?,?,?,?,?,?,?)";
				Logger.debug(sqls[5]);
			}else{
				sqls[4]="commit;";
				sqls[5]="commit;";
			}
			if(extra_count1!=0){
				sqls[6]="insert into t_c_br_record(apply_num,hq,worker_num,count,type_id,ot,time,br_num) values(?,?,?,?,?,?,?,?)";
				Logger.debug(sqls[6]);
				sqls[7]="insert into t_c_lt_apply_record(lt_num,pl_num,count,type_id,lt,apply_time,reason,over,leader_approve) values(?,?,?,?,?,?,?,?,?)";
				Logger.debug(sqls[7]);
			}else{
				sqls[6]="commit;";
				sqls[7]="commit;";
			}
			if(return_count1!=0){
				sqls[8]="insert into t_c_br_record(apply_num,hq,worker_num,count,type_id,ot,time,br_num) values(?,?,?,?,?,?,?,?)";
				Logger.debug(sqls[8]);
			}else{
				sqls[8]="commit;";
			}
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,uc_count_in);
							pstmts[0].addBatch();
							if(rest_count1!=0){
								pstmts[1].setString(1,rest_count);
								pstmts[1].addBatch();
							}else{
								pstmts[1].addBatch();
							}
							if(lose_count1!=0){
								String uuid = UUID.randomUUID().toString();
								String uuid1 = UUID.randomUUID().toString();
								pstmts[2].setString(1,apply_num);
								pstmts[2].setString(2,hq);
								pstmts[2].setString(3,charger);
								pstmts[2].setString(4,lose_count);
								pstmts[2].setString(5,id1);
								pstmts[2].setString(6,"3");				//丢失
								pstmts[2].setDate(7,curDate);
								pstmts[2].setString(8,uuid);
								pstmts[2].addBatch();
								
								pstmts[3].setString(1,uuid1);
								pstmts[3].setString(2,charger);
								pstmts[3].setString(3,lose_count);
								pstmts[3].setString(4,id1);
								pstmts[3].setString(5,"丢失");
								pstmts[3].setDate(6,curDate);
								pstmts[3].setString(7,"未处理");
								pstmts[3].setString(8,"未处理");
								pstmts[3].setString(9,reason);
								pstmts[3].addBatch();
							}
							if(reg_count1!=0){
								String uuid = UUID.randomUUID().toString();
								String uuid1 = UUID.randomUUID().toString();
								pstmts[4].setString(1,apply_num);
								pstmts[4].setString(2,hq);
								pstmts[4].setString(3,charger);
								pstmts[4].setString(4,reg_count);
								pstmts[4].setString(5,id1);
								pstmts[4].setString(6,"1");				//正常报废
								pstmts[4].setDate(7,curDate);
								pstmts[4].setString(8,uuid);
								pstmts[4].addBatch();
								
								pstmts[5].setString(1,uuid1);
								pstmts[5].setString(2,charger);
								pstmts[5].setString(3,reg_count);
								pstmts[5].setString(4,id1);
								pstmts[5].setString(5,"正常报废");
								pstmts[5].setDate(6,curDate);
								pstmts[5].setString(7,"未处理");
								pstmts[5].setString(8,"未处理");
								pstmts[5].setString(9,reason);
								pstmts[5].addBatch();
							}
							if(extra_count1!=0){
								String uuid = UUID.randomUUID().toString();
								String uuid1 = UUID.randomUUID().toString();
								pstmts[6].setString(1,apply_num);
								pstmts[6].setString(2,hq);
								pstmts[6].setString(3,charger);
								pstmts[6].setString(4,extra_count);
								pstmts[6].setString(5,id1);
								pstmts[6].setString(6,"2");					//非正常报废
								pstmts[6].setDate(7,curDate);
								pstmts[6].setString(8,uuid);
								pstmts[6].addBatch();
								
								pstmts[7].setString(1,uuid1);
								pstmts[7].setString(2,charger);
								pstmts[7].setString(3,extra_count);
								pstmts[7].setString(4,id1);
								pstmts[7].setString(5,"非正常报废");
								pstmts[7].setDate(6,curDate);
								pstmts[7].setString(7,reason);
								pstmts[7].setString(8,"未处理");
								pstmts[7].setString(9,"未处理");
								pstmts[7].addBatch();
							}
							if(return_count1!=0){
								String uuid = UUID.randomUUID().toString();
								pstmts[8].setString(1,apply_num);
								pstmts[8].setString(2,hq);
								pstmts[8].setString(3,worker_num);
								pstmts[8].setString(4,return_count);
								pstmts[8].setString(5,id1);
								pstmts[8].setString(6,"5");					//归还****‘4’为借出
								pstmts[8].setDate(7,curDate);
								pstmts[8].setString(8,uuid);
								pstmts[8].addBatch();
							}
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	//普通刀具借出
	public boolean appneOut(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String hq=(String)appcutHMap.get("hq");
		final String nc_count_in=(String)appcutHMap.get("nc_count_in");
		final String use_freq=(String)appcutHMap.get("use_freq");
		final String uc_count_in=(String)appcutHMap.get("uc_count_in");
		final String worker_num=(String)appcutHMap.get("worker_num");
		final String gc_count_in=(String)appcutHMap.get("gc_count_in");
		final String br_count=(String)appcutHMap.get("br_count");
		final String id=(String)appcutHMap.get("id");
		final String id1=(String)appcutHMap.get("id1");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[3];
		try {
			sqls[0]="update t_cutter set nc_count_in=?,uc_count_in=?,gc_count_in=?,use_freq=? where id in("+id+")";
			sqls[1]="insert into t_c_br_record(apply_num,hq,worker_num,count,type_id,ot,time,br_num) values(?,?,?,?,?,?,?,?)";
			sqls[2]="insert into t_c_br_trecord(worker_num,br_count,type_id,br_num) values(?,?,?,?)";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,nc_count_in);
							pstmts[0].setString(2,uc_count_in);
							pstmts[0].setString(3,gc_count_in);
							pstmts[0].setString(4,use_freq);
							pstmts[0].addBatch();
							String uuid = UUID.randomUUID().toString();
							String uuid1 = UUID.randomUUID().toString();
							pstmts[1].setString(1,uuid);
							pstmts[1].setString(2,hq);
							pstmts[1].setString(3,worker_num);
							pstmts[1].setString(4,br_count);
							pstmts[1].setString(5,id1);
							pstmts[1].setString(6,"4");
							pstmts[1].setDate(7,curDate);
							pstmts[1].setString(8,uuid1);
							pstmts[1].addBatch();
							pstmts[2].setString(1,worker_num);
							pstmts[2].setString(2,br_count);
							pstmts[2].setString(3,id1);
							pstmts[2].setString(4,uuid1);
							pstmts[2].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
//	判断归还刀具时同一时间段内此刀具是否被操作过
	public boolean checkReturn(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String br_num=(String)checkHMap.get("br_num");
		String br_count=(String)checkHMap.get("br_count");
		final int Count = Integer.parseInt(br_count);
		sql="select * from t_c_br_trecord where br_count="+Count+" and br_num="+br_num;  
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
	
//	判断特殊借出时同一时间段内此刀具是否存在刚被借出而导致数量不够
	public String checkhqCount(HashMap checkHMap) {
		// TODO 自动生成方法存根
		final String []result = new String[1];
		result[0] = "true";
		String sql ="";	
		String id="'"+(String)checkHMap.get("ids")+"'";
		String counts=(String)checkHMap.get("counts");
		final int Count = Integer.parseInt(counts);
		sql="select * from t_cutter where id="+id;  
		Logger.debug(sql);
		final int[] count=new int[1];
		count[0]=1;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					while(rs.next()){		
						if(rs.getInt("nc_count_in")<Count){
							count[0] = 0;
						}
					}
				}
			});	
			System.out.println("count[0]=="+count[0]);
			if (count[0]==1){	
				result[0] = "true";
			}else{
				result[0] = "hq_little";
			}
		}catch (SQLException e){
			e.printStackTrace();
			result[0] = "faliure";
		}		
		return result[0];
	}	
	
	
	
//	判断普通借出时同一时间段内此刀具是否存在刚被借出而导致数量不够
	public String checkCount(HashMap checkHMap) {
		// TODO 自动生成方法存根
		final String []result = new String[1];
		result[0] = "true";
		String sql ="";	
		String id=(String)checkHMap.get("id");
		String gc_count=(String)checkHMap.get("gc_count");
		final int g_count = Integer.parseInt(gc_count);
		String uc_count=(String)checkHMap.get("uc_count");
		final int u_count = Integer.parseInt(uc_count);
		String nc_count=(String)checkHMap.get("nc_count");
		final int n_count = Integer.parseInt(nc_count);
		sql="select * from t_cutter where id="+id;  
		Logger.debug(sql);
		final int[] count=new int[1];
		count[0]=1;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					while(rs.next()){		
						if("刃磨立铣刀".equals(rs.getString("bclass"))){
							if(rs.getInt("gc_count_in")<g_count){
								count[0] = 0;
							}
						}else if("是".equals(rs.getString("hq"))){
							if(rs.getInt("uc_count_in")<u_count){
								count[0] = 0;
							}
						}else{
							if(rs.getInt("uc_count_in")<u_count||rs.getInt("nc_count_in")<n_count){
								count[0] = 0;
							}
						}
					}
				}
			});	
			if (count[0]==1){	
				result[0] = "true";
			}else{
				result[0] = "little";
			}
		}catch (SQLException e){
			e.printStackTrace();
			result[0] = "faliure";
		}
		return result[0];
	}
	
	//核对工人工号与条形码
	public String checkNum(HashMap checknumHMap) {
		// TODO 自动生成方法存根
		final String []result = new String[1];
		result[0] = "true";
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
			System.out.println("count[0]=="+count[0]);
			if (count[0]==1){	
				result[0] = "true";
			}else{
				result[0] = "none";
			}
		}catch (SQLException e){
			e.printStackTrace();
			result[0] = "faliure";
		}
		return result[0];
	}
	
	//拒绝特殊借出
	public boolean refOut(HashMap refcutHMap) {
		// TODO 自动生成方法存根
		final String apply_nums=(String)refcutHMap.get("apply_nums");
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_hq_c_apply_record set status=? where apply_num in("+apply_nums+")";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,"拒绝");
							pstmts[0].addBatch();
						}
			});						
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	//刀具明细
	public JSONObject viewCut(HashMap viewusrMap) {
		// TODO 自动生成方法存根
		HashMap viewusrHashMap=viewusrMap;
		final JSONObject jsonObj=new JSONObject();
		final String id=(String)viewusrHashMap.get("id");
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
							jsonObj.put("iso_num", rs.getString("iso_num"));
							jsonObj.put("name",rs.getString("name"));
							jsonObj.put("nc_count_in", rs.getString("nc_count_in"));
							jsonObj.put("uc_count_in", rs.getString("uc_count_in"));
							jsonObj.put("gc_count_in", rs.getString("gc_count_in"));
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
							jsonObj.put("tee_count", rs.getString("tee_count"));
							jsonObj.put("s_ang", rs.getString("s_ang"));
							jsonObj.put("bla_wide", rs.getString("bla_wide")==null ? "":decimalformat.format(rs.getFloat("bla_wide")));
							jsonObj.put("tip_heig", rs.getString("tip_heig")==null ? "":decimalformat.format(rs.getFloat("tip_heig")));
							jsonObj.put("c_leng", rs.getString("c_leng")==null ? "":decimalformat.format(rs.getFloat("c_leng")));
							jsonObj.put("e_count",rs.getString("e_count"));
							jsonObj.put("t_form", rs.getString("t_form"));
							jsonObj.put("c_way", rs.getString("c_way"));
							jsonObj.put("t_type", rs.getString("t_type"));
							jsonObj.put("spec", rs.getString("spec"));
							jsonObj.put("remark", rs.getString("remark"));
							jsonObj.put("type", rs.getString("type"));
							jsonObj.put("series_num", rs.getString("series_num"));
							jsonObj.put("order_num", rs.getString("order_num"));						
							jsonObj.put("hq", rs.getString("hq"));
							jsonObj.put("companyid",rs.getString("companyid"));
							jsonObj.put("company",rs.getString("company"));
							jsonObj.put("location", rs.getString("location"));	
							jsonObj.put("effect_length", rs.getString("effect_length")==null ? "":decimalformat.format(rs.getFloat("effect_length")));	
							jsonObj.put("e_wide", rs.getString("e_wide")==null ? "":decimalformat.format(rs.getFloat("e_wide")));							
														
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
	
	//清零
	public String ClearCut() {
		// TODO 自动生成方法存根		
		String[] sqls=new String[1];
		String result = "success";
		try {
			sqls[0]="update t_cutter set NC_COUNT_IN=0";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {							
							pstmts[0].addBatch();
						}
			});	
		} catch (Exception e) {
			e.printStackTrace();
			result = "false";
		}	
		return result;
	}
	
	//规整
	public String TrueCut() {
		// TODO 自动生成方法存根		
		final String []result = new String[1];
		result[0] = "success";
		String sql = "select type_id,count from t_c_br_record where ot='4'";	
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								final String br_type_id = rs.getString("type_id");
								final int br_count = Integer.parseInt(rs.getString("count"));
								final String[] sqls=new String[1];
								
								//判断借用数量是否大于在库量
								String sql1 = "select NC_COUNT_IN from t_cutter where id='"+br_type_id+"'";	
								try {			
									DbUtil.execute(sql1,new IResultSetProcessor(){
										public void process(ResultSet rs) throws SQLException{
											while(rs.next()){		
												if(rs.getInt("nc_count_in")<br_count){
													try {
														sqls[0]="update t_cutter set NC_COUNT_IN=0 where id='"+br_type_id+"'";
														DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
																	public void process(PreparedStatement[] pstmts)throws SQLException {							
																		pstmts[0].addBatch();
																	}
														});	
													} catch (Exception e) {
														e.printStackTrace();
														result[0] = "false";
													}
												}else{													
													try {
														sqls[0]="update t_cutter set NC_COUNT_IN=NC_COUNT_IN-'"+br_count+"' where id='"+br_type_id+"'";
														DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
																	public void process(PreparedStatement[] pstmts)throws SQLException {							
																		pstmts[0].addBatch();
																	}
														});	
													} catch (Exception e) {
														e.printStackTrace();
														result[0] = "false";
													}
												}
											}
										}
									});										
								}catch (SQLException e){
									e.printStackTrace();
									result[0] = "false";
								}								
							}	
						} catch (SQLException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
							result[0] = "false";
						}
					}				
				});	
			}catch (SQLException e) {
			// TODO 自动生成 catch 块
				e.printStackTrace();
				result[0] = "false";
			}
			return result[0] ;
	}
}
