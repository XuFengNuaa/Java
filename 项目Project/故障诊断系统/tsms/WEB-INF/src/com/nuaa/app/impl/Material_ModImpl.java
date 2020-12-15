package com.nuaa.app.impl;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Cell;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import org.apache.commons.fileupload.FileItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.Material_Mod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class Material_ModImpl implements Material_Mod {
	@Override
	//增加材料信息
	public String addMaterial(HashMap hashmap) {
		final String[] count = new String[1];
		final String id=UUID.randomUUID().toString();
		String result = "true";
		final String mnum = (String)hashmap.get("mnum");
		final String classname = (String)hashmap.get("classname");
		final String name = (String)hashmap.get("name");
		final String measure = (String)hashmap.get("measure");
		final String specs = (String)hashmap.get("spec");
		
		final String modelnum = (String)hashmap.get("modelnum");
		String monthuses = (String)hashmap.get("monthuse");
		final float monthuse = Float.parseFloat(monthuses);
		String buycycles = (String)hashmap.get("buycycle");
		final int buycycle = Integer.parseInt(buycycles);
		final String companyid = (String)hashmap.get("companyid");
		String guaranteedates = (String)hashmap.get("guaranteedate");
		final int guaranteedate = Integer.parseInt(guaranteedates);
		String prewarndates = (String)hashmap.get("prewarndate");
		final String remark = (String)hashmap.get("remark");
		final int minstore = (int)(monthuse*(buycycle+1));
		final int maxstore;
		if(guaranteedate<=6){
			maxstore = (int)(minstore + monthuse*(guaranteedate)/2);
		}else {
			maxstore = (int) (minstore + monthuse*6);
		}
		final int singlepur;
		if(guaranteedate<=6){
			singlepur =  (int) (monthuse*(guaranteedate)/2);
		}else {
			singlepur = (int) (minstore + monthuse*6);
		}
		String sql = "select count(*) as ct from t_material where name='" + name + "'";
		try {
			DbUtil.execute(sql, new IResultSetProcessor(){
							public void process(ResultSet rs) throws SQLException{
								while(rs.next()){					
									count[0]=rs.getString("ct");
								}
							}
							});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "exception";
		}
		if("0".equals(count[0])){
			String[] sql1 = new String[1];
			sql1[0] = "insert into t_material(id,mnum,classname,name,modelnum,spec,companyid,measure,guaranteedate,minstore,maxstore" +
					",buycycle,monthuse,singlepur,remark) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				DbUtil.executeBatchs(sql1, new IArrayPreparedStatementProcessor(){
							public void process(PreparedStatement[] pstmts)throws SQLException {
							pstmts[0].setString(1,id);
							pstmts[0].setString(2,mnum);
							pstmts[0].setString(3,classname);
							pstmts[0].setString(4,name);
							pstmts[0].setString(5,modelnum);
							pstmts[0].setString(6,specs);
							pstmts[0].setString(7,companyid);
							pstmts[0].setString(8,measure);
							pstmts[0].setInt(9,guaranteedate);
							pstmts[0].setInt(10,minstore);
							pstmts[0].setInt(11,maxstore);
							pstmts[0].setInt(12,buycycle);
							pstmts[0].setFloat(13,monthuse);
							pstmts[0].setInt(14,singlepur);
							pstmts[0].setString(15,remark);
						    pstmts[0].addBatch();
									
								}
							});
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}else{
		result ="repeat";
	}
		return result;
}
//查询所有材料信息
	public JSONObject getQueryMaterial(HashMap queryMap) {
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select  count(*) as ct  from  T_MATERIAL where "+(String)queryMap.get("filter");
		final String [] count=new String[1];
		count[0] = "";
		try {
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if (rs.next()) {
						try {
							jsonObj.put("totalProperty",rs.getString("ct"));
						} catch (JSONException e) {
							// TODO 自动生成 catch 块
							e.printStackTrace();
						}
					}
				}	
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		int start=Integer.parseInt((String)queryMap.get("start"));
		int limit=Integer.parseInt((String)queryMap.get("limit"));
		String filter=(String)queryMap.get("filter");
		String order=(String)queryMap.get("order");
		sql="select * from (select * from  (select * from T_MATERIAL where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from T_MATERIAL where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order;
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							final JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							String companyid = rs.getString("companyid");
							String sql1 = "select company from T_COMPANY where id = '" + companyid +"'";
							DbUtil.execute(sql1, new IResultSetProcessor(){

								@Override
								public void process(ResultSet rs)
										throws SQLException {
									try {
										while(rs.next()){
										obj.put("company", rs.getString("company"));
									} 
									}catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								
							});
							obj.put("mnum",rs.getString("mnum"));
							obj.put("classname",rs.getString("classname"));
							obj.put("name",rs.getString("name"));
							obj.put("modelnum",rs.getString("modelnum"));
							obj.put("spec",rs.getString("spec"));
							/*obj.put("prewarndate",rs.getInt("prewarndate"));*/
							obj.put("measure",rs.getString("measure"));
							obj.put("guaranteedate",rs.getInt("guaranteedate"));
							obj.put("minstore",rs.getInt("minstore"));
							obj.put("maxstore",rs.getInt("maxstore"));
							obj.put("buycycle",rs.getInt("buycycle"));
							obj.put("monthuse",rs.getFloat("monthuse"));
							obj.put("singlepur",rs.getInt("singlepur"));
							obj.put("remark",rs.getString("remark"));
							jsonArray.put(obj);
						}	
							jsonObj.put("filedata", jsonArray);
							System.out.println(jsonObj.toString());
					} catch (JSONException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
				}				
			});	
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		Logger.debug(jsonObj.toString());
		return jsonObj;
	}
	@Override
	public String editMaterial(HashMap hashmap) {
		String id = (String) hashmap.get("id");
		final String[] count = new String[1];
		String result = "true";
		final String mnum = (String)hashmap.get("mnum");
		final String classname = (String)hashmap.get("classname");
		final String name = (String)hashmap.get("name");
		final String measure = (String)hashmap.get("measure");
		final String specs = (String)hashmap.get("spec");
		final int spec = Integer.parseInt(specs);
		final String modelnum = (String)hashmap.get("modelnum");
		String monthuses = (String)hashmap.get("monthuse");
		final int monthuse = Integer.parseInt(monthuses);
		String buycycles = (String)hashmap.get("buycycle");
		final int buycycle = Integer.parseInt(buycycles);
		final String companyid = (String)hashmap.get("companyid");
		String guaranteedates = (String)hashmap.get("guaranteedate");
		final int guaranteedate = Integer.parseInt(guaranteedates);
		/*String prewarndates = (String)hashmap.get("prewarndate");
		final int prewarndate = Integer.parseInt(prewarndates);*/
		final String remark = (String)hashmap.get("remark");
		final int minstore = monthuse*(buycycle+1);
		final int maxstore;
		if(guaranteedate<=6){
			maxstore = minstore + monthuse*(guaranteedate)/2;
		}else {
			maxstore = minstore + monthuse*6;
		}
		final int singlepur;
		if(guaranteedate<=6){
			singlepur =  monthuse*(guaranteedate)/2;
		}else {
			singlepur = minstore + monthuse*6;
		}
		
		String[] sqls = new String[1];
		sqls[0] = "update t_material set mnum=?,classname=?,name=?,modelnum=?,spec=?,companyid=?,measure=?,guaranteedate=?,minstore=?,maxstore=?,buycycle=?,monthuse=?,singlepur=?,remark=? where id = '" + id +"'";
		try {
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts)	throws SQLException {			
					pstmts[0].setString(1,mnum);
					pstmts[0].setString(2,classname);
					pstmts[0].setString(3,name);
					pstmts[0].setString(4,modelnum);
					pstmts[0].setInt(5,spec);
					/*pstmts[0].setInt(6,prewarndate);*/
					pstmts[0].setString(6,companyid);
					pstmts[0].setString(7,measure);
					pstmts[0].setInt(8,guaranteedate);
					pstmts[0].setInt(9,minstore);
					pstmts[0].setInt(10,maxstore);
					pstmts[0].setInt(11,buycycle);
					pstmts[0].setFloat(12,monthuse);
					pstmts[0].setInt(13,singlepur);
					pstmts[0].setString(14,remark);
					
					pstmts[0].addBatch();
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		
		return "true";
	}
	@Override
	public JSONObject viewMaterial(HashMap queryMap) {
		String id = (String) queryMap.get("id");
	    final JSONObject obj=new JSONObject();
		String sql = "select * from t_material where id = '" + id +"'";
		try {
			DbUtil.execute(sql, new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						
						
						try {
							obj.put("mnum", rs.getString("mnum"));
							obj.put("classname", rs.getString("classname"));
							obj.put("name", rs.getString("name"));
							obj.put("measure", rs.getString("measure"));
							obj.put("spec", rs.getInt("spec"));
							obj.put("monthuse", rs.getFloat("monthuse"));
							obj.put("modelnum", rs.getString("modelnum"));
							obj.put("remark", rs.getString("remark"));
							obj.put("buycycle", rs.getInt("buycycle"));
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String companyid = rs.getString("companyid");
						String sql1 = "select company from T_COMPANY where id = '" + companyid +"'";
						DbUtil.execute(sql1, new IResultSetProcessor(){
							public void process(ResultSet rs)
									throws SQLException {
								try {
									while(rs.next()){
									
									obj.put("company", rs.getString("company"));
								} 
								}catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
						});
						try {
							obj.put("companyid", rs.getString("companyid"));
							obj.put("guaranteedate", rs.getInt("guaranteedate"));
							/*obj.put("prewarndate", rs.getInt("prewarndate"));*/
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	@Override
	public String delMaterial(HashMap hashmap) {
		String id = (String) hashmap.get("id");
		System.out.println(id);
		String result = "true";
		String []sqls=new String[1];
		sqls[0] = "delete from t_material where id in (''," + id + ")";
		try {
			DbUtil.executeBatchs(sqls,
					new IArrayPreparedStatementProcessor() {
						public void process(PreparedStatement[] pstmts)throws SQLException {						
							pstmts[0].addBatch();
						}
						});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "false";
		}
		
		return result;
	}
	@Override
	public HashMap uploadfile(HashMap fileHmap) {
		final HashMap fileHashMap = fileHmap;	
    	final HashMap returnHashMap=new HashMap();	
    	String tempPath = "\\web\\tempfile\\"; // 用于存放临时文件的目录
		String trace = "";
		String filename = "";
		String sclassid = "";
		String dir = (String)fileHashMap.get("dir");
		//建临时文件夹
		File fii=new File(dir+tempPath);
		if(!fii.exists()) {
		   fii.mkdirs(); 
		}
		try{	
			System.out.println("执行到这里了！！！");
			List fileItems = (List)fileHashMap.get("fileItems");
			Iterator i = fileItems.iterator();
			while(i.hasNext()){		
    			  FileItem fi = (FileItem)i.next();
    			  String fileName = fi.getName();
  			 	  if(fileName!=null){
	  			 	//	Calendar date = Calendar.getInstance();
	  				//	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-kk时-mm分-ss秒");
	  				//	final String now = format.format(date.getTime())  ;
    					String[] s=fileName.split("\\.");
    					filename = "导入材料信息."+s[s.length-1];
    					System.out.println("1111111111111111"+filename);
    					System.out.println("22222222222"+s);
    					String[] n = filename.split("\\.");
    					sclassid = n[0];
    					trace = tempPath+filename;
    					fi.write(new File(dir+trace));
    					returnHashMap.put("filename",sclassid);
    					System.out.println("***********"+sclassid);
    			  }
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return returnHashMap;
	}
	
	public String editMaterial1(HashMap hashmap) {
		String id = (String) hashmap.get("id");
		final String[] count = new String[1];
		String result = "true";
		final String mnum = (String)hashmap.get("mnum");
		final String classname = (String)hashmap.get("classname");
		final String name = (String)hashmap.get("name");
		final String measure = (String)hashmap.get("measure");
		final String specs = (String)hashmap.get("spec");
		final int spec = Integer.parseInt(specs);
		final JSONObject json = new JSONObject();
		final String modelnum = (String)hashmap.get("modelnum");
		/*String monthuses = (String)hashmap.get("monthuse");
		final int monthuse = Integer.parseInt(monthuses);*/
		//计算monthuse
		String sql = "select incount from vmaterial_com where indate > sysdate - 180 and indate <= sysdate and typeid = '"+id+"'";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					int insum = 0;
					System.out.println("计算入库量");
					while(rs.next()){
					   insum += rs.getInt("incount");
					}
					try {
						json.put("incount",insum);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql1 = "select outcount from v_matout_mnum where typeid = '" + id +"' and outdate <= sysdate and outdate > sysdate-180";
		try {
			DbUtil.execute(sql1,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					int outsum = 0;
					System.out.println("计算出库量");
					while(rs.next()){
					   outsum += rs.getInt("outcount");
					}
					try {
						json.put("outcount",outsum);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//计算报废数量
		String sql10 = "select delcount from v_del_com where typeid = '" + id +"' and deldate <= sysdate and deldate > sysdate-180";
		try {
			DbUtil.execute(sql10,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					int delsum = 0;
					System.out.println("计算报废量");
					while(rs.next()){
						delsum += rs.getInt("delcount");
					}
					try {
						json.put("delcount",delsum);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			
		try {
		   final float monthuse = (float) ((json.getDouble("incount") - json.getDouble("outcount")-json.getDouble("delcount"))/6);
		   System.out.println(json.getInt("incount")+" "+json.getInt("outcount")+" "+json.getInt("delcount"));
		   System.out.println(monthuse);
		   String buycycles = (String)hashmap.get("buycycle");
			final int buycycle = Integer.parseInt(buycycles);
			final String companyid = (String)hashmap.get("companyid");
			String guaranteedates = (String)hashmap.get("guaranteedate");
			final int guaranteedate = Integer.parseInt(guaranteedates);
			/*String prewarndates = (String)hashmap.get("prewarndate");
			final int prewarndate = Integer.parseInt(prewarndates);*/
			final String remark = (String)hashmap.get("remark");
			final int minstore = (int) (monthuse*(buycycle+1));
			final int maxstore;
			if(guaranteedate<=6){
				maxstore = (int) (minstore + monthuse*(guaranteedate)/2);
			}else {
				maxstore = (int) (minstore + monthuse*6);
			}
			final int singlepur;
			if(guaranteedate<=6){
				singlepur =  (int) (monthuse*(guaranteedate)/2);
			}else {
				singlepur = (int) (minstore + monthuse*6);
			}
			String[] sqls = new String[1];
			sqls[0] = "update t_material set mnum=?,classname=?,name=?,modelnum=?,spec=?,companyid=?,measure=?,guaranteedate=?,minstore=?,maxstore=?,buycycle=?,monthuse=?,singlepur=?,remark=? where id = '" + id +"'";
			try {
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)	throws SQLException {			
						pstmts[0].setString(1,mnum);
						pstmts[0].setString(2,classname);
						pstmts[0].setString(3,name);
						pstmts[0].setString(4,modelnum);
						pstmts[0].setInt(5,spec);
						/*pstmts[0].setInt(6,prewarndate);*/
						pstmts[0].setString(6,companyid);
						pstmts[0].setString(7,measure);
						pstmts[0].setInt(8,guaranteedate);
						pstmts[0].setInt(9,minstore);
						pstmts[0].setInt(10,maxstore);
						pstmts[0].setInt(11,buycycle);
						pstmts[0].setFloat(12,monthuse);
						pstmts[0].setInt(13,singlepur);
						pstmts[0].setString(14,remark);
						
						pstmts[0].addBatch();
					}
				});
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "false";
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "true";
	}
//材料信息导入函数
	public String getExcel(HashMap fileHmap) {
		HashMap xlsHashMap=fileHmap;
		final String []result = new String[1];
		result[0]="true";
		String dir = (String)xlsHashMap.get("dir"); 
		final String[] pa = new String[30];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		
		WorkbookSettings setting=new WorkbookSettings();   
		setting.setEncoding("ISO-8859-1");   
		Workbook book = null;
		try {
			book = Workbook.getWorkbook(new File(dir+"\\web\\tempfile\\导入材料信息.xls"),setting);
		} catch (BiffException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
        //获得第一个工作表对象 
		Sheet sheet=book.getSheet(0); 
    	//得到第一列第一行的单元格 
		Pattern pattern1 = Pattern.compile("^(6|12|18|24)$");  //匹配鉴定保质期
		Pattern pattern2 = Pattern.compile("^(1|2|3|6)$");  //匹配鉴定保质期
		Pattern pattern3 = Pattern.compile("^(0|[1-9][0-9]*)$"); //匹配整数
		Pattern pattern4 = Pattern.compile("^([0-9]+[.][1-9]*$)");
		System.out.println(sheet.getRows());
		for(int x=1;x<sheet.getRows();x++){
			//首先验证物资编码是否为空,如果为空则提示物资编码为空
			System.out.println(x);
			System.out.println(sheet.getCell(0,x).getContents());
			if(sheet.getCell(0,x).getContents().equals("")){//物资编码是否为空
				result[0]="mnumnullfalse";
				break;
			}

			if(sheet.getCell(1,x).getContents().equals("")){//类别是否为空
				result[0]="classnamenullfalse";
				break;
			}
			if(sheet.getCell(2,x).getContents().equals("")){//材料名称是否为空
				result[0]="namenullfalse";
				break;
			}
			/*if(sheet.getCell(3,x).getContents().equals("")){//型号是否为空
				result[0]="modelnullfalse";
				break;
			}*/
		/*	Matcher matcher5 = pattern3.matcher(sheet.getCell(4,x).getContents());//规格
			boolean a = matcher5.matches();*/
			if(sheet.getCell(4,x).getContents().equals("")){
				result[0]="specnullfalse";
				break;
			}/*else if(!(sheet.getCell(4,x).getContents().equals(""))&&(a=false)){//规格是否为数字
				result[0]="specunnumfalse";
				break;
			}*/
			
			//生产厂商是否为空
			if(sheet.getCell(5,x).getContents().equals("")){//生产厂商是否为空
				result[0]="companynullfalse";
				break;
			}
			//计量单位
			if(sheet.getCell(11,x).getContents().equals("")){//计量单位是否为空
				result[0]="measurenullfalse";
				break;
			}
			//保质期
			Matcher matcher11 = pattern2.matcher(sheet.getCell(12,x).getContents());
			boolean z = matcher11.matches();
			if(sheet.getCell(13,x).getContents().equals("")){
				result[0]="guanullfalse";
				break;
			}else if(!(sheet.getCell(13,x).getContents().equals(""))&&(z=false)){
				result[0]="guaunnumfalse";
				break;
			}
			
			//采购周期
			Matcher matcher9 = pattern2.matcher(sheet.getCell(13,x).getContents());//采购周期是否为空
			boolean e = matcher9.matches();
			if(sheet.getCell(13,x).getContents().equals("")){
				result[0]="cyclenullfalse";
				break;
			}else if(!(sheet.getCell(13,x).getContents().equals(""))&&(e=false)){//采购周期是否为数字
				result[0]="cycleunnumfalse";
				break;
			}
			//月用量
			Matcher matcher10 = pattern4.matcher(sheet.getCell(14,x).getContents());//月用量是否为空
			boolean f = matcher9.matches();
			if(sheet.getCell(14,x).getContents().equals("")){
				result[0]="monthusenullfalse";
				break;
			}else if(!(sheet.getCell(14,x).getContents().equals(""))&&(e=false)){//月用量是否为数字
				result[0]="monthuseunnumfalse";
				break;
			}
		}
		
		
		//写入数据库
		if(result[0].equals("true")){
			for(int x = 1; x < sheet.getRows(); x++){
				Cell cell1=sheet.getCell(0,x);//物资编码
				Cell cell2=sheet.getCell(1,x);	//类别	
				Cell cell3=sheet.getCell(2,x);	//名称
				Cell cell4=sheet.getCell(3,x); //型号
				Cell cell5= sheet.getCell(4,x); //规格
				/*NumberCell cell6=(NumberCell)sheet.getCell(5,x); //预警天数
*/				Cell cell6=sheet.getCell(5,x); //生产厂商
				Cell cell7=sheet.getCell(6,x); //地址
				Cell cell8=sheet.getCell(7,x); //联系人
				Cell cell9=sheet.getCell(8,x); //电话
				Cell cell10=sheet.getCell(9,x); //邮箱
				Cell cell11=sheet.getCell(10,x); //生产厂商备注
				Cell cell12=sheet.getCell(11,x); //计量单位
				NumberCell cell13=(NumberCell)sheet.getCell(12,x); //保质期
				NumberCell cell14=(NumberCell)sheet.getCell(13,x); //采购周期
				NumberCell cell15=(NumberCell)sheet.getCell(14,x); //月用量
				Cell cell16=sheet.getCell(15,x); //备注
				//获取生产厂商
				final String []company=new String[6];
				company[0]=cell6.getContents();
				company[1]=cell7.getContents();
				company[2]=cell8.getContents();
				company[3]=cell9.getContents();
				company[4]=cell10.getContents();
				company[5]=cell11.getContents();
				System.out.println("*******************************************************************************");
				//获取材料具体类型信息
			    final String []type = new String[13];
				type[0]=cell1.getContents();//物资编码
				type[1]=cell2.getContents();//类别
				type[2]=cell3.getContents();//名称
				type[3]=cell4.getContents();//型号
				type[4]=cell5.getContents();//规格
				/*type[5]=cell6.getContents();//预警天数	
*/				type[6]=cell12.getContents();//计量单位
				type[7]=cell13.getContents();//保质期
				type[10]=cell14.getContents();//采购周期
				type[11]=cell15.getContents();//月用量
				type[12]=cell16.getContents();//备注
				
				//判断是否存在厂商
				final String[] companyid = new String[1];
				 String sql1="select id from T_COMPANY where COMPANY='"+cell7.getContents()+"'";
				 try {
					DbUtil.execute(sql1,new IResultSetProcessor(){
						public void process(ResultSet rs) throws SQLException {
							if(rs.next()){
								companyid[0] = rs.getString("id");
								
							}else{
								final String id = UUID.randomUUID().toString();  //自动生成id
						 	    companyid[0]=id;  //记录厂商id
						 	   
								String []sqlcom = new String[1];
								sqlcom[0]= "insert into T_COMPANY(id,company,address,contact_person,phone,email,remark) values(?,?,?,?,?,?,?)";
								DbUtil.executeBatchs(sqlcom,
										new IArrayPreparedStatementProcessor() {
											public void process(PreparedStatement[] pstmts)throws SQLException {
											pstmts[0].setString(1,id);
										if(company[0]!=null){
											pstmts[0].setString(2,(String)company[0]);//userid
										}else{
											pstmts[0].setString(2," ");//userid
										}
										if(company[1]!=null){
											pstmts[0].setString(3,(String)company[1]);//username
										}else{
											pstmts[0].setString(3," ");//username
										}
										if(company[2]!=null){
											pstmts[0].setString(4,(String)company[2]);//pwd
										}else{
											pstmts[0].setString(4," ");//pwd
										}
										if(company[3]!=null){
											pstmts[0].setString(5,(String)company[3]);//email
										}else{
											pstmts[0].setString(5," ");//email
										}
										if(company[4]!=null){
											pstmts[0].setString(6,(String)company[4]);//mobilephone
										}else{
											pstmts[0].setString(6," ");//mobilephone
										}
										if(company[5]!=null){
											pstmts[0].setString(7,(String)company[5]);//telephone
										}else{
											pstmts[0].setString(7," ");//telephone
										}
										pstmts[0].addBatch();
										
									}
								});	
							}
							
						}
						 
					 });
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result[0]="error";
				}
				 System.out.println("写入材料名称信息");
				 //向数据库中插入材料 名称信息,首先判断是否存在材料名称是否存在
				 final String []typeid=new String[1];
				 typeid[0] = "";
				 String sql = "select id from t_material where name = '" + cell3.getContents() + "'";
				 try {
					DbUtil.execute(sql,new IResultSetProcessor(){
						public void process(ResultSet rs) throws SQLException {
							while(rs.next()){					
								typeid[0]=rs.getString("id");
								System.out.println("****"+typeid[0]);
							}
						}
					 });
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				 //若id不存在，则生成id，插入数据库
				if(typeid[0].equals("")){
					//保质期
					System.out.println("执行插入操作");
					final int guaranteedate = Integer.parseInt(type[7]);
					//月用量
					int monthuse = Integer.parseInt(type[11]);
					//采购周期
					int buycycle = Integer.parseInt(type[10]);
					//计算最小安全库存
					final int minstore = monthuse * buycycle;
					final int maxstore;
					if(guaranteedate<=6){
						 maxstore = (int)minstore + monthuse * guaranteedate/2;
					}else{
						maxstore = (int)minstore + monthuse * 6;
					}
					//计算单次采购量
					final int singlepur;
					if(guaranteedate<=6){
						singlepur = monthuse  * guaranteedate/2;
					}else{
						singlepur = minstore + monthuse * guaranteedate/2;
					}
					
									
					final String create_typeid=UUID.randomUUID().toString();  //自动生成id
					typeid[0] = create_typeid;
					String []sqltype = new String[1];
					sqltype[0] = "insert into t_material (id,mnum,classname,name,modelnum,spec,companyid,measure,guaranteedate,minstore,maxstore,buycycle,monthuse,singlepur,remark)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					try {
						DbUtil.executeBatchs(sqltype,new IArrayPreparedStatementProcessor(){
							public void process(PreparedStatement[] pstmts)throws SQLException {
								pstmts[0].setString(1,typeid[0]);
								pstmts[0].setString(2,type[0]);
								pstmts[0].setString(3,type[1]);
								pstmts[0].setString(4,type[2]);
								pstmts[0].setString(5,type[3]);
								pstmts[0].setString(6,type[4]);
								pstmts[0].setString(7,companyid[0]);
								pstmts[0].setString(8,type[6]);
								pstmts[0].setInt(9,guaranteedate);
								pstmts[0].setInt(10,minstore);
								pstmts[0].setInt(11,maxstore);
								pstmts[0].setInt(12,Integer.parseInt(type[10]));
								pstmts[0].setInt(13,Integer.parseInt(type[11]));
								pstmts[0].setInt(14,singlepur);
								pstmts[0].setString(15,type[12]);
								pstmts[0].addBatch();
							}
						});
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						result[0]="error";
					}
				}
				//若id不为空，则更新数据库中的信息，根据名称更新
				else{
					System.out.println("执行更新操作");
					final int guaranteedate = Integer.parseInt(type[7]);
					//月用量
					int monthuse = Integer.parseInt(type[11]);
					//采购周期
					int buycycle = Integer.parseInt(type[10]);
					//计算最小安全库存
					final int minstore = monthuse * buycycle;
					final int maxstore;
					if(guaranteedate<=6){
						 maxstore = (int)minstore + monthuse * guaranteedate/2;
					}else{
						 maxstore = (int)minstore + monthuse * 6;
					}
					//计算单次采购量
					final int singlepur;
					if(guaranteedate<=6){
						singlepur = monthuse  * guaranteedate/2;
					}else{
						singlepur = minstore + monthuse * guaranteedate/2;
					}
					String[] s = new String[1];
					s[0] = "update t_material set mnum=?,classname=?,modelnum=?,spec=?,companyid=?,measure=?,guaranteedate=?,minstore=?,maxstore=?,buycycle=?,monthuse=?,singlepur=?,remark=? where name='"+type[2]+"'";
					Logger.debug(s[0]);
					try {
						DbUtil.executeBatchs(s,new IArrayPreparedStatementProcessor(){
							public void process(PreparedStatement[] pstmts)	throws SQLException {			
								pstmts[0].setString(1,type[0]);
								pstmts[0].setString(2,type[1]);
								pstmts[0].setString(3,type[3]);
								pstmts[0].setString(4,type[4]);
								pstmts[0].setString(5,companyid[0]);
								pstmts[0].setString(6,type[6]);
								pstmts[0].setString(7,type[7]);
								pstmts[0].setInt(8,minstore);
								pstmts[0].setInt(9,maxstore);
								pstmts[0].setString(10,type[10]);
								pstmts[0].setString(11,type[11]);
								pstmts[0].setInt(12,singlepur);
								pstmts[0].setString(13,type[12]);
								pstmts[0].addBatch();
						}
						});
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return result[0];
	}
}
