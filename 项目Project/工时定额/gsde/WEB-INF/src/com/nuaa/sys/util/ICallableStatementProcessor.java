package com.nuaa.sys.util;

import java.sql.CallableStatement;
import java.sql.SQLException;

public interface ICallableStatementProcessor {
	public void processBefore(CallableStatement cstmt) throws SQLException;
	public void processAfter(CallableStatement cstmt) throws SQLException;
}
