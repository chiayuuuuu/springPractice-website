package com.ctbcbank.biglab.biglab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbcbank.biglab.biglab.bean.AttractionBean;
import com.ctbcbank.biglab.biglab.bean.Taipei;
import com.ctbcbank.biglab.biglab.repository.AttractionBeanRepository;
import com.ctbcbank.biglab.biglab.repository.KaohsiungRepository;
import com.ctbcbank.biglab.biglab.repository.TaipeiRepository;

@Service
public class AttractionBeanServiceCrudImplement implements AttractionBeanService{
	@Autowired
	private AttractionBeanRepository repository;
	@Autowired
	private KaohsiungRepository kaohsiungRepository;
	@Autowired
	private TaipeiRepository taipeiRepository;
	@Override
	public List<AttractionBean> getAllAttraction() {
		List<AttractionBean> attractionBeanList = new ArrayList<>();
		for(AttractionBean bean : repository.findAll()) {
			attractionBeanList.add(bean);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return attractionBeanList;
	}


	@Override
	public AttractionBean createOrUpdatedAttraction(AttractionBean bean) {
		return repository.save(bean);
	}


	@Override
	public AttractionBean getAttractionBeanById(long id) {
		AttractionBean bean = repository.findById(id).get();
		return bean;
	}


	@Override
	public void removeAttractionById(long id) {
		repository.deleteById(id);
		
	}


	@Override
	public AttractionBean getAttractionBeanByCounty(String county) {
		switch(county) {
			case "Taipei":
//				List<Taipei> taipeiList = taipeiRepository.
				break;
		}
		List<AttractionBean> attractionBeanList = new ArrayList<>();
		for(AttractionBean bean : repository.findAll()) {
			attractionBeanList.add(bean);
		}
		return null;
	}

}


