
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

import com.nuaa.app.huizongExcel;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class huizongxmlImpl implements huizongExcel {

	
	//将查询出的用户信息导入Excel的方法
	public void createExcelhuizong(HashMap hashMap, OutputStream os)
			throws WriteException, IOException {
		// TODO 自动生成方法存根
		String sql="";
		final String id=(String)hashMap.get("id");
		sql="select distinct pclass, bclass, problem, reason, solution,  people, in_date, remark from t_huizong where id in ('',"+id+")"+"and "+(String)hashMap.get("filter");
		Logger.debug(sql);
		//System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"+sql);
		final ArrayList pcArray=new ArrayList();
    	try{
	    	DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {	
					try{
						int j=0;
						while(rs.next()){
							HashMap paHashMap=new HashMap();
							paHashMap.put("pclass", rs.getString("pclass"));
							paHashMap.put("bclass", rs.getString("bclass"));
							paHashMap.put("problem", rs.getString("problem"));
							paHashMap.put("reason", rs.getString("reason"));
							paHashMap.put("solution", rs.getString("solution"));
							paHashMap.put("people", rs.getString("people"));
							paHashMap.put("date", rs.getString("in_date"));
							paHashMap.put("remark", rs.getString("remark"));
							pcArray.add(j,paHashMap);
							j++;
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}				
			});	
	    	
//	    	读取导出日期
	    	Calendar c = Calendar.getInstance();
			final java.sql.Date curDate= new java.sql.Date(c.getTime().getTime());
			String CurDate = curDate.toString();
            //创建工作薄��������
	    	WritableWorkbook workbook = Workbook.createWorkbook(os);
	    	//创建工作表�����µ�
			WritableSheet sheet = workbook.createSheet("故障汇总", 0);
			//标题存放格式设定
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
			sheet.mergeCells(0, 0, 4, 0);
			Label fname = new Label(0, 0, "标题：故障汇总",seat);
			sheet.addCell(fname);
			sheet.mergeCells(0, 1, 4, 1);
			Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
			sheet.addCell(ftime);
			sheet.mergeCells(0, 2, 4, 2);
			Label Search = new Label(0, 2, "查询条件："+(String)hashMap.get("result"),wordsize);
			sheet.addCell(Search);
			
//			创建要显示的具体内容的题头
			Label order = new Label(0, 8, "序号",center);
			sheet.addCell(order);
			
			Label pclass = new Label(1, 8, "整机",center);
			sheet.addCell(pclass);
			Label bclass = new Label(2, 8, "部件",center);
			sheet.addCell(bclass);
			Label problem = new Label(3, 8, "问题",center);
			sheet.addCell(problem);
			Label reason = new Label(4, 8, "产生原因",center);
			sheet.addCell(reason);
			Label solution = new Label(5, 8, "解决办法",center);
			sheet.addCell(solution);
			Label people = new Label(6, 8, "责任人",center);
			sheet.addCell(people);
			Label date = new Label(7, 8, "整改期",center);
			sheet.addCell(date);
			Label remark = new Label(8, 8, "备注",center);
			sheet.addCell(remark);
			
			int nRow=9;
			int k = 1;
	    	for(int i=0;i<pcArray.size();i++){
				String Pclass="";
				String Bclass="";
				String Problem="";
				String Reason="";
				String Solution="";
				String People="";
				String Date="";
				String Remark="";
				
				if(((HashMap)pcArray.get(i)).get("remark")!=null){
					Remark=((HashMap)pcArray.get(i)).get("remark").toString();
				}
				if(((HashMap)pcArray.get(i)).get("date")!=null){
					Date=((HashMap)pcArray.get(i)).get("date").toString();
					Date=Date.substring(0,10);
				}
				if(((HashMap)pcArray.get(i)).get("people")!=null){
					People=((HashMap)pcArray.get(i)).get("people").toString();
				}
				if(((HashMap)pcArray.get(i)).get("solution")!=null){
					Solution=((HashMap)pcArray.get(i)).get("solution").toString();
				}
				if(((HashMap)pcArray.get(i)).get("reason")!=null){
					Reason=((HashMap)pcArray.get(i)).get("reason").toString();
				}
				if(((HashMap)pcArray.get(i)).get("problem")!=null){
					Problem=((HashMap)pcArray.get(i)).get("problem").toString();
				}
				if(((HashMap)pcArray.get(i)).get("bclass")!=null){
					Bclass=((HashMap)pcArray.get(i)).get("bclass").toString();
				}
				if(((HashMap)pcArray.get(i)).get("pclass")!=null){
					Pclass=((HashMap)pcArray.get(i)).get("pclass").toString();
				}
				sheet.addCell(new Number(0,nRow,k,center));
				sheet.addCell(new Label(1,nRow,Pclass,center));
				sheet.addCell(new Label(2,nRow,Bclass,center));
				sheet.addCell(new Label(3,nRow,Problem,center));
				sheet.addCell(new Label(4,nRow,Reason,center));
				sheet.addCell(new Label(5,nRow,Solution,center));
				sheet.addCell(new Label(6,nRow,People,center));
				sheet.addCell(new Label(7,nRow,Date,center));
				sheet.addCell(new Label(8,nRow,Remark,center));
				sheet.addCell(new Label(9,nRow,""));
				nRow++;
				k++;
	    	}
	    	
	    	//设置列宽
	    	sheet.setColumnView(0, 5);
	    	sheet.setColumnView(1, 10);
	    	sheet.setColumnView(2, 20); 
	    	sheet.setColumnView(3, 35); 
	    	sheet.setColumnView(4, 35); 
	    	sheet.setColumnView(5, 35); 
	    	sheet.setColumnView(6, 35);
	    	sheet.setColumnView(7, 20);
	    	sheet.setColumnView(8, 35);
	    	workbook.write();
			workbook.close();
			os.close();
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

}

