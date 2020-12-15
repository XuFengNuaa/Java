package com.nuaa.app.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.UsageStatMod;
import com.nuaa.sys.util.AppInsFactory;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;

public class UsageStatModImpl implements UsageStatMod{
	public JSONObject UsageQuery(HashMap hashmap) {
		final String filter = (String) hashmap.get("filter");
		final String filter1 = (String) hashmap.get("filter1");
		final String filter2 = (String) hashmap.get("filter2");
		final String filter3 = (String) hashmap.get("filter3");
		final String order = (String) hashmap.get("order");
		final JSONObject obj = new JSONObject();
		final JSONArray jsonarray = new JSONArray();
		String sql = "select count (distinct mnum) as ct from vmaterial_com";
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
		String sql1 = "select distinct mnum from vmaterial_com where "+filter;
		System.out.println(sql1);
			try {
				DbUtil.execute(sql1,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						while(rs.next()){
							final JSONObject json = new JSONObject();
							final String mnum = rs.getString("mnum");
							String sqlbasic = "select classname,name,spec,company,guaranteedate,modelnum,buycycle from vmaterial_com where mnum = '"+mnum+"'";
							DbUtil.execute(sqlbasic,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									while(rs.next()){
										try {
											json.put("mnum",mnum);
											json.put("classname",rs.getString("classname"));
											json.put("guaranteedate",rs.getString("guaranteedate"));
											json.put("name",rs.getString("name"));
											json.put("spec",rs.getString("spec"));
											json.put("company",rs.getString("company"));
											json.put("modelnum",rs.getString("modelnum"));
											json.put("buycycle",rs.getString("buycycle"));
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
							});
							//System.out.println("bwcvwulwvwuuuuuuuuuuuuuuuuuuuuuuu");
							
							
							//计算入库总数量
							String sqlin = "select incount from vmaterial_com where mnum = '"+mnum+"'and "+filter3;
							DbUtil.execute(sqlin,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									int incount = 0;
									while(rs.next()){
										incount += rs.getInt("incount");
									}
									try {
										json.put("incount",incount);
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
							
							//计算出库量
							String sqlout = "select outcount from v_matout_mnum where mnum = '"+mnum +"'and "+filter1;
							DbUtil.execute(sqlout,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									int outcount = 0;
									while(rs.next()){
										outcount += rs.getInt("outcount");
									}
									try {
										json.put("outcount",outcount);
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
							//计算报废数量
							String sqldel = "select delcount from v_del_com where mnum = '"+mnum +"'and "+filter2;
							DbUtil.execute(sqldel,new IResultSetProcessor(){
								public void process(ResultSet rs)throws SQLException {
									int delcount = 0;
									while(rs.next()){
										delcount+=rs.getInt("delcount");
									}
									try {
										json.put("delcount",delcount);
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								
							});
							
							try {
								json.put("nowcount",json.getInt("incount")-json.getInt("outcount")-json.getInt("delcount"));
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							jsonarray.put(json);
						}
					}
				});
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				obj.put("filedata",jsonarray);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return obj;
	}


	@Override
	public void UsageExcel(HashMap hashmap, OutputStream os) {
		final String filter = (String) hashmap.get("filter");
		final String filter1 = (String) hashmap.get("filter1");
		final String filter2 = (String) hashmap.get("filter2");
		final String filter3 = (String) hashmap.get("filter3");
		System.out.println(filter);
		System.out.println(filter1);
		System.out.println(filter2);
		System.out.println(filter3);
		final ArrayList pcArray=new ArrayList();
		
		String sql1 = "select distinct mnum from vmaterial_com where "+filter;
		
		try {
			DbUtil.execute(sql1,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					int j = 0;
					while(rs.next()){
						final HashMap paHashMap=new HashMap();
						final String mnum = rs.getString("mnum");
						String sqlbasic = "select classname,name,spec,company,guaranteedate,modelnum,buycycle from vmaterial_com where mnum = '"+mnum+"'";
						DbUtil.execute(sqlbasic,new IResultSetProcessor(){
							public void process(ResultSet rs)throws SQLException {
								while(rs.next()){
									paHashMap.put("mnum",mnum);
									paHashMap.put("classname",rs.getString("classname"));
									paHashMap.put("guaranteedate",rs.getString("guaranteedate"));
									paHashMap.put("name",rs.getString("name"));
									paHashMap.put("spec",rs.getString("spec"));
									paHashMap.put("company",rs.getString("company"));
									paHashMap.put("modelnum",rs.getString("modelnum"));
									paHashMap.put("buycycle",rs.getString("buycycle"));
								}
							}
						});
						int nowcount = 0;
						//计算入库总数量
						String sqlin = "select incount from vmaterial_com where mnum = '"+mnum+"'and "+filter3;
						DbUtil.execute(sqlin,new IResultSetProcessor(){
							public void process(ResultSet rs)throws SQLException {
								int incount = 0;
								while(rs.next()){
									incount += rs.getInt("incount");
								}
								paHashMap.put("incount",incount);
							}
						});
						
						//计算出库量
						String sqlout = "select outcount from v_matout_mnum where mnum = '"+mnum +"'and "+filter1;
						DbUtil.execute(sqlout,new IResultSetProcessor(){
							public void process(ResultSet rs)throws SQLException {
								int outcount = 0;
								while(rs.next()){
									outcount += rs.getInt("outcount");
								}
								paHashMap.put("outcount",outcount);
							}
						});
						//计算报废数量
						String sqldel = "select delcount from v_del_com where mnum = '"+mnum +"'and "+filter2;
						DbUtil.execute(sqldel,new IResultSetProcessor(){
							public void process(ResultSet rs)throws SQLException {
								int delcount = 0;
								while(rs.next()){
									delcount+=rs.getInt("delcount");
								}
								paHashMap.put("delcount",delcount);
							}
						});
						pcArray.add(j,paHashMap);
						System.out.println(pcArray.get(j));
						j++;
					}
				}
			});
			
			
			WritableSheet sheet = null;
			//创建工作薄��������
			WritableWorkbook workbook = null;
			try {
				sheet = null;
				Calendar c = Calendar.getInstance();
				final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
				String CurDate = curDate.toString();
				workbook = null;
				
				workbook = Workbook.createWorkbook(os);
				sheet = workbook.createSheet("设备仪表基本信息表", 0);
				//内容存放格式设定
				WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
				WritableCellFormat seat = new WritableCellFormat(place);
				
				seat.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

				sheet.mergeCells(0, 0, 11, 0);
				Label fname = new Label(0, 0, "材料用量统计信息表",seat);

				sheet.addCell(fname);
				sheet.mergeCells(0, 1, 11, 1);
				Label ftime = new Label(0, 1, "导出日期："+CurDate);
				sheet.addCell(ftime);
				
				
				//创建要显示的具体内容的题头
				Label Name = new Label(0, 2, "物资编码");
				sheet.addCell(Name);
				Label Pclass = new Label(1, 2, "类别");
				sheet.addCell(Pclass);
				Label Bclass = new Label(2, 2, "保质期");
				sheet.addCell(Bclass);
				Label Newstatus = new Label(3, 2, "名称");
				sheet.addCell(Newstatus);
				Label Hq = new Label(4, 2, "规格");
				sheet.addCell(Hq);
				Label In_count = new Label(5, 2, "厂商");
				sheet.addCell(In_count);
				Label Nct = new Label(6, 2, "型号");
				sheet.addCell(Nct);
				Label Uct = new Label(7, 2, "采购周期");
				sheet.addCell(Uct);
				Label Uct1 = new Label(8, 2, "入库数量");
				sheet.addCell(Uct1);
				Label Uct2 = new Label(9, 2, "出库数量");
				sheet.addCell(Uct2);
				Label Uct3 = new Label(10, 2, "报废数量");
				sheet.addCell(Uct3);
			} catch (RowsExceededException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			int nRow=3;
			
			for(int i=0;i<pcArray.size();i++){
				String mnum="";
				String classname="";
				String name="";
				String modelnum="";
				String spec="";
				String company="";
				String measure="";
				String guaranteedate="";
				String minstore="";
				String singlepur="";
				String remark="";
				String maxstore="";
				if(((HashMap)pcArray.get(i)).get("mnum")!=null){
					mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
				}
				if(((HashMap)pcArray.get(i)).get("classname")!=null){
					classname=((HashMap)pcArray.get(i)).get("classname").toString();
				}
				if(((HashMap)pcArray.get(i)).get("guaranteedate")!=null){
					name=((HashMap)pcArray.get(i)).get("guaranteedate").toString();
				}
				if(((HashMap)pcArray.get(i)).get("name")!=null){
					modelnum=((HashMap)pcArray.get(i)).get("name").toString();
				}
				if(((HashMap)pcArray.get(i)).get("spec")!=null){
					spec=((HashMap)pcArray.get(i)).get("spec").toString();
				}
				if(((HashMap)pcArray.get(i)).get("company")!=null){
					maxstore=((HashMap)pcArray.get(i)).get("company").toString();
				}
				if(((HashMap)pcArray.get(i)).get("modelnum")!=null){
					company=((HashMap)pcArray.get(i)).get("modelnum").toString();
				}
				if(((HashMap)pcArray.get(i)).get("buycycle")!=null){
					measure=((HashMap)pcArray.get(i)).get("buycycle").toString();
				}
				if(((HashMap)pcArray.get(i)).get("incount")!=null){
					guaranteedate=((HashMap)pcArray.get(i)).get("incount").toString();
				}
				if(((HashMap)pcArray.get(i)).get("outcount")!=null){
					minstore=((HashMap)pcArray.get(i)).get("outcount").toString();
				}
				if(((HashMap)pcArray.get(i)).get("delcount")!=null){
					singlepur=((HashMap)pcArray.get(i)).get("delcount").toString();
				}
				
					try {
						sheet.addCell(new Label(0,nRow,mnum));
						sheet.addCell(new Label(1,nRow,classname));
						sheet.addCell(new Label(2,nRow,name));
						sheet.addCell(new Label(3,nRow,modelnum));
						sheet.addCell(new Label(4,nRow,spec));
						sheet.addCell(new Label(5,nRow,maxstore));
						sheet.addCell(new Label(6,nRow,company));
						sheet.addCell(new Label(7,nRow,measure));
						sheet.addCell(new Label(8,nRow,guaranteedate));
						sheet.addCell(new Label(9,nRow,minstore));
						sheet.addCell(new Label(10,nRow,singlepur));
						sheet.addCell(new Label(11,nRow,""));
					} catch (RowsExceededException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				nRow++;
	    	}
			try {
				workbook.write();
				workbook.close();
				os.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
