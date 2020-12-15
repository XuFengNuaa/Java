package com.nuaa.app.impl;


import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


import java.util.Date;
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


import org.apache.commons.fileupload.FileItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.nuaa.app.Standard;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.Logger;

import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;

public class StandardImpl implements Standard  {
	
	 public JSONObject getQueryCompanyAll(){
		
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		String sql="";
		sql="select * from T_COMPANY ";
		
		Logger.debug(sql);
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("company", rs.getString("company"));
							
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
	public JSONObject getQueryFile(HashMap queryMap){
	
		HashMap queryHashMap=new HashMap();
		queryHashMap=queryMap;
		System.out.println("***********");
		final JSONObject jsonObj=new JSONObject();
		final JSONArray jsonArray=new JSONArray();
		//String nodeid=(String)queryHashMap.get("nodeid");
		String sql="";
		sql="select  count(*) as ct  from  (select * from v_ct2com ) where "+(String)queryHashMap.get("filter");
		Logger.debug(sql);
		final String[] count=new String[1];
		count[0]="";
		try {			
			DbUtil.execute(sql, new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if (rs.next()) {
						try {
							jsonObj.put("totalProperty",rs.getString("ct"));
						} catch (JSONException e) {
							// TODO
							e.printStackTrace();
						}
					}
				}
			});
		}catch (SQLException e) {
		// TODO
			e.printStackTrace();
		}
		//Logger.debug(jsonObj.toString());
		int start=Integer.parseInt((String)queryHashMap.get("start"));
		int limit=Integer.parseInt((String)queryHashMap.get("limit"));
		String filter=(String)queryHashMap.get("filter");///////
		String order=(String)queryHashMap.get("order");
		System.out.println("filter............"+filter);
		System.out.println("order...................."+order);
		sql="select * from (select  *  from  (select  *  from  (select * from  v_ct2com ) where "+filter+" order by "+order+") where rownum<="+(start+limit)+" minus select  *  from  (select  *  from  (select * from  v_ct2com ) where "+filter+" order by "+order+" ) where rownum<="+start+") order by "+order;			
		System.out.println("sql="+sql);
		try {		
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					try {
						while(rs.next()){
							JSONObject obj=new JSONObject();
							obj.put("id", rs.getString("id"));
							obj.put("mnum", rs.getString("mnum"));
							obj.put("pclass", rs.getString("pclass"));
							obj.put("bclass", rs.getString("bclass"));
							obj.put("name", rs.getString("name"));
							obj.put("spec", rs.getString("spec"));
							obj.put("company", rs.getString("company"));
							obj.put("location", rs.getString("location"));
							obj.put("nct_count_in", rs.getString("nct_count_in"));
							obj.put("uct_count_in", rs.getString("uct_count_in"));
							obj.put("mini_qs", rs.getString("mini_qs"));
						//	obj.put("createtime", rs.getDate("createtime"));
							obj.put("hq", rs.getString("hq"));
							obj.put("use_freq", rs.getString("use_freq"));
							obj.put("order_num", rs.getString("order_num"));
							obj.put("remark", rs.getString("remark"));
							obj.put("companyid", rs.getString("companyid"));
							jsonArray.put(obj);
						}
						jsonObj.put("filedata", jsonArray);
					} catch (JSONException e) {
						// TODO
						e.printStackTrace();
					}
				}				
			});
			//Logger.debug(jsonObj.toString());
		}catch (SQLException e) {
		// TODO
			e.printStackTrace();
		}
		System.out.println("asdas"+jsonObj);
		return jsonObj;
	}
	
	public String addFile(HashMap addMap) {
//		 TODO 自动生成方法存根
		String result = "false";
		HashMap addHashMap = addMap;
		  String mnum=(String)addHashMap.get("pa[1]");
		  String hq=(String)addHashMap.get("pa[11]");
		  String company=(String)addHashMap.get("pa[6]");
		  String order_num=(String)addHashMap.get("pa[13]");
		  System.out.println("mnum............"+mnum);
		  System.out.println("hq..............."+hq);
		  System.out.println("company............"+company);
		  System.out.println("order_num..............."+order_num);
		  
		  String sql ="";
		  if(hq.equals("是")){//高质用进货号和厂商判断
			  sql="select * from (select * from(select * from  t_ct where hq='"+hq+"')where companyid='"+company+"') where order_num='"+order_num+"'";
		  }else {//非高质用物资编码和厂商判断
			  sql="select * from (select * from (select * from  t_ct where hq='"+hq+"') where mnum='"+mnum+"') where companyid='"+company+"'";
		  }
			final int []flag=new int[1];
			flag[0]=0;
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {
						if(rs.next()){
							flag[0]=1;
						}
					}
				});
				if(flag[0]==1){//物资编码、厂商、进货号都重复不能写入
					if(hq.equals("是")){
						return "exit0";
					}else if(hq.equals("否")){
						return "exit1";
					}
					 
				}else{
					  /*final int [] flags = new int[1];
					  flags[0]=0;
					  String sqls1 = "select * from  t_ct where mnum = '"+mnum+"'";//判断物资编码是否重复
					  try {			
						DbUtil.execute(sqls1,new IResultSetProcessor() {
								public void process(ResultSet rs) throws SQLException {
									if(rs.next()){
										flags[0]=1;
									}
								}
						});
						if(flags[0]==1){//物资编码重复时必须同为高质或非高质
							  final int [] flags1 = new int[1];
							  flags1[0]=0;
							  String sqls2 = "select * from  (select * from  t_ct where mnum = '"+mnum+"') where hq = '"+hq+"'";
							  try {			
								DbUtil.execute(sqls2,new IResultSetProcessor() {
										public void process(ResultSet rs) throws SQLException {
											if(rs.next()){
												flags1[0]=1;
											}
										}
								});
								if(flags1[0]==0){
									result="no";//物资编码相同高质与否不同，不能添加
								}else{
									 result = add(addHashMap);
								}	
							  }catch (SQLException e) {
									e.printStackTrace();
									result="false";
							  }
						}else {*/ //物资编码不同，直接添加
							result = add(addHashMap);
						/*}
					  	}catch (SQLException e) {
							e.printStackTrace();
							result="false";
						}*/

				}
			}catch (SQLException e) {
			// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		return result;
	}


	public String modifyFile(HashMap addMap) {
		// TODO 自动生成方法存根
		String result = "false";
		HashMap addHashMap = addMap;
		
		  String mnum=(String)addHashMap.get("pa[0]");
		  String hq=(String)addHashMap.get("pa[8]");
		  String company=(String)addHashMap.get("pa[5]");
		  String order_num=(String)addHashMap.get("pa[12]");
		  String id = (String)addHashMap.get("pa[10]");
		 // String finalmnum = (String)addHashMap.get("pa[11]");
		  String finalhq = (String)addHashMap.get("pa[13]");
		 //String mnum=(String)addHashMap.get("pa[0]");
		  System.out.println("mnum............"+mnum);
		  System.out.println("hq..............."+hq);
		  System.out.println("company............"+company);
		  System.out.println("order_num..............."+order_num);
		  System.out.println("finalhq..............."+finalhq);
		  System.out.println((String)addHashMap.get("pa[11]"));
		  String sql ="";
		  if(hq.equals("是")){//高质用进货号和厂商判断
			  sql="select * from (select * from(select * from(select * from  t_ct where id !='"+id+"') where hq='"+hq+"')where companyid='"+company+"') where order_num='"+order_num+"'";
			  System.out.println(sql);
		  }else if(hq.equals("否")) {//非高质用物资编码和厂商判断
			  sql="select * from (select * from (select * from(select * from  t_ct where id !='"+id+"') where hq='"+hq+"') where mnum='"+mnum+"') where companyid='"+company+"'";
			  System.out.println(sql);
		  }
			final int []flag=new int[1];
			flag[0]=0;
			try {			
				DbUtil.execute(sql,new IResultSetProcessor() {
					public void process(ResultSet rs) throws SQLException {
						if(rs.next()){
							flag[0]=1;
						}
					}
				});
				if(flag[0]==1){//物资编码、厂商、进货号都重复不能写入
					if(hq.equals("是")){
						return "exit0";
					}else if(hq.equals("否")){
						return "exit1";
					}
					 
				}else{
					  final int [] flags = new int[1];
					  flags[0]=0;
					  String sqls1 = "select * from  (select * from  t_ct where id !='"+id+"') where mnum = '"+mnum+"'";//判断物资编码是否重复
					  System.out.println(sqls1);
					  try {			
						DbUtil.execute(sqls1,new IResultSetProcessor() {
								public void process(ResultSet rs) throws SQLException {
									if(rs.next()){
										flags[0]=1;
									}
								}
						});
						if(flags[0]==1){//物资编码重复时必须同为高质或非高质
							  final int [] flags1 = new int[1];
							  flags1[0]=0;
							  String sqls2 = "select * from  (select * from  t_ct where id !='"+id+"') where mnum = '"+mnum+"'";
							  System.out.println(sqls2);
							  try {	
								DbUtil.execute(sqls2,new IResultSetProcessor() {
										public void process(ResultSet rs) throws SQLException {
											if(rs.next()){
												flags1[0]=1;
											}
										}
								});
								if(flags1[0]==0){
									result="no";//物资编码相同高质与否不同，不能添加
								}else{
									 result = modify(addHashMap);
								}	
							  }catch (SQLException e) {
									e.printStackTrace();
									result="false";
							  }
						}else { //物资编码不同，直接添加
							result = modify(addHashMap);
						}
					  	}catch (SQLException e) {
							e.printStackTrace();
							result="false";
						}

				}
			}catch (SQLException e) {
			// TODO 自动生成 catch 块
				e.printStackTrace();
			}

         return result;
	}
	/*final int [] flag1 = new int[1];
	flag[0]=0;
	String sql1 = "select * from (select * from  t_ct where id != '"+id+"') where mnum = '"+mnum+"'";
	try {			
		DbUtil.execute(sql1,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if(rs.next()){
						flag1[0]=1;
					}
				}
		});
	}catch (SQLException e) {
		e.printStackTrace();
		result="false";
   }	*/
	
	public String add(HashMap addMap) {
		// TODO 自动生成方法存根
		String result;
		HashMap addHashMap = addMap;
		final String[] pa = new String[15];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		
		pa[0] = (String) addHashMap.get("pa[0]");
		pa[1] = (String) addHashMap.get("pa[1]");
		pa[2] = (String) addHashMap.get("pa[2]");
		pa[3] = (String) addHashMap.get("pa[3]");
		pa[4] = (String) addHashMap.get("pa[4]");
		pa[5] = (String) addHashMap.get("pa[5]");
		pa[6] = (String) addHashMap.get("pa[6]");
		pa[7] = (String) addHashMap.get("pa[7]");
		pa[8] = (String) addHashMap.get("pa[8]");
		pa[9] = (String) addHashMap.get("pa[9]");
		pa[10] = (String) addHashMap.get("pa[10]");
		pa[11] = (String) addHashMap.get("pa[11]");
		pa[12] = (String) addHashMap.get("pa[12]");
		pa[13] = (String) addHashMap.get("pa[13]");
		pa[14] = (String) addHashMap.get("pa[14]");
		String[] sqls = new String[1];
		
		sqls[0] = "insert into  t_ct" +
				"(id,mnum,pclass,bclass,name,spec," +
				"companyid,location,nct_count_in,uct_count_in," +                           // location,nct_count_in,uct_count_in,
				"mini_qs,hq,remark,order_num,use_freq) values" +                             //mini_qs,hq,
				"(?,?,?,?,?,?," +
				"?,?,?,?," +
				"?,?,?,?,?)";
				try {
					DbUtil.executeBatchs(sqls,
							new IArrayPreparedStatementProcessor() {
								public void process(PreparedStatement[] pstmts)
										throws SQLException {
									pstmts[0].setString(1, pa[0]);
									pstmts[0].setString(2, pa[1]);
									pstmts[0].setString(3, pa[2]);
									pstmts[0].setString(4, pa[3]);
									pstmts[0].setString(5, pa[4]);
									pstmts[0].setString(6, pa[5]);
									pstmts[0].setString(7, pa[6]);
									pstmts[0].setString(8, pa[7]);
									pstmts[0].setString(9, pa[8]);
									pstmts[0].setString(10, pa[9]);
									pstmts[0].setString(11, pa[10]);
									pstmts[0].setString(12, pa[11]);
									pstmts[0].setString(13, pa[12]);
									pstmts[0].setString(14, pa[13]);
									pstmts[0].setString(15, pa[14]);
									pstmts[0].addBatch();
								}
							});
					result="true";
		
			}catch (SQLException e) {
				e.printStackTrace();
				result="false";
			}
		return result;
	}
	
	
  public String modify(HashMap modyMap){
	    String result;
		HashMap addHashMap = modyMap;
		final String[] pa = new String[12];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		
		pa[0] = (String) addHashMap.get("pa[0]");
		pa[1] = (String) addHashMap.get("pa[1]");
		pa[2] = (String) addHashMap.get("pa[2]");
		pa[3] = (String) addHashMap.get("pa[3]");
		pa[4] = (String) addHashMap.get("pa[4]");
		pa[5] = (String) addHashMap.get("pa[5]");
		pa[6] = (String) addHashMap.get("pa[6]");
		pa[7] = (String) addHashMap.get("pa[7]");
		pa[8] = (String) addHashMap.get("pa[8]");
		pa[9] = (String) addHashMap.get("pa[9]");
		pa[10] = (String) addHashMap.get("pa[10]");
		pa[11] = (String) addHashMap.get("pa[12]");
		String[] sqls = new String[1];
		sqls[0] = "update  t_ct set " +
				"mnum=?,pclass=?,bclass=?,name=?,spec=?,companyid=?,location=?," +
				"mini_qs=?,hq=?," +
				"remark=?,order_num=?  where id='" + pa[10] + "'";
				try {
					DbUtil.executeBatchs(sqls,
							new IArrayPreparedStatementProcessor() {
								public void process(PreparedStatement[] pstmts)
										throws SQLException {
									pstmts[0].setString(1, pa[0]);
									pstmts[0].setString(2, pa[1]);
									pstmts[0].setString(3, pa[2]);
									pstmts[0].setString(4, pa[3]);
									pstmts[0].setString(5, pa[4]);
									pstmts[0].setString(6, pa[5]);
									pstmts[0].setString(7, pa[6]);
									pstmts[0].setString(8, pa[7]);
									pstmts[0].setString(9, pa[8]);
									pstmts[0].setString(10, pa[9]);
									pstmts[0].setString(11, pa[11]);
									//pstmts[0].setString(13, pa[10]);
									/*pstmts[0].setString(10, pa[10]);
									pstmts[0].setString(11, process[1]);*/
									pstmts[0].addBatch();
								}
							});
					result="true";
					
				} catch (SQLException e) {
					e.printStackTrace();
					result="false";
				}  
	return result;  
  }
  
	public boolean addrightupFile(HashMap fileHmap) {
		// TODO 自动生成方法存根
		return false;
	}
	public boolean delFile(HashMap delMap) {
		// TODO 自动生成方法存根
		return false;
	}
	public String getProcess(HashMap processMap) {
		// TODO 自动生成方法存根
		return null;
	}
	public boolean passFile(HashMap passMap) {
		// TODO 自动生成方法存根
		return false;
	}
	public HashMap uploadFile(HashMap fileHmap) {
		// TODO 自动生成方法存根
		return null;
	}

	//上传文件
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
						filename = "导入非测量类工具."+s[s.length-1];
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

	//导入文件
	public String getExcel(HashMap xlsHmap) {
	//    TODO 自动生成方法存根
		HashMap xlsHashMap=xlsHmap;
		final String []result = new String[1];
		result[0]="true";
		String dir = (String)xlsHashMap.get("dir"); 
		final String[] pa = new String[30];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = "";
		}
		
		try 
		{ 	
			WorkbookSettings setting=new WorkbookSettings();   
			setting.setEncoding("ISO-8859-1");   
			Workbook book= Workbook.getWorkbook(new File(dir+"\\web\\tempfile\\导入非测量类工具.xls"),setting); 
	        //获得第一个工作表对象 
			Sheet sheet=book.getSheet(0); 
	        //得到第一列第一行的单元格 
			
			Pattern pattern = Pattern.compile("^(0|[1-9][0-9]*)$"); //匹配整数					
			Pattern pattern1 = Pattern.compile("^[0-9]{2}-[0-9]{2}-0[0-3]-[0-9]{2}$"); //匹配库存地点	
			Pattern pattern2 = Pattern.compile("^\\d+$");
			
			for(int x = 1; x < sheet.getRows(); x++){
				
				System.out.println("sheet行数是："+sheet.getRows());
				
				Cell check1=sheet.getCell(0,x);	//物资编码				
				Cell check2=sheet.getCell(4,x); //名称
				Cell check3=sheet.getCell(6,x); //高质				
				Cell check4=sheet.getCell(8,x); //库存地点				
				Cell check5=sheet.getCell(12,x); //厂商名称	
				Cell check9=sheet.getCell(1,x); //物资编码	
				Cell check10=sheet.getCell(2,x); //大类	
				Cell check11=sheet.getCell(3,x); //小类	 
				
				Matcher matcher2 = pattern2.matcher(sheet.getCell(7,x).getContents());
				boolean e = matcher2.matches();
				
				//判断最低库存量是否为空，格式是否正确
				if(!sheet.getCell(7,x).getContents().equals("")&&e==false){
					result[0]="ministorefalse";	
				}else if(!sheet.getCell(7,x).getContents().equals("")&&e==true){
					NumberCell check6=(NumberCell)sheet.getCell(7,x);//最低库存量	
					Matcher matcher = pattern.matcher(check6.getContents());
					boolean a = matcher.matches();
				    if(a==false&&result[0].equals("true")){
						result[0]="ministorefalse";	
				     }	
				  }else{
					  result[0]="mininull";
				  }
				 
				Matcher matcher3 = pattern2.matcher(sheet.getCell(9,x).getContents());//判断新工具在库量
				boolean f = matcher3.matches();
				
				 //判断新工具在库量是否格式正确
				if(!sheet.getCell(9,x).getContents().equals("")&&f==false){
					result[0]="newstorefalse";
				}else if(!sheet.getCell(9,x).getContents().equals("")&&f==true){
						NumberCell check7=(NumberCell)sheet.getCell(9,x);//新工具在库量	
						Matcher matcher = pattern.matcher(check7.getContents());
						boolean b = matcher.matches();						
						System.out.println("********************"+check7.getContents()+"nnnnnnnnnnnnnnnn");
						
					    if(b==false&&result[0].equals("true")){
							result[0]="newstorefalse";	
					     }	
				  }
				
				
				 Matcher matcher4 = pattern2.matcher(sheet.getCell(10,x).getContents());//判断旧工具在库量
				 boolean g = matcher4.matches();
				
				 //判断旧工具在库量是否格式正确				 
				 if(!sheet.getCell(10,x).getContents().equals("")&&g==false){
					    result[0]="oldstorefalse";	
				 }else if(!sheet.getCell(10,x).getContents().equals("")&&g==true){
					    NumberCell check8=(NumberCell)sheet.getCell(10,x);//旧工具在库量	
						Matcher matcher = pattern.matcher(check8.getContents());
						boolean c = matcher.matches();
					    if(c==false&&result[0].equals("true")){
							result[0]="oldstorefalse";	
					     }	
				  }		
				 
				 //判断库存地点是否格式正确
				 if(!check4.getContents().equals("")){
					 Matcher matcher1 = pattern1.matcher(check4.getContents());
					 boolean d = matcher1.matches();
					 if(d==false&&result[0].equals("true")){
						 result[0]="locationfalse";
					 }
				 }else{
					 result[0]="locationnull";
				 }				 
				
				 //判断类别
				 if(check10.getContents().equals("")){
					 result[0]="daleinull";
						break;
				 }else if(check11.getContents().equals("")){
					 result[0]="xiaoleinull";
						break;
				 }else if(!check10.getContents().equals("常规工具")&&!check10.getContents().equals("非常规工具")&&!check10.getContents().equals("用电工具")&&!check10.getContents().equals("组合工具")&&!check10.getContents().equals("耗料工具")){
					 result[0]="daleifalse";
						break;
				 }else if(!check11.getContents().equals("扳手")&&!check11.getContents().equals("锉刀")&&!check11.getContents().equals("锯")&&!check11.getContents().equals("锤子")&&!check11.getContents().equals("起子")&&!check11.getContents().equals("其他")&&!check11.getContents().equals("放大器")&&!check11.getContents().equals("剪刀类")&&!check11.getContents().equals("锁")&&!check11.getContents().equals("注射器")&&!check11.getContents().equals("镊子")&&!check11.getContents().equals("焊枪")&&!check11.getContents().equals("电炉")&&!check11.getContents().equals("电磨")&&!check11.getContents().equals("电器")&&!check11.getContents().equals("风动工具")&&!check11.getContents().equals("组合工具")&&!check11.getContents().equals("油石")&&!check11.getContents().equals("焊膏")&&!check11.getContents().equals("清洗类")&&!check11.getContents().equals("润滑类")&&!check11.getContents().equals("粘接类")){
					 result[0]="xiaoleifalse";
						break;
				 }else if(check10.getContents().equals("常规工具")){
					 if(!check11.getContents().equals("扳手")&&!check11.getContents().equals("锉刀")&&!check11.getContents().equals("锯")&&!check11.getContents().equals("锤子")&&!check11.getContents().equals("起子")&&!check11.getContents().equals("其他")){
						 result[0]="classfalse";	 
					 }
				 }else if(check10.getContents().equals("非常规工具")){
					 if(!check11.getContents().equals("放大器")&&!check11.getContents().equals("剪刀类")&&!check11.getContents().equals("锁")&&!check11.getContents().equals("注射器")&&!check11.getContents().equals("镊子")&&!check11.getContents().equals("其他")){
						 result[0]="classfalse";
					 }
				 }else if(check10.getContents().equals("用电工具")){
					 if(!check11.getContents().equals("焊枪")&&!check11.getContents().equals("电炉")&&!check11.getContents().equals("电磨")&&!check11.getContents().equals("电器")&&!check11.getContents().equals("风动工具")&&!check11.getContents().equals("其他")){
						 result[0]="classfalse";
					 }
				 }else if(check10.getContents().equals("组合工具")){
					 if(!check11.getContents().equals("组合工具")&&!check11.getContents().equals("其他")){
						 result[0]="classfalse";							
					 }
				 }else if(check10.getContents().equals("耗料工具")){
					 if(!check11.getContents().equals("油石")&&!check11.getContents().equals("焊膏")&&!check11.getContents().equals("清洗类")&&!check11.getContents().equals("润滑类")&&!check11.getContents().equals("粘接类")&&!check11.getContents().equals("其他")){
						 result[0]="classfalse";
					 }
				 }
				 
				//判断物资编码、厂商、名称不能为空，必须为系统中的分类
				if(check1.getContents().equals("")){
					result[0]="mnumfalse";
					break;
				}else if(check5.getContents().equals("")){
					result[0]="companyfalse";
					break;
				}else if(check2.getContents().equals("")){
					result[0]="namefalse";
					break;
				}
				
								
	            //判断高质一栏是否填写正确、高质工具是否有订货号
				if(!check3.getContents().equals("是")&&!check3.getContents().equals("否")){
					  result[0]="hqfalse";
					  break;
				}else if(check3.getContents().equals("是")){
					if(check9.getContents().equals("")){
					  result[0]="orderfalse";
					}
				}								
								
			   if(result[0].equals("ministorefalse")){
					result[0]="ministorefalse";							
					break;							
				}else if(result[0].equals("newstorefalse")){
					result[0]="newstorefalse";							
					break;							
				}else if(result[0].equals("oldstorefalse")){
					result[0]="oldstorefalse";							
					break;							
				}else if(result[0].equals("locationfalse")){
					result[0]="locationfalse";							
					break;							
				}else if(result[0].equals("classfalse")){
					result[0]="classfalse";							
					break;							
				}else if(result[0].equals("orderfalse")){
					result[0]="orderfalse";							
					break;							
				}						
			}
			
			System.out.println(result[0]);
			System.out.println(result[0].equals("true"));
			
			//写入数据库
			if(result[0].equals("true")){
				for(int x = 1; x < sheet.getRows(); x++){							
					Cell cell1=sheet.getCell(0,x);	//物资编码
					Cell cell2=sheet.getCell(1,x);	//订货号	
					Cell cell3=sheet.getCell(2,x);	//大类
					Cell cell4=sheet.getCell(3,x); //小类
					Cell cell5=sheet.getCell(4,x); //名称
					Cell cell6=sheet.getCell(5,x);//规格		
					Cell cell7=sheet.getCell(6,x);//高质	
					NumberCell cell8=(NumberCell)sheet.getCell(7,x); //最低库存量
					Cell cell9=sheet.getCell(8,x);	    //库存地点
					Cell cell10=sheet.getCell(9,x);	    //新工具在库量	需测试为空时NumberCell是否出错
					Cell cell11=sheet.getCell(10,x);	//旧工具在库量
					Cell cell12=sheet.getCell(11,x);    //工具备注
					Cell cell13=sheet.getCell(12,x);    ///厂商名称
					Cell cell14=sheet.getCell(13,x);	//厂商地址	
					Cell cell15=sheet.getCell(14,x);	//厂商联系人						
					Cell cell16=sheet.getCell(15,x);    //厂商电话
					Cell cell17=sheet.getCell(16,x);    //厂商邮箱						
					Cell cell18=sheet.getCell(17,x);	//厂商备注					
					
					System.out.println("快看1111"+cell10.getContents().equals(""));
					
					final String []company=new String[6];
					company[0]=cell13.getContents();
					company[1]=cell14.getContents();
					company[2]=cell15.getContents();
					company[3]=cell16.getContents();
					company[4]=cell17.getContents();
					company[5]=cell18.getContents();
					
						
					final String []type=new String[12];
					type[0]=cell1.getContents();//物资编码
					type[1]=cell2.getContents();//订货号
					type[2]=cell3.getContents();//大类
					type[3]=cell4.getContents();//小类
					type[4]=cell5.getContents();//名称
					type[5]=cell6.getContents();//规格		
					type[6]=cell7.getContents();//高质
					type[7]=cell8.getContents();//最低库存量
					type[8]=cell9.getContents();//库存地点
					type[9]=cell12.getContents();//工具备注	
					
					final int newinstore = cell10.getContents().equals("")?0:Integer.parseInt(cell10.getContents());//新工具在库量
					final int oldinstore = cell11.getContents().equals("")?0:Integer.parseInt(cell11.getContents());//旧工具在库量
										
	               //判断数据库中有无此厂商
					 final String []company_id=new String[1];							 
					 String sql1="select id from T_COMPANY where COMPANY='"+cell13.getContents()+"'";			
						try {			
							DbUtil.execute(sql1,new IResultSetProcessor(){
								public void process(ResultSet rs) throws SQLException{
									if(rs.next()){					
										//读取id存到类型表中
										company_id[0]=rs.getString("id");   //记录厂商id
										System.out.println("*************************"+company_id[0]);
									}else{											
									  try {					
									 	    final String id=UUID.randomUUID().toString();  //自动生成id
									 	    company_id[0]=id;  //记录厂商id
									 	    System.out.println("*************************"+company_id[0]);
											String []sqlcom = new String[1];
											sqlcom[0]= "insert into T_COMPANY(id,company,address,contact_person,phone,email,remark) values(?,?,?,?,?,?,?)";
											DbUtil.executeBatchs(sqlcom,
													new IArrayPreparedStatementProcessor() {
														public void process(
														PreparedStatement[] pstmts)
														throws SQLException {
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
									}catch (SQLException e) {
										e.printStackTrace();
										result[0]="error";
									}
								 }
								}										
							});	
						}catch (SQLException p){
							p.printStackTrace();
							result[0]="error";
						  }				
				
	                    //判断数据库中有无此类型
					if(result[0].equals("true")){
						final String []type_id=new String[1];
						String sql2="";
						String sql3="";
						type_id[0]="";
						if("否".equals(cell7.getContents())){
							sql2="select id from T_CT where mnum='"+type[0]+"' and companyid='"+company_id[0]+"' and hq='否'";			
							try {			
								DbUtil.execute(sql2,new IResultSetProcessor(){
									public void process(ResultSet rs) throws SQLException{
										if(rs.next()){					
											type_id[0]=rs.getString("id");
										}
									}
								});	
							 }catch (SQLException e){
								e.printStackTrace();
								result[0]="error";
							 }
							}else if("是".equals(cell7.getContents())){
								  sql3="select id from T_CT where order_num='"+type[1]+"'and companyid='"+company_id[0]+"' and hq='是'";			
									try {			
										DbUtil.execute(sql3,new IResultSetProcessor(){
											public void process(ResultSet rs) throws SQLException{
												while(rs.next()){					
													type_id[0]=rs.getString("id");
												}
											}
										});	
									}catch (SQLException e){
										e.printStackTrace();
										result[0]="error";
									  }
								  
							  }							 
						if (type_id[0].equals("")){	
								final String create_typeid=UUID.randomUUID().toString();  //自动生成id								
								String []sqltype = new String[1];								
								sqltype[0] = "insert into  t_ct(id,mnum,pclass,bclass,name,spec,companyid,location,nct_count_in,uct_count_in,mini_qs,hq,remark,order_num,use_freq) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
								try {
									DbUtil.executeBatchs(sqltype,
											new IArrayPreparedStatementProcessor() {
												public void process(PreparedStatement[] pstmts)
														throws SQLException {
													
													pstmts[0].setString(1, create_typeid);
													pstmts[0].setString(2, type[0]);
													pstmts[0].setString(3, type[2]);
													pstmts[0].setString(4, type[3]);
													pstmts[0].setString(5, type[4]);
													pstmts[0].setString(6, type[5]);
													pstmts[0].setString(7, company_id[0]);
													pstmts[0].setString(8, type[8]);
													pstmts[0].setInt(9, newinstore);
													pstmts[0].setInt(10, oldinstore);													
													pstmts[0].setString(11, type[7]);
													pstmts[0].setString(12, type[6]);
													pstmts[0].setString(13, type[9]);
													pstmts[0].setString(14, type[1]);
													pstmts[0].setString(15, "0");
													pstmts[0].addBatch();
												}
											});						
							}catch (SQLException e) {
								e.printStackTrace();
								result[0]="error";
							}						
						}else{										
							String []sqltype = new String[1];							
							sqltype[0] = "update  t_ct set nct_count_in=nct_count_in+"+newinstore+",uct_count_in=uct_count_in+"+oldinstore+" where id='" +type_id[0]+ "'";
							
							try {
								DbUtil.executeBatchs(sqltype,
										new IArrayPreparedStatementProcessor() {
											public void process(PreparedStatement[] pstmts)
													throws SQLException {												
												pstmts[0].addBatch();
											}
										});						
						}catch (SQLException e) {
							e.printStackTrace();
							result[0]="error";
						}						
					  }			
					}									
				}
			}								
			
			book.close(); 
		}catch(Exception e) { 
			e.printStackTrace();
			result[0]="error";
		}
		System.out.println("****************************"+result[0]); 
		return result[0];
	    
	}
  
}