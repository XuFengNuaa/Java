package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;

import com.jenkov.prizetags.tree.itf.ITree;
import com.jenkov.prizetags.tree.itf.ITreeNode;

public interface MillSim_Mod {
	 public JSONObject QueryAllDalei();//已用
	 public JSONObject QueryAllXiaoleiByDalei(HashMap hashmap);//已用
	 public JSONObject getQuerytuhaoAll();//已用
	 public JSONObject getQueryMaterialAll();//已用
	 public JSONObject getQueryMachineAll();//已用
	 public JSONObject getQuerySimAll();
	 public JSONObject QueryMilldetail(HashMap hashmap);//相似实例定额信息
	 public JSONObject SimView(HashMap hashmap);//相似事例总体信息
}
