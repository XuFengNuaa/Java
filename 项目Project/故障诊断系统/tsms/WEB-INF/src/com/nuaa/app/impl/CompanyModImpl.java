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
import com.nuaa.app.CompanyMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class CompanyModImpl implements CompanyMod{
	       
		    public String addCompany(HashMap addcompanyHMap) {
			// TODO 自动生成方法存根
			final HashMap addcompanyHashMap=addcompanyHMap;
			final String id=UUID.randomUUID().toString();  //自动生成id代码
			//Calendar c = Calendar.getInstance();
			//final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String sql ="";	
			String company=(String)addcompanyHMap.get("company");
			Logger.debug(sql);
			//final JSONArray idArray=new JSONArray();
			
			sql="select count(*) as ct from T_COMPANY where company='"+company+"'";
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
					sqls[0]= "insert into T_COMPANY(id,company,address,contact_person,phone,email,remark) values(?,?,?,?,?,?,?)";
					DbUtil.executeBatchs(sqls,
							new IArrayPreparedStatementProcessor() {
								public void process(
								PreparedStatement[] pstmts)
								throws SQLException {
								pstmts[0].setString(1,id);
							if(addcompanyHashMap.get("company")!=null){
								pstmts[0].setString(2,(String)addcompanyHashMap.get("company"));//userid
							}else{
								pstmts[0].setString(2," ");//userid
							}
							if(addcompanyHashMap.get("address")!=null){
								pstmts[0].setString(3,(String)addcompanyHashMap.get("address"));//username
							}else{
								pstmts[0].setString(3," ");//username
							}
							if(addcompanyHashMap.get("contact_person")!=null){
								pstmts[0].setString(4,(String)addcompanyHashMap.get("contact_person"));//pwd
							}else{
								pstmts[0].setString(4," ");//pwd
							}
							if(addcompanyHashMap.get("phone")!=null){
								pstmts[0].setString(5,(String)addcompanyHashMap.get("phone"));//email
							}else{
								pstmts[0].setString(5," ");//email
							}
							if(addcompanyHashMap.get("email")!=null){
								pstmts[0].setString(6,(String)addcompanyHashMap.get("email"));//mobilephone
							}else{
								pstmts[0].setString(6," ");//mobilephone
							}
							if(addcompanyHashMap.get("remark")!=null){
								pstmts[0].setString(7,(String)addcompanyHashMap.get("remark"));//telephone
							}else{
								pstmts[0].setString(7," ");//telephone
							}
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
		public JSONObject getQueryCompany(HashMap queryCompanyMap) {
			// TODO 自动生成方法存根
			HashMap queryHashMap=queryCompanyMap;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select  count(*) as ct  from  T_COMPANY where "+(String)queryHashMap.get("filter");
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
			sql="select * from (select * from  (select * from T_COMPANY where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from T_COMPANY where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("id", rs.getString("id"));
								obj.put("company", rs.getString("company"));
								obj.put("address", rs.getString("address"));
								obj.put("contact_person", rs.getString("contact_person"));
								obj.put("phone",rs.getString("phone"));
								obj.put("email", rs.getString("email"));
								obj.put("remark", rs.getString("remark"));
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
        
		public JSONObject getQueryCompanyAll() {
			// TODO 自动生成方法存根
			
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select * from T_COMPANY ";
			
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								JSONObject obj=new JSONObject();
								obj.put("id", rs.getString("id"));
								obj.put("company", rs.getString("company"));
								
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

		
		public String delCompany(HashMap delCompanyHMap) {
			// TODO 自动生成方法存根
			final String id=(String)delCompanyHMap.get("id");
			final String []result=new String[1];
			result[0]="true";
			String []sqls=new String[1];
			try {
				sqls[0]="delete from T_COMPANY where id in('',"+id+")";
				DbUtil.executeBatchs(sqls,
						new IArrayPreparedStatementProcessor() {
							public void process(
							PreparedStatement[] pstmts)
							throws SQLException {						
								pstmts[0].addBatch();
							String []sql1=new String[1];
							String []sql2=new String[1];
							String []sql3=new String[1];
							String []sql4=new String[1];
							sql1[0]="update T_M_TYPE set companyid='' where companyid in ("+id+")";
							sql2[0]="update T_MT_TYPE set companyid='' where companyid in ("+id+")";
							sql3[0]="update T_CUTTER set companyid='' where companyid in ("+id+")";
							sql4[0]="update T_CT set companyid='' where companyid in ("+id+")";
							try {
								DbUtil.executeBatchs(sql1,new IArrayPreparedStatementProcessor(){
									public void process(PreparedStatement[] pstmt)	throws SQLException {																				
										pstmt[0].addBatch();
									}										
								});										
							}catch (SQLException e) {
							// TODO 自动生成 catch 块
								e.printStackTrace();
								result[0]="exception";
							}
							try {
								DbUtil.executeBatchs(sql2,new IArrayPreparedStatementProcessor(){
									public void process(PreparedStatement[] pstmt)	throws SQLException {																				
										pstmt[0].addBatch();
									}										
								});										
							}catch (SQLException e) {
							// TODO 自动生成 catch 块
								e.printStackTrace();
								result[0]="exception";
							}			
							try {
								DbUtil.executeBatchs(sql3,new IArrayPreparedStatementProcessor(){
									public void process(PreparedStatement[] pstmt)	throws SQLException {																				
										pstmt[0].addBatch();
									}										
								});										
							}catch (SQLException e) {
							// TODO 自动生成 catch 块
								e.printStackTrace();
								result[0]="exception";
							}			
							try {
								DbUtil.executeBatchs(sql4,new IArrayPreparedStatementProcessor(){
									public void process(PreparedStatement[] pstmt)	throws SQLException {																				
										pstmt[0].addBatch();
									}										
								});										
							}catch (SQLException e) {
							// TODO 自动生成 catch 块
								e.printStackTrace();
								result[0]="exception";
							}			
						}
				});			 
			} catch (Exception ex1) {
				ex1.printStackTrace();
				result[0]="exception";
			}	
		 return result[0];	
		}

		public String editCompany(HashMap editCompanyHMap) {
			// TODO 自动生成方法存根
			final String id=(String)editCompanyHMap.get("id");
			final String company=(String)editCompanyHMap.get("company");
			final String address=(String)editCompanyHMap.get("address");
			final String contact_person=(String)editCompanyHMap.get("contact_person");
			final String phone=(String)editCompanyHMap.get("phone");
			final String email=(String)editCompanyHMap.get("email");
			final String remark=(String)editCompanyHMap.get("remark");
			
			String sql="";
			sql="select count(*) as ct from T_COMPANY where company='"+company+"' and id!='"+id+"'";
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
			String[] sqls = new String[1];	
			sqls[0] = "update T_COMPANY set company=?,address=?,contact_person=?,phone=?,email=?,remark=? where id='"+id+"'";
			System.out.println(sqls[0]);
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)	throws SQLException {			
						pstmts[0].setString(1,company);
						pstmts[0].setString(2,address);
						pstmts[0].setString(3,contact_person);
						pstmts[0].setString(4,phone);
						pstmts[0].setString(5,email);
						pstmts[0].setString(6,remark);
						pstmts[0].addBatch();
					}
				});
				return "true";
			}else {
			 return "false";	
			}}
			catch (SQLException e) {
				e.printStackTrace();
				return "exception";
			}
		}

		public JSONObject viewCompany(HashMap viewCompanyMap) {
			// TODO 自动生成方法存根
			HashMap viewCompanyHashMap=viewCompanyMap;
			final JSONObject jsonObj=new JSONObject();
			final String id=(String)viewCompanyHashMap.get("id");
			String sql="";
			sql="select * from T_COMPANY where id='"+id+"'"; 
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								jsonObj.put("id", rs.getString("id"));
								jsonObj.put("company", rs.getString("company"));
								jsonObj.put("address", rs.getString("address"));
								jsonObj.put("contact_person", rs.getString("contact_person"));
								jsonObj.put("phone", rs.getString("phone"));
								jsonObj.put("email", rs.getString("email"));
								jsonObj.put("remark", rs.getString("remark"));
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
		
		
	}

	

