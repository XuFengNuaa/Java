package com.nuaa.sys.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IPreparedResultRowProcessor {
	public void processResultRow(ResultRow rr) throws SQLException;

	public void processPreparedStatement(PreparedStatement pstmt)
			throws SQLException;
}
