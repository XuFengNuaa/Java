package com.nuaa.sys.util;

import java.sql.SQLException;

public interface IResultRowProcessor {
	public void process(ResultRow rr) throws SQLException;
}
