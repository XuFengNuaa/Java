package com.nuaa.app.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.Query_Delete;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;

public class Query_DeleteImpl implements Query_Delete{
	public JSONObject queryDelete(HashMap hashmap) {
		final String filter = (String) hashmap.get("filter");
		final String order = (String) hashmap.get("order");
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sqlq = "select count(*) as ct from  v_del_com where " + filter;
		try {
			DbUtil.execute(sqlq,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						try {
							obj.put("totalProperty",rs.getString("ct"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
			});
		} catch (SQLException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		
		String sql = "select * from  v_del_com where " + filter +"order by "+ order;
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						JSONObject json = new JSONObject();
						try {
							json.put("ordernum",rs.getString("ordernum"));
						} catch (JSONException e4) {
							// TODO Auto-generated catch block
							e4.printStackTrace();
						}//取出ordernum
						//取出库名
						try {
							if(rs.getString("kuname")!=""){
								json.put("kuname",rs.getString("kuname"));
							}else{
								json.put("kuname","");
							}
							//取出有效期
							if(rs.getString("valiadate")!=""){
								json.put("valiadate",rs.getString("valiadate"));
							}else{
								json.put("valiadate","");
							}
							//取出保质期
							if(rs.getString("guaranteedate")!=""){
								json.put("guaranteedate",rs.getString("guaranteedate"));
							}else{
								json.put("guaranteedate","");
							}
							//取出货架号
							if(rs.getString("shelfnum")!=""){
								json.put("shelfnum",rs.getString("shelfnum"));
							}else{
								json.put("shelfnum","");
							}
							//取出报废数量
							if(!(rs.getString("delcount").equals(""))){
								json.put("delcount",rs.getString("delcount"));
							}else{
								json.put("delcount","");
							}
							//取出报废日期
							json.put("deldate",rs.getDate("deldate"));
							//取出报废人
							if(rs.getString("delperson")!=""){
								json.put("delperson",rs.getString("delperson"));
							}else{
								json.put("delperson","");
							}
							//取出mnum
							if(rs.getString("mnum")!=""){
								json.put("mnum",rs.getString("mnum"));
							}else{
								json.put("mnum","");
							}
							//取出名称
							if(rs.getString("name")!=""){
								json.put("name",rs.getString("name"));
							}else{
								json.put("name","");
							}
							//取出规格
							if(rs.getString("spec")!=""){
								json.put("spec",rs.getString("spec"));
							}else{
								json.put("spec","");
							}
							//取出型号
							if(rs.getString("modelnum")!=""){
								json.put("modelnum",rs.getString("modelnum"));
							}else{
								json.put("modelnum","");
							}
							
						} catch (JSONException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						//取出材料类别
						try {
							if(rs.getString("classname")!=""){
								json.put("classname",rs.getString("classname"));
							}else{
								json.put("classname","");

						}} catch (JSONException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						//取出厂商
						try {
							if(rs.getString("company")!=""){
								json.put("company",rs.getString("company"));
							}else{
								json.put("company","");
							}
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						jsonarray.put(json);
					}
					try {
						obj.put("filedata",jsonarray);
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
		return obj;
	}

	@Override
	public JSONObject queryDelete1() {
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sqlq = "select count(*) as ct from  v_del_com ";
		try {
			DbUtil.execute(sqlq,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						try {
							obj.put("totalProperty",rs.getString("ct"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
			});
		} catch (SQLException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		
		String sql = "select * from  v_del_com ";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						JSONObject json = new JSONObject();
						try {
							json.put("ordernum",rs.getString("ordernum"));
						} catch (JSONException e4) {
							// TODO Auto-generated catch block
							e4.printStackTrace();
						}//取出ordernum
						//取出库名
						try {
							if(rs.getString("kuname")!=""){
								json.put("kuname",rs.getString("kuname"));
							}else{
								json.put("kuname","");
							}
							//取出有效期
							if(rs.getString("valiadate")!=""){
								json.put("valiadate",rs.getString("valiadate"));
							}else{
								json.put("valiadate","");
							}
							//取出保质期
							if(rs.getString("guaranteedate")!=""){
								json.put("guaranteedate",rs.getString("guaranteedate"));
							}else{
								json.put("guaranteedate","");
							}
							//取出货架号
							if(rs.getString("shelfnum")!=""){
								json.put("shelfnum",rs.getString("shelfnum"));
							}else{
								json.put("shelfnum","");
							}
							//取出报废数量
							if(!(rs.getString("delcount").equals(""))){
								json.put("delcount",rs.getString("delcount"));
							}else{
								json.put("delcount","");
							}
							//取出报废日期
							json.put("deldate",rs.getDate("deldate"));
							//取出报废人
							if(rs.getString("delperson")!=""){
								json.put("delperson",rs.getString("delperson"));
							}else{
								json.put("delperson","");
							}
							//取出mnum
							if(rs.getString("mnum")!=""){
								json.put("mnum",rs.getString("mnum"));
							}else{
								json.put("mnum","");
							}
							//取出名称
							if(rs.getString("name")!=""){
								json.put("name",rs.getString("name"));
							}else{
								json.put("name","");
							}
							//取出规格
							if(rs.getString("spec")!=""){
								json.put("spec",rs.getString("spec"));
							}else{
								json.put("spec","");
							}
							//取出型号
							if(rs.getString("modelnum")!=""){
								json.put("modelnum",rs.getString("modelnum"));
							}else{
								json.put("modelnum","");
							}
							
						} catch (JSONException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						//取出材料类别
						try {
							if(rs.getString("classname")!=""){
								json.put("classname",rs.getString("classname"));
							}else{
								json.put("classname","");

						}} catch (JSONException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						//取出厂商
						try {
							if(rs.getString("company")!=""){
								json.put("company",rs.getString("company"));
							}else{
								json.put("company","");
							}
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						jsonarray.put(json);
					}
					try {
						obj.put("filedata",jsonarray);
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
		return obj;
	}

}
