package com.nuaa.dao;

import java.util.List;

import com.nuaa.bean.UpWeight;

public interface UpWeightDao {
    void deleteById(Integer wid);

    void insert(UpWeight record);

    List<UpWeight> selectUpById(UpWeight record);

    void updateById(UpWeight record);
}