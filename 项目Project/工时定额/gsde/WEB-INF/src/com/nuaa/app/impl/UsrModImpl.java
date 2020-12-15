/**
 * 
 */
package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.UsrMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

/**
 * @author Administrator
 *
 */
public class UsrModImpl implements UsrMod {

	/* （非 Javadoc）
	 * @see com.nuaa.app.UsrMod#addUsr(java.util.HashMap)
	 */
	
	//添加新用户以及修改后添加用户
	public String addUsr(HashMap addusrHMap) {
		// TODO 自动生成方法存根
		final HashMap addusrHashMap=addusrHMap;
		String sql ="";	
		String stuff_num=(String)addusrHMap.get("stuff_num");
		final JSONArray idArray=new JSONArray();	
		sql="select count(*) as ct from t_user where stuff_num='"+stuff_num+"'";
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
				String []sqls = new String[1];
				sqls[0]= "insert into t_user(stuff_num,realname,password,id) values(?,?,?,?)";
				DbUtil.executeBatchs(sqls,
						new IArrayPreparedStatementProcessor() {
							public void process(
							PreparedStatement[] pstmts)
							throws SQLException {
						if(addusrHashMap.get("stuff_num")!=null){
							pstmts[0].setString(1,(String)addusrHashMap.get("stuff_num"));//stuff_num
						}else{
							pstmts[0].setString(1," ");//stuff_num		工号
						}
						if(addusrHashMap.get("realname")!=null){
							pstmts[0].setString(2,(String)addusrHashMap.get("realname"));//realname
						}else{
							pstmts[0].setString(2," ");//realname	姓名
						}
						if(addusrHashMap.get("password")!=null){
							pstmts[0].setString(3,(String)addusrHashMap.get("password"));//password
						}else{
							pstmts[0].setString(3," ");//password		密码
						}
						String uuid = UUID.randomUUID().toString();
						pstmts[0].setString(4,uuid);
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
	//查询用户
	public JSONObject getQueryUsr(HashMap queryMap) {
		// TODO 自动生成方法存根
		HashMap queryHashMap=queryMap;
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select  count(*) as ct  from  t_user where "+(String)queryHashMap.get("filter");
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
		sql="select * from (select * from  (select * from t_user where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from t_user where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("stuff_num", rs.getString("stuff_num"));
							obj.put("realname", rs.getString("realname"));
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
	
	//判断删除的用户中是否存在操作人
	public boolean checkNum(HashMap checknumHMap){
		final String stuff_num=(String)checknumHMap.get("stuff_num");
		final String [] sstuff_num = stuff_num.split(",");
		final String user_num="'"+(String)checknumHMap.get("user_num")+"'";
		System.out.println("user_num:"+user_num);
		String []sql = new String[1];
		sql[0]="select * from t_user where stuff_num in("+stuff_num+")";
		Logger.debug(sql[0]);
		try {			
			DbUtil.execute(sql[0],new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					while(rs.next()){					
					}
				}
			});	
			int j=0;
			for(int i=0;i<sstuff_num.length;i++){
				boolean result=(user_num.equals(sstuff_num[i]));
				if(!result){
					j++;
				}else{break;}
			}
			if (sstuff_num.length==j){	
				return true;
			}else{
				return false;
			}
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	//删除用户
	public boolean delUsr(HashMap delusrHMap) {
		// TODO 自动生成方法存根
		String stuff_num=(String)delusrHMap.get("stuff_num");
		String []sqls=new String[1];
		sqls[0]="delete from t_user where stuff_num in("+stuff_num+")";
		//sqls[1]="delete from t_user_role where stuff_num in("+stuff_num+")";
		Logger.debug(sqls[0]);
		//Logger.debug(sqls[1]);
			try {
				DbUtil.executeBatchs(sqls,
					new IArrayPreparedStatementProcessor() {
						public void process(
						PreparedStatement[] pstmts)
						throws SQLException {						
							pstmts[0].addBatch();
							//pstmts[1].addBatch();
						}
			});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}

	
	//修改用户时删除该用户
	public boolean delUsr1(HashMap delusrHMap) {
		// TODO 自动生成方法存根
		String stuff_num=(String)delusrHMap.get("stuff_num");
		String []sqls=new String[1];
		sqls[0]="delete from t_user where stuff_num in('"+stuff_num+"')";
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
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	//修改用户信息stuff_num
	public String modiUser(HashMap modiUsrHMap) {
		// TODO 自动生成方法存根
		final String []result = new String[1];
		result[0] = "true";
		final String stuff_num=(String)modiUsrHMap.get("stuff_num");
		final String realname=(String)modiUsrHMap.get("realname");	
		final String password=(String)modiUsrHMap.get("password");
		String[] sqls = new String[1];
		sqls[0]="update t_user set password='"+password+"',realname='"+realname+"' where stuff_num in('"+stuff_num+"')";
		Logger.debug(sqls);
			try {
				DbUtil.executeBatchs(sqls,
					new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)
						throws SQLException {
							pstmts[0].setString(1, stuff_num);
						    pstmts[0].setString(2, realname);
						    pstmts[0].setString(3, password);
							pstmts[0].addBatch();
							}
			});
			result[0] = "true";
		} catch (Exception e) {
			e.printStackTrace();
			result[0] = "exception";
		}	
		return result[0];
	}

	//修改用户信息时查看用户详细信息
	public JSONObject viewUsr(HashMap viewusrMap) {
		// TODO 自动生成方法存根
		HashMap viewusrHashMap=viewusrMap;
		final JSONObject jsonObj=new JSONObject();
		final String editstuff_num=(String)viewusrHashMap.get("stuff_num");
		String sql="";
		sql="select * from t_user where stuff_num='"+editstuff_num+"'"; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						int i = 0;
						String userlevels = "";
						while(rs.next()){
							jsonObj.put("stuff_num", rs.getString("stuff_num"));
							jsonObj.put("realname", rs.getString("realname"));
							jsonObj.put("password", rs.getString("password"));
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
	
	//查询所有班组长
	public JSONObject allTeamLeader() {
		// TODO 自动生成方法存根
		
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select * from v_user2role where role=3";
		
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("stuff_num", rs.getString("stuff_num"));
							obj.put("realname", rs.getString("realname"));
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
	
	//查询是否存在这个工号
	public String exsit(HashMap get_numHashMap) {
		// TODO 自动生成方法存根
		final String stuff_num=(String)get_numHashMap.get("charger");
		String sql="select count(*) as ct from t_user where stuff_num='"+stuff_num+"'";
		final int[] count=new int[1];
		count[0]=0;
		
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
							if(rs.getInt("ct")==0){
								count[0]=0;
							}else{
								count[0]=1;
							}							
						}
				}				
			});	
			if(count[0]==1){
				return "true";
			}else{
				return "false";
			}
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
			return "exception";
		}
		
	}


}
