package com.nuaa.app.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class User_Excel {
	//用户当前借用查询excel报表导出
	public void createExcel(HashMap hashMap,OutputStream os)throws WriteException, IOException{
		final String username = (String) hashMap.get("username");
		String sql = "select * from c_br_now where realname='"+username+"'";
		final ArrayList pcArray=new ArrayList();
		try {
			DbUtil.execute(sql, new IResultSetProcessor() {
						public void process(ResultSet rs) throws SQLException {	
								int j=0;
								while(rs.next()){
								HashMap paHashMap=new HashMap();
								paHashMap.put("name", rs.getString("name"));
								paHashMap.put("rank_angle", rs.getString("rank_angle"));
								paHashMap.put("e_diam", rs.getString("e_diam"));
								paHashMap.put("h_diam", rs.getString("h_diam"));
								paHashMap.put("e_leng", rs.getString("e_leng"));
								paHashMap.put("t_leng", rs.getString("t_leng"));
								paHashMap.put("e_count", rs.getString("e_count"));
								paHashMap.put("c_mate", rs.getString("c_mate"));
								paHashMap.put("coat_mate", rs.getString("coat_mate"));//涂层材料
								paHashMap.put("hq", rs.getString("hq"));
								paHashMap.put("company", rs.getString("company"));
								paHashMap.put("location", rs.getString("location"));
								paHashMap.put("br_count", rs.getString("br_count"));
								paHashMap.put("time", rs.getDate("time"));
								pcArray.add(j,paHashMap);
								j++;
								}
						}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Calendar c = Calendar.getInstance();
		final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
		String CurDate = curDate.toString();
        //创建工作薄
    	WritableWorkbook workbook = Workbook.createWorkbook(os);
    	//创建工作表
    	WritableSheet sheet = workbook.createSheet("当前借用查询报表", 0);
    	//标题存放格式设定
		WritableFont place = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.RED);
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
        //创建标题，导出时间
		sheet.mergeCells(0, 0, 17, 0);
		Label fname = new Label(0, 0, "标题：当前借用查询报表",seat);
		sheet.addCell(fname);
		sheet.mergeCells(0, 1, 17, 1);
		Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
		sheet.addCell(ftime);
		sheet.mergeCells(0, 2, 17, 2);
		Label user = new Label(0, 2, "工人姓名："+username,wordsize);
		sheet.addCell(user);
		Label order = new Label(0, 3, "序号",center);
		sheet.addCell(order);
		Label name = new Label(1, 3, "名称",center);
		sheet.addCell(name);
		Label rank_angle = new Label(2, 3, "R角",center);
		sheet.addCell(rank_angle);
		Label e_diam = new Label(3, 3, "刃径",center);
		sheet.addCell(e_diam);
		Label h_diam = new Label(4, 3, "柄径",center);
		sheet.addCell(h_diam);
		Label e_leng = new Label(5, 3, "刃长",center);
		sheet.addCell(e_leng);
		Label t_leng = new Label(6, 3, "总长",center);
		sheet.addCell(t_leng);
		Label e_count = new Label(7, 3, "刃数",center);
		sheet.addCell(e_count);
		Label c_mate = new Label(8, 3, "刀具材料",center);
		sheet.addCell(c_mate);
		Label coat_mate = new Label(9, 3, "刀具材料",center);
		sheet.addCell(coat_mate);
		Label hq = new Label(10, 3, "是否高质",center);
		sheet.addCell(hq);
		Label company = new Label(11, 3, "厂商",center);
		sheet.addCell(company);
		Label location = new Label(12, 3, "库存地点",center);
		sheet.addCell(location);
		Label br_count = new Label(13, 3, "借用数量",center);
		sheet.addCell(br_count);
		Label time = new Label(14, 3, "借用时间",center);
		sheet.addCell(time);
		
		int nRow = 4;
		int k=1;
		for(int i=0;i<pcArray.size();i++){
			String name1 = "";
			String rank_angle1 = "";
			String e_diam1 ="";
			String h_diam1 = "";
			String e_leng1 = "";
			String t_leng1 = "";
			String e_count1 = "";
			String c_mate1 = "";
			String coat_mate1 = "";
			String hq1 = "";
			String company1 = "";
			String location1 = "";
			String br_count1 = "";
			String time1 = "";
			
			if(((HashMap)pcArray.get(i)).get("name")!=null){
				name1 = ((HashMap)pcArray.get(i)).get("name").toString();
			}
			if(((HashMap)pcArray.get(i)).get("rank_angle")!=null){
				rank_angle1 = ((HashMap)pcArray.get(i)).get("rank_angle").toString();
			}
			if(((HashMap)pcArray.get(i)).get("e_diam")!=null){
				e_diam1 = ((HashMap)pcArray.get(i)).get("e_diam").toString();
			}
			if(((HashMap)pcArray.get(i)).get("h_diam")!=null){
				h_diam1 = ((HashMap)pcArray.get(i)).get("h_diam").toString();
			}
			if(((HashMap)pcArray.get(i)).get("e_leng")!=null){
				e_leng1 = ((HashMap)pcArray.get(i)).get("e_leng").toString();
			}
			if(((HashMap)pcArray.get(i)).get("t_leng")!=null){
				t_leng1 = ((HashMap)pcArray.get(i)).get("t_leng").toString();
			}
			if(((HashMap)pcArray.get(i)).get("e_count")!=null){
				e_count1 = ((HashMap)pcArray.get(i)).get("e_count").toString();
			}
			if(((HashMap)pcArray.get(i)).get("c_mate")!=null){
				c_mate1 = ((HashMap)pcArray.get(i)).get("c_mate").toString();
			}
			if(((HashMap)pcArray.get(i)).get("coat_mate")!=null){
				coat_mate1 = ((HashMap)pcArray.get(i)).get("coat_mate").toString();
			}
			if(((HashMap)pcArray.get(i)).get("hq")!=null){
				hq1 = ((HashMap)pcArray.get(i)).get("hq").toString();
			}
			if(((HashMap)pcArray.get(i)).get("company")!=null){
				company1 = ((HashMap)pcArray.get(i)).get("company").toString();
			}
			if(((HashMap)pcArray.get(i)).get("location")!=null){
				location1 = ((HashMap)pcArray.get(i)).get("location").toString();
			}
			if(((HashMap)pcArray.get(i)).get("br_count")!=null){
				br_count1 = ((HashMap)pcArray.get(i)).get("br_count").toString();
			}
			if(((HashMap)pcArray.get(i)).get("time")!=null){
				time1 = ((HashMap)pcArray.get(i)).get("time").toString();
			}
			
			sheet.addCell(new Number(0,nRow,k,center));
			sheet.addCell(new Label(1,nRow,name1,center));
			sheet.addCell(new Label(2,nRow,rank_angle1,center));
			sheet.addCell(new Label(3,nRow,e_diam1,center));
			sheet.addCell(new Label(4,nRow,h_diam1,center));
			sheet.addCell(new Label(5,nRow,e_leng1,center));
			sheet.addCell(new Label(6,nRow,t_leng1,center));
			sheet.addCell(new Label(7,nRow,e_count1,center));
			sheet.addCell(new Label(8,nRow,c_mate1,center));
			sheet.addCell(new Label(9,nRow,coat_mate1,center));
			sheet.addCell(new Label(10,nRow,hq1,center));
			sheet.addCell(new Label(11,nRow,company1,center));
			sheet.addCell(new Label(12,nRow,location1,center));
			sheet.addCell(new Label(13,nRow,br_count1,center));
			sheet.addCell(new Label(14,nRow,time1,center));
			sheet.addCell(new Label(15,nRow,""));
			nRow++;
			k++;
			}
		//设置列宽
		sheet.setColumnView(0, 5);
    	sheet.setColumnView(1, 15);
    	sheet.setColumnView(2, 5); 
    	sheet.setColumnView(3, 5); 
    	sheet.setColumnView(4, 5); 
    	sheet.setColumnView(5, 5); 
    	sheet.setColumnView(6, 5); 
    	sheet.setColumnView(7, 5); 
    	sheet.setColumnView(8, 10); 
    	sheet.setColumnView(9, 10); 
    	sheet.setColumnView(10, 15); 
    	sheet.setColumnView(11, 25); 
    	sheet.setColumnView(12, 25);
    	sheet.setColumnView(13, 15);
    	sheet.setColumnView(14, 25);
    	sheet.setColumnView(15, 25);
    	workbook.write();
		workbook.close();
		os.close();
	}
}
