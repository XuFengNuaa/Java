package com.nuaa.app;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import jxl.write.WriteException;

public interface M_tocheck_xml {
	public void createExcelTocheck(HashMap hashMap,OutputStream os) throws WriteException, IOException;
}




