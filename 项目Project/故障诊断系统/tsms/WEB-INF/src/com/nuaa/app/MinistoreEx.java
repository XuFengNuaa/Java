package com.nuaa.app;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import jxl.write.WriteException;

import org.json.JSONObject;
public interface MinistoreEx{
	
	public void MinistoreCEx(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void MinistoreMEx(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void MinistoreMTEx(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void MinistoreCTEx(HashMap hashMap,OutputStream os) throws WriteException, IOException;
}
