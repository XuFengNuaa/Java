package com.nuaa.dao;

import java.util.List;

import com.nuaa.bean.BdWeight;

public interface BdWeightDao {
	
    void deleteBdWById(Integer bid);

    void insertBdW(BdWeight record);

    List<BdWeight> selectBd(BdWeight record);


    void updateBdWById(BdWeight record);
}