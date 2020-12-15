package com.nuaa.sys.util;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IArrayPreparedStatementProcessor {
	public void process(PreparedStatement[] pstmts) throws SQLException;
}
