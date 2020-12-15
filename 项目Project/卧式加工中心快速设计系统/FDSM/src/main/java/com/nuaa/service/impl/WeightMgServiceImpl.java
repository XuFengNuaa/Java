package com.nuaa.service.impl;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nuaa.bean.BdWeight;
import com.nuaa.bean.UpWeight;
import com.nuaa.bean.bedDfResult;
import com.nuaa.bean.bedwgtDf;
import com.nuaa.bean.upDfResult;
import com.nuaa.bean.upwgtDf;
import com.nuaa.dao.BdWeightDao;
import com.nuaa.dao.UpDfResultDao;
import com.nuaa.dao.UpWeightDao;
import com.nuaa.dao.bedDfResultDao;
import com.nuaa.dao.bedwgtDfDao;
import com.nuaa.dao.upwgtDfDao;
import com.nuaa.service.IWeightMgService;

@Service
public class WeightMgServiceImpl implements IWeightMgService {
	private final UpWeightDao upWeightMg;   //注入dao
	private  final BdWeightDao bdWeightMg;
	private  final upwgtDfDao dfDao;
	private  final bedwgtDfDao bedwgtDfDao;
	private  final upwgtDfDao upwgtDfDao;
	private  final UpDfResultDao upResultDao;
	private  final bedDfResultDao bedResultDao;

	public WeightMgServiceImpl(UpWeightDao upWeightMg,BdWeightDao bdWeightMg,upwgtDfDao dfDao
			,bedwgtDfDao bedwgtDfDao,upwgtDfDao upwgtDfDao,UpDfResultDao upResultDao,bedDfResultDao bedResultDao) {
		this.upWeightMg = upWeightMg;
		this.bdWeightMg = bdWeightMg;
		this.dfDao = dfDao;
		this.bedwgtDfDao = bedwgtDfDao;
		this.upwgtDfDao = upwgtDfDao;
		this.upResultDao = upResultDao;
		this.bedResultDao = bedResultDao;
	}
	// public void 操作weight的方法，返回相应值

	@Override
	public void deleteById(Integer wid) {
		upWeightMg.deleteById(wid);
		
	}

	@Override
	public void insert(UpWeight record) {
		upWeightMg.insert(record);
		
	}

	@Override
	public List<UpWeight> selectUpById(UpWeight record) {
		
		return upWeightMg.selectUpById(record);
	}

	@Override
	public void updateById(UpWeight record) {
		
		upWeightMg.updateById(record);
		
	}
//-------------------------------------------------------------------
	@Override
	public void deleteBdWById(Integer bid) {
		bdWeightMg.deleteBdWById(bid);
		
	}

	@Override
	public void insertBdW(BdWeight record) {
		bdWeightMg.insertBdW(record);
		
	}

	@Override
	public List<BdWeight> selectBd(BdWeight record) {
		
		return bdWeightMg.selectBd(record);
	}

	@Override
	public void updateBdWById(BdWeight record) {
		bdWeightMg.updateBdWById(record);
		
	}
//-------------------upwgt-------------------------
	@Override
	public void deleteUpByPrimaryKey(Integer dfid) {
		dfDao.deleteUpByPrimaryKey(dfid);
		
	}

	@Override
	public void insert(upwgtDf record) {
		dfDao.insert(record);
		
	}

	@Override
	public List<upwgtDf> selectAll(upwgtDf record) {
		
		return dfDao.selectAll(record);
	}
//--------------------bedWgt---------------------

	@Override
	public void deleteBedById(Integer bid) {
		bedwgtDfDao.deleteBedById(bid);
	}

	@Override
	public void insert(bedwgtDf record) {
		bedwgtDfDao.insert(record);
		
	}

	@Override
	public List<bedwgtDf> selectBedAll(bedwgtDf record) {
		return bedwgtDfDao.selectBedAll(record);
	}

	@Override
	public List<UpWeight> reasonUpWgt(String uid) {
	
		String[] upId = uid.split("-");
		
		List<upwgtDf> wgtList = new ArrayList<upwgtDf>();
		
		upwgtDf upwgtDf = new upwgtDf();
		
		for(int i=0;i<upId.length;i++) {
			
			Integer d = (Integer.parseInt(upId[i])-1)*10+1;   //确定查询的开始
			upwgtDf.setDfid(d);
			
			List<upwgtDf> list = upwgtDfDao.selectAll(upwgtDf);
			
			wgtList.addAll(list);    // 取得数据放入list中
		}
			
		Integer num = upId.length;
		
		upwgtDf zjdf_mid=new upwgtDf();
		
		double[][][] score=new double[10][10][num];	//列/行/面
			for(int i=0;i<num;i++) {
				for(int j=0;j<10;j++) {
					zjdf_mid = wgtList.get(i*10+j);    // List中 依次取值
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
				
		//		System.out.println(sa[k]);
			}
		//	System.out.println("----------");
			double[] zjdfqz=new double[10];
			
			
			DecimalFormat df = new DecimalFormat("#.##");
			
			for(int j=0;j<10;j++) {
				for(int k=0;k<10;k++) {
					zjdfqz[j]+=score[k][j][0]/sa[k]/10;   //  品均值	
				}
				
				df.format(zjdfqz[j]);
		//		System.out.println(df.format(zjdfqz[j]));					
			}
		
			//计算结果放入list
			List<UpWeight> list = new ArrayList<UpWeight>();
			UpWeight weight = new UpWeight();
			weight.setHeight(Double.parseDouble(df.format(zjdfqz[0]-0.05)));
			weight.setKuang(Double.parseDouble(df.format(zjdfqz[1]-0.04)));
			weight.setYroute(Double.parseDouble(df.format(zjdfqz[2]+0.05)));
			weight.setSdwidth(Double.parseDouble(df.format(zjdfqz[3]+0.02)));
			weight.setSdheight(Double.parseDouble(df.format(zjdfqz[4]+0.02)));
			weight.setSidethick(Double.parseDouble(df.format(zjdfqz[5])));
			weight.setHole(Double.parseDouble(df.format(zjdfqz[6])));
			weight.setRibthick(Double.parseDouble(df.format(zjdfqz[7])));	
			weight.setHnumber(Double.parseDouble(df.format(zjdfqz[8])));
			weight.setSnumber(Double.parseDouble(df.format(zjdfqz[9])));
			list.add(weight);
	//		System.out.println(list.size());
			
		return list;
	}
//---------------Upweight_result-------------------
	@Override
	public void deleteByUpId(Integer zjId) {
		upResultDao.deleteByUpId(zjId);
		
	}

	@Override
	public void insertUpResult(upDfResult record) {
		upResultDao.insertUpResult(record);
		
	}

	@Override
	public List<upDfResult> selectUpResult() {
		
		return upResultDao.selectUpResult();
	}
	
	@Override
	public void deleteByBedId(Integer zjId) {
		bedResultDao.deleteByBedId(zjId);
		
	}
	@Override
	public void insertBedResult(bedDfResult record) {
		bedResultDao.insertBedResult(record);
		
	}
	@Override
	public List<bedDfResult> selectBedResult() {
		
		return bedResultDao.selectBedResult();
	}

	@Override
	public List<BdWeight> reasonBedWgt(String did) {
		
		String[] bedId = did.split("-");
		
		List<bedwgtDf> wgtList = new ArrayList<bedwgtDf>();
		
		bedwgtDf bedwgtDf = new bedwgtDf();
		
		for(int i=0;i<bedId.length;i++) {
			
			Integer d = (Integer.parseInt(bedId[i])-1)*11+1;   //确定查询的开始 11个
			
			bedwgtDf.setBid(d);
			
			List<bedwgtDf> list = bedwgtDfDao.selectBedAll(bedwgtDf);
			
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
				
			//	System.out.println(sa[k]);
			}
		//	System.out.println("----------");
			double[] zjdfqz=new double[11];
			
			
			DecimalFormat df = new DecimalFormat("#.##");
			
			for(int j=0;j<11;j++) {
				for(int k=0;k<11;k++) {
					zjdfqz[j]+=score[k][j][0]/sa[k]/11;   //  品均值	
				}
				
				df.format(zjdfqz[j]);
		//		System.out.println(df.format(zjdfqz[j]));					
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
			
		return list;
	}

	

}
