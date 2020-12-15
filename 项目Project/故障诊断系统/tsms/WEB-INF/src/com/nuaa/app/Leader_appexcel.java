package com.nuaa.app;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import jxl.write.WriteException;

public interface Leader_appexcel {
	public void createExcelC(HashMap hashMap, OutputStream os) throws WriteException, IOException;
	public void createExcelCtem(HashMap hashMap, OutputStream os) throws WriteException, IOException;
	public void createExcelCT(HashMap hashMap, OutputStream os) throws WriteException, IOException;
	public void createExcelCTtem(HashMap hashMap, OutputStream os) throws WriteException, IOException;
}
