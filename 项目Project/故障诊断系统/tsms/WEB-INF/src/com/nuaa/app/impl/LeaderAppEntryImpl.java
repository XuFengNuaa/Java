package com.nuaa.app.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.nuaa.app.LeaderAppEntry;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;

public class LeaderAppEntryImpl implements LeaderAppEntry {

	public void createExcelCT(OutputStream os) throws WriteException, IOException {
		// TODO 自动生成方法存根
		String sql="";
    	sql="select * from t_m_etr_type where trim(status) = '批准'"; 
    	System.out.println(sql);
    	final ArrayList pcArray=new ArrayList();
    	try{
	    	DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {	
					try{
						int j=0;
						while(rs.next()){
							HashMap paHashMap=new HashMap();
							paHashMap.put("name", rs.getString("name"));
							paHashMap.put("classname", rs.getString("classname"));
							paHashMap.put("indi_num", rs.getString("indi_num"));
							paHashMap.put("location", rs.getString("location"));
							paHashMap.put("hq", rs.getString("hq"));
							paHashMap.put("newstatus", rs.getString("newstatus"));
							paHashMap.put("in_time", rs.getDate("in_time"));
							paHashMap.put("status", rs.getString("status"));
							pcArray.add(j,paHashMap);
							System.out.println(pcArray.get(j));
							j++;	
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}				
			});
//	    	//读取导出日期
	    	Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String CurDate = curDate.toString();
            //创建工作薄��������
	    	WritableWorkbook workbook = Workbook.createWorkbook(os);
	    	//创建工作表�����µ�
			WritableSheet sheet = workbook.createSheet("量具入库审批", 0);
			//内容存放格式设定
			WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
			WritableCellFormat seat = new WritableCellFormat(place);
			//水平居中
			seat.setAlignment(jxl.format.Alignment.CENTRE);
			//垂直居中
			seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        //创建标题，导出时间及查询条件
			sheet.mergeCells(0, 0, 7, 0);
			Label fname = new Label(0, 0, "量具入库审批",seat);
			sheet.addCell(fname);
			sheet.mergeCells(0, 1, 7, 1);
			Label ftime = new Label(0, 1, "导出日期："+CurDate);
			sheet.addCell(ftime);
			sheet.mergeCells(0, 2, 7, 2);
			Label Search = new Label(0, 2, "查询条件：已批准项");
			sheet.addCell(Search);
//			创建要显示的具体内容的题头
			Label Name = new Label(0, 3, "量具名称");
			sheet.addCell(Name);
			Label Classname = new Label(1, 3, "量具类别");
			sheet.addCell(Classname);
			Label Indi_num = new Label(2, 3, "个体编号");
			sheet.addCell(Indi_num);
			Label Location = new Label(3, 3, "存放位置");
			sheet.addCell(Location);
			Label Newstatus = new Label(4, 3, "新旧状态");
			sheet.addCell(Newstatus);
			Label Hq = new Label(5, 3, "高质与否");
			sheet.addCell(Hq);
			Label In_time = new Label(6, 3, "申请日期");
			sheet.addCell(In_time);
			Label Status = new Label(7, 3, "审批状态");
			sheet.addCell(Status);
			int nRow=4;
	    	for(int i=0;i<pcArray.size();i++){
				String name="";
				String classname="";
				String indi_num="";
				String location="";
				String hq="";
				String newstatus="";
				String in_time="";
				String status="";
				if(((HashMap)pcArray.get(i)).get("name")!=null){
					name=((HashMap)pcArray.get(i)).get("name").toString();
				}
				if(((HashMap)pcArray.get(i)).get("classname")!=null){
					classname=((HashMap)pcArray.get(i)).get("classname").toString();
				}
				if(((HashMap)pcArray.get(i)).get("indi_num")!=null){
					indi_num=((HashMap)pcArray.get(i)).get("indi_num").toString();
				}
				if(((HashMap)pcArray.get(i)).get("location")!=null){
					location=((HashMap)pcArray.get(i)).get("location").toString();
				}
				if(((HashMap)pcArray.get(i)).get("hq")!=null){
					hq=((HashMap)pcArray.get(i)).get("hq").toString();
				}
				if(((HashMap)pcArray.get(i)).get("newstatus")!=null){
					newstatus=((HashMap)pcArray.get(i)).get("newstatus").toString();
				}
				if(((HashMap)pcArray.get(i)).get("in_time")!=null){
					in_time=((HashMap)pcArray.get(i)).get("in_time").toString();
				}
				if(((HashMap)pcArray.get(i)).get("status")!=null){
					status=((HashMap)pcArray.get(i)).get("status").toString();
				}
				sheet.addCell(new Label(0,nRow,name));
				sheet.addCell(new Label(1,nRow,classname));
				sheet.addCell(new Label(2,nRow,indi_num));
				sheet.addCell(new Label(3,nRow,location));
				sheet.addCell(new Label(4,nRow,hq));
				sheet.addCell(new Label(5,nRow,newstatus));
				sheet.addCell(new Label(6,nRow,in_time));
				sheet.addCell(new Label(7,nRow,status));
				sheet.addCell(new Label(8,nRow,""));
				nRow++;
	    	}
	    	workbook.write();
			workbook.close();
			os.close();
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

	public void createExcelMt(OutputStream os) throws WriteException, IOException {
		// TODO 自动生成方法存根
		String sql="";
    	sql="select * from t_mt_etr_type where trim(status) = '批准'"; 
    	System.out.println(sql);
    	final ArrayList pcArray=new ArrayList();
    	try{
	    	DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {	
					try{
						int j=0;
						while(rs.next()){
							HashMap paHashMap=new HashMap();
							paHashMap.put("name", rs.getString("name"));
							paHashMap.put("classname", rs.getString("classname"));
							paHashMap.put("indi_num", rs.getString("indi_num"));
							paHashMap.put("location", rs.getString("location"));
							paHashMap.put("hq", rs.getString("hq"));
							paHashMap.put("newstatus", rs.getString("newstatus"));
							paHashMap.put("in_time", rs.getDate("in_time"));
							paHashMap.put("status", rs.getString("status"));
							pcArray.add(j,paHashMap);
							System.out.println(pcArray.get(j));
							j++;	
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}				
			});
//	    	//读取导出日期
	    	Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String CurDate = curDate.toString();
            //创建工作薄��������
	    	WritableWorkbook workbook = Workbook.createWorkbook(os);
	    	//创建工作表�����µ�
			WritableSheet sheet = workbook.createSheet("测量类工具入库审批", 0);
			//内容存放格式设定
			WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
			WritableCellFormat seat = new WritableCellFormat(place);
			//水平居中
			seat.setAlignment(jxl.format.Alignment.CENTRE);
			//垂直居中
			seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        //创建标题，导出时间及查询条件
			sheet.mergeCells(0, 0, 7, 0);
			Label fname = new Label(0, 0, "测量类工具入库审批",seat);
			sheet.addCell(fname);
			sheet.mergeCells(0, 1, 7, 1);
			Label ftime = new Label(0, 1, "导出日期："+CurDate);
			sheet.addCell(ftime);
			sheet.mergeCells(0, 2, 7, 2);
			Label Search = new Label(0, 2, "查询条件：已批准项");
			sheet.addCell(Search);
//			创建要显示的具体内容的题头
			Label Name = new Label(0, 3, "工具名称");
			sheet.addCell(Name);
			Label Classname = new Label(1, 3, "工具类别");
			sheet.addCell(Classname);
			Label Indi_num = new Label(2, 3, "个体编号");
			sheet.addCell(Indi_num);
			Label Location = new Label(3, 3, "存放位置");
			sheet.addCell(Location);
			Label Newstatus = new Label(4, 3, "新旧状态");
			sheet.addCell(Newstatus);
			Label Hq = new Label(5, 3, "高质与否");
			sheet.addCell(Hq);
			Label In_time = new Label(6, 3, "申请日期");
			sheet.addCell(In_time);
			Label Status = new Label(7, 3, "审批状态");
			sheet.addCell(Status);
			int nRow=4;
	    	for(int i=0;i<pcArray.size();i++){
				String name="";
				String classname="";
				String indi_num="";
				String location="";
				String hq="";
				String newstatus="";
				String in_time="";
				String status="";
				if(((HashMap)pcArray.get(i)).get("name")!=null){
					name=((HashMap)pcArray.get(i)).get("name").toString();
				}
				if(((HashMap)pcArray.get(i)).get("classname")!=null){
					classname=((HashMap)pcArray.get(i)).get("classname").toString();
				}
				if(((HashMap)pcArray.get(i)).get("indi_num")!=null){
					indi_num=((HashMap)pcArray.get(i)).get("indi_num").toString();
				}
				if(((HashMap)pcArray.get(i)).get("location")!=null){
					location=((HashMap)pcArray.get(i)).get("location").toString();
				}
				if(((HashMap)pcArray.get(i)).get("hq")!=null){
					hq=((HashMap)pcArray.get(i)).get("hq").toString();
				}
				if(((HashMap)pcArray.get(i)).get("newstatus")!=null){
					newstatus=((HashMap)pcArray.get(i)).get("newstatus").toString();
				}
				if(((HashMap)pcArray.get(i)).get("in_time")!=null){
					in_time=((HashMap)pcArray.get(i)).get("in_time").toString();
				}
				if(((HashMap)pcArray.get(i)).get("status")!=null){
					status=((HashMap)pcArray.get(i)).get("status").toString();
				}
				sheet.addCell(new Label(0,nRow,name));
				sheet.addCell(new Label(1,nRow,classname));
				sheet.addCell(new Label(2,nRow,indi_num));
				sheet.addCell(new Label(3,nRow,location));
				sheet.addCell(new Label(4,nRow,hq));
				sheet.addCell(new Label(5,nRow,newstatus));
				sheet.addCell(new Label(6,nRow,in_time));
				sheet.addCell(new Label(7,nRow,status));
				sheet.addCell(new Label(8,nRow,""));
				nRow++;
	    	}
	    	workbook.write();
			workbook.close();
			os.close();
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

	public void createExcelNmg(OutputStream os) throws WriteException, IOException {
//		 TODO 自动生成方法存根
		String sql="";
    	sql="select * from t_ct_trecord where trim(status) = '批准'"; 
    	System.out.println(sql);
    	final ArrayList pcArray=new ArrayList();
    	try{
	    	DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {	
					try{
						int j=0;
						while(rs.next()){
							HashMap paHashMap=new HashMap();
							paHashMap.put("name", rs.getString("name"));
							paHashMap.put("pclass", rs.getString("pclass"));
							paHashMap.put("bclass", rs.getString("bclass"));
							paHashMap.put("in_count", rs.getString("in_count"));
							paHashMap.put("nct_count_in", rs.getString("nct_count_in"));
							paHashMap.put("uct_count_in", rs.getString("uct_count_in"));
							paHashMap.put("location", rs.getString("location"));
							paHashMap.put("hq", rs.getString("hq"));
							paHashMap.put("newstatus", rs.getString("newstatus"));
							paHashMap.put("in_time", rs.getDate("in_time"));
							paHashMap.put("company", rs.getString("company"));
							paHashMap.put("order_num", rs.getString("order_num"));
							paHashMap.put("status", rs.getString("status"));
							pcArray.add(j,paHashMap);
							System.out.println(pcArray.get(j));
							j++;	
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}				
			});
//	    	//读取导出日期
	    	Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String CurDate = curDate.toString();
            //创建工作薄��������
	    	WritableWorkbook workbook = Workbook.createWorkbook(os);
	    	//创建工作表�����µ�
			WritableSheet sheet = workbook.createSheet("非测量类工具入库审批", 0);
			//内容存放格式设定
			WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
			WritableCellFormat seat = new WritableCellFormat(place);
			//水平居中
			seat.setAlignment(jxl.format.Alignment.CENTRE);
			//垂直居中
			seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        //创建标题，导出时间及查询条件
			sheet.mergeCells(0, 0, 12, 0);
			Label fname = new Label(0, 0, "非测量类工具入库审批",seat);
			sheet.addCell(fname);
			sheet.mergeCells(0, 1, 12, 1);
			Label ftime = new Label(0, 1, "导出日期："+CurDate);
			sheet.addCell(ftime);
			sheet.mergeCells(0, 2, 12, 2);
			Label Search = new Label(0, 2, "查询条件：已批准项");
			sheet.addCell(Search);
//			创建要显示的具体内容的题头
			Label Name = new Label(0, 3, "工具名称");
			sheet.addCell(Name);
			Label Pclass = new Label(1, 3, "工具大类");
			sheet.addCell(Pclass);
			Label Bclass = new Label(2, 3, "工具小类");
			sheet.addCell(Bclass);
			Label Newstatus = new Label(3, 3, "新旧状态");
			sheet.addCell(Newstatus);
			Label Hq = new Label(4, 3, "高质与否");
			sheet.addCell(Hq);
			Label In_count = new Label(5, 3, "入库数量");
			sheet.addCell(In_count);
			Label Nct = new Label(6, 3, "新工具在库量");
			sheet.addCell(Nct);
			Label Uct = new Label(7, 3, "旧工具在库量");
			sheet.addCell(Uct);
			Label Location = new Label(8, 3, "存放位置");
			sheet.addCell(Location);
			Label Com = new Label(9, 3, "厂商");
			sheet.addCell(Com);
			Label Order = new Label(10, 3, "订货号");
			sheet.addCell(Order);
			Label In_time = new Label(11, 3, "申请日期");
			sheet.addCell(In_time);
			Label Status = new Label(12, 3, "审批状态");
			sheet.addCell(Status);
			int nRow=4;
	    	for(int i=0;i<pcArray.size();i++){
				String name="";
				String pclass="";
				String bclass="";
				String in_count="";
				String nct_count_in="";
				String uct_count_in="";
				String location="";
				String hq="";
				String newstatus="";
				String company="";
				String order_num="";
				String in_time="";
				String status="";
				if(((HashMap)pcArray.get(i)).get("name")!=null){
					name=((HashMap)pcArray.get(i)).get("name").toString();
				}
				if(((HashMap)pcArray.get(i)).get("pclass")!=null){
					pclass=((HashMap)pcArray.get(i)).get("pclass").toString();
				}
				if(((HashMap)pcArray.get(i)).get("bclass")!=null){
					bclass=((HashMap)pcArray.get(i)).get("bclass").toString();
				}
				if(((HashMap)pcArray.get(i)).get("in_count")!=null){
					in_count=((HashMap)pcArray.get(i)).get("in_count").toString();
				}
				if(((HashMap)pcArray.get(i)).get("nct_count_in")!=null){
					nct_count_in=((HashMap)pcArray.get(i)).get("nct_count_in").toString();
				}
				if(((HashMap)pcArray.get(i)).get("uct_count_in")!=null){
					uct_count_in=((HashMap)pcArray.get(i)).get("uct_count_in").toString();
				}
				if(((HashMap)pcArray.get(i)).get("location")!=null){
					location=((HashMap)pcArray.get(i)).get("location").toString();
				}
				if(((HashMap)pcArray.get(i)).get("hq")!=null){
					hq=((HashMap)pcArray.get(i)).get("hq").toString();
				}
				if(((HashMap)pcArray.get(i)).get("newstatus")!=null){
					newstatus=((HashMap)pcArray.get(i)).get("newstatus").toString();
				}
				if(((HashMap)pcArray.get(i)).get("in_time")!=null){
					in_time=((HashMap)pcArray.get(i)).get("in_time").toString();
				}
				if(((HashMap)pcArray.get(i)).get("company")!=null){
					company=((HashMap)pcArray.get(i)).get("company").toString();
				}
				if(((HashMap)pcArray.get(i)).get("order_num")!=null){
					order_num=((HashMap)pcArray.get(i)).get("order_num").toString();
				}
				if(((HashMap)pcArray.get(i)).get("status")!=null){
					status=((HashMap)pcArray.get(i)).get("status").toString();
				}
				sheet.addCell(new Label(0,nRow,name));
				sheet.addCell(new Label(1,nRow,pclass));
				sheet.addCell(new Label(2,nRow,bclass));
				sheet.addCell(new Label(3,nRow,newstatus));
				sheet.addCell(new Label(4,nRow,hq));
				sheet.addCell(new Label(5,nRow,in_count));
				sheet.addCell(new Label(6,nRow,nct_count_in));
				sheet.addCell(new Label(7,nRow,uct_count_in));
				sheet.addCell(new Label(8,nRow,location));
				sheet.addCell(new Label(9,nRow,company));
				sheet.addCell(new Label(10,nRow,order_num));
				sheet.addCell(new Label(11,nRow,in_time));
				sheet.addCell(new Label(12,nRow,status));
				sheet.addCell(new Label(13,nRow,""));
				nRow++;
	    	}
	    	workbook.write();
			workbook.close();
			os.close();
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
	}

	public void createExcelC(OutputStream os) throws WriteException, IOException {
		// TODO 自动生成方法存根
		String sql="";
    	sql="select * from t_c_trecord where trim(status) = '批准'"; 
    	System.out.println(sql);
    	final ArrayList pcArray=new ArrayList();
    	try{
	    	DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {	
					try{
						int j=0;
						while(rs.next()){
							HashMap paHashMap=new HashMap();
							paHashMap.put("name", rs.getString("name"));
							paHashMap.put("pclass", rs.getString("pclass"));
							paHashMap.put("bclass", rs.getString("bclass"));
							paHashMap.put("in_count", rs.getString("in_count"));
							paHashMap.put("nc_count_in", rs.getString("nc_count_in"));
							paHashMap.put("uc_count_in", rs.getString("uc_count_in"));
							paHashMap.put("location", rs.getString("location"));
							paHashMap.put("hq", rs.getString("hq"));
							paHashMap.put("newstatus", rs.getString("newstatus"));
							paHashMap.put("in_time", rs.getDate("in_time"));
							paHashMap.put("company", rs.getString("company"));
							paHashMap.put("order_num", rs.getString("order_num"));
							paHashMap.put("status", rs.getString("status"));
							pcArray.add(j,paHashMap);
							System.out.println(pcArray.get(j));
							j++;	
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}				
			});
//	    	//读取导出日期
	    	Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String CurDate = curDate.toString();
            //创建工作薄��������
	    	WritableWorkbook workbook = Workbook.createWorkbook(os);
	    	//创建工作表�����µ�
			WritableSheet sheet = workbook.createSheet("刀具入库审批", 0);
			//内容存放格式设定
			WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
			WritableCellFormat seat = new WritableCellFormat(place);
			//水平居中
			seat.setAlignment(jxl.format.Alignment.CENTRE);
			//垂直居中
			seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        //创建标题，导出时间及查询条件
			sheet.mergeCells(0, 0, 12, 0);
			Label fname = new Label(0, 0, "刀具入库审批",seat);
			sheet.addCell(fname);
			sheet.mergeCells(0, 1, 12, 1);
			Label ftime = new Label(0, 1, "导出日期："+CurDate);
			sheet.addCell(ftime);
			sheet.mergeCells(0, 2, 12, 2);
			Label Search = new Label(0, 2, "查询条件：已批准项");
			sheet.addCell(Search);
//			创建要显示的具体内容的题头
			Label Name = new Label(0, 3, "刀具名称");
			sheet.addCell(Name);
			Label Pclass = new Label(1, 3, "刀具大类");
			sheet.addCell(Pclass);
			Label Bclass = new Label(2, 3, "刀具小类");
			sheet.addCell(Bclass);
			Label Newstatus = new Label(3, 3, "新旧状态");
			sheet.addCell(Newstatus);
			Label Hq = new Label(4, 3, "高质与否");
			sheet.addCell(Hq);
			Label In_count = new Label(5, 3, "入库数量");
			sheet.addCell(In_count);
			Label Nct = new Label(6, 3, "新刀具在库量");
			sheet.addCell(Nct);
			Label Uct = new Label(7, 3, "旧刀具在库量");
			sheet.addCell(Uct);
			Label Location = new Label(8, 3, "存放位置");
			sheet.addCell(Location);
			Label Com = new Label(9, 3, "厂商");
			sheet.addCell(Com);
			Label Order = new Label(10, 3, "订货号");
			sheet.addCell(Order);
			Label In_time = new Label(11, 3, "申请日期");
			sheet.addCell(In_time);
			Label Status = new Label(12, 3, "审批状态");
			sheet.addCell(Status);
			int nRow=4;
	    	for(int i=0;i<pcArray.size();i++){
				String name="";
				String pclass="";
				String bclass="";
				String in_count="";
				String nc_count_in="";
				String uc_count_in="";
				String location="";
				String hq="";
				String newstatus="";
				String company="";
				String order_num="";
				String in_time="";
				String status="";
				if(((HashMap)pcArray.get(i)).get("name")!=null){
					name=((HashMap)pcArray.get(i)).get("name").toString();
				}
				if(((HashMap)pcArray.get(i)).get("pclass")!=null){
					pclass=((HashMap)pcArray.get(i)).get("pclass").toString();
				}
				if(((HashMap)pcArray.get(i)).get("bclass")!=null){
					bclass=((HashMap)pcArray.get(i)).get("bclass").toString();
				}
				if(((HashMap)pcArray.get(i)).get("in_count")!=null){
					in_count=((HashMap)pcArray.get(i)).get("in_count").toString();
				}
				if(((HashMap)pcArray.get(i)).get("nc_count_in")!=null){
					nc_count_in=((HashMap)pcArray.get(i)).get("nc_count_in").toString();
				}
				if(((HashMap)pcArray.get(i)).get("uc_count_in")!=null){
					uc_count_in=((HashMap)pcArray.get(i)).get("uc_count_in").toString();
				}
				if(((HashMap)pcArray.get(i)).get("location")!=null){
					location=((HashMap)pcArray.get(i)).get("location").toString();
				}
				if(((HashMap)pcArray.get(i)).get("hq")!=null){
					hq=((HashMap)pcArray.get(i)).get("hq").toString();
				}
				if(((HashMap)pcArray.get(i)).get("newstatus")!=null){
					newstatus=((HashMap)pcArray.get(i)).get("newstatus").toString();
				}
				if(((HashMap)pcArray.get(i)).get("in_time")!=null){
					in_time=((HashMap)pcArray.get(i)).get("in_time").toString();
				}
				if(((HashMap)pcArray.get(i)).get("company")!=null){
					company=((HashMap)pcArray.get(i)).get("company").toString();
				}
				if(((HashMap)pcArray.get(i)).get("order_num")!=null){
					order_num=((HashMap)pcArray.get(i)).get("order_num").toString();
				}
				if(((HashMap)pcArray.get(i)).get("status")!=null){
					status=((HashMap)pcArray.get(i)).get("status").toString();
				}
				sheet.addCell(new Label(0,nRow,name));
				sheet.addCell(new Label(1,nRow,pclass));
				sheet.addCell(new Label(2,nRow,bclass));
				sheet.addCell(new Label(3,nRow,newstatus));
				sheet.addCell(new Label(4,nRow,hq));
				sheet.addCell(new Label(5,nRow,in_count));
				sheet.addCell(new Label(6,nRow,nc_count_in));
				sheet.addCell(new Label(7,nRow,uc_count_in));
				sheet.addCell(new Label(8,nRow,location));
				sheet.addCell(new Label(9,nRow,company));
				sheet.addCell(new Label(10,nRow,order_num));
				sheet.addCell(new Label(11,nRow,in_time));
				sheet.addCell(new Label(12,nRow,status));
				sheet.addCell(new Label(13,nRow,""));
				nRow++;
	    	}
	    	workbook.write();
			workbook.close();
			os.close();
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
			
	}
	

}
