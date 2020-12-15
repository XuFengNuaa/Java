package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.zhimubanMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;

public class zhimubanModImpl implements zhimubanMod{

	@Override
	public JSONObject queryWeight() {
		// TODO Auto-generated method stub
		String sql = "select * from t_zhimuban";
		final JSONObject json = new JSONObject();
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					try {
						while(rs.next()){	
							json.put("sheji", Double.parseDouble(rs.getString("sheji")));
							json.put("gh", Double.parseDouble(rs.getString("gh")));
							json.put("xiuban", Double.parseDouble(rs.getString("xiuban")));				
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
		final String sheji = hashmap.get("sheji").toString();
		final String gh = hashmap.get("gh").toString();
		final String xiuban = hashmap.get("xiuban").toString();
		String[] sql = new String[1];
		sql[0] = "update t_zhimuban set sheji=?,gh=?,xiuban=?";
		try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts)throws SQLException {
					// TODO Auto-generated method stub
					pstmts[0].setString(1,sheji);
					pstmts[0].setString(2,gh);
					pstmts[0].setString(3,xiuban);
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
