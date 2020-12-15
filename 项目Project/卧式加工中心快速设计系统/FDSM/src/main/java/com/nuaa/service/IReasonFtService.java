package com.nuaa.service;

import java.util.List;
import com.nuaa.bean.Atc;
import com.nuaa.bean.Screw;
import com.nuaa.bean.Slideway;
import com.nuaa.bean.Spindle;
import com.nuaa.bean.UpWeight;
import com.nuaa.bean.Upright;
import com.nuaa.bean.Workbed;
import com.nuaa.bean.Workpiece;

public interface IReasonFtService {
	
	 List<Workpiece> findReasonWp(Workpiece record,double wp[][]);
	
	 List<Spindle> findReasonSd(String material,String jgType,Integer wpMaxd);
	
	 List<Atc> findReasonAtc(String jggy,String toolType,Integer wpMaxd);
	
	 List<Upright> findReasonUp(Upright record,double[] param,List byList);
	 
	 List<Screw> findReasonSw(Integer wpMaxd,String jgTyp2,double[][] sw);
	 
	 List<Slideway> findReasonSdWay(Integer xt,Integer yt,Integer zt,Integer upWidth,
			 					Integer sdH,Integer wpMaxD,double[][] way);
	 
	 List<Workbed> findReasonWbed(Workbed record,double[] bparam,String structure,List bd);
	 
	 int getCountUp();  //upright总数
	 
	 int getCountWb(); // bed总数
	 
	
	 
}
