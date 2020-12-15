package com.nuaa.app;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import jxl.write.WriteException;

public interface StoreStatModCTEx {
	public void StoreStatModCT1Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void StoreStatModCT2Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void StoreStatModCT3Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void StoreStatModCT4Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void StoreStatModCT5Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
}
