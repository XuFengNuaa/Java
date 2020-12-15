package com.nuaa.app;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import jxl.write.WriteException;

public interface Leader_checkexcel {
	public void createExcelC(String leader_num,String leadername,OutputStream os) throws WriteException, IOException;
	public void createExcelCT(String leader_num,String leadername,OutputStream os) throws WriteException, IOException;
	public void createExcelM(String leader_num,String leadername,OutputStream os) throws WriteException, IOException;
	public void createExcelMT(String leader_num,String leadername,OutputStream os) throws WriteException, IOException;
	public void createExcelIn(String leader_num,String leadername,HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void createExcelOut(String leader_num,String leadername,HashMap hashMap,OutputStream os) throws WriteException, IOException;
}
