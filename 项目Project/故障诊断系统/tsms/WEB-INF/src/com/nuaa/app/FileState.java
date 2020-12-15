/**
 * 
 */
package com.nuaa.app;

/**
 * @author mahao
 *
 */
public class FileState {
	public static final int UNPASS=-99;
	public static final int EDIT=0;
	public static final int AUDIT=1;
	public static final int REAUDIT=2;
	public static final int CHECK=3;
	public static final int RATIFY=4;
	public static final int PASS=5;

	/*
	 * @param fileState	文件状态
	 * @return 给定状态的工艺册是否可提交申请审核
	 */
	public static boolean canApply(int fileState){
		if((fileState == FileState.UNPASS) || (fileState == FileState.EDIT)){
			return true;
		}else{
			return false;
		}
	}
	/*
	 * @param fileState	文件状态
	 * @return 给定状态的工艺册是否可编辑
	 */
	public static boolean canEdit(int fileState){
		if((fileState == FileState.UNPASS) || (fileState == FileState.EDIT)){
			return true;
		}else{
			return false;
		}
	}
	
}
