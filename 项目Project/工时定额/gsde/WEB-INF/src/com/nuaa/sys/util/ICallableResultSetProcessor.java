package com.nuaa.sys.util;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ICallableResultSetProcessor {
	public void processResultSet(ResultSet rs) throws SQLException;

	public void processCallableStatement(CallableStatement pstmt)
			throws SQLException;
}
