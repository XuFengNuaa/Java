/**
 * 
 */
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

import com.nuaa.app.FileState;
import com.nuaa.app.Login;
import com.nuaa.app.UsrInfMod;
import com.nuaa.sys.util.AppInsFactory;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

/**
 * @author Administrator
 *
 */
public class UsrInfModImpl implements UsrInfMod {

	public JSONObject viewUsr(HashMap viewusrMap) {
		// TODO 自动生成方法存根
		HashMap viewusrHashMap=viewusrMap;
		final JSONObject jsonObj=new JSONObject();
		final String stuff_num=(String)viewusrHashMap.get("stuff_num");
		String sql="";
		sql="select * from V_USER2ROLE where stuff_num='"+stuff_num+"'"; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						int i=0;
						String usrlevels = "";
						while(rs.next()){
							final String []order_num = new String[1];
							String sql1 = "select order_num from t_user where stuff_num='"+stuff_num+"'";
							try {			
								DbUtil.execute(sql1,new IResultSetProcessor(){
									public void process(ResultSet rs1) throws SQLException{
										while(rs1.next()){					
											order_num[0] = rs1.getString("order_num");
										}
									}
								});	
							}catch (SQLException e){
								e.printStackTrace();
							}
							jsonObj.put("stuff_num", rs.getString("stuff_num"));
							jsonObj.put("realname", rs.getString("realname"));
							jsonObj.put("group_num", rs.getString("group_num"));
							jsonObj.put("remark", rs.getString("remark"));
							jsonObj.put("order_num", order_num[0]);
							jsonObj.put("rolename"+i, rs.getString("rolename"));
							usrlevels = usrlevels + (String)rs.getString("role")+",";
							i++;
						}	
						jsonObj.put("usrlevels", usrlevels);
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

	

	public boolean editpwdUsr(HashMap editpwdusrHMap) {
		// TODO 自动生成方法存根
		final String userid = (String) editpwdusrHMap.get("userid");
		final String pwd = (String) editpwdusrHMap.get("pwd");
		final String pwd1 = (String) editpwdusrHMap.get("pwd1");
		Login lg = (Login) AppInsFactory.getServiceInstance(Login.class);
		if (lg.isValid(userid, pwd)) {
			String[] sqls = new String[1];
			sqls[0] = "update t_user set password=? where stuff_num='" + userid + "'";
			System.out.println(sqls[0]);
			try {
				DbUtil.executeBatchs(sqls,
						new IArrayPreparedStatementProcessor() {
							public void process(PreparedStatement[] pstmts)
									throws SQLException {
								pstmts[0].setString(1, pwd1);
								pstmts[0].addBatch();
							}
						});
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}else{
			return false;			
		}
	}



	/*@Override
	public JSONObject getRealName() {
		// TODO Auto-generated method stub
		final JSONArray jarr = new JSONArray();
		final JSONObject json = new JSONObject();
		String sql = "select distinct realname,stuff_num from ku_keeper where role in (8,9)";
		try {
			DbUtil.execute(sql, new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
			while(rs.next()){
				JSONObject json1 = new JSONObject();
				try {
					json1.put("worknum", rs.getString("stuff_num"));
					json1.put("realname", rs.getString("realname"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jarr.put(json1);
			}
			try {
				json.put("filedate",jarr);
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
		
		return json;
	}



	@Override
	public String addKeeper(HashMap addhashmap) {
		String result = "";
		final String keeper = (String) addhashmap.get("keeper");
		final String kuname = (String)addhashmap.get("kuname");
		final String remark = (String)addhashmap.get("remark");
		String sqls = "select count(*) as ct from T_KU_KEEPER where ku_name ='" + kuname +"' and keeper ='" + keeper+"'";
		System.out.println(sqls);
		final String[] count=new String[1];
		try {
			DbUtil.execute(sqls, new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
						count[0] = rs.getString("ct");
					}
				}
				
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if("0".equals(count[0])){
		final String id=UUID.randomUUID().toString();
		String[] sql = new String[1];
		sql[0] = "insert into T_KU_KEEPER(id,ku_name,keeper,remark) values (?,?,?,?)";
		try {
			DbUtil.executeBatchs(sql, new IArrayPreparedStatementProcessor(){
				public void process(
						PreparedStatement[] pstmts)
						throws SQLException {
					pstmts[0].setString(1,id);
					pstmts[0].setString(2,kuname);
					pstmts[0].setString(3,keeper);
					pstmts[0].setString(4,remark);
					pstmts[0].addBatch();
				}
			});
			result = "true";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "exception";
		}
		}else{
			result = "repeat";
		}
		return result;
	}



	@Override
	public JSONObject qureyAllKeeper(HashMap qreryhashmap) {
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsArray=new JSONArray();
		String sql="";
		sql="select  count(*) as ct  from  T_KU_KEEPER where "+(String)qreryhashmap.get("filter");
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
		
		int start=Integer.parseInt((String)qreryhashmap.get("start"));
		int limit=Integer.parseInt((String)qreryhashmap.get("limit"));
		
		String filter=(String)qreryhashmap.get("filter");
		String order=(String)qreryhashmap.get("order");
		sql="select * from (select * from  (select * from T_KU_KEEPER where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from T_KU_KEEPER where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql);
		
		try {			
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("kuname", rs.getString("ku_name"));
							obj.put("realname", rs.getString("keeper"));
							obj.put("remark", rs.getString("remark"));
							jsArray.put(obj);
						}	
						jsonObj.put("filedata", jsArray);
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
	public JSONObject viewKeeper(HashMap qreryhashmap) {
		String id = (String) qreryhashmap.get("id");
		String sql = "select * from t_ku_keeper where id ="+id;
		final JSONObject jsonobj = new JSONObject();
		try {
			DbUtil.execute(sql, new IResultSetProcessor(){
				@Override
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						try {
							jsonobj.put("kuname", rs.getString("ku_name"));
							jsonobj.put("keeper", rs.getString("keeper"));
							jsonobj.put("remark", rs.getString("remark"));
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
		return jsonobj;
	}



	@Override
	public String editKeeper(HashMap hashmap) {
		String result = "true";
		String id = (String) hashmap.get("id");
		final String keeper = (String) hashmap.get("realname");
		final String kuname = (String)hashmap.get("kuname");
		final String remark = (String)hashmap.get("remark");
		String[] sqls = new String[1];
		sqls[0] = "update t_ku_keeper set ku_name=?,keeper=?,remark=? where id="+id;
		System.out.println(sqls[0]);
		try {
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts)	throws SQLException {			
					pstmts[0].setString(1,kuname);
					pstmts[0].setString(2,keeper);
					pstmts[0].setString(3,remark);
					pstmts[0].addBatch();
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "false";
			
		}
		return result;
	}



	@Override
	public String delKeeper(HashMap hashmap) {
			// TODO 自动生成方法存根
			final String id=(String)hashmap.get("id");
			String result=new String();
			result = "true";
			String []sqls=new String[1];
			try {
				sqls[0]="delete from T_KU_KEEPER where id in('',"+id+")";
				DbUtil.executeBatchs(sqls,
						new IArrayPreparedStatementProcessor() {
							public void process(
							PreparedStatement[] pstmts)
							throws SQLException {						
								pstmts[0].addBatch();
	}
				});
				return result;
			}catch(SQLException e){
				e.printStackTrace();
				result = "false";
			}
			return result;
	}*/
	
	
}
