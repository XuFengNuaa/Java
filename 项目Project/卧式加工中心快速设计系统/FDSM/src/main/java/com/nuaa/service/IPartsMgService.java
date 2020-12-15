package com.nuaa.service;

import java.util.List;

import com.nuaa.bean.Atc;
import com.nuaa.bean.Screw;
import com.nuaa.bean.Slideway;
import com.nuaa.bean.Spindle;
import com.nuaa.bean.Upright;
import com.nuaa.bean.Whole;
import com.nuaa.bean.Workbed;
import com.nuaa.bean.Workpiece;

public interface IPartsMgService {
	 void deleteWpById(Integer workid);

	 void insertWp(Workpiece record);

	 List<Workpiece> findSelectiveWp(Workpiece record);
	 
	 List<Workpiece> findWpMHSelective(Workpiece record);

     void updateWpByIdSelective(Workpiece record);

     void updateWpById(Workpiece record);
//-----------------Sd.java-----------------------------
     void deleteSdById(Integer sindleId);

     void insertSd(Spindle record);

     List<Spindle> findSelectiveSd(Spindle record);
     
     List<Spindle> findSelectiveMHSd(Spindle record);
	
	 void updateSdByIdSelective(Spindle record);
	
	 void updateSdById(Spindle record);
//-----------------Atc.java---------------------------
	 List<Atc> findSelectiveAtc(Atc record);
	
	 void deleteAtcById(Integer toolid);
	
	 void insertAtc(Atc record);
	 
	 List<Atc> findSelectiveMHAtc(Atc record);
	
	 void updateAtcByIdSelective(Atc record);
	
	 void updateAtcById(Atc record);
//------------------Screw.java----------------------------
	 void deleteSwById(Integer swid);

	 void insertSw(Screw record);

	 List<Screw> findSwSelective(Screw record);
    //模糊查询，默认不区分大小写！
	 List<Screw> findSwMHSelective(Screw record);
	
     void updateSwByIdSelective(Screw record);

     void updateSwById(Screw record);
//------------------slidway.java-------------------------
     void deleteSdwayById(Integer daoguiId);

     void insertSdway(Slideway record);

     List<Slideway> findSdwaySelective(Slideway record);
    
     List<Slideway> findSdwayMHSelective(Slideway record);
    
     void updateSdwayByIdSelective(Slideway record);

     void updateSdwayById(Slideway record);
//-------------------upright.java--------------------------------
     void deleteUpById(Integer uprightId);

     void insertUp(Upright record);

     List<Upright> findUpSelective(Upright record);
    
     List<Upright> findUpMHSelective(Upright record);

     void updateUpByIdSelective(Upright record);

     void updateUpById(Upright record);
//-------------------bed.java-------------------------------------------
     void deleteWbById(Integer bedid);

     void insertWb(Workbed record);

     List<Workbed> findWbMHSelective(Workbed record);
    
     List<Workbed> findWbSelective(Workbed record);

     void updateWbByIdSelective(Workbed record);

     void updateWbById(Workbed record);
//--------------------whole.java-------------------------------
     void deleteWhById(Integer mtId);

     void insertWh(Whole record);

     List<Whole> findWhSelective(Whole record);
    
     List<Whole> findWhMHSelective(Whole record);

     void updateWhByIdSelective(Whole record);

     void updateWhById(Whole record);
}
