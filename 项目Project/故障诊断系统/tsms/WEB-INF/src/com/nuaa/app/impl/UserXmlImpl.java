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

import com.nuaa.app.UserExcel;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class UserXmlImpl implements UserExcel {

	
	//将查询出的用户信息导入Excel的方法
	public void createExcelUser(HashMap hashMap, OutputStream os)
			throws WriteException, IOException {
		// TODO 自动生成方法存根
		String sql="";
		final String stuff_num1=(String)hashMap.get("stuff_num");
		sql="select distinct stuff_num, realname, group_num, rolename, remark from v_user2role where stuff_num in ('',"+stuff_num1+") and "+(String)hashMap.get("filter");
		System.out.println("44444444444444444444444444"+sql);
		Logger.debug(sql);
		final ArrayList pcArray=new ArrayList();
    	try{
	    	DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {	
					try{
						int j=0;
						while(rs.next()){
							HashMap paHashMap=new HashMap();
							paHashMap.put("stuff_num", rs.getString("stuff_num"));
							paHashMap.put("rolename", rs.getString("rolename"));
							paHashMap.put("realname", rs.getString("realname"));
							paHashMap.put("group_num", rs.getString("group_num"));
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
			WritableSheet sheet = workbook.createSheet("用户信息", 0);
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
			Label fname = new Label(0, 0, "标题：用户信息",seat);
			sheet.addCell(fname);
			sheet.mergeCells(0, 1, 4, 1);
			Label ftime = new Label(0, 1, "导出日期："+CurDate,wordsize);
			sheet.addCell(ftime);
			sheet.mergeCells(0, 2, 4, 2);
			Label Search = new Label(0, 2, "查询条件："+(String)hashMap.get("result"),wordsize);
			sheet.addCell(Search);
			
//			创建要显示的具体内容的题头
			Label order = new Label(0, 5, "序号",center);
			sheet.addCell(order);
			
			Label Stuff_mnum = new Label(1, 5, "用户工号",center);
			sheet.addCell(Stuff_mnum);
			Label Rolename = new Label(2, 5, "用户角色",center);
			sheet.addCell(Rolename);
			Label Realname = new Label(3, 5, "用户姓名",center);
			sheet.addCell(Realname);
			
			Label Group_num = new Label(4, 5, "联系电话",center);
			sheet.addCell(Group_num);
			
			Label Remark = new Label(5, 5, "备注",center);
			sheet.addCell(Remark);
			
			int nRow=6;
			int k = 1;
	    	for(int i=0;i<pcArray.size();i++){
				String stuff_num="";
				String rolename="";
				String realname="";
				String group_num="";
				String remark="";
				
				if(((HashMap)pcArray.get(i)).get("remark")!=null){
					remark=((HashMap)pcArray.get(i)).get("remark").toString();
				}
				if(((HashMap)pcArray.get(i)).get("group_num")!=null){
					group_num=((HashMap)pcArray.get(i)).get("group_num").toString();
				}
				if(((HashMap)pcArray.get(i)).get("realname")!=null){
					realname=((HashMap)pcArray.get(i)).get("realname").toString();
				}
				if(((HashMap)pcArray.get(i)).get("rolename")!=null){
					rolename=((HashMap)pcArray.get(i)).get("rolename").toString();
				}
				if(((HashMap)pcArray.get(i)).get("stuff_num")!=null){
					stuff_num=((HashMap)pcArray.get(i)).get("stuff_num").toString();
				}
				
				sheet.addCell(new Number(0,nRow,k,center));
				sheet.addCell(new Label(1,nRow,stuff_num,center));
				sheet.addCell(new Label(2,nRow,rolename,center));
				sheet.addCell(new Label(3,nRow,realname,center));
				sheet.addCell(new Label(4,nRow,group_num,center));
				sheet.addCell(new Label(5,nRow,remark,center));
				sheet.addCell(new Label(6,nRow,""));
				nRow++;
				k++;
	    	}
	    	
	    	//设置列宽
	    	sheet.setColumnView(0, 5);
	    	sheet.setColumnView(1, 20);
	    	sheet.setColumnView(2, 15); 
	    	sheet.setColumnView(3, 20); 
	    	sheet.setColumnView(4, 30); 
	    	sheet.setColumnView(5, 40); 
	    	workbook.write();
			workbook.close();
			os.close();
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

}
