package com.nuaa.app;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import jxl.write.WriteException;

public interface Leader_ltexcel {
	public void createExcelC(HashMap hashMap, OutputStream os) throws WriteException, IOException;
	public void createExcelM(HashMap hashMap, OutputStream os) throws WriteException, IOException;
	public void createExcelCT(HashMap hashMap, OutputStream os) throws WriteException, IOException;
	public void createExcelMT(HashMap hashMap, OutputStream os) throws WriteException, IOException;
}
