package com.nuaa.sys.util;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtil {
	public DbUtil() {
		try {
			// parameter init
//			drivers = "com.mysql.jdbc.Driver";
//			url = "jdbc:mysql://localhost:3306/cgtms";
//			user = "root";
//			password = "nuaa316";

			// db connection pool init
			// InitialContext env = new InitialContext();
			// javax.sql.DataSource pool =
			// (javax.sql.DataSource)env.lookup(jndi_name);
			// conn = pool.getConnection();

			// db connection init
			//Class.forName(drivers);
			//conn = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			System.out.println("dbBean: init error!" + e.toString());
		}
	}
	public static DataSource getDs(){
		DataSource ds=null;
		InitialContext ctx=null;
		try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		try {
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/gsde");
		} catch (NamingException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return ds;		
	}
	/**
	 * 功能： 在系统库中执行查询sql，处理返回的ResultSet
	 * 无需：关心Connection、Statement、ResultSet的创建和关闭问题
	 * 
	 * @param sql
	 * @param rsp
	 * @throws SQLException
	 */	
	public static void execute(String sql, IResultSetProcessor rsp)
			throws SQLException {
		Connection conn = getSysDbConnection();
		try {
			execute(conn, sql, rsp);
		} finally {
			close(conn);
		}
	}

	/**
	 * 处理单条记录
	 * 
	 * @param sql
	 * @param rrp
	 * @throws SQLException
	 */
	public static void execute(String sql, IResultRowProcessor rrp)
			throws SQLException {
		Connection conn = getSysDbConnection();
		try {
			execute(conn, sql, rrp);
		} finally {
			close(conn);
		}
	}

	/**
	 * 功能： 在指定数据库中执行查询sql，处理返回的ResultSet 需要：调用者需负责Connection的创建、关闭
	 * 无需：关心Statement、ResultSet的创建和关闭问题
	 * 
	 * @param conn
	 * @param sql
	 * @param rsp
	 * @throws SQLException
	 */
	public static void execute(Connection conn, String sql,
			IResultSetProcessor rsp) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (null != rsp)
				rsp.process(rs);
		} finally {
			close(stmt);
			close(rs);
		}

	}

	/**
	 * 处理单条记录
	 * 
	 * @param conn
	 * @param sql
	 * @param rrp
	 * @throws SQLException
	 */
	public static void execute(Connection conn, String sql,
			IResultRowProcessor rrp) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (null != rrp) {
				if (rs.next()) {
					rrp.process(new ResultRow(rs));
				}
			}
		} finally {
			close(rs);
			close(stmt);
		}
	}

	/**
	 * 功能： 在系统数据库中执行查询sql，处理PreparedStatement和ResultSet
	 * 无需：关心Connection、PreparedStatement、ResultSet的创建、关闭
	 * 
	 * @param sql
	 * @param prsp
	 * @throws SQLException
	 */
	public static void execute(String sql, IPreparedResultSetProcessor prsp)
			throws SQLException {
		Connection conn = getSysDbConnection();
		try {
			execute(conn, sql, prsp);
		} finally {
			close(conn);
		}
	}

	/**
	 * 功能： 在指定数据库中执行查询sql，处理PreparedStatement和ResultSet
	 * 需要：调用者需要负责Connection的创建、关闭 无需：关心PreparedStatement、ResultSet的创建、关闭
	 * 
	 * @param conn
	 * @param sql
	 * @param prsp
	 * @throws SQLException
	 */
	public static void execute(Connection conn, String sql,
			IPreparedResultSetProcessor prsp) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if (null != prsp)
				prsp.processPreparedStatement(pstmt);
			rs = pstmt.executeQuery();
			if (null != prsp)
				prsp.processResultSet(rs);
		} finally {
			close(rs);
			close(pstmt);
		}
	}

	/**
	 * 获取单行记录数据，通过IPreparedResultRowProcessor处理ResultRow结果数据
	 * 
	 * @param sql
	 * @param prrp
	 * @throws SQLException
	 */
	public static void execute(String sql, IPreparedResultRowProcessor prrp)
			throws SQLException {
		Connection conn = getSysDbConnection();
		try {
			execute(conn, sql, prrp);
		} finally {
			close(conn);
		}
	}

	/**
	 * 获取单行记录数据，通过IPreparedResultRowProcessor处理ResultRow结果数据
	 * 
	 * @param conn
	 * @param sql
	 * @param prrp
	 * @throws SQLException
	 */
	public static void execute(Connection conn, String sql,
			IPreparedResultRowProcessor prrp) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if (null != prrp)
				prrp.processPreparedStatement(pstmt);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				prrp.processResultRow(new ResultRow(rs));
			}
		} finally {
			close(rs);
			close(pstmt);
		}
	}

	/**
	 * 功能： 在系统数据库中执行更新sql，在执行前处理Statement，返回执行结果数据库受影响的记录数
	 * 无需：关心Connection、Statement的创建、执行、关闭问题
	 * 
	 * @param sql
	 * @param stmtp
	 * @throws SQLException
	 */
	public static int execute(String sql, IStatementProcessor stmtp)
			throws SQLException {
		Connection conn = getSysDbConnection();
		try {
			return execute(conn, sql, stmtp);
		} finally {
			close(conn);
		}
	}

	/**
	 * 功能： 在指定数据库中执行更新sql，在执行前处理Statement，返回执行结果数据库受影响的记录数
	 * 需要：调用者需负责Connection的创建、关闭 无需：关心Statement的创建、执行、关闭问题
	 * 
	 * @param conn
	 * @param sql
	 * @param stmtp
	 * @throws SQLException
	 */
	public static int execute(Connection conn, String sql,
			IStatementProcessor stmtp) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			if (null != stmtp)
				stmtp.process(stmt);
			return stmt.executeUpdate(sql);
		} finally {
			close(stmt);
		}
	}

	/**
	 * 功能： 在系统数据库中批量执行更新sql，在执行前处理PreparedStatement，返回数据库受影响记录数
	 * 无需：关心Connection、Statement的创建、关闭
	 * 
	 * @param stmtp
	 * @throws SQLException
	 */
	public static int[] executeBatch(IStatementProcessor stmtp)
			throws SQLException {
		Connection conn = getSysDbConnection();
		try {
			return executeBatch(conn, stmtp);
		} finally {
			close(conn);
		}
	}

	/**
	 * 功能： 同executeBatch(IStatementProcessor)方法，并提供事务功能
	 * 
	 * @param stmtp
	 * @throws SQLException
	 */
	public static int[] executeBatchTx(IStatementProcessor stmtp)
			throws SQLException {
		Connection conn = getSysDbConnection();
		int[] ret = null;
		try {
			conn.setAutoCommit(false);
			ret = executeBatch(conn, stmtp);
			conn.commit();
		} catch (Exception ex) {
			conn.rollback();
		} finally {
			close(conn);
		}
		return ret;
	}

	/**
	 * 功能： 在指定数据库中批量执行更新sql，在执行前处理Statement，返回数据库受影响的记录数
	 * 需要：调用者负责Connection的创建、关闭 无需：关心Statement的创建、关闭
	 * 
	 * @param conn
	 * @param stmtp
	 * @throws SQLException
	 */
	public static int[] executeBatch(Connection conn, IStatementProcessor stmtp)
			throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			if (null != stmtp)
				stmtp.process(stmt);
			return stmt.executeBatch();
		} finally {
			close(stmt);
		}
	}

	/**
	 * 功能： 在系统数据库中执行更新sql，执行前处理PrepatedStatement，返回数据库受影响的记录数
	 * 
	 * @param sql
	 * @param pstmtp
	 * @throws SQLException
	 */
	public static int execute(String sql, IPreparedStatementProcessor pstmtp)
			throws SQLException {
		Connection conn = getSysDbConnection();
		try {
			return execute(conn, sql, pstmtp);
		} finally {
			close(conn);
		}
	}

	/**
	 * 功能： 在指定数据库中执行更新sql，执行前处理PrepatedStatement，返回数据库受影响的记录数
	 * 
	 * @param conn
	 * @param sql
	 * @param pstmtp
	 * @throws SQLException
	 */
	public static int execute(Connection conn, String sql,
			IPreparedStatementProcessor pstmtp) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if (null != pstmtp)
				pstmtp.process(pstmt);
			return pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}

	/**
	 * 执行CallableStatement，通过ICallableStatementProcessor来处理CallableStatement
	 * 
	 * @param sql
	 * @param cstmtp
	 * @throws SQLException
	 */
	public static int execute(String sql, ICallableStatementProcessor cstmtp)
			throws SQLException {
		Connection conn = getSysDbConnection();
		try {
			return execute(conn, sql, cstmtp);
		} finally {
			close(conn);
		}
	}

	/**
	 * 执行CallableStatement，通过ICallableStatementProcessor来处理CallableStatement
	 * 
	 * @param conn
	 * @param sql
	 * @param cstmtp
	 * @throws SQLException
	 */
	public static int execute(Connection conn, String sql,
			ICallableStatementProcessor cstmtp) throws SQLException {
		CallableStatement cstmt = null;
		int result = -1;
		try {
			cstmt = conn.prepareCall(sql);
			if (null != cstmtp)
				cstmtp.processBefore(cstmt);
			result = cstmt.executeUpdate();
			if (null != cstmtp)
				cstmtp.processAfter(cstmt);
		} finally {
			close(cstmt);
		}
		return result;
	}

	/**
	 * 功能： 在系统数据库中执行更新sql，执行前处理PreparedStatement，返回数据库受影响的记录数
	 * 
	 * @param sql
	 * @param pstmtp
	 * @throws SQLException
	 */
	public static int[] executeBatch(String sql,
			IPreparedStatementProcessor pstmtp) throws SQLException {
		Connection conn = getSysDbConnection();
		try {
			return executeBatch(conn, sql, pstmtp);
		} finally {
			close(conn);
		}
	}

	/**
	 * 功能： 同executeBatch(String, IPreparedStatementProcessor)，并提供事务功能
	 * 
	 * @param sql
	 * @param pstmtp
	 * @throws SQLException
	 */
	public static int[] executeBatchTx(String sql,
			IPreparedStatementProcessor pstmtp) throws SQLException {
		Connection conn = getSysDbConnection();
		int[] ret = null;
		try {
			conn.setAutoCommit(false);
			ret = executeBatch(conn, sql, pstmtp);
			conn.commit();
		} catch (Exception ex) {
			conn.rollback();
		} finally {
			close(conn);
		}
		return ret;
	}

	/**
	 * 功能： 在指定数据库中执行更新sql，执行前处理PreparedStatement，返回数据库受影响的记录数
	 * 
	 * @param conn
	 * @param sql
	 * @param pstmtp
	 * @throws SQLException
	 */
	public static int[] executeBatch(Connection conn, String sql,
			IPreparedStatementProcessor pstmtp) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			// 确保没有被缓存过的Batch
			pstmt.clearBatch();

			if (null != pstmtp)
				pstmtp.process(pstmt);
			return pstmt.executeBatch();
		} finally {
			close(pstmt);
		}
	}

	/**
	 * 获取系统数据库连接
	 * 
	 * @return
	 */	
	public static Connection getSysDbConnection() {
		//return DBConnHandler.getSysDBConn();
		Connection conn=null;
		try {
			conn=getDs().getConnection();
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 直接从应用服务器的数据源中获取数据库连接
	 * 
	 * @param jndiStr
	 * @return
	 */

	/**
	 * 关闭Connection
	 * 
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if (null != conn && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception ex) {
			
		}
	}

	/**
	 * 关闭Statement
	 * 
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		try {
			if (null != stmt)
				stmt.close();
		} catch (Exception ex) {

		}
	}

	/**
	 * 关闭ResultSet
	 * 
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try {
			if (null != rs)
				rs.close();
		} catch (Exception ex) {
		}
	}

	/**
	 * java.util.Date转换为java.sql.Date，反过来不需要转
	 * 
	 * @param d
	 * @return
	 */
	public static java.sql.Date utilDate2SqlDate(java.util.Date d) {
		if (null == d)
			return null;
		return new java.sql.Date(d.getTime());
	}

	/**
	 * 从ResultSetMetaData中获取某个字段的Index
	 * 
	 * @param rsmd
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public static int getMetaFieldIndex(ResultSetMetaData rsmd, String name)
			throws SQLException {
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			if (name.equalsIgnoreCase(rsmd.getColumnName(i)))
				return i;
		}
		return -1;
	}

	/**
	 * 获取Clob对象中所代表的String
	 * 
	 * @param clob
	 * @return
	 * @throws SQLException
	 */
	public static String getClobString(Clob clob) throws SQLException {
		if (null == clob)
			return null;
		int len = (int) clob.length();
		String retStr = clob.getSubString(1, len);
		if (null != retStr)
			retStr = retStr.trim();
		return retStr;
	}


	/**
	 * 在指定数据库连接中执行查询Callable
	 * sql，并处理CallableStatement:CallableStatement.setXXX()和ResultSet
	 * 
	 * @param conn
	 * @param sql
	 * @param crsp
	 * @throws SQLException
	 */
	public static void execute(Connection conn, String sql,
			ICallableResultSetProcessor crsp) throws SQLException {
		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
			cstmt = conn.prepareCall(sql);
			if (null != crsp)
				crsp.processCallableStatement(cstmt);
			rs = cstmt.executeQuery();
			if (null != crsp)
				crsp.processResultSet(rs);
		} finally {
			close(rs);
			close(cstmt);
		}
	}

	/**
	 * 在系统数据库连接中执行查询Callable
	 * sql，并处理CallableStatement:CallableStatement.setXXX()和ResultSet
	 * 
	 * @param sql
	 * @param crsp
	 * @throws SQLException
	 */
	public static void execute(String sql, ICallableResultSetProcessor crsp)
			throws SQLException {
		Connection conn = getSysDbConnection();
		try {
			execute(conn, sql, crsp);
		} finally {
			close(conn);
		}
	}


	/**
	 * 在指定数据库连接中执行查询Callable
	 * sql，并处理CallableStatement:CallableStatement.setXXX()和ResultRow
	 * 
	 * @param conn
	 * @param sql
	 * @param crrp
	 * @throws SQLException
	 */
	public static void execute(Connection conn, String sql,
			ICallableResultRowProcessor crrp) throws SQLException {
		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
			cstmt = conn.prepareCall(sql);
			if (null != crrp)
				crrp.processCallableStatement(cstmt);
			rs = cstmt.executeQuery();
			if (rs.next()) {
				crrp.processResultRow(new ResultRow(rs));
			}
		} finally {
			close(rs);
			close(cstmt);
		}
	}

	/**
	 * 在系统数据库连接中执行查询Callable
	 * sql，并处理CallableStatement:CallableStatement.setXXX()和ResultRow
	 * 
	 * @param sql
	 * @param crrp
	 * @throws SQLException
	 */
	public static void execute(String sql, ICallableResultRowProcessor crrp)
			throws SQLException {
		Connection conn = getSysDbConnection();
		try {
			execute(conn, sql, crrp);
		} finally {
			close(conn);
		}
	}

	/**
	 * 在指定数据库连接中批量执行Batch PreparedStatement
	 * 
	 * @param conn
	 * @param sqls
	 * @param apstmtp
	 * @return
	 * @throws SQLException
	 */
	public static List executeBatchs(Connection conn, String[] sqls,
			IArrayPreparedStatementProcessor apstmtp) throws SQLException {
		PreparedStatement[] pstmts = new PreparedStatement[sqls.length];
		List rets = new ArrayList();
		try {
			for (int i = 0; i < sqls.length; i++) {
				String sql = sqls[i];
				PreparedStatement pstmt = conn.prepareStatement(sql);
				// 确保没有被缓存过的Batch
				pstmt.clearBatch();

				pstmts[i] = pstmt;
			}
			if (apstmtp != null) {
				apstmtp.process(pstmts);
			}

			for (int i = 0; i < pstmts.length; i++) {
				PreparedStatement pstmt = pstmts[i];
				int[] ret = pstmt.executeBatch();
				rets.add(ret);
			}

			return rets;
		} finally {
			for (int i = 0; i < pstmts.length; i++) {
				close(pstmts[i]);
			}
		}
	}

	/**
	 * 在系统数据库连接中批量执行Batch PreparedStatement
	 * 
	 * @param sqls
	 * @param apstmtp
	 * @return
	 * @throws SQLException
	 */
	public static List executeBatchs(String[] sqls,
			IArrayPreparedStatementProcessor apstmtp) throws SQLException {
		Connection conn = DbUtil.getSysDbConnection();
		List rets = null;
		try {
			rets = executeBatchs(conn, sqls, apstmtp);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(conn);
		}
		return rets;
	}

	/**
	 * 在系统数据库连接中批量执行Batch PreparedStatement，支持事务
	 * 
	 * @param sqls
	 * @param apstmtp
	 * @return
	 * @throws SQLException
	 */
	public static List executeBatchsTx(String[] sqls,
			IArrayPreparedStatementProcessor apstmtp) throws SQLException {
		Connection conn = getSysDbConnection();//已经是全新Connection
		List rets = null;
		try {
			conn.setAutoCommit(false);
			rets = executeBatchs(conn, sqls, apstmtp);
			conn.commit();
		} catch (Exception ex) {
			conn.rollback();
			ex.printStackTrace();			
		} finally {
			close(conn);
		}
		return rets;
	}


	/**
	 * 判断目标库中是否存在指定的表
	 * 
	 * @param dbName
	 * @param tableName
	 * @return
	 */
	public static boolean tableExists(String tableName) {
		boolean ret = false;
		String existSql = "select 1 from " + tableName + " where 1=2";
		try {
			DbUtil.execute(existSql, new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
				}
			});
			ret = true;
		} catch (Exception ex) {
			ret = false;
		}
		return ret;
	}

	/**
	 * 在目标库中删除指定表，如果存在的话
	 * 
	 * @param dbName
	 * @param tableName
	 * @throws SQLException
	 */
	public static void dropTableIfExists(String tableName)
			throws SQLException {
		boolean exist = tableExists(tableName);
		if (exist) {
			String dropSql = "drop table " + tableName;
			DbUtil.execute(dropSql, new IStatementProcessor() {
				public void process(Statement stmt) throws SQLException {
				}
			});
		}
	}
}
