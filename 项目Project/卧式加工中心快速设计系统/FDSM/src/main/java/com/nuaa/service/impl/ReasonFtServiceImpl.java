package com.nuaa.service.impl;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.List;

import javax.sound.midi.Soundbank;

import org.springframework.stereotype.Service;
import com.nuaa.bean.Atc;
import com.nuaa.bean.BdWeight;
import com.nuaa.bean.Screw;
import com.nuaa.bean.Slideway;
import com.nuaa.bean.Spindle;
import com.nuaa.bean.UpWeight;
import com.nuaa.bean.Upright;
import com.nuaa.bean.Workbed;
import com.nuaa.bean.Workpiece;
import com.nuaa.dao.AtcDao;
import com.nuaa.dao.ScrewDao;
import com.nuaa.dao.SlidewayDao;
import com.nuaa.dao.SpindleDao;
import com.nuaa.dao.UprightDao;
import com.nuaa.dao.WorkbedDao;
import com.nuaa.dao.WorkpieceDao;
import com.nuaa.service.IReasonFtService;



@Service
public class ReasonFtServiceImpl implements IReasonFtService{
	private final WorkpieceDao wpReason;
	private final SpindleDao sdReason;
	private final AtcDao atcReason;
	private final UprightDao upReason;
	private final ScrewDao swReason;
	private final SlidewayDao swayReason;
	private final WorkbedDao wbReason;
		

	public ReasonFtServiceImpl(WorkpieceDao wpReason, SpindleDao sdReason, AtcDao atcReason, UprightDao upReason,
					ScrewDao swReason,SlidewayDao swayReason,WorkbedDao wbReason) {
		this.wpReason = wpReason;
		this.sdReason = sdReason;
		this.atcReason = atcReason;
		this.upReason = upReason;
		this.swReason = swReason;
		this.swayReason = swayReason;
		this.wbReason = wbReason;

	}

		double[] pb = new double[2];
		
	@Override
	public List<Workpiece> findReasonWp(Workpiece record,double[][] wp) {
		
		//List<Workpiece> wpList = wpReason.findSelectiveWp(record);
		List<Workpiece> wpList = wpReason.findReasonWp(record);
		int num = wpList.size();
		double[][] nature=new double[num][3];//行/列   5个数据（查询）；10个变量
		double[][] mid=new double[num][3];   // d  kg  h
		
		for(int i=0;i<num;i++) {
			nature[i][0]= 1/(1+0.001*Math.abs(wpList.get(i).getGongjianMaxd()-wp[0][1]));
			nature[i][1]= 1/(1+0.001*Math.abs(wpList.get(i).getZaihe()-wp[1][1]));
			nature[i][2]= 1/(1+0.001*Math.abs(wpList.get(i).getGongjianH()-wp[2][1]));
			
		}
		for(int i=0;i<num;i++) {
			mid[i][0]=0;
			for(int j=0;j<3;j++) {
				mid[i][0]+=nature[i][j]*wp[j][0];
			}
			wpList.get(i).setSimilarity(mid[i][0]);
			NumberFormat simi = NumberFormat.getPercentInstance();  // 百分比显示similarity
			simi.setMinimumFractionDigits(2); 
			String format = simi.format(mid[i][0]);
			wpList.get(i).setShowSimi(format);
		}
		
		Comparator<Workpiece> bySimiDesc = Comparator.comparing(Workpiece::getSimilarity).reversed();
		wpList.sort(bySimiDesc);
		
		List<Workpiece> wList = wpList.subList(0, 4);
		
		return wList;
	}

	@Override
	public List<Spindle> findReasonSd(String material,String jgType,Integer wpMaxd) {
		Spindle record = new Spindle();
		if(!"HE".equals(jgType)) {
				// Al
				if("3".equals(material)) if (wpMaxd < 630) {
					record.setKinds("机械主轴");
					record.setPowerMin(11);
					record.setPowerMax(15);
					record.setMaxSpeed(6000);
				} else if (wpMaxd < 800) {
					record.setPowerMin(18.5);
					record.setPowerMax(22);

				} else {
					record.setPowerMin(22);
					record.setPowerMax(26);
				}
				else if("1".equals(material) || "2".equals(material)){    //Ht200  Ht250
							if(wpMaxd <800) {
									record.setPowerMin(18.5);
									record.setPowerMax(22);
							}else {
								record.setPowerMin(22);
								record.setPowerMax(26);	
							}
				}else if ("4".equals(material)) {   //45钢
						record.setPowerMin(22);
						record.setPowerMax(26);
						record.setMaxSpeed(4500);
				}
	//----------------------------------------------------
			}else {
					record.setPowerMin(22);
					record.setPowerMax(26);
					record.setMaxSpeed(6000);	
			}
		
		return sdReason.findReasonSd(record);
	}

	@Override
	public List<Atc> findReasonAtc(String jggy,String toolType,Integer wpMaxd) {
		Atc atc = new Atc();
		if("1".equals(jggy)) {
			atc.setCapacity(24);
			}else if("2".equals(jggy)){
				atc.setCapacity(40);
				}else {
				atc.setCapacity(60);
		}
		
		if(wpMaxd>1100) {
			atc.setXingshi("液压刀库");
		}
		
		if(toolType.substring(0,3).equalsIgnoreCase("HSK")) {
			atc.setDaobing("HSK");
		}else {
			atc.setDaobing("MAS403");
		}
			
		return atcReason.findReasonAtc(atc);
	}

	@Override
	public int getCountUp() {
		
		return upReason.getCountUp();
		}
	
	@Override
	public List<Upright> findReasonUp(Upright record,double[] param,List upById) {
		
		List<Upright> upList = upReason.findUpSelective(record);
		int num = upList.size();
		double[][] nature=new double[num][10];//行/列   5个数据（查询）；10个变量
		double[][] mid=new double[num][10];
		double[] weight = new double[10];
		
		List<UpWeight> upBy = upById;
		
				weight[0] = upBy.get(0).getHeight();    //H
				weight[1] = upBy.get(0).getKuang();    //K
				weight[2] = upBy.get(0).getYroute();	//yRoute
				weight[3] = upBy.get(0).getSidethick();   //壁厚
				weight[4] = upBy.get(0).getHole();	//hole
				weight[5] = upBy.get(0).getRibthick();	//ribthick
				weight[6] = upBy.get(0).getSdwidth();	//s_x
				weight[7] = upBy.get(0).getSdheight();	//s_y
				weight[8] = upBy.get(0).getHnumber();   //heng
				weight[9] = upBy.get(0).getSnumber();	//shu
		
		//		System.out.println(weight[0]);
				
		for(int i=0;i<num;i++) {
			
				double h = param[0]+param[5]+200+206+206;  //yt+s_h
				System.out.println("高度"+h);
			nature[i][0]= 1/(1+0.001*Math.abs(upList.get(i).getHeight()-h));   //H
				double k = param[4] +150;   //s_w + 200
				System.out.println("宽度"+k);
			nature[i][1]= 1/(1+0.001*Math.abs(upList.get(i).getKuangdu()-k));  //K
				double y_route = upList.get(i).getyRoute();
				System.out.println("Y行程"+y_route);
			nature[i][2]= 1/(1+0.001*Math.abs(y_route - param[0]));   //yRoute
			nature[i][3]= 1/(1+0.001*Math.abs(upList.get(i).getSidingthick()-param[1])); // 壁厚
			System.out.println("壁厚"+param[1]);
			nature[i][4]= 1/(1+0.001*Math.abs(upList.get(i).getCehole()-param[2]));   // 孔径
			System.out.println("孔径"+param[2]);
			nature[i][5]= 1/(1+0.001*Math.abs(upList.get(i).getRibthinck()-param[3]));    // 板筋厚度
				double s_x = upList.get(i).getKuangdu() - 200;
			nature[i][6]= 1/(1+0.001*Math.abs(s_x-param[4]));    		// s_x
				double s_y = upList.get(i).getHeight()-200-206-206-y_route;
			nature[i][7]= 1/(1+0.001*Math.abs(s_y-param[5]));         //s_y
				double a = Math.round(upList.get(i).getShangdi() / 230);   // heng
				double b = Math.round(param[5] / 230);  				//shangdi = s_h
				System.out.println("heng"+a+" "+"上底"+b);
			nature[i][8]= 1/(1+0.001*Math.abs(a-b));
				double c = Math.round(upList.get(i).getHeight() / 412);   // shu
				System.out.println("shu"+c);
				double d = Math.round(h / 412);
			nature[i][9]= 1/(1+0.001*Math.abs(c-d));
		}
		
			for(int i=0;i<num;i++) {
				mid[i][0]=0;
				for(int j=0;j<10;j++) {
					mid[i][0]+=nature[i][j]*weight[j];
				}
					if(mid[i][0] >= 0.9) {
						if(mid[i][0] >=0.95) {
							mid[i][0] = mid[i][0]-0.08;
						}else {
							mid[i][0] = mid[i][0]-0.03;
						}
				}
				upList.get(i).setSimilarity(mid[i][0]); //赋值给lsit中的similarity
				
				NumberFormat simi = NumberFormat.getPercentInstance();  // 百分比显示similarity
				simi.setMinimumFractionDigits(2); 
				String format = simi.format(mid[i][0]);
				upList.get(i).setShowSimi(format);
			}
			
			//降序排序
			Comparator<Upright> bySimiDesc = Comparator.comparing(Upright::getSimilarity).reversed();
			upList.sort(bySimiDesc);
		return upList;
	}

	@Override
	public List<Screw> findReasonSw(Integer wpMaxd,String jgTyp2,double[][] sw) {
		Screw screw = new Screw();
			double sp;
		if(sw[0][1] <50) {
		//	 sp = sw[0][1] /2;
			 if(wpMaxd<1000) {
				 screw.setGcd(40.0);
			 }else {
				screw.setGcd(45.0);
			}
		}else {		 
		//	 sp = kSpeed/3;
			 if(wpMaxd<1000) {
				 screw.setGcd(45.0);
			 }else {
				screw.setGcd(50.0);
			}
		}  

		List<Screw> swList = swReason.findSwSelective(screw);
		int num = swList.size();
		double[][] nature=new double[num][4];//行/列   5个数据（查询）；10个变量
		double[][] mid=new double[num][4];   // speed  dzh jd dc
//		double sped;
		double sw_dc;
		for(int i=0;i<num;i++) {
				if(sw[0][1] <50) {
			//		 sped = 2*swList.get(i).getDaochen();
					 sw_dc = sw[0][1]/1.5;
					
				}else {
				//	 sped = 3.2*swList.get(i).getDaochen();

					 sw_dc = sw[0][1]/3;
				}
		 
			nature[i][0]= 1- Math.abs(swList.get(i).getSpeed() - sw[0][1])/Math.max(swList.get(i).getSpeed(),sw[0][1]);
//			System.out.println(nature[i][0]);
			nature[i][1]= 1-Math.abs(swList.get(i).getEddn()-sw[1][1])/Math.max(swList.get(i).getEddn(),sw[1][1]);
//			System.out.println(nature[i][1]);
			nature[i][2]= 1/(1+0.01*Math.abs(Double.parseDouble(
										swList.get(i).getJdlevel().substring(1))-sw[2][1]));
//			System.out.println(swList.get(i).getDaochen());
			
			nature[i][3]= 1 - Math.abs(sw_dc - swList.get(i).getDaochen())/Math.max(sw_dc ,swList.get(i).getDaochen());
		
		}
		
		for(int i=0;i<num;i++) {
			mid[i][0]=0;
			for(int j=0;j<4;j++) {
				mid[i][0]+=nature[i][j]*sw[j][0];
			}
			swList.get(i).setSimilarity(mid[i][0]);
	//		System.out.println(mid[i][0]);
			NumberFormat simi = NumberFormat.getPercentInstance();  // 百分比显示similarity
			simi.setMinimumFractionDigits(2); 
			String format = simi.format(mid[i][0]);
			swList.get(i).setShowSimi(format);
		}
		
		Comparator<Screw> bySimiDesc = Comparator.comparing(Screw::getSimilarity).reversed();
		swList.sort(bySimiDesc);
		
		List<Screw> wList = swList.subList(0,5);
		
		return wList;
	}

	@Override
	public List<Slideway> findReasonSdWay(Integer xt, Integer yt, Integer zt, Integer upWidth, Integer sdH,
			 Integer wpMaxD,double[][] way) {

		Slideway sway = new Slideway();
			// 计算 导轨的长度
		int xLength = xt + upWidth +50;  // 后加的50
		int yLength = yt + sdH + 200;
		int zLength = zt + wpMaxD + 150;
		
		System.out.println("导轨xl:"+ xLength);
		System.out.println("yl:" + yLength);
		System.out.println("zl:"+ zLength);
		pb[0] = xLength;
		pb[1] = zLength;
		sway.setWidth((int)way[0][1]);
		List<Slideway> listX = swayReason.findSdwaySelective(sway);
		int num = listX.size();
		double[][] nature=new double[num][4];//行/列   5个数据（查询）；10个变量
		double[][] mid=new double[num][4];   // swGcd  jd dwjd w_edzh
		
	for(int i=0;i<num;i++) {
		nature[i][0]= 1- Math.abs(listX.get(i).getWidth() - way[0][1])/Math.max(listX.get(i).getWidth(),way[0][1]);
		nature[i][1]= 1/(1+0.1*Math.abs(Double.parseDouble(
						listX.get(i).getJdlevel().substring(1))-way[1][1]));
		nature[i][2]= 1- Math.abs(listX.get(i).getDwjd() - way[2][1])/Math.max(listX.get(i).getDwjd(),way[2][1]);
		nature[i][3]= 1- Math.abs(listX.get(i).getEddn() - way[3][1])/Math.max(listX.get(i).getEddn(),way[3][1]);
	
	}
	
	for(int i=0;i<num;i++) {
			mid[i][0]=0;
			for(int j=0;j<4;j++) {
				mid[i][0]+=nature[i][j]*way[j][0];
			}
			listX.get(i).setSimilarity(mid[i][0]);
			NumberFormat simi = NumberFormat.getPercentInstance();  // 百分比显示similarity
			simi.setMinimumFractionDigits(2); 
			String format = simi.format(mid[i][0]);
			listX.get(i).setShowSimi(format);
		//	System.out.println(mid[i][0]);
			listX.get(i).setxL(pb[0]);
			listX.get(i).setzL(pb[1]);		
		}

		Comparator<Slideway> bySimiDesc = Comparator.comparing(Slideway::getSimilarity).reversed();
		listX.sort(bySimiDesc);
		
		return listX;
	}

	@Override
	public int getCountWb() {
		
		return wbReason.getCountWb();
	}

	@Override
	public List<Workbed> findReasonWbed(Workbed record, double[] bparam,String structure,List bd) {
		List<Workbed> bdList = wbReason.findWbSelective(record);
		int num2 = bdList.size();
		double[][] natureb=new double[num2][12];//行/列   5个数据（查询）；10个变量
		double[][] bmid=new double[num2][12];
		double[] weightb = new double[11];
		
		List<BdWeight> bdBy = bd;
				weightb[0] = bdBy.get(0).getbLength();  //L
				weightb[1] = bdBy.get(0).getbWidth();  //w
				weightb[2] = bdBy.get(0).getbHeight();  //H
				weightb[3] = bdBy.get(0).getbCd();  //C_d
				weightb[4] = bdBy.get(0).getbXl();  //X_L
				weightb[5] = bdBy.get(0).getbXd();  //X_distance
				weightb[6] = bdBy.get(0).getbZl();  //Z_L
				weightb[7] = bdBy.get(0).getbZd();  //Z_distance
				weightb[8] = bdBy.get(0).getbRibthick();  //ribThick
				weightb[9] = bdBy.get(0).getbHole();  //jhole
				weightb[10] = bdBy.get(0).getbStructure();  //structure
		
		for(int i=0;i<num2;i++) {//  0    1安装H     2     3     4        5        6     7     
				// bparam  8个参数  x_L  wp_h-input  z_L    wp_h  upXiadi  wp_D   hole  ribthick
					double L = bparam[4]+ pb[1] + 135 + 355;  //upXiadi + z_L 
					System.out.println("L:"+L);
				natureb[i][0]= 1/(1+0.001*Math.abs(bdList.get(i).getLength()-L));   //L
				
					double W = pb[0] +120;   //x_L + 120
				natureb[i][1]= 1/(1+0.001*Math.abs(bdList.get(i).getWidth()-W));  //W
				System.out.println("K:"+W);
							//  610
					double H = bparam[1] + bparam[3]*1.85 -120;
					System.out.println("H:"+H);
				natureb[i][2]= 1/(1+0.001*Math.abs(bdList.get(i).getHeight() - H));   // H
				
					double Caokuan = bparam[5] + 100;    // wp_d
					System.out.println("CaoKuan:"+Caokuan);
				natureb[i][3]= 1/(1+0.001*Math.abs(bdList.get(i).getCaokuan()-Caokuan)); // caoKuan
				natureb[i][4]= 0.75;
				//	natureb[i][4]= 1/(1+0.001*Math.abs(pb[0]-bparam[0]));   // 计算 - 选中的 X_L
			//	natureb[i][4]= 1/(1+0.001*Math.abs(1850-bparam[0])); 
				natureb[i][5]= 1/(1+0.001*Math.abs(bparam[4]-160-bdList.get(i).getxDistance()));    // X_D
				
				natureb[i][6]= 0.75;  	
				//natureb[i][6]= 1/(1+0.001*Math.abs(pb[1]-bparam[2]));    		// Z_L 计算 - 选中
					double zD = bparam[5] + 220;
				natureb[i][7]= 1/(1+0.001*Math.abs(zD-bdList.get(i).getzDistance()));    //Z_D
					
				
		//		natureb[i][8]= 1/(1+0.001*Math.abs(bparam[1]-bparam[3])); // wprkpiece_h
					
				natureb[i][8]= 1/(1+0.001*Math.abs(bparam[7]-bdList.get(i).getRibthick()));  // ribThick
				System.out.println("Rt:"+bparam[7]);
				natureb[i][9]= 1/(1+0.001*Math.abs(bparam[6]-bdList.get(i).getHoled()));   // hole
				System.out.println("Hd:"+bparam[6]);
					if(bdList.get(i).getStructure().equals(structure)) {
						natureb[i][10] = 1;
					} else {
						natureb[i][10] = 0.5;	
					}
		
			}	
		
		for(int i=0;i<num2;i++) {
				bmid[i][0]=0;
				for(int j=0;j<11;j++) {
					bmid[i][0]+=natureb[i][j]*weightb[j];
				}
					
					if(bmid[i][0] >= 0.9) {
						if(bmid[i][0] >=0.95) {
							bmid[i][0] = bmid[i][0]-0.06;
						}else {
							bmid[i][0] = bmid[i][0]-0.045;
						}
				}
				bdList.get(i).setSimilarity(bmid[i][0]); //赋值给lsit中的similarity
				
				NumberFormat bsimi = NumberFormat.getPercentInstance();  // 百分比显示similarity
				bsimi.setMinimumFractionDigits(2); 
				String bdFormat = bsimi.format(bmid[i][0]);
				bdList.get(i).setShowSimi(bdFormat);
			}
		
		//降序排序
		Comparator<Workbed> bySimiDesc = Comparator.comparing(Workbed::getSimilarity).reversed();
		bdList.sort(bySimiDesc);
		
		return bdList;
	}

}
