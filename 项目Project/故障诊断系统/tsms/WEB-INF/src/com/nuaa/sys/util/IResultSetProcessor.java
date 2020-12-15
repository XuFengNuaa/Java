package com.nuaa.sys.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultSetProcessor {
	public void process(ResultSet rs) throws SQLException;
}
