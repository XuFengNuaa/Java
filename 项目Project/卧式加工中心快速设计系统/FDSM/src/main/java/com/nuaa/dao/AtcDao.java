package com.nuaa.dao;

import java.util.List;

import com.nuaa.bean.Atc;
public interface AtcDao {
	
	List<Atc> findSelectiveAtc(Atc record);
	
	List<Atc> findSelectiveMHAtc(Atc record);
	
	void deleteAtcById(Integer toolid);

    List<Atc> findReasonAtc(Atc record);
	
	void insertAtc(Atc record);

    void updateAtcByIdSelective(Atc record);

    void updateAtcById(Atc record);
}