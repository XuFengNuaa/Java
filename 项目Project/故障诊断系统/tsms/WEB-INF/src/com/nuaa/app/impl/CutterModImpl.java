package com.nuaa.app.impl;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import org.apache.commons.fileupload.FileItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.FileState;
import com.nuaa.app.CutterMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;

public class CutterModImpl implements CutterMod{
	  public String add_cutter(HashMap add_cutter_HMap) {
			// TODO 自动生成方法存根
			final HashMap add_cutterHashMap=add_cutter_HMap;
			final String id=UUID.randomUUID().toString();  //自动生成id,是javaJDK提供的一个自动生成主键的方法.每一台机器都不同
			String sql ="";	
			String mnum=(String)add_cutter_HMap.get("mnum");
			String name=(String)add_cutter_HMap.get("name");
			String pclass=(String)add_cutter_HMap.get("pclass");
			String bclass=(String)add_cutter_HMap.get("bclass");
			String type=(String)add_cutter_HMap.get("type");
			String spec=(String)add_cutter_HMap.get("spec");
			String remark=(String)add_cutter_HMap.get("remark");
			String iso_num=(String)add_cutter_HMap.get("iso_num");
			String series_num=(String)add_cutter_HMap.get("series_num");
			Logger.debug(sql);			
			final String[] count=new String[2];
			
			sql="select count(*) as ct from T_CUTTER where mnum='"+mnum+"'  ";			
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
					sqls[0]= "insert into T_CUTTER(id,mnum,iso_num,pclass,bclass,name,companyid,hq,rank_angle,c_mate,coat_mate,mini_qs,suit_mate1,suit_mate2,location,remark,e_diam,h_diam,e_leng,t_leng,tee_count,s_ang,tip_heig,bla_wide,c_leng,e_count,e_wide,t_form,c_way,t_type,spec,type,series_num,order_num,nc_count_in,gc_count_in,uc_count_in,use_freq,effect_length) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					DbUtil.executeBatchs(sqls,
							new IArrayPreparedStatementProcessor() {
								public void process(
								PreparedStatement[] pstmts)
								throws SQLException {
								pstmts[0].setString(1,id);
							if(add_cutterHashMap.get("mnum")!=null){
								pstmts[0].setString(2,(String)add_cutterHashMap.get("mnum"));//mnum
							}else{
								pstmts[0].setString(2,"");//mnum
							}
							if(add_cutterHashMap.get("iso_num")!=null){
								pstmts[0].setString(3,(String)add_cutterHashMap.get("iso_num"));//class
							}else{
								pstmts[0].setString(3,"");//class
							}
							if(add_cutterHashMap.get("pclass")!=null){
								pstmts[0].setString(4,(String)add_cutterHashMap.get("pclass"));//name
							}else{
								pstmts[0].setString(4,"");//name
							}
							if(add_cutterHashMap.get("bclass")!=null){
								pstmts[0].setString(5,(String)add_cutterHashMap.get("bclass"));//spec
							}else{
								pstmts[0].setString(5,"");//spec
							}
							if(add_cutterHashMap.get("name")!=null){
								pstmts[0].setString(6,(String)add_cutterHashMap.get("name"));//diviv
							}else{
								pstmts[0].setString(6,"");//diviv
							}
							if(add_cutterHashMap.get("companyid")!=null){
								pstmts[0].setString(7,(String)add_cutterHashMap.get("companyid"));//companyid
							}else{
								pstmts[0].setString(7,"");//company
							}
							if(add_cutterHashMap.get("hq")!=null){
								pstmts[0].setString(8,(String)add_cutterHashMap.get("hq"));//mini_qs
							}else{
								pstmts[0].setString(8,"");//mini_qs
							}
							if(add_cutterHashMap.get("rank_angle")!=null){
								pstmts[0].setString(9,(String)add_cutterHashMap.get("rank_angle"));//hq
							}else{
								pstmts[0].setString(9,"");//hq
							}							
							if(add_cutterHashMap.get("c_mate")!=null){
								pstmts[0].setString(10,(String)add_cutterHashMap.get("c_mate"));//hq
							}else{
								pstmts[0].setString(10,"");//hq
							}
							if(add_cutterHashMap.get("coat_mate")!=null){
								pstmts[0].setString(11,(String)add_cutterHashMap.get("coat_mate"));//hq
							}else{
								pstmts[0].setString(11,"");//hq
							}
							if(add_cutterHashMap.get("mini_qs")!=null){
								pstmts[0].setString(12,(String)add_cutterHashMap.get("mini_qs"));//hq
							}else{
								pstmts[0].setString(12,"");//hq
							}
							if(add_cutterHashMap.get("suit_mate1")!=null){
								pstmts[0].setString(13,(String)add_cutterHashMap.get("suit_mate1"));//hq
							}else{
								pstmts[0].setString(13,"");//hq
							}
							if(add_cutterHashMap.get("suit_mate2")!=null){
								pstmts[0].setString(14,(String)add_cutterHashMap.get("suit_mate2"));//hq
							}else{
								pstmts[0].setString(14,"");//hq
							}
							if(add_cutterHashMap.get("location")!=null){
								pstmts[0].setString(15,(String)add_cutterHashMap.get("location"));//remark
							}else{
								pstmts[0].setString(15,"");//remark
							}
							if(add_cutterHashMap.get("remark")!=null){
								pstmts[0].setString(16,(String)add_cutterHashMap.get("remark"));//remark
							}else{
								pstmts[0].setString(16,"");//remark
							}
							if(add_cutterHashMap.get("e_diam")!=null){
								pstmts[0].setString(17,(String)add_cutterHashMap.get("e_diam"));//mnum								
							}else{
								pstmts[0].setString(17,"");//mnum								
							}							
							if(add_cutterHashMap.get("h_diam")!=null){
								pstmts[0].setString(18,(String)add_cutterHashMap.get("h_diam"));//class
							}else{
								pstmts[0].setString(18,"");//class
							}
							if(add_cutterHashMap.get("e_leng")!=null){
								pstmts[0].setString(19,(String)add_cutterHashMap.get("e_leng"));//name
							}else{
								pstmts[0].setString(19,"");//name
							}
							if(add_cutterHashMap.get("t_leng")!=null){
								pstmts[0].setString(20,(String)add_cutterHashMap.get("t_leng"));//spec
							}else{
								pstmts[0].setString(20,"");//spec
							}
							if(add_cutterHashMap.get("tee_count")!=null){
								pstmts[0].setString(21,(String)add_cutterHashMap.get("tee_count"));//diviv
							}else{
								pstmts[0].setString(21,"");//diviv
							}
							if(add_cutterHashMap.get("s_ang")!=null){
								pstmts[0].setString(22,(String)add_cutterHashMap.get("s_ang"));//companyid
							}else{
								pstmts[0].setString(22,"");//company
							}
							if(add_cutterHashMap.get("tip_heig")!=null){
								pstmts[0].setString(23,(String)add_cutterHashMap.get("tip_heig"));//mini_qs
							}else{
								pstmts[0].setString(23,"");//mini_qs
							}
							if(add_cutterHashMap.get("bla_wide")!=null){
								pstmts[0].setString(24,(String)add_cutterHashMap.get("bla_wide"));//hq
							}else{
								pstmts[0].setString(24,"");//hq
							}							
							if(add_cutterHashMap.get("c_leng")!=null){
								pstmts[0].setString(25,(String)add_cutterHashMap.get("c_leng"));//hq
							}else{
								pstmts[0].setString(25,"");//hq
							}
							if(add_cutterHashMap.get("e_count")!=null){
								pstmts[0].setString(26,(String)add_cutterHashMap.get("e_count"));//hq
							}else{
								pstmts[0].setString(26,"");//hq
							}
							if(add_cutterHashMap.get("e_wide")!=null){
								pstmts[0].setString(27,(String)add_cutterHashMap.get("e_wide"));//hq
							}else{
								pstmts[0].setString(27,"");//hq
							}
							if(add_cutterHashMap.get("t_form")!=null){
								pstmts[0].setString(28,(String)add_cutterHashMap.get("t_form"));//hq
							}else{
								pstmts[0].setString(28,"");//hq
							}
							if(add_cutterHashMap.get("c_way")!=null){
								pstmts[0].setString(29,(String)add_cutterHashMap.get("c_way"));//hq
							}else{
								pstmts[0].setString(29,"");//hq
							}
							if(add_cutterHashMap.get("t_type")!=null){
								pstmts[0].setString(30,(String)add_cutterHashMap.get("t_type"));//remark
							}else{
								pstmts[0].setString(30,"");//remark
							}
							if(add_cutterHashMap.get("spec")!=null){
								pstmts[0].setString(31,(String)add_cutterHashMap.get("spec"));//remark
							}else{
								pstmts[0].setString(31,"");//remark
							}
							if(add_cutterHashMap.get("type")!=null){
								pstmts[0].setString(32,(String)add_cutterHashMap.get("type"));//hq
							}else{
								pstmts[0].setString(32,"");//hq
							}
							if(add_cutterHashMap.get("series_num")!=null){
								pstmts[0].setString(33,(String)add_cutterHashMap.get("series_num"));//hq
							}else{
								pstmts[0].setString(33,"");//hq
							}
							if(add_cutterHashMap.get("order_num")!=null){
								pstmts[0].setString(34,(String)add_cutterHashMap.get("order_num"));//hq
							}else{
								pstmts[0].setString(34,"");//hq
							}							
							if(add_cutterHashMap.get("nc_count_in")!=null){
								pstmts[0].setString(35,(String)add_cutterHashMap.get("nc_count_in"));//hq
							}else{
								pstmts[0].setString(35,"");//hq
							}
							if(add_cutterHashMap.get("gc_count_in")!=null){
								pstmts[0].setString(36,(String)add_cutterHashMap.get("gc_count_in"));//remark
							}else{
								pstmts[0].setString(36,"");//remark
							}
							if(add_cutterHashMap.get("uc_count_in")!=null){
								pstmts[0].setString(37,(String)add_cutterHashMap.get("uc_count_in"));//remark
							}else{
								pstmts[0].setString(37,"");//remark
							}
							if(add_cutterHashMap.get("use_freq")!=null){
								pstmts[0].setString(38,(String)add_cutterHashMap.get("use_freq"));//remark
							}else{
								pstmts[0].setString(38,"");//remark
							}
							if(add_cutterHashMap.get("effect_length")!=null){
								pstmts[0].setString(39,(String)add_cutterHashMap.get("effect_length"));//remark
							}else{
								pstmts[0].setString(39,"");//remark
							}
							   pstmts[0].addBatch();							
							
								}							
					});
				
					return "true";
				}
				else{
					return "false";
				}
		    	}catch (SQLException e) {
		 		e.printStackTrace();
				return "Exception";
		     } 			
		}
	  
			
		
	  public JSONObject getQuery_cutter(HashMap query_cutterMap) {
			// TODO 自动生成方法存根
		  //V_CUTTER2COM是t_cutter的视图，类似于继承过来
			HashMap query_cutterHashMap=query_cutterMap;
			final JSONObject jsonObj=new JSONObject();
			final JSONArray jsonArray=new JSONArray();
			String sql="";
			sql="select  count(*) as ct  from  V_CUTTER2COM where "+(String)query_cutterMap.get("filter");
			System.out.print("sql is : " +sql);
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
			int start=Integer.parseInt((String)query_cutterHashMap.get("start"));
			int limit=Integer.parseInt((String)query_cutterHashMap.get("limit"));
			
			String filter=(String)query_cutterHashMap.get("filter");
			String order=(String)query_cutterHashMap.get("order");        
			sql="select * from (select * from  (select * from V_CUTTER2COM where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from V_CUTTER2COM where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
			System.out.print("sql2 is : " +sql);
			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){								
								JSONObject obj=new JSONObject();
								DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();	
							//	decimalformat.applyPattern("###.##");
								decimalformat.setMaximumFractionDigits(2);
								
								obj.put("id", rs.getString("id"));
								obj.put("mnum", rs.getString("mnum"));
								obj.put("iso_num", rs.getString("iso_num"));
								obj.put("pclass", rs.getString("pclass"));
								obj.put("bclass", rs.getString("bclass"));
								obj.put("name",rs.getString("name"));
								obj.put("company", rs.getString("company"));								
								obj.put("hq", rs.getString("hq"));
								obj.put("rank_angle",rs.getString("rank_angle"));
								obj.put("c_mate", rs.getString("c_mate"));
								obj.put("coat_mate", rs.getString("coat_mate"));
								obj.put("mini_qs", rs.getString("mini_qs"));
								obj.put("suit_mate1", rs.getString("suit_mate1"));
								obj.put("suit_mate2", rs.getString("suit_mate2"));
								obj.put("suit_mate3", rs.getString("suit_mate3"));
								obj.put("use_freq", rs.getString("use_freq"));
								obj.put("location", rs.getString("location"));
								obj.put("remark",rs.getString("remark"));
								obj.put("e_diam", rs.getString("e_diam"));							
								obj.put("h_diam",rs.getString("h_diam"));
								obj.put("e_leng",rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
								obj.put("t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));
								obj.put("tee_count",rs.getString("tee_count"));
								obj.put("s_ang", rs.getString("s_ang"));
								obj.put("tip_heig", rs.getString("tip_heig")==null ? "":decimalformat.format(rs.getFloat("tip_heig")));
								obj.put("bla_wide", rs.getString("bla_wide")==null ? "":decimalformat.format(rs.getFloat("bla_wide")));
								obj.put("c_leng", rs.getString("c_leng")==null ? "":decimalformat.format(rs.getFloat("c_leng")));
								obj.put("e_count",rs.getString("e_count"));
								obj.put("e_wide", rs.getString("e_wide")==null ? "":decimalformat.format(rs.getFloat("e_wide")));
								obj.put("t_form", rs.getString("t_form"));
								obj.put("c_way", rs.getString("c_way"));
								obj.put("t_type", rs.getString("t_type"));
								obj.put("spec", rs.getString("spec"));	
								obj.put("type", rs.getString("type"));	
								obj.put("series_num", rs.getString("series_num"));	
								obj.put("order_num", rs.getString("order_num"));
								obj.put("effect_length", rs.getString("effect_length")==null ? "":decimalformat.format(rs.getFloat("effect_length")));
								obj.put("uc_count_in", rs.getString("uc_count_in"));
								obj.put("nc_count_in", rs.getString("nc_count_in"));
								obj.put("gc_count_in", rs.getString("gc_count_in"));	
								obj.put("companyid", rs.getString("companyid"));								
							      jsonArray.put(obj);
							      
							}	
								jsonObj.put("filedata", jsonArray);
							//	System.out.println(jsonObj.toString());
							//	System.out.println("*****************************");
							//	System.out.println(((JSONObject)(jsonArray.get(1))).get("hq"));
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
		};
		 
		public String edit_cutter(HashMap edit_cutterHMap) {
			// TODO 自动生成方法存根
			final String id=(String)edit_cutterHMap.get("id");
			final String mnum=(String)edit_cutterHMap.get("mnum");
			final String pclass=(String)edit_cutterHMap.get("pclass");
			final String bclass=(String)edit_cutterHMap.get("bclass");
			final String iso_num=(String)edit_cutterHMap.get("iso_num");
			final String name=(String)edit_cutterHMap.get("name");
			final String spec=(String)edit_cutterHMap.get("spec");
			final String remark=(String)edit_cutterHMap.get("remark");
			final String type=(String)edit_cutterHMap.get("type");
			final String series_num=(String)edit_cutterHMap.get("series_num");
			String result = "true";
			String sql ="";	
			final String[] count=new String[1];
            sql="select count(*) as ct from T_CUTTER where mnum='"+mnum+"'and id!='"+id+"'";
            Logger.debug(sql);
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
				  };	  
			try {
				if ("0".equals(count[0])){	
			String[] sqls = new String[1];	
			sqls[0] = "update T_CUTTER set mnum=?,pclass=?,bclass=?,iso_num=?,name=?,spec=?,remark=?,type=?,series_num=?  where id='"+id+"'";
			System.out.println(sqls[0]);
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)	throws SQLException {			
						pstmts[0].setString(1,mnum);
						pstmts[0].setString(2,pclass);
						pstmts[0].setString(3,bclass);
						pstmts[0].setString(4,iso_num);
						pstmts[0].setString(5,name);
						pstmts[0].setString(6,spec);
						pstmts[0].setString(7,remark);
						pstmts[0].setString(8,type);
						pstmts[0].setString(9,series_num);						
						pstmts[0].addBatch();
					}
				});
				result="true";
			}else{
				result="false";
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				result="repeat";
				
			}
			return result;	
		}
		@Override
		public String del_cutter(HashMap del_HMap){// TODO Auto-generated method stub
			final String id = del_HMap.get("id").toString();
			String[] sql = new String[1];
			String result = "true";
			sql[0] = "delete from T_CUTTER where id in ('',"+ id +")";
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
			return result;}
		public String edit_grind_cutter(HashMap edit_grdcutterHMap) {
			// TODO 自动生成方法存根			
			final String id=(String)edit_grdcutterHMap.get("id");		
			final String pclass=(String)edit_grdcutterHMap.get("pclass");			
			final String bclass=(String)edit_grdcutterHMap.get("bclass");			
			final String name=(String)edit_grdcutterHMap.get("name");
			final String gc_count_in=(String)edit_grdcutterHMap.get("gc_count_in");				
			final String suit_mate1=(String)edit_grdcutterHMap.get("suit_mate1");		
			final String suit_mate2=(String)edit_grdcutterHMap.get("suit_mate2");		
			final String suit_mate3=(String)edit_grdcutterHMap.get("suit_mate3");			
			final String rank_angle=(String)edit_grdcutterHMap.get("rank_angle");	
			final String c_mate=(String)edit_grdcutterHMap.get("c_mate");		
			final String coat_mate=(String)edit_grdcutterHMap.get("coat_mate");			
			final String e_diam=(String)edit_grdcutterHMap.get("e_diam");			
			final String h_diam=(String)edit_grdcutterHMap.get("h_diam");			
			final String e_leng=(String)edit_grdcutterHMap.get("e_leng");			
			final String t_leng=(String)edit_grdcutterHMap.get("t_leng");			
			final String e_count=(String)edit_grdcutterHMap.get("e_count");				
			final String remark=(String)edit_grdcutterHMap.get("remark");			
			final String hq=(String)edit_grdcutterHMap.get("hq");			
			final String companyid=(String)edit_grdcutterHMap.get("companyid");	
			final String location=(String)edit_grdcutterHMap.get("location");				
			final String effect_length=(String)edit_grdcutterHMap.get("effect_length");			
			
			try {				
			String[] sqls = new String[1];	
			sqls[0] = "update T_CUTTER set pclass=?,bclass=?,name=?,gc_count_in=?,suit_mate1=?,suit_mate2=?,rank_angle=?,c_mate=?,coat_mate=?,e_diam=?, h_diam=?,e_leng=?,t_leng=?,e_count=?,remark=?,hq=?,companyid=?,location=?,suit_mate3=?,effect_length=?  where id='"+id+"'";
			System.out.println(sqls[0]);
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)	throws SQLException {							
						pstmts[0].setString(1,pclass);
						pstmts[0].setString(2,bclass);						
						pstmts[0].setString(3,name);						
						pstmts[0].setString(4,gc_count_in);						
						pstmts[0].setString(5,suit_mate1);
						pstmts[0].setString(6,suit_mate2);
						pstmts[0].setString(7,rank_angle);
						pstmts[0].setString(8,c_mate);
						pstmts[0].setString(9,coat_mate);
						pstmts[0].setString(10,e_diam);
						pstmts[0].setString(11,h_diam);
						pstmts[0].setString(12,e_leng);
						pstmts[0].setString(13,t_leng);						
						pstmts[0].setString(14,e_count);						
						pstmts[0].setString(15,remark);						
						pstmts[0].setString(16,hq);
						pstmts[0].setString(17,companyid);
						pstmts[0].setString(18,location);						
						pstmts[0].setString(19,suit_mate3);							
						pstmts[0].setString(20,effect_length);						
						pstmts[0].addBatch();
					}
				});
				return "1";
				
			}
			catch (SQLException e) {
				e.printStackTrace();
				return "0";
			}
				
		}	
		
		public JSONObject view_cutter(HashMap view_cutterHMap) {
			// TODO 自动生成方法存根
			HashMap view_cutterHashMap=view_cutterHMap;
			final JSONObject jsonObj=new JSONObject();
			final String id=(String)view_cutterHashMap.get("id");
			String sql="";
			sql="select * from V_CUTTER2COM where id='"+id+"'"; 

			Logger.debug(sql);
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
								decimalformat.setMaximumFractionDigits(2);		
								jsonObj.put("id", rs.getString("id"));
								jsonObj.put("mnum", rs.getString("mnum"));
								jsonObj.put("pclass", rs.getString("pclass"));
								jsonObj.put("bclass", rs.getString("bclass"));
								jsonObj.put("iso_num", rs.getString("iso_num"));
								jsonObj.put("name",rs.getString("name"));
								jsonObj.put("nc_count_in", rs.getString("nc_count_in"));
								jsonObj.put("uc_count_in", rs.getString("uc_count_in"));
								jsonObj.put("gc_count_in", rs.getString("gc_count_in"));
								jsonObj.put("mini_qs", rs.getString("mini_qs"));							
								jsonObj.put("suit_mate1", rs.getString("suit_mate1"));
								jsonObj.put("suit_mate2", rs.getString("suit_mate2"));
								jsonObj.put("suit_mate3", rs.getString("suit_mate3"));
								jsonObj.put("use_freq", rs.getString("use_freq"));
								jsonObj.put("rank_angle", rs.getString("rank_angle"));
								jsonObj.put("c_mate", rs.getString("c_mate"));
								jsonObj.put("coat_mate",rs.getString("coat_mate"));
								jsonObj.put("e_diam", rs.getString("e_diam"));
								jsonObj.put("h_diam", rs.getString("h_diam"));
								jsonObj.put("e_leng", rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
								jsonObj.put("t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));								
								jsonObj.put("tee_count", rs.getString("tee_count"));
								jsonObj.put("s_ang", rs.getString("s_ang"));
								jsonObj.put("bla_wide", rs.getString("bla_wide")==null ? "":decimalformat.format(rs.getFloat("bla_wide")));
								jsonObj.put("tip_heig", rs.getString("tip_heig")==null ? "":decimalformat.format(rs.getFloat("tip_heig")));
								jsonObj.put("c_leng", rs.getString("c_leng")==null ? "":decimalformat.format(rs.getFloat("c_leng")));
								jsonObj.put("e_count",rs.getString("e_count"));
								jsonObj.put("t_form", rs.getString("t_form"));
								jsonObj.put("c_way", rs.getString("c_way"));
								jsonObj.put("t_type", rs.getString("t_type"));
								jsonObj.put("spec", rs.getString("spec"));
								jsonObj.put("remark", rs.getString("remark"));
								jsonObj.put("type", rs.getString("type"));
								jsonObj.put("series_num", rs.getString("series_num"));
								jsonObj.put("order_num", rs.getString("order_num"));						
								jsonObj.put("hq", rs.getString("hq"));
								jsonObj.put("companyid",rs.getString("companyid"));
								jsonObj.put("company",rs.getString("company"));
								jsonObj.put("location", rs.getString("location"));	
								jsonObj.put("effect_length", rs.getString("effect_length")==null ? "":decimalformat.format(rs.getFloat("effect_length")));	
								jsonObj.put("e_wide", rs.getString("e_wide")==null ? "":decimalformat.format(rs.getFloat("e_wide")));							
								
								
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
		
		
		public String del_cutter_amount(HashMap del_cutter_amountHMap) {
			// TODO 自动生成方法存根
			final JSONObject jsonObj=new JSONObject();	
			final JSONObject jsonObj1=new JSONObject();	
			final String lt_num = UUID.randomUUID().toString();  
			
			final String id=(String)del_cutter_amountHMap.get("id");			
			final String amount=(String)del_cutter_amountHMap.get("amount");
			final String bclass=(String)del_cutter_amountHMap.get("bclass");			
			final String now_count=(String)del_cutter_amountHMap.get("now_count");
			
			Calendar calendar = Calendar.getInstance();	    
		    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		    final String now = form.format(calendar.getTime())  ;
			
			String sql="";
			sql="select * from T_CUTTER where id='"+id+"'"; 
			final String []result=new String[1];
			result[0]="true";
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {					
						try {
							while(rs.next()){
								if("上轧辊总成".equals(bclass)){
										jsonObj1.put("id", rs.getString("id"));
										jsonObj1.put("gc_count_in", rs.getString("gc_count_in"));
										if(Integer.parseInt(rs.getString("gc_count_in"))!=Integer.parseInt(now_count)){
											result[0]="double";
										}else{
										final int re_amount = Integer.parseInt(jsonObj1.getString("gc_count_in"))-Integer.parseInt(amount);								
										String[] sql1 = new String[1];	
										sql1[0] = "update T_CUTTER set gc_count_in=?  where id='"+id+"'";
										String[] sql2 = new String[1];	
										sql2[0] = "insert into t_c_lt_apply_record(LT_NUM,TYPE_ID,COUNT,PL_NUM,LT,APPLY_TIME,OVER,leader_approve)  values(?,?,?,?,?,?,?,?)";	
										try {
											DbUtil.executeBatchs(sql1,new IArrayPreparedStatementProcessor(){
												public void process(PreparedStatement[] pstmts)	throws SQLException {	
													pstmts[0].setString(1,String.valueOf(re_amount));	
													pstmts[0].addBatch();
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
													pstmt[0].setString(1,lt_num);	
													pstmt[0].setString(2,id);
													pstmt[0].setString(3,amount);		
													pstmt[0].setString(4,"store");	
													pstmt[0].setString(5,"正常报废");	
													pstmt[0].setDate(6,java.sql.Date.valueOf(now));															
													pstmt[0].setString(7,"未处理");
													pstmt[0].setString(8,"未处理");
													pstmt[0].addBatch();
												}										
											});										
										}catch (SQLException e) {
										// TODO 自动生成 catch 块
											e.printStackTrace();
											result[0]="exception";
										}
									 }				
								}else{
								    jsonObj.put("id", rs.getString("id"));
								    jsonObj.put("uc_count_in", rs.getString("uc_count_in"));
									if(Integer.parseInt(rs.getString("uc_count_in"))!=Integer.parseInt(now_count)){
										result[0]="double";
									}else{
									final int now_amount = Integer.parseInt(jsonObj.getString("uc_count_in"))-Integer.parseInt(amount);								
									String[] sqls = new String[1];	
									sqls[0] = "update T_CUTTER set uc_count_in=?  where id='"+id+"'";	
									String[] sqlt = new String[1];	
									sqlt[0] = "insert into t_c_lt_apply_record(LT_NUM,TYPE_ID,COUNT,PL_NUM,LT,APPLY_TIME,OVER,leader_approve)  values(?,?,?,?,?,?,?,?)";		
									try {
										DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
											public void process(PreparedStatement[] pstmts)	throws SQLException {			
												pstmts[0].setString(1,String.valueOf(now_amount));										
												pstmts[0].addBatch();
											}										
										});										
									}catch (SQLException e) {
									// TODO 自动生成 catch 块
										e.printStackTrace();
										result[0]="exception";
									}
									try {
										DbUtil.executeBatchs(sqlt,new IArrayPreparedStatementProcessor(){
											public void process(PreparedStatement[] pstmt)	throws SQLException {			
												pstmt[0].setString(1,lt_num);	
												pstmt[0].setString(2,id);
												pstmt[0].setString(3,amount);		
												pstmt[0].setString(4,"store");	
												pstmt[0].setString(5,"正常报废");	
												pstmt[0].setDate(6,java.sql.Date.valueOf(now));													
												pstmt[0].setString(7,"未处理");	
												pstmt[0].setString(8,"未处理");
												pstmt[0].addBatch();
											}										
										});										
									}catch (SQLException e) {
									// TODO 自动生成 catch 块
										e.printStackTrace();
										result[0]="exception";
									}
								}	
						   }
						 }
						} catch (JSONException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
							result[0]="exception";
						}
					}				
				});					
			}catch (SQLException e) {
			// TODO 自动生成 catch 块
				e.printStackTrace();
				result[0]="exception";
			}			
			return result[0];			
				
		}		
	
        //上传文件
		public HashMap uploadfile(HashMap fileHmap) {
			final HashMap fileHashMap = fileHmap;	
			final HashMap returnHashMap=new HashMap();	
			String tempPath = "\\web\\tempfile\\"; // 用于存放临时文件的目录
			String trace = "";
			String filename = "";
			String sclassid = "";
			String dir = (String)fileHashMap.get("dir");
			//建临时文件夹
			File fii=new File(dir+tempPath);
			if(!fii.exists()) {
			   fii.mkdirs(); 
			}
			try{	
				System.out.println("执行到这里了！！！");
				List fileItems = (List)fileHashMap.get("fileItems");
				Iterator i = fileItems.iterator();
				while(i.hasNext()){		
					  FileItem fi = (FileItem)i.next();
					  String fileName = fi.getName();
					 	  if(fileName!=null){
		  			 	//	Calendar date = Calendar.getInstance();
		  				//	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-kk时-mm分-ss秒");
		  				//	final String now = format.format(date.getTime())  ;
							String[] s=fileName.split("\\.");
							filename = "导入零件."+s[s.length-1];
							System.out.println("1111111111111111"+filename);
							System.out.println("22222222222"+s);
							String[] n = filename.split("\\.");
							sclassid = n[0];
							trace = tempPath+filename;
							fi.write(new File(dir+trace));
							returnHashMap.put("filename",sclassid);
							System.out.println("***********"+sclassid);
					  }
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return returnHashMap;
		}
	
       //导入文件
		public String getExcel(HashMap xlsHmap) {
		    //TODO 自动生成方法存根
			HashMap xlsHashMap=xlsHmap;
			final String []result = new String[1];
			result[0]="true";
			String dir = (String)xlsHashMap.get("dir");
			String dalei = (String)xlsHashMap.get("dalei");
			String xiaolei = (String)xlsHashMap.get("xiaolei");
			
			System.out.println("***********"+dalei);
			System.out.println("***********"+xiaolei);
			
			final String[] pa = new String[30];
			for (int i = 0; i < pa.length; i++) {
				pa[i] = "";
			}
			
			try 
			{ 	
				WorkbookSettings setting=new WorkbookSettings();   
				setting.setEncoding("ISO-8859-1");   
				Workbook book= Workbook.getWorkbook(new File(dir+"\\web\\tempfile\\导入零件.xls"),setting); 
		        //获得第一个工作表对象 
				Sheet sheet=book.getSheet(0); 
		        //得到第一列第一行的单元格 
				
				Pattern pattern = Pattern.compile("^(0|[1-9][0-9]*)$"); //匹配整数					
				Pattern pattern1 = Pattern.compile("^[0-9]{2}-[0-9]{2}-0[0-3]-[0-9]{2}$"); //匹配库存地点	
				Pattern pattern2 = Pattern.compile("^\\d+$");//匹配数字
				Pattern pattern_number = Pattern.compile("^(0|[1-9][0-9]*)(\\.\\d+)?$");  //验证 数字
				 
				for(int x = 1; x < sheet.getRows(); x++){
					
					//excel里列数最多是30					
					System.out.println("sheet行数是："+sheet.getRows());
					System.out.println("********************"+sheet.getColumns()+"nnnnnnnnnnnnnnnn");
					
					if(xiaolei.equals("上轧辊总成")){
						Cell check1=sheet.getCell(0,x); //大类				
						Cell check2=sheet.getCell(1,x); //小类
						Cell check3=sheet.getCell(2,x); //名称
						Cell check4=sheet.getCell(12,x); //库存地点
						Cell check5=sheet.getCell(13,x); //刃磨刀在库量
						
						if(!check1.getContents().equals("热轧")){
							result[0]="daleifalse";
						}else if(check3.getContents().equals("")){
							result[0]="namefalse";
						}else if(!check2.getContents().equals("上轧辊总成")){
							result[0]="xiaoleifalse";
						}
						
						 //判断库存地点是否格式正确
						 if(!check4.getContents().equals("")){
							 Matcher matcher_local = pattern1.matcher(check4.getContents());
							 boolean d = matcher_local.matches();
							 if(d==false){
								 result[0]="locationfalse";
							 }
						 }else{
							 result[0]="locationnull";
					     }
						 
						 //判断旧刀具在库量是否格式正确	
						 Matcher matcher2 = pattern2.matcher(check5.getContents());//判断旧刀具在库量
						 boolean grind_store = matcher2.matches();						
									 
						 if(!check5.getContents().equals("")&&grind_store==false){
							    result[0]="grindstorefalse";	
						 }else if(!check5.getContents().equals("")&&grind_store==true){
							    NumberCell check=(NumberCell)sheet.getCell(13,x);//旧刀具在库量	
								Matcher matcher_grindstore = pattern.matcher(check.getContents());
								boolean c = matcher_grindstore.matches();
							    if(c==false&&result[0].equals("true")){
									result[0]="grindstorefalse";	
							     }	
						  }	
					
					  if(result[0].equals("true")){							 
						String cell1=sheet.getCell(6,x).getContents(); //刃长
						String cell2=sheet.getCell(7,x).getContents(); //总长
						String cell3=sheet.getCell(8,x).getContents(); //有效长度
						String cell4=sheet.getCell(9,x).getContents(); //刃数  整数
						if(!cell1.equals("")){
							 Matcher matcher_cell = pattern_number.matcher(cell1);							
							 boolean cell_result = matcher_cell.matches();
							 if(cell_result==false){
								 result[0]="e_leng_false";
							 }
						 }
						
						if(!cell2.equals("")){
							 Matcher matcher_cell = pattern_number.matcher(cell2);							
							 boolean cell_result = matcher_cell.matches();
							 if(cell_result==false){
								 result[0]="t_leng_false";
							 }
						 }
						
						if(!cell3.equals("")){
							 Matcher matcher_cell = pattern_number.matcher(cell3);							
							 boolean cell_result = matcher_cell.matches();
							 if(cell_result==false){
								 result[0]="effect_length_false";
							 }
						 }
						
						if(!cell4.equals("")){
							 Matcher matcher_cell = pattern.matcher(cell4);							
							 boolean cell_result = matcher_cell.matches();
							 if(cell_result==false){
								 result[0]="e_count_false";
							 }
						 }
					  }	
					}else{
						Cell check1=sheet.getCell(0,x);	//物资编码				
						Cell check2=sheet.getCell(1,x); //订货号
						Cell check3=sheet.getCell(2,x); //大类				
						Cell check4=sheet.getCell(3,x); //小类				
						Cell check5=sheet.getCell(4,x); //名称	
						Cell check6=sheet.getCell(5,x); //库存地点	
						Cell check7=sheet.getCell(6,x); //最低库存量				
						Cell check8=sheet.getCell(7,x); //新刀在库量				
						Cell check9=sheet.getCell(8,x); //旧刀在库量	
						Cell check10=sheet.getCell(11,x); //高质	
						
						
						 //判断类别，必须为系统中的分类
						 if(check3.getContents().equals("")){
							 result[0]="daleinull";								
						 }else if(check4.getContents().equals("")){
							 result[0]="xiaoleinull";								
						 }else if(!check3.getContents().equals(dalei)){
							 result[0]="daleifalse";								
						 }else if(!check4.getContents().equals(xiaolei)){
							 result[0]="xiaoleifalse";								
						 }else if(check3.getContents().equals("热轧")){
							 if(!check4.getContents().equals("上轧辊总成")&&!check4.getContents().equals("润滑系统")&&!check4.getContents().equals("冷却辊总成")){
								 result[0]="classfalse";	 
							 }
						 }else if(check3.getContents().equals("热轧")){
							 if(!check4.getContents().equals("液压系统")&&!check4.getContents().equals("传动系统")&&!check4.getContents().equals("机架总成")){
								 result[0]="classfalse";
							 }
						 }else if(check3.getContents().equals("热轧")){
							 if(!check4.getContents().equals("下轧辊总成")){
								 result[0]="classfalse";
							 }
						 }else if(check3.getContents().equals("热轧")){
							 if(!check4.getContents().equals("限位调节装置")&&!check4.getContents().equals("轴交叉调节装置")&&!check4.getContents().equals("升降座")&&!check4.getContents().equals("镗刀系统")){
								 result[0]="classfalse";							
							 }
						 }else if(check3.getContents().equals("磨削类")){
							 if(!check4.getContents().equals("磨削刀具")){
								 result[0]="classfalse";
							 }
						 }else if(check3.getContents().equals("其他")){
							 if(!check4.getContents().equals("刀柄系统")&&!check4.getContents().equals("工具配件")){
								 result[0]="classfalse";
							 }
						 }
						 
						//判断物资编码、名称不能为空
						if(check1.getContents().equals("")){
							result[0]="mnumfalse";							
						}else if(check5.getContents().equals("")){
							result[0]="namefalse";							
						}						
										
			            //判断高质一栏是否填写正确、高质刀具是否有订货号
						/*if(!check10.getContents().equals("是")&&!check10.getContents().equals("否")){
							  result[0]="hqfalse";							
						}else if(check10.getContents().equals("是")){
							if(check2.getContents().equals("")){
							  result[0]="orderfalse";
							}
						}									
						*/
						//判断最低库存量是否为空，格式是否正确
						Matcher matcher = pattern2.matcher(check7.getContents());
						boolean mini_check = matcher.matches();						
						
						if(!check7.getContents().equals("")&&mini_check==false){
							result[0]="ministorefalse";	
						}else if(!check7.getContents().equals("")&&mini_check==true){
							NumberCell check11=(NumberCell)sheet.getCell(6,x);//最低库存量	
							Matcher matcher_mini = pattern.matcher(check11.getContents());
							boolean a = matcher_mini.matches();
						    if(a==false&&result[0].equals("true")){
								result[0]="ministorefalse";	
						     }	
						  }else{
							  result[0]="mininull";
						  }
						 
						//判断新刀具在库量是否格式正确
						Matcher matcher1 = pattern2.matcher(check8.getContents());//判断新刀具在库量
						boolean newstore_check = matcher1.matches();						
						 
						if(!check8.getContents().equals("")&&newstore_check==false){
							result[0]="newstorefalse";
						}else if(!check8.getContents().equals("")&&newstore_check==true){
								NumberCell check12=(NumberCell)sheet.getCell(7,x);//新刀具在库量	
								Matcher matcher_newstore = pattern.matcher(check12.getContents());
								boolean b = matcher_newstore.matches();						
								System.out.println("********************"+check12.getContents()+"nnnnnnnnnnnnnnnn");
								
							    if(b==false&&result[0].equals("true")){
									result[0]="newstorefalse";	
							     }	
						  }						
						
						 //判断旧刀具在库量是否格式正确	
						 Matcher matcher2 = pattern2.matcher(check9.getContents());//判断旧刀具在库量
						 boolean oldstore_check = matcher2.matches();						
									 
						 if(!check9.getContents().equals("")&&oldstore_check==false){
							    result[0]="oldstorefalse";	
						 }else if(!check9.getContents().equals("")&&oldstore_check==true){
							    NumberCell check13=(NumberCell)sheet.getCell(8,x);//旧刀具在库量	
								Matcher matcher_oldstore = pattern.matcher(check13.getContents());
								boolean c = matcher_oldstore.matches();
							    if(c==false&&result[0].equals("true")){
									result[0]="oldstorefalse";	
							     }	
						  }								 
						 
						 //判断库存地点是否格式正确
						 if(!check6.getContents().equals("")){
							 Matcher matcher_local = pattern1.matcher(check6.getContents());
							 boolean d = matcher_local.matches();
							 if(d==false){
								 result[0]="locationfalse";
							 }
						 }else{
							 result[0]="locationnull";
						 }				 
						
						 String check_company="" ;
						 if(xiaolei.equals("上轧辊总成")){
							 check_company = sheet.getCell(21,x).getContents(); //小类	
						 }else if(xiaolei.equals("润滑系统")){
							 check_company = sheet.getCell(23,x).getContents(); //小类
						 }else if(xiaolei.equals("冷却辊总成")){
							 check_company = sheet.getCell(22,x).getContents(); //小类							 
						 }else if(xiaolei.equals("液压系统")){
							 check_company = sheet.getCell(23,x).getContents(); //小类	
						 }else if(xiaolei.equals("传动系统")){
							 check_company = sheet.getCell(24,x).getContents(); //小类	
						 }else if(xiaolei.equals("机架总成")){
							 check_company = sheet.getCell(19,x).getContents(); //小类	
						 }else if(xiaolei.equals("下轧辊总成")){
							 check_company = sheet.getCell(25,x).getContents(); //小类	
						 }else if(xiaolei.equals("限位调节装置")){
							 check_company = sheet.getCell(23,x).getContents(); //小类	
						 }else if(xiaolei.equals("轴交叉调节装置")){
							 check_company = sheet.getCell(25,x).getContents(); //小类	
						 }else if(xiaolei.equals("升降座")||xiaolei.equals("磨削刀具")||xiaolei.equals("刀柄系统")||xiaolei.equals("工具配件")){
							 check_company = sheet.getCell(17,x).getContents(); //小类	
						 }		
						 
						 if(check_company.equals("")){
							 result[0]="companyfalse";
						 }	
						 
						if(result[0].equals("true")){	
						 if(xiaolei.equals("普通车削刀具")){
							 String cell1=sheet.getCell(13,x).getContents(); //刀尖高度
							 String cell2=sheet.getCell(14,x).getContents(); //刀体宽度
							 String cell3=sheet.getCell(15,x).getContents(); //刀具长度
							 if(!cell1.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell1);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="tipHeig_false";
								 }
							 }
							 if(!cell2.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell2);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="blaWide_false";
								 }
							 }
							 if(!cell3.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell3);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="c_leng_false";
								 }
							 }					 
						}else if(xiaolei.equals("切断切槽刀具")){							
							String cell4=sheet.getCell(13,x).getContents(); //刀尖高度
							String cell5=sheet.getCell(14,x).getContents(); //刀体宽度
							String cell6=sheet.getCell(15,x).getContents(); //切削刃宽度
							String cell7=sheet.getCell(16,x).getContents(); //刃数  整数
							String cell8=sheet.getCell(17,x).getContents(); //刀具长度
							if(!cell4.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell4);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="tipHeig_false";
								 }
							 }
							if(!cell5.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell5);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="blaWide_false";
								 }
							 }
							if(!cell6.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell6);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="e_wide_false";
								 }
							 }
							if(!cell8.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell8);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="c_leng_false";
								 }
							 }
							if(!cell7.equals("")){
								 Matcher matcher_cell = pattern.matcher(cell7);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="e_count_false";
								 }
							 }
						}else if(xiaolei.equals("车削螺纹加工刀具")){
							String cell9=sheet.getCell(13,x).getContents(); //刀尖高度
							String cell10=sheet.getCell(14,x).getContents(); //刀体宽度
							String cell11=sheet.getCell(16,x).getContents(); //刀具长度
							if(!cell9.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell9);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="tipHeig_false";
								 }
							 }
							if(!cell10.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell10);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="blaWide_false";
								 }
							 }
							if(!cell11.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell11);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="c_leng_false";
								 }
							 }			
						}else if(xiaolei.equals("可转位铣刀")){
							String cell12=sheet.getCell(15,x).getContents(); //刃长
							String cell13=sheet.getCell(16,x).getContents(); //总长
							String cell14=sheet.getCell(17,x).getContents(); //刃数  整数
							if(!cell12.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell12);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="e_leng_false";
								 }
							 }
							if(!cell13.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell13);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="t_leng_false";
								 }
							 }
							if(!cell14.equals("")){
								 Matcher matcher_cell = pattern.matcher(cell14);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="e_count_false";
								 }
							 }			
						}else if(xiaolei.equals("整体立铣刀")){
							String cell15=sheet.getCell(15,x).getContents(); //刃长
							String cell16=sheet.getCell(16,x).getContents(); //总长
							String cell17=sheet.getCell(18,x).getContents(); //刃数  整数
							if(!cell15.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell15);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="e_leng_false";
								 }
							 }
							if(!cell16.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell16);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="t_leng_false";
								 }
							 }
							if(!cell17.equals("")){
								 Matcher matcher_cell = pattern.matcher(cell17);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="e_count_false";
								 }
							 }			
						}else if(xiaolei.equals("钻削刀具")||xiaolei.equals("绞削刀具")||xiaolei.equals("螺纹刀具")){
							String cell18=sheet.getCell(15,x).getContents(); //刃长
							String cell19=sheet.getCell(16,x).getContents(); //总长
							String cell20=sheet.getCell(17,x).getContents(); //齿数  整数
							if(!cell18.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell18);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="e_leng_false";
								 }
							 }
							if(!cell19.equals("")){
								 Matcher matcher_cell = pattern_number.matcher(cell19);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="t_leng_false";
								 }
							 }
							if(!cell20.equals("")){
								 Matcher matcher_cell = pattern.matcher(cell20);							
								 boolean cell_result = matcher_cell.matches();
								 if(cell_result==false){
									 result[0]="tee_count_false";
								 }
							 }			
						}
					  }	 
					}					
									
					if(result[0].equals("mnumfalse")){
						result[0]="mnumfalse";							
						break;							
					}else if(result[0].equals("namefalse")){
						result[0]="namefalse";							
						break;							
					}else if(result[0].equals("hqfalse")){
						result[0]="hqfalse";							
						break;							
					}else if(result[0].equals("mininull")){
						result[0]="mininull";							
						break;							
					}else if(result[0].equals("locationnull")){
						result[0]="locationnull";							
						break;							
					}else if(result[0].equals("companyfalse")){
						result[0]="companyfalse";							
						break;							
					}else if(result[0].equals("daleinull")){
						result[0]="daleinull";							
						break;							
					}else if(result[0].equals("xiaoleinull")){
						result[0]="xiaoleinull";							
						break;							
					}else if(result[0].equals("xiaoleifalse")){
						result[0]="xiaoleifalse";							
						break;							
					}else if(result[0].equals("daleifalse")){
						result[0]="daleifalse";							
						break;							
					}else if(result[0].equals("ministorefalse")){
						result[0]="ministorefalse";							
						break;							
					}else if(result[0].equals("newstorefalse")){
						result[0]="newstorefalse";							
						break;							
					}else if(result[0].equals("oldstorefalse")){
						result[0]="oldstorefalse";							
						break;							
					}else if(result[0].equals("locationfalse")){
						result[0]="locationfalse";							
						break;							
					}else if(result[0].equals("classfalse")){
						result[0]="classfalse";							
						break;							
					}else if(result[0].equals("orderfalse")){
						result[0]="orderfalse";							
						break;							
					}else if(result[0].equals("grindstorefalse")){
						result[0]="grindstorefalse";							
						break;							
					}else if(result[0].equals("e_leng_false")){
						result[0]="e_leng_false";							
						break;							
					}else if(result[0].equals("t_leng_false")){
						result[0]="t_leng_false";							
						break;							
					}else if(result[0].equals("effect_length_false")){
						result[0]="effect_length_false";							
						break;							
					}else if(result[0].equals("e_count_false")){
						result[0]="e_count_false";							
						break;							
					}else if(result[0].equals("tipHeig_false")){
						result[0]="tipHeig_false";							
						break;							
					}else if(result[0].equals("blaWide_false")){
						result[0]="blaWide_false";							
						break;							
					}else if(result[0].equals("c_leng_false")){
						result[0]="c_leng_false";							
						break;							
					}else if(result[0].equals("e_wide_false")){
						result[0]="e_wide_false";							
						break;							
					}else if(result[0].equals("tee_count_false")){
						result[0]="tee_count_false";							
						break;							
					}					
				}
				
				System.out.println(result[0]);
				System.out.println(result[0].equals("true"));
				
				//写入数据库
				if(result[0].equals("true")){
					final String []stringArray = new String[37];
					stringArray[0]="";    //mnum
					stringArray[1]="";    //iso_num
					stringArray[2]="";	  //pclass
					stringArray[3]="";    //bclass
					stringArray[4]="";    //name
					stringArray[5]="";    //hq
					stringArray[6]="";    //rank_angle
					stringArray[7]="";    //c_mate
					stringArray[8]="";    //coat_mate
					stringArray[9]="";	  //mini_qs			
					stringArray[10]="";   //suit_mate1
					stringArray[11]="";   //suit_mate2
					stringArray[12]="";   //location
					stringArray[13]="";   //remark
					stringArray[14]="";   //e_diam
					stringArray[15]="";   //h_diam
					stringArray[16]="";   //e_leng
					stringArray[17]="";   //t_leng
					stringArray[18]="";   //tee_count
					stringArray[19]="";   //s_ang
					stringArray[20]="";   //tip_heig
					stringArray[21]="";   //bla_wide
					stringArray[22]="";   //c_leng
					stringArray[23]="";   //e_count
					stringArray[24]="";   //e_wide
					stringArray[25]="";   //t_form
					stringArray[26]="";   //c_way
					stringArray[27]="";   //t_type
					stringArray[28]="";   //spec
					stringArray[29]="";	   //type
					stringArray[30]="";   //series_num
					stringArray[31]="";   //order_num
					stringArray[32]="";   //nc_count_in
					stringArray[33]="";   //gc_count_in
					stringArray[34]="";   //uc_count_in
					stringArray[35]="";   //use_freq
					stringArray[36]="";   //effect_length
					
				    final String  []company=new String[6];
					
					for(int x = 1; x < sheet.getRows(); x++){							
						String cell1=sheet.getCell(0,x).getContents();	
						String cell2=sheet.getCell(1,x).getContents();		
						String cell3=sheet.getCell(2,x).getContents();	
						String cell4=sheet.getCell(3,x).getContents(); 
						String cell5=sheet.getCell(4,x).getContents(); 
						String cell6=sheet.getCell(5,x).getContents();		
						String cell7=sheet.getCell(6,x).getContents();	
						String cell8=sheet.getCell(7,x).getContents(); 
						String cell9=sheet.getCell(8,x).getContents();	    
						String cell10=sheet.getCell(9,x).getContents();	    
						String cell11=sheet.getCell(10,x).getContents();	
						String cell12=sheet.getCell(11,x).getContents();    
						String cell13=sheet.getCell(12,x).getContents();    
						String cell14=sheet.getCell(13,x).getContents();		
						String cell15=sheet.getCell(14,x).getContents();					
						String cell16=sheet.getCell(15,x).getContents();    
						String cell17=sheet.getCell(16,x).getContents();    					
						String cell18=sheet.getCell(17,x).getContents();
						String cell19=sheet.getCell(18,x).getContents();
						String cell20=sheet.getCell(19,x).getContents();
						String cell21=sheet.getCell(20,x).getContents();
						String cell22=sheet.getCell(21,x).getContents();						
						String cell23=sheet.getCell(22,x).getContents();
						
						String cell24="";
						String cell25="";
						String cell26="";
						String cell27="";						
						String cell28="";
						String cell29="";
						String cell30="";
						String cell31="";
						if(xiaolei.equals("普通车削刀具")){
							 cell24=sheet.getCell(23,x).getContents();
							 cell25=sheet.getCell(24,x).getContents();
							 cell26=sheet.getCell(25,x).getContents();
							 cell27=sheet.getCell(26,x).getContents();							 
						}else if(xiaolei.equals("切断切槽刀具")){
							System.out.println("*********************************");
							 cell24=sheet.getCell(23,x).getContents();
							 cell25=sheet.getCell(24,x).getContents();
							 cell26=sheet.getCell(25,x).getContents();
							 cell27=sheet.getCell(26,x).getContents();
							 cell28=sheet.getCell(27,x).getContents();
							 cell29=sheet.getCell(28,x).getContents();
						}else if(xiaolei.equals("车削螺纹加工刀具")){
							 cell24=sheet.getCell(23,x).getContents();
							 cell25=sheet.getCell(24,x).getContents();
							 cell26=sheet.getCell(25,x).getContents();
							 cell27=sheet.getCell(26,x).getContents();
							 cell28=sheet.getCell(27,x).getContents();
						}else if(xiaolei.equals("可转位铣刀")){
							 cell24=sheet.getCell(23,x).getContents();
							 cell25=sheet.getCell(24,x).getContents();
							 cell26=sheet.getCell(25,x).getContents();
							 cell27=sheet.getCell(26,x).getContents();
							 cell28=sheet.getCell(27,x).getContents();
							 cell29=sheet.getCell(28,x).getContents();
						}else if(xiaolei.equals("整体立铣刀")){
							 cell24=sheet.getCell(23,x).getContents();
							 cell25=sheet.getCell(24,x).getContents();
							 cell26=sheet.getCell(25,x).getContents();
							 cell27=sheet.getCell(26,x).getContents();
							 cell28=sheet.getCell(27,x).getContents();
							 cell29=sheet.getCell(28,x).getContents();
							cell30=sheet.getCell(29,x).getContents();
						}else if(xiaolei.equals("刀片")){
							 cell24=sheet.getCell(23,x).getContents();
							 cell25=sheet.getCell(24,x).getContents();
						}else if(xiaolei.equals("钻削刀具")){
							 cell24=sheet.getCell(23,x).getContents();
							 cell25=sheet.getCell(24,x).getContents();
							 cell26=sheet.getCell(25,x).getContents();
							 cell27=sheet.getCell(26,x).getContents();
							 cell28=sheet.getCell(27,x).getContents();
							 cell29=sheet.getCell(28,x).getContents();
							 cell30=sheet.getCell(29,x).getContents();
							 cell31=sheet.getCell(30,x).getContents();
						}else if(xiaolei.equals("绞削刀具")){
							 cell24=sheet.getCell(23,x).getContents();
							 cell25=sheet.getCell(24,x).getContents();
							 cell26=sheet.getCell(25,x).getContents();
							 cell27=sheet.getCell(26,x).getContents();
							 cell28=sheet.getCell(27,x).getContents();
							 cell29=sheet.getCell(28,x).getContents();
						}else if(xiaolei.equals("螺纹刀具")){
							 cell24=sheet.getCell(23,x).getContents();
							 cell25=sheet.getCell(24,x).getContents();
							 cell26=sheet.getCell(25,x).getContents();
							 cell27=sheet.getCell(26,x).getContents();
							 cell28=sheet.getCell(27,x).getContents();
							 cell29=sheet.getCell(28,x).getContents();
							 cell30=sheet.getCell(29,x).getContents();
							 cell31=sheet.getCell(30,x).getContents();
						}
						
						//NumberCell cell8=(NumberCell)sheet.getCell(7,x); //最低库存量  试验不加NumberCell会怎样
						if(!xiaolei.equals("刃磨立铣刀")){
							stringArray[0]=cell1;    //mnum
							stringArray[31]=cell2;   //order_num
							stringArray[2]=cell3;	 //pclass						  						
							stringArray[3]=cell4;    //bclass
							stringArray[4]=cell5;    //name
							stringArray[12]=cell6;   //location
							stringArray[9]=cell7;	 //mini_qs								
							stringArray[32]=cell8;   //nc_count_in
							stringArray[34]=cell9;   //uc_count_in
							stringArray[29]=cell10;  //type
							stringArray[30]=cell11;  //series_num
							stringArray[5]=cell12;   //hq
							stringArray[6]=cell13; //前角
							
							if(xiaolei.equals("普通车削刀具")){								
								stringArray[20]=cell14;  //刀尖高度tip_heig							
								stringArray[21]=cell15;  //刀尖宽度bla_wide
								stringArray[22]=cell16;	  //c_leng					
								stringArray[7]=cell17;    //c_mate	
								stringArray[8]=cell18;  //coat_mate	
								stringArray[10]=cell19;  //suit_mate1	
								stringArray[11]=cell20;  //suit_mate2	
								stringArray[13]=cell21;	  //remark						
								company[0]=cell22;  
								company[1]=cell23;
								company[2]=cell24;
								company[3]=cell25;
								company[4]=cell26;
								company[5]=cell27;	
							}else if(xiaolei.equals("切断切槽刀具")){							
								stringArray[20]=cell14;  //刀尖高度							
								stringArray[21]=cell15;  //刀尖宽度
								stringArray[24]=cell16;	//切削刃宽						
								stringArray[23]=cell17;  //刃数
								stringArray[22]=cell18; //刀具长度								
								stringArray[7]=cell19;//c_mate	
								stringArray[8]=cell20;//coat_mate
								stringArray[10]=cell21;//suit_mate1	
								stringArray[11]=cell22;//suit_mate2	
								stringArray[13]=cell23;	//remark						
								company[0]=cell24;
								company[1]=cell25;
								company[2]=cell26;
								company[3]=cell27;
								company[4]=cell28;
								company[5]=cell29;	
							}else if(xiaolei.equals("车削螺纹加工刀具")){							
								stringArray[20]=cell14;  //刀尖高度							
								stringArray[21]=cell15;  //刀体宽度
								stringArray[22]=cell16;	//螺纹样式						
								stringArray[22]=cell17;  //刀具长度														
								stringArray[7]=cell18;
								stringArray[8]=cell19;
								stringArray[10]=cell20;
								stringArray[11]=cell21;
								stringArray[13]=cell22;							
								company[0]=cell23;
								company[1]=cell24;
								company[2]=cell25;
								company[3]=cell26;
								company[4]=cell27;
								company[5]=cell28;	
							}else if(xiaolei.equals("可转位铣刀")){
								stringArray[14]=cell14;  //刃径e_diam							
								stringArray[15]=cell15;  //柄径h_diam
								stringArray[16]=cell16;	//刃长	e_leng					
								stringArray[17]=cell17;  //总长	t_leng													
								stringArray[23]=cell18;  //刃数e_count
								stringArray[7]=cell19;
								stringArray[8]=cell20;
								stringArray[10]=cell21;
								stringArray[11]=cell22;
								stringArray[13]=cell23;
								company[0]=cell24;
								company[1]=cell25;
								company[2]=cell26;
								company[3]=cell27;
								company[4]=cell28;
								company[5]=cell29;	
							}else if(xiaolei.equals("整体立铣刀")){
								stringArray[14]=cell14;  //刃径							
								stringArray[15]=cell15;  //柄径
								stringArray[16]=cell16;	//刃长						
								stringArray[17]=cell17;  //总长									
								stringArray[19]=cell18;   //螺旋角								
								stringArray[23]=cell19;  //刃数
								stringArray[7]=cell20;
								stringArray[8]=cell21;
								stringArray[10]=cell22;
								stringArray[11]=cell23;
								stringArray[13]=cell24;
								company[0]=cell25;
								company[1]=cell26;
								company[2]=cell27;
								company[3]=cell28;
								company[4]=cell29;
								company[5]=cell30;	
							}else if(xiaolei.equals("刀片")){
								stringArray[1]=cell14;  //ISO							
								stringArray[7]=cell15;  
								stringArray[8]=cell16;							
								stringArray[10]=cell17;  									
								stringArray[11]=cell18;   								
								stringArray[13]=cell19;  							
								company[0]=cell20;
								company[1]=cell21;
								company[2]=cell22;
								company[3]=cell23;
								company[4]=cell24;
								company[5]=cell25;	
							}else if(xiaolei.equals("钻削刀具")){
								stringArray[14]=cell14;  //刃径							
								stringArray[15]=cell15;  //柄径
								stringArray[16]=cell16;	//刃长						
								stringArray[17]=cell17;  //总长														
								stringArray[18]=cell18;  //齿数
								stringArray[19]=cell19;    //螺旋角	s_ang
								stringArray[26]=cell20;  //冷却方式c_way
								stringArray[7]=cell21;
								stringArray[8]=cell22;
								stringArray[10]=cell23;
								stringArray[11]=cell24;
								stringArray[13]=cell25;
								company[0]=cell26;
								company[1]=cell27;
								company[2]=cell28;
								company[3]=cell29;
								company[4]=cell30;
								company[5]=cell31;	
							}else if(xiaolei.equals("绞削刀具")){
								stringArray[14]=cell14;  //刃径e_diam							
								stringArray[15]=cell15;  //柄径h_diam
								stringArray[16]=cell16;	//刃长e_leng						
								stringArray[17]=cell17;  //总长t_leng														
								stringArray[18]=cell18;  //齿数tee_count								
								stringArray[7]=cell19;
								stringArray[8]=cell20;
								stringArray[10]=cell21;
								stringArray[11]=cell22;
								stringArray[13]=cell23;
								company[0]=cell24;
								company[1]=cell25;
								company[2]=cell26;
								company[3]=cell27;
								company[4]=cell28;
								company[5]=cell29;	
							}else if(xiaolei.equals("螺纹刀具")){
								stringArray[14]=cell14;  //刃径							
								stringArray[15]=cell15;  //柄径
								stringArray[16]=cell16;	//刃长						
								stringArray[17]=cell17;  //总长														
								stringArray[18]=cell18;  //齿数
								stringArray[27]=cell19;    //牙型	
								stringArray[26]=cell20;  //冷却方式
								stringArray[7]=cell21;
								stringArray[8]=cell22;
								stringArray[10]=cell23;
								stringArray[11]=cell24;
								stringArray[13]=cell25;
								company[0]=cell26;
								company[1]=cell27;
								company[2]=cell28;
								company[3]=cell29;
								company[4]=cell30;
								company[5]=cell31;	
							}else{
								stringArray[28]=cell14;  //规格	
								stringArray[10]=cell15;
								stringArray[11]=cell16;
								stringArray[13]=cell17;
								company[0]=cell18;
								company[1]=cell19;
								company[2]=cell20;
								company[3]=cell21;
								company[4]=cell22;
								company[5]=cell23;	
							}							
						}else{
							stringArray[2]=cell1;   //pclass	
							stringArray[3]=cell2;	//bclass	
							stringArray[4]=cell3;	//name							
							stringArray[6]=cell4;	//rank_angle							
							stringArray[14]=cell5;	//e_diam	
							stringArray[15]=cell6;	//h_diam
							stringArray[16]=cell7;	//e_leng					
							stringArray[17]=cell8;	//t_leng				
							stringArray[36]=cell9;	//effect_length	
							stringArray[23]=cell10;		//e_count	
							stringArray[7]=cell11;		//c_mate
							stringArray[8]=cell12;		//coat_mate							
							stringArray[12]=cell13; 	//location	
							stringArray[33]=cell14;	 //gc_count_in	
							stringArray[10]=cell15;	//suit_mate1
							stringArray[11]=cell16;	//suit_mate2	
							stringArray[13]=cell17;		//remark	
							company[0]=cell18;
							company[1]=cell19;
							company[2]=cell20;
							company[3]=cell21;
							company[4]=cell22;
							company[5]=cell23;	
						}
						
						final int newinstore = stringArray[32].equals("")?0:Integer.parseInt(stringArray[32]);//新刀具在库量
						final int oldinstore = stringArray[34].equals("")?0:Integer.parseInt(stringArray[34]);//旧刀具在库量
						final int grindinstore = stringArray[33].equals("")?0:Integer.parseInt(stringArray[33]);//刃磨刀具在库量
											
		               //判断数据库中有无此厂商
						 final String []company_id=new String[1];							 
						 String sql1="select id from T_COMPANY where COMPANY='"+company[0]+"'";			
							try {			
								DbUtil.execute(sql1,new IResultSetProcessor(){
									public void process(ResultSet rs) throws SQLException{
										if(rs.next()){					
											//读取id存到类型表中
											company_id[0]=rs.getString("id");   //记录厂商id
											System.out.println("*************************"+company_id[0]);
										}else{											
										  try {					
										 	    final String id=UUID.randomUUID().toString();  //自动生成id
										 	    company_id[0]=id;  //记录厂商id
										 	    System.out.println("*************************"+company_id[0]);
												String []sqlcom = new String[1];
												sqlcom[0]= "insert into T_COMPANY(id,company,address,contact_person,phone,email,remark) values(?,?,?,?,?,?,?)";
												Logger.debug(sqlcom[0]);
												DbUtil.executeBatchs(sqlcom,
														new IArrayPreparedStatementProcessor() {
															public void process(
															PreparedStatement[] pstmts)
															throws SQLException {
															pstmts[0].setString(1,id);
														if(company[0]!=null){
															pstmts[0].setString(2,(String)company[0]);//userid
														}else{
															pstmts[0].setString(2," ");//userid
														}
														if(company[1]!=null){
															pstmts[0].setString(3,(String)company[1]);//username
														}else{
															pstmts[0].setString(3," ");//username
														}
														if(company[2]!=null){
															pstmts[0].setString(4,(String)company[2]);//pwd
														}else{
															pstmts[0].setString(4," ");//pwd
														}
														if(company[3]!=null){
															pstmts[0].setString(5,(String)company[3]);//email
														}else{
															pstmts[0].setString(5," ");//email
														}
														if(company[4]!=null){
															pstmts[0].setString(6,(String)company[4]);//mobilephone
														}else{
															pstmts[0].setString(6," ");//mobilephone
														}
														if(company[5]!=null){
															pstmts[0].setString(7,(String)company[5]);//telephone
														}else{
															pstmts[0].setString(7," ");//telephone
														}
														pstmts[0].addBatch();
														
													}
												});												
										}catch (SQLException e) {
											e.printStackTrace();
											result[0]="error";
										}
									 }
									}										
								});	
							}catch (SQLException p){
								p.printStackTrace();
								result[0]="error";
							  }				
					
		                    //判断数据库中有无此类型
						if(result[0].equals("true")){							
							if(!xiaolei.equals("上轧辊总成")){
								final String []type_id=new String[1];
								String sql2="";
								String sql3="";
								type_id[0]="";
								if("否".equals(stringArray[5])){
									sql2="select id from T_CUTTER where mnum='"+stringArray[0]+"' and companyid='"+company_id[0]+"'";	
									try {			
										DbUtil.execute(sql2,new IResultSetProcessor(){
											public void process(ResultSet rs) throws SQLException{
												if(rs.next()){					
													type_id[0]=rs.getString("id");
												}
											}
										});	
									 }catch (SQLException e){
										e.printStackTrace();
										result[0]="error";
									 }
									}else if("是".equals(stringArray[5])){
										  sql3="select id from T_CUTTER where order_num='"+stringArray[31]+"'and companyid='"+company_id[0]+"' ";			
											try {			
												DbUtil.execute(sql3,new IResultSetProcessor(){
													public void process(ResultSet rs) throws SQLException{
														while(rs.next()){					
															type_id[0]=rs.getString("id");
														}
													}
												});	
											}catch (SQLException e){
												e.printStackTrace();
												result[0]="error";
											  }
										  
									  }							 
								if (type_id[0].equals("")){	
										final String create_typeid=UUID.randomUUID().toString();  //自动生成id		
										type_id[0]=create_typeid;									
										String []sqltype = new String[1];								
										sqltype[0] = "insert into T_CUTTER(id,mnum,iso_num,pclass,bclass,name,companyid,hq,rank_angle,c_mate,coat_mate,mini_qs,suit_mate1,suit_mate2," +
												"location,remark,e_diam,h_diam,e_leng,t_leng,tee_count,s_ang,tip_heig,bla_wide,c_leng,e_count,e_wide,t_form,c_way,t_type,spec,type,series_num," +
												"order_num,nc_count_in,gc_count_in,uc_count_in,use_freq,effect_length) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
										Logger.debug(sqltype[0]);
										
										try {
											DbUtil.executeBatchs(sqltype,
													new IArrayPreparedStatementProcessor() {
														public void process(PreparedStatement[] pstmts)
																throws SQLException {
																
															pstmts[0].setString(1, create_typeid);
															pstmts[0].setString(2, stringArray[0]);
															pstmts[0].setString(3, stringArray[1]);
															pstmts[0].setString(4, stringArray[2]);
															pstmts[0].setString(5, stringArray[3]);
															pstmts[0].setString(6, stringArray[4]);
															pstmts[0].setString(7, company_id[0]);
															pstmts[0].setString(8, stringArray[5]);
															pstmts[0].setString(9, stringArray[6]);
															pstmts[0].setString(10, stringArray[7]);													
															pstmts[0].setString(11, stringArray[8]);
															pstmts[0].setString(12, stringArray[9]);
															pstmts[0].setString(13, stringArray[10]);
															pstmts[0].setString(14, stringArray[11]);
															pstmts[0].setString(15, stringArray[12]);														
															pstmts[0].setString(16, stringArray[13]);													
															pstmts[0].setString(17, stringArray[14]);
															pstmts[0].setString(18, stringArray[15]);
															pstmts[0].setString(19, stringArray[16]);
															pstmts[0].setString(20, stringArray[17]);
															pstmts[0].setString(21, stringArray[18]);														
															pstmts[0].setString(22, stringArray[19]);														
															pstmts[0].setString(23, stringArray[20]);													
															pstmts[0].setString(24, stringArray[21]);
															pstmts[0].setString(25, stringArray[22]);
															pstmts[0].setString(26, stringArray[23]);
															pstmts[0].setString(27, stringArray[24]);
															pstmts[0].setString(28, stringArray[25]);														
															pstmts[0].setString(29, stringArray[26]);														
															pstmts[0].setString(30, stringArray[27]);													
															pstmts[0].setString(31, stringArray[28]);
															pstmts[0].setString(32, stringArray[29]);
															pstmts[0].setString(33, stringArray[30]);
															pstmts[0].setString(34, stringArray[31]);
															pstmts[0].setInt(35, newinstore);
															pstmts[0].setInt(36, grindinstore);														
															pstmts[0].setInt(37, oldinstore);
															pstmts[0].setInt(38, 0);
															pstmts[0].setString(39, stringArray[36]);	
															pstmts[0].addBatch();
														}
													});						
									}catch (SQLException e) {
										e.printStackTrace();
										result[0]="error";
									}						
								}else{										
									String []sqltype = new String[1];							
									sqltype[0] = "update  T_CUTTER set nc_count_in=nc_count_in+"+newinstore+",uc_count_in=uc_count_in+"+oldinstore+" where id='"+type_id[0]+"'";
									
									try {
										DbUtil.executeBatchs(sqltype,
												new IArrayPreparedStatementProcessor() {
													public void process(PreparedStatement[] pstmts)
															throws SQLException {												
														pstmts[0].addBatch();
													}
												});						
								}catch (SQLException e) {
									e.printStackTrace();
									result[0]="error";
								}						
							  }		
						}else{	
							final String create_typeid=UUID.randomUUID().toString();  //自动生成id	
							String []sql_grind = new String[1];
							sql_grind[0]= "insert into T_CUTTER(id,bclass,pclass,name,companyid,rank_angle,c_mate,coat_mate,suit_mate1,suit_mate2,location,remark,e_diam,h_diam,e_leng,t_leng,e_count,gc_count_in,effect_length) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							Logger.debug(sql_grind[0]);
							try {
								DbUtil.executeBatchs(sql_grind,
										new IArrayPreparedStatementProcessor() {
											public void process(PreparedStatement[] pstmt)
													throws SQLException {														
												pstmt[0].setString(1, create_typeid);
												pstmt[0].setString(2, stringArray[3]);
												pstmt[0].setString(3, stringArray[2]);
												pstmt[0].setString(4, stringArray[4]);
												pstmt[0].setString(5, company_id[0]);												
												pstmt[0].setString(6, stringArray[6]);												
												pstmt[0].setString(7, stringArray[7]);
												pstmt[0].setString(8, stringArray[8]);												
												pstmt[0].setString(9, stringArray[10]);
												pstmt[0].setString(10, stringArray[11]);												
												pstmt[0].setString(11, stringArray[12]);
												pstmt[0].setString(12, stringArray[13]);												
												pstmt[0].setString(13, stringArray[14]);
												pstmt[0].setString(14, stringArray[15]);
												pstmt[0].setString(15, stringArray[16]);														
												pstmt[0].setString(16, stringArray[17]);												
												pstmt[0].setString(17, stringArray[23]);												
												pstmt[0].setString(18, stringArray[33]);
												pstmt[0].setString(19, stringArray[36]);
												pstmt[0].addBatch();
											}
										});						
						}catch (SQLException e) {
							e.printStackTrace();
							result[0]="error";
						}
						}	
					  }									
					}
				}
				book.close(); 
			}catch(Exception e) { 
				e.printStackTrace();
				result[0]="error";
			}
			System.out.println("****************************"+result[0]); 
			return result[0];
		    
		}	
		
		public String correct_cutter(HashMap correct_HMap) {
			// TODO 自动生成方法存根			
			final String id=(String)correct_HMap.get("id");
			final String amount=(String)correct_HMap.get("amount");			
			final String k=(String)correct_HMap.get("k");	
			final String before_amount=(String)correct_HMap.get("before_amount");	
			
			try {				
			String[] sqls = new String[1];	
			if(k.equals("0")){
				sqls[0] = "update T_CUTTER set gc_count_in="+amount+"  where id='"+id+"'";
			}else{
				sqls[0] = "update T_CUTTER set uc_count_in="+amount+"  where id='"+id+"'";
			}
			System.out.println(sqls[0]);
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)	throws SQLException {	
						pstmts[0].addBatch();
					}
				});
				Logger.debug(sqls[0]+"+ before_amount="+before_amount+"");	
				return "1";				
			}
			catch (SQLException e) {
				e.printStackTrace();
				return "0";
			}			
		}	
		
		//合并相同数据的两条刀具信息
		public String combine_cutter(HashMap combine_HMap) {
			// TODO 自动生成方法存根			
			final String first_id=(String)combine_HMap.get("first_id");
			final String second_id=(String)combine_HMap.get("second_id");			
			final String combine_nc=(String)combine_HMap.get("combine_nc");	
			final String combine_uc=(String)combine_HMap.get("combine_uc");		
			
			try {				
				String[] sqls = new String[10];
				sqls[0] = "update T_CUTTER set nc_count_in="+combine_nc+",uc_count_in="+combine_uc+" where id='"+first_id+"'";		
				sqls[1] = "delete from T_CUTTER where id='"+second_id+"'";
				sqls[2] = "update t_c_br_record set type_id='"+first_id+"' where type_id='"+second_id+"'";
				sqls[3] = "update t_c_br_trecord set type_id='"+first_id+"' where type_id='"+second_id+"'";
				sqls[4] = "update t_c_entry_record set type_id='"+first_id+"' where type_id='"+second_id+"'";
				sqls[5] = "update t_c_entry_trecord set type_id='"+first_id+"' where type_id='"+second_id+"'";
				sqls[6] = "update t_c_grd_record set type_id='"+first_id+"' where type_id='"+second_id+"'";
				sqls[7] = "update t_c_lt_apply_record set type_id='"+first_id+"' where type_id='"+second_id+"'";
				sqls[8] = "update t_c_need set type_id='"+first_id+"' where type_id='"+second_id+"'";
				sqls[9] = "update t_hq_c_apply_record set type_id='"+first_id+"' where type_id='"+second_id+"'";
				
				System.out.println(sqls[0]);
				System.out.println(sqls[1]);
				System.out.println(sqls[2]);
				System.out.println(sqls[3]);
				System.out.println(sqls[4]);
				System.out.println(sqls[5]);
				System.out.println(sqls[6]);
				System.out.println(sqls[7]);
				System.out.println(sqls[8]);
				System.out.println(sqls[9]);
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)	throws SQLException {	
						pstmts[0].addBatch();
						pstmts[1].addBatch();
						pstmts[2].addBatch();
						pstmts[3].addBatch();
						pstmts[4].addBatch();
						pstmts[5].addBatch();
						pstmts[6].addBatch();
						pstmts[7].addBatch();
						pstmts[8].addBatch();
						pstmts[9].addBatch();
					}
				});				
				return "1";				
			}
			catch (SQLException e) {
				e.printStackTrace();
				return "0";
			}	
		}	
		
}