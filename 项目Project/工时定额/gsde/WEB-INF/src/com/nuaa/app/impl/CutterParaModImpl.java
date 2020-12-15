package com.nuaa.app.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.CutterParaMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class CutterParaModImpl implements CutterParaMod{

	@Override
	public String addCutPara(final HashMap hashmap) {
		// TODO Auto-generated method stub
		System.out.println("我我我我我");
		final String bianhao = hashmap.get("bianhao").toString();
		final String jgyl = hashmap.get("jgyl").toString();
		final String jcxh = hashmap.get("jcxh").toString();
		final String clph = hashmap.get("clph").toString();
		final String djcs = hashmap.get("djcs").toString();
		final String djxlh = hashmap.get("djxlh").toString();
		final String zhijing = hashmap.get("zhijing").toString();
		final String rjbj = hashmap.get("rjbj").toString();
		final String jgfs = hashmap.get("jgfs").toString();
		final String ap = hashmap.get("ap").toString();
		final String ae = hashmap.get("ae").toString();
		final String zzzs = hashmap.get("zzzs").toString();
		final String xsl = hashmap.get("xsl").toString();
		final String mcjg = hashmap.get("mcjg").toString();
		final String dtlx = hashmap.get("dtlx").toString();
		final String lqy = hashmap.get("lqy").toString();
		final String lqfs = hashmap.get("lqfs").toString();
		final String clqcl = hashmap.get("clqcl").toString();
		final String fx = hashmap.get("fx").toString();
		final String fy = hashmap.get("fy").toString();
		final String fz = hashmap.get("fz").toString();
		final String gl = hashmap.get("gl").toString();
		final String nj = hashmap.get("nj").toString();
		final String bx = hashmap.get("bx").toString();
		final String jcbw = hashmap.get("jcbw").toString();
		final String rbwq = hashmap.get("rbwq").toString();
		final String dtyl = hashmap.get("dtyl").toString();
		final String ccddj = hashmap.get("ccddj").toString();
		final String qxwd = hashmap.get("qxwd").toString();
		final String yzr = hashmap.get("yzr").toString();
		final String sjly = hashmap.get("sjly").toString();
		final String xxfs = hashmap.get("xxfs").toString();
		final String gxtj = hashmap.get("gxtj").toString();
		final String yyzt = hashmap.get("yyzt").toString();
		final String djsm = hashmap.get("djsm").toString();
		final String beizhu = hashmap.get("beizhu").toString();
		System.out.println(mcjg);
		final String[] count = new String[1];
		String result = "true";
		final JSONObject daoju = new JSONObject();
		String sqlhhh = "select xinghao from T_CUTTER where company ='"+djcs+"'and xlh = '"+djxlh+"'and zhijing ='"+zhijing+"'and rjbj ='"+rjbj+"'";//机床型号和机床id、机床编号一一对应
		Logger.debug(sqlhhh);
		try {
			DbUtil.execute(sqlhhh,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					String daojuxinghao = "";
					// TODO Auto-generated method stub
					while(rs.next()){
						try {
							daoju.put("daojuxinghao",rs.getString("xinghao"));
							System.out.println(daoju.getString("daojuxinghao"));
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
		}
		//try {String zczczz = daoju.getString("daojuxinghao")}
		//System.out.println(daoju.getString("daojuxinghao"));
		 try {
			String daojuxinghao = daoju.getString("daojuxinghao");
			System.out.println(daoju.getString("daojuxinghao"));
			 String sql = "select count(*) as ct from T_CUTPARA where djxh = '"+daojuxinghao+"'and mingcheng = '"+clph+"'and jcxh = '"+jcxh+"'and jgfs = '"+jgfs+"'and ccddj = '"+ccddj+"'and jgyl='"+jgyl+"'and ap = '"+ap+"'and ae = '"+ae+"'and mcjg = '"+mcjg+"'and zzzs = '"+zzzs+"'and fx = '"+fx+"'and fy = '"+fy+"'and fz = '"+fz+"'and xsl = '"+xsl+"'and dtlx = '"+dtlx+"'";
			 
				DbUtil.execute(sql,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						// TODO Auto-generated method stub
						while(rs.next()){
						count[0] = rs.getString("ct");
						System.out.println("f f f ");
						System.out.println(count[0]);
						}
					}
				});
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if("0".equals(count[0])){
			final String id = UUID.randomUUID().toString();
			String[] sql1 = new String[1];
			sql1[0] = "insert into T_CUTPARA (id,bianhao,jcxh,djxh,jgfs,ap,ae,jgsd,qxsd,zzzs,mcjg,xsl,dtlx,lqy,lqfs,clqcl,fx,fy,fz,gl,nj,bx,jcbw,rbwq,dtyl,ccddj,qxwd,yzr,sjly,xxfs,gxtj,yyzt,djsm,beizhu,mingcheng,jgyl)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Logger.debug(sql1[0]);
			try {
				DbUtil.executeBatchs(sql1,new IArrayPreparedStatementProcessor(){
					public void process(final PreparedStatement[] pstmts)throws SQLException {
						// TODO Auto-generated method stub
						pstmts[0].setString(1,id);
						pstmts[0].setString(2,bianhao);
						pstmts[0].setString(3,jcxh);
						System.out.println("我我我我我");
						//根据刀具厂商+系列号+刀具直径+刀具R角半径————————唯一确定刀具id；选出刀具型号，填入切削参数表中
					String sql2 = "select xinghao,chishu from T_CUTTER where company ='"+djcs+"'and xlh = '"+djxlh+"'and zhijing ='"+zhijing+"'and rjbj ='"+rjbj+"'";//机床型号和机床id、机床编号一一对应
					DbUtil.execute(sql2,new IResultSetProcessor(){
						public void process(ResultSet rs)throws SQLException {
							// TODO Auto-generated method stub
							while(rs.next()){
							int chishu = rs.getInt("chishu");
							float jgsd = 0;
							float qxsd = 0;
							System.out.println(chishu);
							System.out.println(mcjg);
							jgsd = (float) (chishu*(Integer.parseInt(zzzs))*(Float.parseFloat(mcjg)));
							qxsd = (float) ((Integer.parseInt(zhijing)*3.1415*Integer.parseInt(zzzs))/1000);
							System.out.println("我我我我我");
							System.out.println(jgsd);
							System.out.println(mcjg);
							pstmts[0].setString(4,rs.getString("xinghao"));
							pstmts[0].setString(5,jgfs);
							pstmts[0].setString(6,ap);
							pstmts[0].setString(7,ae);
							pstmts[0].setFloat(8,jgsd);
							pstmts[0].setFloat(9,qxsd);
						}
						}
					});
						pstmts[0].setString(10,zzzs);
						pstmts[0].setString(11,mcjg);
						pstmts[0].setString(12,xsl);
						pstmts[0].setString(13,dtlx);
						pstmts[0].setString(14,lqy);
						pstmts[0].setString(15,lqfs);
						pstmts[0].setString(16,clqcl);
						pstmts[0].setString(17,fx);
						pstmts[0].setString(18,fy);
						pstmts[0].setString(19,fz);
						pstmts[0].setString(20,gl);
						pstmts[0].setString(21,nj);
						pstmts[0].setString(22,bx);
						pstmts[0].setString(23,jcbw);
						pstmts[0].setString(24,rbwq);
						pstmts[0].setString(25,dtyl);
						pstmts[0].setString(26,ccddj);
						pstmts[0].setString(27,qxwd);
						pstmts[0].setString(28,yzr);
						pstmts[0].setString(29,sjly);
						pstmts[0].setString(30,xxfs);
						pstmts[0].setString(31,gxtj);
						pstmts[0].setString(32,yyzt);
						pstmts[0].setString(33,djsm);
						pstmts[0].setString(34,beizhu);
						pstmts[0].setString(35,clph);
						pstmts[0].setString(36,jgyl);
						pstmts[0].addBatch();
					}
				});
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "false";
			}
			result = "true";
		}
		else{
			result = "repeat";
		}
		     return result;
	}

	@Override
	public JSONObject QueryAllCutPara(HashMap hashmap) {
		// TODO Auto-generated method stub
		int start = Integer.parseInt((String)hashmap.get("start"));
		int limit = Integer.parseInt((String)hashmap.get("limit"));
		String filter = (String)hashmap.get("filter");
		String order = (String)hashmap.get("order");	
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sql1 = "select count(*) as ct from v_cutpara_cutter where "+ filter;
		String sql2 = "select * from (select * from  (select * from v_cutpara_cutter where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from v_cutpara_cutter where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order; 
		Logger.debug(sql1);
		Logger.debug(sql2);
		try {
			DbUtil.execute(sql1,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						try {
							obj.put("totalProperty",rs.getString("ct"));
							//System.out.println(obj.get("totalProperty"));
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
					final JSONObject json;
					try {
						json = new JSONObject();
						json.put("id", rs.getString("id"));
						json.put("bianhao", rs.getString("bianhao"));
						if(rs.getString("jcxh")!=""){
							json.put("jcxh", rs.getString("jcxh"));
						}if(rs.getString("djxh")!=""){
							json.put("djxh", rs.getString("djxh"));
						}if(rs.getString("jgfs")!=""){
							json.put("jgfs", rs.getString("jgfs"));
						}if(rs.getString("ap")!=""){
							json.put("ap", rs.getDouble("ap"));
						}if(rs.getString("ae")!=""){
							json.put("ae", rs.getDouble("ae"));
							System.out.println(rs.getFloat("ae"));
						}if(rs.getString("jgsd")!=""){
							json.put("jgsd", rs.getString("jgsd"));
						}if(rs.getString("qxsd")!=""){
							json.put("qxsd", rs.getString("qxsd"));
						}if(rs.getString("zzzs")!=""){
							json.put("zzzs", rs.getString("zzzs"));
						}if(rs.getString("mcjg")!=""){
							json.put("mcjg", rs.getString("mcjg"));
						}if(rs.getString("xsl")!=""){
							json.put("xsl", rs.getString("xsl"));
						}if(rs.getString("dtlx")!=""){
							json.put("dtlx", rs.getString("dtlx"));
						}if(rs.getString("lqy")!=""){
							json.put("lqy", rs.getString("lqy"));
						}if(rs.getString("lqfs")!=""){
							json.put("lqfs", rs.getString("lqfs"));
						}if(rs.getString("clqcl")!=""){
							json.put("clqcl", rs.getString("clqcl"));
						}if(rs.getString("fx")!=""){
							json.put("fx", rs.getString("fx"));
						}if(rs.getString("fy")!=""){
							json.put("fy", rs.getString("fy"));
						}if(rs.getString("fz")!=""){
							json.put("fz", rs.getString("fz"));
						}if(rs.getString("gl")!=""){
							json.put("gl", rs.getString("gl"));
						}if(rs.getString("nj")!=""){
							json.put("nj", rs.getString("nj"));
						}if(rs.getString("bx")!=""){
							json.put("bx", rs.getString("bx"));
						}if(rs.getString("jcbw")!=""){
							json.put("jcbw", rs.getString("jcbw"));
						}if(rs.getString("rbwq")!=""){
							json.put("rbwq", rs.getString("rbwq"));
						}if(rs.getString("dtyl")!=""){
							json.put("dtyl", rs.getString("dtyl"));
						}if(rs.getString("ccddj")!=""){
							json.put("ccddj", rs.getString("ccddj"));
						}if(rs.getString("qxwd")!=""){
							json.put("qxwd", rs.getString("qxwd"));
						}if(rs.getString("yzr")!=""){
							json.put("yzr", rs.getString("yzr"));
						}if(rs.getString("sjly")!=""){
							json.put("sjly", rs.getString("sjly"));
						}if(rs.getString("xxfs")!=""){
							json.put("xxfs", rs.getString("xxfs"));
						}if(rs.getString("gxtj")!=""){
							json.put("gxtj", rs.getString("gxtj"));
						}
						if(rs.getString("yyzt")!=""){
							json.put("yyzt", rs.getString("yyzt"));
						}if(rs.getString("djsm")!=""){
							json.put("djsm", rs.getString("djsm"));
						}if(rs.getString("beizhu")!=""){
							json.put("beizhu", rs.getString("beizhu"));
						}if(rs.getString("clmc")!=""){
							json.put("clmc", rs.getString("clmc"));
						}if(rs.getString("jgyl")!=""){
							json.put("jgyl", rs.getString("jgyl"));
						}
						json.put("company", rs.getString("company"));
						json.put("xlh", rs.getString("xlh"));
						json.put("zhijing", rs.getString("zhijing"));
						json.put("rjbj", rs.getString("rjbj"));
						json.put("mingcheng", rs.getString("djmc"));
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
		return obj;
	}


	@Override
	public String delCutPara(HashMap hashmap) {
		// TODO Auto-generated method stub
		final String id = hashmap.get("id").toString();
		String[] sql = new String[1];
		String result = "true";
		sql[0] = "delete from T_CUTPARA where id in ('',"+ id +")";
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
	public String editCutPara(final HashMap hashmap) {
		// TODO Auto-generated method stub
		final String id = hashmap.get("id").toString();
		String result = "true";
		final String bianhao = hashmap.get("bianhao").toString();
		/*final String jgtz = hashmap.get("jgtz").toString();*/
		final String jcxh = hashmap.get("jcxh").toString();
		final String clph = hashmap.get("clph").toString();
		final String djcs = hashmap.get("djcs").toString();
		final String djxlh = hashmap.get("djxlh").toString();
		final String zhijing = hashmap.get("zhijing").toString();
		final String rjbj = hashmap.get("rjbj").toString();
		final String jgfs = hashmap.get("jgfs").toString();
		final String ap = hashmap.get("ap").toString();
		final String ae = hashmap.get("ae").toString();
		final String zzzs = hashmap.get("zzzs").toString();
		final String xsl = hashmap.get("xsl").toString();
		final String mcjg = hashmap.get("mcjg").toString();
		final String dtlx = hashmap.get("dtlx").toString();
		final String lqy = hashmap.get("lqy").toString();
		final String lqfs = hashmap.get("lqfs").toString();
		final String clqcl = hashmap.get("clqcl").toString();
		final String fx = hashmap.get("fx").toString();
		final String fy = hashmap.get("fy").toString();
		final String fz = hashmap.get("fz").toString();
		final String gl = hashmap.get("gl").toString();
		final String nj = hashmap.get("nj").toString();
		final String bx = hashmap.get("bx").toString();
		final String jcbw = hashmap.get("jcbw").toString();
		final String rbwq = hashmap.get("rbwq").toString();
		final String dtyl = hashmap.get("dtyl").toString();
		final String ccddj = hashmap.get("ccddj").toString();
		final String qxwd = hashmap.get("qxwd").toString();
		final String yzr = hashmap.get("yzr").toString();
		final String sjly = hashmap.get("sjly").toString();
		final String xxfs = hashmap.get("xxfs").toString();
		final String gxtj = hashmap.get("gxtj").toString();
		final String yyzt = hashmap.get("yyzt").toString();
		final String djsm = hashmap.get("djsm").toString();
		final String beizhu = hashmap.get("beizhu").toString();
		final String jgyl = hashmap.get("jgyl").toString();
		final String[] count = new String[1];
		final JSONObject daoju = new JSONObject();
		String sqlhhh = "select xinghao from T_CUTTER where company ='"+djcs+"'and xlh = '"+djxlh+"'and zhijing ='"+zhijing+"'and rjbj ='"+rjbj+"'";//机床型号和机床id、机床编号一一对应
		Logger.debug(sqlhhh);
		try {
			DbUtil.execute(sqlhhh,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					String daojuxinghao = "";
					// TODO Auto-generated method stub
					while(rs.next()){
						try {
							daoju.put("daojuxinghao",rs.getString("xinghao"));
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
		}
		
		 try {
			 String daojuxinghao = daoju.getString("daojuxinghao");
			 
			 String sql = "select count(*) as ct from T_CUTPARA where djxh = '"+daojuxinghao+"'and mingcheng = '"+clph+"'and jcxh = '"+jcxh+"'and jgfs = '"+jgfs+"'and ccddj = '"+ccddj+"'and jgyl='"+jgyl+"'and ap = '"+ap+"'and ae = '"+ae+"'and mcjg = '"+mcjg+"'and zzzs = '"+zzzs+"'and fx = '"+fx+"'and fy = '"+fy+"'and fz = '"+fz+"'and xsl = '"+xsl+"'and dtlx = '"+dtlx+"'";
			 
				DbUtil.execute(sql,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						// TODO Auto-generated method stub
						while(rs.next()){
						count[0] = rs.getString("ct");
						}
					}
				});
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*if("0".equals(count[0])){*/
			String[] sqls = new String[1];
			sqls[0] = "update T_CUTPARA set bianhao=?,JCXH=?,DJXH=?,JGFS=?,AP=?,ae=?,JGSD=?,QXSD=?,ZZZS=?,MCJG=?,XSL=?,DTLX=?,LQY=?,LQFS=?,CLQCL=?,FX=?,fy=?,fz=?,gl=?,nj=?,bx=?,JCBW=?,RBWQ=?,DTYL=?,CCDDJ=?,QXWD=?,YZR=?,SJLY=?,XXFS=?,GXTJ=?,YYZT=?,DJSM=?,BEIZHU=?,MINGCHENG=?,jgyl=? where id ='"+ id +"'";
				Logger.debug(sqls[0]);
				try {
					DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
						public void process(final PreparedStatement[] pstmts)throws SQLException {
							// TODO Auto-generated method stub
							pstmts[0].setString(1,bianhao);
							pstmts[0].setString(2,jcxh);
							
							//根据刀具厂商+系列号+刀具直径+刀具R角半径————————唯一确定刀具id；选出刀具型号，填入切削参数表中
						String sql1 = "select xinghao,chishu from T_CUTTER where company ='"+djcs+"'and xlh = '"+djxlh+"'and zhijing ='"+zhijing+"'and rjbj ='"+rjbj+"'";//机床型号和机床id、机床编号一一对应
						DbUtil.execute(sql1,new IResultSetProcessor(){
							public void process(ResultSet rs)throws SQLException {
								// TODO Auto-generated method stub
								while(rs.next()){
								int chishu = rs.getInt("chishu");
								float jgsd = 0;
								float qxsd = 0;
								
								jgsd = chishu*Integer.parseInt(zzzs)*Float.parseFloat(mcjg);
								qxsd = (float) ((Integer.parseInt(zhijing)*3.1415*Integer.parseInt(zzzs))/1000);
								pstmts[0].setString(3,rs.getString("xinghao"));
								pstmts[0].setString(4,jgfs);
								pstmts[0].setString(5,ap);
								pstmts[0].setString(6,ae);
								pstmts[0].setFloat(7,jgsd);
								pstmts[0].setFloat(8,qxsd);
							}
							}
						});
							pstmts[0].setString(9,zzzs);
							pstmts[0].setString(10,mcjg);
							pstmts[0].setString(11,xsl);
							pstmts[0].setString(12,dtlx);
							pstmts[0].setString(13,lqy);
							pstmts[0].setString(14,lqfs);
							pstmts[0].setString(15,clqcl);
							pstmts[0].setString(16,fx);
							pstmts[0].setString(17,fy);
							pstmts[0].setString(18,fz);
							pstmts[0].setString(19,gl);
							pstmts[0].setString(20,nj);
							pstmts[0].setString(21,bx);
							pstmts[0].setString(22,jcbw);
							pstmts[0].setString(23,rbwq);
							pstmts[0].setString(24,dtyl);
							pstmts[0].setString(25,ccddj);
							pstmts[0].setString(26,qxwd);
							pstmts[0].setString(27,yzr);
							pstmts[0].setString(28,sjly);
							pstmts[0].setString(29,xxfs);
							pstmts[0].setString(30,gxtj);
							pstmts[0].setString(31,yyzt);
							pstmts[0].setString(32,djsm);
							pstmts[0].setString(33,beizhu);
							pstmts[0].setString(34,clph);
							pstmts[0].setString(35,jgyl);
							pstmts[0].addBatch();
						}
					});
				result = "true";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = "false";
			}
		/*}else{
			result = "repeat";
		}*/
		return result;
	}
	

}
