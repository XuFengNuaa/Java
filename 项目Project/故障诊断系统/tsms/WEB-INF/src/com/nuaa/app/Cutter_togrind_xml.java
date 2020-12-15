package com.nuaa.app;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import jxl.write.WriteException;

public interface Cutter_togrind_xml {	
	public void createExcelTogrind(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void createExcelToprice(HashMap hashMap,OutputStream os) throws WriteException, IOException;
}

