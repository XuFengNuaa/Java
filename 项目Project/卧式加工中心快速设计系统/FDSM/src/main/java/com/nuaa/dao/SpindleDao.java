package com.nuaa.dao;

import java.util.List;

import com.nuaa.bean.Spindle;

public interface SpindleDao {
	void deleteSdById(Integer sindleId);

	void insertSd(Spindle record);

	List<Spindle> findSelectiveSd(Spindle record);
	
	List<Spindle> findSelectiveMHSd(Spindle record);

	void updateSdByIdSelective(Spindle record);
	
	List<Spindle> findReasonSd(Spindle record);

	void updateSdById(Spindle record);

}