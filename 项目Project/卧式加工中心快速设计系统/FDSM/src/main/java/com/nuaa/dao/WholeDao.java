package com.nuaa.dao;

import java.util.List;

import com.nuaa.bean.Whole;

public interface WholeDao {
    void deleteWhById(Integer mtId);

    void insertWh(Whole record);

    List<Whole> findWhSelective(Whole record);
    
    List<Whole> findWhMHSelective(Whole record);

    void updateWhByIdSelective(Whole record);

    void updateWhById(Whole record);
}