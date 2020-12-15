package com.nuaa.dao;

import java.util.List;

import com.nuaa.bean.Upright;

public interface UprightDao {

    void deleteUpById(Integer uprightId);

    void insertUp(Upright record);

    List<Upright> findUpSelective(Upright record);
    
    List<Upright> findUpMHSelective(Upright record);

    int getCountUp();

    void updateUpByIdSelective(Upright record);

    void updateUpById(Upright record);
}