
package com.nuaa.app;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import jxl.write.WriteException;

public interface huizongExcel {
	public void createExcelhuizong(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	}

