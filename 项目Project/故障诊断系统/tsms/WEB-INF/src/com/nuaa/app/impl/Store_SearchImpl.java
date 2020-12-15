package com.nuaa.app.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.Store_Search;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;

public class Store_SearchImpl implements Store_Search{
	//计算一种材料即typeid不同的材料的预算量函数
	//首先是检索日期要小于当天,否则是报废的材料
	//先检索vmaterial_com不同mnum和typeid的记录数，取出mnum和typeid，从入库记录表中取出并计算总的入库量
	//从出库记录表中取出并计算总的出库数量
	//从移库记录表中取出并计算移库总数量
	public JSONObject QueryAllStore(HashMap hashmap) {
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		/*SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String date = s.format(new Date());*/
		//System.out.println(date);
		
		final String filter = (String) hashmap.get("filter");
		final String order = (String) hashmap.get("order");
		
	
		String sql = "select count(distinct typeid) as ct from vmaterial_com where " + filter +" and valiadate > sysdate";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
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
		//
		String sql1 = "select distinct mnum,typeid from vmaterial_com where " + filter + "and valiadate > sysdate order by "+ order;
		try {
			DbUtil.execute(sql1,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						final JSONObject json = new JSONObject();
						try {
							
							json.put("mnum",rs.getString("mnum"));
							json.put("typeid",rs.getString("typeid"));
							
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String typeid = rs.getString("typeid");
						String sql = "select * from t_material where id ='"+typeid+"'";
						DbUtil.execute(sql,new IResultSetProcessor(){
							public void process(ResultSet rs)throws SQLException {
								while(rs.next()){
								try {
									String mnum = rs.getString("mnum");
									String typeid = rs.getString("id");
									json.put("name",rs.getString("name"));
									json.put("classname",rs.getString("classname"));
									json.put("modelnum",rs.getString("modelnum"));
									json.put("spec",rs.getString("spec"));
									json.put("minstore",rs.getString("minstore"));
									json.put("maxstore",rs.getString("maxstore"));
									json.put("buycycle",rs.getString("buycycle"));
									json.put("monthuse",rs.getString("monthuse"));
									json.put("guaranteedate",rs.getString("guaranteedate"));
									json.put("singlepur",rs.getString("singlepur"));
									String sqlsumin = "select incount from vmaterial_com where mnum = '" + mnum+"'and typeid = '"+ typeid +"'";
									DbUtil.execute(sqlsumin,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											int insum = 0;
											while(rs.next()){
												insum += rs.getInt("incount");
											}
											try {
												json.put("incount",insum);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
										
									});
									
									String sql = "select outcount from v_matout_mnum where mnum = '" + mnum +"'and typeid = '"+ typeid+"'";
									DbUtil.execute(sql,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											int outsum = 0;
											while(rs.next()){
												outsum += rs.getInt("outcount");
											}
											try {
												json.put("outcount",outsum);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
										
									});
									/*String sql1 = "select changecount from v_matchange_com where mnum = '" + mnum +"'";
									DbUtil.execute(sql1,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											int changesum = 0;
											while(rs.next()){
												changesum += rs.getInt("changecount");
											}
											try {
												json.put("changecount",changesum);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
										
									});*/
									
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							}
							
						});
						//判断采购周期是不是1十一则继续执行，不是1则跳出循环
						/*try {
							if(("2".equals(json.get("buycycle")))||("3".equals(json.get("buycycle")))||("6".equals(json.get("buycycle")))){
								continue;
							}
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						//判断在库量是否为0，为0则赋值0，不为0则赋值nowcount
						try {
							int nowcount = 0;
							nowcount = json.getInt("incount") - json.getInt("outcount") /*- json.getInt("changecount")*/;
							/*System.out.println(json.getInt("incount"));
							System.out.println(json.getInt("outcount"));
							System.out.println(nowcount);
							System.out.println("()()())))))");*/
							if(nowcount<=0){
								json.put("nowcount",0);
								/*obj.put("totalProperty",obj.getInt("totalProperty")-1);
								continue;*/
								
								if(json.getInt("nowcount")<=json.getInt("minstore")){
									int thiscount = 0;
									thiscount = json.getInt("singlepur") - json.getInt("nowcount");
									//判断本次预算量是否为负数，为负数则赋值0，否则赋值thiscount
									if(thiscount<=0){
									json.put("thiscount",0);
									}
									else
									json.put("thiscount",thiscount);	
									}else{
										obj.put("totalProperty",obj.getInt("totalProperty")-1);
										continue;
									}
							}else{
							json.put("nowcount",nowcount);
							
							if(json.getInt("nowcount")<=json.getInt("minstore")){
							int thiscount = 0;
							thiscount = json.getInt("singlepur") - json.getInt("nowcount");
							//判断本次预算量是否为负数，为负数则赋值0，否则赋值thiscount
							if(thiscount<=0){
							json.put("thiscount",0);
							continue;
							}
							else
							json.put("thiscount",thiscount);	
							}else{
								obj.put("totalProperty",obj.getInt("totalProperty")-1);
								continue;
							}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							if(json.getInt("thiscount")<=0){
								/*obj.put("totalProperty", obj.getInt("totalProperty")-1);*/
								continue;
							}else{
							jsonarray.put(json);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			});
			try {
				obj.put("filedata",jsonarray);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return obj;
	}

	@Override
	public JSONObject QueryAllStore1() {
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		/*SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		String date = s.format(new Date());*/
		//System.out.println(date);
		
		/*final String filter = (String) hashmap.get("filter");
		final String order = (String) hashmap.get("order");
		*/
	
	/*	String sql = "select count(distinct typeid) as ct from vmaterial_com where valiadate > sysdate";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						try {
							obj.put("totalProperty",rs.getString("ct"));
							System.out.println(obj.getInt("totalProperty"));
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
		*/
		String sql1 = "select distinct mnum,typeid from vmaterial_com where valiadate > sysdate";
		try {
			DbUtil.execute(sql1,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					int j = 0;
					while(rs.next()){
						final JSONObject json = new JSONObject();
						try {
							json.put("mnum",rs.getString("mnum"));
							json.put("typeid",rs.getString("typeid"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String typeid = rs.getString("typeid");
						String sql = "select * from t_material where id ='"+typeid+"'";
						DbUtil.execute(sql,new IResultSetProcessor(){
							public void process(ResultSet rs)throws SQLException {
								while(rs.next()){
								try {
									String mnum = rs.getString("mnum");
									String typeid = rs.getString("id");
									json.put("name",rs.getString("name"));
									json.put("classname",rs.getString("classname"));
									json.put("modelnum",rs.getString("modelnum"));
									json.put("spec",rs.getString("spec"));
									json.put("minstore",rs.getString("minstore"));
									json.put("maxstore",rs.getString("maxstore"));
									json.put("buycycle",rs.getString("buycycle"));
									json.put("monthuse",rs.getString("monthuse"));
									json.put("guaranteedate",rs.getString("guaranteedate"));
									json.put("singlepur",rs.getString("singlepur"));
									String sqlsumin = "select incount from vmaterial_com where mnum = '" + mnum+"'and typeid = '"+ typeid +"'and kuname='海波库'";
									DbUtil.execute(sqlsumin,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											int insum = 0;
											while(rs.next()){
												insum += rs.getInt("incount");
											}
											try {
												json.put("incount",insum);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
										
									});
									
									String sql = "select outcount from v_matout_mnum where mnum = '" + mnum +"'and typeid = '"+ typeid+"'";
									DbUtil.execute(sql,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											int outsum = 0;
											while(rs.next()){
												outsum += rs.getInt("outcount");
											}
											try {
												json.put("outcount",outsum);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
										
									});
									/*String sql1 = "select changecount from v_matchange_com where mnum = '" + mnum +"'";
									DbUtil.execute(sql1,new IResultSetProcessor(){
										public void process(ResultSet rs)throws SQLException {
											int changesum = 0;
											while(rs.next()){
												changesum += rs.getInt("changecount");
											}
											try {
												json.put("changecount",changesum);
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
										
									});*/
									
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							}
							
						});
						//判断采购周期是不是1十一则继续执行，不是1则跳出循环
						/*try {
							if(("2".equals(json.get("buycycle")))||("3".equals(json.get("buycycle")))||("6".equals(json.get("buycycle")))){
								continue;
							}
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						//判断在库量是否为0，为0则赋值0，不为0则赋值nowcount
						try {
							int nowcount = 0;
							nowcount = json.getInt("incount") - json.getInt("outcount")/* - json.getInt("changecount")*/;
							if(nowcount<=0){
								json.put("nowcount",0);
								/*obj.put("totalProperty",obj.getInt("totalProperty")-1);
								continue;*/
								if(json.getInt("nowcount")<=json.getInt("minstore")){
									int thiscount = 0;
									thiscount = json.getInt("singlepur") - json.getInt("nowcount");
									//判断本次预算量是否为负数，为负数则赋值0，否则赋值thiscount
									if(thiscount<=0){
									json.put("thiscount",0);
									continue;
									}
									else
									json.put("thiscount",thiscount);	
									}else{
										/*obj.put("totalProperty",obj.getInt("totalProperty")-1);*/
										continue;
									}
							}else{
							json.put("nowcount",nowcount);
							System.out.println(json.getInt("incount"));
							System.out.println(json.getInt("outcount"));
							System.out.println(nowcount);
							if(json.getInt("nowcount")<=json.getInt("minstore")){
							int thiscount = 0;
							thiscount = json.getInt("singlepur") - json.getInt("nowcount");
							//判断本次预算量是否为负数，为负数则赋值0，否则赋值thiscount
							if(thiscount<=0){
							json.put("thiscount",0);
							continue;
							}
							else{
								json.put("thiscount",thiscount);
							}
							}else{
								/*obj.put("totalProperty",obj.getInt("totalProperty")-1);*/
								continue;
							}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							if(json.getInt("thiscount")<0){
								continue;
							}else{
								j++;
							jsonarray.put(json);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					try {
						System.out.println(j);
						obj.put("totalProperty",j);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			try {
				obj.put("filedata",jsonarray);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return obj;
	}

}
