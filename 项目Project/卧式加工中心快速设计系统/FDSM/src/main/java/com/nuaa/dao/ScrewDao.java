package com.nuaa.dao;

import java.util.List;

import com.nuaa.bean.Screw;

public interface ScrewDao {

    void deleteSwById(Integer swid);

    void insertSw(Screw record);

    List<Screw> findSwSelective(Screw record);
    //模糊查询，默认不区分大小写！
    List<Screw> findSwMHSelective(Screw record);
    
    List<Screw> findReasonSw(Screw record);

    void updateSwByIdSelective(Screw record);

    void updateSwById(Screw record);
}