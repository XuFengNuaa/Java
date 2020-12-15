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

import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;

public class Leader_needxls {
	
	
//	刀具需求导出
	 public void createExcelC(HashMap hashMap, OutputStream os) throws WriteException, IOException
	    {	
	    	String sql="";
	    	sql="select * from v_cut2need where trim(leader_approve)='未处理' and trim(gleader_approve)='批准'"; 
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
								paHashMap.put("count", rs.getString("count"));
								paHashMap.put("apply_time", rs.getDate("apply_time"));
								paHashMap.put("group_num", rs.getString("group_num"));
								paHashMap.put("drawing_num", rs.getString("drawing_num"));
								pcArray.add(j,paHashMap);
								j++;
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}				
				});	
//		    	读取导出日期
		    	Calendar c = Calendar.getInstance();
				final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
				String CurDate = curDate.toString();
	            //创建工作薄��������
		    	WritableWorkbook workbook = Workbook.createWorkbook(os);
		    	//创建工作表�����µ�
				WritableSheet sheet = workbook.createSheet("刀具需求领导审批", 0);
//				标题存放格式设定
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
				Label fname = new Label(0, 0, "标题：刀具需求领导审批",seat);
				sheet.addCell(fname);
				
				sheet.mergeCells(0, 1, 8, 1);
				Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
				sheet.addCell(ftime);
				
				sheet.mergeCells(0, 2, 8, 2);
				Label Search = new Label(0, 2, "查询条件：未审批项",wordsize);
				sheet.addCell(Search);
				
				sheet.mergeCells(0, 3, 8, 3);
				Label People = new Label(0, 3, "审批人："+(String)hashMap.get("leadername"),wordsize);
				sheet.addCell(People);

				//				创建要显示的具体内容的题头
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
				
				Label Drawing_num = new Label(5, 5, "产品令号",center);
				sheet.addCell(Drawing_num);
				
				Label Count = new Label(6, 5, "需求数量",center);
				sheet.addCell(Count);
				
				Label Group = new Label(7, 5, "需求组别",center);
				sheet.addCell(Group);
				
				Label Time = new Label(8, 5, "申请时间",center);
				sheet.addCell(Time);
				int nRow=6;
				int k = 1;
		    	for(int i=0;i<pcArray.size();i++){
					String mnum="";
					String pclass="";
					String bclass="";
					String name="";
					String count="";
					String apply_time="";
					String group_num="";
					String drawing_num = "";
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
					//数量为空时置零并将其转化为整形
					if(((HashMap)pcArray.get(i)).get("count")!=null){
						count=((HashMap)pcArray.get(i)).get("count").toString();
					}else{
						count = "0";
					}
					int C_count=Integer.parseInt(count);
					if(((HashMap)pcArray.get(i)).get("apply_time")!=null){
						apply_time=((HashMap)pcArray.get(i)).get("apply_time").toString();
					}
					if(((HashMap)pcArray.get(i)).get("group_num")!=null){
						group_num=((HashMap)pcArray.get(i)).get("group_num").toString();
					}
					if(((HashMap)pcArray.get(i)).get("drawing_num")!=null){
						drawing_num=((HashMap)pcArray.get(i)).get("drawing_num").toString();
					}
					sheet.addCell(new Number(0,nRow,k,center));
					sheet.addCell(new Label(1,nRow,mnum,center));
					sheet.addCell(new Label(2,nRow,pclass,center));
					sheet.addCell(new Label(3,nRow,bclass,center));
					sheet.addCell(new Label(4,nRow,name,center));
					sheet.addCell(new Label(5,nRow,drawing_num,center));
					sheet.addCell(new Number(6,nRow,C_count,center));
					sheet.addCell(new Label(7,nRow,group_num,center));
					sheet.addCell(new Label(8,nRow,apply_time,center));
					sheet.addCell(new Label(9,nRow,""));
					nRow++;
					k++;
		    	}
		    	
//		    	设置列宽  
		    	sheet.setColumnView(0, 5);
		    	sheet.setColumnView(1, 20);
		    	sheet.setColumnView(2, 12); 
		    	sheet.setColumnView(3, 16); 
		    	sheet.setColumnView(4, 22); 
		    	sheet.setColumnView(5, 20); 
		    	sheet.setColumnView(6, 10);
		    	sheet.setColumnView(7, 12); 
		    	sheet.setColumnView(8, 15); 
		    	workbook.write();
				workbook.close();
				os.close();
			}catch (SQLException e) {
			// TODO 自动生成 catch 块
				e.printStackTrace();
			}
	    }
	 
//		非测量工具需求导出
	 public void createExcelCT(HashMap hashMap, OutputStream os) throws WriteException, IOException
	    {	
	    	String sql="";
	    	sql="select * from v_ct2need where trim(leader_approve)='未处理' and trim(gleader_approve)='批准'";  
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
								paHashMap.put("count", rs.getString("count"));
								paHashMap.put("apply_time", rs.getDate("apply_time"));
								paHashMap.put("group_num", rs.getString("group_num"));
								paHashMap.put("drawing_num", rs.getString("drawing_num"));
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
				WritableSheet sheet = workbook.createSheet("非测量工具需求领导审批", 0);
//				内容存放格式设定
				WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
				WritableCellFormat seat = new WritableCellFormat(place);
				//水平居中
				seat.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//				内容存放格式设定
				WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
				WritableCellFormat center = new WritableCellFormat(Place);
				WritableCellFormat wordsize = new WritableCellFormat(Place);
				//水平居中
				center.setAlignment(jxl.format.Alignment.CENTRE);
		        //创建标题，导出时间及查询条件
				sheet.mergeCells(0, 0, 8, 0);
				Label fname = new Label(0, 0, "标题：非测量工具需求领导审批",seat);
				sheet.addCell(fname);
				
				sheet.mergeCells(0, 1, 8, 1);
				Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
				sheet.addCell(ftime);
				
				sheet.mergeCells(0, 2, 8, 2);
				Label Search = new Label(0, 2, "查询条件：未审批项",wordsize);
				sheet.addCell(Search);
				
				sheet.mergeCells(0, 3, 8, 3);
				Label People = new Label(0, 3, "审批人："+(String)hashMap.get("leadername"),wordsize);
				sheet.addCell(People);

				//				创建要显示的具体内容的题头
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
				
				Label Drawing_num = new Label(5, 5, "产品令号",center);
				sheet.addCell(Drawing_num);
				
				Label Count = new Label(6, 5, "需求数量",center);
				sheet.addCell(Count);
				
				Label Group = new Label(7, 5, "需求组别",center);
				sheet.addCell(Group);
				
				Label Time = new Label(8, 5, "申请时间",center);
				sheet.addCell(Time);
				
				int nRow=6;
				int k=1;
		    	for(int i=0;i<pcArray.size();i++){
					String mnum="";
					String pclass="";
					String bclass="";
					String name="";
					String count="";
					String apply_time="";
					String group_num="";
					String drawing_num = "";
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
//					数量为空时置零并将其转化为整形
					if(((HashMap)pcArray.get(i)).get("count")!=null){
						count=((HashMap)pcArray.get(i)).get("count").toString();
					}else{
						count = "0";
					}
					int C_count=Integer.parseInt(count);
					if(((HashMap)pcArray.get(i)).get("apply_time")!=null){
						apply_time=((HashMap)pcArray.get(i)).get("apply_time").toString();
					}
					if(((HashMap)pcArray.get(i)).get("group_num")!=null){
						group_num=((HashMap)pcArray.get(i)).get("group_num").toString();
					}
					if(((HashMap)pcArray.get(i)).get("drawing_num")!=null){
						drawing_num=((HashMap)pcArray.get(i)).get("drawing_num").toString();
					}
					sheet.addCell(new Number(0,nRow,k,center));
					sheet.addCell(new Label(1,nRow,mnum,center));
					sheet.addCell(new Label(2,nRow,pclass,center));
					sheet.addCell(new Label(3,nRow,bclass,center));
					sheet.addCell(new Label(4,nRow,name,center));
					sheet.addCell(new Label(5,nRow,drawing_num,center));
					sheet.addCell(new Number(6,nRow,C_count,center));
					sheet.addCell(new Label(7,nRow,group_num,center));
					sheet.addCell(new Label(8,nRow,apply_time,center));
					sheet.addCell(new Label(9,nRow,""));
					nRow++;
					k++;
		    	}
		    	
//		    	设置列宽  
		    	sheet.setColumnView(0, 5);
		    	sheet.setColumnView(1, 20);
		    	sheet.setColumnView(2, 12); 
		    	sheet.setColumnView(3, 16); 
		    	sheet.setColumnView(4, 22); 
		    	sheet.setColumnView(5, 20); 
		    	sheet.setColumnView(6, 10);
		    	sheet.setColumnView(7, 12); 
		    	sheet.setColumnView(8, 15); 
		    	workbook.write();
				workbook.close();
				os.close();
			}catch (SQLException e) {
			// TODO 自动生成 catch 块
				e.printStackTrace();
			}
	    }
	 
	 
//		量具需求导出
	 public void createExcelM(HashMap hashMap, OutputStream os) throws WriteException, IOException
	    {
	    	
		 	String sql="";
	    	sql="select * from v_m2need where trim(leader_approve)='未处理' and trim(gleader_approve)='批准'";  
	    	final ArrayList pcArray=new ArrayList();
	    	try{
		    	DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {	
						try{
							int j=0;
							while(rs.next()){
								HashMap paHashMap=new HashMap();
								paHashMap.put("mnum", rs.getString("mnum"));
								paHashMap.put("drawing_num", rs.getString("drawing_num"));
								paHashMap.put("count", rs.getString("count"));
								paHashMap.put("classname", rs.getString("classname"));
								paHashMap.put("spec", rs.getString("spec"));
								paHashMap.put("name", rs.getString("name"));
								paHashMap.put("group_num", rs.getString("group_num"));
								paHashMap.put("apply_time", rs.getDate("apply_time"));
								pcArray.add(j,paHashMap);
								j++;
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}				
				});	
//		    	读取导出日期
		    	Calendar c = Calendar.getInstance();
				final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
				String CurDate = curDate.toString();
	            //创建工作薄��������
		    	WritableWorkbook workbook = Workbook.createWorkbook(os);
		    	//创建工作表�����µ�
				WritableSheet sheet = workbook.createSheet("量具需求领导审批", 0);
//				内容存放格式设定
				WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
				WritableCellFormat seat = new WritableCellFormat(place);
				//水平居中
				seat.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//				内容存放格式设定
				WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
				WritableCellFormat center = new WritableCellFormat(Place);
				WritableCellFormat wordsize = new WritableCellFormat(Place);
				//水平居中
				center.setAlignment(jxl.format.Alignment.CENTRE);				
		        //创建标题，导出时间及查询条件
				sheet.mergeCells(0, 0, 8, 0);
				Label fname = new Label(0, 0, "标题：量具需求领导审批",seat);
				sheet.addCell(fname);
			
				sheet.mergeCells(0, 1, 8, 1);
				Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
				sheet.addCell(ftime);
			
				sheet.mergeCells(0, 2, 8, 2);
				Label Search = new Label(0, 2, "查询条件：未审批项",wordsize);
				sheet.addCell(Search);
			
				sheet.mergeCells(0, 3, 8, 3);
				Label People = new Label(0, 3, "审批人："+(String)hashMap.get("leadername"),wordsize);
				sheet.addCell(People);
				
//				创建要显示的具体内容的题头
				Label order = new Label(0, 5, "序号",center);
				sheet.addCell(order);
				
				Label Mnum = new Label(1, 5, "物资编码",center);
				sheet.addCell(Mnum);
				
				Label Classname = new Label(2, 5, "类别",center);
				sheet.addCell(Classname);
				
				Label Name = new Label(3, 5, "名称",center);
				sheet.addCell(Name);
				
				Label Spec = new Label(4, 5, "规格",center);
				sheet.addCell(Spec);
				
				Label Drawing_num = new Label(5, 5, "产品令号",center);
				sheet.addCell(Drawing_num);
				
				Label Count = new Label(6, 5, "需求数量",center);
				sheet.addCell(Count);
				
				Label Group = new Label(7, 5, "需求组别",center);
				sheet.addCell(Group);
				
				Label Time = new Label(8, 5, "申请时间",center);
				sheet.addCell(Time);
				int nRow=6;
				int k = 1;
		    	for(int i=0;i<pcArray.size();i++){
					String mnum="";
					String classname="";
					String drawing_num="";
					String count="";
					String name="";
					String spec="";
					String group_num="";
					String apply_time="";
					if(((HashMap)pcArray.get(i)).get("mnum")!=null){
						mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
					}
					if(((HashMap)pcArray.get(i)).get("classname")!=null){
						classname=((HashMap)pcArray.get(i)).get("classname").toString();
					}
					if(((HashMap)pcArray.get(i)).get("drawing_num")!=null){
						drawing_num=((HashMap)pcArray.get(i)).get("drawing_num").toString();
					}
					if(((HashMap)pcArray.get(i)).get("name")!=null){
						name=((HashMap)pcArray.get(i)).get("name").toString();
					}
//					数量为空时置零并将其转化为整形
					if(((HashMap)pcArray.get(i)).get("count")!=null){
						count=((HashMap)pcArray.get(i)).get("count").toString();
					}else{
						count = "0";
					}
					int C_count=Integer.parseInt(count);
					if(((HashMap)pcArray.get(i)).get("spec")!=null){
						spec=((HashMap)pcArray.get(i)).get("spec").toString();
					}
					if(((HashMap)pcArray.get(i)).get("group_num")!=null){
						group_num=((HashMap)pcArray.get(i)).get("group_num").toString();
					}
					if(((HashMap)pcArray.get(i)).get("apply_time")!=null){
						apply_time=((HashMap)pcArray.get(i)).get("apply_time").toString();
					}
					sheet.addCell(new Number(0,nRow,k,center));
					sheet.addCell(new Label(1,nRow,mnum,center));
					sheet.addCell(new Label(2,nRow,classname,center));
					sheet.addCell(new Label(3,nRow,name,center));
					sheet.addCell(new Label(4,nRow,spec,center));
					sheet.addCell(new Label(5,nRow,drawing_num,center));
					sheet.addCell(new Number(6,nRow,C_count,center));
					sheet.addCell(new Label(7,nRow,group_num,center));
					sheet.addCell(new Label(8,nRow,apply_time,center));
					sheet.addCell(new Label(9,nRow,""));
					nRow++;
					k++;
		    	}
		    	
//		    	设置列宽  
		    	sheet.setColumnView(0, 5);
		    	sheet.setColumnView(1, 20); 
		    	sheet.setColumnView(2, 15); 
		    	sheet.setColumnView(3, 22); 
		    	sheet.setColumnView(4, 25); 
		    	sheet.setColumnView(5, 20);
		    	sheet.setColumnView(6, 10);
		    	sheet.setColumnView(7, 12); 
		    	sheet.setColumnView(8, 15); 
		    	workbook.write();
				workbook.close();
				os.close();
			}catch (SQLException e) {
			// TODO 自动生成 catch 块
				e.printStackTrace();
			}
	    }
	 
	 
//		测量工具需求导出
	 public void createExcelMT(HashMap hashMap, OutputStream os) throws WriteException, IOException
	    {
	    	
		 	String sql="";
	    	sql="select * from v_mt2need where trim(leader_approve)='未处理' and trim(gleader_approve)='批准'";  
	    	final ArrayList pcArray=new ArrayList();
	    	try{
		    	DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {	
						try{
							int j=0;
							while(rs.next()){
								HashMap paHashMap=new HashMap();
								paHashMap.put("mnum", rs.getString("mnum"));
								paHashMap.put("drawing_num", rs.getString("drawing_num"));
								paHashMap.put("count", rs.getString("count"));
								paHashMap.put("classname", rs.getString("classname"));
								paHashMap.put("spec", rs.getString("spec"));
								paHashMap.put("name", rs.getString("name"));
								paHashMap.put("apply_time", rs.getDate("apply_time"));
								paHashMap.put("group_num", rs.getString("group_num"));
								pcArray.add(j,paHashMap);
								j++;
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}				
				});	
//		    	读取导出日期
		    	Calendar c = Calendar.getInstance();
				final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
				String CurDate = curDate.toString();
	            //创建工作薄��������
		    	WritableWorkbook workbook = Workbook.createWorkbook(os);
		    	//创建工作表�����µ�
				WritableSheet sheet = workbook.createSheet("测量工具需求领导审批", 0);
//				内容存放格式设定
				WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
				WritableCellFormat seat = new WritableCellFormat(place);
				//水平居中
				seat.setAlignment(jxl.format.Alignment.CENTRE);
				//垂直居中
				seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//				内容存放格式设定
				WritableFont Place = new WritableFont(WritableFont.ARIAL,11, WritableFont.NO_BOLD, false);
				WritableCellFormat center = new WritableCellFormat(Place);
				WritableCellFormat wordsize = new WritableCellFormat(Place);
				//水平居中
				center.setAlignment(jxl.format.Alignment.CENTRE);				
		        //创建标题，导出时间及查询条件
				sheet.mergeCells(0, 0, 8, 0);
				Label fname = new Label(0, 0, "标题：测量工具需求领导审批",seat);
				sheet.addCell(fname);
			
				sheet.mergeCells(0, 1, 8, 1);
				Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
				sheet.addCell(ftime);
			
				sheet.mergeCells(0, 2, 8, 2);
				Label Search = new Label(0, 2, "查询条件：未审批项",wordsize);
				sheet.addCell(Search);
				
				sheet.mergeCells(0, 3, 8, 3);
				Label People = new Label(0, 3, "审批人："+(String)hashMap.get("leadername"),wordsize);
				sheet.addCell(People);
				
//				创建要显示的具体内容的题头������ʾ�ľ�������
				Label order = new Label(0, 5, "序号",center);
				sheet.addCell(order);
				
				Label Mnum = new Label(1, 5, "物资编码",center);
				sheet.addCell(Mnum);
				
				Label Classname = new Label(2, 5, "类别",center);
				sheet.addCell(Classname);
				
				Label Name = new Label(3, 5, "名称",center);
				sheet.addCell(Name);
				
				Label Spec = new Label(4, 5, "规格",center);
				sheet.addCell(Spec);
				
				Label Drawing_num = new Label(5, 5, "产品令号",center);
				sheet.addCell(Drawing_num);
				
				Label Count = new Label(6, 5, "需求数量",center);
				sheet.addCell(Count);
				
				Label Group = new Label(7, 5, "需求组别",center);
				sheet.addCell(Group);
				
				Label Time = new Label(8, 5, "申请时间",center);
				sheet.addCell(Time);
				int nRow=6;
				int k = 1;
		    	for(int i=0;i<pcArray.size();i++){
					String mnum="";
					String classname="";
					String name="";
					String spec="";
					String apply_time="";
					String group_num="";
					String drawing_num="";
					String count="";
					
					if(((HashMap)pcArray.get(i)).get("mnum")!=null){
						mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
					}
					if(((HashMap)pcArray.get(i)).get("classname")!=null){
						classname=((HashMap)pcArray.get(i)).get("classname").toString();
					}
					if(((HashMap)pcArray.get(i)).get("drawing_num")!=null){
						drawing_num=((HashMap)pcArray.get(i)).get("drawing_num").toString();
					}
					if(((HashMap)pcArray.get(i)).get("name")!=null){
						name=((HashMap)pcArray.get(i)).get("name").toString();
					}
//					数量为空时置零并将其转化为整形
					if(((HashMap)pcArray.get(i)).get("count")!=null){
						count=((HashMap)pcArray.get(i)).get("count").toString();
					}else{
						count = "0";
					}
					int C_count=Integer.parseInt(count);
					if(((HashMap)pcArray.get(i)).get("spec")!=null){
						spec=((HashMap)pcArray.get(i)).get("spec").toString();
					}
					if(((HashMap)pcArray.get(i)).get("group_num")!=null){
						group_num=((HashMap)pcArray.get(i)).get("group_num").toString();
					}
					if(((HashMap)pcArray.get(i)).get("apply_time")!=null){
						apply_time=((HashMap)pcArray.get(i)).get("apply_time").toString();
					}
					sheet.addCell(new Number(0,nRow,k,center));
					sheet.addCell(new Label(1,nRow,mnum,center));
					sheet.addCell(new Label(2,nRow,classname,center));
					sheet.addCell(new Label(3,nRow,name,center));
					sheet.addCell(new Label(4,nRow,spec,center));
					sheet.addCell(new Label(5,nRow,drawing_num,center));
					sheet.addCell(new Number(6,nRow,C_count,center));
					sheet.addCell(new Label(7,nRow,group_num,center));
					sheet.addCell(new Label(8,nRow,apply_time,center));
					sheet.addCell(new Label(9,nRow,""));
					nRow++;
					k++;
		    	}
		    	
//		    	设置列宽  
		    	sheet.setColumnView(0, 5);
		    	sheet.setColumnView(1, 20); 
		    	sheet.setColumnView(2, 15); 
		    	sheet.setColumnView(3, 22); 
		    	sheet.setColumnView(4, 25); 
		    	sheet.setColumnView(5, 20);
		    	sheet.setColumnView(6, 10);
		    	sheet.setColumnView(7, 12); 
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
