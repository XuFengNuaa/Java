package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.PamEnStandar;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class PamEnStandarImp implements PamEnStandar {

	public String addFile(HashMap addMap) {
		// TODO 自动生成方法存根
		String result = "false";
		HashMap addHashMap = addMap;
		String indi_num=(String)addHashMap.get("pa[3]");
		System.out.println("...................."+indi_num+"....................");
			
					final String[] pa = new String[13];
					for (int i = 0; i < pa.length; i++) {
						pa[i] = "";
					}
					
					pa[0] = (String) addHashMap.get("pa[0]");
					pa[1] = (String) addHashMap.get("pa[1]");
					pa[2] = (String) addHashMap.get("pa[2]");
					pa[3] = (String) addHashMap.get("pa[3]");
					pa[4] = (String) addHashMap.get("pa[4]");
					pa[5] = (String) addHashMap.get("pa[5]");
					pa[6] = (String) addHashMap.get("pa[6]");
					pa[7] = (String) addHashMap.get("pa[7]");
					pa[8] = (String) addHashMap.get("pa[8]");
					pa[9] = (String) addHashMap.get("pa[9]");
					pa[10] = (String) addHashMap.get("pa[10]");
					pa[11] = (String) addHashMap.get("pa[11]");
					pa[12] = (String) addHashMap.get("pa[12]");
					//final java.sql.Date dateAddTime = java.sql.Date.valueOf((String)addHashMap.get("pa[3]"));
                    String[] sqls = new String[1];
					sqls[0] = "insert into  t_m_entry_trecord (type_id,in_time,newstatus,indi_num,location,keeper,remark,startdate,valid,cycle,entry_num,indi_id,status) values(?,to_date(?,'yyyy/mm/dd'),?,?,?,?,?,?,?,?,?,?,?)";
					Logger.debug(sqls[0]);
					try {
								DbUtil.executeBatchs(sqls,
										new IArrayPreparedStatementProcessor() {
											public void process(PreparedStatement[] pstmts)
													throws SQLException {
												pstmts[0].setString(1, pa[0]);
												pstmts[0].setString(2, pa[1]);
												pstmts[0].setString(3, pa[2]);
												pstmts[0].setString(4, pa[3]);
												pstmts[0].setString(5, pa[4]);
												pstmts[0].setString(6, pa[5]);
												pstmts[0].setString(7, pa[6]);
												pstmts[0].setString(8, pa[7]);
												pstmts[0].setString(9, pa[8]);
												pstmts[0].setString(10, pa[9]);
												pstmts[0].setString(11, pa[10]);
												pstmts[0].setString(12, pa[11]);
												pstmts[0].setString(13, pa[12]);
												pstmts[0].addBatch();
											}
										});
								result="true";
					
					}catch (SQLException e) {
						e.printStackTrace();
						result="false";
					}
					/*					
					String[] sqls = new String[1];
					
					sqls[0] = "insert into  t_m_entry_record (entry_num,indi_id,type_id,in_time,newstatus) values(?,?,?,to_date(?,'yyyy/mm/dd'),?)";
					Logger.debug(sqls[0]);
					try {
								DbUtil.executeBatchs(sqls,
										new IArrayPreparedStatementProcessor() {
											public void process(PreparedStatement[] pstmts)
													throws SQLException {
												pstmts[0].setString(1, pa[10]);
												pstmts[0].setString(2, pa[11]);
												pstmts[0].setString(3, pa[0]);
												pstmts[0].setString(4, pa[1]);
												//pstmts[0].setDate(4, (java.sql.Date) dateAddTime);
												pstmts[0].setString(5, pa[2]);
												pstmts[0].addBatch();
											}
										});
								result="true";
					
					}catch (SQLException e) {
						e.printStackTrace();
						result="false";
					}
					
					String[] sql = new String[1];
					sql[0] = "insert into  t_m_indi (indi_id,type_id,indi_num,newstatus,location,keeper,remark,startdate,valid,cycle,status) values (?,?,?,?,?,?,?,?,?,?,?)";//,startdate,valid,cycle
							try {
								DbUtil.executeBatchs(sql,
										new IArrayPreparedStatementProcessor() {
											public void process(PreparedStatement[] pstmts)
													throws SQLException {
												pstmts[0].setString(1, pa[11]);
												pstmts[0].setString(2, pa[0]);
												pstmts[0].setString(3, pa[3]);
												pstmts[0].setString(4, pa[2]);
												pstmts[0].setString(5, pa[4]);
												pstmts[0].setString(6, pa[5]);
												pstmts[0].setString(7, pa[6]);
												pstmts[0].setString(8, pa[7]);
												pstmts[0].setString(9, pa[8]);
												pstmts[0].setString(10, pa[9]);
												pstmts[0].setString(11, pa[12]);
												pstmts[0].addBatch();
											}
										});
								result +="true";
								
							} catch (SQLException e) {
								e.printStackTrace();
								result +="false";
							}	*/
					
		return result;
	}

	
	public JSONObject getQueryMnumAll() {
	   // TODO 自动生成方法存根
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		
		String sql="";
		sql="select distinct mnum from t_m_type ";
		
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("mnum", rs.getString("mnum"));
							//obj.put("company", rs.getString("company"));
							
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


	public JSONObject getQueryFile(HashMap queryMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=new HashMap();
		queryHashMap=queryMap;
		System.out.println("***********");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		//String nodeid=(String)queryHashMap.get("nodeid");
		String sql="";
		//Logger.debug(jsonObj.toString());
		String filter=(String)queryHashMap.get("id");
		sql="select * from   v_mtype2company where mnum='"+filter+"'";			
		System.out.println("sql="+sql);
		try {		
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("classname", rs.getString("classname"));
							obj.put("name", rs.getString("name"));
							obj.put("spec", rs.getString("spec"));
							obj.put("company", rs.getString("company"));
							obj.put("companyid", rs.getString("companyid"));
							obj.put("diviv", rs.getString("diviv"));
							obj.put("mini_qs", rs.getString("mini_qs"));
							obj.put("hq", rs.getString("hq"));
							obj.put("order_num", rs.getString("order_num"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("remark", rs.getString("remark"));
							//obj.put("companyid", rs.getString("companyid"));
							jsonArray.put(obj);
						}
						jsonObj.put("filedata", jsonArray);
					} catch (JSONException e) {
						// TODO
						e.printStackTrace();
					}
				}				
			});
			//Logger.debug(jsonObj.toString());
		}catch (SQLException e) {
		// TODO
			e.printStackTrace();
		}
		System.out.println("asdas"+jsonObj);
		return jsonObj;
	}


	public boolean chexFile(HashMap chexMap) {
		// TODO 自动生成方法存根
		boolean result = false;
		HashMap chexHashMap = chexMap;
		String mnum=(String)chexHashMap.get("mnum");
		System.out.println(mnum);
		String sql="select * from  t_m_type where mnum='"+mnum+"'";
		final int []flag=new int[1];
		flag[0]=0;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if(rs.next()){
						flag[0]=1;
					}
				}
			});
			if(flag[0]==1){
				result=true;
			}else result=false;
		}catch (SQLException e) {
			e.printStackTrace();
			result= false;
		}
		return result;
	}


	public String chexTool(HashMap chexToolMap) {
		// TODO 自动生成方法存根
		String result = "false";
		HashMap chexHashMap = chexToolMap;
		String indi_num = (String)chexHashMap.get("indi_num");
		System.out.println(indi_num);
		String sq="select * from (select * from  t_m_entry_trecord where indi_num='"+indi_num+"') where status = '批准' or status = '申请'";
		final int []fla=new int[1];
		fla[0]=0;
		try {			
			DbUtil.execute(sq,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if(rs.next()){
						fla[0]=1;
					}
				}
			});
			if(fla[0]==1){
				result="true";
			}else result="false";
		}catch (SQLException e) {
			e.printStackTrace();
			result= "false";
		}
		if(result.equals("false")){
			String sql="select * from  t_m_indi where indi_num='"+indi_num+"'";
			final int []flag=new int[1];
			flag[0]=0;
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {
						if(rs.next()){
							flag[0]=1;
						}
					}
				});
				if(flag[0]==1){
					result="truetrue";
				}else result="false";
			}catch (SQLException e) {
				e.printStackTrace();
				result= "false";
			}
		}
		System.out.println(result);
		return result;
	}


	public JSONObject getQuerycomAll(HashMap comMap) {
		// TODO 自动生成方法存根
		HashMap comHashMap = comMap;
		String filter=(String)comHashMap.get("filter");
		String hq=(String)comHashMap.get("hq");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		System.out.println(".............filter..........."+filter);
		String sql="";
		sql="select distinct company from (select * from (select * from (select * from v_mtype2company)  where mnum = '"+filter+"')  where hq = '"+hq+"')";
		
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("company", rs.getString("company"));
							//obj.put("company", rs.getString("company"));
							
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


	public JSONObject getQuerynumAll(HashMap numMap) {
		// TODO 自动生成方法存根
		HashMap numHashMap = numMap;
		String filter=(String)numHashMap.get("filter");//1=1 and mnum like '00%'
		String filter1=(String)numHashMap.get("order");
		System.out.println(".............filter..........."+filter);
		System.out.println(".............filter1..........."+filter1);
		String filter2=(String)numHashMap.get("hq");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		
		String sql="";
		sql="select * from (select * from (select * from (select * from v_mtype2company)  where mnum = '"+filter+"') where hq = '"+filter2+"') where company = '"+filter1+"'";
		//"select * from (select * from (select * from v_cutter2com)  where mnum like '%"+filter+"')  where company like '%"+filter1+"'";
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("order_num", rs.getString("order_num"));
							//obj.put("company", rs.getString("company"));
							
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


	public boolean chexFile1(HashMap chexMap) {
		// TODO 自动生成方法存根
		boolean result = false;
		HashMap chexHashMap = chexMap;
		String mnum=(String)chexHashMap.get("mnum");
		String hq=(String)chexHashMap.get("hq");
		System.out.println(mnum);
		String sql="select * from(select * from  t_m_type where mnum='"+mnum+"')where hq='"+hq+"'";
		final int []flag=new int[1];
		flag[0]=0;
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if(rs.next()){
						flag[0]=1;
					}
				}
			});
			if(flag[0]==1){
				result=true;
			}else result=false;
		}catch (SQLException e) {
			e.printStackTrace();
			result= false;
		}
		return result;
	}


	public JSONObject getQueryFile1(HashMap queryMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=new HashMap();
		queryHashMap=queryMap;
		System.out.println("***********");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		//String nodeid=(String)queryHashMap.get("nodeid");
		String sql="";
		//Logger.debug(jsonObj.toString());
		String filter=(String)queryHashMap.get("id");
		String hq=(String)queryHashMap.get("hq");
		sql="select * from(select * from  v_mtype2company where mnum='"+filter+"')where hq='"+hq+"'";			
		System.out.println("sql="+sql);
		try {		
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("classname", rs.getString("classname"));
							obj.put("name", rs.getString("name"));
							obj.put("spec", rs.getString("spec"));
							obj.put("company", rs.getString("company"));
							obj.put("companyid", rs.getString("companyid"));
							obj.put("diviv", rs.getString("diviv"));
							obj.put("mini_qs", rs.getString("mini_qs"));
							obj.put("hq", rs.getString("hq"));
							obj.put("order_num", rs.getString("order_num"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("remark", rs.getString("remark"));
							//obj.put("companyid", rs.getString("companyid"));
							jsonArray.put(obj);
						}
						jsonObj.put("filedata", jsonArray);
					} catch (JSONException e) {
						// TODO
						e.printStackTrace();
					}
				}				
			});
			//Logger.debug(jsonObj.toString());
		}catch (SQLException e) {
		// TODO
			e.printStackTrace();
		}
		System.out.println("asdas"+jsonObj);
		return jsonObj;
	}


	public JSONObject getQueryApprove(HashMap queryMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryMap;
		int start=Integer.parseInt((String)queryHashMap.get("start"));
		int limit=Integer.parseInt((String)queryHashMap.get("limit"));
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select count(*) as ct from t_m_etr_type where status = '批准' or status = '申请'or status = '拒绝' ";
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
		
		sql="select * from (select  *  from  (select * from (select * from  t_m_etr_type ) where status = '批准'or status = '申请' or status = '拒绝' order by status) where rownum<="+(start+limit)+" minus select  *  from  (select  *  from  (select * from  t_m_etr_type ) where status = '批准'or status = '申请' or status = '拒绝' order by status ) where rownum<="+start+") order by status ";  
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("type_id", rs.getString("type_id"));
							obj.put("in_time", rs.getString("in_time"));//newstatus,indi_num,location,keeper,remark,startdate,valid,cycle,entry_num,indi_id,status
							obj.put("newstatus", rs.getString("newstatus"));
							obj.put("indi_num", rs.getString("indi_num"));
							obj.put("name", rs.getString("name"));
							obj.put("location", rs.getString("location"));
							obj.put("company", rs.getString("company"));
							obj.put("hq", rs.getString("hq"));
							obj.put("classname", rs.getString("classname"));
							obj.put("remark", rs.getString("remark"));
							obj.put("keeper", rs.getString("keeper"));
							obj.put("startdate", rs.getString("startdate"));
							obj.put("valid", rs.getString("valid"));
							obj.put("cycle", rs.getString("cycle"));
							obj.put("entry_num",rs.getString("entry_num"));
							obj.put("indi_id",rs.getString("indi_id"));
							obj.put("status",rs.getString("status"));
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


	public String addFileApp(HashMap addMap) {
		// TODO 自动生成方法存根
		String result = "false";
		HashMap addHashMap = addMap;
		String indi_num=(String)addHashMap.get("pa[3]");
		System.out.println("...................."+indi_num+"....................");
			
					final String[] pa = new String[13];
					for (int i = 0; i < pa.length; i++) {
						pa[i] = "";
					}
					
					pa[0] = (String) addHashMap.get("pa[0]");
					pa[1] = (String) addHashMap.get("pa[1]");
					pa[2] = (String) addHashMap.get("pa[2]");
					pa[3] = (String) addHashMap.get("pa[3]");
					pa[4] = (String) addHashMap.get("pa[4]");
					pa[5] = (String) addHashMap.get("pa[5]");
					pa[6] = (String) addHashMap.get("pa[6]");
					pa[7] = (String) addHashMap.get("pa[7]");
					pa[8] = (String) addHashMap.get("pa[8]");
					pa[9] = (String) addHashMap.get("pa[9]");
					pa[10] = (String) addHashMap.get("pa[10]");
					pa[11] = (String) addHashMap.get("pa[11]");
					pa[12] = (String) addHashMap.get("pa[12]");
                    String[] sqls = new String[1];
					
					sqls[0] = "insert into  t_m_entry_record (entry_num,indi_id,type_id,in_time,newstatus) values(?,?,?,to_date(?,'yyyy-mm-dd'),?)";
					Logger.debug(sqls[0]);
					try {
								DbUtil.executeBatchs(sqls,
										new IArrayPreparedStatementProcessor() {
											public void process(PreparedStatement[] pstmts)
													throws SQLException {
												pstmts[0].setString(1, pa[10]);
												pstmts[0].setString(2, pa[11]);
												pstmts[0].setString(3, pa[0]);
												pstmts[0].setString(4, pa[1]);
												//pstmts[0].setDate(4, (java.sql.Date) dateAddTime);
												pstmts[0].setString(5, pa[2]);
												pstmts[0].addBatch();
											}
										});
								result="true";
					
					}catch (SQLException e) {
						e.printStackTrace();
						result="false";
					}
					
					String[] sql = new String[1];
					sql[0] = "insert into  t_m_indi (indi_id,type_id,indi_num,newstatus,location,keeper,remark,startdate,valid,cycle,status) values (?,?,?,?,?,?,?,?,?,?,?)";//,startdate,valid,cycle
							try {
								DbUtil.executeBatchs(sql,
										new IArrayPreparedStatementProcessor() {
											public void process(PreparedStatement[] pstmts)
													throws SQLException {
												pstmts[0].setString(1, pa[11]);
												pstmts[0].setString(2, pa[0]);
												pstmts[0].setString(3, pa[3]);
												pstmts[0].setString(4, pa[2]);
												pstmts[0].setString(5, pa[4]);
												pstmts[0].setString(6, pa[5]);
												pstmts[0].setString(7, pa[6]);
												pstmts[0].setString(8, pa[7]);
												pstmts[0].setString(9, pa[8]);
												pstmts[0].setString(10, pa[9]);
												pstmts[0].setString(11, pa[12]);
												pstmts[0].addBatch();
											}
										});
								result +="true";
								
							} catch (SQLException e) {
								e.printStackTrace();
								result +="false";
							}
					if (result.equals("truetrue")){
						/*final String status="已入库";
						sqls[0] = "update t_m_entry_trecord set status=? where entry_num = '"+pa[10]+"'";
						Logger.debug(sqls[0]);
						try {
									DbUtil.executeBatchs(sqls,
											new IArrayPreparedStatementProcessor() {
												public void process(PreparedStatement[] pstmts)
														throws SQLException {
													pstmts[0].setString(1, status);
													pstmts[0].addBatch();
												}
											});
						}catch (SQLException e) {
							e.printStackTrace();
							result="false";
						}*/
						sqls[0]="delete from t_m_entry_trecord where entry_num in('"+pa[10]+"')";
						Logger.debug(sqls[0]);
							try {
								DbUtil.executeBatchs(sqls,
									new IArrayPreparedStatementProcessor() {
										public void process(
										PreparedStatement[] pstmts)
										throws SQLException {						
											pstmts[0].addBatch();
										}
							});
						} catch (Exception e) {
							e.printStackTrace();
							result="false";
						}
					}
					
		return result;
	}


	public String deleteRe(HashMap delMap) {
		// TODO 自动生成方法存根
		String result = "";
		String deleteid=(String)delMap.get("deleteid");
		String []sqls=new String[1];
		sqls[0]="delete from t_m_entry_trecord where entry_num in('"+deleteid+"')";
		Logger.debug(sqls[0]);
			try {
				DbUtil.executeBatchs(sqls,
					new IArrayPreparedStatementProcessor() {
						public void process(
						PreparedStatement[] pstmts)
						throws SQLException {						
							pstmts[0].addBatch();
						}
			});
			result="true";
		} catch (Exception e) {
			e.printStackTrace();
			result="false";
		}
		return result;
	}
}
