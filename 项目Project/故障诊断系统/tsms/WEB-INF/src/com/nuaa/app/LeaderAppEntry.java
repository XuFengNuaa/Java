package com.nuaa.app;

import java.io.IOException;
import java.io.OutputStream;

import jxl.write.WriteException;

public interface LeaderAppEntry {
	public void createExcelCT(OutputStream os) throws WriteException, IOException;
	public void createExcelMt(OutputStream os) throws WriteException, IOException;
	public void createExcelNmg(OutputStream os) throws WriteException, IOException;
	public void createExcelC(OutputStream os) throws WriteException, IOException;
}
