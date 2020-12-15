package com.nuaa.dao;

import java.util.List;

import com.nuaa.bean.bedwgtDf;
import com.nuaa.bean.upwgtDf;

public interface bedwgtDfDao {
    void deleteBedById(Integer bid);

    void insert(bedwgtDf record);

    List<bedwgtDf> selectBedAll(bedwgtDf record);
}