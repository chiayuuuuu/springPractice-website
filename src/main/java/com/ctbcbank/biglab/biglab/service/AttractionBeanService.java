package com.ctbcbank.biglab.biglab.service;

import java.util.List;

import com.ctbcbank.biglab.biglab.bean.AttractionBean;

public interface AttractionBeanService {
	List<AttractionBean> getAllAttraction();
	AttractionBean createOrUpdatedAttraction(AttractionBean bean);
	AttractionBean getAttractionBeanById(long id);
	AttractionBean getAttractionBeanByCounty(String county);
	void removeAttractionById(long id);
//	AttractionBean ModifyAttraction(AttractionBean bean);

}
