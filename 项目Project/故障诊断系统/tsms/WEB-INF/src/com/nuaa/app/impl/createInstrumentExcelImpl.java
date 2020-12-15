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
import jxl.write.biff.RowsExceededException;

import com.nuaa.app.createInstrumentExcel;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;

public class createInstrumentExcelImpl implements createInstrumentExcel {
	public void createInstrumentxls(OutputStream os) {
		String sql="";
    	sql="select * from t_instrument"; 
    	System.out.println(sql);
    	final ArrayList pcArray=new ArrayList();
			try {
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {
						int j=0;
						while(rs.next()){
							final HashMap paHashMap=new HashMap();
							paHashMap.put("serialnum", rs.getString("serialnum"));
							paHashMap.put("enterprisenum", rs.getString("enterprisenum"));
							paHashMap.put("outnum", rs.getString("outnum"));
							paHashMap.put("name", rs.getString("name"));
							paHashMap.put("modelnum", rs.getString("modelnum"));
							paHashMap.put("classname", rs.getString("classname"));
							paHashMap.put("nowclass", rs.getString("nowclass"));
							paHashMap.put("detectdate", rs.getDate("detectdate"));
							paHashMap.put("nextdate", rs.getDate("nextdate"));
							paHashMap.put("detectmode", rs.getString("detectmode"));
							paHashMap.put("state", rs.getString("state"));
							paHashMap.put("execution", rs.getString("execution"));
							paHashMap.put("blongteam", rs.getString("blongteam"));
							paHashMap.put("person", rs.getString("person"));
							pcArray.add(j,paHashMap);
							System.out.println(pcArray.get(j));
							j++;
						}
					}
				});
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			
			//读取导出日期
	    	//创建工作表�����µ�
			WritableSheet sheet = null;
				Calendar c = Calendar.getInstance();
				final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
				String CurDate = curDate.toString();
				//创建工作薄��������
				WritableWorkbook workbook = null;
				try {
					workbook = Workbook.createWorkbook(os);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				sheet = workbook.createSheet("设备仪表基本信息表", 0);
				//内容存放格式设定
				WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
				WritableCellFormat seat = new WritableCellFormat(place);
				//水平居中
					try {
						seat.setAlignment(jxl.format.Alignment.CENTRE);
						//垂直居中
						seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

						sheet.mergeCells(0, 0, 14, 0);
						Label fname = new Label(0, 0, "设备仪表基本信息表",seat);

						sheet.addCell(fname);
						sheet.mergeCells(0, 1, 14, 1);
						Label ftime = new Label(0, 1, "导出日期："+CurDate);
						sheet.addCell(ftime);

//创建要显示的具体内容的题头
Label Name = new Label(0, 2, "序号");
sheet.addCell(Name);
Label Pclass = new Label(1, 2, "企业编号");
sheet.addCell(Pclass);
Label Bclass = new Label(2, 2, "出厂编号");
sheet.addCell(Bclass);
Label Newstatus = new Label(3, 2, "型号");
sheet.addCell(Newstatus);
Label Hq = new Label(4, 2, "名称");
sheet.addCell(Hq);
Label In_count = new Label(5, 2, "类别");
sheet.addCell(In_count);
Label Nct = new Label(6, 2, "现场类别");
sheet.addCell(Nct);
Label Uct = new Label(7, 2, "待检日期");
sheet.addCell(Uct);
Label Location = new Label(8, 2, "下次待检日期");
sheet.addCell(Location);
Label Com = new Label(9, 2, "检测方式");
sheet.addCell(Com);
Label Order = new Label(10, 2, "状态");
sheet.addCell(Order);
Label In_time = new Label(11, 2, "完成情况");
sheet.addCell(In_time);
Label Status = new Label(12, 2, "所在班组");
sheet.addCell(Status);
Label Status1 = new Label(13, 2, "责任人");
sheet.addCell(Status1);
					} catch (RowsExceededException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (WriteException e1) {
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
				String buycycle="";
				String monthuse = "";
				if(((HashMap)pcArray.get(i)).get("serialnum")!=null){
					mnum=((HashMap)pcArray.get(i)).get("serialnum").toString();
				}
				if(((HashMap)pcArray.get(i)).get("enterprisenum")!=null){
					classname=((HashMap)pcArray.get(i)).get("enterprisenum").toString();
				}
				if(((HashMap)pcArray.get(i)).get("outnum")!=null){
					name=((HashMap)pcArray.get(i)).get("outnum").toString();
				}
				if(((HashMap)pcArray.get(i)).get("name")!=null){
					modelnum=((HashMap)pcArray.get(i)).get("name").toString();
				}
				if(((HashMap)pcArray.get(i)).get("modelnum")!=null){
					spec=((HashMap)pcArray.get(i)).get("modelnum").toString();
				}
				if(((HashMap)pcArray.get(i)).get("classname")!=null){
					company=((HashMap)pcArray.get(i)).get("classname").toString();
				}
				if(((HashMap)pcArray.get(i)).get("nowclass")!=null){
					measure=((HashMap)pcArray.get(i)).get("nowclass").toString();
				}
				if(((HashMap)pcArray.get(i)).get("detectdate")!=null){
					guaranteedate=((HashMap)pcArray.get(i)).get("detectdate").toString();
				}
				if(((HashMap)pcArray.get(i)).get("nextdate")!=null){
					minstore=((HashMap)pcArray.get(i)).get("nextdate").toString();
				}
				if(((HashMap)pcArray.get(i)).get("detectmode")!=null){
					singlepur=((HashMap)pcArray.get(i)).get("detectmode").toString();
				}
				if(((HashMap)pcArray.get(i)).get("state")!=null){
					remark=((HashMap)pcArray.get(i)).get("state").toString();
				}
				if(((HashMap)pcArray.get(i)).get("execution")!=null){
					maxstore=((HashMap)pcArray.get(i)).get("execution").toString();
				}
				if(((HashMap)pcArray.get(i)).get("blongteam")!=null){
					buycycle=((HashMap)pcArray.get(i)).get("blongteam").toString();
				}if(((HashMap)pcArray.get(i)).get("person")!=null){
					monthuse=((HashMap)pcArray.get(i)).get("person").toString();
				}
					try {
						sheet.addCell(new Label(0,nRow,mnum));
						sheet.addCell(new Label(1,nRow,classname));
						sheet.addCell(new Label(2,nRow,name));
						sheet.addCell(new Label(3,nRow,modelnum));
						sheet.addCell(new Label(4,nRow,spec));
						sheet.addCell(new Label(5,nRow,company));
						sheet.addCell(new Label(6,nRow,measure));
						sheet.addCell(new Label(7,nRow,guaranteedate));
						sheet.addCell(new Label(8,nRow,minstore));
						sheet.addCell(new Label(9,nRow,maxstore));
						sheet.addCell(new Label(10,nRow,buycycle));
						sheet.addCell(new Label(11,nRow,monthuse));
						sheet.addCell(new Label(12,nRow,singlepur));
						sheet.addCell(new Label(13,nRow,remark));
						sheet.addCell(new Label(14,nRow,""));
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
	}
	
}
