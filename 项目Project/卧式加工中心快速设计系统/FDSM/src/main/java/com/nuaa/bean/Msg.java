package com.nuaa.bean;

import java.util.ArrayList;
import java.util.List;

public class Msg {
	//状态码
	private Boolean success;
	private String msg; //提示信息
	
	//传输给web的数据
	
	private List extend = new ArrayList();
	private int resultSize;

	public int getResultSize() {
		return resultSize;
	}

	public void setResultSize(int resultSize) {
		this.resultSize = resultSize;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List getExtend() {
		return extend;
	}

	public void setExtend(List extend) {
		this.extend = extend;
	}



}
