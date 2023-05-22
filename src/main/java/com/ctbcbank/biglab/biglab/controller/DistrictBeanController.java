package com.ctbcbank.biglab.biglab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.biglab.biglab.bean.DistrictBean;
import com.ctbcbank.biglab.biglab.bean.Kaohsiung;
import com.ctbcbank.biglab.biglab.bean.Taipei;
import com.ctbcbank.biglab.biglab.service.DistrictService;
import com.ctbcbank.biglab.biglab.service.KaohsiungService;
import com.ctbcbank.biglab.biglab.service.TaipeiService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/district")
public class DistrictBeanController {
	@Autowired
	private DistrictService districtService;
	@Autowired
	private TaipeiService taipeiService;
	@Autowired
	private KaohsiungService kaohsiungService;

	@GetMapping("/{county}/all")
	public List<DistrictBean> getAllDistrict(@PathVariable String county) {
		log.info("取回所有" + county + "的地區");
		return districtService.getAllDistrict(getCountyChinese(county));
	}

//	@GetMapping("/Tainan/all")
//	public List<DistrictBean> getAllDistrict() {
//		log.info("取回所有臺南的地區");
//		return districtService.getAllDistrict();
//	}
//	@GetMapping("/Taipei/all")
//	public List<Taipei> getAllTaipeiDistrict() {
//		log.info("取回所有台北的地區");
//		return taipeiService.getAllTaipei();
//	}
//	//Kaohsiung.setCounty("高雄市");
//	@GetMapping("/Kaohsiung/all")
//	public List<Kaohsiung> getAllKaohsiungDistrict() {
//		log.info("取回所有高雄的地區");
//		return kaohsiungService.getAllKaohsiung();
//	}
	public static String getCountyChinese(String county) {
		switch (county) {
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
}
