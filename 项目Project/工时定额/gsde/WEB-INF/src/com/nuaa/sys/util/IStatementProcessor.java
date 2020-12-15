package com.nuaa.sys.util;

import java.sql.SQLException;
import java.sql.Statement;

public interface IStatementProcessor {
	public void process(Statement stmt) throws SQLException;
}
