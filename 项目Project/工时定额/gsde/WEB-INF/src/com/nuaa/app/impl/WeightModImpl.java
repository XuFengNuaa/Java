package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.WeightMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;

public class WeightModImpl implements WeightMod{

	@Override
	public JSONObject queryWeight() {
		// TODO Auto-generated method stub
		//final String feature = hashmap.get("feature").toString();
		String sql = "select * from t_weight";
		final JSONObject json = new JSONObject();
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					try {
						while(rs.next()){	
							json.put("chang", Double.parseDouble(rs.getString("chang")));
							json.put("kuan", Double.parseDouble(rs.getString("kuan")));
							json.put("gao", Double.parseDouble(rs.getString("gao")));
							json.put("tiji", Double.parseDouble(rs.getString("tiji")));
							json.put("mianji", Double.parseDouble(rs.getString("mianji")));
							json.put("strw1", Double.parseDouble(rs.getString("clqiangdu")));
							json.put("strw2", Double.parseDouble(rs.getString("clrenxing")));
							json.put("strw3", Double.parseDouble(rs.getString("clyingdu")));
							json.put("strw4", Double.parseDouble(rs.getString("clredaolv")));
							json.put("xiangsidu", Double.parseDouble(rs.getString("xiangsidu")));					
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
		final String chang = hashmap.get("chang").toString();
		final String kuan = hashmap.get("kuan").toString();
		final String gao = hashmap.get("gao").toString();
		final String tiji = hashmap.get("tiji").toString();
		final String mianji = hashmap.get("mianji").toString();
		final String clqiangdu = hashmap.get("clqiangdu").toString();
		final String clrenxing = hashmap.get("clrenxing").toString();
		final String clyingdu = hashmap.get("clyingdu").toString();
		final String clredaolv = hashmap.get("clredaolv").toString();
		final String xiangsidu = hashmap.get("xiangsidu").toString();
		String[] sql = new String[1];
		sql[0] = "update t_weight set chang=?,kuan=?,gao=?,tiji=?,mianji=?,clqiangdu=?,clrenxing=?,clyingdu=?,clredaolv=?,xiangsidu=?";
		try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts)throws SQLException {
					// TODO Auto-generated method stub
					pstmts[0].setString(1,chang);
					pstmts[0].setString(2,kuan);
					pstmts[0].setString(3,gao);
					pstmts[0].setString(4,tiji);
					pstmts[0].setString(5,mianji);
					pstmts[0].setString(6,clqiangdu);
					pstmts[0].setString(7,clrenxing);
					pstmts[0].setString(8,clyingdu);
					pstmts[0].setString(9,clredaolv);
					pstmts[0].setString(10,xiangsidu);
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
