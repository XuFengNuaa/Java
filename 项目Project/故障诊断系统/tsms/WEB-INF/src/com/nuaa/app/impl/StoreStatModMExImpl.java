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

import com.nuaa.app.StoreStatModMEx;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class StoreStatModMExImpl implements StoreStatModMEx {

	public void StoreStatModMType1Ex(HashMap hashMap, OutputStream os)
	throws WriteException, IOException {
// TODO 自动生成方法存根
System.out.println("filter="+(String)hashMap.get("filter"));
String filter=(String)hashMap.get("filter");
String order=(String)hashMap.get("order");
String result=(String)hashMap.get("result");
String sql="";
sql="select * from m_type_base where "+filter+" order by "+order+"";
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
					paHashMap.put("classname", rs.getString("classname"));
					paHashMap.put("name", rs.getString("name"));
					paHashMap.put("company", rs.getString("company"));
					paHashMap.put("order_num", rs.getString("order_num"));
					paHashMap.put("nm_count_in", rs.getString("nm_count_in"));
					paHashMap.put("um_count_in", rs.getString("um_count_in"));
					paHashMap.put("hq", rs.getString("hq"));
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
//	内容存放格式设定
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
	sheet.mergeCells(0, 0, 7, 0);
	Label fname = new Label(0, 0, "标题：量具当前库存统计",seat);
	sheet.addCell(fname);
	sheet.mergeCells(0, 1, 7, 1);
	Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
	sheet.addCell(ftime);
	sheet.mergeCells(0, 2, 7, 2);
	Label Search = new Label(0, 2, "查询条件："+result,wordsize);
	sheet.addCell(Search);
    //创建要显示的具体内容������ʾ�ľ�������
	Label mnum1 = new Label(0, 4, "物资编码",center);
	sheet.addCell(mnum1);
	Label pclass1 = new Label(1, 4, "类别",center);
	sheet.addCell(pclass1);
	Label name1= new Label(2, 4, "名称",center);
	sheet.addCell(name1);
	Label company1 = new Label(3, 4, "厂商",center);
	sheet.addCell(company1);
	Label order_num1 = new Label(4, 4, "订货号",center);
	sheet.addCell(order_num1);
	Label nc_count_in1= new Label(5, 4, "新量具在库量",center);
	sheet.addCell(nc_count_in1);
	Label uc_count_in1 = new Label(6, 4, "旧量具在库量",center);
	sheet.addCell(uc_count_in1);
	Label hq1 = new Label(7, 4, "是否高质",center);
	sheet.addCell(hq1);
	Label location2 = new Label(8, 4, "");
	sheet.addCell(location2);
	int nRow=5;
	for(int i=0;i<pcArray.size();i++){
		String mnum="";
		String classname="";
		String name="";
		String company="";
		String order_num="";
		String nm_count_in="";
		String um_count_in="";
		String hq="";
		if(((HashMap)pcArray.get(i)).get("mnum")!=null){
			mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
		}
		
		if(((HashMap)pcArray.get(i)).get("classname")!=null){
			classname=((HashMap)pcArray.get(i)).get("classname").toString();
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
		if(((HashMap)pcArray.get(i)).get("nm_count_in")!=null){
			nm_count_in=((HashMap)pcArray.get(i)).get("nm_count_in").toString();
		}else{
			nm_count_in = "0";
		}
		int nmcount_in = Integer.parseInt(nm_count_in);
		if(((HashMap)pcArray.get(i)).get("um_count_in")!=null){
			um_count_in=((HashMap)pcArray.get(i)).get("um_count_in").toString();
		}else{
			um_count_in = "0";
		}
		int umcount_in = Integer.parseInt(um_count_in);
		if(((HashMap)pcArray.get(i)).get("hq")!=null){
			hq=((HashMap)pcArray.get(i)).get("hq").toString();
		}
		sheet.addCell(new Label(0,nRow,mnum,center));
		sheet.addCell(new Label(1,nRow,classname,center));
		sheet.addCell(new Label(2,nRow,name,center));
		sheet.addCell(new Label(3,nRow,company,center));
		sheet.addCell(new Label(4,nRow,order_num,center));
		sheet.addCell(new Number(5,nRow,nmcount_in,center));
		sheet.addCell(new Number(6,nRow,umcount_in,center));
		sheet.addCell(new Label(7,nRow,hq,center));
		sheet.addCell(new Label(8,nRow,"",center));
		nRow++;
	}
	sheet.setColumnView(0, 20);
	sheet.setColumnView(1, 16);
	sheet.setColumnView(2, 22); 
	sheet.setColumnView(3, 22); 
	sheet.setColumnView(4, 15); 
	sheet.setColumnView(5, 15); 
	sheet.setColumnView(6, 15); 
	sheet.setColumnView(7, 10); 
	workbook.write();
	workbook.close();
	os.close();
}catch (SQLException e) {
// TODO 自动生成 catch 块
	e.printStackTrace();
}
}


public void StoreStatModMType2Ex(HashMap hashMap, OutputStream os)
throws WriteException, IOException {
//TODO 自动生成方法存根
System.out.println("filter="+(String)hashMap.get("filter"));
String filter=(String)hashMap.get("filter");
String order=(String)hashMap.get("order");
String result=(String)hashMap.get("result");
String sql="";
sql="select * from m_type_base where "+filter+" order by "+order+"";
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
			paHashMap.put("classname", rs.getString("classname"));
			paHashMap.put("name", rs.getString("name"));
			paHashMap.put("company", rs.getString("company"));
			paHashMap.put("order_num", rs.getString("order_num"));
			paHashMap.put("use_freq", rs.getString("use_freq"));
			paHashMap.put("spec", rs.getString("spec"));
			paHashMap.put("diviv", rs.getString("diviv"));
			paHashMap.put("hq", rs.getString("hq"));
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
//内容存放格式设定
WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
WritableCellFormat seat = new WritableCellFormat(place);
//水平居中
seat.setAlignment(jxl.format.Alignment.CENTRE);
//垂直居中
seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//内容存放格式设定
WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
WritableCellFormat center = new WritableCellFormat(Place);
WritableCellFormat wordsize = new WritableCellFormat(Place);
//水平居中
center.setAlignment(jxl.format.Alignment.CENTRE);
//创建标题，导出时间及查询条件
sheet.mergeCells(0, 0, 8, 0);
Label fname = new Label(0, 0, "标题：量具借用频率统计",seat);
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
Label pclass1 = new Label(1, 4, "类别",center);
sheet.addCell(pclass1);
Label name1= new Label(2, 4, "名称",center);
sheet.addCell(name1);
Label company1 = new Label(3, 4, "厂商",center);
sheet.addCell(company1);
Label order_num1 = new Label(4, 4, "订货号",center);
sheet.addCell(order_num1);
Label in_count1= new Label(5, 4, "借用次数",center);
sheet.addCell(in_count1);
Label in_count2= new Label(6, 4, "规格",center);
sheet.addCell(in_count2);
Label in_count3= new Label(7, 4, "分度值",center);
sheet.addCell(in_count3);
Label in_time1 = new Label(8, 4, "是否高质",center);
sheet.addCell(in_time1);
Label hq1 = new Label(9, 4, "");
sheet.addCell(hq1);
int nRow=5;
for(int i=0;i<pcArray.size();i++){
String mnum="";
String classname="";
String name="";
String company="";
String order_num="";
String use_freq="";
String spec="";
String diviv="";
String hq="";
if(((HashMap)pcArray.get(i)).get("mnum")!=null){
	mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
}

if(((HashMap)pcArray.get(i)).get("classname")!=null){
	classname=((HashMap)pcArray.get(i)).get("classname").toString();
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
if(((HashMap)pcArray.get(i)).get("spec")!=null){
	spec=((HashMap)pcArray.get(i)).get("spec").toString();
}if(((HashMap)pcArray.get(i)).get("diviv")!=null){
	diviv=((HashMap)pcArray.get(i)).get("diviv").toString();
}
if(((HashMap)pcArray.get(i)).get("hq")!=null){
	hq=((HashMap)pcArray.get(i)).get("hq").toString();
}

/*if(((HashMap)pcArray.get(i)).get("hq")!=null){
	hq=((HashMap)pcArray.get(i)).get("hq").toString();
}
if(((HashMap)pcArray.get(i)).get("location")!=null){
	location=((HashMap)pcArray.get(i)).get("location").toString();
}*/
sheet.addCell(new Label(0,nRow,mnum,center));
sheet.addCell(new Label(1,nRow,classname,center));
sheet.addCell(new Label(2,nRow,name,center));
sheet.addCell(new Label(3,nRow,company,center));
sheet.addCell(new Label(4,nRow,order_num,center));
sheet.addCell(new Number(5,nRow,usefreq,center));
sheet.addCell(new Label(6,nRow,spec,center));
sheet.addCell(new Label(7,nRow,diviv,center));
sheet.addCell(new Label(8,nRow,hq,center));
sheet.addCell(new Label(9,nRow,"",center));
nRow++;
}
sheet.setColumnView(0, 20);
sheet.setColumnView(1, 16);
sheet.setColumnView(2, 22); 
sheet.setColumnView(3, 22); 
sheet.setColumnView(4, 15); 
sheet.setColumnView(5, 10); 
sheet.setColumnView(6, 10); 
sheet.setColumnView(7, 10); 
sheet.setColumnView(8, 10);
workbook.write();
workbook.close();
os.close();
}catch (SQLException e) {
//TODO 自动生成 catch 块
e.printStackTrace();
}
}



public void StoreStatModMIndi1Ex(HashMap hashMap, OutputStream os)
throws WriteException, IOException {
//TODO 自动生成方法存根
System.out.println("filter="+(String)hashMap.get("filter"));
String filter=(String)hashMap.get("filter");
String order=(String)hashMap.get("order");
String result=(String)hashMap.get("result");
String sql="";
sql="select * from m_entry where "+filter+" order by "+order+"";
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
			paHashMap.put("indi_num", rs.getString("indi_num"));
			paHashMap.put("mnum", rs.getString("mnum"));
			paHashMap.put("classname", rs.getString("classname"));
			paHashMap.put("name", rs.getString("name"));
			paHashMap.put("company", rs.getString("company"));
			paHashMap.put("in_time", rs.getDate("in_time"));
			paHashMap.put("newstatus", rs.getString("newstatus"));
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
//内容存放格式设定
WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
WritableCellFormat seat = new WritableCellFormat(place);
//水平居中
seat.setAlignment(jxl.format.Alignment.CENTRE);
//垂直居中
seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//内容存放格式设定
WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
WritableCellFormat center = new WritableCellFormat(Place);
WritableCellFormat wordsize = new WritableCellFormat(Place);
//水平居中
center.setAlignment(jxl.format.Alignment.CENTRE);
//创建标题，导出时间及查询条件
sheet.mergeCells(0, 0, 8, 0);
Label fname = new Label(0, 0, "标题：量具入库历史记录",seat);
sheet.addCell(fname);
sheet.mergeCells(0, 1, 8, 1);
Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
sheet.addCell(ftime);
sheet.mergeCells(0, 2, 8, 2);
Label Search = new Label(0, 2, "查询条件："+result,wordsize);
sheet.addCell(Search);
//创建要显示的具体内容������ʾ�ľ�������
Label entry_num1 = new Label(0, 4, "入库单号",center);
sheet.addCell(entry_num1);
Label entry_num2 = new Label(1, 4, "个体编码",center);
sheet.addCell(entry_num2);
Label mnum1 = new Label(2, 4, "物资编码",center);
sheet.addCell(mnum1);
Label bclass1 = new Label(3, 4, "类别",center);
sheet.addCell(bclass1);
Label name1= new Label(4, 4, "名称",center);
sheet.addCell(name1);
Label company1 = new Label(5, 4, "厂商",center);
sheet.addCell(company1);
Label in_time1 = new Label(6, 4, "入库日期",center);
sheet.addCell(in_time1);
Label gc_count_in1 = new Label(7, 4, "新旧状态",center);
sheet.addCell(gc_count_in1);
Label gc_count_in2 = new Label(8, 4, "库存地点",center);
sheet.addCell(gc_count_in2);
Label hq1 = new Label(9, 4, "");
sheet.addCell(hq1);
/*Label location1 = new Label(11, 0, "库存地点");
sheet.addCell(location1);*/
int nRow=5;
for(int i=0;i<pcArray.size();i++){
String entry_num="";
String indi_num="";
String mnum="";
String classname="";
String name="";
String company="";
String in_time="";
String newstatus="";
String location="";
if(((HashMap)pcArray.get(i)).get("entry_num")!=null){
	entry_num=((HashMap)pcArray.get(i)).get("entry_num").toString();
}
if(((HashMap)pcArray.get(i)).get("indi_num")!=null){
	indi_num=((HashMap)pcArray.get(i)).get("indi_num").toString();
}
if(((HashMap)pcArray.get(i)).get("mnum")!=null){
	mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
}
if(((HashMap)pcArray.get(i)).get("classname")!=null){
	classname=((HashMap)pcArray.get(i)).get("classname").toString();
}
if(((HashMap)pcArray.get(i)).get("name")!=null){
	name=((HashMap)pcArray.get(i)).get("name").toString();
}
if(((HashMap)pcArray.get(i)).get("company")!=null){
	company=((HashMap)pcArray.get(i)).get("company").toString();
}
if(((HashMap)pcArray.get(i)).get("in_time")!=null){
	in_time=((HashMap)pcArray.get(i)).get("in_time").toString();
}
if(((HashMap)pcArray.get(i)).get("newstatus")!=null){
	newstatus=((HashMap)pcArray.get(i)).get("newstatus").toString();
}

if(((HashMap)pcArray.get(i)).get("location")!=null){
	location=((HashMap)pcArray.get(i)).get("location").toString();
}
sheet.addCell(new Label(0,nRow,entry_num,center));
sheet.addCell(new Label(1,nRow,indi_num,center));
sheet.addCell(new Label(2,nRow,mnum,center));
sheet.addCell(new Label(3,nRow,classname,center));
sheet.addCell(new Label(4,nRow,name,center));
sheet.addCell(new Label(5,nRow,company,center));
sheet.addCell(new Label(6,nRow,in_time,center));
sheet.addCell(new Label(7,nRow,newstatus,center));
sheet.addCell(new Label(8,nRow,location,center));
sheet.addCell(new Label(9,nRow,"",center));
nRow++;
}
sheet.setColumnView(0, 20);
sheet.setColumnView(1, 20);
sheet.setColumnView(2, 20); 
sheet.setColumnView(3, 16); 
sheet.setColumnView(4, 22); 
sheet.setColumnView(5, 22); 
sheet.setColumnView(6, 15); 
sheet.setColumnView(7, 10); 
sheet.setColumnView(8, 15);
workbook.write();
workbook.close();
os.close();
}catch (SQLException e) {
//TODO 自动生成 catch 块
e.printStackTrace();
}
}

public void StoreStatModMIndi2Ex(HashMap hashMap, OutputStream os)
throws WriteException, IOException {
//TODO 自动生成方法存根
System.out.println("filter="+(String)hashMap.get("filter"));
String filter=(String)hashMap.get("filter");
String order=(String)hashMap.get("order");
String result=(String)hashMap.get("result");
String sql="";
sql="select * from m_br_now where "+filter+" order by "+order+"";
Logger.debug(sql);
final ArrayList pcArray=new ArrayList();
try{
DbUtil.execute(sql,new IResultSetProcessor() {
public void process(ResultSet rs) throws SQLException {	
	try{
		int j=0;
		while(rs.next()){
			HashMap paHashMap=new HashMap();
			paHashMap.put("indi_num", rs.getString("indi_num"));
			paHashMap.put("mnum", rs.getString("mnum"));
			paHashMap.put("classname", rs.getString("classname"));
			paHashMap.put("name", rs.getString("name"));
			paHashMap.put("company", rs.getString("company"));
			paHashMap.put("worker_num", rs.getString("worker_num"));
			paHashMap.put("realname", rs.getString("realname"));
			paHashMap.put("group_num", rs.getString("group_num"));
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
//内容存放格式设定
WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
WritableCellFormat seat = new WritableCellFormat(place);
//水平居中
seat.setAlignment(jxl.format.Alignment.CENTRE);
//垂直居中
seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//内容存放格式设定
WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
WritableCellFormat center = new WritableCellFormat(Place);
WritableCellFormat wordsize = new WritableCellFormat(Place);
//水平居中
center.setAlignment(jxl.format.Alignment.CENTRE);
//创建标题，导出时间及查询条件
sheet.mergeCells(0, 0, 7, 0);
Label fname = new Label(0, 0, "标题：量具当前借出记录",seat);
sheet.addCell(fname);
sheet.mergeCells(0, 1, 7, 1);
Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
sheet.addCell(ftime);
sheet.mergeCells(0, 2, 7, 2);
Label Search = new Label(0, 2, "查询条件："+result,wordsize);
sheet.addCell(Search);
//创建要显示的具体内容������ʾ�ľ�������
Label mnum1 = new Label(0, 4, "个体编码",center);
sheet.addCell(mnum1);
Label pclass1 = new Label(1, 4, "物资编码",center);
sheet.addCell(pclass1);
Label bclass1 = new Label(2, 4, "类别",center);
sheet.addCell(bclass1);
Label name1= new Label(3, 4, "名称",center);
sheet.addCell(name1);
Label company1 = new Label(4, 4, "厂商",center);
sheet.addCell(company1);
Label order_num1 = new Label(5, 4, "工人工号",center);
sheet.addCell(order_num1);
Label order_num2 = new Label(6, 4, "工人姓名",center);
sheet.addCell(order_num2);
Label order_num3 = new Label(7, 4, "工人组别",center);
sheet.addCell(order_num3);
Label order_num11 = new Label(8, 4, "");
sheet.addCell(order_num11);
int nRow=5;
for(int i=0;i<pcArray.size();i++){
String indi_num="";
String mnum="";
String classname="";
String name="";
String company="";
String worker_num="";
String realname="";
String group_num="";

if(((HashMap)pcArray.get(i)).get("indi_num")!=null){
	indi_num=((HashMap)pcArray.get(i)).get("indi_num").toString();
}
if(((HashMap)pcArray.get(i)).get("mnum")!=null){
	mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
}
if(((HashMap)pcArray.get(i)).get("classname")!=null){
	classname=((HashMap)pcArray.get(i)).get("classname").toString();
}
if(((HashMap)pcArray.get(i)).get("name")!=null){
	name=((HashMap)pcArray.get(i)).get("name").toString();
}
if(((HashMap)pcArray.get(i)).get("company")!=null){
	company=((HashMap)pcArray.get(i)).get("company").toString();
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

sheet.addCell(new Label(0,nRow,indi_num,center));
sheet.addCell(new Label(1,nRow,mnum,center));
sheet.addCell(new Label(2,nRow,classname,center));
sheet.addCell(new Label(3,nRow,name,center));
sheet.addCell(new Label(4,nRow,company,center));
sheet.addCell(new Label(5,nRow,worker_num,center));
sheet.addCell(new Label(6,nRow,realname,center));
sheet.addCell(new Label(7,nRow,group_num,center));
sheet.addCell(new Label(8,nRow,"",center));
nRow++;
}
sheet.setColumnView(0, 20);
sheet.setColumnView(1, 20);
sheet.setColumnView(2, 16); 
sheet.setColumnView(3, 22); 
sheet.setColumnView(4, 22); 
sheet.setColumnView(5, 20); 
sheet.setColumnView(6, 15);
sheet.setColumnView(7, 10);
workbook.write();
workbook.close();
os.close();
}catch (SQLException e) {
//TODO 自动生成 catch 块
e.printStackTrace();
}
}


public void StoreStatModMIndi3Ex(HashMap hashMap, OutputStream os)
throws WriteException, IOException {
//TODO 自动生成方法存根
System.out.println("filter="+(String)hashMap.get("filter"));
String filter=(String)hashMap.get("filter");
String order=(String)hashMap.get("order");
String result=(String)hashMap.get("result");
String sql="";
sql="select * from m_br_record where "+filter+" order by "+order+"";
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
			paHashMap.put("indi_num", rs.getString("indi_num"));
			paHashMap.put("mnum", rs.getString("mnum"));
			paHashMap.put("classname", rs.getString("classname"));
			paHashMap.put("name", rs.getString("name"));
			paHashMap.put("company", rs.getString("company"));
			paHashMap.put("worker_num", rs.getString("worker_num"));
			paHashMap.put("realname", rs.getString("realname"));
			paHashMap.put("group_num", rs.getString("group_num"));
			if(rs.getString("state").equals("5")){
				paHashMap.put("status", "量具归还");}
			else if (rs.getString("state").equals("4")) {
				paHashMap.put("status", "量具借用");}
			else if (rs.getString("state").equals("1")) {
				paHashMap.put("status", "正常报损");}
			else if (rs.getString("state").equals("2")) {
				paHashMap.put("status", "非正常报损");}
			else if (rs.getString("state").equals("3")) {
				paHashMap.put("status", "量具报丢");}
			paHashMap.put("time", rs.getDate("time"));
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
//内容存放格式设定
WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
WritableCellFormat seat = new WritableCellFormat(place);
//水平居中
seat.setAlignment(jxl.format.Alignment.CENTRE);
//垂直居中
seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//内容存放格式设定
WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
WritableCellFormat center = new WritableCellFormat(Place);
WritableCellFormat wordsize = new WritableCellFormat(Place);
//水平居中
center.setAlignment(jxl.format.Alignment.CENTRE);
//创建标题，导出时间及查询条件
sheet.mergeCells(0, 0, 10, 0);
Label fname = new Label(0, 0, "标题：量具借还历史记录",seat);
sheet.addCell(fname);
sheet.mergeCells(0, 1, 10, 1);
Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
sheet.addCell(ftime);
sheet.mergeCells(0, 2, 10, 2);
Label Search = new Label(0, 2, "查询条件："+result,wordsize);
sheet.addCell(Search);
//创建要显示的具体内容������ʾ�ľ�������
Label entry_num1 = new Label(0, 4, "申请单号",center);
sheet.addCell(entry_num1);
Label mnum1 = new Label(1, 4, "个体编码",center);
sheet.addCell(mnum1);
Label pclass1 = new Label(2, 4, "物资编码",center);
sheet.addCell(pclass1);
Label bclass1 = new Label(3, 4, "类别",center);
sheet.addCell(bclass1);
Label name1= new Label(4, 4, "名称",center);
sheet.addCell(name1);
Label company1 = new Label(5, 4, "厂商",center);
sheet.addCell(company1);
Label order_num1 = new Label(6, 4, "工人工号",center);
sheet.addCell(order_num1);
Label order_num2 = new Label(7, 4, "工人姓名",center);
sheet.addCell(order_num2);
Label order_num3 = new Label(8, 4, "工人组别",center);
sheet.addCell(order_num3);
Label in_count1= new Label(9, 4, "记录类型",center);
sheet.addCell(in_count1);
Label in_time1 = new Label(10, 4, "记录时间",center);
sheet.addCell(in_time1);
Label hq2 = new Label(11, 3, "");
sheet.addCell(hq2);
int nRow=5;
for(int i=0;i<pcArray.size();i++){
String apply_num="";
String indi_num="";
String mnum="";
String classname="";
String name="";
String company="";
String worker_num="";
String realname="";
String group_num="";
String status="";
String time="";
if(((HashMap)pcArray.get(i)).get("apply_num")!=null){
	apply_num=((HashMap)pcArray.get(i)).get("apply_num").toString();
}
if(((HashMap)pcArray.get(i)).get("indi_num")!=null){
	indi_num=((HashMap)pcArray.get(i)).get("indi_num").toString();
}
if(((HashMap)pcArray.get(i)).get("mnum")!=null){
	mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
}

if(((HashMap)pcArray.get(i)).get("classname")!=null){
	classname=((HashMap)pcArray.get(i)).get("classname").toString();
}
if(((HashMap)pcArray.get(i)).get("name")!=null){
	name=((HashMap)pcArray.get(i)).get("name").toString();
}
if(((HashMap)pcArray.get(i)).get("company")!=null){
	company=((HashMap)pcArray.get(i)).get("company").toString();
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
if(((HashMap)pcArray.get(i)).get("status")!=null){
	status=((HashMap)pcArray.get(i)).get("status").toString();
}
if(((HashMap)pcArray.get(i)).get("time")!=null){
	time=((HashMap)pcArray.get(i)).get("time").toString();
}

sheet.addCell(new Label(0,nRow,apply_num,center));
sheet.addCell(new Label(1,nRow,indi_num,center));
sheet.addCell(new Label(2,nRow,mnum,center));
sheet.addCell(new Label(3,nRow,classname,center));
sheet.addCell(new Label(4,nRow,name,center));
sheet.addCell(new Label(5,nRow,company,center));
sheet.addCell(new Label(6,nRow,worker_num,center));
sheet.addCell(new Label(7,nRow,realname,center));
sheet.addCell(new Label(8,nRow,group_num,center));
sheet.addCell(new Label(9,nRow,status,center));
sheet.addCell(new Label(10,nRow,time,center));
sheet.addCell(new Label(11,nRow,"",center));
nRow++;
}
sheet.setColumnView(0, 20);
sheet.setColumnView(1, 20);
sheet.setColumnView(2, 20); 
sheet.setColumnView(3, 16); 
sheet.setColumnView(4, 22); 
sheet.setColumnView(5, 22); 
sheet.setColumnView(6, 22); 
sheet.setColumnView(7, 15); 
sheet.setColumnView(8, 10); 
sheet.setColumnView(9, 15);
sheet.setColumnView(10, 15);
workbook.write();
workbook.close();
os.close();
}catch (SQLException e) {
//TODO 自动生成 catch 块
e.printStackTrace();
}
}


public void StoreStatModMIndi4Ex(HashMap hashMap, OutputStream os)
throws WriteException, IOException {
//TODO 自动生成方法存根
System.out.println("filter="+(String)hashMap.get("filter"));
String filter=(String)hashMap.get("filter");
String order=(String)hashMap.get("order");
String result=(String)hashMap.get("result");
String sql="";
sql="select * from m_test where "+filter+" order by "+order+"";
Logger.debug(sql);
final ArrayList pcArray=new ArrayList();
try{
DbUtil.execute(sql,new IResultSetProcessor() {
public void process(ResultSet rs) throws SQLException {	
	try{
		int j=0;
		while(rs.next()){
			HashMap paHashMap=new HashMap();
			paHashMap.put("test_num", rs.getString("test_num"));
			paHashMap.put("indi_num", rs.getString("indi_num"));
			paHashMap.put("mnum", rs.getString("mnum"));
			paHashMap.put("classname", rs.getString("classname"));
			paHashMap.put("name", rs.getString("name"));
			paHashMap.put("company", rs.getString("company"));
			paHashMap.put("stuff_num", rs.getString("stuff_num"));
			paHashMap.put("realname", rs.getString("realname"));
			paHashMap.put("group_num", rs.getString("group_num"));
			paHashMap.put("test_department", rs.getString("test_department"));
			paHashMap.put("s_time", rs.getDate("s_time"));
			paHashMap.put("r_time_date", rs.getDate("r_time_date"));
			paHashMap.put("r_status", rs.getString("r_status"));
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
//内容存放格式设定
WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
WritableCellFormat seat = new WritableCellFormat(place);
//水平居中
seat.setAlignment(jxl.format.Alignment.CENTRE);
//垂直居中
seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//内容存放格式设定
WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
WritableCellFormat center = new WritableCellFormat(Place);
WritableCellFormat wordsize = new WritableCellFormat(Place);
//水平居中
center.setAlignment(jxl.format.Alignment.CENTRE);
//创建标题，导出时间及查询条件
sheet.mergeCells(0, 0, 11, 0);
Label fname = new Label(0, 0, "标题：量具送检反库记录",seat);
sheet.addCell(fname);
sheet.mergeCells(0, 1, 11, 1);
Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
sheet.addCell(ftime);
sheet.mergeCells(0, 2, 11, 2);
Label Search = new Label(0, 2, "查询条件："+result,wordsize);
sheet.addCell(Search);
//创建要显示的具体内容������ʾ�ľ�������
Label entry_num1 = new Label(0, 4, "送检单号",center);
sheet.addCell(entry_num1);
Label mnum1 = new Label(1, 4, "个体编码",center);
sheet.addCell(mnum1);
Label pclass1 = new Label(2, 4, "物资编码",center);
sheet.addCell(pclass1);
Label bclass1 = new Label(3, 4, "类别",center);
sheet.addCell(bclass1);
Label name1= new Label(4, 4, "名称",center);
sheet.addCell(name1);
Label company1 = new Label(5, 4, "厂商",center);
sheet.addCell(company1);
Label order_num1 = new Label(6, 4, "送检人工号",center);
sheet.addCell(order_num1);
Label order_num2 = new Label(7, 4, "送检人姓名",center);
sheet.addCell(order_num2);
Label order_num3 = new Label(8, 4, "送检人组别",center);
sheet.addCell(order_num3);
Label in_count1= new Label(9, 4, "送检单位",center);
sheet.addCell(in_count1);
Label in_time1 = new Label(10, 4, "送检时间",center);
sheet.addCell(in_time1);
Label gc_count_in1 = new Label(11, 4, "返库时间",center);
sheet.addCell(gc_count_in1);
Label hq1 = new Label(12, 4, "反库状态",center);
sheet.addCell(hq1);
Label location1 = new Label(13, 4, "");
sheet.addCell(location1);
int nRow=5;
for(int i=0;i<pcArray.size();i++){
String test_num="";
String indi_num="";
String mnum="";
String classname="";
String name="";
String company="";
String stuff_num="";
String realname="";
String group_num="";
String test_department="";
String s_time="";
String r_time_date="";
String r_status="";
if(((HashMap)pcArray.get(i)).get("test_num")!=null){
	test_num=((HashMap)pcArray.get(i)).get("test_num").toString();
}
if(((HashMap)pcArray.get(i)).get("indi_num")!=null){
	indi_num=((HashMap)pcArray.get(i)).get("indi_num").toString();
}
if(((HashMap)pcArray.get(i)).get("mnum")!=null){
	mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
}
if(((HashMap)pcArray.get(i)).get("classname")!=null){
	classname=((HashMap)pcArray.get(i)).get("classname").toString();
}

if(((HashMap)pcArray.get(i)).get("name")!=null){
	name=((HashMap)pcArray.get(i)).get("name").toString();
}
if(((HashMap)pcArray.get(i)).get("company")!=null){
	company=((HashMap)pcArray.get(i)).get("company").toString();
}
if(((HashMap)pcArray.get(i)).get("stuff_num")!=null){
	stuff_num=((HashMap)pcArray.get(i)).get("stuff_num").toString();
}
if(((HashMap)pcArray.get(i)).get("realname")!=null){
	realname=((HashMap)pcArray.get(i)).get("realname").toString();
}
if(((HashMap)pcArray.get(i)).get("group_num")!=null){
	group_num=((HashMap)pcArray.get(i)).get("group_num").toString();
}
if(((HashMap)pcArray.get(i)).get("test_department")!=null){
	test_department=((HashMap)pcArray.get(i)).get("test_department").toString();
}
if(((HashMap)pcArray.get(i)).get("s_time")!=null){
	s_time=((HashMap)pcArray.get(i)).get("s_time").toString();
}
if(((HashMap)pcArray.get(i)).get("r_time_date")!=null){
	r_time_date=((HashMap)pcArray.get(i)).get("r_time_date").toString();
}
if(((HashMap)pcArray.get(i)).get("r_status")!=null){
	r_status=((HashMap)pcArray.get(i)).get("r_status").toString();
}


sheet.addCell(new Label(0,nRow,test_num,center));
sheet.addCell(new Label(1,nRow,indi_num,center));
sheet.addCell(new Label(2,nRow,mnum,center));
sheet.addCell(new Label(3,nRow,classname,center));
sheet.addCell(new Label(4,nRow,name,center));
sheet.addCell(new Label(5,nRow,company,center));
sheet.addCell(new Label(6,nRow,stuff_num,center));
sheet.addCell(new Label(7,nRow,realname,center));
sheet.addCell(new Label(8,nRow,group_num,center));
sheet.addCell(new Label(9,nRow,test_department,center));
sheet.addCell(new Label(10,nRow,s_time,center));
sheet.addCell(new Label(11,nRow,r_time_date,center));
sheet.addCell(new Label(12,nRow,r_status,center));
sheet.addCell(new Label(13,nRow,"",center));
nRow++;
}
sheet.setColumnView(0, 20);
sheet.setColumnView(1, 20);
sheet.setColumnView(2, 20); 
sheet.setColumnView(3, 16); 
sheet.setColumnView(4, 22); 
sheet.setColumnView(5, 22); 
sheet.setColumnView(6, 22); 
sheet.setColumnView(7, 15); 
sheet.setColumnView(8, 12);
sheet.setColumnView(9, 15); 
sheet.setColumnView(10, 15);
sheet.setColumnView(11, 15);
sheet.setColumnView(12, 10);
workbook.write();
workbook.close();
os.close();
}catch (SQLException e) {
//TODO 自动生成 catch 块
e.printStackTrace();
}
}
	
}
