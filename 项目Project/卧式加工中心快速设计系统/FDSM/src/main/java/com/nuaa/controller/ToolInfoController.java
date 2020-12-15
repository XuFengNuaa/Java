package com.nuaa.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuaa.bean.Atc;
import com.nuaa.bean.Msg;
import com.nuaa.bean.Screw;
import com.nuaa.bean.Slideway;
import com.nuaa.bean.Spindle;
import com.nuaa.bean.Upright;
import com.nuaa.bean.Whole;
import com.nuaa.bean.Workbed;
import com.nuaa.bean.Workpiece;
import com.nuaa.service.IPartsMgService;



@Controller
public class ToolInfoController {
	private final IPartsMgService partMg;
	
	private static final Logger logger = LoggerFactory.getLogger(ToolInfoController.class);

	public ToolInfoController(IPartsMgService partMg) {
		this.partMg = partMg;
	}
	//-----------------------Wp.js--------------------------------------------------------------------
	@RequestMapping("queryWp.do")
	@ResponseBody
	public Msg queryMsg(String inputext,String selectextId,String sousuo,Integer start,Integer limit){
		Workpiece workpiece = new Workpiece();
		Msg result =new Msg();
		//System.out.println(inputext +"----" +selectextId );
		// 由于分页需要进行二次参数传递，不是通过query方法，所以form的值传不过来，
        //若采用"name".equals(ID)会发生空指针异常，可以采用下面方法或者“常量”.equals(变量)即可解决！
		if("wp".equals(selectextId)){
					workpiece.setType(inputext);				
				}else if("jc".equals(selectextId)){
					workpiece.setCourse(inputext);
					}
		PageHelper.startPage((start/limit)+1, limit);
		try {
			if("wanquan".equals(sousuo)) {	
				List<Workpiece> workpieces0 = partMg.findSelectiveWp(workpiece);
				PageInfo<Workpiece> page = new PageInfo<>(workpieces0);
				int total = (int) page.getTotal();
				//System.out.println(total);
				result.setResultSize(total);
				result.setExtend(workpieces0);
				
			}else {
				List<Workpiece> workpieces0 = partMg.findWpMHSelective(workpiece);
				PageInfo<Workpiece> page = new PageInfo<>(workpieces0);
				int total = (int) page.getTotal();
				//System.out.println(total);
				result.setResultSize(total);
				result.setExtend(workpieces0);
			}
			
			logger.info("查询工作台信息");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("数据加载失败，请稍后再试！");
			logger.error("查询工作台出错:"+e.getMessage());   //输出日志
		}
		
		return result;
	}
	
	@RequestMapping("addWp.do")
	@ResponseBody
	public Msg addWpMsg(Workpiece record){
		Msg msg = new Msg();
		System.out.println(record.getFendu());
		try {
			partMg.insertWp(record);
			msg.setSuccess(true);
			msg.setMsg("添加信息成功！");
			logger.info("添加工作台信息，型号"+record.getType());
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("添加信息失败，请稍后再试！");
			logger.error("添加工作台出错:"+e.getMessage());   //输出日志
		}
		return msg;
	}
	
	@RequestMapping("delWp.do")
	@ResponseBody
	public Msg delWpMsg(Integer workid){
		Msg msg = new Msg();
		try {
			partMg.deleteWpById(workid);
			msg.setSuccess(true);
			msg.setMsg("删除信息成功！");
			logger.info("删除工作台信息,id："+workid);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("删除信息失败，请稍后再试！");
			logger.error("删除工作台出错:"+e.getMessage());   //输出日志
		}		
		return msg;
	}
	
	@RequestMapping("updateWp.do")
	@ResponseBody
	public Msg updateWpMsg(Workpiece record){
		Msg msg = new Msg();
		try {
			partMg.updateWpById(record);
			msg.setSuccess(true);
			msg.setMsg("编辑信息成功！");
			logger.info("编辑工作台信息，id："+record.getWorkid());

		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("编辑信息失败，请稍后再试！");
			logger.error("编辑工作台出错:"+e.getMessage());   //输出日志
		}
		return msg;
	}
//----------------spindle.js----------------------------------------------------
	@RequestMapping("querySd.do")
	@ResponseBody
	public Msg querySdMsg(String inputext,String selectextId,String sousuo,Integer start,Integer limit){
		Spindle spindle = new Spindle();
		Msg result =new Msg();
		//System.out.println(inputext +"----" +selectextId );
		// 由于分页需要进行二次参数传递，不是通过query方法，所以form的值传不过来，
        //若采用"name".equals(ID)会发生空指针异常，可以采用下面方法或者“常量”.equals(变量)即可解决！
		if("sd".equals(selectextId)){
					spindle.setSdType(inputext);				
				}else if("jc".equals(selectextId)){
					spindle.setCourse(inputext);
					}else if("jxsd".equals(selectextId)) {
						spindle.setKinds("机械主轴");
					}else if("dsd".equals(selectextId)) { spindle.setKinds("电主轴");}
		PageHelper.startPage((start/limit)+1, limit);
		try {
			if("wanquan".equals(sousuo)) {	
				List<Spindle> spindle0 = partMg.findSelectiveSd(spindle);
				PageInfo<Spindle> page1 = new PageInfo<>(spindle0);
				int total = (int) page1.getTotal();
				//System.out.println(total);
				result.setSuccess(true);
				result.setResultSize(total);
				result.setExtend(spindle0);
				
			}else {
				List<Spindle> spindle0 = partMg.findSelectiveMHSd(spindle);
				PageInfo<Spindle> page1 = new PageInfo<>(spindle0);
				int total = (int) page1.getTotal();
				//System.out.println(total);
				result.setSuccess(true);
				result.setResultSize(total);
				result.setExtend(spindle0);
			}		
			logger.info("查询主轴信息");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("数据加载失败，请稍后再试！");
			logger.error("查询主轴出错:"+e.getMessage());   //输出日志
		}
		
		return result;
	}
	
	@RequestMapping("addSd.do")
	@ResponseBody
	public Msg addSdMsg(Spindle record){
		Msg msg = new Msg();
		try {
			partMg.insertSd(record);
			msg.setSuccess(true);
			msg.setMsg("添加信息成功！");
			logger.info("添加主轴工作台信息,型号是："+record.getSdType());
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("添加信息失败，请稍后再试！");
			logger.error("添加主轴出错:"+e.getMessage());   //输出日志
		}
		return msg;
	}
	
	@RequestMapping("delSd.do")
	@ResponseBody
	public Msg delSdMsg(Integer sindleId){
		Msg msg = new Msg();
		try {
			partMg.deleteSdById(sindleId);
			msg.setSuccess(true);
			msg.setMsg("删除信息成功！");
			logger.info("删除主轴信息，id:"+sindleId);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("删除信息失败，请稍后再试！");
			logger.error("删除主轴出错:"+e.getMessage());   //输出日志
		}		
		return msg;
	}
	
	@RequestMapping("updateSd.do")
	@ResponseBody
	public Msg updateSdMsg(Spindle record){
		Msg msg = new Msg();
		try {
			partMg.updateSdById(record);
			msg.setSuccess(true);
			msg.setMsg("编辑信息成功！");
			logger.info("编辑主轴信息，型号："+record.getSdType());
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("编辑信息失败，请稍后再试！");
			logger.error("编辑主轴出错:"+e.getMessage());   //输出日志
		}
		return msg;
	}
//--------------------atc.java-------------------------------------
	@RequestMapping("queryAtc.do")
	@ResponseBody
	public Msg queryAtcMsg(String inputext,String selectextId,String sousuo,Integer start,Integer limit){
		Atc atc = new Atc();
		Msg result =new Msg();
		if("kind".equals(selectextId)){
					atc.setXingshi(inputext);				
				}else if("jc".equals(selectextId)){
					atc.setCourse(inputext);}
		PageHelper.startPage((start/limit)+1, limit);
		
		try {
			if("wanquan".equals(sousuo)) {	
				List<Atc> atc0 = partMg.findSelectiveAtc(atc);
				PageInfo<Atc> page3 = new PageInfo<>(atc0);
				int total = (int) page3.getTotal();
				//System.out.println(total);
				result.setSuccess(true);
				result.setResultSize(total);
				result.setExtend(atc0);
				
			}else {
				List<Atc> atc0 = partMg.findSelectiveMHAtc(atc);
				PageInfo<Atc> page3 = new PageInfo<>(atc0);
				int total = (int) page3.getTotal();
				//System.out.println(total);
				result.setSuccess(true);
				result.setResultSize(total);
				result.setExtend(atc0);
			}		
			
			
			logger.info("查询刀库信息");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("数据加载失败，请稍后再试！");
			logger.error("查询atc出错:"+e.getMessage());   //输出日志
		}
		return result;
	}
	
	@RequestMapping("addAtc.do")
	@ResponseBody
	public Msg addAtcMsg(Atc record){
		Msg msg = new Msg();
		try {
			record.setTooldaihao(record.getCourse()+"-tool");
			partMg.insertAtc(record);
			msg.setSuccess(true);
			msg.setMsg("添加信息成功！");
			logger.info("添加刀库信息，型号："+record.getTooldaihao());
		} catch (Exception e) {
			System.out.println(e.getMessage());  //异常信息的输出
			msg.setSuccess(false);
			msg.setMsg("添加信息失败，请稍后再试！");
			logger.error("添加atc出错:"+e.getMessage());   //输出日志
		}
		return msg;
	}
	
	@RequestMapping("delAtc.do")
	@ResponseBody
	public Msg delAtcMsg(Integer toolid){
		Msg msg = new Msg();
		try {
			partMg.deleteAtcById(toolid);
			msg.setSuccess(true);
			msg.setMsg("删除信息成功！");
			logger.info("删除刀库信息，id："+toolid);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			msg.setSuccess(false);
			msg.setMsg("删除信息失败，请稍后再试！");
			logger.error("删除atc出错:"+e.getMessage());   //输出日志
		}		
		return msg;
	}
	
	@RequestMapping("updateAtc.do")
	@ResponseBody
	public Msg updateAtcMsg(Atc record){
		Msg msg = new Msg();
		try {
			record.setTooldaihao(record.getCourse()+"-tool");
			partMg.updateAtcById(record);
			msg.setSuccess(true);
			msg.setMsg("编辑信息成功！");
			logger.info("编辑刀库信息，id："+record.getToolid());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			msg.setSuccess(false);
			msg.setMsg("编辑信息失败，请稍后再试！");
			logger.error("编辑atc出错:"+e.getMessage());   //输出日志
		}
		return msg;
	}
//--------------------sw.java---------------------------------------
	@RequestMapping("querySw.do")
	@ResponseBody
	public Msg querySwMsg(String inputext,String selectextId,String sousuo,Integer start,Integer limit){
				Screw screw = new Screw();
				Msg result =new Msg();
				if("sw".equals(selectextId)){
					screw.setXinghao(inputext);				
						}  
				PageHelper.startPage((start/limit)+1, limit);
				
				try {
					if("wanquan".equals(sousuo)) {	
						List<Screw> screw0 = partMg.findSwSelective(screw);
						PageInfo<Screw> page4 = new PageInfo<>(screw0);
						int total = (int) page4.getTotal();
						//System.out.println(total);
						result.setSuccess(true);
						result.setResultSize(total);
						result.setExtend(screw0);
						
					}else {
						List<Screw> screw0 = partMg.findSwMHSelective(screw);
						PageInfo<Screw> page4 = new PageInfo<>(screw0);
						int total = (int) page4.getTotal();
						//System.out.println(total);
						result.setSuccess(true);
						result.setResultSize(total);
						result.setExtend(screw0);
					}		
					
					
					logger.info("查询丝杠信息");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					result.setSuccess(false);
					logger.error("查询丝杠出错:"+e.getMessage());   //输出日志
				}
				return result;
			}
				
	@RequestMapping("addSw.do")
	@ResponseBody
	public Msg addSwMsg(Screw record){
				Msg msg = new Msg();
				try {
					partMg.insertSw(record);
					msg.setSuccess(true);
					msg.setMsg("添加信息成功！");
					logger.info("添加丝杠信息，型号："+record.getXinghao());
				} catch (Exception e) {
					System.out.println(e.getMessage());  //异常信息的输出
					msg.setSuccess(false);
					msg.setMsg("添加信息失败，请稍后再试！");
					logger.error("添加丝杠出错:"+e.getMessage());   //输出日志
				}
				return msg;
			}

	@RequestMapping("delSw.do")
	@ResponseBody
	public Msg delSwMsg(Integer swid){
				Msg msg = new Msg();
				try {
					partMg.deleteSwById(swid);
					msg.setSuccess(true);
					msg.setMsg("删除信息成功！");
					logger.info("删除丝杠信息，id:"+swid);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					msg.setSuccess(false);
					msg.setMsg("删除信息失败，请稍后再试！");
					logger.error("删除丝杠出错:"+e.getMessage());   //输出日志
				}		
					return msg;
				}
				
	@RequestMapping("updateSw.do")
	@ResponseBody
	public Msg updateSwMsg(Screw record){
				Msg msg = new Msg();
				try {
					partMg.updateSwById(record);
					msg.setSuccess(true);
					msg.setMsg("编辑信息成功！");
					logger.info("编辑丝杠信息，id："+record.getSwid());
				} catch (Exception e) {
					System.out.println(e.getMessage());
					msg.setSuccess(false);
					msg.setMsg("编辑信息失败，请稍后再试！");
					logger.error("编辑丝杠出错:"+e.getMessage());   //输出日志
				}
				return msg;
			}
//--------------------Sdway.java---------------------------------------
	@RequestMapping("querySdway.do")
	@ResponseBody
	public Msg querySdwayMsg(String inputext,String selectextId,String sousuo,Integer start,Integer limit){
				Slideway slideway =new Slideway();
				Msg result =new Msg();
				if("sdway".equals(selectextId)){
					slideway.setXinghao(inputext);				
						}else if("jc".equals(selectextId)){
							slideway.setCourse(inputext);}else if ("axis".equals(selectextId)) {
								 slideway.setZhou(inputext);}else if ("jd".equals(selectextId)) {
									 slideway.setJdlevel(inputext);	}
				PageHelper.startPage((start/limit)+1, limit);
											   
		try {
				if("wanquan".equals(sousuo)) {	
					List<Slideway> slideway0 = partMg.findSdwaySelective(slideway);
					PageInfo<Slideway> page5 = new PageInfo<>(slideway0);
					int total = (int) page5.getTotal();
					result.setResultSize(total);
					result.setExtend(slideway0); 
					logger.info("查询线轨信息");
				}else {
					List<Slideway> slideway0 = partMg.findSdwayMHSelective(slideway);
					PageInfo<Slideway> page5 = new PageInfo<>(slideway0);
					int total = (int) page5.getTotal();
					result.setResultSize(total);
					result.setExtend(slideway0); 
					logger.info("查询线轨信息");
				}
			} catch (Exception e) {
					System.out.println(e.getMessage());
					result.setSuccess(false);
					logger.error("查询线轨出错:"+e.getMessage());   //输出日志
				}
					return result;
				}
				
	@RequestMapping("addSdway.do")
	@ResponseBody
	public Msg addSdwayMsg(Slideway record){
			Msg msg = new Msg();
			try {
					partMg.insertSdway(record);
					msg.setSuccess(true);
					msg.setMsg("添加信息成功！");
					logger.info("添加线轨信息，型号："+record.getXinghao());
			} catch (Exception e) {
					System.out.println(e.getMessage());  //异常信息的输出
					msg.setSuccess(false);
					msg.setMsg("添加信息失败，请稍后再试！");
					logger.error("添加线轨出错:"+e.getMessage());   //输出日志
				}
					return msg;
				}
				
	@RequestMapping("delSdway.do")
	@ResponseBody
	public Msg delSdwayMsg(Integer daoguiId){
			Msg msg = new Msg();
			try {
					partMg.deleteSdwayById(daoguiId);
					msg.setSuccess(true);
					msg.setMsg("删除信息成功！");
					logger.info("删除线轨信息，id:"+daoguiId);
			} catch (Exception e) {
					System.out.println(e.getMessage());
					msg.setSuccess(false);
					msg.setMsg("删除信息失败，请稍后再试！");
					logger.error("删除线轨出错:"+e.getMessage());   //输出日志
				}		
				return msg;
			}
				
	@RequestMapping("updateSdway.do")
	@ResponseBody
	public Msg updateSdwayMsg(Slideway record){
			Msg msg = new Msg();
			try {
					partMg.updateSdwayById(record);
					msg.setSuccess(true);
					msg.setMsg("编辑信息成功！");
					logger.info("编辑线轨信息，id："+record.getDaoguiId());
			} catch (Exception e) {
					System.out.println(e.getMessage());
					msg.setSuccess(false);
					msg.setMsg("编辑信息失败，请稍后再试！");
					logger.error("编辑线轨出错:"+e.getMessage());   //输出日志
				}
				return msg;
			}
//--------------upright.java-------------------------------------------
	@RequestMapping("queryUp.do")
	@ResponseBody
	public Msg queryUpMsg(String inputext,String selectextId,String sousuo,Integer start,Integer limit){
				Upright upright = new Upright();
				Msg result = new Msg();
				 if("jc".equals(selectextId)){
							upright.setCourse(inputext);}
				PageHelper.startPage((start/limit)+1, limit);  
											   
		try {
				if("wanquan".equals(sousuo)) {	
					List<Upright> upright0 = partMg.findUpSelective(upright);
					PageInfo<Upright> page5 = new PageInfo<>(upright0);
					int total = (int) page5.getTotal();
					result.setResultSize(total);
					result.setExtend(upright0); 
					logger.info("查询立柱信息");
		}else {
					List<Upright> upright0 = partMg.findUpMHSelective(upright);
					PageInfo<Upright> page5 = new PageInfo<>(upright0);
					int total = (int) page5.getTotal();
					result.setResultSize(total);
					result.setExtend(upright0); 
					logger.info("查询立柱信息");
				}
			} catch (Exception e) {
					System.out.println(e.getMessage());
					result.setSuccess(false);
					logger.error("查询立柱出错:"+e.getMessage());   //输出日志
				}
					return result;
				}
				
	@RequestMapping("addUp.do")
	@ResponseBody
	public Msg addUpMsg(Upright record){
			Msg msg = new Msg();
			try {
					record.setUpDaihao(record.getCourse()+"-upcolumn");
					record.setHengxiang(record.getHengxiang()+"环筋");
					record.setShuxiang(record.getShuxiang()+"环筋");
					partMg.insertUp(record);
					msg.setSuccess(true);
					msg.setMsg("添加信息成功！");
					logger.info("添加立柱信息，型号："+record.getUpDaihao());
			} catch (Exception e) {
					System.out.println(e.getMessage());  //异常信息的输出
					msg.setSuccess(false);
					msg.setMsg("添加信息失败，请稍后再试！");
					logger.error("添加立柱出错:"+e.getMessage());   //输出日志
				}
					return msg;
				}
				
	@RequestMapping("delUp.do")
	@ResponseBody
	public Msg delUpMsg(Integer uprightId){
			Msg msg = new Msg();
			try {
					partMg.deleteUpById(uprightId);
					msg.setSuccess(true);
					msg.setMsg("删除信息成功！");
					logger.info("删除立柱信息，id:"+uprightId);
			} catch (Exception e) {
					System.out.println(e.getMessage());
					msg.setSuccess(false);
					msg.setMsg("删除信息失败，请稍后再试！");
					logger.error("删除立柱出错:"+e.getMessage());   //输出日志
				}		
				return msg;
			}
				
	@RequestMapping("updateUp.do")
	@ResponseBody
	public Msg updateUpMsg(Upright record){
			Msg msg = new Msg();
			try {
					record.setUpDaihao(record.getCourse()+"-upcolumn");
					record.setHengxiang(record.getHengxiang()+"环筋");
					record.setShuxiang(record.getShuxiang()+"环筋");
					partMg.updateUpById(record);
					msg.setSuccess(true);
					msg.setMsg("编辑信息成功！");
					logger.info("编辑立柱信息，id:"+record.getUprightId());
			} catch (Exception e) {
					System.out.println(e.getMessage());
					msg.setSuccess(false);
					msg.setMsg("编辑信息失败，请稍后再试！");
					logger.error("更新立柱出错:"+e.getMessage());   //输出日志
				}
				return msg;
			}	
//--------------bed.java-------------------------------------------
		@RequestMapping("queryWb.do")
		@ResponseBody
		public Msg queryWbMsg(String inputext,String selectextId,String sousuo,Integer start,Integer limit){
					Workbed workbed = new Workbed();
					Msg result = new Msg();
				 if("jc".equals(selectextId)){
								workbed.setCourse(inputext);}
					PageHelper.startPage((start/limit)+1, limit); 
												   
			try {
					if("wanquan".equals(sousuo)) {	
						List<Workbed> workbed0 = partMg.findWbSelective(workbed);
						PageInfo<Workbed> page7 = new PageInfo<>(workbed0);
						int total = (int) page7.getTotal();
						result.setResultSize(total);
						result.setExtend(workbed0); 
						logger.info("查询床身信息");
		}else {
						List<Workbed> workbed0 = partMg.findWbMHSelective(workbed);
						PageInfo<Workbed> page7 = new PageInfo<>(workbed0);
						int total = (int) page7.getTotal();
						result.setResultSize(total);
						result.setExtend(workbed0); 
						logger.info("查询床身信息");
					} 
				} catch (Exception e) {
						System.out.println(e.getMessage());
						result.setSuccess(false);
						logger.error("查询床身出错:"+e.getMessage());   //输出日志
					}
						return result;
					}
					
		@RequestMapping("addWb.do")
		@ResponseBody
		public Msg addWbMsg(Workbed record){
				Msg msg = new Msg();
				try {
						record.setBeddaihao(record.getCourse()+"-body");
						record.setKuanheng(record.getKuanheng()+"环筋");
						record.setKuanshu(record.getKuanshu()+"环筋");
						record.setZhaiheng(record.getZhaiheng()+"环筋");
						record.setZhaishu(record.getZhaishu()+"环筋");
						partMg.insertWb(record);
						msg.setSuccess(true);
						msg.setMsg("添加信息成功！");
						logger.info("添加床身信息，型号："+record.getBeddaihao());
				} catch (Exception e) {
						System.out.println(e.getMessage());  //异常信息的输出
						msg.setSuccess(false);
						msg.setMsg("添加信息失败，请稍后再试！");
						logger.error("添加床身出错:"+e.getMessage());   //输出日志
					}
						return msg;
					}
					
		@RequestMapping("delWb.do")
		@ResponseBody
		public Msg delWbMsg(Integer bedid){
				Msg msg = new Msg();
				try {
						partMg.deleteWbById(bedid);
						msg.setSuccess(true);
						msg.setMsg("删除信息成功！");
						logger.info("删除床身信息，id："+bedid);
				} catch (Exception e) {
						System.out.println(e.getMessage());
						msg.setSuccess(false);
						msg.setMsg("删除信息失败，请稍后再试！");
						logger.error("删除床身出错:"+e.getMessage());   //输出日志
					}		
					return msg;
				}
					
		@RequestMapping("updateWb.do")
		@ResponseBody
		public Msg updateWbMsg(Workbed record){
				Msg msg = new Msg();
				try {
						record.setBeddaihao(record.getCourse()+"-body");
						record.setKuanheng(record.getKuanheng()+"环筋");
						record.setKuanshu(record.getKuanshu()+"环筋");
						record.setZhaishu(record.getZhaishu()+"环筋");
						record.setZhaishu(record.getZhaishu()+"环筋");
						partMg.updateWbById(record);
						msg.setSuccess(true);
						msg.setMsg("编辑信息成功！");
						logger.info("编辑床身信息，id："+record.getBeddaihao());
				} catch (Exception e) {
						System.out.println(e.getMessage());
						msg.setSuccess(false);
						msg.setMsg("编辑信息失败，请稍后再试！");
						logger.error("编辑床身出错:"+e.getMessage());   //输出日志
					}
					return msg;
				}	
		
		@RequestMapping("queryWpBox.do")
		@ResponseBody
		public Msg queryWpBoxMsg(Workpiece record){
			Msg msg = new Msg();
			List<Workpiece> wpBox = partMg.findSelectiveWp(record);
			msg.setExtend(wpBox);
			return msg;
		}
		
		@RequestMapping("querySdBox.do")
		@ResponseBody
		public Msg querySdBoxMsg(Spindle record){
			Msg msg = new Msg();
			List<Spindle> sdBox = partMg.findSelectiveSd(record);
			msg.setExtend(sdBox);
			return msg;
		}
		
		@RequestMapping("queryAtcBox.do")
		@ResponseBody
		public Msg queryAtcBoxMsg(Atc record){
			Msg msg = new Msg();
			List<Atc> atcBox = partMg.findSelectiveAtc(record);
			msg.setExtend(atcBox);
			return msg;
		}
		
		@RequestMapping("querySwBox.do")
		@ResponseBody
		public Msg querySwBoxMsg(Screw record){
			Msg msg = new Msg();
			List<Screw> swBox = partMg.findSwSelective(record);
			msg.setExtend(swBox);
			return msg;
		}
		
		@RequestMapping("querySdWayBox.do")
		@ResponseBody
		public Msg querySdWayBoxMsg(Slideway record){
			Msg msg = new Msg();
			List<Slideway> wayBox = partMg.findSdwaySelective(record);
			msg.setExtend(wayBox);
			return msg;
		}
		
		@RequestMapping("queryUpBox.do")
		@ResponseBody
		public Msg queryUpBoxMsg(Upright record){
			Msg msg = new Msg();
			List<Upright> upBox = partMg.findUpSelective(record);
			msg.setExtend(upBox);
			return msg;
		}
		
		@RequestMapping("queryBdBox.do")
		@ResponseBody
		public Msg queryBdBoxMsg(Workbed record){
			Msg msg = new Msg();
			List<Workbed> wbBox = partMg.findWbSelective(record);
			msg.setExtend(wbBox);
			return msg;
		}
		
		@RequestMapping("queryWh.do")
		@ResponseBody
		public Msg queryWhMsg(String inputext,String selectextId,String sousuo,Integer start,Integer limit){
			Whole whole = new Whole();
			Msg result =new Msg();
			if("js".equals(selectextId)){
						whole.setMtType(selectextId);}		
			PageHelper.startPage((start/limit)+1, limit);   
			try {
					if("wanquan".equals(sousuo)) {	
						List<Whole> whole0 = partMg.findWhSelective(whole);
						PageInfo<Whole> page8 = new PageInfo<>(whole0);
						int total = (int) page8.getTotal();
						result.setResultSize(total);
						result.setExtend(whole0);
						logger.info("查询实例信息");
		}else {
						List<Whole> whole1 = partMg.findWhMHSelective(whole);
						PageInfo<Whole> page8 = new PageInfo<>(whole1);
						int total = (int) page8.getTotal();
						result.setResultSize(total);
						result.setExtend(whole1); 
						logger.info("查询实例信息");
					} 
				} catch (Exception e) {
						System.out.println(e.getMessage());
						result.setSuccess(false);
						logger.error("查询整机出错:"+e.getMessage());   //输出日志
					}
						return result;
					}
		
		@RequestMapping("addWh.do")
		@ResponseBody
		public Msg addWhMsg(Whole record){
				Msg msg = new Msg();
				try {
						partMg.insertWh(record);
						msg.setSuccess(true);
						msg.setMsg("添加信息成功！");
						logger.info("添加实例信息，型号："+record.getMtType());
				} catch (Exception e) {
						System.out.println(e.getMessage());  //异常信息的输出
						msg.setSuccess(false);
						msg.setMsg("添加信息失败，请稍后再试！");
						logger.error("添加整机出错:"+e.getMessage());   //输出日志
					}
						return msg;
					}
					
		@RequestMapping("delWh.do")
		@ResponseBody
		public Msg delWhMsg(Integer mtId){
				Msg msg = new Msg();
				try {
						partMg.deleteWhById(mtId);
						msg.setSuccess(true);
						msg.setMsg("删除信息成功！");
						logger.info("查询实例信息,id："+mtId);
				} catch (Exception e) {
						System.out.println(e.getMessage());
						msg.setSuccess(false);
						msg.setMsg("删除信息失败，请稍后再试！");
						logger.error("删除整机出错:"+e.getMessage());   //输出日志
					}		
					return msg;
				}
					
		@RequestMapping("updateWh.do")
		@ResponseBody
		public Msg updateWhMsg(Whole record){
				Msg msg = new Msg();
				try {
						partMg.updateWhById(record);
						msg.setSuccess(true);
						msg.setMsg("编辑信息成功！");
						logger.info("编辑实例信息，id:"+record.getMtType());
				} catch (Exception e) {
						System.out.println(e.getMessage());
						msg.setSuccess(false);
						msg.setMsg("编辑信息失败，请稍后再试！");
						logger.error("删除整机出错:"+e.getMessage());   //输出日志
					}
					return msg;
				}
		
		@RequestMapping("addCase.do")
		@ResponseBody
		public Msg addCaseMsg(Whole record){
			System.out.println(record.getMtScrew());
			Msg msg = new Msg();
			Whole whole = new Whole();
			whole.setMtType(record.getMtType());
			List<Whole> whSelective = partMg.findWhSelective(whole);
			if(whSelective.size()>0) {
				msg.setSuccess(false);
				msg.setMsg("实例名已存在，请重新输入");	
			}else {
				try {
					partMg.insertWh(record);
					msg.setSuccess(true);
					msg.setMsg("添加信息成功！");
					logger.info("添加推理实例信息，型号："+record.getMtType());
				} catch (Exception e) {
					msg.setSuccess(false);
					msg.setMsg("添加信息失败，请稍后再试！");
					logger.error("添加结果新实例出错:"+e.getMessage());   //输出日志
					System.out.println(e.getMessage());
				} 
		}	
			return msg;		
		}
}

