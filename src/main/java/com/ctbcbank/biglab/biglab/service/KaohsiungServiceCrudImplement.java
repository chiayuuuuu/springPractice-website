package com.ctbcbank.biglab.biglab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctbcbank.biglab.biglab.bean.Kaohsiung;
import com.ctbcbank.biglab.biglab.repository.KaohsiungRepository;

@Service
public class KaohsiungServiceCrudImplement implements KaohsiungService {
	@Autowired
	private KaohsiungRepository repository;

	@Override
	public List<Kaohsiung> getAllKaohsiung() {
		List<Kaohsiung> KaohsiungList = new ArrayList<>();
		for (Kaohsiung bean : repository.findAll()) {
			KaohsiungList.add(bean);
		}
		return KaohsiungList;
	}

}
