package com.nuaa.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.nuaa.bean.Atc;
import com.nuaa.bean.Screw;
import com.nuaa.bean.Slideway;
import com.nuaa.bean.Spindle;
import com.nuaa.bean.Upright;
import com.nuaa.bean.Whole;
import com.nuaa.bean.Workbed;
import com.nuaa.bean.Workpiece;
import com.nuaa.dao.AtcDao;
import com.nuaa.dao.ScrewDao;
import com.nuaa.dao.SlidewayDao;
import com.nuaa.dao.SpindleDao;
import com.nuaa.dao.UprightDao;
import com.nuaa.dao.WholeDao;
import com.nuaa.dao.WorkbedDao;
import com.nuaa.dao.WorkpieceDao;
import com.nuaa.service.IPartsMgService;

@Service
public class PartsMgServiceImpl implements IPartsMgService {
	private final WorkpieceDao workpieceDao;  //注入dao层,每个都要注入
	private final SpindleDao spindleDao;
	private final AtcDao atcDao;
	private final ScrewDao screwDao;
	private final SlidewayDao slidewayDao;
	private final UprightDao uprightDao;
	private final WorkbedDao workbedDao;
	private final WholeDao wholeDao;

	public PartsMgServiceImpl(WorkpieceDao workpieceDao, SpindleDao spindleDao, AtcDao atcDao, ScrewDao screwDao, SlidewayDao slidewayDao, UprightDao uprightDao, WorkbedDao workbedDao, WholeDao wholeDao) {
		this.workpieceDao = workpieceDao;
		this.spindleDao = spindleDao;
		this.atcDao = atcDao;
		this.screwDao = screwDao;
		this.slidewayDao = slidewayDao;
		this.uprightDao = uprightDao;
		this.workbedDao = workbedDao;
		this.wholeDao = wholeDao;
	}

	public void deleteWpById(Integer workid) {
		workpieceDao.deleteWpById(workid);
		
	}

	public void insertWp(Workpiece record) {
		workpieceDao.insertWp(record);
		
	}

	public List<Workpiece> findSelectiveWp(Workpiece record) {
		return workpieceDao.findSelectiveWp(record);
	}

	public void updateWpByIdSelective(Workpiece record) {
		workpieceDao.updateWpByIdSelective(record);
		
	}
	@Override
	public List<Workpiece> findWpMHSelective(Workpiece record) {
		// TODO Auto-generated method stub
		return workpieceDao.findWpMHSelective(record);
	}
	
	public void updateWpById(Workpiece record) {
		workpieceDao.updateWpById(record);
		
	}
//-------------------spindle.java-------------------------------
	@Override
	public void deleteSdById(Integer sindleId) {
		spindleDao.deleteSdById(sindleId);
		
	}

	@Override
	public List<Spindle> findSelectiveMHSd(Spindle record) {
		// TODO Auto-generated method stub
		return spindleDao.findSelectiveMHSd(record);
	}
	
	@Override
	public void insertSd(Spindle record) {
		spindleDao.insertSd(record);
		
	}

	@Override
	public List<Spindle> findSelectiveSd(Spindle record) {
		
		return spindleDao.findSelectiveSd(record);
	}

	@Override
	public void updateSdByIdSelective(Spindle record) {
		spindleDao.updateSdByIdSelective(record);
		
	}

	@Override
	public void updateSdById(Spindle record) {
		spindleDao.updateSdById(record);
		
	}
//-----------------------atc.java---------------------------------
	@Override
	public List<Atc> findSelectiveAtc(Atc record) {
		
		return atcDao.findSelectiveAtc(record);
	}

	@Override
	public void deleteAtcById(Integer toolid) {
		atcDao.deleteAtcById(toolid);
		
	}

	@Override
	public List<Atc> findSelectiveMHAtc(Atc record) {
		// TODO Auto-generated method stub
		return atcDao.findSelectiveMHAtc(record);
	}
	
	@Override
	public void insertAtc(Atc record) {
		atcDao.insertAtc(record);
		
	}

	@Override
	public void updateAtcByIdSelective(Atc record) {
		atcDao.updateAtcByIdSelective(record);
		
	}

	@Override
	public void updateAtcById(Atc record) {
		atcDao.updateAtcById(record);
		
	}
//----------------Screw.java--------------------------------
	@Override
	public void deleteSwById(Integer swid) {
		screwDao.deleteSwById(swid);
		
	}

	@Override
	public void insertSw(Screw record) {
		screwDao.insertSw(record);
		
	}

	@Override
	public List<Screw> findSwSelective(Screw record) {
		
		return screwDao.findSwSelective(record);
	}

	@Override
	public List<Screw> findSwMHSelective(Screw record) {
		
		return screwDao.findSwMHSelective(record);
	}

	@Override
	public void updateSwByIdSelective(Screw record) {
		screwDao.updateSwByIdSelective(record);
		
	}

	@Override
	public void updateSwById(Screw record) {
		screwDao.updateSwById(record);	
	}
//-------------------slideway.java-------------------------------
	@Override
	public void deleteSdwayById(Integer daoguiId) {
		slidewayDao.deleteSdwayById(daoguiId);		
	}

	@Override
	public void insertSdway(Slideway record) {
		slidewayDao.insertSdway(record);
	}

	@Override
	public List<Slideway> findSdwaySelective(Slideway record) {
		return slidewayDao.findSdwaySelective(record);
	}

	@Override
	public List<Slideway> findSdwayMHSelective(Slideway record) {

		return slidewayDao.findSdwayMHSelective(record);
	}

	@Override
	public void updateSdwayByIdSelective(Slideway record) {
		slidewayDao.updateSdwayByIdSelective(record);
	}

	@Override
	public void updateSdwayById(Slideway record) {
		slidewayDao.updateSdwayById(record);
	}
//---------------------upright.java----------------------------
	@Override
	public void deleteUpById(Integer uprightId) {
		uprightDao.deleteUpById(uprightId);
		
	}

	@Override
	public void insertUp(Upright record) {
		uprightDao.insertUp(record);
		
	}

	@Override
	public List<Upright> findUpSelective(Upright record) {
		
		return uprightDao.findUpSelective(record);
	}

	@Override
	public List<Upright> findUpMHSelective(Upright record) {
		
		return uprightDao.findUpMHSelective(record);
	}

	@Override
	public void updateUpByIdSelective(Upright record) {
		uprightDao.updateUpByIdSelective(record);
		
	}

	@Override
	public void updateUpById(Upright record) {
		uprightDao.updateUpById(record);
		
	}
//--------------------bed.java------------------------------------------------------
	@Override
	public void deleteWbById(Integer bedid) {
		workbedDao.deleteWbById(bedid);
		
	}

	@Override
	public void insertWb(Workbed record) {
		workbedDao.insertWb(record);
		
	}

	@Override
	public List<Workbed> findWbMHSelective(Workbed record) {
		
		return workbedDao.findWbMHSelective(record);
	}

	@Override
	public List<Workbed> findWbSelective(Workbed record) {
		
		return workbedDao.findWbSelective(record);
	}

	@Override
	public void updateWbByIdSelective(Workbed record) {
		workbedDao.updateWbByIdSelective(record);
		
	}

	@Override
	public void updateWbById(Workbed record) {
		workbedDao.updateWbById(record);
		
	}
//------------------------whole.java-----------------------
	@Override
	public void deleteWhById(Integer mtId) {
		wholeDao.deleteWhById(mtId);
		
	}

	@Override
	public void insertWh(Whole record) {
		wholeDao.insertWh(record);
		
	}

	@Override
	public List<Whole> findWhSelective(Whole record) {
		
		return wholeDao.findWhSelective(record);
	}

	@Override
	public List<Whole> findWhMHSelective(Whole record) {
		
		return wholeDao.findWhMHSelective(record);
	}

	@Override
	public void updateWhByIdSelective(Whole record) {
		wholeDao.updateWhByIdSelective(record);
		
	}

	@Override
	public void updateWhById(Whole record) {
		wholeDao.updateWhById(record);
		
	}



}
