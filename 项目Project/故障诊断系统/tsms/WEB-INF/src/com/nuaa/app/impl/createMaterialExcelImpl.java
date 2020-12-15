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

import com.nuaa.app.createMaterialExcel;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;

public class createMaterialExcelImpl implements  createMaterialExcel{

	public void createMaterialxls(OutputStream os) {
		// TODO Auto-generated method stub
		String sql="";
    	sql="select * from t_material"; 
    	System.out.println(sql);
    	final ArrayList pcArray=new ArrayList();
				try {
					DbUtil.execute(sql,new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {
							int j=0;
							while(rs.next()){
								final HashMap paHashMap=new HashMap();
								paHashMap.put("mnum", rs.getString("mnum"));
								paHashMap.put("classname", rs.getString("classname"));
								paHashMap.put("name", rs.getString("name"));
								paHashMap.put("modelnum", rs.getString("modelnum"));
								paHashMap.put("spec", rs.getString("spec"));
								paHashMap.put("measure", rs.getString("measure"));
								paHashMap.put("guaranteedate", rs.getString("guaranteedate"));
								paHashMap.put("minstore", rs.getString("minstore"));
								paHashMap.put("maxstore", rs.getString("maxstore"));
								paHashMap.put("buycycle", rs.getString("buycycle"));
								paHashMap.put("monthuse", rs.getString("monthuse"));
								paHashMap.put("singlepur", rs.getString("singlepur"));
								paHashMap.put("remark", rs.getString("remark"));
								String id = rs.getString("companyid");
								String sqls = "select company from t_company where id = '"+id+"'";
								DbUtil.execute(sqls,new IResultSetProcessor(){
									public void process(ResultSet rs)
											throws SQLException {
										while(rs.next()){
											paHashMap.put("company", rs.getString("company"));
										}
									}
								});
								pcArray.add(j,paHashMap);
								System.out.println(pcArray.get(j));
								j++;
							}
						}
					});
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				//读取导出日期
		    	Calendar c = Calendar.getInstance();
				final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
				String CurDate = curDate.toString();
	            //创建工作薄��������
		    	WritableWorkbook workbook = null;
				try {
					workbook = Workbook.createWorkbook(os);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	//创建工作表�����µ�
				WritableSheet sheet = workbook.createSheet("材料基本信息表", 0);
				//内容存放格式设定
				WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false);
				WritableCellFormat seat = new WritableCellFormat(place);
				//水平居中
					try {
						seat.setAlignment(jxl.format.Alignment.CENTRE);
//垂直居中
						seat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

      //创建标题，导出时间及查询条件
  	
  	sheet.mergeCells(0, 0, 14, 0);
Label fname = new Label(0, 0, "材料基本信息表",seat);

sheet.addCell(fname);
sheet.mergeCells(0, 1, 14, 1);
Label ftime = new Label(0, 1, "导出日期："+CurDate);
sheet.addCell(ftime);

//创建要显示的具体内容的题头
Label Name = new Label(0, 2, "物资编码");
sheet.addCell(Name);
Label Pclass = new Label(1, 2, "类别");
sheet.addCell(Pclass);
Label Bclass = new Label(2, 2, "名称");
sheet.addCell(Bclass);
Label Newstatus = new Label(3, 2, "型号");
sheet.addCell(Newstatus);
Label Hq = new Label(4, 2, "规格");
sheet.addCell(Hq);
Label In_count = new Label(5, 2, "厂商");
sheet.addCell(In_count);
Label Nct = new Label(6, 2, "计量单位");
sheet.addCell(Nct);
Label Uct = new Label(7, 2, "保质期");
sheet.addCell(Uct);
Label Location = new Label(8, 2, "最小安全库存");
sheet.addCell(Location);
Label Com = new Label(9, 2, "最大安全库存");
sheet.addCell(Com);
Label Order = new Label(10, 2, "采购周期");
sheet.addCell(Order);
Label In_time = new Label(11, 2, "月用量");
sheet.addCell(In_time);
Label Status = new Label(12, 2, "单次采购量");
sheet.addCell(Status);
Label Status1 = new Label(13, 2, "备注");
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
				if(((HashMap)pcArray.get(i)).get("mnum")!=null){
					mnum=((HashMap)pcArray.get(i)).get("mnum").toString();
				}
				if(((HashMap)pcArray.get(i)).get("classname")!=null){
					classname=((HashMap)pcArray.get(i)).get("classname").toString();
				}
				if(((HashMap)pcArray.get(i)).get("name")!=null){
					name=((HashMap)pcArray.get(i)).get("name").toString();
				}
				if(((HashMap)pcArray.get(i)).get("modelnum")!=null){
					modelnum=((HashMap)pcArray.get(i)).get("modelnum").toString();
				}
				if(((HashMap)pcArray.get(i)).get("spec")!=null){
					spec=((HashMap)pcArray.get(i)).get("spec").toString();
				}
				if(((HashMap)pcArray.get(i)).get("company")!=null){
					company=((HashMap)pcArray.get(i)).get("company").toString();
				}
				if(((HashMap)pcArray.get(i)).get("measure")!=null){
					measure=((HashMap)pcArray.get(i)).get("measure").toString();
				}
				if(((HashMap)pcArray.get(i)).get("guaranteedate")!=null){
					guaranteedate=((HashMap)pcArray.get(i)).get("guaranteedate").toString();
				}
				if(((HashMap)pcArray.get(i)).get("minstore")!=null){
					minstore=((HashMap)pcArray.get(i)).get("minstore").toString();
				}
				if(((HashMap)pcArray.get(i)).get("singlepur")!=null){
					singlepur=((HashMap)pcArray.get(i)).get("singlepur").toString();
				}
				if(((HashMap)pcArray.get(i)).get("remark")!=null){
					remark=((HashMap)pcArray.get(i)).get("remark").toString();
				}
				if(((HashMap)pcArray.get(i)).get("maxstore")!=null){
					maxstore=((HashMap)pcArray.get(i)).get("maxstore").toString();
				}
				if(((HashMap)pcArray.get(i)).get("buycycle")!=null){
					buycycle=((HashMap)pcArray.get(i)).get("buycycle").toString();
				}if(((HashMap)pcArray.get(i)).get("monthuse")!=null){
					monthuse=((HashMap)pcArray.get(i)).get("monthuse").toString();
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
						
