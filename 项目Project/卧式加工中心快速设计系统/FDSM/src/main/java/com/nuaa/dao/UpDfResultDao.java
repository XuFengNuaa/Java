package com.nuaa.dao;

import java.util.List;

import com.nuaa.bean.upDfResult;

public interface UpDfResultDao {
	
    void deleteByUpId(Integer zjId);

    void insertUpResult(upDfResult record);

    List<upDfResult> selectUpResult();

}