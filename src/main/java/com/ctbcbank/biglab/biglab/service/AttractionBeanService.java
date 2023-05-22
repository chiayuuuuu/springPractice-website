package com.ctbcbank.biglab.biglab.service;

import java.util.List;

import com.ctbcbank.biglab.biglab.bean.AttractionBean;
import com.ctbcbank.biglab.biglab.bean.Kaohsiung;
import com.ctbcbank.biglab.biglab.bean.NewTaipei;
import com.ctbcbank.biglab.biglab.bean.Taipei;

public interface AttractionBeanService {
	List<AttractionBean> getAllAttraction();
	AttractionBean createOrUpdatedAttraction(AttractionBean bean);
	AttractionBean getAttractionBeanById(long id);
	Taipei getTaipeiById(long id);
	NewTaipei getNewTaipeiById(long id);
	Kaohsiung getKaohsiungById(long id);
	List<AttractionBean> getAttractionBeanByCounty(String county);
	void removeAttractionById(long id);
//	AttractionBean ModifyAttraction(AttractionBean bean);

}
