package com.nuaa.app;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import jxl.write.WriteException;

public interface StoreStatModMTEx {
	public void StoreStatModMTType1Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void StoreStatModMTType2Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void StoreStatModMTIndi1Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void StoreStatModMTIndi2Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
	public void StoreStatModMTIndi3Ex(HashMap hashMap,OutputStream os) throws WriteException, IOException;
}
