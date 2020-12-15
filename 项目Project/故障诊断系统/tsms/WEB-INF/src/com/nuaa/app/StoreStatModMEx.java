package com.nuaa.app;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import jxl.write.WriteException;

public interface StoreStatModMEx {
	public void StoreStatModMType1Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void StoreStatModMType2Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void StoreStatModMIndi1Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void StoreStatModMIndi2Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void StoreStatModMIndi3Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void StoreStatModMIndi4Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
}
