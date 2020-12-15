/**
 * 
 */
package com.nuaa.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;

/**
 * @author mahao
 *
 */
public class TestDbUtil {
	private Logger logger=Logger.getLogger(this.getClass().getName());
	public  void select (){
		String sql="select * from t_user";
		try {
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if (rs.next()) {
						logger.debug(rs.getString("username"));
						logger.debug(rs.getString("userid"));
					}
				}
			});
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}		
	}
}
