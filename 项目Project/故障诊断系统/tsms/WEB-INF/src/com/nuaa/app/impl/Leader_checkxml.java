package com.nuaa.app.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.nuaa.app.Leader_checkexcel;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class Leader_checkxml implements Leader_checkexcel{

	
	//未查看的入库刀具导出方法
	public void createExcelC(String leader_num,String leadername,OutputStream os) throws WriteException, IOException
    {
		String sql="";
		sql="select distinct entry_num,mnum,pclass,bclass,name,newstatus,in_count,in_time,company from v_cut2entrycheck where trim(status)='申请'";  
		Logger.debug(sql);
		final ArrayList pcArray=new ArrayList();
    	try{
	    	DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {	
					try{
						int j=0;
						while(rs.next()){
							HashMap paHashMap=new HashMap();
							paHashMap.put("entry_num", rs.getString("entry_num"));
							paHashMap.put("mnum", rs.getString("mnum"));
							paHashMap.put("pclass", rs.getString("pclass"));
							paHashMap.put("bclass", rs.getString("bclass"));
							paHashMap.put("name", rs.getString("name"));
							paHashMap.put("in_count", rs.getString("in_count"));
							paHashMap.put("newstatus", rs.getString("newstatus"));
							paHashMap.put("in_time", rs.getDate("in_time"));
							paHashMap.put("company", rs.getString("company"));
							pcArray.add(j,paHashMap);
							j++;
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}				
			});	
//	    	//读取导出时间
	    	Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String CurDate = curDate.toString();
            //创建工作薄��������
	    	WritableWorkbook workbook = Workbook.createWorkbook(os);
	    	//创建工作表�����µ�
			WritableSheet sheet = workbook.createSheet("刀具入库审批", 0);
//			//标题存放格式设定
			WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
			WritableCellFormat seat = new WritableCellFormat(place);
			//水平居中
			seat.setAlignment(jxl.format.Alignment.CENTRE);
			//垂直居中
			seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//			内容存放格式设定
			WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
			WritableCellFormat center = new WritableCellFormat(Place);
			WritableCellFormat wordsize = new WritableCellFormat(Place);
			//水平居中
			center.setAlignment(jxl.format.Alignment.CENTRE);
	        //创建标题，导出时间及查询条件
			sheet.mergeCells(0, 0, 8, 0);
			Label fname = new Label(0, 0, "标题：刀具入库审批",seat);
			sheet.addCell(fname);
			sheet.mergeCells(0, 1, 8, 1);
			Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
			sheet.addCell(ftime);
			sheet.mergeCells(0, 2, 8, 2);
			Label Search = new Label(0, 2, "查询条件：未审批项",wordsize);
			sheet.addCell(Search);
			sheet.mergeCells(0, 3, 8, 3);
			Label People = new Label(0, 3, "审批人："+leadername,wordsize);
			sheet.addCell(People);
			
//			创建要显示的具体内容的题头
			Label order = new Label(0, 5, "序号",center);
			sheet.addCell(order);
			
			Label Mnum = new Label(1, 5, "物资编码",center);
			sheet.addCell(Mnum);
			
			Label Pclass = new Label(2, 5, "大类",center);
			sheet.addCell(Pclass);
			
			Label Bclass = new Label(3, 5, "小类",center);
			sheet.addCell(Bclass);
		
			Label Name = new Label(4, 5, "名称",center);
			sheet.addCell(Name);
			
			Label Newstatus = new Label(5, 5, "新旧状态",center);
			sheet.addCell(Newstatus);
			
			Label Company = new Label(6, 5, "厂商",center);
			sheet.addCell(Company);
			
			Label In_count = new Label(7, 5, "入库数量",center);
			sheet.addCell(In_count);
			
			Label In_time = new Label(8, 5, "入库时间",center);
			sheet.addCell(In_time);
			
			int nRow=6;
			int k = 1;
	    	for(int i=0;i<pcArray.size();i++){
				String mnum="";
				String pclass="";
				String bclass="";
				String name="";
				String newstatus="";
				String in_count="";
				String in_time="";
				String company = "";
				
				if(((HashMap)pcArray.get(i)).get("mnum")!=null){
					mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
				}
				if(((HashMap)pcArray.get(i)).get("pclass")!=null){
					pclass=((HashMap)pcArray.get(i)).get("pclass").toString();
				}
				if(((HashMap)pcArray.get(i)).get("bclass")!=null){
					bclass=((HashMap)pcArray.get(i)).get("bclass").toString();
				}
				if(((HashMap)pcArray.get(i)).get("name")!=null){
					name=((HashMap)pcArray.get(i)).get("name").toString();
				}
				if(((HashMap)pcArray.get(i)).get("newstatus")!=null){
					newstatus=((HashMap)pcArray.get(i)).get("newstatus").toString();
				}
//				数量为空时置零并将其转化为整形
				if(((HashMap)pcArray.get(i)).get("in_count")!=null){
					in_count=((HashMap)pcArray.get(i)).get("in_count").toString();
				}else{
					in_count = "0";
				}
				int I_count=Integer.parseInt(in_count);
				if(((HashMap)pcArray.get(i)).get("in_time")!=null){
					in_time=((HashMap)pcArray.get(i)).get("in_time").toString();
				}
				if(((HashMap)pcArray.get(i)).get("company")!=null){
					company=((HashMap)pcArray.get(i)).get("company").toString();
				}
				
				sheet.addCell(new Number(0,nRow,k,center));
				sheet.addCell(new Label(1,nRow,mnum,center));
				sheet.addCell(new Label(2,nRow,pclass,center));
				sheet.addCell(new Label(3,nRow,bclass,center));
				sheet.addCell(new Label(4,nRow,name,center));
				sheet.addCell(new Label(5,nRow,newstatus,center));
				sheet.addCell(new Label(6,nRow,company,center));
				sheet.addCell(new Number(7,nRow,I_count,center));
				sheet.addCell(new Label(8,nRow,in_time,center));
				sheet.addCell(new Label(9,nRow,""));
				nRow++;
				k++;
	    	}
	    	
//	    	设置列宽  
	    	sheet.setColumnView(0, 5);
	    	sheet.setColumnView(1, 20);
	    	sheet.setColumnView(2, 12); 
	    	sheet.setColumnView(3, 16); 
	    	sheet.setColumnView(4, 22); 
	    	sheet.setColumnView(5, 10); 
	    	sheet.setColumnView(6, 15); 
	    	sheet.setColumnView(7, 10);
	    	sheet.setColumnView(8, 15);
	    	workbook.write();
			workbook.close();
			os.close();
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
    }
	
	
//	未查看的入库非测量工具导出方法
	public void createExcelCT(String leader_num,String leadername,OutputStream os) throws WriteException, IOException
    {
		String sql="";
		sql="select distinct entry_num,mnum,pclass,bclass,name,newstatus,in_count,in_time,company from v_ct2entrycheck where trim(status)='申请'";   
		Logger.debug(sql);
    	final ArrayList pcArray=new ArrayList();
    	try{
	    	DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {	
					try{
						int j=0;
						while(rs.next()){
							HashMap paHashMap=new HashMap();
							paHashMap.put("entry_num", rs.getString("entry_num"));
							paHashMap.put("mnum", rs.getString("mnum"));
							paHashMap.put("pclass", rs.getString("pclass"));
							paHashMap.put("bclass", rs.getString("bclass"));
							paHashMap.put("name", rs.getString("name"));
							paHashMap.put("in_count", rs.getString("in_count"));
							paHashMap.put("newstatus", rs.getString("newstatus"));
							paHashMap.put("in_time", rs.getDate("in_time"));
							paHashMap.put("company", rs.getString("company"));
							pcArray.add(j,paHashMap);
							j++;
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}				
			});	
//	    	//读取导出时间
	    	Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String CurDate = curDate.toString();
            //创建工作薄��������
	    	WritableWorkbook workbook = Workbook.createWorkbook(os);
	    	//创建工作表�����µ�
			WritableSheet sheet = workbook.createSheet("非测量工具入库审批", 0);
//			//标题存放格式设定
			WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
			WritableCellFormat seat = new WritableCellFormat(place);
			//水平居中
			seat.setAlignment(jxl.format.Alignment.CENTRE);
			//垂直居中
			seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//			内容存放格式设定
			WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
			WritableCellFormat center = new WritableCellFormat(Place);
			WritableCellFormat wordsize = new WritableCellFormat(Place);
			//水平居中
			center.setAlignment(jxl.format.Alignment.CENTRE);
	        //创建标题，导出时间及查询条件
			sheet.mergeCells(0, 0, 8, 0);
			Label fname = new Label(0, 0, "标题：非测量工具入库审批",seat);
			sheet.addCell(fname);
			sheet.mergeCells(0, 1, 8, 1);
			Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
			sheet.addCell(ftime);
			sheet.mergeCells(0, 2, 8, 2);
			Label Search = new Label(0, 2, "查询条件：未审批项",wordsize);
			sheet.addCell(Search);
			sheet.mergeCells(0, 3, 8, 3);
			Label People = new Label(0, 3, "审批人："+leadername,wordsize);
			sheet.addCell(People);
			
//			创建要显示的具体内容的题头
			Label order = new Label(0, 5, "序号",center);
			sheet.addCell(order);
			
			Label Mnum = new Label(1, 5, "物资编码",center);
			sheet.addCell(Mnum);
			
			Label Pclass = new Label(2, 5, "大类",center);
			sheet.addCell(Pclass);
			
			Label Bclass = new Label(3, 5, "小类",center);
			sheet.addCell(Bclass);
			
			Label Name = new Label(4, 5, "名称",center);
			sheet.addCell(Name);
			
			Label Newstatus = new Label(5, 5, "新旧状态",center);
			sheet.addCell(Newstatus);
			
			Label Company = new Label(6, 5, "厂商",center);
			sheet.addCell(Company);
			
			Label In_count = new Label(7, 5, "入库数量",center);
			sheet.addCell(In_count);
			
			Label In_time = new Label(8, 5, "入库时间",center);
			sheet.addCell(In_time);
			
			int nRow=6;
			int k = 1;
	    	for(int i=0;i<pcArray.size();i++){
				String mnum="";
				String pclass="";
				String bclass="";
				String name="";
				String newstatus="";
				String in_count="";
				String in_time="";
				String company = "";
				
				if(((HashMap)pcArray.get(i)).get("mnum")!=null){
					mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
				}
				if(((HashMap)pcArray.get(i)).get("pclass")!=null){
					pclass=((HashMap)pcArray.get(i)).get("pclass").toString();
				}
				if(((HashMap)pcArray.get(i)).get("bclass")!=null){
					bclass=((HashMap)pcArray.get(i)).get("bclass").toString();
				}
				if(((HashMap)pcArray.get(i)).get("name")!=null){
					name=((HashMap)pcArray.get(i)).get("name").toString();
				}
				if(((HashMap)pcArray.get(i)).get("newstatus")!=null){
					newstatus=((HashMap)pcArray.get(i)).get("newstatus").toString();
				}
//				数量为空时置零并将其转化为整形
				if(((HashMap)pcArray.get(i)).get("in_count")!=null){
					in_count=((HashMap)pcArray.get(i)).get("in_count").toString();
				}else{
					in_count = "0";
				}
				int I_count=Integer.parseInt(in_count);
				if(((HashMap)pcArray.get(i)).get("in_time")!=null){
					in_time=((HashMap)pcArray.get(i)).get("in_time").toString();
				}
				if(((HashMap)pcArray.get(i)).get("company")!=null){
					company=((HashMap)pcArray.get(i)).get("company").toString();
				}
				
				sheet.addCell(new Number(0,nRow,k,center));
				sheet.addCell(new Label(1,nRow,mnum,center));
				sheet.addCell(new Label(2,nRow,pclass,center));
				sheet.addCell(new Label(3,nRow,bclass,center));
				sheet.addCell(new Label(4,nRow,name,center));
				sheet.addCell(new Label(5,nRow,newstatus,center));
				sheet.addCell(new Label(6,nRow,company,center));
				sheet.addCell(new Number(7,nRow,I_count,center));
				sheet.addCell(new Label(8,nRow,in_time,center));
				sheet.addCell(new Label(9,nRow,""));
				nRow++;
				k++;
	    	}
	    	
//	    	设置列宽  
	    	sheet.setColumnView(0, 5);
	    	sheet.setColumnView(1, 20);
	    	sheet.setColumnView(2, 12); 
	    	sheet.setColumnView(3, 16); 
	    	sheet.setColumnView(4, 22); 
	    	sheet.setColumnView(5, 10); 
	    	sheet.setColumnView(6, 15); 
	    	sheet.setColumnView(7, 10);
	    	sheet.setColumnView(8, 15);
	    	workbook.write();
			workbook.close();
			os.close();
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
    }
	
	
//	未查看的入库量具导出方法
	public void createExcelM(String leader_num,String leadername,OutputStream os) throws WriteException, IOException
    {
		String sql="";
		sql="select distinct entry_num,mnum,classname,spec,name,newstatus,indi_num,in_time,company from v_m2entrycheck where trim(status)='申请'";   
		Logger.debug(sql);
    	final ArrayList pcArray=new ArrayList();
    	try{
	    	DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {	
					try{
						int j=0;
						while(rs.next()){
							HashMap paHashMap=new HashMap();
							paHashMap.put("entry_num", rs.getString("entry_num"));
							paHashMap.put("mnum", rs.getString("mnum"));
							paHashMap.put("classname", rs.getString("classname"));
							paHashMap.put("indi_num", rs.getString("indi_num"));
							paHashMap.put("name", rs.getString("name"));
							paHashMap.put("spec", rs.getString("spec"));
							paHashMap.put("newstatus", rs.getString("newstatus"));
							paHashMap.put("in_time", rs.getDate("in_time"));
							paHashMap.put("company", rs.getString("company"));
							pcArray.add(j,paHashMap);
							j++;
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}				
			});	
//	    	读取导出时间
	    	Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String CurDate = curDate.toString();
            //创建工作薄��������
	    	WritableWorkbook workbook = Workbook.createWorkbook(os);
	    	//创建工作表�����µ�
			WritableSheet sheet = workbook.createSheet("量具入库审批", 0);
//			//标题存放格式设定
			WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
			WritableCellFormat seat = new WritableCellFormat(place);
			//水平居中
			seat.setAlignment(jxl.format.Alignment.CENTRE);
			//垂直居中
			seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//			内容存放格式设定
			WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
			WritableCellFormat center = new WritableCellFormat(Place);
			WritableCellFormat wordsize = new WritableCellFormat(Place);
			//水平居中
			center.setAlignment(jxl.format.Alignment.CENTRE);
	        //创建标题，导出时间及查询条件
			sheet.mergeCells(0, 0, 8, 0);
			Label fname = new Label(0, 0, "标题：量具入库审批",seat);
			sheet.addCell(fname);
			sheet.mergeCells(0, 1, 8, 1);
			Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
			sheet.addCell(ftime);
			sheet.mergeCells(0, 2, 8, 2);
			Label Search = new Label(0, 2, "查询条件：未审批项",wordsize);
			sheet.addCell(Search);
			sheet.mergeCells(0, 3, 8, 3);
			Label People = new Label(0, 3, "审批人："+leadername,wordsize);
			sheet.addCell(People);
			
//			创建要显示的具体内容的题头
			Label order = new Label(0, 5, "序号",center);
			sheet.addCell(order);
			
			Label Indi_num = new Label(1, 5, "个体编号",center);
			sheet.addCell(Indi_num);
			
			Label Mnum = new Label(2, 5, "物资编码",center);
			sheet.addCell(Mnum);
			
			Label Classname = new Label(3, 5, "类别",center);
			sheet.addCell(Classname);
			
			Label Name = new Label(4, 5, "名称",center);
			sheet.addCell(Name);
			
			Label Company = new Label(5, 5, "厂商",center);
			sheet.addCell(Company);
			
			Label Spec = new Label(6, 5, "规格",center);
			sheet.addCell(Spec);
			
			Label Newstatus = new Label(7, 5, "新旧状态",center);
			sheet.addCell(Newstatus);
			
			Label In_time = new Label(8, 5, "入库时间",center);
			sheet.addCell(In_time);
			
			int nRow=6;
			int k =1;
	    	for(int i=0;i<pcArray.size();i++){
				String mnum="";
				String classname="";
				String spec="";
				String name="";
				String newstatus="";
				String indi_num="";
				String in_time="";
				String company = "";
				
				if(((HashMap)pcArray.get(i)).get("mnum")!=null){
					mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
				}
				if(((HashMap)pcArray.get(i)).get("classname")!=null){
					classname=((HashMap)pcArray.get(i)).get("classname").toString();
				}
				if(((HashMap)pcArray.get(i)).get("spec")!=null){
					spec=((HashMap)pcArray.get(i)).get("spec").toString();
				}
				if(((HashMap)pcArray.get(i)).get("name")!=null){
					name=((HashMap)pcArray.get(i)).get("name").toString();
				}
				if(((HashMap)pcArray.get(i)).get("newstatus")!=null){
					newstatus=((HashMap)pcArray.get(i)).get("newstatus").toString();
				}
				if(((HashMap)pcArray.get(i)).get("indi_num")!=null){
					indi_num=((HashMap)pcArray.get(i)).get("indi_num").toString();
				}
				if(((HashMap)pcArray.get(i)).get("in_time")!=null){
					in_time=((HashMap)pcArray.get(i)).get("in_time").toString();
				}
				if(((HashMap)pcArray.get(i)).get("company")!=null){
					company=((HashMap)pcArray.get(i)).get("company").toString();
				}
				
				sheet.addCell(new Number(0,nRow,k,center));
				sheet.addCell(new Label(1,nRow,indi_num,center));
				sheet.addCell(new Label(2,nRow,mnum,center));
				sheet.addCell(new Label(3,nRow,classname,center));
				sheet.addCell(new Label(4,nRow,name,center));
				sheet.addCell(new Label(5,nRow,company,center));
				sheet.addCell(new Label(6,nRow,spec,center));
				sheet.addCell(new Label(7,nRow,newstatus,center));
				sheet.addCell(new Label(8,nRow,in_time,center));
				sheet.addCell(new Label(9,nRow,""));
				nRow++;
				k++;
	    	}
	    	
//	    	设置列宽  
	    	sheet.setColumnView(0, 5);
	    	sheet.setColumnView(1, 20);
	    	sheet.setColumnView(2, 20); 
	    	sheet.setColumnView(3, 16); 
	    	sheet.setColumnView(4, 22); 
	    	sheet.setColumnView(5, 15);
	    	sheet.setColumnView(6, 26); 
	    	sheet.setColumnView(7, 10); 
	    	sheet.setColumnView(8, 15); 
	    	workbook.write();
			workbook.close();
			os.close();
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
    }
	
	
//	未查看的入库测量工具导出方法
	public void createExcelMT(String leader_num,String leadername,OutputStream os) throws WriteException, IOException
    {
		String sql="";
		sql="select distinct entry_num,mnum,classname,spec,name,newstatus,indi_num,in_time,company from v_mt2entrycheck where trim(status)='申请'";   
		Logger.debug(sql);
    	final ArrayList pcArray=new ArrayList();
    	try{
	    	DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {	
					try{
						int j=0;
						while(rs.next()){
							HashMap paHashMap=new HashMap();
							paHashMap.put("entry_num", rs.getString("entry_num"));
							paHashMap.put("mnum", rs.getString("mnum"));
							paHashMap.put("classname", rs.getString("classname"));
							paHashMap.put("indi_num", rs.getString("indi_num"));
							paHashMap.put("name", rs.getString("name"));
							paHashMap.put("spec", rs.getString("spec"));
							paHashMap.put("newstatus", rs.getString("newstatus"));
							paHashMap.put("in_time", rs.getDate("in_time"));
							paHashMap.put("company", rs.getString("company"));
							pcArray.add(j,paHashMap);
							j++;
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}				
			});	
//	    	读取导出时间
	    	Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String CurDate = curDate.toString();
            //创建工作薄��������
	    	WritableWorkbook workbook = Workbook.createWorkbook(os);
	    	//创建工作表�����µ�
			WritableSheet sheet = workbook.createSheet("测量工具入库审批", 0);
//			//标题存放格式设定
			WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
			WritableCellFormat seat = new WritableCellFormat(place);
			//水平居中
			seat.setAlignment(jxl.format.Alignment.CENTRE);
			//垂直居中
			seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//			内容存放格式设定
			WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
			WritableCellFormat center = new WritableCellFormat(Place);
			WritableCellFormat wordsize = new WritableCellFormat(Place);
			//水平居中
			center.setAlignment(jxl.format.Alignment.CENTRE);
	        //创建标题，导出时间及查询条件
			sheet.mergeCells(0, 0, 8, 0);
			Label fname = new Label(0, 0, "标题：测量工具入库审批",seat);
			sheet.addCell(fname);
			sheet.mergeCells(0, 1, 8, 1);
			Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
			sheet.addCell(ftime);
			sheet.mergeCells(0, 2, 8, 2);
			Label Search = new Label(0, 2, "查询条件：未审批项",wordsize);
			sheet.addCell(Search);
			sheet.mergeCells(0, 3, 8, 3);
			Label People = new Label(0, 3, "审批人："+leadername,wordsize);
			sheet.addCell(People);
			
//			创建要显示的具体内容的题头
			Label order = new Label(0, 5, "序号",center);
			sheet.addCell(order);
			
			Label Indi_num = new Label(1, 5, "个体编号",center);
			sheet.addCell(Indi_num);
			
			Label Mnum = new Label(2, 5, "物资编码",center);
			sheet.addCell(Mnum);
			
			Label Classname = new Label(3, 5, "类别",center);
			sheet.addCell(Classname);
			
			Label Name = new Label(4, 5, "名称",center);
			sheet.addCell(Name);
			
			Label Company = new Label(5, 5, "厂商",center);
			sheet.addCell(Company);
			
			Label Spec = new Label(6, 5, "规格",center);
			sheet.addCell(Spec);
			
			Label Newstatus = new Label(7, 5, "新旧状态",center);
			sheet.addCell(Newstatus);
			
			Label In_time = new Label(8, 5, "入库时间",center);
			sheet.addCell(In_time);
			
			int nRow=6;
			int k = 1;
	    	for(int i=0;i<pcArray.size();i++){
				String mnum="";
				String classname="";
				String spec="";
				String name="";
				String newstatus="";
				String indi_num="";
				String in_time="";
				String company = "";
				
				if(((HashMap)pcArray.get(i)).get("mnum")!=null){
					mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
				}
				if(((HashMap)pcArray.get(i)).get("classname")!=null){
					classname=((HashMap)pcArray.get(i)).get("classname").toString();
				}
				if(((HashMap)pcArray.get(i)).get("spec")!=null){
					spec=((HashMap)pcArray.get(i)).get("spec").toString();
				}
				if(((HashMap)pcArray.get(i)).get("name")!=null){
					name=((HashMap)pcArray.get(i)).get("name").toString();
				}
				if(((HashMap)pcArray.get(i)).get("newstatus")!=null){
					newstatus=((HashMap)pcArray.get(i)).get("newstatus").toString();
				}
				if(((HashMap)pcArray.get(i)).get("indi_num")!=null){
					indi_num=((HashMap)pcArray.get(i)).get("indi_num").toString();
				}
				if(((HashMap)pcArray.get(i)).get("in_time")!=null){
					in_time=((HashMap)pcArray.get(i)).get("in_time").toString();
				}
				if(((HashMap)pcArray.get(i)).get("company")!=null){
					company=((HashMap)pcArray.get(i)).get("company").toString();
				}
				
				sheet.addCell(new Number(0,nRow,k,center));
				sheet.addCell(new Label(1,nRow,indi_num,center));
				sheet.addCell(new Label(2,nRow,mnum,center));
				sheet.addCell(new Label(3,nRow,classname,center));
				sheet.addCell(new Label(4,nRow,name,center));
				sheet.addCell(new Label(5,nRow,company,center));
				sheet.addCell(new Label(6,nRow,spec,center));
				sheet.addCell(new Label(7,nRow,newstatus,center));
				sheet.addCell(new Label(8,nRow,in_time,center));
				sheet.addCell(new Label(9,nRow,""));
				nRow++;
				k++;
	    	}
	    	
//	    	设置列宽  
	    	sheet.setColumnView(0, 5);
	    	sheet.setColumnView(1, 20);
	    	sheet.setColumnView(2, 20); 
	    	sheet.setColumnView(3, 16); 
	    	sheet.setColumnView(4, 22); 
	    	sheet.setColumnView(5, 15);
	    	sheet.setColumnView(6, 26); 
	    	sheet.setColumnView(7, 10); 
	    	sheet.setColumnView(8, 15); 
	    	workbook.write();
			workbook.close();
			os.close();
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
    }
	
	
//	未查看的返库刀具导出方法
	public void createExcelIn(String leader_num,String leadername,HashMap hashMap,OutputStream os) throws WriteException, IOException
    {
		String sql="";
		sql = "select distinct rid,name,s_count,mnum,pclass,bclass,grd_department,s_time,r_count,r_time,l_count from v_cgrd2returncheck where trim(status)='2' and (trim(app_status_in)='未处理' or app_status_in is null) and grd_num='"+(String)hashMap.get("grd_num")+"'";
		Logger.debug(sql);
		final ArrayList pcArray=new ArrayList();
    	try{
	    	DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {	
					try{
						int j=0;
						while(rs.next()){
							HashMap paHashMap=new HashMap();
							DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
							decimalformat.setMaximumFractionDigits(2);
							paHashMap.put("rid", rs.getString("rid"));
							paHashMap.put("mnum", rs.getString("mnum"));
							paHashMap.put("pclass", rs.getString("pclass"));
							paHashMap.put("bclass", rs.getString("bclass"));
							paHashMap.put("name", rs.getString("name"));
							paHashMap.put("l_count", decimalformat.format(rs.getInt("s_count")-rs.getInt("r_count")));
							paHashMap.put("grd_department", rs.getString("grd_department"));
							paHashMap.put("s_time", rs.getDate("s_time"));
							paHashMap.put("s_count", rs.getString("s_count"));
							paHashMap.put("r_count", rs.getString("r_count"));
							paHashMap.put("r_time", rs.getDate("r_time"));
							pcArray.add(j,paHashMap);
							j++;
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}				
			});	
//	    	读取导出时间
	    	Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String CurDate = curDate.toString();
            //创建工作薄��������
	    	WritableWorkbook workbook = Workbook.createWorkbook(os);
	    	//创建工作表�����µ�
			WritableSheet sheet = workbook.createSheet("送磨刀具返库审批", 0);
//			//标题存放格式设定
			WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
			WritableCellFormat seat = new WritableCellFormat(place);
			//水平居中
			seat.setAlignment(jxl.format.Alignment.CENTRE);
			//垂直居中
			seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//			内容存放格式设定
			WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
			WritableCellFormat center = new WritableCellFormat(Place);
			WritableCellFormat wordsize = new WritableCellFormat(Place);
			//水平居中
			center.setAlignment(jxl.format.Alignment.CENTRE);
	        //创建标题，导出时间及查询条件
			sheet.mergeCells(0, 0, 10, 0);
			Label fname = new Label(0, 0, "标题：送磨刀具返库审批",seat);
			sheet.addCell(fname);
			sheet.mergeCells(0, 1, 10, 1);
			Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
			sheet.addCell(ftime);
			sheet.mergeCells(0, 2, 10, 2);
			Label Search = new Label(0, 2, "送磨单号："+(String)hashMap.get("grd_num"),wordsize);
			sheet.addCell(Search);
			sheet.mergeCells(0, 3, 10, 3);
			Label People = new Label(0, 3, "审批人："+leadername,wordsize);
			sheet.addCell(People);
			
//			创建要显示的具体内容的题头
			Label order = new Label(0, 5, "序号",center);
			sheet.addCell(order);
			Label Mnum = new Label(1, 5, "物资编码",center);
			sheet.addCell(Mnum);
			Label Pclass = new Label(2, 5, "大类",center);
			sheet.addCell(Pclass);
			Label Bclass = new Label(3, 5, "小类",center);
			sheet.addCell(Bclass);
			Label Name = new Label(4, 5, "名称",center);
			sheet.addCell(Name);
			Label Scount = new Label(5, 5, "送磨数量",center);
			sheet.addCell(Scount);
			Label Rcount = new Label(6, 5, "返库数量",center);
			sheet.addCell(Rcount);
			Label Lcount = new Label(7, 5, "损耗数量",center);
			sheet.addCell(Lcount);
			Label Department = new Label(8, 5, "磨刀单位",center);
			sheet.addCell(Department);
			Label Stime = new Label(9, 5, "送磨时间",center);
			sheet.addCell(Stime);
			Label Rtime = new Label(10, 5, "返库时间",center);
			sheet.addCell(Rtime);
			int nRow=6;
			int k = 1;
	    	for(int i=0;i<pcArray.size();i++){
				String mnum="";
				String pclass="";
				String bclass="";
				String name="";
				String l_count="";
				String grd_department="";
				String s_time="";
				String s_count="";
				String r_count="";
				String r_time="";
				if(((HashMap)pcArray.get(i)).get("mnum")!=null){
					mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
				}
				if(((HashMap)pcArray.get(i)).get("pclass")!=null){
					pclass=((HashMap)pcArray.get(i)).get("pclass").toString();
				}
				if(((HashMap)pcArray.get(i)).get("bclass")!=null){
					bclass=((HashMap)pcArray.get(i)).get("bclass").toString();
				}
				if(((HashMap)pcArray.get(i)).get("name")!=null){
					name=((HashMap)pcArray.get(i)).get("name").toString();
				}
				if(((HashMap)pcArray.get(i)).get("l_count")!=null){
					l_count=((HashMap)pcArray.get(i)).get("l_count").toString();
				}else{
					l_count = "0";
				}
				int L_count=Integer.parseInt(l_count);
				if(((HashMap)pcArray.get(i)).get("grd_department")!=null){
					grd_department=((HashMap)pcArray.get(i)).get("grd_department").toString();
				}
				if(((HashMap)pcArray.get(i)).get("s_time")!=null){
					s_time=((HashMap)pcArray.get(i)).get("s_time").toString();
				}
//				数量为空时置零并将其转化为整形
				if(((HashMap)pcArray.get(i)).get("s_count")!=null){
					s_count=((HashMap)pcArray.get(i)).get("s_count").toString();
				}else{
					s_count = "0";
				}
				int S_count=Integer.parseInt(s_count);
				if(((HashMap)pcArray.get(i)).get("r_count")!=null){
					r_count=((HashMap)pcArray.get(i)).get("r_count").toString();
				}else{
					r_count = "0";
				}
				int R_count=Integer.parseInt(r_count);
				if(((HashMap)pcArray.get(i)).get("r_time")!=null){
					r_time=((HashMap)pcArray.get(i)).get("r_time").toString();
				}
				sheet.addCell(new Number(0,nRow,k,center));
				sheet.addCell(new Label(1,nRow,mnum,center));
				sheet.addCell(new Label(2,nRow,pclass,center));
				sheet.addCell(new Label(3,nRow,bclass,center));
				sheet.addCell(new Label(4,nRow,name,center));
				sheet.addCell(new Number(5,nRow,S_count,center));
				sheet.addCell(new Number(6,nRow,R_count,center));
				sheet.addCell(new Number(7,nRow,L_count,center));
				sheet.addCell(new Label(8,nRow,grd_department,center));
				sheet.addCell(new Label(9,nRow,s_time,center));
				sheet.addCell(new Label(10,nRow,r_time,center));
				sheet.addCell(new Label(11,nRow,""));
				nRow++;
				k++;
	    	}
	    	
//	    	设置列宽  
	    	sheet.setColumnView(0, 5);
	    	sheet.setColumnView(1, 20);
	    	sheet.setColumnView(2, 12); 
	    	sheet.setColumnView(3, 16); 
	    	sheet.setColumnView(4, 22); 
	    	sheet.setColumnView(5, 10); 
	    	sheet.setColumnView(6, 10); 
	    	sheet.setColumnView(7, 10); 
	    	sheet.setColumnView(8, 20); 
	    	sheet.setColumnView(9, 15); 
	    	sheet.setColumnView(10, 15); 
	    	workbook.write();
			workbook.close();
			os.close();
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
    }
	
	
//	未查看的送磨刀具导出方法
	public void createExcelOut(String leader_num,String leadername,HashMap hashMap,OutputStream os) throws WriteException, IOException
    {
		String sql="";
		sql = "select distinct rid,name,s_count,company,mnum,grd_num,bclass,grd_department,s_time,price from v_cgrd2check where trim(status)='0' and (trim(app_status)='未处理' or app_status is null) and grd_num='"+(String)hashMap.get("grd_num")+"'";
		Logger.debug(sql);
		final ArrayList pcArray=new ArrayList();
    	try{
	    	DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {	
					try{
						int j=0;
						while(rs.next()){
							HashMap paHashMap=new HashMap();
							DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
							decimalformat.setMaximumFractionDigits(2);
							paHashMap.put("rid", rs.getString("rid"));
							paHashMap.put("grd_num", rs.getString("grd_num"));
							paHashMap.put("mnum", rs.getString("mnum"));
							paHashMap.put("company", rs.getString("company"));
							paHashMap.put("bclass", rs.getString("bclass"));
							paHashMap.put("name", rs.getString("name"));
							paHashMap.put("grd_department", rs.getString("grd_department"));
							paHashMap.put("s_time", rs.getDate("s_time"));
							paHashMap.put("s_count", rs.getString("s_count"));
							paHashMap.put("price", decimalformat.format(rs.getFloat("price")));	
							paHashMap.put("total_price", decimalformat.format(rs.getFloat("price")*rs.getInt("s_count")));
							pcArray.add(j,paHashMap);
							j++;
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}				
			});	
	    	
	    	
//	    	读取导出时间
	    	Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String CurDate = curDate.toString();
            //创建工作薄��������
	    	WritableWorkbook workbook = Workbook.createWorkbook(os);
	    	//创建工作表�����µ�
			WritableSheet sheet = workbook.createSheet("刀具送磨审批", 0);
//			//标题存放格式设定
			WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
			WritableCellFormat seat = new WritableCellFormat(place);
			//水平居中
			seat.setAlignment(jxl.format.Alignment.CENTRE);
			//垂直居中
			seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//			内容存放格式设定
			WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
			WritableCellFormat center = new WritableCellFormat(Place);
			WritableCellFormat wordsize = new WritableCellFormat(Place);
			//水平居中
			center.setAlignment(jxl.format.Alignment.CENTRE);
	        //创建标题，导出时间及查询条件
			sheet.mergeCells(0, 0, 9, 0);
			Label fname = new Label(0, 0, "标题：刀具送磨审批",seat);
			sheet.addCell(fname);
			sheet.mergeCells(0, 1, 9, 1);
			Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
			sheet.addCell(ftime);
			sheet.mergeCells(0, 2, 9, 2);
			Label Search = new Label(0, 2, "送磨单号："+(String)hashMap.get("grd_num"),wordsize);
			sheet.addCell(Search);
			sheet.mergeCells(0, 3, 9, 3);
			Label People = new Label(0, 3, "审批人："+leadername,wordsize);
			sheet.addCell(People);
			
//			创建要显示的具体内容的题头
			Label order = new Label(0, 5, "序号",center);
			sheet.addCell(order);
			Label Mnum = new Label(1, 5, "物资编码",center);
			sheet.addCell(Mnum);
			Label Pclass = new Label(2, 5, "小类",center);
			sheet.addCell(Pclass);
			Label Bclass = new Label(3, 5, "名称",center);
			sheet.addCell(Bclass);
			Label Name = new Label(4, 5, "厂商",center);
			sheet.addCell(Name);
			Label Scount = new Label(5, 5, "送磨数量",center);
			sheet.addCell(Scount);
			Label Department = new Label(6, 5, "磨刀单位",center);
			sheet.addCell(Department);
			Label Price = new Label(7, 5, "单价（元）",center);
			sheet.addCell(Price);
			Label Total_price = new Label(8, 5, "总价（元）",center);
			sheet.addCell(Total_price);
			Label Stime = new Label(9, 5, "送磨时间",center);
			sheet.addCell(Stime);
			int nRow=6;
			int k = 1;
			float expense = 0;
			
			//截取小数点后面的位数为2位
			DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
			decimalformat.setMaximumFractionDigits(2);
			
	    	for(int i=0;i<pcArray.size();i++){
				String mnum="";
				String company="";
				String bclass="";
				String name="";
				String grd_department="";
				String s_time="";
				String s_count="";
				String price = "";
				float total_price = 0;
				String Total_Price ="";
				
				
				if(((HashMap)pcArray.get(i)).get("mnum")!=null){
					mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
				}
				if(((HashMap)pcArray.get(i)).get("company")!=null){
					company=((HashMap)pcArray.get(i)).get("company").toString();
				}
				if(((HashMap)pcArray.get(i)).get("bclass")!=null){
					bclass=((HashMap)pcArray.get(i)).get("bclass").toString();
				}
				if(((HashMap)pcArray.get(i)).get("name")!=null){
					name=((HashMap)pcArray.get(i)).get("name").toString();
				}
				if(((HashMap)pcArray.get(i)).get("grd_department")!=null){
					grd_department=((HashMap)pcArray.get(i)).get("grd_department").toString();
				}
				if(((HashMap)pcArray.get(i)).get("s_time")!=null){
					s_time=((HashMap)pcArray.get(i)).get("s_time").toString();
				}
				if(((HashMap)pcArray.get(i)).get("price")!=null){
					price = ((HashMap)pcArray.get(i)).get("price").toString();
				}
				if(((HashMap)pcArray.get(i)).get("total_price")!=null){
					total_price = Float.parseFloat(((HashMap)pcArray.get(i)).get("total_price").toString());
					Total_Price = decimalformat.format(total_price);
				}
				expense += total_price;
//				数量为空时置零并将其转化为整形
				if(((HashMap)pcArray.get(i)).get("s_count")!=null){
					s_count=((HashMap)pcArray.get(i)).get("s_count").toString();
				}else{
					s_count = "0";
				}
				int S_count=Integer.parseInt(s_count);
				sheet.addCell(new Number(0,nRow,k,center));
				sheet.addCell(new Label(1,nRow,mnum,center));
				sheet.addCell(new Label(2,nRow,bclass,center));
				sheet.addCell(new Label(3,nRow,name,center));
				sheet.addCell(new Label(4,nRow,company,center));
				sheet.addCell(new Number(5,nRow,S_count,center));
				sheet.addCell(new Label(6,nRow,grd_department,center));
				sheet.addCell(new Label(7,nRow,price,center));
				sheet.addCell(new Label(8,nRow,Total_Price,center));
				sheet.addCell(new Label(9,nRow,s_time,center));
				sheet.addCell(new Label(10,nRow,""));
				nRow++;
				k++;
	    	}
	    	
	    	sheet.addCell(new Label(7,(nRow+1),"总费用（元）：",center));
	    	sheet.addCell(new Label(8,(nRow+1),decimalformat.format(expense),center));
//	    	设置列宽  
	    	sheet.setColumnView(0, 5);
	    	sheet.setColumnView(1, 20); 
	    	sheet.setColumnView(2, 16); 
	    	sheet.setColumnView(3, 22); 
	    	sheet.setColumnView(4, 22); 
	    	sheet.setColumnView(5, 10); 
	    	sheet.setColumnView(6, 20); 
	    	sheet.setColumnView(7, 12); 
	    	sheet.setColumnView(8, 12);
	    	sheet.setColumnView(9, 15); 
	    	workbook.write();
			workbook.close();
			os.close();
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
    }
	
}
