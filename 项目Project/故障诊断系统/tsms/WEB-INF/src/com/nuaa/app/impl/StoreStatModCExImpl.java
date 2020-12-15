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
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.nuaa.app.StoreStatModCEx;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class StoreStatModCExImpl implements StoreStatModCEx {

	public void StoreStatModC1Ex(HashMap hashMap, OutputStream os)
			throws WriteException, IOException {
		// TODO 自动生成方法存根
		System.out.println("filter="+(String)hashMap.get("filter"));
		String filter=(String)hashMap.get("filter");
		String order=(String)hashMap.get("order");
		String result=(String)hashMap.get("result");
		String sql="";
		sql="select * from c_base where "+filter+" order by "+order+"";
		Logger.debug(sql);
		final ArrayList pcArray=new ArrayList();
    	try{
	    	DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {	
					try{
						int j=0;
						while(rs.next()){
							HashMap paHashMap=new HashMap();
							paHashMap.put("mnum", rs.getString("mnum"));
							paHashMap.put("pclass", rs.getString("pclass"));
							paHashMap.put("bclass", rs.getString("bclass"));
							paHashMap.put("name", rs.getString("name"));
							paHashMap.put("company", rs.getString("company"));
							paHashMap.put("order_num", rs.getString("order_num"));
							paHashMap.put("nc_count_in", rs.getString("nc_count_in"));
							paHashMap.put("uc_count_in", rs.getString("uc_count_in"));
							paHashMap.put("hq", rs.getString("hq"));
							paHashMap.put("location", rs.getString("location"));
							pcArray.add(j,paHashMap);
							j++;
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}				
			});	
	    	//读取导出日期
	    	Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String CurDate = curDate.toString();
            //创建工作薄��������
	    	WritableWorkbook workbook = Workbook.createWorkbook(os);
	    	//创建工作表�����µ�
			WritableSheet sheet = workbook.createSheet("First Sheet", 0);
//			标题存放格式设定
			WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
			WritableCellFormat seat = new WritableCellFormat(place);
//			水平居中
			seat.setAlignment(jxl.format.Alignment.CENTRE);
//			垂直居中
			seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			
//			内容存放格式设定
			WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
			WritableCellFormat center = new WritableCellFormat(Place);
			WritableCellFormat wordsize = new WritableCellFormat(Place);
			//水平居中
			center.setAlignment(jxl.format.Alignment.CENTRE);
//			创建标题，导出时间及查询条件
			sheet.mergeCells(0, 0, 9, 0);
			Label fname = new Label(0, 0, "标题：刀具当前库存统计",seat);
			sheet.addCell(fname);
			sheet.mergeCells(0, 1, 9, 1);
			Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
			sheet.addCell(ftime);
			sheet.mergeCells(0, 2, 9, 2);
			Label Search = new Label(0, 2, "查询条件："+result,wordsize);
			sheet.addCell(Search);
	        //创建要显示的具体内容������ʾ�ľ�������
			Label mnum1 = new Label(0, 4, "物资编码",center);
			sheet.addCell(mnum1);
			Label pclass1 = new Label(1, 4, "大类",center);
			sheet.addCell(pclass1);
			Label bclass1 = new Label(2, 4, "小类",center);
			sheet.addCell(bclass1);
			Label name1= new Label(3, 4, "名称",center);
			sheet.addCell(name1);
			Label company1 = new Label(4, 4, "厂商",center);
			sheet.addCell(company1);
			Label order_num1 = new Label(5, 4, "订货号",center);
			sheet.addCell(order_num1);
			Label nc_count_in1= new Label(6, 4, "新刀在库量",center);
			sheet.addCell(nc_count_in1);
			Label uc_count_in1 = new Label(7, 4, "旧刀在库量",center);
			sheet.addCell(uc_count_in1);
			Label hq1 = new Label(8, 4, "是否高质",center);
			sheet.addCell(hq1);
			Label location1 = new Label(9, 4, "库存地点",center);
			sheet.addCell(location1);
			Label location2 = new Label(10, 4, "");
			sheet.addCell(location2);
			int nRow=5;
	    	for(int i=0;i<pcArray.size();i++){
				String mnum="";
				String pclass="";
				String bclass="";
				String name="";
				String company="";
				String order_num="";
				String nc_count_in="";
				String uc_count_in="";
				String hq="";
				String location="";
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
				if(((HashMap)pcArray.get(i)).get("company")!=null){
					company=((HashMap)pcArray.get(i)).get("company").toString();
				}
				if(((HashMap)pcArray.get(i)).get("order_num")!=null){
					order_num=((HashMap)pcArray.get(i)).get("order_num").toString();
				}
				if(((HashMap)pcArray.get(i)).get("nc_count_in")!=null){
					nc_count_in=((HashMap)pcArray.get(i)).get("nc_count_in").toString();
				}else{
					nc_count_in = "0";
				}
				int nccount_in = Integer.parseInt(nc_count_in);
				if(((HashMap)pcArray.get(i)).get("uc_count_in")!=null){
					uc_count_in=((HashMap)pcArray.get(i)).get("uc_count_in").toString();
				}else{
					uc_count_in = "0";
				}
				int uccount_in = Integer.parseInt(uc_count_in);
				if(((HashMap)pcArray.get(i)).get("hq")!=null){
					hq=((HashMap)pcArray.get(i)).get("hq").toString();
				}
				if(((HashMap)pcArray.get(i)).get("location")!=null){
					location=((HashMap)pcArray.get(i)).get("location").toString();
				}
				sheet.addCell(new Label(0,nRow,mnum,center));
				sheet.addCell(new Label(1,nRow,pclass,center));
				sheet.addCell(new Label(2,nRow,bclass,center));
				sheet.addCell(new Label(3,nRow,name,center));
				sheet.addCell(new Label(4,nRow,company,center));
				sheet.addCell(new Label(5,nRow,order_num,center));
				sheet.addCell(new Number(6,nRow,nccount_in,center));
				sheet.addCell(new Number(7,nRow,uccount_in,center));
				sheet.addCell(new Label(8,nRow,hq,center));
				sheet.addCell(new Label(9,nRow,location,center));
				sheet.addCell(new Label(10,nRow,""));
				nRow++;
	    	}
	    	sheet.setColumnView(0, 20);
	    	sheet.setColumnView(1, 12);
	    	sheet.setColumnView(2, 16); 
	    	sheet.setColumnView(3, 22); 
	    	sheet.setColumnView(4, 22); 
	    	sheet.setColumnView(5, 15); 
	    	sheet.setColumnView(6, 15); 
	    	sheet.setColumnView(7, 15); 
	    	sheet.setColumnView(8, 10);
	    	sheet.setColumnView(9, 15);
	    	workbook.write();
			workbook.close();
			os.close();
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	
	
	
	public void StoreStatModC2Ex(HashMap hashMap, OutputStream os)
	throws WriteException, IOException {
// TODO 自动生成方法存根
System.out.println("filter="+(String)hashMap.get("filter"));
String filter=(String)hashMap.get("filter");
String order=(String)hashMap.get("order");
String result=(String)hashMap.get("result");
String sql="";
sql="select * from c_entry where "+filter+" order by "+order+"";
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
					paHashMap.put("company", rs.getString("company"));
					paHashMap.put("order_num", rs.getString("order_num"));
					paHashMap.put("in_count", rs.getString("in_count"));
					paHashMap.put("in_time", rs.getDate("in_time"));
					paHashMap.put("newstatus", rs.getString("newstatus"));
					pcArray.add(j,paHashMap);
					j++;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}				
	});
	//读取导出日期
	Calendar c = Calendar.getInstance();
	final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
	String CurDate = curDate.toString();
    //创建工作薄��������
	WritableWorkbook workbook = Workbook.createWorkbook(os);
	//创建工作表�����µ�
	WritableSheet sheet = workbook.createSheet("First Sheet", 0);
//	标题存放格式设定
	WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
	WritableCellFormat seat = new WritableCellFormat(place);
//	水平居中
	seat.setAlignment(jxl.format.Alignment.CENTRE);
//	垂直居中
	seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	
//	内容存放格式设定
	WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
	WritableCellFormat center = new WritableCellFormat(Place);
	WritableCellFormat wordsize = new WritableCellFormat(Place);
	//水平居中
	center.setAlignment(jxl.format.Alignment.CENTRE);
//	创建标题，导出时间及查询条件
	sheet.mergeCells(0, 0, 9, 0);
	Label fname = new Label(0, 0, "标题：刀具入库历史记录",seat);
	sheet.addCell(fname);
	sheet.mergeCells(0, 1, 9, 1);
	Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
	sheet.addCell(ftime);
	sheet.mergeCells(0, 2, 9, 2);
	Label Search = new Label(0, 2, "查询条件："+result,wordsize);
	sheet.addCell(Search);
    //创建要显示的具体内容������ʾ�ľ�������
	Label entry_num1 = new Label(0, 4, "入库单号",center);
	sheet.addCell(entry_num1);
	Label mnum1 = new Label(1, 4, "物资编码",center);
	sheet.addCell(mnum1);
	Label pclass1 = new Label(2, 4, "大类",center);
	sheet.addCell(pclass1);
	Label bclass1 = new Label(3, 4, "小类",center);
	sheet.addCell(bclass1);
	Label name1= new Label(4, 4, "名称",center);
	sheet.addCell(name1);
	Label company1 = new Label(5, 4, "厂商",center);
	sheet.addCell(company1);
	Label order_num1 = new Label(6, 4, "订货号",center);
	sheet.addCell(order_num1);
	Label in_count1= new Label(7, 4, "入库数量",center);
	sheet.addCell(in_count1);
	Label in_time1 = new Label(8, 4, "入库日期",center);
	sheet.addCell(in_time1);
	Label gc_count_in1 = new Label(9, 4, "新旧状态",center);
	sheet.addCell(gc_count_in1);
	Label hq1 = new Label(10, 4, "");
	sheet.addCell(hq1);
	/*Label location1 = new Label(11, 0, "库存地点");
	sheet.addCell(location1);*/
	int nRow=5;
	for(int i=0;i<pcArray.size();i++){
		String entry_num="";
		String mnum="";
		String pclass="";
		String bclass="";
		String name="";
		String company="";
		String order_num="";
		String in_count="";
		String in_time="";
		String newstatus="";
		if(((HashMap)pcArray.get(i)).get("entry_num")!=null){
			entry_num=((HashMap)pcArray.get(i)).get("entry_num").toString();
		}
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
		if(((HashMap)pcArray.get(i)).get("company")!=null){
			company=((HashMap)pcArray.get(i)).get("company").toString();
		}
		if(((HashMap)pcArray.get(i)).get("order_num")!=null){
			order_num=((HashMap)pcArray.get(i)).get("order_num").toString();
		}
		if(((HashMap)pcArray.get(i)).get("in_count")!=null){
			in_count=((HashMap)pcArray.get(i)).get("in_count").toString();
		}else{
			in_count = "0";
		}
		int incount = Integer.parseInt(in_count);
		if(((HashMap)pcArray.get(i)).get("in_time")!=null){
			in_time=((HashMap)pcArray.get(i)).get("in_time").toString();
		}
		if(((HashMap)pcArray.get(i)).get("newstatus")!=null){
			newstatus=((HashMap)pcArray.get(i)).get("newstatus").toString();
		}
		/*if(((HashMap)pcArray.get(i)).get("hq")!=null){
			hq=((HashMap)pcArray.get(i)).get("hq").toString();
		}
		if(((HashMap)pcArray.get(i)).get("location")!=null){
			location=((HashMap)pcArray.get(i)).get("location").toString();
		}*/
		sheet.addCell(new Label(0,nRow,entry_num,center));
		sheet.addCell(new Label(1,nRow,mnum,center));
		sheet.addCell(new Label(2,nRow,pclass,center));
		sheet.addCell(new Label(3,nRow,bclass,center));
		sheet.addCell(new Label(4,nRow,name,center));
		sheet.addCell(new Label(5,nRow,company,center));
		sheet.addCell(new Label(6,nRow,order_num,center));
		sheet.addCell(new Number(7,nRow,incount,center));
		sheet.addCell(new Label(8,nRow,in_time,center));
		sheet.addCell(new Label(9,nRow,newstatus,center));
		sheet.addCell(new Label(10,nRow,""));
		nRow++;
	}
	sheet.setColumnView(0, 15);
	sheet.setColumnView(1, 20);
	sheet.setColumnView(2, 12); 
	sheet.setColumnView(3, 16); 
	sheet.setColumnView(4, 22); 
	sheet.setColumnView(5, 22); 
	sheet.setColumnView(6, 15); 
	sheet.setColumnView(7, 10); 
	sheet.setColumnView(8, 15);
	sheet.setColumnView(9, 10);
	workbook.write();
	workbook.close();
	os.close();
}catch (SQLException e) {
// TODO 自动生成 catch 块
	e.printStackTrace();
}
}

	public void StoreStatModC3Ex(HashMap hashMap, OutputStream os)
	throws WriteException, IOException {
// TODO 自动生成方法存根
System.out.println("filter="+(String)hashMap.get("filter"));
String filter=(String)hashMap.get("filter");
String order=(String)hashMap.get("order");
String result=(String)hashMap.get("result");
String sql="";
sql="select * from c_br_now where "+filter+" order by "+order+"";
Logger.debug(sql);
final ArrayList pcArray=new ArrayList();
try{
	DbUtil.execute(sql,new IResultSetProcessor() {
		public void process(ResultSet rs) throws SQLException {	
			try{
				int j=0;
				while(rs.next()){
					HashMap paHashMap=new HashMap();
					paHashMap.put("mnum", rs.getString("mnum"));
					paHashMap.put("pclass", rs.getString("pclass"));
					paHashMap.put("bclass", rs.getString("bclass"));
					paHashMap.put("name", rs.getString("name"));
					paHashMap.put("company", rs.getString("company"));
					paHashMap.put("order_num", rs.getString("order_num"));
					paHashMap.put("worker_num", rs.getString("worker_num"));
					paHashMap.put("realname", rs.getString("realname"));
					paHashMap.put("group_num", rs.getString("group_num"));
					paHashMap.put("br_count", rs.getString("br_count"));
					pcArray.add(j,paHashMap);
					j++;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}				
	});	
//	读取导出日期
	Calendar c = Calendar.getInstance();
	final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
	String CurDate = curDate.toString();
    //创建工作薄��������
	WritableWorkbook workbook = Workbook.createWorkbook(os);
	//创建工作表�����µ�
	WritableSheet sheet = workbook.createSheet("First Sheet", 0);
//	标题存放格式设定
	WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
	WritableCellFormat seat = new WritableCellFormat(place);
//	水平居中
	seat.setAlignment(jxl.format.Alignment.CENTRE);
//	垂直居中
	seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	
//	内容存放格式设定
	WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
	WritableCellFormat center = new WritableCellFormat(Place);
	WritableCellFormat wordsize = new WritableCellFormat(Place);
	//水平居中
	center.setAlignment(jxl.format.Alignment.CENTRE);
//	创建标题，导出时间及查询条件
	sheet.mergeCells(0, 0, 9, 0);
	Label fname = new Label(0, 0, "标题：刀具当前借出记录",seat);
	sheet.addCell(fname);
	sheet.mergeCells(0, 1, 9, 1);
	Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
	sheet.addCell(ftime);
	sheet.mergeCells(0, 2, 9, 2);
	Label Search = new Label(0, 2, "查询条件："+result,wordsize);
	sheet.addCell(Search);
    //创建要显示的具体内容������ʾ�ľ�������
	Label mnum1 = new Label(0, 4, "物资编码",center);
	sheet.addCell(mnum1);
	Label pclass1 = new Label(1, 4, "大类",center);
	sheet.addCell(pclass1);
	Label bclass1 = new Label(2, 4, "小类",center);
	sheet.addCell(bclass1);
	Label name1= new Label(3, 4, "名称",center);
	sheet.addCell(name1);
	Label company1 = new Label(4, 4, "厂商",center);
	sheet.addCell(company1);
	Label order_num1 = new Label(5, 4, "订货号",center);
	sheet.addCell(order_num1);
	Label in_count1= new Label(6, 4, "工人工号",center);
	sheet.addCell(in_count1);
	Label in_count2= new Label(7, 4, "工人姓名",center);
	sheet.addCell(in_count2);
	Label in_count3= new Label(8, 4, "工人组别",center);
	sheet.addCell(in_count3);
	Label in_time1 = new Label(9, 4, "借用数量",center);
	sheet.addCell(in_time1);
	Label hq1 = new Label(10, 4, "");
	sheet.addCell(hq1);
	int nRow=5;
	for(int i=0;i<pcArray.size();i++){
		String mnum="";
		String pclass="";
		String bclass="";
		String name="";
		String company="";
		String order_num="";
		String worker_num="";
		String realname="";
		String group_num="";
		String br_count="";
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
		if(((HashMap)pcArray.get(i)).get("company")!=null){
			company=((HashMap)pcArray.get(i)).get("company").toString();
		}
		if(((HashMap)pcArray.get(i)).get("order_num")!=null){
			order_num=((HashMap)pcArray.get(i)).get("order_num").toString();
		}
		if(((HashMap)pcArray.get(i)).get("worker_num")!=null){
			worker_num=((HashMap)pcArray.get(i)).get("worker_num").toString();
		}
		if(((HashMap)pcArray.get(i)).get("realname")!=null){
			realname=((HashMap)pcArray.get(i)).get("realname").toString();
		}
		if(((HashMap)pcArray.get(i)).get("group_num")!=null){
			group_num=((HashMap)pcArray.get(i)).get("group_num").toString();
		}
		if(((HashMap)pcArray.get(i)).get("br_count")!=null){
			br_count=((HashMap)pcArray.get(i)).get("br_count").toString();
		}else{
			br_count= "0";
		}
		int brcount = Integer.parseInt(br_count);
		
		sheet.addCell(new Label(0,nRow,mnum,center));
		sheet.addCell(new Label(1,nRow,pclass,center));
		sheet.addCell(new Label(2,nRow,bclass,center));
		sheet.addCell(new Label(3,nRow,name,center));
		sheet.addCell(new Label(4,nRow,company,center));
		sheet.addCell(new Label(5,nRow,order_num,center));
		sheet.addCell(new Label(6,nRow,worker_num,center));
		sheet.addCell(new Label(7,nRow,realname,center));
		sheet.addCell(new Label(8,nRow,group_num,center));
		sheet.addCell(new Number(9,nRow,brcount,center));
		sheet.addCell(new Label(10,nRow,""));
		nRow++;
	}
	sheet.setColumnView(0, 20);
	sheet.setColumnView(1, 12); 
	sheet.setColumnView(2, 16); 
	sheet.setColumnView(3, 22); 
	sheet.setColumnView(4, 22); 
	sheet.setColumnView(5, 15); 
	sheet.setColumnView(6, 15); 
	sheet.setColumnView(7, 15); 
	sheet.setColumnView(8, 10);
	sheet.setColumnView(9, 10);
	workbook.write();
	workbook.close();
	os.close();
}catch (SQLException e) {
// TODO 自动生成 catch 块
	e.printStackTrace();
}
}

	public void StoreStatModC4Ex(HashMap hashMap, OutputStream os)
	throws WriteException, IOException {
// TODO 自动生成方法存根
System.out.println("filter="+(String)hashMap.get("filter"));
String filter=(String)hashMap.get("filter");
String order=(String)hashMap.get("order");
String result=(String)hashMap.get("result");
String sql="";
sql="select * from c_gr where "+filter+" order by "+order+"";
Logger.debug(sql);
final ArrayList pcArray=new ArrayList();
try{
	DbUtil.execute(sql,new IResultSetProcessor() {
		public void process(ResultSet rs) throws SQLException {	
			try{
				int j=0;
				while(rs.next()){
					HashMap paHashMap=new HashMap();
					paHashMap.put("grd_num", rs.getString("grd_num"));
					paHashMap.put("mnum", rs.getString("mnum"));
					paHashMap.put("pclass", rs.getString("pclass"));
					paHashMap.put("bclass", rs.getString("bclass"));
					paHashMap.put("name", rs.getString("name"));
					paHashMap.put("company", rs.getString("company"));
					paHashMap.put("order_num", rs.getString("order_num"));
					paHashMap.put("s_count", rs.getString("s_count"));
					paHashMap.put("s_time", rs.getDate("s_time"));
					paHashMap.put("r_count", rs.getString("r_count"));
					paHashMap.put("r_time", rs.getDate("r_time"));
					paHashMap.put("l_count", rs.getString("l_count"));
					paHashMap.put("grd_department", rs.getString("grd_department"));
					pcArray.add(j,paHashMap);
					j++;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}				
	});	
//	读取导出日期
	Calendar c = Calendar.getInstance();
	final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
	String CurDate = curDate.toString();
    //创建工作薄��������
	WritableWorkbook workbook = Workbook.createWorkbook(os);
	//创建工作表�����µ�
	WritableSheet sheet = workbook.createSheet("First Sheet", 0);
//	标题存放格式设定
	WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
	WritableCellFormat seat = new WritableCellFormat(place);
//	水平居中
	seat.setAlignment(jxl.format.Alignment.CENTRE);
//	垂直居中
	seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	
//	内容存放格式设定
	WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
	WritableCellFormat center = new WritableCellFormat(Place);
	WritableCellFormat wordsize = new WritableCellFormat(Place);
	//水平居中
	center.setAlignment(jxl.format.Alignment.CENTRE);
//	创建标题，导出时间及查询条件
	sheet.mergeCells(0, 0, 12, 0);
	Label fname = new Label(0, 0, "标题：刀具送磨反库记录",seat);
	sheet.addCell(fname);
	sheet.mergeCells(0, 1, 12, 1);
	Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
	sheet.addCell(ftime);
	sheet.mergeCells(0, 2, 12, 2);
	Label Search = new Label(0, 2, "查询条件："+result,wordsize);
	sheet.addCell(Search);
    //创建要显示的具体内容������ʾ�ľ�������
	Label entry_num1 = new Label(0, 4, "送磨单号",center);
	sheet.addCell(entry_num1);
	Label mnum1 = new Label(1, 4, "物资编码",center);
	sheet.addCell(mnum1);
	Label pclass1 = new Label(2, 4, "大类",center);
	sheet.addCell(pclass1);
	Label bclass1 = new Label(3, 4, "小类",center);
	sheet.addCell(bclass1);
	Label name1= new Label(4, 4, "名称",center);
	sheet.addCell(name1);
	Label company1 = new Label(5, 4, "厂商",center);
	sheet.addCell(company1);
	Label order_num1 = new Label(6, 4, "订货号",center);
	sheet.addCell(order_num1);
	Label in_count1= new Label(7, 4, "送磨",center);
	sheet.addCell(in_count1);
	Label in_time1 = new Label(8, 4, "送磨时间",center);
	sheet.addCell(in_time1);
	Label gc_count_in1 = new Label(9, 4, "返库数量",center);
	sheet.addCell(gc_count_in1);
	Label hq1 = new Label(10, 4, "反库时间",center);
	sheet.addCell(hq1);
	Label location1 = new Label(11, 4, "损耗数量",center);
	sheet.addCell(location1);
	Label location2 = new Label(12, 4, "磨刀单位",center);
	sheet.addCell(location2);
	Label location3 = new Label(13, 4, "");
	sheet.addCell(location3);
	int nRow=5;
	for(int i=0;i<pcArray.size();i++){
		String grd_num="";
		String mnum="";
		String pclass="";
		String bclass="";
		String name="";
		String company="";
		String order_num="";
		String s_count="";
		String s_time="";
		String r_count="";
		String r_time="";
		String l_count="";
		String grd_department="";
		if(((HashMap)pcArray.get(i)).get("grd_num")!=null){
			grd_num=((HashMap)pcArray.get(i)).get("grd_num").toString();
		}
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
		if(((HashMap)pcArray.get(i)).get("company")!=null){
			company=((HashMap)pcArray.get(i)).get("company").toString();
		}
		if(((HashMap)pcArray.get(i)).get("order_num")!=null){
			order_num=((HashMap)pcArray.get(i)).get("order_num").toString();
		}
		if(((HashMap)pcArray.get(i)).get("s_count")!=null){
			s_count=((HashMap)pcArray.get(i)).get("s_count").toString();
		}else{
			s_count = "0";
		}
		int scount = Integer.parseInt(s_count);
		if(((HashMap)pcArray.get(i)).get("s_time")!=null){
			s_time=((HashMap)pcArray.get(i)).get("s_time").toString();
		}
		if(((HashMap)pcArray.get(i)).get("r_count")!=null){
			r_count=((HashMap)pcArray.get(i)).get("r_count").toString();
		}else{
			r_count = "0";
		}
		int rcount = Integer.parseInt(r_count);
		if(((HashMap)pcArray.get(i)).get("r_time")!=null){
			r_time=((HashMap)pcArray.get(i)).get("r_time").toString();
		}
		if(((HashMap)pcArray.get(i)).get("l_count")!=null){
			l_count=((HashMap)pcArray.get(i)).get("l_count").toString();
		}else{
			l_count = "0";
		}
		int lcount = Integer.parseInt(l_count);
		if(((HashMap)pcArray.get(i)).get("grd_department")!=null){
			grd_department=((HashMap)pcArray.get(i)).get("grd_department").toString();
		}
		
		sheet.addCell(new Label(0,nRow,grd_num,center));
		sheet.addCell(new Label(1,nRow,mnum,center));
		sheet.addCell(new Label(2,nRow,pclass,center));
		sheet.addCell(new Label(3,nRow,bclass,center));
		sheet.addCell(new Label(4,nRow,name,center));
		sheet.addCell(new Label(5,nRow,company,center));
		sheet.addCell(new Label(6,nRow,order_num,center));
		sheet.addCell(new Number(7,nRow,scount,center));
		sheet.addCell(new Label(8,nRow,s_time,center));
		sheet.addCell(new Number(9,nRow,rcount,center));
		sheet.addCell(new Label(10,nRow,r_time,center));
		sheet.addCell(new Number(11,nRow,lcount,center));
		sheet.addCell(new Label(12,nRow,grd_department,center));
		sheet.addCell(new Label(13,nRow,""));
		nRow++;
	}
	sheet.setColumnView(0, 15);
	sheet.setColumnView(1, 20);
	sheet.setColumnView(2, 12); 
	sheet.setColumnView(3, 16); 
	sheet.setColumnView(4, 22); 
	sheet.setColumnView(5, 22); 
	sheet.setColumnView(6, 15); 
	sheet.setColumnView(7, 10); 
	sheet.setColumnView(8, 15);
	sheet.setColumnView(9, 10);
	sheet.setColumnView(10, 15);
	sheet.setColumnView(11, 10);
	sheet.setColumnView(12, 15);
	workbook.write();
	workbook.close();
	os.close();
}catch (SQLException e) {
// TODO 自动生成 catch 块
	e.printStackTrace();
}
}
	
	public void StoreStatModC5Ex(HashMap hashMap, OutputStream os)
	throws WriteException, IOException {
// TODO 自动生成方法存根
System.out.println("filter="+(String)hashMap.get("filter"));
String filter=(String)hashMap.get("filter");
String order=(String)hashMap.get("order");
String result=(String)hashMap.get("result");
String sql="";
sql="select * from c_br_record where "+filter+" order by "+order+"";
Logger.debug(sql);
final ArrayList pcArray=new ArrayList();
try{
	DbUtil.execute(sql,new IResultSetProcessor() {
		public void process(ResultSet rs) throws SQLException {	
			try{
				int j=0;
				while(rs.next()){
					HashMap paHashMap=new HashMap();
					paHashMap.put("apply_num", rs.getString("apply_num"));
					paHashMap.put("mnum", rs.getString("mnum"));
					paHashMap.put("pclass", rs.getString("pclass"));
					paHashMap.put("bclass", rs.getString("bclass"));
					paHashMap.put("name", rs.getString("name"));
					paHashMap.put("company", rs.getString("company"));
					paHashMap.put("order_num", rs.getString("order_num"));
					paHashMap.put("worker_num", rs.getString("worker_num"));
					paHashMap.put("realname", rs.getString("realname"));
					paHashMap.put("group_num", rs.getString("group_num"));
					paHashMap.put("count", rs.getString("count"));
					if(rs.getString("state").equals("5")){
						paHashMap.put("status", "刀具归还");}
					else if (rs.getString("state").equals("4")) {
						paHashMap.put("status", "刀具借用");}
					else if (rs.getString("state").equals("1")) {
						paHashMap.put("status", "正常报损");}
					else if (rs.getString("state").equals("2")) {
						paHashMap.put("status", "非正常报损");}
					else if (rs.getString("state").equals("3")) {
						paHashMap.put("status", "刀具报丢");}
					paHashMap.put("time", rs.getDate("time"));
					pcArray.add(j,paHashMap);
					j++;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}				
	});	
//	读取导出日期
	Calendar c = Calendar.getInstance();
	final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
	String CurDate = curDate.toString();
    //创建工作薄��������
	WritableWorkbook workbook = Workbook.createWorkbook(os);
	//创建工作表�����µ�
	WritableSheet sheet = workbook.createSheet("First Sheet", 0);
//	标题存放格式设定
	WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
	WritableCellFormat seat = new WritableCellFormat(place);
//	水平居中
	seat.setAlignment(jxl.format.Alignment.CENTRE);
//	垂直居中
	seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//	内容存放格式设定
	WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
	WritableCellFormat center = new WritableCellFormat(Place);
	WritableCellFormat wordsize = new WritableCellFormat(Place);
	//水平居中
	center.setAlignment(jxl.format.Alignment.CENTRE);
//	创建标题，导出时间及查询条件
	sheet.mergeCells(0, 0, 12, 0);
	Label fname = new Label(0, 0, "标题：刀具借还历史记录",seat);
	sheet.addCell(fname);
	sheet.mergeCells(0, 1, 12, 1);
	Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
	sheet.addCell(ftime);
	sheet.mergeCells(0, 2, 12, 2);
	Label Search = new Label(0, 2, "查询条件："+result,wordsize);
	sheet.addCell(Search);
    //创建要显示的具体内容������ʾ�ľ�������
	Label entry_num1 = new Label(0, 4, "申请单号",center);
	sheet.addCell(entry_num1);
	Label mnum1 = new Label(1, 4, "物资编码",center);
	sheet.addCell(mnum1);
	Label pclass1 = new Label(2, 4, "大类",center);
	sheet.addCell(pclass1);
	Label bclass1 = new Label(3, 4, "小类",center);
	sheet.addCell(bclass1);
	Label name1= new Label(4, 4, "名称",center);
	sheet.addCell(name1);
	Label company1 = new Label(5, 4, "厂商",center);
	sheet.addCell(company1);
	Label order_num1 = new Label(6, 4, "订货号",center);
	sheet.addCell(order_num1);
	Label in_count1= new Label(7, 4, "工人工号",center);
	sheet.addCell(in_count1);
	Label in_count2= new Label(8, 4, "工人姓名",center);
	sheet.addCell(in_count2);
	Label in_count3= new Label(9, 4, "工人组别",center);
	sheet.addCell(in_count3);
	Label in_time1 = new Label(10, 4, "数量",center);
	sheet.addCell(in_time1);
	Label gc_count_in1 = new Label(11, 4, "记录类型",center);
	sheet.addCell(gc_count_in1);
	Label hq1 = new Label(12, 4, "记录时间",center);
	sheet.addCell(hq1);
	Label hq2 = new Label(13, 4, "");
	sheet.addCell(hq2);
	int nRow=5;
	for(int i=0;i<pcArray.size();i++){
		String apply_num="";
		String mnum="";
		String pclass="";
		String bclass="";
		String name="";
		String company="";
		String order_num="";
		String worker_num="";
		String realname="";
		String group_num="";
		String count="";
		String status="";
		String time="";
		if(((HashMap)pcArray.get(i)).get("apply_num")!=null){
			apply_num=((HashMap)pcArray.get(i)).get("apply_num").toString();
		}
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
		if(((HashMap)pcArray.get(i)).get("company")!=null){
			company=((HashMap)pcArray.get(i)).get("company").toString();
		}
		if(((HashMap)pcArray.get(i)).get("order_num")!=null){
			order_num=((HashMap)pcArray.get(i)).get("order_num").toString();
		}
		if(((HashMap)pcArray.get(i)).get("worker_num")!=null){
			worker_num=((HashMap)pcArray.get(i)).get("worker_num").toString();
		}
		if(((HashMap)pcArray.get(i)).get("realname")!=null){
			realname=((HashMap)pcArray.get(i)).get("realname").toString();
		}
		if(((HashMap)pcArray.get(i)).get("group_num")!=null){
			group_num=((HashMap)pcArray.get(i)).get("group_num").toString();
		}
		if(((HashMap)pcArray.get(i)).get("count")!=null){
			count=((HashMap)pcArray.get(i)).get("count").toString();
		}else{
			count = "0";
		}
		int count1 = Integer.parseInt(count);
		if(((HashMap)pcArray.get(i)).get("status")!=null){
			status=((HashMap)pcArray.get(i)).get("status").toString();
		}
		if(((HashMap)pcArray.get(i)).get("time")!=null){
			time=((HashMap)pcArray.get(i)).get("time").toString();
		}
		
		sheet.addCell(new Label(0,nRow,apply_num,center));
		sheet.addCell(new Label(1,nRow,mnum,center));
		sheet.addCell(new Label(2,nRow,pclass,center));
		sheet.addCell(new Label(3,nRow,bclass,center));
		sheet.addCell(new Label(4,nRow,name,center));
		sheet.addCell(new Label(5,nRow,company,center));
		sheet.addCell(new Label(6,nRow,order_num,center));
		sheet.addCell(new Label(7,nRow,worker_num,center));
		sheet.addCell(new Label(8,nRow,realname,center));
		sheet.addCell(new Label(9,nRow,group_num,center));
		sheet.addCell(new Number(10,nRow,count1,center));
		sheet.addCell(new Label(11,nRow,status,center));
		sheet.addCell(new Label(12,nRow,time,center));
		sheet.addCell(new Label(13,nRow,""));
		nRow++;
	}
	sheet.setColumnView(0, 15);
	sheet.setColumnView(1, 20);
	sheet.setColumnView(2, 12); 
	sheet.setColumnView(3, 16); 
	sheet.setColumnView(4, 22); 
	sheet.setColumnView(5, 22); 
	sheet.setColumnView(6, 15); 
	sheet.setColumnView(7, 15); 
	sheet.setColumnView(8, 15);
	sheet.setColumnView(9, 10);
	sheet.setColumnView(10, 10);
	sheet.setColumnView(11, 10);
	sheet.setColumnView(12, 15);
	workbook.write();
	workbook.close();
	os.close();
}catch (SQLException e) {
// TODO 自动生成 catch 块
	e.printStackTrace();
}
}
	
	
	public void StoreStatModC6Ex(HashMap hashMap, OutputStream os)
	throws WriteException, IOException {
// TODO 自动生成方法存根
System.out.println("filter="+(String)hashMap.get("filter"));
String filter=(String)hashMap.get("filter");
String order=(String)hashMap.get("order");
String result=(String)hashMap.get("result");
String sql="";
sql="select * from c_base where "+filter+" order by "+order+"";
Logger.debug(sql);
final ArrayList pcArray=new ArrayList();
try{
	DbUtil.execute(sql,new IResultSetProcessor() {
		public void process(ResultSet rs) throws SQLException {	
			try{
				int j=0;
				while(rs.next()){
					HashMap paHashMap=new HashMap();
					paHashMap.put("mnum", rs.getString("mnum"));
					paHashMap.put("pclass", rs.getString("pclass"));
					paHashMap.put("bclass", rs.getString("bclass"));
					paHashMap.put("name", rs.getString("name"));
					paHashMap.put("company", rs.getString("company"));
					paHashMap.put("order_num", rs.getString("order_num"));
					paHashMap.put("use_freq", rs.getString("use_freq"));
					paHashMap.put("hq", rs.getString("hq"));
					paHashMap.put("location", rs.getString("location"));
					pcArray.add(j,paHashMap);
					j++;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}				
	});	
//	读取导出日期
	Calendar c = Calendar.getInstance();
	final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
	String CurDate = curDate.toString();
    //创建工作薄��������
	WritableWorkbook workbook = Workbook.createWorkbook(os);
	//创建工作表�����µ�
	WritableSheet sheet = workbook.createSheet("First Sheet", 0);
//	标题存放格式设定
	WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
	WritableCellFormat seat = new WritableCellFormat(place);
//	水平居中
	seat.setAlignment(jxl.format.Alignment.CENTRE);
//	垂直居中
	seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	
//	内容存放格式设定
	WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
	WritableCellFormat center = new WritableCellFormat(Place);
	WritableCellFormat wordsize = new WritableCellFormat(Place);
	//水平居中
	center.setAlignment(jxl.format.Alignment.CENTRE);
//	创建标题，导出时间及查询条件
	sheet.mergeCells(0, 0, 8, 0);
	Label fname = new Label(0, 0, "标题：刀具借用频率统计",seat);
	sheet.addCell(fname);
	sheet.mergeCells(0, 1, 8, 1);
	Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
	sheet.addCell(ftime);
	sheet.mergeCells(0, 2, 8, 2);
	Label Search = new Label(0, 2, "查询条件："+result,wordsize);
	sheet.addCell(Search);
    //创建要显示的具体内容������ʾ�ľ�������
	Label mnum1 = new Label(0, 4, "物资编码",center);
	sheet.addCell(mnum1);
	Label pclass1 = new Label(1, 4, "大类",center);
	sheet.addCell(pclass1);
	Label bclass1 = new Label(2, 4, "小类",center);
	sheet.addCell(bclass1);
	Label name1= new Label(3, 4, "名称",center);
	sheet.addCell(name1);
	Label company1 = new Label(4, 4, "厂商",center);
	sheet.addCell(company1);
	Label order_num1 = new Label(5, 4, "订货号",center);
	sheet.addCell(order_num1);
	Label in_count1= new Label(6, 4, "借用次数",center);
	sheet.addCell(in_count1);
	Label in_time1 = new Label(7, 4, "是否高质",center);
	sheet.addCell(in_time1);
	Label gc_count_in1 = new Label(8, 4, "库存地点",center);
	sheet.addCell(gc_count_in1);
	Label hq1 = new Label(9, 4, "");
	sheet.addCell(hq1);
	int nRow=5;
	for(int i=0;i<pcArray.size();i++){
		String mnum="";
		String pclass="";
		String bclass="";
		String name="";
		String company="";
		String order_num="";
		String use_freq="";
		String hq="";
		String location="";
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
		if(((HashMap)pcArray.get(i)).get("company")!=null){
			company=((HashMap)pcArray.get(i)).get("company").toString();
		}
		if(((HashMap)pcArray.get(i)).get("order_num")!=null){
			order_num=((HashMap)pcArray.get(i)).get("order_num").toString();
		}
		if(((HashMap)pcArray.get(i)).get("use_freq")!=null){
			use_freq=((HashMap)pcArray.get(i)).get("use_freq").toString();
		}else{
			use_freq = "0";
		}
		int usefreq = Integer.parseInt(use_freq);
		if(((HashMap)pcArray.get(i)).get("hq")!=null){
			hq=((HashMap)pcArray.get(i)).get("hq").toString();
		}
		if(((HashMap)pcArray.get(i)).get("location")!=null){
			location=((HashMap)pcArray.get(i)).get("location").toString();
		}
		/*if(((HashMap)pcArray.get(i)).get("hq")!=null){
			hq=((HashMap)pcArray.get(i)).get("hq").toString();
		}
		if(((HashMap)pcArray.get(i)).get("location")!=null){
			location=((HashMap)pcArray.get(i)).get("location").toString();
		}*/
		sheet.addCell(new Label(0,nRow,mnum,center));
		sheet.addCell(new Label(1,nRow,pclass,center));
		sheet.addCell(new Label(2,nRow,bclass,center));
		sheet.addCell(new Label(3,nRow,name,center));
		sheet.addCell(new Label(4,nRow,company,center));
		sheet.addCell(new Label(5,nRow,order_num,center));
		sheet.addCell(new Number(6,nRow,usefreq,center));
		sheet.addCell(new Label(7,nRow,hq,center));
		sheet.addCell(new Label(8,nRow,location,center));
		sheet.addCell(new Label(9,nRow,""));
		nRow++;
	}
	sheet.setColumnView(0, 20);
	sheet.setColumnView(1, 12); 
	sheet.setColumnView(2, 16); 
	sheet.setColumnView(3, 22); 
	sheet.setColumnView(4, 22); 
	sheet.setColumnView(5, 15); 
	sheet.setColumnView(6, 10); 
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
	
	
}
