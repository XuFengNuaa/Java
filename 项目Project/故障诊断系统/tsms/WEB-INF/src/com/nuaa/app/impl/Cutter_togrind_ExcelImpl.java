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
import org.json.JSONException;
import org.json.JSONObject;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.Number;

import com.nuaa.app.Cutter_togrind_xml;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class Cutter_togrind_ExcelImpl implements Cutter_togrind_xml{
	public void createExcelTogrind(HashMap hashMap,OutputStream os) throws WriteException, IOException{
		//HashMap to_grindHashMap=hashMap;		
		String filter=(String)hashMap.get("filter");
		String order=(String)hashMap.get("order");
		//final JSONArray jsonArray=new JSONArray();	
		final ArrayList pcArray=new ArrayList();
		String sql="";
		sql="select  *  from  v_cutter2grd_record where grd_num='"+filter+"' order by "+order+"";
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						int j=0;
						while(rs.next()){							
						//	JSONObject obj=new JSONObject();							
							HashMap paHashMap=new HashMap();
							DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
							decimalformat.setMaximumFractionDigits(2);
							paHashMap.put("id", rs.getString("id"));
							paHashMap.put("grind_id", rs.getString("type_id"));
							paHashMap.put("grind_mnum", rs.getString("mnum"));								
							paHashMap.put("grind_pclass", rs.getString("pclass"));
							paHashMap.put("grind_bclass", rs.getString("bclass"));
							paHashMap.put("grind_name",rs.getString("name"));
							paHashMap.put("grind_company", rs.getString("company"));								
							paHashMap.put("grind_hq", rs.getString("hq"));
							paHashMap.put("grind_rank_angle", rs.getString("rank_angle"));
							paHashMap.put("grind_c_mate", rs.getString("c_mate"));
							paHashMap.put("grind_coat_mate", rs.getString("coat_mate"));									
							paHashMap.put("grind_location", rs.getString("location"));									
							paHashMap.put("grind_e_diam", rs.getString("e_diam"));
							paHashMap.put("grind_h_diam", rs.getString("h_diam"));
							paHashMap.put("grind_e_leng", rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
							paHashMap.put("grind_t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));								
							paHashMap.put("grind_e_count",rs.getString("e_count"));								
							paHashMap.put("grind_type", rs.getString("type"));	
							paHashMap.put("grind_series_num", rs.getString("series_num"));	
							paHashMap.put("grind_order_num", rs.getString("order_num"));
							paHashMap.put("grind_amount", rs.getString("s_count"));
							paHashMap.put("grind_num", rs.getString("grd_num"));
							paHashMap.put("s_time", rs.getString("s_time"));
							paHashMap.put("grd_depart", rs.getString("grd_department"));
							paHashMap.put("price", rs.getString("price"));
							
							pcArray.add(j,paHashMap);	
							j++;
						}							
					} catch (Exception e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
				}				
			});	
			
			//创建工作薄
			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			创建工作表
			WritableSheet sheet = workbook.createSheet("First Sheet", 0);
			//构造表头
			sheet.mergeCells(0, 0, 12, 0);
			WritableFont bold = new WritableFont(WritableFont.ARIAL,18,WritableFont.BOLD);
			WritableCellFormat titleFormate = new WritableCellFormat(bold);
			//单元格中的内容水平方向居中
			titleFormate.setAlignment(jxl.format.Alignment.CENTRE);
            //单元格中的内容垂直方向居中
			titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			Label title = new Label(0,0,"送磨单",titleFormate);
			sheet.addCell(title);
			
			WritableFont common = new WritableFont(WritableFont.ARIAL,10);
			WritableCellFormat labelFormate = new WritableCellFormat(common);
			//单元格中的内容水平方向居中
			labelFormate.setAlignment(jxl.format.Alignment.CENTRE);
            //单元格中的内容垂直方向居中
			labelFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			
			WritableFont common1 = new WritableFont(WritableFont.ARIAL,10);
			WritableCellFormat labelFormate1 = new WritableCellFormat(common1);		
            //单元格中的内容垂直方向居中
			labelFormate1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			
			sheet.mergeCells(0, 1, 4, 1);
			sheet.mergeCells(5, 1, 8, 1);
			sheet.mergeCells(9, 1, 12, 1);
			sheet.mergeCells(0, 2, 12, 3);
		
			
			//创建要显示的具体内容

			Label grd_num = new Label(0,1,"    送磨单号:     "+((HashMap)pcArray.get(0)).get("grind_num"));
			sheet.addCell(grd_num);
			Label grd_depart = new Label(5,1,"    送磨单位: "+((HashMap)pcArray.get(0)).get("grd_depart"));
			sheet.addCell(grd_depart);
			Label s_time = new Label(9,1,"   送磨日期:   "+((HashMap)pcArray.get(0)).get("s_time"));
			sheet.addCell(s_time);
			Label leader_sign = new Label(0,2,"    领导签字:   ",labelFormate1);
			sheet.addCell(leader_sign);
			
			//设置列宽
			sheet.setColumnView(0,5);
			sheet.setColumnView(1,11);
			sheet.setColumnView(5,8);
			sheet.setColumnView(6,5);
			sheet.setColumnView(7,20);
			sheet.setColumnView(10,10);
			
			Label number = new Label(0,4,"序号",labelFormate);
			sheet.addCell(number);
			Label name = new Label(1,4,"名称",labelFormate);
			sheet.addCell(name);
			Label e_diam = new Label(2,4,"刃径",labelFormate);
			sheet.addCell(e_diam);
			Label h_diam = new Label(3,4,"柄径",labelFormate);
			sheet.addCell(h_diam);
			Label e_leng = new Label(4,4,"刃长",labelFormate);
			sheet.addCell(e_leng);
			Label t_leng = new Label(5,4,"总长",labelFormate);
			sheet.addCell(t_leng);
			Label e_count = new Label(6,4,"刃数",labelFormate);			
			sheet.addCell(e_count);	
			Label company = new Label(7,4,"厂商",labelFormate);
			sheet.addCell(company);
			Label c_mate = new Label(8,4,"刀具材料",labelFormate);
			sheet.addCell(c_mate);
			Label coat_mate = new Label(9,4,"涂层材料",labelFormate);
			sheet.addCell(coat_mate);
			Label location = new Label(10,4,"库存位置",labelFormate);
			sheet.addCell(location);
			Label s_count = new Label(11,4,"送磨数量",labelFormate);
			sheet.addCell(s_count);
			Label price = new Label(12,4,"单价",labelFormate);
			sheet.addCell(price);
			
			
					
			for(int i=0;i<pcArray.size();i++){	
				
				String ex_name="";
				String ex_e_diam="";
				float ex_price=0;
				String ex_h_diam="";
				float ex_e_leng=0;
				float ex_t_leng=0;
				int ex_e_count=0;
				String ex_c_mate="";
				String ex_coat_mate="";
				String ex_company="";
				String ex_location="";
				int ex_s_count=0;
				
				if(((HashMap)pcArray.get(i)).get("grind_name")!=null){
					ex_name=((HashMap)pcArray.get(i)).get("grind_name").toString();
				}				
				if(((HashMap)pcArray.get(i)).get("grind_e_diam")!=null){					
					ex_e_diam=((HashMap)pcArray.get(i)).get("grind_e_diam").toString();					
				}			
				if(((HashMap)pcArray.get(i)).get("grind_h_diam")!=null){
					ex_h_diam=((HashMap)pcArray.get(i)).get("grind_h_diam").toString();					
				}
				if(!((HashMap)pcArray.get(i)).get("grind_e_leng").equals("")){
					ex_e_leng=Float.parseFloat(((HashMap)pcArray.get(i)).get("grind_e_leng").toString());
				}
				if(!((HashMap)pcArray.get(i)).get("grind_t_leng").equals("")){
					ex_t_leng=Float.parseFloat(((HashMap)pcArray.get(i)).get("grind_t_leng").toString());
				}				
				if(((HashMap)pcArray.get(i)).get("grind_e_count")!=null){
					ex_e_count=Integer.parseInt(((HashMap)pcArray.get(i)).get("grind_e_count").toString());
				}
				if(((HashMap)pcArray.get(i)).get("grind_c_mate")!=null){
					ex_c_mate=((HashMap)pcArray.get(i)).get("grind_c_mate").toString();
				}
				if(((HashMap)pcArray.get(i)).get("grind_coat_mate")!=null){
					ex_coat_mate=((HashMap)pcArray.get(i)).get("grind_coat_mate").toString();
				}
				if(((HashMap)pcArray.get(i)).get("grind_company")!=null){
					ex_company=((HashMap)pcArray.get(i)).get("grind_company").toString();
				}
				if(((HashMap)pcArray.get(i)).get("grind_location")!=null){
					ex_location=((HashMap)pcArray.get(i)).get("grind_location").toString();
				}
				if(((HashMap)pcArray.get(i)).get("grind_amount")!=null){
					ex_s_count=Integer.parseInt(((HashMap)pcArray.get(i)).get("grind_amount").toString());
				}
				if(((HashMap)pcArray.get(i)).get("price")!=null){
					ex_price=Float.parseFloat((((HashMap)pcArray.get(i)).get("price").toString()));
				}
				
				sheet.addCell(new Number(0,(i+5),i+1,labelFormate));
				sheet.addCell(new Label(1,(i+5),ex_name,labelFormate));
				sheet.addCell(new Label(2,(i+5),ex_e_diam,labelFormate));
				sheet.addCell(new Label(3,(i+5),ex_h_diam,labelFormate));				
				if(ex_e_leng==0){					
				sheet.addCell(new Label(4,(i+5)," "));		
				}else{
				sheet.addCell(new Number(4,(i+5),ex_e_leng,labelFormate));					
				}
				if(ex_t_leng==0){					
				sheet.addCell(new Label(5,(i+5)," "));		
				}else{
				sheet.addCell(new Number(5,(i+5),ex_t_leng,labelFormate));					
				}				
				if(ex_e_count==0){					
				sheet.addCell(new Label(6,(i+5)," "));		
				}else{
				sheet.addCell(new Number(6,(i+5),ex_e_count,labelFormate));					
				}
				sheet.addCell(new Label(7,(i+5),ex_company,labelFormate));
				sheet.addCell(new Label(8,(i+5),ex_c_mate,labelFormate));
				sheet.addCell(new Label(9,(i+5),ex_coat_mate,labelFormate));
				sheet.addCell(new Label(10,(i+5),ex_location,labelFormate));
			    if(ex_s_count==0){					
			 	sheet.addCell(new Label(11,(i+5)," "));		
				}else{
				sheet.addCell(new Number(11,(i+5),ex_s_count,labelFormate));					
				}
			    sheet.addCell(new Number(12,(i+5),ex_price,labelFormate));
			}
			workbook.write();
			workbook.close();
			os.close();		
			
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		
	}
	
	public void createExcelToprice(HashMap hashMap,OutputStream os) throws WriteException, IOException{
		//HashMap to_grindHashMap=hashMap;		
		String filter=(String)hashMap.get("filter");
		String order=(String)hashMap.get("order");
		//final JSONArray jsonArray=new JSONArray();	
		final ArrayList pcArray=new ArrayList();
		String sql="";
		sql="select  *  from  v_cutter2grd_record where "+filter+" order by "+order+"";
		
		System.out.println(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						int j=0;
						while(rs.next()){							
						//	JSONObject obj=new JSONObject();							
							HashMap paHashMap=new HashMap();
							DecimalFormat decimalformat=(DecimalFormat)NumberFormat.getInstance();								
							decimalformat.setMaximumFractionDigits(2);
							paHashMap.put("id", rs.getString("id"));
							paHashMap.put("grind_id", rs.getString("type_id"));
							paHashMap.put("grind_mnum", rs.getString("mnum"));								
							paHashMap.put("grind_pclass", rs.getString("pclass"));
							paHashMap.put("grind_bclass", rs.getString("bclass"));
							paHashMap.put("grind_name",rs.getString("name"));
							paHashMap.put("grind_company", rs.getString("company"));								
							paHashMap.put("grind_hq", rs.getString("hq"));
							paHashMap.put("grind_rank_angle", rs.getString("rank_angle"));
							paHashMap.put("grind_c_mate", rs.getString("c_mate"));
							paHashMap.put("grind_coat_mate", rs.getString("coat_mate"));									
							paHashMap.put("grind_location", rs.getString("location"));									
							paHashMap.put("grind_e_diam", rs.getString("e_diam"));
							paHashMap.put("grind_h_diam", rs.getString("h_diam"));
							paHashMap.put("grind_e_leng", rs.getString("e_leng")==null ? "":decimalformat.format(rs.getFloat("e_leng")));
							paHashMap.put("grind_t_leng", rs.getString("t_leng")==null ? "":decimalformat.format(rs.getFloat("t_leng")));								
							paHashMap.put("grind_e_count",rs.getString("e_count"));								
							paHashMap.put("grind_type", rs.getString("type"));	
							paHashMap.put("grind_series_num", rs.getString("series_num"));	
							paHashMap.put("grind_order_num", rs.getString("order_num"));
							paHashMap.put("grind_amount", rs.getString("s_count"));
							paHashMap.put("grind_num", rs.getString("grd_num"));
							paHashMap.put("s_time", rs.getString("s_time"));
							paHashMap.put("grd_depart", rs.getString("grd_department"));
							
							pcArray.add(j,paHashMap);	
							j++;
						}							
					} catch (Exception e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
				}				
			});	
			
			//创建工作薄
			WritableWorkbook workbook = Workbook.createWorkbook(os);
//			创建工作表
			WritableSheet sheet = workbook.createSheet("First Sheet", 0);
			//构造表头
			sheet.mergeCells(0, 0, 12, 0);
			WritableFont bold = new WritableFont(WritableFont.ARIAL,18,WritableFont.BOLD);
			WritableCellFormat titleFormate = new WritableCellFormat(bold);
			//单元格中的内容水平方向居中
			titleFormate.setAlignment(jxl.format.Alignment.CENTRE);
            //单元格中的内容垂直方向居中
			titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			Label title = new Label(0,0,"定价单",titleFormate);
			sheet.addCell(title);
			
			WritableFont common = new WritableFont(WritableFont.ARIAL,10);
			WritableCellFormat labelFormate = new WritableCellFormat(common);
			//单元格中的内容水平方向居中
			labelFormate.setAlignment(jxl.format.Alignment.CENTRE);
            //单元格中的内容垂直方向居中
			labelFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			
			
			Label number = new Label(0,1,"序号",labelFormate);
			sheet.addCell(number);
			Label name = new Label(1,1,"名称",labelFormate);
			sheet.addCell(name);
			Label e_diam = new Label(2,1,"刃径",labelFormate);
			sheet.addCell(e_diam);
			Label h_diam = new Label(3,1,"柄径",labelFormate);
			sheet.addCell(h_diam);
			Label e_leng = new Label(4,1,"刃长",labelFormate);
			sheet.addCell(e_leng);
			Label t_leng = new Label(5,1,"总长",labelFormate);
			sheet.addCell(t_leng);
			Label e_count = new Label(6,1,"刃数",labelFormate);
			sheet.addCell(e_count);
			Label e_company = new Label(7,1,"厂商",labelFormate);
			sheet.addCell(e_company);
			Label c_mate = new Label(8,1,"刀具材料",labelFormate);
			sheet.addCell(c_mate);
			Label coat_mate = new Label(9,1,"涂层材料",labelFormate);
			sheet.addCell(coat_mate);	
			Label location = new Label(10,1,"库存位置",labelFormate);
			sheet.addCell(location);
			Label s_count = new Label(11,1,"送磨数量",labelFormate);
			sheet.addCell(s_count);
			Label s_price = new Label(12,1,"单价",labelFormate);
			sheet.addCell(s_price);
			
            //设置列宽
			sheet.setColumnView(1,12);
			sheet.setColumnView(7,20);
			sheet.setColumnView(8,10);
			sheet.setColumnView(9,10);
			sheet.setColumnView(10,11);
					
			for(int i=0;i<pcArray.size();i++){	
				
				String ex_name="";
				String ex_e_diam="";
				String ex_h_diam="";
				float ex_e_leng=0;
				float ex_t_leng=0;
				int ex_e_count=0;
				String ex_c_mate="";
				String ex_coat_mate="";
				String ex_company="";
				String ex_location="";
				int ex_s_count=0;
				
				if(((HashMap)pcArray.get(i)).get("grind_name")!=null){
					ex_name=((HashMap)pcArray.get(i)).get("grind_name").toString();
				}				
				if(((HashMap)pcArray.get(i)).get("grind_e_diam")!=null){					
					ex_e_diam=((HashMap)pcArray.get(i)).get("grind_e_diam").toString();					
				}			
				if(((HashMap)pcArray.get(i)).get("grind_h_diam")!=null){
					ex_h_diam=((HashMap)pcArray.get(i)).get("grind_h_diam").toString();					
				}
				if(!((HashMap)pcArray.get(i)).get("grind_e_leng").equals("")){
					ex_e_leng=Float.parseFloat(((HashMap)pcArray.get(i)).get("grind_e_leng").toString());					
				}				
				if(!((HashMap)pcArray.get(i)).get("grind_t_leng").equals("")){
					ex_t_leng=Float.parseFloat(((HashMap)pcArray.get(i)).get("grind_t_leng").toString());					
				}				
				if(((HashMap)pcArray.get(i)).get("grind_e_count")!=null){
					ex_e_count=Integer.parseInt(((HashMap)pcArray.get(i)).get("grind_e_count").toString());
				}
				if(((HashMap)pcArray.get(i)).get("grind_c_mate")!=null){
					ex_c_mate=((HashMap)pcArray.get(i)).get("grind_c_mate").toString();
				}
				if(((HashMap)pcArray.get(i)).get("grind_coat_mate")!=null){
					ex_coat_mate=((HashMap)pcArray.get(i)).get("grind_coat_mate").toString();
				}
				if(((HashMap)pcArray.get(i)).get("grind_company")!=null){
					ex_company=((HashMap)pcArray.get(i)).get("grind_company").toString();
				}
				if(((HashMap)pcArray.get(i)).get("grind_location")!=null){
					ex_location=((HashMap)pcArray.get(i)).get("grind_location").toString();
				}
				if(((HashMap)pcArray.get(i)).get("grind_amount")!=null){
					ex_s_count=Integer.parseInt(((HashMap)pcArray.get(i)).get("grind_amount").toString());
				}
				
				sheet.addCell(new Number(0,(i+2),i+1,labelFormate));
				sheet.addCell(new Label(1,(i+2),ex_name,labelFormate));
				sheet.addCell(new Label(5,(i+2),ex_e_diam,labelFormate));
				sheet.addCell(new Label(3,(i+2),ex_h_diam,labelFormate));			
				if(ex_e_leng==0){					
				sheet.addCell(new Label(4,(i+2)," "));		
				}else{
				sheet.addCell(new Number(4,(i+2),ex_e_leng,labelFormate));					
				}
				if(ex_t_leng==0){					
				sheet.addCell(new Label(5,(i+2)," "));		
				}else{
				sheet.addCell(new Number(5,(i+2),ex_t_leng,labelFormate));					
				}
				if(ex_e_count==0){					
				sheet.addCell(new Label(6,(i+2)," "));		
				}else{
				sheet.addCell(new Number(6,(i+2),ex_e_count,labelFormate));					
				}
				sheet.addCell(new Label(7,(i+2),ex_company,labelFormate));
			    sheet.addCell(new Label(8,(i+2),ex_c_mate,labelFormate));
			    sheet.addCell(new Label(9,(i+2),ex_coat_mate,labelFormate));
			    sheet.addCell(new Label(10,(i+2),ex_location,labelFormate));
			   
			   if(ex_s_count==0){					
				sheet.addCell(new Label(11,(i+2)," "));		
				}else{
				sheet.addCell(new Number(11,(i+2),ex_s_count,labelFormate));					
				}
				
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
