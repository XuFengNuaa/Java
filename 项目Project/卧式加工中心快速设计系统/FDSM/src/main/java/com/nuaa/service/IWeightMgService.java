package com.nuaa.service;

import java.util.List;

import com.nuaa.bean.BdWeight;
import com.nuaa.bean.UpWeight;
import com.nuaa.bean.bedDfResult;
import com.nuaa.bean.bedwgtDf;
import com.nuaa.bean.upDfResult;
import com.nuaa.bean.upwgtDf;

public interface IWeightMgService {
	
	void deleteById(Integer wid);

    void insert(UpWeight record);

    List<UpWeight> selectUpById(UpWeight record);

    void updateById(UpWeight record);
// -------------bedWeight.java ----------------
	
    void deleteBdWById(Integer bid);

    void insertBdW(BdWeight record);

    List<BdWeight> selectBd(BdWeight record);


    void updateBdWById(BdWeight record);
    
    //--------------------up打分--------------------
    void deleteUpByPrimaryKey(Integer dfid);

    void insert(upwgtDf record);

    List<upwgtDf> selectAll(upwgtDf record);
    
   //-----------------bed打分-------------------------
    void deleteBedById(Integer bid);

    void insert(bedwgtDf record);

    List<bedwgtDf> selectBedAll(bedwgtDf record);
    //----------------计算-------------
    
    List<UpWeight> reasonUpWgt(String uid);
    
    List<BdWeight> reasonBedWgt(String did);
    
    //------------Weight_result--------------
    
    void deleteByUpId(Integer zjId);

    void insertUpResult(upDfResult record);

    List<upDfResult> selectUpResult();
    
    //-----------bed_result----------------
    
    void deleteByBedId(Integer zjId);

    void insertBedResult(bedDfResult record);

    List<bedDfResult> selectBedResult();

}
