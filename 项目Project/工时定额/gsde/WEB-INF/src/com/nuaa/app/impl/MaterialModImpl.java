package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.MaterialMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
//import com.nuaa.test.FileIn;

public class MaterialModImpl implements MaterialMod{

	@Override
	public JSONObject QueryAllMaterial(HashMap hashmap) {
		// TODO Auto-generated method stub
		int start = Integer.parseInt((String)hashmap.get("start"));
		int limit = Integer.parseInt((String)hashmap.get("limit"));
		String filter = (String)hashmap.get("filter");
		String order = (String)hashmap.get("order");	
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sql1 = "select count(*) as ct from T_MATERIAL where "+ filter;
		String sql2 = "select * from (select * from  (select * from T_MATERIAL where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from T_MATERIAL where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql1);
		Logger.debug(sql2);
		try {
			DbUtil.execute(sql1,new IResultSetProcessor(){
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			DbUtil.execute(sql2,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
				while(rs.next()){
					JSONObject json;
					try {
						json = new JSONObject();
						json.put("id", rs.getString("id"));
						
						if(rs.getString("bianhao")!=""){
							json.put("bianhao", rs.getString("bianhao"));
						}
						if(rs.getString("mingcheng")!=""){
							json.put("mingcheng", rs.getString("mingcheng"));
						}if(rs.getString("rechuli")!=""){
							json.put("rechuli", rs.getString("rechuli"));
						}if(rs.getString("yinyong")!=""){
							json.put("yinyong", rs.getString("yinyong"));
						}if(rs.getString("leixing")!=""){
							json.put("leixing", rs.getString("leixing"));
						}if(rs.getString("yingdu")!=""){
							json.put("yingdu", rs.getString("yingdu"));
						}if(rs.getString("kangla")!=""){
							json.put("kangla", rs.getString("kangla"));
						}if(rs.getString("yanshen")!=""){
							json.put("yanshen", rs.getString("yanshen"));
						}if(rs.getString("chongji")!=""){
							json.put("chongji", rs.getString("chongji"));
						}if(rs.getString("midu")!=""){
							json.put("midu", rs.getString("midu"));
						}if(rs.getString("repengzhang")!=""){
							json.put("repengzhang", rs.getString("repengzhang"));
						}if(rs.getString("daore")!=""){
							json.put("daore", rs.getString("daore"));
						}if(rs.getString("qufu")!=""){
							json.put("qufu", rs.getString("qufu"));
						}if(rs.getString("company")!=""){
							json.put("company", rs.getString("company"));
						}if(rs.getString("beizhu")!=""){
							json.put("beizhu", rs.getString("beizhu"));
						}if(rs.getString("paihao")!=""){
							json.put("paihao", rs.getString("paihao"));
						}
						jsonarray.put(json);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
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
		//////////////////////////
		//FileIn file = new FileIn();
		//file.In();
		/////////////////////////////
		return obj;
	}

	@Override
	public String AddMaterial(final HashMap hashmap) {
		// TODO Auto-generated method stub
		//final String bianhao = hashmap.get("bianhao").toString();
		final String paihao = hashmap.get("paihao").toString();
		final String rechuli = hashmap.get("rechuli").toString();
		String sql = "select count(*) as ct from T_MATERIAL where bianhao = '"+paihao+"'and rechuli = '"+rechuli+"'";
		final String[] count = new String[1];
		String result = "true";
		Logger.debug(sql);
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					while(rs.next()){
					count[0] = rs.getString("ct");
					}
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if("0".equals(count[0])){
			final String id = UUID.randomUUID().toString();
			String[] sql1 = new String[1];
			sql1[0] = "insert into T_MATERIAL (id,bianhao,mingcheng,rechuli,yinyong,leixing,yingdu,kangla,yanshen,chongji,midu,repengzhang,daore,qufu,beizhu,company,paihao) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Logger.debug(sql1[0]);
			try {
				DbUtil.executeBatchs(sql1,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)throws SQLException {
						// TODO Auto-generated method stub
						pstmts[0].setString(1,id);
						if(hashmap.get("paihao").toString() != ""){
							pstmts[0].setString(2,hashmap.get("paihao").toString());
							}else{
								pstmts[0].setString(2,"");
							}
						if(hashmap.get("mingcheng").toString() != ""){
						pstmts[0].setString(3,hashmap.get("mingcheng").toString());
						}else{
							pstmts[0].setString(3,"");
						}if(hashmap.get("rechuli").toString() != ""){
							pstmts[0].setString(4,hashmap.get("rechuli").toString());
						}else{
							pstmts[0].setString(4,"");
						}
						if(hashmap.get("yinyong").toString() != ""){
							pstmts[0].setString(5,hashmap.get("yinyong").toString());
						}else{
							pstmts[0].setString(5,"");
						}if(hashmap.get("leixing").toString() != ""){
							pstmts[0].setString(6,hashmap.get("leixing").toString());
						}else{
							pstmts[0].setString(6,"");
						}if(hashmap.get("yingdu").toString() != ""){
							pstmts[0].setString(7,hashmap.get("yingdu").toString());
						}else{
							pstmts[0].setString(7,"");
						}if(hashmap.get("kangla").toString() != ""){
							pstmts[0].setString(8,hashmap.get("kangla").toString());
						}else{
							pstmts[0].setString(8,"");
						}if(hashmap.get("yanshen").toString() != ""){
							pstmts[0].setString(9,hashmap.get("yanshen").toString());
						}else{
							pstmts[0].setString(9,"");
						}if(hashmap.get("chongji").toString() != ""){
							pstmts[0].setString(10,hashmap.get("chongji").toString());
						}else{
							pstmts[0].setString(10,"");
						}if(hashmap.get("midu").toString() != ""){
							pstmts[0].setString(11,hashmap.get("midu").toString());
						}else{
							pstmts[0].setString(11,"");
						}if(hashmap.get("repengzhang").toString() != ""){
							pstmts[0].setString(12,hashmap.get("repengzhang").toString());
						}else{
							pstmts[0].setString(12,"");
						}if(hashmap.get("daore").toString() != ""){
							pstmts[0].setString(13,hashmap.get("daore").toString());
						}else{
							pstmts[0].setString(13,"");
						}if(hashmap.get("qufu").toString() != ""){
							pstmts[0].setString(14,hashmap.get("qufu").toString());
						}else{
							pstmts[0].setString(14,"");
						}if(hashmap.get("company").toString() != ""){
							pstmts[0].setString(15,hashmap.get("company").toString());
						}else{
							pstmts[0].setString(15,"");
						}if(hashmap.get("beizhu").toString() != ""){
							pstmts[0].setString(16,hashmap.get("beizhu").toString());
						}else{
							pstmts[0].setString(16,"");
						}if(hashmap.get("paihao").toString() != ""){
							pstmts[0].setString(17,hashmap.get("paihao").toString());
						}else{
							pstmts[0].setString(17,"");
						}
						pstmts[0].addBatch();
					}
				});
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "false";
			}
		}else{
			result = "repeat";
		}
		return result;
	}

	@Override
	public JSONObject ViewMaterial(HashMap hashmap) {
		// TODO Auto-generated method stub
		final String id = hashmap.get("id").toString();
		String sql = "select * from T_MATERIAL where id = '"+ id +"'";
		final JSONObject json = new JSONObject();
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					try {
						while(rs.next()){
							//json.put("bianhao", rs.getString("bianhao"));
							if(rs.getString("paihao")!=""){
								json.put("paihao", rs.getString("paihao"));
							}
							if(rs.getString("mingcheng")!=""){
								json.put("mingcheng", rs.getString("mingcheng"));
							}if(rs.getString("rechuli")!=""){
								json.put("rechuli", rs.getString("rechuli"));
							}if(rs.getString("yinyong")!=""){
								json.put("yinyong", rs.getString("yinyong"));
							}if(rs.getString("leixing")!=""){
								json.put("leixing", rs.getString("leixing"));
							}if(rs.getString("yingdu")!=""){
								json.put("yingdu", rs.getString("yingdu"));
							}if(rs.getString("kangla")!=""){
								json.put("kangla", rs.getString("kangla"));
							}if(rs.getString("yanshen")!=""){
								json.put("yanshen", rs.getString("yanshen"));
							}if(rs.getString("chongji")!=""){
								json.put("chongji", rs.getString("chongji"));
							}if(rs.getString("midu")!=""){
								json.put("midu", rs.getString("midu"));
							}if(rs.getString("repengzhang")!=""){
								json.put("repengzhang", rs.getString("repengzhang"));
							}if(rs.getString("daore")!=""){
								json.put("daore", rs.getString("daore"));
							}if(rs.getString("qufu")!=""){
								json.put("qufu", rs.getString("qufu"));
							}if(rs.getString("beizhu")!=""){
								json.put("beizhu", rs.getString("beizhu"));
							}if(rs.getString("company")!=""){
								json.put("company", rs.getString("company"));
							}if(rs.getString("paihao")!=""){
								json.put("paihao", rs.getString("paihao"));
							}
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
		
		return json;
	}

	@Override
	public String DelMaterial(HashMap hashmap) {
		// TODO Auto-generated method stub
		final String id = hashmap.get("id").toString();
		String[] sql = new String[1];
		String result = "true";
		sql[0] = "delete from T_MATERIAL where id in ('',"+ id +")";
		Logger.debug(sql[0]);
		
		try {
			DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts) throws SQLException {
					// TODO Auto-generated method stub
					pstmts[0].addBatch();
				}
			});
			result = "true";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "false";
		}
		return result;
	}

	@Override
	public String EditMaterial(final HashMap hashmap) {
		// TODO Auto-generated method stub
		final String id = hashmap.get("id").toString();
		String result = "true";
		final String paihao = hashmap.get("paihao").toString();
		final String rechuli = hashmap.get("rechuli").toString();
		String sql = "select count(*) as ct from T_MATERIAL where paihao = '"+paihao+"'and rechuli = '"+rechuli+"'and id!='"+id+"'";
		Logger.debug(sql);
		final String [] count = new String[1];
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
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
			String[] sqls = new String[1];
			sqls[0] = "update T_MATERIAL set bianhao=?,mingcheng=?,rechuli=?,yinyong=?,leixing=?,yingdu=?,kangla=?,yanshen=?,chongji=?,midu=?,repengzhang=?,daore=?,qufu=?,beizhu=?,company=?,paihao=? where id = '"+ id +"'";
			try {
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)throws SQLException {
						// TODO Auto-generated method stub
						if(hashmap.get("bianhao").toString() !=""){
							pstmts[0].setString(1,hashmap.get("bianhao").toString());
						}else{
							pstmts[0].setString(1,"");
						}
						if(hashmap.get("mingcheng").toString() != ""){
						pstmts[0].setString(2,hashmap.get("mingcheng").toString());
						}else{
							pstmts[0].setString(2,"");
						}if(hashmap.get("rechuli").toString() != ""){
							pstmts[0].setString(3,hashmap.get("rechuli").toString());
						}else{
							pstmts[0].setString(3,"");
						}
						if(hashmap.get("yinyong").toString() != ""){
							pstmts[0].setString(4,hashmap.get("yinyong").toString());
						}else{
							pstmts[0].setString(4,"");
						}if(hashmap.get("leixing").toString() != ""){
							pstmts[0].setString(5,hashmap.get("leixing").toString());
						}else{
							pstmts[0].setString(5,"");
						}if(hashmap.get("yingdu").toString() != ""){
							pstmts[0].setString(6,hashmap.get("yingdu").toString());
						}else{
							pstmts[0].setString(6,"");
						}if(hashmap.get("kangla").toString() != ""){
							pstmts[0].setString(7,hashmap.get("kangla").toString());
						}else{
							pstmts[0].setString(7,"");
						}if(hashmap.get("yanshen").toString() != ""){
							pstmts[0].setString(8,hashmap.get("yanshen").toString());
						}else{
							pstmts[0].setString(8,"");
						}if(hashmap.get("chongji").toString() != ""){
							pstmts[0].setString(9,hashmap.get("chongji").toString());
						}else{
							pstmts[0].setString(9,"");
						}if(hashmap.get("midu").toString() != ""){
							pstmts[0].setString(10,hashmap.get("midu").toString());
						}else{
							pstmts[0].setString(10,"");
						}if(hashmap.get("repengzhang").toString() != ""){
							pstmts[0].setString(11,hashmap.get("repengzhang").toString());
						}else{
							pstmts[0].setString(11,"");
						}if(hashmap.get("daore").toString() != ""){
							pstmts[0].setString(12,hashmap.get("daore").toString());
						}else{
							pstmts[0].setString(12,"");
						}if(hashmap.get("qufu").toString() != ""){
							pstmts[0].setString(13,hashmap.get("qufu").toString());
						}else{
							pstmts[0].setString(13,"");
						}if(hashmap.get("company").toString() != ""){
							pstmts[0].setString(14,hashmap.get("company").toString());
						}else{
							pstmts[0].setString(14,"");
						}if(hashmap.get("beizhu").toString() != ""){
							pstmts[0].setString(15,hashmap.get("beizhu").toString());
						}else{
							pstmts[0].setString(15,"");
						}if(hashmap.get("paihao").toString() != ""){
							pstmts[0].setString(16,hashmap.get("paihao").toString());
						}else{
							pstmts[0].setString(16,"");
						}
						pstmts[0].addBatch();
					}
				});
				result = "true";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "false";
			}
		}else{
			result = "repeat";
		}
		return result;
	}

	@Override
	public JSONObject getQueryMaterialAll() {
		// TODO Auto-generated method stub
		String sql = "select * from T_MATERIAL";
		final JSONObject jsonObj = new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("paihao", rs.getString("paihao"));
							
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


}
