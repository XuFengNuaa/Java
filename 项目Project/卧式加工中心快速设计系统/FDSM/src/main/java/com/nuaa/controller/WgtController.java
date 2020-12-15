package com.nuaa.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuaa.bean.BdWeight;
import com.nuaa.bean.BedWgtName;
import com.nuaa.bean.Msg;
import com.nuaa.bean.UpWeight;
import com.nuaa.bean.UpWgtName;
import com.nuaa.bean.Workpiece;
import com.nuaa.bean.bedDfResult;
import com.nuaa.bean.bedwgtDf;
import com.nuaa.bean.upDfResult;
import com.nuaa.bean.upwgtDf;
import com.nuaa.service.IWeightMgService;

@Controller
public class WgtController {

	private final IWeightMgService wgtService;
	private com.nuaa.bean.upwgtDf upwgtDf2;
	
	public WgtController(IWeightMgService wgtService) {
		this.wgtService = wgtService;
	}
	
	@RequestMapping("addUpDf.do")
	@ResponseBody
	public Msg addUpDfMsg(Integer u11,Integer u12,Integer u13,Integer u14,Integer u15,Integer u16,Integer u17,Integer u18,Integer u19,
			Integer u22,Integer u23,Integer u24,Integer u25,Integer u26,Integer u27,Integer u28,Integer u29,
			Integer u32,Integer u33,Integer u34,Integer u35,Integer u36,Integer u37,Integer u38,
			Integer u42,Integer u43,Integer u44,Integer u45,Integer u46,Integer u47,
			Integer u52,Integer u53,Integer u54,Integer u55,Integer u56,
			Integer u62,Integer u63,Integer u64,Integer u65,
			Integer u72,Integer u73,Integer u74,
			Integer u82,Integer u83,
			Integer u92,String loginName){
		
		Msg msg = new Msg();
	
		try {
			wgtService.insert(new upwgtDf(null, 1, u11, u12, u13, u14, u15, u16, u17, u18, u19)); //1
			wgtService.insert(new upwgtDf(null, 0, 1, u22, u23, u24, u25, u26, u27, u28, u29)); //2
			wgtService.insert(new upwgtDf(null, 0, 0, 1, u32, u33, u34, u35, u36, u37, u38));//3
			wgtService.insert(new upwgtDf(null, 0, 0, 0, 1, u42, u43, u44, u45, u46, u47));//4
			wgtService.insert(new upwgtDf(null, 0, 0, 0, 0, 1, u52, u53, u54, u55, u56));//5
			wgtService.insert(new upwgtDf(null, 0, 0, 0, 0, 0, 1, u62, u63, u64, u65));//6
			wgtService.insert(new upwgtDf(null, 0, 0, 0, 0, 0, 0, 1, u72, u73, u74));//7
			wgtService.insert(new upwgtDf(null, 0, 0, 0, 0, 0, 0, 0, 1, u82, u83));//8
			wgtService.insert(new upwgtDf(null, 0, 0, 0, 0, 0, 0, 0, 0, 1, u92));//9
			wgtService.insert(new upwgtDf(null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1));//10
						
			msg.setSuccess(true);
			msg.setMsg("提交成功");
			//--同时 添加result信息
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
	        Date d= new Date();
	       wgtService.insertUpResult(new upDfResult(null, "zj-"+loginName,sdf.format(d)));
	       
		} catch (Exception e) {
			
			msg.setSuccess(false);
			System.out.println(e.getMessage());
		}
	
		return msg;
		
	}
	
	@RequestMapping("addBedDf.do")
	@ResponseBody
	public Msg addBedDfMsg(Integer b11,Integer b12,Integer b13,Integer b14,Integer b15,Integer b16,Integer b17,Integer b18,Integer b19,Integer b20,
			Integer b22,Integer b23,Integer b24,Integer b25,Integer b26,Integer b27,Integer b28,Integer b29,Integer b30,
			Integer b32,Integer b33,Integer b34,Integer b35,Integer b36,Integer b37,Integer b38,Integer b39,
			Integer b42,Integer b43,Integer b44,Integer b45,Integer b46,Integer b47,Integer b48,
			Integer b52,Integer b53,Integer b54,Integer b55,Integer b56,Integer b57,
			Integer b62,Integer b63,Integer b64,Integer b65,Integer b66,
			Integer b72,Integer b73,Integer b74,Integer b75,
			Integer b82,Integer b83,Integer b84,
			Integer b92,Integer b93,
			Integer b102,String loginName){
		
		Msg msg = new Msg();
	
//		System.out.println(d11);
//		System.out.println(d12);
		try {
			wgtService.insert(new bedwgtDf(null, 1, b11, b12, b13, b14, b15, b16, b17, b18, b19,b20)); //1
			wgtService.insert(new bedwgtDf(null, 0, 1, b22, b23, b24, b25, b26, b27, b28, b29,b30)); //2
			wgtService.insert(new bedwgtDf(null, 0, 0, 1, b32, b33, b34, b35, b36, b37, b38,b39));//3
			wgtService.insert(new bedwgtDf(null, 0, 0, 0, 1, b42, b43, b44, b45, b46, b47,b48));//4
			wgtService.insert(new bedwgtDf(null, 0, 0, 0, 0, 1, b52, b53, b54, b55, b56,b57));//5
			wgtService.insert(new bedwgtDf(null, 0, 0, 0, 0, 0, 1, b62, b63, b64, b65,b66));//6
			wgtService.insert(new bedwgtDf(null, 0, 0, 0, 0, 0, 0, 1, b72, b73, b74,b75));//7
			wgtService.insert(new bedwgtDf(null, 0, 0, 0, 0, 0, 0, 0, 1, b82, b83,b84));//8
			wgtService.insert(new bedwgtDf(null, 0, 0, 0, 0, 0, 0, 0, 0, 1, b92,b93));//9
			wgtService.insert(new bedwgtDf(null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,b102));//10
			wgtService.insert(new bedwgtDf(null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,1));//11
		
			msg.setSuccess(true);
			msg.setMsg("提交成功");
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
	        Date d= new Date();
	       wgtService.insertBedResult(new bedDfResult(null, "zj-"+loginName,sdf.format(d)));
		} catch (Exception e) {
			msg.setSuccess(false);
			System.out.println(e.getMessage());
		}   
	
		return msg;
		
	}
	
//------------Up查询结果和计算打分----------------	
	@RequestMapping("queryupDfResult.do")
	@ResponseBody
	public Msg queryupDfResultMsg(Integer start,Integer limit) {
		Msg msg = new Msg();
		try {
			PageHelper.startPage((start/limit)+1, limit);
			List<upDfResult> upResult = wgtService.selectUpResult();
			PageInfo<upDfResult> page0 = new PageInfo<>(upResult);
			int total = (int) page0.getTotal();
			msg.setSuccess(true);
			msg.setExtend(upResult);
			msg.setResultSize(total);
		} catch (Exception e) {
			msg.setSuccess(false);
		}		
		return msg;
		
	}
	
	@RequestMapping("jsUpDf.do")
	@ResponseBody
	public Msg jsUpDfMsg(String uid){
		Msg msg = new Msg();
		try {
			
			List<UpWeight> upWgt = wgtService.reasonUpWgt(uid);
			msg.setSuccess(true);
			msg.setExtend(upWgt);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return msg;
		
	}
	
	//------------bed查询结果和计算打分----------------	
		@RequestMapping("querybedDfResult.do")
		@ResponseBody
		public Msg querybedDfResultMsg(Integer start,Integer limit) {
			Msg msg = new Msg();
			
			try {
				PageHelper.startPage((start/limit)+1, limit);
				List<bedDfResult> bedResult = wgtService.selectBedResult();
				PageInfo<bedDfResult> page = new PageInfo<>(bedResult);
				int total = (int) page.getTotal();
				msg.setSuccess(true);
				msg.setExtend(bedResult);
				msg.setResultSize(total);				
			} catch (Exception e) {
				
				
			}
				
			return msg;
			
		}
		
		@RequestMapping("jsBedDf.do")
		@ResponseBody
		public Msg jsBedDfMsg(String did){
			Msg msg = new Msg();
			try {
				
				List<BdWeight> bedWgt = wgtService.reasonBedWgt(did);
				msg.setSuccess(true);
				msg.setExtend(bedWgt);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			return msg;
			
		}
//---查看详情		
		@RequestMapping("selectUpWgt.do")
		@ResponseBody
		public Msg selectUpWgtMsg(Integer dfid){
			Msg msg = new Msg();
			try {
				upwgtDf upwgtDf = new upwgtDf();
				upwgtDf.setDfid((dfid-1)*10+1);
				List<upwgtDf> list = wgtService.selectAll(upwgtDf);
				upwgtDf df0 = list.get(0);
				upwgtDf df1 = list.get(1);
				upwgtDf df2 = list.get(2);
				upwgtDf df3 = list.get(3);	
				upwgtDf df4 = list.get(4);
				upwgtDf df5 = list.get(5);
				upwgtDf df6 = list.get(6);
				upwgtDf df7 = list.get(7);
				upwgtDf df8 = list.get(8);
				
				ArrayList<UpWgtName> list2 = new ArrayList<UpWgtName>();
				
				UpWgtName name	=new UpWgtName(df0.getKuang(), df0.getYroute(), df0.getSdwidth(), df0.getSdheight(), df0.getSidethick(), df0.getHole(), df0.getRibthick(), df0.getHnumber(), df0.getSnumber(), 
						df1.getYroute(), df1.getSdwidth(), df1.getSdheight(), df1.getSidethick(), df1.getHole(), df1.getRibthick(), df1.getHnumber(), df1.getSnumber(), 
						df2.getSdwidth(), df2.getSdheight(), df2.getSidethick(), df2.getHole(), df2.getRibthick(), df2.getHnumber(), df2.getSnumber(), 
						df3.getSdheight(), df3.getSidethick(), df3.getHole(), df3.getRibthick(), df3.getHnumber(), df3.getSnumber(), 
						df4.getSidethick(), df4.getHole(), df4.getRibthick(), df4.getHnumber(), df4.getSnumber(), 
						df5.getHole(), df5.getRibthick(), df5.getHnumber(), df5.getSnumber(), 
						df6.getRibthick(), df6.getHnumber(), df6.getSnumber(), 
						df7.getHnumber(), df7.getSnumber(), 
						df8.getSnumber());
				
				
				list2.add(name);
				msg.setSuccess(true);
				msg.setExtend(list2);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			return msg;
			
		}
		
		@RequestMapping("selectBedWgt.do")
		@ResponseBody
		public Msg selectBedWgtMsg(Integer bid){
			Msg msg = new Msg();
			try {
				 bedwgtDf bedwgtDf = new bedwgtDf();
				 bedwgtDf.setBid((bid-1)*11+1);
				  List<bedwgtDf> list = wgtService.selectBedAll(bedwgtDf);
				
				bedwgtDf bf0 = list.get(0);
				bedwgtDf bf1 = list.get(1);
				bedwgtDf bf2 = list.get(2);
				bedwgtDf bf3 = list.get(3);	
				bedwgtDf bf4 = list.get(4);
				bedwgtDf bf5 = list.get(5);
				bedwgtDf bf6 = list.get(6);
				bedwgtDf bf7 = list.get(7);
				bedwgtDf bf8 = list.get(8);
				bedwgtDf bf9 = list.get(9);
				
				ArrayList<BedWgtName> list2 = new ArrayList<BedWgtName>();
				
				BedWgtName	name =	new BedWgtName(bf0.getbWidth(), bf0.getbHeight(), bf0.getbCd(), bf0.getbXl(), bf0.getbXd(), bf0.getbZl(), bf0.getbZd(), bf0.getbRibthick(), bf0.getbHole(), bf0.getbStructure(), 
						bf1.getbHeight(), bf1.getbCd(), bf1.getbXl(), bf1.getbXd(), bf1.getbZl(), bf1.getbZd(), bf1.getbRibthick(), bf1.getbHole(), bf1.getbStructure(), 
						bf2.getbCd(), bf2.getbXl(), bf2.getbXd(), bf2.getbZl(), bf2.getbZd(), bf2.getbRibthick(), bf2.getbHole(), bf2.getbStructure(), 
						bf3.getbXl(), bf3.getbXd(), bf3.getbZl(), bf3.getbZd(), bf3.getbRibthick(), bf3.getbHole(), bf3.getbStructure(), 
						bf4.getbXd(), bf4.getbZl(), bf4.getbZd(), bf4.getbRibthick(), bf4.getbHole(), bf4.getbStructure(), 
						bf5.getbZl(), bf5.getbZd(), bf5.getbRibthick(), bf5.getbHole(), bf5.getbStructure(), 
						bf6.getbZd(), bf6.getbRibthick(), bf6.getbHole(), bf6.getbStructure(), 
						bf7.getbRibthick(), bf7.getbHole(), bf7.getbStructure(), 
						bf8.getbHole(), bf8.getbStructure(), 
						bf9.getbStructure());		
				list2.add(name);
				msg.setSuccess(true);
				msg.setExtend(list2);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			return msg;
			
		}
	
}
