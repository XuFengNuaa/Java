package com.nuaa.dao;

import java.util.List;

import com.nuaa.bean.Slideway;

public interface SlidewayDao {

    void deleteSdwayById(Integer daoguiId);

    void insertSdway(Slideway record);

    List<Slideway> findSdwaySelective(Slideway record);
    
    List<Slideway> findSdwayMHSelective(Slideway record);
    
    List<Slideway> findReasonSdWay(Slideway record);
 
    void updateSdwayByIdSelective(Slideway record);

    void updateSdwayById(Slideway record);
}