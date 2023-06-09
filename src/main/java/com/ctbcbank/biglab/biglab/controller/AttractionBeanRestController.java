package com.ctbcbank.biglab.biglab.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctbcbank.biglab.biglab.bean.AttractionBean;
import com.ctbcbank.biglab.biglab.bean.Kaohsiung;
import com.ctbcbank.biglab.biglab.bean.NewTaipei;
import com.ctbcbank.biglab.biglab.bean.Taipei;
import com.ctbcbank.biglab.biglab.service.AttractionBeanService;
import com.ctbcbank.biglab.biglab.service.KaohsiungService;
import com.ctbcbank.biglab.biglab.service.NewTaipeiService;
import com.ctbcbank.biglab.biglab.service.TaipeiService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("api/attraction")
public class AttractionBeanRestController {

	@Autowired
	private AttractionBeanService attractionBeanService;
	@Autowired
	private TaipeiService taipeiService;
	@Autowired
	private NewTaipeiService newTaipeiService;
	@Autowired
	private KaohsiungService kaohsiungService;

	@GetMapping("/all")
	public List<AttractionBean> getData() {
		return attractionBeanService.getAllAttraction();
	}

	@GetMapping("/{ApiId}")
	public ResponseEntity<?> getAttractionById(@PathVariable String ApiId) {
		AttractionBean bean = attractionBeanService.getAttractionBeanById(Long.parseLong(ApiId));
		return new ResponseEntity<>(bean, HttpStatus.OK);
	}

//	@GetMapping("/{county}/{ApiId}")
//	public ResponseEntity<?> getAttractionById(@PathVariable String county, @PathVariable String ApiId) {
////		AttractionBean bean = attractionBeanService.getAttractionBeanById(Long.parseLong(ApiId));
////		return new ResponseEntity<>(bean, HttpStatus.OK);	
//		switch (county) {
//		case "Taipei":
//			Taipei bean = attractionBeanService.getTaipeiById(Long.parseLong(ApiId));
//			return new ResponseEntity<>(bean, HttpStatus.OK);	
//		case "NewTaipei":
//			NewTaipei bean2 = attractionBeanService.getNewTaipeiById(Long.parseLong(ApiId));
//			return new ResponseEntity<>(bean2, HttpStatus.OK);	
//		case "Tainan":
//			AttractionBean bean3 = attractionBeanService.getAttractionBeanById(Long.parseLong(ApiId));
//			return new ResponseEntity<>(bean3, HttpStatus.OK);	
//		case "Kaohsiung":
//			Kaohsiung bean4 = attractionBeanService.getKaohsiungById(Long.parseLong(ApiId));
//			return new ResponseEntity<>(bean4, HttpStatus.OK);	
//		}
//		return null;
//	}

	@GetMapping("/all/{county}")
	public List<?> getAttractionByCounty(@PathVariable String county) {
//		AttractionBean bean = attractionBeanService.getAttractionBeanByCounty(county);
		switch (county) {
		case "Taipei":
//			List<Taipei> taipeiList = taipeiService.getAllDistrict();
			log.info("taipei size={}", taipeiService.getAllTaipei().size());
			return taipeiService.getAllTaipei();
		case "NewTaipei":
			log.info("NewTaipei size={}", newTaipeiService.getAllNewTaipei().size());
			return newTaipeiService.getAllNewTaipei();
		case "Tainan":
			log.info("tainan size={}", attractionBeanService.getAllAttraction().size());
			return attractionBeanService.getAllAttraction();
		case "Kaohsiung":
//			List<Taipei> taipeiList = taipeiService.getAllDistrict();
			log.info("Kaohsiung size={}", kaohsiungService.getAllKaohsiung().size());
			return kaohsiungService.getAllKaohsiung();
		}
		return null;
	}

	@PostMapping("")
	public ResponseEntity<?> createOrUpdateNewAttraction(@RequestBody AttractionBean bean) {
		if (bean.getId() == 0) {
			attractionBeanService.createOrUpdatedAttraction(bean);
			return new ResponseEntity<>(bean, HttpStatus.OK);
		} else {
			Date createDate = attractionBeanService.getAttractionBeanById(bean.getId()).getCreatedAt();
			AttractionBean updatedBean = bean;
			updatedBean.setCreatedAt(createDate);
			attractionBeanService.createOrUpdatedAttraction(updatedBean);
			log.info("get updatedBean:{}", bean);
			return new ResponseEntity<>(updatedBean, HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removePublicAPIById(@PathVariable String id) {
		attractionBeanService.removeAttractionById(Long.parseLong(id));
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
