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
		sql="select * from T_USER where stuff_num='"+stuff_num+"'"; 
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						int i=0;
						String usrlevels = "";
						while(rs.next()){
							jsonObj.put("stuff_num", rs.getString("stuff_num"));
							jsonObj.put("realname", rs.getString("realname"));
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
	
	
}
