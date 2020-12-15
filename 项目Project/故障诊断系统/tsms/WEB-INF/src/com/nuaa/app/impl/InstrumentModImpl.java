package com.nuaa.app.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import org.apache.commons.fileupload.FileItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nuaa.app.InstrumentMod;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

public class InstrumentModImpl implements InstrumentMod{
	//添加仪表信息
	public String addInstrument(HashMap hashmap) {
		final String serialnum = (String) hashmap.get("serialnum");
		final String enterprisenum = (String) hashmap.get("enterprisenum");//企业编号
		final String outnum = (String) hashmap.get("outnum");
		final String modelnum = (String) hashmap.get("modelnum");
		final String name = (String) hashmap.get("name");
		final String classname = (String) hashmap.get("classname");
		final String nowclass = (String) hashmap.get("nowclass");
		final String detectdate = (String) hashmap.get("detectdate");
		final String nextdate = (String) hashmap.get("nextdate");
		final String detectmodel = (String) hashmap.get("detectmodel");
		final String state = (String) hashmap.get("state");
		final String execution = (String) hashmap.get("execution");
		final String belongteam = (String) hashmap.get("belongteam");
		final String person = (String) hashmap.get("person");
		
		final String id = UUID.randomUUID().toString();
		final String []count = new String[1];
		String sql = "select count (*) as ct from t_instrument where enterprisenum = '" + enterprisenum +"'";
		try {
			DbUtil.execute(sql,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						count[0] = rs.getString("ct");
					}
					
				}
				
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if("0".equals(count[0])){
			String []sqls = new String[1];
			sqls[0] = "insert into t_instrument (id,serialnum,enterprisenum,outnum,name,modelnum,classname,nowclass,detectdate,nextdate,detectmode,state,execution,blongteam,person) values(?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?,?,?)";
			try {
				DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
					public void process(PreparedStatement[] pstmts)throws SQLException {
						pstmts[0].setString(1,id);
						pstmts[0].setString(2,serialnum);
						pstmts[0].setString(3,enterprisenum);
						pstmts[0].setString(4,outnum);
						pstmts[0].setString(5,modelnum);
						pstmts[0].setString(6,name);
						pstmts[0].setString(7,classname);
						pstmts[0].setString(8,nowclass);
						pstmts[0].setString(9,detectdate);
						pstmts[0].setString(10,nextdate);
						pstmts[0].setString(11,detectmodel);
						pstmts[0].setString(12,state);
						pstmts[0].setString(13,execution);
						pstmts[0].setString(14,belongteam);
						pstmts[0].setString(15,person);
						pstmts[0].addBatch();
					}
					
				});
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "true";
		}else{
			return "repeat";
		}
	}

	@Override
	//与表格建立数据源查询函数
	public JSONObject queryInstrument(HashMap hashmap) {
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select  count(*) as ct  from  t_instrument where "+(String)hashmap.get("filter");
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
		int start=Integer.parseInt((String)hashmap.get("start"));
		int limit=Integer.parseInt((String)hashmap.get("limit"));
		String filter=(String)hashmap.get("filter");
		String order=(String)hashmap.get("order");
		sql="select * from (select * from  (select * from t_instrument where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select * from t_instrument where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order;
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							final JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("serialnum",rs.getString("serialnum"));
							obj.put("enterprisenum",rs.getString("enterprisenum"));
							obj.put("outnum",rs.getString("outnum"));
							obj.put("name",rs.getString("name"));
							obj.put("modelnum",rs.getString("modelnum"));
							obj.put("classname",rs.getString("classname"));
							obj.put("nowclass",rs.getString("nowclass"));
							obj.put("detectdate",rs.getDate("detectdate"));
							obj.put("nextdate",rs.getDate("nextdate"));
							obj.put("detectmode",rs.getString("detectmode"));
							obj.put("state",rs.getString("state"));
							obj.put("execution",rs.getString("execution"));
							obj.put("blongteam",rs.getString("blongteam"));
							obj.put("person",rs.getString("person"));
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
		return jsonObj;
	}

	@Override
	//查看仪表全部信息
	public JSONObject viewInstrument(HashMap hashmap) {
		String id = (String) hashmap.get("id");
	    final JSONObject obj=new JSONObject();
		String sql = "select * from t_instrument where id = '" + id +"'";
		try {
			DbUtil.execute(sql, new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						try {
							obj.put("id", rs.getString("id"));
							obj.put("serialnum",rs.getString("serialnum"));
							obj.put("enterprisenum",rs.getString("enterprisenum"));
							obj.put("outnum",rs.getString("outnum"));
							obj.put("name",rs.getString("name"));
							obj.put("modelnum",rs.getString("modelnum"));
							obj.put("classname",rs.getString("classname"));
							obj.put("nowclass",rs.getString("nowclass"));
							obj.put("detectdate",rs.getDate("detectdate"));
							obj.put("nextdate",rs.getDate("nextdate"));
							obj.put("detectmode",rs.getString("detectmode"));
							obj.put("state",rs.getString("state"));
							obj.put("execution",rs.getString("execution"));
							obj.put("blongteam",rs.getString("blongteam"));
							obj.put("person",rs.getString("person"));
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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
	//修改仪表信息函数
	public String editInstrument(HashMap hashmap) {
		String pattern = "yyy-MM-dd"; //首先定义时间格式
        SimpleDateFormat format = new SimpleDateFormat(pattern);//然后创建一个日期格式化类
		String result = "true";
		final String id = (String) hashmap.get("id");
		final String serialnum = (String) hashmap.get("serialnum");
		final String enterprisenum = (String) hashmap.get("enterprisenum");//企业编号
		final String outnum = (String) hashmap.get("outnum");
		final String modelnum = (String) hashmap.get("modelnum");
		final String name = (String) hashmap.get("name");
		final String classname = (String) hashmap.get("classname");
		final String nowclass = (String) hashmap.get("nowclass");
		final  String detectdate =  (String) hashmap.get("detectdate");
		final String nextdate =  (String) hashmap.get("nextdate");
		final String detectmodel = (String) hashmap.get("detectmodel");
		final String state = (String) hashmap.get("state");
		final String execution = (String) hashmap.get("execution");
		final String belongteam = (String) hashmap.get("belongteam");
		final String person = (String) hashmap.get("person");
		final String[] count = new String[1];
		System.out.println(detectdate);
		System.out.println(nextdate);
		String sql1 = "select count (*) as ct from t_instrument where enterprisenum = '" +enterprisenum+"' and id != '"+id+"'";
		try {
			DbUtil.execute(sql1,new IResultSetProcessor(){
				public void process(ResultSet rs) throws SQLException {
					while(rs.next()){
						count[0] = rs.getString("ct");
					}
				}
				
			});
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] sqls = new String[1];
		if("0".equals(count[0])){
		sqls[0] = "update t_instrument set serialnum=?,enterprisenum=?,outnum=?,modelnum=?,name=?,classname=?,nowclass=?,detectdate=to_date(?,'yyyy/mm/dd'),nextdate=to_date(?,'yyyy/mm/dd'),detectmode=?,state=?,execution=?,blongteam=?,person=? where id = '" + id +"'";
		Logger.debug(sqls[0]);
		try {
			DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
				public void process(PreparedStatement[] pstmts)	throws SQLException {			
					pstmts[0].setString(1,serialnum);
					pstmts[0].setString(2,enterprisenum);
					pstmts[0].setString(3,outnum);
					pstmts[0].setString(4,modelnum);
					pstmts[0].setString(5,name);
					pstmts[0].setString(6,classname);
					pstmts[0].setString(7,nowclass);
					pstmts[0].setString(8,detectdate);
					pstmts[0].setString(9,nextdate);
					pstmts[0].setString(10,detectmodel);
					pstmts[0].setString(11,state);
					pstmts[0].setString(12,execution);
					pstmts[0].setString(13,belongteam);
					pstmts[0].setString(14,person);
					
					pstmts[0].addBatch();
					
				}
			});
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "false";
			return result;
		}}
		else{
			result = "repeat";
		}
		return result;
		
	}

	@Override
	public String delInstrument(HashMap hashmap) {
		String id = (String) hashmap.get("id");
		System.out.println(id);
		String result = "true";
		String []sqls=new String[1];
		sqls[0] = "delete from t_instrument where id in (''," + id + ")";
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
			return result;
		}
		return result;
	}

//导入函数
	public String getExcel(HashMap fileHmap) {
		HashMap xlsHashMap=fileHmap;
		final String []result = new String[1];
		result[0] = "true";
		String dir = (String)xlsHashMap.get("dir"); 
		final String[] pa = new String[30];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		
		WorkbookSettings setting=new WorkbookSettings();   
		setting.setEncoding("ISO-8859-1");   
		Workbook book = null;
		try {
			book = Workbook.getWorkbook(new File(dir+"\\web\\tempfile\\仪表信息表.xls"),setting);
		} catch (BiffException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		Sheet sheet=book.getSheet(0); 
    	//得到第一列第一行的单元格 
		Pattern pattern = Pattern.compile("^((19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$"); 
		Pattern pattern5 = Pattern.compile("^\\d{2}-\\d{1,2}-\\d{1,2}$");//匹配excel中日期格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for(int x = 1;x < sheet.getRows();x++){
			boolean b = true; 
			boolean c = true;
			//判断有无待检日期，若有，判断格式是否正确						
			Matcher matcher7 = pattern5.matcher(sheet.getCell(7,x).getContents());//待检日期	
			System.out.println(sheet.getCell(7,x).getContents());
			boolean h = matcher7.matches();						
			System.out.println(h);
			if(!sheet.getCell(7,x).equals("")&&h==false){
				result[0]="datefalse";
			}else if(!sheet.getCell(7,x).equals("")&&h==true){							
				DateCell check3=(DateCell)sheet.getCell(7,x);//待检日期
				java.util.Date date1 = check3.getDate();  //待检日期
								
				//判断格式是否正确
				Matcher matcher = pattern.matcher(format.format(date1));
				b = matcher.matches();
				System.out.println(b);
			}
			
			
			if(b==false||c==false){
				result[0]="datefalse";							
				break;
			}
			
			for(int j=0;j<sheet.getColumns();j++){
				if(sheet.getCell(j,x).equals("")){
					result[0]="nullfalse";
					break;
				}
			}
		}
			System.out.println(result[0]);
		
		//写入数据库
		if(result[0].equals("true")){
			for(int x=1;x<sheet.getRows();x++){
				System.out.println(x);
				Cell cell1=sheet.getCell(0,x);	//序号
				Cell cell2=sheet.getCell(1,x);	//企业编号	
				Cell cell3=sheet.getCell(2,x);	//出厂编号
				Cell cell4=sheet.getCell(3,x); //型号
				Cell cell5=sheet.getCell(4,x);//名称
				Cell cell6=sheet.getCell(5,x);//类别
				Cell cell7=sheet.getCell(6,x);//现场类别
				DateCell cell8=(DateCell)sheet.getCell(7,x);//待检日期
				DateCell cell9=(DateCell)sheet.getCell(8,x);//下次待检日期
				Cell cell10=sheet.getCell(9,x);//检测方式
				Cell cell11=sheet.getCell(10,x);//状态
				Cell cell12=sheet.getCell(11,x);//完成情况
				Cell cell13=sheet.getCell(12,x);//所在班组
				Cell cell14=sheet.getCell(13,x);//责任人
				
				//将仪表信息导入
				final String []type = new String[14];
				type[0]=cell1.getContents();//序号
				type[1]=cell2.getContents();//企业编号
				type[2]=cell3.getContents();//出厂编号
				type[3]=cell4.getContents();//型号
				type[4]=cell5.getContents();//名称
				type[5]=cell6.getContents();//类别	
				type[6]=cell7.getContents();//现场类别
				//type[7]=cell8.getContents();
				/*type[8]=cell9.getContents();//最小安全周期
				type[9]=cell10.getContents();//最大安全周期
*/				java.util.Date indi_date1 = cell8.getDate();
				java.util.Date indi_date2 = cell9.getDate();
				type[7]=format.format(indi_date1);//待检日期
				type[8]=format.format(indi_date2);//下次待检日期
				type[9]=cell10.getContents();//检测方式
				type[10]=cell11.getContents();//状态
				type[11]=cell12.getContents();//完成情况
				type[12]=cell13.getContents();//所在班组
				type[13]=cell14.getContents();//责任人
				final String[] count = new String[1];
				final String id = UUID.randomUUID().toString();
				System.out.println("???????????????????????????????????");
				String enterprisenum = type[1];
				String sql = "select count (*) as ct from t_instrument where enterprisenum = '" + enterprisenum +"'";
				try {
					DbUtil.execute(sql,new IResultSetProcessor(){
						public void process(ResultSet rs) throws SQLException {
							while(rs.next()){
								count[0] = rs.getString("ct");
							}
							
						}
						
					});
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if("0".equals(count[0])){
					String []sqls = new String[1];
					sqls[0] = "insert into t_instrument(id,serialnum,enterprisenum,outnum,name,modelnum,classname,nowclass,detectdate,nextdate,detectmode,state,execution,blongteam,person) values(?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?,?,?)";
					try {
						DbUtil.executeBatchs(sqls,new IArrayPreparedStatementProcessor(){
							public void process(PreparedStatement[] pstmts)throws SQLException {
								pstmts[0].setString(1,id);
								pstmts[0].setString(2,type[0]);
								pstmts[0].setString(3,type[1]);
								pstmts[0].setString(4,type[2]);
								pstmts[0].setString(5,type[3]);
								pstmts[0].setString(6,type[4]);
								pstmts[0].setString(7,type[5]);
								pstmts[0].setString(8,type[6]);
								pstmts[0].setString(9,type[7]);
								pstmts[0].setString(10,type[8]);
								pstmts[0].setString(11,type[9]);
								pstmts[0].setString(12,type[10]);
								pstmts[0].setString(13,type[11]);
								pstmts[0].setString(14,type[12]);
								pstmts[0].setString(15,type[13]);
								
								pstmts[0].addBatch();
								
								System.out.println("****************************");
							}

						});
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return "true";
					}
					
				}else{
					return "repeat";
				}
			}
		}
		return result[0];
	}

	@Override
	//上传函数
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
    					filename = "仪表信息表."+s[s.length-1];
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

}
