package com.ctbcbank.biglab.biglab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbcbank.biglab.biglab.bean.AttractionBean;
import com.ctbcbank.biglab.biglab.bean.Kaohsiung;
import com.ctbcbank.biglab.biglab.bean.NewTaipei;
import com.ctbcbank.biglab.biglab.bean.Taipei;
import com.ctbcbank.biglab.biglab.repository.AttractionBeanRepository;
import com.ctbcbank.biglab.biglab.repository.KaohsiungRepository;
import com.ctbcbank.biglab.biglab.repository.NewTaipeiRepository;
import com.ctbcbank.biglab.biglab.repository.TaipeiRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class AttractionBeanServiceCrudImplement implements AttractionBeanService{
	@Autowired
	private AttractionBeanRepository repository;
	@Autowired
	private KaohsiungRepository kaohsiungRepository;
	@Autowired
	private TaipeiRepository taipeiRepository;
	@Autowired
	private NewTaipeiRepository newTaipeiRepository;
	@Override
	public List<AttractionBean> getAllAttraction() {
		List<AttractionBean> attractionBeanList = new ArrayList<>();
		for(AttractionBean bean : repository.findAll()) {
			attractionBeanList.add(bean);
		}
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
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
	public List<AttractionBean> getAttractionBeanByCounty(String county) {
//		switch(county) {
//			case "Taipei":
////				List<Taipei> taipeiList = taipeiRepository.
//				break;
//		}
		
		List<AttractionBean> attractionBeanList = new ArrayList<>();
		for(AttractionBean bean : repository.findAll()) {
			attractionBeanList.add(bean);
		}
		List<AttractionBean> attractionAllList = new ArrayList<>();
		for(AttractionBean bean : attractionBeanList) {
			log.info("county={}",bean.getCounty());
			if(bean.getCounty().equals(getCountyChinese(county))){
				attractionAllList.add(bean);				
			}
		}
		return attractionAllList;
	}
	
	public static String getCountyChinese(String county) {
		switch(county) {
			case "Taipei": 
				return "臺北市";
			case "NewTaipei": 
				return "新北市";
			case "Tainan": 
				return "臺南市";
			case "Kaohsiung": 
				return "高雄市";
				
		}
		return null;
	}


	@Override
	public Taipei getTaipeiById(long id) {
		Taipei bean = taipeiRepository.findById(id).get();
		return bean;
	}


	@Override
	public NewTaipei getNewTaipeiById(long id) {
		NewTaipei bean = newTaipeiRepository.findById(id).get();
		return bean;
	}


	@Override
	public Kaohsiung getKaohsiungById(long id) {
		Kaohsiung bean = kaohsiungRepository.findById(id).get();
		return bean;
	}

}


