package com.nuaa.dao;

import java.util.List;

import com.nuaa.bean.bedDfResult;

public interface bedDfResultDao {
	
    void deleteByBedId(Integer zjId);

    void insertBedResult(bedDfResult record);

    List<bedDfResult> selectBedResult();

}