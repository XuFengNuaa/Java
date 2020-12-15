package com.nuaa.sys.util;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

public class ResultRow {
	private ResultSet _rs = null;

	public ResultRow(ResultSet rs) {
		_rs = rs;
	}

	public boolean wasNull() throws SQLException {
		return _rs.wasNull();
	}

	public String getString(int columnIndex) throws SQLException {
		return _rs.getString(columnIndex);
	}

	public boolean getBoolean(int columnIndex) throws SQLException {
		return _rs.getBoolean(columnIndex);
	}

	public byte getByte(int columnIndex) throws SQLException {
		return _rs.getByte(columnIndex);
	}

	public short getShort(int columnIndex) throws SQLException {
		return _rs.getShort(columnIndex);
	}

	public int getInt(int columnIndex) throws SQLException {
		return _rs.getInt(columnIndex);
	}

	public long getLong(int columnIndex) throws SQLException {
		return _rs.getLong(columnIndex);
	}

	public float getFloat(int columnIndex) throws SQLException {
		return _rs.getFloat(columnIndex);
	}

	public double getDouble(int columnIndex) throws SQLException {
		return _rs.getDouble(columnIndex);
	}

	/**
	 * @deprecated
	 * @param columnIndex
	 * @param scale
	 * @throws SQLException
	 */
	public BigDecimal getBigDecimal(int columnIndex, int scale)
			throws SQLException {
		return _rs.getBigDecimal(columnIndex, scale);
	}

	public byte[] getBytes(int columnIndex) throws SQLException {
		return _rs.getBytes(columnIndex);
	}

	public Date getDate(int columnIndex) throws SQLException {
		return _rs.getDate(columnIndex);
	}

	public Time getTime(int columnIndex) throws SQLException {
		return _rs.getTime(columnIndex);
	}

	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		return _rs.getTimestamp(columnIndex);
	}

	public InputStream getAsciiStream(int columnIndex) throws SQLException {
		return _rs.getAsciiStream(columnIndex);
	}

	/**
	 * @deprecated
	 * @param columnIndex
	 * @throws SQLException
	 */
	public InputStream getUnicodeStream(int columnIndex) throws SQLException {
		return _rs.getUnicodeStream(columnIndex);
	}

	public InputStream getBinaryStream(int columnIndex) throws SQLException {
		return _rs.getBinaryStream(columnIndex);
	}

	public String getString(String columnName) throws SQLException {
		return _rs.getString(columnName);
	}

	public boolean getBoolean(String columnName) throws SQLException {
		return _rs.getBoolean(columnName);
	}

	public byte getByte(String columnName) throws SQLException {
		return _rs.getByte(columnName);
	}

	public short getShort(String columnName) throws SQLException {
		return _rs.getShort(columnName);
	}

	public int getInt(String columnName) throws SQLException {
		return _rs.getInt(columnName);
	}

	public long getLong(String columnName) throws SQLException {
		return _rs.getLong(columnName);
	}

	public float getFloat(String columnName) throws SQLException {
		return _rs.getFloat(columnName);
	}

	public double getDouble(String columnName) throws SQLException {
		return _rs.getDouble(columnName);
	}

	/**
	 * 
	 * @param columnName
	 * @param scale
	 * @throws SQLException
	 */
	public BigDecimal getBigDecimal(String columnName, int scale)
			throws SQLException {
		return _rs.getBigDecimal(columnName);
	}

	public byte[] getBytes(String columnName) throws SQLException {
		return _rs.getBytes(columnName);
	}

	public Date getDate(String columnName) throws SQLException {
		return _rs.getDate(columnName);
	}

	public Time getTime(String columnName) throws SQLException {
		return _rs.getTime(columnName);
	}

	public Timestamp getTimestamp(String columnName) throws SQLException {
		return _rs.getTimestamp(columnName);
	}

	public InputStream getAsciiStream(String columnName) throws SQLException {
		return _rs.getAsciiStream(columnName);
	}

	/**
	 * @deprecated
	 * @param columnName
	 * @throws SQLException
	 */
	public InputStream getUnicodeStream(String columnName) throws SQLException {
		return _rs.getUnicodeStream(columnName);
	}

	public InputStream getBinaryStream(String columnName) throws SQLException {
		return _rs.getBinaryStream(columnName);
	}

	public SQLWarning getWarnings() throws SQLException {
		return _rs.getWarnings();
	}

	public void clearWarnings() throws SQLException {
		_rs.clearWarnings();
	}

	public String getCursorName() throws SQLException {
		return _rs.getCursorName();
	}

	public ResultSetMetaData getMetaData() throws SQLException {
		return _rs.getMetaData();
	}

	public Object getObject(int columnIndex) throws SQLException {
		return _rs.getObject(columnIndex);
	}

	public Object getObject(String columnName) throws SQLException {
		return _rs.getObject(columnName);
	}

	public int findColumn(String columnName) throws SQLException {
		return _rs.findColumn(columnName);
	}

	public Reader getCharacterStream(int columnIndex) throws SQLException {
		return _rs.getCharacterStream(columnIndex);
	}

	public Reader getCharacterStream(String columnName) throws SQLException {
		return _rs.getCharacterStream(columnName);
	}

	public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
		return _rs.getBigDecimal(columnIndex);
	}

	public BigDecimal getBigDecimal(String columnName) throws SQLException {
		return _rs.getBigDecimal(columnName);
	}

	public boolean isBeforeFirst() throws SQLException {
		return _rs.isBeforeFirst();
	}

	public int getType() throws SQLException {
		return _rs.getType();
	}

	public int getConcurrency() throws SQLException {
		return _rs.getConcurrency();
	}

	public Statement getStatement() throws SQLException {
		return _rs.getStatement();
	}

	public Object getObject(int i, Map map) throws SQLException {
		return _rs.getObject(i, map);
	}

	public Ref getRef(int i) throws SQLException {
		return _rs.getRef(i);
	}

	public Blob getBlob(int i) throws SQLException {
		return _rs.getBlob(i);
	}

	public Clob getClob(int i) throws SQLException {
		return _rs.getClob(i);
	}

	public Array getArray(int i) throws SQLException {
		return _rs.getArray(i);
	}

	public Object getObject(String colName, Map map) throws SQLException {
		return _rs.getObject(colName, map);
	}

	public Ref getRef(String colName) throws SQLException {
		return _rs.getRef(colName);
	}

	public Blob getBlob(String colName) throws SQLException {
		return _rs.getBlob(colName);
	}

	public Clob getClob(String colName) throws SQLException {
		return _rs.getClob(colName);
	}

	public Array getArray(String colName) throws SQLException {
		return _rs.getArray(colName);
	}

	public Date getDate(int columnIndex, Calendar cal) throws SQLException {
		return _rs.getDate(columnIndex, cal);
	}

	public Date getDate(String columnName, Calendar cal) throws SQLException {
		return _rs.getDate(columnName, cal);
	}

	public Time getTime(int columnIndex, Calendar cal) throws SQLException {
		return _rs.getTime(columnIndex, cal);
	}

	public Time getTime(String columnName, Calendar cal) throws SQLException {
		return _rs.getTime(columnName, cal);
	}

	public Timestamp getTimestamp(int columnIndex, Calendar cal)
			throws SQLException {
		return _rs.getTimestamp(columnIndex, cal);
	}

	public Timestamp getTimestamp(String columnName, Calendar cal)
			throws SQLException {
		return _rs.getTimestamp(columnName, cal);
	}

}
