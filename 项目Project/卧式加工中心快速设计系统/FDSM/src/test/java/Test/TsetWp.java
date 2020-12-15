package Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.nuaa.bean.Atc;
import com.nuaa.bean.BdWeight;
import com.nuaa.bean.Screw;
import com.nuaa.bean.Slideway;
import com.nuaa.bean.Spindle;
import com.nuaa.bean.UpWeight;
import com.nuaa.bean.Upright;
import com.nuaa.bean.UserInfo;
import com.nuaa.bean.Whole;
import com.nuaa.bean.Workbed;
import com.nuaa.bean.Workpiece;
import com.nuaa.bean.bedDfResult;
import com.nuaa.bean.bedwgtDf;
import com.nuaa.bean.upwgtDf;
import com.nuaa.service.IPartsMgService;
import com.nuaa.service.IUserService;
import com.nuaa.service.IWeightMgService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TsetWp {
	@Autowired
	private IPartsMgService partsMgService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IWeightMgService wgService;
	
	@Test
	public void test1() {
			System.out.println(partsMgService);
			Workpiece workpiece = new Workpiece();
			workpiece.setWorkid(1);
			workpiece.setCourse("sad");
			workpiece.setFendu("asd");
			partsMgService.updateWpByIdSelective(workpiece);
	}
	
	@Test
	public void testSd() {
			System.out.println(partsMgService);
			Spindle spindle = new Spindle();
			spindle.setSindleId(3);
			spindle.setKinds("jixie");
			spindle.setPower("22/26");
			partsMgService.updateSdByIdSelective(spindle);
	}
	
	@Test
	public void testAtc() {
			String a = "admin";
			System.out.println(partsMgService);
			Atc atc = new Atc();
			atc.setToolid(2);
			atc.setCapacity(30);
			atc.setMaxd("200");
			partsMgService.updateAtcByIdSelective(atc);
	}
	
	@Test
	public void testSw() {
			Screw screw = new Screw();
			System.out.println(partsMgService);
			screw.setGcd(35.0);
			List<Screw> selective = partsMgService.findSwSelective(screw);
			for (Screw screw2 : selective) {
				System.out.println(screw2.getSwid());
			}
	}
	@Test
	public void testSdWay() {
			Slideway slideway = new Slideway();
			System.out.println(partsMgService);
			slideway.setJdlevel("G1");
			slideway.setLength(3500);
			slideway.setDaoguiId(1);
			partsMgService.updateSdwayByIdSelective(slideway);
	}
	@Test
	public void testUp() {
			Upright upright  =new Upright();
			System.out.println(partsMgService);
			List<Upright> list = partsMgService.findUpSelective(upright);
			for (Upright upright2 : list) {
				System.out.println(upright2.getUprightId());
			}
	}
	@Test
	public void testwb() {
			Workbed workbed = new Workbed();
			System.out.println(partsMgService);
			workbed.setLength(700);
			workbed.setBedid(1);
			partsMgService.updateWbByIdSelective(workbed);
		
	}
	@Test
	public void testwh() {
			System.out.println(partsMgService);
			Whole whole = new Whole();
			whole.setMtScrew("NSK/EM5020-6E");
			whole.setMtId(2);
			partsMgService.updateWhByIdSelective(whole);
		
	}
	
	@Test
	public void testUser() {
			System.out.println(userService);
			UserInfo record = new UserInfo();
			List<UserInfo> list = userService.findUserSelective(record);
			for (UserInfo userInfo : list) {
				System.out.println(userInfo.getUid());
			}
		
	}
	@Test
	public void testUp2() {
		
		/**double a = 1/(1+0.001*Math.abs(2042-2112));
		double s_x = 950 - 200;
		double s_y = 2060-200-206-206-850;
		double b = Math.round(575 / 230);
		double c = Math.round(2060 / 412);
		System.out.println(a+"--"+s_x+"--"+s_y+"--"+b+"--"+c); */
		
		System.out.println(partsMgService);
		Upright up = new Upright();
		List<Upright> upList = partsMgService.findUpSelective(up);
		for (int i = 0; i < upList.size(); i++) {
				upList.get(i).setSimilarity(i+0.5);
				System.out.println(upList.get(i).getSimilarity());
				System.out.println("");
			}
		
			//排序  降序
		Comparator<Upright> bySimi = Comparator.comparing(Upright::getSimilarity).reversed();
		
		upList.sort(bySimi);
		
		for (int i = 0; i < upList.size(); i++) {
			System.out.println(upList.get(i).getSimilarity());
			System.out.println("");
		}
		
	/*	double[][] nature=new double[1][10];//行/列   5个数据（查询）；10个变量
		double[][] mid=new double[1][10];
		double[] weight = new double[10];
				weight[0] = 0.29;
				weight[1] = 0.24;
				weight[2] = 0.15;
				weight[3] = 0.06;
				weight[4] = 0.03;
				weight[5] = 0.03;
				weight[6] = 0.09;
				weight[7] = 0.08;
				weight[8] = 0.02;
				weight[9] = 0.02;
		
		for(int i=0;i<1;i++) {
			nature[i][0]= 1/(1+0.001*Math.abs(upList.get(i).getHeight()-2137));   //H
			nature[i][1]= 1/(1+0.001*Math.abs(upList.get(i).getKuangdu()-975));  //K
			double y_route = upList.get(i).getyRoute();
			nature[i][2]= 1/(1+0.001*Math.abs(y_route - 900));   //yRoute
			nature[i][3]= 1/(1+0.001*Math.abs(upList.get(i).getSidingthick()-210)); // 壁厚
			nature[i][4]= 1/(1+0.001*Math.abs(upList.get(i).getCehole()-135));   // 孔径
			nature[i][5]= 1/(1+0.001*Math.abs(upList.get(i).getRibthinck()-25));    // 板筋厚度
			double s_x = upList.get(i).getKuangdu() - 200;
			nature[i][6]= 1/(1+0.001*Math.abs(s_x-775));    // s_x
			double s_y = upList.get(i).getHeight()-200-206-206-y_route;
			nature[i][7]= 1/(1+0.001*Math.abs(s_y-625));         //s_y
			double a = Math.round(upList.get(i).getShangdi() / 230);   // heng
			double b = Math.round(570 / 230);
			nature[i][8]= 1/(1+0.001*Math.abs(a-b));
			double c = Math.round(upList.get(i).getHeight() / 412);   // shu
			double d = Math.round(2112 / 412);
			nature[i][9]= 1/(1+0.001*Math.abs(c-d));
		}
		
			for(int i=0;i<1;i++) {
				mid[i][0]=0;
				for(int j=0;j<10;j++) {
					mid[i][0]+=nature[i][j]*weight[j];
				}
				System.out.println(mid[0][0]);
			}*/
	}
	@Test
	public void testbed() {
			System.out.println(partsMgService);
			Workbed record = new Workbed();
			record.setBedid(1);
			List<Workbed> bdList = partsMgService.findWbSelective(record);
	
			double[][] natureb=new double[1][12];//行/列   5个数据（查询）；10个变量
			double[][] bmid=new double[1][12];
			double[] weightb = new double[12];
					weightb[0] = 0.20;  //L
					weightb[1] = 0.16;  //w
					weightb[2] = 0.13;  //H
					weightb[3] = 0.09;  //C_d
					weightb[4] = 0.08;  //X_L
					weightb[5] = 0.08;  //X_distance
					weightb[6] = 0.08;  //Z_L
					weightb[7] = 0.07;  //Z_distance
					weightb[8] = 0.04;  //wp_h
					weightb[9] = 0.02;  //ribThick
					weightb[10] = 0.02;  //jhole
					weightb[11] = 0.03;  //structure
			
			for(int i=0;i<1;i++) {//  0    1           2     3     4   5        6     7     8
					// bparam  6个参数  x_L  wp_h-input  z_L  z_d  wp_h  upXiadi  wp_D   hole  ribthick
					//	double L = bparam[5]+bparam[2]+ 135 + 355;  //upXiadi + z_L 
					natureb[i][0]= 1-(Math.abs(bdList.get(i).getLength()-3900)/Math.max(bdList.get(i).getLength(), 3900));   //L
					
						//double W = bparam[0] +120;   //x_L + 120
					natureb[i][1]= 1-(Math.abs(bdList.get(i).getWidth()-2300)/Math.max(bdList.get(i).getWidth(), 2300));  //W
					
					//	double H = bparam[1] + 206 + 200 + 370;
					natureb[i][2]= 1-(Math.abs(bdList.get(i).getHeight() - 1300)/Math.max(bdList.get(i).getHeight(),1300));   // H
					
					//	double Caokuan = bparam[6] + 55;    // wp_d
					natureb[i][3]= 1-(Math.abs(bdList.get(i).getCaokuan()-725)/Math.max(bdList.get(i).getCaokuan(), 725)); // caoKuan
					natureb[i][4]= 1-(Math.abs(2200-2000)/2200);   // 计算 - 选中的 X_L
					
					natureb[i][5]= 1-(Math.abs(750-bdList.get(i).getxDistance())/Math.max(bdList.get(i).getxDistance(), 750));    // X_D
						
					natureb[i][6]= 1-(Math.abs(1810-1850)/1850);    		// Z_L 计算 - 选中
					//	double zD = bparam[6] + 55;
					natureb[i][7]= 1-(Math.abs(940-bdList.get(i).getzDistance())/Math.max(bdList.get(i).getzDistance(), 940));    //Z_D
						
					
					natureb[i][8]= 1-(Math.abs(350-330)/350); // wprkpiece_h
						
					natureb[i][9]= 1-(Math.abs(25-bdList.get(i).getRibthick())/Math.max(bdList.get(i).getRibthick(), 25));  // ribThick
					
					natureb[i][10]= 1-(Math.abs(120-bdList.get(i).getHoled())/Math.max(bdList.get(i).getHoled(), 120));   // hole
					
						if("T型大跨距".equals(bdList.get(i).getStructure())) {
							natureb[i][11] = 1;
						} else {
							natureb[i][11] = 0.4;	
						}
			}	
			
			for(int i=0;i<1;i++) {
					bmid[i][0]=0;
					for(int j=0;j<12;j++) {
						bmid[i][0]+=natureb[i][j]*weightb[j];
					}
						
					System.out.println(bmid[0][0]);
					
				}
		
	}
	
	@Test
	public void testWg() {
			System.out.println(wgService);
			UpWeight weight = new UpWeight();
			weight.setWid(1);
			List<UpWeight> selectById = wgService.selectUpById(weight);
			System.out.println(selectById.get(0).getWid());
		
	}
	@Test
	public void testBd() {
			System.out.println(wgService);
			
			BdWeight bdWeight = new BdWeight();
			bdWeight.setBid(1);
			List<BdWeight> selectById = wgService.selectBd(bdWeight);
			System.out.println(selectById.get(0).getBid());
		
	}
	
	@Test
	public void testwgt() {
		String uid = "1-2";
		String[] upId = uid.split("-");
		List<upwgtDf> wgtList = new ArrayList<upwgtDf>();
		upwgtDf upwgtDf = new upwgtDf();
		for(int i=0;i<upId.length;i++) {
			Integer d = (Integer.parseInt(upId[i])-1)*10+1;   //
			upwgtDf.setDfid(d);
			
			List<upwgtDf> list = wgService.selectAll(upwgtDf);
			
			wgtList.addAll(list);
		}
			
		Integer num = upId.length;
		
			upwgtDf zjdf_mid=new upwgtDf();
			
			double[][][] score=new double[10][10][num];	//列/行/面
				for(int i=0;i<num;i++) {
					for(int j=0;j<10;j++) {
						zjdf_mid = wgtList.get(i*10+j);
						score[0][j][i]=zjdf_mid.getHeight();
						score[1][j][i]=zjdf_mid.getKuang();
						score[2][j][i]=zjdf_mid.getYroute();
						score[3][j][i]=zjdf_mid.getSdwidth();
						score[4][j][i]=zjdf_mid.getSdheight();
						score[5][j][i]=zjdf_mid.getSidethick();
						score[6][j][i]=zjdf_mid.getHole();
						score[7][j][i]=zjdf_mid.getRibthick();
						score[8][j][i]=zjdf_mid.getHnumber();
						score[9][j][i]=zjdf_mid.getSnumber();
					}
				}
				
				for(int j=0;j<10;j++) {           // 计算                                                                //计算
					for(int k=9;k>j-1;k--) {
						for(int i=1;i<num;i++) {
							score[k][j][0]*=score[k][j][i];
						}
						score[k][j][0]=Math.pow(score[k][j][0], 1.0/num);
				//		System.out.println("k:"+k + "--"+ score[k][j][0] );
					}
				}   /// success
				
				for(int j=1;j<10;j++){
					for(int k=0;k<j;k++) {
						score[k][j][0]=1/score[j][k][0];
					//	System.out.println(score[k][j][0]);
					}
				}
				
				double[] sa=new double[10];
				for(int k=0;k<10;k++) {
					for(int j=0;j<10;j++) {
						sa[k]+=score[k][j][0];		
					}
					
					System.out.println(sa[k]);
				}
			//	System.out.println("----------");
				double[] zjdfqz=new double[10];
				
				
				DecimalFormat df = new DecimalFormat("#.##");
				
				for(int j=0;j<10;j++) {
					for(int k=0;k<10;k++) {
						zjdfqz[j]+=score[k][j][0]/sa[k]/10;   //  品均值	
					}
					
					df.format(zjdfqz[j]);
					System.out.println(df.format(zjdfqz[j]));					
				}
			
				//计算结果放入list
				List<UpWeight> list = new ArrayList<UpWeight>();
				UpWeight weight = new UpWeight();
				weight.setHeight(Double.parseDouble(df.format(zjdfqz[0]-0.06)));
				weight.setKuang(Double.parseDouble(df.format(zjdfqz[1]-0.04)));
				weight.setYroute(Double.parseDouble(df.format(zjdfqz[2]+0.06)));
				weight.setSdwidth(Double.parseDouble(df.format(zjdfqz[3]+0.02)));
				weight.setSdheight(Double.parseDouble(df.format(zjdfqz[4]+0.02)));
				weight.setSidethick(Double.parseDouble(df.format(zjdfqz[5])));
				weight.setHole(Double.parseDouble(df.format(zjdfqz[6])));
				weight.setRibthick(Double.parseDouble(df.format(zjdfqz[7])));	
				weight.setHnumber(Double.parseDouble(df.format(zjdfqz[8])));
				weight.setSnumber(Double.parseDouble(df.format(zjdfqz[9])));
				list.add(weight);
				System.out.println(list.size());

	}
	
	@Test
	public void testBedwgt() {
		
		String did = "1-2";
		String[] bedId = did.split("-");
		
		List<bedwgtDf> wgtList = new ArrayList<bedwgtDf>();
		
		bedwgtDf bedwgtDf = new bedwgtDf();
		
		for(int i=0;i<bedId.length;i++) {
			
			Integer d = (Integer.parseInt(bedId[i])-1)*11+1;   //确定查询的开始
			
			bedwgtDf.setBid(d);
			
			List<bedwgtDf> list = wgService.selectBedAll(bedwgtDf);
			
			wgtList.addAll(list);    // 取得数据放入list中
		}
			
		Integer num = bedId.length;
			bedwgtDf zjdf_mid= new bedwgtDf();
			
			double[][][] score=new double[11][11][num];	//列/行/面
				for(int i=0;i<num;i++) {
					for(int j=0;j<11;j++) {
						zjdf_mid = wgtList.get(i*11+j);
						score[0][j][i]=zjdf_mid.getbLength();
						score[1][j][i]=zjdf_mid.getbWidth();
						score[2][j][i]=zjdf_mid.getbHeight();
						score[3][j][i]=zjdf_mid.getbCd();
						score[4][j][i]=zjdf_mid.getbXl();
						score[5][j][i]=zjdf_mid.getbXd();
						score[6][j][i]=zjdf_mid.getbZl();
						score[7][j][i]=zjdf_mid.getbZd();
						score[8][j][i]=zjdf_mid.getbRibthick();
						score[9][j][i]=zjdf_mid.getbHole();
						score[10][j][i]=zjdf_mid.getbStructure();
					}
				}
				
				for(int j=0;j<11;j++) {           // 计算                                                                //计算
					for(int k=10;k>j-1;k--) {
						for(int i=1;i<num;i++) {
							score[k][j][0]*=score[k][j][i];
						}
						score[k][j][0]=Math.pow(score[k][j][0], 1.0/num);
				//		System.out.println("k:"+k + "--"+ score[k][j][0] );
					}
				}   /// success
				
				for(int j=1;j<11;j++){
					for(int k=0;k<j;k++) {
						score[k][j][0]=1/score[j][k][0];
					//	System.out.println(score[k][j][0]);
					}
				}
				
				double[] sa=new double[11];
				for(int k=0;k<11;k++) {
					for(int j=0;j<11;j++) {
						sa[k]+=score[k][j][0];		
					}
					
					System.out.println(sa[k]);
				}
			//	System.out.println("----------");
				double[] zjdfqz=new double[11];
				
				
				DecimalFormat df = new DecimalFormat("#.##");
				
				for(int j=0;j<11;j++) {
					for(int k=0;k<11;k++) {
						zjdfqz[j]+=score[k][j][0]/sa[k]/11;   //  品均值	
					}
					
					df.format(zjdfqz[j]);
					System.out.println(df.format(zjdfqz[j]));					
				}
			
				//计算结果放入list
				List<BdWeight> list = new ArrayList<BdWeight>();
				BdWeight weight = new BdWeight();
				weight.setbLength(Double.parseDouble(df.format(zjdfqz[0]-0.01)));
				weight.setbWidth(Double.parseDouble(df.format(zjdfqz[1]-0.01)));
				weight.setbHeight(Double.parseDouble(df.format(zjdfqz[2]-0.01)));
				weight.setbCd(Double.parseDouble(df.format(zjdfqz[3])));
				weight.setbXl(Double.parseDouble(df.format(zjdfqz[4]+0.01)));
				weight.setbXd(Double.parseDouble(df.format(zjdfqz[5]+0.01)));
				weight.setbZl(Double.parseDouble(df.format(zjdfqz[6])));
				weight.setbZd(Double.parseDouble(df.format(zjdfqz[7]+0.01)));	
				weight.setbRibthick(Double.parseDouble(df.format(zjdfqz[8]-0.01)));
				weight.setbHole(Double.parseDouble(df.format(zjdfqz[9])));
				weight.setbStructure(Double.parseDouble(df.format(zjdfqz[10]+0.01)));
				list.add(weight);
				System.out.println(list.size()); 
	}
	
	@Test
	public void testUpRewgt() {
		System.out.println(wgService);
/*		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
        Date d= new Date();
       System.out.println(sdf.format(d));  
       
       wgService.insertBedResult(new bedDfResult(null, "zj-002",sdf.format(d)));*/
	
       List<bedDfResult> selectUpResult = wgService.selectBedResult();
		System.out.println(selectUpResult.get(0).getDfDate());
//		upwgtDf upwgtDf = new upwgtDf();
	/*	upwgtDf.setDfid(1);
		List<upwgtDf> wgtList = wgService.selectAll(upwgtDf);
		System.out.println(wgtList.size());*/
	}
}
