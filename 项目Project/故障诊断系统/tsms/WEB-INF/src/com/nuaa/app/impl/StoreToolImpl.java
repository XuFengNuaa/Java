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

import com.nuaa.app.StoreTool;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class StoreToolImpl implements StoreTool {
	
//	查询工人已申请的高质新非测量工具
	public JSONObject getQueryout(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_ct2hqapply where trim(status)='未处理' and "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from (select * from v_ct2hqapply where trim(status)='未处理' and "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from (select * from v_ct2hqapply where status='未处理' and "+filter+" order by "+order+") where rownum<="+start+") order by "+order; 
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
							obj.put("worker_num", rs.getString("worker_num"));
							obj.put("drawing_num", rs.getString("drawing_num"));
							obj.put("location",rs.getString("location"));
							obj.put("apply_num",rs.getString("apply_num"));
							obj.put("apply_time",rs.getDate("apply_time"));
							obj.put("nct_count_in",rs.getString("nct_count_in"));
							obj.put("gleader_approve", rs.getString("gleader_approve"));
							obj.put("leader_approve", rs.getString("leader_approve"));
							obj.put("realname", rs.getString("realname"));
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
	
//	普通借用时查询非测量工具
	public JSONObject getQuerygene(HashMap query_toolMap) {
		// TODO 自动生成方法存根
		HashMap query_toolHashMap=query_toolMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select  count(*) as ct  from  V_CT2COM where "+(String)query_toolMap.get("filter");
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
		sql="select * from (select * from  (select * from V_CT2COM where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from V_CT2COM where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
		
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("pclass", rs.getString("pclass"));
							obj.put("bclass", rs.getString("bclass"));
							obj.put("name",rs.getString("name"));
							obj.put("company", rs.getString("company"));								
							obj.put("hq", rs.getString("hq"));
							obj.put("location", rs.getString("location"));
							obj.put("remark",rs.getString("remark"));
							obj.put("spec", rs.getString("spec"));	
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("mini_qs", rs.getString("mini_qs"));	
							obj.put("order_num", rs.getString("order_num"));
							obj.put("nct_count_in", rs.getString("nct_count_in"));	
							obj.put("uct_count_in", rs.getString("uct_count_in"));	
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
	
//	查询工人已借用的非测量工具
	public JSONObject getQueryre(HashMap queryMap) {
		// TODO Auto-generated constructor stub
		HashMap queryHashMap=queryMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from v_ctbr2trecord where "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from (select * from v_ctbr2trecord where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from (select * from v_ctbr2trecord where "+filter+" order by "+order+") where rownum<="+start+") order by "+order;
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("hq", rs.getString("hq"));
							obj.put("spec", rs.getString("spec"));
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
							obj.put("uct_count_in",rs.getString("uct_count_in"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("mini_qs", rs.getString("mini_qs"));	
							obj.put("order_num", rs.getString("order_num"));
							obj.put("nct_count_in", rs.getString("nct_count_in"));
							obj.put("company", rs.getString("company"));
							obj.put("remark",rs.getString("remark"));
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
	
	
	//高质新非测量工具借出
	public boolean appOut(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String apply_num=(String)appcutHMap.get("apply_nums");
		final String mnum=(String)appcutHMap.get("mnums");
		final String use_freq=(String)appcutHMap.get("use_freq");
		final String finalcount=(String)appcutHMap.get("finalcounts");
		final String worker_num=(String)appcutHMap.get("worker_num");
		final String count=(String)appcutHMap.get("counts");
		final String id=(String)appcutHMap.get("ids");
		final String hq=(String)appcutHMap.get("hqs");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[4];
		try {
			sqls[0]="update t_hq_ct_apply_record set status=? where apply_num in('"+apply_num+"')";
			sqls[1]="update t_ct set nct_count_in=?,use_freq=? where id='"+id+"'";
			sqls[2]="insert into t_ct_br_record(apply_num,hq,worker_num,count,type_id,ot,time,br_num) values(?,?,?,?,?,?,?,?)";
			sqls[3]="insert into t_ct_br_trecord(worker_num,br_count,type_id,br_num) values(?,?,?,?)";
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
	
	
//	高质新非测量工具暂批
	public boolean temOut(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String apply_num=(String)appcutHMap.get("apply_nums");
		final String mnum=(String)appcutHMap.get("mnums");
		final String use_freq=(String)appcutHMap.get("use_freq");
		final String finalcount=(String)appcutHMap.get("finalcounts");
		final String worker_num=(String)appcutHMap.get("worker_num");
		final String count=(String)appcutHMap.get("counts");
		final String id=(String)appcutHMap.get("ids");
		final String hq=(String)appcutHMap.get("hqs");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[4];
		try {
			sqls[0]="update t_hq_ct_apply_record set status=?,ways=? where apply_num in('"+apply_num+"')";
			sqls[1]="update t_ct set nct_count_in=?,use_freq=? where id='"+id+"'";
			sqls[2]="insert into t_ct_br_record(apply_num,hq,worker_num,count,type_id,ot,time,br_num) values(?,?,?,?,?,?,?,?)";
			sqls[3]="insert into t_ct_br_trecord(worker_num,br_count,type_id,br_num) values(?,?,?,?)";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,"借出");
							pstmts[0].setString(2,"1");			//暂批标记
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
	
	
//	判断归还刀具时同一时间段内此工具是否被操作过
	public boolean checkReturn(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String br_num=(String)checkHMap.get("br_num");
		String br_count=(String)checkHMap.get("br_count");
		final int Count = Integer.parseInt(br_count);
		sql="select * from v_ctbr2trecord where br_count="+Count+" and br_num="+br_num;  
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
	
//	判断特殊借出时同一时间段内此工具是否存在刚被借出而导致数量不够
	public boolean checkhqCount(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String id="'"+(String)checkHMap.get("ids")+"'";
		String counts=(String)checkHMap.get("counts");
		final int Count = Integer.parseInt(counts);
		sql="select * from t_ct where id="+id;  
		Logger.debug(sql);
		final int[] count=new int[1];
		count[0]=1;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					while(rs.next()){		
						if(rs.getInt("nct_count_in")<Count){
							count[0] = 0;
						}
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
	
//	判断普通借出时同一时间段内此工具是否存在刚被借出而导致数量不够
	public boolean checkCount(HashMap checkHMap) {
		// TODO 自动生成方法存根
		String sql ="";	
		String id=(String)checkHMap.get("id");
		String uct_count=(String)checkHMap.get("uct_count");
		final int ut_count = Integer.parseInt(uct_count);
		String nct_count=(String)checkHMap.get("nct_count");
		final int nt_count = Integer.parseInt(nct_count);
		sql="select * from t_ct where id="+id;  
		Logger.debug(sql);
		final int[] count=new int[1];
		count[0]=1;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					while(rs.next()){		
						if("是".equals(rs.getString("hq"))){
							if(rs.getInt("uct_count_in")<ut_count){
								count[0] = 0;
							}
						}else{
							if(rs.getInt("uct_count_in")<ut_count||rs.getInt("nct_count_in")<nt_count){
								count[0] = 0;
							}
						}
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
	
//	核对工人工号与条形码
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
	
	//非测量工具的普通借出
	public boolean appneOut(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String hq=(String)appcutHMap.get("hq");
		final String nct_count_in=(String)appcutHMap.get("nct_count_in");
		final String use_freq=(String)appcutHMap.get("use_freq");
		final String uct_count_in=(String)appcutHMap.get("uct_count_in");
		final String worker_num=(String)appcutHMap.get("worker_num");
		final String br_count=(String)appcutHMap.get("br_count");
		final String id=(String)appcutHMap.get("id");
		final String id1=(String)appcutHMap.get("id1");
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String[] sqls=new String[3];
		try {
			sqls[0]="update t_ct set nct_count_in=?,uct_count_in=?,use_freq=? where id in("+id+")";
			sqls[1]="insert into t_ct_br_record(apply_num,hq,worker_num,count,type_id,ot,time,br_num) values(?,?,?,?,?,?,?,?)";
			sqls[2]="insert into t_ct_br_trecord(worker_num,br_count,type_id,br_num) values(?,?,?,?)";
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,nct_count_in);
							pstmts[0].setString(2,uct_count_in);
							pstmts[0].setString(3,use_freq);
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
	
	//工具归还
	public boolean appReturn(HashMap appcutHMap) {
		// TODO 自动生成方法存根
		final String hq=(String)appcutHMap.get("hq");
		
		final String apply_num=(String)appcutHMap.get("apply_num");
		
		final String id=(String)appcutHMap.get("id");
		
		final String id1=(String)appcutHMap.get("id1");
		
		final String reason=(String)appcutHMap.get("reason");
		
		final String br_num=(String)appcutHMap.get("br_num");
		
		final String worker_num=(String)appcutHMap.get("worker_num");
		final String charger=(String)appcutHMap.get("charger");
		
		final String uct_count_in=(String)appcutHMap.get("uct_count_in");
		
		final String lose_count=(String)appcutHMap.get("lose_count");		//丢失数量
		
		final String reg_count=(String)appcutHMap.get("reg_count");				//正常报废数量
		
		final String extra_count=(String)appcutHMap.get("extra_count");		//非正常报废数量	
		
		final String rest_count=(String)appcutHMap.get("rest_count");			//归还后剩余数量
		
		final String return_count=(String)appcutHMap.get("return_count");	//正常归还数量
		
		final int return_count1 = Integer.parseInt(return_count);
		
		final int rest_count1 = Integer.parseInt(rest_count);
		
		final int extra_count1 = Integer.parseInt(extra_count);
		
		final int reg_count1 = Integer.parseInt(reg_count);
		
		final int lose_count1 = Integer.parseInt(lose_count);
		
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		
		String[] sqls=new String[9];
		try {
			sqls[0]="update t_ct set uct_count_in=? where id in("+id+")";
			Logger.debug(sqls[0]);
			if(rest_count1!=0){
				sqls[1]="update t_ct_br_trecord set br_count=? where br_num in("+br_num+")";
				Logger.debug(sqls[1]);
			}else{
				sqls[1]="delete from t_ct_br_trecord where br_num="+br_num+"";
				Logger.debug(sqls[1]);
			}
			if(lose_count1!=0){
				sqls[2]="insert into t_ct_br_record(apply_num,hq,worker_num,count,type_id,ot,time,br_num) values(?,?,?,?,?,?,?,?)";
				Logger.debug(sqls[2]);
				sqls[3]="insert into t_ct_lt_apply_record(lt_num,pl_num,count,type_id,lt,apply_time,over,leader_approve,reason) values(?,?,?,?,?,?,?,?,?)";
				Logger.debug(sqls[3]);
			}else{
				sqls[2]="commit;";
				sqls[3]="commit;";
			}
			if(reg_count1!=0){
				sqls[4]="insert into t_ct_br_record(apply_num,hq,worker_num,count,type_id,ot,time,br_num) values(?,?,?,?,?,?,?,?)";
				Logger.debug(sqls[4]);
				sqls[5]="insert into t_ct_lt_apply_record(lt_num,pl_num,count,type_id,lt,apply_time,over,leader_approve,reason) values(?,?,?,?,?,?,?,?,?)";
				Logger.debug(sqls[5]);
			}else{
				sqls[4]="commit;";
				sqls[5]="commit;";
			}
			if(extra_count1!=0){
				sqls[6]="insert into t_ct_br_record(apply_num,hq,worker_num,count,type_id,ot,time,br_num) values(?,?,?,?,?,?,?,?)";
				Logger.debug(sqls[6]);
				sqls[7]="insert into t_ct_lt_apply_record(lt_num,pl_num,count,type_id,lt,apply_time,reason,over,leader_approve) values(?,?,?,?,?,?,?,?,?)";
				Logger.debug(sqls[7]);
			}else{
				sqls[6]="commit;";
				sqls[7]="commit;";
			}
			if(return_count1!=0){
				sqls[8]="insert into t_ct_br_record(apply_num,hq,worker_num,count,type_id,ot,time,br_num) values(?,?,?,?,?,?,?,?)";
				Logger.debug(sqls[8]);
			}else{
				sqls[8]="commit;";
			}
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {	
							pstmts[0].setString(1,uct_count_in);
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
								pstmts[2].setString(6,"3");			//丢失
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
								pstmts[4].setString(6,"1");			//正常报废
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
								pstmts[6].setString(6,"2");			//非正常报废
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
								pstmts[8].setString(6,"5");			//归还****‘4’为借出
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
	
	
	//拒绝特殊借出
	public boolean refOut(HashMap refcutHMap) {
		// TODO 自动生成方法存根
		final String apply_nums=(String)refcutHMap.get("apply_nums");
		String[] sqls=new String[1];
		try {
			sqls[0]="update t_hq_ct_apply_record set status=? where apply_num in("+apply_nums+")";
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
}
