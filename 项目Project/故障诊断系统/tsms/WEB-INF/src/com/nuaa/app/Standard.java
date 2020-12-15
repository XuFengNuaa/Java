/**
 * 
 */
package com.nuaa.app;

import java.util.HashMap;

import org.json.JSONObject;


/**
 * @author mahao
 *
 */
public interface Standard {
	public JSONObject getQueryFile(HashMap queryMap);
	public JSONObject getQueryCompanyAll();
	public HashMap uploadFile(HashMap fileHmap);
	public boolean delFile(HashMap delMap);
	public boolean addrightupFile(HashMap fileHmap);
	public String addFile(HashMap addMap);
	public String modifyFile(HashMap addMap);
	public boolean passFile(HashMap passMap);
	public String getProcess(HashMap processMap);
	public HashMap uploadfile(HashMap fileHmap);
	public String getExcel(HashMap xlsHmap);
	
}