package com.ctbcbank.biglab.biglab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbcbank.biglab.biglab.bean.Taipei;
import com.ctbcbank.biglab.biglab.repository.TaipeiRepository;

@Service
public class TaipeiServiceCrudImplement implements TaipeiService {
	@Autowired
	private TaipeiRepository repository;

	@Override
	public List<Taipei> getAllTaipei() {
		List<Taipei> TaipeiList = new ArrayList<>();
		for (Taipei bean : repository.findAll()) {
			TaipeiList.add(bean);
		}
		return TaipeiList;
	}

}
