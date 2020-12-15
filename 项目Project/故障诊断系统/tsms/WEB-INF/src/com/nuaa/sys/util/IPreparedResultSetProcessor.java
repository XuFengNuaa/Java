package com.nuaa.sys.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IPreparedResultSetProcessor {
	public void processResultSet(ResultSet rs) throws SQLException;

	public void processPreparedStatement(PreparedStatement pstmt)
			throws SQLException;
}
