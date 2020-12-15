package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.tuxingzhuanyiMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;

public class tuxingzhuanyiModImpl implements tuxingzhuanyiMod{

	@Override
	public JSONObject queryWeight() {
		// TODO Auto-generated method stub
		String sql = "select * from t_tuxingzhuanyi";
		final JSONObject json = new JSONObject();
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					try {
						while(rs.next()){	
							json.put("ps1", Double.parseDouble(rs.getString("ps1")));
							json.put("dm1", Double.parseDouble(rs.getString("dm1")));
							json.put("sm1", Double.parseDouble(rs.getString("sm1")));	
							json.put("dc1", Double.parseDouble(rs.getString("dc1")));
							json.put("bg1", Double.parseDouble(rs.getString("bg1")));
							json.put("xb1", Double.parseDouble(rs.getString("xb1")));	
							json.put("ps2", Double.parseDouble(rs.getString("ps2")));
							json.put("dm2", Double.parseDouble(rs.getString("dm2")));
							json.put("sm2", Double.parseDouble(rs.getString("sm2")));	
							json.put("dc2", Double.parseDouble(rs.getString("dc2")));
							json.put("bg2", Double.parseDouble(rs.getString("bg2")));
							json.put("xb2", Double.parseDouble(rs.getString("xb2")));	
							json.put("ps3", Double.parseDouble(rs.getString("ps3")));
							json.put("dm3", Double.parseDouble(rs.getString("dm3")));
							json.put("sm3", Double.parseDouble(rs.getString("sm3")));	
							json.put("dc3", Double.parseDouble(rs.getString("dc3")));
							json.put("bg3", Double.parseDouble(rs.getString("bg3")));
							json.put("xb3", Double.parseDouble(rs.getString("xb3")));	
							json.put("ps4", Double.parseDouble(rs.getString("ps4")));
							json.put("dm4", Double.parseDouble(rs.getString("dm4")));
							json.put("sm4", Double.parseDouble(rs.getString("sm4")));	
							json.put("dc4", Double.parseDouble(rs.getString("dc4")));
							json.put("bg4", Double.parseDouble(rs.getString("bg4")));
							json.put("xb4", Double.parseDouble(rs.getString("xb4")));	
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
		final String ps1 = hashmap.get("ps1").toString();
		final String dm1 = hashmap.get("dm1").toString();
		final String sm1 = hashmap.get("sm1").toString();
		final String dc1 = hashmap.get("dc1").toString();
		final String bg1 = hashmap.get("bg1").toString();
		final String xb1 = hashmap.get("xb1").toString();
		final String ps2 = hashmap.get("ps2").toString();
		final String dm2 = hashmap.get("dm2").toString();
		final String sm2 = hashmap.get("sm2").toString();
		final String dc2 = hashmap.get("dc2").toString();
		final String bg2 = hashmap.get("bg2").toString();
		final String xb2 = hashmap.get("xb2").toString();
		final String ps3 = hashmap.get("ps3").toString();
		final String dm3 = hashmap.get("dm3").toString();
		final String sm3 = hashmap.get("sm3").toString();
		final String dc3 = hashmap.get("dc3").toString();
		final String bg3 = hashmap.get("bg3").toString();
		final String xb3 = hashmap.get("xb3").toString();
		final String ps4 = hashmap.get("ps4").toString();
		final String dm4 = hashmap.get("dm4").toString();
		final String sm4 = hashmap.get("sm4").toString();
		final String dc4 = hashmap.get("dc4").toString();
		final String bg4 = hashmap.get("bg4").toString();
		final String xb4 = hashmap.get("xb4").toString();
		String[] sql = new String[1];
		sql[0] = "update t_tuxingzhuanyi set ps1=?,dm1=?,sm1=?,dc1=?,bg1=?,xb1=?,ps2=?,dm2=?,sm2=?,dc2=?,bg2=?,xb2=?,ps3=?,dm3=?,sm3=?,dc3=?,bg3=?,xb3=?,ps4=?,dm4=?,sm4=?,dc4=?,bg4=?,xb4=?";
		try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts)throws SQLException {
					// TODO Auto-generated method stub
					pstmts[0].setString(1,ps1);
					pstmts[0].setString(2,dm1);
					pstmts[0].setString(3,sm1);
					pstmts[0].setString(4,dc1);
					pstmts[0].setString(5,bg1);
					pstmts[0].setString(6,xb1);
					pstmts[0].setString(7,ps2);
					pstmts[0].setString(8,dm2);
					pstmts[0].setString(9,sm2);
					pstmts[0].setString(10,dc2);
					pstmts[0].setString(11,bg2);
					pstmts[0].setString(12,xb2);
					pstmts[0].setString(13,ps3);
					pstmts[0].setString(14,dm3);
					pstmts[0].setString(15,sm3);
					pstmts[0].setString(16,dc3);
					pstmts[0].setString(17,bg3);
					pstmts[0].setString(18,xb3);
					pstmts[0].setString(19,ps4);
					pstmts[0].setString(20,dm4);
					pstmts[0].setString(21,sm4);
					pstmts[0].setString(22,dc4);
					pstmts[0].setString(23,bg4);
					pstmts[0].setString(24,xb4);
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
