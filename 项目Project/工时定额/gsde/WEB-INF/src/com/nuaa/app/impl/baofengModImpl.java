package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.baofengMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;

public class baofengModImpl implements baofengMod{

	@Override
	public JSONObject queryWeight() {
		// TODO Auto-generated method stub
		String sql = "select * from t_baofeng";
		final JSONObject json = new JSONObject();
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					try {
						while(rs.next()){	
							json.put("zj", Double.parseDouble(rs.getString("zj")));
							json.put("jg", Double.parseDouble(rs.getString("jg")));		
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
		final String zj = hashmap.get("zj").toString();
		final String jg = hashmap.get("jg").toString();
		String[] sql = new String[1];
		sql[0] = "update t_baofeng set zj=?,jg=?";
		try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts)throws SQLException {
					// TODO Auto-generated method stub
					pstmts[0].setString(1,zj);
					pstmts[0].setString(2,jg);
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
