package com.nuaa.dao;

import java.util.List;

import com.nuaa.bean.Workbed;

public interface WorkbedDao {

    void deleteWbById(Integer bedid);

    void insertWb(Workbed record);

    List<Workbed> findWbMHSelective(Workbed record);
    
    List<Workbed> findWbSelective(Workbed record);
    
    int getCountWb();

    void updateWbByIdSelective(Workbed record);

    void updateWbById(Workbed record);
}