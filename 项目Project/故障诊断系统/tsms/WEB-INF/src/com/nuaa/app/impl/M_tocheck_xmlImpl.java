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

import com.nuaa.app.M_tocheck_xml;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class M_tocheck_xmlImpl implements M_tocheck_xml{
	public void createExcelTocheck(HashMap hashMap,OutputStream os) throws WriteException, IOException{
		//HashMap to_grindHashMap=hashMap;		
		String filter=(String)hashMap.get("filter");
		String order=(String)hashMap.get("order");
		//final JSONArray jsonArray=new JSONArray();	
		final ArrayList pcArray=new ArrayList();
		String sql="";
		sql="select  *  from  v_mtest2v_indi_typecom where test_num='"+filter+"' order by "+order+"";
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
							paHashMap.put("indi_num", rs.getString("indi_num"));
							paHashMap.put("name", rs.getString("name"));
							paHashMap.put("spec", rs.getString("spec"));								
							paHashMap.put("diviv", rs.getString("diviv"));
							paHashMap.put("hq", rs.getString("hq"));
							paHashMap.put("company",rs.getString("company"));
							paHashMap.put("cycle", rs.getString("cycle"));								
							paHashMap.put("valid", rs.getString("valid"));
							paHashMap.put("location", rs.getString("location"));
							paHashMap.put("keeper", rs.getString("keeper"));
							paHashMap.put("startdate", rs.getString("startdate"));									
							paHashMap.put("status", rs.getString("status"));									
							paHashMap.put("test_num", rs.getString("test_num"));
							paHashMap.put("stuff_num", rs.getString("stuff_num"));
							paHashMap.put("s_time", rs.getString("s_time"));
							paHashMap.put("test_department", rs.getString("test_department"));
							paHashMap.put("group_num", rs.getString("group_num"));
							paHashMap.put("realname", rs.getString("realname"));
							
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
			Label title = new Label(0,0,"送检单",titleFormate);
			sheet.addCell(title);
			
			WritableFont common = new WritableFont(WritableFont.ARIAL,10);
			WritableCellFormat labelFormate = new WritableCellFormat(common);
			//单元格中的内容水平方向居中
			labelFormate.setAlignment(jxl.format.Alignment.CENTRE);
            //单元格中的内容垂直方向居中
			labelFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			
			sheet.mergeCells(0, 1, 2, 1);
			sheet.mergeCells(3, 1, 5, 1);
			sheet.mergeCells(6, 1, 8, 1);
			sheet.mergeCells(9, 1, 12, 1);
			//创建要显示的具体内容

			Label test_num = new Label(0,1,"    送检单号:     "+((HashMap)pcArray.get(0)).get("test_num"));
			sheet.addCell(test_num);
			Label test_department = new Label(3,1,"    送检单位: "+((HashMap)pcArray.get(0)).get("test_department"));
			sheet.addCell(test_department);
			Label s_time = new Label(6,1,"   送检日期:   "+((HashMap)pcArray.get(0)).get("s_time"));
			sheet.addCell(s_time);
			Label stuff_num = new Label(9,1,"   送检人员:   "+((HashMap)pcArray.get(0)).get("stuff_num"));
			sheet.addCell(stuff_num);
			
			//设置列宽
			sheet.setColumnView(0,5);
			sheet.setColumnView(1,16);
			sheet.setColumnView(2,12);
			sheet.setColumnView(3,10);			
			sheet.setColumnView(4,10);
			sheet.setColumnView(5,14);
			sheet.setColumnView(6,5);
			sheet.setColumnView(7,9);
			sheet.setColumnView(8,9);
			sheet.setColumnView(9,12);
			sheet.setColumnView(10,10);
			sheet.setColumnView(11,8);
			sheet.setColumnView(12,10);
			
			
			Label number = new Label(0,3,"序号",labelFormate);
			sheet.addCell(number);
			Label indi_num = new Label(1,3,"个体编号",labelFormate);
			sheet.addCell(indi_num);
			Label name = new Label(2,3,"名称",labelFormate);
			sheet.addCell(name);
			Label spec = new Label(3,3,"规格",labelFormate);
			sheet.addCell(spec);
			Label diviv = new Label(4,3,"分度值",labelFormate);
			sheet.addCell(diviv);
			Label company = new Label(5,3,"厂商",labelFormate);
			sheet.addCell(company);
			Label hq = new Label(6,3,"高质",labelFormate);
			sheet.addCell(hq);
			Label realname = new Label(7,3,"保管人",labelFormate);
			sheet.addCell(realname);
			Label group_num = new Label(8,3,"组别",labelFormate);
			sheet.addCell(group_num);
			Label location = new Label(9,3,"库存地点",labelFormate);
			sheet.addCell(location);	
			Label startdate = new Label(10,3,"启用日期",labelFormate);
			sheet.addCell(startdate);
			Label cycle = new Label(11,3,"鉴定周期",labelFormate);
			sheet.addCell(cycle);
			Label valid = new Label(12,3,"有效期",labelFormate);
			sheet.addCell(valid);
				
			for( int i=0;i<pcArray.size();i++){	
				
				String ex_indi_num="";
				String ex_name="";
				String ex_spec="";
				String ex_diviv="";
				String ex_company="";
				String ex_hq="";
				String ex_keeper="";
				String ex_group_num="";
				String ex_location="";
				String ex_startdate="";
				String ex_cycle="";
				String ex_valid="";
				
				if(((HashMap)pcArray.get(i)).get("indi_num")!=null){
					ex_indi_num=((HashMap)pcArray.get(i)).get("indi_num").toString();
				}				
				if(((HashMap)pcArray.get(i)).get("name")!=null){					
					ex_name=((HashMap)pcArray.get(i)).get("name").toString();					
				}
				if(((HashMap)pcArray.get(i)).get("spec")!=null){
					ex_spec=((HashMap)pcArray.get(i)).get("spec").toString();					
				}
				if(((HashMap)pcArray.get(i)).get("diviv")!=null){
					ex_diviv=((HashMap)pcArray.get(i)).get("diviv").toString();
				}
				if(((HashMap)pcArray.get(i)).get("company")!=null){
					ex_company=((HashMap)pcArray.get(i)).get("company").toString();
				}				
				if(((HashMap)pcArray.get(i)).get("hq")!=null){
					ex_hq=((HashMap)pcArray.get(i)).get("hq").toString();
				}
				if(((HashMap)pcArray.get(i)).get("realname")!=null){
					ex_keeper=((HashMap)pcArray.get(i)).get("realname").toString();
				}
				if(((HashMap)pcArray.get(i)).get("group_num")!=null){
					ex_group_num=((HashMap)pcArray.get(i)).get("group_num").toString();
				}
				if(((HashMap)pcArray.get(i)).get("location")!=null){
					ex_location=((HashMap)pcArray.get(i)).get("location").toString();
				}
				if(((HashMap)pcArray.get(i)).get("startdate")!=null){
					ex_startdate=((HashMap)pcArray.get(i)).get("startdate").toString();
				}			
				if(((HashMap)pcArray.get(i)).get("cycle")!=null){
					ex_cycle=((HashMap)pcArray.get(i)).get("cycle").toString();
				}
				if(((HashMap)pcArray.get(i)).get("valid")!=null){
					ex_valid=((HashMap)pcArray.get(i)).get("valid").toString();
				}
				
				sheet.addCell(new Number(0,(i+4),i+1,labelFormate));	
				sheet.addCell(new Label(1,(i+4),ex_indi_num,labelFormate));			
				sheet.addCell(new Label(2,(i+4),ex_name,labelFormate));
				sheet.addCell(new Label(3,(i+4),ex_spec,labelFormate));
				sheet.addCell(new Label(4,(i+4),ex_diviv,labelFormate));
				sheet.addCell(new Label(5,(i+4),ex_company,labelFormate));
				sheet.addCell(new Label(6,(i+4),ex_hq,labelFormate));	
			    sheet.addCell(new Label(7,(i+4),ex_keeper,labelFormate));
			    sheet.addCell(new Label(8,(i+4),ex_group_num,labelFormate));
			    sheet.addCell(new Label(9,(i+4),ex_location,labelFormate));
				sheet.addCell(new Label(10,(i+4),ex_startdate,labelFormate));
				sheet.addCell(new Number(11,(i+4),Integer.parseInt(ex_cycle),labelFormate));
				sheet.addCell(new Label(12,(i+4),ex_valid,labelFormate));
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


