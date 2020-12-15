package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

import com.jenkov.prizetags.tree.itf.ITree;
import com.jenkov.prizetags.tree.itf.ITreeNode;

public interface ManufactureMod {
	 public boolean addFile(HashMap fileHmap);
	 public JSONObject getQueryFile(HashMap hashmap);//已用
	 public HashMap uploadFile1(HashMap fileHmap);
	 public HashMap uploadFile2(HashMap fileHmap);
	 public boolean addImg(HashMap hashmap);
	 public JSONObject QueryAllDalei();
	 public JSONObject QueryAllXiaoleiByDalei(HashMap hashmap);
	 public String delPart(HashMap hashMap);
	 public String editFile(HashMap hashMap);
	 public String getFile(String dir,String tuhao);
	 public String getFile2(String dir,String tuhao);

}
