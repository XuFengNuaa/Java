
package com.nuaa.app.impl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jenkov.prizetags.tree.itf.ITree;
import com.jenkov.prizetags.tree.itf.ITreeNode;
import com.nuaa.app.Material_delete;
import com.nuaa.app.Store_Search;
import com.nuaa.app.Task_mod;
import com.nuaa.sys.util.AppInsFactory;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IStatementProcessor;


public class Task_modImpl implements Task_mod  {
//列表框查询设计人员
	public String Leader_query_togrind(HashMap queryMap){
			String result="";
			final HashMap HashMap=queryMap;
			String userlevel = (String)HashMap.get("userlevel");
			String userid = (String)HashMap.get("userid");
			
			// result="<h1   " +"align="+"center"+">";
			final String[] content=new String[1];
			content[0]="";       
			
			if(userlevel.equals("4")){
				try {			
					String sql="select count(ordernum) as ct from v_del_com where deldate > sysdate - 10";
					Logger.debug(sql);
				    DbUtil.execute(sql,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException{
						
						while(rs.next()){
						                  	content[0]="";
										    String s_temp="";
										    if(Integer.parseInt(rs.getString("ct"))!=0){
											s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最近十天您有<font color=blue >"+""+rs.getString("ct")+""+"条材料报废订单需要查看</font>";
										    }
											content[0]+=s_temp; 
										}				
								}
							}
						);	
				       result+="<a class=n target= _blank href=web/sms/storestatmod/storestatemod_material/material_deletelookup.jsp><p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p></a>";
					}catch (SQLException e){	
						e.printStackTrace();
				  }
				
			try {			
				String sql="select count(apply_num) as ct from t_hq_c_apply_record where gleader_approve='批准' and leader_approve='未处理' and status='未处理'";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					int i=0;
					while(rs.next()){	content[0]="";					                
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){									 
										   s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条高质刀具借用申请</font>需审批";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(apply_num) as ct from t_hq_ct_apply_record where gleader_approve='批准' and leader_approve='未处理'  and status='未处理'";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					int i=0;
					while(rs.next()){
						                content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条高质工具借用申请</font>需审批";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}	
			try {			
				String sql="select count(apply_num) as ct from t_hq_c_apply_record where gleader_approve='批准' and leader_approve='未处理' and status='借出'";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					int i=0;
					while(rs.next()){	content[0]="";					                
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){									 
										   s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条高质刀具暂批信息</font>需处理";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(apply_num) as ct from t_hq_ct_apply_record where gleader_approve='批准' and leader_approve='未处理' and status='借出' ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
						                content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条高质工具暂批信息</font>需处理";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}	
		 try {			
			String sql="select count(*) as ct from T_C_LT_APPLY_RECORD where LEADER_APPROVE='未处理'";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";								   
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条刀具报损信息</font>需审批";
								    }
									content[0]+=s_temp; 									
								}				
						}
					}
				);	
		    result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";		 
			}catch (SQLException e){	
				e.printStackTrace();
			}
	   try {			
			String sql="select count(*) as ct from T_CT_LT_APPLY_RECORD where LEADER_APPROVE='未处理'";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条非测量类工具报损信息</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
					}
				}
			);	
		    result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
		}catch (SQLException e){	
			e.printStackTrace();
		}
	 try {			
			String sql="select count(*) as ct from T_MT_LT_RECORD where LEADER_APPROVE='未处理'";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条测量类工具报损信息</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
					}
				}
			);	
		    result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
		}catch (SQLException e){	
			e.printStackTrace();
		}
		try {			
			String sql="select count(*) as ct from T_M_LT_RECORD where LEADER_APPROVE='未处理'";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条量具信息具报损信息</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
					}
				}
			);	
		    result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
		}catch (SQLException e){	
			e.printStackTrace();
		}
		try {			
			String sql="select count(*) as ct from T_C_ENTRY_TRECORD where STATUS='申请'";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条刀具入库信息</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
					}
				}
			);	
		    result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
		}catch (SQLException e){	
			e.printStackTrace();
		}
		try {			
			String sql="select count(*) as ct from T_CT_ENTRY_TRECORD where STATUS='申请'";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条非测量类工具入库信息</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
					}
				}
			);	
		    result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
		}catch (SQLException e){	
			e.printStackTrace();
		}
		try {			
			String sql="select count(*) as ct from T_MT_ENTRY_TRECORD where STATUS='申请'";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条测量类工具入库信息</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
					}
				}
			);	
		    result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
		}catch (SQLException e){	
			e.printStackTrace();
		}
		try {			
			String sql="select count(*) as ct from T_M_ENTRY_TRECORD where STATUS='申请'";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条量具入库信息</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
					}
				}
			);	
		    result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
		}catch (SQLException e){	
			e.printStackTrace();
		}
		try {			
			String sql="select count(distinct grd_num) as ct from t_c_grd_record where app_status='未处理'";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"张送磨单</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
						}
					}
				);	
		    result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
		 }
		try {			
			String sql="select count(NEED_NUM) as ct from T_C_NEED where GLEADER_APPROVE='批准' and LEADER_APPROVE='未处理'";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条刀具需求信息</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
						}
					}
				);	
		     result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
		 }	
		try {			
			String sql="select count(NEED_NUM) as ct from T_CT_NEED where GLEADER_APPROVE='批准' and LEADER_APPROVE='未处理'";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条非测量类工具需求信息</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
						}
					}
				);	
		      result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
		 }
		try {			
			String sql="select count(NEED_NUM) as ct from T_MT_NEED where GLEADER_APPROVE='批准' and LEADER_APPROVE='未处理'";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条测量类工具需求信息</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
						}
					}
				);	
		       result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
		 }
		try {			
			String sql="select count(NEED_NUM) as ct from T_M_NEED where GLEADER_APPROVE='批准' and LEADER_APPROVE='未处理'";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条量具需求信息</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
						}
					}
				);	
		       result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
		 }			
		
	}else if(userlevel.equals("3")){
		try {		
			
			String sql="select count(apply_num) as ct from v_cut2hqapply where gleader_approve='未处理' and leader_approve='未处理' and group_num in(select group_num from v_user2role where stuff_num='"+userid+"')";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){	
					                content[0]="";					                
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){									 
									   s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条高质刀具借用申请</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
			    	}
			  }
		  );	
		  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
		}catch (SQLException e){	
			e.printStackTrace();
		}
		try {			
			String sql="select count(apply_num) as ct from v_m2hqmmt where GLEADER_APPROVE='未处理' and group_num in(select group_num from v_user2role where stuff_num='"+userid+"') ";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
					                content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条高质量具借用申请</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
			    	}
			  }
		  );	
		  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
		}catch (SQLException e){	
			e.printStackTrace();
		}
		try {			
			String sql="select count(apply_num) as ct from v_ct2hqapply where gleader_approve='未处理' and leader_approve='未处理' and group_num in(select group_num from v_user2role where stuff_num='"+userid+"')";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
					                content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条非测量类高质工具借用申请</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
			    	}
			  }
		  );	
		  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
		}catch (SQLException e){	
			e.printStackTrace();
		}
		try {			
			String sql="select count(apply_num) as ct from v_mt2hqmmt where gleader_approve='未处理' and group_num in(select group_num from v_user2role where stuff_num='"+userid+"')";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
					                content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条测量类高质工具借用申请</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
			    	}
			  }
		  );	
		  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
		}catch (SQLException e){	
			e.printStackTrace();
		}	
		try {			
			String sql="select count(NEED_NUM) as ct from v_cut2need where GLEADER_APPROVE='未处理' and LEADER_APPROVE='未处理' and group_num in(select group_num from v_user2role where stuff_num='"+userid+"')";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
					                content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条刀具需求信息</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
			    	}
			  }
		  );	
		  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
		}catch (SQLException e){	
			e.printStackTrace();
		}
		try {			
			String sql="select count(NEED_NUM) as ct from v_m2need where GLEADER_APPROVE='未处理' and LEADER_APPROVE='未处理' and group_num in(select group_num from v_user2role where stuff_num='"+userid+"')";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条量具需求信息</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
						}
					}
				);	
		       result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
		 }			
		try {			
			String sql="select count(NEED_NUM) as ct from v_ct2need where GLEADER_APPROVE='未处理' and LEADER_APPROVE='未处理' and group_num in(select group_num from v_user2role where stuff_num='"+userid+"')";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条非测量类工具需求信息</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
						}
					}
				);	
		      result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
		 }
		try {			
			String sql="select count(NEED_NUM) as ct from v_mt2need where GLEADER_APPROVE='未处理' and LEADER_APPROVE='未处理' and group_num in(select group_num from v_user2role where stuff_num='"+userid+"')";
			Logger.debug(sql);
		    DbUtil.execute(sql,new IResultSetProcessor(){
			public void process(ResultSet rs) throws SQLException{
				
				while(rs.next()){
				                  	content[0]="";
								    String s_temp="";
								    if(Integer.parseInt(rs.getString("ct"))!=0){
									s_temp+="&nbsp;&nbsp;&nbsp;&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条测量类工具需求信息</font>需审批";
								    }
									content[0]+=s_temp; 
								}				
						}
					}
				);	
		       result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
		  }
		
	   }else if(userlevel.equals("2")){
		   
			try {			
				String sql="select count(*) as ct from T_C_ENTRY_TRECORD where STATUS!='申请' and STATUS!='已入库' ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
					                  	content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条刀具入库信息</font>需处理";
									    }
										content[0]+=s_temp; 
									}				
						}
					}
				);	
			    result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(*) as ct from T_CT_ENTRY_TRECORD where STATUS!='申请' and STATUS!='已入库' ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
					                  	content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条非测量类工具入库信息</font>需处理";
									    }
										content[0]+=s_temp; 
									}				
						}
					}
				);	
			    result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(*) as ct from T_MT_ENTRY_TRECORD where STATUS!='申请' and STATUS!='已入库' ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
					                  	content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条测量类工具入库信息</font>需处理";
									    }
										content[0]+=s_temp; 
									}				
						}
					}
				);	
			    result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(*) as ct from T_M_ENTRY_TRECORD where STATUS!='申请' and STATUS!='已入库' ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
					                  	content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;您当前有<font color=blue >"+""+rs.getString("ct")+""+"条量具入库信息</font>需处理";
									    }
										content[0]+=s_temp; 
									}				
						}
					}
				);	
			    result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}	
			try {			
				String sql="select count(distinct grd_num) as ct from t_c_grd_record where status='0' and grd_num is not null ";
				String sql1="select count(distinct grd_num) as st from t_c_grd_record where status='0' and grd_num is not null and app_status='未处理'";
				final int []i=new int[1];
				final int []j=new int[1];
				Logger.debug(sql);
				Logger.debug(sql1);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{					
					while(rs.next()){  
						                i[0] = Integer.parseInt(rs.getString("ct"));					                  
									}				
							}
						}
					);	
			    DbUtil.execute(sql1,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException{					
						while(rs.next()){  
							            j[0] = Integer.parseInt(rs.getString("st"));					                  
										}				
								}
							}
						);	
			    int k = i[0]-j[0];
			    System.out.println(k);
				content[0]="";
			    String s_temp="";
			    if(k!=0){
				s_temp+="&nbsp;您当前有<font color=blue >"+""+k+""+"张送磨单</font>需处理";
			    }
				content[0]+=s_temp; 
			    result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
				}catch (SQLException e){	
					e.printStackTrace();
			 }	
			try {			
				String sql="select * from t_cutter ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					int i=0;
					while(rs.next()){
						  if(rs.getInt("UC_COUNT_IN")<rs.getInt("MINI_QS")){
							  i++;							  
						  }						               
						}	
					    content[0]="";
					    String s_temp="";
					    if(i!=0){
						s_temp+="&nbsp;您当前有<font color=blue >"+""+i+""+"条刀具在库量低于最低库存量</font>";
					    }
						content[0]+=s_temp; 
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select * from T_M_TYPE ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					final int []j = new int[1];
					int k = 0;
					int i=0;
					while(rs.next()){
						k = rs.getInt("mini_qs");
						String sql1="select count(*) as old_now_store from  T_M_INDI where newstatus='旧' and status='在库' and type_id='"+rs.getString("id")+"'"; 
						try {
							DbUtil.execute(sql1,new IResultSetProcessor() {
								public void process(ResultSet rs) throws SQLException {
									if (rs.next()) {
										j[0] = rs.getInt("old_now_store") ;										
									}
								}	
							});	
						}catch (SQLException e) {
						// TODO 自动生成 catch 块
							e.printStackTrace();
						}  
						
						if(j[0] < k){
							  i++;							  
						  }						               
						}	
					    content[0]="";
					    String s_temp="";
					    if(i!=0){
						s_temp+="&nbsp;您当前有<font color=blue >"+""+i+""+"条量具在库量低于最低库存量</font>";
					    }
						content[0]+=s_temp; 
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select * from T_CT ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					int i=0;
					while(rs.next()){
						  if(rs.getInt("UCT_COUNT_IN")<rs.getInt("MINI_QS")){
							  i++;							  
						  }						               
						}	
					    content[0]="";
					    String s_temp="";
					    if(i!=0){
						s_temp+="&nbsp;您当前有<font color=blue >"+""+i+""+"条非测量类工具在库量低于最低库存量</font>";
					    }
						content[0]+=s_temp; 
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select * from T_MT_TYPE ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					final int []j = new int[1];
					int k = 0;
					int i=0;
					while(rs.next()){
						k = rs.getInt("mini_qs");
						String sql1="select count(*) as old_now_store from  T_MT_INDI where newstatus='旧' and status='在库' and type_id='"+rs.getString("id")+"'"; 
						try {
							DbUtil.execute(sql1,new IResultSetProcessor() {
								public void process(ResultSet rs) throws SQLException {
									if (rs.next()) {
										j[0] = rs.getInt("old_now_store") ;										
									}
								}	
							});	
						}catch (SQLException e) {
						// TODO 自动生成 catch 块
							e.printStackTrace();
						}  
						
						if(j[0] < k){
							  i++;							  
						  }						               
						}	
					    content[0]="";
					    String s_temp="";
					    if(i!=0){
						s_temp+="&nbsp;您当前有<font color=blue >"+""+i+""+"条测量类工具在库量低于最低库存量</font>";
					    }
						content[0]+=s_temp; 
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(lt_num) as ct from t_c_lt_apply_record where LEADER_APPROVE='批准' and over='未处理' and lt!='正常报废' ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
						                content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;您在<font color=blue >刀具处罚信息处有"+""+rs.getString("ct")+""+"条信息</font>需查看";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(lt_num) as ct from t_ct_lt_apply_record where LEADER_APPROVE='批准' and over='未处理' and lt!='正常报废' ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
						                content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;您在<font color=blue >非测量类工具处罚信息处有"+""+rs.getString("ct")+""+"条信息</font>需查看";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(lt_num) as ct from t_m_lt_record where LEADER_APPROVE='批准'  and over='未处理' and ot!='正常报废' ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
						                content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;您在<font color=blue >量具处罚信息处有"+""+rs.getString("ct")+""+"条信息</font>需查看";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(lt_num) as ct from t_mt_lt_record where LEADER_APPROVE='批准'  and over='未处理' and ot!='正常报废' ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
						                content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;您在<font color=blue >测量类工量具处罚信息处有"+""+rs.getString("ct")+""+"条信息</font>需查看";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
	   }else if(userlevel.equals("1")||userlevel.equals("7")){
		   try {		
				
				String sql="select count(apply_num) as ct from t_hq_c_apply_record where gleader_approve='批准' and leader_approve='批准' and worker_num='"+userid+"' and status='未处理'";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{					
					while(rs.next()){	
						                content[0]="";					                
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){									 
										   s_temp+="&nbsp;&nbsp;&nbsp;您有<font color=blue >"+""+rs.getString("ct")+""+"条高质刀具借用申请已处理</font>需查看";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(apply_num) as ct from t_hqm_bapply where GLEADER_APPROVE='批准' and worker_num='"+userid+"' and state='未处理'";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
						                content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;&nbsp;&nbsp;您有<font color=blue >"+""+rs.getString("ct")+""+"条高质量具借用申请已处理</font>需查看";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(apply_num) as ct from v_ct2hqapply where gleader_approve='批准' and leader_approve='批准' and worker_num='"+userid+"' and status='未处理'";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
						                content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;&nbsp;&nbsp;您有<font color=blue >"+""+rs.getString("ct")+""+"条非测量类高质工具借用申请已处理</font>需查看";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(apply_num) as ct from t_hqmt_bapply where GLEADER_APPROVE='批准' and worker_num='"+userid+"' and state='未处理'";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
						                content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;&nbsp;&nbsp;您有<font color=blue >"+""+rs.getString("ct")+""+"条测量类高质工具借用申请已处理</font>需查看";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(lt_num) as ct from t_c_lt_apply_record where LEADER_APPROVE='批准' and pl_num='"+userid+"' and over='未处理' and lt!='正常报废' ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
						                content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;&nbsp;&nbsp;您在<font color=blue >刀具处罚信息处有"+""+rs.getString("ct")+""+"条信息</font>需查看";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(lt_num) as ct from t_ct_lt_apply_record where LEADER_APPROVE='批准' and pl_num='"+userid+"' and over='未处理' and lt!='正常报废' ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
						                content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;&nbsp;&nbsp;您在<font color=blue >非测量类工具处罚信息处有"+""+rs.getString("ct")+""+"条信息</font>需查看";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(lt_num) as ct from t_m_lt_record where LEADER_APPROVE='批准' and LIABLE_STUFF_NUM='"+userid+"' and over='未处理' and ot!='正常报废' ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
						                content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;&nbsp;&nbsp;您在<font color=blue >量具处罚信息处有"+""+rs.getString("ct")+""+"条信息</font>需查看";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
			try {			
				String sql="select count(lt_num) as ct from t_mt_lt_record where LEADER_APPROVE='批准' and LIABLE_STUFF_NUM='"+userid+"' and over='未处理' and ot!='正常报废' ";
				Logger.debug(sql);
			    DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException{
					
					while(rs.next()){
						                content[0]="";
									    String s_temp="";
									    if(Integer.parseInt(rs.getString("ct"))!=0){
										s_temp+="&nbsp;&nbsp;&nbsp;您在<font color=blue >测量类工量具处罚信息处有"+""+rs.getString("ct")+""+"条信息</font>需查看";
									    }
										content[0]+=s_temp; 
									}				
				    	}
				  }
			  );	
			  result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
			}catch (SQLException e){	
				e.printStackTrace();
			}
		
	   }else if("8".equals(userlevel)){
		   //计算报废信息条数
		   //首先计算总的订单数
		   final JSONObject json = new JSONObject();
		   final String[] count = new String[1];
		  /* String sqltotal = "select count(*) as ct from vmaterial_com where valiadate < sysdate";
		   try {
			DbUtil.execute(sqltotal,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						try {
							json.put("total",rs.getString("ct"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				   
			   });
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		  //计算报废的订单数
		 HashMap hashmap = new HashMap();
		 hashmap.put("userlevel",userlevel);
		 Material_delete un = (Material_delete)AppInsFactory.getBean("Material_delete");
		try {
			Object obj =  un.queryAll1(hashmap).get("totalProperty");
			json.put("obj",obj);
			/*System.out.println(obj);*/
		} catch (JSONException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		  /*String sqlorder = "select ordernum from vmaterial_com where valiadate < sysdate";
		  	try {
				DbUtil.execute(sqlorder,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						  int i = 0;
						while(rs.next()){
							String ordernum = rs.getString("ordernum");
							String sql = "select count(*) as ct from t_material_delete_record where ordernum ='"+ ordernum+"'and kuname='海波库'";
							DbUtil.execute(sql,new IResultSetProcessor(){
								
								public void process(ResultSet rs)throws SQLException {
									while(rs.next()){
										if("0".equals(rs.getString("ct"))){
											count[0] = "1";
										}else{
											count[0] = "0";
										}
									}
								}
								
							});
							if("1".equals(count[0])){
								i++;
							}else{
								continue;
							}
							
						}
						try {
							json.put("del",i);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
					
				});
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			content[0]="";
			try {
				int last = json.getInt("obj");
				if(last>0){
				String s_temp ="&nbsp;&nbsp;&nbsp;<font color=black>您有</fount><font color=blue >"+last+"条材料订单</font><font color=black>需要报废</font>";
				/*content[0]+=s_temp;*/
				result+="<a class=n target=_blank  href=web/sms/storemgtmod/material/material_deletelookup.jsp ><p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+s_temp+"</font></p></a>";
				}/*else{
					result+="";
				}*/
				} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		   //计算预算条数
		   /*String sql = "select count(distinct typeid) as ct from vmaterial_com where  valiadate > sysdate";
		   try {
			DbUtil.execute(sql,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
							 content[0]="";
							 String s_temp="";
							 if(Integer.parseInt(rs.getString("ct"))!=0){
								 s_temp+="&nbsp;&nbsp;&nbsp;您有<font color=blue >"+""+rs.getString("ct")+""+"条材料信息需要预算</font>";
							 }
							 content[0]+=s_temp;
						}
						
					}
					   
				   });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
			Store_Search um = (Store_Search)AppInsFactory.getBean("Store_Search");
			
			Object obj;
			String s_temp="";
			try {
				obj = um.QueryAllStore1().get("totalProperty");
				json.put("obj",obj);
				/*System.out.println(obj);*/
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(json.getInt("obj")!=0){
				s_temp+="&nbsp;&nbsp;&nbsp;<font color=black >您有</font><font color=blue >"+""+json.getString("obj")+""+"种材料</font><font color=black >需要预算</font>";
				content[0]+=s_temp;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 result+="<a class=n target=_blank href=web/sms/storestatmod/storestatemod_material/material_precomputelookup.jsp><p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p><a>";
	   }else if("9".equals(userlevel)){
		   //分库材料报废
		 //首先计算总的订单数
		   final JSONObject json = new JSONObject();
		   final String[] count = new String[1];
		  /* String sqltotal = "select count(*) as ct from v_matchange_com where valiadate < sysdate and newkuname = '调漆间'";
		   try {
			DbUtil.execute(sqltotal,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						try {
							json.put("total",rs.getString("ct"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				   
			   });
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		   
		   HashMap hashmap = new HashMap();
			 hashmap.put("userlevel",userlevel);
			 Material_delete un = (Material_delete)AppInsFactory.getBean("Material_delete");
			try {
				Object obj =  un.queryAll1(hashmap).get("totalProperty");
				json.put("obj",obj);
				System.out.println(obj);
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		  //计算报废的订单数
		  /*String sqlorder = "select ordernum from v_matchange_com where valiadate < sysdate and newkuname = '调漆间'";
		  	try {
				DbUtil.execute(sqlorder,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						int i = 0;
						while(rs.next()){
							String ordernum = rs.getString("ordernum");
							String sql = "select count(*)as ct from t_material_delete_record where ordernum ='"+ ordernum+"'and kuname='调漆间'";
							DbUtil.execute(sql,new IResultSetProcessor(){
								
								public void process(ResultSet rs)throws SQLException {
									while(rs.next()){
										if("0".equals(rs.getString("ct"))){
											count[0] = "1";
										}else{
											count[0] = "0";
										}
										
									}
								}
								
							});
							if("1".equals(count[0])){
								i++;
							}else{
								continue;
							}
						
						}
						try {
							json.put("del",i);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				});
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
		  	
			try {
				int last = json.getInt("obj");
				if(last>0){
				String s_temp = "&nbsp;&nbsp;&nbsp;<font color=black >您有</font><font color=blue >"+""+last+""+"条材料订单</font><font color=black >需要报废</font>";
				 result+="<a class=n target=_blank href=web/sms/storemgtmod/material/material_deletelookup.jsp ><p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+s_temp+"</font></p></a>";
				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	   }else if("10".equals(userlevel)){
		   //计算需要报废的材料订单数
		   final JSONObject json = new JSONObject();
		   final String[] count = new String[1];
		   /*String sqlorder = "select ordernum from v_matchange_com where valiadate < sysdate and newkuname in ('江北库','句容库')";
		  	try {
				DbUtil.execute(sqlorder,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						int i = 0;
						while(rs.next()){
							String ordernum = rs.getString("ordernum");
							String sql = "select count(*)as ct from t_material_delete_record where ordernum ='"+ ordernum+"'and kuname in ('江北库','句容库')";
							DbUtil.execute(sql,new IResultSetProcessor(){
								
								public void process(ResultSet rs)throws SQLException {
									while(rs.next()){
										if("0".equals(rs.getString("ct"))){
											count[0] = "1";
										}else{
											count[0] = "0";
										}
										
									}
								}
								
							});
							if("1".equals(count[0])){
								i++;
							}else{
								continue;
							}
						
						}
						try {
							json.put("del",i);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				});
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
		   HashMap hashmap = new HashMap();
			 hashmap.put("userlevel",userlevel);
			 Material_delete un = (Material_delete)AppInsFactory.getBean("Material_delete");
			try {
				Object obj =  un.queryAll1(hashmap).get("totalProperty");
				json.put("obj",obj);
				System.out.println(obj);
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			try {
				int last = json.getInt("obj");
				if(last>0){
				String s_temp = "&nbsp;&nbsp;&nbsp;<font color=black >您有</font><font color=blue >"+""+last+""+"条材料订单</font><font color=black >需要报废</font>";
				 result+="<a class=n target=_blank  href=web/sms/storemgtmod/material/material_deletelookup.jsp ><p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+s_temp+"</font></p></a>";
				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		   
		   /*String sql = "select count(*) as ct from v_matchange_com where newkuname in('江北库','句容库') and valiadate < sysdate";
		   try {
			DbUtil.execute(sql,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
							 content[0]="";
							 String s_temp="";
							 if(Integer.parseInt(rs.getString("ct"))!=0){
								 s_temp+="&nbsp;&nbsp;&nbsp;您有<font color=blue >"+""+rs.getString("ct")+""+"条材料信息需要预算</font>";
							 }
							 content[0]+=s_temp;
						}
						
					}
					   
				   });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";*/
	   }else if("12".equals(userlevel)){
		   String sql = "select count(*) as ct from t_instrument where detectdate < sysdate";
		   try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						 content[0]="";
						 String s_temp="";
						 if(Integer.parseInt(rs.getString("ct"))!=0){
							 s_temp+="&nbsp;&nbsp;&nbsp;<font color=black >您有</font><font color=blue >"+""+rs.getString("ct")+""+"条仪表信息</font><font color=black >需要报废</font>";
						 }
						 content[0]+=s_temp;
					}
				}
				   
			   });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
		   
		   
		   String sql1 = "select count(*) as ct from t_instrument where detectdate > sysdate and detectdate < sysdate + 30";
		   try {
			DbUtil.execute(sql1,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						 content[0]="";
						 String s_temp="";
						 if(Integer.parseInt(rs.getString("ct"))!=0){
							 s_temp+="&nbsp;&nbsp;&nbsp;您有<font color=blue >"+""+rs.getString("ct")+""+"条仪表</font>预警信息";
						 }
						 content[0]+=s_temp;
					}
				}
				   
			   });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   result+="<p  style=\"text-align:left; line-height:20px;\" ><font size=\"2\" face=\"宋体\">"+content[0]+"</font></p>";
	   }
			Logger.debug("*********userLevel="+userlevel);
			return result;
  }
}
