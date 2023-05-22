package com.ctbcbank.biglab.biglab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbcbank.biglab.biglab.bean.NewTaipei;
import com.ctbcbank.biglab.biglab.repository.NewTaipeiRepository;

@Service
public class NewTaipeiServiceCrudImplement implements NewTaipeiService {
	@Autowired
	private NewTaipeiRepository repository;

	@Override
	public List<NewTaipei> getAllNewTaipei() {
		List<NewTaipei> NewTaipeiList = new ArrayList<>();
		for (NewTaipei bean : repository.findAll()) {
			NewTaipeiList.add(bean);
		}
		return NewTaipeiList;
	}

}
