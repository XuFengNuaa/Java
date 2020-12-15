package com.nuaa.dao;

import java.util.List;
import com.nuaa.bean.Workpiece;

public interface WorkpieceDao {
    void deleteWpById(Integer workid);

    void insertWp(Workpiece record);
    
    List<Workpiece> findReasonWp(Workpiece record);
    
    List<Workpiece> findWpMHSelective(Workpiece record);

    List<Workpiece> findSelectiveWp(Workpiece record);

    void updateWpByIdSelective(Workpiece record);

    void updateWpById(Workpiece record);
}