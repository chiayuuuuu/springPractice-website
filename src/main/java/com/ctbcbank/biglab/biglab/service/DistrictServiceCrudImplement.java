package com.ctbcbank.biglab.biglab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbcbank.biglab.biglab.bean.DistrictBean;
import com.ctbcbank.biglab.biglab.repository.DistrictBeanRepository;

@Service
public class DistrictServiceCrudImplement implements DistrictService {
	@Autowired
	private DistrictBeanRepository repository;

	@Override
	public List<DistrictBean> getAllDistrict(String county) {
		List<DistrictBean> districtBeanList = new ArrayList<>();
		for (DistrictBean bean : repository.findAll()) {
			if(bean.getCounty().equals(county)) {
				districtBeanList.add(bean);				
			}
		}
		return districtBeanList;
	}

}
