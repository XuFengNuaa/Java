package com.nuaa.controller;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nuaa.bean.Atc;
import com.nuaa.bean.BdWeight;
import com.nuaa.bean.Msg;
import com.nuaa.bean.Screw;
import com.nuaa.bean.Slideway;
import com.nuaa.bean.Spindle;
import com.nuaa.bean.UpWeight;
import com.nuaa.bean.Upright;
import com.nuaa.bean.Workbed;
import com.nuaa.bean.Workpiece;
import com.nuaa.service.IReasonFtService;
import com.nuaa.service.IWeightMgService;

@Controller
public class ReasonFtController {
	private final IReasonFtService reasons;
	private final IWeightMgService wgtService;
	
	private static final Logger logger = LoggerFactory.getLogger(ReasonFtController.class);
	
	public ReasonFtController(IReasonFtService reasons,IWeightMgService wgtService) {
		this.reasons = reasons;
		this.wgtService = wgtService;
	}

	
	@RequestMapping("reasonWp.do")
	@ResponseBody
	public Msg reasonWpMsg(Integer gjMaxd,Integer gjMaxh,Integer gjKg,Double qz_d,Double qz_k,Double qz_h,
								Integer start,Integer limit){
//		System.out.println(qz_d);
		Msg msg = new Msg();
		double[][] wp = new double[3][2];
		wp[0][0] = qz_d;
		wp[0][1] = gjMaxd;
		wp[1][0] = qz_k;
		wp[1][1] = gjKg;
		wp[2][0] = qz_h;
		wp[2][1] = gjMaxh;
		Workpiece workpiece = new Workpiece();
		workpiece.setGongjianMaxd(gjMaxd);
		workpiece.setGongjianH(gjMaxh);
		workpiece.setZaihe(gjKg);
		List<Workpiece> reasonWp = reasons.findReasonWp(workpiece,wp);
		
		if(reasonWp.size() !=0) {
			msg.setSuccess(true);
			msg.setExtend(reasonWp);
		}
		return msg;
	}
	
/*	@RequestMapping("chooseWp.do")
	@ResponseBody
	public Msg chooseWpMsg(Integer workid){
		Msg msg = new Msg();
		System.out.println(workid);
		return null;
	}*/
	@RequestMapping("reasonSd.do")
	@ResponseBody
	public Msg reasonSdMsg(String material,String jgType,Integer wpMaxd,Integer start,Integer limit){
	//	System.out.println(material);
	//	System.out.println(wpMaxd);
		Msg msg = new Msg();
		
		List<Spindle> reasonSd = reasons.findReasonSd(material, jgType, wpMaxd);
		if(reasonSd.size() !=0) {
			msg.setSuccess(true);
			msg.setExtend(reasonSd);
		}
		return msg;
	}
	
	@RequestMapping("reasonAtc.do")
	@ResponseBody
	public Msg reasonAtcMsg(String jggy,String toolType,Integer wpMaxd,Integer start,Integer limit){
	//	System.out.println(material);
	//	System.out.println(wpMaxd);
		Msg msg = new Msg();
		
		List<Atc> reasonAtc = reasons.findReasonAtc(jggy, toolType, wpMaxd);
	//	if(reasonAtc.size() !=0) {
			msg.setSuccess(true);
			msg.setExtend(reasonAtc);
	//	}
		return msg;
	}
	
	@RequestMapping("reasonUp.do")
	@ResponseBody
	public Msg reasonUpMsg(double sideThick,double hole,double ribThick,
				double yt,double s_w,double s_h, Integer wid,double h ,double k ,double yr,
				double w, double dh ,double t,double ho, double rb ,double hn ,double sn){
	//	System.out.println(h);
	//	System.out.println(s_h);

		Msg msg = new Msg();
		Upright record = new Upright();
		double[] param =new double[6];
			param[0] = yt;
			param[1] = sideThick;
			param[2] = hole;
			param[3] = ribThick;
			param[4] = s_w;
			param[5] = s_h;
					
		List<UpWeight> byId = new ArrayList<UpWeight>();
		
		UpWeight upWeight = new UpWeight(null,h,k,yr,w,dh,t,ho,rb,hn,sn);
		
		byId.add(upWeight);
		
		List<Upright> resultList = reasons.findReasonUp(record, param,byId);
		
		if(resultList.size()>0) {
			msg.setSuccess(true);
			msg.setMsg("success");
			msg.setExtend(resultList);
		}
		  
		return msg;
	}
	
	@RequestMapping("reasonSw.do")
	@ResponseBody
	public Msg reasonSwMsg(Double kSpeed,Integer eddzh,Integer jddj,Integer wpMaxd,
								String jgTyp2,double speed,double dzh,double jd,double dc){
	//	System.out.println(kSpeed);
	//	System.out.println(speed);
		Msg msg = new Msg();
		// weight 赋值
		double[][] sw = new double[4][2];
		sw[0][0] = speed;
		sw[1][0] = dzh;
		sw[2][0] = jd ;
		sw[3][0] = dc;
		//	参数
		sw[0][1] = kSpeed;
		sw[1][1] = eddzh;	
		sw[2][1] = jddj;
	//	System.out.println(sw[2][1]);
		try {
			List<Screw> sw1 = reasons.findReasonSw(wpMaxd,jgTyp2,sw);
			msg.setSuccess(true);
			msg.setExtend(sw1);
		} catch (Exception e) {
			msg.setSuccess(false);
			logger.error("推理丝杠出错:"+e.getMessage());   //输出日志
		}
		return msg;
	}
	
	@RequestMapping("reasonSdWay.do")
	@ResponseBody
	public Msg reasonSdWayMsg(Integer xt, Integer yt, Integer zt, Integer upWidth, Integer sdH,
			Integer swGcd, double dwjd,Integer w_edzh ,Integer wpMaxd,double kuan,double jd,
			double dw,double way_dzh,Integer jddj){
	//	System.out.println(xt);
	//	System.out.println(kuan);
		Msg msg = new Msg();
		double[][] way = new double[4][2];
		// weight----
		way[0][0] = kuan;  //轨宽
		way[1][0] = jd;	   //精度等级
		way[2][0] = dw;    //定位
		way[3][0] = way_dzh; //动载荷
		// param
		way[0][1] = swGcd;
		way[1][1] = jddj;  //G2
		way[2][1] = dwjd;
		way[3][1] = w_edzh;  //动载荷
		try {
			List<Slideway> way1 = reasons.findReasonSdWay(xt, yt, zt, upWidth, sdH, wpMaxd,way);
			msg.setSuccess(true);
			msg.setExtend(way1);
		} catch (Exception e) {
			msg.setSuccess(false);
			System.out.println(e.getMessage());
			logger.error("推理线轨出错:"+e.getMessage());   //输出日志
		}   
		return msg; 
	}
	
	@RequestMapping("reasonBd.do")
	@ResponseBody
	public Msg reasonBdMsg(double wphput,double wphchoo,double xiadi,double wpd,
			double bhole,double bribThick, Integer structure,Integer bid,
			double bl , double bw,double bh,double bc,double bxl,double bxd,double bzl,
			double bzd,double bt,double bho,double bs){
	//	System.out.println(xL);
	//	System.out.println(structure);
		Msg msg = new Msg();
		String structure2 = "";
		Workbed record = new Workbed();
			double[] bparam =new double[8];
			bparam[0] = 0;
			bparam[1] = wphput;
			bparam[2] = 0;
			bparam[3] = wphchoo;  
			bparam[4] = xiadi;
			bparam[5] = wpd;
			bparam[6] = bhole;
			bparam[7] = bribThick;		
		if(structure ==1) {
			structure2 = "T型大跨距";
		}else {
			structure2 = "T型中央排屑结构";
		}
		List<BdWeight> bd = new ArrayList<BdWeight>();
		BdWeight bdWgs = new BdWeight(null,bl,bw,bh,bc,bxl,bxd,bzl,bzd,bt,bho,bs);
		 bd.add(bdWgs);
		
		List<Workbed> bList = reasons.findReasonWbed(record, bparam, structure2,bd);
		
		if(bList.size()>0) {
			msg.setSuccess(true);
			msg.setMsg("success");
			msg.setExtend(bList);
		}
		  
		return msg;
	}
// -----------------Upweight.java ----------------------------------
	@RequestMapping("queryUpWeight.do")
	@ResponseBody
	public Msg queryUpWgtMsg(Integer start,Integer limit){
	//	System.out.println(material);
	//	System.out.println(wpMaxd);
		Msg msg = new Msg();
		UpWeight record = new UpWeight();
		List<UpWeight> list = wgtService.selectUpById(record);
		msg.setSuccess(true);
		msg.setExtend(list);
		return msg;		
	}
	
	@RequestMapping("addUpwgt.do")
	@ResponseBody
	public Msg addUpWgtMsg(UpWeight record){
	//	System.out.println(record.getHeight());
		Msg msg = new Msg();
		try {
			wgtService.insert(record);
			msg.setSuccess(true);
			msg.setMsg("添加信息成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("添加信息失败，请稍后再试！");
			logger.error("添加Up权重出错:"+e.getMessage());   //输出日志
		}  
		return msg;		
	}
	
	@RequestMapping("delUpWgt.do")
	@ResponseBody
	public Msg delUpMsg(Integer wid){
		Msg msg = new Msg();
		try {
			wgtService.deleteById(wid);
			msg.setSuccess(true);
			msg.setMsg("删除信息成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("删除信息失败，请稍后再试！");
			logger.error("删除Up权重出错:"+e.getMessage());   //输出日志
			System.out.println(e.getMessage());
		}		
		return msg;
	}
	
	@RequestMapping("updateUpWgt.do")
	@ResponseBody
	public Msg updateUpWgt(UpWeight record){
		Msg msg = new Msg();
		try {
			wgtService.updateById(record);
			msg.setSuccess(true);
			msg.setMsg("编辑信息成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("编辑信息失败，请稍后再试！");
			logger.error("编辑Up权重出错:"+e.getMessage());   //输出日志
		}
		return msg;
	}
	//-----------BedWight.java------------------------------------------
	@RequestMapping("queryBdWeight.do")
	@ResponseBody
	public Msg queryBdWgtMsg(Integer start,Integer limit){

		Msg msg = new Msg();
		BdWeight bdWeight = new BdWeight();
		try {
				List<BdWeight> bdList = wgtService.selectBd(bdWeight);
				msg.setSuccess(true);
				msg.setExtend(bdList);
		} catch (Exception e) {
				msg.setSuccess(false);
				System.out.println(e.getMessage());
				logger.error("查询Bed权重出错:"+e.getMessage());   //输出日志
		}
		 
		return msg;		
	}
	
	@RequestMapping("delBdWgt.do")
	@ResponseBody
	public Msg delBdMsg(Integer bid){
		Msg msg = new Msg();
		try {
			wgtService.deleteBdWById(bid);
			msg.setSuccess(true);
			msg.setMsg("删除信息成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("删除信息失败，请稍后再试！");
			logger.error("删除Bed权重出错:"+e.getMessage());   //输出日志
		}		
		return msg;
	}
	
	@RequestMapping("addBdWgt.do")
	@ResponseBody
	public Msg addBdWgtMsg(BdWeight record){
	//	System.out.println(record.getHeight());
		Msg msg = new Msg();
		try {
			wgtService.insertBdW(record);
			msg.setSuccess(true);
			msg.setMsg("添加信息成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("添加信息失败，请稍后再试！");
			logger.error("添加Bed权重出错:"+e.getMessage());   //输出日志
		}  
		return msg;		
	}
	
	@RequestMapping("updateBdWgt.do")
	@ResponseBody
	public Msg updateBdWgt(BdWeight record){
		Msg msg = new Msg();
		try {
			wgtService.updateBdWById(record);
			msg.setSuccess(true);
			msg.setMsg("编辑信息成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("编辑信息失败，请稍后再试！");
			logger.error("编辑Bed权重出错:"+e.getMessage());   //输出日志
		}
		return msg;
	}
	
	@RequestMapping("queryUpWgt.do")
	@ResponseBody
	public Msg queryUpWgt(UpWeight record){
			
			Msg msg = new Msg();
			List<UpWeight> list = wgtService.selectUpById(record);
			msg.setSuccess(true);
			msg.setExtend(list);
		
		return msg;
	}
	
	
	@RequestMapping("loadUpwgt.do")
	@ResponseBody
	public Msg loadUpwgt(Integer wid){
		Msg msg = new Msg();
	//	System.out.println(wid);

		UpWeight record = new UpWeight();
		record.setWid(wid);
		List<UpWeight> upById = wgtService.selectUpById(record);
		msg.setSuccess(true);
		msg.setExtend(upById);
		msg.setMsg("成功！");

		return msg;
	}
	
	
	@RequestMapping("queryBdWgt.do")
	@ResponseBody
	public Msg queryBdWgt(BdWeight record){
			
			Msg msg = new Msg();
			List<BdWeight> bdById = wgtService.selectBd(record);
			msg.setSuccess(true);
			msg.setExtend(bdById);
		
		return msg;
	}
	
	@RequestMapping("loadBdwgt.do")
	@ResponseBody
	public Msg loadBdwgt(Integer bid){
		Msg msg = new Msg();
	//	System.out.println(wid);

		BdWeight bdWeight = new BdWeight();
		bdWeight.setBid(bid);
		List<BdWeight> bdById = wgtService.selectBd(bdWeight);
		msg.setSuccess(true);
		msg.setExtend(bdById);
		msg.setMsg("成功！");

		return msg;
	}
	
	
}
