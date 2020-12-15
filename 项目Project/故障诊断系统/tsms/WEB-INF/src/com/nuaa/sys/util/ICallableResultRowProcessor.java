package com.nuaa.sys.util;

import java.sql.CallableStatement;
import java.sql.SQLException;

public interface ICallableResultRowProcessor {
	public void processResultRow(ResultRow rr) throws SQLException;

	public void processCallableStatement(CallableStatement pstmt)
			throws SQLException;
}
