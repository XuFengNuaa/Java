package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.Model_Out;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class Model_OutImpl implements Model_Out{

	public JSONObject queryAllModel(HashMap hashmap) {
		HashMap queryHashMap=hashmap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct  from  t_model_in_record where "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from t_model_in_record where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from t_model_in_record where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							final JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("modelnum", rs.getString("modelnum"));
							String modelnum = rs.getString("modelnum");
							String id = rs.getString("id");
							String sql1 = "select name from t_model where modelnum = '" +modelnum+"'";
							DbUtil.execute(sql1,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									while(rs.next()){
										try {
											obj.put("name", rs.getString("name"));
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									
								}
								
							});
							//计算入库量
							String sql = "select incount from t_model_in_record where id = '" +id + "'";
							DbUtil.execute(sql,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									int incount = 0;
									while(rs.next()){
										incount+=rs.getInt("incount");
									}
									try {
										obj.put("incount",incount );
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								
							});
							//计算借出量
							String sql2 = "select outcount from t_model_br_record where inid = '" +id + "'";
							DbUtil.execute(sql2,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									int outcount = 0;
									while(rs.next()){
										outcount+=rs.getInt("outcount");
									}
									try {
										obj.put("outcount", outcount);
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
							//计算归还量
							String sql3 = "select returncount from t_model_re_record where outid = '" +id + "'";
							DbUtil.execute(sql3,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									int returncount = 0;
									while(rs.next()){
										returncount+=rs.getInt("returncount");
									}
									try {
										obj.put("returncount", returncount);
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
							
							int nowcount = obj.getInt("incount") - obj.getInt("outcount") + obj.getInt("returncount");
							
							obj.put("nowcount", nowcount);
							
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

	@Override
	public String model_Out(final HashMap hashmap) {
		final String inid = (String) hashmap.get("inid");
		final String modelnum = (String) hashmap.get("modelnum");
		final String outdate = (String) hashmap.get("outdate");
		final String outcount = (String) hashmap.get("outcount");
		final String getperson = (String) hashmap.get("getperson");
		final String id = UUID.randomUUID().toString();
		String []sqls = new String[1];
		sqls[0]= "insert into T_MODEL_BR_RECORD(id,inid,modelnum,outcount,outdate,getperson) values(?,?,?,?,to_date(?,'yyyy/mm/dd'),?)";
		try {
			DbUtil.executeBatchs(sqls,
					new IArrayPreparedStatementProcessor() {
						public void process(
						PreparedStatement[] pstmts)
						throws SQLException {
						pstmts[0].setString(1,id);
					if(hashmap.get("inid")!=null){
						pstmts[0].setString(2,(String)hashmap.get("inid"));//userid
					}else{
						pstmts[0].setString(2," ");//userid
					}
					if(hashmap.get("modelnum")!=null){
						pstmts[0].setString(3,(String)hashmap.get("modelnum"));//username
					}else{
						pstmts[0].setString(3," ");//username
					}
					if(hashmap.get("outcount")!=null){
						pstmts[0].setString(4,(String)hashmap.get("outcount"));//pwd
					}else{
						pstmts[0].setString(4," ");//pwd
					}
					if(hashmap.get("outdate")!=null){
						pstmts[0].setString(5,(String)hashmap.get("outdate"));//email
					}else{
						pstmts[0].setString(5," ");//email
					}
					if(hashmap.get("getperson")!=null){
						pstmts[0].setString(6,(String)hashmap.get("getperson"));//mobilephone
					}else{
						pstmts[0].setString(6," ");//mobilephone
					}
					pstmts[0].addBatch();
					
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
