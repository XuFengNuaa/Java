package com.nuaa.dao;

import java.util.List;

import com.nuaa.bean.upwgtDf;

public interface upwgtDfDao {
	
    void deleteUpByPrimaryKey(Integer dfid);

    void insert(upwgtDf record);

    List<upwgtDf> selectAll(upwgtDf record);




}