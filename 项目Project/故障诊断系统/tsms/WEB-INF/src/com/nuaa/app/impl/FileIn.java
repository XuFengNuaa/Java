package com.nuaa.app.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;


public class FileIn {		
		//上传图片保存到数据库  修改图片时必须图片名称相同才行
		public void In1( final HashMap hashmap){
			final String dir = hashmap.get("dir").toString();
			final String filename = hashmap.get("tpmc").toString();
			System.out.println(hashmap.get("tpmc").toString());
			System.out.println(hashmap.get("dir").toString());
			String sql1 = "select count (*) as ct from t_blobtableimg where ljth ='"+hashmap.get("ljth").toString()+"'";
			final String []count = new String[1];
			try {
				DbUtil.execute(sql1,new IResultSetProcessor(){
					public void process(ResultSet rs) throws SQLException {
						// TODO Auto-generated method stub
						while(rs.next()){
							count[0] = rs.getString("ct");
						}
					}
				});
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if("0".equals(count[0])){
				String[] sql = new String[1];
				sql[0] = "insert into t_blobtableimg (ljth,imgvalue)values (?,?)";
				try {
					DbUtil.executeBatchs(sql,new IArrayPreparedStatementProcessor(){
						public void process(PreparedStatement[] pstmts) throws SQLException {
							// TODO Auto-generated method stub
							File file = new File(dir+filename); 
							int fileLength =(int) file.length();
							InputStream fin;
							try {
								fin = new FileInputStream(file);
								pstmts[0].setString (1, hashmap.get("ljth").toString());
								pstmts[0].setBinaryStream (2, fin, fileLength);
								//pstmts[0].executeBatch();
								pstmts[0].executeUpdate(); 
								System.out.println("succeed");
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
							
						}
						
					});
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				String[] sql2 = new String[1];
				sql2[0] = "update t_blobtableimg set imgvalue = ? where ljth = '"+hashmap.get("ljth").toString()+"'";
				try {
					DbUtil.executeBatchs(sql2,new IArrayPreparedStatementProcessor(){
						public void process(PreparedStatement[] pstmts) throws SQLException {
							// TODO Auto-generated method stub
							
							File file = new File(dir+filename); 
							int fileLength =(int) file.length();
							InputStream fin;
							try {
								fin = new FileInputStream(file);
								pstmts[0].setBinaryStream (1, fin, fileLength);
								pstmts[0].executeUpdate(); 
								System.out.println("succeed");
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					});
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			}

}
