package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.shukongzuankongMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;

public class shukongzuankongModImpl implements shukongzuankongMod{

	@Override
	public JSONObject queryWeight() {
		// TODO Auto-generated method stub
		//final String feature = hashmap.get("feature").toString();
		String sql = "select * from t_shukongzuankong";
		final JSONObject json = new JSONObject();
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					try {
						while(rs.next()){	
							json.put("zk2", Double.parseDouble(rs.getString("zk2")));
							json.put("zk4", Double.parseDouble(rs.getString("zk4")));
							json.put("zk6", Double.parseDouble(rs.getString("zk6")));
							json.put("js1", Double.parseDouble(rs.getString("js1")));
							json.put("js2", Double.parseDouble(rs.getString("js2")));
							json.put("js3", Double.parseDouble(rs.getString("js3")));
							json.put("sd", Double.parseDouble(rs.getString("sd")));
							json.put("jd", Double.parseDouble(rs.getString("jd")));
							json.put("dk", Double.parseDouble(rs.getString("dk")));
							json.put("zj", Double.parseDouble(rs.getString("zj")));					
						}
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
		System.out.println(json);
		return json;
	}

	@Override
	public String editWeight(HashMap hashmap) {
		// TODO Auto-generated method stub
		final String zk2 = hashmap.get("zk2").toString();
		final String zk4 = hashmap.get("zk4").toString();
		final String zk6 = hashmap.get("zk6").toString();
		final String js1 = hashmap.get("js1").toString();
		final String js2 = hashmap.get("js2").toString();
		final String js3 = hashmap.get("js3").toString();
		final String sd = hashmap.get("sd").toString();
		final String jd = hashmap.get("jd").toString();
		final String dk = hashmap.get("dk").toString();
		final String zj = hashmap.get("zj").toString();
		String[] sql = new String[1];
		sql[0] = "update t_shukongzuankong set zk2=?,zk4=?,zk6=?,js1=?,js2=?,js3=?,sd=?,jd=?,dk=?,zj=?";
		try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts)throws SQLException {
					// TODO Auto-generated method stub
					pstmts[0].setString(1,zk2);
					pstmts[0].setString(2,zk4);
					pstmts[0].setString(3,zk6);
					pstmts[0].setString(4,js1);
					pstmts[0].setString(5,js2);
					pstmts[0].setString(6,js3);
					pstmts[0].setString(7,sd);
					pstmts[0].setString(8,jd);
					pstmts[0].setString(9,dk);
					pstmts[0].setString(10,zj);
					pstmts[0].addBatch();
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}

}
