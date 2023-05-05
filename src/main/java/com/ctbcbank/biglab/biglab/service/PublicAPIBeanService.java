package com.ctbcbank.biglab.biglab.service;

import java.util.List;

import com.ctbcbank.biglab.biglab.bean.AttractionBean;
import com.ctbcbank.biglab.biglab.bean.PublicAPIBean;

public interface PublicAPIBeanService {
	List<AttractionBean> getPublicAPIData(String uri);
	List<PublicAPIBean> getAllPublicAPI();
	void saveApiData(List<AttractionBean> bean);
	PublicAPIBean createOrUpdatedAPI(PublicAPIBean bean);
	PublicAPIBean getPublicAPIBeanById(long id);
	void removePublicAPIBeanById(long id);
}
