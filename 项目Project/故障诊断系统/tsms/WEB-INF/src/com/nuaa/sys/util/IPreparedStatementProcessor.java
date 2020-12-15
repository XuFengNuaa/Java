package com.nuaa.sys.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IPreparedStatementProcessor {
	public void process(PreparedStatement pstmt) throws SQLException;
}
